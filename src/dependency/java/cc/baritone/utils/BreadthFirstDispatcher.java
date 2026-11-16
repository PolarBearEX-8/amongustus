package cc.baritone.utils;

import cc.Settings;

public enum BreadthFirstDispatcher {
    CLIENT,
    SERVER,
    NONE;

   public static final BreadthFirstDispatcher[] ENUM_VALUES = new BreadthFirstDispatcher[]{CLIENT, SERVER, NONE};

   public static BreadthFirstDispatcher resolveBreadthFirstDispatcher(boolean flag) {
      Settings settings = BaritoneManager.getSettings();
      boolean dataRef = (Boolean)settings.antiCheatCompatibility.dataRef;
      boolean dataRef2 = (Boolean)settings.blockFreeLook.dataRef;
      if ((Boolean)settings.freeLook.dataRef) {
         if (flag) {
            return dataRef2 ? SERVER : CLIENT;
         } else {
            return dataRef ? SERVER : NONE;
         }
      } else {
         return CLIENT;
      }
   }
}
