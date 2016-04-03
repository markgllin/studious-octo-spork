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
	private static String dataFile = "C:\\Users\\marklin\\Desktop\\pt-data2.txt";
	
	public static void main(String args[]) throws IOException{

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

        
		
		//2x + y + 3z = 1
		//2x + 6y + 8z = 3
		//6x + 8y + 18z = 5
		
		//double[][] lhsArray = {{2,1,3}, {2,6,8},{6,8,18}};
		//double[] rhsArray = {1,3,5};
        List<Integer> coef = new ArrayList<Integer>();
        
        //Creating Matrix Objects with arrays
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, rhsArray.length);
 
        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
 
        //Printing Answers
        for (int i=0;i<8;i++)
        	coef.add((int)(100000*(float)ans.get(i, 0)));
        	//System.out.println((float)ans.get(i, 0));
        
        
		//System.out.println("test");
		//System.out.println(new Rational(3.75));
		
		int gcd = findGCD(coef);
		System.out.println("GCD:"+gcd);
        for (int i=0;i<8;i++)
        	System.out.println(coef.get(i)/gcd);
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
				if(i%2==0)
					even.add(i);
				else{
					if(i<smallest)
						smallest=i;
					odd.add(i);
				}
		}

		//if all numbers even, divide everything by 2 and set multiplier
		if (count==even.size()){
			for (int i: even)
				posInts.add(i/2);
			mult *= 2;
		//if all numbers odd, (n-k)/2 where k=smallest number and n = all other numbers
		}else if (count==odd.size()){
			for (int i : odd)
				if(i!=smallest)
					posInts.add((i-smallest)/2);
				else
					posInts.add(i);
		//if mix of odd/even, divide all even by 2
		}else{
			for(int i:even)
				odd.add(i/2);
			posInts = odd;
		}

		for (int i:posInts)
			if(!noDupe.contains(i))
				noDupe.add(i);
		
		return mult*findGCD(noDupe);
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
