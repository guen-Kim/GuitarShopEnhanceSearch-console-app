package guitarShop2;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FindGuiatTester {

	public static void main(String[] args) {

		/**
		 * 사용자로부터 데이터 입력 받아 올바르게 처리하기
		 **/
		while (true) {

			System.out.println("-------------------기타 샵 메인 페이지-------------------------");
			System.out.println("\t검색할 기타의 스펙을 입력해주세요.");
			System.out.println("builder, model, numString, type, backWood,topWood 순으로 입력해주세요.");

			// 시작
			matchingGuitar(start());

			System.out.println("---------------------------------------------------------");

		}
	}
	private static void initializeInventory(Inventory inventory) {
		// 초기 기타
		inventory.addGuitar("1", 1000,
				new GuitarSpec(Builder.FENDER, "g-1", 6, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER));
		inventory.addGuitar("2", 2000,
				new GuitarSpec(Builder.FENDER, "g-2", 8, Type.ELECTRIC, Wood.ALDER, Wood.ALDER));
		inventory.addGuitar("3", 3000,
				new GuitarSpec(Builder.FENDER, "g-3", 8, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));
		inventory.addGuitar("4", 4000,
				new GuitarSpec(Builder.FENDER2, "g-1", 12, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));
		
		inventory.addGuitar("5", 5000,
				new GuitarSpec(Builder.FENDER2, "g-4", 12, Type.ELECTRIC, Wood.ALDER, Wood.ALDER2));
		
		inventory.addGuitar("6", 6000,
				new GuitarSpec(Builder.FENDER2, "g-1", 12, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER));
		
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

	private static void matchingGuitar(GuitarSpec whatErinLikes) {
		Inventory inventory = new Inventory();
		initializeInventory(inventory);

		// 매칭된 기타 반환
		List matchingGuitars = inventory.search(whatErinLikes);

		// 검색으로 반환된 매칭된 기타에 대하여
		if (!matchingGuitars.isEmpty()) {
			// 매장내에 존재하는 기타라면
			System.out.println("Erin, you might like these guitars:");
			for (Iterator i = matchingGuitars.iterator(); i.hasNext();) {
				// 하나의 기타에 대하여
				Guitar guitar = (Guitar) i.next();
				// 매칭된 기타 출력
				System.out.println(guitar.toString());
			}
		} else {
			System.out.println("매칭된 기타가 없습니다.");
		}

	}

	private static GuitarSpec start() {
		Scanner sc = new Scanner(System.in);
		String input_builder = sc.next();
		Builder input_EBuilder = (Builder) matchingEnumFiled(Builder.class, input_builder.toLowerCase());
		if (input_EBuilder == null) {
			input_EBuilder = Builder.UNKNOW;
		}

		String input_model = sc.next();
		int input_numString = 0;
		try {
			
			input_numString = sc.nextInt();

		}catch(Exception e){
			input_numString = 0;
		}

		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
		}

		String input_backWood = sc.next();
		Wood input_EBWood = (Wood) matchingEnumFiled(Wood.class, input_backWood.toLowerCase());
		if (input_EBWood == null) {
			input_EBWood = Wood.UNKNOW;
		}

		String input_topWood = sc.next();
		Wood input_ETWood = (Wood) matchingEnumFiled(Wood.class, input_topWood.toLowerCase());
		if (input_ETWood == null) {
			input_ETWood = Wood.UNKNOW;
		}

		GuitarSpec whatErinLikes = new GuitarSpec(input_EBuilder, input_model, input_numString, input_EType,
				input_EBWood, input_ETWood);

		return whatErinLikes;
	}
}
