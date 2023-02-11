package telran.java2022.stock.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.java2022.configuration.LocalDateConverter;


// Класс для составного ключа
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDate implements Serializable {
    private static final long serialVersionUID = -8248238238853250121L;
    public String symbol;
    @JsonFormat(pattern = "MM/dd/yy")
    @CsvBindByName(column = "Date")
    @CsvCustomBindByName(converter = LocalDateConverter.class)
    public LocalDate date;
}
