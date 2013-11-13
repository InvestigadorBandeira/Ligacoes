package br.edu.ifbaiano.ligacoes.reports;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import br.edu.ifbaiano.ligacoes.util.Mensagem;

public class GerarRelatorio<T> {

    private String titulo;
    private Mensagem mensagem = new Mensagem("Relatórios", null);;
    private Map<String, Object> parametros;

    public GerarRelatorio(String titulo) {
	this.titulo = titulo;
	parametros = new HashMap<String, Object>();
    }

    public GerarRelatorio(String titulo, HashMap<String, Object> parametros) {
	this.titulo = titulo;
	this.parametros = parametros;
    }

    public void vizualizar(String relatorio, List<T> dados) {
	if (dados == null || dados.isEmpty()) {
	    mensagem.informacao("Sem dados para impressão.");
	    return;
	}

	String nomeRelatorioJasper = relatorio + ".jasper";

	InputStream relJasper = getClass().getResourceAsStream(
		nomeRelatorioJasper);

	JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);

	// Parametros adicionais do relatório
	ImageIcon logo = new ImageIcon(getClass().getResource(
		"/br/edu/ifbaiano/ligacoes/images/logo.jpg"));

	parametros.put("logo", logo.getImage());

	JasperPrint impressao = null;

	try {
	    impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
	    JasperViewer view = new JasperViewer(impressao, false);
	    // view.setExtendedState(view.MAXIMIZED_BOTH);
	    view.setSize(1000, 600);
	    view.setTitle(titulo);
	    view.setIconImage(logo.getImage());
	    view.setLocationRelativeTo(null);
	    view.setVisible(true);
	} catch (JRException ex) {
	    mensagem.informacao("Erro no relatório.\n" + ex.getMessage());
	}
    }

    public JRViewer getView(String relatorio, List<T> dados) {

	if (dados == null || dados.isEmpty()) {
	    mensagem.informacao("Sem dados para impressão.");
	    return null;
	}

	String nomeRelatorioJasper = relatorio + ".jasper";

	InputStream relJasper = getClass().getResourceAsStream(
		nomeRelatorioJasper);

	JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);

	// Parametros adicionais do relatório
	ImageIcon logo = new ImageIcon(getClass().getResource(
		"/br/edu/ifbaiano/ligacoes/images/logo.jpg"));

	parametros.put("logo", logo.getImage());

	JasperPrint impressao = null;
	JRViewer view = null;

	try {
	    impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
	    view = new JRViewer(impressao);
	} catch (JRException ex) {
	    mensagem.informacao("Erro no relatório.\n" + ex.getMessage());
	}

	return view;
    }
}
