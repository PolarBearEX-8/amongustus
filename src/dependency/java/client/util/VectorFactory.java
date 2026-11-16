package client.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCaveSpiderAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class VectorFactory extends VectorDispatcher {
   public final EntityCaveSpiderAdapter entityCaveSpiderAdapter;

   @Override
   public Vec3 getVec3() {
      return new Vec3(this.entityCaveSpiderAdapter.posX, this.entityCaveSpiderAdapter.posY, this.entityCaveSpiderAdapter.posZ);
   }

   @Override
   public World getWorldObj() {
      return this.entityCaveSpiderAdapter.worldObj;
   }

   @Override
   public int getCountAsIndex() {
      return 1;
   }

   @Override
   public void updateState() {
      this.entityCaveSpiderAdapter.getDataWatcher().applyTargetByTarget(23, this.getLabelText());
      this.entityCaveSpiderAdapter.getDataWatcher().applyTargetByTarget(24, ChatComponentSerializer.toJson(this.getIChatComponent()));
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.entityCaveSpiderAdapter.posX, this.entityCaveSpiderAdapter.posY + 0.5, this.entityCaveSpiderAdapter.posZ);
   }

   public VectorFactory(EntityCaveSpiderAdapter entityCaveSpiderAdapter2) {
      this.entityCaveSpiderAdapter = entityCaveSpiderAdapter2;
   }

   @Override
   public void applyByteBuf(ByteBuf byteBuf) {
      byteBuf.writeInt(this.entityCaveSpiderAdapter.getEntityId());
   }

   @Override
   public Entity getEntity() {
      return this.entityCaveSpiderAdapter;
   }
}
