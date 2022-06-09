package br.com.digisystem.repositories;

import br.com.digisystem.entities.ProfessorEntity;

public interface ProfessorCustomRepository {

	ProfessorEntity updateProfessor(String id, String nome);
}