package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DistanceBuffer {
   public ItemStack itemStack;
   public ItemStack itemStack2;
   public ItemStack itemStack3;
   public int count;
   public int count2;
   public boolean enabled;

   public ItemStack getItemStack3() {
      return this.itemStack3;
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("buy");
      this.itemStack = ItemStack.resolveItemStack(nBTTagCompound);
      NBTTagCompound nBTTagCompound2 = tagCompound.getNBTTagCompound("sell");
      this.itemStack3 = ItemStack.resolveItemStack(nBTTagCompound2);
      if (tagCompound.matchesText("buyB", 10)) {
         this.itemStack2 = ItemStack.resolveItemStack(tagCompound.getNBTTagCompound("buyB"));
      }

      if (tagCompound.matchesText("uses", 99)) {
         this.count = tagCompound.computeCount("uses");
      }

      if (tagCompound.matchesText("maxUses", 99)) {
         this.count2 = tagCompound.computeCount("maxUses");
      } else {
         this.count2 = 7;
      }

      if (tagCompound.matchesText("rewardExp", 1)) {
         this.enabled = tagCompound.matchesText("rewardExp");
      } else {
         this.enabled = true;
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isActive() {
      return this.count >= this.count2;
   }

   public DistanceBuffer(ItemStack stack, ItemStack stack2) {
      this(stack, (ItemStack)null, stack2);
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.applyText("buy", this.itemStack.resolveNBTTagCompound(new NBTTagCompound()));
      tagCompound.applyText("sell", this.itemStack3.resolveNBTTagCompound(new NBTTagCompound()));
      if (this.itemStack2 != null) {
         tagCompound.applyText("buyB", this.itemStack2.resolveNBTTagCompound(new NBTTagCompound()));
      }

      tagCompound.put("uses", this.count);
      tagCompound.put("maxUses", this.count2);
      tagCompound.applyText("rewardExp", this.enabled);
      return tagCompound;
   }

   public void updateState2() {
      this.count++;
   }

   public int getCount() {
      return this.count;
   }

   public DistanceBuffer(ItemStack stack, Item item) {
      this(stack, new ItemStack(item));
   }

   public DistanceBuffer(ItemStack stack, ItemStack stack2, ItemStack stack3) {
      this(stack, stack2, stack3, 0, 7);
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public DistanceBuffer(ItemStack stack, ItemStack stack2, ItemStack stack3, int count3, int count4) {
      this.itemStack = stack;
      this.itemStack2 = stack2;
      this.itemStack3 = stack3;
      this.count = count3;
      this.count2 = count4;
      this.enabled = true;
   }

   public boolean isActiveAsFlag() {
      return this.itemStack2 != null;
   }

   public void applyIndex(int count3) {
      this.count2 += count3;
   }

   public ItemStack getItemStack2() {
      return this.itemStack2;
   }

   public void updateState() {
      this.count = this.count2;
   }

   public DistanceBuffer(NBTTagCompound tagCompound) {
      this.applyNBTTagCompound(tagCompound);
   }
}
