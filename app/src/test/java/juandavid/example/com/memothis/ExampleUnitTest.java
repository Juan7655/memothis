package juandavid.example.com.memothis;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

	@Test
	public void addition_isCorrect1() throws Exception {
		int i = 0;
		int e = i;
		e %= 10;
		assertEquals(e, i%10);
	}
}