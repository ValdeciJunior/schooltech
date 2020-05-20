package ce.com.valceci.schooltec.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplinaUpdatePartialRequest {
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

}
