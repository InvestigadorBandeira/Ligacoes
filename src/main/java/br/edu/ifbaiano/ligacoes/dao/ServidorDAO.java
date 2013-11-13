package br.edu.ifbaiano.ligacoes.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifbaiano.ligacoes.dao.xml.db.Servidores;
import br.edu.ifbaiano.ligacoes.model.Servidor;

public class ServidorDAO extends AbstractDAO<Servidor> {

    public ServidorDAO() {
	super(new Servidores(), "Servidores");
    }

    public List<Servidor> getAtivos() {
	List<Servidor> servidores = new ArrayList<>();

	for (Servidor s : dados)
	    if (s.isAtivo())
		servidores.add(s);

	return servidores;
    }

    @Override
    protected void setDados() {
	dados = ((Servidores) dao.instance()).getServidores();
    }

    public boolean existeNome(String nome) {
	for (Servidor s : dados)
	    if (nome.equalsIgnoreCase(s.getNome()))
		return true;

	return false;
    }

    public Servidor getServidor(String login, String senha) {
	for (Servidor s : dados)
	    if (login.equalsIgnoreCase(s.getLogin())
		    && senha.equals(s.getSenha()))
		return s;

	return null;
    }

    public boolean existeLogin(String login) {
	for (Servidor s : dados)
	    if (login.equalsIgnoreCase(s.getLogin()))
		return true;

	return false;
    }

}
