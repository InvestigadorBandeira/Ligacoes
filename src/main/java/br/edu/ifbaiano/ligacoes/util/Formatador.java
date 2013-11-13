package br.edu.ifbaiano.ligacoes.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Formatador {

    public static String formataData(Calendar data) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	return data != null ? sdf.format(data.getTime()) : "";
    }

    public static String formataHora(Date hora) {
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	return hora != null ? sdf.format(hora) : "";
    }
}
