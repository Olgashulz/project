package telran.java2022.stock.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// Класс для составного ключа
@Data
public class LabelDate implements Serializable {
    private static final long serialVersionUID = -8248238238853250121L;
    final String symbol;
    @JsonFormat(pattern = "MM/dd/yy")
    final LocalDate date;
}
