package wdl.gui;

import wdl.EntityUtils;
import wdl.WDL;
import wdl.WDLPluginChannels;
import java.io.IOException;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class GuiWDLEntityRangePresets extends GuiScreen implements GuiYesNoCallback {
   private final GuiScreen parent;
   private GuiButton vanillaButton;
   private GuiButton spigotButton;
   private GuiButton serverButton;
   private GuiButton cancelButton;

   public GuiWDLEntityRangePresets(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id < 3) {
            String str = I18n.format("wdl.gui.rangePresets.upperWarning", new Object[0]);
            String str2;
            if (button.id == 0) {
               str2 = I18n.format("wdl.gui.rangePresets.vanilla.warning", new Object[0]);
            } else if (button.id == 1) {
               str2 = I18n.format("wdl.gui.rangePresets.spigot.warning", new Object[0]);
            } else {
               if (button.id != 2) {
                  throw new Error("Button.id should never be negative.");
               }

               str2 = I18n.format("wdl.gui.rangePresets.server.warning", new Object[0]);
            }

            this.mc.displayGuiScreen(new GuiYesNo(this, str, str2, button.id));
         }

         if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   @Override
   public void initGui() {
      int n = this.height / 4;
      this.vanillaButton = new GuiButton(0, this.width / 2 - 100, n, I18n.format("wdl.gui.rangePresets.vanilla", new Object[0]));
      n += 22;
      this.spigotButton = new GuiButton(1, this.width / 2 - 100, n, I18n.format("wdl.gui.rangePresets.spigot", new Object[0]));
      n += 22;
      this.serverButton = new GuiButton(2, this.width / 2 - 100, n, I18n.format("wdl.gui.rangePresets.server", new Object[0]));
      this.serverButton.enabled = WDLPluginChannels.hasServerEntityRange();
      this.buttonList.add(this.vanillaButton);
      this.buttonList.add(this.spigotButton);
      this.buttonList.add(this.serverButton);
      n += 28;
      this.cancelButton = new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.cancel", new Object[0]));
      this.buttonList.add(this.cancelButton);
   }

   @Override
   public void onGuiClosed() {
      WDL.saveProps();
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      if (flag) {
         Set entityTypes = EntityUtils.getEntityTypes();
         if (n == 0) {
            for (String str : (Iterable<String>)(entityTypes)) {
               WDL.worldProps.setProperty("Entity." + str + ".TrackDistance", Integer.toString(EntityUtils.getDefaultVanillaEntityRange2(str)));
            }
         } else if (n == 1) {
            for (String str2 : (Iterable<String>)(entityTypes)) {
               Class clazz = (Class)EntityUtils.stringToClassMapping.get(str2);
               if (clazz != null) {
                  WDL.worldProps.setProperty("Entity." + str2 + ".TrackDistance", Integer.toString(EntityUtils.getDefaultSpigotEntityRange(clazz)));
               }
            }
         } else if (n == 2) {
            for (String str3 : (Iterable<String>)(entityTypes)) {
               WDL.worldProps.setProperty("Entity." + str3 + ".TrackDistance", Integer.toString(WDLPluginChannels.getEntityRange(str3)));
            }
         }
      }

      GuiScreen parent2 = this.parent;
      this.mc.displayGuiScreen(parent2);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawListBackground(23, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.rangePresets.title", new Object[0]), this.width / 2, 8, 16777215);
      String str = null;
      if (this.vanillaButton.isMouseOver()) {
         str = I18n.format("wdl.gui.rangePresets.vanilla.description", new Object[0]);
      } else if (this.spigotButton.isMouseOver()) {
         str = I18n.format("wdl.gui.rangePresets.spigot.description", new Object[0]);
      } else if (this.serverButton.isMouseOver()) {
         str = I18n.format("wdl.gui.rangePresets.server.description", new Object[0]) + "\n\n";
         if (this.serverButton.enabled) {
            str = str + I18n.format("wdl.gui.rangePresets.server.installed", new Object[0]);
         } else {
            str = str + I18n.format("wdl.gui.rangePresets.server.notInstalled", new Object[0]);
         }
      } else if (this.cancelButton.isMouseOver()) {
         str = I18n.format("wdl.gui.rangePresets.cancel.description", new Object[0]);
      }

      if (str != null) {
         Utils.drawGuiInfoBox(str, this.width, this.height, 48);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

}
