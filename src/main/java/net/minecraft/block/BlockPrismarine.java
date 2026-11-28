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
import net.minecraft.util.StatCollector;

public class BlockPrismarine extends Block {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockPrismarine.EnumType.class);
   public static final int ROUGH_META = BlockPrismarine.EnumType.ROUGH.getMetadata();
   public static final int BRICKS_META = BlockPrismarine.EnumType.BRICKS.getMetadata();
   public static final int DARK_META = BlockPrismarine.EnumType.DARK.getMetadata();

   @Override
   public MapColor getMapColor(IBlockState state) {
      return state.getValue(VARIANT) == BlockPrismarine.EnumType.ROUGH ? MapColor.cyanColor : MapColor.diamondColor;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockPrismarine.EnumType.ROUGH.getUnlocalizedName() + ".name");
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPrismarine.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockPrismarine.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT});
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, ROUGH_META));
      list.add(new ItemStack(item, 1, BRICKS_META));
      list.add(new ItemStack(item, 1, DARK_META));
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockPrismarine.EnumType.byMetadata(n));
   }

   public BlockPrismarine() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPrismarine.EnumType.ROUGH));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }


   public enum EnumType implements IStringSerializable {
      ROUGH(0, "prismarine", "rough"),
      BRICKS(1, "prismarine_bricks", "bricks"),
      DARK(2, "dark_prismarine", "dark");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
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

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      private EnumType(int n, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public int getMetadata() {
         return this.meta;
      }
   }
}
