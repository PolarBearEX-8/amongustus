package net.minecraft.client.gui.inventory;

import java.io.IOException;
import net.augustus.utils.player.InventoryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class GuiInventory extends InventoryEffectRenderer {
   private float oldMouseX;
   private float oldMouseY;

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 86, 16, 4210752);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
      }

      if (button.id == 1) {
         this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      if (this.mc.playerController.isInCreativeMode()) {
         this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
      } else {
         super.initGui();
      }

      ScaledResolution sr = new ScaledResolution(this.mc);
      InventoryUtil.setLastCursorPos((double)((float)sr.getScaledWidth() / 2.0F), (double)((float)sr.getScaledHeight() / 2.0F));
   }

   public static void drawEntityOnScreen(int n, int n2, int n3, float f, float f2, EntityLivingBase entity) {
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)n, (float)n2, 50.0F);
      GlStateManager.scale((float)(-n3), (float)n3, (float)n3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float renderYawOffset = entity.renderYawOffset;
      float rotationYaw = entity.rotationYaw;
      float rotationPitch = entity.rotationPitch;
      float prevRotationYawHead = entity.prevRotationYawHead;
      float rotationYawHead = entity.rotationYawHead;
      float rotationPitchHead = entity.rotationPitchHead;
      float prevRotationPitchHead = entity.prevRotationPitchHead;
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(f2 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      entity.renderYawOffset = (float)Math.atan((double)(f / 40.0F)) * 20.0F;
      entity.rotationYaw = (float)Math.atan((double)(f / 40.0F)) * 40.0F;
      entity.rotationPitch = entity.prevRotationPitchHead = entity.rotationPitchHead = -((float)Math.atan((double)(f2 / 40.0F))) * 20.0F;
      entity.rotationYawHead = entity.rotationYaw;
      entity.prevRotationYawHead = entity.rotationYaw;
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      renderManager.setPlayerViewY(180.0F);
      renderManager.setRenderShadow(false);
      renderManager.renderEntityWithPosYaw(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F);
      renderManager.setRenderShadow(true);
      entity.renderYawOffset = renderYawOffset;
      entity.rotationYaw = rotationYaw;
      entity.rotationPitch = rotationPitch;
      entity.prevRotationYawHead = prevRotationYawHead;
      entity.rotationYawHead = rotationYawHead;
      entity.rotationPitchHead = rotationPitchHead;
      entity.prevRotationPitchHead = prevRotationPitchHead;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.oldMouseX = (float)mouseX;
      this.oldMouseY = (float)mouseY;
   }

   @Override
   public void updateScreen() {
      if (this.mc.playerController.isInCreativeMode()) {
         this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
      }

      this.updateActivePotionEffects();
   }

   public GuiInventory(EntityPlayer player) {
      super(player.inventoryContainer);
      this.allowUserInput = true;
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(inventoryBackground);
      int guiLeft2 = this.guiLeft;
      int guiTop2 = this.guiTop;
      this.drawTexturedModalRect(guiLeft2, guiTop2, 0, 0, this.xSize, this.ySize);
      drawEntityOnScreen(guiLeft2 + 51, guiTop2 + 75, 30, (float)(guiLeft2 + 51) - this.oldMouseX, (float)(guiTop2 + 75 - 50) - this.oldMouseY, this.mc.thePlayer);
   }

}
