package beverage;

import java.util.List;
import model.Ingredient;

public class BlackTea implements Beverage {

	@Override
	public String prepare(List<Ingredient> ingredients) {
		return "black_tea is prepared";
	}
}
