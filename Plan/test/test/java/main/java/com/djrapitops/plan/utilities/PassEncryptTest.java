package test.java.main.java.com.djrapitops.plan.utilities;

import main.java.com.djrapitops.plan.utilities.PassEncryptUtil;
import org.junit.Before;
import org.junit.Test;
import test.java.utils.RandomData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Fuzzlemann
 */
public class PassEncryptTest {

    private final List<String> passwords = new ArrayList<>();
    private final Map<String, String> passwordMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        IntStream.range(0, 50).forEach(i -> passwords.add(RandomData.randomString(RandomData.randomInt(1, 100))));

        for (String password : passwords) {
            passwordMap.put(password, PassEncryptUtil.createHash(password));
        }
    }

    @Test
    public void testVerification() throws Exception {
        for (Map.Entry<String, String> entry : passwordMap.entrySet()) {
            String password = entry.getKey();
            String hash = entry.getValue();

            assertTrue(PassEncryptUtil.verifyPassword(password, hash));
            assertFalse(PassEncryptUtil.verifyPassword(RandomData.randomString(RandomData.randomInt(1, 100)), hash));
        }
    }
}
