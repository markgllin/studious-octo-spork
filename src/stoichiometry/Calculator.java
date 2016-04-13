package stoichiometry;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

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

	/**  
	 * Takes in a list of integers and finds the GCD for all of them
	 * recursively. Performance may be better if changed into interative
	 * version
	 * @param numbers = list of integers
	 * @return GCD as an integer
	 */
	public static int findGCD(List<Integer> numbers){
		List<Integer> posInts = new ArrayList<Integer>();
		List<Integer> odd = new ArrayList<Integer>();
		List<Integer> even = new ArrayList<Integer>();
		List<Integer> noDupe = new ArrayList<Integer>();
		int count=0, smallest = 1000000000, mult=1;
			
		if (numbers.size()==1) return numbers.get(0);
		
		//if anything in S is 0, remove
		for (int i:numbers)
			if (i!=0){
				count++;
				if(i%2==0) even.add(i);
				else{
					if(i<smallest) smallest=i;
					odd.add(i);
				}
		}

		//if all numbers even, divide everything by 2 and set multiplier
		if (count==even.size()){
			for (int i: even) posInts.add(i/2);
			mult = 2;
		//if all numbers odd, (n-k)/2 where k=smallest number and n = all other numbers
		}else if (count==odd.size())
			for (int i : odd)
				if(i!=smallest) posInts.add((i-smallest)/2);
				else posInts.add(i);
		//if mix of odd/even, divide all even by 2
		else{
			for(int i:even) odd.add(i/2);
			posInts = odd;
		}

		for (int i:posInts)
			if(!noDupe.contains(i)) noDupe.add(i);
		
		return mult*findGCD(noDupe);
	}
	
	public static List<Integer> balanceEquation(Equation chemical){
		List<Integer> ansCoef = new ArrayList<Integer>();
		List<Integer> coef = new ArrayList<Integer>();
		
		Matrix lhs = new Matrix(chemical.lhsMatrix);
		Matrix rhs = new Matrix(chemical.rhsMatrix, chemical.rhsMatrix.length);
		Matrix ans = lhs.solve(rhs);
		
        for (int i=0;i<chemical.reactCompoundList.size()+chemical.prodCompoundList.size();i++)
        	coef.add((int)(100000*(float)ans.get(i, 0)));
        
        //Printing Answers
		int gcd = findGCD(coef);
        for (int i=0;i<chemical.reactCompoundList.size()+chemical.prodCompoundList.size();i++)
        	ansCoef.add(coef.get(i)/gcd);
		
		return ansCoef;
	}
	
	public static float convertUnit(){
		
		
		return 0f;
	}
	
}
