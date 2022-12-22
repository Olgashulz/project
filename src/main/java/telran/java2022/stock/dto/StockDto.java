package telran.java2022.stock.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class StockDto {
	String name;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDate date;
	Double open;
	Double hight;
	Double low;
	Double close;
}
