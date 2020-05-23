package ce.com.valceci.schooltec.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -3219378577589315612L;
	
	private final String jwttoken;
}
