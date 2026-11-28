package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockOldLog extends BlockLog {
   public static final PropertyEnum VARIANT;

   @Override
   public MapColor getMapColor(IBlockState state) {
      BlockPlanks.EnumType value = (BlockPlanks.EnumType)state.getValue(VARIANT);
      switch (((BlockLog.EnumAxis)state.getValue(LOG_AXIS))) {
         case X:
         case Z:
         case NONE:
         default:
            switch (value) {
               case OAK:
               default:
                  return BlockPlanks.EnumType.SPRUCE.getMapColor();
               case SPRUCE:
                  return BlockPlanks.EnumType.DARK_OAK.getMapColor();
               case BIRCH:
                  return MapColor.quartzColor;
               case JUNGLE:
                  return BlockPlanks.EnumType.SPRUCE.getMapColor();
            }
         case Y:
            return value.getMapColor();
      }
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.OAK.getMetadata()));
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()));
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.BIRCH.getMetadata()));
      list.add(new ItemStack(item, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()));
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new BlockOldLog.FilterPredicate());
   }

   public BlockOldLog() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
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
   public IBlockState getStateFromMeta(int n) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((n & 3) % 4));
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
   public ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT, LOG_AXIS});
   }

public static final class FilterPredicate implements Predicate {
      public boolean apply(BlockPlanks.EnumType enumType) {
         return enumType.getMetadata() < 4;
      }

      public boolean apply(Object obj) {
         return this.apply((BlockPlanks.EnumType)obj);
      }
   }

}
