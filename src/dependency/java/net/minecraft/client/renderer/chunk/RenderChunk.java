package net.minecraft.client.renderer.chunk;

import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.SettingsTxtManager;
import cc.baritone.utils.StaticListener;
import client.Client;
import client.event.BlockActionListener;
import client.event.EventBlockAction;
import client.event.PreMotionConsumer;
import client.module.Module;
import client.render.RenderGlobalEntry;
import client.render.WorldRenderer;
import client.render.WorldRendererArrayEntry;
import client.setting.BlockSolidType;
import client.setting.MovementRangeProperty;
import client.setting.PrimaryOptionType;
import client.util.AlertNotificationSound;
import client.util.AxisAlignedBBAdapter;
import client.util.BaseIBlockStateEntry;
import client.util.BitSetEntry;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.IBlockState;
import client.util.IBlockStateHolder;
import client.util.InternalLongArrayEntry;
import client.util.LayerManager;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import client.util.VectorMatcher;
import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.AuxiliaryItemArrayEntry12Entry;
import net.minecraft.item.DiamondChestplate;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.item.GoldenPickaxe;
import net.minecraft.item.IronShard;
import net.minecraft.item.ItemArrayEntry12Entry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RenderChunk {
   public static final BlockSolidType[] blockSolidTypeArray2 = BlockSolidType.values();
   public static int count3;
   public static FloatBuffer floatBuffer;
   public final RenderGlobal renderGlobal;
   public final ReentrantLock reentrantLock2 = new ReentrantLock();
   public final ReentrantLock reentrantLock = new ReentrantLock();
   public final Set set = Sets.newHashSet();
   public final int count4;
   public final DiamondChestplate[] diamondChestplateArray = new DiamondChestplate[BlockSolidType.values().length];
   public final BlockSolidType[] blockSolidTypeArray = new BlockSolidType[1];
   public final boolean enabled2 = BuildManager.isActive45();
   public final RenderChunk[] renderChunkArray = new RenderChunk[6];
   public IronShard ironShard;
   public AxisAlignedBB axisAlignedBB;
   public int count;
   public int count5;
   public AxisAlignedBBAdapter axisAlignedBBAdapter;
   public final World world;
   public BlockPos blockPos;
   public ItemArrayEntry12Entry itemArrayEntry12Entry;
   public int count2;
   public boolean enabled;
   public EnumMap enumMap;
   public final BlockPos[] blockPosArray;
   public boolean enabled5;
   public boolean enabled3;
   public AlertNotificationSound alertNotificationSound;
   public final RenderChunk[] renderChunkArray3;
   public final RenderChunk[] renderChunkArray2;
   public boolean enabled4;
   public final AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry;

   public BlockPos resolveBlockPosByEnumFacing(EnumFacing enumFacing) {
      return this.resolveBlockPos(enumFacing);
   }

   private void applyWorldRendererArrayEntry(WorldRendererArrayEntry worldRendererArrayEntry, IronShard ironShard2, boolean[] flagArray) {
      this.applyBlockSolidType(BlockSolidType.CUTOUT, worldRendererArrayEntry, ironShard2, flagArray);
      this.applyBlockSolidType(BlockSolidType.CUTOUT_MIPPED, worldRendererArrayEntry, ironShard2, flagArray);
      this.applyBlockSolidType(BlockSolidType.TRANSLUCENT, worldRendererArrayEntry, ironShard2, flagArray);
   }

   public DiamondChestplate resolveDiamondChestplate(int count6) {
      return this.diamondChestplateArray[count6];
   }

   public void updateState4() {
      this.updateState5();
      this.ironShard = IronShard.ironShard;
   }

   public ReentrantLock getReentrantLock2() {
      return this.reentrantLock2;
   }

   public AlertNotificationSound getAlertNotificationSound() {
      return this.resolveAlertNotificationSound(this.blockPos);
   }

   private boolean matchesBlockPos(BlockPos blockPos2) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()
         && (Boolean)BaritoneManager.getSettings().renderCachedChunks.dataRef
         && !Minecraft.getMinecraft().isFullScreen()) {
         BaritoneManager baritoneManager = (BaritoneManager)SettingsTxtManager.getExtraListener().get();
         StaticListener staticListener = baritoneManager.getStaticListener();
         if (staticListener.getEntityPlayerSP() != null && staticListener.getWorld() != null && baritoneManager.heuristicManager != null) {
            BlockPos blockPos3 = this.getBlockPos();

            for (int index = -1; index <= 1; index++) {
               for (int index2 = -1; index2 <= 1; index2++) {
                  if (baritoneManager.heuristicManager.checkIndexByIndex(16 * index + blockPos3.getX(), 16 * index2 + blockPos3.getZ())) {
                     return false;
                  }
               }
            }
         }
      }

      int coordY = blockPos2.getY();
      int count6 = coordY + 15;
      return this.resolveAlertNotificationSound(blockPos2).checkIndex(coordY, count6);
   }

   public void applyItemArrayEntry12Entry(float posX, float posY, float posZ, ItemArrayEntry12Entry itemArrayEntry12Entry2) {
      IronShard ironShard2 = new IronShard();
      int coordX = this.blockPos.getX();
      int coordY = this.blockPos.getY();
      int coordZ = this.blockPos.getZ();
      itemArrayEntry12Entry2.getReentrantLock().lock();

      try {
         if (itemArrayEntry12Entry2.getPrimaryOptionType() != PrimaryOptionType.COMPILING) {
            return;
         }

         itemArrayEntry12Entry2.setIronShard(ironShard2);
      } finally {
         itemArrayEntry12Entry2.getReentrantLock().unlock();
      }

      BitSetEntry bitSetEntry = new BitSetEntry();
      HashSet set2 = Sets.newHashSet();
      if (!this.matchesBlockPos(this.blockPos)) {
         count3++;
         GoldenPickaxe goldenPickaxe = this.resolveGoldenPickaxe(this.blockPos);
         goldenPickaxe.updateState();
         boolean[] flagArray = new boolean[blockSolidTypeArray2.length];
         GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
         EventBlockAction event = new EventBlockAction(null, null);

         for (ExtendedBlockPosAdapter extendedBlockPosAdapter : (Iterable<ExtendedBlockPosAdapter>) (Iterable) ExtendedBlockPosAdapter.resolveIterable(coordX, coordY, coordZ, coordX + 15, coordY + 15, coordZ + 15)) {
            IBlockState blockState = this.resolveIBlockState(goldenPickaxe, extendedBlockPosAdapter);
            Block block = blockState.getBlock();
            BlockActionListener.onBlockAction(extendedBlockPosAdapter, blockState, event);
            if (block.processAction15()) {
               bitSetEntry.applyBlockPos(extendedBlockPosAdapter);
            }

            if (blockState.getBlock().isState8()) {
               TileEntity tileEntity = goldenPickaxe.resolveTileEntity(new BlockPos(extendedBlockPosAdapter));
               ResourceLocationArrayHolder resourceLocationArrayHolder = StandardMapEntry.standardMapEntry.resolveResourceLocationArrayHolder(tileEntity);
               if (tileEntity != null && resourceLocationArrayHolder != null) {
                  ironShard2.add(tileEntity);
                  if (resourceLocationArrayHolder.isActive()) {
                     set2.add(tileEntity);
                  }
               }
            }

            BlockSolidType[] blockSolidTypeArray3 = this.blockSolidTypeArray;
            blockSolidTypeArray3[0] = block.processAction3();

            for (BlockSolidType blockSolidType : blockSolidTypeArray3) {
               BlockSolidType blockSolidType2 = this.resolveBlockSolidType(blockState, blockSolidType);
               int ordinal = blockSolidType2.ordinal();
               if (block.processAction13() != -1) {
                  WorldRenderer worldRenderer = itemArrayEntry12Entry2.getWorldRendererArrayEntry().resolveWorldRenderer(ordinal);
                  worldRenderer.applyBlockSolidType(blockSolidType2);
                  BaseIBlockStateEntry baseIBlockStateEntry = worldRenderer.resolveBaseIBlockStateEntry(blockState, extendedBlockPosAdapter);
                  baseIBlockStateEntry.setWorldRendererArrayEntry(itemArrayEntry12Entry2.getWorldRendererArrayEntry());
                  if (!ironShard2.ordinalByBlockSolidType(blockSolidType2)) {
                     ironShard2.ordinal4(blockSolidType2);
                     this.applyWorldRenderer(worldRenderer, coordX, coordY, coordZ);
                  }

                  flagArray[ordinal] |= goldenCrystal.matchesIBlockState(blockState, extendedBlockPosAdapter, goldenPickaxe, worldRenderer);
                  if (baseIBlockStateEntry.isEnabled()) {
                     this.applyWorldRendererArrayEntry(itemArrayEntry12Entry2.getWorldRendererArrayEntry(), ironShard2, flagArray);
                     baseIBlockStateEntry.setEnabled(false);
                  }
               }
            }
         }

         for (BlockSolidType blockSolidType3 : blockSolidTypeArray2) {
            if (flagArray[blockSolidType3.ordinal()]) {
               ironShard2.ordinal(blockSolidType3);
            }

            if (ironShard2.ordinalByBlockSolidType(blockSolidType3)) {
               if (BuildManager.canExecute()) {
                  InternalLongArrayEntry.applyWorldRendererByWorldRenderer(itemArrayEntry12Entry2.getWorldRendererArrayEntry().ordinal(blockSolidType3));
               }

               WorldRenderer ordinal2 = itemArrayEntry12Entry2.getWorldRendererArrayEntry().ordinal(blockSolidType3);
               this.applyBlockSolidType(blockSolidType3, posX, posY, posZ, ordinal2, ironShard2);
               if (ordinal2.bitSet != null) {
                  ironShard2.ordinal(blockSolidType3, (BitSet)ordinal2.bitSet.clone());
               }
            } else {
               ironShard2.ordinal(blockSolidType3, null);
            }
         }

         goldenPickaxe.updateState2();
      }

      ironShard2.setPrimaryLongEntry(bitSetEntry.getPrimaryLongEntry());
      this.reentrantLock2.lock();

      try {
         HashSet set3 = Sets.newHashSet(set2);
         HashSet set4 = Sets.newHashSet(this.set);
         set3.removeAll(this.set);
         set4.removeAll(set2);
         this.set.clear();
         this.set.addAll(set2);
         this.renderGlobal.applyCollection(set4, set3);
      } finally {
         this.reentrantLock2.unlock();
      }
   }

   private GoldenPickaxe resolveGoldenPickaxe(BlockPos blockPos2) {
      BlockPos blockPos3 = blockPos2.add(-1, -1, -1);
      BlockPos blockPos4 = blockPos2.add(16, 16, 16);
      IBlockStateHolder iBlockStateHolder = this.resolveIBlockStateHolderByWorld(
         this.world, blockPos2.getX() - 1, blockPos2.getY() - 1, blockPos2.getZ() - 1, blockPos2.getX() + 16, blockPos2.getY() + 16, blockPos2.getZ() + 16, 1
      );
      return new GoldenPickaxe(iBlockStateHolder, blockPos3, blockPos4, 1);
   }

   public RenderChunk resolveRenderChunk(RenderGlobalEntry renderGlobalEntry, EnumFacing enumFacing) {
      if (!this.enabled3) {
         for (int index = 0; index < EnumFacing.VALUES.length; index++) {
            EnumFacing enumFacing2 = EnumFacing.VALUES[index];
            BlockPos blockPos2 = this.resolveBlockPosByEnumFacing(enumFacing2);
            this.renderChunkArray[index] = renderGlobalEntry.resolveRenderChunk(blockPos2);
         }

         this.enabled3 = true;
      }

      return this.renderChunkArray[enumFacing.ordinal()];
   }

   private BlockSolidType resolveBlockSolidType(IBlockState blockState, BlockSolidType blockSolidType) {
      if (LayerManager.isEnabled()) {
         BlockSolidType blockSolidType2 = LayerManager.resolveBlockSolidType(blockState);
         if (blockSolidType2 != null) {
            return blockSolidType2;
         }
      }

      if (this.enabled2) {
         if (blockSolidType == BlockSolidType.CUTOUT) {
            Block block = blockState.getBlock();
            if (block instanceof BlockRedstoneWire) {
               return blockSolidType;
            }

            if (block instanceof BlockCactus) {
               return blockSolidType;
            }

            return BlockSolidType.CUTOUT_MIPPED;
         }
      } else if (blockSolidType == BlockSolidType.CUTOUT_MIPPED) {
         return BlockSolidType.CUTOUT;
      }

      return blockSolidType;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public ItemArrayEntry12Entry getItemArrayEntry12Entry() {
      this.reentrantLock2.lock();

      ItemArrayEntry12Entry itemArrayEntry12Entry2;
      try {
         this.updateState5();
         this.itemArrayEntry12Entry = new ItemArrayEntry12Entry(this, MovementRangeProperty.REBUILD_CHUNK);
         itemArrayEntry12Entry2 = this.itemArrayEntry12Entry;
      } finally {
         this.reentrantLock2.unlock();
      }

      return itemArrayEntry12Entry2;
   }

   public void updateState() {
      GlStateManager.glStateAction(floatBuffer);
   }

   public RenderChunk resolveRenderChunk(EnumFacing enumFacing) {
      if (!this.enabled4) {
         this.updateState3();
      }

      return this.renderChunkArray2[enumFacing.ordinal()];
   }

   public BlockPos resolveBlockPos(EnumFacing enumFacing) {
      int indexOffset = enumFacing.getIndexOffset1();
      BlockPos blockPos2 = this.blockPosArray[indexOffset];
      if (blockPos2 == null) {
         blockPos2 = this.getBlockPos().getBlockPos(enumFacing, 16);
         this.blockPosArray[indexOffset] = blockPos2;
      }

      return blockPos2;
   }

   public void applyBlockPos(BlockPos blockPos2) {
      Client.getClient().getModuleManager().chunkAnimator.updateItemArrayEntry(this, blockPos2);
      this.updateState4();
      this.blockPos = blockPos2;
      this.count = blockPos2.getX() >> 8 << 8;
      this.count5 = blockPos2.getZ() >> 8 << 8;
      this.axisAlignedBB = new AxisAlignedBB(blockPos2, blockPos2.add(16, 16, 16));
      this.updateState2();

      for (int index = 0; index < this.blockPosArray.length; index++) {
         this.blockPosArray[index] = null;
      }

      this.enabled3 = false;
      this.enabled4 = false;

      for (int index2 = 0; index2 < this.renderChunkArray3.length; index2++) {
         RenderChunk renderChunk = this.renderChunkArray3[index2];
         if (renderChunk != null) {
            renderChunk.enabled4 = false;
         }
      }

      this.alertNotificationSound = null;
      this.axisAlignedBBAdapter = null;
   }

   public boolean isActive() {
      return this.matchesBlockPos(this.blockPos);
   }

   public boolean checkIndex(int count6) {
      if (this.count2 == count6) {
         return false;
      } else {
         this.count2 = count6;
         return true;
      }
   }

   private void updateState3() {
      int coordX = this.getBlockPos().getX();
      int coordZ = this.getBlockPos().getZ();
      int ordinal = EnumFacing.NORTH.ordinal();
      int ordinal2 = EnumFacing.SOUTH.ordinal();
      int ordinal3 = EnumFacing.WEST.ordinal();
      int ordinal4 = EnumFacing.EAST.ordinal();
      this.renderChunkArray2[ordinal] = this.renderChunkArray3[ordinal].getBlockPos().getZ() == coordZ - 16 ? this.renderChunkArray3[ordinal] : null;
      this.renderChunkArray2[ordinal2] = this.renderChunkArray3[ordinal2].getBlockPos().getZ() == coordZ + 16 ? this.renderChunkArray3[ordinal2] : null;
      this.renderChunkArray2[ordinal3] = this.renderChunkArray3[ordinal3].getBlockPos().getX() == coordX - 16 ? this.renderChunkArray3[ordinal3] : null;
      this.renderChunkArray2[ordinal4] = this.renderChunkArray3[ordinal4].getBlockPos().getX() == coordX + 16 ? this.renderChunkArray3[ordinal4] : null;
      this.enabled4 = true;
   }

   public AxisAlignedBBAdapter getAxisAlignedBBAdapter() {
      if (this.axisAlignedBBAdapter == null) {
         BlockPos blockPos2 = this.getBlockPos();
         int coordX = blockPos2.getX();
         int coordY = blockPos2.getY();
         int coordZ = blockPos2.getZ();
         int count6 = coordX >> 5 << 5;
         int count7 = coordY >> 5 << 5;
         int count8 = coordZ >> 5 << 5;
         if (count6 != coordX || count7 != coordY || count8 != coordZ) {
            AxisAlignedBBAdapter axisAlignedBBAdapter2 = this.renderGlobal.resolveRenderChunk(new BlockPos(count6, count7, count8)).getAxisAlignedBBAdapter();
            if (axisAlignedBBAdapter2 != null && axisAlignedBBAdapter2.minX == (double)count6 && axisAlignedBBAdapter2.minY == (double)count7 && axisAlignedBBAdapter2.minZ == (double)count8) {
               this.axisAlignedBBAdapter = axisAlignedBBAdapter2;
            }
         }

         if (this.axisAlignedBBAdapter == null) {
            this.axisAlignedBBAdapter = new AxisAlignedBBAdapter(
               (double)count6, (double)count7, (double)count8, (double)(count6 + 32), (double)(count7 + 32), (double)(count8 + 32)
            );
         }
      }

      return this.axisAlignedBBAdapter;
   }

   private IBlockState resolveIBlockState(GoldenPickaxe goldenPickaxe, BlockPos blockPos2) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()
         && (Boolean)BaritoneManager.getSettings().renderCachedChunks.dataRef
         && !Minecraft.getMinecraft().isFullScreen()) {
         BaritoneManager baritoneManager = (BaritoneManager)SettingsTxtManager.getExtraListener().get();
         StaticListener staticListener = baritoneManager.getStaticListener();
         if (staticListener.getEntityPlayerSP() != null && staticListener.getWorld() != null && baritoneManager.heuristicManager != null) {
            return baritoneManager.heuristicManager.resolveIBlockState(blockPos2);
         }
      }

      return goldenPickaxe.getBlockState(blockPos2);
   }

   public IBlockStateHolder resolveIBlockStateHolderByWorld(World world2, int count6, int count7, int count8, int count9, int count10, int count11, int count12) {
      return new IBlockStateHolder(world2, count6, count7, count8, count9, count10, count11, count12);
   }

   public void updateState6() {
      this.updateState4();

      for (int index = 0; index < BlockSolidType.values().length; index++) {
         if (this.diamondChestplateArray[index] != null) {
            this.diamondChestplateArray[index].updateState3();
         }
      }
   }

   public void applyItemArrayEntry12EntryByItemArrayEntry12(float posX, float posY, float posZ, ItemArrayEntry12Entry itemArrayEntry12Entry2) {
      IronShard ironShard2 = itemArrayEntry12Entry2.getIronShard();
      if (ironShard2.getBridgeIntArrayEntry() != null && !ironShard2.ordinal3(BlockSolidType.TRANSLUCENT)) {
         WorldRenderer ordinal = itemArrayEntry12Entry2.getWorldRendererArrayEntry().ordinal(BlockSolidType.TRANSLUCENT);
         this.applyWorldRenderer(ordinal, this.blockPos.getX(), this.blockPos.getY(), this.blockPos.getZ());
         ordinal.applyBridgeIntArrayEntry(ironShard2.getBridgeIntArrayEntry());
         this.applyBlockSolidType(BlockSolidType.TRANSLUCENT, posX, posY, posZ, ordinal, ironShard2);
      }
   }

   public void setEnabled(boolean enabled6) {
      this.enabled = enabled6;
      if (enabled6) {
         if (this.isActiveAsFlag()) {
            this.enabled5 = true;
         }
      } else {
         this.enabled5 = false;
      }
   }

   public void applyEnumFacing(EnumFacing enumFacing, RenderChunk renderChunk) {
      this.renderChunkArray3[enumFacing.ordinal()] = renderChunk;
      this.renderChunkArray2[enumFacing.ordinal()] = renderChunk;
   }

   public boolean matchesPreMotionConsumer(PreMotionConsumer preMotionConsumer, int count6) {
      return this.getAxisAlignedBBAdapter().matchesPreMotionConsumer(preMotionConsumer, count6) || preMotionConsumer.checkAxisAlignedBB(this.axisAlignedBB);
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, int count6, int count7, int count8) {
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
      if (BuildManager.isActive55()) {
         int count9 = count7 >> 8 << 8;
         int count10 = this.count;
         int count11 = this.count5;
         worldRenderer.applyDouble((double)(-count10), (double)(-count9), (double)(-count11));
      } else {
         worldRenderer.applyDouble((double)(-count6), (double)(-count7), (double)(-count8));
      }
   }

   private void applyBlockSolidType(BlockSolidType blockSolidType, float posX, float posY, float posZ, WorldRenderer worldRenderer, IronShard ironShard2) {
      if (blockSolidType == BlockSolidType.TRANSLUCENT && !ironShard2.ordinal3(blockSolidType)) {
         worldRenderer.applyFloatByFloat(posX, posY, posZ);
         ironShard2.setBridgeIntArrayEntry(worldRenderer.getBridgeIntArrayEntry());
      }

      worldRenderer.renderElement6();
   }

   private boolean isActiveAsFlag() {
      return this.world instanceof WorldClient ? ((WorldClient)this.world).isState2() : false;
   }

   public IronShard getIronShard() {
      return this.ironShard;
   }

   private void applyBlockSolidType(BlockSolidType blockSolidType, WorldRendererArrayEntry worldRendererArrayEntry, IronShard ironShard2, boolean[] flagArray) {
      if (worldRendererArrayEntry.ordinal(blockSolidType).isEnabled3()) {
         ironShard2.ordinal4(blockSolidType);
         flagArray[blockSolidType.ordinal()] = true;
      }
   }

   private AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos2) {
      AlertNotificationSound alertNotificationSound2 = this.alertNotificationSound;
      if (alertNotificationSound2 != null && alertNotificationSound2.canExecute2()) {
         return alertNotificationSound2;
      } else {
         AlertNotificationSound loggerEntry = this.world.getLoggerEntry(blockPos2);
         this.alertNotificationSound = loggerEntry;
         return loggerEntry;
      }
   }

   public AuxiliaryItemArrayEntry12Entry getAuxiliaryItemArrayEntry12Entry() {
      return this.auxiliaryItemArrayEntry12Entry;
   }

   public void updateState5() {
      this.reentrantLock2.lock();

      try {
         if (this.itemArrayEntry12Entry != null && this.itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.DONE) {
            this.itemArrayEntry12Entry.updateState();
            this.itemArrayEntry12Entry = null;
         }
      } finally {
         this.reentrantLock2.unlock();
      }
   }

   public void applyIronShard(IronShard ironShard2) {
      this.reentrantLock.lock();

      try {
         this.ironShard = ironShard2;
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public ItemArrayEntry12Entry getItemArrayEntry12EntryAsItemArrayEntry12() {
      this.reentrantLock2.lock();

      try {
         if (this.itemArrayEntry12Entry == null || this.itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.PENDING) {
            if (this.itemArrayEntry12Entry != null && this.itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.DONE) {
               this.itemArrayEntry12Entry.updateState();
               this.itemArrayEntry12Entry = null;
            }

            this.itemArrayEntry12Entry = new ItemArrayEntry12Entry(this, MovementRangeProperty.RESORT_TRANSPARENCY);
            this.itemArrayEntry12Entry.setIronShard(this.ironShard);
            return this.itemArrayEntry12Entry;
         }
      } finally {
         this.reentrantLock2.unlock();
      }

      return null;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void updateState2() {
      if (floatBuffer == null) {
         floatBuffer = VectorMatcher.resolveFloatBuffer(16);
         GlStateManager.pushMatrix();
         GlStateManager.loadIdentity();
         float factor = 1.000001F;
         GlStateManager.translate(-8.0F, -8.0F, -8.0F);
         GlStateManager.translate3f3(factor, factor, factor);
         GlStateManager.translate(8.0F, 8.0F, 8.0F);
         GlStateManager.glStateAction2(2982, floatBuffer);
         GlStateManager.popMatrix();
      }
   }

   public RenderChunk(World world2, RenderGlobal renderGlobal2, BlockPos blockPos2, int count6) {
      this.ironShard = IronShard.ironShard;
      this.itemArrayEntry12Entry = null;
      this.count2 = -1;
      this.enabled = true;
      this.blockPosArray = new BlockPos[EnumFacing.VALUES.length];
      this.enabled5 = false;
      this.enabled3 = false;
      this.renderChunkArray3 = new RenderChunk[EnumFacing.VALUES.length];
      this.renderChunkArray2 = new RenderChunk[EnumFacing.VALUES.length];
      this.enabled4 = false;
      this.auxiliaryItemArrayEntry12Entry = new AuxiliaryItemArrayEntry12Entry(this, null, 0);
      this.world = world2;
      this.renderGlobal = renderGlobal2;
      this.count4 = count6;
      if (!blockPos2.equals(this.getBlockPos())) {
         this.applyBlockPos(blockPos2);
      }

      if (OpenGlHelper.isActive()) {
         for (int index = 0; index < BlockSolidType.values().length; index++) {
            this.diamondChestplateArray[index] = new DiamondChestplate(DefaultVertexFormats.auxiliaryLoggerEntry5);
         }
      }
   }

   @Override
   public String toString() {
      return "pos: " + this.getBlockPos() + ", frameIndex: " + this.count2;
   }
}
