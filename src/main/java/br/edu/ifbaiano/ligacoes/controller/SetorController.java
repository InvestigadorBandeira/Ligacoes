package br.edu.ifbaiano.ligacoes.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import br.edu.ifbaiano.ligacoes.dao.ServidorDAO;
import br.edu.ifbaiano.ligacoes.dao.SetorDAO;
import br.edu.ifbaiano.ligacoes.infra.UsuarioLogado;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.model.TipoUsuario;
import br.edu.ifbaiano.ligacoes.util.Mensagem;
import br.edu.ifbaiano.ligacoes.view.SetorView;

public class SetorController {

    private SetorDAO dao;
    private static SetorView view = null;
    private Mensagem mensagem;
    private Servidor logado;

    public SetorController(Container container) {
	dao = new SetorDAO();
	montaView(container);
	logado = UsuarioLogado.instance().getLogado();
    }

    private void montaView(Container container) {

	if (view == null || view.isClosed()) {
	    view = new SetorView(this);
	    view.preencheTabela(dao.getDados());
	    container.add(view);
	    view.setVisible(true);
	    mensagem = new Mensagem(view.getTitle(), view);
	}
    }

    public void salvar(Setor setor) {

	if (logado.getTipoUsuario().equals(TipoUsuario.Administrador))
	    // verifica se existe setores, siglas e nomes iguais já cadastrados
	    if (dao.existe(setor)) {
		mensagem.informacao("Já existe esse setor cadastrado.\n"
			+ setor.getSigla() + " - " + setor.getNome());
	    } else if (dao.existeSigla(setor.getSigla())) {
		mensagem.informacao("Já existe setor com a sigla '"
			+ setor.getSigla() + "' cadastrado.\n");
	    } else if (dao.existeNome(setor.getNome())) {
		mensagem.informacao("Já existe setor com o nome '"
			+ setor.getNome() + "' cadastrado.\n");
	    } else {
		dao.salvar(setor);
		atualiza();
	    }
	else
	    mensagem.informacao("Usuário sem permissões para salvar.");
    }

    public void alterar(Setor atual, Setor antigo) {
	dao.alterar(atual, antigo);
	atualiza();
    }

    public void excluir(Setor setor) {

	if (logado.getTipoUsuario().equals(TipoUsuario.Administrador)) {
	    for (Servidor s : new ServidorDAO().getDados())
		if (s.getSetor().equals(setor)) {
		    mensagem.informacao("Exclusão cancelada.\nExiste Servidor alocado nesse setor.");
		    return;
		}

	    int opcao = JOptionPane.showConfirmDialog(
		    view,
		    String.format("Deseja excluir o setor:\n%s - %s",
			    setor.getSigla(), setor.getNome()), "Exclusão",
		    JOptionPane.YES_NO_OPTION);

	    if (opcao == JOptionPane.YES_OPTION) {
		dao.excluir(setor);
		atualiza();
	    }
	} else
	    mensagem.informacao("Usuário sem permissões para excluir.");
    }

    private void atualiza() {
	view.atualiza();
	view.preencheTabela(dao.getDados());
    }

}
