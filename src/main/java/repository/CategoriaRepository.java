package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	// SELECT * FROM tb_categoria WHERE descricao LIKE "%descricao%";

}
