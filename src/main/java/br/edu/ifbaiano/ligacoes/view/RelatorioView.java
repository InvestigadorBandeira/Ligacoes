package br.edu.ifbaiano.ligacoes.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.edu.ifbaiano.ligacoes.controller.RelatorioController;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;

import com.toedter.calendar.JDateChooser;

public class RelatorioView extends JInternalFrame {

    private JLabel lblDataInicial;
    private JDateChooser txtDataInicial;
    private JLabel lblDataFinal;
    private JDateChooser txtDataFinal;
    private JLabel lblServidor;
    private JComboBox<Servidor> cmbServidor;
    private JComboBox<Setor> cmbSetor;

    private Mensagem mensagem;
    private RelatorioController controller;
    private JButton btnImprimirPorData;
    private JButton btnImprimirPorServidor;
    private JPanel pnPrint;
    private JLabel lblSetor;
    private JButton btnImprimirPorSetor;

    public RelatorioView(RelatorioController controller) {
	setClosable(true);
	setTitle("Impress\u00F5es");
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 1000, 600);
	setLocation(20, 30);
	getContentPane().setLayout(null);

	//
	this.controller = controller;

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
	lblServidor.setBounds(260, 11, 260, 25);
	getContentPane().add(lblServidor);

	cmbServidor = new JComboBox<Servidor>();
	cmbServidor.setMaximumRowCount(10);
	cmbServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
	cmbServidor.setBounds(260, 40, 260, 25);
	getContentPane().add(cmbServidor);

	btnImprimirPorData = new JButton("Imprimir por Data");
	btnImprimirPorData.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnImprimirPorDataActionPerformed(e);
	    }
	});
	btnImprimirPorData.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnImprimirPorData.setBounds(10, 76, 140, 25);
	getContentPane().add(btnImprimirPorData);

	btnImprimirPorServidor = new JButton("Imprimir por Servidor");
	btnImprimirPorServidor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnImprimirPorServidorActionPerformed(e);
	    }
	});
	btnImprimirPorServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnImprimirPorServidor.setBounds(260, 76, 160, 25);
	getContentPane().add(btnImprimirPorServidor);

	pnPrint = new JPanel();
	pnPrint.setBounds(10, 112, 974, 452);
	getContentPane().add(pnPrint);
	pnPrint.setLayout(new BorderLayout(0, 0));

	lblSetor = new JLabel("Setor");
	lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
	lblSetor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblSetor.setBounds(530, 11, 260, 25);
	getContentPane().add(lblSetor);

	cmbSetor = new JComboBox<Setor>();
	cmbSetor.setMaximumRowCount(10);
	cmbSetor.setFont(new Font("Tahoma", Font.BOLD, 12));
	cmbSetor.setBounds(530, 40, 260, 25);
	getContentPane().add(cmbSetor);

	btnImprimirPorSetor = new JButton("Imprimir por Setor");
	btnImprimirPorSetor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnImprimirPorSetorActionPerformed(e);
	    }
	});
	btnImprimirPorSetor.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnImprimirPorSetor.setBounds(530, 77, 160, 25);
	getContentPane().add(btnImprimirPorSetor);

	//
	this.mensagem = new Mensagem(this.getTitle(), this);
    }

    public void montaComboServidor(List<Servidor> servidores) {
	for (Servidor s : servidores)
	    cmbServidor.addItem(s);
    }

    public void montaComboSetor(List<Setor> setores) {
	for (Setor s : setores)
	    cmbSetor.addItem(s);
    }

    public void setReport(JPanel printView) {
	pnPrint.removeAll();

	if (printView != null)
	    pnPrint.add(printView);

	pnPrint.revalidate();
	pnPrint.repaint();
    }

    protected void btnImprimirPorDataActionPerformed(ActionEvent e) {
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

	controller.imprimirPorData(dataInicial, dataFinal);
    }

    protected void btnImprimirPorServidorActionPerformed(ActionEvent e) {
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

	controller.imprimirDataEServidor(servidor, dataInicial, dataFinal);
    }

    protected void btnImprimirPorSetorActionPerformed(ActionEvent e) {
	Calendar dataInicial = txtDataInicial.getCalendar();
	Calendar dataFinal = txtDataFinal.getCalendar();
	Setor setor = cmbSetor.getItemAt(cmbSetor.getSelectedIndex());

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

	if (setor == null) {
	    mensagem.informacao("Selecione um Servidor.");
	    cmbServidor.requestFocus();
	    return;
	}

	controller.imprimirDataESetor(setor, dataInicial, dataFinal);
    }
}
