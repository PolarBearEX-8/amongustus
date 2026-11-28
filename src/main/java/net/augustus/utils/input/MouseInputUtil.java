package net.augustus.utils.input;

import net.augustus.utils.TimeHelper;
import org.lwjgl.input.Mouse;

public class MouseInputUtil {
   public static boolean isButtonDown(int n) {
      return Mouse.isCreated() && Mouse.isButtonDown(n);
   }
}
