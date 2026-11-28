package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
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
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.storage.IThreadedFileIO;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilChunkLoader implements IChunkLoader, IThreadedFileIO {
   private static final Logger LOGGER = LogManager.getLogger();
   private Map chunksToRemove = new ConcurrentHashMap();
   private Set pendingAnvilChunksCoordinates = Collections.newSetFromMap(new ConcurrentHashMap());
   public final File chunkSaveLocation;
   private boolean savingExtraData = false;

   @Override
   public void saveChunk(World world, Chunk chunk) throws MinecraftException, IOException {
      world.checkSessionLock();

      try {
         NBTTagCompound tag = new NBTTagCompound();
         NBTTagCompound tag2 = new NBTTagCompound();
         tag.setTag("Level", tag2);
         this.writeChunkToNBT(chunk, world, tag2);
         this.addChunkToPending(chunk.getChunkCoordIntPair(), tag);
      } catch (Exception e) {
         LOGGER.error("Failed to save chunk", e);
      }
   }

   private void writeChunkData(ChunkCoordIntPair chunkCoordIntPair, NBTTagCompound tag) throws IOException {
      DataOutputStream chunkOutputStream = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos);
      CompressedStreamTools.write(tag, chunkOutputStream);
      chunkOutputStream.close();
   }

   @Override
   public void saveExtraData() {
      try {
         this.savingExtraData = true;

         while (true) {
            if (this.writeNextIO()) {
            }
         }
      } finally {
         this.savingExtraData = false;
      }
   }

   public Chunk checkedReadChunkFromNBT(World world, int n, int n2, NBTTagCompound tag) {
      if (!tag.hasKey("Level", 10)) {
         LOGGER.error("Chunk file at " + n + "," + n2 + " is missing level data, skipping");
         return null;
      } else {
         NBTTagCompound compoundTag = tag.getCompoundTag("Level");
         if (!compoundTag.hasKey("Sections", 9)) {
            LOGGER.error("Chunk file at " + n + "," + n2 + " is missing block data, skipping");
            return null;
         } else {
            Chunk chunk = this.readChunkFromNBT(world, compoundTag);
            if (!chunk.isAtLocation(n, n2)) {
               LOGGER.error(
                  "Chunk file at "
                     + n
                     + ","
                     + n2
                     + " is in the wrong location; relocating. (Expected "
                     + n
                     + ", "
                     + n2
                     + ", got "
                     + chunk.xPosition
                     + ", "
                     + chunk.zPosition
                     + ")"
               );
               compoundTag.setInteger("xPos", n);
               compoundTag.setInteger("zPos", n2);
               chunk = this.readChunkFromNBT(world, compoundTag);
            }

            return chunk;
         }
      }
   }

   @Override
   public void saveExtraChunkData(World world, Chunk chunk) throws IOException {
   }

   @Override
   public void chunkTick() {
   }

   public AnvilChunkLoader(File file) {
      this.chunkSaveLocation = file;
   }

   @Override
   public boolean writeNextIO() {
      if (this.chunksToRemove.isEmpty()) {
         if (this.savingExtraData) {
            LOGGER.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", new Object[]{this.chunkSaveLocation.getName()});
         }

         return false;
      } else {
         ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)this.chunksToRemove.keySet().iterator().next();

         boolean flag;
         try {
            this.pendingAnvilChunksCoordinates.add(chunkCoordIntPair);
            NBTTagCompound tag = (NBTTagCompound)this.chunksToRemove.remove(chunkCoordIntPair);
            if (tag != null) {
               try {
                  this.writeChunkData(chunkCoordIntPair, tag);
               } catch (Exception e) {
                  LOGGER.error("Failed to save chunk", e);
               }
            }

            flag = true;
         } finally {
            this.pendingAnvilChunksCoordinates.remove(chunkCoordIntPair);
         }

         return flag;
      }
   }

   private void writeChunkToNBT(Chunk chunk, World world, NBTTagCompound tag) {
      tag.setByte("V", (byte)1);
      tag.setInteger("xPos", chunk.xPosition);
      tag.setInteger("zPos", chunk.zPosition);
      tag.setLong("LastUpdate", world.getTotalWorldTime());
      tag.setIntArray("HeightMap", chunk.getHeightMap());
      tag.setBoolean("TerrainPopulated", chunk.isTerrainPopulated());
      tag.setBoolean("LightPopulated", chunk.isLightPopulated());
      tag.setLong("InhabitedTime", chunk.getInhabitedTime());
      ExtendedBlockStorage[] blockStorageArray = chunk.getBlockStorageArray();
      NBTTagList tagList = new NBTTagList();
      boolean flag = !world.provider.getHasNoSky();

      for (ExtendedBlockStorage extendedBlockStorage : blockStorageArray) {
         if (extendedBlockStorage != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Y", (byte)(extendedBlockStorage.getYLocation() >> 4 & 0xFF));
            byte[] bytes = new byte[extendedBlockStorage.getData().length];
            NibbleArray nibbleArray = new NibbleArray();
            NibbleArray nibbleArray2 = null;

            for (int i = 0; i < extendedBlockStorage.getData().length; i++) {
               char ch = extendedBlockStorage.getData()[i];
               int n = i & 15;
               int n2 = i >> 8 & 15;
               int n3 = i >> 4 & 15;
               if (ch >> '\f' != 0) {
                  if (nibbleArray2 == null) {
                     nibbleArray2 = new NibbleArray();
                  }

                  nibbleArray2.set(n, n2, n3, ch >> '\f');
               }

               bytes[i] = (byte)(ch >> 4 & 0xFF);
               nibbleArray.set(n, n2, n3, ch & 15);
            }

            tag2.setByteArray("Blocks", bytes);
            tag2.setByteArray("Data", nibbleArray.getData());
            if (nibbleArray2 != null) {
               tag2.setByteArray("Add", nibbleArray2.getData());
            }

            tag2.setByteArray("BlockLight", extendedBlockStorage.getBlocklightArray().getData());
            if (flag) {
               tag2.setByteArray("SkyLight", extendedBlockStorage.getSkylightArray().getData());
            } else {
               tag2.setByteArray("SkyLight", new byte[extendedBlockStorage.getBlocklightArray().getData().length]);
            }

            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Sections", tagList);
      tag.setByteArray("Biomes", chunk.getBiomeArray());
      chunk.setHasEntities(false);
      NBTTagList tagList2 = new NBTTagList();

      for (int j = 0; j < chunk.getEntityLists().length; j++) {
         for (Entity entity : (Iterable<Entity>)(chunk.getEntityLists()[j])) {
            NBTTagCompound tag3 = new NBTTagCompound();
            if (entity.writeToNBTOptional(tag3)) {
               chunk.setHasEntities(true);
               tagList2.appendTag(tag3);
            }
         }
      }

      tag.setTag("Entities", tagList2);
      NBTTagList tagList3 = new NBTTagList();

      for (TileEntity tileEntity : (Iterable<TileEntity>)(chunk.getTileEntityMap().values())) {
         NBTTagCompound tag4 = new NBTTagCompound();
         tileEntity.writeToNBT(tag4);
         tagList3.appendTag(tag4);
      }

      tag.setTag("TileEntities", tagList3);
      List pendingBlockUpdates = world.getPendingBlockUpdates(chunk, false);
      if (pendingBlockUpdates != null) {
         long totalWorldTime = world.getTotalWorldTime();
         NBTTagList tagList4 = new NBTTagList();

         for (NextTickListEntry nextTickListEntry : (Iterable<NextTickListEntry>)(pendingBlockUpdates)) {
            NBTTagCompound tag5 = new NBTTagCompound();
            ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(nextTickListEntry.getBlock());
            tag5.setString("i", nameForObject == null ? "" : nameForObject.toString());
            tag5.setInteger("x", nextTickListEntry.position.getX());
            tag5.setInteger("y", nextTickListEntry.position.getY());
            tag5.setInteger("z", nextTickListEntry.position.getZ());
            tag5.setInteger("t", (int)(nextTickListEntry.scheduledTime - totalWorldTime));
            tag5.setInteger("p", nextTickListEntry.priority);
            tagList4.appendTag(tag5);
         }

         tag.setTag("TileTicks", tagList4);
      }
   }

   private Chunk readChunkFromNBT(World world, NBTTagCompound tag) {
      int integer = tag.getInteger("xPos");
      int integer2 = tag.getInteger("zPos");
      Chunk chunk = new Chunk(world, integer, integer2);
      chunk.setHeightMap(tag.getIntArray("HeightMap"));
      chunk.setTerrainPopulated(tag.getBoolean("TerrainPopulated"));
      chunk.setLightPopulated(tag.getBoolean("LightPopulated"));
      chunk.setInhabitedTime(tag.getLong("InhabitedTime"));
      NBTTagList tagList = tag.getTagList("Sections", 10);
      ExtendedBlockStorage[] extendedBlockStorages = new ExtendedBlockStorage[(byte)16];
      boolean flag = !world.provider.getHasNoSky();

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         byte b2 = compoundTagAt.getByte("Y");
         ExtendedBlockStorage extendedBlockStorage = new ExtendedBlockStorage(b2 << 4, flag);
         byte[] byteArray = compoundTagAt.getByteArray("Blocks");
         NibbleArray nibbleArray = new NibbleArray(compoundTagAt.getByteArray("Data"));
         NibbleArray nibbleArray2 = compoundTagAt.hasKey("Add", 7) ? new NibbleArray(compoundTagAt.getByteArray("Add")) : null;
         char[] chars = new char[byteArray.length];

         for (int j = 0; j < chars.length; j++) {
            int n = j & 15;
            int n2 = j >> 8 & 15;
            int n3 = j >> 4 & 15;
            chars[j] = (char)((nibbleArray2 != null ? nibbleArray2.get(n, n2, n3) : 0) << 12 | (byteArray[j] & 255) << 4 | nibbleArray.get(n, n2, n3));
         }

         extendedBlockStorage.setData(chars);
         extendedBlockStorage.setBlocklightArray(new NibbleArray(compoundTagAt.getByteArray("BlockLight")));
         if (flag) {
            extendedBlockStorage.setSkylightArray(new NibbleArray(compoundTagAt.getByteArray("SkyLight")));
         }

         extendedBlockStorage.removeInvalidBlocks();
         extendedBlockStorages[b2] = extendedBlockStorage;
      }

      chunk.setStorageArrays(extendedBlockStorages);
      if (tag.hasKey("Biomes", 7)) {
         chunk.setBiomeArray(tag.getByteArray("Biomes"));
      }

      NBTTagList tagList2 = tag.getTagList("Entities", 10);
      if (tagList2 != null) {
         for (int k = 0; k < tagList2.tagCount(); k++) {
            NBTTagCompound compoundTagAt2 = tagList2.getCompoundTagAt(k);
            Entity entity = EntityList.createEntityFromNBT(compoundTagAt2, world);
            chunk.setHasEntities(true);
            if (entity != null) {
               chunk.addEntity(entity);
               Entity entity2 = entity;

               for (NBTTagCompound m = compoundTagAt2; m.hasKey("Riding", 10); m = m.getCompoundTag("Riding")) {
                  Entity entity3 = EntityList.createEntityFromNBT(m.getCompoundTag("Riding"), world);
                  if (entity3 != null) {
                     chunk.addEntity(entity3);
                     entity2.mountEntity(entity3);
                  }

                  entity2 = entity3;
               }
            }
         }
      }

      NBTTagList tagList3 = tag.getTagList("TileEntities", 10);
      if (tagList3 != null) {
         for (int idx = 0; idx < tagList3.tagCount(); idx++) {
            TileEntity tileEntity = TileEntity.createAndLoadEntity(tagList3.getCompoundTagAt(idx));
            if (tileEntity != null) {
               chunk.addTileEntity(tileEntity);
            }
         }
      }

      if (tag.hasKey("TileTicks", 9)) {
         NBTTagList tagList4 = tag.getTagList("TileTicks", 10);
         if (tagList4 != null) {
            for (int index = 0; index < tagList4.tagCount(); index++) {
               NBTTagCompound compoundTagAt4 = tagList4.getCompoundTagAt(index);
               Block block;
               if (compoundTagAt4.hasKey("i", 8)) {
                  block = Block.getBlockFromName(compoundTagAt4.getString("i"));
               } else {
                  block = Block.getBlockById(compoundTagAt4.getInteger("i"));
               }

               world.scheduleBlockUpdate(
                  new BlockPos(compoundTagAt4.getInteger("x"), compoundTagAt4.getInteger("y"), compoundTagAt4.getInteger("z")), block, compoundTagAt4.getInteger("t"), compoundTagAt4.getInteger("p")
               );
            }
         }
      }

      return chunk;
   }

   @Override
   public Chunk loadChunk(World world, int n, int n2) throws IOException {
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);
      NBTTagCompound tag = (NBTTagCompound)this.chunksToRemove.get(chunkCoordIntPair);
      if (tag == null) {
         DataInputStream chunkInputStream = RegionFileCache.getChunkInputStream(this.chunkSaveLocation, n, n2);
         if (chunkInputStream == null) {
            return null;
         }

         tag = CompressedStreamTools.read(chunkInputStream);
         chunkInputStream.close();
      }

      return this.checkedReadChunkFromNBT(world, n, n2, tag);
   }

   public void addChunkToPending(ChunkCoordIntPair chunkCoordIntPair, NBTTagCompound tag) {
      if (!this.pendingAnvilChunksCoordinates.contains(chunkCoordIntPair)) {
         this.chunksToRemove.put(chunkCoordIntPair, tag);
      }

      ThreadedFileIOBase.getThreadedIOInstance().queueIO(this);
   }
}
