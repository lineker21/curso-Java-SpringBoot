package br.com.digisystem.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.ProfessorCustomRepository;
import br.com.digisystem.repositories.ProfessorRepository;
import br.com.digisystem.services.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private ProfessorCustomRepository professorCustomRepository;

//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//	private int contador = 1;
	
	public List<ProfessorEntity> getAll() {
		
		List<ProfessorEntity> professores = this.professorRepository.findAll();
		
		
		//return this.listaUsuario;
		return professores;
	}
	
	public ProfessorEntity getOne(String id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
		
//		Optional<UsuarioEntity> usuarioOptional = 
//				this.usuarioRepository.findById(id);
		
//		if (usuarioOptional.isPresent()) {
//			return usuarioOptional.get();
//		}
//		else {
//			return null;
//		}
		
		
		return this.professorRepository.findById(id)
				.orElseThrow( 
						() -> new ObjNotFoundException("Elemento com ID "+ id + " não foi localizado") );
	}
	
	public ProfessorEntity create(ProfessorEntity professor) {
		
		ProfessorEntity meuProfessor = new ProfessorEntity();

		meuProfessor.setNome(professor.getNome());
		meuProfessor.setEmail(professor.getEmail());
		meuProfessor.setTelefone(professor.getTelefone());
		meuProfessor.setCpf(professor.getCpf());
		
		System.out.println(professor);
		
		//listaUsuario.add(meuUsuario);
		
		meuProfessor = this.professorRepository.save(meuProfessor);
		
		//this.contador++;
		
		return meuProfessor;
	}
	
	public ProfessorEntity update(String id, ProfessorEntity professor) {
		
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
		
		// verificar se existe o registro no banco de dados
		
		ProfessorEntity professorUpdate = 
					this.professorRepository.findById(id)
						.orElseThrow( () -> new ObjNotFoundException("ID " + id +  " não localizado") );
		
		//if (usuarioOptional.isPresent()) {
			//UsuarioEntity usuarioUpdate = usuarioOptional.get();
			
		professorUpdate.setEmail( professor.getEmail() );
		professorUpdate.setNome( professor.getNome() );
		professorUpdate.setTelefone(professor.getTelefone());
		professorUpdate.setCpf(professor.getCpf());
			
			return this.professorRepository.save(professorUpdate);
//		}
//		else {
//			return null;
//		}
	}
	
	public void delete(String id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			if (this.listaUsuario.get(i).getId() == id) {
//				this.listaUsuario.remove(i);
//			}
//		}
		
		this.professorRepository.deleteById(id);
		
	}
	
	public List<ProfessorEntity> getByNome(String nome){
		// return this.usuarioRepository.findByNomeContains(nome);
		return this.professorRepository.searchByNome(nome);	
		// return this.usuarioRepository.searchByNomeNativo(nome);	
		
	}
	
	public ProfessorEntity updateProfessor(String id, String nome) {
		return this.professorCustomRepository.updateProfessor(id, nome);
	}
}