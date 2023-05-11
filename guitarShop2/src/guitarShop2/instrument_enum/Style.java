package guitarShop2.instrument_enum;

public enum Style {
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