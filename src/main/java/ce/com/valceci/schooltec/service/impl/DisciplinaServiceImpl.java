package ce.com.valceci.schooltec.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ce.com.valceci.schooltec.dto.PesquisaDisciplina;
import ce.com.valceci.schooltec.entity.Disciplina;
import ce.com.valceci.schooltec.exception.DisciplinaNotFound;
import ce.com.valceci.schooltec.exception.FileUploadException;
import ce.com.valceci.schooltec.repository.DisciplinaRepository;
import ce.com.valceci.schooltec.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService{

	private final DisciplinaRepository repository;
	
	@Override
	public Disciplina cadastrar(Disciplina disciplina) {
		
		repository.save(disciplina);
		
		return disciplina;
	}

	@Override
	public void deletar(String uuid) throws Exception {
		Disciplina disciplina = get(uuid);
		repository.delete(disciplina);
	}

	@Override
	public Page<Disciplina> listar(Pageable pageable, PesquisaDisciplina pesquisa) throws DisciplinaNotFound {
		log.info("Consultando com o parâmetro nome = "+pesquisa.getNome());
		Page<Disciplina> disiplinas;
		if(pesquisa.getNome() != null) {
			log.info("Consultando com o parâmetro nome = "+pesquisa.getNome());
			disiplinas = repository.findAllByNomeIgnoreCaseContaining(pesquisa.getNome(), pageable);
		}else {
			log.info("Consultando sem parâmetros");
			disiplinas = repository.findAll(pageable);
		}
		if(disiplinas.isEmpty()) {
			throw new DisciplinaNotFound(DisciplinaNotFound.NAO_FOI_ENCONTRADA_NENHUMA_DISCIPLINA);
		}
		return disiplinas;
	}

	@Override
	public Disciplina atualizar(Disciplina disciplina, String uuid) throws Exception {
		Disciplina disciplinaSalva = get(uuid);
		if(disciplina.getNome() != null) {
			disciplinaSalva.setNome(disciplina.getNome());
		}
		return repository.save(disciplinaSalva);
	}

	@Override
	public Disciplina get(String uuid) throws Exception {
		Optional<Disciplina> disciplinaOptional = repository.findById(uuid);
		if(!disciplinaOptional.isPresent()) {
			throw new DisciplinaNotFound(DisciplinaNotFound.DISCIPLINA_NAO_ENCONTRADA);
		}
		return disciplinaOptional.get();
	}

	@Override
	public void uploadImage(MultipartFile file, String uuid) throws Exception {

		try {
		    Disciplina disciplina = get(uuid);

		    Byte[] byteObjects = new Byte[file.getBytes().length];

		    int i = 0;

		    for (byte b : file.getBytes()){
		        byteObjects[i++] = b;
		    }

		    disciplina.setImagem(byteObjects);
		    repository.save(disciplina);
		} catch (FileUploadException e) {
		    log.error("Error occurred", e);

		    e.printStackTrace();
		    throw new FileUploadException(FileUploadException.OCORREU_UM_ERRO_AO_TENTAR_FAZER_O_UPLOAD_DO_ARQUIVO+" "+file.getOriginalFilename());
		}
	}

}
