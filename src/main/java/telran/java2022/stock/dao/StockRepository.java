package telran.java2022.stock.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, String> {


	}

