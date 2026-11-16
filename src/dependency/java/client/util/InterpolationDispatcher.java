package client.util;

import net.minecraft.client.Minecraft;

public class InterpolationDispatcher {
   public String labelText;
   public String labelText2;
   public long timestamp;

   public void updateState() {
      this.timestamp = Minecraft.getTimestamp();
   }

   public InterpolationDispatcher(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
      this.timestamp = Minecraft.getTimestamp();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
