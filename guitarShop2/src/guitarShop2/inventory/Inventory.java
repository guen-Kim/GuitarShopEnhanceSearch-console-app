package guitarShop2.inventory;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import guitarShop2.instrument_enum.Builder;
import guitarShop2.instrument_enum.Style;
import guitarShop2.instrument_enum.Type;
import guitarShop2.instrument_enum.Wood;
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

	// ���Ͽ� ����� �Ǳ� ����Ʈ�� �ҷ��ɴϴ�.
	public void loadInstrumentData() throws Exception {
		// inventory.clear();

		/* ���� �б� */
		// 1.FileInputStream �����
		FileInputStream fileStream = new FileInputStream("InstrumentList.ser");
		// 2.ObjectInputStream �����
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fileStream);
			// 3. ��ü �б�
			while (true) {
				Object ob = ois.readObject();
				if (ob instanceof Guitar) {
					Guitar guitar = (Guitar) ob;
					inventory.add(guitar);
				} else {
					Mandolin mandolin = (Mandolin) ob;
					inventory.add(mandolin);
				}
			}

		} catch (EOFException e) {

			for (Instrument ie : inventory) {
				if (ie instanceof Guitar) {
					Guitar eg = (Guitar) ie;
					System.out.println(eg);
				} else {
					Mandolin em = (Mandolin) ie;
					System.out.println(em);

				}
				// 4. ObjectInputStream �ݱ�
				ois.close();

			}
			System.out.println("�ε� ��");
			System.out.println("����� �Ǳ� ��: "+ inventory.size());

		}
	}

	// ���α׷��� �����ϱ� �� �����Ǿ��� inventory �����͸� ���������� ���Ͽ� �����մϴ�.
	public void updateInventoryData() throws Exception {
		/* ���� ���� */
		// 1. FileOutputStream �����
		FileOutputStream fileOutputStream = new FileOutputStream("InstrumentList.ser");
		// 2.ObjectOutputStream �����
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

		//System.out.println(inventory.size());
		
		// 3.��ü ����
		for (Instrument e : inventory) {
			if (e instanceof Guitar) {
				oos.writeObject((Guitar) e);
			} else {
				oos.writeObject((Mandolin) e);
			}
		}
		// 4. ObjectOutputStream �ݱ�
		// oos.close();
	}

	public void origin() throws Exception {

		// 1. FileOutputStream �����
		FileOutputStream fileOutputStream = new FileOutputStream("InstrumentList.ser");
		// 2.ObjectOutputStream �����
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		// 3.��ü ����
		inventory.add(
				new Guitar("1", 1000, new GuitarSpec(Builder.FENDER, "g-1", 6, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER)));
		inventory.add(
				new Guitar("2", 2000, new GuitarSpec(Builder.FENDER, "g-2", 6, Type.ACOUSTIC, Wood.ALDER, Wood.ALDER)));
		inventory.add(new Mandolin("3", 3000,
				new MandolinSpec(Builder.FENDER, "g-1", Type.ACOUSTIC, Style.STYLE2, Wood.ALDER, Wood.ALDER)));
		inventory.add(new Mandolin("4", 4000,
				new MandolinSpec(Builder.FENDER, "g-2", Type.ACOUSTIC, Style.STYLE1, Wood.ALDER, Wood.ALDER)));

		for (Instrument e : inventory) {
			if (e instanceof Guitar) {
				oos.writeObject((Guitar) e);
			} else {
				oos.writeObject((Mandolin) e);
			}
		}
		//4.ObjectOutputStream �ݱ�
		// oos.close();


	}

}
