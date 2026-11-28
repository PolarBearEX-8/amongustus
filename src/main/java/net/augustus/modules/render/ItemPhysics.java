package net.augustus.modules.render;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPhysics extends Module {
   public final BooleanValue rotate;

   public ItemPhysics() {
       super("ItemPhysics", "ArtikelPhysiken", new Color(65, 224, 145), Categorys.RENDER);
      this.rotate = new BooleanValue(this, "Rotate", true);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public int transformDroppedItem(EntityItem entityItem, double d, double d2, double d3, float f, IBakedModel bakedModel) {
      ItemStack entityItem2 = entityItem.getEntityItem();
      if (entityItem2.getItem() == null) {
         return 0;
      } else {
         boolean isGui3d = bakedModel.isGui3d();
         int modelCount = this.getModelCount(entityItem2);
         float f2 = 0.25F;
         float f3 = -0.125F;
         if (!isGui3d) {
            f3 = -0.175F;
         }

         float f4 = bakedModel.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
         GlStateManager.translate((float)d, (float)d2 + f3 + 0.25F * f4, (float)d3);
         if (isGui3d || mc.getRenderManager().options != null) {
            float f5 = (((float)entityItem.getAge() + f) / 20.0F + entityItem.hoverStart) * 57.295776F;
         }

         if (!isGui3d) {
            float f6 = -0.0F * (float)(modelCount - 1) * 0.5F;
            float f7 = -0.0F * (float)(modelCount - 1) * 0.5F;
            float f8 = -0.046875F * (float)(modelCount - 1) * 0.5F;
            if (entityItem.onGround) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 1.0F);
            }
         }

         float f9 = 10.0F;
         if (!entityItem.onGround && this.rotate.getValue()) {
            GlStateManager.rotate((float)entityItem.getAge() * f9 % 360.0F, 1.0F, 0.0F, 1.0F);
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         return modelCount;
      }
   }

   private int getModelCount(ItemStack stack) {
      byte b = 1;
      if (stack.stackSize > 48) {
         b = 5;
      } else if (stack.stackSize > 32) {
         b = 4;
      } else if (stack.stackSize > 16) {
         b = 3;
      } else if (stack.stackSize > 1) {
         b = 2;
      }

      return b;
   }

}
