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

	// 파일에 저장된 악기 리스트를 불러옵니다.
	public void loadInstrumentData() throws Exception {
		// inventory.clear();

		/* 파일 읽기 */
		// 1.FileInputStream 만들기
		FileInputStream fileStream = new FileInputStream("InstrumentList.ser");
		// 2.ObjectInputStream 만들기
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fileStream);
			// 3. 객체 읽기
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
				// 4. ObjectInputStream 닫기
				ois.close();

			}
			System.out.println("로딩 끝");
			System.out.println("저장된 악기 수: "+ inventory.size());

		}
	}

	// 프로그램이 종료하기 전 수정되었던 inventory 데이터를 최종적으로 파일에 저장합니다.
	public void updateInventoryData() throws Exception {
		/* 파일 저장 */
		// 1. FileOutputStream 만들기
		FileOutputStream fileOutputStream = new FileOutputStream("InstrumentList.ser");
		// 2.ObjectOutputStream 만들기
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

		//System.out.println(inventory.size());
		
		// 3.객체 저장
		for (Instrument e : inventory) {
			if (e instanceof Guitar) {
				oos.writeObject((Guitar) e);
			} else {
				oos.writeObject((Mandolin) e);
			}
		}
		// 4. ObjectOutputStream 닫기
		// oos.close();
	}

	public void origin() throws Exception {

		// 1. FileOutputStream 만들기
		FileOutputStream fileOutputStream = new FileOutputStream("InstrumentList.ser");
		// 2.ObjectOutputStream 만들기
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		// 3.객체 저장
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
		//4.ObjectOutputStream 닫기
		// oos.close();


	}

}
