package net.minecraft.client.gui.inventory;

import net.minecraft.inventory.IInventory;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.augustus.utils.input.MouseInputUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiContainerCreative extends InventoryEffectRenderer {
   private static final ResourceLocation creativeInventoryTabs = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static InventoryBasic basicInventory = new InventoryBasic("tmp", true, 45);
   private static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
   private float currentScroll;
   private boolean isScrolling;
   private boolean wasClicking;
   private GuiTextField searchField;
   private List originalSlots;
   private Slot destroyItemSlot;
   private boolean clearSearch;
   private CreativeCrafting listener;

   public void drawTab(CreativeTabs creativeTabs) {
      boolean flag = creativeTabs.getTabIndex() == selectedTabIndex;
      boolean isTabInFirstRow = creativeTabs.isTabInFirstRow();
      int tabColumn = creativeTabs.getTabColumn();
      int n = tabColumn * 28;
      int n2 = 0;
      int n3 = this.guiLeft + 28 * tabColumn;
      int guiTop2 = this.guiTop;
      if (flag) {
         n2 += 32;
      }

      if (tabColumn == 5) {
         n3 = this.guiLeft + this.xSize - 28;
      } else if (tabColumn > 0) {
         n3 += tabColumn;
      }

      if (isTabInFirstRow) {
         guiTop2 -= 28;
      } else {
         n2 += 64;
         guiTop2 += this.ySize - 4;
      }

      GlStateManager.disableLighting();
      this.drawTexturedModalRect(n3, guiTop2, n, n2, 28, (byte)32);
      this.zLevel = 100.0F;
      this.itemRender.zLevel = 100.0F;
      n3 += 6;
      guiTop2 = guiTop2 + 8 + (isTabInFirstRow ? 1 : -1);
      GlStateManager.enableLighting();
      GlStateManager.enableRescaleNormal();
      ItemStack iconItemStack = creativeTabs.getIconItemStack();
      this.itemRender.renderItemAndEffectIntoGUI(iconItemStack, n3, guiTop2);
      this.itemRender.renderItemOverlays(this.fontRendererObj, iconItemStack, n3, guiTop2);
      GlStateManager.disableLighting();
      this.itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   public static InventoryBasic accessBasicInventory() {
      return basicInventory;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex()) {
         if (GameSettings.isKeyDown(this.mc.gameSettings.keyBindChat)) {
            this.setCurrentCreativeTab(CreativeTabs.tabAllSearch);
         } else {
            super.keyTyped(typedChar, keyCode);
         }
      } else {
         if (this.clearSearch) {
            this.clearSearch = false;
            this.searchField.setText("");
         }

         if (!this.checkHotbarKeys(keyCode)) {
            if (this.searchField.textboxKeyTyped(typedChar, keyCode)) {
               this.updateCreativeSearch();
            } else {
               super.keyTyped(typedChar, keyCode);
            }
         }
      }
   }

   private void setCurrentCreativeTab(CreativeTabs creativeTabs) {
      int n = selectedTabIndex;
      selectedTabIndex = creativeTabs.getTabIndex();
      GuiContainerCreative.ContainerCreative inventorySlots2 = (GuiContainerCreative.ContainerCreative)this.inventorySlots;
      this.dragSplittingSlots.clear();
      inventorySlots2.itemList.clear();
      creativeTabs.displayAllReleventItems(inventorySlots2.itemList);
      if (creativeTabs == CreativeTabs.tabInventory) {
         Container inventoryContainer = this.mc.thePlayer.inventoryContainer;
         if (this.originalSlots == null) {
            this.originalSlots = inventorySlots2.inventorySlots;
         }

         inventorySlots2.inventorySlots = Lists.newArrayList();

         for (int i = 0; i < inventoryContainer.inventorySlots.size(); i++) {
            GuiContainerCreative.CreativeSlot creativeSlot = new GuiContainerCreative.CreativeSlot(this, (Slot)inventoryContainer.inventorySlots.get(i), i);
            inventorySlots2.inventorySlots.add(creativeSlot);
            if (i >= 5 && i < 9) {
               int n2 = i - 5;
               int n3 = n2 / 2;
               int n4 = n2 % 2;
               creativeSlot.xDisplayPosition = 9 + n3 * 54;
               creativeSlot.yDisplayPosition = 6 + n4 * 27;
            } else if (i >= 0 && i < 5) {
               creativeSlot.yDisplayPosition = -2000;
               creativeSlot.xDisplayPosition = -2000;
            } else if (i < inventoryContainer.inventorySlots.size()) {
               int n5 = i - 9;
               int n6 = n5 % 9;
               int n7 = n5 / 9;
               creativeSlot.xDisplayPosition = 9 + n6 * 18;
               if (i >= 36) {
                  creativeSlot.yDisplayPosition = 112;
               } else {
                  creativeSlot.yDisplayPosition = 54 + n7 * 18;
               }
            }
         }

         this.destroyItemSlot = new Slot(basicInventory, 0, 173, 112);
         inventorySlots2.inventorySlots.add(this.destroyItemSlot);
      } else if (n == CreativeTabs.tabInventory.getTabIndex()) {
         inventorySlots2.inventorySlots = this.originalSlots;
         this.originalSlots = null;
      }

      if (this.searchField != null) {
         if (creativeTabs == CreativeTabs.tabAllSearch) {
            this.searchField.setVisible(true);
            this.searchField.setCanLoseFocus(false);
            this.searchField.setFocused(true);
            this.searchField.setText("");
            this.updateCreativeSearch();
         } else {
            this.searchField.setVisible(false);
            this.searchField.setCanLoseFocus(true);
            this.searchField.setFocused(false);
         }
      }

      this.currentScroll = 0.0F;
      inventorySlots2.scrollTo(0.0F);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      CreativeTabs creativeTabs = CreativeTabs.creativeTabArray[selectedTabIndex];

      for (CreativeTabs creativeTabs2 : CreativeTabs.creativeTabArray) {
         this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
         if (creativeTabs2.getTabIndex() != selectedTabIndex) {
            this.drawTab(creativeTabs2);
         }
      }

      this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativeTabs.getBackgroundImageName()));
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      this.searchField.drawTextBox();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      int n3 = this.guiLeft + 175;
      int n4 = this.guiTop + 18;
      int n5 = n4 + 112;
      this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
      if (creativeTabs.shouldHidePlayerInventory()) {
         this.drawTexturedModalRect(n3, n4 + (int)((float)(n5 - n4 - 17) * this.currentScroll), 232 + (this.needsScrollBars() ? 0 : 12), 0, 12, 15);
      }

      this.drawTab(creativeTabs);
      if (creativeTabs == CreativeTabs.tabInventory) {
         GuiInventory.drawEntityOnScreen(
            this.guiLeft + 43, this.guiTop + 45, 20, (float)(this.guiLeft + 43 - n), (float)(this.guiTop + 45 - 30 - n2), this.mc.thePlayer
         );
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         int n = mouseX - this.guiLeft;
         int n2 = mouseY - this.guiTop;

         for (CreativeTabs creativeTabs : CreativeTabs.creativeTabArray) {
            if (this.isMouseOverTab(creativeTabs, n, n2)) {
               return;
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public boolean isMouseOverTab(CreativeTabs creativeTabs, int n, int n2) {
      int tabColumn = creativeTabs.getTabColumn();
      int n3 = 28 * tabColumn;
      int n4 = 0;
      if (tabColumn == 5) {
         n3 = this.xSize - 28 + 2;
      } else if (tabColumn > 0) {
         n3 += tabColumn;
      }

      if (creativeTabs.isTabInFirstRow()) {
         n4 -= 32;
      } else {
         n4 += this.ySize;
      }

      return n >= n3 && n <= n3 + 28 && n2 >= n4 && n2 <= n4 + 32;
   }

   @Override
   public void renderToolTip(ItemStack stack, int n, int n2) {
      if (selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex()) {
         List tooltip = stack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
         CreativeTabs creativeTab = stack.getItem().getCreativeTab();
         if (creativeTab == null && stack.getItem() == Items.enchanted_book) {
            Map enchantments = EnchantmentHelper.getEnchantments(stack);
            if (enchantments.size() == 1) {
               Enchantment enchantmentById = Enchantment.getEnchantmentById((Integer)enchantments.keySet().iterator().next());

               for (CreativeTabs creativeTabs : CreativeTabs.creativeTabArray) {
                  if (creativeTabs.hasRelevantEnchantmentType(enchantmentById.type)) {
                     creativeTab = creativeTabs;
                     break;
                  }
               }
            }
         }

         if (creativeTab != null) {
            tooltip.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(creativeTab.getTranslatedTabLabel(), new Object[0]));
         }

         for (int i = 0; i < tooltip.size(); i++) {
            if (i == 0) {
               tooltip.set(i, stack.getRarity().rarityColor + (String)tooltip.get(i));
            } else {
               tooltip.set(i, EnumChatFormatting.GRAY + (String)tooltip.get(i));
            }
         }

         this.drawHoveringText(tooltip, n, n2);
      } else {
         super.renderToolTip(stack, n, n2);
      }
   }

   public GuiContainerCreative(EntityPlayer player) {
      super(new GuiContainerCreative.ContainerCreative(player));
      player.openContainer = this.inventorySlots;
      this.allowUserInput = true;
      this.ySize = 136;
      this.xSize = 195;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      int eventDWheel = Mouse.getEventDWheel();
      if (eventDWheel != 0 && this.needsScrollBars()) {
         int n = ((GuiContainerCreative.ContainerCreative)this.inventorySlots).itemList.size() / 9 - 5;
         if (eventDWheel > 0) {
            eventDWheel = 1;
         }

         if (eventDWheel < 0) {
            eventDWheel = -1;
         }

         this.currentScroll = (float)((double)this.currentScroll - (double)eventDWheel / (double)n);
         this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
      }
   }

   @Override
   public void initGui() {
      if (this.mc.playerController.isInCreativeMode()) {
         super.initGui();
         this.buttonList.clear();
         Keyboard.enableRepeatEvents(true);
         this.searchField = new GuiTextField(0, this.fontRendererObj, this.guiLeft + 82, this.guiTop + 6, 89, this.fontRendererObj.FONT_HEIGHT);
         this.searchField.setMaxStringLength(15);
         this.searchField.setEnableBackgroundDrawing(false);
         this.searchField.setVisible(false);
         this.searchField.setTextColor(16777215);
         int n = selectedTabIndex;
         selectedTabIndex = -1;
         this.setCurrentCreativeTab(CreativeTabs.creativeTabArray[n]);
         this.listener = new CreativeCrafting(this.mc);
         this.mc.thePlayer.inventoryContainer.onCraftGuiOpened(this.listener);
      } else {
         this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
      }
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (state == 0) {
         int n = mouseX - this.guiLeft;
         int n2 = mouseY - this.guiTop;

         for (CreativeTabs creativeTabs : CreativeTabs.creativeTabArray) {
            if (this.isMouseOverTab(creativeTabs, n, n2)) {
               this.setCurrentCreativeTab(creativeTabs);
               return;
            }
         }
      }

      super.mouseReleased(mouseX, mouseY, state);
   }

   public int getSelectedTabIndex() {
      return selectedTabIndex;
   }

   private void updateCreativeSearch() {
      GuiContainerCreative.ContainerCreative inventorySlots2 = (GuiContainerCreative.ContainerCreative)this.inventorySlots;
      inventorySlots2.itemList.clear();

      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         if (item != null && item.getCreativeTab() != null) {
            item.getSubItems(item, (CreativeTabs)null, inventorySlots2.itemList);
         }
      }

      for (Enchantment enchantment : Enchantment.enchantmentsBookList) {
         if (enchantment != null && enchantment.type != null) {
            Items.enchanted_book.getAll(enchantment, inventorySlots2.itemList);
         }
      }

      Iterator it = inventorySlots2.itemList.iterator();
      String str = this.searchField.getText().toLowerCase();

      while (it.hasNext()) {
         ItemStack stack = (ItemStack)it.next();
         boolean flag = false;

         for (String str2 : (Iterable<String>)(stack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips))) {
            if (EnumChatFormatting.getTextWithoutFormattingCodes(str2).toLowerCase().contains(str)) {
               flag = true;
               break;
            }
         }

         if (!flag) {
            it.remove();
         }
      }

      this.currentScroll = 0.0F;
      inventorySlots2.scrollTo(0.0F);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      boolean isButtonDown = MouseInputUtil.isButtonDown(0);
      int guiLeft2 = this.guiLeft;
      int guiTop2 = this.guiTop;
      int n = guiLeft2 + 175;
      int n2 = guiTop2 + 18;
      int n3 = n + 14;
      int n4 = n2 + 112;
      if (!this.wasClicking && isButtonDown && mouseX >= n && mouseY >= n2 && mouseX < n3 && mouseY < n4) {
         this.isScrolling = this.needsScrollBars();
      }

      if (!isButtonDown) {
         this.isScrolling = false;
      }

      this.wasClicking = isButtonDown;
      if (this.isScrolling) {
         this.currentScroll = ((float)(mouseY - n2) - 7.5F) / ((float)(n4 - n2) - 15.0F);
         this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);

      for (CreativeTabs creativeTabs : CreativeTabs.creativeTabArray) {
         if (this.renderCreativeInventoryHoveringText(creativeTabs, mouseX, mouseY)) {
            break;
         }
      }

      if (this.destroyItemSlot != null
         && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()
         && this.isPointInRegion(this.destroyItemSlot.xDisplayPosition, this.destroyItemSlot.yDisplayPosition, 16, 16, mouseX, mouseY)) {
         this.drawCreativeTabHoveringText(I18n.format("inventory.binSlot", new Object[0]), mouseX, mouseY);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
      }

      if (button.id == 1) {
         this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
      }
   }

   public boolean renderCreativeInventoryHoveringText(CreativeTabs creativeTabs, int n, int n2) {
      int tabColumn = creativeTabs.getTabColumn();
      int n3 = 28 * tabColumn;
      int n4 = 0;
      if (tabColumn == 5) {
         n3 = this.xSize - 28 + 2;
      } else if (tabColumn > 0) {
         n3 += tabColumn;
      }

      if (creativeTabs.isTabInFirstRow()) {
         n4 -= 32;
      } else {
         n4 += this.ySize;
      }

      if (this.isPointInRegion(n3 + 3, n4 + 3, 23, 27, n, n2)) {
         this.drawCreativeTabHoveringText(I18n.format(creativeTabs.getTranslatedTabLabel(), new Object[0]), n, n2);
         return true;
      } else {
         return false;
      }
   }

   private boolean needsScrollBars() {
      return selectedTabIndex != CreativeTabs.tabInventory.getTabIndex()
         && CreativeTabs.creativeTabArray[selectedTabIndex].shouldHidePlayerInventory()
         && ((GuiContainerCreative.ContainerCreative)this.inventorySlots).canScroll();
   }

   @Override
   public void handleMouseClick(Slot slot, int n, int n2, int n3) {
      this.clearSearch = true;
      boolean flag = n3 == 1;
      n3 = n == -999 && n3 == 0 ? 4 : n3;
      if (slot == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && n3 != 5) {
         InventoryPlayer inventory = this.mc.thePlayer.inventory;
         if (inventory.getItemStack() != null) {
            if (n2 == 0) {
               this.mc.thePlayer.dropPlayerItemWithRandomChoice(inventory.getItemStack(), true);
               this.mc.playerController.sendPacketDropItem(inventory.getItemStack());
               inventory.setItemStack((ItemStack)null);
            }

            if (n2 == 1) {
               ItemStack stack = inventory.getItemStack().splitStack(1);
               this.mc.thePlayer.dropPlayerItemWithRandomChoice(stack, true);
               this.mc.playerController.sendPacketDropItem(stack);
               if (inventory.getItemStack().stackSize == 0) {
                  inventory.setItemStack((ItemStack)null);
               }
            }
         }
      } else if (slot == this.destroyItemSlot && flag) {
         for (int i = 0; i < this.mc.thePlayer.inventoryContainer.getInventory().size(); i++) {
            this.mc.playerController.sendSlotPacket((ItemStack)null, i);
         }
      } else if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
         if (slot == this.destroyItemSlot) {
            this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
         } else if (n3 == 4 && slot != null && slot.getHasStack()) {
            ItemStack stack2 = slot.decrStackSize(n2 == 0 ? 1 : slot.getStack().getMaxStackSize());
            this.mc.thePlayer.dropPlayerItemWithRandomChoice(stack2, true);
            this.mc.playerController.sendPacketDropItem(stack2);
         } else if (n3 == 4 && this.mc.thePlayer.inventory.getItemStack() != null) {
            this.mc.thePlayer.dropPlayerItemWithRandomChoice(this.mc.thePlayer.inventory.getItemStack(), true);
            this.mc.playerController.sendPacketDropItem(this.mc.thePlayer.inventory.getItemStack());
            this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
         } else {
            this.mc
               .thePlayer
               .inventoryContainer
               .slotClick(
                  slot == null ? n : GuiContainerCreative.CreativeSlot.accessSlot((GuiContainerCreative.CreativeSlot)slot).slotNumber,
                  n2,
                  n3,
                  this.mc.thePlayer
               );
            this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
         }
      } else if (n3 != 5 && slot.inventory == basicInventory) {
         InventoryPlayer inventory2 = this.mc.thePlayer.inventory;
         ItemStack itemStack = inventory2.getItemStack();
         ItemStack stack3 = slot.getStack();
         if (n3 == 2) {
            if (stack3 != null && n2 >= 0 && n2 < 9) {
               ItemStack stack4 = stack3.copy();
               stack4.stackSize = stack4.getMaxStackSize();
               this.mc.thePlayer.inventory.setInventorySlotContents(n2, stack4);
               this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
            }

            return;
         }

         if (n3 == 3) {
            if (inventory2.getItemStack() == null && slot.getHasStack()) {
               ItemStack stack5 = slot.getStack().copy();
               stack5.stackSize = stack5.getMaxStackSize();
               inventory2.setItemStack(stack5);
            }

            return;
         }

         if (n3 == 4) {
            if (stack3 != null) {
               ItemStack stack6 = stack3.copy();
               stack6.stackSize = n2 == 0 ? 1 : stack6.getMaxStackSize();
               this.mc.thePlayer.dropPlayerItemWithRandomChoice(stack6, true);
               this.mc.playerController.sendPacketDropItem(stack6);
            }

            return;
         }

         if (itemStack != null && stack3 != null && itemStack.isItemEqual(stack3)) {
            if (n2 == 0) {
               if (flag) {
                  itemStack.stackSize = itemStack.getMaxStackSize();
               } else if (itemStack.stackSize < itemStack.getMaxStackSize()) {
                  itemStack.stackSize++;
               }
            } else if (itemStack.stackSize <= 1) {
               inventory2.setItemStack((ItemStack)null);
            } else {
               itemStack.stackSize--;
            }
         } else if (stack3 != null && itemStack == null) {
            inventory2.setItemStack(ItemStack.copyItemStack(stack3));
            itemStack = inventory2.getItemStack();
            if (flag) {
               itemStack.stackSize = itemStack.getMaxStackSize();
            }
         } else {
            inventory2.setItemStack((ItemStack)null);
         }
      } else {
         this.inventorySlots.slotClick(slot == null ? n : slot.slotNumber, n2, n3, this.mc.thePlayer);
         if (Container.getDragEvent(n2) == 2) {
            for (int j = 0; j < 9; j++) {
               this.mc.playerController.sendSlotPacket(this.inventorySlots.getSlot(45 + j).getStack(), 36 + j);
            }
         } else if (slot != null) {
            ItemStack stack7 = this.inventorySlots.getSlot(slot.slotNumber).getStack();
            this.mc.playerController.sendSlotPacket(stack7, slot.slotNumber - this.inventorySlots.inventorySlots.size() + 9 + 36);
         }
      }
   }

   @Override
   public void updateScreen() {
      if (!this.mc.playerController.isInCreativeMode()) {
         this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
      }

      this.updateActivePotionEffects();
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.mc.thePlayer != null && this.mc.thePlayer.inventory != null) {
         this.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.listener);
      }

      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      CreativeTabs creativeTabs = CreativeTabs.creativeTabArray[selectedTabIndex];
      if (creativeTabs.drawInForegroundOfTab()) {
         GlStateManager.disableBlend();
         this.fontRendererObj.drawString(I18n.format(creativeTabs.getTranslatedTabLabel(), new Object[0]), 8, 6, 4210752);
      }
   }

   @Override
   public void updateActivePotionEffects() {
      int guiLeft2 = this.guiLeft;
      super.updateActivePotionEffects();
      if (this.searchField != null && this.guiLeft != guiLeft2) {
         this.searchField.xPosition = this.guiLeft + 82;
      }
   }



   public static class CreativeSlot extends Slot {
      private final Slot slot;
      public final GuiContainerCreative creativeScreen;

      @Override
      public void onSlotChanged() {
         this.slot.onSlotChanged();
      }

      @Override
      public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
         this.slot.onPickupFromSlot(player, stack);
      }

      @Override
      public boolean isHere(IInventory inventory2, int n) {
         return this.slot.isHere(inventory2, n);
      }

      @Override
      public String getSlotTexture() {
         return this.slot.getSlotTexture();
      }

      @Override
      public ItemStack decrStackSize(int n) {
         return this.slot.decrStackSize(n);
      }

      @Override
      public int getItemStackLimit(ItemStack stack) {
         return this.slot.getItemStackLimit(stack);
      }

      @Override
      public void putStack(ItemStack stack) {
         this.slot.putStack(stack);
      }

      public CreativeSlot(GuiContainerCreative guiContainerCreative, Slot slot2, int n) {
         super(slot2.inventory, n, 0, 0);
         this.creativeScreen = guiContainerCreative;
         this.slot = slot2;
      }

      @Override
      public boolean getHasStack() {
         return this.slot.getHasStack();
      }

      @Override
      public int getSlotStackLimit() {
         return this.slot.getSlotStackLimit();
      }

      public static Slot accessSlot(CreativeSlot creativeSlot) {
         return creativeSlot.slot;
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return this.slot.isItemValid(stack);
      }

      @Override
      public ItemStack getStack() {
         return this.slot.getStack();
      }
   }

   public static class ContainerCreative extends Container {
      public List itemList = Lists.newArrayList();

      public boolean canScroll() {
         return this.itemList.size() > 45;
      }

      @Override
      public boolean canMergeSlot(ItemStack stack, Slot slot) {
         return slot.yDisplayPosition > 90;
      }

      public void scrollTo(float f) {
         int n2 = (int)((double)(f * (float)((this.itemList.size() + 9 - 1) / 9 - 5)) + 0.5);
         if (n2 < 0) {
            n2 = 0;
         }

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
               int n3 = j + (i + n2) * 9;
               if (n3 >= 0 && n3 < this.itemList.size()) {
                  GuiContainerCreative.accessBasicInventory().setInventorySlotContents(j + i * 9, (ItemStack)this.itemList.get(n3));
               } else {
                  GuiContainerCreative.accessBasicInventory().setInventorySlotContents(j + i * 9, (ItemStack)null);
               }
            }
         }
      }

      @Override
      public void retrySlotClick(int n, int n2, boolean flag, EntityPlayer player) {
      }

      public ContainerCreative(EntityPlayer player) {
         InventoryPlayer inventory = player.inventory;

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
               this.addSlotToContainer(new Slot(GuiContainerCreative.accessBasicInventory(), i * 9 + j, 9 + j * 18, 18 + i * 18));
            }
         }

         for (int k = 0; k < 9; k++) {
            this.addSlotToContainer(new Slot(inventory, k, 9 + k * 18, 112));
         }

         this.scrollTo(0.0F);
      }

      @Override
      public boolean canInteractWith(EntityPlayer player) {
         return true;
      }

      @Override
      public boolean canDragIntoSlot(Slot slot) {
         return slot.inventory instanceof InventoryPlayer || slot.yDisplayPosition > 90 && slot.xDisplayPosition <= 162;
      }

      @Override
      public ItemStack transferStackInSlot(EntityPlayer player, int n) {
         if (n >= this.inventorySlots.size() - 9 && n < this.inventorySlots.size()) {
            Slot slot = (Slot)this.inventorySlots.get(n);
            if (slot != null && slot.getHasStack()) {
               slot.putStack((ItemStack)null);
            }
         }

         return null;
      }
   }
}
