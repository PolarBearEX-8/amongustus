package net.minecraft.enchantment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandom;

public class EnchantmentHelper {
   private static final Random enchantmentRand = new Random();
   public static final EnchantmentHelper.ModifierDamage enchantmentModifierDamage = new EnchantmentHelper.ModifierDamage(null);
   private static final EnchantmentHelper.ModifierLiving enchantmentModifierLiving = new EnchantmentHelper.ModifierLiving(null);
   private static final EnchantmentHelper.HurtIterator ENCHANTMENT_ITERATOR_HURT = new EnchantmentHelper.HurtIterator(null);
   private static final EnchantmentHelper.DamageIterator ENCHANTMENT_ITERATOR_DAMAGE = new EnchantmentHelper.DamageIterator(null);

   public static List buildEnchantmentList(Random rand, ItemStack stack, int n) {
      int itemEnchantability = stack.getItem().getItemEnchantability();
      if (itemEnchantability <= 0) {
         return null;
      } else {
         itemEnchantability /= 2;
         itemEnchantability = 1 + rand.nextInt((itemEnchantability >> 1) + 1) + rand.nextInt((itemEnchantability >> 1) + 1);
         int n2 = itemEnchantability + n;
         float f = (rand.nextFloat() + rand.nextFloat() - 1.0F) * 0.15F;
         int n3 = (int)((float)n2 * (1.0F + f) + 0.5F);
         if (n3 < 1) {
            n3 = 1;
         }

         ArrayList list = null;
         Map map = mapEnchantmentData(n3, stack);
         if (map != null && !map.isEmpty()) {
            EnchantmentData randomItem = (EnchantmentData)WeightedRandom.getRandomItem(rand, map.values());
            if (randomItem != null) {
               list = Lists.newArrayList();
               list.add(randomItem);

               for (int i = n3; rand.nextInt(50) <= i; i >>= 1) {
                  Iterator it = map.keySet().iterator();

                  while (it.hasNext()) {
                     Integer num = (Integer)it.next();
                     boolean flag = true;

                     for (EnchantmentData enchantmentData : (Iterable<EnchantmentData>)(list)) {
                        if (!enchantmentData.enchantmentobj.canApplyTogether(Enchantment.getEnchantmentById(num))) {
                           flag = false;
                           break;
                        }
                     }

                     if (!flag) {
                        it.remove();
                     }
                  }

                  if (!map.isEmpty()) {
                     list.add((EnchantmentData)WeightedRandom.getRandomItem(rand, map.values()));
                  }
               }
            }
         }

         return list;
      }
   }

   public static float getModifierForCreature(ItemStack stack, EnumCreatureAttribute enumCreatureAttribute) {
      enchantmentModifierLiving.livingModifier = 0.0F;
      enchantmentModifierLiving.entityLiving = enumCreatureAttribute;
      applyEnchantmentModifier(enchantmentModifierLiving, stack);
      return enchantmentModifierLiving.livingModifier;
   }

