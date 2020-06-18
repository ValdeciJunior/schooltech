package ce.com.valceci.schooltec.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DisciplinaResponse {

	private String nome;
	
	private String codigo;
	
	private Integer vagas;
	
	private String imagem;
}
