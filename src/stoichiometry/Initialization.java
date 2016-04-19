package stoichiometry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Jama.Matrix;

public class Initialization {
	private static List<Element> allElements = new ArrayList<Element>();
	//private static String dataFile = "C:\\Users\\marklin\\Desktop\\pt-data2.txt";
	private static String dataFile = "C:\\Users\\Mark.Lin\\Desktop\\pt-data2.txt";

	public static void main(String args[]) throws IOException{
		initializeElems();
		
		System.out.println(Calculator.pvNRT(79.97f, 4.167f, 0f, 303.0f, "n"));
		////////////////////////////////////////////////////////////////
		////EXAMPLE UNIT CONVERTER
		
		/*
		System.out.println(UnitCalculator.volumeConverter(112, "M3", "OZ"));
		System.out.println(UnitCalculator.molConverter(3, "AVOGAD", "MOL"));
		*/
		
		/////////////////////////////////////////////////////////////////////////////////////
		/////EXAMPLE BALANCER///
		
		/*
		String reactants = "CuSCN+KIO3+HCl";
		String products = "CuSO4+KCl+HCN+ICl+H2O";

		Equation chemical = new Equation();
		if(!chemical.createEquation(reactants, products))
			System.out.println("Equation was not valid");
		
		try{
			chemical.coefficients=Calculator.balanceEquation(chemical);
		}catch(Exception e){
			System.out.println("There was an error in the equation. Check to make sure it was inputted correctly.");
		}
		
		float molarMass;
		float grams;
		int coef;
		
		float termNum = 1f;
		float weight = 28f;
		
		if (termNum-chemical.reactCompoundList.size()-1>0)
			molarMass = chemical.prodCompoundList.get(Math.round(termNum-chemical.reactCompoundList.size()-1)).getMass();
		else{
			System.out.println("TERM:"+chemical.reactCompoundList.get(Math.round(termNum)).getCompoundName());
			molarMass = chemical.reactCompoundList.get(Math.round(termNum)).getMass();
		}
		
		coef = chemical.coefficients.get(Math.round(termNum));
		
		for (int i=0; i<chemical.reactCompoundList.size();i++){
			grams = (weight*chemical.coefficients.get(i)*chemical.reactCompoundList.get(i).getMass())/(coef*molarMass);
			System.out.println(chemical.reactCompoundList.get(i).getCompoundName()+":"+grams+"g");
		}
		
		int reactSize = chemical.reactCompoundList.size();
		
		for (int i=0; i<chemical.prodCompoundList.size();i++){
			grams = (weight*chemical.coefficients.get(i+reactSize)*chemical.prodCompoundList.get(i).getMass())/(coef*molarMass);
			System.out.println(chemical.prodCompoundList.get(i).getCompoundName()+":"+grams+"g");
		}
		*/
        //////////////////////////////////////////////////////////////////////////////////
        //////////EXAMPLE CALCULATING MOLAR MASS//////////
	/*
		float mass, percentMass;
		Compound compound;
		
		initializeElems();
		
	    Scanner in = new Scanner(System.in);
	    System.out.println("Enter Compound");
	    String userCompound = in.nextLine();
	    in.close();
	    
		compound = new Compound(userCompound);
		
		System.out.println("formula:" + compound.getFormula());
		System.out.println("Total mass: "+ compound.getMass());
		
		for(Pair element:compound.getParsedFormula()){
			mass = Calculator.calcElementMass(element);
			percentMass = Calculator.calcPercentMass(compound.getMass(), mass);
			
			System.out.println(element.getPairElement().getSymbol()+": "+percentMass+"%" + " or "+mass+"g");
		}
		*/
/*
		for (String s:Parser.splitEquation("AlSO4 + Gl6")){
			System.out.println(s.trim());
		}
		*/
	}
	

	/* reads the data file and instantiates all 118 elements.
	 * uses setElementInfo.
	 */
	public static void initializeElems() throws IOException{
		int k=1;
		
		try(BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
			//assign information for each element
			for(String line; (line = br.readLine()) != null;)
				allElements.add(new Element(k++, line.split("%")));
		} catch (FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	
	
	/* takes in an element as a string, iterates through all
	 * stored elements, and returns if found. Returns null if
	 * not. Searches using symbol
	 */
	public static Element findElementInfo(String element){
		for (Element elem:allElements)
			if (elem.getSymbol().equals(element))
				return elem;

		return new Element(element);
	}
	
 	//////////////////////////neccessary?////////////////////////////////////////
 	
	/* takes in an array of elements ending with a multiplier, 
	 * and combines all elements into 1 string with multiplier
	 * in second index (e.g. {"", "Al", "SO4", "2"} -> {"AlSO4", "2"}
	 */
	public static String[] combineCompounds(String[] compound){
		String[] ion = new String[2];
		
		for (int i=0; i<compound.length; i++){
			
			if (compound[i].equals(""))
				ion[0]=compound[i];
			else if(Character.isDigit(compound[i].charAt(0)))
				ion[1] = compound[i];
			else
				ion[0]+= compound[i];
		}
		
		return ion;
	}
}
