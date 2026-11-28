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

public class BlockPlanks extends Block {
   public static final PropertyEnum VARIANT;

   static {
      VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMapColor();
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(n));
   }

   public BlockPlanks() {
      super(Material.wood);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }


   public enum EnumType implements IStringSerializable {
      OAK(0, "oak", MapColor.woodColor),
      SPRUCE(1, "spruce", MapColor.obsidianColor),
      BIRCH(2, "birch", MapColor.sandColor),
      JUNGLE(3, "jungle", MapColor.dirtColor),
      ACACIA(4, "acacia", MapColor.adobeColor),
      DARK_OAK(5, "dark_oak", "big_oak", MapColor.brownColor);

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
      private final MapColor mapColor;
   @Override
      public String getName() {
         return this.name;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
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

      private EnumType(int n, String str, String str2, MapColor mapColor2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
         this.mapColor = mapColor2;
      }

      private EnumType(int n, String str, MapColor mapColor2) {
         this(n, str, str, mapColor2);
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
