package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import br.edu.ifbaiano.ligacoes.dao.LigacaoDAO;
import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.ServidorView;

public class ServidorController {

    private ServidorDAO dao;
    private static ServidorView view = null;
    private Mensagem mensagem;

    public ServidorController(Container container) {
	dao = new ServidorDAO();
	montaView(container);
    }

    private void montaView(Container container) {
	if (view == null || view.isClosed()) {
	    view = new ServidorView(this);
	    view.preencheTabela(dao.getAtivos());
	    container.add(view);
	    view.setVisible(true);
	    mensagem = new Mensagem(view.getTitle(), view);
	}
    }

    public void salvar(Servidor servidor) {

	// verifica se existe servidor com o mesmo nome/login cadastrado
	if (dao.existeNome(servidor.getNome())) {
	    mensagem.informacao("Já existe servidor com o nome '"
		    + servidor.getNome() + "' cadastrado.\n");
	    return;
	}

	if (dao.existeLogin(servidor.getLogin())) {
	    mensagem.informacao("Já existe servidor com o login '"
		    + servidor.getLogin() + "' cadastrado.\n");
	    return;
	}

	dao.salvar(servidor);
	atualiza();
    }

    public void alterar(Servidor servidor) {

    }

    public void excluir(Servidor servidor) {

	// verifica se existe ligação feita pelo servidor
	for (Ligacao l : new LigacaoDAO().getDados())
	    if (l.getServidor().equals(servidor)) {
		mensagem.informacao("Exclusão cancelada.\nExiste ligações feitas por esse servidor.");
		return;
	    }

	int opcao = JOptionPane.showConfirmDialog(
		view,
		String.format("Deseja excluir o servidor:\n%s - %s",
			servidor.getTipo(), servidor.getNome()), "Exclusão",
		JOptionPane.YES_NO_OPTION);

	if (opcao == JOptionPane.YES_OPTION) {
	    dao.excluir(servidor);
	    atualiza();
	}
    }

    private void atualiza() {
	view.atualiza();
	view.preencheTabela(dao.getDados());
    }

}
