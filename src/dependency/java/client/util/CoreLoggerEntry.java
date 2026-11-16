package client.util;

import client.event.NativeListener;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFilledMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S15PacketEntityRelMove;
import net.minecraft.network.play.server.S16PacketEntityLook;
import net.minecraft.network.play.server.S17PacketEntityLookMove;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoreLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public Entity entity;
   public int count;
   public int count2;
   public int count3;
   public int count4;
   public int count5;
   public int count6;
   public int count7;
   public int count8;
   public double value;
   public double value2;
   public double value3;
   public int count9;
   public double value4;
   public double value5;
   public double value6;
   public boolean enabled;
   public boolean enabled2;
   public int count10;
   public Entity entity2;
   public boolean enabled3;
   public boolean enabled4;
   public boolean enabled5;
   public Set set = Sets.newHashSet();

   public CoreLoggerEntry(Entity entity3, int count11, int count12, boolean flag) {
      this.entity = entity3;
      this.count = count11;
      this.count2 = count12;
      this.enabled2 = flag;
      this.count3 = MathHelper.ceiling_double_int(entity3.posX * 32.0);
      this.count4 = MathHelper.ceiling_double_int(entity3.posY * 32.0);
      this.count5 = MathHelper.ceiling_double_int(entity3.posZ * 32.0);
      this.count6 = MathHelper.floor_float(entity3.rotationYaw * 256.0F / 360.0F);
      this.count7 = MathHelper.floor_float(entity3.rotationPitch * 256.0F / 360.0F);
      this.count8 = MathHelper.floor_float(entity3.getScaleFactor12() * 256.0F / 360.0F);
      this.enabled4 = entity3.onGround;
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      if (this.set.contains(entityPlayerAdapter)) {
         entityPlayerAdapter.applyEntity30(this.entity);
         this.set.remove(entityPlayerAdapter);
      }
   }

   private Packet getPacket() {
      if (this.entity.isDead) {
         logger.warn("Fetching addPacket for removed entity");
      }

      if (this.entity instanceof EntityItem) {
         return new S0EPacketSpawnObject(this.entity, 2, 1);
      } else if (this.entity instanceof EntityPlayerAdapter) {
         return new S0CPacketSpawnPlayer((EntityPlayer)this.entity);
      } else if (this.entity instanceof EntityMinecart) {
         EntityMinecart entity3 = (EntityMinecart)this.entity;
         return new S0EPacketSpawnObject(this.entity, 10, entity3.getFURNACE().getCount());
      } else if (this.entity instanceof EntityBoat) {
         return new S0EPacketSpawnObject(this.entity, 1);
      } else if (this.entity instanceof NativeListener) {
         this.count8 = MathHelper.floor_float(this.entity.getScaleFactor12() * 256.0F / 360.0F);
         return new S0FPacketSpawnMob((EntityLivingBase)this.entity);
      } else if (this.entity instanceof EntityFishHook) {
         EntityPlayer targetPlayer = ((EntityFishHook)this.entity).targetPlayer;
         return new S0EPacketSpawnObject(this.entity, 90, targetPlayer != null ? targetPlayer.getEntityId() : this.entity.getEntityId());
      } else if (this.entity instanceof EntityArrow) {
         Entity entity4 = ((EntityArrow)this.entity).entity2;
         return new S0EPacketSpawnObject(this.entity, 60, entity4 != null ? entity4.getEntityId() : this.entity.getEntityId());
      } else if (this.entity instanceof EntitySnowball) {
         return new S0EPacketSpawnObject(this.entity, 61);
      } else if (this.entity instanceof EntityThrownpotion) {
         return new S0EPacketSpawnObject(this.entity, 73, ((EntityThrownpotion)this.entity).getCount4());
      } else if (this.entity instanceof EntityThrownexpbottle) {
         return new S0EPacketSpawnObject(this.entity, 75);
      } else if (this.entity instanceof EntityThrownenderpearl) {
         return new S0EPacketSpawnObject(this.entity, 65);
      } else if (this.entity instanceof EntityEyeofendersignal) {
         return new S0EPacketSpawnObject(this.entity, 72);
      } else if (this.entity instanceof EntityFireworksrocketentity) {
         return new S0EPacketSpawnObject(this.entity, 76);
      } else if (this.entity instanceof EntityFireball) {
         EntityFireball entity5 = (EntityFireball)this.entity;
         byte byteValue = 63;
         if (this.entity instanceof EntitySmallfireball) {
            byteValue = 64;
         } else if (this.entity instanceof EntityWitherskull) {
            byteValue = 66;
         }

         S0EPacketSpawnObject packet;
         if (entity5.entityLivingBase != null) {
            packet = new S0EPacketSpawnObject(this.entity, byteValue, ((EntityFireball)this.entity).entityLivingBase.getEntityId());
         } else {
            packet = new S0EPacketSpawnObject(this.entity, byteValue, 0);
         }

         packet.setCount7((int)(entity5.lastTickPosZ2 * 8000.0));
         packet.setCount8((int)(entity5.coordOffset * 8000.0));
         packet.setCount10((int)(entity5.prevPosZ2 * 8000.0));
         return packet;
      } else if (this.entity instanceof EntityThrownegg) {
         return new S0EPacketSpawnObject(this.entity, 62);
      } else if (this.entity instanceof EntityPrimedtnt) {
         return new S0EPacketSpawnObject(this.entity, 50);
      } else if (this.entity instanceof EntityEndercrystal) {
         return new S0EPacketSpawnObject(this.entity, 51);
      } else if (this.entity instanceof EntityFallingsand) {
         EntityFallingsand entity6 = (EntityFallingsand)this.entity;
         return new S0EPacketSpawnObject(this.entity, 70, Block.processAction5(entity6.getIBlockState()));
      } else if (this.entity instanceof EntityArmorstand) {
         return new S0EPacketSpawnObject(this.entity, 78);
      } else if (this.entity instanceof EntityPainting) {
         return new S10PacketSpawnPainting((EntityPainting)this.entity);
      } else if (this.entity instanceof EntityItemframe) {
         EntityItemframe entity7 = (EntityItemframe)this.entity;
         S0EPacketSpawnObject packet2 = new S0EPacketSpawnObject(this.entity, 71, entity7.enumFacing2.getIndexOffset());
         BlockPos blockPos = entity7.getBlockPos2();
         packet2.setCount4(MathHelper.floor_float((float)(blockPos.getX() * 32)));
         packet2.setCount6(MathHelper.floor_float((float)(blockPos.getY() * 32)));
         packet2.setCount5(MathHelper.floor_float((float)(blockPos.getZ() * 32)));
         return packet2;
      } else if (this.entity instanceof EntityLeashknot) {
         EntityLeashknot entity8 = (EntityLeashknot)this.entity;
         S0EPacketSpawnObject packet3 = new S0EPacketSpawnObject(this.entity, 77);
         BlockPos blockPos2 = entity8.getBlockPos2();
         packet3.setCount4(MathHelper.floor_float((float)(blockPos2.getX() * 32)));
         packet3.setCount6(MathHelper.floor_float((float)(blockPos2.getY() * 32)));
         packet3.setCount5(MathHelper.floor_float((float)(blockPos2.getZ() * 32)));
         return packet3;
      } else if (this.entity instanceof EntityXporb) {
         return new S11PacketSpawnExperienceOrb((EntityXporb)this.entity);
      } else {
         throw new IllegalArgumentException("Don't know how to add " + this.entity.getClass() + "!");
      }
   }

   @Override
   public boolean equals(Object other) {
      return other instanceof CoreLoggerEntry ? ((CoreLoggerEntry)other).entity.getEntityId() == this.entity.getEntityId() : false;
   }

   public boolean matchesEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      double offset = entityPlayerAdapter.posX - (double)(this.count3 / 32);
      double offset2 = entityPlayerAdapter.posZ - (double)(this.count5 / 32);
      return offset >= (double)(-this.count)
         && offset <= (double)this.count
         && offset2 >= (double)(-this.count)
         && offset2 <= (double)this.count
         && this.entity.matchesEntityPlayerAdapter(entityPlayerAdapter);
   }

   @Override
   public int hashCode() {
      return this.entity.getEntityId();
   }

   public void applyPacket(Packet packet) {
      this.applyPacketByPacket(packet);
      if (this.entity instanceof EntityPlayerAdapter) {
         ((EntityPlayerAdapter)this.entity).auxiliaryListenerAdapter4.applyPacket(packet);
      }
   }

   public void applyPacketByPacket(Packet packet) {
      Iterator iterator = this.set.iterator();

      while (iterator.hasNext()) {
         ((EntityPlayerAdapter)iterator.next()).auxiliaryListenerAdapter4.applyPacket(packet);
      }
   }

   public void applyListByList(List list) {
      this.enabled5 = false;
      if (!this.enabled || this.entity.getDouble3(this.value4, this.value5, this.value6) > 16.0) {
         this.value4 = this.entity.posX;
         this.value5 = this.entity.posY;
         this.value6 = this.entity.posZ;
         this.enabled = true;
         this.enabled5 = true;
         this.applyList(list);
      }

      if (this.entity2 != this.entity.ridingEntity || this.entity.ridingEntity != null && this.count9 % 60 == 0) {
         this.entity2 = this.entity.ridingEntity;
         this.applyPacketByPacket(new S1BPacketEntityAttach(0, this.entity, this.entity.ridingEntity));
      }

      if (this.entity instanceof EntityItemframe && this.count9 % 10 == 0) {
         ItemStack stack = ((EntityItemframe)this.entity).getItemStack();
         if (stack != null && stack.getItem() instanceof ItemFilledMap) {
            DimensionStringEntry dimensionStringEntry = Items.filledMap.resolveDimensionStringEntryByItemStack(stack, this.entity.worldObj);

            for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable<?>)list) {
               dimensionStringEntry.applyEntityPlayer(entityPlayerAdapter, stack);
               Packet packet = Items.filledMap.resolvePacketByItemStack(stack, this.entity.worldObj, entityPlayerAdapter);
               if (packet != null) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
               }
            }
         }

         this.updateState2();
      }

      if (this.count9 % this.count2 == 0 || this.entity.enabled4 || this.entity.getDataWatcher().isEnabled2()) {
         if (this.entity.ridingEntity == null) {
            this.count10++;
            int count11 = MathHelper.ceiling_double_int(this.entity.posX * 32.0);
            int count12 = MathHelper.ceiling_double_int(this.entity.posY * 32.0);
            int count13 = MathHelper.ceiling_double_int(this.entity.posZ * 32.0);
            int count14 = MathHelper.floor_float(this.entity.rotationYaw * 256.0F / 360.0F);
            int count15 = MathHelper.floor_float(this.entity.rotationPitch * 256.0F / 360.0F);
            int count16 = count11 - this.count3;
            int count17 = count12 - this.count4;
            int count18 = count13 - this.count5;
            Object entry = null;
            boolean flag = Math.abs(count16) >= 4 || Math.abs(count17) >= 4 || Math.abs(count18) >= 4 || this.count9 % 60 == 0;
            boolean flag2 = Math.abs(count14 - this.count6) >= 4 || Math.abs(count15 - this.count7) >= 4;
            if (this.count9 > 0 || this.entity instanceof EntityArrow) {
               if (count16 >= -128
                  && count16 < 128
                  && count17 >= -128
                  && count17 < 128
                  && count18 >= -128
                  && count18 < 128
                  && this.count10 <= 400
                  && !this.enabled3
                  && this.enabled4 == this.entity.onGround) {
                  if ((!flag || !flag2) && !(this.entity instanceof EntityArrow)) {
                     if (flag) {
                        entry = new S15PacketEntityRelMove(this.entity.getEntityId(), (byte)count16, (byte)count17, (byte)count18, this.entity.onGround);
                     } else if (flag2) {
                        entry = new S16PacketEntityLook(this.entity.getEntityId(), (byte)count14, (byte)count15, this.entity.onGround);
                     }
                  } else {
                     entry = new S17PacketEntityLookMove(
                        this.entity.getEntityId(), (byte)count16, (byte)count17, (byte)count18, (byte)count14, (byte)count15, this.entity.onGround
                     );
                  }
               } else {
                  this.enabled4 = this.entity.onGround;
                  this.count10 = 0;
                  entry = new S18PacketEntityTeleport(this.entity.getEntityId(), count11, count12, count13, (byte)count14, (byte)count15, this.entity.onGround);
               }
            }

            if (this.enabled2) {
               double offset = this.entity.motionX - this.value;
               double offset2 = this.entity.motionY - this.value2;
               double offset3 = this.entity.motionZ - this.value3;
               double offset4 = 0.02;
               double offset5 = offset * offset + offset2 * offset2 + offset3 * offset3;
               if (offset5 > offset4 * offset4 || offset5 > 0.0 && this.entity.motionX == 0.0 && this.entity.motionY == 0.0 && this.entity.motionZ == 0.0) {
                  this.value = this.entity.motionX;
                  this.value2 = this.entity.motionY;
                  this.value3 = this.entity.motionZ;
                  this.applyPacketByPacket(new S12PacketEntityVelocity(this.entity.getEntityId(), this.value, this.value2, this.value3));
               }
            }

            if (entry != null) {
               this.applyPacketByPacket((Packet)entry);
            }

            this.updateState2();
            if (flag) {
               this.count3 = count11;
               this.count4 = count12;
               this.count5 = count13;
            }

            if (flag2) {
               this.count6 = count14;
               this.count7 = count15;
            }

            this.enabled3 = false;
         } else {
            int count19 = MathHelper.floor_float(this.entity.rotationYaw * 256.0F / 360.0F);
            int count20 = MathHelper.floor_float(this.entity.rotationPitch * 256.0F / 360.0F);
            if (Math.abs(count19 - this.count6) >= 4 || Math.abs(count20 - this.count7) >= 4) {
               this.applyPacketByPacket(new S16PacketEntityLook(this.entity.getEntityId(), (byte)count19, (byte)count20, this.entity.onGround));
               this.count6 = count19;
               this.count7 = count20;
            }

            this.count3 = MathHelper.ceiling_double_int(this.entity.posX * 32.0);
            this.count4 = MathHelper.ceiling_double_int(this.entity.posY * 32.0);
            this.count5 = MathHelper.ceiling_double_int(this.entity.posZ * 32.0);
            this.updateState2();
            this.enabled3 = true;
         }

         int count21 = MathHelper.floor_float(this.entity.getScaleFactor12() * 256.0F / 360.0F);
         if (Math.abs(count21 - this.count8) >= 4) {
            this.applyPacketByPacket(new S19PacketEntityHeadLook(this.entity, (byte)count21));
            this.count8 = count21;
         }

         this.entity.enabled4 = false;
      }

      this.count9++;
      if (this.entity.enabled8) {
         this.applyPacket(new S12PacketEntityVelocity(this.entity));
         this.entity.enabled8 = false;
      }
   }

   public void applyEntityPlayerAdapter2(EntityPlayerAdapter entityPlayerAdapter) {
      if (this.set.contains(entityPlayerAdapter)) {
         this.set.remove(entityPlayerAdapter);
         entityPlayerAdapter.applyEntity30(this.entity);
      }
   }

   public void applyList(List list) {
      for (int index = 0; index < list.size(); index++) {
         this.applyEntityPlayerAdapter((EntityPlayerAdapter)list.get(index));
      }
   }

   private boolean matchesEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      return entityPlayerAdapter.getWorldServer().getManagedLoggerEntry().matchesEntityPlayerAdapter(entityPlayerAdapter, this.entity.count7, this.entity.count8);
   }

   private void updateState2() {
      DataWatcher dataWatcher = this.entity.getDataWatcher();
      if (dataWatcher.isEnabled2()) {
         this.applyPacket(new S1CPacketEntityMetadata(this.entity.getEntityId(), dataWatcher, false));
      }

      if (this.entity instanceof EntityLivingBase) {
         Set set2 = ((SetEntry)((EntityLivingBase)this.entity).getAdvancedMapEntry()).getSet();
         if (!set2.isEmpty()) {
            this.applyPacket(new S20PacketEntityProperties(this.entity.getEntityId(), set2));
         }

         set2.clear();
      }
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      if (entityPlayerAdapter != this.entity) {
         if (this.matchesEntityPlayerAdapter(entityPlayerAdapter)) {
            if (!this.set.contains(entityPlayerAdapter) && (this.matchesEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter) || this.entity.enabled10)) {
               this.set.add(entityPlayerAdapter);
               Packet packet = this.getPacket();
               entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
               if (!this.entity.getDataWatcher().isEnabled()) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S1CPacketEntityMetadata(this.entity.getEntityId(), this.entity.getDataWatcher(), true));
               }

               NBTTagCompound nBTTagCompound = this.entity.getNBTTagCompound();
               if (nBTTagCompound != null) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S49PacketUpdateEntityNBT(this.entity.getEntityId(), nBTTagCompound));
               }

               if (this.entity instanceof EntityLivingBase) {
                  Collection collection = ((SetEntry)((EntityLivingBase)this.entity).getAdvancedMapEntry()).getCollection();
                  if (!collection.isEmpty()) {
                     entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S20PacketEntityProperties(this.entity.getEntityId(), collection));
                  }
               }

               this.value = this.entity.motionX;
               this.value2 = this.entity.motionY;
               this.value3 = this.entity.motionZ;
               if (this.enabled2 && !(packet instanceof S0FPacketSpawnMob)) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4
                     .applyPacket(new S12PacketEntityVelocity(this.entity.getEntityId(), this.entity.motionX, this.entity.motionY, this.entity.motionZ));
               }

               if (this.entity.ridingEntity != null) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S1BPacketEntityAttach(0, this.entity, this.entity.ridingEntity));
               }

               if (this.entity instanceof EntityMob && ((EntityMob)this.entity).getEntity6() != null) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S1BPacketEntityAttach(1, this.entity, ((EntityMob)this.entity).getEntity6()));
               }

               if (this.entity instanceof EntityLivingBase) {
                  for (int index = 0; index < 5; index++) {
                     ItemStack stack = ((EntityLivingBase)this.entity).resolveItemStack(index);
                     if (stack != null) {
                        entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S04PacketEntityEquipment(this.entity.getEntityId(), index, stack));
                     }
                  }
               }

               if (this.entity instanceof EntityPlayer) {
                  EntityPlayer entity3 = (EntityPlayer)this.entity;
                  if (entity3.isState5()) {
                     entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S0APacketUseBed(entity3, new BlockPos(this.entity)));
                  }
               }

               if (this.entity instanceof EntityLivingBase) {
                  for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)((EntityLivingBase)this.entity).getCollection()) {
                     entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S1DPacketEntityEffect(this.entity.getEntityId(), potionEffect));
                  }
               }
            }
         } else if (this.set.contains(entityPlayerAdapter)) {
            this.set.remove(entityPlayerAdapter);
            entityPlayerAdapter.applyEntity30(this.entity);
         }
      }
   }

   public void updateState() {
      Iterator iterator = this.set.iterator();

      while (iterator.hasNext()) {
         ((EntityPlayerAdapter)iterator.next()).applyEntity30(this.entity);
      }
   }
}
