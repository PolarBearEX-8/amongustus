package net.minecraft.client.renderer.tileentity;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class TileEntityItemStackRenderer {
   public static TileEntityItemStackRenderer instance = new TileEntityItemStackRenderer();
   private TileEntityChest chestBasic = new TileEntityChest(0);
   private TileEntityChest chestTrap = new TileEntityChest(1);
   private TileEntityEnderChest enderChest = new TileEntityEnderChest();
   private TileEntityBanner banner = new TileEntityBanner();
   private TileEntitySkull skull = new TileEntitySkull();

   public void renderByItem(ItemStack stack) {
      if (stack.getItem() == Items.banner) {
         this.banner.setItemValues(stack);
         TileEntityRendererDispatcher.instance.renderTileEntityAt(this.banner, 0.0, 0.0, 0.0, 0.0F);
      } else if (stack.getItem() == Items.skull) {
         GameProfile profile = null;
         if (stack.hasTagCompound()) {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound.hasKey("SkullOwner", 10)) {
               if (stack.getCachedSkullProfile() == null) {
                  profile = NBTUtil.readGameProfileFromNBT(tagCompound.getCompoundTag("SkullOwner"));
                  stack.setCachedSkullProfile(profile);
               } else {
                  profile = stack.getCachedSkullProfile();
               }
            } else if (tagCompound.hasKey("SkullOwner", 8) && tagCompound.getString("SkullOwner").length() > 0) {
               profile = TileEntitySkull.updateGameprofile(new GameProfile((UUID)null, tagCompound.getString("SkullOwner")));
               tagCompound.removeTag("SkullOwner");
               tagCompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), profile));
            }
         }

         if (TileEntitySkullRenderer.instance != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(-0.5F, 0.0F, -0.5F);
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            GlStateManager.disableCull();
            stack.setCachedSkullTexture(TileEntitySkullRenderer.instance.renderSkull(0.0F, 0.0F, 0.0F, EnumFacing.UP, 0.0F, stack.getMetadata(), profile, -1, stack.getCachedSkullTexture()));
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
         }
      } else {
         Block blockFromItem = Block.getBlockFromItem(stack.getItem());
         if (blockFromItem == Blocks.ender_chest) {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.enderChest, 0.0, 0.0, 0.0, 0.0F);
         } else if (blockFromItem == Blocks.trapped_chest) {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.chestTrap, 0.0, 0.0, 0.0, 0.0F);
         } else {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.chestBasic, 0.0, 0.0, 0.0, 0.0F);
         }
      }
   }
}
