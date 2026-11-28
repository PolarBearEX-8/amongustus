package net.augustus.modules.player;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.modules.player.crafter.CraftingUpgradeCategory;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.RangeSetting;
import net.augustus.modules.player.crafter.CraftingInventoryActions;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.modules.player.crafter.CraftingInventorySnapshot;
import net.augustus.modules.player.crafter.CraftingItemScorer;
import net.augustus.Augustus;
import net.augustus.modules.player.crafter.CraftingStepExecutor;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.player.crafter.CraftingRecipePlanner;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class Crafter extends Module implements TickListener, RenderListener, CraftingInventoryActions {
   public final DoubleValue startDelay;
   public final RangeSetting delay;
   public final BooleanValue smartDelay;
   public final StringValue eventToClick;
   public final StringValue clickMode;
   private final TimeHelper clickTimer;
   private final TimeHelper openTimer;
   private int nextClickDelayMs;
   private final List upgradeCategories;
   private int currentCategoryIndex;
   private boolean completed;
   private final CraftingStepExecutor craftingStepExecutor;

   public Crafter() {
       super("Crafter", "Handwerker", new Color(123, 230, 10), Categorys.PLAYER);
      this.startDelay = new DoubleValue(this, "StartDelay", 100.0, 0.0, 1000.0, 0);
      this.delay = new RangeSetting(this, "Delay", 70.0, 120.0, 0.0, 400.0, 0);
      this.smartDelay = new BooleanValue(this, "SmartDelay", true);
      this.eventToClick = new StringValue(this, "EventToClick", "EventTick", new String[]{"EventTick", "EventRender"});
      this.clickMode = new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.clickTimer = new TimeHelper();
      this.openTimer = new TimeHelper();
      this.nextClickDelayMs = 0;
      this.upgradeCategories = this.createUpgradeCategories();
      this.currentCategoryIndex = 0;
      this.completed = false;
      this.craftingStepExecutor = new CraftingStepExecutor();
   }

   private List createUpgradeCategories() {
      CraftingUpgradeCategory craftingUpgradeCategory = new CraftingUpgradeCategory(obj -> {
         return ((ItemStack)obj).getItem() instanceof ItemArmor && ((ItemArmor)((ItemStack)obj).getItem()).armorType == 0;
      }, obj2 -> CraftingItemScorer.scoreArmor((ItemStack)obj2), new Item[]{Items.diamond_helmet, Items.iron_helmet, Items.golden_helmet});
      CraftingUpgradeCategory craftingUpgradeCategory2 = new CraftingUpgradeCategory(obj3 -> {
         return ((ItemStack)obj3).getItem() instanceof ItemArmor && ((ItemArmor)((ItemStack)obj3).getItem()).armorType == 1;
      }, obj4 -> CraftingItemScorer.scoreArmor((ItemStack)obj4), new Item[]{Items.diamond_chestplate, Items.iron_chestplate, Items.golden_chestplate});
      CraftingUpgradeCategory craftingUpgradeCategory3 = new CraftingUpgradeCategory(obj5 -> {
         return ((ItemStack)obj5).getItem() instanceof ItemArmor && ((ItemArmor)((ItemStack)obj5).getItem()).armorType == 2;
      }, obj6 -> CraftingItemScorer.scoreArmor((ItemStack)obj6), new Item[]{Items.diamond_leggings, Items.iron_leggings, Items.golden_leggings});
      CraftingUpgradeCategory craftingUpgradeCategory4 = new CraftingUpgradeCategory(obj7 -> {
         return ((ItemStack)obj7).getItem() instanceof ItemArmor && ((ItemArmor)((ItemStack)obj7).getItem()).armorType == 3;
      }, obj8 -> CraftingItemScorer.scoreArmor((ItemStack)obj8), new Item[]{Items.diamond_boots, Items.iron_boots, Items.golden_boots});
      CraftingUpgradeCategory craftingUpgradeCategory5 = new CraftingUpgradeCategory(
         obj9 -> ((ItemStack)obj9).getItem() instanceof ItemSword,
         obj10 -> CraftingItemScorer.scoreSword((ItemStack)obj10),
         new Item[]{Items.diamond_sword, Items.iron_sword, Items.golden_sword, Items.stone_sword, Items.wooden_sword}
      );
      return Arrays.asList(craftingUpgradeCategory5, craftingUpgradeCategory, craftingUpgradeCategory2, craftingUpgradeCategory3, craftingUpgradeCategory4, new CraftingUpgradeCategory(obj11 -> ((ItemStack)obj11).getItem() instanceof ItemFishingRod, obj12 -> 1.0, new Item[]{Items.fishing_rod}));
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.eventToClick.getSelectedValue().equals("EventRender")) {
         this.processCrafting();
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.eventToClick.getSelectedValue().equals("EventTick")) {
         this.processCrafting();
      }
   }

   private void processCrafting() {
      if (!(mc.currentScreen instanceof GuiCrafting)) {
         this.resetCraftingState();
      } else if (!this.completed) {
         GuiCrafting currentScreen = (GuiCrafting)mc.currentScreen;
         if (this.openTimer.reached(this.startDelay.getValue())) {
            long nextClickDelayMs2 = (long)this.nextClickDelayMs;
            if (this.clickTimer.reached(nextClickDelayMs2)) {
               boolean flag;
               if (!this.craftingStepExecutor.isFinished()) {
                  flag = this.craftingStepExecutor.executeNextStep(this, currentScreen);
               } else {
                  this.planNextUpgrade(currentScreen);
                  flag = true;
               }

               if (flag) {
                  this.clickTimer.reset();
                  this.nextClickDelayMs = DefaultRandomGenerator.instance.nextInt(this.delay.getMinValue(), this.delay.getMaxValue());
               }

               return;
            }
         }
      }
   }

   private void planNextUpgrade(GuiCrafting guiCrafting) {
      if (this.hasHeldCursorStack()) {
         int n = this.findInventorySlot(guiCrafting, (Item)null, (byte)0);
         if (n >= 0) {
            this.performPickupClick(guiCrafting, n);
         }
      } else {
         CraftingInventorySnapshot snapshot = CraftingInventorySnapshot.capture(guiCrafting);

         while (this.currentCategoryIndex < this.upgradeCategories.size()) {
            CraftingUpgradeCategory craftingUpgradeCategory = (CraftingUpgradeCategory)this.upgradeCategories.get(this.currentCategoryIndex);
            this.currentCategoryIndex++;
            double bestCurrentScore = this.getBestCurrentScore(craftingUpgradeCategory);
            double d = -1.0;
            List list = null;

            for (Item item : (Iterable<Item>)(craftingUpgradeCategory.getCandidateItems())) {
               double d2 = craftingUpgradeCategory.score(new ItemStack(item, 1, 0));
               if (!(d2 <= bestCurrentScore) && !(d2 <= d)) {
                  List list2 = CraftingRecipePlanner.planCraftingSteps(item, snapshot);
                  if (list2 != null && !list2.isEmpty()) {
                     d = d2;
                     list = list2;
                  }
               }
            }

            if (list != null) {
               this.craftingStepExecutor.setSteps(list);
               return;
            }
         }

         this.completed = true;
      }
   }

   private double getBestCurrentScore(CraftingUpgradeCategory craftingUpgradeCategory) {
      double d = -1.0;

      for (int i = 0; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null && craftingUpgradeCategory.matches(stack)) {
            d = Math.max(d, craftingUpgradeCategory.score(stack));
         }
      }

      return d;
   }

   private void resetCraftingState() {
      this.craftingStepExecutor.reset();
      this.currentCategoryIndex = 0;
      this.completed = false;
      this.openTimer.reset();
   }

   @Override
   public boolean pickupFromSlot(GuiCrafting guiCrafting, int n) {
      return n < 0 ? false : this.performPickupClick(guiCrafting, n);
   }

   @Override
   public boolean placeOneInSlot(GuiCrafting guiCrafting, int n) {
      return this.performPlaceClick(guiCrafting, n);
   }

   @Override
   public boolean takeResultSlot(GuiCrafting guiCrafting, int n) {
      return this.performShiftClick(guiCrafting, n);
   }

   @Override
   public int findInventorySlot(GuiCrafting guiCrafting, Item item, int n) {

      for (int i = 10; i < guiCrafting.inventorySlots.inventorySlots.size(); i++) {
         ItemStack stack = ((Slot)guiCrafting.inventorySlots.inventorySlots.get(i)).getStack();
         if (item == null) {
            if (stack == null) {
               return i;
            }
         } else if (stack != null && stack.getItem().equals(item) && stack.stackSize >= n) {
            return i;
         }
      }

      return -1;
   }

   @Override
   public boolean hasHeldCursorStack() {
      return mc.thePlayer.inventory.getItemStack() != null;
   }

   private boolean performPickupClick(GuiCrafting guiCrafting, int n) {
      Slot slot = (Slot)guiCrafting.inventorySlots.inventorySlots.get(n);
      if (!this.isSmartDelayElapsed(guiCrafting, slot)) {
         return false;
      } else {
         guiCrafting.centerPos = this.getSlotCenterPosition(guiCrafting, slot);
         switch (this.clickMode.getSelectedValue()) {
            case "Basic":
               mc.playerController.windowClick(guiCrafting.inventorySlots.windowId, n, 0, 0, mc.thePlayer);
               break;
            case "Other":
               break;
            case "Exploit":
         }

         this.scheduleNextClickDelay();
         return true;
      }
   }

   private boolean performPlaceClick(GuiCrafting guiCrafting, int n) {
      if (!this.hasHeldCursorStack()) {
         return false;
      } else {
         int stackSize = mc.thePlayer.inventory.getItemStack().stackSize;
         Slot slot = (Slot)guiCrafting.inventorySlots.inventorySlots.get(n);
         if (!this.isSmartDelayElapsed(guiCrafting, slot)) {
            return false;
         } else {
            guiCrafting.centerPos = this.getSlotCenterPosition(guiCrafting, slot);
            switch (this.clickMode.getSelectedValue()) {
               case "Basic":
                  mc.playerController.windowClick(guiCrafting.inventorySlots.windowId, n, stackSize == 1 ? 0 : 1, 0, mc.thePlayer);
                  break;
               case "Other":
                  break;
               case "Exploit":
            }

            this.scheduleNextClickDelay();
            return true;
         }
      }
   }

   private boolean performShiftClick(GuiCrafting guiCrafting, int n) {
      Slot slot = guiCrafting.inventorySlots.getSlot(n);
      if (!this.isSmartDelayElapsed(guiCrafting, slot)) {
         return false;
      } else {
         guiCrafting.centerPos = this.getSlotCenterPosition(guiCrafting, slot);
         switch (this.clickMode.getSelectedValue()) {
            case "Exploit":
               break;
            case "Basic":
            case "Other":
               mc.playerController.windowClick(guiCrafting.inventorySlots.windowId, n, 0, 1, mc.thePlayer);
         }

         this.scheduleNextClickDelay();
         return true;
      }
   }

   private boolean isSmartDelayElapsed(GuiCrafting guiCrafting, Slot slot) {
      if (!this.smartDelay.getValue()) {
         return true;
      } else {
         double d = Math.abs((double)(slot.xDisplayPosition + 2 + guiCrafting.guiLeft) - guiCrafting.centerPos[0]);
         double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + guiCrafting.guiTop) - guiCrafting.centerPos[1]);
         double d3 = Math.sqrt(d * d + d2 * d2);
         if (d3 > 36.0) {
            long l = (long)(((this.delay.getMinValue() + this.delay.getMaxValue()) / 2.0) * (d3 / 30.0));
            return this.clickTimer.reached(l);
         } else {
            return true;
         }
      }
   }

   private double[] getSlotCenterPosition(GuiCrafting guiCrafting, Slot slot) {
      return new double[]{(double)(slot.xDisplayPosition + 2 + guiCrafting.guiLeft), (double)(slot.yDisplayPosition + 2 + guiCrafting.guiTop)};
   }

   private void scheduleNextClickDelay() {
      this.clickTimer.reset();
      this.nextClickDelayMs = DefaultRandomGenerator.instance.nextInt(this.delay.getMinValue(), this.delay.getMaxValue());
   }

}
