package net.minecraft.item;

import client.util.AuxiliaryNameManager;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSkull extends Item {
   public static final String[] stringArray = new String[]{"skeleton", "wither", "zombie", "char", "creeper"};

   @Override
   public boolean processAction(NBTTagCompound tagCompound) {
      super.processAction(tagCompound);
      if (tagCompound.matchesText("SkullOwner", 8) && tagCompound.resolveText("SkullOwner").length() > 0) {
         GameProfile gameProfile = TileEntitySkull.resolveGameProfile(new GameProfile((UUID)null, tagCompound.resolveText("SkullOwner")));
         tagCompound.applyText("SkullOwner", AuxiliaryNameManager.resolveNBTTagCompound(new NBTTagCompound(), gameProfile));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (int index = 0; index < stringArray.length; index++) {
         list.add(new ItemStack(item, 1, index));
      }
   }

   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public String getText(ItemStack stack) {
      int indexOffset = stack.getIndexOffset();
      if (indexOffset < 0 || indexOffset >= stringArray.length) {
         indexOffset = 0;
      }

      return super.getString() + "." + stringArray[indexOffset];
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      if (enumFacing == EnumFacing.DOWN) {
         return false;
      } else {
         if (!world.getBlockState(blockPos).getBlock().processAction6(world, blockPos)) {
            if (!world.getBlockState(blockPos).getBlock().getMaterial().canExecute2()) {
               return false;
            }

            blockPos2 = blockPos.offset(enumFacing);
         }

         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else if (!Blocks.blockSkull.processAction7(world, blockPos2)) {
            return false;
         } else {
            if (!world.state) {
               world.matchesBlockPos(blockPos2, Blocks.blockSkull.getDefaultState().resolveIBlockState(BlockSkull.autoWalkPath, enumFacing), 3);
               int count = 0;
               if (enumFacing == EnumFacing.UP) {
                  count = MathHelper.ceiling_double_int((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5) & 15;
               }

               TileEntity tileEntity = world.resolveTileEntity(blockPos2);
               if (tileEntity instanceof TileEntitySkull) {
                  TileEntitySkull tileEntitySkull = (TileEntitySkull)tileEntity;
                  if (stack.getIndexOffset() == 3) {
                     GameProfile gameProfile = null;
                     if (stack.canExecute()) {
                        NBTTagCompound stackTagCompound = stack.getStackTagCompound();
                        if (stackTagCompound.matchesText("SkullOwner", 10)) {
                           gameProfile = AuxiliaryNameManager.resolveGameProfile(stackTagCompound.getNBTTagCompound("SkullOwner"));
                        } else if (stackTagCompound.matchesText("SkullOwner", 8) && stackTagCompound.resolveText("SkullOwner").length() > 0) {
                           gameProfile = new GameProfile((UUID)null, stackTagCompound.resolveText("SkullOwner"));
                        }
                     }

                     tileEntitySkull.applyGameProfile(gameProfile);
                  } else {
                     tileEntitySkull.applyIndex(stack.getIndexOffset());
                  }

                  tileEntitySkull.setCount3(count);
                  Blocks.blockSkull.applyWorld(world, blockPos2, tileEntitySkull);
               }

               stack.animationsToGo--;
            }

            return true;
         }
      }
   }

   @Override
   public String resolveText(ItemStack stack) {
      if (stack.getIndexOffset() == 3 && stack.canExecute()) {
         if (stack.getStackTagCompound().matchesText("SkullOwner", 8)) {
            return I18n.resolveText("item.skull.player.name", new Object[]{stack.getStackTagCompound().resolveText("SkullOwner")});
         }

         if (stack.getStackTagCompound().matchesText("SkullOwner", 10)) {
            NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("SkullOwner");
            if (nBTTagCompound.matchesText("Name", 8)) {
               return I18n.resolveText("item.skull.player.name", new Object[]{nBTTagCompound.resolveText("Name")});
            }
         }
      }

      return super.resolveText(stack);
   }

   public ItemSkull() {
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.processAction3(0);
      this.processAction(true);
   }
}
