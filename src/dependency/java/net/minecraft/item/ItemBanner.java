package net.minecraft.item;

import client.setting.LocalOptionType;
import client.util.AngleAccumulator;
import java.util.List;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBanner extends ItemBlock {
   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (LocalOptionType localOptionType : LocalOptionType.values()) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         TileEntityBanner.applyNBTTagCompound(tagCompound, localOptionType.getCount2(), (NBTTagList)null);
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound2.applyText("BlockEntityTag", tagCompound);
         ItemStack stack = new ItemStack(item, 1, localOptionType.getCount2());
         stack.processAction(tagCompound2);
         list.add(stack);
      }
   }

   @Override
   public ItemGroupManager getItemGroupManager() {
      return ItemGroupManager.itemGroupManager8;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (enumFacing == EnumFacing.DOWN) {
         return false;
      } else if (!world.getBlockState(blockPos).getBlock().getMaterial().canExecute2()) {
         return false;
      } else {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else if (!Blocks.block138.processAction7(world, blockPos2)) {
            return false;
         } else if (world.state) {
            return true;
         } else {
            if (enumFacing == EnumFacing.UP) {
               int count = MathHelper.ceiling_double_int((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
               world.matchesBlockPos(blockPos2, Blocks.block138.getDefaultState().resolveIBlockState(BlockStandingSign.auxiliaryImmutableSetEntry, count), 3);
            } else {
               world.matchesBlockPos(blockPos2, Blocks.block79.getDefaultState().resolveIBlockState(BlockWallSign.autoWalkPath, enumFacing), 3);
            }

            stack.animationsToGo--;
            TileEntity tileEntity = world.resolveTileEntity(blockPos2);
            if (tileEntity instanceof TileEntityBanner) {
               ((TileEntityBanner)tileEntity).applyItemStack(stack);
            }

            return true;
         }
      }
   }

   private LocalOptionType resolveLocalOptionType(ItemStack stack) {
      NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", false);
      LocalOptionType localOptionType;
      if (tagCompound != null && tagCompound.containsKey("Base")) {
         localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(tagCompound.computeCount("Base"));
      } else {
         localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset());
      }

      return localOptionType;
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      return count == 0 ? 16777215 : this.resolveLocalOptionType(stack).getMapColor().indexOffset;
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", false);
      if (tagCompound != null && tagCompound.containsKey("Patterns")) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Patterns", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt() && index < 6; index++) {
            NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
            LocalOptionType localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(nBTTagCompound.computeCount("Color"));
            AngleAccumulator angleAccumulator = AngleAccumulator.resolveAngleAccumulator(nBTTagCompound.resolveText("Pattern"));
            if (angleAccumulator != null) {
               list.add(I18n.getString2("item.banner." + angleAccumulator.getLabelText() + "." + localOptionType.getLabelText()));
            }
         }
      }
   }

   @Override
   public String resolveText(ItemStack stack) {
      String text = "item.banner.";
      LocalOptionType localOptionType = this.resolveLocalOptionType(stack);
      return I18n.getString2(text + localOptionType.getLabelText() + ".name");
   }

   public ItemBanner() {
      super(Blocks.block138);
      this.indexOffset1 = 16;
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.processAction(true);
      this.processAction3(0);
   }
}
