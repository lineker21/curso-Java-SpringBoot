package br.com.digisystem.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.repositories.ProfessorCustomRepository;

@Primary
@Repository
public class ProfessorCustomRepositoryImpl implements ProfessorCustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public ProfessorEntity updateProfessor(String id, String nome) {
		
	
		Query query = new Query();
		

		query.addCriteria(Criteria.where("id").is(id));
		
	
		Update update = new Update();
		update.set("nome", nome);
		
	
		mongoTemplate.findAndModify(query,update,ProfessorEntity.class);
		
		
		ProfessorEntity professor = mongoTemplate.findOne(query, ProfessorEntity.class);
		
		return professor;
	}

}