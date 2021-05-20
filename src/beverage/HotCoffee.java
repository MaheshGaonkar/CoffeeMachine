package beverage;

import java.util.List;
import model.Ingredient;

public class HotCoffee implements Beverage {

	@Override
	public String prepare(List<Ingredient> ingredients) {
		return "hot_coffee is prepared";
	}
	
}
