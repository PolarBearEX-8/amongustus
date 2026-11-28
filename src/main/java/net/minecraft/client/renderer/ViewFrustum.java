package net.minecraft.client.renderer;

import java.util.HashMap;
import java.util.Map;
import net.augustus.utils.MutableReference;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.optifine.config.Config;
import net.optifine.render.VboRegion;

public class ViewFrustum {
   public final RenderGlobal renderGlobal;
   public final World world;
   public int countChunksY;
   public int countChunksX;
   public int countChunksZ;
   public RenderChunk[] renderChunks;
   private Map mapVboRegions = new HashMap();

   public void updateChunkPositions(double d, double d2) {
      int n = MathHelper.floor_double(d) - 8;
      int n2 = MathHelper.floor_double(d2) - 8;
      int n3 = this.countChunksX * 16;
      MutableReference mutableReference = new MutableReference(true);

      for (int i = 0; i < this.countChunksX; i++) {
         int baseCoordinate = this.getBaseCoordinate(n, n3, i);

         for (int j = 0; j < this.countChunksZ; j++) {
            Boolean bool = true;
            mutableReference.setValue(bool);
            int baseCoordinate2 = this.getBaseCoordinate(n2, n3, j);

            for (int k = 0; k < this.countChunksY; k++) {
               int n4 = k * 16;
               RenderChunk renderChunk = this.renderChunks[(j * this.countChunksY + k) * this.countChunksX + i];
               BlockPos position = renderChunk.getPosition();
               if (position.getX() != baseCoordinate || position.getY() != n4 || position.getZ() != baseCoordinate2) {
                  BlockPos pos = new BlockPos(baseCoordinate, n4, baseCoordinate2);
                  if (!pos.equals(renderChunk.getPosition())) {
                     renderChunk.setPosition(pos, mutableReference);
                  }
               }
            }
         }
      }
   }

   public ViewFrustum(World world2, int n, RenderGlobal renderGlobal2, IRenderChunkFactory renderChunkFactory) {
      this.renderGlobal = renderGlobal2;
      this.world = world2;
      this.setCountChunksXYZ(n);
      this.createRenderChunks(renderChunkFactory);
   }

   public void deleteVboRegions() {
      for (ChunkCoordIntPair chunkCoordIntPair : (Iterable<ChunkCoordIntPair>)(this.mapVboRegions.keySet())) {
         VboRegion[] vboRegions = (VboRegion[])this.mapVboRegions.get(chunkCoordIntPair);

         for (int i = 0; i < vboRegions.length; i++) {
            VboRegion vboRegion = vboRegions[i];
            if (vboRegion != null) {
               vboRegion.deleteGlBuffers();
            }

            vboRegions[i] = null;
         }
      }

      this.mapVboRegions.clear();
   }

   public void createRenderChunks(IRenderChunkFactory renderChunkFactory) {
      this.renderChunks = new RenderChunk[this.countChunksX * this.countChunksY * this.countChunksZ];
      int n2 = 0;

      for (int i = 0; i < this.countChunksX; i++) {
         for (int j = 0; j < this.countChunksY; j++) {
            for (int k = 0; k < this.countChunksZ; k++) {
               int n3 = (k * this.countChunksY + j) * this.countChunksX + i;
               BlockPos pos = new BlockPos(i * 16, j * 16, k * 16);
               this.renderChunks[n3] = renderChunkFactory.makeRenderChunk(this.world, this.renderGlobal, pos, n2++);
               if (Config.isVbo() && Config.isRenderRegions()) {
                  this.updateVboRegion(this.renderChunks[n3]);
               }
            }
         }
      }

      for (int m = 0; m < this.renderChunks.length; m++) {
         RenderChunk renderChunk = this.renderChunks[m];

         for (int idx = 0; idx < EnumFacing.VALUES.length; idx++) {
            EnumFacing facing = EnumFacing.VALUES[idx];
            renderChunk.setRenderChunkNeighbour(facing, this.getRenderChunk(renderChunk.getBlockPosOffset16(facing)));
         }
      }
   }

   public void markBlocksForUpdate(int n, int n2, int n3, int n4, int n5, int n6) {
      int n7 = MathHelper.bucketInt(n, 16);
      int n8 = MathHelper.bucketInt(n2, 16);
      int n9 = MathHelper.bucketInt(n3, 16);
      int n10 = MathHelper.bucketInt(n4, 16);
      int n11 = MathHelper.bucketInt(n5, 16);
      int n12 = MathHelper.bucketInt(n6, 16);

      for (int i = n7; i <= n10; i++) {
         int n13 = i % this.countChunksX;
         if (n13 < 0) {
            n13 += this.countChunksX;
         }

         for (int j = n8; j <= n11; j++) {
            int n14 = j % this.countChunksY;
            if (n14 < 0) {
               n14 += this.countChunksY;
            }

            for (int k = n9; k <= n12; k++) {
               int n15 = k % this.countChunksZ;
               if (n15 < 0) {
                  n15 += this.countChunksZ;
               }

               int n16 = (n15 * this.countChunksY + n14) * this.countChunksX + n13;
               this.renderChunks[n16].setNeedsUpdate(true);
            }
         }
      }
   }

   private int getBaseCoordinate(int n, int n2, int n3) {
      int n4 = n3 * 16;
      int n5 = n4 - n + n2 / 2;
      if (n5 < 0) {
         n5 -= n2 - 1;
      }

      return n4 - n5 / n2 * n2;
   }

   public RenderChunk getRenderChunk(int n, int n2, int n3) {
      n >>= 4;
      n2 >>= 4;
      n3 >>= 4;
      if (n2 >= 0 && n2 < this.countChunksY) {
         n %= this.countChunksX;
         if (n < 0) {
            n += this.countChunksX;
         }

         n3 %= this.countChunksZ;
         if (n3 < 0) {
            n3 += this.countChunksZ;
         }

         int n4 = (n3 * this.countChunksY + n2) * this.countChunksX + n;
         return this.renderChunks[n4];
      } else {
         return null;
      }
   }

   private void updateVboRegion(RenderChunk renderChunk) {
      BlockPos position = renderChunk.getPosition();
      int n = position.getX() >> 8 << 8;
      int n2 = position.getZ() >> 8 << 8;
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);
      EnumWorldBlockLayer[] enumworldblocklayers = RenderChunk.ENUM_WORLD_BLOCK_LAYERS;
      VboRegion[] vboRegions = (VboRegion[])this.mapVboRegions.get(chunkCoordIntPair);
      if (vboRegions == null) {
         vboRegions = new VboRegion[enumworldblocklayers.length];

         for (int i = 0; i < enumworldblocklayers.length; i++) {
            vboRegions[i] = new VboRegion(enumworldblocklayers[i]);
         }

         this.mapVboRegions.put(chunkCoordIntPair, vboRegions);
      }

      for (int j = 0; j < enumworldblocklayers.length; j++) {
         VboRegion vboRegion = vboRegions[j];
         if (vboRegion != null) {
            renderChunk.getVertexBufferByLayer(j).setVboRegion(vboRegion);
         }
      }
   }

   public RenderChunk getRenderChunk(BlockPos pos) {
      return this.getRenderChunk(pos.getX(), pos.getY(), pos.getZ());
   }

   public void setCountChunksXYZ(int n) {
      int n2 = n * 2 + 1;
      this.countChunksX = n2;
      this.countChunksY = 16;
      this.countChunksZ = n2;
   }

   public void deleteGlResources() {
      for (RenderChunk renderChunk : this.renderChunks) {
         renderChunk.deleteGlResources();
      }

      this.deleteVboRegions();
   }
}
