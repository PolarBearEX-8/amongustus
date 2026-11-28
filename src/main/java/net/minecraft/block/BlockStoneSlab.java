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
import net.minecraft.world.World;

public abstract class BlockStoneSlab extends BlockSlab {
   public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockStoneSlab.EnumType.class);

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockStoneSlab.EnumType)state.getValue(VARIANT)).getMapColor();
   }

   @Override
   public Object getVariant(ItemStack stack) {
      return BlockStoneSlab.EnumType.byMetadata(stack.getMetadata() & 7);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockStoneSlab.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.stone_slab);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockStoneSlab.EnumType.byMetadata(n & 7));
      if (this.isDouble()) {
         state = state.withProperty(SEAMLESS, (n & 8) != 0);
      } else {
         state = state.withProperty(HALF, (n & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return state;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      if (item != Item.getItemFromBlock(Blocks.double_stone_slab)) {
         for (BlockStoneSlab.EnumType enumType : BlockStoneSlab.EnumType.values()) {
            if (enumType != BlockStoneSlab.EnumType.WOOD) {
               list.add(new ItemStack(item, 1, enumType.getMetadata()));
            }
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockStoneSlab.EnumType)state.getValue(VARIANT)).getMetadata();
      if (this.isDouble()) {
         if ((Boolean)state.getValue(SEAMLESS)) {
            n |= 8;
         }
      } else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
         n |= 8;
      }

      return n;
   }

   @Override
   public String getUnlocalizedName(int n) {
      return super.getUnlocalizedName() + "." + BlockStoneSlab.EnumType.byMetadata(n).getUnlocalizedName();
   }

   public BlockStoneSlab() {
      super(Material.rock);
      IBlockState baseState = this.blockState.getBaseState();
      if (this.isDouble()) {
         baseState = baseState.withProperty(SEAMLESS, Boolean.FALSE);
      } else {
         baseState = baseState.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(baseState.withProperty(VARIANT, BlockStoneSlab.EnumType.STONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IProperty getVariantProperty() {
      return VARIANT;
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, new IProperty[]{SEAMLESS, VARIANT}) : new BlockState(this, new IProperty[]{HALF, VARIANT});
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.stone_slab);
   }


   public enum EnumType implements IStringSerializable {
      STONE(0, MapColor.stoneColor, "stone"),
      SAND(1, MapColor.sandColor, "sandstone", "sand"),
      WOOD(2, MapColor.woodColor, "wood_old", "wood"),
      COBBLESTONE(3, MapColor.stoneColor, "cobblestone", "cobble"),
      BRICK(4, MapColor.redColor, "brick"),
      SMOOTHBRICK(5, MapColor.stoneColor, "stone_brick", "smoothStoneBrick"),
      NETHERBRICK(6, MapColor.netherrackColor, "nether_brick", "netherBrick"),
      QUARTZ(7, MapColor.quartzColor, "quartz");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final MapColor mapColor;
      private final String name;
      private final String unlocalizedName;
   public MapColor getMapColor() {
         return this.mapColor;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      private EnumType(int n, MapColor mapColor2, String str, String str2) {
         this.meta = n;
         this.mapColor = mapColor2;
         this.name = str;
         this.unlocalizedName = str2;
      }

      @Override
      public String toString() {
         return this.name;
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      private EnumType(int n, MapColor mapColor2, String str) {
         this(n, mapColor2, str, str);
      }

      public int getMetadata() {
         return this.meta;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }
   }
}
