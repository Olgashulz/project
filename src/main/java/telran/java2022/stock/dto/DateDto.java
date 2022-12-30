package telran.java2022.stock.dto;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DateDto {
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
}