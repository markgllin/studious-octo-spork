package stoichiometry;

public class UnitCalculator {

	public static float volumeConverter(float value, String inUnit, String outUnit){
		if (inUnit==outUnit) return value;
		
		switch (inUnit){
			case "LITER": 	break;
			case "MLITER": 	value *=0.001;		break;
			case "M3": 		value *=1000;		break;
			case "CM3": 	value *=0.001;		break;
			case "OZ": 		value *=0.029574;	break;
			case "GILL": 	value *=0.118294;	break;
			case "PINT":	value *=0.473176;	break;
			case "QUART":	value *=0.946353;	break;
			case "GAL":		value *=3.78541;	break;
			case "CUP":		value *=0.236588;	break;
			default:		return -1;			//invalid unit
		}

		switch (outUnit){
			case "LITER": 	return value;
			case "MLITER": 	return value*1000;
			case "M3": 		return value*0.001f;
			case "CM3": 	return value*1000;
			case "OZ": 		return value*33.8135f;
			case "GILL": 	return value*8.45351f;
			case "PINT":	return value*2.11338f;
			case "QUART":	return value*1.05669f;
			case "GAL":		return value*0.264172f;
			case "CUP":		return value*4.22676f;
			default:		return -1; 					//invalid unit
		}
	}
	
	public static float massConverter(float value, String inUnit, String outUnit){
		if (inUnit==outUnit) return value;
		
		switch(inUnit){
			case "GRAM":	break;
			case "MGRAM":	value *=0.001;		break;
			case "KGRAM":	value *=1000;		break;
			case "LBS":		value *=453.592;	break;
			default:		return -1; 					//invalid unit
		}
		
		switch(outUnit){
			case "GRAM":	return value;
			case "MGRAM":	return value*1000;
			case "KGRAM":	return value*0.001f;
			case "LBS":		return value*0.00220462f;
			default:		return -1; 					//invalid unit
		}
	}
	
	public static float tempConverter(float value, String inUnit, String outUnit){
		if (inUnit==outUnit) return value;
		
		switch(inUnit){
			case "CELSIUS":		break;
			case "KELVINS":		value += 273.15f;				break;
			case "FAHRENHEIT":	value = (value*1.8f) + 32;		break;
			default:			return -1; 						//invalid unit
		}
		
		switch(outUnit){
			case "CELSIUS":		return value;
			case "KELVINS":		return value-273.15f;
			case "FAHRENHEIT":	return (value-32)/1.8f;
			default:			return -1; 						//invalid unit
		}
	}
	
	public static float pressureConverter(float value, String inUnit, String outUnit){
		if (inUnit==outUnit) return value;
		
		switch (inUnit){
			case "KPA": 	break;
			case "PA": 		value *=0.001;		break;
			case "PSI": 	value *=6.89476;	break;
			case "ATM": 	value *=101.325;	break;
			case "BAR": 	value *=100;		break;
			case "MMHG":	value *=0.133322;	break;
			case "TORR": 	value *=0.133322;	break;
			default:		return -1;			//invalid unit
		}
		
		switch (outUnit){
			case "KPA": 	return value;
			case "PA": 		return value*1000f;
			case "PSI": 	return value*0.145038f;
			case "ATM": 	return value*0.00986923f;
			case "BAR": 	return value*0.01f;
			case "MMHG":	return value*7.50062f;
			case "TORR": 	return value*7.50062f;
			default:		return -1;				//invalid unit
		}
	}
	
	public static float molConverter(float value, String inUnit, String outUnit){
		if (inUnit==outUnit) return value;
		
		switch (inUnit){
			case "MOL": 	break;
			case "AVOGAD": 	value /=6.022140857*Math.pow(10, 23);		break;
			default:		return -1;							//invalid unit
		}
		
		switch (outUnit){
			case "MOL": 	return value;
			case "AVOGAD": 	return value*(float)(6.022140857*Math.pow(10, 23));
			default:		return -1;							//invalid unit
		}
	}
	
	public static float massConverter(float value, String inUnit, String outUnit, float molarMass){
		if (inUnit==outUnit) return value;
		
		switch(inUnit){
			case "GRAM":	break;
			case "MGRAM":	value *=0.001;		break;
			case "KGRAM":	value *=1000;		break;
			case "LBS":		value *=453.592;	break;
			case "MOL":		value *= molarMass;	break;
			case "AVOGAD":	value = (value/((float)(6.022140857*Math.pow(10, 23))))*molarMass;
			default:		return -1; 					//invalid unit
		}
		
		switch(outUnit){
			case "GRAM":	return value;
			case "MGRAM":	return value*1000;
			case "KGRAM":	return value*0.001f;
			case "LBS":		return value*0.00220462f;
			case "MOL":		return value/molarMass;
			case "AVOGAD":	return (value/molarMass)*(float)(6.022140857*Math.pow(10, 23));
			default:		return -1; 					//invalid unit
		}
	}
}
