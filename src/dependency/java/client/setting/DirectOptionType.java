package client.setting;

public enum DirectOptionType {
    SCAFFOLD("Scaffold"),
    AUTOCLICKER("AutoClicker"),
    KILLAURA("KillAura");

   public final String labelText2;
   public static final DirectOptionType[] ENUM_VALUES = new DirectOptionType[]{KILLAURA, SCAFFOLD, AUTOCLICKER};

   public String getLabelText2() {
      return this.labelText2;
   }

   private DirectOptionType(String text) {
      this.labelText2 = text;
   }


}
