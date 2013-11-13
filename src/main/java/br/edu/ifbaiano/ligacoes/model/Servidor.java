package br.edu.ifbaiano.ligacoes.model;

public class Servidor {

    private String nome;
    private TipoServidor tipo;
    private Setor setor;
    private TipoUsuario tipoUsuario;
    private String login;
    private String senha;
    private boolean ativo;

    public Servidor() {
    }

    public Servidor(String nome, TipoServidor tipo, Setor setor,
	    TipoUsuario tipoUsuario, String login, String senha, boolean ativo) {
	this.nome = nome;
	this.tipo = tipo;
	this.setor = setor;
	this.tipoUsuario = tipoUsuario;
	this.login = login;
	this.senha = senha;
	this.ativo = ativo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public TipoServidor getTipo() {
	return tipo;
    }

    public void setTipo(TipoServidor tipo) {
	this.tipo = tipo;
    }

    public Setor getSetor() {
	return setor;
    }

    public void setSetor(Setor setor) {
	this.setor = setor;
    }

    public TipoUsuario getTipoUsuario() {
	return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
	this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((senha == null) ? 0 : senha.hashCode());
	result = prime * result + ((setor == null) ? 0 : setor.hashCode());
	result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
	result = prime * result
		+ ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
	Servidor other = (Servidor) obj;
	if (login == null) {
	    if (other.login != null)
		return false;
	} else if (!login.equals(other.login))
	    return false;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (senha == null) {
	    if (other.senha != null)
		return false;
	} else if (!senha.equals(other.senha))
	    return false;
	if (setor == null) {
	    if (other.setor != null)
		return false;
	} else if (!setor.equals(other.setor))
	    return false;
	if (tipo != other.tipo)
	    return false;
	if (tipoUsuario != other.tipoUsuario)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return nome;
    }

}
