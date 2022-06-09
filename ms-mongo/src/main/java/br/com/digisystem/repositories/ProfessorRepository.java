package br.com.digisystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import br.com.digisystem.entities.ProfessorEntity;

public interface ProfessorRepository extends MongoRepository<ProfessorEntity, String> {

	@Query("  { nome: {$regex: /?0/  }  }  ")
	public List<ProfessorEntity> searchByNomeNativo(String nome);
	
	@Query("  { nome: {$regex: /:#{#nome}/  }  }  ")
	public List<ProfessorEntity> searchByNome(@Param("nome") String nome);
}