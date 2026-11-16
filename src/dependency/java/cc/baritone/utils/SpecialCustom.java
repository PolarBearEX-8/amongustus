package cc.baritone.utils;

import client.util.AlertNotificationSound;
import client.util.DonTKnowHowToSearchForAbstractSet;
import client.util.EndManager;
import client.util.ExtendedComparableAdapter;
import client.util.InternalCharArrayEntry;
import client.util.ListenerAdapter17;
import client.util.LoadingNBTDataManager;
import client.util.NetherFloatArrayEntry;
import client.util.RotationDeserializer;
import client.util.SecondaryFloatArrayEntry;
import client.util.SpecialByteArrayEntry;
import client.util.SpecialMapEntry;
import client.util.TargetFilter;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLitFurnace;
import net.minecraft.block.BlockNoteblock;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecialCustom extends RotationDeserializer {
   public static Logger logger2 = LogManager.getLogger();
   public final File file2;

   private NBTTagCompound resolveNBTTagCompound(AlertNotificationSound alertNotificationSound, World world) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("V", (byte)1);
      tagCompound.put("xPos", alertNotificationSound.indexOffset1);
      tagCompound.put("zPos", alertNotificationSound.indexOffset);
      tagCompound.put("LastUpdate", world.getTimestamp2());
      tagCompound.put("HeightMap", alertNotificationSound.getCountArray2());
      tagCompound.applyText("TerrainPopulated", alertNotificationSound.isEnabled7());
      tagCompound.applyText("LightPopulated", alertNotificationSound.isEnabled3());
      tagCompound.put("InhabitedTime", alertNotificationSound.getTimestamp());
      InternalCharArrayEntry[] internalCharArrayEntryArray = alertNotificationSound.getInternalCharArrayEntryArray();
      NBTTagList nBTTagList = new NBTTagList();
      boolean flag = !world.secondaryFloatArrayEntry.isEnabled();

      for (InternalCharArrayEntry internalCharArrayEntry : internalCharArrayEntryArray) {
         if (internalCharArrayEntry != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            tagCompound2.put("Y", (byte)(internalCharArrayEntry.getCount3() >> 4 & 0xFF));
            byte[] byteValueArray = new byte[internalCharArrayEntry.getCharCodeArray().length];
            SpecialByteArrayEntry specialByteArrayEntry = new SpecialByteArrayEntry();
            SpecialByteArrayEntry specialByteArrayEntry2 = null;

            for (int index = 0; index < internalCharArrayEntry.getCharCodeArray().length; index++) {
               char character = internalCharArrayEntry.getCharCodeArray()[index];
               int count = index & 15;
               int count2 = index >> 8 & 15;
               int count3 = index >> 4 & 15;
               if (character >> '\f' != 0) {
                  if (specialByteArrayEntry2 == null) {
                     specialByteArrayEntry2 = new SpecialByteArrayEntry();
                  }

                  specialByteArrayEntry2.applyIndex(count, count2, count3, character >> '\f');
               }

               byteValueArray[index] = (byte)(character >> 4 & 0xFF);
               specialByteArrayEntry.applyIndex(count, count2, count3, character & 15);
            }

            tagCompound2.put("Blocks", byteValueArray);
            tagCompound2.put("Data", specialByteArrayEntry.getByteFlagArray());
            if (specialByteArrayEntry2 != null) {
               tagCompound2.put("Add", specialByteArrayEntry2.getByteFlagArray());
            }

            tagCompound2.put("BlockLight", internalCharArrayEntry.getSpecialByteArrayEntry2().getByteFlagArray());
            if (flag) {
               tagCompound2.put("SkyLight", internalCharArrayEntry.getSpecialByteArrayEntry().getByteFlagArray());
            } else {
               tagCompound2.put("SkyLight", new byte[internalCharArrayEntry.getSpecialByteArrayEntry2().getByteFlagArray().length]);
            }

            nBTTagList.applyLISTManager(tagCompound2);
         }
      }

      tagCompound.applyText("Sections", nBTTagList);
      tagCompound.put("Biomes", alertNotificationSound.getByteFlagArray());
      alertNotificationSound.setEnabled2(false);
      NBTTagList nBTTagList2 = this.resolveNBTTagList(alertNotificationSound);
      tagCompound.applyText("Entities", nBTTagList2);
      NBTTagList nBTTagList3 = this.resolveNBTTagListByAlertNotificatio(alertNotificationSound);
      tagCompound.applyText("TileEntities", nBTTagList3);
      List list = world.resolveList(alertNotificationSound, false);
      if (list != null) {
         long timestamp = world.getTimestamp2();
         NBTTagList nBTTagList4 = new NBTTagList();

         for (ExtendedComparableAdapter extendedComparableAdapter : (Iterable<ExtendedComparableAdapter>)(Iterable<?>)list) {
            NBTTagCompound tagCompound3 = new NBTTagCompound();
            ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(extendedComparableAdapter.getBlock());
            tagCompound3.put("i", object == null ? "" : object.toString());
            tagCompound3.put("x", extendedComparableAdapter.blockPos.getX());
            tagCompound3.put("y", extendedComparableAdapter.blockPos.getY());
            tagCompound3.put("z", extendedComparableAdapter.blockPos.getZ());
            tagCompound3.put("t", (int)(extendedComparableAdapter.timestamp - timestamp));
            tagCompound3.put("p", extendedComparableAdapter.count);
            nBTTagList4.applyLISTManager(tagCompound3);
         }

         tagCompound.applyText("TileTicks", nBTTagList4);
      }

      return tagCompound;
   }

   public SpecialCustom(File file) {
      super(file);
      this.file2 = file;
   }

   public static boolean matchesEntity(Entity entity, short shortValue) {
      if (entity instanceof EntityPlayer) {
         return false;
      } else if (!AStarCoordinator.matchesEntity(0x21ad78dd88a3L, entity)) {
         LoggerEntry.applyAltListener(OptionType.REMOVE_ENTITY, "wdl.messages.removeEntity.notSavingUserPreference", new Object[]{entity});
         return false;
      } else {
         return true;
      }
   }

   @Override
   public void applyWorld(World world, AlertNotificationSound alertNotificationSound) throws client.util.ExceptionAdapter, IOException {
      world.updateState6();
      NBTTagCompound tagCompound = this.resolveNBTTagCompound(alertNotificationSound, world);
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      tagCompound2.applyText("Level", tagCompound);
      this.applyInterpolationCollector(alertNotificationSound.getInterpolationCollector(), tagCompound2);
   }

   private static File resolveFile(ListenerAdapter17 listenerAdapter, SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      File file = listenerAdapter.getFile();
      if (secondaryFloatArrayEntry instanceof NetherFloatArrayEntry) {
         File file3 = new File(file, "DIM-1");
         file3.mkdirs();
         return file3;
      } else if (secondaryFloatArrayEntry instanceof EndManager) {
         File file4 = new File(file, "DIM1");
         file4.mkdirs();
         return file4;
      } else {
         return file;
      }
   }

   public static void applyBlockPos(BlockPos blockPos, NBTTagCompound tagCompound, EXISTINGIMPORTEDEnum eXISTINGIMPORTEDEnum) {
      for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(BlockPosSpecialListener.class)) {
         try {
            if (((BlockPosSpecialListener)stringEntry.specialListener).matchesBlockPos(blockPos, tagCompound, eXISTINGIMPORTEDEnum)) {
               ((BlockPosSpecialListener)stringEntry.specialListener).applyBlockPos(blockPos, tagCompound, eXISTINGIMPORTEDEnum);
               LoggerEntry.applyAltListener(OptionType.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.edited", new Object[]{blockPos, stringEntry.getTextAsText()});
            }
         } catch (Exception exception) {
            throw new RuntimeException(
               "Failed to edit tile entity at "
                  + blockPos
                  + " with extension "
                  + stringEntry
                  + "; NBT is now "
                  + tagCompound
                  + " (this may be the initial value, an edited value, or a partially edited value)",
               exception
            );
         }
      }
   }

   public NBTTagList resolveNBTTagList(AlertNotificationSound alertNotificationSound) {
      NBTTagList nBTTagList = new NBTTagList();
      if (!DownloadInGeneralManager.matchesAlertNotificationSound3(alertNotificationSound)) {
         return nBTTagList;
      } else {
         java.util.ArrayList list = new java.util.ArrayList();

         for (DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet : alertNotificationSound.getDonTKnowHowToSearchForAbstractSetArray()) {
            list.addAll(donTKnowHowToSearchForAbstractSet);
         }

         for (Entity entity : (Iterable<Entity>)(Iterable<?>)Pokechu22WorldDownloaderManager.hashMultimap.get(alertNotificationSound.getInterpolationCollector())) {
            entity.isDead = false;
            list.add(entity);
         }

         for (Entity entity2 : (Iterable<Entity>)(Iterable<?>)list) {
            if (entity2 == null) {
               logger2.warn("[WDL] Null entity in chunk at " + alertNotificationSound.getInterpolationCollector());
            } else if (matchesEntity(entity2, (short)0)) {
               for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)HologramManager.resolveList(SecondaryListener12Extension.class)) {
                  try {
                     if (((SecondaryListener12Extension)stringEntry.specialListener).matchesEntity(entity2)) {
                        ((SecondaryListener12Extension)stringEntry.specialListener).applyEntity(entity2);
                     }
                  } catch (Exception exception) {
                     throw new RuntimeException(
                        "Failed to edit entity " + entity2 + " for chunk at " + alertNotificationSound.getInterpolationCollector() + " with extension " + stringEntry, exception
                     );
                  }
               }

               NBTTagCompound tagCompound = new NBTTagCompound();

               try {
                  if (entity2.matchesNBTTagCompound(tagCompound)) {
                     alertNotificationSound.setEnabled2(true);
                     nBTTagList.applyLISTManager(tagCompound);
                  }
               } catch (Exception exception2) {
                  LoggerEntry.applyAltListener(
                     OptionType.ERROR, "wdl.messages.generalError.failedToSaveEntity", new Object[]{entity2, alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, exception2}
                  );
                  logger2.warn("Compound: " + tagCompound);
                  logger2.warn("Entity metadata dump:");

                  try {
                     List list2 = entity2.getDataWatcher().getList();
                     if (list2 == null) {
                        logger2.warn("No entries (getAllWatched() returned null)");
                     } else {
                        logger2.warn(list2);

                        for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)list2) {
                           if (targetFilter != null) {
                              logger2.warn(
                                 "WatchableObject [getDataValueId()="
                                    + targetFilter.getCount()
                                    + ", getObject()="
                                    + targetFilter.getDataRef()
                                    + ", getObjectType()="
                                    + targetFilter.getCount2()
                                    + ", isWatched()="
                                    + targetFilter.isEnabled()
                                    + "]"
                              );
                           }
                        }
                     }
                  } catch (Exception exception3) {
                     logger2.warn("Failed to complete dump: ", exception2);
                  }

                  logger2.warn("End entity metadata dump");
               }
            }
         }

         return nBTTagList;
      }
   }

   public NBTTagList resolveNBTTagListByAlertNotificatio(AlertNotificationSound alertNotificationSound) {
      NBTTagList nBTTagList = new NBTTagList();
      if (!DownloadInGeneralManager.matchesAlertNotificationSound(alertNotificationSound)) {
         return nBTTagList;
      } else {
         Map map = alertNotificationSound.getMap();
         Map map2 = this.resolveMap(alertNotificationSound);
         Map entry = (Map)Pokechu22WorldDownloaderManager.hashMap.get(alertNotificationSound.getInterpolationCollector());
         if (entry == null) {
            entry = new HashMap();
         }

         HashSet set = new HashSet();
         set.addAll(map.keySet());
         set.addAll(map2.keySet());
         set.addAll(entry.keySet());

         for (BlockPos blockPos : (Iterable<BlockPos>)(Iterable<?>)set) {
            if (entry.containsKey(blockPos)) {
               NBTTagCompound tagCompound = new NBTTagCompound();
               TileEntity tileEntity = (TileEntity)entry.get(blockPos);

               try {
                  tileEntity.applyNBTTagCompound(tagCompound);
               } catch (Exception exception) {
                  LoggerEntry.applyAltListener(
                     OptionType.ERROR, "wdl.messages.generalError.failedToSaveTE", new Object[]{tileEntity, blockPos, alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, exception}
                  );
                  logger2.warn("Compound: " + tagCompound);
                  continue;
               }

               String text = tagCompound.resolveText("id") + " (" + tileEntity.getClass().getCanonicalName() + ")";
               LoggerEntry.applyAltListener(OptionType.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.usingNew", new Object[]{text, blockPos});
               applyBlockPos(blockPos, tagCompound, EXISTINGIMPORTEDEnum.NEW);
               nBTTagList.applyLISTManager(tagCompound);
            } else if (map2.containsKey(blockPos)) {
               NBTTagCompound tagCompound2 = (NBTTagCompound)map2.get(blockPos);
               String text2 = tagCompound2.resolveText("id");
               LoggerEntry.applyAltListener(OptionType.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.usingOld", new Object[]{text2, blockPos});
               applyBlockPos(blockPos, tagCompound2, EXISTINGIMPORTEDEnum.IMPORTED);
               nBTTagList.applyLISTManager(tagCompound2);
            } else if (map.containsKey(blockPos)) {
               TileEntity tileEntity2 = (TileEntity)map.get(blockPos);
               NBTTagCompound tagCompound3 = new NBTTagCompound();

               try {
                  tileEntity2.applyNBTTagCompound(tagCompound3);
               } catch (Exception exception2) {
                  LoggerEntry.applyAltListener(
                     OptionType.ERROR, "wdl.messages.generalError.failedToSaveTE", new Object[]{tileEntity2, blockPos, alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, exception2}
                  );
                  logger2.warn("Compound: " + tagCompound3);
                  continue;
               }

               applyBlockPos(blockPos, tagCompound3, EXISTINGIMPORTEDEnum.EXISTING);
               nBTTagList.applyLISTManager(tagCompound3);
            }
         }

         return nBTTagList;
      }
   }

   public static SpecialCustom resolveSpecialCustom(ListenerAdapter17 listenerAdapter, SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      return new SpecialCustom(resolveFile(listenerAdapter, secondaryFloatArrayEntry));
   }

   public boolean matchesText(String text, BlockPos blockPos, Block block, NBTTagCompound tagCompound, AlertNotificationSound alertNotificationSound) {
      if (block instanceof BlockTrappedChest && text.equals("Chest")) {
         return true;
      } else if (block instanceof BlockDispenser && text.equals("Trap")) {
         return true;
      } else if (block instanceof BlockDropper && text.equals("Dropper")) {
         return true;
      } else if (block instanceof BlockLitFurnace && text.equals("Furnace")) {
         return true;
      } else if (block instanceof BlockNoteblock && text.equals("Music")) {
         return true;
      } else if (block instanceof BlockBrewingStand && text.equals("Cauldron")) {
         return true;
      } else if (block instanceof BlockHopper && text.equals("Hopper")) {
         return true;
      } else if (block instanceof BlockBeacon && text.equals("Beacon")) {
         return true;
      } else {
         Iterator iterator = HologramManager.resolveList(RuntimeComponentAdapter.class).iterator();

         while (iterator.hasNext()) {
            if (((RuntimeComponentAdapter)((StringEntry)iterator.next()).specialListener).matchesText(text, blockPos, block, tagCompound, alertNotificationSound)) {
               return true;
            }
         }

         return false;
      }
   }

   public Map resolveMap(AlertNotificationSound alertNotificationSound) {
      DataInputStream dataInputStream = null;
      HashMap map = new HashMap();

      HashMap map2;
      try {
         dataInputStream = SpecialMapEntry.resolveDataInputStream(this.file2, alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset);
         if (dataInputStream != null) {
            NBTTagList extendedLoggerEntry = LoadingNBTDataManager.resolveNBTTagCompound(dataInputStream).getNBTTagCompound("Level").getExtendedLoggerEntry("TileEntities", 10);
            if (extendedLoggerEntry != null) {
               for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
                  NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
                  String text = nBTTagCompound.resolveText("id");
                  BlockPos blockPos = new BlockPos(nBTTagCompound.computeCount("x"), nBTTagCompound.computeCount("y"), nBTTagCompound.computeCount("z"));
                  Block block = alertNotificationSound.resolveBlock(blockPos);
                  if (this.matchesText(text, blockPos, block, nBTTagCompound, alertNotificationSound)) {
                     map.put(blockPos, nBTTagCompound);
                  } else {
                     LoggerEntry.applyAltListener(OptionType.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.notImporting", new Object[]{text, blockPos});
                  }
               }
            }

            return map;
         }

         map2 = map;
      } catch (Exception exception) {
         LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.failedToImportTE", new Object[]{alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, exception});
         return map;
      } finally {
         if (dataInputStream != null) {
            try {
               dataInputStream.close();
            } catch (Exception exception2) {
               throw new RuntimeException(exception2);
            }
         }
      }

      return map2;
   }
}
