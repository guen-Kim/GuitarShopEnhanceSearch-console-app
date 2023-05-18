package guitarShop2.instrument_enum;

import java.io.Serializable;

public enum Wood implements Serializable{
	ALDER, ALDER2, UNKNOW;

	public String toString() {
		switch (this) {
		case ALDER:
			return "alder";
		case ALDER2:
			return "alder2";
		default:
			return "unknow";
		}
	}
}
