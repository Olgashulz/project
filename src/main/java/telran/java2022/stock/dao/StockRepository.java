package telran.java2022.stock.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, LabelDate> {

	Stream<Stock> findStocksById_AndIdDate(LocalDate localDate);

	Stream<Stock> findStockById_Symbol(String symbol);

    Stream<Stock> findStocksByIdSymbolAndIdDateBetween(String symbol, LocalDate from, LocalDate to);
    
    Stream<Stock> findStocksByIdSymbolAndIdDateBetweenOrderByIdDateDesc(String symbol, LocalDate from, LocalDate to);

	Stock findFirstByIdSymbolOrderByCloseDesc(String symbol);

	Stock findFirstByIdSymbolOrderByClose(String symbol);

	Stock findTopByIdSymbolOrderByIdDateDesc(String symbol);

//	Stream<Stock> findStocksByIdSymbolAndIdDateBetween(String symbol, LocalDate plusYears, LocalDate plusYears2);
	
	
//	@Query("SELECT s FROM stocksAPITest s WHERE s.id.symbol = ?1 AND s.id.Date between ?2 and ?3 order by s.close desk limit 1")
//	@Query("SELECT MAX(s.close) FROM Stock s WHERE s.id.symbol = ?1 AND s.startDate between ?2 and ?3 ")
//	Stock findFirstById_Symbol_OrderByClose_DateBetween(String symbol, LocalDate dateFrom, LocalDate dateTo);

//	Stock findStockByDate(LocalDate date );

	//Stock findFirstByIdSymbolOrderByCloseDateBetween(String symbol, LocalDate dateFrom, LocalDate dateTo);
	
	

}
