package client.util;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class DampenerCoordinator {
   public static DampenerCoordinator dampenerCoordinator = new DampenerCoordinator();
   public TileEntityChest tileEntityChest = new TileEntityChest(0);
   public TileEntityChest tileEntityChest2 = new TileEntityChest(1);
   public TileEntityEnderchest tileEntityEnderchest = new TileEntityEnderchest();
   public TileEntityBanner tileEntityBanner = new TileEntityBanner();
   public TileEntitySkull tileEntitySkull = new TileEntitySkull();

   public void applyItemStack(ItemStack stack) {
      if (stack.getItem() == Items.banner) {
         this.tileEntityBanner.applyItemStack(stack);
         StandardMapEntry.standardMapEntry.applyTileEntity(this.tileEntityBanner, 0.0, 0.0, 0.0, 0.0F);
      } else if (stack.getItem() == Items.skull) {
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
            } else if (stackTagCompound.matchesText("SkullOwner", 8) && stackTagCompound.resolveText("SkullOwner").length() > 0) {
               gameProfile = TileEntitySkull.resolveGameProfile(new GameProfile((UUID)null, stackTagCompound.resolveText("SkullOwner")));
               stackTagCompound.remove("SkullOwner");
               stackTagCompound.applyText("SkullOwner", AuxiliaryNameManager.resolveNBTTagCompound(new NBTTagCompound(), gameProfile));
            }
         }

         if (BaseResourceLocationArrayHandler.baseResourceLocationArrayHandler != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(-0.5F, 0.0F, -0.5F);
            GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
            GlStateManager.disableAlpha();
            stack.setResourceLocation(
               BaseResourceLocationArrayHandler.baseResourceLocationArrayHandler
                  .resolveResourceLocation(0.0F, 0.0F, 0.0F, EnumFacing.UP, 0.0F, stack.getIndexOffset(), gameProfile, -1, stack.getResourceLocation())
            );
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
         }
      } else {
         Block block = Block.resolveBlock(stack.getItem());
         if (block == Blocks.block60) {
            StandardMapEntry.standardMapEntry.applyTileEntity(this.tileEntityEnderchest, 0.0, 0.0, 0.0, 0.0F);
         } else if (block == Blocks.block128) {
            StandardMapEntry.standardMapEntry.applyTileEntity(this.tileEntityChest2, 0.0, 0.0, 0.0, 0.0F);
         } else {
            StandardMapEntry.standardMapEntry.applyTileEntity(this.tileEntityChest, 0.0, 0.0, 0.0, 0.0F);
         }
      }
   }
}
