package stoichiometry;

public class Pair {
	private Element elem;
	private float multiplier = 1;
	
	public Pair(){}
	
	public Pair (Element newElem, float newMulti){
		elem = newElem;
		multiplier = newMulti;
	}
	
	public Element getPairElement(){
		return elem;
	}
	
	public float getPairMultiplier(){
		return multiplier;
	}
	
}
