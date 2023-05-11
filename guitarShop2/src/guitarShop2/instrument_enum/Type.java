package guitarShop2.instrument_enum;

public enum Type {
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