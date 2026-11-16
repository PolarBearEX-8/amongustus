package cc.baritone.api;

import cc.baritone.utils.BreadthFirstTracker;
import cc.baritone.utils.DepthFirstAuditor;
import cc.baritone.utils.DepthFirstDispatcher;
import cc.baritone.utils.DepthFirstFilter;
import cc.baritone.utils.DiagonalCalculator;
import cc.baritone.utils.DiagonalTracker;
import cc.baritone.utils.DownloadInGeneralManager;
import cc.baritone.utils.HeuristicProvider;
import cc.baritone.utils.HeuristicRegistry;
import cc.baritone.utils.HologramManager;
import cc.baritone.utils.LoggerEntry;
import cc.baritone.utils.OptionType;
import cc.baritone.utils.Pokechu22WorldDownloaderManager;
import cc.baritone.utils.StringEntry;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.util.AlertNotificationSound;
import client.util.DimensionStringEntry;
import client.util.UNKNOWNManager;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemFilledMap;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;

public class SecondaryItemEntry {
   public static final UNKNOWNManager uNKNOWNManager;
   public static final int count2 = 1464093811;
   public static final int count = 1464093807;

   public static void applyWorldClient(WorldClient world, int count3) {
      try {
         if (!Pokechu22WorldDownloaderManager.enabled) {
            return;
         }

         uNKNOWNManager.applyTextByText("wdl.onRemoveEntityFromWorld");
         Entity entityByID = world.getEntityByID(count3);
         uNKNOWNManager.applyTextByText("Core");
         CustomItemEntry.applyEntityByEntity(entityByID);
         uNKNOWNManager.updateState();
         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onWorldRemoveEntityFromWorld event");
      }
   }

   public static void applyNetHandlerPlayClient(NetHandlerPlayClient netHandlerPlayClient, S34PacketMaps packet) {
      try {
         if (!Minecraft.getMinecraft().isSingleplayer()) {
            return;
         }

         if (!Pokechu22WorldDownloaderManager.enabled) {
            return;
         }

         uNKNOWNManager.applyTextByText("wdl.onMapDataLoaded");
         int count3 = packet.getCount4();
         DimensionStringEntry dimensionStringEntry = ItemFilledMap.resolveDimensionStringEntry(packet.getCount4(), Pokechu22WorldDownloaderManager.worldClient);
         uNKNOWNManager.applyTextByText("Core");
         CustomItemEntry.applyDimensionStringEntry(count3, dimensionStringEntry);
         uNKNOWNManager.updateState();
         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onNHPCHandleMaps event");
      }
   }

