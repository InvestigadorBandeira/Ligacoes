package br.edu.ifbaiano.ligacoes.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.edu.ifbaiano.ligacoes.controller.ServidorController;
import br.edu.ifbaiano.ligacoes.dao.SetorDAO;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.model.TipoServidor;
import br.edu.ifbaiano.ligacoes.model.TipoUsuario;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarServidores;
import br.edu.ifbaiano.ligacoes.util.OrdenarSetores;
import br.edu.ifbaiano.ligacoes.util.SimpleCrypt;
import br.edu.ifbaiano.ligacoes.view.components.FixedLengthDocument;
import br.edu.ifbaiano.ligacoes.view.models.ServidorTableModel;

public class ServidorView extends JInternalFrame {
    private JLabel lblTipo;
    private JComboBox<TipoServidor> cmbTipo;
    private JLabel lblNome;
    private JTextField txtNome;
    private JButton btnSalvar;
    private JScrollPane scrollPane;
    private JTable tblServidores;

    private ServidorTableModel tableModel;
    private ServidorController controller;
    private Mensagem mensagem;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JLabel lblSetor;
    private JComboBox<Setor> cmbSetor;
    private JLabel lblLogin;
    private JTextField txtLogin;
    private JLabel lblSenha;
    private JPasswordField pwdSenha;
    private JComboBox<TipoUsuario> cmbTipoUsuario;
    private JLabel lblTipoDeUsuario;

