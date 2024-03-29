package com.generation.games.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.games.model.Produto;
import com.generation.games.repository.CategoriaRepository;
import com.generation.games.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {

		return ResponseEntity.ok(produtoRepository.findAll());

		// Select * from tb_produto;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {

		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		// Select * from tb_produto;
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {

		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));

	}

	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {

			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> postagem = produtoRepository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtoRepository.deleteById(id);

		/* DELETE FROM tb_produto WHERE id = id */
	}

	// Consulta pelo preço maior do que o preço digitado emm ordem crescente

	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaiorQue(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanOrderByPreco(preco));
	}

	// Consulta pelo preço menor do que o preço digitado em ordem decrescente

	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenorQue(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanOrderByPrecoDesc(preco));
	}

}
