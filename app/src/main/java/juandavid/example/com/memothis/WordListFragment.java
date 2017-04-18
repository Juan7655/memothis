package juandavid.example.com.memothis;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by juandavid on 17/04/17.
 */

public class WordListFragment extends Fragment {

	private OnItemWordListener mListener;

	public WordListFragment() {
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_practice, container, false);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
				R.layout.list_item_practice, R.id.list_item_practice_textview,
				new ArrayList<>(Arrays.asList(ItemList.getInstance().getNameList())));

		ListView lv = (ListView) rootView.findViewById(R.id.listview_practice);
		lv.setAdapter(adapter);

		if (getActivity() instanceof OnItemWordListener) {
			mListener = (OnItemWordListener) getActivity();
		} else {
			throw new RuntimeException(getActivity().toString()
					+ " must implement OnNotificationListener");
		}

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				mListener.OnItemWordInteraction(i);
			}
		});

		return rootView;
	}

	interface OnItemWordListener {
		void OnItemWordInteraction(int item);
	}
}