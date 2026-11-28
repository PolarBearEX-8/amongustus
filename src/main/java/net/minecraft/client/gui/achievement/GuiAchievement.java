package net.minecraft.client.gui.achievement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;

public class GuiAchievement extends Gui {
   private static final ResourceLocation achievementBg = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   private Minecraft mc;
   private int width;
   private int height;
   private String achievementTitle;
   private String achievementDescription;
   private Achievement theAchievement;
   private long notificationTime;
   private RenderItem renderItem;
   private boolean permanentNotification;

   public void clearAchievements() {
      this.theAchievement = null;
      this.notificationTime = 0L;
   }

   public void updateAchievementWindow() {
      if (this.theAchievement != null && this.notificationTime != 0L && Minecraft.getMinecraft().thePlayer != null) {
         double d = (double)(Minecraft.getSystemTime() - this.notificationTime) / 3000.0;
         if (!this.permanentNotification) {
            if (d < 0.0 || d > 1.0) {
               this.notificationTime = 0L;
               return;
            }
         } else if (d > 0.5) {
            d = 0.5;
         }

         this.updateAchievementWindowScale();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         double d2 = d * 2.0;
         if (d2 > 1.0) {
            d2 = 2.0 - d2;
         }

         d2 *= 4.0;
         d2 = 1.0 - d2;
         if (d2 < 0.0) {
            d2 = 0.0;
         }

         d2 *= d2;
         d2 *= d2;
         int n = this.width - 160;
         int n2 = 0 - (int)(d2 * 36.0);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         this.mc.getTextureManager().bindTexture(achievementBg);
         GlStateManager.disableLighting();
         this.drawTexturedModalRect(n, n2, 96, 202, 160, 32);
         if (this.permanentNotification) {
            this.mc.fontRendererObj.drawSplitString(this.achievementDescription, n + 30, n2 + 7, 120, -1);
         } else {
            this.mc.fontRendererObj.drawString(this.achievementTitle, n + 30, n2 + 7, -256);
            this.mc.fontRendererObj.drawString(this.achievementDescription, n + 30, n2 + 18, -1);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         this.renderItem.renderItemAndEffectIntoGUI(this.theAchievement.theItemStack, n + 8, n2 + 8);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(true);
         GlStateManager.enableDepth();
      }
   }

   public void displayUnformattedAchievement(Achievement achievement) {
      this.achievementTitle = achievement.getStatName().getUnformattedText();
      this.achievementDescription = achievement.getDescription();
      this.notificationTime = Minecraft.getSystemTime() + 2500L;
      this.theAchievement = achievement;
      this.permanentNotification = true;
   }

   public GuiAchievement(Minecraft mc2) {
      this.mc = mc2;
      this.renderItem = mc2.getRenderItem();
   }

   private void updateAchievementWindowScale() {
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      this.width = this.mc.displayWidth;
      this.height = this.mc.displayHeight;
      ScaledResolution sr = new ScaledResolution(this.mc);
      this.width = sr.getScaledWidth();
      this.height = sr.getScaledHeight();
      GlStateManager.clear(256);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.ortho(0.0, (double)this.width, (double)this.height, 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
   }

   public void displayAchievement(Achievement achievement) {
      this.achievementTitle = I18n.format("achievement.get", new Object[0]);
      this.achievementDescription = achievement.getStatName().getUnformattedText();
      this.notificationTime = Minecraft.getSystemTime();
      this.theAchievement = achievement;
      this.permanentNotification = false;
   }
}
