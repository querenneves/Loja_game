package com.generation.games.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.games.model.Usuario;
import com.generation.games.model.UsuarioLogin;

import jakarta.validation.Valid;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsuario(String usuario);
	
	List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);

	Optional<Usuario> cadastrarUsuario(@Valid Usuario usuario);

	Optional<Usuario> atualizarUsuario(@Valid Usuario usuario);

}
