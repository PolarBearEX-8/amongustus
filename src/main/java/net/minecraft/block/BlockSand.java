package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockSand extends BlockFalling {
   public static final PropertyEnum VARIANT;

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockSand.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockSand.EnumType.byMetadata(n));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockSand.EnumType.class);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockSand.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public BlockSand() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockSand.EnumType.SAND));
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockSand.EnumType enumType : BlockSand.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockSand.EnumType)state.getValue(VARIANT)).getMapColor();
   }


   public enum EnumType implements IStringSerializable {
      SAND(0, "sand", "default", MapColor.sandColor),
      RED_SAND(1, "red_sand", "red", MapColor.adobeColor);

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final MapColor mapColor;
      private final String unlocalizedName;
   static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      @Override
      public String toString() {
         return this.name;
      }

      public MapColor getMapColor() {
         return this.mapColor;
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

      public int getMetadata() {
         return this.meta;
      }

      private EnumType(int n, String str, String str2, MapColor mapColor2) {
         this.meta = n;
         this.name = str;
         this.mapColor = mapColor2;
         this.unlocalizedName = str2;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }
   }
}
