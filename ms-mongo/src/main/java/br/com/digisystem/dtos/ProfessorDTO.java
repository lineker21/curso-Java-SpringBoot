package br.com.digisystem.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

	private String id;
	
	@NotEmpty( message = "O campo nome é obrigatório" )
	@NotBlank( message = "o campo nome não pode ser vazio")
	@Length(min = 3, message = "O campo nome deve possuir pelo menos 3 caracteres")
	private String nome;
	
	private String email;
	
	private String cpf;
	
	private String telefone;
	
	
	public ProfessorEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorEntity.class);
	}
	
}