   public static void applyNetHandlerPlayClient(NetHandlerPlayClient netHandlerPlayClient, S24PacketBlockAction packet) {
      try {
         if (!Minecraft.getMinecraft().isSingleplayer()) {
            return;
         }

         if (!Pokechu22WorldDownloaderManager.enabled) {
            return;
         }

         uNKNOWNManager.applyTextByText("wdl.onBlockEvent");
         BlockPos blockPos = packet.getBlockPos();
         Block block = packet.getBlock();
         int count3 = packet.getCount2();
         int count4 = packet.getCount();
         uNKNOWNManager.applyTextByText("Core");
         CustomItemEntry.applyBlockPos(blockPos, block, count3, count4);
         uNKNOWNManager.updateState();

         for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(DepthFirstDispatcher.class)) {
            uNKNOWNManager.applyTextByText(stringEntry.labelText);
            ((DepthFirstDispatcher)stringEntry.specialListener).applyWorldClientByWorldClient(Pokechu22WorldDownloaderManager.worldClient, blockPos, block, count3, count4);
            uNKNOWNManager.updateState();
         }

         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onNHPCHandleBlockAction event");
      }
   }

   public static void applyWorldClient(WorldClient world) {
      try {
         uNKNOWNManager.applyTextByText("wdl");
         if (world != Pokechu22WorldDownloaderManager.worldClient) {
            uNKNOWNManager.applyTextByText("onWorldLoad");
            if (Pokechu22WorldDownloaderManager.enabled2) {
               return;
            }

            CustomItemEntry.applyWorldClient(world);
            uNKNOWNManager.updateState();
         } else {
            uNKNOWNManager.applyTextByText("inventoryCheck");
            if (Pokechu22WorldDownloaderManager.enabled
               && Pokechu22WorldDownloaderManager.entityPlayerSP != null
               && Pokechu22WorldDownloaderManager.entityPlayerSP.container2 != Pokechu22WorldDownloaderManager.container) {
               if (Pokechu22WorldDownloaderManager.entityPlayerSP.container2 != Pokechu22WorldDownloaderManager.entityPlayerSP.container) {
                  uNKNOWNManager.applyTextByText("onItemGuiOpened");
                  uNKNOWNManager.applyTextByText("Core");
                  CustomItemEntry.updateState();
                  uNKNOWNManager.updateState();
                  uNKNOWNManager.updateState();
               } else {
                  uNKNOWNManager.applyTextByText("onItemGuiClosed");
                  uNKNOWNManager.applyTextByText("Core");
                  boolean active = CustomItemEntry.isActive();
                  uNKNOWNManager.updateState();
                  Container container = Pokechu22WorldDownloaderManager.entityPlayerSP.container2;
                  if (Pokechu22WorldDownloaderManager.entity != null) {
                     Entity entity = Pokechu22WorldDownloaderManager.entity;

                     for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(DepthFirstAuditor.class)) {
                        if (active) {
                           break;
                        }

                        uNKNOWNManager.applyTextByText(stringEntry.labelText);
                        active = ((DepthFirstAuditor)stringEntry.specialListener).matchesWorldClientByWorldClient(world, entity, container);
                        uNKNOWNManager.updateState();
                     }

                     if (!active) {
                        LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_WARNING, "wdl.messages.onGuiClosedWarning.unhandledEntity", new Object[]{entity});
                     }
                  } else {
                     BlockPos blockPos = Pokechu22WorldDownloaderManager.blockPos;

                     for (StringEntry stringEntry2 : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(DepthFirstAuditor.class)) {
                        if (active) {
                           break;
                        }

                        uNKNOWNManager.applyTextByText(stringEntry2.labelText);
                        active = ((DepthFirstAuditor)stringEntry2.specialListener).matchesWorldClientByWorldClient(world, blockPos, container);
                        uNKNOWNManager.updateState();
                     }

                     if (!active) {
                        LoggerEntry.applyAltListener(
                           OptionType.ON_GUI_CLOSED_WARNING,
                           "wdl.messages.onGuiClosedWarning.unhandledTileEntity",
                           new Object[]{blockPos, world.resolveTileEntity(blockPos)}
                        );
                     }
                  }

                  uNKNOWNManager.updateState();
               }

               Pokechu22WorldDownloaderManager.container = Pokechu22WorldDownloaderManager.entityPlayerSP.container2;
            }

            uNKNOWNManager.updateState();
         }

         uNKNOWNManager.applyTextByText("capes");
         uNKNOWNManager.updateState();
         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onWorldClientTick event");
      }
   }

   public static void applyCrashReport(CrashReport crashReport) {
      crashReport.resolveCrashReportCategory("World Downloader Mod").applyText("Core", new DepthFirstFilter());
   }

   public static void applyGuiScreenServerList(GuiScreenServerList guiScreenServerList, List list) {
      int count3 = 0;

      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>)(Iterable<?>)list) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5) {
            count3 = auxiliaryTexturesGuiIconsPngManagerAdapter.count3 + 24;
            break;
         }
      }

      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2 : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>)(Iterable<?>)list) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 >= count3) {
            auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 += 24;
         }
      }

      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(1464093811, guiScreenServerList.width / 2 - 100, count3, 168, 20, null);
      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         1464093807, guiScreenServerList.width / 2 + 72, count3, 28, 20, StatCollector.getText("wdl.gui.ingameMenu.settings", new Object[0])
      );
      if (Pokechu22WorldDownloaderManager.minecraft.isGamePaused()) {
         auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.singlePlayer", new Object[0]);
         auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      } else if (!DownloadInGeneralManager.isActive6()) {
         if (DownloadInGeneralManager.isActive9()) {
            auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.request", new Object[0]);
         } else {
            auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.disabled", new Object[0]);
            auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
         }
      } else if (Pokechu22WorldDownloaderManager.enabled6) {
         auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.saving", new Object[0]);
         auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
         auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = false;
      } else if (Pokechu22WorldDownloaderManager.enabled) {
         auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.stop", new Object[0]);
      } else {
         auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("wdl.gui.ingameMenu.downloadStatus.start", new Object[0]);
      }

      list.add(auxiliaryTexturesGuiIconsPngManagerAdapter3);
      list.add(auxiliaryTexturesGuiIconsPngManagerAdapter4);
   }

   public static void applyWorldClientByWorldClient(short shortValue, WorldClient world, int count3, int count4, short shortValue2, int count5, boolean flag) {
      try {
         if (!Pokechu22WorldDownloaderManager.enabled) {
            return;
         }

         uNKNOWNManager.applyTextByText("wdl");
         if (!flag) {
            uNKNOWNManager.applyTextByText("onChunkNoLongerNeeded");
            AlertNotificationSound loggerEntry = world.getLoggerEntry2(count3, count4);
            uNKNOWNManager.applyTextByText("Core");
            CustomItemEntry.applyAlertNotificationSound(loggerEntry);
            uNKNOWNManager.updateState();
            uNKNOWNManager.updateState();
         }

         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onWorldDoPreChunk event");
      }
   }

   public static void applyNetHandlerPlayClient(NetHandlerPlayClient netHandlerPlayClient, S02PacketChat packet) {
      try {
         if (!Minecraft.getMinecraft().isSingleplayer()) {
            return;
         }

         if (!Pokechu22WorldDownloaderManager.enabled) {
            return;
         }

         uNKNOWNManager.applyTextByText("wdl.onChatMessage");
         String string = packet.getChatComponent().getString();
         uNKNOWNManager.applyTextByText("Core");
         CustomItemEntry.applyText(string);
         uNKNOWNManager.updateState();

         for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(BreadthFirstTracker.class)) {
            uNKNOWNManager.applyTextByText(stringEntry.labelText);
            ((BreadthFirstTracker)stringEntry.specialListener).applyWorldClientByWorldClient(Pokechu22WorldDownloaderManager.worldClient, string);
            uNKNOWNManager.updateState();
         }

         uNKNOWNManager.updateState();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "WDL mod: exception in onNHPCHandleChat event");
      }
   }

   public static void applyGuiScreenServerList(GuiScreenServerList guiScreenServerList, AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1464093811) {
            if (Pokechu22WorldDownloaderManager.minecraft.isGamePaused()) {
               return;
            }

            if (Pokechu22WorldDownloaderManager.enabled) {
               Pokechu22WorldDownloaderManager.updateState7();
            } else {
               if (!DownloadInGeneralManager.isActive6()) {
                  if (DownloadInGeneralManager.isActive9()) {
                     Minecraft mc = Pokechu22WorldDownloaderManager.minecraft;
                     HeuristicProvider heuristicProvider = new HeuristicProvider(guiScreenServerList);
                     mc.displayGuiScreen(heuristicProvider);
                  } else {
                     auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;
                  }

                  return;
               }

               if (DownloadInGeneralManager.isActiveAsFlag() && !DownloadInGeneralManager.isActive4()) {
                  Minecraft mc2 = Pokechu22WorldDownloaderManager.minecraft;
                  DiagonalCalculator diagonalCalculator = new DiagonalCalculator(guiScreenServerList);
                  mc2.displayGuiScreen(diagonalCalculator);
               } else {
                  Pokechu22WorldDownloaderManager.updateState3();
               }
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1464093807) {
            if (Pokechu22WorldDownloaderManager.minecraft.isGamePaused()) {
               Minecraft mc3 = Pokechu22WorldDownloaderManager.minecraft;
               DiagonalTracker diagonalTracker = new DiagonalTracker(guiScreenServerList);
               mc3.displayGuiScreen(diagonalTracker);
            } else {
               Minecraft mc4 = Pokechu22WorldDownloaderManager.minecraft;
               HeuristicRegistry heuristicRegistry = new HeuristicRegistry(guiScreenServerList);
               mc4.displayGuiScreen(heuristicRegistry);
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            Pokechu22WorldDownloaderManager.updateState7();
         }
      }
   }

   static {
      uNKNOWNManager = Minecraft.getMinecraft().uNKNOWNManager;
   }
}
