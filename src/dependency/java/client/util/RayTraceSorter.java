package client.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class RayTraceSorter extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.entitydata.usage";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.entitydata.usage", new Object[0]);
      } else {
         Entity entity = resolveEntity(iCommandSender, textArray[0]);
         if (entity instanceof EntityPlayer) {
            throw new InternalExceptionAdapter("commands.entitydata.noPlayers", new Object[]{entity.getChatComponent()});
         } else {
            NBTTagCompound tagCompound = new NBTTagCompound();
            entity.applyNBTTagCompound3(tagCompound);
            NBTTagCompound lISTManager = (NBTTagCompound)tagCompound.getLISTManager();

            NBTTagCompound tagCompound2;
            try {
               tagCompound2 = DampenerEstimator.resolveNBTTagCompound(resolveIChatComponent(iCommandSender, textArray, 1).getString());
            } catch (SecondaryExceptionAdapter exception) {
               throw new InternalExceptionAdapter("commands.entitydata.tagError", new Object[]{exception.getMessage()});
            }

            tagCompound2.remove("UUIDMost");
            tagCompound2.remove("UUIDLeast");
            tagCompound.applyNBTTagCompound(tagCompound2);
            if (tagCompound.equals(lISTManager)) {
               throw new InternalExceptionAdapter("commands.entitydata.failed", new Object[]{tagCompound.toString()});
            } else {
               entity.applyNBTTagCompound2(tagCompound);
               applyICommandSender(iCommandSender, this, "commands.entitydata.success", new Object[]{tagCompound.toString()});
            }
         }
      }
   }

   @Override
   public String getText() {
      return "entitydata";
   }
}
