package br.edu.ifbaiano.ligacoes.util;

import java.util.Collections;
import java.util.Comparator;

import br.edu.ifbaiano.ligacoes.model.Servidor;

public enum OrdenarServidores implements Comparator<Servidor> {

    PorNome {
	@Override
	public int compare(Servidor servidor1, Servidor servidor2) {
	    return servidor1.getNome().compareToIgnoreCase(servidor2.getNome());
	}
    },

    PorTipo {
	@Override
	public int compare(Servidor servidor1, Servidor servidor2) {
	    return servidor1.getTipo().compareTo(servidor2.getTipo());
	}
    },

    PorSetor {
	@Override
	public int compare(Servidor servidor1, Servidor servidor2) {
	    return servidor1.getSetor().getSigla()
		    .compareTo(servidor2.getSetor().getSigla());
	}
    };

    public Comparator<Servidor> asc() {
	return this;
    }

    public Comparator<Servidor> desc() {
	return Collections.reverseOrder(this);
    }

}
