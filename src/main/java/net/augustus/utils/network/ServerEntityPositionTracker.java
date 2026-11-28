package net.augustus.utils.network;

import java.util.concurrent.ConcurrentHashMap;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.util.Vec3;

public class ServerEntityPositionTracker implements MC {
   public static final ConcurrentHashMap TRACKED_ENTITIES = new ConcurrentHashMap();

   public static void onPacket(Packet packet, EnumPacketDirection enumPacketDirection) {
      if (enumPacketDirection == EnumPacketDirection.CLIENTBOUND
         && (packet instanceof S14PacketEntity || packet instanceof S18PacketEntityTeleport || packet instanceof S12PacketEntityVelocity)) {
         if (packet instanceof S14PacketEntity) {
            S14PacketEntity packet2 = (S14PacketEntity)packet;
            Entity entity = (Entity)TRACKED_ENTITIES.get(packet2.getEntityId());
            if (entity instanceof EntityLivingBase) {
               EntityLivingBase entity2 = (EntityLivingBase)entity;
               entity2.realServerPosX = entity2.realServerPosX + packet2.getPosX();
               entity2.realServerPosY = entity2.realServerPosY + packet2.getPosY();
               entity2.realServerPosZ = entity2.realServerPosZ + packet2.getPosZ();
               updateServerPosition(packet2, entity2);
            }
         }

         if (packet instanceof S18PacketEntityTeleport) {
            S18PacketEntityTeleport packet3 = (S18PacketEntityTeleport)packet;
            Entity entity3 = (Entity)TRACKED_ENTITIES.get(packet3.getEntityId());
            if (entity3 instanceof EntityLivingBase) {
               EntityLivingBase entity4 = (EntityLivingBase)entity3;
               entity4.realServerPosX = packet3.getX();
               entity4.realServerPosY = packet3.getY();
               entity4.realServerPosZ = packet3.getZ();
               updateServerPosition(packet3, entity4);
            }
         }

         if (packet instanceof S12PacketEntityVelocity) {
            PacketThreadUtil.runOnMainThreadImmediate(() -> {
               S12PacketEntityVelocity packet4 = (S12PacketEntityVelocity)packet;
               Entity entityByID = mc.theWorld.getEntityByID(packet4.getEntityID());
               if (entityByID instanceof EntityLivingBase) {
                  EntityLivingBase entity5 = (EntityLivingBase)entityByID;
                  entity5.lastMotionX = (double)packet4.getMotionX() / 8000.0;
                  entity5.lastMotionY = (double)packet4.getMotionY() / 8000.0;
                  entity5.lastMotionZ = (double)packet4.getMotionZ() / 8000.0;
               }
            });
         }
      }
   }

   private static void updateServerPosition(Packet packet, EntityLivingBase entity) {
      entity.trackedPosX = entity.predictedPosX;
      entity.trackedPosY = entity.predictedPosY;
      entity.trackedPosZ = entity.predictedPosZ;
      entity.predictedPosX = entity.realPosX;
      entity.predictedPosY = entity.realPosY;
      entity.predictedPosZ = entity.realPosZ;
      if (entity instanceof EntityVillager) {
      }

      entity.realPosX = (double)entity.realServerPosX / 32.0;
      entity.realPosY = (double)entity.realServerPosY / 32.0;
      entity.realPosZ = (double)entity.realServerPosZ / 32.0;
      PacketThreadUtil.runOnMainThreadImmediate(() -> {
         Vec3 vec = (Vec3)(entity.packetPositionMap.put(packet, new Vec3(entity.realPosX, entity.realPosY, entity.realPosZ)));
      });
      entity.realPosRotationIncrements = 3;
   }
}
