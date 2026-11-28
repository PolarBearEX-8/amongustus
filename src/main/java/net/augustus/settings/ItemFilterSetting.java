package net.augustus.settings;

import net.augustus.settings.options.ItemFilterOption;
import net.augustus.utils.UnaryFunction;
import net.augustus.utils.player.InventoryUtil;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import net.augustus.modules.Module;
import net.augustus.utils.TimeHelper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemFilterSetting {
   private final HashMap itemOptions;

   public boolean isItemAllowed(Item item) {
      ItemFilterOption itemFilterOption = (ItemFilterOption)this.itemOptions.get(item);
      return itemFilterOption == null ? false : ItemFilterOption.isOptionSelected(itemFilterOption) && InventoryUtil.isNotSoupCraftIngredient(item);
   }

   public ItemFilterSetting(Module module, String str, SettingGroup doubleValue, SettingVisibilityCondition condition, UnaryFunction unaryFunction) {
       super();
      this.itemOptions = new HashMap();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Item.itemRegistry.getKeys())) {
         String resourcePath = location.getResourcePath();
         Item byNameOrId = Item.getByNameOrId(resourcePath);
         this.itemOptions.put(byNameOrId, new ItemFilterOption(this, resourcePath, (Boolean)unaryFunction.apply(byNameOrId)));
      }

      ItemFilterOption[] itemFilterOptions = (ItemFilterOption[])this.itemOptions.values().toArray(new ItemFilterOption[0]);
      Arrays.sort(itemFilterOptions, Comparator.comparing(ItemFilterOption::getName));
      if (doubleValue == null) {
         (new MultiOptionSetting(module, str, itemFilterOptions)).visibleWhen(condition).markVisualOnly();
      } else {
         (new MultiOptionSetting(module, str, itemFilterOptions, doubleValue)).visibleWhen(condition).markVisualOnly();
      }
   }
}
