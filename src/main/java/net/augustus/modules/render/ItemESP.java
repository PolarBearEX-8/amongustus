package net.augustus.modules.render;

import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.options.ItemEspItemOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class ItemESP extends Module implements RenderListener, TickListener {
   public final StringValue mode;
   public final ColorSetting boxColor;
   public final BooleanValue renderCustomItemnames;
   public final BooleanValue applyNametagsRendering;
   private static BooleanValue onlyShowWhitelistedItems;
   private static HashMap ITEM_OPTIONS = new HashMap();

   public ItemESP() {
       super("ItemESP", "ArtikelDurchWandSehen", Color.RED, Categorys.RENDER);
      this.mode = new StringValue(this, "Mode", "Box", new String[]{"Box", "Vanilla"});
      this.boxColor = new ColorSetting(this, "BoxColor", new Color(71, 151, 255, 100));
      this.renderCustomItemnames = new BooleanValue(this, "Render custom itemnames", false);
      BooleanValue booleanValue = new BooleanValue(this, "Apply nametags rendering", false);
      BooleanValue renderCustomItemnames2 = this.renderCustomItemnames;
      this.applyNametagsRendering = (BooleanValue)booleanValue.withVisibility(() -> renderCustomItemnames2.getValue());
      onlyShowWhitelistedItems = new BooleanValue(this, "Only show whitelisted items", false);
      HashSet set = new HashSet();

      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         String unlocalizedName = item.getUnlocalizedName();
         unlocalizedName = unlocalizedName.substring(5);
         if (set.contains(unlocalizedName)) {
            unlocalizedName = unlocalizedName + "-" + Item.getIdFromItem(item);
         } else {
            set.add(unlocalizedName);
         }

         ITEM_OPTIONS.put(
            item,
            new ItemEspItemOption(
               unlocalizedName,
               item instanceof ItemTool
                  || item instanceof ItemArmor
                  || item instanceof ItemSword
                  || item == Items.gold_ingot
                  || item == Items.iron_ingot
                  || item == Items.diamond
                  || item == Items.brick
                  || item == Items.golden_apple,
               null
            )
         );
      }

      ItemEspItemOption[] itemEspItemOptions = (ItemEspItemOption[])ITEM_OPTIONS.values().toArray(new ItemEspItemOption[0]);
      Arrays.sort(itemEspItemOptions, Comparator.comparing(ItemEspItemOption::getName));
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "Whitelisted items", itemEspItemOptions);
      booleanValue = onlyShowWhitelistedItems;
      BooleanValue booleanValue3 = booleanValue;
      multiOptionSetting.visibleWhen(() -> booleanValue3.getValue());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.mode.getSelectedValue());
   }

   @Override
   public void updateEventSubscriptions() {
   }

   public static boolean isItemWhitelisted(Item item) {
      if (!onlyShowWhitelistedItems.getValue()) {
         return true;
      } else {
         ItemEspItemOption itemEspItemOption = (ItemEspItemOption)ITEM_OPTIONS.get(item);
         return itemEspItemOption != null && itemEspItemOption.selected;
      }
   }


}
