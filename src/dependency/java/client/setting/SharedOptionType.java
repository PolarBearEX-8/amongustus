package client.setting;

public enum SharedOptionType {
    NONE(""),
    SHADOW("shadow"),
    GBUFFERS("gbuffers"),
    DEFERRED("deferred"),
    COMPOSITE("composite");

   public String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   private SharedOptionType(String text) {
      this.labelText = text;
   }


}
