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

		final TextView textView = (TextView) findViewById(R.id.tv);
		final EditText editText = (EditText) findViewById(R.id.edit_text);
		final Button button = (Button) findViewById(R.id.submit_button);

		final ItemList list = ItemList.getInstance();
		String msg = getIntent().getStringExtra(ITEM_VALUE);
		final int itemId = Integer.parseInt(msg == null ? "-1" : msg) % list.size();
		if (itemId == -1) {
			editText.setText(" ");
			editText.setEnabled(false);
			button.setEnabled(false);
		} else {
			textView.setText(list.getName(itemId));
			editText.setEnabled(true);
			editText.setText("");
			button.setEnabled(true);
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
