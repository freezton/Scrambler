import com.scrambler.classes.VigenereMethodScrambler;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VigenereMethodScramblerTest {
    @Test
    public void getCorrectKeyMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        VigenereMethodScrambler scrambler = new VigenereMethodScrambler();
        Method getCorrectKeyMethod = scrambler.getClass().getDeclaredMethod("getCorrectKey", String.class, String.class);
        getCorrectKeyMethod.setAccessible(true);
        String expected = "ВЕСЛОГЁТМПДЖУН";
        String actual = getCorrectKeyMethod.invoke(scrambler, "трёхзвёздочный", "весло").toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encryptWithLetterYo() {
        VigenereMethodScrambler scrambler = new VigenereMethodScrambler();
        String textToEncrypt = "трёх звёздочный";

        String expected = "ХЦШВЧЁМЫСЯЬХПШ";
        String actual = scrambler.encrypt(textToEncrypt, "весло");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decipher() {
        VigenereMethodScrambler scrambler = new VigenereMethodScrambler();
        String textToEncrypt = "ХЦШВЧЁМЫСЯЬХПШ";

        String expected = "ТРЁХЗВЁЗДОЧНЫЙ";
        String actual = scrambler.decipher(textToEncrypt, "весло");
        Assert.assertEquals(expected, actual);
    }
}