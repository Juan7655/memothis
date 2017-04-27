package juandavid.example.com.memothis.database;

import android.content.Context;
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
	private Context mContext;

	public MyValueEventListener(Context context){
		mContext = context;
	}

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

		int size = definitionList.size();
		if (nameList.size() != size) return;
		ItemList.getInstance().setVocabularyList(mContext, nameList, definitionList);
		ArrayAdapter<String> adapt = WordListFragment.adapter;
		if (adapt != null) {
			adapt.clear();
			adapt.addAll(ItemList.getInstance().getNameList(mContext));
		}
	}

	@Override
	public void onCancelled(DatabaseError databaseError) {
	}
}
