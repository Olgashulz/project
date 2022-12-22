package telran.java2022.stock.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DatePeriodDto {
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate dateFrom;
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate dateTo;
}
