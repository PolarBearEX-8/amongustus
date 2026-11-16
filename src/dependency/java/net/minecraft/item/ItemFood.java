package net.minecraft.item;

import client.setting.ItemNoneType;
import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFood extends Item {
   public final int count3 = 32;
   public final int count;
   public final float factor2;
   public final boolean enabled;
   public boolean enabled2;
   public int count4;
   public int count2;
   public int count5;
   public float factor;

   @Override
   public int processAction(ItemStack stack) {
      return 32;
   }

   public void applyItemStack2(ItemStack stack, World world, EntityPlayer player) {
      if (!world.state && this.count4 > 0 && world.random.nextFloat() < this.factor) {
         player.applyPotionEffectByPotionEffect(new PotionEffect(this.count4, this.count2 * 20, this.count5));
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public float computeFactor(ItemStack stack) {
      return this.factor2;
   }

   @Override
   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      stack.animationsToGo--;
      player.getFoodLevelManager().applyItemFood(this, stack);
      world.applyEntityByEntity(player, "random.burp", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
      this.applyItemStack2(stack, world, player);
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack;
   }

   @Override
   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.EAT;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (player.checkFlag(this.enabled2)) {
         player.applyItemStack(stack, this.processAction(stack));
      }

      return stack;
   }

   public ItemFood resolveItemFood(int count6, int count7, int count8, float factor3) {
      this.count4 = count6;
      this.count2 = count7;
      this.count5 = count8;
      this.factor = factor3;
      return this;
   }

   public int computeCount(ItemStack stack) {
      return this.count;
   }

   public ItemFood getItemFood() {
      this.enabled2 = true;
      return this;
   }

   public ItemFood(int count6, float factor3, boolean flag) {
      this.count = count6;
      this.enabled = flag;
      this.factor2 = factor3;
      this.processAction(ItemGroupManager.itemGroupManager3);
   }

   public ItemFood(int count6, boolean flag) {
      this(count6, 0.6F, flag);
   }
}
