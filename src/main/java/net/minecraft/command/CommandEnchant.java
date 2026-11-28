package net.minecraft.command;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandEnchant extends CommandBase {

   public String[] getListOfPlayers() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.enchant.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandName() {
      return "enchant";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.enchant.usage", new Object[0]);
      } else {
         EntityPlayerMP player = getPlayer(commandSender, strings[0]);
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);

         int n;
         try {
            n = parseInt(strings[1], 0);
         } catch (NumberInvalidException e) {
            Enchantment enchantmentByLocation = Enchantment.getEnchantmentByLocation(strings[1]);
            if (enchantmentByLocation == null) {
               throw e;
            }

            n = enchantmentByLocation.effectId;
         }

         int n2 = 1;
         ItemStack currentEquippedItem = player.getCurrentEquippedItem();
         if (currentEquippedItem == null) {
            throw new CommandException("commands.enchant.noItem", new Object[0]);
         } else {
            Enchantment enchantmentById = Enchantment.getEnchantmentById(n);
            if (enchantmentById == null) {
               throw new NumberInvalidException("commands.enchant.notFound", new Object[]{n});
            } else if (!enchantmentById.canApply(currentEquippedItem)) {
               throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if (strings.length >= 3) {
                  n2 = parseInt(strings[2], enchantmentById.getMinLevel(), enchantmentById.getMaxLevel());
               }

               if (currentEquippedItem.hasTagCompound()) {
                  NBTTagList enchantmentTagList = currentEquippedItem.getEnchantmentTagList();
                  if (enchantmentTagList != null) {
                     for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
                        short s = enchantmentTagList.getCompoundTagAt(i).getShort("id");
                        if (Enchantment.getEnchantmentById(s) != null) {
                           Enchantment enchantmentById2 = Enchantment.getEnchantmentById(s);
                           if (!enchantmentById2.canApplyTogether(enchantmentById)) {
                              throw new CommandException(
                                 "commands.enchant.cantCombine",
                                 new Object[]{enchantmentById.getTranslatedName(n2), enchantmentById2.getTranslatedName(enchantmentTagList.getCompoundTagAt(i).getShort("lvl"))}
                              );
                           }
                        }
                     }
                  }
               }

               currentEquippedItem.addEnchantment(enchantmentById, n2);
               notifyOperators(commandSender, this, "commands.enchant.success", new Object[0]);
               commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 1);
            }
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, this.getListOfPlayers())
         : (strings.length == 2 ? getListOfStringsMatchingLastWord(strings, Enchantment.getLocationNames()) : null);
   }
}
