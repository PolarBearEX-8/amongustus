package net.minecraft.client.renderer.entity.layers;

import net.augustus.ClientManagers;
import net.augustus.render.WaveyCapeRenderer;
import net.augustus.render.cape.WaveyCapeConfig;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerCape implements LayerRenderer {
   private final RenderPlayer playerRenderer;
   private WaveyCapeRenderer waveyCapeRenderer;

   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((AbstractClientPlayer)entity, f, f2, f3, f4, f5, f6, f7);
   }

   public void doRenderLayer(AbstractClientPlayer clientPlayer, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (!ClientManagers.moduleManager.child.isToggled() || !ClientManagers.moduleManager.child.removeCapes.getValue()) {
         boolean flag = clientPlayer.getName().equals(Minecraft.getMinecraft().thePlayer.getName()) && ClientManagers.moduleManager.customCape.isToggled();
         if (clientPlayer.hasPlayerInfo() && !clientPlayer.isInvisible() && (clientPlayer.isWearing(EnumPlayerModelParts.CAPE) && clientPlayer.getLocationCape() != null || flag)) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            if (flag) {
               try {
                  if (ClientManagers.moduleManager.customCape.type.getSelectedValue().equals("LabyMod")) {
                     String selectedValue = ClientManagers.moduleManager.customCape.labyModCapes.getSelectedValue();
                     int n = (Integer)ClientManagers.moduleManager.customCape.customCapeTextures.get(selectedValue);
                     if (n == 0) {
                        return;
                     }

                     GlStateManager.bindTexture(n);
                  } else {
                     ResourceLocation location = (ResourceLocation)ClientManagers.moduleManager.customCape.mojangCapeResources.get(ClientManagers.moduleManager.customCape.mojangCapes.getSelectedValue());
                     if (location == null) {
                        return;
                     }

                     this.playerRenderer.bindTexture(location);
                  }
               } catch (Exception e) {
                  return;
               }
            } else {
               ResourceLocation locationCape = clientPlayer.getLocationCape();
               this.playerRenderer.bindTexture(locationCape);
            }

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.125F);
            double d = clientPlayer.prevChasingPosX + (clientPlayer.chasingPosX - clientPlayer.prevChasingPosX) * (double)f3 - (clientPlayer.prevPosX + (clientPlayer.posX - clientPlayer.prevPosX) * (double)f3);
            double d2 = clientPlayer.prevChasingPosY + (clientPlayer.chasingPosY - clientPlayer.prevChasingPosY) * (double)f3 - (clientPlayer.prevPosY + (clientPlayer.posY - clientPlayer.prevPosY) * (double)f3);
            double d3 = clientPlayer.prevChasingPosZ + (clientPlayer.chasingPosZ - clientPlayer.prevChasingPosZ) * (double)f3 - (clientPlayer.prevPosZ + (clientPlayer.posZ - clientPlayer.prevPosZ) * (double)f3);
            float f8 = clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * f3;
            double d4 = (double)MathHelper.sin(f8 * 3.1415927F / 180.0F);
            double d5 = (double)(-MathHelper.cos(f8 * 3.1415927F / 180.0F));
            float f9 = (float)d2 * 10.0F;
            f9 = MathHelper.clamp_float(f9, -6.0F, 32.0F);
            float f10 = (float)(d * d4 + d3 * d5) * 100.0F;
            float f11 = (float)(d * d5 - d3 * d4) * 100.0F;
            if (f10 < 0.0F) {
               f10 = 0.0F;
            }

            if (f10 > 165.0F) {
               f10 = 165.0F;
            }

            if (f9 < -5.0F) {
               f9 = -5.0F;
            }

            float f12 = clientPlayer.prevCameraYaw + (clientPlayer.cameraYaw - clientPlayer.prevCameraYaw) * f3;
            f9 += MathHelper.sin((clientPlayer.prevDistanceWalkedModified + (clientPlayer.distanceWalkedModified - clientPlayer.prevDistanceWalkedModified) * f3) * 6.0F) * 32.0F * f12;
            if (clientPlayer.isSneaking()) {
               f9 += 25.0F;
               GlStateManager.translate(0.0F, 0.142F, -0.0178F);
            }

            if (flag && Augustus.getInstance().getModuleManager().customCape.capePhysics.getValue()) {
               if (this.waveyCapeRenderer == null) {
                  this.waveyCapeRenderer = new WaveyCapeRenderer(this.playerRenderer, this.playerRenderer.getPlayerModel());
               }

               WaveyCapeConfig waveyCapeConfig = ClientManagers.moduleManager.customCape.getWaveyCapeConfig();
               boolean isLightingEnabled = GlStateManager.isLightingEnabled();
               GlStateManager.disableLighting();
               GlStateManager.translate(0.0F, 0.0F, -0.125F);
               this.waveyCapeRenderer.renderCape(clientPlayer, f3, waveyCapeConfig);
               if (isLightingEnabled) {
                  GlStateManager.enableLighting();
               }
            } else {
               GlStateManager.rotate(6.0F + f10 / 2.0F + f9, 1.0F, 0.0F, 0.0F);
               GlStateManager.rotate(f11 / 2.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.rotate(-f11 / 2.0F, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               this.playerRenderer.getPlayerModel().renderCape(0.0625F);
            }

            GlStateManager.popMatrix();
         }
      }
   }

   public LayerCape(RenderPlayer renderPlayer) {
      this.playerRenderer = renderPlayer;
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
