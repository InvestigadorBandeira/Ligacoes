package br.edu.ifbaiano.ligacoes.dao;

import java.util.List;

import br.edu.ifbaiano.ligacoes.dao.xml.XmlDAO;

public abstract class AbstractDAO<T> {
    protected List<T> dados;
    protected XmlDAO dao;

    public AbstractDAO(Object classe, String database) {
	dao = new XmlDAO(classe, database);
	setDados();
    }

    // apenas pra atualizar
    protected abstract void setDados();

    public List<T> getDados() {
	return dados;
    }

    public void salvar(T t) {
	dados.add(t);
	persistir();
    }

    public void excluir(T t) {
	dados.remove(t);
	persistir();
    }

    protected void persistir() {
	dao.salvar();
	setDados();
    }

    public boolean existe(T t) {
	if (dados.contains(t))
	    return true;

	return false;
    }
}
