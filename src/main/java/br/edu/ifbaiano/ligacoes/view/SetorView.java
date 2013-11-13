package br.edu.ifbaiano.ligacoes.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import br.edu.ifbaiano.ligacoes.controller.SetorController;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarSetores;
import br.edu.ifbaiano.ligacoes.view.components.FixedLengthDocument;
import br.edu.ifbaiano.ligacoes.view.models.SetorTableModel;

public class SetorView extends JInternalFrame {
    private JLabel lblSigla;
    private JTextField txtSigla;
    private JLabel lblNome;
    private JTextField txtNome;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JScrollPane scrollPane;
    private JTable tblSetores;
    private JButton btnAlterar;
    private JButton btnExcluir;

    private SetorTableModel tableModel;
    private SetorController controller;
    private Mensagem mensagem;
    private boolean alterar = false;
    private Setor antigo;

    public SetorView(SetorController controller) {
	setClosable(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setTitle("Cadastro de Setores");
	setBounds(100, 100, 570, 530);
	setLocation(20, 30);
	getContentPane().setLayout(null);

	lblSigla = new JLabel("Sigla");
	lblSigla.setBounds(10, 13, 50, 15);
	lblSigla.setFont(new Font("Tahoma", Font.BOLD, 12));
	getContentPane().add(lblSigla);

	txtSigla = new JTextField();
	txtSigla.setHorizontalAlignment(SwingConstants.CENTER);
	txtSigla.setBounds(70, 8, 100, 25);
	txtSigla.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtSigla.setDocument(new FixedLengthDocument(6));
	getContentPane().add(txtSigla);
	txtSigla.setColumns(10);

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
	btnSalvar.setBounds(10, 80, 100, 25);
	btnSalvar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		btnSalvarActionPerformed(event);
	    }
	});
	getContentPane().add(btnSalvar);

	btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnCancelarActionPerformed(e);
	    }
	});
	btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnCancelar.setBounds(120, 81, 100, 23);
	getContentPane().add(btnCancelar);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 116, 544, 339);
	getContentPane().add(scrollPane);

	tableModel = new SetorTableModel();

	tblSetores = new JTable();
	tblSetores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tblSetores.setModel(tableModel);
	tblSetores.getColumnModel().getColumn(0).setResizable(false);
	tblSetores.getColumnModel().getColumn(1).setResizable(false);
	tblSetores.getColumnModel().getColumn(1).setPreferredWidth(400);
	scrollPane.setViewportView(tblSetores);

	btnAlterar = new JButton("Alterar");
	btnAlterar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		btnAlterarActionPerformed(arg0);
	    }
	});
	btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnAlterar.setBounds(10, 466, 100, 25);
	getContentPane().add(btnAlterar);

	btnExcluir = new JButton("Excluir");
	btnExcluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnExcluir.setBounds(120, 466, 100, 25);
	getContentPane().add(btnExcluir);

	//
	this.controller = controller;
	mensagem = new Mensagem(this.getTitle(), this);
    }

    protected void btnSalvarActionPerformed(ActionEvent event) {
	Setor setor = new Setor();

	String sigla = txtSigla.getText().trim();
	String nome = txtNome.getText().trim();

	if (sigla == null || sigla.isEmpty()) {
	    mensagem.informacao("Digite a SIGLA.");
	    txtSigla.requestFocus();
	    return;
	}

	if (nome == null || nome.isEmpty()) {
	    mensagem.informacao("Digite o NOME.");
	    txtNome.requestFocus();
	    return;
	}

	setor.setSigla(sigla.toUpperCase());
	setor.setNome(nome);

	if (alterar)
	    controller.alterar(setor, antigo);
	else
	    controller.salvar(setor);
    }

    protected void btnCancelarActionPerformed(ActionEvent e) {
	atualiza();
    }

    protected void btnAlterarActionPerformed(ActionEvent event) {
	int index = tblSetores.getSelectedRow();

	if (index == -1) {
	    mensagem.informacao("Selecione um Setor para alterar.");
	    return;
	}

	antigo = tableModel.getSetor(index);

	txtSigla.setText(antigo.getSigla());
	txtNome.setText(antigo.getNome());
	alterar = true;
    }

    protected void btnExcluirActionPerformed(ActionEvent event) {
	int index = tblSetores.getSelectedRow();

	if (index == -1) {
	    mensagem.informacao("Selecione um Setor para excluir.");
	    return;
	}

	Setor setor = tableModel.getSetor(index);
	controller.excluir(setor);
    }

    public void atualiza() {
	txtSigla.setText("");
	txtSigla.requestFocus();
	txtNome.setText("");
	alterar = false;
    }

    public void preencheTabela(List<Setor> setores) {
	Collections.sort(setores, OrdenarSetores.PorSigla);
	tableModel.setSetores(setores);
    }

}
