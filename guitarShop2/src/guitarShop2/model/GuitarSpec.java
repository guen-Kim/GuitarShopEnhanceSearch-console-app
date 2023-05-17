package guitarShop2.model;

import guitarShop2.instrument_enum.Builder;
import guitarShop2.instrument_enum.Type;
import guitarShop2.instrument_enum.Wood;

// Just as Guitar extended Instrument, GuitarSpec extends InstrumentSpec.
public class GuitarSpec extends InstrumentSpec  implements Comparable{
	// Only a guitar has a numStrings property
	// it's not in the Instrument superclass
	private int numStrings;

	public GuitarSpec(Builder builder, String model, int numStrings, Type type, Wood backWood, Wood topWood) {
//This constructor just adds the guitar-specific properties
// to what's already stroed in the base InstrumentSpec class
		super(builder, model, type, backWood, topWood);
		this.numStrings = numStrings;
	}

	public int getNumStrings() {
		return numStrings;
	}

	public void setNumStrings(int numStrings) {
		this.numStrings = numStrings;
	}

	/*
	 * matches() uses the superclass's matches(), and then performs additional
	 * checks to make sure the spec is the right type and matches the
	 * guitar-specific properties
	 */
	public boolean matches(GuitarSpec otherSpec) {
		if (!super.matches(otherSpec)) // 디폴트 매치
			return false;
		
		// TODO: 추가된 기타 매치			
		if ((this.getNumStrings() != 0 && compareTo(otherSpec) != 0))
			return false;
		System.err.println("matches-Yes");
		return true; // 조건에 맞는 기타 확인

	}

	@Override
	public int compareTo(Object ob) {
		GuitarSpec other = (GuitarSpec) ob;
		if(numStrings < other.numStrings) return -1;
		else if(numStrings > other.numStrings) return 1;
		return 0;
	}

}
