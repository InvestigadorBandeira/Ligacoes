package br.edu.ifbaiano.ligacoes.view.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifbaiano.ligacoes.model.Ligacao;
import br.edu.ifbaiano.ligacoes.model.Servidor;
import br.edu.ifbaiano.ligacoes.util.Formatador;

public class LigacaoTableModel extends AbstractTableModel {

    private List<Ligacao> ligacoes;

    // Array com os nomes das colunas.
    private String[] colunas = new String[] { "Data", "Hora", "Servidor",
	    "Telefone", "Destino" };

    // Constantes representando o índice das colunas
    private static final int DATA = 0;
    private static final int HOTA = 1;
    private static final int SERVIDOR = 2;
    private static final int TELEFONE = 3;
    private static final int DESTINO = 4;

    public LigacaoTableModel() {
	ligacoes = new ArrayList<Ligacao>();
    }

    public void setLigacoes(List<Ligacao> ligacoes) {
	this.ligacoes = ligacoes;
	fireTableDataChanged();
    }

    public Ligacao getLigacao(int index) {
	return ligacoes.get(index);
    }

    @Override
    public int getRowCount() {
	return ligacoes.size();
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
	case DATA:
	    return String.class;
	case HOTA:
	    return String.class;
	case SERVIDOR:
	    return Servidor.class;
	case TELEFONE:
	    return String.class;
	case DESTINO:
	    return String.class;
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
	Ligacao ligacao = ligacoes.get(linha);

	switch (coluna) {
	case DATA:
	    return Formatador.formataData(ligacao.getData());
	case HOTA:
	    return Formatador.formataHora(ligacao.getHora());
	case SERVIDOR:
	    return ligacao.getServidor();
	case TELEFONE:
	    return ligacao.getTelefone();
	case DESTINO:
	    return ligacao.getDestino();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    public void limpar() {
	ligacoes.clear();
	fireTableDataChanged();
    }
}
