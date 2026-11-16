package cc.baritone.utils;

import client.event.MultiplayerChunkCacheListener;
import client.util.AlertNotificationSound;
import client.util.DimensionStringEntry;
import client.util.InternalClientManagerRegistry;
import client.util.InterpolationCollector;
import client.util.ListenerAdapter17;
import client.util.LoadingNBTDataManager;
import client.util.MinecraftAccessor;
import client.util.PrimaryDoubleEntry;
import client.util.RunnableHandler;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.VanillaManager;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pokechu22WorldDownloaderManager implements MinecraftAccessor {
   public static final String labelText = "1.8.9a-beta2";
   public static final String labelText5 = "1.8.9";
   public static final String labelText3 = "Pokechu22/WorldDownloader";
   public static WorldClient worldClient;
   public static NetworkManager networkManager = null;
   public static EntityPlayerSP entityPlayerSP;
   public static Container container;
   public static BlockPos blockPos;
   public static Entity entity;
   public static ListenerAdapter17 listenerAdapter17;
   public static client.event.GenericListener genericListener;
   public static HashMap hashMap = new HashMap();
   public static HashMultimap hashMultimap = HashMultimap.create();
   public static HashMap hashMap2 = new HashMap();
   public static boolean enabled = false;
   public static boolean enabled4 = false;
   public static boolean enabled5 = false;
   public static boolean enabled7 = false;
   public static boolean enabled3 = false;
   public static boolean enabled6 = false;
   public static boolean enabled2 = false;
   public static String labelText4 = "WorldDownloaderERROR";
   public static String labelText2 = "WorldDownloaderERROR";
   public static Properties properties4;
   public static Properties properties2;
   public static final Properties properties = new Properties();
   public static final Properties properties3 = new Properties(properties);
   public static Logger logger = LogManager.getLogger();
   public static final int count = 19133;

   public static String getTextAsText() {
      Map map = Minecraft.getMap();
      return map.containsKey("X-Minecraft-Version") ? (String)map.get("X-Minecraft-Version") : "1.8.9";
   }

   public static void updateState6() {
      minecraft.getISaveFormat().updateState();
      listenerAdapter17.updateState();
      worldClient = null;
      enabled2 = false;
      if (enabled) {
         LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.saveComplete.startingAgain", new Object[0]);
         isActive();
      } else {
         LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.saveComplete.done", new Object[0]);
      }
   }

   public static void applyBlockPos(BlockPos blockPos2, TileEntity tileEntity) {
      int count2 = blockPos2.getX() / 16;
      int count3 = blockPos2.getZ() / 16;
      InterpolationCollector interpolationCollector = new InterpolationCollector(count2, count3);
      if (!hashMap.containsKey(interpolationCollector)) {
         hashMap.put(interpolationCollector, new HashMap());
      }

      ((Map)hashMap.get(interpolationCollector)).put(blockPos2, tileEntity);
   }

   public static void updateState3() {
      worldClient = minecraft.theWorld;
      if (DownloadInGeneralManager.isActive6()) {
         if (enabled4 && labelText4.isEmpty()) {
            Minecraft mc2 = minecraft;
            HeuristicPipeline heuristicPipeline = new HeuristicPipeline(
               StatCollector.getText("wdl.gui.multiworldSelect.title.startDownload", new Object[0]), new BreadthFirstAuditor()
            );
            mc2.displayGuiScreen(heuristicPipeline);
         } else if (!enabled5) {
            Minecraft mc3 = minecraft;
            HeuristicAuditor heuristicAuditor = new HeuristicAuditor(new DiagonalPlanner());
            mc3.displayGuiScreen(heuristicAuditor);
         } else {
            properties2 = resolveProperties(labelText4);
            listenerAdapter17 = (ListenerAdapter17)minecraft.getISaveFormat().resolveISaveHandler(resolveText(labelText4), true);
            FileInputStream inputStream = null;
            boolean flag = false ;

            checkBlock: {
               guardBlock: {
                  searchBlock: {
                     try {
                        flag = true;
                        long value = Long.parseLong(properties2.getProperty("LastSaved", "-1"));
                        inputStream = new FileInputStream(new File(listenerAdapter17.getFile(), "level.dat"));
                        long value2 = LoadingNBTDataManager.resolveNBTTagCompound(inputStream).getNBTTagCompound("Data").computeTimestamp("LastPlayed");
                        if (!enabled3) {
                           if (value2 > value) {
                              Minecraft mc4 = minecraft;
                              DiagonalWatcher diagonalWatcher = new DiagonalWatcher(value, value2);
                              mc4.displayGuiScreen(diagonalWatcher);
                              flag = false;
                              break checkBlock;
                           }

                           flag = false;
                        } else {
                           flag = false;
                        }
                        break searchBlock;
                     } catch (Exception exception) {
                        logger.warn(
                           "Error while checking if the map has been played andneeds to be backed up (this is normal if this world has not been saved before): ",
                           exception
                        );
                        flag = false;
                     } finally {
                        if (flag) {
                           if (inputStream != null) {
                              try {
                                 inputStream.close();
                              } catch (Exception exception2) {
                                 exception2.printStackTrace();
                              }
                           }
                        }
                     }

                     if (inputStream != null) {
                        try {
                           inputStream.close();
                        } catch (Exception exception3) {
                           exception3.printStackTrace();
                        }
                     }
                     break guardBlock;
                  }

                  try {
                     inputStream.close();
                  } catch (Exception exception4) {
                     exception4.printStackTrace();
                  }
               }

               GuiScreen screen = null;
               minecraft.displayGuiScreen(screen);
               minecraft.updateState21();
               genericListener = SpecialCustom.resolveSpecialCustom(listenerAdapter17, worldClient.secondaryFloatArrayEntry);
               hashMap = new HashMap();
               hashMultimap = HashMultimap.create();
               hashMap2 = new HashMap();
               if (properties4.getProperty("ServerName").isEmpty()) {
                  properties4.setProperty("ServerName", getText());
               }

               enabled7 = true;
               enabled = true;
               LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.downloadStarted", new Object[0]);
               return;
            }

            try {
               inputStream.close();
            } catch (Exception exception5) {
               exception5.printStackTrace();
            }
         }
      }
   }

   public static Properties resolveProperties(String text) {
      Properties properties5 = new Properties(properties4);
      if (text.isEmpty()) {
         return properties5;
      } else {
         File file = new File(minecraft.mcDataDir, "saves");
         String text2 = resolveText(text);
         File file2 = new File(file, text2);
         FileReader reader = null;
         boolean flag = false ;

         Properties properties6;
         searchBlock: {
            try {
               flag = true;
               reader = new FileReader(new File(file2, "WorldDownloader.txt"));
               properties5.load(reader);
               properties6 = properties5;
               flag = false;
               break searchBlock;
            } catch (Exception exception) {
               logger.debug("Failed to load world props for " + labelText4, exception);
               flag = false;
            } finally {
               if (flag) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Exception exception2) {
                        logger.warn("Failed to close world props reader for " + labelText4, exception2);
                     }
                  }
               }
            }

            if (reader != null) {
               try {
                  reader.close();
               } catch (Exception exception3) {
                  logger.warn("Failed to close world props reader for " + labelText4, exception3);
               }
            }

            return properties5;
         }

         try {
            reader.close();
         } catch (Exception exception4) {
            logger.warn("Failed to close world props reader for " + labelText4, exception4);
         }

         return properties6;
      }
   }

   public static void updateState2() {
      applyText(labelText4, properties2);
   }

   public static void applyHeuristicEngine(HeuristicEngine heuristicEngine, NBTTagCompound tagCompound) {
      if (DownloadInGeneralManager.isActive6()) {
         heuristicEngine.applyTextByText(
            StatCollector.getText("wdl.saveProgress.worldMetadata.title", new Object[0]), 3 + HologramManager.resolveList(BreadthFirstFilter.class).size()
         );
         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.savingWorld", new Object[0]);
         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.worldMetadata.creatingNBT", new Object[0]), 1);
         worldClient.getRandomSeedManager().setCount7(19133);
         NBTTagCompound tagCompound2 = worldClient.getRandomSeedManager().resolveNBTTagCompound(tagCompound);
         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.worldMetadata.editingNBT", new Object[0]), 2);
         applyNBTTagCompoundByNBTTagCompound(tagCompound2);
         int count2 = 3;

         for (StringEntry stringEntry : (Iterable<StringEntry>) (HologramManager.resolveList(BreadthFirstFilter.class))) {
            heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.worldMetadata.extension", new Object[]{stringEntry.getTextAsText()}), count2);
            ((BreadthFirstFilter)stringEntry.specialListener).applyWorldClient(worldClient, worldClient.getRandomSeedManager(), listenerAdapter17, tagCompound2);
            count2++;
         }

         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.worldMetadata.writingNBT", new Object[0]), count2);
         File file = listenerAdapter17.getFile();
         NBTTagCompound tagCompound3 = new NBTTagCompound();
         tagCompound3.applyText("Data", tagCompound2);
         properties2.setProperty("LastSaved", Long.toString(tagCompound2.computeTimestamp("LastPlayed")));
         FileOutputStream outputStream = null;
         boolean flag = false ;

         try {
            flag = true;
            File file2 = new File(file, "level.dat_new");
            File file3 = new File(file, "level.dat_old");
            File file4 = new File(file, "level.dat");
            outputStream = new FileOutputStream(file2);
            LoadingNBTDataManager.applyNBTTagCompound(tagCompound3, outputStream);
            if (file3.exists()) {
               file3.delete();
            }

            file4.renameTo(file3);
            if (file4.exists()) {
               file4.delete();
            }

            file2.renameTo(file4);
            if (file2.exists()) {
               file2.delete();
               flag = false;
            } else {
               flag = false;
            }
         } catch (Exception exception) {
            throw new RuntimeException("Couldn't save the world metadata!", exception);
         } finally {
            if (flag) {
               if (outputStream != null) {
                  try {
                     outputStream.close();
                  } catch (IOException ioException) {
                     throw new RuntimeException(ioException);
                  }
               }
            }
         }

         try {
            outputStream.close();
         } catch (IOException ioException2) {
            throw new RuntimeException(ioException2);
         }

         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.worldSaved", new Object[0]);
      }
   }

   public static boolean isActive() {
      labelText4 = "";
      worldClient = minecraft.theWorld;
      entityPlayerSP = minecraft.thePlayer;
      container = entityPlayerSP.container2;
      enabled3 = false;
      NetworkManager networkManager2 = entityPlayerSP.netHandlerPlayClient.getNetworkManager();
      if (networkManager != networkManager2) {
         updateState();
         LoggerEntry.updateState();
      }

      DownloadInGeneralManager.updateState();
      if (networkManager != networkManager2) {
         LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.differentServer", new Object[0]);
         networkManager = networkManager2;
         if (isActiveAsFlag()) {
            LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.spigot", new Object[]{entityPlayerSP.getText4()});
         } else {
            LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.vanilla", new Object[]{entityPlayerSP.getText4()});
         }

         enabled7 = false;
         return true;
      } else {
         LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.sameServer", new Object[0]);
         if (isActiveAsFlag()) {
            LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.spigot", new Object[]{entityPlayerSP.getText4()});
         } else {
            LoggerEntry.applyAltListener(OptionType.ON_WORLD_LOAD, "wdl.messages.onWorldLoad.vanilla", new Object[]{entityPlayerSP.getText4()});
         }

         if (enabled7) {
            updateState3();
         }

         return false;
      }
   }

   public static void updateState() {
      labelText2 = getText2();
      properties4 = new Properties(properties3);
      FileReader reader = null;
      boolean flag = false ;

      guardBlock: {
         searchBlock: {
            try {
               flag = true;
               File file = new File(minecraft.mcDataDir, "saves");
               File file2 = new File(file, labelText2);
               reader = new FileReader(new File(file2, "WorldDownloader.txt"));
               properties4.load(reader);
               enabled5 = true;
               flag = false;
               break searchBlock;
            } catch (Exception exception) {
               enabled5 = false;
               logger.debug("Failed to load base properties", exception);
               flag = false;
            } finally {
               if (flag) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Exception exception2) {
                        logger.warn("Failed to close base properties reader", exception2);
                     }
                  }
               }
            }

            if (reader != null) {
               try {
                  reader.close();
               } catch (Exception exception3) {
                  logger.warn("Failed to close base properties reader", exception3);
               }
            }
            break guardBlock;
         }

         try {
            reader.close();
         } catch (Exception exception4) {
            logger.warn("Failed to close base properties reader", exception4);
         }
      }

      if (properties4.getProperty("LinkedWorlds").isEmpty()) {
         enabled4 = false;
         properties2 = new Properties(properties4);
      } else {
         enabled4 = true;
      }
   }

   public static void applyThrowable(Throwable throwable, String text) {
      CrashReport crashReport;
      if (throwable instanceof SecondaryRuntimeExceptionAdapter) {
         CrashReport crashReport2 = ((SecondaryRuntimeExceptionAdapter)throwable).getCrashReport();
         crashReport = CrashReport.resolveCrashReport(crashReport2.getThrowable(), text + " (" + crashReport2.getText4() + ")");

         try {
            List list = (List)DepthFirstService.resolveTarget(crashReport2, List.class);
            ((List)DepthFirstService.resolveTarget(crashReport, List.class)).addAll(list);
         } catch (Exception exception) {
            crashReport.resolveCrashReportCategory("An exception occured while trying to copy the origional categories.").applyText(":(", exception);
         }
      } else {
         crashReport = CrashReport.resolveCrashReport(throwable, text);
      }

      minecraft.applyCrashReport(crashReport);
   }

   public static void applyHeuristicEngine(HeuristicEngine heuristicEngine) throws IllegalArgumentException, IllegalAccessException {
      if (DownloadInGeneralManager.isActive6()) {
         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.savingChunks", new Object[0]);
         List list = (List)DepthFirstService.resolveTarget((MultiplayerChunkCacheListener)worldClient.getCommonListener(), List.class);
         heuristicEngine.applyTextByText(StatCollector.getText("wdl.saveProgress.chunk.title", new Object[0]), list.size());

         for (int index = 0; index < list.size(); index++) {
            AlertNotificationSound alertNotificationSound = (AlertNotificationSound)list.get(index);
            if (alertNotificationSound != null && DownloadInGeneralManager.matchesAlertNotificationSound4(alertNotificationSound)) {
               heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.chunk.saving", new Object[]{alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset}), index);
               applyAlertNotificationSound(alertNotificationSound);
            }
         }

         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.chunksSaved", new Object[0]);
      }
   }

   public static String getText() {
      try {
         if (minecraft.getServerData() != null) {
            String labelText6 = minecraft.getServerData().labelText3;
            if (labelText6.equals(StatCollector.getText("selectServer.defaultName", new Object[0]))) {
               labelText6 = minecraft.getServerData().text;
            }

            return labelText6;
         }
      } catch (Exception exception) {
         logger.warn("Exception while getting server name: ", exception);
      }

      return "Unidentified Server";
   }

   public static void updateState4() throws Exception {
      if (!DownloadInGeneralManager.isActive6()) {
         LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.forbidden", new Object[0]);
      } else {
         CompositeCustom compositeCustom = CompositeCustom.resolveCompositeCustom(properties4.getProperty("Backup", "ZIP"));
         HeuristicEngine heuristicEngine = new HeuristicEngine(
            StatCollector.getText("wdl.saveProgress.title", new Object[0]),
            (compositeCustom != CompositeCustom.NONE ? 6 : 5) + HologramManager.resolveList(DepthFirstRecorder.class).size()
         );
         Minecraft mc2 = minecraft;
         DepthFirstProcessor depthFirstProcessor = new DepthFirstProcessor(heuristicEngine);
         mc2.resolveListenableFuture(depthFirstProcessor);
         updateState2();

         try {
            listenerAdapter17.updateState2();
         } catch (client.util.ExceptionAdapter exception) {
            throw new RuntimeException("WorldDownloader: Couldn't get session lock for saving the world!", exception);
         }

         NBTTagCompound tagCompound = resolveNBTTagCompound(heuristicEngine);
         applyHeuristicEngine(heuristicEngine, tagCompound);
         applyHeuristicEngineByHeuristicEngine(heuristicEngine);
         applyHeuristicEngine(heuristicEngine);
         updateState2();

         for (StringEntry stringEntry : (Iterable<StringEntry>) (HologramManager.resolveList(DepthFirstRecorder.class))) {
            heuristicEngine.applyTextByText(StatCollector.getText("wdl.saveProgress.extension.title", new Object[]{stringEntry.getTextAsText()}), 1);
            ((DepthFirstRecorder)stringEntry.specialListener).applyFile(listenerAdapter17.getFile());
         }

         try {
            LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.flushingIO", new Object[0]);
            heuristicEngine.applyTextByText(StatCollector.getText("wdl.saveProgress.flushingIO.title", new Object[0]), 1);
            heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.flushingIO.subtitle", new Object[0]), 1);
            RunnableHandler.getRunnableHandler().sleep();
         } catch (Exception exception2) {
            throw new RuntimeException("Threw exception waiting for asynchronous IO to finish. Hmmm.", exception2);
         }

         if (compositeCustom != CompositeCustom.NONE) {
            LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.backingUp", new Object[0]);
            heuristicEngine.applyTextByText(compositeCustom.getTextAsText(), 1);
            heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.backingUp.preparing", new Object[0]), 1);

            try {
               DepthFirstStrategy.applyFile(listenerAdapter17.getFile(), resolveText(labelText4), compositeCustom, (IndirectListener)(Object)heuristicEngine);
            } catch (IOException ioException) {
               LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.failedToBackUp", new Object[0]);
            }
         }

         heuristicEngine.updateState();
      }
   }

   public static void updateState8() {
      try {
         properties3.store(new FileWriter(new File(minecraft.mcDataDir, "WorldDownloader.txt")), StatCollector.getText("wdl.props.global.title", new Object[0]));
      } catch (Exception exception) {
      }
   }

   public static String resolveText(String text) {
      return text.isEmpty() ? labelText2 : labelText2 + " - " + text;
   }

   public static void applyText(String text, Properties properties5) {
      File file = new File(minecraft.mcDataDir, "saves");
      if (text.length() > 0) {
         String text2 = resolveText(text);
         File file2 = new File(file, text2);
         file2.mkdirs();

         try {
            properties5.store(new FileWriter(new File(file2, "WorldDownloader.txt")), StatCollector.getText("wdl.props.world.title", new Object[0]));
         } catch (Exception exception) {
         }
      } else if (!enabled4) {
         properties4.putAll(properties5);
      }

      File file3 = new File(file, labelText2);
      file3.mkdirs();

      try {
         properties4.store(new FileWriter(new File(file3, "WorldDownloader.txt")), StatCollector.getText("wdl.props.base.title", new Object[0]));
      } catch (Exception exception2) {
      }

      updateState8();
   }

   public static void applyContainer(Container container2, IInventory iInventory, int count2) {
      int size = container2.itemList2.size();
      int count3 = iInventory.processAction4();
      int count4 = count2;

      for (int index = 0; count4 < size && index < count3; count4++) {
         ItemStack stack = container2.getSlot(count4).getStack();
         iInventory.processAction(index, stack);
         index++;
      }
   }

   public static String getText3() {
      String textAsText = getTextAsText();
      String labelText6 = Minecraft.getMinecraft().getLabelText3();
      String text = VanillaManager.getText();
      return String.format("Minecraft %s (%s/%s)", textAsText, labelText6, text);
   }

   public static String getText4() {
      StringBuilder builder = new StringBuilder();
      builder.append("### CORE INFO\n\n");
      builder.append("WDL version: ").append("1.8.9a-beta2").append('\n');
      builder.append("Launched version: ").append(Minecraft.getMinecraft().getLabelText3()).append('\n');
      builder.append("Client brand: ").append(VanillaManager.getText()).append('\n');
      builder.append("File location: ");

      try {
         String path = new File(Pokechu22WorldDownloaderManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
         String property = System.getProperty("user.name");
         String text = path.replace(property, "<USERNAME>");
         builder.append(text);
      } catch (Exception exception) {
         builder.append("Unknown (").append(exception).append(')');
      }

      builder.append("\n\n### EXTENSIONS\n\n");
      Map map = HologramManager.copyOf();
      builder.append(map.size()).append(" loaded\n");

      for (Entry entry : (Iterable<Entry>) (map.entrySet())) {
         builder.append("\n#### ").append((String)entry.getKey()).append("\n\n");

         try {
            builder.append(((StringEntry)entry.getValue()).getText());
         } catch (Exception exception2) {
            builder.append("ERROR: ").append(exception2).append('\n');

            for (StackTraceElement stackTraceElement : exception2.getStackTrace()) {
               builder.append(stackTraceElement).append('\n');
            }
         }
      }

      builder.append("\n### STATE\n\n");
      builder.append("minecraft: ").append(minecraft).append('\n');
      builder.append("worldClient: ").append(worldClient).append('\n');
      builder.append("networkManager: ").append(networkManager).append('\n');
      builder.append("thePlayer: ").append(entityPlayerSP).append('\n');
      builder.append("windowContainer: ").append(container).append('\n');
      builder.append("lastClickedBlock: ").append(blockPos).append('\n');
      builder.append("lastEntity: ").append(entity).append('\n');
      builder.append("saveHandler: ").append(listenerAdapter17).append('\n');
      builder.append("chunkLoader: ").append(genericListener).append('\n');
      builder.append("newTileEntities: ").append(hashMap).append('\n');
      builder.append("newEntities: ").append(hashMultimap).append('\n');
      builder.append("newMapDatas: ").append(hashMap2).append('\n');
      builder.append("downloading: ").append(enabled).append('\n');
      builder.append("isMultiworld: ").append(enabled4).append('\n');
      builder.append("propsFound: ").append(enabled5).append('\n');
      builder.append("startOnChange: ").append(enabled7).append('\n');
      builder.append("overrideLastModifiedCheck: ").append(enabled3).append('\n');
      builder.append("saving: ").append(enabled6).append('\n');
      builder.append("worldLoadingDeferred: ").append(enabled2).append('\n');
      builder.append("worldName: ").append(labelText4).append('\n');
      builder.append("baseFolderName: ").append(labelText2).append('\n');
      builder.append("### CONNECTED SERVER\n\n");
      ServerData serverData = Minecraft.getMinecraft().getServerData();
      if (serverData == null) {
         builder.append("No data\n");
      } else {
         builder.append("Name: ").append(serverData.labelText3).append('\n');
         builder.append("IP: ").append(serverData.text).append('\n');
      }

      builder.append("\n### PROPERTIES\n\n");
      builder.append("\n#### BASE\n\n");
      if (properties4 != null) {
         if (!properties4.isEmpty()) {
            for (Entry entry2 : properties4.entrySet()) {
               builder.append(entry2.getKey()).append(": ").append(entry2.getValue());
               builder.append('\n');
            }
         } else {
            builder.append("empty\n");
         }
      } else {
         builder.append("null\n");
      }

      builder.append("\n#### WORLD\n\n");
      if (properties2 != null) {
         if (!properties2.isEmpty()) {
            for (Entry entry3 : properties2.entrySet()) {
               builder.append(entry3.getKey()).append(": ").append(entry3.getValue());
               builder.append('\n');
            }
         } else {
            builder.append("empty\n");
         }
      } else {
         builder.append("null\n");
      }

      builder.append("\n#### DEFAULT\n\n");
      if (properties3 != null) {
         if (!properties3.isEmpty()) {
            for (Entry entry4 : properties3.entrySet()) {
               builder.append(entry4.getKey()).append(": ").append(entry4.getValue());
               builder.append('\n');
            }
         } else {
            builder.append("empty\n");
         }
      } else {
         builder.append("null\n");
      }

      return builder.toString();
   }

   public static void updateState9() {
      if (enabled) {
         minecraft.getISaveFormat().updateState();
         listenerAdapter17.updateState();
         enabled7 = false;
         enabled6 = false;
         enabled = false;
         enabled2 = false;
         LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.downloadCanceled", new Object[0]);
      }
   }

   public static void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      String property = properties4.getProperty("ServerName");
      String property2 = properties2.getProperty("WorldName");
      if (property2.isEmpty()) {
         tagCompound.put("LevelName", property);
      } else {
         tagCompound.put("LevelName", property + " - " + property2);
      }

      if (properties2.getProperty("AllowCheats").equals("true")) {
         tagCompound.applyText("allowCommands", true);
      } else {
         tagCompound.applyText("allowCommands", false);
      }

      String property3 = properties2.getProperty("GameType");
      if (property3.equals("keep")) {
         if (entityPlayerSP.invulnerableManager.enabled2) {
            tagCompound.put("GameType", 1);
         } else {
            tagCompound.put("GameType", 0);
         }
      } else if (property3.equals("survival")) {
         tagCompound.put("GameType", 0);
      } else if (property3.equals("creative")) {
         tagCompound.put("GameType", 1);
      } else if (property3.equals("hardcore")) {
         tagCompound.put("GameType", 0);
         tagCompound.applyText("hardcore", true);
      }

      String property4 = properties2.getProperty("Time");
      if (!property4.equals("keep")) {
         long value = (long)Integer.parseInt(property4);
         tagCompound.put("Time", value);
      }

      String property5 = properties2.getProperty("RandomSeed");
      long value2 = 0L;
      if (!property5.isEmpty()) {
         try {
            value2 = Long.parseLong(property5);
         } catch (NumberFormatException numberFormatException) {
            value2 = (long)property5.hashCode();
         }
      }

      tagCompound.put("RandomSeed", value2);
      boolean flag = Boolean.parseBoolean(properties2.getProperty("MapFeatures"));
      tagCompound.applyText("MapFeatures", flag);
      String property6 = properties2.getProperty("GeneratorName");
      tagCompound.put("generatorName", property6);
      String property7 = properties2.getProperty("GeneratorOptions");
      tagCompound.put("generatorOptions", property7);
      int count2 = Integer.parseInt(properties2.getProperty("GeneratorVersion"));
      tagCompound.put("generatorVersion", count2);
      String property8 = properties2.getProperty("Weather");
      if (property8.equals("sunny")) {
         tagCompound.applyText("raining", false);
         tagCompound.put("rainTime", 0);
         tagCompound.applyText("thundering", false);
         tagCompound.put("thunderTime", 0);
      } else if (property8.equals("rain")) {
         tagCompound.applyText("raining", true);
         tagCompound.put("rainTime", 24000);
         tagCompound.applyText("thundering", false);
         tagCompound.put("thunderTime", 0);
      } else if (property8.equals("thunderstorm")) {
         tagCompound.applyText("raining", true);
         tagCompound.put("rainTime", 24000);
         tagCompound.applyText("thundering", true);
         tagCompound.put("thunderTime", 24000);
      }

      String property9 = properties2.getProperty("Spawn");
      if (property9.equals("player")) {
         int count3 = MathHelper.ceiling_double_int(entityPlayerSP.posX);
         int count4 = MathHelper.ceiling_double_int(entityPlayerSP.posY);
         int count5 = MathHelper.ceiling_double_int(entityPlayerSP.posZ);
         tagCompound.put("SpawnX", count3);
         tagCompound.put("SpawnY", count4);
         tagCompound.put("SpawnZ", count5);
         tagCompound.applyText("initialized", true);
      } else if (property9.equals("xyz")) {
         int count6 = Integer.parseInt(properties2.getProperty("SpawnX"));
         int count7 = Integer.parseInt(properties2.getProperty("SpawnY"));
         int count8 = Integer.parseInt(properties2.getProperty("SpawnZ"));
         tagCompound.put("SpawnX", count6);
         tagCompound.put("SpawnY", count7);
         tagCompound.put("SpawnZ", count8);
         tagCompound.applyText("initialized", true);
      }
   }

   public static void applyIInventory(IInventory iInventory, IInventory iInventory2) {
      for (int index = 0; index < iInventory.processAction3(); index++) {
         iInventory2.processAction(index, iInventory.processAction(index));
      }
   }

   public static void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound) {
      if (DownloadInGeneralManager.isActive6()) {
         if (DownloadInGeneralManager.matchesAlertNotificationSound4(alertNotificationSound)) {
            alertNotificationSound.setEnabled7(true);

            try {
               genericListener.applyWorld(worldClient, alertNotificationSound);
            } catch (Exception exception) {
               LoggerEntry.applyAltListener(
                  OptionType.ERROR, "wdl.messages.generalError.failedToSaveChunk", new Object[]{alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, exception}
               );
            }
         }
      }
   }

   public static void updateState5() {
      LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.saveStarted", new Object[0]);
      enabled6 = true;
      new ThreadAdapter("WDL Save Thread").start();
   }

   public static NBTTagCompound resolveNBTTagCompound(HeuristicEngine heuristicEngine) {
      if (!DownloadInGeneralManager.isActive6()) {
         return new NBTTagCompound();
      } else {
         heuristicEngine.applyTextByText(
            StatCollector.getText("wdl.saveProgress.playerData.title", new Object[0]), 3 + HologramManager.resolveList(BreadthFirstScanner.class).size()
         );
         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.savingPlayer", new Object[0]);
         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.playerData.creatingNBT", new Object[0]), 1);
         NBTTagCompound tagCompound = new NBTTagCompound();
         entityPlayerSP.applyNBTTagCompound3(tagCompound);
         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.playerData.editingNBT", new Object[0]), 2);
         applyNBTTagCompound(tagCompound);
         int count2 = 3;

         for (StringEntry stringEntry : (Iterable<StringEntry>) (HologramManager.resolveList(BreadthFirstScanner.class))) {
            heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.playerData.extension", new Object[]{stringEntry.getTextAsText()}), count2);
            ((BreadthFirstScanner)stringEntry.specialListener).applyEntityPlayerSPByEntityPlayerSP(entityPlayerSP, listenerAdapter17, tagCompound);
            count2++;
         }

         heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.playerData.writingNBT", new Object[0]), count2);
         FileOutputStream outputStream = null;
         boolean flag = false ;

         try {
            flag = true;
            File file = new File(listenerAdapter17.getFile(), "playerdata");
            File file2 = new File(file, entityPlayerSP.getEntityUniqueID().toString() + ".dat.tmp");
            File file3 = new File(file, entityPlayerSP.getEntityUniqueID().toString() + ".dat");
            outputStream = new FileOutputStream(file2);
            LoadingNBTDataManager.applyNBTTagCompound(tagCompound, outputStream);
            if (file3.exists()) {
               file3.delete();
            }

            file2.renameTo(file3);
            flag = false;
         } catch (Exception exception) {
            throw new RuntimeException("Couldn't save the player!", exception);
         } finally {
            if (flag) {
               if (outputStream != null) {
                  try {
                     outputStream.close();
                  } catch (IOException ioException) {
                     throw new RuntimeException(ioException);
                  }
               }
            }
         }

         try {
            outputStream.close();
         } catch (IOException ioException2) {
            throw new RuntimeException(ioException2);
         }

         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.playerSaved", new Object[0]);
         return tagCompound;
      }
   }

   public static void applyNBTTagCompound(NBTTagCompound tagCompound) {
      String property = properties2.getProperty("PlayerHealth");
      if (!property.equals("keep")) {
         short shortValue = Short.parseShort(property);
         tagCompound.put("Health", shortValue);
      }

      String property2 = properties2.getProperty("PlayerFood");
      if (!property2.equals("keep")) {
         int count2 = Integer.parseInt(property2);
         tagCompound.put("foodLevel", count2);
         tagCompound.put("foodTickTimer", 0);
         if (count2 == 20) {
            tagCompound.put("foodSaturationLevel", 5.0F);
         } else {
            tagCompound.put("foodSaturationLevel", 0.0F);
         }

         tagCompound.put("foodExhaustionLevel", 0.0F);
      }

      if (properties2.getProperty("PlayerPos").equals("xyz")) {
         int count3 = Integer.parseInt(properties2.getProperty("PlayerX"));
         int count4 = Integer.parseInt(properties2.getProperty("PlayerY"));
         int count5 = Integer.parseInt(properties2.getProperty("PlayerZ"));
         NBTTagList nBTTagList = new NBTTagList();
         nBTTagList.applyLISTManager(new PrimaryDoubleEntry((double)count3 + 0.5));
         nBTTagList.applyLISTManager(new PrimaryDoubleEntry((double)count4 + 0.621));
         nBTTagList.applyLISTManager(new PrimaryDoubleEntry((double)count5 + 0.5));
         tagCompound.applyText("Pos", nBTTagList);
         NBTTagList nBTTagList2 = new NBTTagList();
         nBTTagList2.applyLISTManager(new PrimaryDoubleEntry(0.0));
         nBTTagList2.applyLISTManager(new PrimaryDoubleEntry(-1.0E-4));
         nBTTagList2.applyLISTManager(new PrimaryDoubleEntry(0.0));
         tagCompound.applyText("Motion", nBTTagList2);
         NBTTagList nBTTagList3 = new NBTTagList();
         nBTTagList3.applyLISTManager(new InternalClientManagerRegistry(0.0F));
         nBTTagList3.applyLISTManager(new InternalClientManagerRegistry(0.0F));
         tagCompound.applyText("Rotation", nBTTagList3);
      }

      if (entityPlayerSP.invulnerableManager.state2) {
         tagCompound.getNBTTagCompound("abilities").applyText("flying", true);
      }
   }

   public static String getText2() {
      return getText().replaceAll("\\W+", "_");
   }

   public static void updateState7() {
      if (enabled) {
         enabled = false;
         enabled7 = false;
         LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.downloadStopped", new Object[0]);
         updateState5();
      }
   }

   public static void applyHeuristicEngineByHeuristicEngine(HeuristicEngine heuristicEngine) {
      if (DownloadInGeneralManager.isActive3()) {
         File file = new File(listenerAdapter17.getFile(), "data");
         file.mkdirs();
         heuristicEngine.applyTextByText(StatCollector.getText("wdl.saveProgress.map.title", new Object[0]), hashMap2.size());
         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.savingMapItemData", new Object[0]);
         int count2 = 0;

         for (Entry entry : (Iterable<Entry>) (hashMap2.entrySet())) {
            heuristicEngine.applyText(StatCollector.getText("wdl.saveProgress.map.saving", new Object[]{entry.getKey()}), ++count2);
            File file2 = new File(file, "map_" + entry.getKey() + ".dat");
            NBTTagCompound tagCompound = new NBTTagCompound();
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            ((DimensionStringEntry)entry.getValue()).applyNBTTagCompound(tagCompound2);
            tagCompound.applyText("data", tagCompound2);

            try {
               LoadingNBTDataManager.applyNBTTagCompound(tagCompound, new FileOutputStream(file2));
            } catch (IOException ioException) {
               throw new RuntimeException("WDL: Exception while writing map data for map " + entry.getKey() + "!", ioException);
            }
         }

         LoggerEntry.applyAltListener(OptionType.SAVING, "wdl.messages.saving.mapItemDataSaved", new Object[0]);
      }
   }

   public static boolean isActiveAsFlag() {
      return entityPlayerSP != null && entityPlayerSP.getText4() != null ? entityPlayerSP.getText4().toLowerCase().contains("spigot") : false;
   }

   static {
      properties.setProperty("ServerName", "");
      properties.setProperty("WorldName", "");
      properties.setProperty("LinkedWorlds", "");
      properties.setProperty("Backup", "ZIP");
      properties.setProperty("AllowCheats", "true");
      properties.setProperty("GameType", "keep");
      properties.setProperty("Time", "keep");
      properties.setProperty("Weather", "keep");
      properties.setProperty("MapFeatures", "false");
      properties.setProperty("RandomSeed", "");
      properties.setProperty("MapGenerator", "void");
      properties.setProperty("GeneratorName", "flat");
      properties.setProperty("GeneratorVersion", "0");
      properties.setProperty("GeneratorOptions", ";0");
      properties.setProperty("Spawn", "player");
      properties.setProperty("SpawnX", "8");
      properties.setProperty("SpawnY", "127");
      properties.setProperty("SpawnZ", "8");
      properties.setProperty("PlayerPos", "keep");
      properties.setProperty("PlayerX", "8");
      properties.setProperty("PlayerY", "127");
      properties.setProperty("PlayerZ", "8");
      properties.setProperty("PlayerHealth", "20");
      properties.setProperty("PlayerFood", "20");
      properties.setProperty("Messages.enableAll", "true");
      properties.setProperty("Entity.TrackDistanceMode", "server");
      properties.setProperty("Entity.FireworksRocketEntity.Enabled", "false");
      properties.setProperty("Entity.EnderDragon.Enabled", "false");
      properties.setProperty("Entity.WitherBoss.Enabled", "false");
      properties.setProperty("Entity.PrimedTnt.Enabled", "false");
      properties.setProperty("Entity.null.Enabled", "false");
      properties.setProperty("EntityGroup.Other.Enabled", "true");
      properties.setProperty("EntityGroup.Hostile.Enabled", "true");
      properties.setProperty("EntityGroup.Passive.Enabled", "true");
      properties.setProperty("LastSaved", "-1");
      properties.setProperty("TutorialShown", "false");
      properties.setProperty("UpdateMinecraftVersion", "client");
      properties.setProperty("UpdateAllowBetas", "true");
      FileReader reader = null;
      boolean flag = false ;

      guardBlock: {
         searchBlock: {
            try {
               flag = true;
               reader = new FileReader(new File(minecraft.mcDataDir, "WorldDownloader.txt"));
               properties3.load(reader);
               flag = false;
               break searchBlock;
            } catch (Exception exception) {
               logger.debug("Failed to load global properties", exception);
               flag = false;
            } finally {
               if (flag) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Exception exception2) {
                        logger.warn("Failed to close global properties reader", exception2);
                     }
                  }
               }
            }

            if (reader != null) {
               try {
                  reader.close();
               } catch (Exception exception3) {
                  logger.warn("Failed to close global properties reader", exception3);
               }
            }
            break guardBlock;
         }

         try {
            reader.close();
         } catch (Exception exception4) {
            logger.warn("Failed to close global properties reader", exception4);
         }
      }

      properties4 = new Properties(properties3);
      properties2 = new Properties(properties4);
   }
}
