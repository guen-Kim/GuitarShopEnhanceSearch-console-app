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

	/* 만도린 매치 */
	public boolean matches(MandolinSpec otherSpec) {
		// 매칭 알고리즘 작성: 비교 순서 중요 효율성
		// 입력받은 만돌린(this), 이벤토리에 저장된 Mandolin (otherSpec)
		
		if (!super.matches(otherSpec)) // 디폴트 매치
			return false;
		// TODO: 추가된 만도린 매치

		MandolinSpec spec = (MandolinSpec) otherSpec;
		if (style != Style.NONSTYLE && !style.equals(spec.style))
			return false;
		return true;
	}
}
