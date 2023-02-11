package telran.java2022.stock.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//StockProfitDto предназначен для вывода статистических данных
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockProfitDto {
    String symbol;
    LocalDate dateFrom;
    LocalDate dateTo;
    Double closeStart;
    Double closeEnd;
    Double yearProfit;
}
