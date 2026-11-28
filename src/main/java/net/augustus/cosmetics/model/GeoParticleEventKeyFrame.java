package net.augustus.cosmetics.model;

public class GeoParticleEventKeyFrame extends GeoEventKeyFrame {
   public final String locator;
   public final String effect;
   public final String script;

   public String getEventDataString() {
      return (String)super.getEventData();
   }

   @Override
   public Object getEventData() {
      return this.getEventDataString();
   }

   public GeoParticleEventKeyFrame(Double d, String str, String str2, String str3) {
      super(d, str + "\n" + str2 + "\n" + str3);
      this.script = str3;
      this.locator = str2;
      this.effect = str;
   }
}
