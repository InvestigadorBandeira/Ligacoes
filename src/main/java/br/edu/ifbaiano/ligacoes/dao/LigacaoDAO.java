package br.edu.ifbaiano.ligacoes.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifbaiano.ligacoes.dao.xml.db.Ligacoes;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;

public class LigacaoDAO extends AbstractDAO<Ligacao> {

    public LigacaoDAO() {
	super(new Ligacoes(), "Ligacoes");
    }

    @Override
    protected void setDados() {
	dados = ((Ligacoes) dao.instance()).getLigacoes();
    }

    public List<Ligacao> filtrarPorServidor(Servidor servidor) {
	List<Ligacao> filtradas = new ArrayList<Ligacao>();

	for (Ligacao l : dados) {
	    if (l.getServidor().getNome().equals(servidor.getNome()))
		filtradas.add(l);
	}

	return filtradas;
    }

    public List<Ligacao> filtrarPorData(Calendar dataInicial, Calendar dataFinal) {
	List<Ligacao> filtradas = new ArrayList<Ligacao>();

	for (Ligacao l : dados) {
	    if (comparaData(l.getData(), dataInicial) == 0
		    || comparaData(l.getData(), dataFinal) == 0)
		filtradas.add(l);
	    else if (l.getData().before(dataFinal)
		    && l.getData().after(dataInicial))
		filtradas.add(l);
	}

	return filtradas;
    }

    public List<Ligacao> filtrarPorDataEServidor(Calendar dataInicial,
	    Calendar dataFinal, Servidor servidor) {
	List<Ligacao> filtradas = new ArrayList<Ligacao>();

	for (Ligacao l : dados) {
	    if ((comparaData(l.getData(), dataInicial) == 0 || comparaData(
		    l.getData(), dataFinal) == 0)
		    && l.getServidor().getNome().equals(servidor.getNome()))
		filtradas.add(l);
	    else if (l.getData().before(dataFinal)
		    && l.getData().after(dataInicial)
		    && l.getServidor().getNome().equals(servidor.getNome()))
		filtradas.add(l);
	}

	return filtradas;
    }

    public List<Ligacao> filtrarPorDataESetor(Calendar dataInicial,
	    Calendar dataFinal, Setor setor) {
	List<Ligacao> filtradas = new ArrayList<Ligacao>();

	for (Ligacao l : dados) {
	    if ((comparaData(l.getData(), dataInicial) == 0 || comparaData(
		    l.getData(), dataFinal) == 0)
		    && l.getServidor().getSetor().equals(setor))
		filtradas.add(l);
	    else if (l.getData().before(dataFinal)
		    && l.getData().after(dataInicial)
		    && l.getServidor().getSetor().equals(setor))
		filtradas.add(l);
	}

	return filtradas;
    }

    private int comparaData(Calendar data1, Calendar data2) {
	int retorno = 0;
	int dia1 = data1.get(Calendar.DATE);
	int mes1 = data1.get(Calendar.MONTH);
	int ano1 = data1.get(Calendar.YEAR);
	int dia2 = data2.get(Calendar.DATE);
	int mes2 = data2.get(Calendar.MONTH);
	int ano2 = data2.get(Calendar.YEAR);

	if (ano1 < ano2) {
	    retorno = -1;
	} else if (ano1 > ano2) {
	    retorno = 1;
	} else {
	    if (mes1 < mes2) {
		retorno = -1;
	    } else if (mes1 > mes2) {
		retorno = 1;
	    } else {
		if (dia1 < dia2) {
		    retorno = -1;
		} else if (dia1 > dia2) {
		    retorno = 1;
		}
	    }
	}
	return retorno;
    }

}
