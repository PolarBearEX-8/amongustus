package net.minecraft.client.renderer.chunk;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.utils.IPlayerContext;
import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import net.augustus.modules.Module;
import net.augustus.events.EventRenderBlock;
import net.augustus.events.listeners.ChunkUnloadListener;
import net.augustus.Augustus;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.utils.MutableReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;
import net.optifine.CustomBlockLayers;
import net.optifine.config.Config;
import net.optifine.override.ChunkCacheOF;
import net.optifine.render.AabbFrame;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class RenderChunk {
   public static final EnumWorldBlockLayer[] ENUM_WORLD_BLOCK_LAYERS = EnumWorldBlockLayer.values();
   public static int renderChunksUpdated;
   private static FloatBuffer modelviewMatrix;
   private final RenderGlobal renderGlobal;
   private final ReentrantLock lockCompileTask = new ReentrantLock();
   private final ReentrantLock lockCompiledChunk = new ReentrantLock();
   private final Set setTileEntities = Sets.newHashSet();
   private final int index;
   private final VertexBuffer[] vertexBuffers = new VertexBuffer[EnumWorldBlockLayer.values().length];
   private final EnumWorldBlockLayer[] blockLayersSingle = new EnumWorldBlockLayer[1];
   private final boolean isMipmaps = Config.isMipmaps();
   private final RenderChunk[] renderChunksOffset16 = new RenderChunk[6];
   public CompiledChunk compiledChunk = CompiledChunk.DUMMY;
   public AxisAlignedBB boundingBox;
   public int regionX;
   public int regionZ;
   public AabbFrame boundingBoxParent;
   private final World world;
   private BlockPos position;
   private ChunkCompileTaskGenerator compileTask = null;
   private int frameIndex = -1;
   private boolean needsUpdate = true;
   private EnumMap mapEnumFacing;
   private final BlockPos[] positionOffsets16 = new BlockPos[EnumFacing.VALUES.length];
   private boolean playerUpdate = false;
   private boolean renderChunksOffset16Updated = false;
   private Chunk chunk;
   private final RenderChunk[] renderChunkNeighbours = new RenderChunk[EnumFacing.VALUES.length];
   private final RenderChunk[] renderChunkNeighboursValid = new RenderChunk[EnumFacing.VALUES.length];
   private boolean renderChunkNeighboursUpdated = false;
   private final RenderGlobal.ContainerLocalRenderInformation renderInfo = new RenderGlobal.ContainerLocalRenderInformation(this, null, 0);

   public void deleteGlResources() {
      this.stopCompileTask();

      for (int i = 0; i < EnumWorldBlockLayer.values().length; i++) {
         if (this.vertexBuffers[i] != null) {
            this.vertexBuffers[i].deleteGlBuffers();
         }
      }
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public VertexBuffer getVertexBufferByLayer(int n) {
      return this.vertexBuffers[n];
   }

   private void updateRenderChunkNeighboursValid() {
      int x = this.getPosition().getX();
      int z = this.getPosition().getZ();
      int n = EnumFacing.NORTH.ordinal();
      int n2 = EnumFacing.SOUTH.ordinal();
      int n3 = EnumFacing.WEST.ordinal();
      int n4 = EnumFacing.EAST.ordinal();
      this.renderChunkNeighboursValid[n] = this.renderChunkNeighbours[n].getPosition().getZ() == z - 16 ? this.renderChunkNeighbours[n] : null;
      this.renderChunkNeighboursValid[n2] = this.renderChunkNeighbours[n2].getPosition().getZ() == z + 16 ? this.renderChunkNeighbours[n2] : null;
      this.renderChunkNeighboursValid[n3] = this.renderChunkNeighbours[n3].getPosition().getX() == x - 16 ? this.renderChunkNeighbours[n3] : null;
      this.renderChunkNeighboursValid[n4] = this.renderChunkNeighbours[n4].getPosition().getX() == x + 16 ? this.renderChunkNeighbours[n4] : null;
      this.renderChunkNeighboursUpdated = true;
   }

   @Override
   public String toString() {
      return "pos: " + this.getPosition() + ", frameIndex: " + this.frameIndex;
   }

   private boolean isChunkEmpty(BlockPos pos) {
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()
         && (Boolean)Baritone.settings().renderCachedChunks.value
         && !Minecraft.getMinecraft().isSingleplayer()) {
         Baritone primaryBaritone = (Baritone)BaritoneAPI.getProvider().getPrimaryBaritone();
         IPlayerContext playerContext = primaryBaritone.getPlayerContext();
         if (playerContext.player() != null && playerContext.world() != null && primaryBaritone.bsi != null) {
            BlockPos position2 = this.getPosition();

            for (int i = -1; i <= 1; i++) {
               for (int j = -1; j <= 1; j++) {
                  if (primaryBaritone.bsi.isLoaded(16 * i + position2.getX(), 16 * j + position2.getZ())) {
                     return false;
                  }
               }
            }
         }
      }

      int y = pos.getY();
      int n = y + 15;
      return this.getChunk(pos).getAreLevelsEmpty(y, n);
   }

   private void preRenderBlocks(WorldRenderer worldRenderer, int n, int n2, int n3) {
      worldRenderer.begin(7, DefaultVertexFormats.BLOCK);
      if (Config.isRenderRegions()) {
         byte b = 8;
         int n4 = n >> b << b;
         int n5 = n2 >> b << b;
         int n6 = n3 >> b << b;
         n4 = this.regionX;
         n6 = this.regionZ;
         worldRenderer.setTranslation((double)(-n4), (double)(-n5), (double)(-n6));
      } else {
         worldRenderer.setTranslation((double)(-n), (double)(-n2), (double)(-n3));
      }
   }

   public void setNeedsUpdate(boolean flag) {
      this.needsUpdate = flag;
      if (flag) {
         if (this.isWorldPlayerUpdate()) {
            this.playerUpdate = true;
         }
      } else {
         this.playerUpdate = false;
      }
   }

   public ChunkCompileTaskGenerator makeCompileTaskTransparency() {
      this.lockCompileTask.lock();

      Object obj;
      try {
         if (this.compileTask == null || this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.PENDING) {
            if (this.compileTask != null && this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.DONE) {
               this.compileTask.finish();
               this.compileTask = null;
            }

            this.compileTask = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY);
            this.compileTask.setCompiledChunk(this.compiledChunk);
            return this.compileTask;
         }

         obj = null;
      } finally {
         this.lockCompileTask.unlock();
      }

      return (ChunkCompileTaskGenerator)obj;
   }

   public ChunkCompileTaskGenerator makeCompileTaskChunk() {
      this.lockCompileTask.lock();

      ChunkCompileTaskGenerator generator;
      try {
         this.finishCompileTask();
         this.compileTask = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.REBUILD_CHUNK);
         generator = this.compileTask;
      } finally {
         this.lockCompileTask.unlock();
      }

      return generator;
   }

   public RenderChunk getRenderChunkNeighbour(EnumFacing facing) {
      if (!this.renderChunkNeighboursUpdated) {
         this.updateRenderChunkNeighboursValid();
      }

      return this.renderChunkNeighboursValid[facing.ordinal()];
   }

   private boolean isWorldPlayerUpdate() {
      if (this.world instanceof WorldClient) {
         return ((WorldClient)this.world).isPlayerUpdate();
      } else {
         return false;
      }
   }

   private void postRenderOverlay(EnumWorldBlockLayer enumWorldBlockLayer, RegionRenderCacheBuilder regionRenderCacheBuilder, CompiledChunk compiledChunk2, boolean[] flags) {
      if (regionRenderCacheBuilder.getWorldRendererByLayer(enumWorldBlockLayer).isDrawing()) {
         compiledChunk2.setLayerStarted(enumWorldBlockLayer);
         flags[enumWorldBlockLayer.ordinal()] = true;
      }
   }

   private void postRenderOverlays(RegionRenderCacheBuilder regionRenderCacheBuilder, CompiledChunk compiledChunk2, boolean[] flags) {
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT, regionRenderCacheBuilder, compiledChunk2, flags);
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT_MIPPED, regionRenderCacheBuilder, compiledChunk2, flags);
      this.postRenderOverlay(EnumWorldBlockLayer.TRANSLUCENT, regionRenderCacheBuilder, compiledChunk2, flags);
   }

   public boolean isNeedsUpdate() {
      return this.needsUpdate;
   }

   private void initModelviewMatrix() {
      if (modelviewMatrix == null) {
         modelviewMatrix = GLAllocation.createDirectFloatBuffer(16);
         GlStateManager.pushMatrix();
         GlStateManager.loadIdentity();
         float f = 1.000001F;
         GlStateManager.translate(-8.0F, -8.0F, -8.0F);
         GlStateManager.scale(f, f, f);
         GlStateManager.translate(8.0F, 8.0F, 8.0F);
         GlStateManager.getFloat(2982, modelviewMatrix);
         GlStateManager.popMatrix();
      }
   }

   public void rebuildChunk(float f, float f2, float f3, ChunkCompileTaskGenerator generator) {
      CompiledChunk compiledChunk2 = new CompiledChunk();
      int x = this.position.getX();
      int y = this.position.getY();
      int z = this.position.getZ();
      generator.getLock().lock();

      try {
         if (generator.getStatus() != ChunkCompileTaskGenerator.Status.COMPILING) {
            return;
         }

         generator.setCompiledChunk(compiledChunk2);
      } finally {
         generator.getLock().unlock();
      }

      VisGraph visGraph = new VisGraph();
      HashSet set = Sets.newHashSet();
      if (!this.isChunkEmpty(this.position)) {
         renderChunksUpdated++;
         ChunkCacheOF chunkCacheOF = this.makeChunkCacheOF(this.position);
         chunkCacheOF.renderStart();
         boolean[] flags = new boolean[ENUM_WORLD_BLOCK_LAYERS.length];
         BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
         EventRenderBlock event = new EventRenderBlock(null, null);

         for (Object obj : BlockPosM.getAllInBoxMutable(x, y, z, x + 15, y + 15, z + 15)) {
            BlockPosM blockPosM = (BlockPosM)obj;
            IBlockState blockState = this.getBlockState(chunkCacheOF, blockPosM);
            Block block = blockState.getBlock();
            RenderBlockListener.onRenderBlock(blockPosM, blockState, event);
            if (block.isOpaqueCube()) {
               visGraph.setOpaqueCube(blockPosM);
            }

            if (blockState.getBlock().hasTileEntity()) {
               TileEntity tileEntity = chunkCacheOF.getTileEntity(new BlockPos(blockPosM));
               TileEntitySpecialRenderer specialRenderer = TileEntityRendererDispatcher.instance.getSpecialRenderer(tileEntity);
               if (tileEntity != null && specialRenderer != null) {
                  compiledChunk2.addTileEntity(tileEntity);
                  if (specialRenderer.forceTileEntityRender()) {
                     set.add(tileEntity);
                  }
               }
            }

            EnumWorldBlockLayer[] blockLayersSingle2 = this.blockLayersSingle;
            blockLayersSingle2[0] = block.getBlockLayer();

            for (EnumWorldBlockLayer enumWorldBlockLayer : blockLayersSingle2) {
               EnumWorldBlockLayer enumWorldBlockLayer2 = this.fixBlockLayer(blockState, enumWorldBlockLayer);
               int n = enumWorldBlockLayer2.ordinal();
               if (block.getRenderType() != -1) {
                  WorldRenderer worldRendererByLayerId = generator.getRegionRenderCacheBuilder().getWorldRendererByLayerId(n);
                  worldRendererByLayerId.setBlockLayer(enumWorldBlockLayer2);
                  RenderEnv renderEnv = worldRendererByLayerId.getRenderEnv(blockState, blockPosM);
                  renderEnv.setRegionRenderCacheBuilder(generator.getRegionRenderCacheBuilder());
                  if (!compiledChunk2.isLayerStarted(enumWorldBlockLayer2)) {
                     compiledChunk2.setLayerStarted(enumWorldBlockLayer2);
                     this.preRenderBlocks(worldRendererByLayerId, x, y, z);
                  }

                  flags[n] |= blockRendererDispatcher.renderBlock(blockState, blockPosM, chunkCacheOF, worldRendererByLayerId);
                  if (renderEnv.isOverlaysRendered()) {
                     this.postRenderOverlays(generator.getRegionRenderCacheBuilder(), compiledChunk2, flags);
                     renderEnv.setOverlaysRendered(false);
                  }
               }
            }
         }

         for (EnumWorldBlockLayer enumWorldBlockLayer3 : ENUM_WORLD_BLOCK_LAYERS) {
            if (flags[enumWorldBlockLayer3.ordinal()]) {
               compiledChunk2.setLayerUsed(enumWorldBlockLayer3);
            }

            if (compiledChunk2.isLayerStarted(enumWorldBlockLayer3)) {
               if (Config.isShaderPackLoaded()) {
                  SVertexBuilder.calcNormalChunkLayer(generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(enumWorldBlockLayer3));
               }

               WorldRenderer worldRendererByLayer = generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(enumWorldBlockLayer3);
               this.postRenderBlocks(enumWorldBlockLayer3, f, f2, f3, worldRendererByLayer, compiledChunk2);
               if (worldRendererByLayer.animatedSprites != null) {
                  compiledChunk2.setAnimatedSprites(enumWorldBlockLayer3, (BitSet)worldRendererByLayer.animatedSprites.clone());
               }
            } else {
               compiledChunk2.setAnimatedSprites(enumWorldBlockLayer3, null);
            }
         }

         chunkCacheOF.renderFinish();
      }

      compiledChunk2.setVisibility(visGraph.computeVisibility());
      this.lockCompileTask.lock();

      try {
         HashSet set2 = Sets.newHashSet(set);
         HashSet set3 = Sets.newHashSet(this.setTileEntities);
         set2.removeAll(this.setTileEntities);
         set3.removeAll(set);
         this.setTileEntities.clear();
         this.setTileEntities.addAll(set);
         this.renderGlobal.updateTileEntities(set3, set2);
      } finally {
         this.lockCompileTask.unlock();
      }
   }

   public boolean isBoundingBoxInFrustum(ICamera camera, int n) {
      return this.getBoundingBoxParent().isBoundingBoxInFrustumFully(camera, n) || camera.isBoundingBoxInFrustum(this.boundingBox);
   }

   public BlockPos getBlockPosOffset16(EnumFacing facing) {
      return this.getPositionOffset16(facing);
   }

   public void setCompiledChunk(CompiledChunk compiledChunk2) {
      this.lockCompiledChunk.lock();

      try {
         this.compiledChunk = compiledChunk2;
      } finally {
         this.lockCompiledChunk.unlock();
      }
   }

   public CompiledChunk getCompiledChunk() {
      return this.compiledChunk;
   }

   public Chunk getChunk() {
      return this.getChunk(this.position);
   }

   public RenderChunk(World world2, RenderGlobal renderGlobal2, BlockPos pos, int n) {
      this.world = world2;
      this.renderGlobal = renderGlobal2;
      this.index = n;
      if (!pos.equals(this.getPosition())) {
         this.setPosition(pos);
      }

      if (OpenGlHelper.useVbo()) {
         for (int i = 0; i < EnumWorldBlockLayer.values().length; i++) {
            this.vertexBuffers[i] = new VertexBuffer(DefaultVertexFormats.BLOCK);
         }
      }
   }

   public void setPosition(BlockPos pos) {
      this.setPosition(pos, null);
   }

   public RenderGlobal.ContainerLocalRenderInformation getRenderInfo() {
      return this.renderInfo;
   }

   public boolean isPlayerUpdate() {
      return this.playerUpdate;
   }

   private Chunk getChunk(BlockPos pos) {
      Chunk chunk2 = this.chunk;
      if (chunk2 != null && chunk2.isLoaded()) {
         return chunk2;
      } else {
         chunk2 = this.world.getChunkFromBlockCoords(pos);
         this.chunk = chunk2;
         return chunk2;
      }
   }

   private IBlockState getBlockState(ChunkCacheOF chunkCacheOF, BlockPos pos) {
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()
         && (Boolean)Baritone.settings().renderCachedChunks.value
         && !Minecraft.getMinecraft().isSingleplayer()) {
         Baritone primaryBaritone = (Baritone)BaritoneAPI.getProvider().getPrimaryBaritone();
         IPlayerContext playerContext = primaryBaritone.getPlayerContext();
         if (playerContext.player() != null && playerContext.world() != null && primaryBaritone.bsi != null) {
            return primaryBaritone.bsi.get0(pos);
         }
      }

      return chunkCacheOF.getBlockState(pos);
   }

   public void finishCompileTask() {
      this.lockCompileTask.lock();

      try {
         if (this.compileTask != null && this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.DONE) {
            this.compileTask.finish();
            this.compileTask = null;
         }
      } finally {
         this.lockCompileTask.unlock();
      }
   }

   public void stopCompileTask() {
      this.finishCompileTask();
      this.compiledChunk = CompiledChunk.DUMMY;
   }

   public BlockPos getPositionOffset16(EnumFacing facing) {
      int index2 = facing.getIndex();
      BlockPos pos = this.positionOffsets16[index2];
      if (pos == null) {
         pos = this.getPosition().offset(facing, 16);
         this.positionOffsets16[index2] = pos;
      }

      return pos;
   }

   public void multModelviewMatrix() {
      GlStateManager.multMatrix(modelviewMatrix);
   }

   public void setPosition(BlockPos pos, MutableReference mutableReference) {
      if (mutableReference != null && (Boolean)mutableReference.getValue() && this.position != null && this.chunk != null) {
         ChunkUnloadListener.postChunkUnload(ChunkCoordIntPair.chunkXZ2Int(this.chunk.xPosition, this.chunk.zPosition));
         Boolean bool = false;
         mutableReference.setValue(bool);
      }

      Augustus.getInstance().getModuleManager().chunkAnimator.onChunkPositionSet(this, pos);
      this.stopCompileTask();
      this.position = pos;
      byte b = 8;
      this.regionX = pos.getX() >> b << b;
      this.regionZ = pos.getZ() >> b << b;
      this.boundingBox = new AxisAlignedBB(pos, pos.add(16, 16, 16));
      this.initModelviewMatrix();

      for (int i = 0; i < this.positionOffsets16.length; i++) {
         this.positionOffsets16[i] = null;
      }

      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighboursUpdated = false;

      for (int j = 0; j < this.renderChunkNeighbours.length; j++) {
         RenderChunk renderChunk = this.renderChunkNeighbours[j];
         if (renderChunk != null) {
            renderChunk.renderChunkNeighboursUpdated = false;
         }
      }

      this.chunk = null;
      this.boundingBoxParent = null;
   }

   public RenderChunk getRenderChunkOffset16(ViewFrustum viewFrustum, EnumFacing facing) {
      if (!this.renderChunksOffset16Updated) {
         for (int i = 0; i < EnumFacing.VALUES.length; i++) {
            BlockPos blockPosOffset16 = this.getBlockPosOffset16(EnumFacing.VALUES[i]);
            this.renderChunksOffset16[i] = viewFrustum.getRenderChunk(blockPosOffset16);
         }

         this.renderChunksOffset16Updated = true;
      }

      return this.renderChunksOffset16[facing.ordinal()];
   }

   private void postRenderBlocks(EnumWorldBlockLayer enumWorldBlockLayer, float f, float f2, float f3, WorldRenderer worldRenderer, CompiledChunk compiledChunk2) {
      if (enumWorldBlockLayer == EnumWorldBlockLayer.TRANSLUCENT && !compiledChunk2.isLayerEmpty(enumWorldBlockLayer)) {
         worldRenderer.sortVertexData(f, f2, f3);
         compiledChunk2.setState(worldRenderer.getVertexState());
      }

      worldRenderer.finishDrawing();
   }

   public ReentrantLock getLockCompileTask() {
      return this.lockCompileTask;
   }

   public boolean isEmpty() {
      return this.isChunkEmpty(this.position);
   }

   private EnumWorldBlockLayer fixBlockLayer(IBlockState state, EnumWorldBlockLayer enumWorldBlockLayer) {
      if (CustomBlockLayers.isActive()) {
         EnumWorldBlockLayer renderLayer = CustomBlockLayers.getRenderLayer(state);
         if (renderLayer != null) {
            return renderLayer;
         }
      }

      if (this.isMipmaps) {
         if (enumWorldBlockLayer == EnumWorldBlockLayer.CUTOUT) {
            Block block = state.getBlock();
            if (block instanceof BlockRedstoneWire) {
               return enumWorldBlockLayer;
            }

            if (block instanceof BlockCactus) {
               return enumWorldBlockLayer;
            }

            return EnumWorldBlockLayer.CUTOUT_MIPPED;
         }
      } else if (enumWorldBlockLayer == EnumWorldBlockLayer.CUTOUT_MIPPED) {
         return EnumWorldBlockLayer.CUTOUT;
      }

      return enumWorldBlockLayer;
   }

   public RegionRenderCache createRegionRenderCache(World world2, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
      return new RegionRenderCache(world2, n, n2, n3, n4, n5, n6, n7);
   }

   public boolean setFrameIndex(int n) {
      if (this.frameIndex == n) {
         return false;
      } else {
         this.frameIndex = n;
         return true;
      }
   }

   public AabbFrame getBoundingBoxParent() {
      if (this.boundingBoxParent == null) {
         BlockPos position2 = this.getPosition();
         int x = position2.getX();
         int y = position2.getY();
         int z = position2.getZ();
         byte b = 5;
         int n = x >> b << b;
         int n2 = y >> b << b;
         int n3 = z >> b << b;
         if (n != x || n2 != y || n3 != z) {
            AabbFrame boundingBoxParent2 = this.renderGlobal.getRenderChunk(new BlockPos(n, n2, n3)).getBoundingBoxParent();
            if (boundingBoxParent2 != null && boundingBoxParent2.minX == (double)n && boundingBoxParent2.minY == (double)n2 && boundingBoxParent2.minZ == (double)n3) {
               this.boundingBoxParent = boundingBoxParent2;
            }
         }

         if (this.boundingBoxParent == null) {
            int n4 = 1 << b;
            this.boundingBoxParent = new AabbFrame((double)n, (double)n2, (double)n3, (double)(n + n4), (double)(n2 + n4), (double)(n3 + n4));
         }
      }

      return this.boundingBoxParent;
   }

   private ChunkCacheOF makeChunkCacheOF(BlockPos pos) {
      BlockPos pos2 = pos.add(-1, -1, -1);
      BlockPos pos3 = pos.add(16, 16, 16);
      RegionRenderCache regionRenderCache = this.createRegionRenderCache(this.world, pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 16, pos.getY() + 16, pos.getZ() + 16, 1);
      return new ChunkCacheOF(regionRenderCache, pos2, pos3, 1);
   }

   public void setRenderChunkNeighbour(EnumFacing facing, RenderChunk renderChunk) {
      this.renderChunkNeighbours[facing.ordinal()] = renderChunk;
      this.renderChunkNeighboursValid[facing.ordinal()] = renderChunk;
   }

   public void resortTransparency(float f, float f2, float f3, ChunkCompileTaskGenerator generator) {
      CompiledChunk compiledChunk2 = generator.getCompiledChunk();
      if (compiledChunk2.getState() != null && !compiledChunk2.isLayerEmpty(EnumWorldBlockLayer.TRANSLUCENT)) {
         WorldRenderer worldRendererByLayer = generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(EnumWorldBlockLayer.TRANSLUCENT);
         this.preRenderBlocks(worldRendererByLayer, this.position.getX(), this.position.getY(), this.position.getZ());
         worldRendererByLayer.setVertexState(compiledChunk2.getState());
         this.postRenderBlocks(EnumWorldBlockLayer.TRANSLUCENT, f, f2, f3, worldRendererByLayer, compiledChunk2);
      }
   }
}
