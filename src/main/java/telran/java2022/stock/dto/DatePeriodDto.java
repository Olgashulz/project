package telran.java2022.stock.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DatePeriodDto {
	@JsonFormat(pattern = "MM/dd/yy")
	LocalDate dateFrom;
	@JsonFormat(pattern = "MM/dd/yy")
	LocalDate dateTo;
}
