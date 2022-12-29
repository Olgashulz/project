package telran.java2022.stock.dao;

import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, LabelDate> {

	 Stream<Stock> findStocksByIdSymbolAndIdDateBetween(String symbol, String from, String to);
}
