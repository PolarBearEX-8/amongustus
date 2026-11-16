package client.setting;

public enum GameSettingsPeacefulType {
    PEACEFUL(0, "options.difficulty.peaceful"),
    EASY(1, "options.difficulty.easy"),
    NORMAL(2, "options.difficulty.normal"),
    HARD(3, "options.difficulty.hard");

   public static final GameSettingsPeacefulType[] ENUM_VALUES2 = new GameSettingsPeacefulType[values().length];
   public final int count;
   public final String labelText;

   public static GameSettingsPeacefulType resolveGameSettingsPeacefulType(int count2) {
      return ENUM_VALUES2[count2 % ENUM_VALUES2.length];
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getIndex() {
      return this.count;
   }

   private GameSettingsPeacefulType(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   static {
      for (GameSettingsPeacefulType entry : values()) {
               ENUM_VALUES2[entry.count] = entry;
            }
   }
}
