package client.gui;

import client.setting.AutoPlayTargetEntity;
import client.util.GeometryEstimator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiButtonAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final AutoPlayTargetEntity autoPlayTargetEntity;
   public final String labelText2;
   public float factor2;
   public boolean enabled5;
   public final GeometryEstimator geometryEstimator;

   @Override
   public int computeCount(boolean flag) {
      return 0;
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         if (this.enabled5) {
            this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
            this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
            mc.gameSettings.updateAutoPlayTargetEntity(this.autoPlayTargetEntity, this.factor2);
            mc.gameSettings.updateState();
            this.labelText = this.labelText2 + ": " + this.geometryEstimator.resolveText(this.autoPlayTargetEntity);
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)), this.count3, 0, 66, 4, 20);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)) + 4, this.count3, 196, 66, 4, 20);
      }
   }

   @Override
   public void applyIndex(int count, int count2) {
      if (this.enabled5) {
         if (this.autoPlayTargetEntity != AutoPlayTargetEntity.MASTER) {
            GeometryEstimator.resolveGameSettings(this.geometryEstimator).getScaleFactor(this.autoPlayTargetEntity);
         }

         this.geometryEstimator.mc.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
      }

      this.enabled5 = false;
   }

   @Override
   public void applySoundHandler(SoundHandler soundHandler) {
   }

   public GuiButtonAuxiliaryTexturesGuiIc(GeometryEstimator geometryEstimator2, int count, int count2, int count3, AutoPlayTargetEntity autoPlayTargetEntity2, boolean flag) {
      super(count, count2, count3, flag ? 310 : 150, 20, "");
      this.geometryEstimator = geometryEstimator2;
      this.factor2 = 1.0F;
      this.autoPlayTargetEntity = autoPlayTargetEntity2;
      this.labelText2 = autoPlayTargetEntity2 == AutoPlayTargetEntity.AUGUSTUS ? "Augustus" : StatCollector.getText("soundCategory." + autoPlayTargetEntity2.getLabelText(), new Object[0]);
      this.labelText = this.labelText2 + ": " + geometryEstimator2.resolveText(autoPlayTargetEntity2);
      this.factor2 = GeometryEstimator.resolveGameSettings(geometryEstimator2).getScaleFactor(autoPlayTargetEntity2);
   }

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
         this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
         mc.gameSettings.updateAutoPlayTargetEntity(this.autoPlayTargetEntity, this.factor2);
         mc.gameSettings.updateState();
         this.labelText = this.labelText2 + ": " + this.geometryEstimator.resolveText(this.autoPlayTargetEntity);
         this.enabled5 = true;
         return true;
      } else {
         return false;
      }
   }
}
