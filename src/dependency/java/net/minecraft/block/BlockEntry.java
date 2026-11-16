package net.minecraft.block;

import client.util.StandardFunctionAdapter;
import com.google.common.base.Function;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockEntry extends ItemBlock {
   public final Block block2;
   public final Function function;

   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public String getText(ItemStack stack) {
      return super.getString() + "." + (String)this.function.apply(stack);
   }

   public BlockEntry(Block block, Block block3, String[] textArray) {
      this(block, block3, new StandardFunctionAdapter(textArray));
   }

   public BlockEntry(Block block, Block block3, Function mapper) {
      super(block);
      this.block2 = block3;
      this.function = mapper;
      this.processAction3(0);
      this.processAction(true);
   }
}
