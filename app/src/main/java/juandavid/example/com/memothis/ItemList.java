package juandavid.example.com.memothis;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juandavid on 16/04/17.
 */

final class ItemList {
	private final SparseArray<String> ITEMS_NAME = new SparseArray<>();
	private final SparseArray<String> ITEMS_DEFINITION = new SparseArray<>();
	private static ItemList instance = new ItemList();

	private ItemList() {
	}

	static ItemList getInstance(){
		return instance;
	}

	String getName(int id){
		id %= ITEMS_NAME.size();
		return ITEMS_NAME.get(id);
	}

	@SuppressWarnings("unused")
	public String getDefinition(int id){
		id %= ITEMS_NAME.size();
		return ITEMS_DEFINITION.get(id);
	}

	boolean isDefinition(int id, String expected){
		return ITEMS_DEFINITION.get(id % ITEMS_DEFINITION.size()).equals(expected);
	}

	int size(){
		return ITEMS_NAME.size();
	}

	String[] getNameArray(){
		String[] array = new String[ITEMS_NAME.size()];

		for (int i = 0; i <ITEMS_NAME.size(); i++) array[i] = ITEMS_NAME.get(i);

		return array;
	}

	List<String> getNameList(){
		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < ITEMS_NAME.size(); i++) {
			array.add(ITEMS_NAME.get(i));
		}
		return array;
	}

	void setNameList(List<String> array){
		ITEMS_NAME.clear();
		ITEMS_DEFINITION.clear();
		for (int i = 0; i < array.size(); i++) {
			ITEMS_NAME.put(i, array.get(i));
			ITEMS_DEFINITION.put(i, "def" + i);
		}
	}
}
