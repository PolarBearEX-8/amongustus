package net.minecraft.client.gui;

import java.util.Date;
import net.minecraft.util.EnumChatFormatting;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import net.augustus.modules.misc.ClientSettings;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiSelectWorld extends GuiScreen implements GuiYesNoCallback {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private final DateFormat dateFormat = new SimpleDateFormat();
   public String screenTitle = "Select world";
   private boolean selected;
   private int selectedIndex;
   private java.util.List saveList;
   private GuiSelectWorld.List availableWorlds;
   private String worldText;
   private String conversionText;
   private String[] gameModeNames = new String[4];
   private boolean confirmingDelete;
   private GuiButton deleteButton;
   private GuiButton selectButton;
   private GuiButton renameButton;
   private GuiButton recreateButton;

   public static String[] accessGameModeNames(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.gameModeNames;
   }

   public static GuiButton getDeleteButton(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.deleteButton;
   }

   public static GuiButton getRecreateButton(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.recreateButton;
   }

   public String getSaveName(int n) {
      String displayName = ((SaveFormatComparator)this.saveList.get(n)).getDisplayName();
      if (StringUtils.isEmpty(displayName)) {
         displayName = I18n.format("selectWorld.world", new Object[0]) + " " + (n + 1);
      }

      return displayName;
   }

   public static java.util.List accessSaveList(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.saveList;
   }

   public String getSaveFileName(int n) {
      return ((SaveFormatComparator)this.saveList.get(n)).getFileName();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.availableWorlds.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      ClientSettings.renderMainMenuAccountInfo();
   }

   public void addWorldSelectionButtons() {
      this.buttonList.add(this.selectButton = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, I18n.format("selectWorld.select", new Object[0])));
      this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, I18n.format("selectWorld.create", new Object[0])));
      this.buttonList.add(this.renameButton = new GuiButton(6, this.width / 2 - 154, this.height - 28, 72, 20, I18n.format("selectWorld.rename", new Object[0])));
      this.buttonList.add(this.deleteButton = new GuiButton(2, this.width / 2 - 76, this.height - 28, 72, 20, I18n.format("selectWorld.delete", new Object[0])));
      this.buttonList.add(this.recreateButton = new GuiButton(7, this.width / 2 + 4, this.height - 28, 72, 20, I18n.format("selectWorld.recreate", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 + 82, this.height - 28, 72, 20, I18n.format("gui.cancel", new Object[0])));
      this.selectButton.enabled = false;
      this.deleteButton.enabled = false;
      this.renameButton.enabled = false;
      this.recreateButton.enabled = false;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 2) {
            String saveName = this.getSaveName(this.selectedIndex);
            if (saveName != null) {
               this.confirmingDelete = true;
               GuiYesNo guiYesNo = makeDeleteWorldYesNo(this, saveName, this.selectedIndex);
               this.mc.displayGuiScreen(guiYesNo);
            }
         } else if (button.id == 1) {
            this.selectWorld(this.selectedIndex);
         } else if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiCreateWorld(this));
         } else if (button.id == 6) {
            this.mc.displayGuiScreen(new GuiRenameWorld(this, this.getSaveFileName(this.selectedIndex)));
         } else if (button.id == 0) {
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         } else if (button.id == 7) {
            GuiCreateWorld guiCreateWorld2 = new GuiCreateWorld(this);
            ISaveHandler saveLoader = this.mc.getSaveLoader().getSaveLoader(this.getSaveFileName(this.selectedIndex), false);
            WorldInfo worldInfo = saveLoader.loadWorldInfo();
            saveLoader.flush();
            guiCreateWorld2.recreateFromExistingWorld(worldInfo);
            this.mc.displayGuiScreen(guiCreateWorld2);
         } else {
            this.availableWorlds.actionPerformed(button);
         }
      }
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("selectWorld.title", new Object[0]);

      try {
         this.loadLevelList();
      } catch (AnvilConverterException e) {
         LOGGER2.error("Couldn't load level list", e);
         this.mc.displayGuiScreen((GuiScreen)new GuiErrorScreen("Unable to load worlds", e.getMessage()));
         return;
      }

      this.worldText = I18n.format("selectWorld.world", new Object[0]);
      this.conversionText = I18n.format("selectWorld.conversion", new Object[0]);
      this.gameModeNames[WorldSettings.GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
      this.gameModeNames[WorldSettings.GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
      this.gameModeNames[WorldSettings.GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
      this.gameModeNames[WorldSettings.GameType.SPECTATOR.getID()] = I18n.format("gameMode.spectator", new Object[0]);
      this.availableWorlds = new GuiSelectWorld.List(this, this.mc);
      this.availableWorlds.registerScrollButtons(4, 5);
      this.addWorldSelectionButtons();
   }

   public static DateFormat accessDateFormat(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.dateFormat;
   }

   public static GuiYesNo makeDeleteWorldYesNo(GuiYesNoCallback guiYesNoCallback, String str, int n) {
      String str2 = I18n.format("selectWorld.deleteQuestion", new Object[0]);
      String str3 = "'" + str + "' " + I18n.format("selectWorld.deleteWarning", new Object[0]);
      String str4 = I18n.format("selectWorld.deleteButton", new Object[0]);
      String str5 = I18n.format("gui.cancel", new Object[0]);
      return new GuiYesNo(guiYesNoCallback, str2, str3, str4, str5, n);
   }

   public static int assignSelectedIndex(GuiSelectWorld guiSelectWorld, int n) {
      return guiSelectWorld.selectedIndex = n;
   }

   public static GuiButton getRenameButton(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.renameButton;
   }

   public static String getConversionText(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.conversionText;
   }

   private void loadLevelList() throws AnvilConverterException {
      this.saveList = this.mc.getSaveLoader().getSaveList();
      Collections.sort(this.saveList);
      this.selectedIndex = -1;
   }

   public static GuiButton getSelectButton(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.selectButton;
   }

   public GuiSelectWorld(GuiScreen screen) {
      super(screen);
   }

   public void selectWorld(int n) {
      if (!this.selected) {
         this.selected = true;
         String saveFileName = this.getSaveFileName(n);
         if (saveFileName == null) {
            saveFileName = "World" + n;
         }

         String saveName = this.getSaveName(n);
         if (saveName == null) {
            saveName = "World" + n;
         }

         if (this.mc.getSaveLoader().canLoadWorld(saveFileName)) {
            WorldSettings worldSettings = (WorldSettings)null;
            this.mc.launchIntegratedServer(saveFileName, saveName, worldSettings);
         }
      }
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      if (this.confirmingDelete) {
         this.confirmingDelete = false;
         if (flag) {
            ISaveFormat saveLoader = this.mc.getSaveLoader();
            saveLoader.flushCache();
            saveLoader.deleteWorldDirectory(this.getSaveFileName(n));

            try {
               this.loadLevelList();
            } catch (AnvilConverterException e) {
               LOGGER2.error("Couldn't load level list", e);
            }
         }

         this.mc.displayGuiScreen(this);
      }
   }

   public static String getWorldText(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.worldText;
   }

   public static int accessSelectedIndex(GuiSelectWorld guiSelectWorld) {
      return guiSelectWorld.selectedIndex;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.availableWorlds.handleMouseInput();
   }



   public static class List extends GuiSlot {
      public final GuiSelectWorld selectWorldScreen;

      public List(GuiSelectWorld guiSelectWorld, Minecraft mc2) {
         super(mc2, guiSelectWorld.width, guiSelectWorld.height, 32, guiSelectWorld.height - 64, 36);
         this.selectWorldScreen = guiSelectWorld;
      }

      @Override
      public int getContentHeight() {
         return GuiSelectWorld.accessSaveList(this.selectWorldScreen).size() * 36;
      }

      @Override
      public void drawBackground() {
         this.selectWorldScreen.drawDefaultBackground();
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         SaveFormatComparator saveFormatComparator = (SaveFormatComparator)GuiSelectWorld.accessSaveList(this.selectWorldScreen).get(n);
         String displayName = saveFormatComparator.getDisplayName();
         if (StringUtils.isEmpty(displayName)) {
            displayName = GuiSelectWorld.getWorldText(this.selectWorldScreen) + " " + (n + 1);
         }

         String fileName = saveFormatComparator.getFileName();
         fileName = fileName + " (" + GuiSelectWorld.accessDateFormat(this.selectWorldScreen).format(new Date(saveFormatComparator.getLastTimePlayed()));
         fileName = fileName + ")";
         String str = "";
         if (saveFormatComparator.requiresConversion()) {
            str = GuiSelectWorld.getConversionText(this.selectWorldScreen) + " " + str;
         } else {
            str = GuiSelectWorld.accessGameModeNames(this.selectWorldScreen)[saveFormatComparator.getEnumGameType().getID()];
            if (saveFormatComparator.isHardcoreModeEnabled()) {
               str = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET;
            }

            if (saveFormatComparator.getCheatsEnabled()) {
               str = str + ", " + I18n.format("selectWorld.cheats", new Object[0]);
            }
         }

         this.selectWorldScreen.drawString(this.selectWorldScreen.fontRendererObj, displayName, n2 + 2, n3 + 1, 16777215);
         this.selectWorldScreen.drawString(this.selectWorldScreen.fontRendererObj, fileName, n2 + 2, n3 + 12, 8421504);
         this.selectWorldScreen.drawString(this.selectWorldScreen.fontRendererObj, str, n2 + 2, n3 + 12 + 10, 8421504);
      }

      @Override
      public boolean isSelected(int n) {
         return n == GuiSelectWorld.accessSelectedIndex(this.selectWorldScreen);
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
         GuiSelectWorld.assignSelectedIndex(this.selectWorldScreen, n);
         boolean flag2 = GuiSelectWorld.accessSelectedIndex(this.selectWorldScreen) >= 0 && GuiSelectWorld.accessSelectedIndex(this.selectWorldScreen) < this.getSize();
         GuiSelectWorld.getSelectButton(this.selectWorldScreen).enabled = flag2;
         GuiSelectWorld.getDeleteButton(this.selectWorldScreen).enabled = flag2;
         GuiSelectWorld.getRenameButton(this.selectWorldScreen).enabled = flag2;
         GuiSelectWorld.getRecreateButton(this.selectWorldScreen).enabled = flag2;
         if (flag && flag2) {
            this.selectWorldScreen.selectWorld(n);
         }
      }

      @Override
      public int getSize() {
         return GuiSelectWorld.accessSaveList(this.selectWorldScreen).size();
      }
   }
}
