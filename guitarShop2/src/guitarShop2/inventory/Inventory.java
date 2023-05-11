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

		// �Է� ���� ���忡 ���Ͽ�
		// GuitarSpec �ν��Ͻ���� -> Guitar ��ü�� ����
		// MandolinSpec �ν��Ͻ���� -> Mandolin ��ü�� ����
		if (spec instanceof GuitarSpec) {
			instrument = new Guitar(serialNumber, price, (GuitarSpec) spec);
		} else if (spec instanceof MandolinSpec) {
			instrument = new Mandolin(serialNumber, price, (MandolinSpec) spec);
		}
		inventory.add(instrument);
	}

	public Instrument get(String serialNumber) {
		// inventory ����Ʈ ��ȸ
		for (Iterator i = inventory.iterator(); i.hasNext();) {
			// inventory ���� instrument �����´�.
			Instrument instrument = (Instrument) i.next();

			// instrument�� id�� �Էµ� id�� ���ٸ� ����
			if (instrument.getSerialNumber().equals(serialNumber)) {
				return instrument; // Here's another spot where using an abstract base class makes our
// design more flexible
			}
		}
		return null;
	}

	/* GuitarSpec ���� �˻� */
	public List search(GuitarSpec searchSpec) {

		List matchingInstruments = new LinkedList();

		// Inventory�� ����� Guitar ������.
		for (Iterator i = inventory.iterator(); i.hasNext();) { // ���ο� for each
			Instrument inventoryInstrument = (Instrument) i.next();

			if (inventoryInstrument instanceof Guitar) {
				// inventory���� ������ �ǱⰡ ��Ÿ���
				GuitarSpec guitarSpec = (GuitarSpec) inventoryInstrument.getSpec(); // ���� ��Ÿ�� ���� �����´�.

				// �Է¹��� ��Ÿ(this), �̺��丮�� ����� ��Ÿ ��
				boolean selectedGuitar = searchSpec.matches(guitarSpec);
				// ã�Ҵٸ� ����
				if (selectedGuitar) {
					matchingInstruments.add((Guitar) inventoryInstrument);
				}
			}
		}
		return matchingInstruments;
	}

	/* MandolinSpec ���� �˻� */
	public List search(MandolinSpec searchSpec) {

		List matchingInstruments = new LinkedList();

		for (Iterator i = inventory.iterator(); i.hasNext();) { // ���ο� for each
			Instrument inventoryInstrument = (Instrument) i.next();

			if (inventoryInstrument instanceof Mandolin) {

				MandolinSpec mandolinSpec = (MandolinSpec) inventoryInstrument.getSpec();
				// �Է¹��� ��Ÿ(this), �̺��丮�� ����� ������ ��
				boolean selectedMandolin = searchSpec.matches(mandolinSpec);
				// ã�Ҵٸ� ����
				if (selectedMandolin) {
					matchingInstruments.add((Mandolin) inventoryInstrument);
				}
			}

		}
		return matchingInstruments;
	}

}
