package juandavid.example.com.memothis;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by juandavid on 17/04/17.
 */

public class ForeGroundMessage extends FirebaseMessagingService {

	private static final String TAG = "tagtag";
	private final String ITEM_VALUE = "ITEM_VALUE";
	private Context context;

	public ForeGroundMessage(){
		context = getBaseContext();
	}

	public ForeGroundMessage(Context context) {
		this.context = context;
	}

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		Log.i(TAG, "From: " + remoteMessage.getFrom());

		// Check if message contains a data payload.
		if (remoteMessage.getData().size() > 0)
			Log.i(TAG, "Message data payload: " + remoteMessage.getData());
		String msg = remoteMessage.getData().get(ITEM_VALUE);
		final int itemId = Integer.parseInt(msg == null ? "-1" : msg) % ItemList.getInstance().size();

		if (context instanceof MainActivity) {
			((MainActivity) context).setEnabled(itemId == -1, itemId);
		}else{
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(ITEM_VALUE, remoteMessage.getData().get(ITEM_VALUE));
			startActivity(intent);
		}
	}
}
