package com.example.demo.model;

/**
 * Objeto de usuario: todos los datos del perfil salen del servidor.
 */
public class Usuario {

	private String nombre;
	private String email;
	private String github;
	private String avatarUrl;

	public Usuario() {
	}

	public Usuario(String nombre, String email, String github, String avatarUrl) {
		this.nombre = nombre;
		this.email = email;
		this.github = github;
		this.avatarUrl = avatarUrl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
