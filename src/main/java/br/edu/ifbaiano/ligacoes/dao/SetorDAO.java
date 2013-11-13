package br.edu.ifbaiano.ligacoes.dao;

import java.util.Iterator;

import br.edu.ifbaiano.ligacoes.dao.xml.db.Setores;
import br.edu.ifbaiano.ligacoes.model.Setor;

public class SetorDAO extends AbstractDAO<Setor> {

    public SetorDAO() {
	super(new Setores(), "Setores");
    }

    @Override
    protected void setDados() {
	dados = ((Setores) dao.instance()).getSetores();
    }

    public void alterar(Setor atual, Setor antigo) {
	Iterator<Setor> it = dados.iterator();

	while (it.hasNext()) {
	    Setor s = it.next();
	    if (antigo.equals(s)) {
		s.setSigla(atual.getSigla());
		s.setNome(atual.getNome());
	    }
	}

	persistir();
    }

    public boolean existeSigla(String sigla) {
	for (Setor s : dados)
	    if (sigla.equalsIgnoreCase(s.getSigla()))
		return true;

	return false;
    }

    public boolean existeNome(String nome) {
	for (Setor s : dados)
	    if (nome.equalsIgnoreCase(s.getNome()))
		return true;

	return false;
    }

}
