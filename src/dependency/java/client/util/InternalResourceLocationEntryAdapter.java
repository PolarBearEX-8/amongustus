package client.util;

import augustus.modules.render.ItemESP;
import augustus.modules.render.Items2D;
import client.ClientGlobals;
import client.module.Module;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.RenderUtils;
import client.setting.ItemPhysicsNoneType;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class InternalResourceLocationEntryAdapter extends EntitySkinLocation {
   public final RenderItem renderItem;
   public Random random = new Random();

   public ResourceLocation resolveResourceLocation(EntityItem entityItem) {
      return ListenerAdapter27.resourceLocation;
   }

   public void applyEntityItem(EntityItem entityItem, double posX, double posY, double posZ, float factor, float factor2) {
      if (((Module)ClientGlobals.moduleManager.items2D).isToggled()
         && ClientGlobals.moduleManager.items2D.settingRemove3DItems.isEnabled()
         && entityItem.getItemStack() != null) {
         Items2D items2D = ClientGlobals.moduleManager.items2D;
         ItemStack stack = entityItem.getItemStack();
         if (items2D.checkItemStack(stack)) {
            return;
         }
      }

      ItemStack stack2 = entityItem.getItemStack();
      this.random.setSeed(187L);
      boolean flag = false;
      if (this.matchesEntity3(entityItem)) {
         this.renderManager.textureManager.get(this.resolveResourceLocation(entityItem)).applyFlag(false, false);
         flag = true;
      }

      GlStateManager.disableLighting();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.pushMatrix();
      IBakedModel iBakedModel = this.renderItem.getSecondaryMapEntry().resolveIBakedModel(stack2);
      int count;
      if (((Module)ClientGlobals.moduleManager.itemPhysics).isToggled()) {
         count = ClientGlobals.moduleManager.itemPhysics.getIndex(entityItem, posX, posY, posZ, factor2, iBakedModel);
      } else {
         count = this.computeCount(entityItem, posX, posY, posZ, factor2, iBakedModel);
      }

      for (int index = 0; index < count; index++) {
         if (iBakedModel.canExecute()) {
            GlStateManager.pushMatrix();
            if (index > 0) {
               float factor3 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
               float factor4 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
               float factor5 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
               GlStateManager.translate(factor3, factor4, factor5);
            }

            GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
            iBakedModel.getSpecialItemEntry().applyItemPhysicsNoneType(ItemPhysicsNoneType.GROUND);
            this.renderItem.applyItemStack(stack2, iBakedModel, true, false, false);
            if (((Module)ClientGlobals.moduleManager.itemESP).isToggled()
               && ClientGlobals.moduleManager.itemESP.settingMode.getSelected().equals("Box")
               && entityItem.getItemStack() != null
               && ItemESP.checkItem(entityItem.getItemStack().getItem())) {
               if (BuildManager.canExecute() && EntityRenderer.state) {
                  PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
               }

               GlStateManager.pushMatrix();
               GL11.glPushMatrix();
               float factor6 = entityItem.width / 2.0F;
               RenderUtils.updateState();
               RenderHelper.disableStandardItemLighting();
               GL11.glScaled(2.0, 2.0, 2.0);
               RenderUtils.setColor(ClientGlobals.moduleManager.itemESP.settingBoxColor.getInt12(0));
               RenderUtils.processAction2(0.0F - factor6, entityItem.height / -2.0F, 0.0F - factor6, 0.0F + factor6, entityItem.height / 2.0F, 0.0F + factor6);
               RenderUtils.prepareScissor();
               GlStateManager.applyState13();
               GlStateManager.enableState4(0);
               GlStateManager.enableState4(1);
               GlStateManager.applyState6();
               RenderUtils.processAction(1, 1, 1, 1);
               GL11.glPopMatrix();
               GlStateManager.popMatrix();
               if (BuildManager.canExecute() && EntityRenderer.state) {
                  PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager7);
               }
            }

            GlStateManager.popMatrix();
         } else {
            GlStateManager.pushMatrix();
            iBakedModel.getSpecialItemEntry().applyItemPhysicsNoneType(ItemPhysicsNoneType.GROUND);
            this.renderItem.applyItemStack(stack2, iBakedModel, false, false, false);
            GlStateManager.popMatrix();
            float coordX = iBakedModel.getSpecialItemEntry().auxiliaryItemEntry2.vector3f3.x;
            float coordY = iBakedModel.getSpecialItemEntry().auxiliaryItemEntry2.vector3f3.y;
            float coordZ = iBakedModel.getSpecialItemEntry().auxiliaryItemEntry2.vector3f3.z;
            GlStateManager.translate(0.0F * coordX, 0.0F * coordY, 0.046875F * coordZ);
         }
      }

      if (!iBakedModel.canExecute()
         && ((Module)ClientGlobals.moduleManager.itemESP).isToggled()
         && ClientGlobals.moduleManager.itemESP.settingMode.getSelected().equals("Box")
         && entityItem.getItemStack() != null
         && ItemESP.checkItem(entityItem.getItemStack().getItem())) {
         if (BuildManager.canExecute()) {
            PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
         }

         float factor7 = 0.046875F * iBakedModel.getSpecialItemEntry().auxiliaryItemEntry2.vector3f3.z * (float)(-count);
         GlStateManager.translate(0.0F, 0.0F, factor7 / 2.0F);
         GlStateManager.pushMatrix();
         GL11.glPushMatrix();
         float factor8 = entityItem.width / 2.0F;
         RenderUtils.updateState();
         RenderHelper.disableStandardItemLighting();
         GL11.glScaled(2.0, 2.0, 2.0);
         RenderUtils.setColor(ClientGlobals.moduleManager.itemESP.settingBoxColor.getInt12(0));
         RenderUtils.processAction2(0.0F - factor8, entityItem.height / -2.0F, 0.0F - factor8, 0.0F + factor8, entityItem.height / 2.0F, 0.0F + factor8);
         RenderUtils.prepareScissor();
         GlStateManager.applyState13();
         GlStateManager.enableState4(0);
         GlStateManager.enableState4(1);
         GlStateManager.applyState6();
         RenderUtils.processAction(1, 1, 1, 1);
         GL11.glPopMatrix();
         GlStateManager.popMatrix();
         if (BuildManager.canExecute()) {
            PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager7);
         }
      }

      GlStateManager.popMatrix();
      GlStateManager.applyState8();
      GlStateManager.enableBlend();
      this.matchesEntity3(entityItem);
      if (flag) {
         this.renderManager.textureManager.get(this.resolveResourceLocation(entityItem)).updateState();
      }

      super.applyEntity(entityItem, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ) {
      this.applyEntityItem((EntityItem)entity, posX, posY, posZ);
   }

   private int computeCount(EntityItem entityItem, double posX, double posY, double posZ, float factor, IBakedModel iBakedModel) {
      ItemStack stack = entityItem.getItemStack();
      if (stack.getItem() == null) {
         return 0;
      } else {
         boolean flag = iBakedModel.canExecute();
         int count = this.computeCount(stack);
         float factor2 = MathHelper.abs(((float)entityItem.getIndex() + factor) / 10.0F + entityItem.rotationPitch2) * 0.1F + 0.1F;
         float coordY = iBakedModel.getSpecialItemEntry().getAuxiliaryItemEntry(ItemPhysicsNoneType.GROUND).vector3f3.y;
         GlStateManager.translate((float)posX, (float)posY + factor2 + 0.25F * coordY, (float)posZ);
         if (flag || this.renderManager.gameSettings != null) {
            GlStateManager.rotate((((float)entityItem.getIndex() + factor) / 20.0F + entityItem.rotationPitch2) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (!flag) {
            float factor3 = -0.0F * (float)(count - 1) * 0.5F;
            float factor4 = -0.0F * (float)(count - 1) * 0.5F;
            float factor5 = -0.046875F * (float)(count - 1) * 0.5F;
            GlStateManager.translate(factor3, factor4, factor5);
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         return count;
      }
   }

   public void applyEntityItem(EntityItem entityItem, double posX, double posY, double posZ) {
      if (Minecraft.isActive2()
         && entityItem.getItemStack() != null
         && entityItem.getItemStack().isActive()
         && ((Module)ClientGlobals.moduleManager.itemESP).isToggled()
         && ClientGlobals.moduleManager.itemESP.settingRenderCustomItemnames.isEnabled()
         && ItemESP.checkItem(entityItem.getItemStack().getItem())) {
         if (ClientGlobals.moduleManager.itemESP.settingApplyNametagsRendering.isEnabled()) {
            this.applyEntity2(entityItem, entityItem.getItemStack().getString(), posX, posY, posZ, 64);
         } else {
            this.applyEntity(entityItem, entityItem.getItemStack().getString(), posX, posY, posZ, 64);
         }
      }
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityItem((EntityItem)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityItem)entity);
   }

   private int computeCount(ItemStack stack) {
      byte byteValue = 1;
      if (stack.animationsToGo > 48) {
         byteValue = 5;
      } else if (stack.animationsToGo > 32) {
         byteValue = 4;
      } else if (stack.animationsToGo > 16) {
         byteValue = 3;
      } else if (stack.animationsToGo > 1) {
         byteValue = 2;
      }

      return byteValue;
   }

   public InternalResourceLocationEntryAdapter(RenderManager renderManager, RenderItem renderItem2) {
      super(renderManager);
      this.renderItem = renderItem2;
      this.factor = 0.15F;
      this.factor2 = 0.75F;
   }
}
