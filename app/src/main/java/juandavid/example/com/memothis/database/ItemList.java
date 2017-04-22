package juandavid.example.com.memothis.database;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juandavid on 16/04/17.
 */

public final class ItemList {
	private static ItemList instance = new ItemList();
	private final SparseArray<String> ITEMS_NAME = new SparseArray<>();
	private final SparseArray<String> ITEMS_DEFINITION = new SparseArray<>();

	private ItemList() {
	}

	public static ItemList getInstance() {
		return instance;
	}

	public String getName(int id) {
		id %= ITEMS_NAME.size();
		return ITEMS_NAME.get(id);
	}

	public String getDefinition(int id) {
		id %= ITEMS_NAME.size();
		return ITEMS_DEFINITION.get(id);
	}

	public boolean isDefinition(int id, String expected) {
		return ITEMS_DEFINITION.get(id % ITEMS_DEFINITION.size()).equals(expected);
	}

	public int size() {
		return ITEMS_NAME.size();
	}

	public String[] getNameArray() {
		String[] array = new String[ITEMS_NAME.size()];

		for (int i = 0; i < ITEMS_NAME.size(); i++) array[i] = ITEMS_NAME.get(i);

		return array;
	}

	public List<String> getNameList() {
		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < ITEMS_NAME.size(); i++) {
			array.add(ITEMS_NAME.get(i));
		}
		return array;
	}

	public void setVocabularyList(List<String> names, List<String> definitions) {
		if (names.size() != definitions.size())
			throw new IndexOutOfBoundsException("Definitions array size must correspond to Names array");
		ITEMS_NAME.clear();
		ITEMS_DEFINITION.clear();

		int i = 0;
		for (String name : names) {
			if(name != null) {
				ITEMS_NAME.put(i, name);
				ITEMS_DEFINITION.put(i++, definitions.get(names.indexOf(name)));
			}
		}

	}
}
