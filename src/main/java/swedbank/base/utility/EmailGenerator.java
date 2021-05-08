package swedbank.base.utility;

import java.time.Instant;

public class EmailGenerator {

    public static String generateNewUniqueEmail() {
        System.out.println("Date in the Email(UTC):  " + Instant.now());
        return Instant.now().getEpochSecond() + ".testacc"+ "@qatests.com";

    }
}
