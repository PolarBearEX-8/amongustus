package baritone.behavior;

import baritone.Baritone;
import baritone.api.Settings;

public enum LookTargetMode {
   CLIENT,
   SERVER,
   NONE;

public static LookTargetMode resolve(boolean flag) {
      Settings settings = Baritone.settings();
      boolean value = (Boolean)settings.antiCheatCompatibility.value;
      boolean value2 = (Boolean)settings.blockFreeLook.value;
      if ((Boolean)settings.freeLook.value) {
         if (flag) {
            return value2 ? SERVER : CLIENT;
         } else {
            return value ? SERVER : NONE;
         }
      } else {
         return CLIENT;
      }
   }
}
