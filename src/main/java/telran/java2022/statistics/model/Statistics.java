
package telran.java2022.statistics.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode(of = {"date"})
@Document(collection = "statistics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Statistics implements Serializable{
	private static final long serialVersionUID = -3717087401171342857L;
	@Id
	String date;
	String open;
	String hight;
	String low;
	String close;
		
}

