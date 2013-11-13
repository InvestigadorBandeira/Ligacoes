package br.edu.ifbaiano.ligacoes.dao.xml.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.OrdenarServidores;

public class Servidores {

    private List<Servidor> servidores = new ArrayList<Servidor>();

    public List<Servidor> getServidores() {
	Collections.sort(servidores, OrdenarServidores.PorNome);
	return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
	this.servidores = servidores;
    }
}
