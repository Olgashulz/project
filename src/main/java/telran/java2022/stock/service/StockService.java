package telran.java2022.stock.service;

import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.dto.StockProfitDto;

public interface StockService {
	Iterable<StockDto> findStockByName(String name);

	StockDto findStockByDate(String symbol, String date);

	Iterable<StockDto> findStocksByPeriod(String symbol, DatePeriodDto datePeriodDto);

	Boolean downloadCSVandParseToDB(String symbol);

	StockDto findMaxBySymbol(String symbol);

	StockDto findMinBySymbol(String symbol);

	Iterable<StockProfitDto> getMinAndMaxYearProfit(String symbol, String fromDate, String toDate,
			Integer periodInYears);
}
