package client.util;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBlockOutputCommandSender;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StandardListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "execute";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray())
         : (
            textArray.length > 1 && textArray.length <= 4
               ? resolveListByTextArray(textArray, 1, blockPos)
               : (
                  textArray.length > 5 && textArray.length <= 8 && "detect".equals(textArray[4])
                     ? resolveListByTextArray(textArray, 5, blockPos)
                     : (textArray.length == 9 && "detect".equals(textArray[4]) ? resolveList(textArray, Block.iterableExtensionBridge.getSet()) : null)
               )
         );
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.execute.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 5) {
         throw new AimDispatcher("commands.execute.usage", new Object[0]);
      } else {
         Entity entity = resolveEntity(iCommandSender, textArray[0], Entity.class);
         double offset = computeValue(entity.posX, textArray[1], false);
         double offset2 = computeValue(entity.posY, textArray[2], false);
         double offset3 = computeValue(entity.posZ, textArray[3], false);
         BlockPos blockPos = new BlockPos(offset, offset2, offset3);
         byte byteValue = 4;
         if ("detect".equals(textArray[4]) && textArray.length > 10) {
            World worldObj = entity.getWorldObj();
            double offset4 = computeValue(offset, textArray[5], false);
            double offset5 = computeValue(offset2, textArray[6], false);
            double offset6 = computeValue(offset3, textArray[7], false);
            Block block = resolveBlock(iCommandSender, textArray[8]);
            int count = computeCount(textArray[9], -1, 15);
            BlockPos blockPos2 = new BlockPos(offset4, offset5, offset6);
            IBlockState blockState = worldObj.getBlockState(blockPos2);
            if (blockState.getBlock() != block || count >= 0 && blockState.getBlock().getIndex(blockState) != count) {
               throw new InternalExceptionAdapter("commands.execute.failed", new Object[]{"detect", entity.getText()});
            }

            byteValue = 10;
         }

         String text = resolveText(textArray, byteValue);
         CommandBlockOutputCommandSender commandBlockOutputCommandSender = new CommandBlockOutputCommandSender(this, entity, iCommandSender, blockPos, offset, offset2, offset3);
         ICommandManager iCommandManager = VanillaRunnable.getVanillaRunnable().getICommandManager();

         try {
            if (iCommandManager.computeCount(commandBlockOutputCommandSender, text) < 1) {
               throw new InternalExceptionAdapter("commands.execute.allInvocationsFailed", new Object[]{text});
            }
         } catch (Throwable throwable) {
            throw new InternalExceptionAdapter("commands.execute.failed", new Object[]{text, entity.getText()});
         }
      }
   }
}
