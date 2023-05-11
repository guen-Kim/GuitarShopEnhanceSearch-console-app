package guitarShop2;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import guitarShop2.model.*;
import guitarShop2.inventory.*;
import guitarShop2.instrument_enum.*;

public class FindGuiatTester {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Inventory inventory = new Inventory();
		initializeInventory(inventory);

		while (true) {
			System.out.println("-------------------악기 샵 메인 페이지-------------------------");
			System.out.println("\t검색할 악기의 이름을 입력해주세요.");
			System.out.println("-------------------어떤 악기를 검색하나요?-------------------------");
			String input_instrumentName = sc.next().toLowerCase();
			switch (input_instrumentName) {
			case "mandolin":
			case "만도린":
				System.out.println("---------------mandolin의 Spec을 입력해주세요.-------------------------");
				System.out.println("제조사, 모델, 타입, 스타일, 뒷면우드, 윗면우드 순으로 입력해주세요.");
				MandolinSpec mandolinSpec = inputMandolinSpec(sc);
				matchingInstrument(mandolinSpec, inventory);
				break;

			case "guitar":
			case "기타":
				System.out.println("---------------guitar의 Spec을 입력해주세요.-------------------------");
				System.out.println("제조사, 모델, 줄수, 타입, 백,탑  순으로 입력해주세요.");
				GuitarSpec whatLikes = inputGuitarSpec(sc);
				matchingInstrument(whatLikes, inventory);
				break;
			}
			System.out.println("---------------------------------------------------------");

		}
	}

	private static void initializeInventory(Inventory inventory) {
		// 초기 기타
		// test fender2 m-1 acoustic style1 alder alder

		inventory.addInstrument("1", 1000,
				new GuitarSpec(Builder.FENDER, "g-1", 6, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER));

		inventory.addInstrument("2", 2000,
				new GuitarSpec(Builder.FENDER, "g-2", 8, Type.ELECTRIC, Wood.ALDER, Wood.ALDER));
		inventory.addInstrument("3", 3000,
				new GuitarSpec(Builder.FENDER, "g-3", 8, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));
		inventory.addInstrument("4", 4000,
				new GuitarSpec(Builder.FENDER2, "g-1", 12, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));

		inventory.addInstrument("5", 5000,
				new GuitarSpec(Builder.FENDER2, "g-3", 12, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));

		inventory.addInstrument("6", 6000,
				new GuitarSpec(Builder.FENDER2, "g-1", 12, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER));
		// 초기 만도린
		inventory.addInstrument("7", 7000,
				new MandolinSpec(Builder.FENDER2, "m-1", Type.ACOUSTIC, Style.STYLE1, Wood.ALDER, Wood.ALDER));
		inventory.addInstrument("8", 8000,
				new MandolinSpec(Builder.FENDER2, "m-2", Type.ACOUSTIC, Style.STYLE2, Wood.ALDER, Wood.ALDER));
		inventory.addInstrument("9", 9000,
				new MandolinSpec(Builder.FENDER, "m-2", Type.ELECTRIC, Style.STYLE1, Wood.ALDER, Wood.ALDER2));
		inventory.addInstrument("10", 10000,
				new MandolinSpec(Builder.FENDER, "m-1", Type.ELECTRIC, Style.STYLE2, Wood.ALDER, Wood.ALDER));
		inventory.addInstrument("11", 11000,
				new MandolinSpec(Builder.FENDER, "m-2", Type.ACOUSTIC, Style.STYLE1, Wood.ALDER, Wood.ALDER));
	}

	public static <E extends Enum<E>> Enum matchingEnumFiled(Class<E> enumClass, String input_str) {
		for (Enum filed : EnumSet.allOf(enumClass)) {
			if (filed.toString().equals(input_str)) {
				return filed;
			} else {
				continue;
			}
		}
		return null;
	}

	// polymorphism: GuitarSpec, MandolinSpec extends InstrumentSpec
	private static void matchingInstrument(InstrumentSpec whatErinLikesSpec, Inventory inventory) {

		// 매칭된 기타 반환
		List matchingInstrument = null;

		// 기타스팩일 경우 검색 및 매칭
		if (whatErinLikesSpec instanceof GuitarSpec) {
			matchingInstrument = inventory.search((GuitarSpec) whatErinLikesSpec);
			// 검색으로 반환된 매칭된 기타에 대하여
			if (!matchingInstrument.isEmpty()) {
				// 매장내에 존재하는 기타라면
				System.out.println("Erin, you might like these guitars:");
				for (Iterator i = matchingInstrument.iterator(); i.hasNext();) {
					// 하나의 기타에 대하여
					Guitar item = (Guitar) i.next();
					// 매칭된 기타 출력
					System.out.println(item.toString());
				}
			} else {
				System.out.println("매칭된 기타가 없습니다.");
			}

			// 만도린스팩 객체일 경우 검색 및 매칭
		} else if (whatErinLikesSpec instanceof MandolinSpec) {
			MandolinSpec d = (MandolinSpec) whatErinLikesSpec;
			matchingInstrument = inventory.search((MandolinSpec) whatErinLikesSpec);
			if (!matchingInstrument.isEmpty()) {
				System.out.println("Erin, you might like these guitars:");
				for (Iterator i = matchingInstrument.iterator(); i.hasNext();) {
					Mandolin item = (Mandolin) i.next();
					System.out.println(item.toString());
				}
			} else {
				System.out.println("매칭된 만도린이 없습니다.");
			}

		}

	}

	// *순서* 제조사, 모델, 줄수, 타입, 백,탑
	private static GuitarSpec inputGuitarSpec(Scanner sc) {
		
		// 제조사
		String input_builder = sc.next();
		Builder input_EBuilder = (Builder) matchingEnumFiled(Builder.class, input_builder.toLowerCase());
		if (input_EBuilder == null) {
			input_EBuilder = Builder.UNKNOW;
		}
		System.out.println("input_builder:" +input_builder);


		// 모델
		String input_model = sc.next();
		System.out.println("input_model:" +input_model);


		// 줄수
		int input_numString = 0;
		try {
			input_numString = sc.nextInt();
		} catch (Exception e) {
			input_numString = 0;
			String nextTokens = sc.next(); // try 문에서 에러 발생 시, next() 메소드 발생 위치에서 다음 next() 메소드에서 곧바로 토큰 반환함
		}
		System.out.println("input_numString:" +input_numString);

		
		// 타입	
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
		}
		System.out.println("input_type:" +input_type);

		// 백우드
		String input_backWood = sc.next();
		Wood input_EBWood = (Wood) matchingEnumFiled(Wood.class, input_backWood.toLowerCase());
		if (input_EBWood == null) {
			input_EBWood = Wood.UNKNOW;
		}
		System.out.println("input_backWood:" +input_backWood);


		// 탑우드
		String input_topWood = sc.next();
		Wood input_ETWood = (Wood) matchingEnumFiled(Wood.class, input_topWood.toLowerCase());
		if (input_ETWood == null) {
			input_ETWood = Wood.UNKNOW;
		}
		System.out.println("input_topWood:" +input_topWood);


		return new GuitarSpec(input_EBuilder, input_model, input_numString, input_EType, input_EBWood, input_ETWood);

	}

	// *순서* 제조사,모델, 타입, 스타일, 백, 탑
	private static MandolinSpec inputMandolinSpec(Scanner sc) {
		// 제조사
		String input_builder = sc.next();
		Builder input_EBuilder = (Builder) matchingEnumFiled(Builder.class, input_builder.toLowerCase());
		if (input_EBuilder == null) {
			input_EBuilder = Builder.UNKNOW;
		}

		// 모델
		String input_model = sc.next();

		// 타입
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
		}

		// 스타일
		String input_style = sc.next();
		Style input_EStyle = (Style) matchingEnumFiled(Style.class, input_style.toLowerCase());
		if (input_EStyle == null) {
			input_EStyle = Style.NONSTYLE;
		}

		// 백우드
		String input_backWood = sc.next();
		Wood input_EBWood = (Wood) matchingEnumFiled(Wood.class, input_backWood.toLowerCase());
		if (input_EBWood == null) {
			input_EBWood = Wood.UNKNOW;
		}

		// 탑우드
		String input_topWood = sc.next();
		Wood input_ETWood = (Wood) matchingEnumFiled(Wood.class, input_topWood.toLowerCase());
		if (input_ETWood == null) {
			input_ETWood = Wood.UNKNOW;
		}

		return new MandolinSpec(input_EBuilder, input_model, input_EType, input_EStyle, input_EBWood, input_ETWood);

	}
}
