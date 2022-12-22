package telran.java2022.stock.service;

import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;

public interface StockService {
	
	Iterable<StockDto> findAllStocks();
	
	Iterable<StockDto> findStocksByName(String name);

	Iterable<StockDto> findStocksByDates(DatePeriodDto datePeriodDto);

	StockDto getStock(String id);

	StockDto removeStock(String id);


	
	

}
