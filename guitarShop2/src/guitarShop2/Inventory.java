package guitarShop2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
	private List guitars; // <Guitar>

	public Inventory() {
		guitars = new LinkedList();
	}

	public void addGuitar(String serialNumber, double price, GuitarSpec spec) {
		Guitar guitar = new Guitar(serialNumber, price, spec);
		guitars.add(guitar);
	}

	public Guitar getGuitar(String serialNumber) {
		for (Iterator i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;

	}

	public List search(GuitarSpec searchSpec) {
		// �˻� ��Ÿ�� ��� ������ ������ ��Ÿ ����
		List matchingGuitars = new LinkedList();

		// ���� Inventory�� ����� Guitar ������.
		for (Iterator i = guitars.iterator(); i.hasNext();) { // ���ο� for each
			Guitar guitar = (Guitar) i.next();
			GuitarSpec guitarSpec = guitar.getSpec(); // ���� ��Ÿ�� ���� �����´�.

			// �Է¹��� ��Ÿ(this), �̺��丮�� ����� ��Ÿ ��
			boolean selectedGuitar = searchSpec.matches(guitarSpec);
			// ã�Ҵٸ� ����
			if (selectedGuitar) {
				matchingGuitars.add(guitar);
			}

		}
		return matchingGuitars;
	}

	// �־��� ���� ������ ��Ÿ �˻�
	public List searchForPrice(double left, double right) {
		List matchingGuitars = new LinkedList();

		// ���� Inventory�� ����� Guitar ������.
		for (Iterator i = guitars.iterator(); i.hasNext();) { // ���ο� for each
			Guitar guitar = (Guitar) i.next();
			// �̺��丮�� ����� ��Ÿ ����
			double price = guitar.getPrice();

			boolean selectedGuitar = false;
			// �̺��丮�� ����� ��Ÿ ���� ��
			if (left <= price && right >= price) {
				selectedGuitar = true;
			}
			// ã�Ҵٸ� ����
			if (selectedGuitar) {
				matchingGuitars.add(guitar);
			}
		}
		return matchingGuitars;
	}

}

