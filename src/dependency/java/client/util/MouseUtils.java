package client.util;

import org.lwjgl.input.Mouse;

public class MouseUtils {
   public static boolean checkCondition(int count) {
      return Mouse.isCreated() && Mouse.isButtonDown(count);
   }
}
