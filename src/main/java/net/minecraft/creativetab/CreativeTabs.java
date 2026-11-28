package net.minecraft.creativetab;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs {
   public static final CreativeTabs[] creativeTabArray = new CreativeTabs[12];
   public static final CreativeTabs tabBlock = new CreativeTabs.DefaultCreativeTabs(0, "buildingBlocks");
   public static final CreativeTabs tabDecorations = new CreativeTabs.DefaultCreativeTabs2(1, "decorations");
   public static final CreativeTabs tabRedstone = new CreativeTabs.DefaultCreativeTabs3(2, "redstone");
   public static final CreativeTabs tabTransport = new CreativeTabs.DefaultCreativeTabs4(3, "transportation");
   public static final CreativeTabs tabMisc = new CreativeTabs.DefaultCreativeTabs5(4, "misc").setRelevantEnchantmentTypes(new EnumEnchantmentType[]{EnumEnchantmentType.ALL});
   public static final CreativeTabs tabAllSearch = new CreativeTabs.DefaultCreativeTabs6(5, "search").setBackgroundImageName("item_search.png");
   public static final CreativeTabs tabFood = new CreativeTabs.DefaultCreativeTabs7(6, "food");
   public static final CreativeTabs tabTools = new CreativeTabs.DefaultCreativeTabs8(7, "tools")
      .setRelevantEnchantmentTypes(new EnumEnchantmentType[]{EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE});
   public static final CreativeTabs tabCombat = new CreativeTabs.DefaultCreativeTabs9(8, "combat")
      .setRelevantEnchantmentTypes(
         new EnumEnchantmentType[]{
            EnumEnchantmentType.ARMOR,
            EnumEnchantmentType.ARMOR_FEET,
            EnumEnchantmentType.ARMOR_HEAD,
            EnumEnchantmentType.ARMOR_LEGS,
            EnumEnchantmentType.ARMOR_TORSO,
            EnumEnchantmentType.BOW,
            EnumEnchantmentType.WEAPON
         }
      );
   public static final CreativeTabs tabBrewing = new CreativeTabs.DefaultCreativeTabs10(9, "brewing");
   public static final CreativeTabs tabMaterials = new CreativeTabs.DefaultCreativeTabs11(10, "materials");
   public static final CreativeTabs tabInventory = new CreativeTabs.DefaultCreativeTabs12(11, "inventory").setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   private final int tabIndex;
   private final String tabLabel;
   private String theTexture = "items.png";
   private boolean hasScrollbar = true;
   private boolean drawTitle = true;
   private EnumEnchantmentType[] enchantmentTypes;
   private ItemStack iconItemStack;

   public CreativeTabs setNoScrollbar() {
      this.hasScrollbar = false;
      return this;
   }

   public abstract Item getTabIconItem();

   public int getTabColumn() {
      return this.tabIndex % 6;
   }

   public boolean shouldHidePlayerInventory() {
      return this.hasScrollbar;
   }

   public CreativeTabs setRelevantEnchantmentTypes(EnumEnchantmentType[] enumEnchantmentTypes) {
      this.enchantmentTypes = enumEnchantmentTypes;
      return this;
   }

   public String getBackgroundImageName() {
      return this.theTexture;
   }

   public CreativeTabs setBackgroundImageName(String str) {
      this.theTexture = str;
      return this;
   }

   public CreativeTabs(int n, String str) {
      this.tabIndex = n;
      this.tabLabel = str;
      creativeTabArray[n] = this;
   }

   public void addEnchantmentBooksToList(List list, EnumEnchantmentType[] enumEnchantmentTypes) {
      for (Enchantment enchantment : Enchantment.enchantmentsBookList) {
         if (enchantment != null && enchantment.type != null) {
            boolean flag = false;

            for (int i = 0; i < enumEnchantmentTypes.length && !flag; i++) {
               if (enchantment.type == enumEnchantmentTypes[i]) {
                  flag = true;
               }
            }

            if (flag) {
               list.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
            }
         }
      }
   }

   public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
      return this.enchantmentTypes;
   }

   public int getIconItemDamage() {
      return 0;
   }

   public int getTabIndex() {
      return this.tabIndex;
   }

   public CreativeTabs setNoTitle() {
      this.drawTitle = false;
      return this;
   }

   public String getTranslatedTabLabel() {
      return "itemGroup." + this.getTabLabel();
   }

   public void displayAllReleventItems(List list) {
      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         if (item != null && item.getCreativeTab() == this) {
            item.getSubItems(item, this, list);
         }
      }

      if (this.getRelevantEnchantmentTypes() != null) {
         this.addEnchantmentBooksToList(list, this.getRelevantEnchantmentTypes());
      }
   }

   public boolean hasRelevantEnchantmentType(EnumEnchantmentType enumEnchantmentType) {
      if (this.enchantmentTypes == null) {
         return false;
      } else {
         for (EnumEnchantmentType enumEnchantmentType2 : this.enchantmentTypes) {
            if (enumEnchantmentType2 == enumEnchantmentType) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isTabInFirstRow() {
      return this.tabIndex < 6;
   }

   public String getTabLabel() {
      return this.tabLabel;
   }

   public boolean drawInForegroundOfTab() {
      return this.drawTitle;
   }

   public ItemStack getIconItemStack() {
      if (this.iconItemStack == null) {
         this.iconItemStack = new ItemStack(this.getTabIconItem(), 1, this.getIconItemDamage());
      }

      return this.iconItemStack;
   }


   public static final class DefaultCreativeTabs extends CreativeTabs {
      public DefaultCreativeTabs(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.brick_block);
      }
   }

   public static final class DefaultCreativeTabs2 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.double_plant);
      }

      public DefaultCreativeTabs2(int n, String str) {
         super(n, str);
      }

      @Override
      public int getIconItemDamage() {
         return BlockDoublePlant.EnumPlantType.PAEONIA.getMeta();
      }
   }

   public static final class DefaultCreativeTabs3 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Items.redstone;
      }

      public DefaultCreativeTabs3(int n, String str) {
         super(n, str);
      }
   }

   public static final class DefaultCreativeTabs4 extends CreativeTabs {
      public DefaultCreativeTabs4(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.golden_rail);
      }
   }

   public static final class DefaultCreativeTabs5 extends CreativeTabs {
      public DefaultCreativeTabs5(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Items.lava_bucket;
      }
   }

   public static final class DefaultCreativeTabs6 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Items.compass;
      }

      public DefaultCreativeTabs6(int n, String str) {
         super(n, str);
      }
   }

   public static final class DefaultCreativeTabs7 extends CreativeTabs {
      public DefaultCreativeTabs7(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Items.apple;
      }
   }

   public static final class DefaultCreativeTabs8 extends CreativeTabs {
      public DefaultCreativeTabs8(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Items.iron_axe;
      }
   }

   public static final class DefaultCreativeTabs9 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Items.golden_sword;
      }

      public DefaultCreativeTabs9(int n, String str) {
         super(n, str);
      }
   }

   public static final class DefaultCreativeTabs10 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Items.potionitem;
      }

      public DefaultCreativeTabs10(int n, String str) {
         super(n, str);
      }
   }

   public static final class DefaultCreativeTabs11 extends CreativeTabs {
      @Override
      public Item getTabIconItem() {
         return Items.stick;
      }

      public DefaultCreativeTabs11(int n, String str) {
         super(n, str);
      }
   }

   public static final class DefaultCreativeTabs12 extends CreativeTabs {
      public DefaultCreativeTabs12(int n, String str) {
         super(n, str);
      }

      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.chest);
      }
   }
}
