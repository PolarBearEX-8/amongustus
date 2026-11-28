package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class ScreenChatOptions extends GuiScreen {
   private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[]{
      GameSettings.Options.CHAT_VISIBILITY,
      GameSettings.Options.CHAT_COLOR,
      GameSettings.Options.CHAT_LINKS,
      GameSettings.Options.CHAT_OPACITY,
      GameSettings.Options.CHAT_LINKS_PROMPT,
      GameSettings.Options.CHAT_SCALE,
      GameSettings.Options.CHAT_HEIGHT_FOCUSED,
      GameSettings.Options.CHAT_HEIGHT_UNFOCUSED,
      GameSettings.Options.CHAT_WIDTH,
      GameSettings.Options.REDUCED_DEBUG_INFO
   };
   private final GameSettings game_settings;
   private String chatTitle;

   @Override
   public void initGui() {
      int n = 0;
      this.chatTitle = I18n.format("options.chat.title", new Object[0]);

      for (GameSettings.Options options : CHAT_OPTIONS) {
         if (options.getEnumFloat()) {
            this.buttonList.add(new GuiOptionSlider(options.returnEnumOrdinal(), this.width / 2 - 155 + n % 2 * 160, this.height / 6 + 24 * (n >> 1), options));
         } else {
            this.buttonList
               .add(
                  new GuiOptionButton(
                     options.returnEnumOrdinal(),
                     this.width / 2 - 155 + n % 2 * 160,
                     this.height / 6 + 24 * (n >> 1),
                     options,
                     this.game_settings.getKeyBinding(options)
                  )
               );
         }

         n++;
      }

      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 120, I18n.format("gui.done", new Object[0])));
   }

   public ScreenChatOptions(GuiScreen screen, GameSettings gameSettings) {
      super(screen);
      this.game_settings = gameSettings;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id < 100 && button instanceof GuiOptionButton) {
            this.game_settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
            button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
         }

         if (button.id == 200) {
            this.mc.gameSettings.saveOptions();
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.chatTitle, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

}
