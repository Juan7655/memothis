package juandavid.example.com.memothis;

import android.support.annotation.Keep;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by juandavid on 22/04/17.
 */
@SuppressWarnings("unused")
@Keep
@IgnoreExtraProperties
public class ItemResult{
	public int idWord;
	public boolean result;

	ItemResult(int idWord, boolean result) {
		this.idWord = idWord;
		this.result = result;
	}

	public int getIdWord() {
		return idWord;
	}

	public boolean isResult() {
		return result;
	}
}
