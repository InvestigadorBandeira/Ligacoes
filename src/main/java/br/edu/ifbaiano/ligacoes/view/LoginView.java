package br.edu.ifbaiano.ligacoes.view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import br.edu.ifbaiano.ligacoes.controller.LoginController;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.SimpleCrypt;
import br.edu.ifbaiano.ligacoes.view.components.RequiredPasswordField;
import br.edu.ifbaiano.ligacoes.view.components.RequiredTextField;

public class LoginView extends JFrame {
    private JLabel lblLogo;
    private JLabel lblLogin;
    private RequiredTextField txtLogin;
    private JLabel lblSenha;
    private RequiredPasswordField pwdSenha;
    private JButton btnEntrar;
    private JButton btnCancelar;
    private JTextPane txtpnInfo;
    private LoginController controller;
    private Mensagem mensagem;

    public LoginView(LoginController controller) {
	setIconImage(Toolkit
		.getDefaultToolkit()
		.getImage(
			LoginView.class
				.getResource("/br/edu/ifbaiano/ligacoes/images/logo2.jpg")));
	setResizable(false);
	setTitle(" :: Login ::");
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 285, 295);
	getContentPane().setLayout(null);

	lblLogin = new JLabel("Login");
	lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblLogin.setBounds(10, 139, 60, 18);
	getContentPane().add(lblLogin);

	txtLogin = new RequiredTextField(20);
	txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
	txtLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtLogin.setBounds(80, 137, 180, 25);
	getContentPane().add(txtLogin);
	txtLogin.setColumns(10);

	lblSenha = new JLabel("Senha");
	lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblSenha.setBounds(10, 183, 60, 18);
	getContentPane().add(lblSenha);

	pwdSenha = new RequiredPasswordField(20);
	pwdSenha.setHorizontalAlignment(SwingConstants.CENTER);
	pwdSenha.setColumns(10);
	pwdSenha.setBounds(80, 182, 180, 25);
	getContentPane().add(pwdSenha);

	btnEntrar = new JButton("Entrar");
	btnEntrar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnEntrarActionPerformed(e);
	    }
	});
	btnEntrar.setBounds(10, 230, 100, 25);
	getContentPane().add(btnEntrar);

	btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnCancelarActionPerformed(e);
	    }
	});
	btnCancelar.setBounds(160, 230, 100, 25);
	getContentPane().add(btnCancelar);

	lblLogo = new JLabel();
	lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
	lblLogo.setIcon(new ImageIcon(LoginView.class
		.getResource("/br/edu/ifbaiano/ligacoes/images/logo2.jpg")));
	lblLogo.setBounds(10, 0, 95, 120);
	getContentPane().add(lblLogo);

	txtpnInfo = new JTextPane();
	txtpnInfo.setFocusable(false);
	txtpnInfo.setEditable(false);
	txtpnInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
	txtpnInfo.setText("\n\n      IF Baiano Campus\n\n"
		+ " Governador Mangabeira");
	txtpnInfo.setBounds(104, 0, 160, 120);
	getContentPane().add(txtpnInfo);

	//
	this.setLocationRelativeTo(null);
	this.getRootPane().setDefaultButton(btnEntrar);
	this.controller = controller;
	this.mensagem = new Mensagem(this.getTitle(), this);
    }

    protected void btnEntrarActionPerformed(ActionEvent e) {
	String login = txtLogin.getText().trim();
	String senha = String.valueOf(pwdSenha.getPassword()).trim();

	if (login == null || login.isEmpty()) {
	    mensagem.informacao("Digite o LOGIN.");
	    txtLogin.requestFocus();
	    return;
	}

	if (senha == null || senha.isEmpty()) {
	    mensagem.informacao("Digite a SENHA.");
	    pwdSenha.requestFocus();
	    return;
	}

	senha = SimpleCrypt.crypt(senha);

	controller.logar(login, senha);
    }

    protected void btnCancelarActionPerformed(ActionEvent e) {
	System.exit(0);
    }
}
