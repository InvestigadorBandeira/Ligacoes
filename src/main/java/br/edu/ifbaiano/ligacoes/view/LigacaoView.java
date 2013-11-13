package br.edu.ifbaiano.ligacoes.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import br.edu.ifbaiano.ligacoes.controller.LigacaoController;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarLigacoes;
import br.edu.ifbaiano.ligacoes.view.models.LigacaoTableModel;

import com.toedter.calendar.JDateChooser;

public class LigacaoView extends JInternalFrame {
    private JLabel lblData;
    private JDateChooser txtData;
    private JLabel lblHora;
    private JDateChooser txtHora;
    private JLabel lblServidor;
    private JComboBox<Servidor> cmbServidor;
    private JLabel lblTelefone;
    private JLabel lblDestino;
    private JFormattedTextField txtTelefone;
    private JTextField txtDestino;
    private JScrollPane scrollPane;
    private JTable tblLigacoes;
    private JButton btnSalvar;

    private LigacaoTableModel tableModel;
    private LigacaoController controller;
    private Mensagem mensagem;
    private JButton btnExcluir;

    public LigacaoView(LigacaoController controller) {
	setClosable(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setTitle("Cadastro de Liga\u00E7\u00F5es");
	setBounds(100, 100, 737, 530);
	setLocation(20, 30);
	getContentPane().setLayout(null);

	lblData = new JLabel("Data");
	lblData.setHorizontalAlignment(SwingConstants.CENTER);
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblData.setBounds(10, 11, 115, 25);
	getContentPane().add(lblData);

	txtData = new JDateChooser(Calendar.getInstance().getTime());
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setBounds(10, 40, 115, 25);
	getContentPane().add(txtData);

	lblHora = new JLabel("Hora");
	lblHora.setHorizontalAlignment(SwingConstants.CENTER);
	lblHora.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblHora.setBounds(135, 11, 50, 25);
	getContentPane().add(lblHora);

	txtHora = new JDateChooser(Calendar.getInstance().getTime());
	txtHora.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtHora.getCalendarButton().setVisible(false);
	txtHora.setDateFormatString("HH:mm");
	txtHora.setBounds(135, 40, 50, 25);
	getContentPane().add(txtHora);

	lblServidor = new JLabel("Servidor");
	lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
	lblServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblServidor.setBounds(195, 11, 165, 25);
	getContentPane().add(lblServidor);

	cmbServidor = new JComboBox<Servidor>();
	cmbServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
	cmbServidor.setMaximumRowCount(10);
	cmbServidor.setBounds(195, 40, 165, 25);
	getContentPane().add(cmbServidor);

	lblTelefone = new JLabel("Telefone");
	lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
	lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTelefone.setBounds(370, 11, 125, 25);
	getContentPane().add(lblTelefone);

	montaTxtTelefone();

	lblDestino = new JLabel("Destino");
	lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
	lblDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDestino.setBounds(505, 11, 206, 25);
	getContentPane().add(lblDestino);

	txtDestino = new JTextField();
	txtDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDestino.setBounds(505, 40, 206, 25);
	getContentPane().add(txtDestino);
	txtDestino.setColumns(10);

	btnSalvar = new JButton("Salvar");
	btnSalvar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSalvarActionPerformed(e);
	    }
	});
	btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnSalvar.setBounds(10, 76, 100, 25);
	getContentPane().add(btnSalvar);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 112, 701, 333);
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
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnExcluir.setBounds(10, 456, 100, 25);
	getContentPane().add(btnExcluir);

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

    public void atualiza() {
	txtTelefone.setValue(null);
	txtDestino.setText("");
	txtData.requestFocus();
    }

    private void montaTxtTelefone() {
	try {
	    MaskFormatter telefone = new MaskFormatter("(##) ####-####");
	    telefone.setValueContainsLiteralCharacters(false);
	    txtTelefone = new JFormattedTextField(telefone);
	    txtTelefone.setHorizontalAlignment(SwingConstants.CENTER);
	    txtTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
	    txtTelefone.setBounds(370, 40, 125, 25);
	    getContentPane().add(txtTelefone);
	} catch (ParseException e) {
	}
    }

    protected void btnSalvarActionPerformed(ActionEvent event) {
	Calendar data = txtData.getCalendar();
	Calendar hora = txtHora.getCalendar();
	Servidor servidor = cmbServidor.getItemAt(cmbServidor
		.getSelectedIndex());
	String telefone = txtTelefone.getText().trim();
	String destino = txtDestino.getText().trim();

	if (data == null) {
	    mensagem.informacao("Data inválida.");
	    txtData.requestFocus();
	    return;
	}

	if (hora == null) {
	    mensagem.informacao("Hora inválida.");
	    txtHora.requestFocus();
	    return;
	}

	if (servidor == null) {
	    mensagem.informacao("Escolha 01 Servidor.");
	    cmbServidor.requestFocus();
	    return;
	}

	if (telefone == null || telefone.isEmpty()) {
	    mensagem.informacao("Digite o Telefone corretamente.");
	    txtTelefone.requestFocus();
	    return;
	}

	if (destino == null || destino.isEmpty()) {
	    mensagem.informacao("Digite um Destino.");
	    txtDestino.requestFocus();
	    return;
	}

	if (data.after(Calendar.getInstance())) {
	    mensagem.informacao("Data não pode ser posterior.");
	    return;
	}

	Servidor novo = new Servidor();
	novo.setNome(servidor.getNome());
	novo.setSetor(servidor.getSetor());
	novo.setTipo(servidor.getTipo());

	Ligacao ligacao = new Ligacao(data, hora.getTime(), novo, telefone,
		destino);
	controller.salvar(ligacao);
    }

    protected void btnExcluirActionPerformed(ActionEvent event) {
	int index = tblLigacoes.getSelectedRow();

	if (index == -1) {
	    mensagem.informacao("Selecione uma Ligação para excluir.");
	    return;
	}

	Ligacao ligacao = tableModel.getLigacao(index);
	controller.excluir(ligacao);
    }
}
