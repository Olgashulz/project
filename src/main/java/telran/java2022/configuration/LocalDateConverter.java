package telran.java2022.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class LocalDateConverter extends AbstractBeanField<String, LocalDate> {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate localDate = LocalDate.parse(value, formatter);
	return localDate;
    }
}
