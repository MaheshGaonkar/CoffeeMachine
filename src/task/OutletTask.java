package task;

import java.util.List;
import exception.IngredientNotAvaliableException;
import exception.NotSufficientIngredientException;
import manager.BeverageMaker;
import manager.IngredientManager;
import model.BeverageType;
import model.Ingredient;

/**
 * This class is used to accept the task of beverage preparation
 * @author Mahesh Gaonkar
 *
 */
public class OutletTask implements Runnable {
	
	private BeverageMaker beverageManager;
	private IngredientManager ingredientManager;
	private BeverageType beverageType;
	private List<Ingredient> ingredients;
	
	public OutletTask(BeverageMaker beverageManager, IngredientManager ingredientManager,
			BeverageType beverageType, List<Ingredient> ingredients) {
		this.beverageManager = beverageManager;
		this.ingredientManager = ingredientManager;
		this.beverageType = beverageType;
		this.ingredients = ingredients;
	}
	
	@Override
	public void run() {
		String message;
		try {
			message = beverageManager.makeBeverage(beverageType, ingredientManager, ingredients);
		} catch (NotSufficientIngredientException | IngredientNotAvaliableException e) {
			message = e.getLocalizedMessage();
		}
		System.out.println(message);
	}
}