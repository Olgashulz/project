package telran.java2022.statistics.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.statistics.model.Statistics;

public interface StatisticsRepository extends CrudRepository<Statistics, String> {

	}

