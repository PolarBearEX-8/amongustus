package net.minecraft.entity;

import net.minecraft.network.play.server.S14PacketEntity;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTrackerEntry {
   private static final Logger LOGGER = LogManager.getLogger();
   public Entity trackedEntity;
   public int trackingDistanceThreshold;
   public int updateFrequency;
   public int encodedPosX;
   public int encodedPosY;
   public int encodedPosZ;
   public int encodedRotationYaw;
   public int encodedRotationPitch;
   public int lastHeadMotion;
   public double lastTrackedEntityMotionX;
   public double lastTrackedEntityMotionY;
   public double lastTrackedEntityMotionZ;
   public int updateCounter;
   private double lastTrackedEntityPosX;
   private double lastTrackedEntityPosY;
   private double lastTrackedEntityPosZ;
   private boolean firstUpdateDone;
   private boolean sendVelocityUpdates;
   private int ticksSinceLastForcedTeleport;
   private Entity lastRidingEntity;
   private boolean ridingEntity;
   private boolean onGround;
   public boolean playerEntitiesUpdated;
   public Set trackingPlayers = Sets.newHashSet();

   @Override
   public boolean equals(Object obj) {
      return obj instanceof EntityTrackerEntry ? ((EntityTrackerEntry)obj).trackedEntity.getEntityId() == this.trackedEntity.getEntityId() : false;
   }

   public boolean isVisibleTo(EntityPlayerMP entityPlayerMP) {
      double d = entityPlayerMP.posX - (double)(this.encodedPosX / 32);
      double d2 = entityPlayerMP.posZ - (double)(this.encodedPosZ / 32);
      return d >= (double)(-this.trackingDistanceThreshold)
         && d <= (double)this.trackingDistanceThreshold
         && d2 >= (double)(-this.trackingDistanceThreshold)
         && d2 <= (double)this.trackingDistanceThreshold
         && this.trackedEntity.isSpectatedByPlayer(entityPlayerMP);
   }

   public void sendDestroyEntityPacketToTrackedPlayers() {
      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.trackingPlayers)) {
         entityPlayerMP.removeEntity(this.trackedEntity);
      }
   }

   public EntityTrackerEntry(Entity entity, int n, int n2, boolean flag) {
      this.trackedEntity = entity;
      this.trackingDistanceThreshold = n;
      this.updateFrequency = n2;
      this.sendVelocityUpdates = flag;
      this.encodedPosX = MathHelper.floor_double(entity.posX * 32.0);
      this.encodedPosY = MathHelper.floor_double(entity.posY * 32.0);
      this.encodedPosZ = MathHelper.floor_double(entity.posZ * 32.0);
      this.encodedRotationYaw = MathHelper.floor_float(entity.rotationYaw * 256.0F / 360.0F);
      this.encodedRotationPitch = MathHelper.floor_float(entity.rotationPitch * 256.0F / 360.0F);
      this.lastHeadMotion = MathHelper.floor_float(entity.getRotationYawHead() * 256.0F / 360.0F);
      this.onGround = entity.onGround;
   }

   public void updatePlayerEntities(List list) {
      for (int i = 0; i < list.size(); i++) {
         this.updatePlayerEntity((EntityPlayerMP)list.get(i));
      }
   }

   private void sendMetadataToAllAssociatedPlayers() {
      DataWatcher dataWatcher = this.trackedEntity.getDataWatcher();
      if (dataWatcher.hasObjectChanged()) {
         this.sendToTrackingAndSelf(new S1CPacketEntityMetadata(this.trackedEntity.getEntityId(), dataWatcher, false));
      }

      if (this.trackedEntity instanceof EntityLivingBase) {
         Set attributeInstanceSet = ((ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap()).getAttributeInstanceSet();
         if (!attributeInstanceSet.isEmpty()) {
            this.sendToTrackingAndSelf(new S20PacketEntityProperties(this.trackedEntity.getEntityId(), attributeInstanceSet));
         }

         attributeInstanceSet.clear();
      }
   }

   public void sendToTrackingAndSelf(Packet packet) {
      this.sendPacketToTrackedPlayers(packet);
      if (this.trackedEntity instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.trackedEntity).playerNetServerHandler.sendPacket(packet);
      }
   }

   public void updatePlayerList(List list) {
      this.playerEntitiesUpdated = false;
      if (!this.firstUpdateDone || this.trackedEntity.getDistanceSq(this.lastTrackedEntityPosX, this.lastTrackedEntityPosY, this.lastTrackedEntityPosZ) > 16.0) {
         this.lastTrackedEntityPosX = this.trackedEntity.posX;
         this.lastTrackedEntityPosY = this.trackedEntity.posY;
         this.lastTrackedEntityPosZ = this.trackedEntity.posZ;
         this.firstUpdateDone = true;
         this.playerEntitiesUpdated = true;
         this.updatePlayerEntities(list);
      }

      if (this.lastRidingEntity != this.trackedEntity.ridingEntity || this.trackedEntity.ridingEntity != null && this.updateCounter % 60 == 0) {
         this.lastRidingEntity = this.trackedEntity.ridingEntity;
         this.sendPacketToTrackedPlayers(new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.ridingEntity));
      }

      if (this.trackedEntity instanceof EntityItemFrame && this.updateCounter % 10 == 0) {
         ItemStack displayedItem = ((EntityItemFrame)this.trackedEntity).getDisplayedItem();
         if (displayedItem != null && displayedItem.getItem() instanceof ItemMap) {
            MapData mapData = Items.filled_map.getMapData(displayedItem, this.trackedEntity.entityWorld);

            for (EntityPlayer player : (Iterable<EntityPlayer>)(list)) {
               EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
               mapData.updateVisiblePlayers(entityPlayerMP, displayedItem);
               Packet packet = Items.filled_map.createMapDataPacket(displayedItem, this.trackedEntity.entityWorld, entityPlayerMP);
               if (packet != null) {
                  entityPlayerMP.playerNetServerHandler.sendPacket(packet);
               }
            }
         }

         this.sendMetadataToAllAssociatedPlayers();
      }

      if (this.updateCounter % this.updateFrequency == 0 || this.trackedEntity.isAirBorne || this.trackedEntity.getDataWatcher().hasObjectChanged()) {
         if (this.trackedEntity.ridingEntity == null) {
            this.ticksSinceLastForcedTeleport++;
            int n = MathHelper.floor_double(this.trackedEntity.posX * 32.0);
            int n2 = MathHelper.floor_double(this.trackedEntity.posY * 32.0);
            int n3 = MathHelper.floor_double(this.trackedEntity.posZ * 32.0);
            int n4 = MathHelper.floor_float(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
            int n5 = MathHelper.floor_float(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
            int n6 = n - this.encodedPosX;
            int n7 = n2 - this.encodedPosY;
            int n8 = n3 - this.encodedPosZ;
            Object obj = null;
            boolean flag = Math.abs(n6) >= 4 || Math.abs(n7) >= 4 || Math.abs(n8) >= 4 || this.updateCounter % 60 == 0;
            boolean flag2 = Math.abs(n4 - this.encodedRotationYaw) >= 4 || Math.abs(n5 - this.encodedRotationPitch) >= 4;
            if (this.updateCounter > 0 || this.trackedEntity instanceof EntityArrow) {
               if (n6 >= -128
                  && n6 < 128
                  && n7 >= -128
                  && n7 < 128
                  && n8 >= -128
                  && n8 < 128
                  && this.ticksSinceLastForcedTeleport <= 400
                  && !this.ridingEntity
                  && this.onGround == this.trackedEntity.onGround) {
                  if ((!flag || !flag2) && !(this.trackedEntity instanceof EntityArrow)) {
                     if (flag) {
                        obj = new S14PacketEntity.S15PacketEntityRelMove(
                           this.trackedEntity.getEntityId(), (byte)n6, (byte)n7, (byte)n8, this.trackedEntity.onGround
                        );
                     } else if (flag2) {
                        obj = new S14PacketEntity.S16PacketEntityLook(this.trackedEntity.getEntityId(), (byte)n4, (byte)n5, this.trackedEntity.onGround);
                     }
                  } else {
                     obj = new S14PacketEntity.S17PacketEntityLookMove(
                        this.trackedEntity.getEntityId(), (byte)n6, (byte)n7, (byte)n8, (byte)n4, (byte)n5, this.trackedEntity.onGround
                     );
                  }
               } else {
                  this.onGround = this.trackedEntity.onGround;
                  this.ticksSinceLastForcedTeleport = 0;
                  obj = new S18PacketEntityTeleport(this.trackedEntity.getEntityId(), n, n2, n3, (byte)n4, (byte)n5, this.trackedEntity.onGround);
               }
            }

            if (this.sendVelocityUpdates) {
               double d = this.trackedEntity.motionX - this.lastTrackedEntityMotionX;
               double d2 = this.trackedEntity.motionY - this.lastTrackedEntityMotionY;
               double d3 = this.trackedEntity.motionZ - this.lastTrackedEntityMotionZ;
               double d4 = 0.02;
               double d5 = d * d + d2 * d2 + d3 * d3;
               if (d5 > d4 * d4
                  || d5 > 0.0 && this.trackedEntity.motionX == 0.0 && this.trackedEntity.motionY == 0.0 && this.trackedEntity.motionZ == 0.0) {
                  this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
                  this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
                  this.lastTrackedEntityMotionZ = this.trackedEntity.motionZ;
                  this.sendPacketToTrackedPlayers(new S12PacketEntityVelocity(this.trackedEntity.getEntityId(), this.lastTrackedEntityMotionX, this.lastTrackedEntityMotionY, this.lastTrackedEntityMotionZ));
               }
            }

            if (obj != null) {
               this.sendPacketToTrackedPlayers((Packet)obj);
            }

            this.sendMetadataToAllAssociatedPlayers();
            if (flag) {
               this.encodedPosX = n;
               this.encodedPosY = n2;
               this.encodedPosZ = n3;
            }

            if (flag2) {
               this.encodedRotationYaw = n4;
               this.encodedRotationPitch = n5;
            }

            this.ridingEntity = false;
         } else {
            int n9 = MathHelper.floor_float(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
            int n10 = MathHelper.floor_float(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
            if (Math.abs(n9 - this.encodedRotationYaw) >= 4 || Math.abs(n10 - this.encodedRotationPitch) >= 4) {
               this.sendPacketToTrackedPlayers(
                  new S14PacketEntity.S16PacketEntityLook(this.trackedEntity.getEntityId(), (byte)n9, (byte)n10, this.trackedEntity.onGround)
               );
               this.encodedRotationYaw = n9;
               this.encodedRotationPitch = n10;
            }

            this.encodedPosX = MathHelper.floor_double(this.trackedEntity.posX * 32.0);
            this.encodedPosY = MathHelper.floor_double(this.trackedEntity.posY * 32.0);
            this.encodedPosZ = MathHelper.floor_double(this.trackedEntity.posZ * 32.0);
            this.sendMetadataToAllAssociatedPlayers();
            this.ridingEntity = true;
         }

         int n11 = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         if (Math.abs(n11 - this.lastHeadMotion) >= 4) {
            this.sendPacketToTrackedPlayers(new S19PacketEntityHeadLook(this.trackedEntity, (byte)n11));
            this.lastHeadMotion = n11;
         }

         this.trackedEntity.isAirBorne = false;
      }

      this.updateCounter++;
      if (this.trackedEntity.velocityChanged) {
         this.sendToTrackingAndSelf(new S12PacketEntityVelocity(this.trackedEntity));
         this.trackedEntity.velocityChanged = false;
      }
   }

   public void sendPacketToTrackedPlayers(Packet packet) {
      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.trackingPlayers)) {
         entityPlayerMP.playerNetServerHandler.sendPacket(packet);
      }
   }

   public void removeTrackedPlayerSymmetric(EntityPlayerMP entityPlayerMP) {
      if (this.trackingPlayers.contains(entityPlayerMP)) {
         this.trackingPlayers.remove(entityPlayerMP);
         entityPlayerMP.removeEntity(this.trackedEntity);
      }
   }

   private boolean isPlayerWatchingThisChunk(EntityPlayerMP entityPlayerMP) {
      return entityPlayerMP.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(entityPlayerMP, this.trackedEntity.chunkCoordX, this.trackedEntity.chunkCoordZ);
   }

   @Override
   public int hashCode() {
      return this.trackedEntity.getEntityId();
   }

   public void updatePlayerEntity(EntityPlayerMP entityPlayerMP) {
      if (entityPlayerMP != this.trackedEntity) {
         if (this.isVisibleTo(entityPlayerMP)) {
            if (!this.trackingPlayers.contains(entityPlayerMP) && (this.isPlayerWatchingThisChunk(entityPlayerMP) || this.trackedEntity.forceSpawn)) {
               this.trackingPlayers.add(entityPlayerMP);
               Packet packet = this.createSpawnPacket();
               entityPlayerMP.playerNetServerHandler.sendPacket(packet);
               if (!this.trackedEntity.getDataWatcher().getIsBlank()) {
                  entityPlayerMP.playerNetServerHandler
                     .sendPacket(new S1CPacketEntityMetadata(this.trackedEntity.getEntityId(), this.trackedEntity.getDataWatcher(), true));
               }

               NBTTagCompound nbtTagCompound = this.trackedEntity.getNBTTagCompound();
               if (nbtTagCompound != null) {
                  entityPlayerMP.playerNetServerHandler.sendPacket(new S49PacketUpdateEntityNBT(this.trackedEntity.getEntityId(), nbtTagCompound));
               }

               if (this.trackedEntity instanceof EntityLivingBase) {
                  Collection watchedAttributes = ((ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap()).getWatchedAttributes();
                  if (!watchedAttributes.isEmpty()) {
                     entityPlayerMP.playerNetServerHandler.sendPacket(new S20PacketEntityProperties(this.trackedEntity.getEntityId(), watchedAttributes));
                  }
               }

               this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
               this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
               this.lastTrackedEntityMotionZ = this.trackedEntity.motionZ;
               if (this.sendVelocityUpdates && !(packet instanceof S0FPacketSpawnMob)) {
                  entityPlayerMP.playerNetServerHandler
                     .sendPacket(
                        new S12PacketEntityVelocity(
                           this.trackedEntity.getEntityId(), this.trackedEntity.motionX, this.trackedEntity.motionY, this.trackedEntity.motionZ
                        )
                     );
               }

               if (this.trackedEntity.ridingEntity != null) {
                  entityPlayerMP.playerNetServerHandler.sendPacket(new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.ridingEntity));
               }

               if (this.trackedEntity instanceof EntityLiving && ((EntityLiving)this.trackedEntity).getLeashedToEntity() != null) {
                  entityPlayerMP.playerNetServerHandler
                     .sendPacket(new S1BPacketEntityAttach(1, this.trackedEntity, ((EntityLiving)this.trackedEntity).getLeashedToEntity()));
               }

               if (this.trackedEntity instanceof EntityLivingBase) {
                  for (int i = 0; i < 5; i++) {
                     ItemStack equipmentInSlot = ((EntityLivingBase)this.trackedEntity).getEquipmentInSlot(i);
                     if (equipmentInSlot != null) {
                        entityPlayerMP.playerNetServerHandler.sendPacket(new S04PacketEntityEquipment(this.trackedEntity.getEntityId(), i, equipmentInSlot));
                     }
                  }
               }

               if (this.trackedEntity instanceof EntityPlayer) {
                  EntityPlayer trackedEntity2 = (EntityPlayer)this.trackedEntity;
                  if (trackedEntity2.isPlayerSleeping()) {
                     entityPlayerMP.playerNetServerHandler.sendPacket(new S0APacketUseBed(trackedEntity2, new BlockPos(this.trackedEntity)));
                  }
               }

               if (this.trackedEntity instanceof EntityLivingBase) {

                  for (PotionEffect potionEffect : (Iterable<PotionEffect>)(((EntityLivingBase)this.trackedEntity).getActivePotionEffects())) {
                     entityPlayerMP.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.trackedEntity.getEntityId(), potionEffect));
                  }
               }
            }
         } else if (this.trackingPlayers.contains(entityPlayerMP)) {
            this.trackingPlayers.remove(entityPlayerMP);
            entityPlayerMP.removeEntity(this.trackedEntity);
         }
      }
   }

   private Packet createSpawnPacket() {
      if (this.trackedEntity.isDead) {
         LOGGER.warn("Fetching addPacket for removed entity");
      }

      if (this.trackedEntity instanceof EntityItem) {
         return new S0EPacketSpawnObject(this.trackedEntity, 2, 1);
      } else if (this.trackedEntity instanceof EntityPlayerMP) {
         return new S0CPacketSpawnPlayer((EntityPlayer)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityMinecart) {
         EntityMinecart trackedEntity2 = (EntityMinecart)this.trackedEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 10, trackedEntity2.getMinecartType().getNetworkID());
      } else if (this.trackedEntity instanceof EntityBoat) {
         return new S0EPacketSpawnObject(this.trackedEntity, 1);
      } else if (this.trackedEntity instanceof IAnimals) {
         this.lastHeadMotion = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         return new S0FPacketSpawnMob((EntityLivingBase)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityFishHook) {
         EntityPlayer angler = ((EntityFishHook)this.trackedEntity).angler;
         return new S0EPacketSpawnObject(this.trackedEntity, 90, angler != null ? angler.getEntityId() : this.trackedEntity.getEntityId());
      } else if (this.trackedEntity instanceof EntityArrow) {
         Entity shootingEntity = ((EntityArrow)this.trackedEntity).shootingEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 60, shootingEntity != null ? shootingEntity.getEntityId() : this.trackedEntity.getEntityId());
      } else if (this.trackedEntity instanceof EntitySnowball) {
         return new S0EPacketSpawnObject(this.trackedEntity, 61);
      } else if (this.trackedEntity instanceof EntityPotion) {
         return new S0EPacketSpawnObject(this.trackedEntity, 73, ((EntityPotion)this.trackedEntity).getPotionDamage());
      } else if (this.trackedEntity instanceof EntityExpBottle) {
         return new S0EPacketSpawnObject(this.trackedEntity, 75);
      } else if (this.trackedEntity instanceof EntityEnderPearl) {
         return new S0EPacketSpawnObject(this.trackedEntity, 65);
      } else if (this.trackedEntity instanceof EntityEnderEye) {
         return new S0EPacketSpawnObject(this.trackedEntity, 72);
      } else if (this.trackedEntity instanceof EntityFireworkRocket) {
         return new S0EPacketSpawnObject(this.trackedEntity, 76);
      } else if (this.trackedEntity instanceof EntityFireball) {
         EntityFireball trackedEntity3 = (EntityFireball)this.trackedEntity;
         S0EPacketSpawnObject packet = null;
         byte b = 63;
         if (this.trackedEntity instanceof EntitySmallFireball) {
            b = 64;
         } else if (this.trackedEntity instanceof EntityWitherSkull) {
            b = 66;
         }

         if (trackedEntity3.shootingEntity != null) {
            packet = new S0EPacketSpawnObject(this.trackedEntity, b, ((EntityFireball)this.trackedEntity).shootingEntity.getEntityId());
         } else {
            packet = new S0EPacketSpawnObject(this.trackedEntity, b, 0);
         }

         packet.setSpeedX((int)(trackedEntity3.accelerationX * 8000.0));
         packet.setSpeedY((int)(trackedEntity3.accelerationY * 8000.0));
         packet.setSpeedZ((int)(trackedEntity3.accelerationZ * 8000.0));
         return packet;
      } else if (this.trackedEntity instanceof EntityEgg) {
         return new S0EPacketSpawnObject(this.trackedEntity, 62);
      } else if (this.trackedEntity instanceof EntityTNTPrimed) {
         return new S0EPacketSpawnObject(this.trackedEntity, 50);
      } else if (this.trackedEntity instanceof EntityEnderCrystal) {
         return new S0EPacketSpawnObject(this.trackedEntity, 51);
      } else if (this.trackedEntity instanceof EntityFallingBlock) {
         EntityFallingBlock trackedEntity4 = (EntityFallingBlock)this.trackedEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 70, Block.getStateId(trackedEntity4.getBlock()));
      } else if (this.trackedEntity instanceof EntityArmorStand) {
         return new S0EPacketSpawnObject(this.trackedEntity, 78);
      } else if (this.trackedEntity instanceof EntityPainting) {
         return new S10PacketSpawnPainting((EntityPainting)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityItemFrame) {
         EntityItemFrame trackedEntity5 = (EntityItemFrame)this.trackedEntity;
         S0EPacketSpawnObject packet2 = new S0EPacketSpawnObject(this.trackedEntity, 71, trackedEntity5.facingDirection.getHorizontalIndex());
         BlockPos hangingPosition = trackedEntity5.getHangingPosition();
         packet2.setX(MathHelper.floor_float((float)(hangingPosition.getX() * 32)));
         packet2.setY(MathHelper.floor_float((float)(hangingPosition.getY() * 32)));
         packet2.setZ(MathHelper.floor_float((float)(hangingPosition.getZ() * 32)));
         return packet2;
      } else if (this.trackedEntity instanceof EntityLeashKnot) {
         EntityLeashKnot trackedEntity6 = (EntityLeashKnot)this.trackedEntity;
         S0EPacketSpawnObject packet3 = new S0EPacketSpawnObject(this.trackedEntity, 77);
         BlockPos hangingPosition2 = trackedEntity6.getHangingPosition();
         packet3.setX(MathHelper.floor_float((float)(hangingPosition2.getX() * 32)));
         packet3.setY(MathHelper.floor_float((float)(hangingPosition2.getY() * 32)));
         packet3.setZ(MathHelper.floor_float((float)(hangingPosition2.getZ() * 32)));
         return packet3;
      } else if (this.trackedEntity instanceof EntityXPOrb) {
         return new S11PacketSpawnExperienceOrb((EntityXPOrb)this.trackedEntity);
      } else {
         throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
      }
   }

   public void removeFromTrackedPlayers(EntityPlayerMP entityPlayerMP) {
      if (this.trackingPlayers.contains(entityPlayerMP)) {
         entityPlayerMP.removeEntity(this.trackedEntity);
         this.trackingPlayers.remove(entityPlayerMP);
      }
   }
}