    public ServidorView(ServidorController controller) {
	setClosable(true);
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setTitle("Cadastro de Servidores");
	setBounds(100, 100, 700, 570);
	setLocation(20, 30);
	getContentPane().setLayout(null);

	lblTipo = new JLabel("Tipo");
	lblTipo.setBounds(10, 13, 50, 15);
	lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
	getContentPane().add(lblTipo);

	lblNome = new JLabel("Nome");
	lblNome.setBounds(10, 49, 50, 15);
	lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	getContentPane().add(lblNome);

	txtNome = new JTextField();
	txtNome.setBounds(70, 44, 484, 25);
	txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtNome.setColumns(10);
	txtNome.setDocument(new FixedLengthDocument(60));
	getContentPane().add(txtNome);

	btnSalvar = new JButton("Salvar");
	btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnSalvar.setBounds(10, 147, 120, 25);
	btnSalvar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		btnSalvarActionPerformed(event);
	    }
	});
	getContentPane().add(btnSalvar);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 183, 664, 303);
	getContentPane().add(scrollPane);

	tableModel = new ServidorTableModel();

	tblServidores = new JTable();
	tblServidores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tblServidores.setModel(tableModel);
	tblServidores.getColumnModel().getColumn(0).setResizable(false);
	tblServidores.getColumnModel().getColumn(1).setResizable(false);
	tblServidores.getColumnModel().getColumn(2).setResizable(false);
	tblServidores.getColumnModel().getColumn(3).setResizable(false);
	tblServidores.getColumnModel().getColumn(4).setResizable(false);
	tblServidores.getColumnModel().getColumn(2).setPreferredWidth(100);
	tblServidores.getColumnModel().getColumn(3).setPreferredWidth(200);
	scrollPane.setViewportView(tblServidores);

	btnAlterar = new JButton("Alterar");
	btnAlterar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnAlterarActionPerformed(e);
	    }
	});
	btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnAlterar.setBounds(10, 497, 100, 25);
	getContentPane().add(btnAlterar);

	btnExcluir = new JButton("Excluir");
	btnExcluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnExcluir.setBounds(120, 497, 100, 25);
	getContentPane().add(btnExcluir);

	cmbTipo = new JComboBox<TipoServidor>();
	cmbTipo.setModel(new DefaultComboBoxModel<TipoServidor>(TipoServidor
		.values()));
	cmbTipo.setBounds(70, 11, 130, 25);
	getContentPane().add(cmbTipo);

	lblSetor = new JLabel("Setor");
	lblSetor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblSetor.setBounds(10, 84, 50, 15);
	getContentPane().add(lblSetor);

	cmbSetor = new JComboBox<Setor>();
	cmbSetor.setBounds(70, 80, 484, 25);
	getContentPane().add(cmbSetor);

	lblLogin = new JLabel("Login");
	lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblLogin.setBounds(10, 121, 50, 15);
	getContentPane().add(lblLogin);

	txtLogin = new JTextField();
	txtLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtLogin.setColumns(10);
	txtLogin.setBounds(70, 116, 150, 25);
	getContentPane().add(txtLogin);

	lblSenha = new JLabel("Senha");
	lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblSenha.setBounds(240, 121, 50, 15);
	getContentPane().add(lblSenha);

	pwdSenha = new JPasswordField();
	pwdSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
	pwdSenha.setEchoChar('#');
	pwdSenha.setBounds(300, 116, 150, 25);
	getContentPane().add(pwdSenha);

	cmbTipoUsuario = new JComboBox<TipoUsuario>();
	cmbTipoUsuario.setModel(new DefaultComboBoxModel<TipoUsuario>(
		TipoUsuario.values()));
	cmbTipoUsuario.setBounds(350, 9, 130, 25);
	getContentPane().add(cmbTipoUsuario);

	lblTipoDeUsuario = new JLabel("Tipo de Usu\u00E1rio");
	lblTipoDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTipoDeUsuario.setBounds(240, 13, 120, 15);
	getContentPane().add(lblTipoDeUsuario);

	//
	this.controller = controller;
	mensagem = new Mensagem(this.getTitle(), this);
	preencheComboSetor();
    }

    protected void btnSalvarActionPerformed(ActionEvent event) {
	TipoServidor tipo = (TipoServidor) cmbTipo.getSelectedItem();
	String nome = txtNome.getText().trim();
	Setor setor = (Setor) cmbSetor.getSelectedItem();
	TipoUsuario usuario = (TipoUsuario) cmbTipoUsuario.getSelectedItem();
	String login = txtLogin.getText().trim();
	String senha = String.valueOf(pwdSenha.getPassword()).trim();

	if (nome == null || nome.isEmpty()) {
	    mensagem.informacao("Digite o NOME.");
	    txtNome.requestFocus();
	    return;
	}

	if (login == null || login.isEmpty()) {
	    mensagem.informacao("Digite o LOGIN.");
	    txtLogin.requestFocus();
	    return;
	}

	if (senha == null || senha.isEmpty()) {
	    mensagem.informacao("Digite a SENHA.");
	    pwdSenha.setText(null);
	    pwdSenha.requestFocus();
	    return;
	}

	senha = SimpleCrypt.crypt(senha);

	Servidor servidor = new Servidor(nome, tipo, setor, usuario, login,
		senha, true);
	controller.salvar(servidor);
    }

    protected void btnAlterarActionPerformed(ActionEvent event) {
    }

    protected void btnExcluirActionPerformed(ActionEvent event) {
	int index = tblServidores.getSelectedRow();

	if (index == -1) {
	    mensagem.informacao("Selecione um Servidor para excluir.");
	    return;
	}

	Servidor servidor = tableModel.getServidor(index);
	controller.excluir(servidor);
    }

    public void atualiza() {
	cmbTipo.setSelectedIndex(0);
	cmbTipo.requestFocus();
	txtNome.setText("");
	pwdSenha.setText(null);
	cmbSetor.setSelectedIndex(0);
	cmbTipoUsuario.setSelectedIndex(0);
    }

    public void preencheTabela(List<Servidor> servidores) {
	Collections.sort(servidores, OrdenarServidores.PorNome);
	tableModel.setServidores(servidores);
    }

    private void preencheComboSetor() {
	List<Setor> setores = new SetorDAO().getDados();
	Collections.sort(setores, OrdenarSetores.PorNome);

	for (Setor s : setores)
	    cmbSetor.addItem(s);
    }
}
