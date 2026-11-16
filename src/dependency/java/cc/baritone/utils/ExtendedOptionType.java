package cc.baritone.utils;

public enum ExtendedOptionType {
    MOJANG("https://authserver.mojang.com/", "https://sessionserver.mojang.com/"),
    THEALTENING("http://authserver.thealtening.com/", "http://sessionserver.thealtening.com/");

   public final String labelText2;
   public final String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   private ExtendedOptionType(String text, String text2) {
      this.labelText2 = text;
      this.labelText = text2;
   }


}
