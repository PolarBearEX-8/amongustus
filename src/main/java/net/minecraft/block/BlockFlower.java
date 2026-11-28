package net.minecraft.block;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BlockFlower extends BlockBush {
   public PropertyEnum type;

   public BlockFlower() {
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(
               this.getTypeProperty(),
               this.getBlockType() == BlockFlower.EnumFlowerColor.RED ? BlockFlower.EnumFlowerType.POPPY : BlockFlower.EnumFlowerType.DANDELION
            )
      );
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockFlower.EnumFlowerType)state.getValue(this.getTypeProperty())).getMeta();
   }

   public IProperty getTypeProperty() {
      if (this.type == null) {
         this.type = PropertyEnum.create("type", BlockFlower.EnumFlowerType.class, new BlockFlower.FilterPredicate(this));
      }

      return this.type;
   }

   @Override
   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.XZ;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockFlower.EnumFlowerType)state.getValue(this.getTypeProperty())).getMeta();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(this.getTypeProperty(), BlockFlower.EnumFlowerType.getType(this.getBlockType(), n));
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockFlower.EnumFlowerType enumFlowerType : BlockFlower.EnumFlowerType.getTypes(this.getBlockType())) {
         list.add(new ItemStack(item, 1, enumFlowerType.getMeta()));
      }
   }

   public abstract BlockFlower.EnumFlowerColor getBlockType();

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{this.getTypeProperty()});
   }


   public static class FilterPredicate implements Predicate {
      public final BlockFlower blockFlower;

      public boolean apply(Object obj) {
         return this.apply((BlockFlower.EnumFlowerType)obj);
      }

      public FilterPredicate(BlockFlower blockFlower2) {
         this.blockFlower = blockFlower2;
      }

      public boolean apply(BlockFlower.EnumFlowerType enumFlowerType) {
         return enumFlowerType.getBlockType() == this.blockFlower.getBlockType();
      }
   }

   public enum EnumFlowerType implements IStringSerializable {
      DANDELION(BlockFlower.EnumFlowerColor.YELLOW, 0, "dandelion"),
      POPPY(BlockFlower.EnumFlowerColor.RED, 0, "poppy"),
      BLUE_ORCHID(BlockFlower.EnumFlowerColor.RED, 1, "blue_orchid", "blueOrchid"),
      ALLIUM(BlockFlower.EnumFlowerColor.RED, 2, "allium"),
      HOUSTONIA(BlockFlower.EnumFlowerColor.RED, 3, "houstonia"),
      RED_TULIP(BlockFlower.EnumFlowerColor.RED, 4, "red_tulip", "tulipRed"),
      ORANGE_TULIP(BlockFlower.EnumFlowerColor.RED, 5, "orange_tulip", "tulipOrange"),
      WHITE_TULIP(BlockFlower.EnumFlowerColor.RED, 6, "white_tulip", "tulipWhite"),
      PINK_TULIP(BlockFlower.EnumFlowerColor.RED, 7, "pink_tulip", "tulipPink"),
      OXEYE_DAISY(BlockFlower.EnumFlowerColor.RED, 8, "oxeye_daisy", "oxeyeDaisy");

   private static final EnumFlowerType[][] TYPES_FOR_BLOCK = new EnumFlowerType[BlockFlower.EnumFlowerColor.values().length][];
      private final BlockFlower.EnumFlowerColor blockType;
      private final int meta;
      private final String name;
      private final String unlocalizedName;
   @Override
      public String toString() {
         return this.name;
      }

      public static EnumFlowerType getType(BlockFlower.EnumFlowerColor enumFlowerColor, int n) {
         EnumFlowerType[] enumFlowerTypes = TYPES_FOR_BLOCK[enumFlowerColor.ordinal()];
         if (n < 0 || n >= enumFlowerTypes.length) {
            n = 0;
         }

         return enumFlowerTypes[n];
      }

      static {
         for (BlockFlower.EnumFlowerColor enumFlowerColor : BlockFlower.EnumFlowerColor.values()) {
            Collection items = Collections2.filter(Lists.newArrayList(values()), new BlockFlower.EnumFlowerType.FilterPredicate(enumFlowerColor));
            TYPES_FOR_BLOCK[enumFlowerColor.ordinal()] = (EnumFlowerType[])items.toArray(new EnumFlowerType[0]);
         }
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public BlockFlower.EnumFlowerColor getBlockType() {
         return this.blockType;
      }

      public int getMeta() {
         return this.meta;
      }

      private EnumFlowerType(BlockFlower.EnumFlowerColor enumFlowerColor, int n, String str, String str2) {
         this.blockType = enumFlowerColor;
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      public static EnumFlowerType[] getTypes(BlockFlower.EnumFlowerColor enumFlowerColor) {
         return TYPES_FOR_BLOCK[enumFlowerColor.ordinal()];
      }

      private EnumFlowerType(BlockFlower.EnumFlowerColor enumFlowerColor, int n, String str) {
         this(enumFlowerColor, n, str, str);
      }


      public static final class FilterPredicate implements Predicate {
         public final BlockFlower.EnumFlowerColor flowerColor;

         public boolean apply(BlockFlower.EnumFlowerType enumFlowerType) {
            return enumFlowerType.getBlockType() == this.flowerColor;
         }

         public FilterPredicate(BlockFlower.EnumFlowerColor enumFlowerColor) {
            this.flowerColor = enumFlowerColor;
         }

         public boolean apply(Object obj) {
            return this.apply((BlockFlower.EnumFlowerType)obj);
         }
      }
   }

   public enum EnumFlowerColor {
      YELLOW,
      RED;

   public BlockFlower getBlock() {
         return this == YELLOW ? Blocks.yellow_flower : Blocks.red_flower;
      }
   }
}
