package juandavid.example.com.memothis.database;

import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import juandavid.example.com.memothis.WordListFragment;

/**
 * Created by juandavid on 21/04/17.
 */

public class MyValueEventListener implements ValueEventListener {

	@SuppressWarnings("FieldCanBeLocal")
	private final String NAME_TAG = "NameList",
			DEFINITION_TAG = "DefinitionList";

	@Override
	@SuppressWarnings("unchecked")
	public void onDataChange(DataSnapshot dataSnapshot) {
		List<String> nameList, definitionList;
		try {
			nameList = (List<String>) dataSnapshot.child(NAME_TAG).getValue();
			definitionList = (List<String>) dataSnapshot.child(DEFINITION_TAG).getValue();
		} catch (ClassCastException e) {
			return;
		}

		if (nameList.size() != definitionList.size()) return;
		ItemList.getInstance().setVocabularyList(nameList, definitionList);
		ArrayAdapter<String> adapt = WordListFragment.adapter;
		if (adapt != null) {
			adapt.clear();
			adapt.addAll(ItemList.getInstance().getNameList());
		}
	}

	@Override
	public void onCancelled(DatabaseError databaseError) {
	}
}
