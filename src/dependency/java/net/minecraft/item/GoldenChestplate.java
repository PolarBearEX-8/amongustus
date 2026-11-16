package net.minecraft.item;

import client.ClientGlobals;
import client.event.PreMotionDispatcher;
import client.module.Module;
import client.render.RenderPlayer;
import client.setting.TabListCapeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GoldenChestplate implements PreMotionDispatcher {
   public final RenderPlayer renderPlayer;

   public void applyAbstractClientPlayer(AbstractClientPlayer player, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!((Module)ClientGlobals.moduleManager.child).isToggled() || !ClientGlobals.moduleManager.child.settingRemoveCapes.isEnabled()) {
         boolean flag = player.getText().equals(Minecraft.getMinecraft().thePlayer.getText()) && ((Module)ClientGlobals.moduleManager.customCape).isToggled();
         if (player.isActive28() && !player.isActive12() && (player.checkTabListCapeType(TabListCapeType.CAPE) && player.getResourceLocation3() != null || flag)) {
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            if (flag) {
               try {
                  if (ClientGlobals.moduleManager.customCape.settingType.getSelected().equals("LabyMod")) {
                     String selected = ClientGlobals.moduleManager.customCape.settingLabyModCapes.getSelected();
                     int count = (Integer)ClientGlobals.moduleManager.customCape.entryMap.get(selected);
                     if (count == 0) {
                        return;
                     }

                     GlStateManager.setActiveTexture(count);
                  } else {
                     ResourceLocation location = (ResourceLocation)ClientGlobals.moduleManager
                        .customCape
                        .entryMap2
                        .get(ClientGlobals.moduleManager.customCape.settingMojangCapes.getSelected());
                     if (location == null) {
                        return;
                     }

                     this.renderPlayer.applyResourceLocation2(location);
                  }
               } catch (Exception exception) {
                  return;
               }
            } else {
               ResourceLocation resourceLocation = player.getResourceLocation3();
               this.renderPlayer.applyResourceLocation2(resourceLocation);
            }

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.125F);
            double offset = player.value30 + (player.value25 - player.value30) * (double)posX3 - (player.prevPosX + (player.posX - player.prevPosX) * (double)posX3);
            double offset2 = player.value28 + (player.value27 - player.value28) * (double)posX3 - (player.prevPosY + (player.posY - player.prevPosY) * (double)posX3);
            double offset3 = player.value29 + (player.value26 - player.value29) * (double)posX3 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)posX3);
            float factor = player.factor12 + (player.scaleFactor8 - player.factor12) * posX3;
            double offset4 = (double)MathHelper.abs(factor * (float) Math.PI / 180.0F);
            double offset5 = (double)(-MathHelper.cos(factor * (float) Math.PI / 180.0F));
            float factor2 = MathHelper.clamp_float((float)offset2 * 10.0F, -6.0F, 32.0F);
            float factor3 = (float)(offset * offset4 + offset3 * offset5) * 100.0F;
            float factor4 = (float)(offset * offset5 - offset3 * offset4) * 100.0F;
            if (factor3 < 0.0F) {
               factor3 = 0.0F;
            }

            if (factor3 > 165.0F) {
               factor3 = 165.0F;
            }

            if (factor2 < -5.0F) {
               factor2 = -5.0F;
            }

            float factor5 = player.factor30 + (player.factor47 - player.factor30) * posX3;
            float factor6 = factor2 + MathHelper.abs((player.factor4 + (player.distanceWalkedModified - player.factor4) * posX3) * 6.0F) * 32.0F * factor5;
            if (player.isSprinting()) {
               factor6 += 25.0F;
               GlStateManager.translate(0.0F, 0.142F, -0.0178F);
            }

            GlStateManager.rotate(6.0F + factor3 / 2.0F + factor6, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(factor4 / 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-factor4 / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.renderPlayer.getAuxiliaryFloatEntryBridge().applyFloat2(0.0625F);
            GlStateManager.popMatrix();
         }
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public GoldenChestplate(RenderPlayer renderPlayer2) {
      this.renderPlayer = renderPlayer2;
   }
}
