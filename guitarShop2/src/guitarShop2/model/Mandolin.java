package guitarShop2.model;

public class Mandolin extends Instrument{

	public Mandolin(String serialNumber, double price, MandolinSpec spec) {
		super(serialNumber, price, spec);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+ 
		"Mandolin info - " +
		"serialNumber: " + getSerialNumber() +
		", price: " + getPrice() +
		", builder: " +  getSpec().getBuilder() +
		", model: " + getSpec().getModel() +
		", type:  "+ getSpec().getType() +
		", style: " + ((MandolinSpec) getSpec()).getStyle() +
		", backWood: " + getSpec().getBackWood() +
		", topWood: " + getSpec().getTopWood() +
		"]";
	}
}


// 기타 스펙 알고리즘 고치기 끝