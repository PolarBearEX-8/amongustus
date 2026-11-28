package net.augustus.modules.player.crafter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;

public class CraftingRecipePlanner {

   public static CraftingRecipePlan findRecipePlan(Item item) {

      for (Object obj : CraftingManager.getInstance().getRecipeList()) {
         if (obj instanceof ShapedRecipes) {
            ShapedRecipes shapedRecipes = (ShapedRecipes)obj;
            ItemStack recipeOutput = shapedRecipes.getRecipeOutput();
            if (recipeOutput != null && recipeOutput.getItem() == item) {
               return toRecipePlan(shapedRecipes);
            }
         }
      }

      return null;
   }

   public static List planCraftingSteps(Item item, CraftingInventorySnapshot snapshot) {
      SimulatedCraftingInventory inventory = new SimulatedCraftingInventory(snapshot);
      ArrayList list = new ArrayList();
      if (!resolveCraftRecursive(item, inventory, list, new HashSet())) {
         return null;
      } else {
         CraftingRecipePlan craftingRecipePlan = findRecipePlan(item);
         if (craftingRecipePlan != null) {
            if (!inventory.canFitItem(item, craftingRecipePlan.output.stackSize)) {
               return null;
            }
         }

         return list;
      }
   }

   private static boolean resolveCraftRecursive(Item item, SimulatedCraftingInventory inventory, List list, Set set) {
      if (set.contains(item)) {
         return false;
      } else {
         set.add(item);
         CraftingRecipePlan craftingRecipePlan = findRecipePlan(item);
         if (craftingRecipePlan == null) {
            set.remove(item);
            return false;
         } else {
            LinkedHashMap map = new LinkedHashMap();

            for (RecipeIngredientSlot recipeIngredientSlot : (Iterable<RecipeIngredientSlot>)(craftingRecipePlan.ingredientSlots)) {
               map.merge(recipeIngredientSlot.item, 1, (obj, obj2) -> Integer.sum((Integer)obj, (Integer)obj2));
            }

            for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
               Item key = (Item)entry.getKey();
               int value = (Integer)entry.getValue();

               while (inventory.getItemCount(key) < value) {
                  CraftingRecipePlan craftingRecipePlan2 = findRecipePlan(key);
                  if (craftingRecipePlan2 == null) {
                     set.remove(item);
                     return false;
                  }

                  int stackSize = craftingRecipePlan2.output.stackSize;
                  if (!resolveCraftRecursive(key, inventory, list, new HashSet(set))) {
                     set.remove(item);
                     return false;
                  }

                  if (!inventory.canFitItem(key, stackSize)) {
                     set.remove(item);
                     return false;
                  }

                  inventory.addItem(key, stackSize);
               }

               inventory.consumeItem(key, value);
            }

            list.addAll(buildStepsForRecipe(craftingRecipePlan));
            set.remove(item);
            return true;
         }
      }
   }

   public static List buildStepsForRecipe(CraftingRecipePlan craftingRecipePlan) {
      ArrayList list = new ArrayList();
      LinkedHashMap map = new LinkedHashMap();

      for (RecipeIngredientSlot recipeIngredientSlot : (Iterable<RecipeIngredientSlot>)(craftingRecipePlan.ingredientSlots)) {
         ((List)map.computeIfAbsent(recipeIngredientSlot.item, obj -> new ArrayList<>())).add(recipeIngredientSlot.craftingSlot);
      }

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         List list2 = orderSlotsByNearestNeighbor((List)entry.getValue());
         list.add(new PickupIngredientStep((Item)entry.getKey(), list2.size()));

         for (int n : (Iterable<Integer>)(list2)) {
            list.add(new PlaceIngredientStep(n));
         }

         list.add(new ReturnLeftoverStep());
      }

      list.add(new TakeCraftResultStep(0));
      return list;
   }

   private static List orderSlotsByNearestNeighbor(List list) {
      if (list.size() <= 1) {
         return list;
      } else {
         ArrayList list2 = new ArrayList(list);
         ArrayList list3 = new ArrayList(list.size());
         int n = (Integer)list2.remove(0);
         list3.add(n);

         while (!list2.isEmpty()) {
            int n2 = -1;
            double maxvalue = Double.MAX_VALUE;

            for (int n3 : (Iterable<Integer>)(list2)) {
               double d = gridSlotDistance(n, n3);
               if (d < maxvalue) {
                  maxvalue = d;
                  n2 = n3;
               }
            }

            list2.remove(Integer.valueOf(n2));
            list3.add(n2);
            n = n2;
         }

         return list3;
      }
   }

   private static double gridSlotDistance(int n, int n2) {
      int n3 = (n - 1) % 3;
      int n4 = (n - 1) / 3;
      int n5 = (n2 - 1) % 3;
      int n6 = (n2 - 1) / 3;
      int n7 = n3 - n5;
      int n8 = n4 - n6;
      return Math.sqrt((double)(n7 * n7 + n8 * n8));
   }

   private static CraftingRecipePlan toRecipePlan(ShapedRecipes shapedRecipes) {
      ArrayList list = new ArrayList();
      ItemStack[] recipeItems = shapedRecipes.getRecipeItems();
      int recipeWidth = shapedRecipes.getRecipeWidth();
      int n = recipeItems.length / recipeWidth;
      int n2 = (3 - recipeWidth) / 2;
      int n3 = (3 - n) / 2;

      for (int i = 0; i < recipeItems.length; i++) {
         if (recipeItems[i] != null) {
            int n6 = (i / recipeWidth + n3) * 3 + (i % recipeWidth + n2) + 1;
            list.add(new RecipeIngredientSlot(n6, recipeItems[i].getItem()));
         }
      }

      return new CraftingRecipePlan(shapedRecipes.getRecipeOutput(), list);
   }
}
