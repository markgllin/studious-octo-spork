package stoichiometry;

import java.util.List;

public class Compound {
	private String formula;
	private float mass;
	List<Pair> parsedCompound;
	
	public Compound(String compoundFormula){
		formula = compoundFormula;
		setParsedFormula(Parser.parseFormula(compoundFormula));
		mass = Calculator.calcCompoundMass(parsedCompound);
		setMass(mass);
	}
	
	public String getFormula(){
		return formula;
	}
	
	public void setMass(float compoundMass){
		mass = compoundMass;
	}
	
	public float getMass(){
		return mass;
	}

	public void setParsedFormula(List<Pair> parsed){
		parsedCompound = parsed;
	}
	
	public List<Pair> getParsedFormula(){
		return parsedCompound;
	}

}
