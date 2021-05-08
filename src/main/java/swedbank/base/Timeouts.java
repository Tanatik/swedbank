package swedbank.base;

public class Timeouts {

    public static int DEF_TIMEOUT_PAGE_OBJECT_WAIT = 75;
    public static int DEF_TIMEOUT_BLOCK_OBJECT_WAIT = 30;
    public static int DEF_TIMEOUT_WIDGET_WAIT = 30;
    public static int DEF_TIMEOUT_WIDGET_ACTION_WAIT = 500;
    public static int DEF_TIMEOUT_ELEMENT_WAIT = 10;

    public static void waitFewSec(long msec){
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
