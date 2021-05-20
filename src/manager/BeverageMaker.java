package manager;

import java.util.List;
import java.util.Objects;
import beverage.Beverage;
import beverage.BlackTea;
import beverage.GreenTea;
import beverage.HotCoffee;
import beverage.HotTea;
import exception.IngredientNotAvaliableException;
import exception.NotSufficientIngredientException;
import model.BeverageType;
import model.Ingredient;

public class BeverageMaker {
	
	/**
	 * This method is used to prepared the beverage of given beverageType if all ingredients are present or else return message
	 * @param beverageToPrepare Beverage which need to prepared
	 * @param ingredientManager Ingredientanager is used to check available ingredients
	 * @param ingredientsOfBeverage Ingredients which are require to prepared beverage
	 * @return Beverage
	 * @throws NotSufficientIngredientException throw if sufficient ingredient quantity not available
	 * @throws IngredientNotAvaliableException throw if ingredient not available
	 */
	public String makeBeverage(BeverageType beverageToPrepare, IngredientManager ingredientManager, 
			List<Ingredient> ingredientsOfBeverage) throws NotSufficientIngredientException, IngredientNotAvaliableException {
		reduceIngredentIfBeverageCanPrepare(beverageToPrepare, ingredientManager, ingredientsOfBeverage);
		Beverage beverage = getBeverageFactory(beverageToPrepare);
		return beverage.prepare(ingredientsOfBeverage);
	}
	
	/**
	 * This method is used to check all ingredient quantity and ingredient available. If all there is not any shortage of any ingredient
	 * then quantity of used ingredient will be reduce
	 * @param beverageType  Beverage which need to ingredient
	 * @param ingredientManager Ingredientanager is used to check available ingredients
	 * @param ingredients Ingredients which are require to prepared beverage
	 * @throws IngredientNotAvaliableException throw if sufficient ingredient quantity not available
	 * @throws NotSufficientIngredientException throw if ingredient not available
	 */
	private synchronized void reduceIngredentIfBeverageCanPrepare(BeverageType beverageType, IngredientManager ingredientManager, List<Ingredient> ingredients) 
			throws IngredientNotAvaliableException, NotSufficientIngredientException {
		Ingredient.Type notAvailableIngredient = ingredientManager.getNotAvaliableIngredient(ingredients);
		if (Objects.nonNull(notAvailableIngredient)) {
			throw new IngredientNotAvaliableException(beverageType.getValue() + " cannot be prepared because " + notAvailableIngredient.getValue() + " is not available");
		}
		Ingredient.Type notSufficientIngredient = ingredientManager.getInsufficientIngredient(ingredients);
		if (Objects.nonNull(notSufficientIngredient)) {
			throw new NotSufficientIngredientException(beverageType.getValue() + " cannot be prepared because " + notSufficientIngredient.getValue() + " is not sufficient");
		}
		ingredientManager.reduceIngredientQty(ingredients);
	}
	
	/**
	 * This method is used to get the object of beverageFactory for given beverageType
	 * @param beverageType
	 * @return
	 */
	private Beverage getBeverageFactory(BeverageType beverageType) {
		Beverage beverage = null;
		switch(beverageType) {
			case HOT_TEA:
				beverage = new HotTea();
				break;
			case HOT_COFFEE:
				beverage = new HotCoffee();
				break;
			case BLACK_TEA:
				beverage = new BlackTea();
				break;
			case GREEN_TEA:
				beverage = new GreenTea();
		}
		return beverage;
	}
}