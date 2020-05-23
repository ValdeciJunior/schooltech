package ce.com.valceci.schooltec.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 426956299882194305L;

	private String username;
	private String password;
}
