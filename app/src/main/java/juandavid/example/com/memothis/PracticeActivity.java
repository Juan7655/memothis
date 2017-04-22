package juandavid.example.com.memothis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import juandavid.example.com.memothis.database.ItemList;

public class PracticeActivity extends AppCompatActivity implements WordListFragment.OnItemWordListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);

	}

	@Override
	public void OnItemWordInteraction(int item) {
		Toast.makeText(this, "Item click: " + ItemList.getInstance().getDefinition(item), Toast.LENGTH_SHORT).show();
	}
}
