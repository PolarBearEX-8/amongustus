package client.util;

import client.event.PreMotionDispatcher;
import client.setting.ItemPhysicsNoneType;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class ListenerAdapter5 implements PreMotionDispatcher {
   public final BoxesManager boxesManager;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      ItemStack stack = livingEntity.getItemStack3(3);
      if (stack != null && stack.getItem() != null) {
         Item item = stack.getItem();
         Minecraft mc = Minecraft.getMinecraft();
         GlStateManager.pushMatrix();
         if (livingEntity.isSprinting()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         boolean flag = livingEntity instanceof EntityVillager || livingEntity instanceof EntityZombie && ((EntityZombie)livingEntity).isActive32();
         if (!flag && livingEntity.isActive16()) {
            float factor = 2.0F;
            float factor2 = 1.4F;
            GlStateManager.translate3f3(factor2 / factor, factor2 / factor, factor2 / factor);
            GlStateManager.translate(0.0F, 16.0F * posZ, 0.0F);
         }

         this.boxesManager.applyFloatByFloat(0.0625F);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         if (item instanceof ItemBlock) {
            float factor3 = 0.625F;
            GlStateManager.translate(0.0F, -0.25F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate3f3(factor3, -factor3, -factor3);
            if (flag) {
               GlStateManager.translate(0.0F, 0.1875F, 0.0F);
            }

            mc.getItemRenderer().applyEntityLivingBaseByEntityLivingBase(livingEntity, stack, ItemPhysicsNoneType.HEAD);
         } else if (item == Items.skull) {
            float factor4 = 1.1875F;
            GlStateManager.translate3f3(factor4, -factor4, -factor4);
            if (flag) {
               GlStateManager.translate(0.0F, 0.0625F, 0.0F);
            }

            GameProfile gameProfile = null;
            if (stack.canExecute()) {
               NBTTagCompound stackTagCompound = stack.getStackTagCompound();
               if (stackTagCompound.matchesText("SkullOwner", 10)) {
                  if (stack.getGameProfile() == null) {
                     gameProfile = AuxiliaryNameManager.resolveGameProfile(stackTagCompound.getNBTTagCompound("SkullOwner"));
                     stack.setGameProfile(gameProfile);
                  } else {
                     gameProfile = stack.getGameProfile();
                  }
               } else if (stackTagCompound.matchesText("SkullOwner", 8)) {
                  String text = stackTagCompound.resolveText("SkullOwner");
                  if (!ClientBrandHelper.isEmpty(text)) {
                     gameProfile = TileEntitySkull.resolveGameProfile(new GameProfile((UUID)null, text));
                     stackTagCompound.applyText("SkullOwner", AuxiliaryNameManager.resolveNBTTagCompound(new NBTTagCompound(), gameProfile));
                  }
               }
            }

            stack.setResourceLocation(
               BaseResourceLocationArrayHandler.baseResourceLocationArrayHandler
                  .resolveResourceLocation(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, stack.getIndexOffset(), gameProfile, -1, stack.getResourceLocation())
            );
         }

         GlStateManager.popMatrix();
      }
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public ListenerAdapter5(BoxesManager boxesManager2) {
      this.boxesManager = boxesManager2;
   }
}
