package stoichiometry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Jama.Matrix;

public class Initialization {
	private static List<Element> allElements = new ArrayList<Element>();
	//private static String dataFile = "C:\\Users\\marklin\\Desktop\\pt-data2.txt";
	private static String dataFile = "C:\\Users\\Mark.Lin\\Desktop\\pt-data2.txt";

	public static void main(String args[]) throws IOException{

		initializeElems();
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter reactants:");
		String reactants = in.nextLine();
		System.out.println("Enter products");
		String products = in.nextLine();
		in.close();
		
		Equation chemical = new Equation();
		if(!chemical.createEquation(reactants, products))
			System.out.println("Equation was not valid");
		
		System.out.println("--------LHS Matrix--------");
		for (int i=0; i<chemical.lhsMatrix.length; i++){
			System.out.print("[");
			for (int j=0; j<chemical.lhsMatrix[i].length;j++)
				System.out.print(chemical.lhsMatrix[i][j]+", ");
			System.out.println("]");
		}
		
		System.out.println("--------RHS Matrix--------");
		System.out.print("[");
		for (int i=0; i<chemical.rhsMatrix.length; i++)
			System.out.print(chemical.rhsMatrix[i]+", ");
		System.out.println("]");
		
		Matrix lhs = new Matrix(chemical.lhsMatrix);
		Matrix rhs = new Matrix(chemical.rhsMatrix, chemical.rhsMatrix.length);
		
		Matrix ans = lhs.solve(rhs);
		
		List<Integer> coef = new ArrayList<Integer>();
		
		System.out.println("--------Solution Matrix--------");  
        //Printing Answers
        for (int i=0;i<chemical.reactCompoundList.size()+chemical.prodCompoundList.size();i++)
        	coef.add((int)(100000*(float)ans.get(i, 0)));
        
		int gcd = Calculator.findGCD(coef);
		System.out.println("GCD:"+gcd);
        for (int i=0;i<chemical.reactCompoundList.size()+chemical.prodCompoundList.size();i++)
        	System.out.println(coef.get(i)/gcd);
        
		//////////////////////////////////////////////////////////////////////////////////
		/*
        //Creating  Arrays Representing Equations
        double[][] lhsArray = {{1,0,0,-1,0,0,0,0}       //Cu
							  ,{1,0,0,-1,0,0,0,0}		//S
							  ,{1,0,0,0,0,-1,0,0}		//C
							  ,{1,0,0,0,0,-1,0,0}		//N
							  ,{0,1,0,0,-1,0,0,0}		//K
							  ,{0,1,0,0,0,0,-1,0}		//I
							  ,{0,3,0,-4,0,0,0,-1}		//O
							  ,{0,0,1,0,0,-1,0,-2}		//H
							  ,{0,0,1,0,-1,0,-1,0}		//Cl
							  ,{1,0,0,0,0,0,0,0}		//arbitrary
							  };
        double[] rhsArray = {0,0,0,0,0,0,0,0,0,1};

        List<Integer> coef = new ArrayList<Integer>();
        
        //Creating Matrix Objects with arrays
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, rhsArray.length);
 
        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
 
        //Printing Answers
        for (int i=0;i<8;i++)
        	coef.add((int)(100000*(float)ans.get(i, 0)));
        
		int gcd = Equations.findGCD(coef);
		System.out.println("GCD:"+gcd);
        for (int i=0;i<8;i++)
        	System.out.println(coef.get(i)/gcd);
        */
        //////////////////////////////////////////////////////////////////////////////////
        
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
