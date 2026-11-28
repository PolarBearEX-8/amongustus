package net.minecraft.client.renderer;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.ItemPosListener;
import net.augustus.events.EventItemPos;
import net.augustus.utils.player.SilentSlotManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.DynamicLights;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class ItemRenderer {
   private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
   private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
   private final Minecraft mc;
   private ItemStack itemToRender;
   private float equippedProgress;
   private float prevEquippedProgress;
   private final RenderManager renderManager;
   private final RenderItem itemRenderer;
   private int equippedItemSlot = -1;

   public void resetEquippedProgress2() {
      this.equippedProgress = 0.0F;
   }

   private void setLightMapFromPlayer(AbstractClientPlayer clientPlayer) {
      int combinedLight = this.mc
         .theWorld
         .getCombinedLight(MathHelper.floor_double(clientPlayer.posX), MathHelper.floor_double(clientPlayer.posY + (double)clientPlayer.getEyeHeight()), MathHelper.floor_double(clientPlayer.posZ), 0);
      if (Config.isDynamicLights()) {
         combinedLight = DynamicLights.getCombinedLight2(this.mc.getRenderViewEntity(), combinedLight);
      }

      float f = (float)(combinedLight & 65535);
      float f2 = (float)(combinedLight >> 16);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f2);
   }

   private void rotateWithPlayerRotations(EntityPlayerSP player, float f) {
      float f2 = player.prevRenderArmPitch + (player.renderArmPitch - player.prevRenderArmPitch) * f;
      float f3 = player.prevRenderArmYaw + (player.renderArmYaw - player.prevRenderArmYaw) * f;
      GlStateManager.rotate((player.rotationPitch - f2) * 0.1F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate((player.rotationYaw - f3) * 0.1F, 0.0F, 1.0F, 0.0F);
   }

   public void resetEquippedProgress() {
      this.equippedProgress = 0.0F;
   }

   public void renderOverlays(float f) {
      GlStateManager.disableAlpha();
      if (this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
         IBlockState blockState = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer));
         new BlockPos(this.mc.thePlayer);
         EntityPlayerSP thePlayer = this.mc.thePlayer;

         for (int i = 0; i < 8; i++) {
            double d = thePlayer.posX + (double)(((float)((i >> 0) % 2) - 0.5F) * thePlayer.width * 0.8F);
            double d2 = thePlayer.posY + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F);
            double d3 = thePlayer.posZ + (double)(((float)((i >> 2) % 2) - 0.5F) * thePlayer.width * 0.8F);
            BlockPos pos = new BlockPos(d, d2 + (double)thePlayer.getEyeHeight(), d3);
            IBlockState blockState2 = this.mc.theWorld.getBlockState(pos);
            if (blockState2.getBlock().isVisuallyOpaque()) {
               blockState = blockState2;
            }
         }

         if (blockState.getBlock().getRenderType() != -1) {
            this.renderBlockInHand(f, this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(blockState));
         }
      }

      if (!this.mc.thePlayer.isSpectator()) {
         if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
            this.renderWaterOverlayTexture(f);
         }

         if (this.mc.thePlayer.isBurning()) {
            this.renderFireInFirstPerson(f);
         }
      }

      GlStateManager.enableAlpha();
   }

   private void transformFirstPersonItem(float f, float f2) {
      if (ClientManagers.moduleManager.legacyAnimations.shouldPlayLegacySwing()) {
         GlStateManager.scale(0.85F, 0.85F, 0.85F);
         GlStateManager.translate(-0.078F, 0.003F, 0.05F);
      }

      if (ClientManagers.moduleManager.legacyAnimations.isLegacyItemHeldEnabled()) {
         GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
         GlStateManager.translate(0.0F, f * -0.6F, 0.0F);
         GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
      } else {
         GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
         GlStateManager.translate(0.0F, f * -0.6F, 0.0F);
         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      }

      float f3 = MathHelper.sin(f2 * f2 * 3.1415927F);
      float f4 = MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F);
      GlStateManager.rotate(f3 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f4 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(f4 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
   }

   private void doBlockTransformations() {
      if (ClientManagers.moduleManager.legacyAnimations.isLegacyItemHeldEnabled()) {
         GlStateManager.translate(-0.43F, 0.2F, 0.0F);
      } else {
         GlStateManager.translate(-0.5F, 0.2F, 0.0F);
      }

      GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
   }

   private void renderPlayerArm(AbstractClientPlayer clientPlayer, float f, float f2) {
      GlStateManager.translate(-0.3F * MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F), 0.4F * MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F * 2.0F), -0.4F * MathHelper.sin(f2 * 3.1415927F));
      GlStateManager.translate(0.64000005F, -0.6F, -0.71999997F);
      GlStateManager.translate(0.0F, f * -0.6F, 0.0F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float f6 = MathHelper.sin(f2 * f2 * 3.1415927F);
      GlStateManager.rotate(MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F) * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f6 * -20.0F, 0.0F, 0.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
      GlStateManager.translate(-1.0F, 3.6F, 3.5F);
      GlStateManager.rotate(120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(1.0F, 1.0F, 1.0F);
      GlStateManager.translate(5.6F, 0.0F, 0.0F);
      Render entityRenderObject = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
      GlStateManager.disableCull();
      ((RenderPlayer)entityRenderObject).renderRightArm(this.mc.thePlayer);
      GlStateManager.enableCull();
   }

   private void renderPlayerArms(AbstractClientPlayer clientPlayer) {
      this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
      RenderPlayer renderPlayer = (RenderPlayer)this.renderManager.getEntityRenderObject(this.mc.thePlayer);
      if (!clientPlayer.isInvisible()) {
         GlStateManager.disableCull();
         this.renderRightArm(renderPlayer);
         this.renderLeftArm(renderPlayer);
         GlStateManager.enableCull();
      }
   }

   private void rotateAroundXAndY(float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   private void renderLeftArm(RenderPlayer renderPlayer) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(-0.3F, -1.1F, 0.45F);
      renderPlayer.renderLeftArm(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

   public void updateEquippedItem() {
      this.prevEquippedProgress = this.equippedProgress;
      EntityPlayerSP thePlayer = this.mc.thePlayer;
      SilentSlotManager.heldItemStack = this.mc.thePlayer.inventoryContainer.getSlot(SilentSlotManager.realHotbarSlot + 36).getStack();
      ItemStack stack = SilentSlotManager.isSilentMode() ? SilentSlotManager.heldItemStack : thePlayer.inventory.getCurrentItem();
      boolean flag = false;
      if (this.itemToRender != null && stack != null) {
         if (!this.itemToRender.getIsItemStackEqual(stack)) {
            flag = true;
         }
      } else if (this.itemToRender == null && stack == null) {
         flag = false;
      } else {
         flag = true;
      }

      float f = 0.4F;
      float f3 = MathHelper.clamp_float((flag ? 0.0F : 1.0F) - this.equippedProgress, -f, f);
      this.equippedProgress += f3;
      if (this.equippedProgress < 0.1F) {
         this.itemToRender = stack;
         this.equippedItemSlot = thePlayer.inventory.currentItem;
         if (Config.isShaderPackLoaded()) {
            Shaders.setItemToRenderMain(stack);
         }

         this.itemToRender = stack;
         this.equippedItemSlot = thePlayer.inventory.currentItem;
      }
   }

   private float getMapAngleFromPitch(float f) {
      float f2 = 1.0F - f / 45.0F + 0.1F;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      return -MathHelper.cos(f2 * 3.1415927F) * 0.5F + 0.5F;
   }

   public void renderItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transformType) {
      if (stack != null) {
         Block blockFromItem = Block.getBlockFromItem(stack.getItem());
         GlStateManager.pushMatrix();
         if (this.itemRenderer.shouldRenderItemIn3D(stack)) {
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            if (this.isBlockTranslucent(blockFromItem) && (!Config.isShaderPackLoaded() || !Shaders.renderItemKeepDepthMask)) {
               GlStateManager.depthMask(false);
            }
         }

         this.itemRenderer.renderItemModelForEntity(stack, entity, transformType);
         if (this.isBlockTranslucent(blockFromItem)) {
            GlStateManager.depthMask(true);
         }

         GlStateManager.popMatrix();
      }
   }

   private void performDrinking(AbstractClientPlayer clientPlayer, float f) {
      float f2 = (float)clientPlayer.getItemInUseCount() - f + 1.0F;
      float f3 = f2 / (float)this.itemToRender.getMaxItemUseDuration();
      float f4 = MathHelper.abs(MathHelper.cos(f2 / 4.0F * 3.1415927F) * 0.1F);
      if (f3 >= 0.8F) {
         f4 = 0.0F;
      }

      GlStateManager.translate(0.0F, f4, 0.0F);
      float f5 = 1.0F - (float)Math.pow((double)f3, 27.0);
      GlStateManager.translate(f5 * 0.6F, f5 * -0.5F, f5 * 0.0F);
      GlStateManager.rotate(f5 * 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f5 * 10.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f5 * 30.0F, 0.0F, 0.0F, 1.0F);
   }

   public ItemRenderer(Minecraft mc2) {
      this.mc = mc2;
      this.renderManager = mc2.getRenderManager();
      this.itemRenderer = mc2.getRenderItem();
   }

   private void doItemUsedTransformations(float f) {
      GlStateManager.translate(-0.4F * MathHelper.sin(MathHelper.sqrt_float(f) * 3.1415927F), 0.2F * MathHelper.sin(MathHelper.sqrt_float(f) * 3.1415927F * 2.0F), -0.2F * MathHelper.sin(f * 3.1415927F));
   }

   private void renderWaterOverlayTexture(float f) {
      if (!Config.isShaderPackLoaded() || Shaders.isUnderwaterOverlay()) {
         this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         float brightness = this.mc.thePlayer.getBrightness(f);
         GlStateManager.color(brightness, brightness, brightness, 0.5F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.pushMatrix();
         float f2 = 4.0F;
         float f3 = -1.0F;
         float f4 = 1.0F;
         float f5 = -1.0F;
         float f6 = 1.0F;
         float f7 = -0.5F;
         float f8 = -this.mc.thePlayer.rotationYaw / 64.0F;
         float f9 = this.mc.thePlayer.rotationPitch / 64.0F;
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos(-1.0, -1.0, -0.5).tex((double)(4.0F + f8), (double)(4.0F + f9)).endVertex();
         worldRenderer.pos(1.0, -1.0, -0.5).tex((double)(0.0F + f8), (double)(4.0F + f9)).endVertex();
         worldRenderer.pos(1.0, 1.0, -0.5).tex((double)(0.0F + f8), (double)(0.0F + f9)).endVertex();
         worldRenderer.pos(-1.0, 1.0, -0.5).tex((double)(4.0F + f8), (double)(0.0F + f9)).endVertex();
         instance.draw();
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
      }
   }

   private void doBowTransformations(float f, AbstractClientPlayer clientPlayer) {
      GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-0.9F, 0.2F, 0.0F);
      float f2 = (float)this.itemToRender.getMaxItemUseDuration() - ((float)clientPlayer.getItemInUseCount() - f + 1.0F);
      float f3 = f2 / 20.0F;
      f3 = (f3 * f3 + f3 * 2.0F) / 3.0F;
      if (f3 > 1.0F) {
         f3 = 1.0F;
      }

      if (f3 > 0.1F) {
         float f6 = MathHelper.sin((f2 - 0.1F) * 1.3F) * (f3 - 0.1F);
         GlStateManager.translate(f6 * 0.0F, f6 * 0.01F, f6 * 0.0F);
      }

      GlStateManager.translate(f3 * 0.0F, f3 * 0.0F, f3 * 0.1F);
      GlStateManager.scale(1.0F, 1.0F, 1.0F + f3 * 0.2F);
   }

   public void renderItemInFirstPerson(float f) {
      if (!Config.isShaderPackLoaded() || !Shaders.isSkipRenderHandMain()) {
         float f2 = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * f);
         EntityPlayerSP thePlayer = this.mc.thePlayer;
         float swingProgress = thePlayer.getSwingProgress(f);
         float f3 = thePlayer.prevRotationPitch + (thePlayer.rotationPitch - thePlayer.prevRotationPitch) * f;
         this.rotateAroundXAndY(f3, thePlayer.prevRotationYaw + (thePlayer.rotationYaw - thePlayer.prevRotationYaw) * f);
         this.setLightMapFromPlayer(thePlayer);
         this.rotateWithPlayerRotations(thePlayer, f);
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         if (ClientManagers.moduleManager.customItemPos.isToggled() && ClientManagers.moduleManager.customItemPos.leftHand.getValue()) {
            GlStateManager.scale(-1.0, 1.0, 1.0);
            GlStateManager.disableCull();
         }

         if (this.itemToRender != null) {
            EventItemPos event = ItemPosListener.onItemPos(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0);
            boolean flag = thePlayer.getItemInUseCount() > 0;
            ItemStack currentEquippedItem = this.mc.thePlayer.getCurrentEquippedItem();
            if (currentEquippedItem != null
               && ((Module)ClientManagers.moduleManager.blockAnimation).isToggled()
               && ClientManagers.moduleManager.blockAnimation.fakeBlock.getValue()
               && (!ClientManagers.moduleManager.blockAnimation.onlyWhileKillAura.getValue() || ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null)
               && swingProgress > 0.0F
               && currentEquippedItem.getItem() instanceof ItemSword) {
               flag = true;
            }

            GlStateManager.translate(event.getPosX(), event.getPosY(), event.getPosZ());
            if (this.itemToRender.getItem() instanceof ItemMap) {
               this.renderItemMap(thePlayer, f3, f2, swingProgress);
            } else if (flag) {
               GlStateManager.translate(-event.getPosX(), -event.getPosY(), -event.getPosZ());
               GlStateManager.translate(-event.getBlockX(), -event.getBlockY(), -event.getBlockZ());
               EnumAction itemUseAction = this.itemToRender.getItemUseAction();
               switch (itemUseAction) {
                  case NONE:
                     this.transformFirstPersonItem(f2, 0.0F);
                     break;
                  case EAT:
                  case DRINK:
                     this.performDrinking((AbstractClientPlayer)thePlayer, f);
                     this.transformFirstPersonItem(f2, 0.0F);
                     break;
                  case BLOCK:
                     if (((Module)ClientManagers.moduleManager.blockAnimation).isToggled()) {
                        GlStateManager.translate(0.0, ClientManagers.moduleManager.blockAnimation.height.getValue(), 0.0);
                     }

                     if (((Module)ClientManagers.moduleManager.blockAnimation).isToggled()) {
                        switch (ClientManagers.moduleManager.blockAnimation.blockAnimations.getSelectedValue()) {
                           case "1.7":
                              this.transformFirstPersonItem(f2, swingProgress);
                              this.doBlockTransformations();
                              break;
                           case "Wiggle":
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              GlStateManager.rotate(-MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F) * 45.0F, 2.09F, 0.0F, 0.0F);
                              this.doBlockTransformations();
                              break;
                           case "Minimalistic":
                              float f6 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
                              GlStateManager.translate(0.0F, 0.0F, -f6 / 5.0F);
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              this.doBlockTransformations();
                              break;
                           case "Swong":
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              float f7 = MathHelper.sin((float)((double)MathHelper.sqrt_float(swingProgress) * 3.141592653589793));
                              GL11.glRotatef(-f7 * 80.0F / 5.0F, f7 / 3.0F, -0.0F, 9.0F);
                              GL11.glRotatef(-f7 * 40.0F, 8.0F, f7 / 9.0F, -0.1F);
                              this.doBlockTransformations();
                              break;
                           case "Jello":
                              float f8 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              GlStateManager.scale(0.8F, 0.8F, 0.8F);
                              GlStateManager.translate(0.0F, 0.1F, 0.0F);
                              this.doBlockTransformations();
                              GlStateManager.rotate(f8 * 35.0F / 2.0F, 0.0F, 1.0F, 1.5F);
                              GlStateManager.rotate(-f8 * 190.0F / 4.0F, 1.0F, 0.5F, 0.0F);
                              break;
                           case "Exhibition":
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              float f9 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
                              GlStateManager.rotate(-f9 * 36.0F, 2.09F, 0.0F, 0.0F);
                              GlStateManager.rotate(-f9 * 25.0F, 2.09F, 0.0F, 15.0F);
                              GlStateManager.translate(0.0, 0.3, 0.0);
                              this.doBlockTransformations();
                              break;
                           case "Forward":
                              this.transformFirstPersonItem(f2 / 3.0F, 0.0F);
                              GlStateManager.translate(0.0, 0.3, 0.0);
                              float f10 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
                              GlStateManager.rotate(-f10 * 36.0F, 2.09F, 0.0F, 0.0F);
                              GlStateManager.rotate(-f10 * 25.0F, 2.09F, 0.0F, 15.0F);
                              GlStateManager.rotate(-f10 * 16.0F, 12.09F, 3.0F, 0.0F);
                              GlStateManager.rotate(5.0F, 0.0F, -2.0F, 12.0F);
                              this.doBlockTransformations();
                              break;
                           case "Jigsaw":
                              this.transformFirstPersonItem(f2, swingProgress + f2);
                              this.doBlockTransformations();
                              GlStateManager.translate(0.1, 0.0, 0.0);
                              break;
                           case "Violence":
                              this.transformFirstPersonItem(0.0F, swingProgress);
                              this.doBlockTransformations();
                              GlStateManager.translate(-0.2F, 0.2F, -0.5F);
                           case "Sigma":
                              float f11 = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
                              this.transformFirstPersonItem(f2 * 0.5F, 0.0F);
                              GlStateManager.rotate(-f11 * 55.0F / 2.0F, -8.0F, -0.0F, 9.0F);
                              GlStateManager.rotate(-f11 * 45.0F, 1.0F, f11 / 2.0F, -0.0F);
                              this.doBlockTransformations();
                              GL11.glTranslated(1.2, 0.3, 0.5);
                              GL11.glTranslatef(-1.0F, -0.2F, 0.2F);
                              break;
                           case "Exhibition2":
                              float f12 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
                              this.transformFirstPersonItem(f2 / 2.5F, 0.0F);
                              GL11.glTranslatef(0.1F, 0.4F, -0.1F);
                              GL11.glRotated((double)(-f12 * 20.0F), (double)(f12 / 2.0F), 0.0, 9.0);
                              GL11.glRotated((double)(-f12 * 50.0F), 0.800000011920929, (double)(f12 / 2.0F), 0.0);
                              this.doBlockTransformations();
                              break;
                           case "Spin":
                              this.transformFirstPersonItem(f2, 0.0F);
                              this.doBlockTransformations();
                              GlStateManager.translate(-0.2, 1.0, 0.5);
                              GlStateManager.rotate(90.0F, 3.0F, 1.0F, 0.0F);
                              GlStateManager.rotate((float)Math.toDegrees((double)System.currentTimeMillis() / 200.0 % 6.283185307179586), 1.0F, 0.0F, 1.0F);
                           default:
                              this.transformFirstPersonItem(f2, 0.0F);
                              this.doBlockTransformations();
                        }
                     } else {
                        this.transformFirstPersonItem(f2, 0.0F);
                        this.doBlockTransformations();
                     }
                     break;
                  case BOW:
                     this.transformFirstPersonItem(f2, 0.0F);
                     this.doBowTransformations(f, thePlayer);
               }
            } else {
               this.doItemUsedTransformations(((Module)ClientManagers.moduleManager.swingAnimation).isToggled() ? 0.0F : swingProgress);
               this.transformFirstPersonItem(f2, swingProgress);
            }

            GlStateManager.scale(event.getScale(), event.getScale(), event.getScale());
            this.renderItem(thePlayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
         } else if (!thePlayer.isInvisible()) {
            this.renderPlayerArm(thePlayer, f2, swingProgress);
         }

         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
         RenderHelper.disableStandardItemLighting();
      }
   }

   private void renderBlockInHand(float f, TextureAtlasSprite textureAtlasSprite) {
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      float f2 = 0.1F;
      GlStateManager.color(0.1F, 0.1F, 0.1F, 0.5F);
      GlStateManager.pushMatrix();
      float f3 = -1.0F;
      float f4 = 1.0F;
      float f5 = -1.0F;
      float f6 = 1.0F;
      float f7 = -0.5F;
      float minU = textureAtlasSprite.getMinU();
      float maxU = textureAtlasSprite.getMaxU();
      float minV = textureAtlasSprite.getMinV();
      float maxV = textureAtlasSprite.getMaxV();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(-1.0, -1.0, -0.5).tex((double)maxU, (double)maxV).endVertex();
      worldRenderer.pos(1.0, -1.0, -0.5).tex((double)minU, (double)maxV).endVertex();
      worldRenderer.pos(1.0, 1.0, -0.5).tex((double)minU, (double)minV).endVertex();
      worldRenderer.pos(-1.0, 1.0, -0.5).tex((double)maxU, (double)minV).endVertex();
      instance.draw();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderFireInFirstPerson(float f) {
      if (!ClientManagers.moduleManager.customFireHeight.isToggled() || !ClientManagers.moduleManager.customFireHeight.removeFire.getValue()) {
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
         GlStateManager.depthFunc(519);
         GlStateManager.depthMask(false);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         float f2 = 1.0F;
         float f3 = ClientManagers.moduleManager.customFireHeight.isToggled() ? (float)ClientManagers.moduleManager.customFireHeight.fireHeight.getValue() : -0.3F;

         for (int i = 0; i < 2; i++) {
            GlStateManager.pushMatrix();
            TextureAtlasSprite atlasSprite = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            float minU = atlasSprite.getMinU();
            float maxU = atlasSprite.getMaxU();
            float minV = atlasSprite.getMinV();
            float maxV = atlasSprite.getMaxV();
            float f4 = (0.0F - f2) / 2.0F;
            float f5 = f4 + f2;
            float f6 = 0.0F - f2 / 2.0F;
            float f7 = f6 + f2;
            float f8 = -0.5F;
            GlStateManager.translate((float)(-(i * 2 - 1)) * 0.24F, f3, 0.0F);
            GlStateManager.rotate((float)(i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.setSprite(atlasSprite);
            worldRenderer.pos((double)f4, (double)f6, (double)f8).tex((double)maxU, (double)maxV).endVertex();
            worldRenderer.pos((double)f5, (double)f6, (double)f8).tex((double)minU, (double)maxV).endVertex();
            worldRenderer.pos((double)f5, (double)f7, (double)f8).tex((double)minU, (double)minV).endVertex();
            worldRenderer.pos((double)f4, (double)f7, (double)f8).tex((double)maxU, (double)minV).endVertex();
            instance.draw();
            GlStateManager.popMatrix();
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         GlStateManager.depthFunc(515);
      }
   }

   private boolean isBlockTranslucent(Block block) {
      return block != null && block.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT;
   }

   private void renderItemMap(AbstractClientPlayer clientPlayer, float f, float f2, float f3) {
      GlStateManager.translate(-0.4F * MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927F), 0.2F * MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927F * 2.0F), -0.2F * MathHelper.sin(f3 * 3.1415927F));
      float mapAngleFromPitch = this.getMapAngleFromPitch(f);
      GlStateManager.translate(0.0F, 0.04F, -0.72F);
      GlStateManager.translate(0.0F, f2 * -1.2F, 0.0F);
      GlStateManager.translate(0.0F, mapAngleFromPitch * -0.5F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(mapAngleFromPitch * -85.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      this.renderPlayerArms(clientPlayer);
      float f7 = MathHelper.sin(f3 * f3 * 3.1415927F);
      float f8 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927F);
      GlStateManager.rotate(f7 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f8 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(f8 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.38F, 0.38F, 0.38F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-1.0F, -1.0F, 0.0F);
      GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
      this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
      worldRenderer.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
      worldRenderer.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
      worldRenderer.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
      instance.draw();
      MapData mapData = Items.filled_map.getMapData(this.itemToRender, this.mc.theWorld);
      if (mapData != null) {
         this.mc.entityRenderer.getMapItemRenderer().renderMap(mapData, false);
      }
   }

   private void renderRightArm(RenderPlayer renderPlayer) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(54.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(64.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-62.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.25F, -0.85F, 0.75F);
      renderPlayer.renderRightArm(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

}
