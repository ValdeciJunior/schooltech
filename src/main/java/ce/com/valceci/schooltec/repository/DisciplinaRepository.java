package ce.com.valceci.schooltec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ce.com.valceci.schooltec.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {

	Page<Disciplina> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
	
}
