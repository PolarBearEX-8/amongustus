package net.minecraft.village;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;

public class MerchantRecipeList extends ArrayList {

   public NBTTagCompound getRecipiesAsTags() {
      NBTTagCompound tag = new NBTTagCompound();
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.size(); i++) {
         tagList.appendTag(((MerchantRecipe)this.get(i)).writeToTags());
      }

      tag.setTag("Recipes", tagList);
      return tag;
   }

   public static MerchantRecipeList readFromBuf(PacketBuffer buf) throws IOException {
      MerchantRecipeList merchantRecipeList = new MerchantRecipeList();
      int n = buf.readByte() & 255;

      for (int i = 0; i < n; i++) {
         ItemStack stack = buf.readItemStackFromBuffer();
         ItemStack stack2 = buf.readItemStackFromBuffer();
         ItemStack stack3 = null;
         if (buf.readBoolean()) {
            stack3 = buf.readItemStackFromBuffer();
         }

         boolean flag = buf.readBoolean();
         int n2 = buf.readInt();
         int n3 = buf.readInt();
         MerchantRecipe merchantRecipe = new MerchantRecipe(stack, stack3, stack2, n2, n3);
         if (flag) {
            merchantRecipe.compensateToolUses();
         }

         merchantRecipeList.add(merchantRecipe);
      }

      return merchantRecipeList;
   }

   public void writeToBuf(PacketBuffer buf) {
      buf.writeByte((byte)(this.size() & 0xFF));

      for (int i = 0; i < this.size(); i++) {
         MerchantRecipe merchantRecipe = (MerchantRecipe)this.get(i);
         buf.writeItemStackToBuffer(merchantRecipe.getItemToBuy());
         buf.writeItemStackToBuffer(merchantRecipe.getItemToSell());
         ItemStack secondItemToBuy = merchantRecipe.getSecondItemToBuy();
         buf.writeBoolean(secondItemToBuy != null);
         if (secondItemToBuy != null) {
            buf.writeItemStackToBuffer(secondItemToBuy);
         }

         buf.writeBoolean(merchantRecipe.isRecipeDisabled());
         buf.writeInt(merchantRecipe.getToolUses());
         buf.writeInt(merchantRecipe.getMaxTradeUses());
      }
   }

   public MerchantRecipeList(NBTTagCompound tag) {
      this.readRecipiesFromTags(tag);
   }

   public MerchantRecipeList() {
   }

   public void readRecipiesFromTags(NBTTagCompound tag) {
      NBTTagList tagList = tag.getTagList("Recipes", 10);

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         this.add(new MerchantRecipe(compoundTagAt));
      }
   }

   private boolean areItemStacksEqual(ItemStack stack, ItemStack stack2) {
      return ItemStack.areItemsEqual(stack, stack2)
         && (!stack2.hasTagCompound() || stack.hasTagCompound() && NBTUtil.areNBTEquals(stack2.getTagCompound(), stack.getTagCompound(), false));
   }

   public MerchantRecipe canRecipeBeUsed(ItemStack stack, ItemStack stack2, int n) {
      if (n > 0 && n < this.size()) {
         MerchantRecipe merchantRecipe = (MerchantRecipe)this.get(n);
         return this.areItemStacksEqual(stack, merchantRecipe.getItemToBuy())
               && (stack2 == null && !merchantRecipe.hasSecondItemToBuy() || merchantRecipe.hasSecondItemToBuy() && this.areItemStacksEqual(stack2, merchantRecipe.getSecondItemToBuy()))
               && stack.stackSize >= merchantRecipe.getItemToBuy().stackSize
               && (!merchantRecipe.hasSecondItemToBuy() || stack2.stackSize >= merchantRecipe.getSecondItemToBuy().stackSize)
            ? merchantRecipe
            : null;
      } else {
         for (int i = 0; i < this.size(); i++) {
            MerchantRecipe merchantRecipe2 = (MerchantRecipe)this.get(i);
            if (this.areItemStacksEqual(stack, merchantRecipe2.getItemToBuy())
               && stack.stackSize >= merchantRecipe2.getItemToBuy().stackSize
               && (
                  !merchantRecipe2.hasSecondItemToBuy() && stack2 == null
                     || merchantRecipe2.hasSecondItemToBuy() && this.areItemStacksEqual(stack2, merchantRecipe2.getSecondItemToBuy()) && stack2.stackSize >= merchantRecipe2.getSecondItemToBuy().stackSize
               )) {
               return merchantRecipe2;
            }
         }

         return null;
      }
   }
}
