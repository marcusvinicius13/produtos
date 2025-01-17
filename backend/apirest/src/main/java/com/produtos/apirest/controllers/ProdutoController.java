package com.produtos.apirest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired 
	private ProdutoService produtoService;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna uma lista de produtos!")
	public ResponseEntity<List<Produto>> index(){
		return produtoService.buscarTodos();
	}
	
	@GetMapping(value = ("/produtos/{id}"))
	@ApiOperation(value = "Retorna um produto identificado pelo ID!")
	public ResponseEntity<Produto> show(@PathVariable(value =  "id") Long id) {
		return produtoService.buscarPorPk(id);
	}
	
	@PostMapping(value = "/produtos")
	@ApiOperation(value = "Cria um produto no banco de dados!")
	public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto) {
		return produtoService.salvar(produto);
	}
	
	@PutMapping(value = ("/produtos/{id}"))
	@ApiOperation(value = "Altera um produto no banco de dados de acordo com o Identificador passado como parametro de rota!")
	public ResponseEntity<Produto> update(@PathVariable(value =  "id") Long id, @RequestBody @Valid Produto produto) {
		return produtoService.atualizar(id, produto);
	}
	
	@PatchMapping(value = ("/produtos/{id}"))
	@ApiOperation(value = "Altera parte do produto passado no corpo da requisição!")
	public ResponseEntity<Produto> edit(@PathVariable(value =  "id") Long id, @RequestBody @Valid Produto produto) {
		return produtoService.atualizarParteDoProduto(id, produto);
	}
	
	@DeleteMapping(value = ("/produtos/{id}"))
	@ApiOperation(value = "Deleta um produto de acordo com o identificador passado como parâmetro de requisição!")
	public HttpStatus destroy(@PathVariable(value =  "id") Long id) {
		return produtoService.deletar(id);
	}
	
}
