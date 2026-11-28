package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirt extends Block {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDirt.DirtType.class);
   public static final PropertyBool SNOWY = PropertyBool.create("snowy");

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockDirt.DirtType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT, SNOWY});
   }

   public BlockDirt() {
      super(Material.ground);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDirt.DirtType.DIRT).withProperty(SNOWY, Boolean.FALSE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(this, 1, BlockDirt.DirtType.DIRT.getMetadata()));
      list.add(new ItemStack(this, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()));
      list.add(new ItemStack(this, 1, BlockDirt.DirtType.PODZOL.getMetadata()));
   }

   @Override
   public int damageDropped(IBlockState state) {
      BlockDirt.DirtType value = (BlockDirt.DirtType)state.getValue(VARIANT);
      if (value == BlockDirt.DirtType.PODZOL) {
         value = BlockDirt.DirtType.DIRT;
      }

      return value.getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockDirt.DirtType.byMetadata(n));
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock() != this ? 0 : ((BlockDirt.DirtType)blockState2.getValue(VARIANT)).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockDirt.DirtType)state.getValue(VARIANT)).getMapColor();
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (state.getValue(VARIANT) == BlockDirt.DirtType.PODZOL) {
         Block block = world.getBlockState2(n, n2 + 1, n3).getBlock();
         state = state.withProperty(SNOWY, block == Blocks.snow || block == Blocks.snow_layer);
      }

      return state;
   }


   public enum DirtType implements IStringSerializable {
      DIRT(0, "dirt", "default", MapColor.dirtColor),
      COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.dirtColor),
      PODZOL(2, "podzol", MapColor.obsidianColor);

   private static final DirtType[] METADATA_LOOKUP = new DirtType[values().length];
      private final int metadata;
      private final String name;
      private final String unlocalizedName;
      private final MapColor mapColor;
   public int getMetadata() {
         return this.metadata;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public static DirtType byMetadata(int n) {
         if (n < 0 || n >= METADATA_LOOKUP.length) {
            n = 0;
         }

         return METADATA_LOOKUP[n];
      }

      private DirtType(int n, String str, String str2, MapColor mapColor2) {
         this.metadata = n;
         this.name = str;
         this.unlocalizedName = str2;
         this.mapColor = mapColor2;
      }

      static {
         for (DirtType dirtType : values()) {
            METADATA_LOOKUP[dirtType.getMetadata()] = dirtType;
         }
      }

      private DirtType(int n, String str, MapColor mapColor2) {
         this(n, str, str, mapColor2);
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }
   }
}
