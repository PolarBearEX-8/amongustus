package net.minecraft.world.chunk;

import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EmptyChunk extends Chunk {
   @Override
   public void setChunkModified() {
   }

   @Override
   public boolean getAreLevelsEmpty(int n, int n2) {
      return true;
   }

   @Override
   public int getBlockMetadata(BlockPos pos) {
      return 0;
   }

   @Override
   public void getEntitiesOfTypeWithinAAAB(Class clazz, AxisAlignedBB box, List list, Predicate predicate) {
   }

   @Override
   public boolean needsSaving(boolean flag) {
      return false;
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public void setLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3, int n4) {
   }

   @Override
   public void removeTileEntity(BlockPos pos) {
   }

   @Override
   public void addTileEntity(BlockPos pos, TileEntity tileEntity) {
   }

   @Override
   public void onChunkUnload() {
   }

   @Override
   public Block getBlock(BlockPos pos) {
      return Blocks.air;
   }

   @Override
   public void onChunkLoad() {
   }

   @Override
   public int getLightSubtracted(int n, int n2, int n3, int n4) {
      return 0;
   }

   @Override
   public int getBlockLightOpacity(int n, int n2, int n3) {
      return 255;
   }

   @Override
   public void generateSkylightMap() {
   }

   public boolean canSeeSky(BlockPos pos) {
      return false;
   }

   @Override
   public void removeEntityAtIndex(Entity entity, int n) {
   }

   @Override
   public boolean isAtLocation(int n, int n2) {
      return n == this.xPosition && n2 == this.zPosition;
   }

   @Override
   public void addTileEntity(TileEntity tileEntity) {
   }

   @Override
   public int getHeightValue(int n, int n2) {
      return 0;
   }

   @Override
   public void addEntity(Entity entity) {
   }

   @Override
   public void removeEntity(Entity entity) {
   }

   @Override
   public void generateHeightMap() {
   }

   @Override
   public int getLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      return enumSkyBlock.defaultLightValue;
   }

   public EmptyChunk(World world, int n, int n2) {
      super(world, n, n2);
   }

   @Override
   public void getEntitiesWithinAABBForEntity(Entity entity, AxisAlignedBB box, List list, Predicate predicate) {
   }

   @Override
   public TileEntity getTileEntity(BlockPos pos, Chunk.EnumCreateEntityType enumCreateEntityType) {
      return null;
   }

   @Override
   public Random getRandomWithSeed(long l) {
      return new Random(
         this.getWorld().getSeed()
               + (long)(this.xPosition * this.xPosition * 4987142)
               + (long)(this.xPosition * 5947611)
               + (long)(this.zPosition * this.zPosition) * 4392871L
               + (long)(this.zPosition * 389711)
            ^ l
      );
   }
}
