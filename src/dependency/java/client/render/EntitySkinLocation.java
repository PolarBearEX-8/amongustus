package client.render;


import net.minecraft.client.entity.AbstractClientPlayer;
import client.ClientGlobals;
import client.event.EventEntitySyncCallback;
import client.event.PreMotionConsumer;
import client.event.PreRenderDispatcher;
import client.module.Module;
import client.setting.BlockSolidType;
import client.util.BuildManager;
import client.util.CoreResourceLocationEntry;
import client.util.DefaultVertexFormats;
import client.util.EnchantmentManager;
import client.util.FloatEntryRegistry;
import client.util.ListenerAdapter27;
import client.util.LvlManager;
import client.util.MinecraftAccessor;
import client.util.MovementHandler;
import client.util.PreEndHandManager;
import client.util.ServiceStringEntry;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public abstract class EntitySkinLocation implements PreRenderDispatcher {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/misc/shadow.png");
   public final RenderManager renderManager;
   public float factor;
   public float factor2 = 1.0F;
   public Class memberRef = null;
   public ResourceLocation resourceLocation = null;

   public void applyEntity(Entity entity, String text, double posX, double posY, double posZ, int count) {
      if (entity.getCoordinate(this.renderManager.entity) <= (double)(count * count)) {
         FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
         float factor3 = 1.6F;
         float factor4 = 0.016666668F * factor3;
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)posX + 0.0F, (float)posY + entity.height + 0.5F, (float)posZ);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate3f3(-factor4, -factor4, factor4);
         GlStateManager.applyState3();
         GlStateManager.depthMask(false);
         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         byte byteValue = 0;
         if (text.equals("deadmau5")) {
            byteValue = -10;
         }

         int count2 = fontRendererAsFontRenderer.getIndex(text) / 2;
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState();
         }

         GlStateManager.enableTexture2D();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
         worldRenderer.getWorldRenderer((double)(-count2 - 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
         worldRenderer.getWorldRenderer((double)(-count2 - 1), (double)(8 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
         worldRenderer.getWorldRenderer((double)(count2 + 1), (double)(8 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
         worldRenderer.getWorldRenderer((double)(count2 + 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
         tessellator.updateState();
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState2();
         }

         GlStateManager.disableTexture2D();
         fontRendererAsFontRenderer.getIndex(text, -fontRendererAsFontRenderer.getIndex(text) / 2, byteValue, 553648127);
         GlStateManager.disableDepth();
         GlStateManager.depthMask(true);
         fontRendererAsFontRenderer.getIndex(text, -fontRendererAsFontRenderer.getIndex(text) / 2, byteValue, -1);
         GlStateManager.applyState13();
         GlStateManager.enableBlend();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private void applyEntityByEntity(Entity entity, double posX, double posY, double posZ, float factor3) {
      GlStateManager.applyState3();
      ListenerAdapter27 listenerAdapter = Minecraft.getMinecraft().getListenerAdapter27();
      ServiceStringEntry serviceStringEntry = listenerAdapter.get("minecraft:blocks/fire_layer_0");
      ServiceStringEntry serviceStringEntry2 = listenerAdapter.get("minecraft:blocks/fire_layer_1");
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      float factor4 = entity.width * 1.4F;
      GlStateManager.translate3f3(factor4, factor4, factor4);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      float factor5 = 0.5F;
      float factor6 = 0.0F;
      float factor7 = entity.height / factor4;
      float factor8 = (float)(entity.posY - entity.getBoundingBox2().minY);
      GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, -0.3F + (float)((int)factor7) * 0.02F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      float factor9 = 0.0F;
      int count = 0;
      boolean active = BuildManager.isActive46();
      if (active) {
         worldRenderer.applyBlockSolidType(BlockSolidType.SOLID);
      }

      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);

      while (factor7 > 0.0F) {
         ServiceStringEntry serviceStringEntry3 = count % 2 == 0 ? serviceStringEntry : serviceStringEntry2;
         worldRenderer.applyServiceStringEntryByServiceStringEnt(serviceStringEntry3);
         this.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         float factor10 = serviceStringEntry3.getFactor5();
         float factor11 = serviceStringEntry3.getFactor();
         float factor12 = serviceStringEntry3.getFactor2();
         float factor13 = serviceStringEntry3.getFactor6();
         if (count / 2 % 2 == 0) {
            float factor14 = factor12;
            factor12 = factor10;
            factor10 = factor14;
         }

         worldRenderer.getWorldRenderer((double)(factor5 - factor6), (double)(0.0F - factor8), (double)factor9).getWorldRenderer6((double)factor12, (double)factor13).updateState();
         worldRenderer.getWorldRenderer((double)(-factor5 - factor6), (double)(0.0F - factor8), (double)factor9).getWorldRenderer6((double)factor10, (double)factor13).updateState();
         worldRenderer.getWorldRenderer((double)(-factor5 - factor6), (double)(1.4F - factor8), (double)factor9).getWorldRenderer6((double)factor10, (double)factor11).updateState();
         worldRenderer.getWorldRenderer((double)(factor5 - factor6), (double)(1.4F - factor8), (double)factor9).getWorldRenderer6((double)factor12, (double)factor11).updateState();
         factor7 -= 0.45F;
         factor8 -= 0.45F;
         factor5 *= 0.9F;
         factor9 += 0.03F;
         count++;
      }

      tessellator.updateState();
      if (active) {
         worldRenderer.applyBlockSolidType(null);
         GlStateManager.bindTexture();
      }

      GlStateManager.popMatrix();
      GlStateManager.applyState13();
   }

   private void applyEntity3(Entity entity, String text, double posX, double posY, double posZ, int count, RenderPass renderPass) {
      this.applyEntityByEntity(entity, text, posX, posY, posZ, count, renderPass);
   }

   @Override
   public Class getMemberRef() {
      return this.memberRef;
   }

   @Override
   public void setResourceLocation(ResourceLocation resourceLocation3) {
      this.resourceLocation = resourceLocation3;
   }

   public void applyEntity2(Entity entity, double posX, double posY, double posZ, float factor3, float factor4) {
   }

   public void applyEntity(Entity entity, double posX, double posY, double posZ) {
      if (this.matchesEntityByEntity(entity) && !ClientGlobals.moduleManager.antiStrike.canExecute9() && entity.isState1()) {
         this.applyEntity(entity, entity.getChatComponent().getString2(), posX, posY, posZ, 64);
      }
   }

   public FontRenderer getFontRendererAsFontRenderer() {
      return this.renderManager.getFontRenderer();
   }

   public void applyResourceLocation2(ResourceLocation location) {
      this.renderManager.textureManager.applyResourceLocation3(location);
   }

   public void applyEntityByEntity(Entity entity, String text, double posX, double posY, double posZ, int count, RenderPass renderPass) {
      if (entity.getCoordinate(this.renderManager.entity) <= (double)(count * count)) {
         FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
         float value = (float)ClientGlobals.moduleManager.nameTags.settingScale.getValue();
         float factor3 = 0.016666668F * value;
         GlStateManager.pushMatrix();
         GlStateManager.translate3d2(
            (double)((float)posX + 0.0F),
            (double)((float)posY + entity.height + 0.5F) + ClientGlobals.moduleManager.nameTags.settingHeight.getValue(),
            (double)((float)posZ)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.indexOffset64 == 2 ? -this.renderManager.scaleFactor7 : this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.translate3f3(-factor3, -factor3, factor3);
         GlStateManager.applyState3();
         GlStateManager.depthMask(false);
         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         byte byteValue = 0;
         if (text.equals("deadmau5")) {
            byteValue = -10;
         }

         int count2 = fontRendererAsFontRenderer.getIndex(text) / 2;
         GlStateManager.enableTexture2D();
         if (renderPass != RenderPass.NONE && !ClientGlobals.moduleManager.nameTags.settingShadowFont.isEnabled()
            || !ClientGlobals.moduleManager.nameTags.settingTransparent.isEnabled()) {
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer((double)(-count2 - 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
            worldRenderer.getWorldRenderer((double)(-count2 - 1), (double)(8 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
            worldRenderer.getWorldRenderer((double)(count2 + 1), (double)(8 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
            worldRenderer.getWorldRenderer((double)(count2 + 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
            tessellator.updateState();
         }

         GlStateManager.disableTexture2D();
         fontRendererAsFontRenderer.computeCount(
            text,
            (float)(-fontRendererAsFontRenderer.getIndex(text)) / 2.0F,
            (float)byteValue,
            ClientGlobals.moduleManager.nameTags.settingThroughWalls.isEnabled() ? -1 : 553648127,
            false
         );
         this.applyEntity3(entity);
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.applyState3();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(true);
         this.applyEntityByEntity(entity);
         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         GlStateManager.popMatrix();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(true);
         fontRendererAsFontRenderer.computeCount(text, (float)(-fontRendererAsFontRenderer.getIndex(text)) / 2.0F, (float)byteValue, -1, false);
         this.applyEntity3(entity);
         GlStateManager.applyState13();
         GlStateManager.enableBlend();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private World getWorld() {
      return this.renderManager.world;
   }

   public void applyEntity(Entity entity, double posX, double posY, double posZ, String text, float factor3, double offset) {
      String text2 = text;
      if (((Module)ClientGlobals.moduleManager.nameTags).isToggled() && entity instanceof EntityPlayer) {
         if (!ClientGlobals.moduleManager.nameTags.settingHealthMode.getSelected().equals("None") && text != null && !text.equals("§r§r")) {
            StringBuilder builder = new StringBuilder().append(text).append(" §c");
            EntityPlayer player = (EntityPlayer)entity;
            text2 = builder.append((int)ClientGlobals.moduleManager.nameTags.getScaleFactor(player)).append("❤").toString();
         }

         if (((Module)ClientGlobals.moduleManager.nameTags).isToggled() && ClientGlobals.moduleManager.nameTags.settingPing.isEnabled()) {
            AbstractClientPlayer player2 = (AbstractClientPlayer)entity;
            int index = ClientGlobals.moduleManager.nameTags.getIndex(player2);
            if (index >= 0) {
               text2 = text2 + "§6 " + index + "ms";
            }
         }

         searchBlock: {
            String selected = ClientGlobals.moduleManager.nameTags.settingMode.getSelected();
            byte byteValue = -1;
            switch (selected.hashCode()) {
               case 1897755483:
                  if (selected.equals("Vanilla")) {
                     break searchBlock;
                  }
                  break;
               case 2029746065:
                  if (selected.equals("Custom")) {
                     byteValue = 1;
                  }
            }

            switch (byteValue) {
               case 0:
                  break;
               case 1:
                  this.applyEntity4(entity, text2, posX, posY, posZ, (int)ClientGlobals.moduleManager.nameTags.settingMaximumRenderDistance.getValue());
                  return;
               default:
                  return;
            }
         }

         this.applyEntity2(entity, text2, posX, posY, posZ, (int)ClientGlobals.moduleManager.nameTags.settingMaximumRenderDistance.getValue());
      } else {
         this.applyEntity(entity, text, posX, posY, posZ, 64);
      }
   }

   private void applyEntityByEntity(Entity entity, String text, int count, double posX, double posY, double posZ, RenderPass renderPass) {
      this.applyEntityByEntity(entity, text, (double)count, posX, posY, posZ, renderPass);
   }

   @Override
   public void setMemberRef(Class memberRef2) {
      this.memberRef = memberRef2;
   }

   public boolean matchesEntityByEntity(Entity entity) {
      return entity.isActive13() && entity.processAction();
   }

   public RenderManager getRenderManager() {
      return this.renderManager;
   }

   public void applyEntityByEntity(Entity entity, String text, double posX, double posX2, double posY, double posZ, RenderPass renderPass) {
      double coordinate = entity.getCoordinate(this.renderManager.entity);
      boolean flag = entity instanceof EntityPlayer && ((Module)ClientGlobals.moduleManager.nameTags).isToggled();
      if (coordinate <= posX * posX || flag) {
         FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
         float value = (float)ClientGlobals.moduleManager.nameTags.settingScale.getValue();
         float factor3 = (float)((double)(0.016666668F * value) + Math.sqrt(coordinate) / 850.0);
         GlStateManager.pushMatrix();
         GlStateManager.translate3d2(
            (double)((float)posX2 + 0.0F),
            (double)((float)posY + entity.height + 0.5F + factor3 * 8.0F - 0.016666668F * value * 8.0F)
               + ClientGlobals.moduleManager.nameTags.settingHeight.getValue(),
            (double)((float)posZ)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.indexOffset64 == 2 ? -this.renderManager.scaleFactor7 : this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.translate3f3(-factor3, -factor3, factor3);
         GlStateManager.applyState3();
         GlStateManager.depthMask(false);
         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         byte byteValue = 0;
         if (text.equals("deadmau5")) {
            byteValue = -10;
         }

         int count = fontRendererAsFontRenderer.getIndex(text) / 2;
         GlStateManager.enableTexture2D();
         if (renderPass != RenderPass.NONE && !ClientGlobals.moduleManager.nameTags.settingShadowFont.isEnabled()
            || !ClientGlobals.moduleManager.nameTags.settingTransparent.isEnabled()) {
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer((double)(-count - 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.5F).updateState();
            worldRenderer.getWorldRenderer((double)(-count - 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.5F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.5F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.5F).updateState();
            tessellator.updateState();
            GL11.glLineWidth(1.0E-4F);
            worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer((double)(-count - 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(-count - 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(-count - 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(-count - 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), (double)(-1 + byteValue), 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            worldRenderer.getWorldRenderer((double)(count + 1), 8.5 + (double)byteValue, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 1.0F).updateState();
            tessellator.updateState();
         }

         GlStateManager.disableTexture2D();
         fontRendererAsFontRenderer.computeCount(
            text,
            (float)(-fontRendererAsFontRenderer.getIndex(text)) / 2.0F,
            (float)byteValue,
            ClientGlobals.moduleManager.nameTags.settingThroughWalls.isEnabled() ? -1 : 553648127,
            false
         );
         this.applyEntity3(entity);
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.applyState3();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(true);
         this.applyEntityByEntity(entity);
         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         GlStateManager.popMatrix();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(true);
         fontRendererAsFontRenderer.computeCount(text, (float)(-fontRendererAsFontRenderer.getIndex(text)) / 2.0F, (float)byteValue, -1, false);
         this.applyEntity3(entity);
         GlStateManager.applyState13();
         GlStateManager.enableBlend();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private void applyEntity3(Entity entity) {
      int count = 0;
      if (entity instanceof EntityPlayer
         && ClientGlobals.moduleManager.nameTags.settingArmorEnchantments.isEnabled()
         && ClientGlobals.moduleManager.nameTags.settingArmor.isEnabled()) {
         EntityPlayer player = (EntityPlayer)entity;
         int count2 = this.computeCount(player.getHeldItem(), -45, -30);
         if (count2 > count) {
            count = count2;
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         int count3 = this.computeCount(player.getItemStack3(3), -27, -30);
         if (count3 > count) {
            count = count3;
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         int count4 = this.computeCount(player.getItemStack3(2), -9, -30);
         if (count4 > count) {
            count = count4;
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         int count5 = this.computeCount(player.getItemStack3(1), 9, -30);
         if (count5 > count) {
            count = count5;
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         int count6 = this.computeCount(player.getItemStack3(0), 27, -30);
         if (count6 > count) {
            count = count6;
         }
      }

      if (entity instanceof EntityPlayer && ClientGlobals.moduleManager.nameTags.settingPotionEffects.isEnabled()) {
         double offset = 0.75;
         int count7 = (int)((double)(count * -10) / offset - (double)(ClientGlobals.moduleManager.nameTags.settingArmor.isEnabled() ? 58 : 30));
         GlStateManager.translate3d(offset, offset, 1.0);

         for (Object potionEffectObj : ((EntityPlayer)entity).getCollection()) { PotionEffect potionEffect = (PotionEffect)potionEffectObj;
            if (potionEffect.getIndexOffset() > 0) {
               Potion potion = Potion.potionArray[potionEffect.getIndexOffset3()];
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               String text = StatCollector.getText(potion.getText(), new Object[0]);
               if (potionEffect.getIndexOffset2() == 1) {
                  text = text + " " + StatCollector.getText("enchantment.level.2", new Object[0]);
               } else if (potionEffect.getIndexOffset2() == 2) {
                  text = text + " " + StatCollector.getText("enchantment.level.3", new Object[0]);
               } else if (potionEffect.getIndexOffset2() == 3) {
                  text = text + " " + StatCollector.getText("enchantment.level.4", new Object[0]);
               }

               int count8 = -((MinecraftAccessor.minecraft.fontRendererObj.getIndex(text) + 23) / 2);
               if (potion.canExecute()) {
                  MinecraftAccessor.minecraft.getTextureManager().applyResourceLocation3(GuiInventory.resourceLocation4);
                  int indexOffset = potion.getIndexOffset();
                  MinecraftAccessor.minecraft.ingameGUI.processAction(count8, count7 + 7, 0 + indexOffset % 8 * 18, 198 + indexOffset / 8 * 18, 18, 18);
               }

               MinecraftAccessor.minecraft.fontRendererObj.getIndex2(text, (float)(count8 + 10 + 12), (float)(count7 + 6), 16777215);
               String text2 = Potion.getText(potionEffect);
               MinecraftAccessor.minecraft.fontRendererObj.getIndex2(text2, (float)(count8 + 10 + 12), (float)(count7 + 6 + 10), 8355711);
               count7 -= 25;
            }
         }

         GlStateManager.translate3d(1.0 / offset, 1.0 / offset, 1.0);
      }
   }

   public void applyEntity4(Entity entity, String text, double posX, double posY, double posZ, int count) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(2929);
      GL11.glNormal3d(0.0, 1.0, 0.0);
      GlStateManager.applyState3();
      GlStateManager.disableBlend();
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      MovementHandler.processBoolean(
         false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
      );
      MovementHandler.applyRunnableTask(false, renderPass -> this.applyEntityByEntity(entity, text, count, posX, posY, posZ, renderPass));
      GL11.glPopMatrix();
   }

   public void applyEntity7(Entity entity, double posX, double posY, double posZ, float factor3, float factor4) {
      if (this.renderManager.gameSettings != null) {
         if (this.renderManager.gameSettings.state91 && this.factor > 0.0F && !entity.isActive12() && this.renderManager.isState1()) {
            double distance = this.renderManager.getDistanceToCamera(entity.posX, entity.posY, entity.posZ);
            float factor5 = (float)((1.0 - distance / 256.0) * (double)this.factor2);
            if (factor5 > 0.0F) {
               this.applyEntity4(entity, posX, posY, posZ, factor5, factor4);
            }
         }

         if (entity.isActive4() && (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isActive23())) {
            this.applyEntityByEntity(entity, posX, posY, posZ, factor4);
         }
      }
   }

   private int computeCount(ItemStack stack, int count, int count2) {
      int count3 = 0;
      int index = LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, stack);
      if (index > 0) {
         MinecraftAccessor.minecraft.fontRendererObj.getIndex(ClientGlobals.moduleManager.nameTags.unbreakingAbbrev + index, count, count2, Color.white.getRGB());
         count3++;
      }

      int index2 = LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, stack);
      if (index2 > 0) {
         MinecraftAccessor.minecraft
            .fontRendererObj
            .getIndex(ClientGlobals.moduleManager.nameTags.sharpnessAbbrev + index2, count, count2 - count3 * 10, Color.white.getRGB());
         count3++;
      }

      int index3 = LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, stack);
      if (index3 > 0) {
         MinecraftAccessor.minecraft
            .fontRendererObj
            .getIndex(ClientGlobals.moduleManager.nameTags.protectionAbbrev + index3, count, count2 - count3 * 10, Color.white.getRGB());
         count3++;
      }

      return count3;
   }

   public boolean matchesEntityByEntity(Entity entity, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      if (EventEntitySyncCallback.matchesEntity(entity)) {
         return false;
      } else {
         AxisAlignedBB boundingBox = entity.getBoundingBox2();
         if (boundingBox.isActive() || boundingBox.getValue() == 0.0) {
            boundingBox = new AxisAlignedBB(entity.posX - 2.0, entity.posY - 2.0, entity.posZ - 2.0, entity.posX + 2.0, entity.posY + 2.0, entity.posZ + 2.0);
         }

         return entity.checkDouble2(posX, posY, posZ) && (entity.enabled3 || preMotionConsumer.checkAxisAlignedBB(boundingBox));
      }
   }

   private void applyEntity4(Entity entity, double posX, double posY, double posZ, float factor3, float factor4) {
      if (!BuildManager.canExecute() || !PreEndHandManager.enabled14) {
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
         this.renderManager.textureManager.applyResourceLocation3(resourceLocation2);
         World world = this.getWorld();
         GlStateManager.depthMask(false);
         float factor5 = this.factor;
         if (entity instanceof EntityMob) {
            EntityMob entityMob = (EntityMob)entity;
            factor5 *= entityMob.getFactor2();
            if (entityMob.isActive16()) {
               factor5 *= 0.5F;
            }
         }

         double offset = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)factor4;
         double offset2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)factor4;
         double offset3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)factor4;
         int count = MathHelper.ceiling_double_int(offset - (double)factor5);
         int count2 = MathHelper.ceiling_double_int(offset + (double)factor5);
         int count3 = MathHelper.ceiling_double_int(offset2 - (double)factor5);
         int count4 = MathHelper.ceiling_double_int(offset2);
         int count5 = MathHelper.ceiling_double_int(offset3 - (double)factor5);
         int count6 = MathHelper.ceiling_double_int(offset3 + (double)factor5);
         double offset4 = posX - offset;
         double offset5 = posY - offset2;
         double offset6 = posZ - offset3;
         Tessellator tessellator = Tessellator.getTessellator();
         tessellator.getWorldRenderer().updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);

         for (Object blockPosObj : BlockPos.resolveIterableByBlockPos(new BlockPos(count, count3, count5), new BlockPos(count2, count4, count6))) { BlockPos blockPos = (BlockPos)blockPosObj;
            Block block = world.getIBlockState(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()).getBlock();
            if (block.processAction13() != -1 && world.computeCountByIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 3) {
               this.applyBlock(block, posX, posY, posZ, blockPos, factor3, factor5, offset4, offset5, offset6);
            }
         }

         tessellator.updateState();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.depthMask(true);
      }
   }

   public static void applyAxisAlignedBB(AxisAlignedBB boundingBox, double posX, double posY, double posZ) {
      GlStateManager.enableTexture2D();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.applyDouble(posX, posY, posZ);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry7);
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(0.0F, 0.0F, -1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(0.0F, 0.0F, -1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(0.0F, 0.0F, -1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(0.0F, 0.0F, -1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 0.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 0.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 0.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 0.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(0.0F, -1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(0.0F, -1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(0.0F, -1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(0.0F, -1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(-1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(-1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(-1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(-1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).resolveWorldRenderer(1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).resolveWorldRenderer(1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).resolveWorldRenderer(1.0F, 0.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).resolveWorldRenderer(1.0F, 0.0F, 0.0F).updateState();
      tessellator.updateState();
      worldRenderer.applyDouble(0.0, 0.0, 0.0);
      GlStateManager.disableTexture2D();
   }

   public boolean matchesEntity3(Entity entity) {
      ResourceLocation location = this.resolveResourceLocation(entity);
      if (this.resourceLocation != null) {
         location = this.resourceLocation;
      }

      if (location == null) {
         return false;
      } else {
         this.applyResourceLocation2(location);
         return true;
      }
   }

   private void applyEntityByEntity(Entity entity) {
      if (entity instanceof EntityPlayer && ClientGlobals.moduleManager.nameTags.settingArmor.isEnabled()) {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);
         MinecraftAccessor.minecraft.getRenderItem().factor = -100.0F;
         EntityPlayer player = (EntityPlayer)entity;
         boolean enabled = ClientGlobals.moduleManager.nameTags.settingArmorDurability.isEnabled();
         if (player.getHeldItem() != null) {
            RenderUtils.applyItemStack2(player.getHeldItem(), -45, -20, enabled);
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         if (player.getItemStack3(3) != null) {
            RenderUtils.applyItemStack2(player.getItemStack3(3), -27, -20, enabled);
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         if (player.getItemStack3(2) != null) {
            RenderUtils.applyItemStack2(player.getItemStack3(2), -9, -20, enabled);
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         if (player.getItemStack3(1) != null) {
            RenderUtils.applyItemStack2(player.getItemStack3(1), 9, -20, enabled);
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         if (player.getItemStack3(0) != null) {
            RenderUtils.applyItemStack2(player.getItemStack3(0), 27, -20, enabled);
         }

         MovementHandler.processBoolean(
            false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
         );
         if (GL11.glIsEnabled(32823)) {
            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
         }
      }
   }

   public void applyEntity2(Entity entity, String text, double posX, double posY, double posZ, int count) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(2929);
      GL11.glNormal3d(0.0, 1.0, 0.0);
      GlStateManager.applyState3();
      GlStateManager.disableBlend();
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      MovementHandler.processBoolean(
         false, ClientGlobals.moduleManager.nameTags.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled()
      );
      MovementHandler.applyRunnableTask(false, renderPass -> this.applyEntity3(entity, text, posX, posY, posZ, count, renderPass));
      GL11.glPopMatrix();
   }

   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor3, float factor4) {
      this.applyEntity(entity, posX, posY, posZ);
   }

   @Override
   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public static void applyCoreResourceLocationEntry(CoreResourceLocationEntry coreResourceLocationEntry, FloatEntryRegistry floatEntryRegistry) {
      coreResourceLocationEntry.floatEntryRegistry = floatEntryRegistry;
   }

   private void applyBlock(Block block, double posX, double posY, double posZ, BlockPos blockPos, float factor3, float factor4, double posX2, double posY2, double posZ2) {
      if (block.processAction14()) {
         WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
         double offset = ((double)factor3 - (posY - ((double)blockPos.getY() + posY2)) / 2.0)
            * 0.5
            * (double)this.getWorld().computeFactor(blockPos.getX(), blockPos.getY(), blockPos.getZ());
         if (offset >= 0.0) {
            if (offset > 1.0) {
               offset = 1.0;
            }

            double offset2 = (double)blockPos.getX() + block.getCoordOffset() + posX2;
            double offset3 = (double)blockPos.getX() + block.getCoordOffset9() + posX2;
            double offset4 = (double)blockPos.getY() + block.getCoordOffset11() + posY2 + 0.015625;
            double offset5 = (double)blockPos.getZ() + block.getCoordOffset2() + posZ2;
            double offset6 = (double)blockPos.getZ() + block.getCoordOffset15() + posZ2;
            float factor5 = (float)((posX - offset2) / 2.0 / (double)factor4 + 0.5);
            float factor6 = (float)((posX - offset3) / 2.0 / (double)factor4 + 0.5);
            float factor7 = (float)((posZ - offset5) / 2.0 / (double)factor4 + 0.5);
            float factor8 = (float)((posZ - offset6) / 2.0 / (double)factor4 + 0.5);
            worldRenderer.getWorldRenderer(offset2, offset4, offset5)
               .getWorldRenderer6((double)factor5, (double)factor7)
               .resolveWorldRenderer(1.0F, 1.0F, 1.0F, (float)offset)
               .updateState();
            worldRenderer.getWorldRenderer(offset2, offset4, offset6)
               .getWorldRenderer6((double)factor5, (double)factor8)
               .resolveWorldRenderer(1.0F, 1.0F, 1.0F, (float)offset)
               .updateState();
            worldRenderer.getWorldRenderer(offset3, offset4, offset6)
               .getWorldRenderer6((double)factor6, (double)factor8)
               .resolveWorldRenderer(1.0F, 1.0F, 1.0F, (float)offset)
               .updateState();
            worldRenderer.getWorldRenderer(offset3, offset4, offset5)
               .getWorldRenderer6((double)factor6, (double)factor7)
               .resolveWorldRenderer(1.0F, 1.0F, 1.0F, (float)offset)
               .updateState();
         }
      }
   }

   public boolean isActive() {
      return false;
   }

   public abstract ResourceLocation resolveResourceLocation(Entity entity);

   public EntitySkinLocation(RenderManager renderManager2) {
      this.renderManager = renderManager2;
   }
}
