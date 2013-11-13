package br.edu.ifbaiano.ligacoes.model;

import java.util.Calendar;
import java.util.Date;

public class Ligacao {

    private Calendar data;
    private Date hora;
    private Servidor servidor;
    private String telefone;
    private String destino;

    public Ligacao() {
    }

    public Ligacao(Calendar data, Date hora, Servidor servidor,
	    String telefone, String destino) {
	this.data = data;
	this.hora = hora;
	this.servidor = servidor;
	this.telefone = telefone;
	this.destino = destino;
    }

    public Calendar getData() {
	return data;
    }

    public void setData(Calendar data) {
	this.data = data;
    }

    public Date getHora() {
	return hora;
    }

    public void setHora(Date hora) {
	this.hora = hora;
    }

    public Servidor getServidor() {
	return servidor;
    }

    public void setServidor(Servidor servidor) {
	this.servidor = servidor;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public String getDestino() {
	return destino;
    }

    public void setDestino(String destino) {
	this.destino = destino;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((data == null) ? 0 : data.hashCode());
	result = prime * result + ((destino == null) ? 0 : destino.hashCode());
	result = prime * result + ((hora == null) ? 0 : hora.hashCode());
	result = prime * result
		+ ((servidor == null) ? 0 : servidor.hashCode());
	result = prime * result
		+ ((telefone == null) ? 0 : telefone.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Ligacao other = (Ligacao) obj;
	if (data == null) {
	    if (other.data != null)
		return false;
	} else if (!data.equals(other.data))
	    return false;
	if (destino == null) {
	    if (other.destino != null)
		return false;
	} else if (!destino.equals(other.destino))
	    return false;
	if (hora == null) {
	    if (other.hora != null)
		return false;
	} else if (!hora.equals(other.hora))
	    return false;
	if (servidor == null) {
	    if (other.servidor != null)
		return false;
	} else if (!servidor.equals(other.servidor))
	    return false;
	if (telefone == null) {
	    if (other.telefone != null)
		return false;
	} else if (!telefone.equals(other.telefone))
	    return false;
	return true;
    }

}
