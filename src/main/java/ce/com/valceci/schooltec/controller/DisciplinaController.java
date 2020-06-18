package ce.com.valceci.schooltec.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ce.com.valceci.schooltec.dto.DisciplinaRequest;
import ce.com.valceci.schooltec.dto.DisciplinaResponse;
import ce.com.valceci.schooltec.dto.DisciplinaUpdatePartialRequest;
import ce.com.valceci.schooltec.dto.PesquisaDisciplina;
import ce.com.valceci.schooltec.entity.Disciplina;
import ce.com.valceci.schooltec.exception.DisciplinaNotFound;
import ce.com.valceci.schooltec.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
@Slf4j
public class DisciplinaController {

	private final DisciplinaService service;
	
	@PostMapping
	public ResponseEntity<DisciplinaResponse> cadastrar(@Valid @RequestBody DisciplinaRequest request){
		Disciplina disciplina = Disciplina.builder().nome(request.getNome()).codigo(request.getCodigo()).vagas(request.getVagas()).build();
		Disciplina saved = service.cadastrar(disciplina);
		DisciplinaResponse response = DisciplinaResponse.builder().nome(saved.getNome()).codigo(saved.getCodigo()).vagas(saved.getVagas()).build();
		return ResponseEntity.created(URI.create("/disciplinas/"+saved.getUuid())).body(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<Disciplina>> retornaDisciplinas(@RequestParam(name = "nome", required = false)String nome,  Pageable pageable) throws DisciplinaNotFound{
		PesquisaDisciplina pesquisa = new PesquisaDisciplina();
		if(nome != null) {
			pesquisa.setNome(nome);
		}
		Page<Disciplina> disciplinas = service.listar(pageable, pesquisa);
		return ResponseEntity.ok(disciplinas);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<DisciplinaResponse> get(@PathVariable("uuid") String uuid) throws Exception{
		Disciplina disciplina = service.get(uuid);
		DisciplinaResponse response = DisciplinaResponse.builder().nome(disciplina.getNome()).codigo(disciplina.getCodigo()).vagas(disciplina.getVagas()).build();
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{uuid}")
	public ResponseEntity<DisciplinaResponse> atualizarParte(@PathVariable("uuid")String uuid, @Valid @RequestBody DisciplinaUpdatePartialRequest request) throws Exception{
		Disciplina disciplina = Disciplina.builder().nome(request.getNome()).build();
		Disciplina disciplinaSalva = service.atualizar(disciplina, uuid);
		DisciplinaResponse response = DisciplinaResponse.builder().imagem(disciplinaSalva.getImagem()).nome(disciplinaSalva.getNome()).codigo(disciplinaSalva.getCodigo()).vagas(disciplinaSalva.getVagas()).build();
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> deletar(@PathVariable("uuid") String uuid) throws Exception{
		service.deletar(uuid);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{uuid}/upload-image")
	public ResponseEntity<?> handleFileUpload(@PathVariable("uuid") String uuid, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
		String url = service.uploadImage(file, uuid);
		log.info("You successfully uploaded " + file.getOriginalFilename() + "!");
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
		return ResponseEntity.ok(url);
	}
	
}
