package br.com.digisystem.services;

import java.util.List;

import br.com.digisystem.entities.ProfessorEntity;

public interface ProfessorService {

	List<ProfessorEntity> getAll();
	ProfessorEntity getOne(String id);
	ProfessorEntity create(ProfessorEntity professor);
	ProfessorEntity update(String id, ProfessorEntity professor);
	void delete(String id);
	List<ProfessorEntity> getByNome(String nome);
	ProfessorEntity updateProfessor(String id, String nome);
}