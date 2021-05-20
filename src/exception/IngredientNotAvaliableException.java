package exception;

public class IngredientNotAvaliableException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IngredientNotAvaliableException(String errorMessage) {
		super(errorMessage);
	}
}
