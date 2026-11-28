package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StringUtils;

public class LayerCustomHead implements LayerRenderer {
   private final ModelRenderer modelRenderer;

   public LayerCustomHead(ModelRenderer modelRenderer2) {
      this.modelRenderer = modelRenderer2;
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      ItemStack currentArmor = entity.getCurrentArmor(3);
      if (currentArmor != null && currentArmor.getItem() != null) {
         Item item = currentArmor.getItem();
         Minecraft minecraft = Minecraft.getMinecraft();
         GlStateManager.pushMatrix();
         if (entity.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         boolean flag = entity instanceof EntityVillager || entity instanceof EntityZombie && ((EntityZombie)entity).isVillager();
         if (!flag && entity.isChild()) {
            float f8 = 2.0F;
            float f9 = 1.4F;
            GlStateManager.scale(f9 / f8, f9 / f8, f9 / f8);
            GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
         }

         this.modelRenderer.postRender(0.0625F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (item instanceof ItemBlock) {
            float f10 = 0.625F;
            GlStateManager.translate(0.0F, -0.25F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.scale(f10, -f10, -f10);
            if (flag) {
               GlStateManager.translate(0.0F, 0.1875F, 0.0F);
            }

            minecraft.getItemRenderer().renderItem(entity, currentArmor, ItemCameraTransforms.TransformType.HEAD);
         } else if (item == Items.skull) {
            float f11 = 1.1875F;
            GlStateManager.scale(f11, -f11, -f11);
            if (flag) {
               GlStateManager.translate(0.0F, 0.0625F, 0.0F);
            }

            GameProfile profile = null;
            if (currentArmor.hasTagCompound()) {
               NBTTagCompound tagCompound = currentArmor.getTagCompound();
               if (tagCompound.hasKey("SkullOwner", 10)) {
                  if (currentArmor.getCachedSkullProfile() == null) {
                     profile = NBTUtil.readGameProfileFromNBT(tagCompound.getCompoundTag("SkullOwner"));
                     currentArmor.setCachedSkullProfile(profile);
                  } else {
                     profile = currentArmor.getCachedSkullProfile();
                  }
               } else if (tagCompound.hasKey("SkullOwner", 8)) {
                  String string = tagCompound.getString("SkullOwner");
                  if (!StringUtils.isNullOrEmpty(string)) {
                     profile = TileEntitySkull.updateGameprofile(new GameProfile((UUID)null, string));
                     tagCompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), profile));
                  }
               }
            }

            currentArmor.setCachedSkullTexture(TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, currentArmor.getMetadata(), profile, -1, currentArmor.getCachedSkullTexture()));
         }

         GlStateManager.popMatrix();
      }
   }
}
