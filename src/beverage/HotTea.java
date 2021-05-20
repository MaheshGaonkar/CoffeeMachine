package beverage;

import java.util.List;
import model.Ingredient;

public class HotTea implements Beverage {	
	@Override
	public String prepare(List<Ingredient> ingredients) {
		return "hot_tea is prepared";
	}
}