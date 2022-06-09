package br.com.digisystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.services.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProfessorController {
	
//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//    private int contador = 1;
	
	//private UsuarioService usuarioService = new UsuarioService();
	@Autowired
	private ProfessorService professorService;

	@GetMapping("professores")
	@ApiOperation(value = "Listar todos os professores")
	@ApiResponses (value =  {
			@ApiResponse(code=200, message = "Sucesso"),
			@ApiResponse(code=400, message = "Bad Request")
			
	})
	public ResponseEntity<List<ProfessorDTO>> getAll() {
//		System.out.println("primeiro usuário");
//		return "um texto 2";
		
		List<ProfessorEntity> lista = this.professorService.getAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		List<ProfessorDTO> listaDTO2 = lista.stream()
					.map( x -> x.toDTO() ).collect(Collectors.toList())  ;
		
		return ResponseEntity.ok().body( listaDTO );		
	}
	// usuarios/1
	// usuarios/12
	@GetMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable String id) {
		System.out.println("o valor do ID é "+ id);
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
		
//		UsuarioEntity usuario = this.usuarioService.getOne(id);
//		return usuario;
		
		ProfessorEntity professorEntity = this.professorService.getOne(id);
		
		return ResponseEntity.ok().body( professorEntity.toDTO() );
	}
	
	@PostMapping("professores")
	public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody ProfessorDTO professor ) {
		
		
//		UsuarioEntity meuUsuario= new UsuarioEntity();
//		meuUsuario.setId(this.contador);
//		//meuUsuario.setNome("Fabrizio " + this.contador);
//		meuUsuario.setNome(usuario.getNome());
//		//meuUsuario.setEmail("fabrizio@grandeporte.com.br");
//		meuUsuario.setEmail(usuario.getEmail());
//		
//		System.out.println(usuario);
//		
//		listaUsuario.add(meuUsuario);
//		this.contador++;
//		
//		
//		return meuUsuario;
		
//		UsuarioEntity usuarioEntity = new UsuarioEntity();
//		usuarioEntity.setEmail( usuario.getEmail() );
//		usuarioEntity.setNome( usuario.getNome() );
//		return this.usuarioService.create(usuarioEntity);
		
		ProfessorEntity professorEntity = professor.toEntity();
		
		ProfessorEntity professorEntitySalvo = this.professorService.create( professorEntity );
		
		return ResponseEntity.ok().body( professorEntitySalvo.toDTO() );
	}
	// usuarios/5
	@PatchMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable String id, 
			@RequestBody ProfessorDTO professor) {
		
//		for (int i =0 ; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				
//				//if (usuario.getNome() != null ) {
//				this.listaUsuario.get(i).setNome( usuario.getNome() );
//				//}
//				
//				this.listaUsuario.get(i).setEmail( usuario.getEmail() ); 
//				
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
	
		
		ProfessorEntity professorEntitySalvo =
					this.professorService.update(id, professor.toEntity() );
		
		return ResponseEntity.ok().body( professorEntitySalvo.toDTO() );
	}
	
	@DeleteMapping("professores/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			if (this.listaUsuario.get(i).getId() == id) {
//				this.listaUsuario.remove(i);
//			}
//		}
		
		this.professorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("professores/get-by-nome/{nome}")
	public ResponseEntity<List<ProfessorDTO>> getByNome(@PathVariable String nome){
		
		List<ProfessorEntity> lista = this.professorService.getByNome(nome);
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
				
		return ResponseEntity.ok().body( listaDTO );
	}
	
	@PatchMapping("professores/update/{id}")
	public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable String id, 
			@RequestBody ProfessorDTO dto){
		
		ProfessorEntity professor = this.professorService.updateProfessor(id, dto.getNome());
		
		return ResponseEntity.ok().body( professor.toDTO() );
	}
}