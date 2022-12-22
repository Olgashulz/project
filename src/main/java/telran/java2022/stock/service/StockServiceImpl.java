package telran.java2022.stock.service;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import telran.java2022.stock.dao.StockRepository;
import telran.java2022.stock.dto.DatePeriodDto;
import telran.java2022.stock.dto.StockDto;
import telran.java2022.stock.model.Stock;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	final StockRepository stockRepository;
	final ModelMapper modelMapper;

	@Override
	public StockDto addNewStock(StockDto newStockDto, String name) {
//		 добавить проверку на уникальность... Понять что у нас будет Id
		Stock stock = modelMapper.map(newStockDto, Stock.class);
		stock.setName(name);
		stock = stockRepository.save(stock);
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public StockDto getStockByDate(String date, String name) {
//		 ID - ????
		Stock stock = stockRepository.findById(date).orElseThrow();
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public StockDto removeStock(String date, String name) {
//		 ID - ????
		Stock stock = stockRepository.findById(date).orElseThrow();
		stockRepository.delete(stock);
		return modelMapper.map(stock, StockDto.class);
	}

	@Override
	public Iterable<StockDto> findStockByName(String name) {
		return (stockRepository.findStockByNameInIgnoreCase(name)).map(p -> modelMapper.map(p, StockDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<StockDto> findStockByPeriod(DatePeriodDto datePeriodDto) {
		return stockRepository.findByDateBetween(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
				.map(p -> modelMapper.map(p, StockDto.class)).collect(Collectors.toList());
	}

}
