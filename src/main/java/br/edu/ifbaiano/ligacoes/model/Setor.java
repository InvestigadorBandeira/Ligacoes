package br.edu.ifbaiano.ligacoes.model;

public class Setor {

    private String sigla;
    private String nome;

    public Setor() {
    }

    public Setor(String sigla, String nome) {
	this.sigla = sigla;
	this.nome = nome;
    }

    public String getSigla() {
	return sigla;
    }

    public void setSigla(String sigla) {
	this.sigla = sigla;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
	Setor other = (Setor) obj;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (sigla == null) {
	    if (other.sigla != null)
		return false;
	} else if (!sigla.equals(other.sigla))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return sigla + " - " + nome;
    }

}
