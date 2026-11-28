package wdl;

import net.minecraft.util.MovingObjectPosition;
import wdl.api.IWorldLoadListener;
import wdl.api.ModInfo;
import wdl.api.WDLApi;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class WDLEvents {
   private static final Profiler profiler = Minecraft.getMinecraft().mcProfiler;

   public static void onItemGuiOpened() {
      if (WDL.downloading) {
         if (WDL.mc.objectMouseOver != null) {
            if (WDL.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
               WDL.lastEntity = WDL.mc.objectMouseOver.entityHit;
            } else {
               WDL.lastEntity = null;
               WDL.lastClickedBlock = WDL.mc.objectMouseOver.getBlockPos();
            }
         }
      }
   }

   public static void onChatMessage(String str) {
      if (WDL.downloading && str.startsWith("Seed: ")) {
         String str2 = str.substring(6);
         WDL.worldProps.setProperty("RandomSeed", str2);
         if (WDL.worldProps.getProperty("MapGenerator", "void").equals("void")) {
            WDL.worldProps.setProperty("MapGenerator", "default");
            WDL.worldProps.setProperty("GeneratorName", "default");
            WDL.worldProps.setProperty("GeneratorVersion", "1");
            WDL.worldProps.setProperty("GeneratorOptions", "");
            WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.seedAndGenSet", new Object[]{str2});
         } else {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.seedSet", new Object[]{str2});
         }
      }
   }

   public static void onChunkNoLongerNeeded(Chunk chunk) {
      if (WDL.downloading) {
         if (chunk != null) {
            if (WDLPluginChannels.canSaveChunk(chunk)) {
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ON_CHUNK_NO_LONGER_NEEDED,
                  "wdl.messages.onChunkNoLongerNeeded.saved",
                  new Object[]{chunk.xPosition, chunk.zPosition}
               );
               WDL.saveChunk(chunk);
            } else {
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ON_CHUNK_NO_LONGER_NEEDED,
                  "wdl.messages.onChunkNoLongerNeeded.didNotSave",
                  new Object[]{chunk.xPosition, chunk.zPosition}
               );
            }
         }
      }
   }

   public static boolean onItemGuiClosed() {
      if (!WDL.downloading) {
         return true;
      } else {
         String str = "";
         if (WDL.thePlayer.ridingEntity != null && WDL.thePlayer.ridingEntity instanceof EntityHorse && WDL.windowContainer instanceof ContainerHorseInventory
            )
          {
            EntityHorse entityHorse = (EntityHorse)ReflectionUtils.stealAndGetField(WDL.windowContainer, EntityHorse.class);
            if (entityHorse == WDL.thePlayer.ridingEntity) {
               if (!WDLPluginChannels.canSaveEntities(entityHorse.chunkCoordX, entityHorse.chunkCoordZ)) {
                  WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveEntities", new Object[0]);
                  return true;
               }

               EntityHorse ridingEntity = (EntityHorse)WDL.thePlayer.ridingEntity;
               AnimalChest animalChest = new AnimalChest("HorseChest", !ridingEntity.isChested() || ridingEntity.getHorseType() != 1 && ridingEntity.getHorseType() != 2 ? 2 : 17);
               animalChest.setCustomName(ridingEntity.getName());
               WDL.saveContainerItems(WDL.windowContainer, animalChest, 0);
               animalChest.addInventoryChangeListener(ridingEntity);
               ReflectionUtils.stealAndSetField(ridingEntity, AnimalChest.class, animalChest);
               WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedRiddenHorse", new Object[0]);
               return true;
            }
         }

         if (WDL.lastEntity != null) {
            if (!WDLPluginChannels.canSaveEntities(WDL.lastEntity.chunkCoordX, WDL.lastEntity.chunkCoordZ)) {
               WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveEntities", new Object[0]);
               return true;
            } else {
               if (WDL.lastEntity instanceof EntityMinecartChest && WDL.windowContainer instanceof ContainerChest) {
                  EntityMinecartChest lastEntity = (EntityMinecartChest)WDL.lastEntity;

                  for (int i = 0; i < lastEntity.getSizeInventory(); i++) {
                     lastEntity.setInventorySlotContents(i, WDL.windowContainer.getSlot(i).getStack());
                  }

                  str = "storageMinecart";
               } else if (WDL.lastEntity instanceof EntityMinecartHopper && WDL.windowContainer instanceof ContainerHopper) {
                  EntityMinecartHopper lastEntity2 = (EntityMinecartHopper)WDL.lastEntity;

                  for (int j = 0; j < lastEntity2.getSizeInventory(); j++) {
                     lastEntity2.setInventorySlotContents(j, WDL.windowContainer.getSlot(j).getStack());
                  }

                  str = "hopperMinecart";
               } else if (WDL.lastEntity instanceof EntityVillager && WDL.windowContainer instanceof ContainerMerchant) {
                  EntityVillager lastEntity3 = (EntityVillager)WDL.lastEntity;
                  short s = 17931;
                  MerchantRecipeList recipes = ((IMerchant)ReflectionUtils.stealAndGetField(WDL.windowContainer, IMerchant.class))
                     .getRecipes(WDL.thePlayer);
                  ReflectionUtils.stealAndSetField(lastEntity3, MerchantRecipeList.class, recipes);
                  str = "villager";
               } else {
                  if (!(WDL.lastEntity instanceof EntityHorse) || !(WDL.windowContainer instanceof ContainerHorseInventory)) {
                     return false;
                  }

                  EntityHorse lastEntity4 = (EntityHorse)WDL.lastEntity;
                  AnimalChest animalChest2 = new AnimalChest("HorseChest", !lastEntity4.isChested() || lastEntity4.getHorseType() != 1 && lastEntity4.getHorseType() != 2 ? 2 : 17);
                  short s2 = 19486;
                  animalChest2.setCustomName(lastEntity4.getName());
                  WDL.saveContainerItems(WDL.windowContainer, animalChest2, 0);
                  animalChest2.addInventoryChangeListener(lastEntity4);
                  ReflectionUtils.stealAndSetField(lastEntity4, AnimalChest.class, animalChest2);
                  str = "horse";
               }

               WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedEntity." + str, new Object[0]);
               return true;
            }
         } else {
            TileEntity tileEntity = WDL.worldClient.getTileEntity(WDL.lastClickedBlock);
            if (tileEntity == null) {
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ON_GUI_CLOSED_WARNING,
                  "wdl.messages.onGuiClosedWarning.couldNotGetTE",
                  new Object[]{WDL.lastClickedBlock}
               );
               return true;
            } else if (!WDLPluginChannels.canSaveContainers(tileEntity.getPos().getX() << 4, tileEntity.getPos().getZ() << 4)) {
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveTileEntities", new Object[0]
               );
               return true;
            } else {
               if (WDL.windowContainer instanceof ContainerChest && tileEntity instanceof TileEntityChest) {
                  if (WDL.windowContainer.inventorySlots.size() > 63) {
                     BlockPos lastClickedBlock = WDL.lastClickedBlock;
                     BlockPos pos = null;
                     BlockPos pos2 = null;
                     TileEntityChest tileEntityChest = null;
                     TileEntityChest tileEntityChest2 = null;
                     BlockPos pos3 = lastClickedBlock.add(0, 0, 1);
                     TileEntity tileEntity2 = WDL.worldClient.getTileEntity(pos3);
                     if (tileEntity2 instanceof TileEntityChest && ((TileEntityChest)tileEntity2).getChestType() == ((TileEntityChest)tileEntity).getChestType()) {
                        tileEntityChest = (TileEntityChest)tileEntity;
                        tileEntityChest2 = (TileEntityChest)tileEntity2;
                        pos = lastClickedBlock;
                        pos2 = pos3;
                     }

                     pos3 = lastClickedBlock.add(0, 0, -1);
                     tileEntity2 = WDL.worldClient.getTileEntity(pos3);
                     if (tileEntity2 instanceof TileEntityChest && ((TileEntityChest)tileEntity2).getChestType() == ((TileEntityChest)tileEntity).getChestType()) {
                        tileEntityChest = (TileEntityChest)tileEntity2;
                        tileEntityChest2 = (TileEntityChest)tileEntity;
                        pos = pos3;
                        pos2 = lastClickedBlock;
                     }

                     pos3 = lastClickedBlock.add(1, 0, 0);
                     tileEntity2 = WDL.worldClient.getTileEntity(pos3);
                     if (tileEntity2 instanceof TileEntityChest && ((TileEntityChest)tileEntity2).getChestType() == ((TileEntityChest)tileEntity).getChestType()) {
                        tileEntityChest = (TileEntityChest)tileEntity;
                        tileEntityChest2 = (TileEntityChest)tileEntity2;
                        pos = lastClickedBlock;
                        pos2 = pos3;
                     }

                     pos3 = lastClickedBlock.add(-1, 0, 0);
                     tileEntity2 = WDL.worldClient.getTileEntity(pos3);
                     if (tileEntity2 instanceof TileEntityChest && ((TileEntityChest)tileEntity2).getChestType() == ((TileEntityChest)tileEntity).getChestType()) {
                        tileEntityChest = (TileEntityChest)tileEntity2;
                        tileEntityChest2 = (TileEntityChest)tileEntity;
                        pos = pos3;
                        pos2 = lastClickedBlock;
                     }

                     if (tileEntityChest == null || tileEntityChest2 == null || pos == null || pos2 == null) {
                        WDLMessages.chatMessageTranslated(
                           WDLMessageTypes.ERROR, "wdl.messages.onGuiClosedWarning.failedToFindDoubleChest", new Object[0]
                        );
                        return true;
                     }

                     WDL.saveContainerItems(WDL.windowContainer, tileEntityChest, 0);
                     short s3 = 28518;
                     WDL.saveContainerItems(WDL.windowContainer, tileEntityChest2, 27);
                     WDL.saveTileEntity(pos, tileEntityChest);
                     WDL.saveTileEntity(pos2, tileEntityChest2);
                     str = "doubleChest";
                  } else {
                     WDL.saveContainerItems(WDL.windowContainer, (TileEntityChest)tileEntity, 0);
                     short s4 = 266;
                     WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                     str = "singleChest";
                  }
               } else if (WDL.windowContainer instanceof ContainerChest && tileEntity instanceof TileEntityEnderChest) {
                  InventoryEnderChest inventoryEnderChest = WDL.thePlayer.getInventoryEnderChest();
                  int sizeInventory = inventoryEnderChest.getSizeInventory();
                  int size = WDL.windowContainer.inventorySlots.size();

                  for (int k = 0; k < size && k < sizeInventory; k++) {
                     inventoryEnderChest.setInventorySlotContents(k, WDL.windowContainer.getSlot(k).getStack());
                  }

                  str = "enderChest";
               } else if (WDL.windowContainer instanceof ContainerBrewingStand && tileEntity instanceof TileEntityBrewingStand) {
                  IInventory inventory = (IInventory)ReflectionUtils.stealAndGetField(WDL.windowContainer, IInventory.class);
                  short s5 = 26408;
                  WDL.saveContainerItems(WDL.windowContainer, (TileEntityBrewingStand)tileEntity, 0);
                  WDL.saveInventoryFields(inventory, (TileEntityBrewingStand)tileEntity);
                  WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                  str = "brewingStand";
               } else if (WDL.windowContainer instanceof ContainerDispenser && tileEntity instanceof TileEntityDispenser) {
                  WDL.saveContainerItems(WDL.windowContainer, (TileEntityDispenser)tileEntity, 0);
                  short s6 = 29269;
                  WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                  str = "dispenser";
               } else if (WDL.windowContainer instanceof ContainerFurnace && tileEntity instanceof TileEntityFurnace) {
                  IInventory inventory2 = (IInventory)ReflectionUtils.stealAndGetField(WDL.windowContainer, IInventory.class);
                  short s7 = 12876;
                  WDL.saveContainerItems(WDL.windowContainer, (TileEntityFurnace)tileEntity, 0);
                  WDL.saveInventoryFields(inventory2, (TileEntityFurnace)tileEntity);
                  WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                  str = "furnace";
               } else if (WDL.windowContainer instanceof ContainerHopper && tileEntity instanceof TileEntityHopper) {
                  WDL.saveContainerItems(WDL.windowContainer, (TileEntityHopper)tileEntity, 0);
                  short s8 = 10906;
                  WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                  str = "hopper";
               } else {
                  if (!(WDL.windowContainer instanceof ContainerBeacon) || !(tileEntity instanceof TileEntityBeacon)) {
                     return false;
                  }

                  IInventory tileBeacon = ((ContainerBeacon)WDL.windowContainer).getTileBeacon();
                  TileEntityBeacon tileEntityBeacon = (TileEntityBeacon)tileEntity;
                  WDL.saveContainerItems(WDL.windowContainer, tileEntityBeacon, 0);
                  WDL.saveInventoryFields(tileBeacon, tileEntityBeacon);
                  WDL.saveTileEntity(WDL.lastClickedBlock, tileEntity);
                  str = "beacon";
               }

               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedTileEntity." + str, new Object[0]
               );
               return true;
            }
         }
      }
   }

   public static void onPluginChannelPacket(String str, byte[] bytes) {
      WDLPluginChannels.onPluginChannelPacket(str, bytes);
   }

   public static void onMapDataLoaded(int n, MapData mapData) {
      if (WDL.downloading) {
         if (WDLPluginChannels.canSaveMaps()) {
            WDL.newMapDatas.put(n, mapData);
            WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_MAP_SAVED, "wdl.messages.onMapSaved", new Object[]{n});
         }
      }
   }

   public static void onBlockEvent(BlockPos pos, Block block, int n, int n2) {
      if (WDL.downloading) {
         if (WDLPluginChannels.canSaveTileEntities(pos.getX() << 4, pos.getZ() << 4)) {
            if (block == Blocks.noteblock) {
               TileEntityNote tileEntityNote = new TileEntityNote();
               tileEntityNote.note = (byte)(n2 % 25);
               WDL.worldClient.setTileEntity(pos, tileEntityNote);
               WDL.saveTileEntity(pos, tileEntityNote);
               WDLMessages.chatMessageTranslated(WDLMessageTypes.ON_BLOCK_EVENT, "wdl.messages.onBlockEvent.noteblock", new Object[]{pos, n2, tileEntityNote});
            }
         }
      }
   }

   public static void onWorldLoad(WorldClient world) {
      profiler.startSection("Core");
      if (!WDL.mc.isIntegratedServerRunning()) {
         if (WDL.downloading) {
            if (!WDL.saving) {
               WDLMessages.chatMessageTranslated(WDLMessageTypes.INFO, "wdl.messages.generalInfo.worldChanged", new Object[0]);
               WDL.worldLoadingDeferred = true;
               WDL.startSaveThread();
            }

            profiler.endSection();
         } else {
            boolean flag = WDL.loadWorld();
            profiler.endSection();

            for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IWorldLoadListener.class))) {
               profiler.startSection(modInfo.id);
               ((IWorldLoadListener)modInfo.mod).onWorldLoad(world, flag);
               profiler.endSection();
            }
         }
      }
   }

   public static void onRemoveEntityFromWorld(Entity entity) {
      if (WDL.downloading && entity != null && WDLPluginChannels.canSaveEntities(entity.chunkCoordX, entity.chunkCoordZ)) {
         if (!EntityUtils.isEntityEnabled2(entity)) {
            WDLMessages.chatMessageTranslated(WDLMessageTypes.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveUserPref", new Object[]{entity});
            return;
         }

         int entityTrackDistance = EntityUtils.getEntityTrackDistance(entity);
         if (entityTrackDistance < 0) {
            WDLMessages.chatMessageTranslated(
               WDLMessageTypes.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveUnrecognizedDistance", new Object[]{entity}
            );
            return;
         }

         double distance = entity.getDistance(WDL.thePlayer.posX, entity.posY, WDL.thePlayer.posZ);
         if (distance > (double)entityTrackDistance) {
            WDLMessages.chatMessageTranslated(
               WDLMessageTypes.REMOVE_ENTITY, "wdl.messages.removeEntity.savingDistance", new Object[]{entity, distance, entityTrackDistance}
            );
            entity.chunkCoordX = MathHelper.floor_double(entity.posX / 16.0);
            entity.chunkCoordZ = MathHelper.floor_double(entity.posZ / 16.0);
            WDL.newEntities.put(new ChunkCoordIntPair(entity.chunkCoordX, entity.chunkCoordZ), entity);
            return;
         }

         WDLMessages.chatMessageTranslated(
            WDLMessageTypes.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveDistance", new Object[]{entity, distance, entityTrackDistance}
         );
      }
   }
}
