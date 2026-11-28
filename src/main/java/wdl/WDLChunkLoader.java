package wdl;

import net.minecraft.entity.DataWatcher;
import wdl.api.IEntityEditor;
import wdl.api.ITileEntityEditor;
import wdl.api.ITileEntityImportationIdentifier;
import wdl.api.ModInfo;
import wdl.api.TileEntityCreationMode;
import wdl.api.WDLApi;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockNote;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.minecraft.world.storage.SaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WDLChunkLoader extends AnvilChunkLoader {
   private static Logger logger = LogManager.getLogger();
   private final File chunkSaveLocation2;

   public static WDLChunkLoader create(SaveHandler saveHandler, WorldProvider worldProvider) {
      return new WDLChunkLoader(getWorldSaveFolder(saveHandler, worldProvider));
   }

   private static File getWorldSaveFolder(SaveHandler saveHandler, WorldProvider worldProvider) {
      File worldDirectory = saveHandler.getWorldDirectory();
      if (worldProvider instanceof WorldProviderHell) {
         File file = new File(worldDirectory, "DIM-1");
         file.mkdirs();
         return file;
      } else if (worldProvider instanceof WorldProviderEnd) {
         File file2 = new File(worldDirectory, "DIM1");
         file2.mkdirs();
         return file2;
      } else {
         return worldDirectory;
      }
   }

   public static void editTileEntity(BlockPos pos, NBTTagCompound tag, TileEntityCreationMode tileEntityCreationMode) {

      for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ITileEntityEditor.class))) {
         try {
            if (((ITileEntityEditor)modInfo.mod).shouldEdit(pos, tag, tileEntityCreationMode)) {
               ((ITileEntityEditor)modInfo.mod).editTileEntity(pos, tag, tileEntityCreationMode);
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.edited", new Object[]{pos, modInfo.getDisplayName()}
               );
            }
         } catch (Exception e) {
            throw new RuntimeException(
               "Failed to edit tile entity at "
                  + pos
                  + " with extension "
                  + modInfo
                  + "; NBT is now "
                  + tag
                  + " (this may be the initial value, an edited value, or a partially edited value)",
               e
            );
         }
      }
   }

   public WDLChunkLoader(File file) {
      super(file);
      this.chunkSaveLocation2 = file;
   }

   private NBTTagCompound saveChunkToNBT(Chunk chunk, World world) {
      NBTTagCompound tag = new NBTTagCompound();
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
      NBTTagList entityList = this.getEntityList(chunk);
      tag.setTag("Entities", entityList);
      NBTTagList tileEntityList = this.getTileEntityList(chunk);
      tag.setTag("TileEntities", tileEntityList);
      List pendingBlockUpdates = world.getPendingBlockUpdates(chunk, false);
      if (pendingBlockUpdates != null) {
         long totalWorldTime = world.getTotalWorldTime();
         NBTTagList tagList2 = new NBTTagList();

         for (NextTickListEntry nextTickListEntry : (Iterable<NextTickListEntry>)(pendingBlockUpdates)) {
            NBTTagCompound tag3 = new NBTTagCompound();
            ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(nextTickListEntry.getBlock());
            tag3.setString("i", nameForObject == null ? "" : nameForObject.toString());
            tag3.setInteger("x", nextTickListEntry.position.getX());
            tag3.setInteger("y", nextTickListEntry.position.getY());
            tag3.setInteger("z", nextTickListEntry.position.getZ());
            tag3.setInteger("t", (int)(nextTickListEntry.scheduledTime - totalWorldTime));
            tag3.setInteger("p", nextTickListEntry.priority);
            tagList2.appendTag(tag3);
         }

         tag.setTag("TileTicks", tagList2);
      }

      return tag;
   }

   public Map getOldTileEntities(Chunk chunk) {
      DataInputStream in = null;
      HashMap map = new HashMap();

      HashMap map2;
      try {
         in = RegionFileCache.getChunkInputStream(this.chunkSaveLocation2, chunk.xPosition, chunk.zPosition);
         if (in != null) {
            NBTTagList tagList = CompressedStreamTools.read(in).getCompoundTag("Level").getTagList("TileEntities", 10);
            if (tagList != null) {
               for (int i = 0; i < tagList.tagCount(); i++) {
                  NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
                  String string = compoundTagAt.getString("id");
                  BlockPos pos = new BlockPos(compoundTagAt.getInteger("x"), compoundTagAt.getInteger("y"), compoundTagAt.getInteger("z"));
                  if (this.shouldImportTileEntity(string, pos, chunk.getBlock(pos), compoundTagAt, chunk)) {
                     map.put(pos, compoundTagAt);
                  } else {
                     WDLMessages.chatMessageTranslated(
                        WDLMessageTypes.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.notImporting", new Object[]{string, pos}
                     );
                  }
               }
            }

            return map;
         }

         map2 = map;
      } catch (Exception e) {
         WDLMessages.chatMessageTranslated(
            WDLMessageTypes.ERROR, "wdl.messages.generalError.failedToImportTE", new Object[]{chunk.xPosition, chunk.zPosition, e}
         );
         return map;
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (Exception e2) {
               throw new RuntimeException(e2);
            }
         }
      }

      return map2;
   }

   public static boolean shouldSaveEntity(Entity entity) {
      if (entity instanceof EntityPlayer) {
         return false;
      } else if (!EntityUtils.isEntityEnabled2(entity)) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.REMOVE_ENTITY, "wdl.messages.removeEntity.notSavingUserPreference", new Object[]{entity});
         return false;
      } else {
         return true;
      }
   }

   public NBTTagList getTileEntityList(Chunk chunk) {
      NBTTagList tagList = new NBTTagList();
      if (!WDLPluginChannels.canSaveTileEntities(chunk)) {
         return tagList;
      } else {
         Map tileEntityMap = chunk.getTileEntityMap();
         Map oldTileEntities = this.getOldTileEntities(chunk);
         Map map = (Map)WDL.newTileEntities.get(chunk.getChunkCoordIntPair());
         if (map == null) {
            map = new HashMap();
         }

         HashSet set = new HashSet();
         set.addAll(tileEntityMap.keySet());
         set.addAll(oldTileEntities.keySet());
         set.addAll(map.keySet());

         for (BlockPos pos : (Iterable<BlockPos>)(set)) {
            if (map.containsKey(pos)) {
               NBTTagCompound tag = new NBTTagCompound();
               TileEntity tileEntity = (TileEntity)map.get(pos);

               try {
                  tileEntity.writeToNBT(tag);
               } catch (Exception e) {
                  WDLMessages.chatMessageTranslated(
                     WDLMessageTypes.ERROR,
                     "wdl.messages.generalError.failedToSaveTE",
                     new Object[]{tileEntity, pos, chunk.xPosition, chunk.zPosition, e}
                  );
                  logger.warn("Compound: " + tag);
                  continue;
               }

               String str = tag.getString("id") + " (" + tileEntity.getClass().getCanonicalName() + ")";
               WDLMessages.chatMessageTranslated(WDLMessageTypes.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.usingNew", new Object[]{str, pos});
               editTileEntity(pos, tag, TileEntityCreationMode.NEW);
               tagList.appendTag(tag);
            } else if (oldTileEntities.containsKey(pos)) {
               NBTTagCompound tag2 = (NBTTagCompound)oldTileEntities.get(pos);
               String string = tag2.getString("id");
               WDLMessages.chatMessageTranslated(WDLMessageTypes.LOAD_TILE_ENTITY, "wdl.messages.tileEntity.usingOld", new Object[]{string, pos});
               editTileEntity(pos, tag2, TileEntityCreationMode.IMPORTED);
               tagList.appendTag(tag2);
            } else if (tileEntityMap.containsKey(pos)) {
               TileEntity tileEntity2 = (TileEntity)tileEntityMap.get(pos);
               NBTTagCompound tag3 = new NBTTagCompound();

               try {
                  tileEntity2.writeToNBT(tag3);
               } catch (Exception e2) {
                  WDLMessages.chatMessageTranslated(
                     WDLMessageTypes.ERROR,
                     "wdl.messages.generalError.failedToSaveTE",
                     new Object[]{tileEntity2, pos, chunk.xPosition, chunk.zPosition, e2}
                  );
                  logger.warn("Compound: " + tag3);
                  continue;
               }

               editTileEntity(pos, tag3, TileEntityCreationMode.EXISTING);
               tagList.appendTag(tag3);
            }
         }

         return tagList;
      }
   }

   public NBTTagList getEntityList(Chunk chunk) {
      NBTTagList tagList = new NBTTagList();
      if (!WDLPluginChannels.canSaveEntities(chunk)) {
         return tagList;
      } else {
         ArrayList list = new ArrayList();

         for (ClassInheritanceMultiMap classInheritanceMultiMap : chunk.getEntityLists()) {
            list.addAll(classInheritanceMultiMap);
         }

         for (Entity entity : (Iterable<Entity>)(WDL.newEntities.get(chunk.getChunkCoordIntPair()))) {
            entity.isDead = false;
            list.add(entity);
         }

         for (Entity entity2 : (Iterable<Entity>)(list)) {
            if (entity2 == null) {
               logger.warn("[WDL] Null entity in chunk at " + chunk.getChunkCoordIntPair());
            } else if (shouldSaveEntity(entity2)) {
               for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IEntityEditor.class))) {
                  try {
                     if (((IEntityEditor)modInfo.mod).shouldEdit(entity2)) {
                        ((IEntityEditor)modInfo.mod).editEntity(entity2);
                     }
                  } catch (Exception e) {
                     throw new RuntimeException(
                        "Failed to edit entity " + entity2 + " for chunk at " + chunk.getChunkCoordIntPair() + " with extension " + modInfo, e
                     );
                  }
               }

               NBTTagCompound tag = new NBTTagCompound();

               try {
                  if (entity2.writeToNBTOptional(tag)) {
                     chunk.setHasEntities(true);
                     tagList.appendTag(tag);
                  }
               } catch (Exception e2) {
                  WDLMessages.chatMessageTranslated(
                     WDLMessageTypes.ERROR,
                     "wdl.messages.generalError.failedToSaveEntity",
                     new Object[]{entity2, chunk.xPosition, chunk.zPosition, e2}
                  );
                  logger.warn("Compound: " + tag);
                  logger.warn("Entity metadata dump:");

                  try {
                     List allWatched = entity2.getDataWatcher().getAllWatched();
                     if (allWatched == null) {
                        logger.warn("No entries (getAllWatched() returned null)");
                     } else {
                        logger.warn(allWatched);

                        for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(allWatched)) {
                           if (watchableObject != null) {
                              logger.warn(
                                 "WatchableObject [getDataValueId()="
                                    + watchableObject.getDataValueId()
                                    + ", getObject()="
                                    + watchableObject.getObject()
                                    + ", getObjectType()="
                                    + watchableObject.getObjectType()
                                    + ", isWatched()="
                                    + watchableObject.isWatched()
                                    + "]"
                              );
                           }
                        }
                     }
                  } catch (Exception e3) {
                     logger.warn("Failed to complete dump: ", e2);
                  }

                  logger.warn("End entity metadata dump");
               }
            }
         }

         return tagList;
      }
   }

   public boolean shouldImportTileEntity(String str, BlockPos pos, Block block, NBTTagCompound tag, Chunk chunk) {
      if (block instanceof BlockChest && str.equals("Chest")) {
         return true;
      } else if (block instanceof BlockDispenser && str.equals("Trap")) {
         return true;
      } else if (block instanceof BlockDropper && str.equals("Dropper")) {
         return true;
      } else if (block instanceof BlockFurnace && str.equals("Furnace")) {
         return true;
      } else if (block instanceof BlockNote && str.equals("Music")) {
         return true;
      } else if (block instanceof BlockBrewingStand && str.equals("Cauldron")) {
         return true;
      } else if (block instanceof BlockHopper && str.equals("Hopper")) {
         return true;
      } else if (block instanceof BlockBeacon && str.equals("Beacon")) {
         return true;
      } else {
         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ITileEntityImportationIdentifier.class))) {
            if (((ITileEntityImportationIdentifier)modInfo.mod).shouldImportTileEntity(str, pos, block, tag, chunk)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void saveChunk(World world, Chunk chunk) throws MinecraftException, IOException {
      world.checkSessionLock();
      NBTTagCompound tag = this.saveChunkToNBT(chunk, world);
      NBTTagCompound tag2 = new NBTTagCompound();
      tag2.setTag("Level", tag);
      this.addChunkToPending(chunk.getChunkCoordIntPair(), tag2);
   }
}
