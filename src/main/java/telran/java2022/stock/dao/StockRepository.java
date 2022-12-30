package telran.java2022.stock.dao;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.model.LabelDate;
import telran.java2022.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, LabelDate> {

	Stream<Stock> findStocksById_DateBetween(LocalDate localDate, LocalDate localDate2);

	Stream<Stock> findById_Symbol(String symbol);

	Stream<Stock> findStocksById_SymbolAndId_DateBetween(String symbol, LocalDate dateFrom, LocalDate dateTo);

}
