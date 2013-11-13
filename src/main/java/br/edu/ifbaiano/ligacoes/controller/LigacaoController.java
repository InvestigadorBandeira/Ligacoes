package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifbaiano.ligacoes.dao.LigacaoDAO;
import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Formatador;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.LigacaoView;

public class LigacaoController {

    private LigacaoDAO dao;
    private static LigacaoView view = null;
    private Mensagem mensagem;

    public LigacaoController(Container container) {
	this.dao = new LigacaoDAO();
	montaView(container);
    }

    private void montaView(Container container) {
	if (view == null || view.isClosed()) {
	    view = new LigacaoView(this);
	    view.preencheTabela(filtradas());
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

    public void salvar(Ligacao ligacao) {

	String telefone = ligacao.getTelefone().replace("(", "")
		.replace(")", "").replace("-", "").replace(" ", "").trim();

	if (telefone.length() != 10) {
	    mensagem.informacao("Telefone incorreto. Digite novamente.");
	    return;
	}

	dao.salvar(ligacao);
	atualiza();

    }

    public void alterar(Ligacao ligacao) {

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
	view.atualiza();

	view.preencheTabela(filtradas());
    }

    private List<Ligacao> filtradas() {
	List<Ligacao> ligacoes = new ArrayList<>();

	Calendar hoje = Calendar.getInstance();

	for (Ligacao l : dao.getDados()) {
	    if (comparaData(l.getData(), hoje) == 0)
		ligacoes.add(l);
	}

	return ligacoes;
    }

    private int comparaData(Calendar data1, Calendar data2) {
	int retorno = 0;
	int dia1 = data1.get(Calendar.DATE);
	int mes1 = data1.get(Calendar.MONTH);
	int ano1 = data1.get(Calendar.YEAR);
	int dia2 = data2.get(Calendar.DATE);
	int mes2 = data2.get(Calendar.MONTH);
	int ano2 = data2.get(Calendar.YEAR);

	if (ano1 < ano2) {
	    retorno = -1;
	} else if (ano1 > ano2) {
	    retorno = 1;
	} else {
	    if (mes1 < mes2) {
		retorno = -1;
	    } else if (mes1 > mes2) {
		retorno = 1;
	    } else {
		if (dia1 < dia2) {
		    retorno = -1;
		} else if (dia1 > dia2) {
		    retorno = 1;
		}
	    }
	}
	return retorno;
    }
}
