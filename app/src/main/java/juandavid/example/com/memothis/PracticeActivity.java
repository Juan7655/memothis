package juandavid.example.com.memothis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

import juandavid.example.com.memothis.database.ItemList;

public class PracticeActivity extends AppCompatActivity implements WordListFragment.OnItemWordListener{

	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);

		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
	}

	@Override
	public void OnItemWordInteraction(int item) {
		String def = ItemList.getInstance().getDefinition(this, item);
		Toast.makeText(this, def, Toast.LENGTH_SHORT).show();

		publishAnalytics(item ,def);
		publishDatabase(item);
	}

	private void publishAnalytics(int item, String def){
		Bundle params = new Bundle();
		params.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(item));
		params.putString(FirebaseAnalytics.Param.ITEM_NAME, def);
		params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, String.valueOf(item));

		mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
	}

	private void publishDatabase(int item){
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
		if(refreshedToken == null) refreshedToken = "UserNoName";
		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("UserData");
		CharSequence s  = DateFormat.format("d-MM-yy HH:MM:ss", new Date().getTime());
		myRef.child(refreshedToken).child("PracticeEvents").child(s.toString()).setValue(item - 1);
	}
}
