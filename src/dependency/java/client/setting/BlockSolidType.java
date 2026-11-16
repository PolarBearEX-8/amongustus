package client.setting;

public enum BlockSolidType {
    SOLID("Solid"),
    CUTOUT_MIPPED("Mipped Cutout"),
    CUTOUT("Cutout"),
    TRANSLUCENT("Translucent");

   public final String labelText;

   private BlockSolidType(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
