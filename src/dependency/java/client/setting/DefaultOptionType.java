package client.setting;

import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.Blocks;

import client.event.PrimaryListener;
import client.util.DampenerTransformer;
import client.util.IBlockState;

public enum DefaultOptionType implements PrimaryListener {
   STONE(0, "stone") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.STONE);
      }
   },
   COBBLESTONE(1, "cobblestone", "cobble") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.block65.getDefaultState();
      }
   },
   STONEBRICK(2, "stone_brick", "brick") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.block42.getDefaultState().resolveIBlockState(BlockStonebrick.immutableSetEntry, CombatTimeoutProperty.DEFAULT);
      }
   },
   MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.block42.getDefaultState().resolveIBlockState(BlockStonebrick.immutableSetEntry, CombatTimeoutProperty.MOSSY);
      }
   },
   CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.block42.getDefaultState().resolveIBlockState(BlockStonebrick.immutableSetEntry, CombatTimeoutProperty.CRACKED);
      }
   },
   CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {
      @Override
      public IBlockState getIBlockState() {
         return Blocks.block42.getDefaultState().resolveIBlockState(BlockStonebrick.immutableSetEntry, CombatTimeoutProperty.CHISELED);
      }
   };

   public static final DefaultOptionType[] ENUM_VALUES2 = new DefaultOptionType[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final DefaultOptionType[] ENUM_VALUES = values();

   public abstract IBlockState getIBlockState();

   @Override
   public String getText() {
      return this.labelText;
   }

   public static DefaultOptionType resolveDefaultOptionType(IBlockState blockState) {
      for (DefaultOptionType defaultOptionType : values()) {
         if (blockState == defaultOptionType.getIBlockState()) {
            return defaultOptionType;
         }
      }

      return STONE;
   }

   public static DefaultOptionType resolveDefaultOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   private DefaultOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }

   private DefaultOptionType(int count2, String text) {
      this(count2, text, text);
   }

   private DefaultOptionType(int count2, String text, String text2, DampenerTransformer dampenerTransformer) {
      this(count2, text, text2);
   }

   private DefaultOptionType(int count2, String text, DampenerTransformer dampenerTransformer) {
      this(count2, text);
   }

   static {

      for (DefaultOptionType defaultOptionType : values()) {
         ENUM_VALUES2[defaultOptionType.getCount()] = defaultOptionType;
      }
   }

   @Override
   public String toString() {
      return this.labelText;
   }
}
