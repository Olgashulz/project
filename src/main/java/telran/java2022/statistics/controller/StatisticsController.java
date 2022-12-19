package telran.java2022.statistics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.statistics.dao.StatisticsRepository;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StatisticsController {	
	final StatisticsRepository statisticsRepository;

}
