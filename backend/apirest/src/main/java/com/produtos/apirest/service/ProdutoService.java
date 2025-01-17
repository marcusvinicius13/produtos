package com.produtos.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	public ResponseEntity<List<Produto>> buscarTodos() {
		List<Produto> produtoList = produtoRepository.findAll();	
		
		if(produtoList.isEmpty())
			return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Produto>>(produtoList, HttpStatus.CREATED);	
	
	}

	
	public ResponseEntity<Produto> buscarPorPk(Long id) {
		
		if(produtoRepository.findById(id).isPresent())
			return new ResponseEntity<Produto>(produtoRepository.findById(id).get(), HttpStatus.OK);	
		
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
	
	}
	
	
	public ResponseEntity<Produto> salvar(Produto produto){
		
		if(produto != null) {
			Produto produtoSalvo = produtoRepository.save(produto);
			return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND); 
	}
	
	
	public ResponseEntity<Produto> atualizar(Long id, Produto produto){
		if(produtoRepository.findById(id).isPresent()) {
			produto.setId(id);
			produtoRepository.save(produto);
			
			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND); 
	}
	
	public ResponseEntity<Produto> atualizarParteDoProduto(Long id, Produto produtoAlterar){
		
		if(produtoRepository.findById(id).isPresent()) {
			Produto produto = produtoRepository.findById(id).get();
			
			if(produtoAlterar.getNome() != null)
				produto.setNome(produtoAlterar.getNome());
			
			if(produtoAlterar.getQuantidade() != null)
				produto.setQuantidade(produtoAlterar.getQuantidade());
			
			if(produtoAlterar.getValor() != null)
				produto.setValor(produtoAlterar.getValor());
			
			produtoRepository.save(produto);
			
			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
			
		}
		
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND); 
	}
	
	
	public HttpStatus deletar(Long id){
		if(produtoRepository.findById(id).isPresent()) {
			produtoRepository.deleteById(id);
			return HttpStatus.OK;
		}
		
		return HttpStatus.NOT_FOUND;
	}
	

}
