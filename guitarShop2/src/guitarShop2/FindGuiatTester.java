package guitarShop2;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import guitarShop2.model.*;
import guitarShop2.inventory.*;
import guitarShop2.instrument_enum.*;

public class FindGuiatTester {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Inventory inventory = new Inventory();
		// inventory.origin();

		inventory.loadInstrumentData();

		while (true) {
			System.out.println("-------------------악기 샵 메인 페이지-------------------------");
			System.out.println("\t악기의 이름을 입력해주세요. 종료를 원한다면 '종료'를 입력해주세요.");

			String input_instrumentName = sc.next().toLowerCase();
			switch (input_instrumentName) {

			case "mandolin":
			case "만도린":
				System.out.println("만도린 메뉴입니다. '검색 'or '추가'");

				String input_menu_m = sc.next().toLowerCase();
				switch (input_menu_m) {
				case "추가":
					System.out.println("--------추가할 mandolin의 Spec을 입력해주세요.-------------------------");
					System.out.println("serialNumber, 가격, 제조사, 모델, 타입, 스타일, 뒷면우드, 윗면우드 순으로 입력해주세요.");
					String serialNumber =sc.next();
					double price = sc.nextDouble();
					MandolinSpec mandolinSpec_ = inputMandolinSpec(sc);
					inventory.addInstrument(serialNumber, price, mandolinSpec_);
					break;
				case "검색":
					System.out.println("--------검색할 mandolin의 Spec을 입력해주세요.-------------------------");
					System.out.println("제조사, 모델, 타입, 스타일, 뒷면우드, 윗면우드 순으로 입력해주세요.");
					MandolinSpec mandolinSpec = inputMandolinSpec(sc);
					matchingInstrument(mandolinSpec, inventory);
					break;
				}
				break;

			case "guitar":
			case "기타":
				System.out.println("기타 메뉴입니다. '검색 'or '추가'");

				String input_menu_g = sc.next().toLowerCase();
				switch (input_menu_g) {
				case "추가":
					System.out.println("---------추가할 guitar의 Spec을 입력해주세요.-------------------------");
					System.out.println("id, 가격, 제조사, 모델, 줄수, 타입, 백,탑  순으로 입력해주세요.");
					String serialNumber =sc.next();
					double price = sc.nextDouble();
					GuitarSpec guitarSpec_ = inputGuitarSpec(sc);
					inventory.addInstrument(serialNumber, price, guitarSpec_);
					break;
				case "검색":
					System.out.println("-----------검색할 guitar의 Spec을 입력해주세요.-------------------------");
					System.out.println("제조사, 모델, 줄수, 타입, 백,탑  순으로 입력해주세요.");
					GuitarSpec guitarSpec = inputGuitarSpec(sc);
					matchingInstrument(guitarSpec, inventory);
					break;

				}
				break;

			case "종료":
				inventory.updateInventoryData();
				return;
			}
			System.out.println("---------------------------------------------------------");

		}

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

		// 모델
		String input_model = sc.next();

		// 줄수
		int input_numString = 0;
		try {
			input_numString = sc.nextInt();
		} catch (Exception e) {
			input_numString = 0;
			String nextTokens = sc.next(); // try 문에서 에러 발생 시, next() 메소드 발생 위치에서 다음 next() 메소드에서 곧바로 토큰 반환함
		}

		// 타입
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
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
