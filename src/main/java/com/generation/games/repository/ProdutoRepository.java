package com.generation.games.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.games.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	// SELECT * FROM tb_produto WHERE nome LIKE "%nome%";

	public List<Produto> findAllByPrecoGreaterThanOrderByPreco(BigDecimal preco);

	public List<Produto> findAllByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);

}
