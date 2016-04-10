package stoichiometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equation {
	List<Compound> reactCompoundList = new ArrayList<Compound>();
	List<Compound> prodCompoundList = new ArrayList<Compound>();
	List<Element> elements = new ArrayList<Element>();
	double[][]lhsMatrix;
	
	public boolean createEquation(String reactants, String products){
		setReactantCompounds(reactants);
		setProductCompounds(products);
		setElementList();

		return buildLHSMatrix();
	}
	
	public List<Compound> getReactantCompounds(){
		return reactCompoundList;
	}
	
	public void setReactantCompounds(String eqReactants){
		for (String comp: Parser.splitEquation(eqReactants)){
			System.out.println(comp);
			reactCompoundList.add(new Compound(comp));
			reactCompoundList.get(0).parsedCompound.get(0).getPairElement().getName();
		}
	}
	
	public List<Compound> getProductCompounds(){
		return prodCompoundList;
	}
	
	public void setProductCompounds(String eqProducts){
		for (String comp: Parser.splitEquation(eqProducts))
			prodCompoundList.add(new Compound(comp));
	}
	
	public void setElementList(){
		for (Compound c:reactCompoundList)
			for(Pair p:c.parsedCompound)
				if (!elements.contains(p.getPairElement()))
					elements.add(p.getPairElement());		
	}

	public boolean buildLHSMatrix(){
		double[] row = new double[reactCompoundList.size() + prodCompoundList.size()];;
		lhsMatrix = new double[elements.size()+1][];
		int i,k=0;
		
		System.out.println("row size:"+row.length);
		
		for (Element elem:elements){
			i=-1;
			for (Compound c:reactCompoundList){
				i++;
				for (Pair p: c.parsedCompound)
					if (!p.getPairElement().elementExists()){
						System.out.println(p.getPairElement().getName());
						return false;
					}else if (p.getPairElement().equals(elem)){
						row[i] =(double)p.getPairMultiplier();
						System.out.println("react else if:"+i);
					}else{
						row[i] =0.0;
						System.out.println("react else:"+i);
					}
			}
			for (Compound c:prodCompoundList)
				for (Pair p: c.parsedCompound){
					i++;
					if (!p.getPairElement().elementExists())
						return false;
					else if (p.getPairElement().equals(elem)){
						row[i] =(double)p.getPairMultiplier()*-1;
						System.out.println("prod else if:"+i);
					}else{
						System.out.println("prod else:"+i);
						row[i] =0.0;	
						System.out.println("prod else:"+i);
					}
				}
			lhsMatrix[k++]=row;
		}
		
		lhsMatrix[k]=new double[reactCompoundList.size() + prodCompoundList.size()];
		lhsMatrix[k][0]=1;
		
		return true;
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
	
	
}
