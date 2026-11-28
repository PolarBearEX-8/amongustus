package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.render.RenderUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.MathHelper;

public class Model extends Module implements RenderListener, TickListener {
   public final DoubleValue xCoord;
   public final DoubleValue yCoord;
   public final DoubleValue scale;
   public final BooleanValue yawRot;
   private float currentYaw;
   private float previousYaw;

   public Model() {
       super("Model", "Model", new Color(252, 186, 3), Categorys.RENDER);
      this.xCoord = new DoubleValue(this, "X-Coord", 1.0, 0.0, 100.0, 2);
      this.yCoord = new DoubleValue(this, "Y-Coord", 8.0, 0.0, 100.0, 2);
      this.scale = new DoubleValue(this, "Scale", 30.0, 10.0, 100.0, 0);
      this.yawRot = new BooleanValue(this, "YawRot", true);
      this.currentYaw = 0.0F;
      this.previousYaw = 0.0F;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      this.previousYaw = this.currentYaw;
      this.currentYaw = mc.thePlayer.rotationYaw;
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      int n = (int)(1.0 * this.scale.getValue() / 2.0 + ((double)sr.getScaledWidth() - 1.0 * this.scale.getValue()) / 100.0 * this.xCoord.getValue());
      int n2 = (int)(2.0 * this.scale.getValue() + ((double)sr.getScaledHeight() - 2.0 * this.scale.getValue()) / 100.0 * this.yCoord.getValue());
      int value = (int)this.scale.getValue();
      EntityPlayerSP thePlayer = mc.thePlayer;
      float f2 = 0.0F;
      this.renderPlayerModel(n, n2, value, 0.0F, f2, thePlayer);
      RenderUtil.resetGlColorState();
   }

   private void renderPlayerModel(int n, int n2, int n3, float f, float f2, EntityPlayerSP player) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)n, (float)n2, 50.0F);
      GlStateManager.scale((float)(-n3), (float)n3, (float)n3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float renderYawOffset = player.renderYawOffset;
      float rotationYaw = player.rotationYaw;
      float rotationPitch = player.rotationPitch;
      float prevRotationYawHead = player.prevRotationYawHead;
      float rotationYawHead = player.rotationYawHead;
      float rotationPitchHead = player.rotationPitchHead;
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(f2 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      player.renderYawOffset = (float)Math.atan((double)(f / 40.0F)) * 20.0F;
      player.rotationYaw = 0.0F + (this.yawRot.getValue() ? MathHelper.clamp_float(this.previousYaw - this.currentYaw, -8.0F, 8.0F) : 0.0F);
      player.rotationPitch = 0.0F;
      player.rotationPitchHead = 0.0F;
      player.rotationYawHead = player.rotationYaw;
      player.prevRotationYawHead = player.rotationYaw;
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      float playerViewY = renderManager.playerViewY;
      renderManager.setPlayerViewY(180.0F);
      renderManager.setRenderShadow(false);
      player.setRenderNameTag(false);
      renderManager.renderEntityWithPosYaw(player, 0.0, 0.0, 0.0, 0.0F, 1.0F);
      player.setRenderNameTag(true);
      renderManager.setRenderShadow(true);
      renderManager.setPlayerViewY(playerViewY);
      player.renderYawOffset = renderYawOffset;
      player.rotationPitchHead = rotationPitchHead;
      player.rotationYaw = rotationYaw;
      player.rotationPitch = rotationPitch;
      player.prevRotationYawHead = prevRotationYawHead;
      player.rotationYawHead = rotationYawHead;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.popMatrix();
   }

}
