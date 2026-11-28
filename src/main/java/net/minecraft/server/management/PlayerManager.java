package net.minecraft.server.management;

import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;
import net.optifine.ChunkPosComparator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.optifine.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerManager {
   private static final Logger pmLogger = LogManager.getLogger();
   private final WorldServer theWorldServer;
   private final List players = Lists.newArrayList();
   private final LongHashMap playerInstances = new LongHashMap();
   private final List playerInstancesToUpdate = Lists.newArrayList();
   private final List playerInstanceList = Lists.newArrayList();
   private final int[][] xzDirectionsConst = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
   private final Map mapPlayerPendingEntries = new HashMap();
   private int playerViewRadius;
   private long previousTotalWorldTime;

   public void setPlayerViewRadius(int n) {
      n = MathHelper.clamp_int(n, 3, 64);
      if (n != this.playerViewRadius) {
         int n2 = n - this.playerViewRadius;

         for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(Lists.newArrayList(this.players))) {
            int n3 = (int)entityPlayerMP.posX >> 4;
            int n4 = (int)entityPlayerMP.posZ >> 4;
            Set pendingEntriesSafe = this.getPendingEntriesSafe(entityPlayerMP);
            if (n2 > 0) {
               for (int i = n3 - n; i <= n3 + n; i++) {
                  for (int j = n4 - n; j <= n4 + n; j++) {
                     if (Config.isLazyChunkLoading()) {
                        pendingEntriesSafe.add(new ChunkCoordIntPair(i, j));
                     } else {
                        PlayerManager.PlayerInstance playerInstance = this.getPlayerInstance(i, j, true);
                        if (!PlayerManager.PlayerInstance.accessPlayersWatchingChunk(playerInstance).contains(entityPlayerMP)) {
                           playerInstance.addPlayer(entityPlayerMP);
                        }
                     }
                  }
               }
            } else {
               for (int k = n3 - this.playerViewRadius; k <= n3 + this.playerViewRadius; k++) {
                  for (int m = n4 - this.playerViewRadius; m <= n4 + this.playerViewRadius; m++) {
                     if (!this.overlaps(k, m, n3, n4, n)) {
                        pendingEntriesSafe.remove(new ChunkCoordIntPair(k, m));
                        PlayerManager.PlayerInstance playerInstance2 = this.getPlayerInstance(k, m, true);
                        if (playerInstance2 != null) {
                           playerInstance2.removePlayer(entityPlayerMP);
                        }
                     }
                  }
               }
            }
         }

         this.playerViewRadius = n;
      }
   }

   public static List accessPlayerInstancesToUpdate(PlayerManager playerManager) {
      return playerManager.playerInstancesToUpdate;
   }

   public void filterChunkLoadQueue(EntityPlayerMP entityPlayerMP) {
      ArrayList list = Lists.newArrayList(entityPlayerMP.loadedChunks);
      int n = 0;
      int playerViewRadius2 = this.playerViewRadius;
      int n2 = (int)entityPlayerMP.posX >> 4;
      int n3 = (int)entityPlayerMP.posZ >> 4;
      int n4 = 0;
      int n5 = 0;
      ChunkCoordIntPair chunkCoordIntPair = PlayerManager.PlayerInstance.accessChunkCoords(this.getPlayerInstance(n2, n3, true));
      entityPlayerMP.loadedChunks.clear();
      if (list.contains(chunkCoordIntPair)) {
         entityPlayerMP.loadedChunks.add(chunkCoordIntPair);
      }

      for (int i = 1; i <= playerViewRadius2 * 2; i++) {
         for (int j = 0; j < 2; j++) {
            int[] ints = this.xzDirectionsConst[n++ % 4];

            for (int k = 0; k < i; k++) {
               n4 += ints[0];
               n5 += ints[1];
               chunkCoordIntPair = PlayerManager.PlayerInstance.accessChunkCoords(this.getPlayerInstance(n2 + n4, n3 + n5, true));
               if (list.contains(chunkCoordIntPair)) {
                  entityPlayerMP.loadedChunks.add(chunkCoordIntPair);
               }
            }
         }
      }

      n %= 4;

      for (int m = 0; m < playerViewRadius2 * 2; m++) {
         n4 += this.xzDirectionsConst[n][0];
         n5 += this.xzDirectionsConst[n][1];
         chunkCoordIntPair = PlayerManager.PlayerInstance.accessChunkCoords(this.getPlayerInstance(n2 + n4, n3 + n5, true));
         if (list.contains(chunkCoordIntPair)) {
            entityPlayerMP.loadedChunks.add(chunkCoordIntPair);
         }
      }
   }

   public void removePlayer(EntityPlayerMP entityPlayerMP) {
      this.mapPlayerPendingEntries.remove(entityPlayerMP);
      int n = (int)entityPlayerMP.managedPosX >> 4;
      int n2 = (int)entityPlayerMP.managedPosZ >> 4;

      for (int i = n - this.playerViewRadius; i <= n + this.playerViewRadius; i++) {
         for (int j = n2 - this.playerViewRadius; j <= n2 + this.playerViewRadius; j++) {
            PlayerManager.PlayerInstance playerInstance = this.getPlayerInstance(i, j, false);
            if (playerInstance != null) {
               playerInstance.removePlayer(entityPlayerMP);
            }
         }
      }

      this.players.remove(entityPlayerMP);
   }

   public static LongHashMap accessPlayerInstances(PlayerManager playerManager) {
      return playerManager.playerInstances;
   }

   public static WorldServer accessTheWorldServer(PlayerManager playerManager) {
      return playerManager.theWorldServer;
   }

   public static Logger accessPmLogger() {
      return pmLogger;
   }

   public void addPlayer(EntityPlayerMP entityPlayerMP) {
      int n = (int)entityPlayerMP.posX >> 4;
      int n2 = (int)entityPlayerMP.posZ >> 4;
      entityPlayerMP.managedPosX = entityPlayerMP.posX;
      entityPlayerMP.managedPosZ = entityPlayerMP.posZ;
      int n3 = Math.min(this.playerViewRadius, 8);
      int n4 = n - n3;
      int n5 = n + n3;
      int n6 = n2 - n3;
      int n7 = n2 + n3;
      Set pendingEntriesSafe = this.getPendingEntriesSafe(entityPlayerMP);

      for (int i = n - this.playerViewRadius; i <= n + this.playerViewRadius; i++) {
         for (int j = n2 - this.playerViewRadius; j <= n2 + this.playerViewRadius; j++) {
            if (i >= n4 && i <= n5 && j >= n6 && j <= n7) {
               this.getPlayerInstance(i, j, true).addPlayer(entityPlayerMP);
            } else {
               pendingEntriesSafe.add(new ChunkCoordIntPair(i, j));
            }
         }
      }

      this.players.add(entityPlayerMP);
      this.filterChunkLoadQueue(entityPlayerMP);
   }

   public static List accessPlayerInstanceList(PlayerManager playerManager) {
      return playerManager.playerInstanceList;
   }

   private boolean overlaps(int n, int n2, int n3, int n4, int n5) {
      int n6 = n - n3;
      int n7 = n2 - n4;
      return n6 >= -n5 && n6 <= n5 && n7 >= -n5 && n7 <= n5;
   }

   public static int getFurthestViewableBlock(int n) {
      return n * 16 - 16;
   }

   private PlayerManager.PlayerInstance getPlayerInstance(int n, int n2, boolean flag) {
      long l = (long)n + 2147483647L | (long)n2 + 2147483647L << 32;
      PlayerManager.PlayerInstance valueByKey = (PlayerManager.PlayerInstance)this.playerInstances.getValueByKey(l);
      if (valueByKey == null && flag) {
         valueByKey = new PlayerManager.PlayerInstance(this, n, n2);
         this.playerInstances.add(l, valueByKey);
         this.playerInstanceList.add(valueByKey);
      }

      return valueByKey;
   }

   private PriorityQueue getNearest(Set set, EntityPlayerMP entityPlayerMP, int n) {
      float f = entityPlayerMP.rotationYaw + 90.0F;

      while (f <= -180.0F) {
         f += 360.0F;
      }

      while (f > 180.0F) {
         f -= 360.0F;
      }

      double d = (double)f * (Math.PI / 180.0);
      double d2 = ((double)entityPlayerMP.rotationPitch) * (Math.PI / 180.0);
      ChunkPosComparator chunkPosComparator = new ChunkPosComparator(entityPlayerMP.chunkCoordX, entityPlayerMP.chunkCoordZ, d, d2);
      Comparator comparator = Collections.reverseOrder(chunkPosComparator);
      PriorityQueue priorityQueue = new PriorityQueue(n, comparator);

      for (ChunkCoordIntPair chunkCoordIntPair : (Iterable<ChunkCoordIntPair>)(set)) {
         if (priorityQueue.size() < n) {
            priorityQueue.add(chunkCoordIntPair);
         } else {
            if (chunkPosComparator.compare2(chunkCoordIntPair, (ChunkCoordIntPair)priorityQueue.peek()) < 0) {
               priorityQueue.remove();
               priorityQueue.add(chunkCoordIntPair);
            }
         }
      }

      return priorityQueue;
   }

   public WorldServer getWorldServer() {
      return this.theWorldServer;
   }

   private Set getPendingEntriesSafe(EntityPlayerMP entityPlayerMP) {
      Set set = (Set)this.mapPlayerPendingEntries.get(entityPlayerMP);
      if (set != null) {
         return set;
      } else {
         int n = Math.min(this.playerViewRadius, 8);
         int n2 = this.playerViewRadius * 2 + 1;
         int n3 = n * 2 + 1;
         int n4 = n2 * n2 - n3 * n3;
         n4 = Math.max(n4, 16);
         HashSet set2 = new HashSet(n4);
         this.mapPlayerPendingEntries.put(entityPlayerMP, set2);
         return set2;
      }
   }

   public boolean isPlayerWatchingChunk(EntityPlayerMP entityPlayerMP, int n, int n2) {
      PlayerManager.PlayerInstance playerInstance = this.getPlayerInstance(n, n2, false);
      return playerInstance != null
         && PlayerManager.PlayerInstance.accessPlayersWatchingChunk(playerInstance).contains(entityPlayerMP)
         && !entityPlayerMP.loadedChunks.contains(PlayerManager.PlayerInstance.accessChunkCoords(playerInstance));
   }

   public void updatePlayerInstances() {
      Iterator it = this.mapPlayerPendingEntries.entrySet().iterator();

      while (it.hasNext()) {
         Entry entry = (Entry)it.next();
         Set value = (Set)entry.getValue();
         if (!value.isEmpty()) {
            EntityPlayerMP key = (EntityPlayerMP)entry.getKey();
            if (key.entityWorld != this.theWorldServer) {
               it.remove();
            } else {
               int n = this.playerViewRadius / 3 + 1;
               if (!Config.isLazyChunkLoading()) {
                  n = this.playerViewRadius * 2 + 1;
               }

               for (ChunkCoordIntPair chunkCoordIntPair : (Iterable<ChunkCoordIntPair>)(this.getNearest(value, key, n))) {
                  this.getPlayerInstance(chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos, true).addPlayer(key);
                  value.remove(chunkCoordIntPair);
               }
            }
         }
      }

      long totalWorldTime = this.theWorldServer.getTotalWorldTime();
      if (totalWorldTime - this.previousTotalWorldTime > 8000L) {
         this.previousTotalWorldTime = totalWorldTime;

         for (int i = 0; i < this.playerInstanceList.size(); i++) {
            PlayerManager.PlayerInstance playerInstance2 = (PlayerManager.PlayerInstance)this.playerInstanceList.get(i);
            playerInstance2.onUpdate();
            playerInstance2.processChunk();
         }
      } else {
         for (int j = 0; j < this.playerInstancesToUpdate.size(); j++) {
            ((PlayerManager.PlayerInstance)this.playerInstancesToUpdate.get(j)).onUpdate();
         }
      }

      this.playerInstancesToUpdate.clear();
      if (this.players.isEmpty()) {
         if (!this.theWorldServer.provider.canRespawnHere()) {
            this.theWorldServer.theChunkProviderServer.unloadAllChunks();
         }
      }
   }

   public PlayerManager(WorldServer world) {
      this.theWorldServer = world;
      this.setPlayerViewRadius(world.getMinecraftServer().getConfigurationManager().getViewDistance());
   }

   public void markBlockForUpdate(BlockPos pos) {
      PlayerManager.PlayerInstance playerInstance = this.getPlayerInstance(pos.getX() >> 4, pos.getZ() >> 4, false);
      if (playerInstance != null) {
         playerInstance.flagChunkForUpdate(pos.getX() & 15, pos.getY(), pos.getZ() & 15);
      }
   }

   public void updateMountedMovingPlayer(EntityPlayerMP entityPlayerMP) {
      int n = (int)entityPlayerMP.posX >> 4;
      int n2 = (int)entityPlayerMP.posZ >> 4;
      double d = entityPlayerMP.managedPosX - entityPlayerMP.posX;
      double d2 = entityPlayerMP.managedPosZ - entityPlayerMP.posZ;
      if ((d * d + d2 * d2) >= 64.0) {
         int n3 = (int)entityPlayerMP.managedPosX >> 4;
         int n4 = (int)entityPlayerMP.managedPosZ >> 4;
         int playerViewRadius2 = this.playerViewRadius;
         int n5 = n - n3;
         int n6 = n2 - n4;
         if (n5 != 0 || n6 != 0) {
            Set pendingEntriesSafe = this.getPendingEntriesSafe(entityPlayerMP);

            for (int i = n - playerViewRadius2; i <= n + playerViewRadius2; i++) {
               for (int j = n2 - playerViewRadius2; j <= n2 + playerViewRadius2; j++) {
                  if (!this.overlaps(i, j, n3, n4, playerViewRadius2)) {
                     if (Config.isLazyChunkLoading()) {
                        pendingEntriesSafe.add(new ChunkCoordIntPair(i, j));
                     } else {
                        this.getPlayerInstance(i, j, true).addPlayer(entityPlayerMP);
                     }
                  }

                  if (!this.overlaps(i - n5, j - n6, n, n2, playerViewRadius2)) {
                     pendingEntriesSafe.remove(new ChunkCoordIntPair(i - n5, j - n6));
                     PlayerManager.PlayerInstance playerInstance = this.getPlayerInstance(i - n5, j - n6, false);
                     if (playerInstance != null) {
                        playerInstance.removePlayer(entityPlayerMP);
                     }
                  }
               }
            }

            this.filterChunkLoadQueue(entityPlayerMP);
            entityPlayerMP.managedPosX = entityPlayerMP.posX;
            entityPlayerMP.managedPosZ = entityPlayerMP.posZ;
         }
      }
   }

   public boolean hasPlayerInstance(int n, int n2) {
      long l = (long)n + 2147483647L | (long)n2 + 2147483647L << 32;
      return this.playerInstances.getValueByKey(l) != null;
   }


   public static class PlayerInstance {
      private final List playersWatchingChunk;
      private final ChunkCoordIntPair chunkCoords;
      private final short[] locationOfBlockChange;
      private int numBlocksToUpdate;
      private int flagsYAreasToUpdate;
      private long previousWorldTime;
      public final PlayerManager playerManager;

      public static List accessPlayersWatchingChunk(PlayerInstance playerInstance) {
         return playerInstance.playersWatchingChunk;
      }

      private void sendTileToAllPlayersWatchingChunk(TileEntity tileEntity) {
         if (tileEntity != null) {
            Packet descriptionPacket = tileEntity.getDescriptionPacket();
            if (descriptionPacket != null) {
               this.sendToAllPlayersWatchingChunk(descriptionPacket);
            }
         }
      }

      public void addPlayer(EntityPlayerMP entityPlayerMP) {
         if (this.playersWatchingChunk.contains(entityPlayerMP)) {
            PlayerManager.accessPmLogger()
               .debug("Failed to add player. {} already is in chunk {}, {}", new Object[]{entityPlayerMP, this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos});
         } else {
            if (this.playersWatchingChunk.isEmpty()) {
               this.previousWorldTime = PlayerManager.accessTheWorldServer(this.playerManager).getTotalWorldTime();
            }

            this.playersWatchingChunk.add(entityPlayerMP);
            entityPlayerMP.loadedChunks.add(this.chunkCoords);
         }
      }

      private void increaseInhabitedTime(Chunk chunk) {
         chunk.setInhabitedTime(chunk.getInhabitedTime() + PlayerManager.accessTheWorldServer(this.playerManager).getTotalWorldTime() - this.previousWorldTime);
         this.previousWorldTime = PlayerManager.accessTheWorldServer(this.playerManager).getTotalWorldTime();
      }

      public void removePlayer(EntityPlayerMP entityPlayerMP) {
         if (this.playersWatchingChunk.contains(entityPlayerMP)) {
            Chunk chunkFromChunkCoords = PlayerManager.accessTheWorldServer(this.playerManager).getChunkFromChunkCoords(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
            if (chunkFromChunkCoords.isPopulated()) {
               entityPlayerMP.playerNetServerHandler.sendPacket(new S21PacketChunkData(chunkFromChunkCoords, true, 0));
            }

            this.playersWatchingChunk.remove(entityPlayerMP);
            entityPlayerMP.loadedChunks.remove(this.chunkCoords);
            if (this.playersWatchingChunk.isEmpty()) {
               long l = (long)this.chunkCoords.chunkXPos + 2147483647L | (long)this.chunkCoords.chunkZPos + 2147483647L << 32;
               this.increaseInhabitedTime(chunkFromChunkCoords);
               PlayerManager.accessPlayerInstances(this.playerManager).remove(l);
               PlayerManager.accessPlayerInstanceList(this.playerManager).remove(this);
               if (this.numBlocksToUpdate > 0) {
                  PlayerManager.accessPlayerInstancesToUpdate(this.playerManager).remove(this);
               }

               this.playerManager.getWorldServer().theChunkProviderServer.dropChunk(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
            }
         }
      }

      public void sendToAllPlayersWatchingChunk(Packet packet) {
         for (int i = 0; i < this.playersWatchingChunk.size(); i++) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playersWatchingChunk.get(i);
            if (!entityPlayerMP.loadedChunks.contains(this.chunkCoords)) {
               entityPlayerMP.playerNetServerHandler.sendPacket(packet);
            }
         }
      }

      public void onUpdate() {
         if (this.numBlocksToUpdate != 0) {
            if (this.numBlocksToUpdate == 1) {
               int n = (this.locationOfBlockChange[0] >> 12 & 15) + this.chunkCoords.chunkXPos * 16;
               int n2 = this.locationOfBlockChange[0] & 255;
               int n3 = (this.locationOfBlockChange[0] >> 8 & 15) + this.chunkCoords.chunkZPos * 16;
               BlockPos pos = new BlockPos(n, n2, n3);
               this.sendToAllPlayersWatchingChunk(new S23PacketBlockChange(PlayerManager.accessTheWorldServer(this.playerManager), pos));
               if (PlayerManager.accessTheWorldServer(this.playerManager).getBlockState(pos).getBlock().hasTileEntity()) {
                  this.sendTileToAllPlayersWatchingChunk(PlayerManager.accessTheWorldServer(this.playerManager).getTileEntity(pos));
               }
            } else if (this.numBlocksToUpdate != 64) {
               this.sendToAllPlayersWatchingChunk(
                  new S22PacketMultiBlockChange(
                     this.numBlocksToUpdate,
                     this.locationOfBlockChange,
                     PlayerManager.accessTheWorldServer(this.playerManager).getChunkFromChunkCoords(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos)
                  )
               );

               for (int i = 0; i < this.numBlocksToUpdate; i++) {
                  int n4 = (this.locationOfBlockChange[i] >> 12 & 15) + this.chunkCoords.chunkXPos * 16;
                  int n5 = this.locationOfBlockChange[i] & 255;
                  int n6 = (this.locationOfBlockChange[i] >> 8 & 15) + this.chunkCoords.chunkZPos * 16;
                  BlockPos pos2 = new BlockPos(n4, n5, n6);
                  if (PlayerManager.accessTheWorldServer(this.playerManager).getBlockState(pos2).getBlock().hasTileEntity()) {
                     this.sendTileToAllPlayersWatchingChunk(PlayerManager.accessTheWorldServer(this.playerManager).getTileEntity(pos2));
                  }
               }
            } else {
               int n7 = this.chunkCoords.chunkXPos * 16;
               int n8 = this.chunkCoords.chunkZPos * 16;
               this.sendToAllPlayersWatchingChunk(
                  new S21PacketChunkData(
                     PlayerManager.accessTheWorldServer(this.playerManager).getChunkFromChunkCoords(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos),
                     false,
                     this.flagsYAreasToUpdate
                  )
               );

               for (int j = 0; j < 16; j++) {
                  if ((this.flagsYAreasToUpdate & 1 << j) != 0) {
                     int n9 = j << 4;
                     List tileEntitiesIn = PlayerManager.accessTheWorldServer(this.playerManager).getTileEntitiesIn(n7, n9, n8, n7 + 16, n9 + 16, n8 + 16);

                     for (int k = 0; k < tileEntitiesIn.size(); k++) {
                        this.sendTileToAllPlayersWatchingChunk((TileEntity)tileEntitiesIn.get(k));
                     }
                  }
               }
            }

            this.numBlocksToUpdate = 0;
            this.flagsYAreasToUpdate = 0;
         }
      }

      public PlayerInstance(PlayerManager playerManager2, int n, int n2) {
         this.playerManager = playerManager2;
         this.playersWatchingChunk = Lists.newArrayList();
         this.locationOfBlockChange = new short[64];
         this.chunkCoords = new ChunkCoordIntPair(n, n2);
         playerManager2.getWorldServer().theChunkProviderServer.loadChunk(n, n2);
      }

      public void flagChunkForUpdate(int n, int n2, int n3) {
         if (this.numBlocksToUpdate == 0) {
            PlayerManager.accessPlayerInstancesToUpdate(this.playerManager).add(this);
         }

         this.flagsYAreasToUpdate |= 1 << (n2 >> 4);
         if (this.numBlocksToUpdate < 64) {
            short s = (short)(n << 12 | n3 << 8 | n2);

            for (int i = 0; i < this.numBlocksToUpdate; i++) {
               if (this.locationOfBlockChange[i] == s) {
                  return;
               }
            }

            this.locationOfBlockChange[this.numBlocksToUpdate++] = s;
         }
      }

      public static ChunkCoordIntPair accessChunkCoords(PlayerInstance playerInstance) {
         return playerInstance.chunkCoords;
      }

      public void processChunk() {
         this.increaseInhabitedTime(PlayerManager.accessTheWorldServer(this.playerManager).getChunkFromChunkCoords(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos));
      }
   }
}
