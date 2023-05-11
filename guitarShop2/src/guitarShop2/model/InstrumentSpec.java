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

	/* 디폴트 매치 */
// This version of matches() does just what you'd expect: compares all properties
// in this class to another spec instance. We'll have to override this in subclasses, though...
	public boolean matches(InstrumentSpec otherSpec) {
		// 매칭 알고리즘 작성; 입력받은 기타(this), 이벤토리에 저장된 기타 (otherSpec)
		// if 깊이는 최대한 작게 거짓일 경우 기준으로 작성
		/* 논리식 순서: 입력받은 기타  와일드 카드[ '*', Enum default value ] 인지 판단  -> 인벤토리 기타와 비교 */

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
