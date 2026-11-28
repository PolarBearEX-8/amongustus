package net.augustus.settings.options;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SelectableOption;
import net.augustus.utils.ActionRateAccumulator;
import java.util.function.BiFunction;
import net.augustus.modules.world.FastBreak;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;

public enum FastPlaceItemOption implements SelectableOption {
   BLOCKS("Blocks", (obj, obj2) -> obj2 instanceof ItemBlock),
   XP_BOTTLES("XP-Bottles", (obj, obj2) -> obj2 instanceof ItemExpBottle),
   SNOWBALLS("SnowBalls", (obj, obj2) -> obj2 instanceof ItemSnowball),
   EGGS("Eggs", (obj, obj2) -> obj2 instanceof ItemEgg),
   FOOD("Food", (obj, obj2) -> obj2 instanceof ItemFood),
   POTIONS("Potions", (obj, obj2) -> {
      return obj2 instanceof ItemPotion && !ItemPotion.isSplash(((ItemStack)obj).getMetadata());
   }),
   REST("Rest", (obj, obj2) -> {
      return !(obj2 instanceof ItemFood) && !(obj2 instanceof ItemBucketMilk) && (!(obj2 instanceof ItemPotion) || ItemPotion.isSplash(((ItemStack)obj).getMetadata()));
   });
   public String name;
   public boolean selected;
   public BiFunction itemMatcher;
   public RandomizedRangeSettings cpsRange;
   public RandomizedRangeSettings doubleClickCpsRange;
   public final ActionRateAccumulator cpsAccumulator = new ActionRateAccumulator(20, 0.0);
   public final ActionRateAccumulator doubleClickCpsAccumulator = new ActionRateAccumulator(20, 0.0);

   private FastPlaceItemOption(String str, BiFunction biFunction) {
      this.name = str;
      this.itemMatcher = biFunction;
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

   public boolean matchesItem(ItemStack stack) {
      return (Boolean)this.itemMatcher.apply(stack, stack.getItem());
   }

}
