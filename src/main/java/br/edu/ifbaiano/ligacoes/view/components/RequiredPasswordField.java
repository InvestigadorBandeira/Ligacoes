package br.edu.ifbaiano.ligacoes.view.components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class RequiredPasswordField extends JPasswordField {

    public RequiredPasswordField(int fixedLenght) {
	super();
	addFocusListener(new ThisFocus());
	setDocument(new FixedLengthDocument(fixedLenght));
	//
	setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.RED, Color.RED));
    }

    private class ThisFocus extends FocusAdapter {
	@Override
	public void focusLost(FocusEvent e) {
	    if (String.valueOf(getPassword()).trim().isEmpty())
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.RED,
			Color.RED));
	    else
		setBorder(UIManager.getBorder("TextField.border"));
	}
    }
}