package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockHugeMushroom extends Block {
   public static final PropertyEnum VARIANT;
   private final Block smallBlock;

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockHugeMushroom.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockHugeMushroom.EnumType.byMetadata(n));
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(this.smallBlock);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState();
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      switch (((BlockHugeMushroom.EnumType)state.getValue(VARIANT))) {
         case ALL_STEM:
            return MapColor.clothColor;
         case ALL_INSIDE:
            return MapColor.sandColor;
         case STEM:
            return MapColor.sandColor;
         default:
            return super.getMapColor(state);
      }
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockHugeMushroom.EnumType.class);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   @Override
   public int quantityDropped(Random rand) {
      return Math.max(0, rand.nextInt(10) - 7);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(this.smallBlock);
   }

   public BlockHugeMushroom(Material material, MapColor mapColor, Block block) {
      super(material, mapColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
      this.smallBlock = block;
   }

public enum EnumType implements IStringSerializable {
      NORTH_WEST(1, "north_west"),
      NORTH(2, "north"),
      NORTH_EAST(3, "north_east"),
      WEST(4, "west"),
      CENTER(5, "center"),
      EAST(6, "east"),
      SOUTH_WEST(7, "south_west"),
      SOUTH(8, "south"),
      SOUTH_EAST(9, "south_east"),
      STEM(10, "stem"),
      ALL_INSIDE(0, "all_inside"),
      ALL_OUTSIDE(14, "all_outside"),
      ALL_STEM(15, "all_stem");

   private static final EnumType[] META_LOOKUP = new EnumType[16];
      private final int meta;
      private final String name;
   private EnumType(int n, String str) {
         this.meta = n;
         this.name = str;
      }

      @Override
      public String getName() {
         return this.name;
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      public int getMetadata() {
         return this.meta;
      }

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         EnumType enumType = META_LOOKUP[n];
         return enumType == null ? META_LOOKUP[0] : enumType;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
