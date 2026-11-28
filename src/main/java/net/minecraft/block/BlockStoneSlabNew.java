package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class BlockStoneSlabNew extends BlockSlab {
   public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockStoneSlabNew.EnumType.class);

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      if (item != Item.getItemFromBlock(Blocks.double_stone_slab2)) {
         for (BlockStoneSlabNew.EnumType enumType : BlockStoneSlabNew.EnumType.values()) {
            list.add(new ItemStack(item, 1, enumType.getMetadata()));
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.stone_slab2);
   }

   @Override
   public IProperty getVariantProperty() {
      return VARIANT;
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockStoneSlabNew.EnumType)state.getValue(VARIANT)).getMapColor();
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockStoneSlabNew.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public Object getVariant(ItemStack stack) {
      return BlockStoneSlabNew.EnumType.byMetadata(stack.getMetadata() & 7);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockStoneSlabNew.EnumType)state.getValue(VARIANT)).getMetadata();
      if (this.isDouble()) {
         if ((Boolean)state.getValue(SEAMLESS)) {
            n |= 8;
         }
      } else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
         n |= 8;
      }

      return n;
   }

   public BlockStoneSlabNew() {
      super(Material.rock);
      IBlockState baseState = this.blockState.getBaseState();
      if (this.isDouble()) {
         baseState = baseState.withProperty(SEAMLESS, Boolean.FALSE);
      } else {
         baseState = baseState.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(baseState.withProperty(VARIANT, BlockStoneSlabNew.EnumType.RED_SANDSTONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockStoneSlabNew.EnumType.byMetadata(n & 7));
      if (this.isDouble()) {
         state = state.withProperty(SEAMLESS, (n & 8) != 0);
      } else {
         state = state.withProperty(HALF, (n & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return state;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".red_sandstone.name");
   }

   @Override
   public String getUnlocalizedName(int n) {
      return super.getUnlocalizedName() + "." + BlockStoneSlabNew.EnumType.byMetadata(n).getUnlocalizedName();
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.stone_slab2);
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, new IProperty[]{SEAMLESS, VARIANT}) : new BlockState(this, new IProperty[]{HALF, VARIANT});
   }


   public enum EnumType implements IStringSerializable {
      RED_SANDSTONE(0, "red_sandstone", BlockSand.EnumType.RED_SAND.getMapColor());

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final MapColor mapColor;
   public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      private EnumType(int n, String str, MapColor mapColor2) {
         this.meta = n;
         this.name = str;
         this.mapColor = mapColor2;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public String getUnlocalizedName() {
         return this.name;
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
   }
}
