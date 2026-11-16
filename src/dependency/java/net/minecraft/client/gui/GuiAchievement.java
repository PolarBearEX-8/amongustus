package net.minecraft.client.gui;

import client.gui.TexturesGuiIconsPngManager;
import client.util.AuxiliaryDuplicateStatIdManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiAchievement extends TexturesGuiIconsPngManager {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   public Minecraft mc;
   public int count2;
   public int count;
   public String labelText;
   public String labelText2;
   public AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter;
   public long timestamp;
   public RenderItem renderItem;
   public boolean enabled;

   public void renderElement() {
      if (this.auxiliaryDuplicateStatIdManagerAdapter != null && this.timestamp != 0L && Minecraft.getMinecraft().thePlayer != null) {
         double offset = (double)(Minecraft.getTimestamp() - this.timestamp) / 3000.0;
         if (!this.enabled) {
            if (offset < 0.0 || offset > 1.0) {
               this.timestamp = 0L;
               return;
            }
         } else if (offset > 0.5) {
            offset = 0.5;
         }

         this.renderElement2();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(false);
         double offset2 = offset * 2.0;
         if (offset2 > 1.0) {
            offset2 = 2.0 - offset2;
         }

         double offset3 = offset2 * 4.0;
         double offset4 = 1.0 - offset3;
         if (offset4 < 0.0) {
            offset4 = 0.0;
         }

         double offset5 = offset4 * offset4;
         double offset6 = offset5 * offset5;
         int count3 = this.count2 - 160;
         int count4 = 0 - (int)(offset6 * 36.0);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableTexture2D();
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.applyState3();
         this.processAction(count3, count4, 96, 202, 160, 32);
         if (this.enabled) {
            this.mc.fontRendererObj.applyText(this.labelText2, count3 + 30, count4 + 7, 120, -1);
         } else {
            this.mc.fontRendererObj.getIndex(this.labelText, count3 + 30, count4 + 7, -256);
            this.mc.fontRendererObj.getIndex(this.labelText2, count3 + 30, count4 + 18, -1);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.applyState3();
         GlStateManager.disableLighting();
         GlStateManager.applyState6();
         GlStateManager.applyState13();
         this.renderItem.renderItemIntoGUI(this.auxiliaryDuplicateStatIdManagerAdapter.itemStack, count3 + 8, count4 + 8);
         GlStateManager.applyState3();
         GlStateManager.depthMask(true);
         GlStateManager.disableDepth();
      }
   }

   public void applyAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2) {
      this.labelText = auxiliaryDuplicateStatIdManagerAdapter2.getIChatComponent().getString();
      this.labelText2 = auxiliaryDuplicateStatIdManagerAdapter2.getText();
      this.timestamp = Minecraft.getTimestamp() + 2500L;
      this.auxiliaryDuplicateStatIdManagerAdapter = auxiliaryDuplicateStatIdManagerAdapter2;
      this.enabled = true;
   }

   public void renderElement3() {
      this.auxiliaryDuplicateStatIdManagerAdapter = null;
      this.timestamp = 0L;
   }

   public void applyAuxiliaryDuplicateStatIdMa(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2) {
      this.labelText = StatCollector.getText("achievement.get", new Object[0]);
      this.labelText2 = auxiliaryDuplicateStatIdManagerAdapter2.getIChatComponent().getString();
      this.timestamp = Minecraft.getTimestamp();
      this.auxiliaryDuplicateStatIdManagerAdapter = auxiliaryDuplicateStatIdManagerAdapter2;
      this.enabled = false;
   }

   private void renderElement2() {
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      this.count2 = this.mc.displayWidth;
      this.count = this.mc.displayHeight;
      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      this.count2 = scaledResolution.getScaledWidth();
      this.count = scaledResolution.getScaledHeight();
      GlStateManager.glClear(256);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.glStateAction(0.0, (double)this.count2, (double)this.count, 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
   }

   public GuiAchievement(Minecraft mc2) {
      this.mc = mc2;
      this.renderItem = mc2.getRenderItem();
   }
}
