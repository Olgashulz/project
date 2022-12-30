package telran.java2022.stock.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import telran.java2022.API.StockAPI;
import telran.java2022.parsing.service.ParseData;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DataDto;
import telran.java2022.stock.dto.DateDto;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.dto.StockDtoAPI;
import telran.java2022.stock.dto.exeptions.StockNotFoundException;
import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;

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
	public Iterable<StockDto> findStocksByPeriod(DatePeriodDto datePeriodDto) {
		return repository
				.findStocksById_DateBetween(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
				.map(s -> modelMapper.map(s, StockDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public Iterable<StockDto> findStockByName(String symbol) {
		return repository.findById_Symbol(symbol)
				.map(s -> modelMapper.map(s, StockDto.class))
				.collect(Collectors.toList());   
	}
	
	@Override
	public Iterable<StockDto> findStocksBySymbolInPeriod(String symbol, DatePeriodDto datePeriodDto) {
		return repository.findStocksById_SymbolAndId_DateBetween(symbol, datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
				.map(s -> modelMapper.map(s, StockDto.class))
				.collect(Collectors.toList());
	}
	
	
	
//  Admin methods
	@Override
	public Integer downloadDataForStockByPeriod(String label, DatePeriodDto datePeriodDto) {
		ResponseEntity<StockDtoAPI> responseEntity = StockAPI.request(label, datePeriodDto.getDateFrom(),
				datePeriodDto.getDateTo());
		List<DataDto> list = responseEntity.getBody().getData();
		for (int i = 0; i < list.size(); i++) {
			DataDto dataDto = modelMapper.map(list.get(i), DataDto.class);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'+'0000");
			LabelDate id = new LabelDate(dataDto.getSymbol(), LocalDate.parse(dataDto.getDate(), formatter));
			double close = Double.parseDouble(dataDto.getClose());
			Stock stock = new Stock(id, close);
			System.out.println(id.getDate());
			repository.save(stock);
		}
		return list.size();
	}

	@Override
	public void parseData() throws IOException, URISyntaxException {
		ParseData parseData = new ParseData(repository);
		parseData.CSVMaptoObject();
	}



//	@Override
//	public StockDto getStockByDate(String date, String name) {
////		 ID - ????
//		Stock stock = stockRepository.findById(date).orElseThrow();
//		return modelMapper.map(stock, StockDto.class);
//	}

//	@Override
//	public StockDto removeStock(String date, String name) {
////		 ID - ????
//		Stock stock = stockRepository.findById(date).orElseThrow();
//		stockRepository.delete(stock);
//		return modelMapper.map(stock, StockDto.class);
//	}

//	@Override
//	public Iterable<StockDto> findStockByName(String name) {
//		return (stockRepository.findStockByNameInIgnoreCase(name)).map(p -> modelMapper.map(p, StockDto.class))
//				.collect(Collectors.toList());
//	}

}
