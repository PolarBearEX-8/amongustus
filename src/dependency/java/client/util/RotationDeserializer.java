package client.util;

import client.event.GenericListener;
import client.event.SharedListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RotationDeserializer implements SharedListener, GenericListener {
   public static final Logger logger = LogManager.getLogger();
   public Map map = new ConcurrentHashMap();
   public Set set = Collections.newSetFromMap(new ConcurrentHashMap());
   public final File file;
   public boolean enabled = false;

   private void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, World world, NBTTagCompound tagCompound) {
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
      NBTTagList nBTTagList2 = new NBTTagList();

      for (int index2 = 0; index2 < alertNotificationSound.getDonTKnowHowToSearchForAbstractSetArray().length; index2++) {
         for (Object entityObj : alertNotificationSound.getDonTKnowHowToSearchForAbstractSetArray()[index2]) {
            Entity entity = (Entity)entityObj;
            NBTTagCompound tagCompound3 = new NBTTagCompound();
            if (entity.matchesNBTTagCompound(tagCompound3)) {
               alertNotificationSound.setEnabled2(true);
               nBTTagList2.applyLISTManager(tagCompound3);
            }
         }
      }

      tagCompound.applyText("Entities", nBTTagList2);
      NBTTagList nBTTagList3 = new NBTTagList();

      for (Object tileEntityObj : alertNotificationSound.getMap().values()) {
         TileEntity tileEntity = (TileEntity)tileEntityObj;
         NBTTagCompound tagCompound4 = new NBTTagCompound();
         tileEntity.applyNBTTagCompound(tagCompound4);
         nBTTagList3.applyLISTManager(tagCompound4);
      }

      tagCompound.applyText("TileEntities", nBTTagList3);
      List list = world.resolveList(alertNotificationSound, false);
      if (list != null) {
         long timestamp = world.getTimestamp2();
         NBTTagList nBTTagList4 = new NBTTagList();

         for (Object extendedComparableAdapterObj : list) {
            ExtendedComparableAdapter extendedComparableAdapter = (ExtendedComparableAdapter)extendedComparableAdapterObj;
            NBTTagCompound tagCompound5 = new NBTTagCompound();
            ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(extendedComparableAdapter.getBlock());
            tagCompound5.put("i", object == null ? "" : object.toString());
            tagCompound5.put("x", extendedComparableAdapter.blockPos.getX());
            tagCompound5.put("y", extendedComparableAdapter.blockPos.getY());
            tagCompound5.put("z", extendedComparableAdapter.blockPos.getZ());
            tagCompound5.put("t", (int)(extendedComparableAdapter.timestamp - timestamp));
            tagCompound5.put("p", extendedComparableAdapter.count);
            nBTTagList4.applyLISTManager(tagCompound5);
         }

         tagCompound.applyText("TileTicks", nBTTagList4);
      }
   }

   public AlertNotificationSound resolveAlertNotificationSound(World world, int count, int count2, NBTTagCompound tagCompound) {
      if (!tagCompound.matchesText("Level", 10)) {
         logger.error("Chunk file at " + count + "," + count2 + " is missing level data, skipping");
         return null;
      } else {
         NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Level");
         if (!nBTTagCompound.matchesText("Sections", 9)) {
            logger.error("Chunk file at " + count + "," + count2 + " is missing block data, skipping");
            return null;
         } else {
            AlertNotificationSound alertNotificationSound = this.resolveAlertNotificationSound(world, nBTTagCompound);
            if (!alertNotificationSound.checkIndex2(count, count2)) {
               logger.error(
                  "Chunk file at "
                     + count
                     + ","
                     + count2
                     + " is in the wrong location; relocating. (Expected "
                     + count
                     + ", "
                     + count2
                     + ", got "
                     + alertNotificationSound.indexOffset1
                     + ", "
                     + alertNotificationSound.indexOffset
                     + ")"
               );
               nBTTagCompound.put("xPos", count);
               nBTTagCompound.put("zPos", count2);
               alertNotificationSound = this.resolveAlertNotificationSound(world, nBTTagCompound);
            }

            return alertNotificationSound;
         }
      }
   }

   @Override
   public void applyWorldByWorld(World world, AlertNotificationSound alertNotificationSound) throws IOException {
   }

   public RotationDeserializer(File file2) {
      this.file = file2;
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(World world, int count, int count2) throws IOException {
      InterpolationCollector interpolationCollector = new InterpolationCollector(count, count2);
      NBTTagCompound tagCompound = (NBTTagCompound)this.map.get(interpolationCollector);
      if (tagCompound == null) {
         DataInputStream dataInputStream = SpecialMapEntry.resolveDataInputStream(this.file, count, count2);
         if (dataInputStream == null) {
            return null;
         }

         tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(dataInputStream);
         dataInputStream.close();
      }

      return this.resolveAlertNotificationSound(world, count, count2, tagCompound);
   }

   @Override
   public void updateState() {
      try {
         this.enabled = true;

         while (true) {
            if (this.isActive()) {
            }
         }
      } finally {
         this.enabled = false;
      }
   }

   private void applyInterpolationCollectorByInterpolationCol(InterpolationCollector interpolationCollector, NBTTagCompound tagCompound) throws IOException {
      DataOutputStream dataOutputStream = SpecialMapEntry.resolveDataOutputStream(this.file, interpolationCollector.count3, interpolationCollector.count2);
      LoadingNBTDataManager.applyNBTTagCompound(tagCompound, (DataOutput)dataOutputStream);
      dataOutputStream.close();
   }

   private AlertNotificationSound resolveAlertNotificationSound(World world, NBTTagCompound tagCompound) {
      int count = tagCompound.computeCount("xPos");
      int count2 = tagCompound.computeCount("zPos");
      AlertNotificationSound alertNotificationSound = new AlertNotificationSound(world, count, count2);
      alertNotificationSound.applyIndexArray(tagCompound.resolveIndexArray("HeightMap"));
      alertNotificationSound.setEnabled7(tagCompound.matchesText("TerrainPopulated"));
      alertNotificationSound.setEnabled3(tagCompound.matchesText("LightPopulated"));
      alertNotificationSound.setTimestamp(tagCompound.computeTimestamp("InhabitedTime"));
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Sections", 10);
      InternalCharArrayEntry[] internalCharArrayEntryArray = new InternalCharArrayEntry[16];
      boolean flag = !world.secondaryFloatArrayEntry.isEnabled();

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         byte byteValue = nBTTagCompound.computeByteCode("Y");
         InternalCharArrayEntry internalCharArrayEntry = new InternalCharArrayEntry(byteValue << 4, flag);
         byte[] byteValueArray = nBTTagCompound.resolveByteArray("Blocks");
         SpecialByteArrayEntry specialByteArrayEntry = new SpecialByteArrayEntry(nBTTagCompound.resolveByteArray("Data"));
         SpecialByteArrayEntry specialByteArrayEntry2 = nBTTagCompound.matchesText("Add", 7) ? new SpecialByteArrayEntry(nBTTagCompound.resolveByteArray("Add")) : null;
         char[] characterArray = new char[byteValueArray.length];

         for (int index2 = 0; index2 < characterArray.length; index2++) {
            int count3 = index2 & 15;
            int count4 = index2 >> 8 & 15;
            int count5 = index2 >> 4 & 15;
            int count6 = specialByteArrayEntry2 != null ? specialByteArrayEntry2.computeCountByIndex(count3, count4, count5) : 0;
            characterArray[index2] = (char)(count6 << 12 | (byteValueArray[index2] & 255) << 4 | specialByteArrayEntry.computeCountByIndex(count3, count4, count5));
         }

         internalCharArrayEntry.setCharCodeArray(characterArray);
         internalCharArrayEntry.setSpecialByteArrayEntry2(new SpecialByteArrayEntry(nBTTagCompound.resolveByteArray("BlockLight")));
         if (flag) {
            internalCharArrayEntry.setSpecialByteArrayEntry(new SpecialByteArrayEntry(nBTTagCompound.resolveByteArray("SkyLight")));
         }

         internalCharArrayEntry.updateState();
         internalCharArrayEntryArray[byteValue] = internalCharArrayEntry;
      }

      alertNotificationSound.applyInternalCharArrayEntryArra(internalCharArrayEntryArray);
      if (tagCompound.matchesText("Biomes", 7)) {
         alertNotificationSound.applyByteArray(tagCompound.resolveByteArray("Biomes"));
      }

      NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("Entities", 10);
      if (extendedLoggerEntry2 != null) {
         for (int index3 = 0; index3 < extendedLoggerEntry2.getInt(); index3++) {
            NBTTagCompound nBTTagCompound2 = extendedLoggerEntry2.getNBTTagCompound(index3);
            Entity entity = SkippingEntityWithIdManager.resolveEntityByNBTTagCompound(nBTTagCompound2, world);
            alertNotificationSound.setEnabled2(true);
            if (entity != null) {
               alertNotificationSound.applyEntityByEntity(entity);
               Entity entity2 = entity;

               for (NBTTagCompound tagCompound2 = nBTTagCompound2; tagCompound2.matchesText("Riding", 10); tagCompound2 = tagCompound2.getNBTTagCompound("Riding")) {
                  Entity entity3 = SkippingEntityWithIdManager.resolveEntityByNBTTagCompound(tagCompound2.getNBTTagCompound("Riding"), world);
                  if (entity3 != null) {
                     alertNotificationSound.applyEntityByEntity(entity3);
                     entity2.setEntity8(entity3);
                  }

                  entity2 = entity3;
               }
            }
         }
      }

      NBTTagList extendedLoggerEntry3 = tagCompound.getExtendedLoggerEntry("TileEntities", 10);
      if (extendedLoggerEntry3 != null) {
         for (int index4 = 0; index4 < extendedLoggerEntry3.getInt(); index4++) {
            TileEntity tileEntity = TileEntity.resolveTileEntity(extendedLoggerEntry3.getNBTTagCompound(index4));
            if (tileEntity != null) {
               alertNotificationSound.applyTileEntity(tileEntity);
            }
         }
      }

      if (tagCompound.matchesText("TileTicks", 9)) {
         NBTTagList extendedLoggerEntry4 = tagCompound.getExtendedLoggerEntry("TileTicks", 10);
         if (extendedLoggerEntry4 != null) {
            for (int index5 = 0; index5 < extendedLoggerEntry4.getInt(); index5++) {
               NBTTagCompound nBTTagCompound3 = extendedLoggerEntry4.getNBTTagCompound(index5);
               Block block;
               if (nBTTagCompound3.matchesText("i", 8)) {
                  block = Block.getBlockFromName(nBTTagCompound3.resolveText("i"));
               } else {
                  block = Block.processAction3(nBTTagCompound3.computeCount("i"));
               }

               world.applyBlockPos(
                  new BlockPos(nBTTagCompound3.computeCount("x"), nBTTagCompound3.computeCount("y"), nBTTagCompound3.computeCount("z")),
                  block,
                  nBTTagCompound3.computeCount("t"),
                  nBTTagCompound3.computeCount("p")
               );
            }
         }
      }

      return alertNotificationSound;
   }

   @Override
   public void applyWorld(World world, AlertNotificationSound alertNotificationSound) throws ExceptionAdapter, IOException {
      world.updateState6();

      try {
         NBTTagCompound tagCompound = new NBTTagCompound();
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound.applyText("Level", tagCompound2);
         this.applyAlertNotificationSound(alertNotificationSound, world, tagCompound2);
         this.applyInterpolationCollector(alertNotificationSound.getInterpolationCollector(), tagCompound);
      } catch (Exception exception) {
         logger.error("Failed to save chunk", exception);
      }
   }

   public void applyInterpolationCollector(InterpolationCollector interpolationCollector, NBTTagCompound tagCompound) {
      if (!this.set.contains(interpolationCollector)) {
         this.map.put(interpolationCollector, tagCompound);
      }

      RunnableHandler.getRunnableHandler().applySharedListener(this);
   }

   @Override
   public boolean isActive() {
      if (this.map.isEmpty()) {
         if (this.enabled) {
            logger.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", new Object[]{this.file.getName()});
         }

         return false;
      } else {
         InterpolationCollector interpolationCollector = (InterpolationCollector)this.map.keySet().iterator().next();

         try {
            this.set.add(interpolationCollector);
            NBTTagCompound tagCompound = (NBTTagCompound)this.map.remove(interpolationCollector);
            if (tagCompound != null) {
               try {
                  this.applyInterpolationCollectorByInterpolationCol(interpolationCollector, tagCompound);
               } catch (Exception exception) {
                  logger.error("Failed to save chunk", exception);
               }
            }
         } finally {
            this.set.remove(interpolationCollector);
         }

         return true;
      }
   }

   @Override
   public void updateState2() {
   }
}
