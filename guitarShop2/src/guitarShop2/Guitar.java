package guitarShop2;

enum Type {
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

enum Builder { // Á¦Á¶»ç
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

enum Wood { 
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

public class Guitar {
	private String serialNumber;
	private double price;
	GuitarSpec spec;

	public Guitar(String serialNumber, double price, GuitarSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "[serialNumber: " + serialNumber + ", " +
					"price: " + price + ", " +
					"Builder: " + this.getBuilder() + ", " +
					"Type:" + this.getType()+", " + 
					"NumStrings: " + this.getNumStrings()+"," +
					"Model: " + this.getModel() + ", " +
					"BackWood: " + this.getBackWood() + ", " +
					"TopWood: " + this.getTopWood() + ", " +
					
					"]";
	}

	public GuitarSpec getSpec() {
		return spec;
	}
	
	public Builder getBuilder() {
		return spec.getBuilder();
	}


	public String getModel() {
		return spec.getModel();
	}


	public Type getType() {
		return spec.getType();
	}


	public Wood getBackWood() {
		return spec.getBackWood();
	}


	public Wood getTopWood() {
		return spec.getTopWood();
	}

	public int getNumStrings() {
		return spec.getNumStrings();
	}
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSpec(GuitarSpec spec) {
		this.spec = spec;
	}

}
