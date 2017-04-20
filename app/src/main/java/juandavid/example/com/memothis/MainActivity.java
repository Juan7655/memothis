package juandavid.example.com.memothis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	@SuppressWarnings("FieldCanBeLocal")
	private final String ITEM_VALUE = "ITEM_VALUE";
	private TextView textView;
	private Button button;
	private EditText editText;
	private ItemList list = ItemList.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), PracticeActivity.class));
			}
		});

		textView = (TextView) findViewById(R.id.tv);
		button = (Button) findViewById(R.id.submit_button);
		editText = (EditText) findViewById(R.id.edit_text);

		attachFirebaseDatabase();
		String msg = getIntent().getStringExtra(ITEM_VALUE);
		final int itemId = msg == null ? -1 : Integer.parseInt(msg) % list.size();
		setEnabled(itemId != -1, itemId);
	}

	public void setEnabled(boolean state, final int itemId) {
		editText.setEnabled(state);
		button.setEnabled(state);
		editText.setText(state ? "" : " ");

		if (state) {
			textView.setText(list.getName(itemId));
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Configuration of the Views
					findViewById(R.id.image).setBackgroundResource(
							list.isDefinition(itemId, editText.getText().toString()) ?
									R.drawable.ic_check_black_24dp : R.drawable.ic_clear_black_24dp);
					editText.setEnabled(false);
					button.setEnabled(false);
				}
			});
		}
	}

	private void attachFirebaseDatabase() {
		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("ItemList");

		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				@SuppressWarnings("unchecked") List<String> list = (List<String>) dataSnapshot.getValue();
				ItemList.getInstance().setNameList(list);
				ArrayAdapter<String> adapt = WordListFragment.adapter;
				if (adapt != null) {
					adapt.clear();
					adapt.addAll(ItemList.getInstance().getNameList());
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
}
