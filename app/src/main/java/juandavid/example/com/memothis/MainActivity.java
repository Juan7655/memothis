package juandavid.example.com.memothis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


		String msg = getIntent().getStringExtra(ITEM_VALUE);
		final int itemId = Integer.parseInt(msg == null ? "-1" : msg) % list.size();
		setEnabled(itemId == -1, itemId);
	}

	public void setEnabled(boolean state, final int itemId) {
		editText.setEnabled(!state);
		button.setEnabled(!state);
		editText.setText(state ? " " : "");

		if (!state) {
			textView.setText(list.getName(itemId));
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					findViewById(R.id.image).setBackgroundResource(
							list.isDefinition(itemId, editText.getText().toString()) ?
									R.drawable.ic_check_black_24dp : R.drawable.ic_clear_black_24dp);
					editText.setEnabled(false);
					button.setEnabled(false);
				}
			});
		}
	}
}
