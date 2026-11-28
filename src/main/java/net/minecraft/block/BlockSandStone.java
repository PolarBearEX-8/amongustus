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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockSandStone extends Block {
   public static final PropertyEnum TYPE;

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockSandStone.EnumType enumType : BlockSandStone.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(TYPE, BlockSandStone.EnumType.byMetadata(n));
   }

   static {
      TYPE = PropertyEnum.create("type", BlockSandStone.EnumType.class);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockSandStone.EnumType)state.getValue(TYPE)).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.sandColor;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockSandStone.EnumType)state.getValue(TYPE)).getMetadata();
   }

   public BlockSandStone() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockSandStone.EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{TYPE});
   }


   public enum EnumType implements IStringSerializable {
      DEFAULT(0, "sandstone", "default"),
      CHISELED(1, "chiseled_sandstone", "chiseled"),
      SMOOTH(2, "smooth_sandstone", "smooth");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int metadata;
      private final String name;
      private final String unlocalizedName;
   public int getMetadata() {
         return this.metadata;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
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

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      @Override
      public String getName() {
         return this.name;
      }

      private EnumType(int n, String str, String str2) {
         this.metadata = n;
         this.name = str;
         this.unlocalizedName = str2;
      }
   }
}
