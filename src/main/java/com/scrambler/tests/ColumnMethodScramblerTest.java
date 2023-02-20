import com.scrambler.classes.ColumnMethodScrambler;
import org.junit.Assert;
import org.junit.Test;
public class ColumnMethodScramblerTest {

    @Test
    public void encryptWithoutFullFilling() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "This is*&%$*( english привет %#$%text";

        String expected = "TSIXILESGTHESTINH";
        String actual = scrambler.encrypt(textToEncrypt, "apple");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encryptWithFullFilling() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "Som$#$%^e random ^$^ бд words";

        String expected = "ORDWDSENMRMAOOS";
        String actual = scrambler.encrypt(textToEncrypt, "key");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encryptWithEmptyKey() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "Som$#$%^e random ^$^ бд words";

        String actual = scrambler.encrypt(textToEncrypt, "");
        Assert.assertNull(actual);
    }

    @Test
    public void encryptWithTooLongKey() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "This is*&%$*( english привет %#$%text";

        String expected = "ISTHHTNILSIEGTSXE";
        String actual = scrambler.encrypt(textToEncrypt, "That key is longer than text");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encryptWithoutFullFillingAndInvalidKey() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "This is*&%$*( english привет %#$%text";

        String expected = "TSIXILESGTHESTINH";
        String actual = scrambler.encrypt(textToEncrypt, "Привет $%^&apple");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encryptWithFullFillingAndInvalidKey() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToEncrypt = "Som$#$%^e random ^$^ бд words";

        String expected = "ORDWDSENMRMAOOS";
        String actual = scrambler.encrypt(textToEncrypt, "ke#@$%^&y слово на русском");
        Assert.assertEquals(expected, actual);
    }

            //decipher method test

    @Test
    public void decipherWithoutFullFilling() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToDecipher = "TSIXILESGTHESTINH";

        String expected = "THISISENGLISHTEXT";
        String actual = scrambler.decipher(textToDecipher, "apple");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decipherWithFullFilling() {
        ColumnMethodScrambler scrambler = new ColumnMethodScrambler();
        String textToDecipher = "ORD#$%^&*()WDSENMRMAoos";

        String expected = "SOMERANDOMWORDS";
        String actual = scrambler.decipher(textToDecipher, "key");
        Assert.assertEquals(expected, actual);
    }
}
