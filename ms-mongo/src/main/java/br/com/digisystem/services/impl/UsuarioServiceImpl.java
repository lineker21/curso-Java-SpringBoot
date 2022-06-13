package br.com.digisystem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.CustomRepository;
import br.com.digisystem.repositories.UsuarioRepository;
import br.com.digisystem.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CustomRepository customRepository;

//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//	private int contador = 1;
	
	public List<UsuarioEntity> getAll() {
		
		List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
		
		
		//return this.listaUsuario;
		return usuarios;
	}
	
	public UsuarioEntity getOne(String id) {
		
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
		
		
		return this.usuarioRepository.findById(id)
				.orElseThrow( 
						() -> new ObjNotFoundException("Elemento com ID "+ id + " não foi localizado") );
	}
	
	public UsuarioEntity create(UsuarioEntity usuario) {
		
		UsuarioEntity meuUsuario = new UsuarioEntity();
		
		//meuUsuario.setId(this.contador);
		//meuUsuario.setNome("Fabrizio " + this.contador);
		meuUsuario.setNome(usuario.getNome());
		//meuUsuario.setEmail("fabrizio@grandeporte.com.br");
		meuUsuario.setEmail(usuario.getEmail());
		
		System.out.println(usuario);
		
		//listaUsuario.add(meuUsuario);
		
		meuUsuario = this.usuarioRepository.save(meuUsuario);
		
		//this.contador++;
		
		return meuUsuario;
	}
	
	public UsuarioEntity update(String id, UsuarioEntity usuario) {
		
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
		
		UsuarioEntity usuarioUpdate = 
					this.usuarioRepository.findById(id)
						.orElseThrow( () -> new ObjNotFoundException("ID " + id +  " não localizado") );
		
		//if (usuarioOptional.isPresent()) {
			//UsuarioEntity usuarioUpdate = usuarioOptional.get();
			
			usuarioUpdate.setEmail( usuario.getEmail() );
			usuarioUpdate.setNome( usuario.getNome() );
			
			return this.usuarioRepository.save(usuarioUpdate);
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
		try {
			this.usuarioRepository.deleteById(id);
		}
		catch (Exception e) {
			log.error("Erro ao deletar usuário com ID : " + id + ". Erro: " + e.getMessage() );
		}
		
	}
	
	public List<UsuarioEntity> getByNome(String nome){
		// return this.usuarioRepository.findByNomeContains(nome);
		return this.usuarioRepository.searchByNome(nome);	
		// return this.usuarioRepository.searchByNomeNativo(nome);	
		
	}
	
	public UsuarioEntity updateUsuario(String id, String nome) {
		return this.customRepository.updateUsuario(id, nome);
	}
	
	public Page<UsuarioEntity> getAllPagination(int page, int limit){
		
		PageRequest pageRequest = PageRequest.of(page, limit);
		
		Page<UsuarioEntity> paginado = usuarioRepository.findAll( pageRequest );
		
		return paginado;
	}
}