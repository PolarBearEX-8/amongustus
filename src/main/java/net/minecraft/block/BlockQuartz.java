package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockQuartz extends Block {
   public static final PropertyEnum VARIANT;

   @Override
   public ItemStack createStackedBlock(IBlockState state) {
      BlockQuartz.EnumType value = (BlockQuartz.EnumType)state.getValue(VARIANT);
      return value != BlockQuartz.EnumType.LINES_X && value != BlockQuartz.EnumType.LINES_Z
         ? super.createStackedBlock(state)
         : new ItemStack(Item.getItemFromBlock(this), 1, BlockQuartz.EnumType.LINES_Y.getMetadata());
   }

   public BlockQuartz() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockQuartz.EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.quartzColor;
   }

   @Override
   public int damageDropped(IBlockState state) {
      BlockQuartz.EnumType value = (BlockQuartz.EnumType)state.getValue(VARIANT);
      return value != BlockQuartz.EnumType.LINES_X && value != BlockQuartz.EnumType.LINES_Z ? value.getMetadata() : BlockQuartz.EnumType.LINES_Y.getMetadata();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      if (n == BlockQuartz.EnumType.LINES_Y.getMetadata()) {
         switch (facing.getAxis()) {
            case Z:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_Z);
            case X:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_X);
            case Y:
            default:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_Y);
         }
      } else {
         return n == BlockQuartz.EnumType.CHISELED.getMetadata()
            ? this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.CHISELED)
            : this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.DEFAULT);
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockQuartz.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.byMetadata(n));
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockQuartz.EnumType.class);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, BlockQuartz.EnumType.DEFAULT.getMetadata()));
      list.add(new ItemStack(item, 1, BlockQuartz.EnumType.CHISELED.getMetadata()));
      list.add(new ItemStack(item, 1, BlockQuartz.EnumType.LINES_Y.getMetadata()));
   }

public enum EnumType implements IStringSerializable {
      DEFAULT(0, "default", "default"),
      CHISELED(1, "chiseled", "chiseled"),
      LINES_Y(2, "lines_y", "lines"),
      LINES_X(3, "lines_x", "lines"),
      LINES_Z(4, "lines_z", "lines");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
   @Override
      public String getName() {
         return this.name;
      }

      public int getMetadata() {
         return this.meta;
      }

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      private EnumType(int n, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      @Override
      public String toString() {
         return this.unlocalizedName;
      }
   }
}
