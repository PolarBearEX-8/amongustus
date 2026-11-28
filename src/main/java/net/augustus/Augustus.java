package net.augustus;

import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.commands.CommandManager;
import net.augustus.config.ConfigManager;
import net.augustus.cosmetics.MoreCosmeticsBootstrap;
import net.augustus.discord.DiscordRpcManager;
import net.augustus.font.FontManager;
import net.augustus.modules.ModuleManager;
import net.augustus.modules.ModuleRegistry;
import net.augustus.modules.misc.helper.EventWarmupDummyModule;
import net.augustus.settings.Setting;
import net.augustus.settings.SettingDescriptionManager;
import net.augustus.settings.SettingManager;
import net.augustus.settings.SettingNameManager;
import net.augustus.ui.menu.ChangeLogLoader;
import net.augustus.ui.menu.ClientTabManager;
import net.augustus.utils.MinotarAvatarCache;
import net.augustus.utils.MouseWheelTracker;
import net.augustus.utils.PlayerRotationHandler;
import net.augustus.utils.ProtectorManager;
import net.augustus.utils.SystemTrayNotifier;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.utils.network.ProxyEntry;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.utils.shader.BackgroundShaderManager;
import net.augustus.viaversion.ViaAugustus;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.Minecraft;

public class Augustus {
   public final boolean devMode = false;
   public final boolean betaMode = false;
   private static final Augustus INSTANCE = new Augustus();
   private final String name = "Augustus";
   private final String version = "b4.23.3";
   private final String authors = "E-sound, DasNeueUpdate, EnZaXD";
   private int clientColor = new Color(41, 146, 222).getRGB();
   private final ModuleRegistry moduleRegistry;
   private ModuleManager moduleManager;
   private SettingManager settingManager;
   private CommandManager commandManager;
   private final ClientTabManager clientTabManager;
   private final ChangeLogLoader changeLogLoader;
   private ClickGuiScreen clickGui;
   private ClientPaths clientPaths;
   private BackgroundShaderManager backgroundShaderManager;
   private RotationController rotationController;
   private SettingDescriptionManager settingDescriptionManager;
   private SettingNameManager settingNameManager;
   private ConfigManager configManager;
   private YawPitchHelper yawPitchHelper;
   private ProxyEntry proxy;
   private MouseWheelTracker mouseWheelTracker;
   private final DiscordRpcManager discordRpcManager;
   public boolean firstStart = true;
   private final TimeHelper timeHelper;
   private final ProtectorManager protectorManager;
   private EventWarmupDummyModule eventWarmupDummyModule;
   private boolean configEditingEnabled = true;
   private boolean shuttingDown = false;
   private static boolean initialized = false;

   public static Augustus getInstance() {
      return INSTANCE;
   }

   public Augustus() {
      this.moduleRegistry = new ModuleRegistry();
      this.clientTabManager = new ClientTabManager();
      this.changeLogLoader = new ChangeLogLoader();
      this.discordRpcManager = new DiscordRpcManager();
      this.timeHelper = new TimeHelper();
      this.protectorManager = new ProtectorManager();
   }

   public void preStart() {
   }

