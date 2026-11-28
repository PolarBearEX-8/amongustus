package net.minecraft.client.gui;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class GuiRepair extends GuiContainer implements ICrafting {
   private static final ResourceLocation anvilResource = new ResourceLocation("textures/gui/container/anvil.png");
   private ContainerRepair anvil;
   private GuiTextField nameField;
   private InventoryPlayer playerInventory;

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.fontRendererObj.drawString(I18n.format("container.repair", new Object[0]), 60, 6, 4210752);
      if (this.anvil.maximumCost > 0) {
         int n3 = 8453920;
         boolean flag = true;
         String str = I18n.format("container.repair.cost", new Object[]{this.anvil.maximumCost});
         if (this.anvil.maximumCost >= 40 && !this.mc.thePlayer.capabilities.isCreativeMode) {
            str = I18n.format("container.repair.expensive", new Object[0]);
            n3 = 16736352;
         } else if (!this.anvil.getSlot(2).getHasStack()) {
            flag = false;
         } else if (!this.anvil.getSlot(2).canTakeStack(this.playerInventory.player)) {
            n3 = 16736352;
         }

         if (flag) {
            int n4 = 0xFF000000 | (n3 & 16579836) >> 2 | n3 & 0xFF000000;
            int n5 = this.xSize - 8 - this.fontRendererObj.getStringWidth(str);
            byte b = 67;
            if (this.fontRendererObj.getUnicodeFlag()) {
               drawRect(n5 - 3, b - 2, this.xSize - 7, b + 10, -16777216);
               drawRect(n5 - 2, b - 1, this.xSize - 8, b + 9, -12895429);
            } else {
               this.fontRendererObj.drawString(str, n5, b + 1, n4);
               this.fontRendererObj.drawString(str, n5 + 1, b, n4);
               this.fontRendererObj.drawString(str, n5 + 1, b + 1, n4);
            }

            this.fontRendererObj.drawString(str, n5, b, n3);
         }
      }

      GlStateManager.enableLighting();
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.nameField.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(anvilResource);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      this.drawTexturedModalRect(n3 + 59, n4 + 20, 0, this.ySize + (this.anvil.getSlot(0).getHasStack() ? 0 : 16), 110, 16);
      if ((this.anvil.getSlot(0).getHasStack() || this.anvil.getSlot(1).getHasStack()) && !this.anvil.getSlot(2).getHasStack()) {
         this.drawTexturedModalRect(n3 + 99, n4 + 45, this.xSize, 0, 28, 21);
      }
   }

   @Override
   public void sendAllWindowProperties(Container container, IInventory inventory) {
   }

   private void renameItem() {
      String text = this.nameField.getText();
      Slot slot = this.anvil.getSlot(0);
      if (slot != null && slot.getHasStack() && !slot.getStack().hasDisplayName() && text.equals(slot.getStack().getDisplayName())) {
         text = "";
      }

      this.anvil.updateItemName(text);
      this.mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|ItemName", new PacketBuffer(Unpooled.buffer()).writeString(text)));
   }

   @Override
   public void sendSlotContents(Container container, int n, ItemStack stack) {
      if (n == 0) {
         this.nameField.setText(stack == null ? "" : stack.getDisplayName());
         this.nameField.setEnabled(stack != null);
         if (stack != null) {
            this.renameItem();
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.nameField.drawTextBox();
   }

   @Override
   public void sendProgressBarUpdate(Container container, int n, int n2) {
   }

   @Override
   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
      int n = (this.width - this.xSize) / 2;
      int n2 = (this.height - this.ySize) / 2;
      this.nameField = new GuiTextField(0, this.fontRendererObj, n + 62, n2 + 24, 103, 12);
      this.nameField.setTextColor(-1);
      this.nameField.setDisabledTextColour(-1);
      this.nameField.setEnableBackgroundDrawing(false);
      this.nameField.setMaxStringLength(30);
      this.inventorySlots.removeCraftingFromCrafters(this);
      this.inventorySlots.onCraftGuiOpened(this);
   }

   @Override
   public void updateCraftingInventory(Container container, List list) {
      this.sendSlotContents(container, 0, container.getSlot(0).getStack());
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.nameField.textboxKeyTyped(typedChar, keyCode)) {
         this.renameItem();
      } else {
         super.keyTyped(typedChar, keyCode);
      }
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
      Keyboard.enableRepeatEvents(false);
      this.inventorySlots.removeCraftingFromCrafters(this);
   }

   public GuiRepair(InventoryPlayer inventoryPlayer, World world) {
      super(new ContainerRepair(inventoryPlayer, world, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = inventoryPlayer;
      this.anvil = (ContainerRepair)this.inventorySlots;
   }
}
