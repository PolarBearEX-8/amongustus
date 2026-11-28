package net.minecraft.client.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
   private static final GameSettings.Options[] OPTIONS = new GameSettings.Options[]{GameSettings.Options.FOV};
   private final GameSettings game_settings_1;
   private GuiButton difficultyButton;
   private GuiLockIconButton lockButton;
   public String title = "Options";

   @Override
   public void initGui() {
      int n = 0;
      this.title = I18n.format("options.title", new Object[0]);

      for (GameSettings.Options options : OPTIONS) {
         if (options.getEnumFloat()) {
            this.buttonList
               .add(new GuiOptionSlider(options.returnEnumOrdinal(), this.width / 2 - 155 + n % 2 * 160, this.height / 6 - 12 + 24 * (n >> 1), options));
         } else {
            GuiOptionButton guiOptionButton = new GuiOptionButton(
               options.returnEnumOrdinal(),
               this.width / 2 - 155 + n % 2 * 160,
               this.height / 6 - 12 + 24 * (n >> 1),
               options,
               this.game_settings_1.getKeyBinding(options)
            );
            this.buttonList.add(guiOptionButton);
         }

         n++;
      }

      if (this.mc.theWorld != null) {
         EnumDifficulty difficulty = this.mc.theWorld.getDifficulty();
         this.difficultyButton = new GuiButton(108, this.width / 2 - 155 + n % 2 * 160, this.height / 6 - 12 + 24 * (n >> 1), 150, 20, this.getDifficultyText(difficulty));
         this.buttonList.add(this.difficultyButton);
         if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
            this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
            this.lockButton = new GuiLockIconButton(109, this.difficultyButton.xPosition + this.difficultyButton.getButtonWidth(), this.difficultyButton.yPosition);
            this.buttonList.add(this.lockButton);
            this.lockButton.setLocked(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
            this.lockButton.enabled = !this.lockButton.isLocked();
            this.difficultyButton.enabled = !this.lockButton.isLocked();
         } else {
            this.difficultyButton.enabled = false;
         }
      }

      this.buttonList.add(new GuiOptions.DefaultGuiButton(this, 8675309, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Super Secret Settings..."));
      this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation", new Object[0])));
      this.buttonList.add(new GuiButton(106, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
      this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.video", new Object[0])));
      this.buttonList.add(new GuiButton(100, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
      this.buttonList.add(new GuiButton(102, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.language", new Object[0])));
      this.buttonList.add(new GuiButton(103, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.chat.title", new Object[0])));
      this.buttonList.add(new GuiButton(105, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id < 100 && button instanceof GuiOptionButton) {
            GameSettings.Options options = ((GuiOptionButton)button).returnEnumOptions();
            this.game_settings_1.setOptionValue(options, 1);
            button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
         }

         if (button.id == 108) {
            this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.theWorld.getDifficulty().getDifficultyId() + 1));
            this.difficultyButton.displayString = this.getDifficultyText(this.mc.theWorld.getDifficulty());
         }

         if (button.id == 109) {
            Minecraft mc2 = this.mc;
            GuiYesNo guiYesNo = new GuiYesNo(
               this,
               new ChatComponentTranslation("difficulty.lock.title", new Object[0]).getFormattedText(),
               new ChatComponentTranslation(
                     "difficulty.lock.question",
                     new Object[]{new ChatComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0])}
                  )
                  .getFormattedText(),
               109
            );
            mc2.displayGuiScreen(guiYesNo);
         }

         if (button.id == 110) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
         }

         if (button.id == 8675309) {
            this.mc.entityRenderer.activateNextShader();
         }

         if (button.id == 101) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
         }

         if (button.id == 100) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
         }

         if (button.id == 102) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.mc.getLanguageManager()));
         }

         if (button.id == 103) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
         }

         if (button.id == 200) {
            this.mc.gameSettings.saveOptions();
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         }

         if (button.id == 105) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
         }

         if (button.id == 106) {
            this.mc.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
         }
      }
   }

   public GuiOptions(GuiScreen screen, GameSettings gameSettings) {
      super(screen);
      this.game_settings_1 = gameSettings;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      this.mc.displayGuiScreen(this);
      if (n == 109 && flag && this.mc.theWorld != null) {
         this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
         this.lockButton.setLocked(true);
         this.lockButton.enabled = false;
         this.difficultyButton.enabled = false;
      }
   }

   public String getDifficultyText(EnumDifficulty enumDifficulty) {
      ChatComponentText chatComponentText = new ChatComponentText("");
      chatComponentText.appendSibling(new ChatComponentTranslation("options.difficulty", new Object[0]));
      chatComponentText.appendText(": ");
      chatComponentText.appendSibling(new ChatComponentTranslation(enumDifficulty.getDifficultyResourceKey(), new Object[0]));
      return chatComponentText.getFormattedText();
   }



   public static class DefaultGuiButton extends GuiButton {
      public final GuiOptions optionsScreen;

      public DefaultGuiButton(GuiOptions guiOptions, int n, int n2, int n3, int n4, int n5, String str) {
         super(n, n2, n3, n4, n5, str);
         this.optionsScreen = guiOptions;
      }

      @Override
      public void playPressSound(SoundHandler soundHandler) {
         SoundEventAccessorComposite composite = soundHandler.getRandomSoundFromCategories(
            new SoundCategory[]{SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER}
         );
         if (composite != null) {
            soundHandler.playSound(PositionedSoundRecord.create(composite.getSoundEventLocation(), 0.5F));
         }
      }
   }
}
