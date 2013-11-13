package br.edu.ifbaiano.ligacoes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateConverter implements Converter {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public boolean canConvert(Class clazz) {
	return Date.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer,
	    MarshallingContext context) {

	Date date = (Date) value;

	writer.setValue(sdf.format(date));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
	    UnmarshallingContext context) {

	Calendar date = Calendar.getInstance();

	try {
	    date.setTime(sdf.parse(reader.getValue()));
	} catch (ParseException e) {
	    throw new ConversionException(e.getMessage(), e);
	}

	return date.getTime();
    }

}
