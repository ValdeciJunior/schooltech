package ce.com.valceci.schooltec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="disciplinas")
public class Disciplina {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false, unique = true)
	private String uuid;
	
	@Column(name = "nome", nullable = false, length=100)
	private String nome;
	
	@Column(name = "codigo", nullable = false, length=100)
	private String codigo;
	
	@Column(name ="vagas", nullable = false)
	private Integer vagas;
	
	@Lob
	private Byte[] imagem;
}
