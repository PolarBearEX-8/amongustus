package client.setting;

public enum GameSettingsFullType {
    FULL(0, "options.chat.visibility.full"),
    SYSTEM(1, "options.chat.visibility.system"),
    HIDDEN(2, "options.chat.visibility.hidden");

   public static final GameSettingsFullType[] ENUM_VALUES2 = new GameSettingsFullType[values().length];
   public final int count;
   public final String labelText;
   public static final GameSettingsFullType[] ENUM_VALUES = new GameSettingsFullType[]{FULL, SYSTEM, HIDDEN};

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public static GameSettingsFullType resolveGameSettingsFullType(int count2) {
      return ENUM_VALUES2[count2 % ENUM_VALUES2.length];
   }

   private GameSettingsFullType(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   static {
      for (GameSettingsFullType entry : values()) {
               ENUM_VALUES2[entry.count] = entry;
            }
   }
}
