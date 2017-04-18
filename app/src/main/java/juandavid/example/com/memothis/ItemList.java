package juandavid.example.com.memothis;

import android.util.SparseArray;

/**
 * Created by juandavid on 16/04/17.
 */

final class ItemList {
	private final SparseArray<String> ITEMS_NAME = new SparseArray<>();
	private final SparseArray<String> ITEMS_DEFINITION = new SparseArray<>();
	private static ItemList instance = new ItemList();

	private ItemList() {
		for (int i = 0; i < 10; i++) {
			ITEMS_NAME.put(i, "ITEM"+i);
			ITEMS_DEFINITION.put(i, "DEF"+i);
		}
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
		return ITEMS_DEFINITION.get(id).equals(expected);
	}

	int size(){
		return ITEMS_NAME.size();
	}

	String[] getNameList(){
		String[] array = new String[ITEMS_NAME.size()];

		for (int i = 0; i <ITEMS_NAME.size(); i++) array[i] = ITEMS_NAME.get(i);

		return array;
	}
}
