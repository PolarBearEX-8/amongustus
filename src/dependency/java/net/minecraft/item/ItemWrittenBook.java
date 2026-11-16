package net.minecraft.item;

import client.setting.ClientSettingsBlackType;
import client.util.AngleMonitor;
import client.util.ClientBrandHelper;
import client.util.HandlerStringEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemWrittenBook extends Item {
   @Override
   public boolean processAction2(ItemStack stack) {
      return true;
   }

   public static int computeCount(ItemStack stack) {
      return stack.getStackTagCompound().computeCount("generation");
   }

   private void applyItemStackByItemStack(ItemStack stack, EntityPlayer player) {
      if (stack != null && stack.getStackTagCompound() != null) {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         if (!stackTagCompound.matchesText("resolved")) {
            stackTagCompound.applyText("resolved", true);
            if (matchesNBTTagCompound(stackTagCompound)) {
               NBTTagList extendedLoggerEntry = stackTagCompound.getExtendedLoggerEntry("pages", 8);

               for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
                  String text = extendedLoggerEntry.resolveText(index);

                  Object entry;
                  try {
                     entry = ChatComponentSerializer.parseChatComponent(text);
                     entry = AngleMonitor.resolveIChatComponentByICommandSender(player, (IChatComponent)entry, player);
                  } catch (Exception exception) {
                     entry = new ChatComponentText(text);
                  }

                  extendedLoggerEntry.applyLISTManager(index, new HandlerStringEntry(ChatComponentSerializer.toJson((IChatComponent)entry)));
               }

               stackTagCompound.applyText("pages", extendedLoggerEntry);
               if (player instanceof EntityPlayerAdapter && player.getHeldItem() == stack) {
                  Slot slot = player.container2.resolveSlot(player.inventoryPlayer, player.inventoryPlayer.currentItem);
                  ((EntityPlayerAdapter)player).auxiliaryListenerAdapter4.applyPacket(new S2FPacketSetSlot(0, slot.indexOffset1, stack));
               }
            }
         }
      }
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (!world.state) {
         this.applyItemStackByItemStack(stack, player);
      }

      player.applyItemStack(stack);
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack;
   }

   @Override
   public String resolveText(ItemStack stack) {
      if (stack.canExecute()) {
         String text = stack.getStackTagCompound().resolveText("title");
         if (!ClientBrandHelper.isEmpty(text)) {
            return text;
         }
      }

      return super.resolveText(stack);
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.canExecute()) {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         String text = stackTagCompound.resolveText("author");
         if (!ClientBrandHelper.isEmpty(text)) {
            list.add(ClientSettingsBlackType.GRAY + I18n.resolveText("book.byAuthor", new Object[]{text}));
         }

         list.add(ClientSettingsBlackType.GRAY + I18n.getString2("book.generation." + stackTagCompound.computeCount("generation")));
      }
   }

   public static boolean matchesNBTTagCompound(NBTTagCompound tagCompound) {
      if (!ItemWritableBook.matchesNBTTagCompound(tagCompound)) {
         return false;
      } else if (!tagCompound.matchesText("title", 8)) {
         return false;
      } else {
         String text = tagCompound.resolveText("title");
         return text != null && text.length() <= 32 ? tagCompound.matchesText("author", 8) : false;
      }
   }

   public ItemWrittenBook() {
      this.processAction4(1);
   }
}
