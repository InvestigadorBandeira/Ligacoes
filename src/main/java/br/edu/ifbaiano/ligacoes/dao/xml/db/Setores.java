package br.edu.ifbaiano.ligacoes.dao.xml.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.util.OrdenarSetores;

public class Setores {

    private List<Setor> setores = new ArrayList<Setor>();

    public List<Setor> getSetores() {
	Collections.sort(setores, OrdenarSetores.PorNome);
	return setores;
    }

    public void setSetores(List<Setor> setores) {
	this.setores = setores;
    }
}
