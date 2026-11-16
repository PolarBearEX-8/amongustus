package client.setting;

import client.util.InventoryUtil;
import client.util.MinecraftAccessor;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionEffect;

public enum RefillPotionType implements NamedOption {
   SOUPS("Soups", true, RefillPotionType::resolveListByInteger),
   HEAL_POTS("HealPots", true, RefillPotionType::resolveList);

   public final BiFunction<Integer, Integer, List> biFunction;
   public final String labelText;
   public boolean enabled;
   public static final RefillPotionType[] ENUM_VALUES = new RefillPotionType[]{SOUPS, HEAL_POTS};

   private static List resolveList(Integer count, Integer count2) {
      Container container = MinecraftAccessor.minecraft.thePlayer.container;
      int count3 = count;
      int count4 = count2;
      ItemPotion potion = Items.potion;
      return (List)InventoryUtil.resolveArrayList(container, count3, count4, potion)
         .stream()
         .filter(item -> matchesInteger((Integer)item))
         .sorted(Comparator.comparingDouble(item -> computeValue((Integer)item)))
         .collect(Collectors.toList());
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   private static boolean matchesInteger(Integer count) {
      ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(count).getStack();
      if (stack != null && stack.getItem() instanceof ItemPotion) {
         List list = ((ItemPotion)stack.getItem()).getList(stack);
         return list != null && !list.isEmpty() ? ((PotionEffect)list.get(0)).getIndexOffset3() == 6 : false;
      } else {
         return false;
      }
   }

   private static double computeValue(Integer count) {
      ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(count).getStack();
      if (stack != null && stack.getItem() instanceof ItemPotion) {
         List list = ((ItemPotion)stack.getItem()).getList(stack);
         if (list != null && !list.isEmpty()) {
            PotionEffect potionEffect = (PotionEffect)list.get(0);
            return potionEffect.getIndexOffset3() != 6 ? -10.0 : (double)potionEffect.getIndexOffset2();
         } else {
            return -10.0;
         }
      } else {
         return -10.0;
      }
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public List getList(int count, int count2) {
      return this.biFunction.apply(count, count2);
   }

   private static List resolveListByInteger(Integer count, Integer count2) {
      Container container = MinecraftAccessor.minecraft.thePlayer.container;
      int count3 = count;
      int count4 = count2;
      Item mushroomStew = Items.mushroomStew;
      return InventoryUtil.resolveArrayList(container, count3, count4, mushroomStew);
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private RefillPotionType(String text, boolean flag, BiFunction<Integer, Integer, List> biFunction2) {
      this.labelText = text;
      this.biFunction = biFunction2;
      this.enabled = flag;
   }
}
