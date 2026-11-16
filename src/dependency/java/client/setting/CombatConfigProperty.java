package client.setting;

public enum CombatConfigProperty {
    SKY(15),
    BLOCK(0);

   public final int defaultLightValue;

   private CombatConfigProperty(int count) {
      this.defaultLightValue = count;
   }


}
