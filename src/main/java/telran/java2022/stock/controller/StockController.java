package telran.java2022.stock.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DateDto;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.service.StockService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StockController {
	final StockRepository stockRepository;
	final StockService service;
	
    @GetMapping("/stock/{symbol}/date")
    public StockDto findStockByDate(@PathVariable String symbol, @RequestBody DateDto date) {
	return service.findStockByDate(symbol, date);
    }

    @GetMapping("/stock/{symbol}/stocks")
    public Iterable<StockDto> findStocksByPeriod(@PathVariable String symbol,
	    @RequestBody DatePeriodDto datePeriodDto) {
	return service.findStocksByPeriod(symbol, datePeriodDto);
    }

    @GetMapping("/stock/download/{label}")
    public Integer downloadDataForStockByPeriod(@PathVariable String label, @RequestBody DatePeriodDto datePeriodDto) {
	return service.downloadDataForStockByPeriod(label, datePeriodDto);
    }
    
    @GetMapping("/stock/parse")
    public void parseData() throws IOException, URISyntaxException {
    	service.parseData();
    }
    
    


//	@PostMapping("/stock/{name}")
//	public StockDto addStock(@RequestBody StockDto newStockDto, @PathVariable String name) {
//		return service.addNewStock(newStockDto, name);
//	}

//	@GetMapping("/stock/{name}/{date}")
//	public StockDto getStock(@PathVariable String name, @PathParam(value = "date") String date) {
//		return service.getStockByDate(date, name);
//	}

//	@DeleteMapping("/stock/{name}/{date}")
//	public StockDto removeStock(@PathVariable String name, @PathParam(value = "date") String date) {
//		return service.getStockByDate(date, name);
//	}

//	@PutMapping("/stock/{name}")
//	Update

//	@GetMapping("/stocks/{name}")
//	public Iterable<StockDto> findStocksByName(@PathVariable String name) {
//		return service.findStockByName(name);
//	}

}
