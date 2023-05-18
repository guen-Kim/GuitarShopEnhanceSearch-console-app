package guitarShop2.instrument_enum;

import java.io.Serializable;

public enum Type implements Serializable{
	ACOUSTIC, ELECTRIC, NUSEPCIFIED;

	public String toString() {
		switch (this) {
		case ACOUSTIC:
			return "acoustic";
		case ELECTRIC:
			return "electric";
		default:
			return "unspecified";
		}
	}
}