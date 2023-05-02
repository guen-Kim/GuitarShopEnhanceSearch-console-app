package guitarShop2;


public class GuitarSpec {
	
	private Builder builder;
	private String model;
	private Type type;
	private int numStrings;
	private Wood backWood;
	private Wood topWood;

	public GuitarSpec(Builder builder, String model, int numStrings, Type type, Wood backWood, Wood topWood) {
		super();
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.numStrings = numStrings;
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

	public int getNumStrings() {
		return numStrings;
	}

	public void setNumStrings(int numStrings) {
		this.numStrings = numStrings;
	}


	public boolean matches(GuitarSpec otherSpec) {
		//매칭 알고리즘 작성
		// 입력받은 기타(this), 이벤토리에 저장된 기타 (otherSpec)
		if (this.getBuilder() == otherSpec.getBuilder() || this.getBuilder() == Builder.UNKNOW) {
			String inven_model = otherSpec.getModel().toLowerCase();
			String input_model = this.getModel().toString();
			if ((input_model != null) && (!input_model.equals("")) && (input_model.equals(inven_model)) || input_model.equals("*")) {
				if (this.getType() == otherSpec.getType() || this.getType() == Type.NUSEPCIFIED) {
					if (this.getBackWood() == otherSpec.getBackWood() || this.getBackWood() == Wood.UNKNOW) {
						if (this.getTopWood() == otherSpec.getTopWood()|| this.getTopWood() == Wood.UNKNOW) {
							// 줄 수 추가
							if(this.getNumStrings() == otherSpec.getNumStrings() || this.getNumStrings() == 0) {
								System.err.println("matches-Yes");
								return true; // 조건에 맞는 기타 확인 
							}
						}
					}
				}
			}
		}
		return false;
	}

}
