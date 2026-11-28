package wdl.gui;

import wdl.api.ModInfo;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLExtensions extends GuiScreen {
   private int bottomLocation;
   private static final int TOP_HEIGHT = 23;
   private static final int MIDDLE_HEIGHT = 19;
   private static final int BOTTOM_HEIGHT = 32;
   private int selectedModIndex = -1;
   private final GuiScreen parent;
   private ExtensionModList list;
   private GuiWDLExtensionsModDetailList detailsList;
   private boolean dragging = false;
   private int dragOffset;

   public static void updateDetailsListFor(GuiWDLExtensions guiWDLExtensions, ModInfo modInfo) {
      guiWDLExtensions.updateDetailsList(modInfo);
   }

   public static FontRenderer getFontRendererForEntryHeight(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.fontRendererObj;
   }

   public static int getBottomLocation(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.bottomLocation;
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      if (this.dragging) {
         this.bottomLocation = mouseY - this.dragOffset;
      }

      if (this.bottomLocation < 31) {
         this.bottomLocation = 31;
      }

      if (this.bottomLocation > this.height - 32 - 8) {
         this.bottomLocation = this.height - 32 - 8;
      }
   }

   public static Minecraft getMinecraftForDetailList(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.mc;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.dragging = false;
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         if (!this.detailsList.mouseReleased(mouseX, mouseY, state)) {
            super.mouseReleased(mouseX, mouseY, state);
         }
      }
   }

   @Override
   public void initGui() {
      this.bottomLocation = this.height - 100;
      this.dragging = false;
      this.list = new ExtensionModList(this);
      this.detailsList = new GuiWDLExtensionsModDetailList(this);
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      if (this.bottomLocation < 56) {
         this.bottomLocation = 56;
      }

      if (this.bottomLocation > this.height - 19 - 32 - 33) {
         this.bottomLocation = this.height - 19 - 32 - 33;
      }

      this.list.drawScreen(mouseX, mouseY, partialTicks);
      this.detailsList.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.extensions.title", new Object[0]), this.width / 2, 8, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
      this.detailsList.handleMouseInput();
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }
   }

   private void updateDetailsList(ModInfo modInfo) {
      this.detailsList.clearLines();
      if (modInfo != null) {
         String info = modInfo.getInfo();
         this.detailsList.addLine(info);
      }
   }

   public GuiWDLExtensions(GuiScreen screen) {
      this.parent = screen;
   }

   public static FontRenderer getFontRendererForDetailsCaption(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.fontRendererObj;
   }

   public static int getSelectedModIndex(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.selectedModIndex;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseY > this.bottomLocation && mouseY < this.bottomLocation + 19) {
         this.dragging = true;
         this.dragOffset = mouseY - this.bottomLocation;
      } else if (!this.list.mouseClicked(mouseX, mouseY, mouseButton)) {
         if (!this.detailsList.mouseClicked(mouseX, mouseY, mouseButton)) {
            super.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }
   }

   public static FontRenderer getFontRendererForEntryDraw(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.fontRendererObj;
   }

   public static int setSelectedModIndex(GuiWDLExtensions guiWDLExtensions, int n) {
      return guiWDLExtensions.selectedModIndex = n;
   }

   public static Minecraft getMinecraftForModList(GuiWDLExtensions guiWDLExtensions) {
      return guiWDLExtensions.mc;
   }

}
