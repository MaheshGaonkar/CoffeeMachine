package beverage;

import java.util.List;
import model.Ingredient;

public interface Beverage {		
	String prepare(List<Ingredient> ingredients);
}
