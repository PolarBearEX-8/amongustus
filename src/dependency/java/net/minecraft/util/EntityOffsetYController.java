package net.minecraft.util;

import client.util.BuildManager;
import client.util.TargetEstimator;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.item.IronShard;

public class EntityOffsetYController {
   public Entity entity = null;
   public double value = 0.0;
   public double value2 = -2.1474836E9F;
   public double value3 = -2.1474836E9F;
   public double value4 = -2.1474836E9F;
   public int count = 0;
   public boolean enabled = false;
   public long timestamp = 0L;
   public Set set = new HashSet();
   public AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

   private BlockPos resolveBlockPos(RenderChunk renderChunk, BlockPos blockPos, EnumFacing enumFacing) {
      return renderChunk != null ? renderChunk.resolveBlockPosByEnumFacing(enumFacing) : blockPos.getBlockPos(enumFacing, 16);
   }

   public void applyRenderGlobalByRenderGlobal(RenderGlobal renderGlobal) {
      if (BuildManager.isActive8()) {
         long timestamp2 = System.currentTimeMillis();
         if (timestamp2 < this.timestamp + 500L) {
            return;
         }

         this.timestamp = timestamp2;
      }

      double offset = this.entity.posX - 0.5;
      double offset2 = this.entity.posY - 0.5 + this.value;
      double offset3 = this.entity.posZ - 0.5;
      int count2 = TargetEstimator.computeCount(this.entity);
      double offset4 = offset - this.value2;
      double offset5 = offset2 - this.value3;
      double offset6 = offset3 - this.value4;
      double offset7 = 0.1;
      if (Math.abs(offset4) > offset7 || Math.abs(offset5) > offset7 || Math.abs(offset6) > offset7 || this.count != count2) {
         this.value2 = offset;
         this.value3 = offset2;
         this.value4 = offset3;
         this.count = count2;
         this.enabled = false;
         WorldClient worldClient = renderGlobal.getWorldClient();
         if (worldClient != null) {
            this.auxiliaryBlockPosAdapter
               .resolveAuxiliaryBlockPosAdapter(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2), MathHelper.ceiling_double_int(offset3));
            Block block = worldClient.getBlockState(this.auxiliaryBlockPosAdapter).getBlock();
            this.enabled = block == Blocks.blockLava2;
         }

         HashSet set2 = new HashSet();
         if (count2 > 0) {
            EnumFacing enumFacing = (MathHelper.ceiling_double_int(offset) & 15) >= 8 ? EnumFacing.EAST : EnumFacing.WEST;
            EnumFacing enumFacing2 = (MathHelper.ceiling_double_int(offset2) & 15) >= 8 ? EnumFacing.UP : EnumFacing.DOWN;
            EnumFacing enumFacing3 = (MathHelper.ceiling_double_int(offset3) & 15) >= 8 ? EnumFacing.SOUTH : EnumFacing.NORTH;
            BlockPos blockPos = new BlockPos(offset, offset2, offset3);
            RenderChunk renderChunk = renderGlobal.resolveRenderChunk(blockPos);
            BlockPos blockPos2 = this.resolveBlockPos(renderChunk, blockPos, enumFacing);
            RenderChunk renderChunk2 = renderGlobal.resolveRenderChunk(blockPos2);
            BlockPos blockPos3 = this.resolveBlockPos(renderChunk, blockPos, enumFacing3);
            RenderChunk renderChunk3 = renderGlobal.resolveRenderChunk(blockPos3);
            BlockPos blockPos4 = this.resolveBlockPos(renderChunk2, blockPos2, enumFacing3);
            RenderChunk renderChunk4 = renderGlobal.resolveRenderChunk(blockPos4);
            BlockPos blockPos5 = this.resolveBlockPos(renderChunk, blockPos, enumFacing2);
            RenderChunk renderChunk5 = renderGlobal.resolveRenderChunk(blockPos5);
            BlockPos blockPos6 = this.resolveBlockPos(renderChunk5, blockPos5, enumFacing);
            RenderChunk renderChunk6 = renderGlobal.resolveRenderChunk(blockPos6);
            BlockPos blockPos7 = this.resolveBlockPos(renderChunk5, blockPos5, enumFacing3);
            RenderChunk renderChunk7 = renderGlobal.resolveRenderChunk(blockPos7);
            BlockPos blockPos8 = this.resolveBlockPos(renderChunk6, blockPos6, enumFacing3);
            RenderChunk renderChunk8 = renderGlobal.resolveRenderChunk(blockPos8);
            this.applyRenderChunk(renderChunk, this.set, set2);
            this.applyRenderChunk(renderChunk2, this.set, set2);
            this.applyRenderChunk(renderChunk3, this.set, set2);
            this.applyRenderChunk(renderChunk4, this.set, set2);
            this.applyRenderChunk(renderChunk5, this.set, set2);
            this.applyRenderChunk(renderChunk6, this.set, set2);
            this.applyRenderChunk(renderChunk7, this.set, set2);
            this.applyRenderChunk(renderChunk8, this.set, set2);
         }

         this.applyRenderGlobal(renderGlobal);
         this.set = set2;
      }
   }

   public double getValue2() {
      return this.value2;
   }

   @Override
   public String toString() {
      return "Entity: " + this.entity + ", offsetY: " + this.value;
   }

   public double getValue() {
      return this.value;
   }

   public void applyRenderGlobal(RenderGlobal renderGlobal) {
      for (BlockPos blockPos : (Iterable<BlockPos>)(Iterable<?>)this.set) {
         RenderChunk renderChunk = renderGlobal.resolveRenderChunk(blockPos);
         this.applyRenderChunk(renderChunk, (Set)null, (Set)null);
      }
   }

   public int getCount() {
      return this.count;
   }

   public double getValue4() {
      return this.value4;
   }

   public Entity getEntity() {
      return this.entity;
   }

   private void applyRenderChunk(RenderChunk renderChunk, Set set2, Set set3) {
      if (renderChunk != null) {
         IronShard ironShard = renderChunk.getIronShard();
         if (ironShard != null && !ironShard.isEnabled()) {
            renderChunk.setEnabled(true);
         }

         BlockPos blockPos = renderChunk.getBlockPos();
         if (set2 != null) {
            set2.remove(blockPos);
         }

         if (set3 != null) {
            set3.add(blockPos);
         }
      }
   }

   public double getValue3() {
      return this.value3;
   }

   public EntityOffsetYController(Entity entity2) {
      this.entity = entity2;
      this.value = (double)entity2.getHeight2();
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
