package net.minecraft.village;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MerchantRecipe {
   private ItemStack itemToBuy;
   private ItemStack secondItemToBuy;
   private ItemStack itemToSell;
   private int toolUses;
   private int maxTradeUses;
   private boolean rewardsExp;

   public void incrementToolUses() {
      this.toolUses++;
   }

   public MerchantRecipe(NBTTagCompound tag) {
      this.readFromTags(tag);
   }

   public MerchantRecipe(ItemStack stack, ItemStack stack2) {
      this(stack, (ItemStack)null, stack2);
   }

   public int getToolUses() {
      return this.toolUses;
   }

   public ItemStack getItemToSell() {
      return this.itemToSell;
   }

   public void compensateToolUses() {
      this.toolUses = this.maxTradeUses;
   }

   public MerchantRecipe(ItemStack stack, ItemStack stack2, ItemStack stack3) {
      this(stack, stack2, stack3, 0, 7);
   }

   public int getMaxTradeUses() {
      return this.maxTradeUses;
   }

   public ItemStack getSecondItemToBuy() {
      return this.secondItemToBuy;
   }

   public ItemStack getItemToBuy() {
      return this.itemToBuy;
   }

   public void increaseMaxTradeUses(int n) {
      this.maxTradeUses += n;
   }

   public NBTTagCompound writeToTags() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setTag("buy", this.itemToBuy.writeToNBT(new NBTTagCompound()));
      tag.setTag("sell", this.itemToSell.writeToNBT(new NBTTagCompound()));
      if (this.secondItemToBuy != null) {
         tag.setTag("buyB", this.secondItemToBuy.writeToNBT(new NBTTagCompound()));
      }

      tag.setInteger("uses", this.toolUses);
      tag.setInteger("maxUses", this.maxTradeUses);
      tag.setBoolean("rewardExp", this.rewardsExp);
      return tag;
   }

   public boolean isRecipeDisabled() {
      return this.toolUses >= this.maxTradeUses;
   }

   public MerchantRecipe(ItemStack stack, Item item) {
      this(stack, new ItemStack(item));
   }

   public boolean getRewardsExp() {
      return this.rewardsExp;
   }

   public boolean hasSecondItemToBuy() {
      return this.secondItemToBuy != null;
   }

   public MerchantRecipe(ItemStack stack, ItemStack stack2, ItemStack stack3, int n, int n2) {
      this.itemToBuy = stack;
      this.secondItemToBuy = stack2;
      this.itemToSell = stack3;
      this.toolUses = n;
      this.maxTradeUses = n2;
      this.rewardsExp = true;
   }

   public void readFromTags(NBTTagCompound tag) {
      this.itemToBuy = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("buy"));
      this.itemToSell = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("sell"));
      if (tag.hasKey("buyB", 10)) {
         this.secondItemToBuy = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("buyB"));
      }

      if (tag.hasKey("uses", 99)) {
         this.toolUses = tag.getInteger("uses");
      }

      if (tag.hasKey("maxUses", 99)) {
         this.maxTradeUses = tag.getInteger("maxUses");
      } else {
         this.maxTradeUses = 7;
      }

      if (tag.hasKey("rewardExp", 1)) {
         this.rewardsExp = tag.getBoolean("rewardExp");
      } else {
         this.rewardsExp = true;
      }
   }
}
