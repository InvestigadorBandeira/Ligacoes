package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.dao.SetorDAO;
import br.edu.ifbaiano.ligacoes.infra.UsuarioLogado;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.model.TipoServidor;
import br.edu.ifbaiano.ligacoes.reports.GerarRelatorio;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.util.OrdenarServidores;
import br.edu.ifbaiano.ligacoes.view.PrincipalView;

public class PrincipalController {

    private PrincipalView view;
    private Mensagem mensagem;

    public PrincipalController() {
	montaView();
    }

    private void montaView() {
	if (UsuarioLogado.instance().isLogado()) {
	    view = new PrincipalView(this);
	    view.setVisible(true);
	    view.setExtendedState(view.MAXIMIZED_BOTH);
	    mensagem = new Mensagem(view.getTitle(), view);
	}
    }

    public void geraRelatorioSetores() {
	new GerarRelatorio<Setor>("Setores").vizualizar("setor",
		new SetorDAO().getDados());
    }

    public void geraRelatorioEstagiarios() {
	List<Servidor> servidores = new ArrayList<Servidor>();

	for (Servidor s : new ServidorDAO().getAtivos())
	    if (s.getTipo().equals(TipoServidor.Estagiário))
		servidores.add(s);

	Collections.sort(servidores, OrdenarServidores.PorNome);

	new GerarRelatorio<Servidor>("Estagiários").vizualizar("servidor",
		servidores);
    }

    public void geraRelatorioServidores() {
	List<Servidor> servidores = new ArrayList<Servidor>();

	for (Servidor s : new ServidorDAO().getAtivos())
	    if (s.getTipo().equals(TipoServidor.Servidor))
		servidores.add(s);

	Collections.sort(servidores, OrdenarServidores.PorNome);

	new GerarRelatorio<Servidor>("Servidores").vizualizar("servidor",
		servidores);
    }

    public void geraRelatorioTerceirizados() {
	List<Servidor> servidores = new ArrayList<Servidor>();

	for (Servidor s : new ServidorDAO().getAtivos())
	    if (s.getTipo().equals(TipoServidor.Terceirizado))
		servidores.add(s);

	Collections.sort(servidores, OrdenarServidores.PorNome);

	new GerarRelatorio<Servidor>("Terceirizados").vizualizar("servidor",
		servidores);
    }

    public void geraRelatorioLigacoes(Container container) {
	new RelatorioController(container);
    }
}
