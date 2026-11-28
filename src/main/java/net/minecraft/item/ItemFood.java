package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFood extends Item {
   public final int itemUseDuration = 32;
   private final int healAmount;
   private final float saturationModifier;
   private final boolean isWolfsFavoriteMeat;
   private boolean alwaysEdible;
   private int potionId;
   private int potionDuration;
   private int potionAmplifier;
   private float potionEffectProbability;

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.EAT;
   }

   public ItemFood setPotionEffect(int n, int n2, int n3, float f) {
      this.potionId = n;
      this.potionDuration = n2;
      this.potionAmplifier = n3;
      this.potionEffectProbability = f;
      return this;
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
      stack.stackSize--;
      player.getFoodStats().addStats(this, stack);
      world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
      this.onFoodEaten(stack, world, player);
      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack;
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (player.canEat(this.alwaysEdible)) {
         player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
      }

      return stack;
   }

   public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
      if (!world.isRemote && this.potionId > 0 && world.rand.nextFloat() < this.potionEffectProbability) {
         player.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
      }
   }

   public ItemFood(int n, float f, boolean flag) {
      this.healAmount = n;
      this.isWolfsFavoriteMeat = flag;
      this.saturationModifier = f;
      this.setCreativeTab(CreativeTabs.tabFood);
   }

   public int getHealAmount(ItemStack stack) {
      return this.healAmount;
   }

   public float getSaturationModifier(ItemStack stack) {
      return this.saturationModifier;
   }

   public ItemFood setAlwaysEdible() {
      this.alwaysEdible = true;
      return this;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 32;
   }

   public boolean isWolfsFavoriteMeat() {
      return this.isWolfsFavoriteMeat;
   }

   public ItemFood(int n, boolean flag) {
      this(n, 0.6F, flag);
   }
}
