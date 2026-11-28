package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandClearInventory extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, this.getPlayerUsernames())
         : (strings.length == 2 ? getListOfStringsMatchingLastWord(strings, Item.itemRegistry.getKeys()) : null);
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   public String[] getPlayerUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.clear.usage";
   }

   @Override
   public String getCommandName() {
      return "clear";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      EntityPlayerMP entityPlayerMP = strings.length == 0 ? getCommandSenderAsPlayer(commandSender) : getPlayer(commandSender, strings[0]);
      Item item = strings.length >= 2 ? getItemByText(commandSender, strings[1]) : null;
      int n = strings.length >= 3 ? parseInt(strings[2], -1) : -1;
      int n2 = strings.length >= 4 ? parseInt(strings[3], -1) : -1;
      NBTTagCompound tag = null;
      if (strings.length >= 5) {
         try {
            tag = JsonToNBT.getTagFromJson(buildString(strings, 4));
         } catch (NBTException e) {
            throw new CommandException("commands.clear.tagError", new Object[]{e.getMessage()});
         }
      }

      if (strings.length >= 2 && item == null) {
         throw new CommandException("commands.clear.failure", new Object[]{entityPlayerMP.getName()});
      } else {
         int n3 = entityPlayerMP.inventory.clearMatchingItems(item, n, n2, tag);
         entityPlayerMP.inventoryContainer.detectAndSendChanges();
         if (!entityPlayerMP.capabilities.isCreativeMode) {
            entityPlayerMP.updateHeldItem();
         }

         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, n3);
         if (n3 == 0) {
            throw new CommandException("commands.clear.failure", new Object[]{entityPlayerMP.getName()});
         } else {
            if (n2 == 0) {
               commandSender.addChatMessage(new ChatComponentTranslation("commands.clear.testing", new Object[]{entityPlayerMP.getName(), n3}));
            } else {
               notifyOperators(commandSender, this, "commands.clear.success", new Object[]{entityPlayerMP.getName(), n3});
            }
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }
}
