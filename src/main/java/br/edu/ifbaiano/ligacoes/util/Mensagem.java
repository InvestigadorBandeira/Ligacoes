package br.edu.ifbaiano.ligacoes.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensagem {
    private String titulo;
    private Component component;

    public Mensagem(String titulo, Component component) {
	this.titulo = titulo;
	this.component = component;
    }

    public void informacao(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.INFORMATION_MESSAGE);
    }
}
