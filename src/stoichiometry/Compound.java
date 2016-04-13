package stoichiometry;

import java.util.List;

public class Compound {
	private String compoundName;
	private float mass;
	List<Pair> parsedCompound;
	
	public Compound(String compoundFormula){
		compoundName = compoundFormula;
		setCompoundElements(Parser.parseFormula(compoundFormula));
		mass = Calculator.calcCompoundMass(parsedCompound);
		setMass(mass);
	}
	
	public String getCompoundName(){
		return compoundName;
	}
	
	public void setMass(float compoundMass){
		mass = compoundMass;
	}
	
	public float getMass(){
		return mass;
	}

	public void setCompoundElements(List<Pair> parsed){
		parsedCompound = parsed;
	}
	
	public List<Pair> getComopundElements(){
		return parsedCompound;
	}

}
