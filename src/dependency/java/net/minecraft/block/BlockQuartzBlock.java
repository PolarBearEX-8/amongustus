package net.minecraft.block;

import client.setting.CommonOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.VectorBuffer;
import java.util.List;
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

public class BlockQuartzBlock extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", CommonOptionType.class);

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor30;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.resolveCommonOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, CommonOptionType.DEFAULT.getCount()));
      list.add(new ItemStack(item, 1, CommonOptionType.CHISELED.getCount()));
      list.add(new ItemStack(item, 1, CommonOptionType.LINES_Y.getCount()));
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      if (count == CommonOptionType.LINES_Y.getCount()) {
         switch (VectorBuffer.countArray[enumFacing.getEnumFacingXType().ordinal()]) {
            case 1:
               return this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.LINES_Z);
            case 2:
               return this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.LINES_X);
            case 3:
            default:
               return this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.LINES_Y);
         }
      } else {
         return count == CommonOptionType.CHISELED.getCount()
            ? this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.CHISELED)
            : this.getDefaultState().resolveIBlockState(immutableSetEntry, CommonOptionType.DEFAULT);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      CommonOptionType comparable = (CommonOptionType)blockState.getComparable(immutableSetEntry);
      return comparable != CommonOptionType.LINES_X && comparable != CommonOptionType.LINES_Z ? comparable.getCount() : CommonOptionType.LINES_Y.getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CommonOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      CommonOptionType comparable = (CommonOptionType)blockState.getComparable(immutableSetEntry);
      return comparable != CommonOptionType.LINES_X && comparable != CommonOptionType.LINES_Z
         ? super.resolveItemStack(blockState)
         : new ItemStack(Item.processAction2(this), 1, CommonOptionType.LINES_Y.getCount());
   }

   public BlockQuartzBlock() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, CommonOptionType.DEFAULT));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
