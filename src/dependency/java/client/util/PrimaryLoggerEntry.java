package client.util;

import client.event.LocalListener;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.MinecraftAuxiliaryTexturesGuiIc;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimaryLoggerEntry extends GuiContainer {
   public static final Logger logger2 = LogManager.getLogger();
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/villager.png");
   public LocalListener localListener;
   public MinecraftAuxiliaryTexturesGuiIc minecraftAuxiliaryTexturesGuiIc;
   public MinecraftAuxiliaryTexturesGuiIc minecraftAuxiliaryTexturesGuiIc2;
   public int count12;
   public IChatComponent iChatComponent;

   @Override
   public void applyIndex(int count, int count2) {
      String string = this.iChatComponent.getString();
      this.fontRenderer.getIndex(string, this.count9 / 2 - this.fontRenderer.getIndex(string) / 2, 6, 4210752);
      this.fontRenderer.getIndex(StatCollector.getText("container.inventory", new Object[0]), 8, this.count10 - 96 + 2, 4210752);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = this.localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this.mc.thePlayer);
      if (auxiliaryArrayListAdapter != null && !auxiliaryArrayListAdapter.isEmpty()) {
         int count3 = (this.width - this.count9) / 2;
         int count4 = (this.height - this.count10) / 2;
         int count5 = this.count12;
         DistanceBuffer distanceBuffer = (DistanceBuffer)auxiliaryArrayListAdapter.get(count5);
         ItemStack stack = distanceBuffer.getItemStack();
         ItemStack stack2 = distanceBuffer.getItemStack2();
         ItemStack stack3 = distanceBuffer.getItemStack3();
         GlStateManager.pushMatrix();
         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.applyState3();
         GlStateManager.disableLighting();
         GlStateManager.applyState6();
         GlStateManager.applyState13();
         this.renderItem.factor = 100.0F;
         this.renderItem.renderItemIntoGUI(stack, count3 + 36, count4 + 24);
         this.renderItem.renderItemOverlays(this.fontRenderer, stack, count3 + 36, count4 + 24);
         if (stack2 != null) {
            this.renderItem.renderItemIntoGUI(stack2, count3 + 62, count4 + 24);
            this.renderItem.renderItemOverlays(this.fontRenderer, stack2, count3 + 62, count4 + 24);
         }

         this.renderItem.renderItemIntoGUI(stack3, count3 + 120, count4 + 24);
         this.renderItem.renderItemOverlays(this.fontRenderer, stack3, count3 + 120, count4 + 24);
         this.renderItem.factor = 0.0F;
         GlStateManager.applyState3();
         if (this.checkIndex(36, 24, 16, 16, count, count2) && stack != null) {
            this.applyItemStack(stack, count, count2);
         } else if (stack2 != null && this.checkIndex(62, 24, 16, 16, count, count2) && stack2 != null) {
            this.applyItemStack(stack2, count, count2);
         } else if (stack3 != null && this.checkIndex(120, 24, 16, 16, count, count2) && stack3 != null) {
            this.applyItemStack(stack3, count, count2);
         } else if (distanceBuffer.isActive() && (this.checkIndex(83, 21, 28, 21, count, count2) || this.checkIndex(83, 51, 28, 21, count, count2))) {
            this.applyText(StatCollector.getText("merchant.deprecated", new Object[0]), count, count2);
         }

         GlStateManager.popMatrix();
         GlStateManager.applyState13();
         GlStateManager.disableDepth();
         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
      AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = this.localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this.mc.thePlayer);
      if (auxiliaryArrayListAdapter != null) {
         this.minecraftAuxiliaryTexturesGuiIc.enabled3 = this.count12 < auxiliaryArrayListAdapter.size() - 1;
         this.minecraftAuxiliaryTexturesGuiIc2.enabled3 = this.count12 > 0;
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      int count = (this.width - this.count9) / 2;
      int count2 = (this.height - this.count10) / 2;
      this.list.add(this.minecraftAuxiliaryTexturesGuiIc = new MinecraftAuxiliaryTexturesGuiIc(1, count + 120 + 27, count2 + 24 - 1, true));
      this.list.add(this.minecraftAuxiliaryTexturesGuiIc2 = new MinecraftAuxiliaryTexturesGuiIc(2, count + 36 - 19, count2 + 24 - 1, false));
      this.minecraftAuxiliaryTexturesGuiIc.enabled3 = false;
      this.minecraftAuxiliaryTexturesGuiIc2.enabled3 = false;
   }

   public static ResourceLocation getResourceLocation5() {
      return resourceLocation5;
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = this.localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this.mc.thePlayer);
      if (auxiliaryArrayListAdapter != null && !auxiliaryArrayListAdapter.isEmpty()) {
         int count5 = this.count12;
         if (count5 < 0 || count5 >= auxiliaryArrayListAdapter.size()) {
            return;
         }

         if (((DistanceBuffer)auxiliaryArrayListAdapter.get(count5)).isActive()) {
            this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.applyState3();
            this.processAction(this.indexOffset1 + 83, this.indexOffset2 + 21, 212, 0, 28, 21);
            this.processAction(this.indexOffset1 + 83, this.indexOffset2 + 51, 212, 0, 28, 21);
         }
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      boolean flag = false;
      if (auxiliaryTexturesGuiIconsPngManagerAdapter == this.minecraftAuxiliaryTexturesGuiIc) {
         this.count12++;
         AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = this.localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this.mc.thePlayer);
         if (auxiliaryArrayListAdapter != null && this.count12 >= auxiliaryArrayListAdapter.size()) {
            this.count12 = auxiliaryArrayListAdapter.size() - 1;
         }

         flag = true;
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter == this.minecraftAuxiliaryTexturesGuiIc2) {
         this.count12--;
         if (this.count12 < 0) {
            this.count12 = 0;
         }

         flag = true;
      }

      if (flag) {
         ((ExtendedContainerAdapter)this.container).applyIndex(this.count12);
         PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
         packet.writeInt(this.count12);
         this.mc.getNetHandlerPlayClient().handlePacket(new C17PacketCustomPayload("MC|TrSel", packet));
      }
   }

   public LocalListener getLocalListener() {
      return this.localListener;
   }

   public PrimaryLoggerEntry(InventoryPlayer inventoryPlayer, LocalListener localListener2, World world) {
      super(new ExtendedContainerAdapter(inventoryPlayer, localListener2, world));
      this.localListener = localListener2;
      this.iChatComponent = localListener2.getChatComponent();
   }
}
