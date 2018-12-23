package neb;

import org.junit.Assert;
import org.junit.Test;

public class WCTest {
    private final WordsChecker wc = WordsChecker.simple();

    @Test
    public void simpleTest() {
        Assert.assertFalse(wc.containsBadWords("simple text"));
        Assert.assertTrue(wc.containsBadWords(" stay on the nee;"));
    }
}
