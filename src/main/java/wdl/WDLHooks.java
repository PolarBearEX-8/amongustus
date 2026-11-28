package wdl;

import wdl.api.IBlockEventListener;
import wdl.api.IChatMessageListener;
import wdl.api.IGuiHooksListener;
import wdl.api.ModInfo;
import wdl.api.WDLApi;
import wdl.gui.GuiWDL;
import wdl.gui.GuiWDLAbout;
import wdl.gui.GuiWDLChunkOverrides;
import wdl.gui.GuiWDLPermissions;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemMap;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class WDLHooks {
   private static final Profiler profiler = Minecraft.getMinecraft().mcProfiler;
   private static final int WDL_BUTTON_START_DOWNLOAD = 1464093811;
   private static final int WDL_BUTTON_OPTIONS = 1464093807;

   public static void onWorldRemoveEntityFromWorld(WorldClient world, int n) {

      try {
         if (!WDL.downloading) {
            return;
         }

         profiler.startSection("wdl.onRemoveEntityFromWorld");
         Entity entityByID = world.getEntityByID(n);
         profiler.startSection("Core");
         WDLEvents.onRemoveEntityFromWorld(entityByID);
         profiler.endSection();
         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onWorldRemoveEntityFromWorld event");
      }
   }

   public static void injectWDLButtons(GuiIngameMenu guiIngameMenu, List list) {
      int n = 0;

      for (Object obj : list) {
         GuiButton button = (GuiButton)obj;
         if (button.id == 5) {
            n = button.yPosition + 24;
            break;
         }
      }

      for (Object obj2 : list) {
         GuiButton button2 = (GuiButton)obj2;
         if (button2.yPosition >= n) {
            button2.yPosition += 24;
         }
      }

      GuiButton button3 = new GuiButton(1464093811, guiIngameMenu.width / 2 - 100, n, 168, 20, null);
      GuiButton button4 = new GuiButton(1464093807, guiIngameMenu.width / 2 + 72, n, 28, 20, I18n.format("wdl.gui.ingameMenu.settings", new Object[0]));
      if (WDL.mc.isIntegratedServerRunning()) {
         button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.singlePlayer", new Object[0]);
         button3.enabled = false;
      } else if (!WDLPluginChannels.canDownloadAtAll()) {
         if (WDLPluginChannels.canRequestPermissions()) {
            button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.request", new Object[0]);
         } else {
            button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.disabled", new Object[0]);
            button3.enabled = false;
         }
      } else if (WDL.saving) {
         button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.saving", new Object[0]);
         button3.enabled = false;
         button4.enabled = false;
      } else if (WDL.downloading) {
         button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.stop", new Object[0]);
      } else {
         button3.displayString = I18n.format("wdl.gui.ingameMenu.downloadStatus.start", new Object[0]);
      }

      list.add(button3);
      list.add(button4);
   }

   public static void onWorldClientTick(WorldClient world) {

      try {
         profiler.startSection("wdl");
         if (world != WDL.worldClient) {
            profiler.startSection("onWorldLoad");
            if (WDL.worldLoadingDeferred) {
               return;
            }

            WDLEvents.onWorldLoad(world);
            profiler.endSection();
         } else {
            profiler.startSection("inventoryCheck");
            if (WDL.downloading && WDL.thePlayer != null && WDL.thePlayer.openContainer != WDL.windowContainer) {
               if (WDL.thePlayer.openContainer != WDL.thePlayer.inventoryContainer) {
                  profiler.startSection("onItemGuiOpened");
                  profiler.startSection("Core");
                  WDLEvents.onItemGuiOpened();
                  profiler.endSection();
                  profiler.endSection();
               } else {
                  profiler.startSection("onItemGuiClosed");
                  profiler.startSection("Core");
                  boolean flag = WDLEvents.onItemGuiClosed();
                  profiler.endSection();
                  Container openContainer = WDL.thePlayer.openContainer;
                  if (WDL.lastEntity != null) {
                     Entity lastEntity = WDL.lastEntity;

                     for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IGuiHooksListener.class))) {
                        if (flag) {
                           break;
                        }

                        profiler.startSection(modInfo.id);
                        flag = ((IGuiHooksListener)modInfo.mod).onEntityGuiClosed(world, lastEntity, openContainer);
                        profiler.endSection();
                     }

                     if (!flag) {
                        WDLMessages.chatMessageTranslated(
                           WDLMessageTypes.ON_GUI_CLOSED_WARNING,
                           "wdl.messages.onGuiClosedWarning.unhandledEntity",
                           new Object[]{lastEntity}
                        );
                     }
                  } else {
                     BlockPos lastClickedBlock = WDL.lastClickedBlock;

                     for (ModInfo modInfo2 : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IGuiHooksListener.class))) {
                        if (flag) {
                           break;
                        }

                        profiler.startSection(modInfo2.id);
                        flag = ((IGuiHooksListener)modInfo2.mod).onBlockGuiClosed(world, lastClickedBlock, openContainer);
                        profiler.endSection();
                     }

                     if (!flag) {
                        WDLMessages.chatMessageTranslated(
                           WDLMessageTypes.ON_GUI_CLOSED_WARNING,
                           "wdl.messages.onGuiClosedWarning.unhandledTileEntity",
                           new Object[]{lastClickedBlock, world.getTileEntity(lastClickedBlock)}
                        );
                     }
                  }

                  profiler.endSection();
               }

               WDL.windowContainer = WDL.thePlayer.openContainer;
            }

            profiler.endSection();
         }

         profiler.startSection("capes");
         profiler.endSection();
         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onWorldClientTick event");
      }
   }

   public static void handleWDLButtonClick(GuiIngameMenu guiIngameMenu, GuiButton button) {
      if (button.enabled) {
         if (button.id == 1464093811) {
            if (WDL.mc.isIntegratedServerRunning()) {
               return;
            }

            if (WDL.downloading) {
               WDL.stopDownload();
            } else {
               if (!WDLPluginChannels.canDownloadAtAll()) {
                  if (WDLPluginChannels.canRequestPermissions()) {
                     WDL.mc.displayGuiScreen(new GuiWDLPermissions(guiIngameMenu));
                  } else {
                     button.enabled = false;
                  }

                  return;
               }

               if (WDLPluginChannels.hasChunkOverrides() && !WDLPluginChannels.canDownloadInGeneral()) {
                  WDL.mc.displayGuiScreen(new GuiWDLChunkOverrides(guiIngameMenu));
               } else {
                  WDL.startDownload();
               }
            }
         } else if (button.id == 1464093807) {
            if (WDL.mc.isIntegratedServerRunning()) {
               WDL.mc.displayGuiScreen(new GuiWDLAbout(guiIngameMenu));
            } else {
               WDL.mc.displayGuiScreen(new GuiWDL(guiIngameMenu));
            }
         } else if (button.id == 1) {
            WDL.stopDownload();
         }
      }
   }

   public static void onNHPCHandleChat(NetHandlerPlayClient netHandlerPlayClient, S02PacketChat packet) {

      try {
         if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            return;
         }

         if (!WDL.downloading) {
            return;
         }

         profiler.startSection("wdl.onChatMessage");
         String unformattedText = packet.getChatComponent().getUnformattedText();
         profiler.startSection("Core");
         WDLEvents.onChatMessage(unformattedText);
         profiler.endSection();

         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IChatMessageListener.class))) {
            profiler.startSection(modInfo.id);
            ((IChatMessageListener)modInfo.mod).onChat(WDL.worldClient, unformattedText);
            profiler.endSection();
         }

         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onNHPCHandleChat event");
      }
   }

   public static void onNHPCHandleMaps(NetHandlerPlayClient netHandlerPlayClient, S34PacketMaps packet) {

      try {
         if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            return;
         }

         if (!WDL.downloading) {
            return;
         }

         profiler.startSection("wdl.onMapDataLoaded");
         int mapId = packet.getMapId();
         MapData mapData = ItemMap.loadMapData(packet.getMapId(), WDL.worldClient);
         profiler.startSection("Core");
         WDLEvents.onMapDataLoaded(mapId, mapData);
         profiler.endSection();
         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onNHPCHandleMaps event");
      }
   }

   public static void onCrashReportPopulateEnvironment(long l, CrashReport crashReport) {
      crashReport.makeCategory("World Downloader Mod").addCrashSectionCallable("Info", new WDLCrashInfoCallable());
   }

   public static void onWorldDoPreChunk(WorldClient world, int n, int n2, boolean flag) {

      try {
         if (!WDL.downloading) {
            return;
         }

         profiler.startSection("wdl");
         if (!flag) {
            profiler.startSection("onChunkNoLongerNeeded");
            Chunk chunkFromChunkCoords = world.getChunkFromChunkCoords(n, n2);
            profiler.startSection("Core");
            WDLEvents.onChunkNoLongerNeeded(chunkFromChunkCoords);
            profiler.endSection();
            profiler.endSection();
         }

         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onWorldDoPreChunk event");
      }
   }

   public static void onNHPCHandleBlockAction(NetHandlerPlayClient netHandlerPlayClient, S24PacketBlockAction packet) {

      try {
         if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            return;
         }

         if (!WDL.downloading) {
            return;
         }

         profiler.startSection("wdl.onBlockEvent");
         BlockPos blockPosition = packet.getBlockPosition();
         Block blockType = packet.getBlockType();
         int data1 = packet.getData1();
         int data2 = packet.getData2();
         profiler.startSection("Core");
         WDLEvents.onBlockEvent(blockPosition, blockType, data1, data2);
         profiler.endSection();

         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IBlockEventListener.class))) {
            profiler.startSection(modInfo.id);
            ((IBlockEventListener)modInfo.mod).onBlockEvent(WDL.worldClient, blockPosition, blockType, data1, data2);
            profiler.endSection();
         }

         profiler.endSection();
      } catch (Throwable e) {
         WDL.crashed(e, "WDL mod: exception in onNHPCHandleBlockAction event");
      }
   }

}
