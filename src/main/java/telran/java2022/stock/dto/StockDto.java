package telran.java2022.stock.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class StockDto {
	String name;
	@JsonFormat(pattern = "MM/dd/yy")
	LocalDate date;
	Double open;
	Double hight;
	Double low;
	Double close;

}