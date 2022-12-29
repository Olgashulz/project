package telran.java2022.stock.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import telran.java2022.stock.dto.DateDto;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;

public interface StockService {
	
//	StockDto addNewStock(StockDto newStockDto, String name);
	
//	StockDto getStockByDate(String date, String name);
//
//	StockDto removeStock(String date, String name);

//	Iterable<StockDto> findStockByName(String name);

	Iterable<StockDto> findStocksByPeriod(String symbol, DatePeriodDto datePeriodDto);

	StockDto findStockByDate(String symbol, DateDto date);
	
	Integer downloadDataForStockByPeriod(String label, DatePeriodDto datePeriodDto);

	void parseData() throws FileNotFoundException, IOException, URISyntaxException;


}
