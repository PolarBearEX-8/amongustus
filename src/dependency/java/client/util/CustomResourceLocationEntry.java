package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class CustomResourceLocationEntry {
   public static final ResourceLocation resourceLocation;
   public final Minecraft mc;
   public float factor = 1.0F;
   public int count = 1;

   public void updateState() {
      this.factor = 1.0F;
      this.count = 1;
   }

   public void applyIndex(int count2, int count3) {
   }

   public CustomResourceLocationEntry(Minecraft mc2) {
      this.mc = mc2;
   }

   static {
      String text = "textures/gui/stream_indicator.png";
      resourceLocation = new ResourceLocation(text);
   }
}
