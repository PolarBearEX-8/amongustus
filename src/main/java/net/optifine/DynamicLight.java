package net.optifine;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;

public class DynamicLight {
   private Entity entity = null;
   private double offsetY = 0.0;
   private double lastPosX = -2.1474836E9F;
   private double lastPosY = -2.1474836E9F;
   private double lastPosZ = -2.1474836E9F;
   private int lastLightLevel = 0;
   private boolean underwater = false;
   private long timeCheckMs = 0L;
   private Set setLitChunkPos = new HashSet();
   private BlockPos.MutableBlockPos blockPosMutable = new BlockPos.MutableBlockPos();

   public double getLastPosZ() {
      return this.lastPosZ;
   }

   public boolean isUnderwater() {
      return this.underwater;
   }

   public void updateLitChunks(RenderGlobal renderGlobal) {
      for (BlockPos pos : (Iterable<BlockPos>)(this.setLitChunkPos)) {
         this.updateChunkLight(renderGlobal.getRenderChunk(pos), (Set)null, (Set)null);
      }
   }

   public DynamicLight(Entity entity2) {
      this.entity = entity2;
      this.offsetY = (double)entity2.getEyeHeight();
   }

   private BlockPos getChunkPos(RenderChunk renderChunk, BlockPos pos, EnumFacing facing) {
      return renderChunk != null ? renderChunk.getBlockPosOffset16(facing) : pos.offset(facing, 16);
   }

   public double getLastPosX() {
      return this.lastPosX;
   }

   public double getLastPosY() {
      return this.lastPosY;
   }

   public double getOffsetY() {
      return this.offsetY;
   }

   @Override
   public String toString() {
      return "Entity: " + this.entity + ", offsetY: " + this.offsetY;
   }

   public Entity getEntity() {
      return this.entity;
   }

   public void update(RenderGlobal renderGlobal) {
      if (Config.isDynamicLightsFast()) {
         long l = System.currentTimeMillis();
         if (l < this.timeCheckMs + 500L) {
            return;
         }

         this.timeCheckMs = l;
      }

      double d = this.entity.posX - 0.5;
      double d2 = this.entity.posY - 0.5 + this.offsetY;
      double d3 = this.entity.posZ - 0.5;
      int lightLevel = DynamicLights.getLightLevel(this.entity);
      double d4 = d - this.lastPosX;
      double d5 = d2 - this.lastPosY;
      double d6 = d3 - this.lastPosZ;
      double d7 = 0.1;
      if (Math.abs(d4) > d7 || Math.abs(d5) > d7 || Math.abs(d6) > d7 || this.lastLightLevel != lightLevel) {
         this.lastPosX = d;
         this.lastPosY = d2;
         this.lastPosZ = d3;
         this.lastLightLevel = lightLevel;
         this.underwater = false;
         WorldClient theWorld = renderGlobal.getTheWorld();
         if (theWorld != null) {
            this.blockPosMutable.set(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
            this.underwater = theWorld.getBlockState(this.blockPosMutable).getBlock() == Blocks.water;
         }

         HashSet set = new HashSet();
         if (lightLevel > 0) {
            EnumFacing facing = (MathHelper.floor_double(d) & 15) >= 8 ? EnumFacing.EAST : EnumFacing.WEST;
            EnumFacing facing2 = (MathHelper.floor_double(d2) & 15) >= 8 ? EnumFacing.UP : EnumFacing.DOWN;
            EnumFacing facing3 = (MathHelper.floor_double(d3) & 15) >= 8 ? EnumFacing.SOUTH : EnumFacing.NORTH;
            BlockPos pos = new BlockPos(d, d2, d3);
            RenderChunk renderChunk = renderGlobal.getRenderChunk(pos);
            BlockPos chunkPos = this.getChunkPos(renderChunk, pos, facing);
            RenderChunk renderChunk2 = renderGlobal.getRenderChunk(chunkPos);
            RenderChunk renderChunk3 = renderGlobal.getRenderChunk(this.getChunkPos(renderChunk, pos, facing3));
            RenderChunk renderChunk4 = renderGlobal.getRenderChunk(this.getChunkPos(renderChunk2, chunkPos, facing3));
            BlockPos chunkPos4 = this.getChunkPos(renderChunk, pos, facing2);
            RenderChunk renderChunk5 = renderGlobal.getRenderChunk(chunkPos4);
            BlockPos chunkPos5 = this.getChunkPos(renderChunk5, chunkPos4, facing);
            RenderChunk renderChunk6 = renderGlobal.getRenderChunk(chunkPos5);
            RenderChunk renderChunk7 = renderGlobal.getRenderChunk(this.getChunkPos(renderChunk5, chunkPos4, facing3));
            RenderChunk renderChunk8 = renderGlobal.getRenderChunk(this.getChunkPos(renderChunk6, chunkPos5, facing3));
            this.updateChunkLight(renderChunk, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk2, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk3, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk4, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk5, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk6, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk7, this.setLitChunkPos, set);
            this.updateChunkLight(renderChunk8, this.setLitChunkPos, set);
         }

         this.updateLitChunks(renderGlobal);
         this.setLitChunkPos = set;
      }
   }

   private void updateChunkLight(RenderChunk renderChunk, Set set, Set set2) {
      if (renderChunk != null) {
         CompiledChunk compiledChunk = renderChunk.getCompiledChunk();
         if (compiledChunk != null && !compiledChunk.isEmpty()) {
            renderChunk.setNeedsUpdate(true);
         }

         BlockPos position = renderChunk.getPosition();
         if (set != null) {
            set.remove(position);
         }

         if (set2 != null) {
            set2.add(position);
         }
      }
   }

   public int getLastLightLevel() {
      return this.lastLightLevel;
   }
}
