package net.augustus.ui.config;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.clickgui.ClickGuiWindow;
import net.augustus.clickgui.CreateConfigScreen;
import net.augustus.config.ModuleConfigStorage;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.options.NotificationCategoryOption;
import net.augustus.ui.notifications.NotificationLevel;
import net.augustus.ui.notifications.NotificationManager;
import net.augustus.ui.widgets.SmoothScrollBar;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.utils.file.DirectoryWatchService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ConfigSystemPanel extends ClickGuiWindow {
   private static final String CONFIGS_DIRECTORY = ClientPaths.CLIENT_DIR + File.separator + "configs";
   private static final float MY_CONFIGS_HEADER_WIDTH = FontManager.consolas32.getStringWidth("Local Configs");
   private static final float CREATE_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Create");
   private static final float LOAD_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Load");
   private static final float SAVE_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Save");
   private static final float FOLDER_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Folder");
   private static final float REFRESH_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Refresh");
   private static final float DELETE_BUTTON_WIDTH = FontManager.consolas32.getStringWidth("Delete");
   private static final float CREATE_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Create");
   private static final float LOAD_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Load");
   private static final float SAVE_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Save");
   private static final float FOLDER_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Folder");
   private static final float REFRESH_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Refresh");
   private static final float DELETE_BUTTON_SMALL_WIDTH = FontManager.consolas30.getStringWidth("Delete");

   public final CopyOnWriteArrayList<AbstractConfigListItem> userConfigs = new CopyOnWriteArrayList<>();
   private final SmoothScrollBar userConfigsScrollBar = new SmoothScrollBar();
   private final CustomFontUtil itemFont = FontManager.consolas30;
   private final CustomFontUtil headerFont = FontManager.consolas32;
   private int userConfigsScrollOffset = 0;
   private AbstractConfigListItem selectedConfig;
   public CompletableFuture refreshFuture;
   private boolean initialLoadTriggered = false;

   public ConfigSystemPanel(String title, int x, int y, int width, int height) {
      super(title, x, y, width, height);
      DirectoryWatchService.watch(CONFIGS_DIRECTORY)
         .onCreate(path -> this.onLocalConfigFileModified(((Path)path).toFile()))
         .onDelete(path -> this.onLocalConfigFileDeleted((Path)path))
         .register();
   }

   private void scanLocalConfigDirectory() {
      this.userConfigs.clear();
      try {
         File[] files = new File(CONFIGS_DIRECTORY).listFiles(file -> !file.isDirectory() && file.getName().endsWith(".json"));
         if (files != null) {
            for (File file : files) {
               this.loadLocalConfigEntry(file);
            }
         }
      } catch (Exception ignored) {
      }
      this.sortUserConfigs();
   }

   private void onLocalConfigFileModified(File file) {
      this.removeConfigEntryForFile(file);
      this.loadLocalConfigEntry(file);
      this.sortUserConfigs();
   }

   private void onLocalConfigFileDeleted(Path path) {
      this.removeConfigEntryForFile(path.toFile());
   }

   private void sortUserConfigs() {
      this.userConfigs.sort(Comparator.comparing(item -> item.getConfigName().toLowerCase()));
   }

   private void removeConfigEntryForFile(File file) {
      String fileName = file.getName();
      int dotIdx = fileName.lastIndexOf('.');
      String name = dotIdx >= 0 ? fileName.substring(0, dotIdx) : fileName;
      this.userConfigs.stream()
         .filter(item -> item.getConfigName().equals(name))
         .findFirst()
         .ifPresent(this.userConfigs::remove);
      this.sortUserConfigs();
   }

   private void loadLocalConfigEntry(File file) {
      String fileName = file.getName();
      if (!fileName.endsWith(".json")) {
         return;
      }
      try {
         String configName = fileName.substring(0, fileName.length() - 5);
         String[] header = Augustus.getInstance().getConfigManager().moduleConfigStorage.getConfigHeaderInfo(configName, "configs");
         String date = header != null && header.length > 1 ? header[1] : "";
         String time = header != null && header.length > 2 ? header[2] : "";
         String editableStr = header != null && header.length == 5 ? header[3] : "true";
         String author = header != null && header.length >= 4 ? (header.length == 5 ? header[4] : header[3]) : "";
         this.userConfigs.stream().filter(item -> item.getConfigName().equals(configName)).findAny().ifPresent(this.userConfigs::remove);
         this.userConfigs.add(new UserConfigListItem(configName, author, date, time, Boolean.parseBoolean(editableStr)));
      } catch (Exception e) {
         e.printStackTrace();
         if (mc.theWorld != null) {
            notifyConfigStatus("Failed to load config: " + file.getName(), NotificationLevel.ERROR);
         }
      }
   }

   public static void notifyConfigStatus(String message, NotificationLevel level) {
      if (mc.theWorld != null) {
         ChatUtil.sendChat(message);
         if (Augustus.getInstance().getModuleManager().notifications.isToggled() && NotificationCategoryOption.CONFIG_SYSTEM.isSelected()) {
            NotificationManager.pushNotification("Configsystem", message, level);
         }
      }
   }

   @Override
   public void drawWindow(int mouseX, int mouseY, float partialTicks) {
      super.drawWindow(mouseX, mouseY, partialTicks);
      if (!this.isVisible()) {
         return;
      }

      ScaledResolution sr = new ScaledResolution(mc);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      GL11.glPushMatrix();
      mouseX -= this.getX();
      mouseY -= this.getY();
      GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);

      int listWidth = this.getWidth() * 3 / 4;
      int sidebarWidth = this.getWidth() - listWidth;
      int headerColor = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
      RenderUtil.drawRect(listWidth - 1, 30, listWidth + 1, this.getHeight(), headerColor);
      RenderUtil.drawRect(0, 82, listWidth, 84, headerColor);
      if (!this.isFullScreen() && ClientManagers.moduleManager.clickGui.outline.getValue()) {
         float radius = this.isFullScreen() ? 0.0F : (float)this.cornerRadius;
         RenderUtil.drawRoundedRectOutline(0.0F, 0.0F, (float)this.getWidth(), (float)this.getHeight(), radius, 1, 1, ClientManagers.moduleManager.clickGui.outlineColor.getColorInt((byte)0));
      }

      int textColor = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
      GL11.glEnable(GL11.GL_SCISSOR_TEST);
      RenderUtil.scissorRectScaled(
         (float)this.getX(),
         (float)this.getY(),
         (float)(this.getX() + this.getWidth()),
         (float)(this.getY() + this.getHeight()),
         1.0
      );

      this.headerFont.drawString("Local Configs", (double)((int)((float)listWidth / 2.0F - MY_CONFIGS_HEADER_WIDTH / 2.0F)), 50.0, textColor);

      RenderUtil.scissorRectScaled(
         (float)(this.getX() + this.cornerRadius),
         (float)(this.getY() + 90),
         (float)(this.getX() + listWidth - 3),
         (float)(this.getY() + this.getHeight() - this.cornerRadius),
         1.0
      );

      int offsetY = 0;
      int itemAreaWidth = listWidth - 3;
      int itemHeight = (int)this.itemFont.getFontHeight();
      for (AbstractConfigListItem item : this.userConfigs) {
         int itemX = (int)((float)itemAreaWidth / 2.0F - this.itemFont.getStringWidth(item.getConfigName()) / 2.0F);
         int itemY = 90 + offsetY + this.userConfigsScrollOffset;
         boolean isSelected = this.selectedConfig != null && item.equals(this.selectedConfig);
         item.renderItem(itemX, itemY, itemAreaWidth, itemHeight, mouseX, mouseY, this.itemFont, isSelected);
         offsetY += 25;
      }

      int bottomLimit = this.getHeight() - this.cornerRadius;
      this.userConfigsScrollBar.drawVerticalBar(listWidth - 15, 90, listWidth - 5, bottomLimit, 90 - this.userConfigsScrollOffset, offsetY, headerColor);
      this.userConfigsScrollOffset = this.userConfigsScrollBar.updateScrollOffset(
         offsetY, 90, bottomLimit, this.userConfigsScrollOffset, 0, 90, listWidth, bottomLimit - 90, mouseX, mouseY
      );

      RenderUtil.scissorRectScaled(
         (float)(this.getX() + listWidth + 3),
         (float)(this.getY() + 40),
         (float)(this.getX() + this.getWidth() - this.cornerRadius),
         (float)(this.getY() + this.getHeight() - this.cornerRadius),
         1.0
      );

      float sidebarCenter = (float)listWidth + (float)sidebarWidth / 2.0F;
      int btnHeight = (int)this.itemFont.getFontHeight() + 6;
      drawSidebarButton("Create", CREATE_BUTTON_WIDTH, CREATE_BUTTON_SMALL_WIDTH, sidebarCenter, 50, 47, btnHeight, textColor, mouseX, mouseY);
      drawSidebarButton("Load", LOAD_BUTTON_WIDTH, LOAD_BUTTON_SMALL_WIDTH, sidebarCenter, 85, 82, btnHeight, textColor, mouseX, mouseY);
      drawSidebarButton("Save", SAVE_BUTTON_WIDTH, SAVE_BUTTON_SMALL_WIDTH, sidebarCenter, 120, 117, btnHeight, textColor, mouseX, mouseY);
      drawSidebarButton("Folder", FOLDER_BUTTON_WIDTH, FOLDER_BUTTON_SMALL_WIDTH, sidebarCenter, 155, 152, btnHeight, textColor, mouseX, mouseY);
      drawSidebarButton("Refresh", REFRESH_BUTTON_WIDTH, REFRESH_BUTTON_SMALL_WIDTH, sidebarCenter, 190, 187, btnHeight, textColor, mouseX, mouseY);

      int deleteY = (int)((float)this.getHeight() - this.headerFont.getFontHeight() - 2.0F - (float)this.cornerRadius);
      drawSidebarButton("Delete", DELETE_BUTTON_WIDTH, DELETE_BUTTON_SMALL_WIDTH, sidebarCenter, deleteY, deleteY - 3, btnHeight, textColor, mouseX, mouseY);

      GL11.glDisable(GL11.GL_SCISSOR_TEST);
      if (this.isMouseOver(0, 0, this.getWidth(), bottomLimit, mouseX, mouseY)) {
         for (AbstractConfigListItem item : this.userConfigs) {
            item.renderTooltip();
         }
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   private void drawSidebarButton(String label, float width, float smallWidth, float centerX, int y, int hoverY, int hoverHeight, int baseColor, int mouseX, int mouseY) {
      int drawX = (int)(centerX - width / 2.0F);
      int color = RenderUtil.getHoveredColor(baseColor, 0.2, mouseX, mouseY, drawX - 10, hoverY, (int)smallWidth + 20, hoverHeight);
      this.headerFont.drawString(label, (double)drawX, (double)y, color);
   }

   private boolean isSidebarButtonHovered(String label, float centerX, float hoverY, int mouseX, int mouseY) {
      float x = (float)((int)(centerX - this.headerFont.getStringWidth(label) / 2.0F - 10.0F));
      return RenderUtil.isHovered(x, hoverY, (float)((int)this.itemFont.getStringWidth(label) + 20), (float)((int)this.itemFont.getFontHeight() + 6), mouseX, mouseY);
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      if (this.isVisible()) {
         super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      }
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (this.isVisible()) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (!this.isVisible()) {
         return;
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
      ScaledResolution sr = new ScaledResolution(mc);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      mouseX -= this.getX();
      mouseY -= this.getY();

      int listWidth = this.getWidth() * 3 / 4;
      int sidebarWidth = this.getWidth() - listWidth;
      float sidebarCenter = (float)listWidth + (float)sidebarWidth / 2.0F;

      if (Augustus.getInstance().isConfigEditingEnabled() && this.isSidebarButtonHovered("Create", sidebarCenter, 47.0F, mouseX, mouseY)) {
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
         mc.displayGuiScreen(new CreateConfigScreen(Augustus.getInstance().getClickGui()));
      }

      if (this.isSidebarButtonHovered("Load", sidebarCenter, 82.0F, mouseX, mouseY) && this.selectedConfig != null) {
         Augustus.getInstance().getConfigManager().snapshotAll();
         Augustus.getInstance().getConfigManager().moduleConfigStorage.loadConfig(this.selectedConfig.getConfigName(), "configs");
      }

      if (Augustus.getInstance().isConfigEditingEnabled()
         && this.isSidebarButtonHovered("Save", sidebarCenter, 117.0F, mouseX, mouseY)
         && this.selectedConfig != null) {
         String configName = this.selectedConfig.getConfigName();
         ModuleConfigStorage storage = Augustus.getInstance().getConfigManager().moduleConfigStorage;
         storage.saveConfig(configName, "configs", this.selectedConfig.isEditable());
         this.loadLocalConfigEntry(new File(storage.getDirectoryPath() + File.separator + "configs", configName + ".json"));
         this.sortUserConfigs();
         this.selectedConfig = this.userConfigs.stream().filter(item -> item.getConfigName().equals(configName)).findFirst().orElse(null);
      }

      if (this.isSidebarButtonHovered("Folder", sidebarCenter, 152.0F, mouseX, mouseY)) {
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
         try {
            Desktop.getDesktop().open(new File(new File(ClientPaths.CLIENT_DIR + "/configs").getAbsolutePath()));
         } catch (Exception e) {
            notifyConfigStatus("Error opening folder", NotificationLevel.ERROR);
         }
      }

      if (this.isSidebarButtonHovered("Refresh", sidebarCenter, 187.0F, mouseX, mouseY)) {
         if (this.refreshFuture == null || this.refreshFuture.isDone()) {
            this.refreshFuture = CompletableFuture.runAsync(this::reloadAllConfigs);
         }
         notifyConfigStatus("Refreshing local configs...", NotificationLevel.INFO);
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
      }

      float deleteHoverY = (float)((int)((float)this.getHeight() - this.headerFont.getFontHeight() - 2.0F - (float)this.cornerRadius - 3.0F));
      if (this.isSidebarButtonHovered("Delete", sidebarCenter, deleteHoverY, mouseX, mouseY) && this.selectedConfig != null) {
         String configName = this.selectedConfig.getConfigName();
         if (this.refreshFuture == null || this.refreshFuture.isDone()) {
            this.refreshFuture = CompletableFuture.runAsync(() -> {
               if (this.selectedConfig != null) {
                  ClientFileUtil.moveToTrashOrDelete(new File(CONFIGS_DIRECTORY + File.separator + this.selectedConfig.getConfigName() + ".json"));
                  this.selectedConfig = null;
               }
               this.reloadAllConfigs();
            });
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
            notifyConfigStatus("Config moved to trash bin: §c" + configName, NotificationLevel.ERROR);
         }
      }

      int listLeft = this.getX() + this.cornerRadius;
      int listTop = this.getY() + 90;
      int listRight = this.getX() + listWidth - 3;
      int listBottom = this.getY() + this.getHeight() - this.cornerRadius;
      boolean inList = this.isMouseWithinBounds(listLeft, listTop, listRight, listBottom, mouseX + this.getX(), mouseY + this.getY());
      if (inList) {
         for (AbstractConfigListItem item : this.userConfigs) {
            if (item.getFontRenderer() != null
               && mouseButton == 0
               && RenderUtil.isHovered((float)(item.getX() - 5), (float)item.getY(), (float)((int)(item.getFontRenderer().getStringWidth(item.getConfigName()) + 10.0F)), (float)(item.getHeight() + 2), mouseX, mouseY)) {
               this.selectedConfig = item;
            }
            item.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }
   }

   public ConfigSystemPanel initPanel() {
      super.closeWindow();
      return this;
   }

   private void reloadAllConfigs() {
      this.scanLocalConfigDirectory();
      if (this.selectedConfig != null) {
         String name = this.selectedConfig.getConfigName();
         this.selectedConfig = this.userConfigs.stream().filter(item -> item.getConfigName().equals(name)).findFirst().orElse(null);
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.isVisible()) {
         super.keyTyped(typedChar, keyCode);
      }
   }

   public void onPanelOpened() {
      if (!this.initialLoadTriggered || this.refreshFuture == null || this.refreshFuture.isDone()) {
         this.initialLoadTriggered = true;
         this.refreshFuture = CompletableFuture.runAsync(this::reloadAllConfigs);
      }
   }
}
