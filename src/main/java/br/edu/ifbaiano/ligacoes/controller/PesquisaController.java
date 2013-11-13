package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifbaiano.ligacoes.dao.LigacaoDAO;
import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Formatador;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.PesquisaView;

public class PesquisaController {

    private LigacaoDAO dao;
    private static PesquisaView view = null;
    private Mensagem mensagem;

    public PesquisaController(Container container) {
	this.dao = new LigacaoDAO();
	montaView(container);
    }

    private void montaView(Container container) {
	if (view == null || view.isClosed()) {
	    view = new PesquisaView(this);
	    filtrarPorData(Calendar.getInstance(), Calendar.getInstance());
	    montaComboServidor();
	    container.add(view);
	    view.setVisible(true);
	    this.mensagem = new Mensagem(view.getTitle(), view);
	}
    }

    private void montaComboServidor() {
	List<Servidor> servidores = new ServidorDAO().getAtivos();

	if (servidores == null || servidores.isEmpty())
	    mensagem.informacao("É necessário cadastrar Servidores primeiro.");
	else
	    view.montaComboServidor(servidores);
    }

    public void excluir(Ligacao ligacao) {

	int opcao = JOptionPane.showConfirmDialog(
		view,
		String.format("Deseja excluir a ligação:\nData: %s\nHora: %s\n"
			+ "Servidor: %s\nTelefone: %s\nDestino: %s",
			Formatador.formataData(ligacao.getData()),
			Formatador.formataHora(ligacao.getHora()),
			ligacao.getServidor(), ligacao.getTelefone(),
			ligacao.getDestino()), "Exclusão",
		JOptionPane.YES_NO_OPTION);

	if (opcao == JOptionPane.YES_OPTION) {
	    dao.excluir(ligacao);
	    atualiza();
	}

    }

    private void atualiza() {
	view.preencheTabela(dao.getDados());
    }

    public void filtrarPorData(Calendar dataInicial, Calendar dataFinal) {
	view.preencheTabela(dao.filtrarPorData(dataInicial, dataFinal));
    }

    public void filtrarDataEServidor(Servidor servidor, Calendar dataInicial,
	    Calendar dataFinal) {
	view.preencheTabela(dao.filtrarPorDataEServidor(dataInicial, dataFinal,
		servidor));
    }

}
