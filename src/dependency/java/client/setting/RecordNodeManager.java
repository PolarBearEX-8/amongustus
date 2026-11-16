package client.setting;

public class RecordNodeManager {
   public static final RecordNodeManager recordNodeManager = new RecordNodeManager();
   public final int[] countArray = new int[MovementBooleanProperty.values().length];

   public RecordNodeManager() {
      try {
         this.countArray[MovementBooleanProperty.ALWAYS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[MovementBooleanProperty.NEVER.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[MovementBooleanProperty.HIDE_FOR_OTHER_TEAMS.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[MovementBooleanProperty.HIDE_FOR_OWN_TEAM.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
