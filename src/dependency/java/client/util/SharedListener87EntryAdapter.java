package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class SharedListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "testfor";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.testfor.usage";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.testfor.usage", new Object[0]);
      } else {
         Entity entity = resolveEntity(iCommandSender, textArray[0]);
         NBTTagCompound tagCompound = null;
         if (textArray.length >= 2) {
            try {
               tagCompound = DampenerEstimator.resolveNBTTagCompound(resolveText(textArray, 1));
            } catch (SecondaryExceptionAdapter exception) {
               throw new InternalExceptionAdapter("commands.testfor.tagError", new Object[]{exception.getMessage()});
            }
         }

         if (tagCompound != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            entity.applyNBTTagCompound3(tagCompound2);
            if (!AuxiliaryNameManager.matchesLISTManager(tagCompound, tagCompound2, true)) {
               throw new InternalExceptionAdapter("commands.testfor.failure", new Object[]{entity.getText()});
            }
         }

         applyICommandSender(iCommandSender, this, "commands.testfor.success", new Object[]{entity.getText()});
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }
}
