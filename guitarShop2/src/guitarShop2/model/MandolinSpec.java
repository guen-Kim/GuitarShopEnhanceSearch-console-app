package guitarShop2.model;

import guitarShop2.instrument_enum.Builder;
import guitarShop2.instrument_enum.Style;
import guitarShop2.instrument_enum.Type;
import guitarShop2.instrument_enum.Wood;

import java.io.Serializable;


public class MandolinSpec extends InstrumentSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	
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

		MandolinSpec spec = (MandolinSpec) otherSpec;
		if (style != Style.NONSTYLE && !style.equals(spec.style))
			return false;
		return true;
	}
}
