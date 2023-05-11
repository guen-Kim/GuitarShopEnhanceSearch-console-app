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
			System.out.println("-------------------�Ǳ� �� ���� ������-------------------------");
			System.out.println("\t�˻��� �Ǳ��� �̸��� �Է����ּ���.");
			System.out.println("-------------------� �Ǳ⸦ �˻��ϳ���?-------------------------");
			String input_instrumentName = sc.next().toLowerCase();
			switch (input_instrumentName) {
			case "mandolin":
			case "������":
				System.out.println("---------------mandolin�� Spec�� �Է����ּ���.-------------------------");
				System.out.println("������, ��, Ÿ��, ��Ÿ��, �޸���, ������ ������ �Է����ּ���.");
				MandolinSpec mandolinSpec = inputMandolinSpec(sc);
				matchingInstrument(mandolinSpec, inventory);
				break;

			case "guitar":
			case "��Ÿ":
				System.out.println("---------------guitar�� Spec�� �Է����ּ���.-------------------------");
				System.out.println("������, ��, �ټ�, Ÿ��, ��,ž  ������ �Է����ּ���.");
				GuitarSpec whatLikes = inputGuitarSpec(sc);
				matchingInstrument(whatLikes, inventory);
				break;
			}
			System.out.println("---------------------------------------------------------");

		}
	}

	private static void initializeInventory(Inventory inventory) {
		// �ʱ� ��Ÿ
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
		// �ʱ� ������
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

		// ��Ī�� ��Ÿ ��ȯ
		List matchingInstrument = null;

		// ��Ÿ������ ��� �˻� �� ��Ī
		if (whatErinLikesSpec instanceof GuitarSpec) {
			matchingInstrument = inventory.search((GuitarSpec) whatErinLikesSpec);
			// �˻����� ��ȯ�� ��Ī�� ��Ÿ�� ���Ͽ�
			if (!matchingInstrument.isEmpty()) {
				// ���峻�� �����ϴ� ��Ÿ���
				System.out.println("Erin, you might like these guitars:");
				for (Iterator i = matchingInstrument.iterator(); i.hasNext();) {
					// �ϳ��� ��Ÿ�� ���Ͽ�
					Guitar item = (Guitar) i.next();
					// ��Ī�� ��Ÿ ���
					System.out.println(item.toString());
				}
			} else {
				System.out.println("��Ī�� ��Ÿ�� �����ϴ�.");
			}

			// ���������� ��ü�� ��� �˻� �� ��Ī
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
				System.out.println("��Ī�� �������� �����ϴ�.");
			}

		}

	}

	// *����* ������, ��, �ټ�, Ÿ��, ��,ž
	private static GuitarSpec inputGuitarSpec(Scanner sc) {
		
		// ������
		String input_builder = sc.next();
		Builder input_EBuilder = (Builder) matchingEnumFiled(Builder.class, input_builder.toLowerCase());
		if (input_EBuilder == null) {
			input_EBuilder = Builder.UNKNOW;
		}
		System.out.println("input_builder:" +input_builder);


		// ��
		String input_model = sc.next();
		System.out.println("input_model:" +input_model);


		// �ټ�
		int input_numString = 0;
		try {
			input_numString = sc.nextInt();
		} catch (Exception e) {
			input_numString = 0;
			String nextTokens = sc.next(); // try ������ ���� �߻� ��, next() �޼ҵ� �߻� ��ġ���� ���� next() �޼ҵ忡�� ��ٷ� ��ū ��ȯ��
		}
		System.out.println("input_numString:" +input_numString);

		
		// Ÿ��	
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
		}
		System.out.println("input_type:" +input_type);

		// ����
		String input_backWood = sc.next();
		Wood input_EBWood = (Wood) matchingEnumFiled(Wood.class, input_backWood.toLowerCase());
		if (input_EBWood == null) {
			input_EBWood = Wood.UNKNOW;
		}
		System.out.println("input_backWood:" +input_backWood);


		// ž���
		String input_topWood = sc.next();
		Wood input_ETWood = (Wood) matchingEnumFiled(Wood.class, input_topWood.toLowerCase());
		if (input_ETWood == null) {
			input_ETWood = Wood.UNKNOW;
		}
		System.out.println("input_topWood:" +input_topWood);


		return new GuitarSpec(input_EBuilder, input_model, input_numString, input_EType, input_EBWood, input_ETWood);

	}

	// *����* ������,��, Ÿ��, ��Ÿ��, ��, ž
	private static MandolinSpec inputMandolinSpec(Scanner sc) {
		// ������
		String input_builder = sc.next();
		Builder input_EBuilder = (Builder) matchingEnumFiled(Builder.class, input_builder.toLowerCase());
		if (input_EBuilder == null) {
			input_EBuilder = Builder.UNKNOW;
		}

		// ��
		String input_model = sc.next();

		// Ÿ��
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
		}

		// ��Ÿ��
		String input_style = sc.next();
		Style input_EStyle = (Style) matchingEnumFiled(Style.class, input_style.toLowerCase());
		if (input_EStyle == null) {
			input_EStyle = Style.NONSTYLE;
		}

		// ����
		String input_backWood = sc.next();
		Wood input_EBWood = (Wood) matchingEnumFiled(Wood.class, input_backWood.toLowerCase());
		if (input_EBWood == null) {
			input_EBWood = Wood.UNKNOW;
		}

		// ž���
		String input_topWood = sc.next();
		Wood input_ETWood = (Wood) matchingEnumFiled(Wood.class, input_topWood.toLowerCase());
		if (input_ETWood == null) {
			input_ETWood = Wood.UNKNOW;
		}

		return new MandolinSpec(input_EBuilder, input_model, input_EType, input_EStyle, input_EBWood, input_ETWood);

	}
}