   public void start() {
      MoreCosmeticsBootstrap.initialize(1);

      try {
         this.initializeClient();
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }

   private void initializeClient() throws IOException, InterruptedException {
      if (initialized) {
         return;
      }

      initialized = true;
      Setting.notifyListenersOnConfigLoad = true;
      this.yawPitchHelper = new PlayerRotationHandler();
      this.mouseWheelTracker = new MouseWheelTracker();
      this.shuttingDown = false;
      SystemTrayNotifier.init();
      FontManager.initFonts();
      this.protectorManager.init();
      this.rotationController = new RotationController(this.getVersion());
      this.settingManager = new SettingManager();
      this.moduleManager = new ModuleManager();
      this.configManager = new ConfigManager();
      this.moduleManager.initModules();
      this.settingDescriptionManager = new SettingDescriptionManager();
      this.settingNameManager = new SettingNameManager();
      this.moduleManager.autoDisable.initModuleSettings(this.moduleManager);
      this.commandManager = new CommandManager();
      this.changeLogLoader.loadChangelog();
      this.clientPaths = new ClientPaths();
      ClientPaths.init();
      this.backgroundShaderManager = new BackgroundShaderManager();
      ClientManagers.init();
      this.clickGui = new ClickGuiScreen();
      this.settingDescriptionManager.initialize();
      this.settingNameManager.initialize();
      this.clientTabManager.initTabs();
      this.configManager.loadAll();
      MinotarAvatarCache.preloadAccountAvatars();

      try {
         ClientFileUtil.extractResource(
            "assets/augustus/sounds/alarm.ogg",
            this.getName().toLowerCase() + "/sounds",
            false
         );
      } catch (Exception e) {
         System.err.println("Failed to save sound");
      }

      CompletableFuture.runAsync(() -> {
         try {
            ViaAugustus.initialize();
         } catch (Throwable e2) {
            e2.printStackTrace();
         }
      });
   }

   public void stop() {
      if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getSession() != null) {
         Minecraft.getMinecraft().getSession().updatePlayTime(false);
      }

      this.configManager.saveAll();

      try {
         Thread.sleep(200L);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }

   public String getName() {
      return this.name;
   }

   public String getVersion() {
      return this.version;
   }

   public String getAuthors() {
      return this.authors;
   }

   public int getClientColor() {
      return this.clientColor;
   }

   public void setClientColor(int color) {
      this.clientColor = color;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public SettingManager getSettingManager() {
      return this.settingManager;
   }

   public CommandManager getCommandManager() {
      return this.commandManager;
   }

   public ClientPaths getClientPaths() {
      return this.clientPaths;
   }

   public BackgroundShaderManager getBackgroundShaderManager() {
      return this.backgroundShaderManager;
   }

   public YawPitchHelper getYawPitchHelper() {
      return this.yawPitchHelper;
   }

   public ModuleRegistry getModuleRegistry() {
      return this.moduleRegistry;
   }

   public ClickGuiScreen getClickGui() {
      return this.clickGui;
   }

   public MouseWheelTracker getMouseWheelTracker() {
      return this.mouseWheelTracker;
   }

   public DiscordRpcManager getDiscordRpcManager() {
      return this.discordRpcManager;
   }

   public RotationController getRotationController() {
      return this.rotationController;
   }

   public void setBackgroundShaderManager(BackgroundShaderManager backgroundShaderManager) {
      this.backgroundShaderManager = backgroundShaderManager;
   }

   public void setMouseWheelTracker(MouseWheelTracker mouseWheelTracker) {
      this.mouseWheelTracker = mouseWheelTracker;
   }

   public TimeHelper getTimeHelper() {
      return this.timeHelper;
   }

   public ClientTabManager getClientTabManager() {
      return this.clientTabManager;
   }

   public ChangeLogLoader getChangeLogLoader() {
      return this.changeLogLoader;
   }

   public static byte[] readAllBytes(InputStream in, int initialCapacity, long unused) throws IOException {
      int maxArraySize = 2147483639;
      int capacity = initialCapacity;
      byte[] bytes = new byte[initialCapacity];
      int offset = 0;

      while (true) {
         int read;
         while ((read = in.read(bytes, offset, capacity - offset)) > 0) {
            offset += read;
         }

         if (read < 0 || (read = in.read()) < 0) {
            return capacity == offset ? bytes : Arrays.copyOf(bytes, offset);
         }

         if (capacity <= maxArraySize - capacity) {
            capacity = Math.max(capacity << 1, 8192);
         } else {
            if (capacity == maxArraySize) {
               throw new OutOfMemoryError("Required array size too large");
            }
            capacity = maxArraySize;
         }

         bytes = Arrays.copyOf(bytes, capacity);
         bytes[offset++] = (byte)read;
      }
   }

   public ProtectorManager getProtectorManager() {
      return this.protectorManager;
   }

   public String getUsername() {
      Minecraft mc = Minecraft.getMinecraft();
      return mc != null && mc.getSession() != null ? mc.getSession().getUsername() : "Player";
   }

   public ProxyEntry getProxy() {
      return this.proxy;
   }

   public synchronized void setProxy(ProxyEntry proxyEntry) {
      this.proxy = proxyEntry;
   }

   public void setYawPitchHelper(PlayerRotationHandler playerRotationHandler) {
      this.yawPitchHelper = playerRotationHandler;
   }

   public SettingDescriptionManager getSettingDescriptionManager() {
      return this.settingDescriptionManager;
   }

   public SettingNameManager getSettingNameManager() {
      return this.settingNameManager;
   }

   public ConfigManager getConfigManager() {
      return this.configManager;
   }

   public EventWarmupDummyModule getEventWarmupDummyModule() {
      return this.eventWarmupDummyModule;
   }

   public void setEventWarmupDummyModule(EventWarmupDummyModule event) {
      this.eventWarmupDummyModule = event;
   }

   public boolean isConfigEditingEnabled() {
      return this.configEditingEnabled;
   }

   public void setConfigEditingEnabled(boolean enabled) {
      this.configEditingEnabled = enabled;
   }
}
