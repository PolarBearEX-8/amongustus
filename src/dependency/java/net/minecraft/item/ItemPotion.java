package net.minecraft.item;

import client.event.ExtendedListener;
import client.setting.ClientSettingsBlackType;
import client.setting.ItemNoneType;
import client.util.BezierReader;
import client.util.InvalidOperationManager;
import client.util.StatCraftItemManager;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPotion extends Item {
   public Map map2 = Maps.newHashMap();
   public static final Map map = Maps.newLinkedHashMap();

   public boolean checkIndex(int count) {
      List list = this.resolveList(count);
      if (list != null && !list.isEmpty()) {
         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)list) {
            if (Potion.potionArray[potionEffect.getIndexOffset3()].isActive()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Override
   public int processAction(ItemStack stack) {
      return 32;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      super.processAction(item, itemGroupManager, list);
      if (map.isEmpty()) {
         for (int index = 0; index <= 15; index++) {
            for (int index2 = 0; index2 <= 1; index2++) {
               int count;
               if (index2 == 0) {
                  count = index | 8192;
               } else {
                  count = index | 16384;
               }

               for (int index3 = 0; index3 <= 2; index3++) {
                  int count2 = count;
                  if (index3 != 0) {
                     if (index3 == 1) {
                        count2 = count | 32;
                     } else if (index3 == 2) {
                        count2 = count | 64;
                     }
                  }

                  List list2 = BezierReader.resolveList(count2, false);
                  if (list2 != null && !list2.isEmpty()) {
                     map.put(list2, count2);
                  }
               }
            }
         }
      }

      for (Integer count3 : (Iterable<Integer>)(Iterable<?>)map.values()) {
         list.add(new ItemStack(item, 1, count3));
      }
   }

   @Override
   public boolean processAction2(ItemStack stack) {
      List list = this.getList(stack);
      return list != null && !list.isEmpty();
   }

   public static boolean checkCondition(int count) {
      return (count & 16384) != 0;
   }

   public int computeCount(int count) {
      return BezierReader.computeCount(count, false);
   }

   public List getList(ItemStack stack) {
      if (stack.canExecute() && stack.getStackTagCompound().matchesText("CustomPotionEffects", 9)) {
         ArrayList list = Lists.newArrayList();
         NBTTagList extendedLoggerEntry = stack.getStackTagCompound().getExtendedLoggerEntry("CustomPotionEffects", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            PotionEffect potionEffect = PotionEffect.resolvePotionEffect(extendedLoggerEntry.getNBTTagCompound(index));
            if (potionEffect != null) {
               list.add(potionEffect);
            }
         }

         return list;
      } else {
         List list2 = (List)this.map2.get(stack.getIndexOffset());
         if (list2 == null) {
            list2 = BezierReader.resolveList(stack.getIndexOffset(), false);
            this.map2.put(stack.getIndexOffset(), list2);
         }

         return list2;
      }
   }

   @Override
   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      if (!player.invulnerableManager.enabled2) {
         stack.animationsToGo--;
      }

      if (!world.state) {
         List list = this.getList(stack);
         if (list != null) {
            for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)list) {
               player.applyPotionEffectByPotionEffect(new PotionEffect(potionEffect));
            }
         }
      }

      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      if (!player.invulnerableManager.enabled2) {
         if (stack.animationsToGo <= 0) {
            return new ItemStack(Items.glassBottle);
         }

         player.inventoryPlayer.checkItemStack3(new ItemStack(Items.glassBottle));
      }

      return stack;
   }

   public List resolveList(int count) {
      List list = (List)this.map2.get(count);
      if (list == null) {
         list = BezierReader.resolveList(count, false);
         this.map2.put(count, list);
      }

      return list;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (checkCondition(stack.getIndexOffset())) {
         if (!player.invulnerableManager.enabled2) {
            stack.animationsToGo--;
         }

         world.applyEntityByEntity(player, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
         if (!world.state) {
            world.matchesEntity(new EntityThrownpotion(world, player, stack));
         }

         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
         return stack;
      } else {
         player.applyItemStack(stack, this.processAction(stack));
         return stack;
      }
   }

   @Override
   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.DRINK;
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      return count > 0 ? 16777215 : this.computeCount(stack.getIndexOffset());
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.getIndexOffset() != 0) {
         List list2 = Items.potion.getList(stack);
         HashMultimap hashMultimap = HashMultimap.create();
         if (list2 != null && !list2.isEmpty()) {
            for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)list2) {
               Object entry = I18n.getString2(potionEffect.processAction2()).trim();
               Potion potion = Potion.potionArray[potionEffect.getIndexOffset3()];
               Map entryMap = potion.getEntryMap2();
               if (entryMap != null && entryMap.size() > 0) {
                  for (Entry entry2 : (Iterable<Entry>)(Iterable<?>)entryMap.entrySet()) {
                     InvalidOperationManager value = (InvalidOperationManager)entry2.getValue();
                     InvalidOperationManager invalidOperationManager = new InvalidOperationManager(
                        value.getLabelText(), potion.computeValue(potionEffect.getIndexOffset2(), value), value.getCount()
                     );
                     hashMultimap.put(((ExtendedListener)entry2.getKey()).getLabelText(), invalidOperationManager);
                  }
               }

               if (potionEffect.getIndexOffset2() > 0) {
                  entry = entry + " " + I18n.getString2("potion.potency." + potionEffect.getIndexOffset2()).trim();
               }

               if (potionEffect.getIndexOffset() > 20) {
                  entry = entry + " (" + Potion.getText(potionEffect) + ")";
               }

               if (potion.isState2()) {
                  list.add("" + ClientSettingsBlackType.RED + entry);
               } else {
                  list.add("" + ClientSettingsBlackType.GRAY + entry);
               }
            }
         } else {
            Object entry3 = I18n.getString2("potion.empty").trim();
            list.add("" + ClientSettingsBlackType.GRAY + entry3);
         }

         if (!hashMultimap.isEmpty()) {
            list.add("");
            list.add(ClientSettingsBlackType.DARK_PURPLE + I18n.getString2("potion.effects.whenDrank"));

            for (Entry entry4 : (Iterable<Entry>)(Iterable<?>)hashMultimap.entries()) {
               InvalidOperationManager value2 = (InvalidOperationManager)entry4.getValue();
               double value3 = value2.getValue();
               double offset;
               if (value2.getCount() != 1 && value2.getCount() != 2) {
                  offset = value2.getValue();
               } else {
                  offset = value2.getValue() * 100.0;
               }

               if (value3 > 0.0) {
                  list.add(
                     ClientSettingsBlackType.BLUE
                        + I18n.resolveText(
                           "attribute.modifier.plus." + value2.getCount(),
                           new Object[]{ItemStack.decimalFormat.format(offset), I18n.getString2("attribute.name." + (String)entry4.getKey())}
                        )
                  );
               } else if (value3 < 0.0) {
                  double offset2 = offset * -1.0;
                  list.add(
                     ClientSettingsBlackType.RED
                        + I18n.resolveText(
                           "attribute.modifier.take." + value2.getCount(),
                           new Object[]{ItemStack.decimalFormat.format(offset2), I18n.getString2("attribute.name." + (String)entry4.getKey())}
                        )
                  );
               }
            }
         }
      }
   }

   @Override
   public String resolveText(ItemStack stack) {
      if (stack.getIndexOffset() == 0) {
         return I18n.getString2("item.emptyPotion.name").trim();
      } else {
         String text = "";
         if (checkCondition(stack.getIndexOffset())) {
            text = I18n.getString2("potion.prefix.grenade").trim() + " ";
         }

         List list = Items.potion.getList(stack);
         if (list != null && !list.isEmpty()) {
            String text2 = ((PotionEffect)list.get(0)).processAction2();
            String text3 = text2 + ".postfix";
            return text + I18n.getString2(text3).trim();
         } else {
            String text4 = BezierReader.resolveText(stack.getIndexOffset());
            return I18n.getString2(text4).trim() + " " + super.resolveText(stack);
         }
      }
   }

   public ItemPotion() {
      this.processAction4(1);
      this.processAction(true);
      this.processAction3(0);
      this.processAction(ItemGroupManager.itemGroupManager7);
   }
}
