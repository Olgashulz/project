
package telran.java2022.parsing.service;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ObjectForParser implements Serializable{
	private static final long serialVersionUID = -3717087401171342857L;
	String date;
	String open;
	String hight;
	String low;
	String close;
		
}

