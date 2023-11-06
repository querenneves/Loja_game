package com.generation.games.model;

import java.time.LocalDate;

public class UsuarioLogin {

	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String foto;
	private LocalDate Datanascimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDatanascimento() {
		return Datanascimento;
	}
	public void setDatanascimento(LocalDate datanascimento) {
		Datanascimento = datanascimento;
	}
}
