package net.minecraft.item;

import client.setting.EnhancedOptionType;
import client.util.AuxiliaryClientManagerResolver;
import client.util.EnchantmentManager;
import client.util.InterpolationCounter;
import client.util.InterpolationDirector;
import client.util.InterpolationFactory;
import client.util.InterpolationObserver;
import client.util.InterpolationScheduler;
import client.util.InterpolationService;
import client.util.InterpolationSynchronizer;
import client.util.InterpolationSystem;
import client.util.InterpolationTracker;
import client.util.InterpolationWatcher;
import java.util.List;

public abstract class ItemGroupManager {
   public static final ItemGroupManager[] itemGroupManagerArray = new ItemGroupManager[12];
   public static final ItemGroupManager itemGroupManager10 = new InterpolationCounter(0, "buildingBlocks");
   public static final ItemGroupManager itemGroupManager8 = new InterpolationService(1, "decorations");
   public static final ItemGroupManager itemGroupManager9 = new InterpolationSynchronizer(2, "redstone");
   public static final ItemGroupManager itemGroupManager12 = new InterpolationFactory(3, "transportation");
   public static final ItemGroupManager itemGroupManager5 = new InterpolationObserver(4, "misc")
      .withEnhancedOptionTypeArray(new EnhancedOptionType[]{EnhancedOptionType.ALL});
   public static final ItemGroupManager itemGroupManager6 = new ItemGroupManagerAdapter(5, "search").withLabelText2("item_search.png");
   public static final ItemGroupManager itemGroupManager3 = new InterpolationTracker(6, "food");
   public static final ItemGroupManager itemGroupManager2 = new AuxiliaryItemGroupManagerAdapter(7, "tools")
      .withEnhancedOptionTypeArray(new EnhancedOptionType[]{EnhancedOptionType.DIGGER, EnhancedOptionType.FISHING_ROD, EnhancedOptionType.BREAKABLE});
   public static final ItemGroupManager itemGroupManager4 = new InterpolationScheduler(8, "combat")
      .withEnhancedOptionTypeArray(
         new EnhancedOptionType[]{
            EnhancedOptionType.ARMOR,
            EnhancedOptionType.ARMOR_FEET,
            EnhancedOptionType.ARMOR_HEAD,
            EnhancedOptionType.ARMOR_LEGS,
            EnhancedOptionType.ARMOR_TORSO,
            EnhancedOptionType.BOW,
            EnhancedOptionType.WEAPON
         }
      );
   public static final ItemGroupManager itemGroupManager7 = new InterpolationSystem(9, "brewing");
   public static final ItemGroupManager itemGroupManager11 = new InterpolationWatcher(10, "materials");
   public static final ItemGroupManager itemGroupManager = new InterpolationDirector(11, "inventory")
      .withLabelText2("inventory.png")
      .getItemGroupManager()
      .getItemGroupManagerAsItemGroupManager();
   public final int count;
   public final String labelText;
   public String labelText2 = "items.png";
   public boolean enabled2 = true;
   public boolean enabled = true;
   public EnhancedOptionType[] enhancedOptionTypeArray;
   public ItemStack itemStack;

   public ItemGroupManager getItemGroupManager() {
      this.enabled2 = false;
      return this;
   }

   public ItemStack getItemStack() {
      if (this.itemStack == null) {
         this.itemStack = new ItemStack(this.getApple(), 1, this.getCount());
      }

      return this.itemStack;
   }

   public int getCountAsIndex() {
      return this.count % 6;
   }

   public boolean isActive() {
      return this.count < 6;
   }

   public EnhancedOptionType[] getEnhancedOptionTypeArray() {
      return this.enhancedOptionTypeArray;
   }

   public boolean matchesEnhancedOptionType(EnhancedOptionType enhancedOptionType) {
      if (this.enhancedOptionTypeArray == null) {
         return false;
      } else {
         EnhancedOptionType[] enhancedOptionTypeArray2 = this.enhancedOptionTypeArray;
         int length = enhancedOptionTypeArray2.length;

         for (int index = 0; index < length; index++) {
            if (enhancedOptionTypeArray2[index] == enhancedOptionType) {
               return true;
            }
         }

         return false;
      }
   }

   public ItemGroupManager withLabelText2(String text) {
      this.labelText2 = text;
      return this;
   }

   public ItemGroupManager withEnhancedOptionTypeArray(EnhancedOptionType[] enhancedOptionTypeArray2) {
      this.enhancedOptionTypeArray = enhancedOptionTypeArray2;
      return this;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount() {
      return 0;
   }

   public void applyList(List list) {
      for (Item item : (Iterable<Item>) Item.iterableExtensionAdapter) {
         if (item != null && item.getItemGroupManager() == this) {
            item.processAction(item, this, list);
         }
      }

      if (this.getEnhancedOptionTypeArray() != null) {
         this.applyList(list, this.getEnhancedOptionTypeArray());
      }
   }

   public String getText() {
      return "itemGroup." + this.getLabelText();
   }

   public abstract Item getApple();

   public int getCount2() {
      return this.count;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public ItemGroupManager getItemGroupManagerAsItemGroupManager() {
      this.enabled = false;
      return this;
   }

   public void applyList(List list, EnhancedOptionType[] enhancedOptionTypeArray2) {
      for (EnchantmentManager enchantmentManager : EnchantmentManager.enchantmentManagerArray2) {
         if (enchantmentManager != null && enchantmentManager.enhancedOptionType != null) {
            boolean flag = false;

            for (int index = 0; index < enhancedOptionTypeArray2.length && !flag; index++) {
               if (enchantmentManager.enhancedOptionType == enhancedOptionTypeArray2[index]) {
                  flag = true;
               }
            }

            if (flag) {
               list.add(Items.enchantedBook.resolveItemStack(new AuxiliaryClientManagerResolver(enchantmentManager, enchantmentManager.getCount())));
            }
         }
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public ItemGroupManager(int count2, String text) {
      this.count = count2;
      this.labelText = text;
      itemGroupManagerArray[count2] = this;
   }
}
