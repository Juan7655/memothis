package juandavid.example.com.memothis;

/**
 * Created by juandavid on 22/04/17.
 */

public class ItemResult{
	private int idWord;
	private boolean result;

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
