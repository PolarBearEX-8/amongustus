package wdl;

public class WorldBackupTypeSwitch {
   public static final int[] SWITCH_MAP = new int[WorldBackupType.values().length];

   static {
      try {
         SWITCH_MAP[WorldBackupType.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError e) {
      }

      try {
         SWITCH_MAP[WorldBackupType.FOLDER.ordinal()] = 2;
      } catch (NoSuchFieldError e2) {
      }

      try {
         SWITCH_MAP[WorldBackupType.ZIP.ordinal()] = 3;
      } catch (NoSuchFieldError e3) {
      }
   }
}
