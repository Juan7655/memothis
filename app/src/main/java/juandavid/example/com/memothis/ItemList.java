package juandavid.example.com.memothis;

import android.util.SparseArray;

/**
 * Created by juandavid on 16/04/17.
 */

public final class ItemList {
	private final SparseArray<String> ITEMS_NAME = new SparseArray<>();
	private final SparseArray<String> ITEMS_DEFINITION = new SparseArray<>();
	private static ItemList instance = new ItemList();

	private ItemList() {
		for (int i = 0; i < 10; i++) {
			ITEMS_NAME.put(i, "ITEM"+i);
			ITEMS_DEFINITION.put(i, "DEF"+i);
		}
	}

	public static ItemList getInstance(){
		return instance;
	}

	public boolean hasItem(String name){
		for (int i = 0; i < ITEMS_NAME.size(); i++) {
			if(name.equals(ITEMS_NAME.get(i))) return true;
		}
		return false;
	}

	public String getName(int id){
		id %= ITEMS_NAME.size();
		return ITEMS_NAME.get(id);
	}

	public String getDefinition(int id){
		id %= ITEMS_NAME.size();
		return ITEMS_DEFINITION.get(id);
	}

	public void addItem(String name, String definition){
		ITEMS_NAME.put(ITEMS_NAME.size(), name);
		ITEMS_DEFINITION.put(ITEMS_DEFINITION.size(), definition);
	}

	public boolean isDefinition(int id, String expected){
		return ITEMS_DEFINITION.get(id).equals(expected);
	}

	public int size(){
		return ITEMS_NAME.size();
	}

	public String[] getNameList(){
		String[] array = new String[ITEMS_NAME.size()];

		for (int i = 0; i <ITEMS_NAME.size(); i++) {
				array[i] = ITEMS_NAME.get(i);
		}

		return array;
	}
}
