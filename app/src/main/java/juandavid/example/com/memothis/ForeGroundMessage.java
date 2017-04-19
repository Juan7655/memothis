package juandavid.example.com.memothis;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by juandavid on 17/04/17.
 */

public class ForeGroundMessage extends FirebaseMessagingService {

	@SuppressWarnings("FieldCanBeLocal")
	private final String ITEM_VALUE = "ITEM_VALUE";

	@SuppressWarnings("unused")//Default constructor. DO NOT ERASE.
	public ForeGroundMessage() {
	}

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		Intent intent = new Intent(getBaseContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(ITEM_VALUE, remoteMessage.getData().get(ITEM_VALUE));
		startActivity(intent);
	}
}
