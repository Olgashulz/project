package telran.java2022.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import lombok.RequiredArgsConstructor;
import telran.java2022.statistics.dao.StatisticsRepository;
import telran.java2022.statistics.model.Statistics;

@Service
@RequiredArgsConstructor
public class ParseData {
	
	final StatisticsRepository statisticsRepository;

	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void CSVMaptoObject() throws FileNotFoundException {
		CsvToBean csv = new CsvToBean();
		String csvFilename = "HistoricalPrices.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		List list = csv.parse(setColumMapping(), csvReader);
		for (Object object : list) {
			Statistics statistics = (Statistics) object;
			if (!statistics.getDate().equals("Date")) {
				System.out.println(statistics);
				statisticsRepository.save(statistics);
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ColumnPositionMappingStrategy setColumMapping() {
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(Statistics.class);
		String[] columns = new String[] { "date", "open", "hight", "low", "close" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

}
