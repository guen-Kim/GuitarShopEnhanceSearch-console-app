package guitarShop2.inventory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import guitarShop2.model.Guitar;
import guitarShop2.model.GuitarSpec;
import guitarShop2.model.Instrument;
import guitarShop2.model.InstrumentSpec;
import guitarShop2.model.Mandolin;
import guitarShop2.model.MandolinSpec;

public class Inventory {
//The inventory list now holds
//multipe types of instruments, not just guitars.
	private List<Instrument> inventory; // Guitar, Mandolin . class

	public Inventory() {
		inventory = new LinkedList();
	}

// By using the instrument and instrumentSpec classes, we can turn addGuitar() into a more generic method, and
// create any kind of instrument.	
	public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
		Instrument instrument = null;

		// 입력 받은 스펙에 대하여
		// GuitarSpec 인스턴스라면 -> Guitar 객체로 저장
		// MandolinSpec 인스턴스라면 -> Mandolin 객체로 저장
		if (spec instanceof GuitarSpec) {
			instrument = new Guitar(serialNumber, price, (GuitarSpec) spec);
		} else if (spec instanceof MandolinSpec) {
			instrument = new Mandolin(serialNumber, price, (MandolinSpec) spec);
		}
		inventory.add(instrument);
	}

	public Instrument get(String serialNumber) {
		// inventory 리스트 순회
		for (Iterator i = inventory.iterator(); i.hasNext();) {
			// inventory 에서 instrument 가져온다.
			Instrument instrument = (Instrument) i.next();

			// instrument의 id가 입력된 id와 같다면 리턴
			if (instrument.getSerialNumber().equals(serialNumber)) {
				return instrument; // Here's another spot where using an abstract base class makes our
// design more flexible
			}
		}
		return null;
	}

	/* GuitarSpec 스펙 검색 */
	public List search(GuitarSpec searchSpec) {

		List matchingInstruments = new LinkedList();

		// Inventory에 저장된 Guitar 가져옴.
		for (Iterator i = inventory.iterator(); i.hasNext();) { // 새로운 for each
			Instrument inventoryInstrument = (Instrument) i.next();

			if (inventoryInstrument instanceof Guitar) {
				// inventory에서 꺼내온 악기가 기타라면
				GuitarSpec guitarSpec = (GuitarSpec) inventoryInstrument.getSpec(); // 현재 기타의 스펙 가져온다.

				// 입력받은 기타(this), 이벤토리에 저장된 기타 비교
				boolean selectedGuitar = searchSpec.matches(guitarSpec);
				// 찾았다면 저장
				if (selectedGuitar) {
					matchingInstruments.add((Guitar) inventoryInstrument);
				}
			}
		}
		return matchingInstruments;
	}

	/* MandolinSpec 스펙 검색 */
	public List search(MandolinSpec searchSpec) {

		List matchingInstruments = new LinkedList();

		for (Iterator i = inventory.iterator(); i.hasNext();) { // 새로운 for each
			Instrument inventoryInstrument = (Instrument) i.next();

			if (inventoryInstrument instanceof Mandolin) {

				MandolinSpec mandolinSpec = (MandolinSpec) inventoryInstrument.getSpec();
				// 입력받은 기타(this), 이벤토리에 저장된 마도린 비교
				boolean selectedMandolin = searchSpec.matches(mandolinSpec);
				// 찾았다면 저장
				if (selectedMandolin) {
					matchingInstruments.add((Mandolin) inventoryInstrument);
				}
			}

		}
		return matchingInstruments;
	}

}
