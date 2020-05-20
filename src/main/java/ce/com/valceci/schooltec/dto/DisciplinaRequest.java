package ce.com.valceci.schooltec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaRequest {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "Código é obrigatório")
	private String codigo;
	
	@NotNull(message = "Vagas é obrigatório")
	private Integer vagas;
}
