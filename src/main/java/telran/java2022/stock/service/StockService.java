package telran.java2022.stock.service;

import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;

public interface StockService {
	
	StockDto addNewStock(StockDto newStockDto, String name);
	
	StockDto getStockByDate(String date, String name);

	StockDto removeStock(String date, String name);

	Iterable<StockDto> findStockByName(String name);

	Iterable<StockDto> findStockByPeriod(DatePeriodDto datePeriodDto);
	
}
