package client.util;

import net.minecraft.util.ResourceLocation;

public class SecondaryResourceLocationEntry {
   public ResourceLocation resourceLocation;
   public int count;
   public int count2;
   public int count3;
   public String labelText;

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public SecondaryResourceLocationEntry(String text, int count4, int count5, int count6) {
      this.labelText = text;
      this.count3 = count4;
      this.count2 = count5;
      this.count = count6;
      this.resourceLocation = new ResourceLocation("augustus", "emojifymod/" + this.labelText);
   }

   public SecondaryResourceLocationEntry(String text, int count4, int count5) {
      this.labelText = text;
      this.count3 = 0;
      this.count2 = count4;
      this.count = count5;
      this.resourceLocation = new ResourceLocation("augustus", "emojifymod/" + this.labelText);
   }

   @Override
   public String toString() {
      return this.count3 + "," + this.count2 + "," + this.count;
   }
}
