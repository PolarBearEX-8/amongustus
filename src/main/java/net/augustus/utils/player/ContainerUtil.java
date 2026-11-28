package net.augustus.utils.player;

import net.minecraft.util.IChatComponent;
import net.augustus.Augustus;
import net.augustus.settings.AbstractListSetting;
import net.augustus.settings.StringListSetting;
import com.google.gson.JsonObject;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChatComponentTranslation;

public class ContainerUtil implements MC {

   public static boolean isValidChest(GuiChest guiChest, String str, boolean flag, StringListSetting stringListSetting) {
      if (str.equals("Always")) {
         return true;
      } else {
         String name = guiChest.lowerChestInventory.getName();
         boolean flag2 = false;
         if (guiChest.lowerChestInventory instanceof InventoryBasic) {
            InventoryBasic lowerChestInventory = (InventoryBasic)guiChest.lowerChestInventory;

            try {
               String asString = IChatComponent.Serializer.componentToJsonObject(lowerChestInventory.getDisplayNameComponent()).get("translate").getAsString();
               flag2 = asString.equals("container.enderchest") && !flag || asString.equals("container.chestDouble") || asString.equals("container.chest");
            } catch (Exception e) {
            }
         }

         return name.equals(new ChatComponentTranslation("container.enderchest", new Object[0]).getUnformattedText()) && !flag
            || name.equals(new ChatComponentTranslation("container.chestDouble", new Object[0]).getUnformattedText())
            || name.equals(new ChatComponentTranslation("container.chest", new Object[0]).getUnformattedText())
            || flag2
            || str.equals("CustomChest") && ((AbstractListSetting)stringListSetting).contains(name);
      }
   }

   public static void shiftClickSlot(Container container, String str, Slot slot, Runnable runnable) {
      prepareSlotClick(container, slot, runnable);
      int windowId = container.windowId;
      int slotNumber = slot.slotNumber;
      switch (str) {
         case "Basic":
            mc.playerController.windowClick(windowId, slotNumber, 0, 1, mc.thePlayer);
            break;
         case "Other":
         case "Exploit":
      }
   }

   public static void dropSlot(Container container, String str, Slot slot) {
      dropSlot(container, str, slot, (Runnable)null);
   }

   public static boolean isChestOpen() {
      return mc.currentScreen instanceof GuiChest;
   }

   public static double getSlotCenterY(Container container, Slot slot) {
      int n = (new ScaledResolution(mc).getScaledHeight() - InventoryUtil.getContainerGuiHeight(container)) / 2;
      return (double)(slot.yDisplayPosition + 8 + n);
   }

   public static void dropSlot(Container container, String str, Slot slot, Runnable runnable) {
      prepareSlotClick(container, slot, runnable);
      int windowId = container.windowId;
      int slotNumber = slot.slotNumber;
      switch (str) {
         case "Basic":
            mc.playerController.windowClick(windowId, slotNumber, 1, 4, mc.thePlayer);
            break;
         case "Other":
            break;
         case "Exploit":
      }
   }

   public static void swapHotbarSlot(Container container, String str, Slot slot, int n) {
      swapHotbarSlot(container, str, slot, n, (Runnable)null);
   }

   public static boolean isInventoryOpen() {
      return mc.currentScreen instanceof GuiInventory;
   }

   private static void prepareSlotClick(Container container, Slot slot, Runnable runnable) {
      if (runnable != null) {
         runnable.run();
      }

      ScaledResolution sr = new ScaledResolution(mc);
      int n = (sr.getScaledWidth() - 176) / 2;
      int n2 = (sr.getScaledHeight() - InventoryUtil.getContainerGuiHeight(container)) / 2;
      InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + n), (double)(slot.yDisplayPosition + 2 + n2));
      InventoryUtil.setLastClickedSlot(slot.slotNumber);
   }

   public static void shiftClickSlot(Container container, String str, Slot slot) {
      shiftClickSlot(container, str, slot, (Runnable)null);
   }

   public static double getSlotCenterX(Slot slot) {
      int n = (new ScaledResolution(mc).getScaledWidth() - 176) / 2;
      return (double)(slot.xDisplayPosition + 8 + n);
   }

   public static boolean isSilentChestOpen() {
      return mc.previousScreen instanceof GuiChest;
   }

   public static void pickupClickSlot(Container container, Slot slot, int n) {
      pickupClickSlot(container, slot, n, (Runnable)null);
   }

   public static void swapHotbarSlot(Container container, String str, Slot slot, int n, Runnable runnable) {
      prepareSlotClick(container, slot, runnable);
      int windowId = container.windowId;
      int slotNumber = slot.slotNumber;
      switch (str) {
         case "Exploit":
            break;
         case "Basic":
         case "Other":
            mc.playerController.windowClick(windowId, slotNumber, n, 2, mc.thePlayer);
      }
   }

   public static boolean isSilentInventoryOpen() {
      return mc.previousScreen instanceof GuiInventory;
   }

   public static void pickupClickSlot(Container container, Slot slot, int n, Runnable runnable) {
      prepareSlotClick(container, slot, runnable);
      int windowId = container.windowId;
      int slotNumber = slot.slotNumber;
      mc.playerController.windowClick(windowId, slotNumber, n, 0, mc.thePlayer);
   }


}
