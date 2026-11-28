package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemEditableBook extends Item {

   private void resolveContents(ItemStack stack, EntityPlayer player) {
      if (stack != null && stack.getTagCompound() != null) {
         NBTTagCompound tagCompound = stack.getTagCompound();
         if (!tagCompound.getBoolean("resolved")) {
            tagCompound.setBoolean("resolved", true);
            if (validBookTagContents(tagCompound)) {
               NBTTagList tagList = tagCompound.getTagList("pages", 8);

               for (int i = 0; i < tagList.tagCount(); i++) {
                  String stringTagAt = tagList.getStringTagAt(i);

                  Object obj;
                  try {
                     obj = IChatComponent.Serializer.jsonToComponent(stringTagAt);
                     obj = ChatComponentProcessor.processComponent(player, (IChatComponent)obj, player);
                  } catch (Exception e) {
                     obj = new ChatComponentText(stringTagAt);
                  }

                  tagList.set(i, new NBTTagString(IChatComponent.Serializer.componentToJson((IChatComponent)obj)));
               }

               tagCompound.setTag("pages", tagList);
               if (player instanceof EntityPlayerMP && player.getCurrentEquippedItem() == stack) {
                  Slot slotFromInventory = player.openContainer.getSlotFromInventory(player.inventory, player.inventory.currentItem);
                  ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(0, slotFromInventory.slotNumber, stack));
               }
            }
         }
      }
   }

   public static int getGeneration(ItemStack stack) {
      return stack.getTagCompound().getInteger("generation");
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (!world.isRemote) {
         this.resolveContents(stack, player);
      }

      player.displayGUIBook(stack);
      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack;
   }

   public static boolean validBookTagContents(NBTTagCompound tag) {
      if (!ItemWritableBook.isNBTValid(tag)) {
         return false;
      } else if (!tag.hasKey("title", 8)) {
         return false;
      } else {
         String string = tag.getString("title");
         return string != null && string.length() <= 32 ? tag.hasKey("author", 8) : false;
      }
   }

   public ItemEditableBook() {
      this.setMaxStackSize(1);
   }

   @Override
   public String getItemStackDisplayName(ItemStack stack) {
      if (stack.hasTagCompound()) {
         String string = stack.getTagCompound().getString("title");
         if (!StringUtils.isNullOrEmpty(string)) {
            return string;
         }
      }

      return super.getItemStackDisplayName(stack);
   }

   @Override
   public boolean hasEffect(ItemStack stack) {
      return true;
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.hasTagCompound()) {
         NBTTagCompound tagCompound = stack.getTagCompound();
         String string = tagCompound.getString("author");
         if (!StringUtils.isNullOrEmpty(string)) {
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[]{string}));
         }

         list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("book.generation." + tagCompound.getInteger("generation")));
      }
   }
}
