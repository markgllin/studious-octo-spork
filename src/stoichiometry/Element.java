package stoichiometry;

public class Element{
	private int atomicNum;
	private String symbol;
	private String name;
	private String sMass;
	private float fMass;
	private String eConfig;
	private String eNeg;
	private String atomicRad;
	private String ionRad;
	private String vdwRad;
	private String ie;
	private String ea;
	private String oxiStates;
	private String sType;
	private String bType;
	private String mPoint;
	private String bPoint;
	private float density;
	private String metalType;
	private boolean exists = true;
	
	public Element(String notFound){
		symbol = notFound;
		exists = false;
	}
	
	public Element(int atomicNum, String[] temp){
        setAtomicNum(Integer.parseInt(temp[0]));
        setSymbol(temp[1]);
        setName(temp[2]);
        setStringMass(temp[3]);
        setEConfig(temp[4]);
        setENeg(temp[5]);
        setAtomicRadius(temp[6]);
        setIonRadius(temp[7]);
        setVDWRad(temp[8]);
        setIE(temp[9]);
        setEA(temp[10]);
        setStateType(temp[11]);
        setBondingType(temp[12]);
        setMeltPoint(temp[13]);
        setBoilPoint(temp[14]);
        setDensity(Float.parseFloat(temp[15]));
        setMetalType(temp[16]);
	}
	
	public boolean elementExists(){
		return exists;
	}
	
	public int getAtomicNum(){
		return atomicNum;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public String getName(){
		return name;
	}
	
	public float getMass(){
		return fMass;
	}
	
	public String getStringMass(){
		return sMass;
	}
	
	public String getEConfig(){
		return eConfig;
	}
	
	public String getENeg(){
		return eNeg;
	}
	
	public String getAtomicRadius(){
		return atomicRad;
	}
	
	public String getIonRadius(){
		return ionRad;
	}
	
	public String getVDWRad(){
		return vdwRad;
	}
	
	public String getIE(){
		return ie;
	}
	
	public String getEA(){
		return ea;
	}
	
	public String getOxiStates(){
		return oxiStates;
	}
	
	public String getStateType(){
		return sType;
	}
	
	public String getBondingType(){
		return bType;
	}
	
	public String getMeltPoint(){
		return mPoint;
	}

	public String getBoilPoint(){
		return bPoint;
	}

	public double getDensity(){
		return density;
	}

	public String getMetalType(){
		return metalType;
	}
	
	public void setAtomicNum(int val){
		atomicNum=val;
	}
	
	public void setSymbol(String val){
		symbol=val;
	}
	
	public void setName(String val){
		name=val;
	}
	
	public void setStringMass(String val){
		sMass=val;
		setMass(Parser.parseMass(sMass));
	}
	
	public void setMass(float mass){
		fMass = mass;
	}
	
	public void setEConfig(String val){
		eConfig=val;
	}
	
	public void setENeg(String val){
		eNeg=val;
	}
	
	public void setAtomicRadius(String val){
		atomicRad=val;
	}
	
	public void setIonRadius(String val){
		ionRad=val;
	}
	
	public void setVDWRad(String val){
		vdwRad=val;
	}
	
	public void setIE(String val){
		ie=val;
	}
	
	public void setEA(String val){
		ea=val;
	}
	
	public void setOxiStates(String val){
		oxiStates=val;
	}
	
	public void setStateType(String val){
		sType=val;
	}
	
	public void setBondingType(String val){
		bType=val;
	}
	
	public void setMeltPoint(String val){
		mPoint=val;
	}

	public void setBoilPoint(String val){
		bPoint=val;
	}

	public void setDensity(float val){
		density=val;
	}

	public void setMetalType(String val){
		metalType=val;
	}
}

