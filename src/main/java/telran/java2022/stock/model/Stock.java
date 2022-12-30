
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
public class Stock implements Serializable {

	private static final long serialVersionUID = 998443373406764689L;
    @Id
    LabelDate id;
    double close;
}
	 


