package telran.java2022.parsing.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.model.Stock;

@Service
@RequiredArgsConstructor
public class ParseData {

	final StockRepository stockRepository;

	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void CSVMaptoObject() throws FileNotFoundException {
		CsvToBean csv = new CsvToBean();
		String csvFilename = "HistoricalPrices.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		List list = csv.parse(setColumMapping(), csvReader);

		for (int i = 1; i < list.size(); i++) {
			ObjectForParser objectForParser = (ObjectForParser) list.get(i);
			//System.out.println(objectForParser);
			Stock stock = new Stock(objectForParser.getDate(), objectForParser.getOpen(),
					objectForParser.getHight(), objectForParser.getLow(), objectForParser.getClose());
			stockRepository.save(stock);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ColumnPositionMappingStrategy setColumMapping() {
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(ObjectForParser.class);
		String[] columns = new String[] { "date", "open", "hight", "low", "close" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

}
