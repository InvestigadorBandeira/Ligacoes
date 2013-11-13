package br.edu.ifbaiano.ligacoes.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import br.edu.ifbaiano.ligacoes.controller.PesquisaController;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarLigacoes;
import br.edu.ifbaiano.ligacoes.view.models.LigacaoTableModel;

import com.toedter.calendar.JDateChooser;

public class PesquisaView extends JInternalFrame {
    private JLabel lblDataInicial;
    private JDateChooser txtDataInicial;
    private JLabel lblDataFinal;
    private JDateChooser txtDataFinal;
    private JLabel lblServidor;
    private JComboBox<Servidor> cmbServidor;
    private JScrollPane scrollPane;
    private JTable tblLigacoes;
    private JButton btnFiltrarPorData;

    private LigacaoTableModel tableModel;
    private PesquisaController controller;
    private Mensagem mensagem;
    private JButton btnExcluir;
    private JButton btnFiltrarPorServidor;

    public PesquisaView(PesquisaController controller) {
	setClosable(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setTitle("Pesquisar Liga\u00E7\u00F5es");
	setBounds(100, 100, 737, 598);
	setLocation(20, 30);
	getContentPane().setLayout(null);

	lblDataInicial = new JLabel("Data Inicial");
	lblDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
	lblDataInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDataInicial.setBounds(10, 11, 115, 25);
	getContentPane().add(lblDataInicial);

	// pega dia inicial do mes
	Calendar inicio = Calendar.getInstance();
	inicio.set(inicio.DAY_OF_MONTH, 1);
	txtDataInicial = new JDateChooser(inicio.getTime());
	txtDataInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDataInicial.setBounds(10, 40, 115, 25);
	getContentPane().add(txtDataInicial);

	lblDataFinal = new JLabel("Data Final");
	lblDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
	lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDataFinal.setBounds(135, 11, 115, 25);
	getContentPane().add(lblDataFinal);

	txtDataFinal = new JDateChooser(Calendar.getInstance().getTime());
	txtDataFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDataFinal.setBounds(135, 40, 115, 25);
	getContentPane().add(txtDataFinal);

	lblServidor = new JLabel("Servidor");
	lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
	lblServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblServidor.setBounds(260, 11, 290, 25);
	getContentPane().add(lblServidor);

	cmbServidor = new JComboBox<Servidor>();
	cmbServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
	cmbServidor.setMaximumRowCount(10);
	cmbServidor.setBounds(260, 40, 290, 25);
	getContentPane().add(cmbServidor);

	btnFiltrarPorData = new JButton("Filtrar por Data");
	btnFiltrarPorData.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnFiltrarPorDataActionPerformed(e);
	    }
	});
	btnFiltrarPorData.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnFiltrarPorData.setBounds(10, 76, 120, 25);
	getContentPane().add(btnFiltrarPorData);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 112, 701, 395);
	getContentPane().add(scrollPane);

	tableModel = new LigacaoTableModel();

	tblLigacoes = new JTable();
	tblLigacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tblLigacoes.setModel(tableModel);
	tblLigacoes.getColumnModel().getColumn(0).setResizable(false);
	tblLigacoes.getColumnModel().getColumn(1).setResizable(false);
	tblLigacoes.getColumnModel().getColumn(2).setResizable(false);
	tblLigacoes.getColumnModel().getColumn(3).setResizable(false);
	tblLigacoes.getColumnModel().getColumn(4).setResizable(false);
	tblLigacoes.getColumnModel().getColumn(1).setPreferredWidth(45);
	tblLigacoes.getColumnModel().getColumn(2).setPreferredWidth(250);
	tblLigacoes.getColumnModel().getColumn(3).setPreferredWidth(100);
	tblLigacoes.getColumnModel().getColumn(4).setPreferredWidth(250);
	scrollPane.setViewportView(tblLigacoes);

	btnExcluir = new JButton("Excluir");
	btnExcluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		btnExcluirActionPerformed(arg0);
	    }
	});
	btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnExcluir.setBounds(10, 524, 100, 25);
	getContentPane().add(btnExcluir);

	btnFiltrarPorServidor = new JButton("por Servidor");
	btnFiltrarPorServidor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnFiltrarPorServidorActionPerformed(e);
	    }
	});
	btnFiltrarPorServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnFiltrarPorServidor.setBounds(260, 76, 120, 25);
	getContentPane().add(btnFiltrarPorServidor);

	//
	this.controller = controller;
	this.mensagem = new Mensagem(this.getTitle(), this);
    }

    public void preencheTabela(List<Ligacao> ligacoes) {
	Collections.sort(ligacoes, OrdenarLigacoes.PorData);
	tableModel.setLigacoes(ligacoes);
    }

    public void montaComboServidor(List<Servidor> servidores) {
	for (Servidor s : servidores)
	    cmbServidor.addItem(s);
    }

    protected void btnFiltrarPorDataActionPerformed(ActionEvent e) {
	Calendar dataInicial = txtDataInicial.getCalendar();
	Calendar dataFinal = txtDataFinal.getCalendar();

	if (dataInicial == null) {
	    mensagem.informacao("Data Inicial inválida.");
	    txtDataInicial.requestFocus();
	    return;
	}

	if (dataFinal == null) {
	    mensagem.informacao("Data Final inválida.");
	    txtDataFinal.requestFocus();
	    return;
	}

	if (dataFinal.before(dataInicial)) {
	    mensagem.informacao("Data inicial deve ser igual ou superior à data inicial.");
	    return;
	}

	controller.filtrarPorData(dataInicial, dataFinal);

    }

    protected void btnFiltrarPorServidorActionPerformed(ActionEvent e) {
	Calendar dataInicial = txtDataInicial.getCalendar();
	Calendar dataFinal = txtDataFinal.getCalendar();
	Servidor servidor = cmbServidor.getItemAt(cmbServidor
		.getSelectedIndex());

	if (dataInicial == null) {
	    mensagem.informacao("Data Inicial inválida.");
	    txtDataInicial.requestFocus();
	    return;
	}

	if (dataFinal == null) {
	    mensagem.informacao("Data Final inválida.");
	    txtDataFinal.requestFocus();
	    return;
	}

	if (dataFinal.before(dataInicial)) {
	    mensagem.informacao("Data inicial deve ser igual ou superior à data inicial.");
	    return;
	}

	if (servidor == null) {
	    mensagem.informacao("Selecione um Servidor.");
	    cmbServidor.requestFocus();
	    return;
	}

	controller.filtrarDataEServidor(servidor, dataInicial, dataFinal);

    }

    protected void btnExcluirActionPerformed(ActionEvent e) {
	int index = tblLigacoes.getSelectedRow();

	if (index == -1) {
	    // mensagem.informacao("Selecione uma Ligação para excluir.");
	    return;
	}

	Ligacao ligacao = tableModel.getLigacao(index);
	// controller.excluir(ligacao);
    }

}
