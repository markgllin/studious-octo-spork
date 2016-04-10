package stoichiometry;

import java.util.ArrayList;
import java.util.List;

public class Equation {
	List<Compound> reactCompoundList = new ArrayList<Compound>();
	List<Compound> prodCompoundList = new ArrayList<Compound>();
	List<Element> elements = new ArrayList<Element>();
	double[][] lhsMatrix;
	double[] rhsMatrix;
	
	public boolean createEquation(String reactants, String products){
		setReactantCompounds(reactants);
		setProductCompounds(products);
		setElementList();
		buildRHSMatrix();

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

	public boolean buildRHSMatrix(){
		rhsMatrix = new double[elements.size()+1];
		rhsMatrix[rhsMatrix.length-1]=1;
		return true;
	}
	
	public boolean buildLHSMatrix(){
		int i,k=0;
		double[] row;
		int rowSize = reactCompoundList.size() + prodCompoundList.size();
		lhsMatrix = new double[elements.size()+1][];
		
		for (Element elem:elements){
			row = new double[rowSize];
			i=-1;
			for (Compound c:reactCompoundList){
				i++;
				for (Pair p: c.parsedCompound)
					if (!p.getPairElement().elementExists()) return false;
					else if (p.getPairElement().equals(elem))
						row[i] +=(double)p.getPairMultiplier();
			}
			
			for (Compound c:prodCompoundList){
				i++;
				for (Pair p: c.parsedCompound)
					if (!p.getPairElement().elementExists()) return false;
					else if (p.getPairElement().equals(elem))
						row[i] +=(double)p.getPairMultiplier()*-1;
			}
			
			lhsMatrix[k++]=row;
		}
		
		lhsMatrix[k]=new double[rowSize];
		lhsMatrix[k][0]=1;
		
		return true;
	}
	
}
