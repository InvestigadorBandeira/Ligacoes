package br.edu.ifbaiano.ligacoes.main;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.edu.ifbaiano.ligacoes.controller.LoginController;
import br.edu.ifbaiano.ligacoes.dao.LigacaoDAO;
import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.dao.SetorDAO;
import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.model.TipoServidor;
import br.edu.ifbaiano.ligacoes.model.TipoUsuario;
import br.edu.ifbaiano.ligacoes.util.CalendarConverter;
import br.edu.ifbaiano.ligacoes.util.DateConverter;
import br.edu.ifbaiano.ligacoes.util.Formatador;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarServidores;
import br.edu.ifbaiano.ligacoes.util.OrdenarSetores;
import br.edu.ifbaiano.ligacoes.util.SimpleCrypt;

import com.thoughtworks.xstream.XStream;

public class Main {
    public static void main(String[] args) {
	try {
	    // new SetorController();
	    // new ServidorController();
	    // new LigacaoController();
	    // new PrincipalController();

	    // criaSetores();
	    // criaServidores();
	    // criaLigacoes();

	    new LoginController();

	} catch (Exception e) {
	    new Mensagem("Controle de Ligações", null)
		    .informacao("Erro ao executar o sistema.\n\n"
			    + e.getMessage());
	    e.printStackTrace();
	}

    }

    private static void criaLigacoes() {
	System.out.println();
	Ligacao ligacao = new Ligacao();
	LigacaoDAO dao = new LigacaoDAO();

	Servidor servidor = new ServidorDAO().getDados().get(0);

	Calendar data = Calendar.getInstance();

	try {
	    Thread.sleep(0);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	Date hora = Calendar.getInstance().getTime();

	ligacao = new Ligacao(data, hora, servidor, "(71) 3186-0024",
		"Reitoria - DGTI");

	// dao.salvar(ligacao);

	for (Ligacao l : dao.getDados())

	    System.out.println(String.format(
		    "| %10s | %5s | %-30s | %14s | %s", Formatador
			    .formataData(l.getData()), Formatador.formataHora(l
			    .getHora()), l.getServidor().getNome(), l
			    .getTelefone(), l.getDestino()));

	XStream xml = new XStream();
	xml.alias("ligacao", Ligacao.class);
	xml.registerConverter(new CalendarConverter());
	xml.registerConverter(new DateConverter());
	xml.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);

	System.out.println(xml.toXML(dao.getDados()));

    }

    private static void criaServidores() {
	System.out.println();
	Servidor servidor = new Servidor();
	ServidorDAO dao = new ServidorDAO();

	servidor = new Servidor("ADMINISTRADOR", TipoServidor.Servidor,
		new SetorDAO().getDados().get(0), TipoUsuario.Administrador,
		"admin", SimpleCrypt.crypt("123"), true);

	dao.salvar(servidor);

	List<Servidor> servidores = dao.getDados();
	Collections.sort(servidores, OrdenarServidores.PorSetor);

	for (Servidor s : servidores)
	    System.out.println(String.format(
		    "| %-12s | %-13s | %-13s | %-11s | %-11s | %-5s |",
		    s.getTipo(), s.getTipoUsuario(), s.getNome(), s.getLogin(),
		    s.getSenha(), s.getSetor().getSigla()));

    }

    private static void criaSetores() {
	System.out.println();
	Setor setor = new Setor();
	SetorDAO dao = new SetorDAO();

	// setor = new Setor("DG", "Diretoria Geral");
	// setor = new Setor("DAP",
	// "Diretoria de Administração e Planejamento");
	// setor = new Setor("DDE", "Diretoria de Desenvolvimento Educacional");
	setor = new Setor("UTIC",
		"Unidade de Tecnologia da Informação e Comunicação");

	// System.out.println("\nAntes de persistir.");
	// System.out.println("Setor: " + setor.getSigla() + " - "
	// + setor.getNome());

	dao.salvar(setor);
	// System.out.println("Persistido.");

	List<Setor> setores = dao.getDados();
	Collections.sort(setores, OrdenarSetores.PorSigla);

	for (Setor s : setores)
	    System.out.println(String.format("| %-5s | %-54s |", s.getSigla(),
		    s.getNome()));

    }
}
