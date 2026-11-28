package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockNewLog extends BlockLog {
   public static final PropertyEnum VARIANT;

   static {
      VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, obj -> ((BlockPlanks.EnumType)obj).getMetadata() >= 4);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4;
   }

   public BlockNewLog() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4;
      switch (((BlockLog.EnumAxis)state.getValue(LOG_AXIS))) {
         case X:
            n |= 4;
            break;
         case Z:
            n |= 8;
            break;
         case NONE:
            n |= 12;
      }

      return n;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.ACACIA.getMetadata() - 4));
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.DARK_OAK.getMetadata() - 4));
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      BlockPlanks.EnumType value = (BlockPlanks.EnumType)state.getValue(VARIANT);
      switch (((BlockLog.EnumAxis)state.getValue(LOG_AXIS))) {
         case X:
         case Z:
         case NONE:
         default:
            switch (value) {
               case ACACIA:
               default:
                  return MapColor.stoneColor;
               case DARK_OAK:
                  return BlockPlanks.EnumType.DARK_OAK.getMapColor();
            }
         case Y:
            return value.getMapColor();
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((n & 3) + 4));
      switch (n & 12) {
         case 0:
            state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
            break;
         case 4:
            state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
            break;
         case 8:
            state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
            break;
         default:
            state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
      }

      return state;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT, LOG_AXIS});
   }

   @Override
   public ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4);
   }

}
