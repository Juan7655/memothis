package juandavid.example.com.memothis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

import juandavid.example.com.memothis.database.ItemList;
import juandavid.example.com.memothis.database.MyValueEventListener;

public class MainActivity extends AppCompatActivity {

	@SuppressWarnings("FieldCanBeLocal")
	private final String ITEM_VALUE = "ITEM_VALUE";
	private TextView textView;
	private Button button;
	private EditText editText;
	private final ItemList list = ItemList.getInstance();

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

		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("ItemList");
		myRef.addValueEventListener(new MyValueEventListener(this));
		String msg = getIntent().getStringExtra(ITEM_VALUE);
		final int itemId = msg == null ? -1 : Integer.parseInt(msg);
		setEnabled(itemId != -1, itemId);
	}

	private void setEnabled(boolean state, final int itemId) {
		editText.setEnabled(state);
		button.setEnabled(state);
		editText.setText(state ? "" : " ");

		if (state) {
			textView.setText(list.getName(this, itemId));
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Configuration of the Views
					boolean result = list.isDefinition(getBaseContext(), itemId, editText.getText().toString());
					findViewById(R.id.image).setBackgroundResource(
							result ? R.drawable.ic_check_black_24dp : R.drawable.ic_clear_black_24dp);
					editText.setEnabled(false);
					button.setEnabled(false);

					String refreshedToken = FirebaseInstanceId.getInstance().getToken();
					if(refreshedToken == null) refreshedToken = "UserNoName";
					DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("UserData");
					CharSequence s  = DateFormat.format("d-MM-yy HH:MM:ss", new Date().getTime() + 1);
					myRef.child(refreshedToken).child(s.toString()).setValue(new ItemResult(itemId, result));
				}
			});
		}
	}
}
