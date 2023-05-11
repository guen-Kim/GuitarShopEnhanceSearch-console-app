package guitarShop2.model;

import guitarShop2.instrument_enum.*;

//just like instrument, intrumentSpect is abstract and you'll
// use subclass for each instrument type
public abstract class InstrumentSpec {

	private Builder builder;
	private String model;
	private Type type;
	private Wood backWood;
	private Wood topWood;

//This is similar to our old Guitar constructor
// except that we've pulled out properties not common to all instruments, like numStrings and style.
	public InstrumentSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Wood getBackWood() {
		return backWood;
	}

	public void setBackWood(Wood backWood) {
		this.backWood = backWood;
	}

	public Wood getTopWood() {
		return topWood;
	}

	public void setTopWood(Wood topWood) {
		this.topWood = topWood;
	}

	/* ����Ʈ ��ġ */
// This version of matches() does just what you'd expect: compares all properties
// in this class to another spec instance. We'll have to override this in subclasses, though...
	public boolean matches(InstrumentSpec otherSpec) {
		// ��Ī �˰��� �ۼ�; �Է¹��� ��Ÿ(this), �̺��丮�� ����� ��Ÿ (otherSpec)
		// if ���̴� �ִ��� �۰� ������ ��� �������� �ۼ�
		/* ���� ����: �Է¹��� ��Ÿ  ���ϵ� ī��[ '*', Enum default value ] ���� �Ǵ�  -> �κ��丮 ��Ÿ�� �� */

		if (this.getBuilder() != Builder.UNKNOW && this.getBuilder() != otherSpec.getBuilder())
			return false;

		String inven_model = otherSpec.getModel().toLowerCase();
		String input_model = this.getModel().toString();

		if (input_model == null || input_model.equals("") || (!input_model.equals("*") && !input_model.equals(inven_model)) )
			return false;

		if ( ( this.getType()!=Type.NUSEPCIFIED && this.getType() != otherSpec.getType()) )
			return false;
		System.out.println(otherSpec.getType());

		
		
		if (( this.getBackWood ()!= Wood.UNKNOW && this.getBackWood() != otherSpec.getBackWood()))
			return false;

		if (( this.getTopWood ()!= Wood.UNKNOW && this.getTopWood() != otherSpec.getTopWood()))
			return false;

		return true;

	}

}
