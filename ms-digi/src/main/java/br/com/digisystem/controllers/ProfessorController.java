package br.com.digisystem.controllers;


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




import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.services.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;

	//GET
	@GetMapping("professores")
	public ResponseEntity<List<ProfessorDTO>> getAll() {

List<ProfessorEntity> lista = this.professorService.getAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );		
	}

	//GET 1
	@GetMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		
		ProfessorEntity professorEntity = this.professorService.getOne(id);
		
		return ResponseEntity.ok().body( professorEntity.toDTO() );
	}

	
	//CREATE
	@PostMapping("professores")
public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professor ) {
		
		

		
		ProfessorEntity professorEntity = professor.toEntity();
		
		ProfessorEntity professorEntitySalvo = this.professorService.save( professorEntity );
		
		return ResponseEntity.ok().body( professorEntitySalvo.toDTO() );
	}

	//UPDATE
	@PatchMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, 
			@RequestBody ProfessorDTO professor) {
		
		ProfessorEntity professorEntitySalvo =
				this.professorService.update(id, professor.toEntity() );
	
	return ResponseEntity.ok().body( professorEntitySalvo.toDTO() );
	}
	
	// DELETE
	@DeleteMapping("professores/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {

		this.professorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	//GET NOME USUARIO
		@GetMapping("professores/get-by-nome/{nome}")
		public ResponseEntity<List<ProfessorDTO>> getByNome(@PathVariable String nome){
			
	List<ProfessorEntity> lista = this.professorService.getByNome(nome);
			
			List<ProfessorDTO> listaDTO = new ArrayList<>();
			
			for (int i = 0; i < lista.size(); i++) {
				listaDTO.add( lista.get(i).toDTO() );
			}
			
			return ResponseEntity.ok().body( listaDTO );	
		}
		
		//PATCH USUARIO/ID
		@PatchMapping("proferssores/update/{id}")
		public ResponseEntity<Void> updateProfessor(@PathVariable int id, 
				@RequestBody ProfessorDTO dto){
			
			this.professorService.updateProfessor(id, dto.getNome());
			
			return ResponseEntity.ok().build();
		}
		
		@GetMapping("professores/export")
	    public void exportToCSV(HttpServletResponse response) throws IOException {
	        response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=professores_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<ProfessorEntity> lista = this.professorService.getAll();
	        
	        List<ProfessorDTO> listaDTO = new ArrayList<>();
			
			for(int i = 0; i < lista.size(); i++) {
				listaDTO.add(lista.get(i).toDTO());
			}
	        
	 
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"ID", "Nome", "cpf", "telefone", "email"};
	        String[] nameMapping = {"id", "nome", "cpf", "telefone", "email"};
	         
	        csvWriter.writeHeader(csvHeader);
	         
	        for (ProfessorDTO professorDTO : listaDTO) {
	            csvWriter.write(professorDTO, nameMapping);
	        }
	         
	        csvWriter.close();
	         
	    }
		
		
}