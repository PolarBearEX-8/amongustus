package client.util;

import client.event.CoreListener;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.RepairContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class SplinePipeline extends GuiContainer implements CoreListener {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/anvil.png");
   public RepairContainer repairContainer;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public InventoryPlayer inventoryPlayer;

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count)) {
         this.updateState();
      } else {
         super.applyChar(character, count);
      }
   }

   @Override
   public void applyContainer(Container container, int count, int count2) {
   }

   @Override
   public void applyContainer(Container container, IInventory iInventory) {
   }

   @Override
   public void applyContainer(Container container, List list) {
      this.applyContainer(container, 0, container.getSlot(0).getStack());
   }

   @Override
   public void applyContainer(Container container, int count, ItemStack stack) {
      if (count == 0) {
         this.internalTexturesGuiIconsPngManagerAdapter.applyText(stack == null ? "" : stack.getString());
         this.internalTexturesGuiIconsPngManagerAdapter.setEnabled5(stack != null);
         if (stack != null) {
            this.updateState();
         }
      }
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      this.processAction(count3 + 59, count4 + 20, 0, this.count10 + (this.repairContainer.getSlot(0).isActive() ? 0 : 16), 110, 16);
      if ((this.repairContainer.getSlot(0).isActive() || this.repairContainer.getSlot(1).isActive()) && !this.repairContainer.getSlot(2).isActive()) {
         this.processAction(count3 + 99, count4 + 45, this.count9, 0, 28, 21);
      }
   }

   public SplinePipeline(InventoryPlayer inventoryPlayer2, World world) {
      super(new RepairContainer(inventoryPlayer2, world, Minecraft.getMinecraft().thePlayer));
      this.inventoryPlayer = inventoryPlayer2;
      this.repairContainer = (RepairContainer)this.container;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      GlStateManager.applyState3();
      GlStateManager.enableBlend();
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
   }

   @Override
   public void updateState2() {
      super.updateState2();
      Keyboard.enableRepeatEvents(true);
      int count = (this.width - this.count9) / 2;
      int count2 = (this.height - this.count10) / 2;
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(0, this.fontRenderer, count + 62, count2 + 24, 103, 12);
      this.internalTexturesGuiIconsPngManagerAdapter.setCount8(-1);
      this.internalTexturesGuiIconsPngManagerAdapter.setCount6(-1);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabled4(false);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(30);
      this.container.processAction(this);
      this.container.applyCoreListener(this);
   }

   @Override
   public void applyIndex(int count, int count2) {
      GlStateManager.applyState3();
      GlStateManager.enableBlend();
      this.fontRenderer.getIndex(StatCollector.getText("container.repair", new Object[0]), 60, 6, 4210752);
      if (this.repairContainer.count > 0) {
         int count3 = 8453920;
         boolean flag = true;
         String text = StatCollector.getText("container.repair.cost", new Object[]{this.repairContainer.count});
         if (this.repairContainer.count >= 40 && !this.mc.thePlayer.invulnerableManager.enabled2) {
            text = StatCollector.getText("container.repair.expensive", new Object[0]);
            count3 = 16736352;
         } else if (!this.repairContainer.getSlot(2).isActive()) {
            flag = false;
         } else if (!this.repairContainer.getSlot(2).processAction(this.inventoryPlayer.targetPlayer)) {
            count3 = 16736352;
         }

         if (flag) {
            int count4 = 0xFF000000 | (count3 & 16579836) >> 2 | count3 & 0xFF000000;
            int count5 = this.count9 - 8 - this.fontRenderer.getIndex(text);
            if (this.fontRenderer.isState()) {
               processAction(count5 - 3, 65, this.count9 - 7, 77, -16777216);
               processAction(count5 - 2, 66, this.count9 - 8, 76, -12895429);
            } else {
               this.fontRenderer.getIndex(text, count5, 68, count4);
               this.fontRenderer.getIndex(text, count5 + 1, 67, count4);
               this.fontRenderer.getIndex(text, count5 + 1, 68, count4);
            }

            this.fontRenderer.getIndex(text, count5, 67, count3);
         }
      }

      GlStateManager.applyState13();
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      Keyboard.enableRepeatEvents(false);
      this.container.processAction(this);
   }

   private void updateState() {
      String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      Slot slot = this.repairContainer.getSlot(0);
      if (slot != null && slot.isActive() && !slot.getStack().isActive() && labelText.equals(slot.getStack().getString())) {
         labelText = "";
      }

      this.repairContainer.applyText(labelText);
      this.mc
         .thePlayer
         .netHandlerPlayClient
         .handlePacket(new C17PacketCustomPayload("MC|ItemName", new PacketBuffer(Unpooled.buffer()).getPacketBuffer(labelText)));
   }
}
