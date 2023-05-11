package guitarShop2.model;

//Instrument is abstract. 
//you have to instantiate subclass of 
// this base class, like Guitar
public abstract class Instrument {

	private String serialNumber;
	private double price;
// We used the aggregation form of association because each Instrument is made up
//price member virialbes, and an instrumentSpec instance.
	private InstrumentSpec spec;

	// Most of this is pretty simple, and looks a lot like the old Guitar class we had.
	public Instrument(String serialNumber, double price, InstrumentSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec; // subclass Plymorishm
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

	public InstrumentSpec getSpec() {
		return spec;
	}

	public void setSpec(InstrumentSpec spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "hear";
	}

}
