package telran.java2022.stock.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.service.StockService;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {	
	final StockRepository stockRepository;
	final StockService service;
	
//	@GetMapping("/{name}")
//	public Iterable<StockDto> getStocksByName(@PathVariable String name) {
//		return service.findStocksByName(name);
//	}
	
//	@PostMapping("/{name}")
//	public StockDto addStock(@RequestBody StockDto newStockDto, @PathVariable String date) {
//		return serviceAddNewStock(newStockDto);
//	}

//	@GetMapping("{name}/{id}")
//	public StockDto getStock(@PathVariable String id) {
//		return service.getStock(id);
//	}
//
//	@DeleteMapping("/{name}/{id}")
//	public StockDto removeStock(@PathVariable String id) {
//		return service.removeStock(id);
//	}
//
//	@PutMapping("/{name}/{id}")
//	public StockDto updateStock(@PathVariable String id, @RequestBody NewStockDto postUpdateDto) {
//		return service.updateStock(StockUpdateDto stockUpdateDto, id);
//	}	
//	
//	@GetMapping("/")
//	public Iterable<StockDto> getAllStocks(@PathVariable String name) {
//		return service.findAllStocks();
//	}
//	
//	
//	@PostMapping("/period")
//	public Iterable<StockDto> findStocksByDate(@RequestBody DatePeriodDto datePeriodDto) {
//		return service.findStocksByDates(datePeriodDto);
//	}

}


//getAllData(), getDataByPeriod(), findByDate() 