package br.com.digisystem.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import br.com.digisystem.dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.services.UsuarioService;

@RestController
public class UsuarioController {
	
//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//    private int contador = 1;
	
	//private UsuarioService usuarioService = new UsuarioService();
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("usuarios")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
//		System.out.println("primeiro usuário");
//		return "um texto 2";
		
		List<UsuarioEntity> lista = this.usuarioService.getAll();
		
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );		
	}
	// usuarios/1
	// usuarios/12
	@GetMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable int id) {
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
		
		UsuarioEntity usuarioEntity = this.usuarioService.getOne(id);
		
		return ResponseEntity.ok().body( usuarioEntity.toDTO() );
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuario ) {
		
		
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
		
		UsuarioEntity usuarioEntity = usuario.toEntity();
		
		UsuarioEntity usuarioEntitySalvo = this.usuarioService.create( usuarioEntity );
		
		return ResponseEntity.ok().body( usuarioEntitySalvo.toDTO() );
	}
	// usuarios/5
	@PatchMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable int id, 
			@RequestBody UsuarioDTO usuario) {
		
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
	
		
		UsuarioEntity usuarioEntitySalvo =
					this.usuarioService.update(id, usuario.toEntity() );
		
		return ResponseEntity.ok().body( usuarioEntitySalvo.toDTO() );
	}
	
	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			if (this.listaUsuario.get(i).getId() == id) {
//				this.listaUsuario.remove(i);
//			}
//		}
		
		this.usuarioService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	//GET NOME USUARIO
	@GetMapping("usuarios/get-by-nome/{nome}")
	public ResponseEntity<List<UsuarioDTO>> getByNome(@PathVariable String nome){
		
List<UsuarioEntity> lista = this.usuarioService.getByNome(nome);
		
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );	
	}
	
	//PATCH USUARIO/ID
	@PatchMapping("usuarios/update/{id}")
	public ResponseEntity<Void> updateUsuario(@PathVariable int id, 
			@RequestBody UsuarioDTO dto){
		
		this.usuarioService.updateUsuario(id, dto.getNome());
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("usuarios/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<UsuarioEntity> lista = this.usuarioService.getAll();
        
        List<UsuarioDTO> listaDTO = new ArrayList<>();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID", "E-mail", "Full Name", "Roles", "Enabled"};
        String[] nameMapping = {"id", "email", "fullName", "roles", "enabled"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (UsuarioDTO usuarioDTO : listaDTO) {
            csvWriter.write(usuarioDTO, nameMapping);
        }
         
        csvWriter.close();
         
    }
	
}