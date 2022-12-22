
package telran.java2022.stock.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode(of = {"date", "name"})
@Document(collection = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Stock implements Serializable{
	private static final long serialVersionUID = -3717087401171342857L;
	@Id
	String id;
	String name = "S&P500";
//	@JsonFormat(pattern = "MM/dd/yy")
	LocalDate date;
	Double open;
	Double hight;
	Double low;
	Double close;
	
	public Stock(String date, String open, String hight, String low, String close) {
		this.date = stringToLocalDate(date);
		this.open = Double.parseDouble(open);
		this.hight = Double.parseDouble(hight);
		this.low = Double.parseDouble(close);
		this.close = Double.parseDouble(close);
	}
	
	private LocalDate stringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		return LocalDate.parse(date, formatter);		
	}
		
}

