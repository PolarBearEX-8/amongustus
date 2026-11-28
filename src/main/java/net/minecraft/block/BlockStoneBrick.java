package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockStoneBrick extends Block {
   public static final PropertyEnum VARIANT;
   public static final int DEFAULT_META = BlockStoneBrick.EnumType.DEFAULT.getMetadata();
   public static final int MOSSY_META = BlockStoneBrick.EnumType.MOSSY.getMetadata();
   public static final int CRACKED_META = BlockStoneBrick.EnumType.CRACKED.getMetadata();
   public static final int CHISELED_META = BlockStoneBrick.EnumType.CHISELED.getMetadata();

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockStoneBrick.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   public BlockStoneBrick() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockStoneBrick.EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockStoneBrick.EnumType.byMetadata(n));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockStoneBrick.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockStoneBrick.EnumType enumType : BlockStoneBrick.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockStoneBrick.EnumType.class);
   }


   public enum EnumType implements IStringSerializable {
      DEFAULT(0, "stonebrick", "default"),
      MOSSY(1, "mossy_stonebrick", "mossy"),
      CRACKED(2, "cracked_stonebrick", "cracked"),
      CHISELED(3, "chiseled_stonebrick", "chiseled");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
   private EnumType(int n, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public int getMetadata() {
         return this.meta;
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      @Override
      public String toString() {
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
