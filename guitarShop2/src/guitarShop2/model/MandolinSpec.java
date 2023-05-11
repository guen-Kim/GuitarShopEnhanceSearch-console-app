package guitarShop2.model;

import guitarShop2.instrument_enum.*;



public class MandolinSpec extends InstrumentSpec {
	
//You'll need a new enumerated type, Style.
//Use two enumerated values, ~ and ~
	private Style style;

	public MandolinSpec(Builder builder, String model, Type type, Style style, Wood backWood, Wood topWood) {
		super(builder, model, type, backWood, topWood);
		this.style = style;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	/* ������ ��ġ */
	public boolean matches(MandolinSpec otherSpec) {
		// ��Ī �˰��� �ۼ�: �� ���� �߿� ȿ����
		// �Է¹��� ������(this), �̺��丮�� ����� Mandolin (otherSpec)
		
		if (!super.matches(otherSpec)) // ����Ʈ ��ġ
			return false;
		// TODO: �߰��� ������ ��ġ
//		if (!(otherSpec instanceof MandolinSpec)) //inventory�� �ִ� �ǱⰡ Madolin�̶��
//			return false;
		MandolinSpec spec = (MandolinSpec) otherSpec;
		if (style != Style.NONSTYLE && !style.equals(spec.style))
			return false;
		return true;
	}
}
