package br.edu.ifbaiano.ligacoes.view.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifbaiano.ligacoes.model.Setor;

public class SetorTableModel extends AbstractTableModel {

    private List<Setor> setores;

    // Array com os nomes das colunas.
    private String[] colunas = new String[] { "Sigla", "Nome" };

    // Constantes representando o índice das colunas
    private static final int SIGLA = 0;
    private static final int NOME = 1;

    public SetorTableModel() {
	setores = new ArrayList<Setor>();
    }

    public void setSetores(List<Setor> setores) {
	this.setores = setores;
	fireTableDataChanged();
    }

    public Setor getSetor(int index) {
	return setores.get(index);
    }

    @Override
    public int getRowCount() {
	return setores.size();
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
	case SIGLA:
	case NOME:
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
	Setor setor = setores.get(linha);

	switch (coluna) {
	case SIGLA:
	    return setor.getSigla();
	case NOME:
	    return setor.getNome();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    public void limpar() {
	setores.clear();
	fireTableDataChanged();
    }
}
