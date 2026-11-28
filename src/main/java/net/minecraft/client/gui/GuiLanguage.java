package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import java.io.IOException;
import net.augustus.Augustus;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;

public class GuiLanguage extends GuiScreen {
   private GuiLanguage.List list;
   private final GameSettings game_settings_3;
   private final LanguageManager languageManager;
   private GuiOptionButton forceUnicodeFontBtn;
   private GuiOptionButton confirmSettingsBtn;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, I18n.format("options.language", new Object[0]), this.width / 2, 16, 16777215);
      drawCenteredString(this.fontRendererObj, "(" + I18n.format("options.languageWarning", new Object[0]) + ")", this.width / 2, this.height - 56, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public static GameSettings accessGame_settings_3(GuiLanguage guiLanguage) {
      return guiLanguage.game_settings_3;
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
      this.mc.ingameGUI.getChatGUI().refreshChat();
   }

   public static LanguageManager accessLanguageManager(GuiLanguage guiLanguage) {
      return guiLanguage.languageManager;
   }

   public static GuiOptionButton getConfirmSettingsBtn(GuiLanguage guiLanguage) {
      return guiLanguage.confirmSettingsBtn;
   }

   public GuiLanguage(GuiScreen screen, GameSettings gameSettings, LanguageManager languageManager2) {
      super(screen);
      this.game_settings_3 = gameSettings;
      this.languageManager = languageManager2;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void initGui() {
      this.buttonList
         .add(
            this.forceUnicodeFontBtn = new GuiOptionButton(
               100, this.width / 2 - 155, this.height - 38, GameSettings.Options.FORCE_UNICODE_FONT, this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT)
            )
         );
      this.buttonList
         .add(this.confirmSettingsBtn = new GuiOptionButton(6, this.width / 2 - 155 + 160, this.height - 38, I18n.format("gui.done", new Object[0])));
      this.list = new GuiLanguage.List(this, this.mc);
      this.list.registerScrollButtons(7, 8);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         switch (button.id) {
            case 5:
               break;
            case 6:
               GuiScreen parentScreen2 = this.parentScreen;
               this.mc.displayGuiScreen(parentScreen2);
               break;
            case 100:
               if (button instanceof GuiOptionButton) {
                  this.game_settings_3.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                  Augustus.getInstance().getModuleManager().arrayList.recalculateModuleDimensions();
                  button.displayString = this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
                  ScaledResolution sr = new ScaledResolution(this.mc);
                  int scaledWidth = sr.getScaledWidth();
                  int scaledHeight = sr.getScaledHeight();
                  this.setWorldAndResolution(this.mc, scaledWidth, scaledHeight);
               }
               break;
            default:
               this.list.actionPerformed(button);
         }
      }
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   public static GuiOptionButton getForceUnicodeFontBtn(GuiLanguage guiLanguage) {
      return guiLanguage.forceUnicodeFontBtn;
   }



   public static class List extends GuiSlot {
      private final java.util.List langCodeList;
      private final Map languageMap;
      public final GuiLanguage languageScreen;

      public List(GuiLanguage guiLanguage, Minecraft mc2) {
         super(mc2, guiLanguage.width, guiLanguage.height, 32, guiLanguage.height - 65 + 4, 18);
         this.languageScreen = guiLanguage;
         this.langCodeList = Lists.newArrayList();
         this.languageMap = Maps.newHashMap();

         for (Language language : (Iterable<Language>)(GuiLanguage.accessLanguageManager(guiLanguage).getLanguages())) {
            this.languageMap.put(language.getLanguageCode(), language);
            this.langCodeList.add(language.getLanguageCode());
         }
      }

      @Override
      public void drawBackground() {
         this.languageScreen.drawDefaultBackground();
      }

      @Override
      public boolean isSelected(int n) {
         return ((String)this.langCodeList.get(n)).equals(GuiLanguage.accessLanguageManager(this.languageScreen).getCurrentLanguage().getLanguageCode());
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         this.languageScreen.fontRendererObj.setBidiFlag(true);
         GuiLanguage.drawCenteredString(
            this.languageScreen.fontRendererObj, ((Language)this.languageMap.get(this.langCodeList.get(n))).toString(), this.width / 2, n3 + 1, 16777215
         );
         this.languageScreen.fontRendererObj.setBidiFlag(GuiLanguage.accessLanguageManager(this.languageScreen).getCurrentLanguage().isBidirectional());
      }

      @Override
      public int getContentHeight() {
         return this.getSize() * 18;
      }

      @Override
      public int getSize() {
         return this.langCodeList.size();
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
         Language language = (Language)this.languageMap.get(this.langCodeList.get(n));
         GuiLanguage.accessLanguageManager(this.languageScreen).setCurrentLanguage(language);
         GuiLanguage.accessGame_settings_3(this.languageScreen).language = language.getLanguageCode();
         this.mc.refreshResources();
         this.languageScreen
            .fontRendererObj
            .setUnicodeFlag(GuiLanguage.accessLanguageManager(this.languageScreen).isCurrentLocaleUnicode() || GuiLanguage.accessGame_settings_3(this.languageScreen).forceUnicodeFont);
         this.languageScreen.fontRendererObj.setBidiFlag(GuiLanguage.accessLanguageManager(this.languageScreen).isCurrentLanguageBidirectional());
         GuiLanguage.getConfirmSettingsBtn(this.languageScreen).displayString = I18n.format("gui.done", new Object[0]);
         GuiLanguage.getForceUnicodeFontBtn(this.languageScreen).displayString = GuiLanguage.accessGame_settings_3(this.languageScreen).getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
         GuiLanguage.accessGame_settings_3(this.languageScreen).saveOptions();
      }
   }
}
