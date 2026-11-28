package net.minecraft.client.gui.inventory;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Set;
import net.augustus.ClientManagers;
import net.augustus.utils.player.InventoryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public abstract class GuiContainer extends GuiScreen {
   public static final ResourceLocation inventoryBackground;
   public int xSize = 176;
   public int ySize = 166;
   public Container inventorySlots;
   public int guiLeft;
   public int guiTop;
   private Slot theSlot;
   private Slot clickedSlot;
   private boolean isRightMouseClick;
   public ItemStack draggedStack;
   private int touchUpX;
   private int touchUpY;
   private Slot returningStackDestSlot;
   private long returningStackTime;
   private ItemStack returningStack;
   private Slot currentDragTargetSlot;
   private long dragItemDropDelay;
   public final Set dragSplittingSlots = Sets.newHashSet();
   public boolean dragSplitting;
   private int dragSplittingLimit;
   private int dragSplittingButton;
   private boolean ignoreMouseUp;
   private int dragSplittingRemnant;
   private long lastClickTime;
   private Slot lastClickSlot;
   private int lastClickButton;
   private boolean doubleClick;
   private ItemStack shiftClickedSlot;
   public boolean forceShiftClick = false;

   private void updateDragSplitting() {
      ItemStack itemStack = this.mc.thePlayer.inventory.getItemStack();
      if (itemStack != null && this.dragSplitting) {
         this.dragSplittingRemnant = itemStack.stackSize;

         for (Slot slot : (Iterable<Slot>)(this.dragSplittingSlots)) {
            ItemStack stack = itemStack.copy();
            int n = slot.getStack() == null ? 0 : slot.getStack().stackSize;
            Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, stack, n);
            if (stack.stackSize > stack.getMaxStackSize()) {
               stack.stackSize = stack.getMaxStackSize();
            }

            if (stack.stackSize > slot.getItemStackLimit(stack)) {
               stack.stackSize = slot.getItemStackLimit(stack);
            }

            this.dragSplittingRemnant = this.dragSplittingRemnant - (stack.stackSize - n);
         }
      }
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.mc.thePlayer.openContainer = this.inventorySlots;
      this.guiLeft = (this.width - this.xSize) / 2;
      this.guiTop = (this.height - this.ySize) / 2;
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      Slot slotAtPosition = this.getSlotAtPosition(mouseX, mouseY);
      ItemStack itemStack = this.mc.thePlayer.inventory.getItemStack();
      if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
         if (clickedMouseButton == 0 || clickedMouseButton == 1) {
            if (this.draggedStack == null) {
               if (slotAtPosition != this.clickedSlot && this.clickedSlot.getStack() != null) {
                  this.draggedStack = this.clickedSlot.getStack().copy();
               }
            } else if (this.draggedStack.stackSize > 1 && slotAtPosition != null && Container.canAddItemToSlot(slotAtPosition, this.draggedStack, false)) {
               long systemTime = Minecraft.getSystemTime();
               if (this.currentDragTargetSlot == slotAtPosition) {
                  if (systemTime - this.dragItemDropDelay > 500L) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.handleMouseClick(slotAtPosition, slotAtPosition.slotNumber, 1, 0);
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.dragItemDropDelay = systemTime + 750L;
                     this.draggedStack.stackSize--;
                  }
               } else {
                  this.currentDragTargetSlot = slotAtPosition;
                  this.dragItemDropDelay = systemTime;
               }
            }
         }
      } else if (this.dragSplitting
         && slotAtPosition != null
         && itemStack != null
         && itemStack.stackSize > this.dragSplittingSlots.size()
         && Container.canAddItemToSlot(slotAtPosition, itemStack, true)
         && slotAtPosition.isItemValid(itemStack)
         && this.inventorySlots.canDragIntoSlot(slotAtPosition)) {
         this.dragSplittingSlots.add(slotAtPosition);
         this.updateDragSplitting();
      }
   }

   public void drawGuiContainerForegroundLayer(int n, int n2) {
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead) {
         this.mc.thePlayer.closeScreen();
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton - 100 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }

      InventoryUtil.setLastCursorPos((double)mouseX, (double)mouseY);
      if (ClientManagers.moduleManager.invManager.isToggled()) {
         ClientManagers.moduleManager.invManager.resetActionTickCounters();
      }

      if (ClientManagers.moduleManager.chestStealer.isToggled()) {
         ClientManagers.moduleManager.chestStealer.resetClickTickCounter();
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
      boolean flag = mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
      Slot slotAtPosition = this.getSlotAtPosition(mouseX, mouseY);
      long systemTime = Minecraft.getSystemTime();
      this.doubleClick = this.lastClickSlot == slotAtPosition && systemTime - this.lastClickTime < 250L && this.lastClickButton == mouseButton;
      this.ignoreMouseUp = false;
      if (mouseButton == 0 || mouseButton == 1 || flag) {
         int guiLeft2 = this.guiLeft;
         int guiTop2 = this.guiTop;
         boolean flag2 = mouseX < guiLeft2 || mouseY < guiTop2 || mouseX >= guiLeft2 + this.xSize || mouseY >= guiTop2 + this.ySize;
         int n = -1;
         if (slotAtPosition != null) {
            n = slotAtPosition.slotNumber;
         }

         if (flag2) {
            n = -999;
         }

         if (this.mc.gameSettings.touchscreen && flag2 && this.mc.thePlayer.inventory.getItemStack() == null) {
            GuiScreen screen = (GuiScreen)null;
            this.mc.displayGuiScreen(screen);
            return;
         }

         if (n != -1) {
            if (this.mc.gameSettings.touchscreen) {
               if (slotAtPosition != null && slotAtPosition.getHasStack()) {
                  this.clickedSlot = slotAtPosition;
                  this.draggedStack = null;
                  this.isRightMouseClick = mouseButton == 1;
               } else {
                  this.clickedSlot = null;
               }
            } else if (!this.dragSplitting) {
               if (this.mc.thePlayer.inventory.getItemStack() == null) {
                  if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.handleMouseClick(slotAtPosition, n, mouseButton, 3);
                  } else {
                     boolean flag3 = n != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54) || this.forceShiftClick);
                     byte b = 0;
                     if (flag3) {
                        this.shiftClickedSlot = slotAtPosition != null && slotAtPosition.getHasStack() ? slotAtPosition.getStack() : null;
                        b = 1;
                     } else if (n == -999) {
                        b = 4;
                     }

                     this.handleMouseClick(slotAtPosition, n, mouseButton, b);
                  }

                  this.ignoreMouseUp = true;
               } else {
                  this.dragSplitting = true;
                  this.dragSplittingButton = mouseButton;
                  this.dragSplittingSlots.clear();
                  if (mouseButton == 0) {
                     this.dragSplittingLimit = 0;
                  } else if (mouseButton == 1) {
                     this.dragSplittingLimit = 1;
                  } else if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.dragSplittingLimit = 2;
                  }
               }
            }
         }
      }

      this.lastClickSlot = slotAtPosition;
      this.lastClickTime = systemTime;
      this.lastClickButton = mouseButton;
      this.forceShiftClick = false;
      this.checkHotbarKeys(mouseButton - 100);
   }

   private void drawSlot(Slot slot) {
      int xDisplayPosition = slot.xDisplayPosition;
      int yDisplayPosition = slot.yDisplayPosition;
      ItemStack stack = slot.getStack();
      boolean flag = false;
      boolean flag2 = slot == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
      ItemStack itemStack = this.mc.thePlayer.inventory.getItemStack();
      String str = null;
      if (slot == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && stack != null) {
         stack = stack.copy();
         stack.stackSize /= 2;
      } else if (this.dragSplitting && this.dragSplittingSlots.contains(slot) && itemStack != null) {
         if (this.dragSplittingSlots.size() == 1) {
            return;
         }

         if (Container.canAddItemToSlot(slot, itemStack, true) && this.inventorySlots.canDragIntoSlot(slot)) {
            stack = itemStack.copy();
            flag = true;
            Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, stack, slot.getStack() == null ? 0 : slot.getStack().stackSize);
            if (stack.stackSize > stack.getMaxStackSize()) {
               str = EnumChatFormatting.YELLOW + "" + stack.getMaxStackSize();
               stack.stackSize = stack.getMaxStackSize();
            }

            if (stack.stackSize > slot.getItemStackLimit(stack)) {
               str = EnumChatFormatting.YELLOW + "" + slot.getItemStackLimit(stack);
               stack.stackSize = slot.getItemStackLimit(stack);
            }
         } else {
            this.dragSplittingSlots.remove(slot);
            this.updateDragSplitting();
         }
      }

      this.zLevel = 100.0F;
      this.itemRender.zLevel = 100.0F;
      if (stack == null) {
         String slotTexture = slot.getSlotTexture();
         if (slotTexture != null) {
            TextureAtlasSprite atlasSprite = this.mc.getTextureMapBlocks().getAtlasSprite(slotTexture);
            GlStateManager.disableLighting();
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            this.drawTexturedModalRect(xDisplayPosition, yDisplayPosition, atlasSprite, 16, 16);
            GlStateManager.enableLighting();
            flag2 = true;
         }
      }

      if (!flag2) {
         if (flag) {
            drawRect(xDisplayPosition, yDisplayPosition, xDisplayPosition + 16, yDisplayPosition + 16, -2130706433);
         }

         GlStateManager.enableDepth();
         this.itemRender.renderItemAndEffectIntoGUI(stack, xDisplayPosition, yDisplayPosition);
         this.itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, stack, xDisplayPosition, yDisplayPosition, str);
      }

      this.itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }

      if (!ClientManagers.moduleManager.invManager.isToggled() || !ClientManagers.moduleManager.invManager.isBlockingGuiInput()) {
         this.checkHotbarKeys(keyCode);
         if (this.theSlot != null && this.theSlot.getHasStack()) {
            if (keyCode == this.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
            } else if (keyCode == this.mc.gameSettings.keyBindDrop.getKeyCode()) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
            }
         }
      }
   }

   public boolean isMouseOverSlot(Slot slot, int n, int n2) {
      return this.isPointInRegion(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, n, n2);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      int guiLeft2 = this.guiLeft;
      int guiTop2 = this.guiTop;
      this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
      GlStateManager.disableRescaleNormal();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableLighting();
      GlStateManager.disableDepth();
      super.drawScreen(mouseX, mouseY, partialTicks);
      RenderHelper.enableGUIStandardItemLighting();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)guiLeft2, (float)guiTop2, 0.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableRescaleNormal();
      this.theSlot = null;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)((short)240) / 1.0F, (float)((short)240) / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

      for (int i = 0; i < this.inventorySlots.inventorySlots.size(); i++) {
         Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i);
         this.drawSlot(slot);
         if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.canBeHovered()
            || ClientManagers.moduleManager.chestStealer.isToggled() && ClientManagers.moduleManager.chestStealer.visualize.getValue() && ClientManagers.moduleManager.chestStealer.getNextTargetSlot() == i
            || ClientManagers.moduleManager.stealer.isToggled() && ClientManagers.moduleManager.stealer.visualize.getValue() && ClientManagers.moduleManager.stealer.getNextTargetSlot() == slot
            || ClientManagers.moduleManager.invManager.isToggled() && ClientManagers.moduleManager.invManager.visualize.getValue() && ClientManagers.moduleManager.invManager.getNextTargetSlot() == i) {
            this.theSlot = slot;
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int xDisplayPosition = slot.xDisplayPosition;
            int yDisplayPosition = slot.yDisplayPosition;
            GlStateManager.colorMask(true, true, true, false);
            this.drawGradientRect(xDisplayPosition, yDisplayPosition, xDisplayPosition + 16, yDisplayPosition + 16, -2130706433, -2130706433);
            GlStateManager.colorMask(true, true, true, true);
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
         }
      }

      RenderHelper.disableStandardItemLighting();
      this.drawGuiContainerForegroundLayer(mouseX, mouseY);
      RenderHelper.enableGUIStandardItemLighting();
      InventoryPlayer inventory = this.mc.thePlayer.inventory;
      ItemStack stack = this.draggedStack == null ? inventory.getItemStack() : this.draggedStack;
      if (stack != null) {
         int n = this.draggedStack == null ? 8 : 16;
         String str = null;
         if (this.draggedStack != null && this.isRightMouseClick) {
            stack = stack.copy();
            stack.stackSize = MathHelper.ceiling_float_int((float)stack.stackSize / 2.0F);
         } else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
            stack = stack.copy();
            stack.stackSize = this.dragSplittingRemnant;
            if (stack.stackSize == 0) {
               str = "" + EnumChatFormatting.YELLOW + "0";
            }
         }

         this.drawItemStack(stack, mouseX - guiLeft2 - ((byte)8), mouseY - guiTop2 - n, str);
      }

      if (this.returningStack != null) {
         float f = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
         if (f >= 1.0F) {
            f = 1.0F;
            this.returningStack = null;
         }

         int n2 = this.returningStackDestSlot.xDisplayPosition - this.touchUpX;
         int n3 = this.returningStackDestSlot.yDisplayPosition - this.touchUpY;
         int n4 = this.touchUpX + (int)((float)n2 * f);
         int n5 = this.touchUpY + (int)((float)n3 * f);
         this.drawItemStack(this.returningStack, n4, n5, (String)null);
      }

      GlStateManager.popMatrix();
      if (inventory.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
         this.renderToolTip(this.theSlot.getStack(), mouseX, mouseY);
      }

      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      RenderHelper.enableStandardItemLighting();
   }

   private void drawItemStack(ItemStack stack, int n, int n2, String str) {
      GlStateManager.translate(0.0F, 0.0F, 32.0F);
      this.zLevel = 200.0F;
      this.itemRender.zLevel = 200.0F;
      this.itemRender.renderItemAndEffectIntoGUI(stack, n, n2);
      this.itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, stack, n, n2 - (this.draggedStack == null ? 0 : 8), str);
      this.zLevel = 0.0F;
      this.itemRender.zLevel = 0.0F;
   }

   public GuiContainer(Container container) {
      this.inventorySlots = container;
      this.ignoreMouseUp = true;
   }

   public boolean checkHotbarKeys(int n) {
      if (this.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null) {
         for (int i = 0; i < 9; i++) {
            if (n == this.mc.gameSettings.keyBindsHotbar[i].getKeyCode()) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, i, 2);
               return true;
            }
         }
      }

      return false;
   }

   public abstract void drawGuiContainerBackgroundLayer(float f, int n, int n2);

   public boolean isPointInRegion(int n, int n2, int n3, int n4, int n5, int n6) {
      int guiLeft2 = this.guiLeft;
      int guiTop2 = this.guiTop;
      n5 -= guiLeft2;
      n6 -= guiTop2;
      return n5 >= n - 1 && n5 < n + n3 + 1 && n6 >= n2 - 1 && n6 < n2 + n4 + 1;
   }

   public void handleMouseClick(Slot slot, int n, int n2, int n3) {
      if (slot != null) {
         n = slot.slotNumber;
      }

      this.mc.playerController.windowClick(this.inventorySlots.windowId, n, n2, n3, this.mc.thePlayer);
   }

   private Slot getSlotAtPosition(int n, int n2) {
      for (int i = 0; i < this.inventorySlots.inventorySlots.size(); i++) {
         Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i);
         if (this.isMouseOverSlot(slot, n, n2)) {
            return slot;
         }
      }

      return null;
   }

   @Override
   public void onGuiClosed() {
      if (this.mc.thePlayer != null) {
         this.inventorySlots.onContainerClosed(this.mc.thePlayer);
      }
   }

   static {
      String str = "textures/gui/container/inventory.png";
      inventoryBackground = new ResourceLocation(str);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      Slot slotAtPosition = this.getSlotAtPosition(mouseX, mouseY);
      int guiLeft2 = this.guiLeft;
      int guiTop2 = this.guiTop;
      boolean flag = mouseX < guiLeft2 || mouseY < guiTop2 || mouseX >= guiLeft2 + this.xSize || mouseY >= guiTop2 + this.ySize;
      int n = -1;
      if (slotAtPosition != null) {
         n = slotAtPosition.slotNumber;
      }

      if (flag) {
         n = -999;
      }

      if (this.doubleClick && slotAtPosition != null && state == 0 && this.inventorySlots.canMergeSlot((ItemStack)null, slotAtPosition)) {
         if (isShiftKeyDown()) {
            if (slotAtPosition != null && slotAtPosition.inventory != null && this.shiftClickedSlot != null) {
               for (Slot slot : (Iterable<Slot>)(this.inventorySlots.inventorySlots)) {
                  if (slot != null
                     && slot.canTakeStack(this.mc.thePlayer)
                     && slot.getHasStack()
                     && slot.inventory == slotAtPosition.inventory
                     && Container.canAddItemToSlot(slot, this.shiftClickedSlot, true)) {
                     this.handleMouseClick(slot, slot.slotNumber, state, 1);
                  }
               }
            }
         } else {
            this.handleMouseClick(slotAtPosition, n, state, 6);
         }

         this.doubleClick = false;
         this.lastClickTime = 0L;
      } else {
         if (this.dragSplitting && this.dragSplittingButton != state) {
            this.dragSplitting = false;
            this.dragSplittingSlots.clear();
            this.ignoreMouseUp = true;
            return;
         }

         if (this.ignoreMouseUp) {
            this.ignoreMouseUp = false;
            return;
         }

         if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
            if (state == 0 || state == 1) {
               if (this.draggedStack == null && slotAtPosition != this.clickedSlot) {
                  this.draggedStack = this.clickedSlot.getStack();
               }

               boolean flag2 = Container.canAddItemToSlot(slotAtPosition, this.draggedStack, false);
               if (n != -1 && this.draggedStack != null && flag2) {
                  this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
                  this.handleMouseClick(slotAtPosition, n, 0, 0);
                  if (this.mc.thePlayer.inventory.getItemStack() != null) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
                     this.touchUpX = mouseX - guiLeft2;
                     this.touchUpY = mouseY - guiTop2;
                     this.returningStackDestSlot = this.clickedSlot;
                     this.returningStack = this.draggedStack;
                     this.returningStackTime = Minecraft.getSystemTime();
                  } else {
                     this.returningStack = null;
                  }
               } else if (this.draggedStack != null) {
                  this.touchUpX = mouseX - guiLeft2;
                  this.touchUpY = mouseY - guiTop2;
                  this.returningStackDestSlot = this.clickedSlot;
                  this.returningStack = this.draggedStack;
                  this.returningStackTime = Minecraft.getSystemTime();
               }

               this.draggedStack = null;
               this.clickedSlot = null;
            }
         } else if (this.dragSplitting && !this.dragSplittingSlots.isEmpty()) {
            this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(0, this.dragSplittingLimit), 5);

            for (Slot slot2 : (Iterable<Slot>)(this.dragSplittingSlots)) {
               this.handleMouseClick(slot2, slot2.slotNumber, Container.getQuickcraftMask(1, this.dragSplittingLimit), 5);
            }

            this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(2, this.dragSplittingLimit), 5);
         } else if (this.mc.thePlayer.inventory.getItemStack() != null) {
            if (state == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
               this.handleMouseClick(slotAtPosition, n, state, 3);
            } else {
               boolean flag3 = n != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
               if (flag3) {
                  this.shiftClickedSlot = slotAtPosition != null && slotAtPosition.getHasStack() ? slotAtPosition.getStack() : null;
               }

               this.handleMouseClick(slotAtPosition, n, state, flag3 ? 1 : 0);
            }
         }
      }

      if (this.mc.thePlayer.inventory.getItemStack() == null) {
         this.lastClickTime = 0L;
      }

      this.dragSplitting = false;
   }

}
