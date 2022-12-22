package telran.java2022.stock.dao;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import telran.java2022.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, String> {

	Stream<Stock> findStockByNameInIgnoreCase(String name);

	Stream<Stock> findByDateBetween(LocalDate dateFrom, LocalDate dateTo);

}
