package net.minecraft.client.renderer.entity;

import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.utils.player.SilentSlotManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;

public class RenderPlayer extends RendererLivingEntity {
   private boolean smallArms;

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((AbstractClientPlayer)entity, d, d2, d3, f, f2);
   }

   public RenderPlayer(RenderManager renderManager2, boolean flag) {
      super(renderManager2, new ModelPlayer(0.0F, flag), 0.5F);
      this.smallArms = flag;
      this.addLayer(new LayerBipedArmor(this));
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new LayerArrow(this));
      this.addLayer(new LayerDeadmau5Head(this));
      this.addLayer(new LayerCape(this));
      this.addLayer(new LayerCustomHead(this.getPlayerModel().bipedHead));
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((AbstractClientPlayer)entity, f);
   }

   public void renderOffsetLivingLabel2(AbstractClientPlayer clientPlayer, double d, double d2, double d3, String str, double d4, boolean flag) {
      double d5;
      double d6;
      if (ClientManagers.moduleManager.nameTags.isToggled()) {
         if (ClientManagers.moduleManager.nameTags.showExtraServerNametag.getValue()) {
            d5 = ClientManagers.moduleManager.nameTags.maximumRenderDistance.getValue();
            d6 = d5 * d5;
         } else {
            d5 = -100.0;
            d6 = -100.0;
         }
      } else {
         d5 = 64.0;
         d6 = 100.0;
      }

      if (d4 < d6) {
         Scoreboard worldScoreboard = clientPlayer.getWorldScoreboard();
         ScoreObjective objectiveInDisplaySlot = worldScoreboard.getObjectiveInDisplaySlot(2);
         if (objectiveInDisplaySlot != null) {
            super.renderOffsetLivingLabel(clientPlayer, d, d2, d3, worldScoreboard.getValueFromObjective(clientPlayer.getName(), objectiveInDisplaySlot).getScorePoints() + " " + objectiveInDisplaySlot.getDisplayName(), d5, false);
            if (ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.mode.getSelectedValue().equals("Custom")) {
               float renderPartialTicks2 = Minecraft.getMinecraft().getTimer().renderPartialTicks;
               double d7 = this.renderManager.getRenderPosX() - (clientPlayer.lastTickPosX + (clientPlayer.posX - clientPlayer.lastTickPosX) * (double)renderPartialTicks2);
               double d8 = this.renderManager.getRenderPosY() - (clientPlayer.lastTickPosY + (clientPlayer.posY - clientPlayer.lastTickPosY) * (double)renderPartialTicks2);
               double d9 = this.renderManager.getRenderPosZ() - (clientPlayer.lastTickPosZ + (clientPlayer.posZ - clientPlayer.lastTickPosZ) * (double)renderPartialTicks2);
               double d10 = d7 * d7 + d8 * d8 + d9 * d9;
               float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
               float f = (float)((double)(0.016666668F * value) + Math.sqrt(d10) / 850.0);
               float f2;
               if (ClientManagers.moduleManager.nameTags.customFontRenderer != null) {
                  f2 = ClientManagers.moduleManager.nameTags.customFontRenderer.getFontHeight();
               } else {
                  f2 = 9.5F;
               }

               d2 += (double)((f2 + 0.5F) * f);
            } else if (ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.mode.getSelectedValue().equals("Vanilla")) {
               float value2 = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
               float f3 = 0.016666668F * value2;
               d2 += (double)(9.5F * f3);
            } else {
               d2 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.02666667F);
            }
         }
      }

      super.renderOffsetLivingLabel(clientPlayer, d, d2, d3, str, d4, true);
   }

   public void renderLeftArm(AbstractClientPlayer clientPlayer) {
      float f = 1.0F;
      GlStateManager.color(f, f, f);
      ModelPlayer playerModel = this.getPlayerModel();
      this.setModelVisibilities(clientPlayer);
      playerModel.isSneak = false;
      playerModel.swingProgress = 0.0F;
      playerModel.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
      playerModel.renderLeftArm();
   }

   public void preRenderCallback(AbstractClientPlayer clientPlayer, float f) {
      float f2 = 0.9375F;
      GlStateManager.scale(f2, f2, f2);
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((AbstractClientPlayer)entity, f, f2, f3);
   }

   public void rotateCorpse(AbstractClientPlayer clientPlayer, float f, float f2, float f3) {
      if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping()) {
         GlStateManager.rotate(clientPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.getDeathMaxRotation(clientPlayer), 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else {
         super.rotateCorpse(clientPlayer, f, f2, f3);
      }
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((AbstractClientPlayer)entity);
   }

   public void renderRightArm(AbstractClientPlayer clientPlayer) {
      float f = 1.0F;
      GlStateManager.color(f, f, f);
      ModelPlayer playerModel = this.getPlayerModel();
      this.setModelVisibilities(clientPlayer);
      playerModel.swingProgress = 0.0F;
      playerModel.isRiding = playerModel.isSneak = false;
      playerModel.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
      playerModel.renderRightArm();
   }

   public RenderPlayer(RenderManager renderManager2) {
      this(renderManager2, false);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((AbstractClientPlayer)entity, d, d2, d3, f, f2);
   }

   @Override
   public void renderLivingAt(EntityLivingBase entity, double d, double d2, double d3) {
      this.renderLivingAt((AbstractClientPlayer)entity, d, d2, d3);
   }

   @Override
   public void renderOffsetLivingLabel(Entity entity, double d, double d2, double d3, String str, double d4, boolean flag) {
      this.renderOffsetLivingLabel2((AbstractClientPlayer)entity, d, d2, d3, str, d4, flag);
   }

   public ResourceLocation getEntityTexture(AbstractClientPlayer clientPlayer) {
      return clientPlayer.getLocationSkin();
   }

   public ModelPlayer getPlayerModel() {
      return (ModelPlayer)super.getMainModel();
   }

   public void renderLivingAt(AbstractClientPlayer clientPlayer, double d, double d2, double d3) {
      if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping()) {
         super.renderLivingAt(clientPlayer, d + (double)clientPlayer.renderOffsetX, d2 + (double)clientPlayer.renderOffsetY, d3 + (double)clientPlayer.renderOffsetZ);
      } else {
         super.renderLivingAt(clientPlayer, d, d2, d3);
      }
   }

   private void setModelVisibilities(AbstractClientPlayer clientPlayer) {
      ModelPlayer playerModel = this.getPlayerModel();
      if (clientPlayer.isSpectator()) {
         playerModel.setInvisible(false);
         playerModel.bipedHead.showModel = true;
         playerModel.bipedHeadwear.showModel = true;
      } else {
         ItemStack stack = clientPlayer instanceof EntityPlayerSP ? SilentSlotManager.heldItemStack : clientPlayer.inventory.getCurrentItem();
         playerModel.setInvisible(true);
         playerModel.bipedHeadwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.HAT);
         playerModel.bipedBodyWear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.JACKET);
         playerModel.bipedLeftLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
         playerModel.bipedRightLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
         playerModel.bipedLeftArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
         playerModel.bipedRightArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
         playerModel.heldItemLeft = 0;
         playerModel.aimedBow = false;
         playerModel.isSneak = clientPlayer.isSneaking();
         if (stack == null) {
            playerModel.heldItemRight = 0;
         } else {
            playerModel.heldItemRight = 1;
            if (clientPlayer.getItemInUseCount() > 0) {
               EnumAction itemUseAction = stack.getItemUseAction();
               if (itemUseAction == EnumAction.BLOCK) {
                  playerModel.heldItemRight = 3;
               } else if (itemUseAction == EnumAction.BOW) {
                  playerModel.aimedBow = true;
               }
            }
         }
      }
   }

   @Override
   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   @Override
   public ModelBase getMainModel() {
      return this.getPlayerModel();
   }

   public void doRender(AbstractClientPlayer clientPlayer, double d, double d2, double d3, float f, float f2) {
      if (!clientPlayer.isUser() || this.renderManager.livingPlayer == clientPlayer) {
         if (((Module)ClientManagers.moduleManager.brightPlayers).isToggled()) {
            GlStateManager.disableLighting();
         }

         double d4 = d2;
         if (clientPlayer.isSneaking() && !(clientPlayer instanceof EntityPlayerSP)) {
            d4 = d2 - 0.125;
         }

         this.setModelVisibilities(clientPlayer);
         super.doRender((EntityLivingBase)clientPlayer, d, d4, d3, f, f2);
      }
   }
}
