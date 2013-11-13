package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.swing.JRViewer;
import br.edu.ifbaiano.ligacoes.dao.LigacaoDAO;
import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.dao.SetorDAO;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.reports.GerarRelatorio;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.RelatorioView;

public class RelatorioController {

    private LigacaoDAO dao;
    private static RelatorioView view = null;
    private Mensagem mensagem;
    private JRViewer printView;

    public RelatorioController(Container container) {
	this.dao = new LigacaoDAO();
	montaView(container);
    }

    private void montaView(Container container) {

	if (view == null || view.isClosed()) {
	    view = new RelatorioView(this);
	    montaComboServidor();
	    montaComboSetor();
	    container.add(view);
	    view.setVisible(true);
	    mensagem = new Mensagem("Impress\u00F5es", view);
	}
    }

    private void montaComboServidor() {
	List<Servidor> servidores = new ServidorDAO().getAtivos();

	if (servidores == null || servidores.isEmpty())
	    mensagem.informacao("É necessário cadastrar Servidores primeiro.");
	else
	    view.montaComboServidor(servidores);
    }

    private void montaComboSetor() {
	List<Setor> setores = new SetorDAO().getDados();

	if (setores == null || setores.isEmpty())
	    mensagem.informacao("É necessário cadastrar Servidores primeiro.");
	else
	    view.montaComboSetor(setores);
    }

    public void imprimirPorData(Calendar dataInicial, Calendar dataFinal) {
	List<Ligacao> ligacoes = new ArrayList<>();

	HashMap<String, Object> parametros = new HashMap<String, Object>();
	parametros.put("dataInicial", dataInicial.getTime());
	parametros.put("dataFinal", dataFinal.getTime());

	ligacoes = dao.filtrarPorData(dataInicial, dataFinal);

	if (ligacoes != null && !ligacoes.isEmpty()) {
	    parametros.put("totalLigacoes", ligacoes.size());
	    printView = new GerarRelatorio<Ligacao>("Ligações", parametros)
		    .getView("ligacaoPorData", ligacoes);

	    view.setReport(printView);
	} else
	    mensagem.informacao("Sem dados para impressão.");
    }

    public void imprimirDataEServidor(Servidor servidor, Calendar dataInicial,
	    Calendar dataFinal) {

	List<Ligacao> ligacoes = new ArrayList<Ligacao>();

	HashMap<String, Object> parametros = new HashMap<String, Object>();
	parametros.put("dataInicial", dataInicial.getTime());
	parametros.put("dataFinal", dataFinal.getTime());

	ligacoes = dao
		.filtrarPorDataEServidor(dataInicial, dataFinal, servidor);

	if (ligacoes != null && !ligacoes.isEmpty())
	    parametros.put("totalLigacoes", ligacoes.size());

	printView = new GerarRelatorio<Ligacao>("Ligações por Servidor",
		parametros).getView("ligacaoPorServidor", ligacoes);

	view.setReport(printView);
    }

    public void imprimirDataESetor(Setor setor, Calendar dataInicial,
	    Calendar dataFinal) {

	List<Ligacao> ligacoes = new ArrayList<Ligacao>();

	HashMap<String, Object> parametros = new HashMap<String, Object>();
	parametros.put("dataInicial", dataInicial.getTime());
	parametros.put("dataFinal", dataFinal.getTime());

	ligacoes = dao.filtrarPorDataESetor(dataInicial, dataFinal, setor);

	if (ligacoes != null && !ligacoes.isEmpty())
	    parametros.put("totalLigacoes", ligacoes.size());

	printView = new GerarRelatorio<Ligacao>("Ligações por Setor",
		parametros).getView("ligacaoPorSetor", ligacoes);

	view.setReport(printView);

    }

}
