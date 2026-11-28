package net.augustus.cosmetics.render;

import net.augustus.cosmetics.CosmeticTickListener;
import net.augustus.cosmetics.CosmeticsConfig;
import java.awt.Color;

public class CosmeticRainbowColorTicker implements CosmeticTickListener {
   private static final int[] RAINBOW_PALETTE = new int[360];
   public static int currentRainbowColor;

   public CosmeticRainbowColorTicker(short s, char ch, int n) {
      for (int i = 0; i < 360; i++) {
         RAINBOW_PALETTE[i] = Color.HSBtoRGB((float)i / 360.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public void onTick(int n) {
      currentRainbowColor = RAINBOW_PALETTE[n * CosmeticsConfig.getInstance().rainbowSpeed % 360];
   }
}
