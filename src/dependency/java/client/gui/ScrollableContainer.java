package client.gui;

import client.setting.ClientSettingsBlackType;
import client.setting.GameSettings;
import client.util.CurveMonitor;
import client.util.EnchantmentManager;
import client.util.FovCounter;
import client.util.InterpolationStack;
import client.util.LvlManager;
import client.util.MouseUtils;
import client.util.SplineBridge;
import client.util.SplineSystem;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ScrollableContainer extends GuiContainerCreative {
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   public static InventoryLargeChest inventoryLargeChest = new InventoryLargeChest("tmp", true, 45);
   public static int count19 = ItemGroupManager.itemGroupManager10.getCount2();
   public float factor4;
   public boolean enabled12;
   public boolean enabled13;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public List list4;
   public Slot slot6;
   public boolean enabled14;
   public FovCounter fovCounter;

   @Override
   public void updateState2() {
      if (this.mc.playerController.processAction2()) {
         super.updateState2();
         this.list.clear();
         Keyboard.enableRepeatEvents(true);
         this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
            0, this.fontRenderer, this.indexOffset1 + 82, this.indexOffset2 + 6, 89, this.fontRenderer.indexOffset
         );
         this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(15);
         this.internalTexturesGuiIconsPngManagerAdapter.setEnabled4(false);
         this.internalTexturesGuiIconsPngManagerAdapter.setEnabled3(false);
         this.internalTexturesGuiIconsPngManagerAdapter.setCount8(16777215);
         int count = count19;
         count19 = -1;
         this.applyItemGroupManager(ItemGroupManager.itemGroupManagerArray[count]);
         this.fovCounter = new FovCounter(this.mc);
         this.mc.thePlayer.container.applyCoreListener(this.fovCounter);
      } else {
         Minecraft mc = this.mc;
         GuiInventory guiInventory = new GuiInventory(this.mc.thePlayer);
         mc.displayGuiScreen(guiInventory);
      }
   }

   @Override
   public void applyItemStack(ItemStack stack, int count, int count2) {
      if (count19 == ItemGroupManager.itemGroupManager6.getCount2()) {
         List list = stack.resolveList(this.mc.thePlayer, this.mc.gameSettings.state136);
         ItemGroupManager itemGroupManager = stack.getItem().getItemGroupManager();
         if (itemGroupManager == null && stack.getItem() == Items.enchantedBook) {
            Map map = LvlManager.resolveMap(stack);
            if (map.size() == 1) {
               EnchantmentManager enchantmentManager = EnchantmentManager.resolveEnchantmentManager((Integer)map.keySet().iterator().next());

               for (ItemGroupManager itemGroupManager2 : ItemGroupManager.itemGroupManagerArray) {
                  if (itemGroupManager2.matchesEnhancedOptionType(enchantmentManager.enhancedOptionType)) {
                     itemGroupManager = itemGroupManager2;
                     break;
                  }
               }
            }
         }

         if (itemGroupManager != null) {
            list.add(1, "" + ClientSettingsBlackType.BOLD + ClientSettingsBlackType.BLUE + StatCollector.getText(itemGroupManager.getText(), new Object[0]));
         }

         for (int index = 0; index < list.size(); index++) {
            if (index == 0) {
               list.set(index, stack.processAction8().rarityColor + (String)list.get(index));
            } else {
               list.set(index, ClientSettingsBlackType.GRAY + (String)list.get(index));
            }
         }

         this.applyList(list, count, count2);
      } else {
         super.applyItemStack(stack, count, count2);
      }
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      if (this.mc.thePlayer != null && this.mc.thePlayer.inventoryPlayer != null) {
         this.mc.thePlayer.container.processAction(this.fovCounter);
      }

      Keyboard.enableRepeatEvents(false);
   }

   private boolean isActive6() {
      return count19 != ItemGroupManager.itemGroupManager.getCount2()
         && ItemGroupManager.itemGroupManagerArray[count19].isEnabled2()
         && ((InterpolationStack)this.container).size();
   }

   public static InventoryLargeChest getInventoryLargeChest() {
      return inventoryLargeChest;
   }

   public boolean matchesItemGroupManager(ItemGroupManager itemGroupManager, int count, int count2) {
      int countAsIndex = itemGroupManager.getCountAsIndex();
      int count3 = 28 * countAsIndex;
      int byteValue = 0;
      if (countAsIndex == 5) {
         count3 = this.count9 - 28 + 2;
      } else if (countAsIndex > 0) {
         count3 += countAsIndex;
      }

      int count4;
      if (itemGroupManager.isActive()) {
         count4 = byteValue - 32;
      } else {
         count4 = byteValue + this.count10;
      }

      return count >= count3 && count <= count3 + 28 && count2 >= count4 && count2 <= count4 + 32;
   }

   private void applyItemGroupManager(ItemGroupManager itemGroupManager) {
      int count = count19;
      count19 = itemGroupManager.getCount2();
      InterpolationStack container = (InterpolationStack)this.container;
      this.set3.clear();
      container.list.clear();
      itemGroupManager.applyList(container.list);
      if (itemGroupManager == ItemGroupManager.itemGroupManager) {
         Container container2 = this.mc.thePlayer.container;
         if (this.list4 == null) {
            this.list4 = container.itemList2;
         }

         container.itemList2 = Lists.newArrayList();

         for (int index = 0; index < container2.itemList2.size(); index++) {
            CurveMonitor curveMonitor = new CurveMonitor(this, (Slot)container2.itemList2.get(index), index);
            container.itemList2.add(curveMonitor);
            if (index >= 5 && index < 9) {
               int count2 = index - 5;
               int count3 = count2 / 2;
               int count4 = count2 % 2;
               curveMonitor.indexOffset = 9 + count3 * 54;
               curveMonitor.indexOffset2 = 6 + count4 * 27;
            } else if (index >= 0 && index < 5) {
               curveMonitor.indexOffset2 = -2000;
               curveMonitor.indexOffset = -2000;
            } else if (index < container2.itemList2.size()) {
               int count5 = index - 9;
               int count6 = count5 % 9;
               int count7 = count5 / 9;
               curveMonitor.indexOffset = 9 + count6 * 18;
               if (index >= 36) {
                  curveMonitor.indexOffset2 = 112;
               } else {
                  curveMonitor.indexOffset2 = 54 + count7 * 18;
               }
            }
         }

         this.slot6 = new Slot(inventoryLargeChest, 0, 173, 112);
         container.itemList2.add(this.slot6);
      } else if (count == ItemGroupManager.itemGroupManager.getCount2()) {
         container.itemList2 = this.list4;
         this.list4 = null;
      }

      if (this.internalTexturesGuiIconsPngManagerAdapter != null) {
         if (itemGroupManager == ItemGroupManager.itemGroupManager6) {
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabled3(true);
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabled(false);
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
            this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
            this.renderElement7();
         } else {
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabled3(false);
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabled(true);
            this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(false);
         }
      }

      this.factor4 = 0.0F;
      container.applyFloat(0.0F);
   }

   public void applyItemGroupManagerByItemGroupManager(ItemGroupManager itemGroupManager) {
      boolean flag = itemGroupManager.getCount2() == count19;
      boolean active = itemGroupManager.isActive();
      int countAsIndex = itemGroupManager.getCountAsIndex();
      int count = countAsIndex * 28;
      int byteValue = 0;
      int count2 = this.indexOffset1 + 28 * countAsIndex;
      int count3 = this.indexOffset2;
      if (flag) {
         byteValue += 32;
      }

      if (countAsIndex == 5) {
         count2 = this.indexOffset1 + this.count9 - 28;
      } else if (countAsIndex > 0) {
         count2 += countAsIndex;
      }

      int count4;
      if (active) {
         count4 = count3 - 28;
      } else {
         byteValue += 64;
         count4 = count3 + (this.count10 - 4);
      }

      GlStateManager.applyState3();
      this.processAction(count2, count4, count, byteValue, 28, 32);
      this.factor = 100.0F;
      this.renderItem.factor = 100.0F;
      int count5 = count2 + 6;
      int count6 = count4 + 8 + (active ? 1 : -1);
      GlStateManager.applyState13();
      GlStateManager.disableLighting();
      ItemStack stack = itemGroupManager.getItemStack();
      this.renderItem.renderItemIntoGUI(stack, count5, count6);
      this.renderItem.renderItemOverlays(this.fontRenderer, stack, count5, count6);
      GlStateManager.applyState3();
      this.renderItem.factor = 0.0F;
      this.factor = 0.0F;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         Minecraft mc = this.mc;
         SplineSystem splineSystem = new SplineSystem(this, this.mc.thePlayer.getStatFileWriter());
         mc.displayGuiScreen(splineSystem);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         Minecraft mc2 = this.mc;
         SplineBridge splineBridge = new SplineBridge(this, this.mc.thePlayer.getStatFileWriter());
         mc2.displayGuiScreen(splineBridge);
      }
   }

   public int getCount19() {
      return count19;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count19 != ItemGroupManager.itemGroupManager6.getCount2()) {
         if (GameSettings.matchesKeyBinding(this.mc.gameSettings.keyBindChat)) {
            this.applyItemGroupManager(ItemGroupManager.itemGroupManager6);
         } else {
            super.applyChar(character, count);
         }
      } else {
         if (this.enabled14) {
            this.enabled14 = false;
            this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
         }

         if (!this.checkIndex4(count)) {
            if (this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count)) {
               this.renderElement7();
            } else {
               super.applyChar(character, count);
            }
         }
      }
   }

   @Override
   public void updateState4() {
      if (!this.mc.playerController.processAction2()) {
         Minecraft mc = this.mc;
         GuiInventory guiInventory = new GuiInventory(this.mc.thePlayer);
         mc.displayGuiScreen(guiInventory);
      }

      this.renderElement5();
   }

   @Override
   public void applyIndex(int count, int count2) {
      ItemGroupManager itemGroupManager = ItemGroupManager.itemGroupManagerArray[count19];
      if (itemGroupManager.isEnabled()) {
         GlStateManager.enableBlend();
         this.fontRenderer.getIndex(StatCollector.getText(itemGroupManager.getText(), new Object[0]), 8, 6, 4210752);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (count3 == 0) {
         int count4 = count - this.indexOffset1;
         int count5 = count2 - this.indexOffset2;

         for (ItemGroupManager itemGroupManager : ItemGroupManager.itemGroupManagerArray) {
            if (this.matchesItemGroupManager(itemGroupManager, count4, count5)) {
               this.applyItemGroupManager(itemGroupManager);
               return;
            }
         }
      }

      super.applyIndex(count, count2, count3);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      int eventDWheel = Mouse.getEventDWheel();
      if (eventDWheel != 0 && this.isActive6()) {
         int count = ((InterpolationStack)this.container).list.size() / 9 - 5;
         if (eventDWheel > 0) {
            eventDWheel = 1;
         }

         if (eventDWheel < 0) {
            eventDWheel = -1;
         }

         this.factor4 = (float)((double)this.factor4 - (double)eventDWheel / (double)count);
         this.factor4 = MathHelper.clamp_float(this.factor4, 0.0F, 1.0F);
         ((InterpolationStack)this.container).applyFloat(this.factor4);
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      boolean flag = MouseUtils.checkCondition(0);
      int count3 = this.indexOffset1;
      int count4 = this.indexOffset2;
      int count5 = count3 + 175;
      int count6 = count4 + 18;
      int count7 = count5 + 14;
      int count8 = count6 + 112;
      if (!this.enabled13 && flag && count >= count5 && count2 >= count6 && count < count7 && count2 < count8) {
         this.enabled12 = this.isActive6();
      }

      if (!flag) {
         this.enabled12 = false;
      }

      this.enabled13 = flag;
      if (this.enabled12) {
         this.factor4 = ((float)(count2 - count6) - 7.5F) / ((float)(count8 - count6) - 15.0F);
         this.factor4 = MathHelper.clamp_float(this.factor4, 0.0F, 1.0F);
         ((InterpolationStack)this.container).applyFloat(this.factor4);
      }

      super.applyIndex(count, count2, factor);

      for (ItemGroupManager itemGroupManager : ItemGroupManager.itemGroupManagerArray) {
         if (this.matchesItemGroupManagerByItemGroupManager(itemGroupManager, count, count2)) {
            break;
         }
      }

      if (this.slot6 != null
         && count19 == ItemGroupManager.itemGroupManager.getCount2()
         && this.checkIndex(this.slot6.indexOffset, this.slot6.indexOffset2, 16, 16, count, count2)) {
         this.applyText(StatCollector.getText("inventory.binSlot", new Object[0]), count, count2);
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.applyState3();
   }

   @Override
   public void applySlot(Slot slot, int count, int count2, int count3) {
      this.enabled14 = true;
      boolean flag = count3 == 1;
      int count4 = count == -999 && count3 == 0 ? 4 : count3;
      if (slot == null && count19 != ItemGroupManager.itemGroupManager.getCount2() && count4 != 5) {
         InventoryPlayer inventoryPlayer = this.mc.thePlayer.inventoryPlayer;
         if (inventoryPlayer.getItemStack() != null) {
            if (count2 == 0) {
               this.mc.thePlayer.resolveEntityItem(inventoryPlayer.getItemStack(), true);
               this.mc.playerController.applyItemStack(inventoryPlayer.getItemStack());
               inventoryPlayer.setItemStack((ItemStack)null);
            }

            if (count2 == 1) {
               ItemStack stack = inventoryPlayer.getItemStack().resolveItemStack(1);
               this.mc.thePlayer.resolveEntityItem(stack, true);
               this.mc.playerController.applyItemStack(stack);
               if (inventoryPlayer.getItemStack().animationsToGo == 0) {
                  inventoryPlayer.setItemStack((ItemStack)null);
               }
            }
         }
      } else if (slot == this.slot6 && flag) {
         for (int index = 0; index < this.mc.thePlayer.container.getList().size(); index++) {
            this.mc.playerController.applyItemStack((ItemStack)null, index);
         }
      } else if (count19 == ItemGroupManager.itemGroupManager.getCount2()) {
         if (slot == this.slot6) {
            this.mc.thePlayer.inventoryPlayer.setItemStack((ItemStack)null);
         } else if (count4 == 4 && slot != null && slot.isActive()) {
            ItemStack stack2 = slot.decrStackSize(count2 == 0 ? 1 : slot.getStack().processAction5());
            this.mc.thePlayer.resolveEntityItem(stack2, true);
            this.mc.playerController.applyItemStack(stack2);
         } else if (count4 == 4 && this.mc.thePlayer.inventoryPlayer.getItemStack() != null) {
            this.mc.thePlayer.resolveEntityItem(this.mc.thePlayer.inventoryPlayer.getItemStack(), true);
            this.mc.playerController.applyItemStack(this.mc.thePlayer.inventoryPlayer.getItemStack());
            this.mc.thePlayer.inventoryPlayer.setItemStack((ItemStack)null);
         } else {
            this.mc
               .thePlayer
               .container
               .slotClick(slot == null ? count : CurveMonitor.resolveSlot((CurveMonitor)slot).indexOffset1, count2, count4, this.mc.thePlayer);
            this.mc.thePlayer.container.detectAndSendChanges();
         }
      } else if (count4 != 5 && slot.iInventory == inventoryLargeChest) {
         InventoryPlayer inventoryPlayer2 = this.mc.thePlayer.inventoryPlayer;
         ItemStack stack3 = inventoryPlayer2.getItemStack();
         ItemStack stack4 = slot.getStack();
         if (count4 == 2) {
            if (stack4 != null && count2 >= 0 && count2 < 9) {
               ItemStack stack5 = stack4.getItemStack();
               stack5.animationsToGo = stack5.processAction5();
               this.mc.thePlayer.inventoryPlayer.processAction(count2, stack5);
               this.mc.thePlayer.container.detectAndSendChanges();
            }

            return;
         }

         if (count4 == 3) {
            if (inventoryPlayer2.getItemStack() == null && slot.isActive()) {
               ItemStack stack6 = slot.getStack().getItemStack();
               stack6.animationsToGo = stack6.processAction5();
               inventoryPlayer2.setItemStack(stack6);
            }

            return;
         }

         if (count4 == 4) {
            if (stack4 != null) {
               ItemStack stack7 = stack4.getItemStack();
               stack7.animationsToGo = count2 == 0 ? 1 : stack7.processAction5();
               this.mc.thePlayer.resolveEntityItem(stack7, true);
               this.mc.playerController.applyItemStack(stack7);
            }

            return;
         }

         if (stack3 != null && stack4 != null && stack3.matchesItemStack(stack4)) {
            if (count2 == 0) {
               if (flag) {
                  stack3.animationsToGo = stack3.processAction5();
               } else if (stack3.animationsToGo < stack3.processAction5()) {
                  stack3.animationsToGo++;
               }
            } else if (stack3.animationsToGo <= 1) {
               inventoryPlayer2.setItemStack((ItemStack)null);
            } else {
               stack3.animationsToGo--;
            }
         } else if (stack4 != null && stack3 == null) {
            inventoryPlayer2.setItemStack(ItemStack.resolveItemStack(stack4));
            ItemStack stack8 = inventoryPlayer2.getItemStack();
            if (flag) {
               stack8.animationsToGo = stack8.processAction5();
            }
         } else {
            inventoryPlayer2.setItemStack((ItemStack)null);
         }
      } else {
         this.container.slotClick(slot == null ? count : slot.indexOffset1, count2, count4, this.mc.thePlayer);
         if (Container.processAction3(count2) == 2) {
            for (int index2 = 0; index2 < 9; index2++) {
               this.mc.playerController.applyItemStack(this.container.getSlot(45 + index2).getStack(), 36 + index2);
            }
         } else if (slot != null) {
            ItemStack stack9 = this.container.getSlot(slot.indexOffset1).getStack();
            this.mc.playerController.applyItemStack(stack9, slot.indexOffset1 - this.container.itemList2.size() + 9 + 36);
         }
      }
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      if (count3 == 0) {
         int count4 = count - this.indexOffset1;
         int count5 = count2 - this.indexOffset2;

         for (ItemGroupManager itemGroupManager : ItemGroupManager.itemGroupManagerArray) {
            if (this.matchesItemGroupManager(itemGroupManager, count4, count5)) {
               return;
            }
         }
      }

      super.processAction(count, count2, count3);
   }

   @Override
   public void renderElement5() {
      int count = this.indexOffset1;
      super.renderElement5();
      if (this.internalTexturesGuiIconsPngManagerAdapter != null && this.indexOffset1 != count) {
         this.internalTexturesGuiIconsPngManagerAdapter.count7 = this.indexOffset1 + 82;
      }
   }

   public boolean matchesItemGroupManagerByItemGroupManager(ItemGroupManager itemGroupManager, int count, int count2) {
      int countAsIndex = itemGroupManager.getCountAsIndex();
      int count3 = 28 * countAsIndex;
      int byteValue = 0;
      if (countAsIndex == 5) {
         count3 = this.count9 - 28 + 2;
      } else if (countAsIndex > 0) {
         count3 += countAsIndex;
      }

      int count4;
      if (itemGroupManager.isActive()) {
         count4 = byteValue - 32;
      } else {
         count4 = byteValue + this.count10;
      }

      if (this.checkIndex(count3 + 3, count4 + 3, 23, 27, count, count2)) {
         this.applyText(StatCollector.getText(itemGroupManager.getText(), new Object[0]), count, count2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      ItemGroupManager itemGroupManager = ItemGroupManager.itemGroupManagerArray[count19];

      for (ItemGroupManager itemGroupManager2 : ItemGroupManager.itemGroupManagerArray) {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation6);
         if (itemGroupManager2.getCount2() != count19) {
            this.applyItemGroupManagerByItemGroupManager(itemGroupManager2);
         }
      }

      this.mc.getTextureManager().applyResourceLocation3(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + itemGroupManager.getLabelText2()));
      this.processAction(this.indexOffset1, this.indexOffset2, 0, 0, this.count9, this.count10);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      int count3 = this.indexOffset1 + 175;
      int count4 = this.indexOffset2 + 18;
      int count5 = count4 + 112;
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation6);
      if (itemGroupManager.isEnabled2()) {
         this.processAction(count3, count4 + (int)((float)(count5 - count4 - 17) * this.factor4), 232 + (this.isActive6() ? 0 : 12), 0, 12, 15);
      }

      this.applyItemGroupManagerByItemGroupManager(itemGroupManager);
      if (itemGroupManager == ItemGroupManager.itemGroupManager) {
         GuiInventory.applyEntityLivingBaseByEntityLivingBase(
            this.indexOffset1 + 43, this.indexOffset2 + 45, 20, (float)(this.indexOffset1 + 43 - count), (float)(this.indexOffset2 + 45 - 30 - count2), this.mc.thePlayer
         );
      }
   }

   private void renderElement7() {
      InterpolationStack container = (InterpolationStack)this.container;
      container.list.clear();

      for (Item item : (Iterable<Item>)(Iterable)(Item.iterableExtensionAdapter)) {
         if (item != null && item.getItemGroupManager() != null) {
            item.processAction(item, (ItemGroupManager)null, container.list);
         }
      }

      for (EnchantmentManager enchantmentManager : EnchantmentManager.enchantmentManagerArray2) {
         if (enchantmentManager != null && enchantmentManager.enhancedOptionType != null) {
            Items.enchantedBook.applyEnchantmentManager(enchantmentManager, container.list);
         }
      }

      Iterator iterator = container.list.iterator();
      String text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().toLowerCase();

      while (iterator.hasNext()) {
         ItemStack stack = (ItemStack)iterator.next();
         boolean flag = false;
         Iterator iterator2 = stack.resolveList(this.mc.thePlayer, this.mc.gameSettings.state136).iterator();

         while (iterator2.hasNext()) {
            if (ClientSettingsBlackType.resolveText((String)iterator2.next()).toLowerCase().contains(text)) {
               flag = true;
               break;
            }
         }

         if (!flag) {
            iterator.remove();
         }
      }

      this.factor4 = 0.0F;
      container.applyFloat(0.0F);
   }

   public ScrollableContainer(EntityPlayer player) {
      super(new InterpolationStack(player));
      player.container2 = this.container;
      this.enabled = true;
      this.count10 = 136;
      this.count9 = 195;
   }
}
