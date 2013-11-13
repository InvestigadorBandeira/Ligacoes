package br.edu.ifbaiano.ligacoes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CalendarConverter implements Converter {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean canConvert(Class clazz) {
	return Calendar.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer,
	    MarshallingContext context) {

	Calendar calendar = (Calendar) value;

	writer.setValue(sdf.format(calendar.getTime()));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
	    UnmarshallingContext context) {

	Calendar calendar = Calendar.getInstance();

	try {
	    calendar.setTime(sdf.parse(reader.getValue()));
	} catch (ParseException e) {
	    throw new ConversionException(e.getMessage(), e);
	}

	return calendar;
    }

}
