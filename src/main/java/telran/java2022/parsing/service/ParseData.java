package telran.java2022.parsing.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;

@Service
@RequiredArgsConstructor
public class ParseData {

	final StockRepository stockRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void CSVMaptoObject() throws FileNotFoundException {
		CsvToBean csv = new CsvToBean();
		String csvFilename = "HistoricalPrices.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		
		List list = csv.parse(setMapStrategy(), csvReader);
		for (int i = 1; i < list.size(); i++) {
			ObjectForParser objectForParser = (ObjectForParser) list.get(i);
//			System.out.println(objectForParser);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
			LocalDate date = LocalDate.parse(objectForParser.getDate(), formatter);
			
			LabelDate labelDate = new LabelDate("SPX", date);
			
			Stock stock = new Stock(labelDate, Double.parseDouble(objectForParser.getClose()));
			stockRepository.save(stock);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ColumnPositionMappingStrategy setMapStrategy() {
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(ObjectForParser.class);
		String[] columns = new String[] { "date", "open", "hight", "low", "close" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

}



