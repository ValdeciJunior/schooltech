package ce.com.valceci.schooltec.exception;

import java.io.IOException;

public class FileUploadException extends IOException {
	private static final long serialVersionUID = 1L;
	
	public static final String OCORREU_UM_ERRO_AO_TENTAR_FAZER_O_UPLOAD_DO_ARQUIVO = "Ocorreu um erro ao tentar fazer o upload do arquivo";
	
	public FileUploadException(String msg) {
		super(msg);
	}
}
