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
		// 검색 기타와 모든 조건이 동일한 기타 저장
		List matchingGuitars = new LinkedList();

		// 현재 Inventory에 저장된 Guitar 가져옴.
		for (Iterator i = guitars.iterator(); i.hasNext();) { // 새로운 for each
			Guitar guitar = (Guitar) i.next();
			GuitarSpec guitarSpec = guitar.getSpec(); // 현재 기타의 스펙 가져온다.

			// 입력받은 기타(this), 이벤토리에 저장된 기타 비교
			boolean selectedGuitar = searchSpec.matches(guitarSpec);
			// 찾았다면 저장
			if (selectedGuitar) {
				matchingGuitars.add(guitar);
			}

		}
		return matchingGuitars;
	}

	// 주어진 가격 내에서 기타 검색
	public List searchForPrice(double left, double right) {
		List matchingGuitars = new LinkedList();

		// 현재 Inventory에 저장된 Guitar 가져옴.
		for (Iterator i = guitars.iterator(); i.hasNext();) { // 새로운 for each
			Guitar guitar = (Guitar) i.next();
			// 이벤토리에 저장된 기타 가격
			double price = guitar.getPrice();

			boolean selectedGuitar = false;
			// 이벤토리에 저장된 기타 가격 비교
			if (left <= price && right >= price) {
				selectedGuitar = true;
			}
			// 찾았다면 저장
			if (selectedGuitar) {
				matchingGuitars.add(guitar);
			}
		}
		return matchingGuitars;
	}

}

