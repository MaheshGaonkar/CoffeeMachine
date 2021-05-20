package manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.Ingredient;

/**
 * In this class all the ingredients are managing like adding new ingredient, checking ingredient qty
 * refilling the ingredient qty and any operation which is related to ingredient
 * 
 * @author Mahesh Gaonkar
 *
 */
public class IngredientManager {
	
	
	private static final Map<Ingredient.Type, Ingredient> ingredientList = new HashMap<>() ;
	
	/**
	 * This method is used to add any new ingredients or update the existing ingredient qty.
	 * @param ingredientsWithQty - Contains ingredients name with qty
	 */
	public void addIngredient(Map<Ingredient.Type, Integer> ingredientsWithQty) {
		ingredientsWithQty.entrySet().forEach(ingredientWithQty -> {
			if (ingredientList.containsKey(ingredientWithQty.getKey())) {
				Ingredient ingredient = ingredientList.get(ingredientWithQty.getKey());
				ingredient.addQty(ingredientWithQty.getValue());
			} else {
				Ingredient ingredient = createIngredient(ingredientWithQty.getKey(), ingredientWithQty.getValue());
				ingredientList.put(ingredientWithQty.getKey(), ingredient);
			}
		});
	}
	
	/**
	 * This method is used to add more quantity in existing ingredient.
	 * @param ingredientToRefill Ingredient name to which the qty need to updated
	 * @param quantityToRefill Quantity which need to add
	 */
	public void refillIngredient(Ingredient.Type ingredientToRefill, final int quantityToRefill) {
		Ingredient ingredient = ingredientList.getOrDefault(ingredientToRefill, new Ingredient(ingredientToRefill, 0));
		ingredient.addQty(quantityToRefill);
	}
	
	/**
	 * This method is used to check for given ingredient is sufficient quantities available or not
	 * @param ingredientType Ingredient name whose quantities need to check
	 * @param requireQuantity Quantities to be required
	 * @return true if sufficient quantities are available or else false
	 */
	public boolean hasSufficientQty(Ingredient.Type ingredientType, final int requireQuantity) {
		return ingredientList.containsKey(ingredientType) 
				&& ingredientList.get(ingredientType).getQty() > 0 && 
				requireQuantity <= ingredientList.get(ingredientType).getQty();
	}
	
	/**
	 * This method is used to check for given ingredient is available or not
	 * @param ingredientType Ingredient name which need to check
	 * @return true if Ingredient is available or else false
	 */
	public boolean isIngredientAvailable(Ingredient.Type ingredientType) {
		return ingredientList.containsKey(ingredientType) && ingredientList.get(ingredientType).getQty() > 0;
	}
	
	/**
	 * This method is used to reduce the quantities of given ingredient list
	 * @param ingredientsToReduceQuantities
	 */
	public void reduceIngredientQty(List<Ingredient> ingredientsToReduceQuantities) {
		ingredientsToReduceQuantities.stream().filter(ingredient -> hasSufficientQty(ingredient.getName(), ingredient.getQty()))
		.forEach(ingredient -> {
			ingredientList.get(ingredient.getName()).reduceQty(ingredient.getQty());
		});
	}
	
	public Ingredient.Type getInsufficientIngredient(List<Ingredient> ingredients) {
		return ingredients.stream().filter(ingredient -> 
		!hasSufficientQty(ingredient.getName(), ingredient.getQty())).map(ingredient -> ingredient.getName())
		.findFirst().orElse(null);
	}

	public Ingredient.Type getNotAvaliableIngredient(List<Ingredient> ingredients) {
		Ingredient.Type ingredientType = ingredients.stream().filter(ingredient -> 
		!isIngredientAvailable(ingredient.getName())).map(ingredient -> ingredient.getName())
				.findFirst().orElse(null);
		return ingredientType;
	}
	
	public Set<String> getAllIngredientWhichHasZeroQty() {
		return ingredientList.entrySet().stream().filter(ingredient -> !isIngredientAvailable(ingredient.getKey()))
		.map(ingredient -> ingredient.getValue().getName().getValue()).collect(Collectors.toSet());
	}
	
	private static Ingredient createIngredient(Ingredient.Type ingredientType, final int qty) {
		return new Ingredient(ingredientType, qty);
	}
}