package org.generation.blogPessoal.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/postagem")
public class PostagemController implements WebMvcConfigurer
{

	@Autowired
	private PostagemRepository repository;
	
	//
	@GetMapping 
	public ResponseEntity<List<Postagem>> findAllPostagem() 
	{		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Postagem>findByIdPostagem(@PathVariable Long id) 
	{		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) //se tiver o id retorne ok
				.orElse(ResponseEntity.notFound().build()); //senão retorne o notfound
	}
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findAllByTitulo(@PathVariable String titulo) 
	{		
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping(value = "/maior")
	public ResponseEntity<List<Postagem>> findAllMaior(){
		return ResponseEntity.ok(repository.findAllMaior());
	}
	
	@GetMapping(value = "/ordem")
	public ResponseEntity<List<Postagem>> anoDesc(){
		return ResponseEntity.ok(repository.anoDesc());
	}
	
	@GetMapping(value = "/asc")
	public ResponseEntity<List<Postagem>> anoAsc(){
		return ResponseEntity.ok(repository.anoAsc());
	}
	
	@GetMapping(value = "/intervalo")
	public ResponseEntity<List<Postagem>> anoIntervalo(){
		return ResponseEntity.ok(repository.anoIntervalo());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	repository.deleteById(id);
	}
}