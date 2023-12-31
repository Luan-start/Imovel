package com.exemple.imovel.exemple.imovel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.imovel.entities.Imovel;
import com.exemple.imovel.repository.ImovelRepository;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
	
	@Autowired
	ImovelRepository repo;
	
	@GetMapping()
	public ResponseEntity<List<Imovel>> getContatos() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@PostMapping()
	public ResponseEntity<Imovel> inserirContato(@RequestBody Imovel contato) {
		Imovel ct = repo.save(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/{idcontato}")
	public ResponseEntity<Imovel> alterarContato(@PathVariable("idcontato") Long idcontato,
			@RequestBody Imovel contato) {
		Optional<Imovel> opImovel = repo.findById(idcontato);
		try {
			Imovel ct = opImovel.get();
			ct.setRua(contato.getRua());
			ct.setNumero(contato.getNumero());
			ct.setBairro(contato.getBairro());
			ct.setCidade(contato.getCidade());
			ct.setEstado(contato.getEstado());
			ct.setStatus(contato.getStatus());
			repo.save(ct);
			return ResponseEntity.status(HttpStatus.OK).body(contato);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getUmContato(@PathVariable("id") long id) {
		Optional<Imovel> opContato = repo.findById(id);
		try {
			Imovel ct = opContato.get();
			return ResponseEntity.status(HttpStatus.OK).body(ct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Imovel> excluirUmContato(@PathVariable("id") long id) {
		Optional<Imovel> opContato = repo.findById(id);
		try {
			Imovel ct = opContato.get();
			repo.delete(ct);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}