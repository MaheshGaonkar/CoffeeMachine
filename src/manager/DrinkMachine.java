package manager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.BeverageType;
import model.Ingredient;
import task.OutletTask;

public class DrinkMachine {
		
	private final BeverageMaker beverageManager;
	private final IngredientManager ingredientManager;
	
	ExecutorService outletService;
	
	public DrinkMachine(final int totalOutlets) {
		outletService = Executors.newFixedThreadPool(totalOutlets);
		
		beverageManager = new BeverageMaker();
		ingredientManager = new IngredientManager();
	}
	
	/**
	 * This method is used to add any new ingredients.
	 * @param ingredientsWithQty - Contains ingredients name with quantity
	 */
	public void addIngredientWithQty(Map<Ingredient.Type, Integer> ingredientWithQty) {
		ingredientManager.addIngredient(ingredientWithQty);
	}
	
	/**
	 * This method is used to accept the order of beverage.
	 * For every order new task will be created i.e outlet task
	 * @param beverageToPrepare Beverage to prepared
	 * @param ingredientsOfBeverage Ingredients which are require to prepared beverage
	 */
	public void prepareBeverage(BeverageType beverageToPrepare, List<Ingredient> ingredientsOfBeverage) {		
		outletService.execute(new OutletTask(beverageManager, ingredientManager, beverageToPrepare, ingredientsOfBeverage));
	}
	
	/**
	 * This method is used to get all the ingredient whose quantity is zero
	 * @return Ingredients name whose quantity are zero
	 */
	public Set<String> getAllIngredientWhichHasZeroQty() {
		return ingredientManager.getAllIngredientWhichHasZeroQty();
	}
	
	/**
	 * This method is used to refill the ingredient quantity
	 * @param ingredientToRefill ingredient to refill quantity
	 * @param quantity quantity to be refill
	 */
	public void refillIngredient(Ingredient.Type ingredientToRefill, final int quantity) {
		ingredientManager.refillIngredient(ingredientToRefill, quantity);
	}
}