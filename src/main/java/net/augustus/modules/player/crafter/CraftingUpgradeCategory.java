package net.augustus.modules.player.crafter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingUpgradeCategory {
   private final List candidateItems = new ArrayList();
   private final Predicate itemFilter;
   private final Function itemScorer;

   public CraftingUpgradeCategory(Predicate predicate, Function function, Item[] items) {
      this.itemFilter = predicate;
      this.itemScorer = function;
      this.candidateItems.addAll(Arrays.asList(items));
   }

   public Predicate getItemFilter() {
      return this.itemFilter;
   }

   public Function getItemScorer() {
      return this.itemScorer;
   }

   public List getCandidateItems() {
      return Collections.unmodifiableList(this.candidateItems);
   }

   public boolean matches(ItemStack stack) {
      return this.getItemFilter().test(stack);
   }

   public double score(ItemStack stack) {
      return (Double)this.getItemScorer().apply(stack);
   }
}
