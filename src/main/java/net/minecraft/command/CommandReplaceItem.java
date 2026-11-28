package net.minecraft.command;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandReplaceItem extends CommandBase {
   private static final Map SHORTCUTS = Maps.newHashMap();

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   private int getSlotForShortcut(String str) throws CommandException {
      if (!SHORTCUTS.containsKey(str)) {
         throw new CommandException("commands.generic.parameter.invalid", new Object[]{str});
      } else {
         return (Integer)SHORTCUTS.get(str);
      }
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return strings.length > 0 && strings[0].equals("entity") && n == 1;
   }

   static {
      for (int i = 0; i < 54; i++) {
         SHORTCUTS.put("slot.container." + i, i);
      }

      for (int j = 0; j < 9; j++) {
         SHORTCUTS.put("slot.hotbar." + j, j);
      }

      for (int k = 0; k < 27; k++) {
         SHORTCUTS.put("slot.inventory." + k, 9 + k);
      }

      for (int m = 0; m < 27; m++) {
         SHORTCUTS.put("slot.enderchest." + m, 200 + m);
      }

      for (int idx = 0; idx < 8; idx++) {
         SHORTCUTS.put("slot.villager." + idx, 300 + idx);
      }

      for (int index = 0; index < 15; index++) {
         SHORTCUTS.put("slot.horse." + index, 500 + index);
      }

      SHORTCUTS.put("slot.weapon", 99);
      SHORTCUTS.put("slot.armor.head", 103);
      SHORTCUTS.put("slot.armor.chest", 102);
      SHORTCUTS.put("slot.armor.legs", 101);
      SHORTCUTS.put("slot.armor.feet", 100);
      SHORTCUTS.put("slot.horse.saddle", 400);
      SHORTCUTS.put("slot.horse.armor", 401);
      SHORTCUTS.put("slot.horse.chest", 499);
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.replaceitem.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, new String[]{"entity", "block"})
         : (
            strings.length == 2 && strings[0].equals("entity")
               ? getListOfStringsMatchingLastWord(strings, this.getUsernames())
               : (
                  strings.length >= 2 && strings.length <= 4 && strings[0].equals("block")
                     ? getTabCompletionCoordinate(strings, 1, pos)
                     : (
                        (strings.length != 3 || !strings[0].equals("entity")) && (strings.length != 5 || !strings[0].equals("block"))
                           ? (
                              strings.length == 4 && strings[0].equals("entity") || strings.length == 6 && strings[0].equals("block")
                                 ? getListOfStringsMatchingLastWord(strings, Item.itemRegistry.getKeys())
                                 : null
                           )
                           : getListOfStringsMatchingLastWord(strings, SHORTCUTS.keySet())
                     )
               )
         );
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.replaceitem.usage", new Object[0]);
      } else {
         boolean flag;
         if (strings[0].equals("entity")) {
            flag = false;
         } else {
            if (!strings[0].equals("block")) {
               throw new WrongUsageException("commands.replaceitem.usage", new Object[0]);
            }

            flag = true;
         }

         int n;
         if (flag) {
            if (strings.length < 6) {
               throw new WrongUsageException("commands.replaceitem.block.usage", new Object[0]);
            }

            n = 4;
         } else {
            if (strings.length < 4) {
               throw new WrongUsageException("commands.replaceitem.entity.usage", new Object[0]);
            }

            n = 2;
         }

         int slotForShortcut = this.getSlotForShortcut(strings[n++]);

         Item item;
         try {
            item = getItemByText(commandSender, strings[n]);
         } catch (NumberInvalidException e) {
            if (Block.getBlockFromName(strings[n]) != Blocks.air) {
               throw e;
            }

            item = null;
         }

         n++;
         int n2 = strings.length > n ? parseInt(strings[n++], 1, 64) : 1;
         int n3 = strings.length > n ? parseInt(strings[n++]) : 0;
         ItemStack stack = new ItemStack(item, n2, n3);
         if (strings.length > n) {
            String unformattedText = getChatComponentFromNthArg(commandSender, strings, n).getUnformattedText();

            try {
               stack.setTagCompound(JsonToNBT.getTagFromJson(unformattedText));
            } catch (NBTException e2) {
               throw new CommandException("commands.replaceitem.tagError", new Object[]{e2.getMessage()});
            }
         }

         if (stack.getItem() == null) {
            stack = null;
         }

         if (flag) {
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
            BlockPos pos = parseBlockPos(commandSender, strings, 1, false);
            TileEntity tileEntity = commandSender.getEntityWorld().getTileEntity(pos);
            if (tileEntity == null || !(tileEntity instanceof IInventory)) {
               throw new CommandException("commands.replaceitem.noContainer", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
            }

            IInventory inventory = (IInventory)tileEntity;
            if (slotForShortcut >= 0 && slotForShortcut < inventory.getSizeInventory()) {
               inventory.setInventorySlotContents(slotForShortcut, stack);
            }
         } else {
            Entity entity = getEntity(commandSender, strings[1]);
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
            if (entity instanceof EntityPlayer) {
               ((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
            }

            if (!entity.replaceItemInInventory(slotForShortcut, stack)) {
               throw new CommandException("commands.replaceitem.failed", new Object[]{slotForShortcut, n2, stack == null ? "Air" : stack.getChatComponent()});
            }

            if (entity instanceof EntityPlayer) {
               ((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
            }
         }

         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, n2);
         notifyOperators(commandSender, this, "commands.replaceitem.success", new Object[]{slotForShortcut, n2, stack == null ? "Air" : stack.getChatComponent()});
      }
   }

   @Override
   public String getCommandName() {
      return "replaceitem";
   }

   public String[] getUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }
}
