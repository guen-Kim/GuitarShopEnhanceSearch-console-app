package guitarShop2.instrument_enum;

import java.io.Serializable;

public enum Style implements Serializable{
	STYLE1, STYLE2, NONSTYLE;

	public String toString() {
		switch (this) {
		case STYLE1:
			return "style1";
		case STYLE2:
			return "style2";
		default:
			return "nonStyle";
		}
	}
}