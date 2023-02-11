package telran.java2022.stock.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.util.Precision;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Let;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DataDto;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.dto.StockDtoAPI;
import telran.java2022.stock.dto.StockProfitDto;
import telran.java2022.stock.dto.exeptions.StockNotFoundException;
import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;
//import telran.java2022.stock.model.StockForParse;
import telran.java2022.stock.model.StockProfit;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	final StockRepository repository;

	final ModelMapper modelMapper;

	@Override
	public StockDto findStockByDate(String symbol, String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LabelDate labelDate = new LabelDate(symbol, LocalDate.parse(date, formatter));

		Stock stock = repository.findById(labelDate)
				.orElseThrow(() -> new StockNotFoundException(symbol, LocalDate.parse(date, formatter)));
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public Iterable<StockDto> findStockByName(String symbol) {
		return repository.findStockById_Symbol(symbol).map(s -> modelMapper.map(s, StockDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<StockDto> findStocksByPeriod(String symbol, DatePeriodDto datePeriodDto) {
		return repository
				.findStocksByIdSymbolAndIdDateBetween(symbol, datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
				.map(s -> modelMapper.map(s, StockDto.class))
				.collect(Collectors.toList());
	}
	
	//Statistics
	@Override
	public StockDto findMaxBySymbol(String symbol) {
		Stock stock = repository.findFirstByIdSymbolOrderByCloseDesc(symbol);
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public StockDto findMinBySymbol(String symbol) {
		Stock stock = repository.findFirstByIdSymbolOrderByClose(symbol);
		return modelMapper.map(stock, StockDto.class);
	}
	

//  Admin methods
	@Override
	public Boolean downloadCSVandParseToDB(String symbol) {
//		final String period1 = "1";
		final String period1 = "1359590400";
		final String period2 = "16725312000";
		final String BASE_URL = "https://query1.finance.yahoo.com/v7/finance/download/" + symbol;

		File file = new File("‪/Users/HP/tel-ran-project/" + symbol);
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(BASE_URL).queryParam("period1", period1)
				.queryParam("period2", period2).build();

		try {
			FileUtils.copyURLToFile(new URL(builder.toString()), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		List<Stock> beans = new ArrayList<>();
		try {
			beans = new CsvToBeanBuilder<Stock>(new FileReader(file)).withType(Stock.class).build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		beans.forEach(b -> b.getId().setSymbol(symbol));
//		beans.forEach(a -> System.out.println(a));

		if (!(repository.existsById(beans.get(0).getId())
				&& repository.existsById(beans.get(beans.size() - 1).getId()))) {
			repository.saveAll(beans);
		}
		return true;
	}
	
	
    @Override
    public Iterable<StockProfitDto> getMinAndMaxYearProfit(String symbol, String from, String to,
	    Integer periodInYears) {

	List<StockProfitDto> returnedListOfStatistics = new ArrayList<>();

	LocalDate fromDate = LocalDate.parse(from);
	LocalDate toDate = LocalDate.parse(to);
	
	// Проверка на корректность дат
	if (toDate.isAfter(LocalDate.now())) {
	    Stock lastDayForThisLabel = repository.findTopByIdSymbolOrderByIdDateDesc(symbol);
	    toDate = lastDayForThisLabel.getId()
		    .getDate();
	}
	final Integer localFinalPeriodInYears = periodInYears;
//	periodInYears *= 365;

	// Stream 2 -> to List stocksToDates
	System.out.println(fromDate);
	System.out.println(fromDate.plusYears(periodInYears));
	
	List<Stock> stocksToDate = repository
		.findStocksByIdSymbolAndIdDateBetweenOrderByIdDateDesc(symbol, fromDate.plusYears(periodInYears),
			toDate.plusYears(periodInYears))
		.collect(Collectors.toList());
	
	//System.out.println(stocksToDate.get(stocksToDate.size()-1));
	//stocksToDate.forEach(s-> System.out.println(s));

	// Stream 1 -> to List stocksFromDate
	List<Stock> stocksFromDate = repository.findStocksByIdSymbolAndIdDateBetweenOrderByIdDateDesc(symbol, fromDate, toDate)
		.limit(stocksToDate.size())
		.collect(Collectors.toList());
	
	
	//System.out.println(stocksFromDate.get(stocksFromDate.size()-1));

	List<StockProfit> listOfStockProfits = new ArrayList<>();

	// Stream 3
	for (int i = 0; i < stocksFromDate.size(); i++) {
	    StockProfit stockProfit = new StockProfit();
	    stockProfit.setSymbol(stocksFromDate.get(i)
		    .getId()
		    .getSymbol());
	    stockProfit.setDateFrom(stocksFromDate.get(i)
		    .getId()
		    .getDate());
	    stockProfit.setDateTo(stocksToDate.get(i)
		    .getId()
		    .getDate());
	    stockProfit.setCloseStart(stocksFromDate.get(i)
		    .getClose());
	    stockProfit.setCloseEnd(stocksToDate.get(i)
		    .getClose());
	    listOfStockProfits.add(stockProfit);
	}

	// Sorting array by profit field for fiding MIN and MAX
	listOfStockProfits.sort((s1, s2) -> s1.getProfit(localFinalPeriodInYears)
		.compareTo(s2.getProfit(localFinalPeriodInYears)));

	// Statistics
	// Minimum StockProfit
	StockProfitDto stockProfitDtoMin = modelMapper.map(listOfStockProfits.get(0), StockProfitDto.class);
	stockProfitDtoMin.setYearProfit(Precision.round(listOfStockProfits.get(0)
		.getProfit(localFinalPeriodInYears), 2));

	// Maximum StockProfit
	StockProfitDto stockProfitDtoMax = modelMapper.map(listOfStockProfits.get(listOfStockProfits.size() - 1),
		StockProfitDto.class);
	stockProfitDtoMax.setYearProfit(Precision.round(listOfStockProfits.get(listOfStockProfits.size() - 1)
		.getProfit(localFinalPeriodInYears), 2));

	// Adding MIN and MAX StockProfits to returning list
	returnedListOfStatistics.add(stockProfitDtoMin);
	returnedListOfStatistics.add(stockProfitDtoMax);
	return returnedListOfStatistics;
    }




	
    
    

	// метод для загрузки данных и апи, бесплатной версии не достаточно
//	@Override
//	public Integer downloadDataFromAPIForStockByPeriod(String label, DatePeriodDto datePeriodDto) {
//		ResponseEntity<StockDtoAPI> responseEntity = StockAPI.request(label, datePeriodDto.getDateFrom(),
//				datePeriodDto.getDateTo());
//		List<DataDto> list = responseEntity.getBody().getData();
//		for (int i = 0; i < list.size(); i++) {
//			DataDto dataDto = modelMapper.map(list.get(i), DataDto.class);
//
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'+'0000");
//			LabelDate id = new LabelDate(dataDto.getSymbol(), LocalDate.parse(dataDto.getDate(), formatter));
//			double close = Double.parseDouble(dataDto.getClose());
//			Stock stock = new Stock(id, close);
//			System.out.println(id.getDate());
//			repository.save(stock);
//		}
//		return list.size();
//	}

	// метод который парсит данные с файла. Устарел...
//	@Override
//	public void parseData() throws IOException, URISyntaxException {
//		ParseData parseData = new ParseData(repository);
//		parseData.CSVMaptoObject();
//	}
	
	


//	@Override
//	public StockDto findMaxBySymbolInPeriod(String symbol, DatePeriodDto datePeriodDto) {
//		Stock stock = repository.findFirstById_Symbol_OrderByClose_DateBetween(symbol, datePeriodDto.getDateFrom(),
//				datePeriodDto.getDateTo());
//		return modelMapper.map(stock, StockDto.class);
//	}

//	@Override
//	public StockProfitDto findProfit(String symbol, Double sum, DatePeriodDto datePeriodDto) {
////		calculatePeriod(datePeriodDto);
//		int yers = Period.between(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo()).getYears();
//		double startPrice = repository.findStockByDate(datePeriodDto.getDateFrom()).getClose();
//		double endPrice = repository.findStockByDate(datePeriodDto.getDateTo()).getClose();
//
//		double amountAtEnd = ((sum / startPrice) * endPrice - sum)/sum*100;
//		double profitability = amountAtEnd/sum*100;
//		System.out.println(amountAtEnd);
//		
//
//		return null;
//	}
//
//	private double calculatePeriod(DatePeriodDto datePeriodDto) {
//		LocalDate dateFrom = datePeriodDto.getDateFrom();
//		LocalDate dateTo = datePeriodDto.getDateTo();
//
//		Period period = Period.between(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo());
//		int fullYear = period.getYears();
//		int month = period.getMonths();
//		int days = period.getDays();
//		return fullYear;
//	}
//
//	public static boolean isLeapYear(int year) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
//	}

}
