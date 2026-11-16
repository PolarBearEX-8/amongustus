package cc.baritone.api;

import augustus.modes.movement.MovementContainerEnderchestMode;
import cc.baritone.utils.AStarCoordinator;
import cc.baritone.utils.DepthFirstService;
import cc.baritone.utils.DownloadInGeneralManager;
import cc.baritone.utils.ExtendedListener12Extension;
import cc.baritone.utils.HologramManager;
import cc.baritone.utils.LoggerEntry;
import cc.baritone.utils.OptionType;
import cc.baritone.utils.Pokechu22WorldDownloaderManager;
import cc.baritone.utils.StringEntry;
import client.event.LocalListener;
import client.util.AdvancedContainerAdapter;
import client.util.AlertNotificationSound;
import client.util.AuxiliaryArrayListAdapter;
import client.util.BezierEvaluator;
import client.util.DimensionStringEntry;
import client.util.ExtendedContainerAdapter;
import client.util.IInventoryBridge;
import client.util.InternalContainerAdapter;
import client.util.InterpolationBridge;
import client.util.InterpolationCollector;
import client.util.InterpolationPipeline;
import client.util.MovingObjectType;
import client.util.SecondaryContainerAdapter;
import client.util.TertiaryContainerAdapter;
import client.util.UNKNOWNManager;
import client.util.VectorEvaluator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPosEntry;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCauldron;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityMusic;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CustomItemEntry {
   public static final UNKNOWNManager uNKNOWNManager;

   public static boolean isActive() {
      if (!Pokechu22WorldDownloaderManager.enabled) {
         return true;
      } else {
         if (Pokechu22WorldDownloaderManager.entityPlayerSP.ridingEntity != null
            && Pokechu22WorldDownloaderManager.entityPlayerSP.ridingEntity instanceof EntityHorse
            && Pokechu22WorldDownloaderManager.container instanceof TertiaryContainerAdapter) {
            EntityHorse entityHorse = (EntityHorse)DepthFirstService.resolveTarget(Pokechu22WorldDownloaderManager.container, EntityHorse.class);
            if (entityHorse == Pokechu22WorldDownloaderManager.entityPlayerSP.ridingEntity) {
               if (!DownloadInGeneralManager.checkIndex3(entityHorse.count7, entityHorse.count8)) {
                  LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveEntities", new Object[0]);
                  return true;
               }

               EntityHorse ridingEntity = (EntityHorse)Pokechu22WorldDownloaderManager.entityPlayerSP.ridingEntity;
               IInventoryBridge iInventoryBridge = new IInventoryBridge("HorseChest", !ridingEntity.isActive32() || ridingEntity.getCount12() != 1 && ridingEntity.getCount12() != 2 ? 2 : 17);
               iInventoryBridge.applyText(ridingEntity.getText());
               Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, iInventoryBridge, 0);
               iInventoryBridge.applyPostUpdateDispatcher(ridingEntity);
               DepthFirstService.applyTarget(ridingEntity, IInventoryBridge.class, iInventoryBridge);
               LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedRiddenHorse", new Object[0]);
               return true;
            }
         }

         if (Pokechu22WorldDownloaderManager.entity != null) {
            if (!DownloadInGeneralManager.checkIndex3(Pokechu22WorldDownloaderManager.entity.count7, Pokechu22WorldDownloaderManager.entity.count8)) {
               LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveEntities", new Object[0]);
               return true;
            } else {
               String text;
               if (Pokechu22WorldDownloaderManager.entity instanceof VectorEvaluator
                  && Pokechu22WorldDownloaderManager.container instanceof AdvancedContainerAdapter) {
                  VectorEvaluator entity = (VectorEvaluator)Pokechu22WorldDownloaderManager.entity;

                  for (int index = 0; index < entity.processAction4(); index++) {
                     entity.processAction(index, Pokechu22WorldDownloaderManager.container.getSlot(index).getStack());
                  }

                  text = "storageMinecart";
               } else if (Pokechu22WorldDownloaderManager.entity instanceof BlockPosEntry
                  && Pokechu22WorldDownloaderManager.container instanceof InterpolationPipeline) {
                  BlockPosEntry entity2 = (BlockPosEntry)Pokechu22WorldDownloaderManager.entity;

                  for (int index2 = 0; index2 < entity2.processAction4(); index2++) {
                     entity2.processAction(index2, Pokechu22WorldDownloaderManager.container.getSlot(index2).getStack());
                  }

                  text = "hopperMinecart";
               } else if (Pokechu22WorldDownloaderManager.entity instanceof EntityVillager
                  && Pokechu22WorldDownloaderManager.container instanceof ExtendedContainerAdapter) {
                  EntityVillager entity3 = (EntityVillager)Pokechu22WorldDownloaderManager.entity;
                  AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = ((LocalListener)DepthFirstService.resolveTarget(
                        Pokechu22WorldDownloaderManager.container, LocalListener.class
                     ))
                     .resolveAuxiliaryArrayListAdapterByEntityPlayer(Pokechu22WorldDownloaderManager.entityPlayerSP);
                  DepthFirstService.applyTarget(entity3, AuxiliaryArrayListAdapter.class, auxiliaryArrayListAdapter);
                  text = "villager";
               } else {
                  if (!(Pokechu22WorldDownloaderManager.entity instanceof EntityHorse)
                     || !(Pokechu22WorldDownloaderManager.container instanceof TertiaryContainerAdapter)) {
                     return false;
                  }

                  EntityHorse entity4 = (EntityHorse)Pokechu22WorldDownloaderManager.entity;
                  IInventoryBridge iInventoryBridge2 = new IInventoryBridge(
                     "HorseChest", !entity4.isActive32() || entity4.getCount12() != 1 && entity4.getCount12() != 2 ? 2 : 17
                  );
                  iInventoryBridge2.applyText(entity4.getText());
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, iInventoryBridge2, 0);
                  iInventoryBridge2.applyPostUpdateDispatcher(entity4);
                  DepthFirstService.applyTarget(entity4, IInventoryBridge.class, iInventoryBridge2);
                  text = "horse";
               }

               LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedEntity." + text, new Object[0]);
               return true;
            }
         } else {
            TileEntity tileEntity = Pokechu22WorldDownloaderManager.worldClient.resolveTileEntity(Pokechu22WorldDownloaderManager.blockPos);
            if (tileEntity == null) {
               LoggerEntry.applyAltListener(
                  OptionType.ON_GUI_CLOSED_WARNING, "wdl.messages.onGuiClosedWarning.couldNotGetTE", new Object[]{Pokechu22WorldDownloaderManager.blockPos}
               );
               return true;
            } else if (!DownloadInGeneralManager.checkIndex2(tileEntity.getBlockPos().getX() << 4, tileEntity.getBlockPos().getZ() << 4)) {
               LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.cannotSaveTileEntities", new Object[0]);
               return true;
            } else {
               String text2;
               if (Pokechu22WorldDownloaderManager.container instanceof AdvancedContainerAdapter && tileEntity instanceof TileEntityChest) {
                  if (Pokechu22WorldDownloaderManager.container.itemList2.size() > 63) {
                     BlockPos blockPos = Pokechu22WorldDownloaderManager.blockPos;
                     BlockPos blockPos2 = null;
                     BlockPos blockPos3 = null;
                     TileEntityChest tileEntityChest = null;
                     TileEntityChest tileEntityChest2 = null;
                     BlockPos blockPos4 = blockPos.add(0, 0, 1);
                     TileEntity tileEntity2 = Pokechu22WorldDownloaderManager.worldClient.resolveTileEntity(blockPos4);
                     if (tileEntity2 instanceof TileEntityChest && ((TileEntityChest)tileEntity2).getCountAsIndex() == ((TileEntityChest)tileEntity).getCountAsIndex()) {
                        tileEntityChest = (TileEntityChest)tileEntity;
                        tileEntityChest2 = (TileEntityChest)tileEntity2;
                        blockPos2 = blockPos;
                        blockPos3 = blockPos4;
                     }

                     BlockPos blockPos5 = blockPos.add(0, 0, -1);
                     TileEntity tileEntity3 = Pokechu22WorldDownloaderManager.worldClient.resolveTileEntity(blockPos5);
                     if (tileEntity3 instanceof TileEntityChest && ((TileEntityChest)tileEntity3).getCountAsIndex() == ((TileEntityChest)tileEntity).getCountAsIndex()) {
                        tileEntityChest = (TileEntityChest)tileEntity3;
                        tileEntityChest2 = (TileEntityChest)tileEntity;
                        blockPos2 = blockPos5;
                        blockPos3 = blockPos;
                     }

                     BlockPos blockPos6 = blockPos.add(1, 0, 0);
                     TileEntity tileEntity4 = Pokechu22WorldDownloaderManager.worldClient.resolveTileEntity(blockPos6);
                     if (tileEntity4 instanceof TileEntityChest && ((TileEntityChest)tileEntity4).getCountAsIndex() == ((TileEntityChest)tileEntity).getCountAsIndex()) {
                        tileEntityChest = (TileEntityChest)tileEntity;
                        tileEntityChest2 = (TileEntityChest)tileEntity4;
                        blockPos2 = blockPos;
                        blockPos3 = blockPos6;
                     }

                     BlockPos blockPos7 = blockPos.add(-1, 0, 0);
                     TileEntity tileEntity5 = Pokechu22WorldDownloaderManager.worldClient.resolveTileEntity(blockPos7);
                     if (tileEntity5 instanceof TileEntityChest && ((TileEntityChest)tileEntity5).getCountAsIndex() == ((TileEntityChest)tileEntity).getCountAsIndex()) {
                        tileEntityChest = (TileEntityChest)tileEntity5;
                        tileEntityChest2 = (TileEntityChest)tileEntity;
                        blockPos2 = blockPos7;
                        blockPos3 = blockPos;
                     }

                     if (tileEntityChest == null || tileEntityChest2 == null || blockPos2 == null || blockPos3 == null) {
                        LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.onGuiClosedWarning.failedToFindDoubleChest", new Object[0]);
                        return true;
                     }

                     Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, tileEntityChest, 0);
                     Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, tileEntityChest2, 27);
                     Pokechu22WorldDownloaderManager.applyBlockPos(blockPos2, tileEntityChest);
                     Pokechu22WorldDownloaderManager.applyBlockPos(blockPos3, tileEntityChest2);
                     text2 = "doubleChest";
                  } else {
                     Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, (TileEntityChest)tileEntity, 0);
                     Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                     text2 = "singleChest";
                  }
               } else if (Pokechu22WorldDownloaderManager.container instanceof AdvancedContainerAdapter && tileEntity instanceof TileEntityEnderchest) {
                  MovementContainerEnderchestMode movementContainerEnderchestMode = Pokechu22WorldDownloaderManager.entityPlayerSP.getMovementContainerEnderchestMode();
                  int count = movementContainerEnderchestMode.processAction4();
                  int size = Pokechu22WorldDownloaderManager.container.itemList2.size();

                  for (int index3 = 0; index3 < size && index3 < count; index3++) {
                     movementContainerEnderchestMode.processAction(index3, Pokechu22WorldDownloaderManager.container.getSlot(index3).getStack());
                  }

                  text2 = "enderChest";
               } else if (Pokechu22WorldDownloaderManager.container instanceof InternalContainerAdapter && tileEntity instanceof TileEntityCauldron) {
                  IInventory iInventory = (IInventory)DepthFirstService.resolveTarget(Pokechu22WorldDownloaderManager.container, IInventory.class);
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, (TileEntityCauldron)tileEntity, 0);
                  Pokechu22WorldDownloaderManager.applyIInventory(iInventory, (TileEntityCauldron)tileEntity);
                  Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                  text2 = "brewingStand";
               } else if (Pokechu22WorldDownloaderManager.container instanceof InterpolationBridge && tileEntity instanceof TileEntityTrap) {
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, (TileEntityTrap)tileEntity, 0);
                  Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                  text2 = "dispenser";
               } else if (Pokechu22WorldDownloaderManager.container instanceof SecondaryContainerAdapter && tileEntity instanceof TileEntityFurnace) {
                  IInventory iInventory2 = (IInventory)DepthFirstService.resolveTarget(Pokechu22WorldDownloaderManager.container, IInventory.class);
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, (TileEntityFurnace)tileEntity, 0);
                  Pokechu22WorldDownloaderManager.applyIInventory(iInventory2, (TileEntityFurnace)tileEntity);
                  Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                  text2 = "furnace";
               } else if (Pokechu22WorldDownloaderManager.container instanceof InterpolationPipeline && tileEntity instanceof TileEntityHopper) {
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, (TileEntityHopper)tileEntity, 0);
                  Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                  text2 = "hopper";
               } else {
                  if (!(Pokechu22WorldDownloaderManager.container instanceof BezierEvaluator) || !(tileEntity instanceof TileEntityBeacon)) {
                     return false;
                  }

                  IInventory iInventory3 = ((BezierEvaluator)Pokechu22WorldDownloaderManager.container).getIInventory();
                  TileEntityBeacon tileEntityBeacon = (TileEntityBeacon)tileEntity;
                  Pokechu22WorldDownloaderManager.applyContainer(Pokechu22WorldDownloaderManager.container, tileEntityBeacon, 0);
                  Pokechu22WorldDownloaderManager.applyIInventory(iInventory3, tileEntityBeacon);
                  Pokechu22WorldDownloaderManager.applyBlockPos(Pokechu22WorldDownloaderManager.blockPos, tileEntity);
                  text2 = "beacon";
               }

               LoggerEntry.applyAltListener(OptionType.ON_GUI_CLOSED_INFO, "wdl.messages.onGuiClosedInfo.savedTileEntity." + text2, new Object[0]);
               return true;
            }
         }
      }
   }

   public static void applyWorldClient(WorldClient world) {
      uNKNOWNManager.applyTextByText("Core");
      if (!Pokechu22WorldDownloaderManager.minecraft.isGamePaused()) {
         if (Pokechu22WorldDownloaderManager.enabled) {
            if (!Pokechu22WorldDownloaderManager.enabled6) {
               LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.worldChanged", new Object[0]);
               Pokechu22WorldDownloaderManager.enabled2 = true;
               Pokechu22WorldDownloaderManager.updateState5();
            }

            uNKNOWNManager.updateState();
         } else {
            boolean active = Pokechu22WorldDownloaderManager.isActive();
            uNKNOWNManager.updateState();

            for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable)HologramManager.resolveList(ExtendedListener12Extension.class)) {
               uNKNOWNManager.applyTextByText(stringEntry.labelText);
               ((ExtendedListener12Extension)stringEntry.specialListener).applyWorldClient(world, active);
               uNKNOWNManager.updateState();
            }
         }
      }
   }

   public static void applyText(String text) {
      if (Pokechu22WorldDownloaderManager.enabled && text.startsWith("Seed: ")) {
         String text2 = text.substring(6);
         Pokechu22WorldDownloaderManager.properties2.setProperty("RandomSeed", text2);
         if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator", "void").equals("void")) {
            Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "default");
            Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "default");
            Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "1");
            Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
            LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.seedAndGenSet", new Object[]{text2});
         } else {
            LoggerEntry.applyAltListener(OptionType.INFO, "wdl.messages.generalInfo.seedSet", new Object[]{text2});
         }
      }
   }

   public static void applyEntityByEntity(Entity entity) {
      if (Pokechu22WorldDownloaderManager.enabled && entity != null && DownloadInGeneralManager.checkIndex3(entity.count7, entity.count8)) {
         if (!AStarCoordinator.matchesEntity(0x21ad78dd88a3L, entity)) {
            LoggerEntry.applyAltListener(OptionType.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveUserPref", new Object[]{entity});
            return;
         }

         int count = AStarCoordinator.computeCount(entity, 0x1eb379adf259L);
         if (count < 0) {
            LoggerEntry.applyAltListener(OptionType.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveUnrecognizedDistance", new Object[]{entity});
            return;
         }

         double doubleItem = entity.getDouble4(Pokechu22WorldDownloaderManager.entityPlayerSP.posX, entity.posY, Pokechu22WorldDownloaderManager.entityPlayerSP.posZ);
         if (doubleItem > (double)count) {
            LoggerEntry.applyAltListener(OptionType.REMOVE_ENTITY, "wdl.messages.removeEntity.savingDistance", new Object[]{entity, doubleItem, count});
            entity.count7 = MathHelper.ceiling_double_int(entity.posX / 16.0);
            entity.count8 = MathHelper.ceiling_double_int(entity.posZ / 16.0);
            Pokechu22WorldDownloaderManager.hashMultimap.put(new InterpolationCollector(entity.count7, entity.count8), entity);
            return;
         }

         LoggerEntry.applyAltListener(OptionType.REMOVE_ENTITY, "wdl.messages.removeEntity.allowingRemoveDistance", new Object[]{entity, doubleItem, count});
      }
   }

   public static void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound) {
      if (Pokechu22WorldDownloaderManager.enabled) {
         if (alertNotificationSound != null) {
            if (DownloadInGeneralManager.matchesAlertNotificationSound4(alertNotificationSound)) {
               LoggerEntry.applyAltListener(
                  OptionType.ON_CHUNK_NO_LONGER_NEEDED, "wdl.messages.onChunkNoLongerNeeded.saved", new Object[]{alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset}
               );
               Pokechu22WorldDownloaderManager.applyAlertNotificationSound(alertNotificationSound);
            } else {
               LoggerEntry.applyAltListener(
                  OptionType.ON_CHUNK_NO_LONGER_NEEDED, "wdl.messages.onChunkNoLongerNeeded.didNotSave", new Object[]{alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset}
               );
            }
         }
      }
   }

   public static void applyText(String text, byte[] byteValueArray) {
      DownloadInGeneralManager.applyText(text, byteValueArray);
   }

   public static void applyBlockPos(BlockPos blockPos, Block block, int count, int count2) {
      if (Pokechu22WorldDownloaderManager.enabled) {
         if (DownloadInGeneralManager.checkIndex(blockPos.getX() << 4, blockPos.getZ() << 4)) {
            if (block == Blocks.block143) {
               TileEntityMusic tileEntityMusic = new TileEntityMusic();
               tileEntityMusic.byteFlag = (byte)(count2 % 25);
               Pokechu22WorldDownloaderManager.worldClient.applyBlockPos(blockPos, tileEntityMusic);
               Pokechu22WorldDownloaderManager.applyBlockPos(blockPos, tileEntityMusic);
               LoggerEntry.applyAltListener(OptionType.ON_BLOCK_EVENT, "wdl.messages.onBlockEvent.noteblock", new Object[]{blockPos, count2, tileEntityMusic});
            }
         }
      }
   }

   public static void applyDimensionStringEntry(int count, DimensionStringEntry dimensionStringEntry) {
      if (Pokechu22WorldDownloaderManager.enabled) {
         if (DownloadInGeneralManager.isActive3()) {
            Pokechu22WorldDownloaderManager.hashMap2.put(count, dimensionStringEntry);
            LoggerEntry.applyAltListener(OptionType.ON_MAP_SAVED, "wdl.messages.onMapSaved", new Object[]{count});
         }
      }
   }

   public static void updateState() {
      if (Pokechu22WorldDownloaderManager.enabled) {
         if (Pokechu22WorldDownloaderManager.minecraft.objectMouseOver != null) {
            if (Pokechu22WorldDownloaderManager.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
               Pokechu22WorldDownloaderManager.entity = Pokechu22WorldDownloaderManager.minecraft.objectMouseOver.entityHit;
            } else {
               Pokechu22WorldDownloaderManager.entity = null;
               Pokechu22WorldDownloaderManager.blockPos = Pokechu22WorldDownloaderManager.minecraft.objectMouseOver.getBlockPos();
            }
         }
      }
   }

   static {
      uNKNOWNManager = Minecraft.getMinecraft().uNKNOWNManager;
   }
}
