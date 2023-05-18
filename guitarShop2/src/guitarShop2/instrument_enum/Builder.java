package guitarShop2.instrument_enum;

import java.io.Serializable;

public enum Builder implements Serializable { // ������
	FENDER, FENDER2, UNKNOW;

	public String toString() {
		switch (this) {
		case FENDER:
			return "fender";
		case FENDER2:
			return "fender2";
		default:
			return "unknow";
		}
	}
}
