package br.edu.ifbaiano.ligacoes.util;

import java.util.Collections;
import java.util.Comparator;

import br.edu.ifbaiano.ligacoes.model.Ligacao;

public enum OrdenarLigacoes implements Comparator<Ligacao> {

    PorData {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    return ligacao1.getData().compareTo(ligacao2.getData());
	}
    },

    PorHora {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    return ligacao1.getHora().compareTo(ligacao2.getHora());
	}
    },

    PorServidor {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    return ligacao1.getServidor().getNome()
		    .compareTo(ligacao2.getServidor().getNome());
	}
    },

    PorTelefone {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    return ligacao1.getTelefone().compareTo(ligacao2.getTelefone());
	}
    },

    PorDestino {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    return ligacao1.getDestino().compareTo(ligacao2.getDestino());
	}
    },

    PorDataHora {
	@Override
	public int compare(Ligacao ligacao1, Ligacao ligacao2) {
	    int resultado = ligacao1.getData().compareTo(ligacao2.getData());

	    if (resultado == 0) {
		resultado = ligacao1.getHora().compareTo(ligacao2.getHora());
	    }

	    return resultado;
	}
    };

    public Comparator<Ligacao> asc() {
	return this;
    }

    public Comparator<Ligacao> desc() {
	return Collections.reverseOrder(this);
    }
}
