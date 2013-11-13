package br.edu.ifbaiano.ligacoes.view.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.model.Setor;
import br.edu.ifbaiano.ligacoes.model.TipoServidor;

public class ServidorTableModel extends AbstractTableModel {

    private List<Servidor> servidores;

    // Array com os nomes das colunas.
    private String[] colunas = new String[] { "Tipo", "Usuário", "Login",
	    "Nome", "Setor" };

    // Constantes representando o índice das colunas
    private static final int TIPO = 0;
    private static final int USUARIO = 1;
    private static final int LOGIN = 2;
    private static final int NOME = 3;
    private static final int SETOR = 4;

    public ServidorTableModel() {
	servidores = new ArrayList<Servidor>();
    }

    public void setServidores(List<Servidor> servidores) {
	this.servidores = servidores;
	fireTableDataChanged();
    }

    public Servidor getServidor(int index) {
	return servidores.get(index);
    }

    @Override
    public int getRowCount() {
	return servidores.size();
    }

    @Override
    public int getColumnCount() {
	return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
	return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int index) {
	switch (index) {
	case TIPO:
	    return TipoServidor.class;
	case USUARIO:
	    return TipoServidor.class;
	case NOME:
	    return String.class;
	case LOGIN:
	    return String.class;
	case SETOR:
	    return Setor.class;
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
	Servidor servidor = servidores.get(linha);

	switch (coluna) {
	case TIPO:
	    return servidor.getTipo();
	case USUARIO:
	    return servidor.getTipoUsuario();
	case LOGIN:
	    return servidor.getLogin();
	case NOME:
	    return servidor.getNome();
	case SETOR:
	    return servidor.getSetor().getSigla();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    public void limpar() {
	servidores.clear();
	fireTableDataChanged();
    }
}
