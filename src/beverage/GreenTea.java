package beverage;

import java.util.List;
import model.Ingredient;

public class GreenTea implements Beverage {

	@Override
	public String prepare(List<Ingredient> ingredients){
		return "green_tea is prepared";
	}
	
}