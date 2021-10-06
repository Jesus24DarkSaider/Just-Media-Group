package com.justmediagroup.logica_comun.exception;

/**
 * @author fobregon
 * 
 * Esta clase se mantiene para no afectar la dependecia de ella en otros servicios que la reutilizan
 * sin embargo la clase a usar para generar las excepciones de negocio es BusinessException
 *
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoError error;
	
	public ApplicationException(String mensaje, TipoError error) {
		super(mensaje);
		this.error = error;
	}
	
	public ApplicationException(String mensaje, TipoError error, Throwable causa) {
		super(mensaje,causa);
		this.error = error;
	}
	
	/**
	 * Por defecto esta definido como tipo de error: ERROR_INESPERADO
	 * @param mensaje
	 */
	public ApplicationException(String mensaje) {
		super(mensaje);
		this.error = TipoError.ERROR_INESPERADO;
	}
	
	/**
	 * @return the error
	 */
	public TipoError getError() {
		return error;
	}
}
