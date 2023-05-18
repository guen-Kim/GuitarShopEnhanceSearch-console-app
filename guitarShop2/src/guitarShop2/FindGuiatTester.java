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
			System.out.println("-------------------�Ǳ� �� ���� ������-------------------------");
			System.out.println("\t�Ǳ��� �̸��� �Է����ּ���. ���Ḧ ���Ѵٸ� '����'�� �Է����ּ���.");

			String input_instrumentName = sc.next().toLowerCase();
			switch (input_instrumentName) {

			case "mandolin":
			case "������":
				System.out.println("������ �޴��Դϴ�. '�˻� 'or '�߰�'");

				String input_menu_m = sc.next().toLowerCase();
				switch (input_menu_m) {
				case "�߰�":
					System.out.println("--------�߰��� mandolin�� Spec�� �Է����ּ���.-------------------------");
					System.out.println("serialNumber, ����, ������, ��, Ÿ��, ��Ÿ��, �޸���, ������ ������ �Է����ּ���.");
					String serialNumber =sc.next();
					double price = sc.nextDouble();
					MandolinSpec mandolinSpec_ = inputMandolinSpec(sc);
					inventory.addInstrument(serialNumber, price, mandolinSpec_);
					break;
				case "�˻�":
					System.out.println("--------�˻��� mandolin�� Spec�� �Է����ּ���.-------------------------");
					System.out.println("������, ��, Ÿ��, ��Ÿ��, �޸���, ������ ������ �Է����ּ���.");
					MandolinSpec mandolinSpec = inputMandolinSpec(sc);
					matchingInstrument(mandolinSpec, inventory);
					break;
				}
				break;

			case "guitar":
			case "��Ÿ":
				System.out.println("��Ÿ �޴��Դϴ�. '�˻� 'or '�߰�'");

				String input_menu_g = sc.next().toLowerCase();
				switch (input_menu_g) {
				case "�߰�":
					System.out.println("---------�߰��� guitar�� Spec�� �Է����ּ���.-------------------------");
					System.out.println("id, ����, ������, ��, �ټ�, Ÿ��, ��,ž  ������ �Է����ּ���.");
					String serialNumber =sc.next();
					double price = sc.nextDouble();
					GuitarSpec guitarSpec_ = inputGuitarSpec(sc);
					inventory.addInstrument(serialNumber, price, guitarSpec_);
					break;
				case "�˻�":
					System.out.println("-----------�˻��� guitar�� Spec�� �Է����ּ���.-------------------------");
					System.out.println("������, ��, �ټ�, Ÿ��, ��,ž  ������ �Է����ּ���.");
					GuitarSpec guitarSpec = inputGuitarSpec(sc);
					matchingInstrument(guitarSpec, inventory);
					break;

				}
				break;

			case "����":
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

		// ��
		String input_model = sc.next();

		// �ټ�
		int input_numString = 0;
		try {
			input_numString = sc.nextInt();
		} catch (Exception e) {
			input_numString = 0;
			String nextTokens = sc.next(); // try ������ ���� �߻� ��, next() �޼ҵ� �߻� ��ġ���� ���� next() �޼ҵ忡�� ��ٷ� ��ū ��ȯ��
		}

		// Ÿ��
		String input_type = sc.next();
		Type input_EType = (Type) matchingEnumFiled(Type.class, input_type.toLowerCase());
		if (input_EType == null) {
			input_EType = Type.NUSEPCIFIED;
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
