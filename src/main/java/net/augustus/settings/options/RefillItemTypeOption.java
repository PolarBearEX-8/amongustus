package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.utils.player.ContainerSlotQuery;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import net.augustus.modules.legit.Range;
import net.augustus.utils.interfaces.MC;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public enum RefillItemTypeOption implements SelectableOption {
   SOUPS("Soups", true, (obj, obj2) -> findSoupSlots((Integer)obj, (Integer)obj2)),
   HEAL_POTS("HealPots", true, (obj, obj2) -> findHealPotionSlots((Integer)obj, (Integer)obj2));
   private final BiFunction slotFinder;
   private final String name;
   private boolean selected;

   private RefillItemTypeOption(String str, boolean flag, BiFunction biFunction) {
      this.name = str;
      this.slotFinder = biFunction;
      this.selected = flag;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public List findMatchingSlots(int n, int n2) {
      return (List)this.slotFinder.apply(n, n2);
   }

   private static List findHealPotionSlots(Integer num, Integer num2) {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(MC.mc.thePlayer.inventoryContainer);
      int n = num;
      containerSlotQuery = containerSlotQuery.fromSlot(n);
      int n2 = num2;
      containerSlotQuery = containerSlotQuery.toSlot(n2);
      return (List)containerSlotQuery.filterItemExact(Items.potionitem).toSlotIndexList().stream().filter(obj -> {
         ItemStack stack = MC.mc.thePlayer.inventoryContainer.getSlot((Integer)obj).getStack();
         if (stack != null && stack.getItem() instanceof ItemPotion) {
            List effects = ((ItemPotion)stack.getItem()).getEffects(stack);
            if (effects != null && !effects.isEmpty()) {
               return ((PotionEffect)effects.get(0)).getPotionID() == 6;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }).sorted(Comparator.comparingDouble(obj2 -> {
         ItemStack stack2 = MC.mc.thePlayer.inventoryContainer.getSlot((Integer)obj2).getStack();
         if (stack2 != null && stack2.getItem() instanceof ItemPotion) {
            List effects2 = ((ItemPotion)stack2.getItem()).getEffects(stack2);
            if (effects2 != null && !effects2.isEmpty()) {
               PotionEffect potionEffect2 = (PotionEffect)effects2.get(0);
               return potionEffect2.getPotionID() != 6 ? -10.0 : (double)potionEffect2.getAmplifier();
            } else {
               return -10.0;
            }
         } else {
            return -10.0;
         }
      })).collect(Collectors.toList());
   }

   private static List findSoupSlots(Integer num, Integer num2) {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(MC.mc.thePlayer.inventoryContainer);
      int n = num;
      containerSlotQuery = containerSlotQuery.fromSlot(n);
      int n2 = num2;
      containerSlotQuery = containerSlotQuery.toSlot(n2);
      return containerSlotQuery.filterItemExact(Items.mushroom_stew).toSlotIndexList();
   }

}
