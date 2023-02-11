package telran.java2022.stock.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockProfit {
    String symbol;
    LocalDate dateFrom;
    LocalDate dateTo;
    Double closeStart;
    Double closeEnd;
    Double profit;
    
    public Double getProfit(int years) {
	return (Math.pow(closeEnd / closeStart, 1.0 / years) - 1) * 100;
    }
}
