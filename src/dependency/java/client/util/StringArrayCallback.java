package client.util;

public interface StringArrayCallback {
    String[] stringArray = ((java.util.function.Supplier<String[]>)(() -> {
        String[] textArray = new String[]{"Oooooo", "ooOooo", "oOoooo", "ooooOo", "oooooO", "oooOoo", "oooooo"};
        return new String[]{textArray[6], textArray[0], textArray[2], textArray[1], textArray[5], textArray[3], textArray[4]};
    })).get();



   void updateState();
}
