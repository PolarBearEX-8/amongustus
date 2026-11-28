package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandGive extends CommandBase {

   public String[] getPlayers() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.give.usage", new Object[0]);
      } else {
         EntityPlayerMP player = getPlayer(commandSender, strings[0]);
         Item itemByText = getItemByText(commandSender, strings[1]);
         int n = strings.length >= 3 ? parseInt(strings[2], 1, 64) : 1;
         int n2 = strings.length >= 4 ? parseInt(strings[3]) : 0;
         ItemStack stack = new ItemStack(itemByText, n, n2);
         if (strings.length >= 5) {
            String unformattedText = getChatComponentFromNthArg(commandSender, strings, 4).getUnformattedText();

            try {
               stack.setTagCompound(JsonToNBT.getTagFromJson(unformattedText));
            } catch (NBTException e) {
               throw new CommandException("commands.give.tagError", new Object[]{e.getMessage()});
            }
         }

         boolean flag = player.inventory.addItemStackToInventory(stack);
         if (flag) {
            player.entityWorld.playSoundAtEntity(player, "random.pop", 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.inventoryContainer.detectAndSendChanges();
         }

         if (flag && stack.stackSize <= 0) {
            stack.stackSize = 1;
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, n);
            EntityItem entityItem = player.dropPlayerItemWithRandomChoice(stack, false);
            if (entityItem != null) {
               entityItem.makeFakeItem();
            }
         } else {
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, n - stack.stackSize);
            EntityItem entityItem2 = player.dropPlayerItemWithRandomChoice(stack, false);
            if (entityItem2 != null) {
               entityItem2.setNoPickupDelay();
               entityItem2.setOwner(player.getName());
            }
         }

         notifyOperators(commandSender, this, "commands.give.success", new Object[]{stack.getChatComponent(), n, player.getName()});
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.give.usage";
   }

   @Override
   public String getCommandName() {
      return "give";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, this.getPlayers())
         : (strings.length == 2 ? getListOfStringsMatchingLastWord(strings, Item.itemRegistry.getKeys()) : null);
   }
}
