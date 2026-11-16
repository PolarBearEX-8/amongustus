package client.util;

import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.client.settings.KeyBinding;

public class CurveFilter {
   public static void applyKeyBindingArray(KeyBinding[] keyBindingArray, KeyBinding[] keyBindingArray2) {
      HashSet set = new HashSet();

      for (int index = 0; index < keyBindingArray2.length; index++) {
         KeyBinding keyBinding = keyBindingArray2[index];
         set.add(keyBinding.getKeyCode());
      }

      HashSet set2 = new HashSet<>(Arrays.asList(keyBindingArray));
      set2.removeAll(Arrays.asList(keyBindingArray2));

      for (KeyBinding keyBinding2 : (Iterable<KeyBinding>)(Iterable<?>)set2) {
         Integer keyCode = keyBinding2.getKeyCode();
         if (set.contains(keyCode)) {
            keyBinding2.setKeyCode(0);
         }
      }
   }
}
