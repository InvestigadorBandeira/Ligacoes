package br.edu.ifbaiano.ligacoes.controller;

import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.infra.UsuarioLogado;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.LoginView;

public class LoginController {

    private ServidorDAO dao;
    private LoginView view;
    private Mensagem mensagem;
    private int contador = 0;

    public LoginController() {
	dao = new ServidorDAO();
	montaView();
    }

    private void montaView() {
	view = new LoginView(this);
	this.mensagem = new Mensagem(view.getTitle(), view);
	view.setVisible(true);
    }

    public void logar(String login, String senha) {

	Servidor servidor = dao.getServidor(login, senha);

	if (servidor != null) {
	    UsuarioLogado.instance().login(servidor);
	    view.dispose();
	    new PrincipalController();
	} else {
	    ++contador;

	    if (contador == 3)
		System.exit(0);

	    mensagem.informacao("Login e/ou Senha inválidos.");
	}

    }
}
