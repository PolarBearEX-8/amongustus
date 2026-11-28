package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class BlockStone extends Block {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockStone.EnumType.class);

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return state.getValue(VARIANT) == BlockStone.EnumType.STONE ? Item.getItemFromBlock(Blocks.cobblestone) : Item.getItemFromBlock(Blocks.stone);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockStone.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockStone.EnumType.STONE.getUnlocalizedName() + ".name");
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockStone.EnumType)state.getValue(VARIANT)).getMapColor();
   }

   public BlockStone() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockStone.EnumType.STONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.byMetadata(n));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockStone.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockStone.EnumType enumType : BlockStone.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }


   public enum EnumType implements IStringSerializable {
      STONE(0, MapColor.stoneColor, "stone"),
      GRANITE(1, MapColor.dirtColor, "granite"),
      GRANITE_SMOOTH(2, MapColor.dirtColor, "smooth_granite", "graniteSmooth"),
      DIORITE(3, MapColor.quartzColor, "diorite"),
      DIORITE_SMOOTH(4, MapColor.quartzColor, "smooth_diorite", "dioriteSmooth"),
      ANDESITE(5, MapColor.stoneColor, "andesite"),
      ANDESITE_SMOOTH(6, MapColor.stoneColor, "smooth_andesite", "andesiteSmooth");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
      private final MapColor mapColor;
   public int getMetadata() {
         return this.meta;
      }

      private EnumType(int n, MapColor mapColor2, String str) {
         this(n, mapColor2, str, str);
      }

      private EnumType(int n, MapColor mapColor2, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
         this.mapColor = mapColor2;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
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
   }
}
