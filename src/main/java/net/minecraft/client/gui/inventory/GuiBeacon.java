package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiBeacon extends GuiContainer {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private static final ResourceLocation beaconGuiTextures = new ResourceLocation("textures/gui/container/beacon.png");
   public IInventory tileBeacon;
   private GuiBeacon.ConfirmButton beaconConfirmButton;
   private boolean buttonsNotDrawn;

   public static ResourceLocation accessBeaconGuiTextures() {
      return beaconGuiTextures;
   }

   public static void drawCancelButtonTooltip(GuiBeacon guiBeacon, String str, int n, int n2) {
      guiBeacon.drawCreativeTabHoveringText(str, n, n2);
   }

   public GuiBeacon(InventoryPlayer inventoryPlayer, IInventory inventory) {
      super(new ContainerBeacon(inventoryPlayer, inventory));
      this.tileBeacon = inventory;
      this.xSize = 230;
      this.ySize = 219;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == -2) {
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
      } else if (button.id == -1) {
         String str = "MC|Beacon";
         PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
         buf.writeInt(this.tileBeacon.getField(1));
         buf.writeInt(this.tileBeacon.getField(2));
         this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(str, buf));
         GuiScreen screen2 = (GuiScreen)null;
         this.mc.displayGuiScreen(screen2);
      } else if (button instanceof GuiBeacon.PowerButton) {
         if (((GuiBeacon.PowerButton)button).isSelected()) {
            return;
         }

         int id = button.id;
         int n = id & 0xFF;
         if ((id >> 8) < 3) {
            this.tileBeacon.setField(1, n);
         } else {
            this.tileBeacon.setField(2, n);
         }

         this.buttonList.clear();
         this.initGui();
         this.updateScreen();
      }
   }

   public static void drawConfirmButtonTooltip(GuiBeacon guiBeacon, String str, int n, int n2) {
      guiBeacon.drawCreativeTabHoveringText(str, n, n2);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(beaconGuiTextures);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      this.itemRender.zLevel = 100.0F;
      this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Items.emerald), n3 + 42, n4 + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Items.diamond), n3 + 42 + 22, n4 + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Items.gold_ingot), n3 + 42 + 44, n4 + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Items.iron_ingot), n3 + 42 + 66, n4 + 109);
      this.itemRender.zLevel = 0.0F;
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      RenderHelper.disableStandardItemLighting();
      drawCenteredString(this.fontRendererObj, I18n.format("tile.beacon.primary", new Object[0]), 62, 10, 14737632);
      drawCenteredString(this.fontRendererObj, I18n.format("tile.beacon.secondary", new Object[0]), 169, 10, 14737632);

      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         if (button.isMouseOver()) {
            button.drawButtonForegroundLayer(n - this.guiLeft, n2 - this.guiTop);
            break;
         }
      }

      RenderHelper.enableGUIStandardItemLighting();
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      int field = this.tileBeacon.getField(0);
      int field2 = this.tileBeacon.getField(1);
      int field3 = this.tileBeacon.getField(2);
      if (this.buttonsNotDrawn && field >= 0) {
         this.buttonsNotDrawn = false;

         for (int i = 0; i <= 2; i++) {
            int length = TileEntityBeacon.effectsList[i].length;
            int n = length * 22 + (length - 1) * 2;

            for (int j = 0; j < length; j++) {
               int id = TileEntityBeacon.effectsList[i][j].id;
               GuiBeacon.PowerButton powerButton = new GuiBeacon.PowerButton(
                  this, i << 8 | id, this.guiLeft + 76 + j * 24 - n / 2, this.guiTop + 22 + i * 25, id, i
               );
               this.buttonList.add(powerButton);
               if (i >= field) {
                  powerButton.enabled = false;
               } else if (id == field2) {
                  powerButton.setSelected(true);
               }
            }
         }

         byte b = 3;
         int n2 = TileEntityBeacon.effectsList[b].length + 1;
         int n3 = n2 * 22 + (n2 - 1) * 2;

         for (int k = 0; k < n2 - 1; k++) {
            int id2 = TileEntityBeacon.effectsList[b][k].id;
            GuiBeacon.PowerButton powerButton2 = new GuiBeacon.PowerButton(
               this, b << 8 | id2, this.guiLeft + 167 + k * 24 - n3 / 2, this.guiTop + 47, id2, b
            );
            this.buttonList.add(powerButton2);
            if (b >= field) {
               powerButton2.enabled = false;
            } else if (id2 == field3) {
               powerButton2.setSelected(true);
            }
         }

         if (field2 > 0) {
            GuiBeacon.PowerButton powerButton3 = new GuiBeacon.PowerButton(
               this, b << 8 | field2, this.guiLeft + 167 + (n2 - 1) * 24 - n3 / 2, this.guiTop + 47, field2, b
            );
            this.buttonList.add(powerButton3);
            if (b >= field) {
               powerButton3.enabled = false;
            } else if (field2 == field3) {
               powerButton3.setSelected(true);
            }
         }
      }

      this.beaconConfirmButton.enabled = this.tileBeacon.getStackInSlot(0) != null && field2 > 0;
   }

   public static void drawPowerButtonTooltip(GuiBeacon guiBeacon, String str, int n, int n2) {
      guiBeacon.drawCreativeTabHoveringText(str, n, n2);
   }

   @Override
   public void initGui() {
      super.initGui();
      this.buttonList.add(this.beaconConfirmButton = new GuiBeacon.ConfirmButton(this, -1, this.guiLeft + 164, this.guiTop + 107));
      this.buttonList.add(new GuiBeacon.CancelButton(this, -2, this.guiLeft + 190, this.guiTop + 107));
      this.buttonsNotDrawn = true;
      this.beaconConfirmButton.enabled = false;
   }



   public static class Button extends GuiButton {
      private final ResourceLocation iconTexture;
      private final int iconX;
      private final int iconY;
      private boolean selected;

      @Override
      public void drawButton(Minecraft mc, int n, int n2) {
         if (this.visible) {
            mc.getTextureManager().bindTexture(GuiBeacon.accessBeaconGuiTextures());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
            int n3 = 0;
            if (!this.enabled) {
               n3 += this.width * 2;
            } else if (this.selected) {
               n3 += this.width * 1;
            } else if (this.hovered) {
               n3 += this.width * 3;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, n3, (short)219, this.width, this.height);
            if (!GuiBeacon.accessBeaconGuiTextures().equals(this.iconTexture)) {
               mc.getTextureManager().bindTexture(this.iconTexture);
            }

            this.drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, this.iconX, this.iconY, 18, 18);
         }
      }

      public void setSelected(boolean flag) {
         this.selected = flag;
      }

      public Button(int n, int n2, int n3, ResourceLocation location, int n4, int n5) {
         super(n, n2, n3, 22, 22, "");
         this.iconTexture = location;
         this.iconX = n4;
         this.iconY = n5;
      }

      public boolean isSelected() {
         return this.selected;
      }
   }

   public static class PowerButton extends GuiBeacon.Button {
      private final int effectId;
      private final int tier;
      public final GuiBeacon beaconScreen;

      @Override
      public void drawButtonForegroundLayer(int n, int n2) {
         String str = I18n.format(Potion.potionTypes[this.effectId].getName(), new Object[0]);
         if (this.tier >= 3 && this.effectId != Potion.regeneration.id) {
            str = str + " II";
         }

         GuiBeacon.drawPowerButtonTooltip(this.beaconScreen, str, n, n2);
      }

      public PowerButton(GuiBeacon guiBeacon, int n, int n2, int n3, int n4, int n5) {
         super(
            n,
            n2,
            n3,
            GuiContainer.inventoryBackground,
            0 + Potion.potionTypes[n4].getStatusIconIndex() % 8 * 18,
            198 + Potion.potionTypes[n4].getStatusIconIndex() / 8 * 18
         );
         this.beaconScreen = guiBeacon;
         this.effectId = n4;
         this.tier = n5;
      }
   }

   public static class CancelButton extends GuiBeacon.Button {
      public final GuiBeacon beaconScreen;

      @Override
      public void drawButtonForegroundLayer(int n, int n2) {
         GuiBeacon.drawCancelButtonTooltip(this.beaconScreen, I18n.format("gui.cancel", new Object[0]), n, n2);
      }

      public CancelButton(GuiBeacon guiBeacon, int n, int n2, int n3) {
         super(n, n2, n3, GuiBeacon.accessBeaconGuiTextures(), 112, 220);
         this.beaconScreen = guiBeacon;
      }
   }

   public static class ConfirmButton extends GuiBeacon.Button {
      public final GuiBeacon beaconScreen;

      public ConfirmButton(GuiBeacon guiBeacon, int n, int n2, int n3) {
         super(n, n2, n3, GuiBeacon.accessBeaconGuiTextures(), 90, 220);
         this.beaconScreen = guiBeacon;
      }

      @Override
      public void drawButtonForegroundLayer(int n, int n2) {
         GuiBeacon.drawConfirmButtonTooltip(this.beaconScreen, I18n.format("gui.done", new Object[0]), n, n2);
      }
   }
}
