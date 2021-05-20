package exception;

public class NotSufficientIngredientException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotSufficientIngredientException(String errorMessage) {
		super(errorMessage);
	}
}
