package net.minecraft.client.gui.inventory;

import client.ClientGlobals;
import client.module.Module;
import client.setting.ClientSettingsBlackType;
import client.util.ListenerAdapter27;
import client.util.ServiceStringEntry;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public abstract class GuiContainer extends GuiScreen {
   public static final ResourceLocation resourceLocation4;
   public int count9 = 176;
   public int count10 = 166;
   public Container container;
   public int indexOffset1;
   public int indexOffset2;
   public Slot slot3;
   public Slot slot4;
   public boolean enabled7;
   public ItemStack itemStack3;
   public int count7;
   public int count2;
   public Slot slot2;
   public long timestamp3;
   public ItemStack itemStack;
   public Slot slot;
   public long timestamp2;
   public final Set set3 = Sets.newHashSet();
   public boolean enabled6;
   public int count8;
   public int count6;
   public boolean enabled8;
   public int count3;
   public long timestamp4;
   public Slot slot5;
   public int count5;
   public boolean enabled9;
   public ItemStack itemStack2;
   public boolean state2 = false;

   @Override
   public void updateState2() {
      super.updateState2();
      this.mc.thePlayer.container2 = this.container;
      this.indexOffset1 = (this.width - this.count9) / 2;
      this.indexOffset2 = (this.height - this.count10) / 2;
   }

   public boolean checkIndex4(int count) {
      if (this.mc.thePlayer.inventoryPlayer.getItemStack() == null && this.slot3 != null) {
         for (int index = 0; index < 9; index++) {
            if (count == this.mc.gameSettings.scorePlayerTeamArray[index].getKeyCode()) {
               this.applySlot(this.slot3, this.slot3.indexOffset1, index, 2);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void enableRepeatEvents() {
      if (this.mc.thePlayer != null) {
         this.container.applyEntityPlayer(this.mc.thePlayer);
      }
   }

   @Override
   public void applyIndex(int count, int count4, int count11, long value) {
      Slot slot6 = this.resolveSlot(count, count4);
      ItemStack stack = this.mc.thePlayer.inventoryPlayer.getItemStack();
      if (this.slot4 != null && this.mc.gameSettings.state48) {
         if (count11 == 0 || count11 == 1) {
            if (this.itemStack3 == null) {
               if (slot6 != this.slot4 && this.slot4.getStack() != null) {
                  this.itemStack3 = this.slot4.getStack().getItemStack();
               }
            } else if (this.itemStack3.animationsToGo > 1 && slot6 != null && Container.matchesSlot(slot6, this.itemStack3, false)) {
               long timestamp = Minecraft.getTimestamp();
               if (this.slot == slot6) {
                  if (timestamp - this.timestamp2 > 500L) {
                     this.applySlot(this.slot4, this.slot4.indexOffset1, 0, 0);
                     this.applySlot(slot6, slot6.indexOffset1, 1, 0);
                     this.applySlot(this.slot4, this.slot4.indexOffset1, 0, 0);
                     this.timestamp2 = timestamp + 750L;
                     this.itemStack3.animationsToGo--;
                  }
               } else {
                  this.slot = slot6;
                  this.timestamp2 = timestamp;
               }
            }
         }
      } else if (this.enabled6
         && slot6 != null
         && stack != null
         && stack.animationsToGo > this.set3.size()
         && Container.matchesSlot(slot6, stack, true)
         && slot6.isItemValid(stack)
         && this.container.processAction(slot6)) {
         this.set3.add(slot6);
         this.renderElement();
      }
   }

   private void renderElement() {
      ItemStack stack = this.mc.thePlayer.inventoryPlayer.getItemStack();
      if (stack != null && this.enabled6) {
         this.count3 = stack.animationsToGo;

         for (Slot slot6 : (Iterable<Slot>)(Iterable<?>)this.set3) {
            ItemStack stack2 = stack.getItemStack();
            int count = slot6.getStack() == null ? 0 : slot6.getStack().animationsToGo;
            Container.applySet(this.set3, this.count8, stack2, count);
            if (stack2.animationsToGo > stack2.processAction5()) {
               stack2.animationsToGo = stack2.processAction5();
            }

            if (stack2.animationsToGo > slot6.processAction3(stack2)) {
               stack2.animationsToGo = slot6.processAction3(stack2);
            }

            this.count3 = this.count3 - (stack2.animationsToGo - count);
         }
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyIndex(int count, int count4) {
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1 || count == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.updateState5();
      }

      if (!((Module)ClientGlobals.moduleManager.invManager).isToggled() || !ClientGlobals.moduleManager.invManager.checkCondition2()) {
         this.checkIndex4(count);
         if (this.slot3 != null && this.slot3.isActive()) {
            if (count == this.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
               this.applySlot(this.slot3, this.slot3.indexOffset1, 0, 3);
            } else if (count == this.mc.gameSettings.keyBindDrop.getKeyCode()) {
               this.applySlot(this.slot3, this.slot3.indexOffset1, ((isActive3()) ? 1 : 0), 4);
            }
         }
      }
   }

   @Override
   public void applyIndex(int count, int count4, int count11) {
      Slot slot6 = this.resolveSlot(count, count4);
      int indexOffset = this.indexOffset1;
      int indexOffset3 = this.indexOffset2;
      boolean flag = count < indexOffset || count4 < indexOffset3 || count >= indexOffset + this.count9 || count4 >= indexOffset3 + this.count10;
      int count12 = -1;
      if (slot6 != null) {
         count12 = slot6.indexOffset1;
      }

      if (flag) {
         count12 = -999;
      }

      if (this.enabled9 && slot6 != null && count11 == 0 && this.container.processAction((ItemStack)null, slot6)) {
         if (isActiveAsFlag()) {
            if (slot6 != null && slot6.iInventory != null && this.itemStack2 != null) {
               for (Slot slot7 : (Iterable<Slot>)(Iterable<?>)this.container.itemList2) {
                  if (slot7 != null
                     && slot7.processAction(this.mc.thePlayer)
                     && slot7.isActive()
                     && slot7.iInventory == slot6.iInventory
                     && Container.matchesSlot(slot7, this.itemStack2, true)) {
                     this.applySlot(slot7, slot7.indexOffset1, count11, 1);
                  }
               }
            }
         } else {
            this.applySlot(slot6, count12, count11, 6);
         }

         this.enabled9 = false;
         this.timestamp4 = 0L;
      } else {
         if (this.enabled6 && this.count6 != count11) {
            this.enabled6 = false;
            this.set3.clear();
            this.enabled8 = true;
            return;
         }

         if (this.enabled8) {
            this.enabled8 = false;
            return;
         }

         if (this.slot4 != null && this.mc.gameSettings.state48) {
            if (count11 == 0 || count11 == 1) {
               if (this.itemStack3 == null && slot6 != this.slot4) {
                  this.itemStack3 = this.slot4.getStack();
               }

               boolean flag2 = Container.matchesSlot(slot6, this.itemStack3, false);
               if (count12 != -1 && this.itemStack3 != null && flag2) {
                  this.applySlot(this.slot4, this.slot4.indexOffset1, count11, 0);
                  this.applySlot(slot6, count12, 0, 0);
                  if (this.mc.thePlayer.inventoryPlayer.getItemStack() != null) {
                     this.applySlot(this.slot4, this.slot4.indexOffset1, count11, 0);
                     this.count7 = count - indexOffset;
                     this.count2 = count4 - indexOffset3;
                     this.slot2 = this.slot4;
                     this.itemStack = this.itemStack3;
                     this.timestamp3 = Minecraft.getTimestamp();
                  } else {
                     this.itemStack = null;
                  }
               } else if (this.itemStack3 != null) {
                  this.count7 = count - indexOffset;
                  this.count2 = count4 - indexOffset3;
                  this.slot2 = this.slot4;
                  this.itemStack = this.itemStack3;
                  this.timestamp3 = Minecraft.getTimestamp();
               }

               this.itemStack3 = null;
               this.slot4 = null;
            }
         } else if (this.enabled6 && !this.set3.isEmpty()) {
            this.applySlot((Slot)null, -999, Container.processAction(0, this.count8), 5);

            for (Slot slot8 : (Iterable<Slot>)(Iterable<?>)this.set3) {
               this.applySlot(slot8, slot8.indexOffset1, Container.processAction(1, this.count8), 5);
            }

            this.applySlot((Slot)null, -999, Container.processAction(2, this.count8), 5);
         } else if (this.mc.thePlayer.inventoryPlayer.getItemStack() != null) {
            if (count11 == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
               this.applySlot(slot6, count12, count11, 3);
            } else {
               boolean flag3 = count12 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
               if (flag3) {
                  this.itemStack2 = slot6 != null && slot6.isActive() ? slot6.getStack() : null;
               }

               this.applySlot(slot6, count12, count11, flag3 ? 1 : 0);
            }
         }
      }

      if (this.mc.thePlayer.inventoryPlayer.getItemStack() == null) {
         this.timestamp4 = 0L;
      }

      this.enabled6 = false;
   }

   @Override
   public void updateState4() {
      super.updateState4();
      if (!this.mc.thePlayer.canExecute() || this.mc.thePlayer.isDead) {
         this.mc.thePlayer.updateState5();
      }
   }

   public boolean checkSlot(Slot slot6, int count, int count4) {
      return this.checkIndex(slot6.indexOffset, slot6.indexOffset2, 16, 16, count, count4);
   }

   private Slot resolveSlot(int count, int count4) {
      for (int index = 0; index < this.container.itemList2.size(); index++) {
         Slot slot6 = (Slot)this.container.itemList2.get(index);
         if (this.checkSlot(slot6, count, count4)) {
            return slot6;
         }
      }

      return null;
   }

   private void applyItemStack(ItemStack stack, int count, int count4, String text) {
      GlStateManager.translate(0.0F, 0.0F, 32.0F);
      this.factor = 200.0F;
      this.renderItem.factor = 200.0F;
      this.renderItem.renderItemIntoGUI(stack, count, count4);
      this.renderItem.renderItemOverlayIntoGUI(this.fontRenderer, stack, count, count4 - (this.itemStack3 == null ? 0 : 8), text);
      this.factor = 0.0F;
      this.renderItem.factor = 0.0F;
   }

   public boolean checkIndex(int count, int count4, int count11, int count12, int count13, int count14) {
      int indexOffset = this.indexOffset1;
      int indexOffset3 = this.indexOffset2;
      int count15 = count13 - indexOffset;
      int count16 = count14 - indexOffset3;
      return count15 >= count - 1 && count15 < count + count11 + 1 && count16 >= count4 - 1 && count16 < count4 + count12 + 1;
   }

   private void applySlot(Slot slot6) {
      int indexOffset = slot6.indexOffset;
      int indexOffset3 = slot6.indexOffset2;
      ItemStack stack = slot6.getStack();
      boolean flag = false;
      boolean flag2 = slot6 == this.slot4 && this.itemStack3 != null && !this.enabled7;
      ItemStack stack2 = this.mc.thePlayer.inventoryPlayer.getItemStack();
      String text = null;
      if (slot6 == this.slot4 && this.itemStack3 != null && this.enabled7 && stack != null) {
         stack = stack.getItemStack();
         stack.animationsToGo /= 2;
      } else if (this.enabled6 && this.set3.contains(slot6) && stack2 != null) {
         if (this.set3.size() == 1) {
            return;
         }

         if (Container.matchesSlot(slot6, stack2, true) && this.container.processAction(slot6)) {
            stack = stack2.getItemStack();
            flag = true;
            Container.applySet(this.set3, this.count8, stack, slot6.getStack() == null ? 0 : slot6.getStack().animationsToGo);
            if (stack.animationsToGo > stack.processAction5()) {
               text = ClientSettingsBlackType.YELLOW + "" + stack.processAction5();
               stack.animationsToGo = stack.processAction5();
            }

            if (stack.animationsToGo > slot6.processAction3(stack)) {
               text = ClientSettingsBlackType.YELLOW + "" + slot6.processAction3(stack);
               stack.animationsToGo = slot6.processAction3(stack);
            }
         } else {
            this.set3.remove(slot6);
            this.renderElement();
         }
      }

      this.factor = 100.0F;
      this.renderItem.factor = 100.0F;
      if (stack == null) {
         String text2 = slot6.processAction();
         if (text2 != null) {
            ServiceStringEntry serviceStringEntry = this.mc.getListenerAdapter27().get(text2);
            GlStateManager.applyState3();
            this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
            this.applyServiceStringEntry(indexOffset, indexOffset3, serviceStringEntry, 16, 16);
            GlStateManager.applyState13();
            flag2 = true;
         }
      }

      if (!flag2) {
         if (flag) {
            processAction(indexOffset, indexOffset3, indexOffset + 16, indexOffset3 + 16, -2130706433);
         }

         GlStateManager.disableDepth();
         this.renderItem.renderItemIntoGUI(stack, indexOffset, indexOffset3);
         this.renderItem.renderItemOverlayIntoGUI(this.fontRenderer, stack, indexOffset, indexOffset3, text);
      }

      this.renderItem.factor = 0.0F;
      this.factor = 0.0F;
   }

   @Override
   public void processAction(int count, int count4, int count11) throws IOException {
      if (count11 - 100 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.updateState5();
      }

      double[] offsetArray = new double[]{(double)count, (double)count4};
      if (((Module)ClientGlobals.moduleManager.invManager).isToggled()) {
         ClientGlobals.moduleManager.invManager.updateState();
      }

      if (((Module)ClientGlobals.moduleManager.chestStealer).isToggled()) {
         ClientGlobals.moduleManager.chestStealer.updateState();
      }

      super.processAction(count, count4, count11);
      boolean flag = count11 == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
      Slot slot6 = this.resolveSlot(count, count4);
      long timestamp = Minecraft.getTimestamp();
      this.enabled9 = this.slot5 == slot6 && timestamp - this.timestamp4 < 250L && this.count5 == count11;
      this.enabled8 = false;
      if (count11 == 0 || count11 == 1 || flag) {
         int indexOffset = this.indexOffset1;
         int indexOffset3 = this.indexOffset2;
         boolean flag2 = count < indexOffset || count4 < indexOffset3 || count >= indexOffset + this.count9 || count4 >= indexOffset3 + this.count10;
         int count12 = -1;
         if (slot6 != null) {
            count12 = slot6.indexOffset1;
         }

         if (flag2) {
            count12 = -999;
         }

         if (this.mc.gameSettings.state48 && flag2 && this.mc.thePlayer.inventoryPlayer.getItemStack() == null) {
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            return;
         }

         if (count12 != -1) {
            if (this.mc.gameSettings.state48) {
               if (slot6 != null && slot6.isActive()) {
                  this.slot4 = slot6;
                  this.itemStack3 = null;
                  this.enabled7 = count11 == 1;
               } else {
                  this.slot4 = null;
               }
            } else if (!this.enabled6) {
               if (this.mc.thePlayer.inventoryPlayer.getItemStack() == null) {
                  if (count11 == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.applySlot(slot6, count12, count11, 3);
                  } else {
                     boolean flag3 = count12 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54) || this.state2);
                     byte byteValue = 0;
                     if (flag3) {
                        this.itemStack2 = slot6 != null && slot6.isActive() ? slot6.getStack() : null;
                        byteValue = 1;
                     } else if (count12 == -999) {
                        byteValue = 4;
                     }

                     this.applySlot(slot6, count12, count11, byteValue);
                  }

                  this.enabled8 = true;
               } else {
                  this.enabled6 = true;
                  this.count6 = count11;
                  this.set3.clear();
                  if (count11 == 0) {
                     this.count8 = 0;
                  } else if (count11 == 1) {
                     this.count8 = 1;
                  } else if (count11 == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.count8 = 2;
                  }
               }
            }
         }
      }

      this.slot5 = slot6;
      this.timestamp4 = timestamp;
      this.count5 = count11;
      this.state2 = false;
      this.checkIndex4(count11 - 100);
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      this.renderElement3();
      int indexOffset = this.indexOffset1;
      int indexOffset3 = this.indexOffset2;
      this.applyFloat(factor, count, count4);
      GlStateManager.applyState8();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.applyState3();
      GlStateManager.enableDepth();
      super.applyIndex(count, count4, factor);
      RenderHelper.enableGUIStandardItemLighting();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)indexOffset, (float)indexOffset3, 0.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      this.slot3 = null;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)240 / 1.0F, (float)240 / 1.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);

      for (int index = 0; index < this.container.itemList2.size(); index++) {
         Slot slot6 = (Slot)this.container.itemList2.get(index);
         this.applySlot(slot6);
         if (this.checkSlot(slot6, count, count4) && slot6.getHasStack()
            || ((Module)ClientGlobals.moduleManager.chestStealer).isToggled()
               && ClientGlobals.moduleManager.chestStealer.settingVisualize.isEnabled()
               && ClientGlobals.moduleManager.chestStealer.findItemSlot() == index
            || ((Module)ClientGlobals.moduleManager.invManager).isToggled()
               && ClientGlobals.moduleManager.invManager.settingVisualize.isEnabled()
               && ClientGlobals.moduleManager.invManager.findItemSlot() == index) {
            this.slot3 = slot6;
            GlStateManager.applyState3();
            GlStateManager.enableDepth();
            int indexOffset4 = slot6.indexOffset;
            int indexOffset5 = slot6.indexOffset2;
            GlStateManager.applyFlag(true, true, true, false);
            this.applyIndex(indexOffset4, indexOffset5, indexOffset4 + 16, indexOffset5 + 16, -2130706433, -2130706433);
            GlStateManager.applyFlag(true, true, true, true);
            GlStateManager.applyState13();
            GlStateManager.disableDepth();
         }
      }

      RenderHelper.disableStandardItemLighting();
      this.applyIndex(count, count4);
      RenderHelper.enableGUIStandardItemLighting();
      InventoryPlayer inventoryPlayer = this.mc.thePlayer.inventoryPlayer;
      ItemStack stack = this.itemStack3 == null ? inventoryPlayer.getItemStack() : this.itemStack3;
      if (stack != null) {
         byte byteValue = 8;
         int count11 = this.itemStack3 == null ? 8 : 16;
         String text = null;
         if (this.itemStack3 != null && this.enabled7) {
            stack = stack.getItemStack();
            stack.animationsToGo = MathHelper.computeCount((float)stack.animationsToGo / 2.0F);
         } else if (this.enabled6 && this.set3.size() > 1) {
            stack = stack.getItemStack();
            stack.animationsToGo = this.count3;
            if (stack.animationsToGo == 0) {
               text = "" + ClientSettingsBlackType.YELLOW + "0";
            }
         }

         this.applyItemStack(stack, count - indexOffset - byteValue, count4 - indexOffset3 - count11, text);
      }

      if (this.itemStack != null) {
         float factor2 = (float)(Minecraft.getTimestamp() - this.timestamp3) / 100.0F;
         if (factor2 >= 1.0F) {
            factor2 = 1.0F;
            this.itemStack = null;
         }

         int count12 = this.slot2.indexOffset - this.count7;
         int count13 = this.slot2.indexOffset2 - this.count2;
         int count14 = this.count7 + (int)((float)count12 * factor2);
         int count15 = this.count2 + (int)((float)count13 * factor2);
         this.applyItemStack(this.itemStack, count14, count15, (String)null);
      }

      GlStateManager.popMatrix();
      if (inventoryPlayer.getItemStack() == null && this.slot3 != null && this.slot3.isActive()) {
         ItemStack stack2 = this.slot3.getStack();
         this.applyItemStack(stack2, count, count4);
      }

      GlStateManager.applyState13();
      GlStateManager.disableDepth();
      RenderHelper.enableStandardItemLighting();
   }

   public void applySlot(Slot slot6, int count, int count4, int count11) {
      int count12 = count;
      if (slot6 != null) {
         count12 = slot6.indexOffset1;
      }

      this.mc.playerController.windowClick(this.container.indexOffset2, count12, count4, count11, this.mc.thePlayer);
   }

   public abstract void applyFloat(float factor, int count, int count4);

   public GuiContainer(Container container2) {
      this.container = container2;
      this.enabled8 = true;
   }

   static {
      String text = "textures/gui/container/inventory.png";
      resourceLocation4 = new ResourceLocation(text);
   }
}
