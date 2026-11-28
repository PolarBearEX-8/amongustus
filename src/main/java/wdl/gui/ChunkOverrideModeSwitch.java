package wdl.gui;

public class ChunkOverrideModeSwitch {
   public static final int[] SWITCH_MAP = new int[ChunkOverrideMode.values().length];

   static {
      try {
         SWITCH_MAP[ChunkOverrideMode.PANNING.ordinal()] = 1;
      } catch (NoSuchFieldError e) {
      }

      try {
         SWITCH_MAP[ChunkOverrideMode.REQUESTING.ordinal()] = 2;
      } catch (NoSuchFieldError e2) {
      }
   }
}
