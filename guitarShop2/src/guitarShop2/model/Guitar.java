package guitarShop2.model;

import java.io.Serializable;

//All each instrument class needs is to extend Instrument, and provide a constructor
// that takes the right kind of spec object
public class Guitar extends Instrument implements Serializable{
	private static final long serialVersionUID = -968059540265428959L;
	
	public Guitar(String serialNumber, double price, GuitarSpec spec) {// 생성시 기타스펙 입력받음
		super(serialNumber, price, spec);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+ 
		"Guitar info - " +
		"serialNumber: " + getSerialNumber() +
		", price: " + getPrice() +
		", builder: " +  getSpec().getBuilder() +
		", model: " + getSpec().getModel() +
		", type:  "+ getSpec().getType() +
		", stringNum: " + ((GuitarSpec) getSpec()).getNumStrings() +
		", backWood: " + getSpec().getBackWood() +
		", topWood: " + getSpec().getTopWood() +
		"]";
	}
	

}
