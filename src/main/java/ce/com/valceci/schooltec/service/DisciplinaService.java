package ce.com.valceci.schooltec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ce.com.valceci.schooltec.dto.PesquisaDisciplina;
import ce.com.valceci.schooltec.entity.Disciplina;
import ce.com.valceci.schooltec.exception.DisciplinaNotFound;

public interface DisciplinaService {
	
	Disciplina cadastrar(Disciplina disciplina);
	
	void deletar(String uuid) throws Exception;
		
	Disciplina atualizar(Disciplina disciplina, String uuid) throws Exception;
	
	Disciplina get(String uuid) throws Exception;

	Page<Disciplina> listar(Pageable pageable, PesquisaDisciplina pesquisa) throws DisciplinaNotFound;
	
	String uploadImage(MultipartFile file, String uuid) throws Exception;

}
