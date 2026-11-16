package augustus.custom;

public final class MenuBackground {
    private static boolean drawn;

    private MenuBackground() {}

    public static void beginFrame() {
        drawn = false;
    }

    public static void loading(Object renderer) {
        // Preserve Augustus's original startup loading screen instead of Alan's portrait.
    }

    public static boolean drawCurrent() {
        return false;
    }

    public static boolean draw(Object screen) {
        return false;
    }
}
