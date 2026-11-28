package net.augustus.cosmetics;


import net.augustus.cosmetics.ui.CosmeticsColors;
public class CosmeticsConfig {
   public boolean hideWithArmor;
   public boolean cloaksEnabled;
   public boolean animationsEnabled;
   public int rainbowSpeed;
   public boolean cosmeticsEnabled = true;
   private static CosmeticsConfig instance = new CosmeticsConfig();
   public boolean debugEnabled;
   public int accentColor;
   public int maxCosmetics;
   public int renderDistance;

   private CosmeticsConfig() {
      this.cloaksEnabled = true;
      this.animationsEnabled = true;
      this.hideWithArmor = false;
      this.maxCosmetics = 10;
      this.rainbowSpeed = 5;
      this.renderDistance = 50;
      this.accentColor = CosmeticsColors.ACCENT_COLOR;
   }

   public static CosmeticsConfig getInstance() {
      return instance;
   }
}