   public static int getLuckOfSeaModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.luckOfTheSea.effectId, entity.getHeldItem());
   }

   public static int getLootingModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.looting.effectId, entity.getHeldItem());
   }

   public static Map mapEnchantmentData(int n, ItemStack stack) {
      Item item = stack.getItem();
      HashMap map = null;
      boolean flag = stack.getItem() == Items.book;

      for (Enchantment enchantment : Enchantment.enchantmentsBookList) {
         if (enchantment != null && (enchantment.type.canEnchantItem(item) || flag)) {
            for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); i++) {
               if (n >= enchantment.getMinEnchantability(i) && n <= enchantment.getMaxEnchantability(i)) {
                  if (map == null) {
                     map = Maps.newHashMap();
                  }

                  map.put(enchantment.effectId, new EnchantmentData(enchantment, i));
               }
            }
         }
      }

      return map;
   }

   public static void applyArthropodEnchantments(EntityLivingBase entity, Entity entity2) {
      ENCHANTMENT_ITERATOR_DAMAGE.user = entity;
      ENCHANTMENT_ITERATOR_DAMAGE.target = entity2;
      if (entity != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_DAMAGE, entity.getInventory());
      }

      if (entity instanceof EntityPlayer) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_DAMAGE, entity.getHeldItem());
      }
   }

   private static void applyEnchantmentModifier(EnchantmentHelper.IModifier modifier, ItemStack stack) {
      if (stack != null) {
         NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
         if (enchantmentTagList != null) {
            for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
               short s = enchantmentTagList.getCompoundTagAt(i).getShort("id");
               short s2 = enchantmentTagList.getCompoundTagAt(i).getShort("lvl");
               if (Enchantment.getEnchantmentById(s) != null) {
                  modifier.calculateModifier(Enchantment.getEnchantmentById(s), s2);
               }
            }
         }
      }
   }

   private static void applyEnchantmentModifierArray(EnchantmentHelper.IModifier modifier, ItemStack[] stacks) {
      for (ItemStack stack : stacks) {
         applyEnchantmentModifier(modifier, stack);
      }
   }

   public static int getEnchantmentModifierDamage(ItemStack[] stacks, DamageSource damageSource) {
      enchantmentModifierDamage.damageModifier = 0;
      enchantmentModifierDamage.source = damageSource;
      applyEnchantmentModifierArray(enchantmentModifierDamage, stacks);
      if (enchantmentModifierDamage.damageModifier > 25) {
         enchantmentModifierDamage.damageModifier = 25;
      } else if (enchantmentModifierDamage.damageModifier < 0) {
         enchantmentModifierDamage.damageModifier = 0;
      }

      return (enchantmentModifierDamage.damageModifier + 1 >> 1) + enchantmentRand.nextInt((enchantmentModifierDamage.damageModifier >> 1) + 1);
   }

   public static int getFortuneModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.fortune.effectId, entity.getHeldItem());
   }

   public static boolean getSilkTouchModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.silkTouch.effectId, entity.getHeldItem()) > 0;
   }

   public static void applyThornEnchantments(EntityLivingBase entity, Entity entity2) {
      ENCHANTMENT_ITERATOR_HURT.attacker = entity2;
      ENCHANTMENT_ITERATOR_HURT.user = entity;
      if (entity != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_HURT, entity.getInventory());
      }

      if (entity2 instanceof EntityPlayer) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_HURT, entity.getHeldItem());
      }
   }

   public static int calcItemStackEnchantability(Random rand, int n, int n2, ItemStack stack) {
      if (stack.getItem().getItemEnchantability() <= 0) {
         return 0;
      } else {
         if (n2 > 15) {
            n2 = 15;
         }

         int n3 = rand.nextInt(8) + 1 + (n2 >> 1) + rand.nextInt(n2 + 1);
         return n == 0 ? Math.max(n3 / 3, 1) : (n == 1 ? n3 * 2 / 3 + 1 : Math.max(n3, n2 * 2));
      }
   }

   public static boolean getAquaAffinityModifier(EntityLivingBase entity) {
      return getMaxEnchantmentLevel(Enchantment.aquaAffinity.effectId, entity.getInventory()) > 0;
   }

   public static ItemStack getEnchantedItem(Enchantment enchantment, EntityLivingBase entity) {
      for (ItemStack stack : entity.getInventory()) {
         if (stack != null && getEnchantmentLevel(enchantment.effectId, stack) > 0) {
            return stack;
         }
      }

      return null;
   }

   public static int getLureModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.lure.effectId, entity.getHeldItem());
   }

   public static int getKnockbackModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.knockback.effectId, entity.getHeldItem());
   }

   public static Map getEnchantments(ItemStack stack) {
      LinkedHashMap map = Maps.newLinkedHashMap();
      NBTTagList tagList = stack.getItem() == Items.enchanted_book ? Items.enchanted_book.getEnchantments(stack) : stack.getEnchantmentTagList();
      if (tagList != null) {
         for (int i = 0; i < tagList.tagCount(); i++) {
            short s = tagList.getCompoundTagAt(i).getShort("id");
            short s2 = tagList.getCompoundTagAt(i).getShort("lvl");
            map.put(Integer.valueOf(s), Integer.valueOf(s2));
         }
      }

      return map;
   }

   public static int getDepthStriderModifier(Entity entity) {
      return getMaxEnchantmentLevel(Enchantment.depthStrider.effectId, entity.getInventory());
   }

   public static int getEfficiencyModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.efficiency.effectId, entity.getHeldItem());
   }

   public static int getMaxEnchantmentLevel(int n, ItemStack[] stacks) {
      if (stacks == null) {
         return 0;
      } else {
         int n2 = 0;

         for (ItemStack stack : stacks) {
            int enchantmentLevel = getEnchantmentLevel(n, stack);
            if (enchantmentLevel > n2) {
               n2 = enchantmentLevel;
            }
         }

         return n2;
      }
   }

   public static ItemStack addRandomEnchantment(Random rand, ItemStack stack, int n) {
      List list = buildEnchantmentList(rand, stack, n);
      boolean flag = stack.getItem() == Items.book;
      if (flag) {
         stack.setItem(Items.enchanted_book);
      }

      if (list != null) {
         for (EnchantmentData enchantmentData : (Iterable<EnchantmentData>)(list)) {
            if (flag) {
               Items.enchanted_book.addEnchantment(stack, enchantmentData);
            } else {
               stack.addEnchantment(enchantmentData.enchantmentobj, enchantmentData.enchantmentLevel);
            }
         }
      }

      return stack;
   }

   public static int getRespiration(Entity entity) {
      return getMaxEnchantmentLevel(Enchantment.respiration.effectId, entity.getInventory());
   }

   public static void setEnchantments(Map map, ItemStack stack) {
      NBTTagList tagList = new NBTTagList();

      for (int n : (Iterable<Integer>)(map.keySet())) {
         Enchantment enchantmentById = Enchantment.getEnchantmentById(n);
         if (enchantmentById != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setShort("id", (short)n);
            tag.setShort("lvl", (short)((Integer)map.get(n)).intValue());
            tagList.appendTag(tag);
            if (stack.getItem() == Items.enchanted_book) {
               Items.enchanted_book.addEnchantment(stack, new EnchantmentData(enchantmentById, (Integer)map.get(n)));
            }
         }
      }

      if (tagList.tagCount() > 0) {
         if (stack.getItem() != Items.enchanted_book) {
            stack.setTagInfo("ench", tagList);
         }
      } else if (stack.hasTagCompound()) {
         stack.getTagCompound().removeTag("ench");
      }
   }

   public static int getEnchantmentLevel(int n, ItemStack stack) {
      if (stack != null) {
         NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
         if (enchantmentTagList != null) {
            for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
               if (enchantmentTagList.getCompoundTagAt(i).getShort("id") == n) {
                  return enchantmentTagList.getCompoundTagAt(i).getShort("lvl");
               }
            }
         }
      }

      return 0;
   }

   public static int getFireAspectModifier(EntityLivingBase entity) {
      return getEnchantmentLevel(Enchantment.fireAspect.effectId, entity.getHeldItem());
   }


   public static class InnerEntry {
   }

   public interface IModifier {
      void calculateModifier(Enchantment enchantment, int n);
   }

   public static final class HurtIterator implements EnchantmentHelper.IModifier {
      public EntityLivingBase user;
      public Entity attacker;

      private HurtIterator() {
      }

      public HurtIterator(EnchantmentHelper.InnerEntry obj) {
         this();
      }

      @Override
      public void calculateModifier(Enchantment enchantment, int n) {
         enchantment.onUserHurt(this.user, this.attacker, n);
      }
   }

   public static final class DamageIterator implements EnchantmentHelper.IModifier {
      public EntityLivingBase user;
      public Entity target;

      @Override
      public void calculateModifier(Enchantment enchantment, int n) {
         enchantment.onEntityDamaged(this.user, this.target, n);
      }

      private DamageIterator() {
      }

      public DamageIterator(EnchantmentHelper.InnerEntry obj) {
         this();
      }
   }

   public static final class ModifierDamage implements EnchantmentHelper.IModifier {
      public int damageModifier;
      public DamageSource source;

      private ModifierDamage() {
      }

      @Override
      public void calculateModifier(Enchantment enchantment, int n) {
         this.damageModifier = this.damageModifier + enchantment.calcModifierDamage(n, this.source);
      }

      public ModifierDamage(EnchantmentHelper.InnerEntry obj) {
         this();
      }

      public int getDamageModifier(Enchantment enchantment, int n) {
         return enchantment.calcModifierDamage(n, this.source);
      }
   }

   public static final class ModifierLiving implements EnchantmentHelper.IModifier {
      public float livingModifier;
      public EnumCreatureAttribute entityLiving;

      public ModifierLiving(EnchantmentHelper.InnerEntry obj) {
         this();
      }

      @Override
      public void calculateModifier(Enchantment enchantment, int n) {
         this.livingModifier = this.livingModifier + enchantment.calcDamageByCreature(n, this.entityLiving);
      }

      private ModifierLiving() {
      }
   }
}
