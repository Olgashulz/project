package telran.java2022.stock.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.dto.StockProfitDto;
import telran.java2022.stock.service.StockService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StockController {
	final StockRepository stockRepository;
	final StockService service;
	
    @GetMapping("/stock/{symbol}/")
    public StockDto findStockByDate(@PathVariable String symbol, @RequestParam String date) {
	return service.findStockByDate(symbol, date);
    }
    
    // весь период по 1 компании
	@GetMapping("/stocks/{symbol}")
	public Iterable<StockDto> findStocksBySymbol(@PathVariable String symbol) {
		return service.findStockByName(symbol);
	}
	
	// по 1 компании за период
	@GetMapping("/stocks/{symbol}/period")
	public Iterable<StockDto> findStocksBySymbolInPeriod(@PathVariable String symbol,
		    @RequestBody DatePeriodDto datePeriodDto) {
		return service.findStocksByPeriod(symbol, datePeriodDto);		
	}    
	
    @GetMapping("stock/{symbol}/max")
    public StockDto findMaxBySymbol(@PathVariable String symbol) {    	
	return service.findMaxBySymbol(symbol);
    }
    
    @GetMapping("stock/{symbol}/min")
    public StockDto findMinBySymbol(@PathVariable String symbol) {
	return service.findMinBySymbol(symbol);
    }
    
//    Admin methods    
	// бесплатной не достаточно
//    @GetMapping("/stock/download/{label}")
//    public Integer downloadDataFromAPIForStockByPeriod(@PathVariable String label, @RequestBody DatePeriodDto datePeriodDto) {
//	return service.downloadDataFromAPIForStockByPeriod(label, datePeriodDto);
//    }
    
	// устарел есть новое решение
//    @GetMapping("/stock/parse")
//    public void parseData() throws IOException, URISyntaxException {
//    	service.parseData();
//    }
    
    @GetMapping("stock/download/csv")
    public Boolean parseDataFromLocalCSV(@RequestParam String symbol) {
	return service.downloadCSVandParseToDB(symbol);
    }
    
    @GetMapping("stock/profit")
    public Iterable<StockProfitDto> getMinAndMaxYearProfit(@RequestParam String symbol, @RequestParam String fromDate,
	    @RequestParam String toDate, @RequestParam Integer periodInYears) {
	return service.getMinAndMaxYearProfit(symbol, fromDate, toDate, periodInYears);
    }
    

    
//    @GetMapping("stock/{symbol}/max/period")
//    public StockDto findMaxBySymbolInPeriod(@PathVariable String symbol,@RequestBody DatePeriodDto datePeriodDto) {
//    return service.findMaxBySymbolInPeriod(symbol, datePeriodDto);
//    }
    
//    @GetMapping("stock/{symbol}/min/period")
//    public StockDto findMinBySymbolInPeriod(@PathVariable String symbol,@RequestBody DatePeriodDto datePeriodDto) {
//	return service.findMinBySymbolInPeriod(symbol, datePeriodDto);
//    }.
    
//    @GetMapping("stock/profit")
//    public StockProfitDto getYearProfit(@RequestParam String symbol,@RequestParam Double sum, @RequestBody DatePeriodDto datePeriodDto ) {
//		return service.findProfit(symbol, sum, datePeriodDto);
//    	
//    }
    
}
