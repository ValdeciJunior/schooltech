package ce.com.valceci.schooltec.exception;

import javassist.NotFoundException;

public class DisciplinaNotFound extends NotFoundException {
	private static final long serialVersionUID = 1L;
	
	public static  final String DISCIPLINA_NAO_ENCONTRADA = "Disciplina não encontrada";
	
	public static  final String NAO_FOI_ENCONTRADA_NENHUMA_DISCIPLINA = "Não foi encontrada nenhuma disciplina";

	public DisciplinaNotFound(String msg) {
		super(msg);
	}

}
