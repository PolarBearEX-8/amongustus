package augustus.custom;

import java.util.Locale;

/**
 * Remove only the obsolete main-menu connection notice from text drawing.
 */
public final class MenuNotice {
    private MenuNotice() {}

    public static String filter(String text) {
        if (text == null) return null;
        String plain = text.replaceAll("§.", "").toLowerCase(Locale.ROOT);
        return plain.contains("connect") && plain.contains("successfully")
                && plain.contains("augustus") && plain.contains("server") ? "" : text;
    }
}
