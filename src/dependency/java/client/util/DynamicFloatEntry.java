package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.setting.FactoryOptionType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class DynamicFloatEntry extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public float factor4 = 1.0F;
   public boolean enabled5;
   public FactoryOptionType factoryOptionType;
   public final float factor2;
   public final float factor3;

   @Override
   public void applyIndex(int count, int count2) {
      this.enabled5 = false;
   }

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.factor4 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
         this.factor4 = MathHelper.clamp_float(this.factor4, 0.0F, 1.0F);
         mc.gameSettings.applyFactoryOptionType(this.factoryOptionType, this.factoryOptionType.computeFactor2(this.factor4));
         this.labelText = mc.gameSettings.resolveTextByFactoryOptionTyp(this.factoryOptionType);
         this.enabled5 = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int computeCount(boolean flag) {
      return 0;
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         if (this.enabled5) {
            this.factor4 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
            this.factor4 = MathHelper.clamp_float(this.factor4, 0.0F, 1.0F);
            float factor = this.factoryOptionType.computeFactor2(this.factor4);
            mc.gameSettings.applyFactoryOptionType(this.factoryOptionType, factor);
            this.factor4 = this.factoryOptionType.computeFactor(factor);
            this.labelText = mc.gameSettings.resolveTextByFactoryOptionTyp(this.factoryOptionType);
         }

         mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.processAction(this.count + (int)(this.factor4 * (float)(this.count4 - 8)), this.count3, 0, 66, 4, 20);
         this.processAction(this.count + (int)(this.factor4 * (float)(this.count4 - 8)) + 4, this.count3, 196, 66, 4, 20);
      }
   }

   public DynamicFloatEntry(int count, int count2, int count3, FactoryOptionType factoryOptionType2, float factor, float factor5) {
      super(count, count2, count3, 150, 20, "");
      this.factoryOptionType = factoryOptionType2;
      this.factor2 = factor;
      this.factor3 = factor5;
      Minecraft mc = Minecraft.getMinecraft();
      this.factor4 = factoryOptionType2.computeFactor(mc.gameSettings.computeFactor(factoryOptionType2));
      this.labelText = mc.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType2);
   }

   public DynamicFloatEntry(int count, int count2, int count3, FactoryOptionType factoryOptionType2) {
      this(count, count2, count3, factoryOptionType2, 0.0F, 1.0F);
   }
}
