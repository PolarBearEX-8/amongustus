package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockHayBlock extends CommonBlockAdapter {
   @Override
   public IBlockState processAction(int count) {
      EnumFacingXType enumFacingXType = EnumFacingXType.Y;
      int count2 = count & 12;
      if (count2 == 4) {
         enumFacingXType = EnumFacingXType.X;
      } else if (count2 == 8) {
         enumFacingXType = EnumFacingXType.Z;
      }

      return this.getDefaultState().resolveIBlockState(immutableSetEntry, enumFacingXType);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return super.onBlockPlaced(world, blockPos, enumFacing, posX, posY, posZ, count, livingEntity).resolveIBlockState(immutableSetEntry, enumFacing.getEnumFacingXType());
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      EnumFacingXType comparable = (EnumFacingXType)blockState.getComparable(immutableSetEntry);
      if (comparable == EnumFacingXType.X) {
         byteValue |= 4;
      } else if (comparable == EnumFacingXType.Z) {
         byteValue |= 8;
      }

      return byteValue;
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(Item.processAction2(this), 1, 0);
   }

   public BlockHayBlock() {
      super(Material.material30, MapColor.mapColor3);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, EnumFacingXType.Y));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
