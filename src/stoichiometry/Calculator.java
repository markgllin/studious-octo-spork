package stoichiometry;

import java.util.List;

public class Calculator {

	/* calcs the mass of a compound. Compound is taken in as a List
	 * of list of string, in the same format that is returned from
	 * parseFormula(). Mass is returned as a double.
	 */
 	public static float calcCompoundMass(List<Pair> compound){
 		float mass = 0;

 		//search for each element and calc the mass
 		for (Pair curElement:compound)
 			mass += curElement.getPairElement().getMass() * curElement.getPairMultiplier();

		return mass;
	}
	
	/* calculates the subtotal mass of an element in a compound.
	 * takes in a compound list (e.g. {<symbol>, <multiplier>}) and
	 * searches for the corresponding element in the element list, using that
	 * to calculate the subtotal mass once found.
	 */
	public static float calcElementMass(Pair compound){
 		return compound.getPairElement().getMass() * compound.getPairMultiplier();
	}
 	
	/* calculates the percent composition an element is in a compound.
	 * requires the total mass of the element in the compound as well
	 * as the total mass of compound. If an uncaught error occured earlier
	 * that resulted int total compound mass being 0, function returns -1
	 * to indicate an error.
	 */
 	public static float calcPercentMass(float compMass, float elemMass){
 		//if division by 0, return -1
 		if (compMass<1) return -1;
 		return (elemMass/compMass)*100;
 	}

}
