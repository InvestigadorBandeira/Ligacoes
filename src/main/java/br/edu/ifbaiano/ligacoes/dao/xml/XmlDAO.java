package br.edu.ifbaiano.ligacoes.dao.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.util.CalendarConverter;
import br.edu.ifbaiano.ligacoes.util.DateConverter;

import com.thoughtworks.xstream.XStream;

public class XmlDAO {
    private String DATABASE;
    private String BACKUP;
    private Object instance = null;
    private XStream xml;

    public XmlDAO(Object instance, String database) {
	DATABASE = "database/" + database + ".xml";

	int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	BACKUP = "database/backup/" + database + dia + ".xml";

	this.instance = instance;
	configuraXStream();
	carregar();
    }

    private void configuraXStream() {
	xml = new XStream();
	xml.alias("servidor", Servidor.class);
	xml.alias("setor", Setor.class);
	xml.alias("ligacao", Ligacao.class);
	xml.alias("database", instance.getClass());
	xml.registerConverter(new CalendarConverter());
	xml.registerConverter(new DateConverter());
	xml.omitField(Ligacao.class, "servidor.ativo");
    }

    public synchronized Object instance() {
	if (instance == null)
	    carregar();
	return instance;
    }

    private void carregar() {
	try {
	    BufferedReader input = new BufferedReader(new FileReader(DATABASE));
	    instance = xml.fromXML(input);
	    input.close();
	} catch (FileNotFoundException e) {
	    // instance = new Database();
	} catch (IOException e) {
	    throw new RuntimeException(
		    "(OPCAO CARREGAR)\nArquivo de banco de dados não pôde ser fechado.\nMensagem: "
			    + e.getMessage());
	}
    }

    public void salvar() {
	try {
	    FileOutputStream output = new FileOutputStream(new File(DATABASE));
	    output.write(xml.toXML(instance).getBytes());
	    output.close();
	    backup();
	} catch (FileNotFoundException e) {
	    throw new RuntimeException(
		    "(OPCAO SALVAR)\nArquivo de banco de dados não pôde ser criado.\nMensagem: "
			    + e.getMessage());
	} catch (IOException e) {
	    throw new RuntimeException(
		    "(OPCAO SALVAR)\nArquivo de banco de dados não pôde ser editado/fechado.\nMensagem: "
			    + e.getMessage());
	}
    }

    private void backup() {
	try {
	    FileOutputStream output = new FileOutputStream(new File(BACKUP));
	    output.write(xml.toXML(instance).getBytes());
	    output.close();
	} catch (FileNotFoundException e) {
	    throw new RuntimeException(
		    "(OPCAO BACKUP)\nArquivo de banco de dados não pôde ser criado.\nMensagem: "
			    + e.getMessage());
	} catch (IOException e) {
	    throw new RuntimeException(
		    "(OPCAO BACKUP)\nArquivo de banco de dados não pôde ser editado/fechado.\nMensagem: "
			    + e.getMessage());
	}
    }

}
