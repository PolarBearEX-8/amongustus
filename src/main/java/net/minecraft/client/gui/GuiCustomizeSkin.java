package net.minecraft.client.gui;

import java.io.IOException;
import net.optifine.gui.GuiButtonOF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.optifine.gui.GuiScreenCapeOF;

public class GuiCustomizeSkin extends GuiScreen {
   private String title;

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 210) {
            this.mc.displayGuiScreen((GuiScreen)new GuiScreenCapeOF(this));
         } else if (button.id == 200) {
            this.mc.gameSettings.saveOptions();
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         } else if (button instanceof GuiCustomizeSkin.ButtonPart) {
            EnumPlayerModelParts enumPlayerModelParts = GuiCustomizeSkin.ButtonPart.accessPlayerModelParts((GuiCustomizeSkin.ButtonPart)button);
            this.mc.gameSettings.switchModelPartEnabled(enumPlayerModelParts);
            button.displayString = this.getModelPartButtonText(enumPlayerModelParts);
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public static String invokeGetModelPartButtonText(GuiCustomizeSkin guiCustomizeSkin, EnumPlayerModelParts enumPlayerModelParts) {
      return guiCustomizeSkin.getModelPartButtonText(enumPlayerModelParts);
   }

   private String getModelPartButtonText(EnumPlayerModelParts enumPlayerModelParts) {
      String str;
      if (this.mc.gameSettings.getModelParts().contains(enumPlayerModelParts)) {
         str = I18n.format("options.on", new Object[0]);
      } else {
         str = I18n.format("options.off", new Object[0]);
      }

      return enumPlayerModelParts.getName().getFormattedText() + ": " + str;
   }

   @Override
   public void initGui() {
      int n = 0;
      this.title = I18n.format("options.skinCustomisation.title", new Object[0]);

      for (EnumPlayerModelParts enumPlayerModelParts : EnumPlayerModelParts.values()) {
         this.buttonList
            .add(
               new GuiCustomizeSkin.ButtonPart(
                  this, enumPlayerModelParts.getPartId(), this.width / 2 - 155 + n % 2 * 160, this.height / 6 + 24 * (n >> 1), 150, 20, enumPlayerModelParts, null
               )
            );
         n++;
      }

      if (n % 2 == 1) {
         n++;
      }

      this.buttonList
         .add(new GuiButtonOF(210, this.width / 2 - 100, this.height / 6 + 24 * (n >> 1), I18n.format("of.options.skinCustomisation.ofCape", new Object[0])));
      n += 2;
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 24 * (n >> 1), I18n.format("gui.done", new Object[0])));
   }

   public GuiCustomizeSkin(GuiScreen screen) {
      super(screen);
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }



   public static class InnerEntry {
   }

   public static class ButtonPart extends GuiButton {
      private final EnumPlayerModelParts playerModelParts;
      public final GuiCustomizeSkin parentScreen;

      public ButtonPart(
         GuiCustomizeSkin guiCustomizeSkin, int n, int n2, int n3, int n4, int n5, EnumPlayerModelParts enumPlayerModelParts, GuiCustomizeSkin.InnerEntry obj
      ) {
         this(guiCustomizeSkin, n, n2, n3, n4, n5, enumPlayerModelParts);
      }

      private ButtonPart(GuiCustomizeSkin guiCustomizeSkin, int n, int n2, int n3, int n4, int n5, EnumPlayerModelParts enumPlayerModelParts) {
         super(n, n2, n3, n4, n5, GuiCustomizeSkin.invokeGetModelPartButtonText(guiCustomizeSkin, enumPlayerModelParts));
         this.parentScreen = guiCustomizeSkin;
         this.playerModelParts = enumPlayerModelParts;
      }

      public static EnumPlayerModelParts accessPlayerModelParts(ButtonPart buttonPart) {
         return buttonPart.playerModelParts;
      }
   }
}
