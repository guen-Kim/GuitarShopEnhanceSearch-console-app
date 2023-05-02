package guitarShop2;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FindGuiatTester {

	public static void main(String[] args) {

		/**
		 * ����ڷκ��� ������ �Է� �޾� �ùٸ��� ó���ϱ�
		 **/
		while (true) {

			System.out.println("-------------------��Ÿ �� ���� ������-------------------------");
			System.out.println("\t�˻��� ��Ÿ�� ������ �Է����ּ���.");
			System.out.println("builder, model, numString, type, backWood,topWood ������ �Է����ּ���.");

			// ����
			matchingGuitar(start());

			System.out.println("---------------------------------------------------------");

		}
	}
	private static void initializeInventory(Inventory inventory) {
		// �ʱ� ��Ÿ
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

		// ��Ī�� ��Ÿ ��ȯ
		List matchingGuitars = inventory.search(whatErinLikes);

		// �˻����� ��ȯ�� ��Ī�� ��Ÿ�� ���Ͽ�
		if (!matchingGuitars.isEmpty()) {
			// ���峻�� �����ϴ� ��Ÿ���
			System.out.println("Erin, you might like these guitars:");
			for (Iterator i = matchingGuitars.iterator(); i.hasNext();) {
				// �ϳ��� ��Ÿ�� ���Ͽ�
				Guitar guitar = (Guitar) i.next();
				// ��Ī�� ��Ÿ ���
				System.out.println(guitar.toString());
			}
		} else {
			System.out.println("��Ī�� ��Ÿ�� �����ϴ�.");
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
