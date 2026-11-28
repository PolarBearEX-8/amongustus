package wdl;

import wdl.api.IPlayerInfoEditor;
import wdl.api.ISaveListener;
import wdl.api.IWorldInfoEditor;
import wdl.api.ModInfo;
import wdl.api.WDLApi;
import wdl.gui.GuiWDLMultiworld;
import wdl.gui.GuiWDLMultiworldSelect;
import wdl.gui.GuiWDLOverwriteChanges;
import wdl.gui.GuiWDLSaveProgress;
import com.google.common.collect.HashMultimap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WDL implements MC {
   public static final String VERSION = null;
   public static final String EXPECTED_MINECRAFT_VERSION = null;
   public static WorldClient worldClient;
   public static NetworkManager networkManager = null;
   public static EntityPlayerSP thePlayer;
   public static Container windowContainer;
   public static BlockPos lastClickedBlock;
   public static Entity lastEntity;
   public static SaveHandler saveHandler;
   public static IChunkLoader chunkLoader;
   public static HashMap newTileEntities = new HashMap();
   public static HashMultimap newEntities = HashMultimap.create();
   public static HashMap newMapDatas = new HashMap();
   public static boolean downloading = false;
   public static boolean isMultiworld = false;
   public static boolean propsFound = false;
   public static boolean startOnChange = false;
   public static boolean overrideLastModifiedCheck = false;
   public static boolean saving = false;
   public static boolean worldLoadingDeferred = false;
   public static String worldName = "WorldDownloaderERROR";
   public static String baseFolderName = "WorldDownloaderERROR";
   public static Properties baseProps;
   public static Properties worldProps;
   public static final Properties defaultProps = new Properties();
   public static final Properties globalProps = new Properties(defaultProps);
   private static Logger logger = LogManager.getLogger();
   private static final int ANVIL_SAVE_VERSION = 19133;

   public static void saveTileEntity(BlockPos pos, TileEntity tileEntity) {
      int n = pos.getX() / 16;
      int n2 = pos.getZ() / 16;
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);
      if (!newTileEntities.containsKey(chunkCoordIntPair)) {
         newTileEntities.put(chunkCoordIntPair, new HashMap());
      }

      ((Map)newTileEntities.get(chunkCoordIntPair)).put(pos, tileEntity);
   }

   public static String getWorldFolderName(String str) {
      return str.isEmpty() ? baseFolderName : baseFolderName + " - " + str;
   }

   public static void saveGlobalProps() {
      try {
         globalProps.store(new FileWriter(new File(mc.mcDataDir, "WorldDownloader.txt")), I18n.format("wdl.props.global.title", new Object[0]));
      } catch (Exception e) {
      }
   }

   public static void saveContainerItems(Container container, IInventory inventory, int n) {
      int size = container.inventorySlots.size();
      int sizeInventory = inventory.getSizeInventory();
      int n2 = n;

      for (int i = 0; n2 < size && i < sizeInventory; n2++) {
         inventory.setInventorySlotContents(i, container.getSlot(n2).getStack());
         i++;
      }
   }

   public static void cancelDownload() {
      if (downloading) {
         mc.getSaveLoader().flushCache();
         saveHandler.flush();
         startOnChange = false;
         saving = false;
         downloading = false;
         worldLoadingDeferred = false;
         WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.downloadCanceled", new Object[0]);
      }
   }

   public static Properties loadWorldProps(String str) {
      Properties properties = new Properties(baseProps);
      if (str.isEmpty()) {
         return properties;
      } else {
         File file = new File(mc.mcDataDir, "saves");
         String worldFolderName = getWorldFolderName(str);
         File file2 = new File(file, worldFolderName);
         FileReader fileReader = null;

         Properties properties2;
         try {
            fileReader = new FileReader(new File(file2, "WorldDownloader.txt"));
            properties.load(fileReader);
            return properties;
         } catch (Exception e) {
            logger.debug("Failed to load world props for " + worldName, e);
            properties2 = properties;
         } finally {
            if (fileReader != null) {
               try {
                  fileReader.close();
               } catch (Exception e2) {
                  logger.warn("Failed to close world props reader for " + worldName, e2);
               }
            }
         }

         return properties2;
      }
   }

   public static String getBaseFolderName() {
      return getServerName().replaceAll("\\W+", "_");
   }

   public static void saveWorldInfo(GuiWDLSaveProgress guiWDLSaveProgress, NBTTagCompound tag) {
      if (WDLPluginChannels.canDownloadAtAll()) {
         guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.worldMetadata.title", new Object[0]), 3 + WDLApi.getImplementingExtensions(IWorldInfoEditor.class).size());
         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.savingWorld", new Object[0]);
         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.worldMetadata.creatingNBT", new Object[0]), 1);
         worldClient.getWorldInfo().setSaveVersion(19133);
         NBTTagCompound tag2 = worldClient.getWorldInfo().cloneNBTCompound(tag);
         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.worldMetadata.editingNBT", new Object[0]), 2);
         applyOverridesToWorldInfo(tag2);
         int n = 3;

         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IWorldInfoEditor.class))) {
            guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.worldMetadata.extension", new Object[]{modInfo.getDisplayName()}), n);
            ((IWorldInfoEditor)modInfo.mod).editWorldInfo(worldClient, worldClient.getWorldInfo(), saveHandler, tag2);
            n++;
         }

         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.worldMetadata.writingNBT", new Object[0]), n);
         File worldDirectory = saveHandler.getWorldDirectory();
         NBTTagCompound tag3 = new NBTTagCompound();
         tag3.setTag("Data", tag2);
         worldProps.setProperty("LastSaved", Long.toString(tag2.getLong("LastPlayed")));
         FileOutputStream fileOutputStream = null;

         try {
            File file = new File(worldDirectory, "level.dat_new");
            File file2 = new File(worldDirectory, "level.dat_old");
            File file3 = new File(worldDirectory, "level.dat");
            fileOutputStream = new FileOutputStream(file);
            CompressedStreamTools.writeCompressed(tag3, fileOutputStream);
            if (file2.exists()) {
               file2.delete();
            }

            file3.renameTo(file2);
            if (file3.exists()) {
               file3.delete();
            }

            file.renameTo(file3);
            if (file.exists()) {
               file.delete();
            }
         } catch (Exception e) {
            throw new RuntimeException("Couldn't save the world metadata!", e);
         } finally {
            if (fileOutputStream != null) {
               try {
                  fileOutputStream.close();
               } catch (IOException e2) {
                  throw new RuntimeException(e2);
               }
            }
         }

         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.worldSaved", new Object[0]);
      }
   }

   public static NBTTagCompound savePlayer(GuiWDLSaveProgress guiWDLSaveProgress) {
      if (!WDLPluginChannels.canDownloadAtAll()) {
         return new NBTTagCompound();
      } else {
         guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.playerData.title", new Object[0]), 3 + WDLApi.getImplementingExtensions(IPlayerInfoEditor.class).size());
         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.savingPlayer", new Object[0]);
         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.playerData.creatingNBT", new Object[0]), 1);
         NBTTagCompound tag = new NBTTagCompound();
         thePlayer.writeToNBT(tag);
         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.playerData.editingNBT", new Object[0]), 2);
         applyOverridesToPlayer(tag);
         int n = 3;

         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IPlayerInfoEditor.class))) {
            guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.playerData.extension", new Object[]{modInfo.getDisplayName()}), n);
            ((IPlayerInfoEditor)modInfo.mod).editPlayerInfo(thePlayer, saveHandler, tag);
            n++;
         }

         guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.playerData.writingNBT", new Object[0]), n);
         FileOutputStream fileOutputStream = null;

         try {
            File file = new File(saveHandler.getWorldDirectory(), "playerdata");
            File file2 = new File(file, thePlayer.getUniqueID().toString() + ".dat.tmp");
            File file3 = new File(file, thePlayer.getUniqueID().toString() + ".dat");
            fileOutputStream = new FileOutputStream(file2);
            CompressedStreamTools.writeCompressed(tag, fileOutputStream);
            if (file3.exists()) {
               file3.delete();
            }

            file2.renameTo(file3);
         } catch (Exception e) {
            throw new RuntimeException("Couldn't save the player!", e);
         } finally {
            if (fileOutputStream != null) {
               try {
                  fileOutputStream.close();
               } catch (IOException e2) {
                  throw new RuntimeException(e2);
               }
            }
         }

         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.playerSaved", new Object[0]);
         return tag;
      }
   }

   public static void stopDownload() {
      if (downloading) {
         downloading = false;
         startOnChange = false;
         WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.downloadStopped", new Object[0]);
         startSaveThread();
      }
   }

   public static void applyOverridesToWorldInfo(NBTTagCompound tag) {
      String property = baseProps.getProperty("ServerName");
      String property2 = worldProps.getProperty("WorldName");
      if (property2.isEmpty()) {
         tag.setString("LevelName", property);
      } else {
         tag.setString("LevelName", property + " - " + property2);
      }

      if (worldProps.getProperty("AllowCheats").equals("true")) {
         tag.setBoolean("allowCommands", true);
      } else {
         tag.setBoolean("allowCommands", false);
      }

      String property3 = worldProps.getProperty("GameType");
      if (property3.equals("keep")) {
         if (thePlayer.capabilities.isCreativeMode) {
            tag.setInteger("GameType", 1);
         } else {
            tag.setInteger("GameType", 0);
         }
      } else if (property3.equals("survival")) {
         tag.setInteger("GameType", 0);
      } else if (property3.equals("creative")) {
         tag.setInteger("GameType", 1);
      } else if (property3.equals("hardcore")) {
         tag.setInteger("GameType", 0);
         tag.setBoolean("hardcore", true);
      }

      String property4 = worldProps.getProperty("Time");
      if (!property4.equals("keep")) {
         long l = (long)Integer.parseInt(property4);
         tag.setLong("Time", l);
      }

      String property5 = worldProps.getProperty("RandomSeed");
      long l2 = 0L;
      if (!property5.isEmpty()) {
         try {
            l2 = Long.parseLong(property5);
         } catch (NumberFormatException e) {
            l2 = (long)property5.hashCode();
         }
      }

      tag.setLong("RandomSeed", l2);
      boolean flag = Boolean.parseBoolean(worldProps.getProperty("MapFeatures"));
      tag.setBoolean("MapFeatures", flag);
      String property6 = worldProps.getProperty("GeneratorName");
      tag.setString("generatorName", property6);
      String property7 = worldProps.getProperty("GeneratorOptions");
      tag.setString("generatorOptions", property7);
      int n = Integer.parseInt(worldProps.getProperty("GeneratorVersion"));
      tag.setInteger("generatorVersion", n);
      String property8 = worldProps.getProperty("Weather");
      if (property8.equals("sunny")) {
         tag.setBoolean("raining", false);
         tag.setInteger("rainTime", 0);
         tag.setBoolean("thundering", false);
         tag.setInteger("thunderTime", 0);
      } else if (property8.equals("rain")) {
         tag.setBoolean("raining", true);
         tag.setInteger("rainTime", 24000);
         tag.setBoolean("thundering", false);
         tag.setInteger("thunderTime", 0);
      } else if (property8.equals("thunderstorm")) {
         tag.setBoolean("raining", true);
         tag.setInteger("rainTime", 24000);
         tag.setBoolean("thundering", true);
         tag.setInteger("thunderTime", 24000);
      }

      String property9 = worldProps.getProperty("Spawn");
      if (property9.equals("player")) {
         int n2 = MathHelper.floor_double(thePlayer.posX);
         int n3 = MathHelper.floor_double(thePlayer.posY);
         int n4 = MathHelper.floor_double(thePlayer.posZ);
         tag.setInteger("SpawnX", n2);
         tag.setInteger("SpawnY", n3);
         tag.setInteger("SpawnZ", n4);
         tag.setBoolean("initialized", true);
      } else if (property9.equals("xyz")) {
         int n5 = Integer.parseInt(worldProps.getProperty("SpawnX"));
         int n6 = Integer.parseInt(worldProps.getProperty("SpawnY"));
         int n7 = Integer.parseInt(worldProps.getProperty("SpawnZ"));
         tag.setInteger("SpawnX", n5);
         tag.setInteger("SpawnY", n6);
         tag.setInteger("SpawnZ", n7);
         tag.setBoolean("initialized", true);
      }
   }

   public static String getServerName() {
      try {
         if (mc.getCurrentServerData() != null) {
            String serverName = mc.getCurrentServerData().serverName;
            if (serverName.equals(I18n.format("selectServer.defaultName", new Object[0]))) {
               serverName = mc.getCurrentServerData().serverIP;
            }

            return serverName;
         }
      } catch (Exception e) {
         logger.warn("Exception while getting server name: ", e);
      }

      return "Unidentified Server";
   }

   static {
      defaultProps.setProperty("ServerName", "");
      defaultProps.setProperty("WorldName", "");
      defaultProps.setProperty("LinkedWorlds", "");
      defaultProps.setProperty("Backup", "ZIP");
      defaultProps.setProperty("AllowCheats", "true");
      defaultProps.setProperty("GameType", "keep");
      defaultProps.setProperty("Time", "keep");
      defaultProps.setProperty("Weather", "keep");
      defaultProps.setProperty("MapFeatures", "false");
      defaultProps.setProperty("RandomSeed", "");
      defaultProps.setProperty("MapGenerator", "void");
      defaultProps.setProperty("GeneratorName", "flat");
      defaultProps.setProperty("GeneratorVersion", "0");
      defaultProps.setProperty("GeneratorOptions", ";0");
      defaultProps.setProperty("Spawn", "player");
      defaultProps.setProperty("SpawnX", "8");
      defaultProps.setProperty("SpawnY", "127");
      defaultProps.setProperty("SpawnZ", "8");
      defaultProps.setProperty("PlayerPos", "keep");
      defaultProps.setProperty("PlayerX", "8");
      defaultProps.setProperty("PlayerY", "127");
      defaultProps.setProperty("PlayerZ", "8");
      defaultProps.setProperty("PlayerHealth", "20");
      defaultProps.setProperty("PlayerFood", "20");
      defaultProps.setProperty("Messages.enableAll", "true");
      defaultProps.setProperty("Entity.TrackDistanceMode", "server");
      defaultProps.setProperty("Entity.FireworksRocketEntity.Enabled", "false");
      defaultProps.setProperty("Entity.EnderDragon.Enabled", "false");
      defaultProps.setProperty("Entity.WitherBoss.Enabled", "false");
      defaultProps.setProperty("Entity.PrimedTnt.Enabled", "false");
      defaultProps.setProperty("Entity.null.Enabled", "false");
      defaultProps.setProperty("EntityGroup.Other.Enabled", "true");
      defaultProps.setProperty("EntityGroup.Hostile.Enabled", "true");
      defaultProps.setProperty("EntityGroup.Passive.Enabled", "true");
      defaultProps.setProperty("LastSaved", "-1");
      defaultProps.setProperty("TutorialShown", "false");
      defaultProps.setProperty("UpdateMinecraftVersion", "client");
      defaultProps.setProperty("UpdateAllowBetas", "true");
      FileReader fileReader = null;

      try {
         fileReader = new FileReader(new File(mc.mcDataDir, "WorldDownloader.txt"));
         globalProps.load(fileReader);
      } catch (Exception e) {
         logger.debug("Failed to load global properties", e);
      } finally {
         if (fileReader != null) {
            try {
               fileReader.close();
            } catch (Exception e2) {
               logger.warn("Failed to close global properties reader", e2);
            }
         }
      }

      baseProps = new Properties(globalProps);
      worldProps = new Properties(baseProps);
   }

   public static void startSaveThread() {
      WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.saveStarted", new Object[0]);
      saving = true;
      new WDLSaveThread("WDL Save Thread").start();
   }

   public static void saveInventoryFields(IInventory inventory, IInventory inventory2) {
      for (int i = 0; i < inventory.getFieldCount(); i++) {
         inventory2.setField(i, inventory.getField(i));
      }
   }

   public static void saveProps(String str, Properties properties) {
      File file = new File(mc.mcDataDir, "saves");
      if (str.length() > 0) {
         String worldFolderName = getWorldFolderName(str);
         File file2 = new File(file, worldFolderName);
         file2.mkdirs();

         try {
            properties.store(new FileWriter(new File(file2, "WorldDownloader.txt")), I18n.format("wdl.props.world.title", new Object[0]));
         } catch (Exception e) {
         }
      } else if (!isMultiworld) {
         baseProps.putAll(properties);
      }

      File file3 = new File(file, baseFolderName);
      file3.mkdirs();

      try {
         baseProps.store(new FileWriter(new File(file3, "WorldDownloader.txt")), I18n.format("wdl.props.base.title", new Object[0]));
      } catch (Exception e2) {
      }

      saveGlobalProps();
   }

   public static String getFullMinecraftVersion() {
      String minecraftVersion = getMinecraftVersion();
      String version = Minecraft.getMinecraft().getVersion();
      String clientModName = ClientBrandRetriever.getClientModName();
      return String.format("Minecraft %s (%s/%s)", minecraftVersion, version, clientModName);
   }

   public static void saveEverything() throws Exception {
      if (!WDLPluginChannels.canDownloadAtAll()) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.ERROR, "wdl.messages.generalError.forbidden", new Object[0]);
      } else {
         WorldBackupType worldBackupType = WorldBackupType.match(baseProps.getProperty("Backup", "ZIP"));
         GuiWDLSaveProgress guiWDLSaveProgress = new GuiWDLSaveProgress(
            I18n.format("wdl.saveProgress.title", new Object[0]), (worldBackupType != WorldBackupType.NONE ? 6 : 5) + WDLApi.getImplementingExtensions(ISaveListener.class).size()
         );
         mc.addScheduledTask((Runnable)new DisplaySaveProgressTask(guiWDLSaveProgress));
         saveProps();

         try {
            saveHandler.checkSessionLock();
         } catch (MinecraftException e) {
            throw new RuntimeException("WorldDownloader: Couldn't get session lock for saving the world!", e);
         }

         saveWorldInfo(guiWDLSaveProgress, savePlayer(guiWDLSaveProgress));
         saveMapData(guiWDLSaveProgress);
         saveChunks(guiWDLSaveProgress);
         saveProps();

         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISaveListener.class))) {
            guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.extension.title", new Object[]{modInfo.getDisplayName()}), 1);
            ((ISaveListener)modInfo.mod).afterChunksSaved(saveHandler.getWorldDirectory());
         }

         try {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.flushingIO", new Object[0]);
            guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.flushingIO.title", new Object[0]), 1);
            guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.flushingIO.subtitle", new Object[0]), 1);
            ThreadedFileIOBase.getThreadedIOInstance().waitForFinish();
         } catch (Exception e2) {
            throw new RuntimeException("Threw exception waiting for asynchronous IO to finish. Hmmm.", e2);
         }

         if (worldBackupType != WorldBackupType.NONE) {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.backingUp", new Object[0]);
            guiWDLSaveProgress.startMajorTask(worldBackupType.getTitle(), 1);
            guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.backingUp.preparing", new Object[0]), 1);

            try {
               WorldBackup.backupWorld(saveHandler.getWorldDirectory(), getWorldFolderName(worldName), worldBackupType, guiWDLSaveProgress);
            } catch (IOException e3) {
               WDLMessages.chatMessageTranslated(WDLMessageTypes.ERROR, "wdl.messages.generalError.failedToBackUp", new Object[0]);
            }
         }

         guiWDLSaveProgress.setDoneWorking();
      }
   }

   public static void saveChunk(Chunk chunk) {
      if (WDLPluginChannels.canDownloadAtAll()) {
         if (WDLPluginChannels.canSaveChunk(chunk)) {
            chunk.setTerrainPopulated(true);

            try {
               chunkLoader.saveChunk(worldClient, chunk);
            } catch (Exception e) {
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ERROR,
                  "wdl.messages.generalError.failedToSaveChunk",
                  new Object[]{chunk.xPosition, chunk.zPosition, e}
               );
            }
         }
      }
   }

   public static String getMinecraftVersion() {
      Map sessionInfo = Minecraft.getSessionInfo();
      return sessionInfo.containsKey("X-Minecraft-Version") ? (String)sessionInfo.get("X-Minecraft-Version") : "1.8.9";
   }

   public static void loadBaseProps() {
      baseFolderName = getBaseFolderName();
      baseProps = new Properties(globalProps);
      FileReader fileReader = null;

      try {
         File file = new File(mc.mcDataDir, "saves");
         File file2 = new File(file, baseFolderName);
         fileReader = new FileReader(new File(file2, "WorldDownloader.txt"));
         baseProps.load(fileReader);
         propsFound = true;
      } catch (Exception e) {
         propsFound = false;
         logger.debug("Failed to load base properties", e);
      } finally {
         if (fileReader != null) {
            try {
               fileReader.close();
            } catch (Exception e2) {
               logger.warn("Failed to close base properties reader", e2);
            }
         }
      }

      if (baseProps.getProperty("LinkedWorlds").isEmpty()) {
         isMultiworld = false;
         worldProps = new Properties(baseProps);
      } else {
         isMultiworld = true;
      }
   }

   public static void saveChunks(GuiWDLSaveProgress guiWDLSaveProgress) throws IllegalArgumentException, IllegalAccessException {
      if (WDLPluginChannels.canDownloadAtAll()) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.savingChunks", new Object[0]);
         List list = (List)ReflectionUtils.stealAndGetField((ChunkProviderClient)worldClient.getChunkProvider(), List.class);
         guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.chunk.title", new Object[0]), list.size());

         for (int i = 0; i < list.size(); i++) {
            Chunk chunk = (Chunk)list.get(i);
            if (chunk != null && WDLPluginChannels.canSaveChunk(chunk)) {
               guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.chunk.saving", new Object[]{chunk.xPosition, chunk.zPosition}), i);
               saveChunk(chunk);
            }
         }

         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.chunksSaved", new Object[0]);
      }
   }

   public static void crashed(Throwable t, String str) {
      CrashReport crashReport;
      if (t instanceof ReportedException) {
         CrashReport crashReport2 = ((ReportedException)t).getCrashReport();
         crashReport = CrashReport.makeCrashReport(crashReport2.getCrashCause(), str + " (" + crashReport2.getCauseStackTraceOrString() + ")");

         try {
            List list = (List)ReflectionUtils.stealAndGetField(crashReport2, List.class);
            ((List)ReflectionUtils.stealAndGetField(crashReport, List.class)).addAll(list);
         } catch (Exception e) {
            crashReport.makeCategory("An exception occured while trying to copy the origional categories.").addCrashSectionThrowable(":(", e);
         }
      } else {
         crashReport = CrashReport.makeCrashReport(t, str);
      }

      mc.crashed(crashReport);
   }

   public static void saveProps() {
      saveProps(worldName, worldProps);
   }

   public static void startDownload() {
      worldClient = mc.theWorld;
      if (WDLPluginChannels.canDownloadAtAll()) {
         if (isMultiworld && worldName.isEmpty()) {
            mc.displayGuiScreen((GuiScreen)new GuiWDLMultiworldSelect(I18n.format("wdl.gui.multiworldSelect.title.startDownload", new Object[0]), new StartDownloadMultiworldSelectCallback()));
         } else if (!propsFound) {
            mc.displayGuiScreen((GuiScreen)new GuiWDLMultiworld(new StartDownloadMultiworldCallback()));
         } else {
            worldProps = loadWorldProps(worldName);
            saveHandler = (SaveHandler)mc.getSaveLoader().getSaveLoader(getWorldFolderName(worldName), true);
            FileInputStream fileInputStream = null;

            try {
               long l = Long.parseLong(worldProps.getProperty("LastSaved", "-1"));
               fileInputStream = new FileInputStream(new File(saveHandler.getWorldDirectory(), "level.dat"));
               long l2 = CompressedStreamTools.readCompressed(fileInputStream).getCompoundTag("Data").getLong("LastPlayed");
               if (!overrideLastModifiedCheck && l2 > l) {
                  mc.displayGuiScreen(new GuiWDLOverwriteChanges(l, l2));
                  return;
               }
            } catch (Exception e) {
               logger.warn(
                  "Error while checking if the map has been played andneeds to be backed up (this is normal if this world has not been saved before): ", e
               );
            } finally {
               if (fileInputStream != null) {
                  try {
                     fileInputStream.close();
                  } catch (Exception e2) {
                     e2.printStackTrace();
                  }
               }
            }

            GuiScreen screen = (GuiScreen)null;
            mc.displayGuiScreen(screen);
            mc.setIngameFocus();
            chunkLoader = WDLChunkLoader.create(saveHandler, worldClient.provider);
            newTileEntities = new HashMap();
            newEntities = HashMultimap.create();
            newMapDatas = new HashMap();
            if (baseProps.getProperty("ServerName").isEmpty()) {
               baseProps.setProperty("ServerName", getServerName());
            }

            startOnChange = true;
            downloading = true;
            WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.downloadStarted", new Object[0]);
         }
      }
   }

   public static void saveMapData(GuiWDLSaveProgress guiWDLSaveProgress) {
      if (WDLPluginChannels.canSaveMaps()) {
         File file = new File(saveHandler.getWorldDirectory(), "data");
         file.mkdirs();
         guiWDLSaveProgress.startMajorTask(I18n.format("wdl.saveProgress.map.title", new Object[0]), newMapDatas.size());
         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.savingMapItemData", new Object[0]);
         int n = 0;

         for (Entry entry : (Iterable<Entry>)(newMapDatas.entrySet())) {
            guiWDLSaveProgress.setMinorTaskProgress(I18n.format("wdl.saveProgress.map.saving", new Object[]{entry.getKey()}), ++n);
            File file2 = new File(file, "map_" + entry.getKey() + ".dat");
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound tag2 = new NBTTagCompound();
            ((MapData)entry.getValue()).writeToNBT(tag2);
            tag.setTag("data", tag2);

            try {
               CompressedStreamTools.writeCompressed(tag, new FileOutputStream(file2));
            } catch (IOException e) {
               throw new RuntimeException("WDL: Exception while writing map data for map " + entry.getKey() + "!", e);
            }
         }

         WDLMessages.chatMessageTranslated(WDLMessageTypes.SAVING, "wdl.messages.saving.mapItemDataSaved", new Object[0]);
      }
   }

   public static void applyOverridesToPlayer(NBTTagCompound tag) {
      String property = worldProps.getProperty("PlayerHealth");
      if (!property.equals("keep")) {
         short s = Short.parseShort(property);
         tag.setShort("Health", s);
      }

      String property2 = worldProps.getProperty("PlayerFood");
      if (!property2.equals("keep")) {
         int n = Integer.parseInt(property2);
         tag.setInteger("foodLevel", n);
         tag.setInteger("foodTickTimer", 0);
         if (n == 20) {
            tag.setFloat("foodSaturationLevel", 5.0F);
         } else {
            tag.setFloat("foodSaturationLevel", 0.0F);
         }

         tag.setFloat("foodExhaustionLevel", 0.0F);
      }

      if (worldProps.getProperty("PlayerPos").equals("xyz")) {
         int n2 = Integer.parseInt(worldProps.getProperty("PlayerX"));
         int n3 = Integer.parseInt(worldProps.getProperty("PlayerY"));
         int n4 = Integer.parseInt(worldProps.getProperty("PlayerZ"));
         NBTTagList tagList = new NBTTagList();
         tagList.appendTag(new NBTTagDouble((double)n2 + 0.5));
         tagList.appendTag(new NBTTagDouble((double)n3 + 0.621));
         tagList.appendTag(new NBTTagDouble((double)n4 + 0.5));
         tag.setTag("Pos", tagList);
         NBTTagList tagList2 = new NBTTagList();
         tagList2.appendTag(new NBTTagDouble(0.0));
         tagList2.appendTag(new NBTTagDouble(-1.0E-4));
         tagList2.appendTag(new NBTTagDouble(0.0));
         tag.setTag("Motion", tagList2);
         NBTTagList tagList3 = new NBTTagList();
         tagList3.appendTag(new NBTTagFloat(0.0F));
         tagList3.appendTag(new NBTTagFloat(0.0F));
         tag.setTag("Rotation", tagList3);
      }

      if (thePlayer.capabilities.allowFlying) {
         tag.getCompoundTag("abilities").setBoolean("flying", true);
      }
   }

   public static String getDebugInfo() {
      StringBuilder sb = new StringBuilder();
      sb.append("### CORE INFO\n\n");
      sb.append("WDL version: ").append("1.8.9a-beta2").append('\n');
      sb.append("Launched version: ").append(Minecraft.getMinecraft().getVersion()).append('\n');
      sb.append("Client brand: ").append(ClientBrandRetriever.getClientModName()).append('\n');
      sb.append("File location: ");

      try {
         String path = new File(WDL.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
         path = path.replace(System.getProperty("user.name"), "<USERNAME>");
         sb.append(path);
      } catch (Exception e) {
         sb.append("Unknown (").append(e).append(')');
      }

      sb.append("\n\n### EXTENSIONS\n\n");
      Map wdlMods = WDLApi.getWDLMods();
      sb.append(wdlMods.size()).append(" loaded\n");

      for (Entry entry : (Iterable<Entry>)(wdlMods.entrySet())) {
         sb.append("\n#### ").append((String)entry.getKey()).append("\n\n");

         try {
            sb.append(((ModInfo)entry.getValue()).getInfo());
         } catch (Exception e2) {
            sb.append("ERROR: ").append(e2).append('\n');

            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
               sb.append(stackTraceElement).append('\n');
            }
         }
      }

      sb.append("\n### STATE\n\n");
      sb.append("minecraft: ").append(mc).append('\n');
      sb.append("worldClient: ").append(worldClient).append('\n');
      sb.append("networkManager: ").append(networkManager).append('\n');
      sb.append("thePlayer: ").append(thePlayer).append('\n');
      sb.append("windowContainer: ").append(windowContainer).append('\n');
      sb.append("lastClickedBlock: ").append(lastClickedBlock).append('\n');
      sb.append("lastEntity: ").append(lastEntity).append('\n');
      sb.append("saveHandler: ").append(saveHandler).append('\n');
      sb.append("chunkLoader: ").append(chunkLoader).append('\n');
      sb.append("newTileEntities: ").append(newTileEntities).append('\n');
      sb.append("newEntities: ").append(newEntities).append('\n');
      sb.append("newMapDatas: ").append(newMapDatas).append('\n');
      sb.append("downloading: ").append(downloading).append('\n');
      sb.append("isMultiworld: ").append(isMultiworld).append('\n');
      sb.append("propsFound: ").append(propsFound).append('\n');
      sb.append("startOnChange: ").append(startOnChange).append('\n');
      sb.append("overrideLastModifiedCheck: ").append(overrideLastModifiedCheck).append('\n');
      sb.append("saving: ").append(saving).append('\n');
      sb.append("worldLoadingDeferred: ").append(worldLoadingDeferred).append('\n');
      sb.append("worldName: ").append(worldName).append('\n');
      sb.append("baseFolderName: ").append(baseFolderName).append('\n');
      sb.append("### CONNECTED SERVER\n\n");
      ServerData currentServerData = Minecraft.getMinecraft().getCurrentServerData();
      if (currentServerData == null) {
         sb.append("No data\n");
      } else {
         sb.append("Name: ").append(currentServerData.serverName).append('\n');
         sb.append("IP: ").append(currentServerData.serverIP).append('\n');
      }

      sb.append("\n### PROPERTIES\n\n");
      sb.append("\n#### BASE\n\n");
      if (baseProps != null) {
         if (!baseProps.isEmpty()) {
            for (Entry entry2 : baseProps.entrySet()) {
               sb.append(entry2.getKey()).append(": ").append(entry2.getValue());
               sb.append('\n');
            }
         } else {
            sb.append("empty\n");
         }
      } else {
         sb.append("null\n");
      }

      sb.append("\n#### WORLD\n\n");
      if (worldProps != null) {
         if (!worldProps.isEmpty()) {
            for (Entry entry3 : worldProps.entrySet()) {
               sb.append(entry3.getKey()).append(": ").append(entry3.getValue());
               sb.append('\n');
            }
         } else {
            sb.append("empty\n");
         }
      } else {
         sb.append("null\n");
      }

      sb.append("\n#### DEFAULT\n\n");
      if (globalProps != null) {
         if (!globalProps.isEmpty()) {
            for (Entry entry4 : globalProps.entrySet()) {
               sb.append(entry4.getKey()).append(": ").append(entry4.getValue());
               sb.append('\n');
            }
         } else {
            sb.append("empty\n");
         }
      } else {
         sb.append("null\n");
      }

      return sb.toString();
   }

   public static void onSaveComplete() {
      mc.getSaveLoader().flushCache();
      saveHandler.flush();
      worldClient = null;
      worldLoadingDeferred = false;
      if (downloading) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.saveComplete.startingAgain", new Object[0]);
         loadWorld();
      } else {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.saveComplete.done", new Object[0]);
      }
   }

   public static boolean isSpigot() {
      return thePlayer != null && thePlayer.getClientBrand() != null ? thePlayer.getClientBrand().toLowerCase().contains("spigot") : false;
   }

   public static boolean loadWorld() {
      worldName = "";
      worldClient = mc.theWorld;
      thePlayer = mc.thePlayer;
      windowContainer = thePlayer.openContainer;
      overrideLastModifiedCheck = false;
      NetworkManager networkManager2 = thePlayer.sendQueue.getNetworkManager();
      if (networkManager != networkManager2) {
         loadBaseProps();
         WDLMessages.onNewServer();
      }

      WDLPluginChannels.onWorldLoad();
      if (networkManager != networkManager2) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.differentServer", new Object[0]);
         networkManager = networkManager2;
         if (isSpigot()) {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.spigot", new Object[]{thePlayer.getClientBrand()});
         } else {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.vanilla", new Object[]{thePlayer.getClientBrand()});
         }

         startOnChange = false;
         return true;
      } else {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.sameServer", new Object[0]);
         if (isSpigot()) {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.spigot", new Object[]{thePlayer.getClientBrand()});
         } else {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.vanilla", new Object[]{thePlayer.getClientBrand()});
         }

         if (startOnChange) {
            startDownload();
         }

         return false;
      }
   }

}
