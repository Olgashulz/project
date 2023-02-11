package telran.java2022.stock.service;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//StockAverageProfitDto предназначен для вывода средней годовой доходности для определенного периода
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockAverageProfitDto {
  String symbol;
  Integer periodInYears;
  LocalDate froDate;
  LocalDate toDate;
  Double yearProfit;
}
