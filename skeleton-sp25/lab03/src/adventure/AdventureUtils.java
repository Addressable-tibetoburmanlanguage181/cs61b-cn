package adventure;

public class AdventureUtils {

    /** 返回给定字符串是否是有效的整数。 */
    static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
