package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiStreamIndicator {
   private static final ResourceLocation locationStreamIndicator;
   private final Minecraft mc;
   private float streamAlpha = 1.0F;
   private int streamAlphaDelta = 1;

   static {
      String str = "textures/gui/stream_indicator.png";
      locationStreamIndicator = new ResourceLocation(str);
   }

   public GuiStreamIndicator(Minecraft mc2) {
      this.mc = mc2;
   }

   public void updateStreamAlpha() {
      this.streamAlpha = 1.0F;
      this.streamAlphaDelta = 1;
   }

   public void render(int n, int n2) {
   }
}
