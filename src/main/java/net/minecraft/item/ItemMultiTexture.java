package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.Block;

public class ItemMultiTexture extends ItemBlock {
   public final Block theBlock;
   public final Function nameFunction;

   public ItemMultiTexture(Block block2, Block block3, String[] strings) {
      this(block2, block3, new ItemMultiTexture.TransformFunction(strings));
   }

   public ItemMultiTexture(Block block2, Block block3, Function function) {
      super(block2);
      this.theBlock = block3;
      this.nameFunction = function;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      return super.getUnlocalizedName() + "." + (String)this.nameFunction.apply(stack);
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }


   public static class TransformFunction implements Function {
      public final String[] namesByMeta;

      public TransformFunction(String[] strings) {
         this.namesByMeta = strings;
      }

      public String apply(ItemStack stack) {
         int metadata = stack.getMetadata();
         if (metadata < 0 || metadata >= this.namesByMeta.length) {
            metadata = 0;
         }

         return this.namesByMeta[metadata];
      }

      public Object apply(Object obj) {
         return this.apply((ItemStack)obj);
      }
   }
}
