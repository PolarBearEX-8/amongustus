package client.util;

import com.google.gson.JsonObject;

public class TargetPredictor {
   public float factor;
   public float factor2;
   public float factor3;
   public float factor4;
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;

   public TargetPredictor getTargetPredictor() {
      TargetPredictor targetPredictor = new TargetPredictor();
      targetPredictor.factor = this.factor;
      targetPredictor.factor2 = this.factor2;
      targetPredictor.factor3 = this.factor3;
      targetPredictor.factor4 = this.factor4;
      targetPredictor.enabled = this.enabled;
      targetPredictor.enabled2 = this.enabled2;
      targetPredictor.enabled3 = this.enabled3;
      return targetPredictor;
   }

   public JsonObject getJsonObject() {
      JsonObject jsonObject = new JsonObject();
      JsonPropertyGetter jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor5 = this.factor;
      String text = "Y";
      jsonPropertyGetter.applyText(text, factor5, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor6 = this.factor2;
      String text2 = "P";
      jsonPropertyGetter.applyText(text2, factor6, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor7 = this.factor3;
      String text3 = "F";
      jsonPropertyGetter.applyText(text3, factor7, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor8 = this.factor4;
      String text4 = "S";
      jsonPropertyGetter.applyText(text4, factor8, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled4 = this.enabled;
      String text5 = "SP";
      jsonPropertyGetter.applyText(text5, enabled4, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled5 = this.enabled2;
      String text6 = "J";
      jsonPropertyGetter.applyText(text6, enabled5, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled6 = this.enabled3;
      String text7 = "SN";
      jsonPropertyGetter.applyText(text7, enabled6, jsonObject);
      return jsonObject;
   }

   public void applyJsonObject(JsonObject jsonObject) {
      JsonPropertyGetter jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor5 = 1.0F;
      String text = "Y";
      this.factor = (Float)jsonPropertyGetter.getEntry(text, factor5, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor6 = 1.0F;
      String text2 = "P";
      this.factor2 = (Float)jsonPropertyGetter.getEntry(text2, factor6, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor7 = 1.0F;
      String text3 = "F";
      this.factor3 = (Float)jsonPropertyGetter.getEntry(text3, factor7, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter2();
      Float factor8 = 1.0F;
      String text4 = "S";
      this.factor4 = (Float)jsonPropertyGetter.getEntry(text4, factor8, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean flag = false;
      String text5 = "SP";
      this.enabled = (Boolean)jsonPropertyGetter.getEntry(text5, flag, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean flag2 = false;
      String text6 = "J";
      this.enabled2 = (Boolean)jsonPropertyGetter.getEntry(text6, flag2, jsonObject);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean flag3 = false;
      String text7 = "SN";
      this.enabled3 = (Boolean)jsonPropertyGetter.getEntry(text7, flag3, jsonObject);
   }

   public float getFactor() {
      return this.factor;
   }

   public void setFactor(float factor5) {
      this.factor = factor5;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void setFactor2(float factor5) {
      this.factor2 = factor5;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public void setFactor3(float factor5) {
      this.factor3 = factor5;
   }

   public float getFactor4() {
      return this.factor4;
   }

   public void setFactor4(float factor5) {
      this.factor4 = factor5;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled4) {
      this.enabled = enabled4;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setEnabled2(boolean enabled4) {
      this.enabled2 = enabled4;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void setEnabled3(boolean enabled4) {
      this.enabled3 = enabled4;
   }

   @Override
   public String toString() {
      return "Yaw: "
         + this.factor
         + ", Pitch: "
         + this.factor2
         + ", Forward: "
         + this.factor3
         + ", Strafe: "
         + this.factor4
         + ", Sprinting: "
         + this.enabled
         + ", Jump: "
         + this.enabled2
         + ", Sneak: "
         + this.enabled3;
   }
}
