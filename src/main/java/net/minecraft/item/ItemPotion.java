package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPotion extends Item {
   private Map effectCache = Maps.newHashMap();
   private static final Map SUB_ITEMS_CACHE = Maps.newLinkedHashMap();

   public List getEffects(int n) {
      List list = (List)this.effectCache.get(n);
      if (list == null) {
         list = PotionHelper.getPotionEffects(n, false);
         this.effectCache.put(n, list);
      }

      return list;
   }

   public static boolean isSplash(int n) {
      return (n & 16384) != 0;
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      super.getSubItems(item, creativeTabs, list);
      if (SUB_ITEMS_CACHE.isEmpty()) {
         for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 1; j++) {
               int n;
               if (j == 0) {
                  n = i | 8192;
               } else {
                  n = i | 16384;
               }

               for (int k = 0; k <= 2; k++) {
                  int n2 = n;
                  if (k != 0) {
                     if (k == 1) {
                        n2 = n | 32;
                     } else if (k == 2) {
                        n2 = n | 64;
                     }
                  }

                  List potionEffects = PotionHelper.getPotionEffects(n2, false);
                  if (potionEffects != null && !potionEffects.isEmpty()) {
                     SUB_ITEMS_CACHE.put(potionEffects, n2);
                  }
               }
            }
         }
      }

      for (int n3 : (Iterable<Integer>)(SUB_ITEMS_CACHE.values())) {
         list.add(new ItemStack(item, 1, n3));
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      return n > 0 ? 16777215 : this.getColorFromDamage(stack.getMetadata());
   }

   public ItemPotion() {
      this.setMaxStackSize(1);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }

   @Override
   public boolean hasEffect(ItemStack stack) {
      List effects = this.getEffects(stack);
      return effects != null && !effects.isEmpty();
   }

   public List getEffects(ItemStack stack) {
      if (stack.hasTagCompound() && stack.getTagCompound().hasKey("CustomPotionEffects", 9)) {
         ArrayList list = Lists.newArrayList();
         NBTTagList tagList = stack.getTagCompound().getTagList("CustomPotionEffects", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            PotionEffect potionEffect2 = PotionEffect.readCustomPotionEffectFromNBT(tagList.getCompoundTagAt(i));
            if (potionEffect2 != null) {
               list.add(potionEffect2);
            }
         }

         return list;
      } else {
         List list2 = (List)this.effectCache.get(stack.getMetadata());
         if (list2 == null) {
            list2 = PotionHelper.getPotionEffects(stack.getMetadata(), false);
            this.effectCache.put(stack.getMetadata(), list2);
         }

         return list2;
      }
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
      if (!player.capabilities.isCreativeMode) {
         stack.stackSize--;
      }

      if (!world.isRemote) {
         List effects = this.getEffects(stack);
         if (effects != null) {
            for (PotionEffect potionEffect2 : (Iterable<PotionEffect>)(effects)) {
               player.addPotionEffect(new PotionEffect(potionEffect2));
            }
         }
      }

      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      if (!player.capabilities.isCreativeMode) {
         if (stack.stackSize <= 0) {
            return new ItemStack(Items.glass_bottle);
         }

         player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
      }

      return stack;
   }

   public int getColorFromDamage(int n) {
      return PotionHelper.getLiquidColor(n, false);
   }

   public boolean isEffectInstant(int n) {
      List effects = this.getEffects(n);
      if (effects != null && !effects.isEmpty()) {
         for (PotionEffect potionEffect2 : (Iterable<PotionEffect>)(effects)) {
            if (Potion.potionTypes[potionEffect2.getPotionID()].isInstant()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (isSplash(stack.getMetadata())) {
         if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
         }

         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityPotion(world, player, stack));
         }

         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         return stack;
      } else {
         player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
         return stack;
      }
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.getMetadata() != 0) {
         List effects = Items.potionitem.getEffects(stack);
         HashMultimap hashMultimap = HashMultimap.create();
         if (effects != null && !effects.isEmpty()) {
            for (PotionEffect potionEffect2 : (Iterable<PotionEffect>)(effects)) {
               Object obj = StatCollector.translateToLocal(potionEffect2.getEffectName()).trim();
               Potion potion = Potion.potionTypes[potionEffect2.getPotionID()];
               Map attributeModifierMap = potion.getAttributeModifierMap();
               if (attributeModifierMap != null && attributeModifierMap.size() > 0) {
                  for (Entry entry : (Iterable<Entry>)(attributeModifierMap.entrySet())) {
                     AttributeModifier value = (AttributeModifier)entry.getValue();
                     AttributeModifier attributeModifier = new AttributeModifier(
                        value.getName(), potion.getAttributeModifierAmount(potionEffect2.getAmplifier(), value), value.getOperation()
                     );
                     hashMultimap.put(((IAttribute)entry.getKey()).getAttributeUnlocalizedName(), attributeModifier);
                  }
               }

               if (potionEffect2.getAmplifier() > 0) {
                  obj = obj + " " + StatCollector.translateToLocal("potion.potency." + potionEffect2.getAmplifier()).trim();
               }

               if (potionEffect2.getDuration() > 20) {
                  obj = obj + " (" + Potion.getDurationString(potionEffect2) + ")";
               }

               if (potion.isBadEffect()) {
                  list.add(EnumChatFormatting.RED.toString() + obj);
               } else {
                  list.add(EnumChatFormatting.GRAY.toString() + obj);
               }
            }
         } else {
            Object obj2 = StatCollector.translateToLocal("potion.empty").trim();
            list.add(EnumChatFormatting.GRAY.toString() + obj2);
         }

         if (!hashMultimap.isEmpty()) {
            list.add("");
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));

            for (Entry entry2 : (Iterable<Entry>)(hashMultimap.entries())) {
               AttributeModifier value2 = (AttributeModifier)entry2.getValue();
               double amount = value2.getAmount();
               double d;
               if (value2.getOperation() != 1 && value2.getOperation() != 2) {
                  d = value2.getAmount();
               } else {
                  d = value2.getAmount() * 100.0;
               }

               if (amount > 0.0) {
                  list.add(
                     EnumChatFormatting.BLUE
                        + StatCollector.translateToLocalFormatted(
                           "attribute.modifier.plus." + value2.getOperation(),
                           new Object[]{ItemStack.DECIMALFORMAT.format(d), StatCollector.translateToLocal("attribute.name." + (String)entry2.getKey())}
                        )
                  );
               } else if (amount < 0.0) {
                  d *= -1.0;
                  list.add(
                     EnumChatFormatting.RED
                        + StatCollector.translateToLocalFormatted(
                           "attribute.modifier.take." + value2.getOperation(),
                           new Object[]{ItemStack.DECIMALFORMAT.format(d), StatCollector.translateToLocal("attribute.name." + (String)entry2.getKey())}
                        )
                  );
               }
            }
         }
      }
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 32;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.DRINK;
   }

   @Override
   public String getItemStackDisplayName(ItemStack stack) {
      if (stack.getMetadata() == 0) {
         return StatCollector.translateToLocal("item.emptyPotion.name").trim();
      } else {
         String str = "";
         if (isSplash(stack.getMetadata())) {
            str = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " ";
         }

         List effects = Items.potionitem.getEffects(stack);
         if (effects != null && !effects.isEmpty()) {
            String effectName = ((PotionEffect)effects.get(0)).getEffectName();
            effectName = effectName + ".postfix";
            return str + StatCollector.translateToLocal(effectName).trim();
         } else {
            return StatCollector.translateToLocal(PotionHelper.getPotionPrefix(stack.getMetadata())).trim() + " " + super.getItemStackDisplayName(stack);
         }
      }
   }
}
