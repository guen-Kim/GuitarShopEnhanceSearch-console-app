package guitarShop2.instrument_enum;

public enum Wood {
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
