package client.util;

import client.event.DefaultListener;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.AuxiliaryBlockLeaves;
import net.minecraft.block.AuxiliaryBlockLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMonsterEgg;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockReeds;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandstone;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.BlockTallgrass;
import net.minecraft.block.BlockTnt;
import net.minecraft.block.BlockTripwire;
import net.minecraft.block.Blocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.IronDust;
import net.minecraft.item.IronNugget;

public class CommonMapEntry {
   public final Map map = Maps.newIdentityHashMap();
   public final EnhancedMapEntry enhancedMapEntry = new EnhancedMapEntry();
   public final IronDust ironDust;

   public void applyBlockArray(Block[] blockArray) {
      this.enhancedMapEntry.addAll(blockArray);
   }

   public IBakedModel resolveIBakedModel(IBlockState blockState) {
      IBakedModel iBakedModel = (IBakedModel)this.map.get(blockState);
      if (iBakedModel == null) {
         iBakedModel = this.ironDust.getIBakedModel();
      }

      return iBakedModel;
   }

   public EnhancedMapEntry getEnhancedMapEntry() {
      return this.enhancedMapEntry;
   }

   private void updateState() {
      this.applyBlockArray(
         new Block[]{
            Blocks.air,
            Blocks.blockFlowingLava,
            Blocks.blockLava2,
            Blocks.blockFlowingLava2,
            Blocks.blockLava,
            Blocks.blockPistonExtension,
            Blocks.blockTrappedChest,
            Blocks.block60,
            Blocks.block128,
            Blocks.block37,
            Blocks.blockSkull,
            Blocks.block2,
            Blocks.block145,
            Blocks.block108,
            Blocks.block79,
            Blocks.block138
         }
      );
      this.applyBlock(Blocks.stone, new IronNugget().withIProperty(BlockStone.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.block58, new IronNugget().withIProperty(BlockPrismarine.immutableSetEntry).getListener137Entry());
      this.applyBlock(
         Blocks.decayableManager2,
         new IronNugget()
            .withIProperty(BlockLeaves.immutableSetEntry)
            .withLabelText("_leaves")
            .addAll(new IProperty[]{DecayableManager.internalImmutableSetEntry, DecayableManager.internalImmutableSetEntry2})
            .getListener137Entry()
      );
      this.applyBlock(
         Blocks.decayableManager,
         new IronNugget()
            .withIProperty(AuxiliaryBlockLeaves.immutableSetEntry)
            .withLabelText("_leaves")
            .addAll(new IProperty[]{DecayableManager.internalImmutableSetEntry, DecayableManager.internalImmutableSetEntry2})
            .getListener137Entry()
      );
      this.applyBlock(Blocks.blockCactus, new IronNugget().addAll(new IProperty[]{BlockCactus.auxiliaryImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.blockReeds, new IronNugget().addAll(new IProperty[]{BlockReeds.auxiliaryImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block114, new IronNugget().addAll(new IProperty[]{BlockJukebox.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block139, new IronNugget().addAll(new IProperty[]{BlockCommandBlock.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block63, new IronNugget().withIProperty(BlockCobblestoneWall.immutableSetEntry).withLabelText("_wall").getListener137Entry());
      this.applyBlock(
         Blocks.blockDoublePlant,
         new IronNugget().withIProperty(BlockDoublePlant.immutableSetEntry).addAll(new IProperty[]{BlockDoublePlant.immutableSetEntry2}).getListener137Entry()
      );
      this.applyBlock(Blocks.block117, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(Blocks.block43, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(Blocks.block73, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(Blocks.block31, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(Blocks.block101, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(Blocks.block116, new IronNugget().addAll(new IProperty[]{BlockAcaciaFenceGate.internalImmutableSetEntry2}).getListener137Entry());
      this.applyBlock(
         Blocks.block6,
         new IronNugget().addAll(new IProperty[]{BlockTripwire.internalImmutableSetEntry4, BlockTripwire.internalImmutableSetEntry}).getListener137Entry()
      );
      this.applyBlock(
         Blocks.extraBlockAdapter, new IronNugget().withIProperty(BlockPlanks.immutableSetEntry).withLabelText("_double_slab").getListener137Entry()
      );
      this.applyBlock(Blocks.extraBlockAdapter2, new IronNugget().withIProperty(BlockPlanks.immutableSetEntry).withLabelText("_slab").getListener137Entry());
      this.applyBlock(Blocks.block135, new IronNugget().addAll(new IProperty[]{BlockTnt.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.blockFire, new IronNugget().addAll(new IProperty[]{BlockFire.auxiliaryImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.blockRedstoneWire, new IronNugget().addAll(new IProperty[]{BlockRedstoneWire.auxiliaryImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block110, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block36, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block124, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block112, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block84, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block99, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.ironDoor, new IronNugget().addAll(new IProperty[]{BlockDarkOakDoor.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.wool, new IronNugget().withIProperty(BlockStainedHardenedClay.immutableSetEntry).withLabelText("_wool").getListener137Entry());
      this.applyBlock(
         Blocks.block141, new IronNugget().withIProperty(BlockStainedHardenedClay.immutableSetEntry).withLabelText("_carpet").getListener137Entry()
      );
      this.applyBlock(
         Blocks.block107,
         new IronNugget().withIProperty(BlockStainedHardenedClay.immutableSetEntry).withLabelText("_stained_hardened_clay").getListener137Entry()
      );
      this.applyBlock(
         Blocks.blockStainedGlassPane,
         new IronNugget().withIProperty(BlockStainedHardenedClay.immutableSetEntry).withLabelText("_stained_glass_pane").getListener137Entry()
      );
      this.applyBlock(
         Blocks.blockStainedGlass,
         new IronNugget().withIProperty(BlockStainedHardenedClay.immutableSetEntry).withLabelText("_stained_glass").getListener137Entry()
      );
      this.applyBlock(Blocks.block136, new IronNugget().withIProperty(BlockSandstone.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.block119, new IronNugget().withIProperty(BlockRedSandstone.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.blockTallgrass, new IronNugget().withIProperty(BlockTallgrass.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.bed, new IronNugget().addAll(new IProperty[]{BlockBed.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.immutableSetEntry2Entry, new IronNugget().withIProperty(Blocks.immutableSetEntry2Entry.getIProperty()).getListener137Entry());
      this.applyBlock(Blocks.immutableSetEntry2Entry2, new IronNugget().withIProperty(Blocks.immutableSetEntry2Entry2.getIProperty()).getListener137Entry());
      this.applyBlock(
         Blocks.extraBlockAdapter6, new IronNugget().withIProperty(ImmutableSetWrapper.immutableSetEntry3).withLabelText("_slab").getListener137Entry()
      );
      this.applyBlock(
         Blocks.extraBlockAdapter4, new IronNugget().withIProperty(ImmutableSetHolder.immutableSetEntry3).withLabelText("_slab").getListener137Entry()
      );
      this.applyBlock(Blocks.block22, new IronNugget().withIProperty(BlockMonsterEgg.immutableSetEntry).withLabelText("_monster_egg").getListener137Entry());
      this.applyBlock(Blocks.block42, new IronNugget().withIProperty(BlockStonebrick.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.block129, new IronNugget().addAll(new IProperty[]{BlockDispenser.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block69, new IronNugget().addAll(new IProperty[]{BlockDropper.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block140, new IronNugget().withIProperty(BlockLog.immutableSetEntry3).withLabelText("_log").getListener137Entry());
      this.applyBlock(Blocks.block104, new IronNugget().withIProperty(AuxiliaryBlockLog.immutableSetEntry4).withLabelText("_log").getListener137Entry());
      this.applyBlock(Blocks.block142, new IronNugget().withIProperty(BlockPlanks.immutableSetEntry).withLabelText("_planks").getListener137Entry());
      this.applyBlock(Blocks.block83, new IronNugget().withIProperty(BlockSapling.immutableSetEntry).withLabelText("_sapling").getListener137Entry());
      this.applyBlock(Blocks.blockSand, new IronNugget().withIProperty(BlockSand.immutableSetEntry).getListener137Entry());
      this.applyBlock(Blocks.blockHopper, new IronNugget().addAll(new IProperty[]{BlockHopper.internalImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block56, new IronNugget().addAll(new IProperty[]{BlockFlowerPot.auxiliaryImmutableSetEntry}).getListener137Entry());
      this.applyBlock(Blocks.block17, new DampenerTracker(this));
      this.applyBlock(Blocks.blockDeadbush, new DampenerScheduler(this));
      this.applyBlock(Blocks.block55, new DampenerWatcher(this));
      this.applyBlock(Blocks.block133, new DampenerMonitor(this));
      this.applyBlock(Blocks.block115, new DampenerProfiler(this));
      this.applyBlock(Blocks.extraBlockAdapter5, new DampenerPredictor(this));
      this.applyBlock(Blocks.extraBlockAdapter3, new DampenerDirector(this));
   }

   public void updateState2() {
      this.map.clear();

      for (Entry entry : (Iterable<Entry>) (this.enhancedMapEntry.getMap().entrySet())) {
         this.map.put(entry.getKey(), this.ironDust.resolveIBakedModel((ResourceLocationAdapter)entry.getValue()));
      }
   }

   public void applyBlock(Block block, DefaultListener defaultListener) {
      this.enhancedMapEntry.put(block, defaultListener);
   }

   public IronDust getIronDust() {
      return this.ironDust;
   }

   public ServiceStringEntry resolveServiceStringEntry(IBlockState blockState) {
      Block block = blockState.getBlock();
      IBakedModel iBakedModel = this.resolveIBakedModel(blockState);
      if (iBakedModel == null || iBakedModel == this.ironDust.getIBakedModel()) {
         if (block == Blocks.block108
            || block == Blocks.block37
            || block == Blocks.blockTrappedChest
            || block == Blocks.block128
            || block == Blocks.block138
            || block == Blocks.block79) {
            return this.ironDust.getListenerAdapter27().get("minecraft:blocks/planks_oak");
         }

         if (block == Blocks.block60) {
            return this.ironDust.getListenerAdapter27().get("minecraft:blocks/obsidian");
         }

         if (block == Blocks.blockFlowingLava2 || block == Blocks.blockLava) {
            return this.ironDust.getListenerAdapter27().get("minecraft:blocks/lava_still");
         }

         if (block == Blocks.blockFlowingLava || block == Blocks.blockLava2) {
            return this.ironDust.getListenerAdapter27().get("minecraft:blocks/water_still");
         }

         if (block == Blocks.blockSkull) {
            return this.ironDust.getListenerAdapter27().get("minecraft:blocks/soul_sand");
         }

         if (block == Blocks.block145) {
            return this.ironDust.getListenerAdapter27().get("minecraft:items/barrier");
         }
      }

      if (iBakedModel == null) {
         iBakedModel = this.ironDust.getIBakedModel();
      }

      return iBakedModel.getServiceStringEntry();
   }

   public CommonMapEntry(IronDust ironDust2) {
      this.ironDust = ironDust2;
      this.updateState();
   }
}
