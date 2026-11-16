package client.util;

import client.event.PreUpdateNotifier;
import client.render.StorageEspRenderer;
import client.setting.DisablerUndefinedType;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;

public class LvlManager {
   public static final Random random = new Random();
   public static final StorageEspRenderer storageEspRenderer = new StorageEspRenderer(null);
   public static final AuxiliaryListenerProvider auxiliaryListenerProvider = new AuxiliaryListenerProvider(null);
   public static final InternalListenerController internalListenerController = new InternalListenerController(null);
   public static final ListenerProvider listenerProvider = new ListenerProvider(null);

   public static int computeCount(Entity entity) {
      return computeCount(EnchantmentManager.enchantmentManager21.indexOffset, entity.getItemStackArray2());
   }

   public static int computeCount9(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager15.indexOffset, livingEntity.getItemStack());
   }

   public static ItemStack resolveItemStackByEnchantmentManag(EnchantmentManager enchantmentManager, EntityLivingBase livingEntity) {
      for (ItemStack stack : livingEntity.getItemStackArray2()) {
         if (stack != null && getIndex(enchantmentManager.indexOffset, stack) > 0) {
            return stack;
         }
      }

      return null;
   }

   public static int computeCount(Random random2, int count, int count2, ItemStack stack) {
      int count3 = count2;
      if (stack.getItem().processAction2() <= 0) {
         return 0;
      } else {
         if (count2 > 15) {
            count3 = 15;
         }

         int count4 = random2.nextInt(8) + 1 + (count3 >> 1) + random2.nextInt(count3 + 1);
         return count == 0 ? Math.max(count4 / 3, 1) : (count == 1 ? count4 * 2 / 3 + 1 : Math.max(count4, count3 * 2));
      }
   }

   public static List resolveList(Random random2, ItemStack stack, int count) {
      int count2 = stack.getItem().processAction2();
      if (count2 <= 0) {
         return null;
      } else {
         int count3 = count2 / 2;
         int count4 = 1 + random2.nextInt((count3 >> 1) + 1) + random2.nextInt((count3 >> 1) + 1) + count;
         float factor = (random2.nextFloat() + random2.nextFloat() - 1.0F) * 0.15F;
         int count5 = (int)((float)count4 * (1.0F + factor) + 0.5F);
         if (count5 < 1) {
            count5 = 1;
         }

         ArrayList list = null;
         Map map = resolveMap(count5, stack);
         if (map != null && !map.isEmpty()) {
            AuxiliaryClientManagerResolver auxiliaryClientManagerResolver = (AuxiliaryClientManagerResolver)SmoothSystem.resolveDampenerSorter(random2, map.values());
            if (auxiliaryClientManagerResolver != null) {
               list = Lists.newArrayList();
               list.add(auxiliaryClientManagerResolver);

               for (; random2.nextInt(50) <= count5; count5 >>= 1) {
                  Iterator iterator = map.keySet().iterator();

                  while (iterator.hasNext()) {
                     Integer count6 = (Integer)iterator.next();
                     boolean flag = true;
                     Iterator iterator2 = list.iterator();

                     while (iterator2.hasNext()) {
                        if (!((AuxiliaryClientManagerResolver)iterator2.next())
                           .enchantmentManager
                           .matchesEnchantmentManager(EnchantmentManager.resolveEnchantmentManager(count6))) {
                           flag = false;
                           break;
                        }
                     }

                     if (!flag) {
                        iterator.remove();
                     }
                  }

                  if (!map.isEmpty()) {
                     AuxiliaryClientManagerResolver auxiliaryClientManagerResolver2 = (AuxiliaryClientManagerResolver)SmoothSystem.resolveDampenerSorter(random2, map.values());
                     list.add(auxiliaryClientManagerResolver2);
                  }
               }
            }
         }

         return list;
      }
   }

   public static Map resolveMap(int count, ItemStack stack) {
      Item item = stack.getItem();
      HashMap map = null;
      boolean flag = stack.getItem() == Items.book;

      for (EnchantmentManager enchantmentManager : EnchantmentManager.enchantmentManagerArray2) {
         if (enchantmentManager != null && (enchantmentManager.enhancedOptionType.matchesItem(item) || flag)) {
            for (int index = enchantmentManager.getCount2(); index <= enchantmentManager.getCount(); index++) {
               if (count >= enchantmentManager.computeCountByIndex(index) && count <= enchantmentManager.computeCount(index)) {
                  if (map == null) {
                     map = Maps.newHashMap();
                  }

                  map.put(enchantmentManager.indexOffset, new AuxiliaryClientManagerResolver(enchantmentManager, index));
               }
            }
         }
      }

      return map;
   }

   public static ItemStack resolveItemStack(Random random2, ItemStack stack, int count) {
      List list = resolveList(random2, stack, count);
      boolean flag = stack.getItem() == Items.book;
      if (flag) {
         stack.setItem(Items.enchantedBook);
      }

      if (list != null) {
         for (AuxiliaryClientManagerResolver auxiliaryClientManagerResolver : (Iterable<AuxiliaryClientManagerResolver>)(Iterable<?>)list) {
            if (flag) {
               Items.enchantedBook.applyItemStack(stack, auxiliaryClientManagerResolver);
            } else {
               stack.applyEnchantmentManager(auxiliaryClientManagerResolver.enchantmentManager, auxiliaryClientManagerResolver.count2);
            }
         }
      }

      return stack;
   }

   public static void applyEntityLivingBase(EntityLivingBase livingEntity, Entity entity) {
      internalListenerController.entity = entity;
      internalListenerController.entityLivingBase = livingEntity;
      if (livingEntity != null) {
         applyPreUpdateNotifier(internalListenerController, livingEntity.getItemStackArray2());
      }

      if (entity instanceof EntityPlayer) {
         applyPreUpdateNotifier(internalListenerController, livingEntity.getItemStack());
      }
   }

   public static void applyMap(Map map, ItemStack stack) {
      NBTTagList nBTTagList = new NBTTagList();

      for (int count : (Iterable<Integer>)(Iterable<?>)map.keySet()) {
         EnchantmentManager enchantmentManager = EnchantmentManager.resolveEnchantmentManager(count);
         if (enchantmentManager != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.put("id", (short)count);
            tagCompound.put("lvl", (short)((Integer)map.get(count)).intValue());
            nBTTagList.applyLISTManager(tagCompound);
            if (stack.getItem() == Items.enchantedBook) {
               Items.enchantedBook.applyItemStack(stack, new AuxiliaryClientManagerResolver(enchantmentManager, (Integer)map.get(count)));
            }
         }
      }

      if (nBTTagList.getInt() > 0) {
         if (stack.getItem() != Items.enchantedBook) {
            stack.applyText("ench", nBTTagList);
         }
      } else if (stack.canExecute()) {
         stack.getStackTagCompound().remove("ench");
      }
   }

   public static int computeCount3(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager3.indexOffset, livingEntity.getItemStack());
   }

   public static int computeCountByEntityLivingBase(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager11.indexOffset, livingEntity.getItemStack());
   }

   public static boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return computeCount(EnchantmentManager.enchantmentManager9.indexOffset, livingEntity.getItemStackArray2()) > 0;
   }

   public static int computeCount5(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager19.indexOffset, livingEntity.getItemStack());
   }

   public static int computeCount(int count, ItemStack[] stackArray) {
      if (stackArray == null) {
         return 0;
      } else {
         int count2 = 0;

         for (ItemStack stack : stackArray) {
            int index = getIndex(count, stack);
            if (index > count2) {
               count2 = index;
            }
         }

         return count2;
      }
   }

   public static int computeCount11(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager5.indexOffset, livingEntity.getItemStack());
   }

   public static int computeCount(ItemStack[] stackArray, DamageSource damageSource) {
      storageEspRenderer.count = 0;
      storageEspRenderer.damageSource = damageSource;
      applyPreUpdateNotifier(storageEspRenderer, stackArray);
      if (storageEspRenderer.count > 25) {
         storageEspRenderer.count = 25;
      } else if (storageEspRenderer.count < 0) {
         storageEspRenderer.count = 0;
      }

      return (storageEspRenderer.count + 1 >> 1) + random.nextInt((storageEspRenderer.count >> 1) + 1);
   }

   public static int getIndex(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager.indexOffset, livingEntity.getItemStack());
   }

   public static boolean matchesEntityLivingBase2(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager4.indexOffset, livingEntity.getItemStack()) > 0;
   }

   public static int computeCount7(EntityLivingBase livingEntity) {
      return getIndex(EnchantmentManager.enchantmentManager23.indexOffset, livingEntity.getItemStack());
   }

   public static float getScaleFactor(ItemStack stack, DisablerUndefinedType disablerUndefinedType) {
      auxiliaryListenerProvider.factor = 0.0F;
      auxiliaryListenerProvider.disablerUndefinedType = disablerUndefinedType;
      applyPreUpdateNotifier(auxiliaryListenerProvider, stack);
      return auxiliaryListenerProvider.factor;
   }

   private static void applyPreUpdateNotifier(PreUpdateNotifier preUpdateNotifier, ItemStack[] stackArray) {
      for (ItemStack stack : stackArray) {
         applyPreUpdateNotifier(preUpdateNotifier, stack);
      }
   }

   public static int getIndex(int count, ItemStack stack) {
      if (stack != null) {
         NBTTagList extendedLoggerEntry = stack.getExtendedLoggerEntry();
         if (extendedLoggerEntry != null) {
            for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
               if (extendedLoggerEntry.getNBTTagCompound(index).processValue("id") == count) {
                  return extendedLoggerEntry.getNBTTagCompound(index).processValue("lvl");
               }
            }
         }
      }

      return 0;
   }

   public static int computeCount2(Entity entity) {
      return computeCount(EnchantmentManager.enchantmentManager22.indexOffset, entity.getItemStackArray2());
   }

   public static Map resolveMap(ItemStack stack) {
      LinkedHashMap map = Maps.newLinkedHashMap();
      NBTTagList nBTTagList = stack.getItem() == Items.enchantedBook ? Items.enchantedBook.resolveNBTTagList(stack) : stack.getExtendedLoggerEntry();
      if (nBTTagList != null) {
         for (int index = 0; index < nBTTagList.getInt(); index++) {
            short shortValue = nBTTagList.getNBTTagCompound(index).processValue("id");
            short shortValue2 = nBTTagList.getNBTTagCompound(index).processValue("lvl");
            map.put(Integer.valueOf(shortValue), Integer.valueOf(shortValue2));
         }
      }

      return map;
   }

   private static void applyPreUpdateNotifier(PreUpdateNotifier preUpdateNotifier, ItemStack stack) {
      if (stack != null) {
         NBTTagList extendedLoggerEntry = stack.getExtendedLoggerEntry();
         if (extendedLoggerEntry != null) {
            for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
               short shortValue = extendedLoggerEntry.getNBTTagCompound(index).processValue("id");
               short shortValue2 = extendedLoggerEntry.getNBTTagCompound(index).processValue("lvl");
               if (EnchantmentManager.resolveEnchantmentManager(shortValue) != null) {
                  preUpdateNotifier.applyEnchantmentManager(EnchantmentManager.resolveEnchantmentManager(shortValue), shortValue2);
               }
            }
         }
      }
   }

   public static void applyEntityLivingBase2(EntityLivingBase livingEntity, Entity entity) {
      listenerProvider.entityLivingBase = livingEntity;
      listenerProvider.entity = entity;
      if (livingEntity != null) {
         applyPreUpdateNotifier(listenerProvider, livingEntity.getItemStackArray2());
      }

      if (livingEntity instanceof EntityPlayer) {
         applyPreUpdateNotifier(listenerProvider, livingEntity.getItemStack());
      }
   }
}
