package br.edu.ifbaiano.ligacoes.infra;

import br.edu.ifbaiano.ligacoes.model.Servidor;

public class UsuarioLogado {

    private Servidor logado;
    private static UsuarioLogado instance;

    private UsuarioLogado() {
    }

    public static UsuarioLogado instance() {
	if (instance == null)
	    instance = new UsuarioLogado();
	return instance;
    }

    public void login(Servidor usuario) {
	this.logado = usuario;
    }

    public String getNome() {
	return logado.getNome();
    }

    public Servidor getLogado() {
	return logado;
    }

    public boolean isLogado() {
	return logado != null;
    }

    public void logout() {
	this.logado = null;
    }

}
