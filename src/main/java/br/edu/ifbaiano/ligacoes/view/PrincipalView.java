package br.edu.ifbaiano.ligacoes.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import br.edu.ifbaiano.ligacoes.controller.LigacaoController;
import br.edu.ifbaiano.ligacoes.controller.PesquisaController;
import br.edu.ifbaiano.ligacoes.controller.PrincipalController;
import br.edu.ifbaiano.ligacoes.controller.ServidorController;
import br.edu.ifbaiano.ligacoes.controller.SetorController;
import br.edu.ifbaiano.ligacoes.infra.UsuarioLogado;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.TipoUsuario;
import br.edu.ifbaiano.ligacoes.util.Mensagem;

public class PrincipalView extends JFrame {

    private PrincipalController controller;
    private Mensagem mensagem;
    private JMenuBar menuBar;
    private JMenu mnAjuda;
    private JMenu mnCadastrar;
    private JMenuItem mntmAjuda;
    private JSeparator separator;
    private JMenuItem mntmSobre;
    private JMenuItem mnCadastrarSetor;
    private JMenuItem mnCadastrarServidor;
    private JMenuItem mnCadastrarLigacao;
    private JMenu mnRelatorios;
    private JMenu mnPesquisar;
    private JMenuItem mnPesquisarLigacoes;
    private JMenuItem mntmSetores;
    private JMenu mnServidores;
    private JMenuItem mntmEstagiario;
    private JMenuItem mntmServidor;
    private JMenuItem mntmTerceirizado;
    private JMenuItem mntmLigacoes;
    private Servidor logado;

    public PrincipalView(PrincipalController controller) {
	setIconImage(Toolkit
		.getDefaultToolkit()
		.getImage(
			PrincipalView.class
				.getResource("/br/edu/ifbaiano/ligacoes/images/logo2.jpg")));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Controle de Liga\u00E7\u00F5es Telef\u00F4nicas 1.0  ||  "
		+ UsuarioLogado.instance().getNome() + "  ||  "
		+ UsuarioLogado.instance().getLogado().getTipoUsuario());
	setBounds(100, 100, 473, 364);
	getContentPane().setLayout(null);

	menuBar = new JMenuBar();
	setJMenuBar(menuBar);

	mnCadastrar = new JMenu("Cadastrar");
	menuBar.add(mnCadastrar);

	mnCadastrarSetor = new JMenuItem("Setor");
	mnCadastrarSetor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setorActionPerformed(e);
	    }
	});
	mnCadastrar.add(mnCadastrarSetor);

	mnCadastrarServidor = new JMenuItem("Servidor");
	mnCadastrarServidor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		servidorActionPerformed(e);
	    }
	});
	mnCadastrar.add(mnCadastrarServidor);

	mnCadastrarLigacao = new JMenuItem("Liga\u00E7\u00E3o");
	mnCadastrarLigacao.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ligacaoActionPerformed(e);
	    }
	});
	mnCadastrar.add(mnCadastrarLigacao);

	mnPesquisar = new JMenu("Pesquisar");
	menuBar.add(mnPesquisar);

	mnPesquisarLigacoes = new JMenuItem("Liga\u00E7\u00F5es");
	mnPesquisarLigacoes.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		mnPesquisarLigacoesActionPerformed(e);
	    }
	});
	mnPesquisar.add(mnPesquisarLigacoes);

	mnRelatorios = new JMenu("Relat\u00F3rios");
	menuBar.add(mnRelatorios);

	mntmSetores = new JMenuItem("Setores");
	mntmSetores.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		reportSetoresActionPerformed(e);
	    }
	});
	mnRelatorios.add(mntmSetores);

	mnServidores = new JMenu("Servidores");
	mnRelatorios.add(mnServidores);

	mntmEstagiario = new JMenuItem("Estagi\u00E1rio");
	mntmEstagiario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		reportEstagiarioActionPerformed(e);
	    }
	});
	mnServidores.add(mntmEstagiario);

	mntmServidor = new JMenuItem("Servidor");
	mntmServidor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		reportServidorActionPerformed(e);
	    }
	});
	mnServidores.add(mntmServidor);

	mntmTerceirizado = new JMenuItem("Terceirizado");
	mntmTerceirizado.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		reportTerceirizadoActionPerformed(e);
	    }
	});
	mnServidores.add(mntmTerceirizado);

	mntmLigacoes = new JMenuItem("Liga\u00E7\u00F5es");
	mntmLigacoes.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		reportLigacoesActionPerformed(e);
	    }
	});
	mnRelatorios.add(mntmLigacoes);

	mnAjuda = new JMenu("Ajuda");
	menuBar.add(mnAjuda);

	mntmAjuda = new JMenuItem("Ajuda...");
	mnAjuda.add(mntmAjuda);

	separator = new JSeparator();
	mnAjuda.add(separator);

	mntmSobre = new JMenuItem("Sobre...");
	mnAjuda.add(mntmSobre);

	//
	this.controller = controller;
	this.mensagem = new Mensagem(
		"Controle de Liga\u00E7\u00F5es Telef\u00F4nicas 1.0", this);
	logado = UsuarioLogado.instance().getLogado();
    }

    protected void setorActionPerformed(ActionEvent e) {
	if (logado.getTipoUsuario().equals(TipoUsuario.Administrador))
	    new SetorController(this.getContentPane());
	else
	    mensagem.informacao("Usuário sem permissões para Cadastrar Setor.");
    }

    protected void servidorActionPerformed(ActionEvent e) {
	if (!logado.getTipoUsuario().equals(TipoUsuario.Comum))
	    new ServidorController(this.getContentPane());
	else
	    mensagem.informacao("Usuário sem permissões para Cadastrar Servidor.");
    }

    protected void ligacaoActionPerformed(ActionEvent e) {
	new LigacaoController(this.getContentPane());
    }

    protected void mnPesquisarLigacoesActionPerformed(ActionEvent e) {
	new PesquisaController(this.getContentPane());
    }

    protected void reportSetoresActionPerformed(ActionEvent e) {
	controller.geraRelatorioSetores();
    }

    protected void reportEstagiarioActionPerformed(ActionEvent e) {
	controller.geraRelatorioEstagiarios();
    }

    protected void reportServidorActionPerformed(ActionEvent e) {
	controller.geraRelatorioServidores();
    }

    protected void reportTerceirizadoActionPerformed(ActionEvent e) {
	controller.geraRelatorioTerceirizados();
    }

    protected void reportLigacoesActionPerformed(ActionEvent e) {
	controller.geraRelatorioLigacoes(this.getContentPane());
    }
}
