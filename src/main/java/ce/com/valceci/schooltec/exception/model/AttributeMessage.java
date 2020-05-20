package ce.com.valceci.schooltec.exception.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	  private String attribute;

	  private String message;
}
