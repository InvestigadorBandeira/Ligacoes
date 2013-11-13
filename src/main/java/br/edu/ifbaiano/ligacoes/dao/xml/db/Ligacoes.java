package br.edu.ifbaiano.ligacoes.dao.xml.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.util.OrdenarLigacoes;

public class Ligacoes {

    private List<Ligacao> ligacoes = new ArrayList<Ligacao>();

    public List<Ligacao> getLigacoes() {
	Collections.sort(ligacoes, OrdenarLigacoes.PorDataHora);
	return ligacoes;
    }

    public void setLigacoes(List<Ligacao> ligacoes) {
	this.ligacoes = ligacoes;
    }

}
