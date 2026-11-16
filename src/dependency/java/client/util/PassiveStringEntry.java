package client.util;

import client.event.PostRenderConsumer;
import client.setting.AttackEffectsExplosionNormalType;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class PassiveStringEntry {
   public int count = 20;
   public String labelText = "Pig";
   public final List list = Lists.newArrayList();
   public PropertiesTypeDampenerSorter propertiesTypeDampenerSorter;
   public double value;
   public double value2;
   public int count2 = 200;
   public int count3 = 800;
   public int count4 = 4;
   public Entity entity;
   public int count5 = 6;
   public int count6 = 16;
   public int count7 = 4;

   private Entity resolveEntity(Entity entity2, boolean flag) {
      if (this.getPropertiesTypeDampenerSorter() != null) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         entity2.matchesNBTTagCompound(tagCompound);

         for (String text : (Iterable<String>)(Iterable<?>)PropertiesTypeDampenerSorter.resolveNBTTagCompound(this.getPropertiesTypeDampenerSorter()).keySet()) {
            LISTManager lISTManager = PropertiesTypeDampenerSorter.resolveNBTTagCompound(this.getPropertiesTypeDampenerSorter()).get(text);
            tagCompound.applyText(text, lISTManager.getLISTManager());
         }

         entity2.applyNBTTagCompound2(tagCompound);
         if (entity2.worldObj != null && flag) {
            entity2.worldObj.matchesEntity(entity2);
         }

         Entity entity3 = entity2;

         while (tagCompound.matchesText("Riding", 10)) {
            NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Riding");
            Entity entity4 = SkippingEntityWithIdManager.resolveEntityByText(nBTTagCompound.resolveText("id"), entity2.worldObj);
            if (entity4 != null) {
               NBTTagCompound tagCompound2 = new NBTTagCompound();
               entity4.matchesNBTTagCompound(tagCompound2);

               for (String text2 : (Iterable<String>)(Iterable<?>)nBTTagCompound.keySet()) {
                  LISTManager lISTManager2 = nBTTagCompound.get(text2);
                  tagCompound2.applyText(text2, lISTManager2.getLISTManager());
               }

               entity4.applyNBTTagCompound2(tagCompound2);
               entity4.applyDoubleByDouble(entity3.posX, entity3.posY, entity3.posZ, entity3.rotationYaw, entity3.rotationPitch);
               if (entity2.worldObj != null && flag) {
                  entity2.worldObj.matchesEntity(entity4);
               }

               entity3.setEntity8(entity4);
            }

            entity3 = entity4;
            tagCompound = nBTTagCompound;
         }
      } else if (entity2 instanceof EntityLivingBase && entity2.worldObj != null && flag) {
         if (entity2 instanceof EntityMob) {
            ((EntityMob)entity2).resolvePostRenderConsumer(entity2.worldObj.resolveGoldenOre(new BlockPos(entity2)), (PostRenderConsumer)null);
         }

         entity2.worldObj.matchesEntity(entity2);
      }

      return entity2;
   }

   public double getValue() {
      return this.value;
   }

   public abstract void applyIndex(int count8);

   private PropertiesTypeDampenerSorter getPropertiesTypeDampenerSorter() {
      return this.propertiesTypeDampenerSorter;
   }

   public abstract World getWorld();

   public double getValue2() {
      return this.value2;
   }

   private void updateState2() {
      if (this.count3 <= this.count2) {
         this.count = this.count2;
      } else {
         int count8 = this.count3 - this.count2;
         this.count = this.count2 + this.getWorld().random.nextInt(count8);
      }

      if (this.list.size() > 0) {
         this.setPropertiesTypeDampenerSorter((PropertiesTypeDampenerSorter)SmoothSystem.resolveDampenerSorter(this.getWorld().random, this.list));
      }

      this.applyIndex(1);
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      String text = this.getText();
      if (!ClientBrandHelper.isEmpty(text)) {
         tagCompound.put("EntityId", text);
         tagCompound.put("Delay", (short)this.count);
         tagCompound.put("MinSpawnDelay", (short)this.count2);
         tagCompound.put("MaxSpawnDelay", (short)this.count3);
         tagCompound.put("SpawnCount", (short)this.count4);
         tagCompound.put("MaxNearbyEntities", (short)this.count5);
         tagCompound.put("RequiredPlayerRange", (short)this.count6);
         tagCompound.put("SpawnRange", (short)this.count7);
         if (this.getPropertiesTypeDampenerSorter() != null) {
            tagCompound.applyText("SpawnData", PropertiesTypeDampenerSorter.resolveNBTTagCompound(this.getPropertiesTypeDampenerSorter()).getLISTManager());
         }

         if (this.getPropertiesTypeDampenerSorter() != null || this.list.size() > 0) {
            NBTTagList nBTTagList = new NBTTagList();
            if (this.list.size() > 0) {
               for (PropertiesTypeDampenerSorter propertiesTypeDampenerSorter2 : (Iterable<PropertiesTypeDampenerSorter>)(Iterable<?>)this.list) {
                  nBTTagList.applyLISTManager(propertiesTypeDampenerSorter2.getNBTTagCompound());
               }
            } else {
               nBTTagList.applyLISTManager(this.getPropertiesTypeDampenerSorter().getNBTTagCompound());
            }

            tagCompound.applyText("SpawnPotentials", nBTTagList);
         }
      }
   }

   public Entity resolveEntity(World world) {
      if (this.entity == null) {
         Entity entity2 = SkippingEntityWithIdManager.resolveEntityByText(this.getText(), world);
         if (entity2 != null) {
            Entity entity3 = this.resolveEntity(entity2, false);
            this.entity = entity3;
         }
      }

      return this.entity;
   }

   private boolean isActive() {
      BlockPos blockPos = this.getBlockPos();
      return this.getWorld().checkDouble((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, (double)this.count6);
   }

   public boolean checkIndex(int count8) {
      if (count8 == 1 && this.getWorld().state) {
         this.count = this.count2;
         return true;
      } else {
         return false;
      }
   }

   public void updateState() {
      if (this.isActive()) {
         BlockPos blockPos = this.getBlockPos();
         if (this.getWorld().state) {
            double offset = (double)((float)blockPos.getX() + this.getWorld().random.nextFloat());
            double offset2 = (double)((float)blockPos.getY() + this.getWorld().random.nextFloat());
            double offset3 = (double)((float)blockPos.getZ() + this.getWorld().random.nextFloat());
            this.getWorld().applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
            this.getWorld().applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
            if (this.count > 0) {
               this.count--;
            }

            this.value2 = this.value;
            this.value = (this.value + (double)(1000.0F / ((float)this.count + 200.0F))) % 360.0;
         } else {
            if (this.count == -1) {
               this.updateState2();
            }

            if (this.count > 0) {
               this.count--;
               return;
            }

            boolean flag = false;

            for (int index = 0; index < this.count4; index++) {
               Entity entity2 = SkippingEntityWithIdManager.resolveEntityByText(this.getText(), this.getWorld());
               if (entity2 == null) {
                  return;
               }

               if (this.getWorld()
                     .resolveList(
                        entity2.getClass(),
                        new AxisAlignedBB(
                              (double)blockPos.getX(),
                              (double)blockPos.getY(),
                              (double)blockPos.getZ(),
                              (double)(blockPos.getX() + 1),
                              (double)(blockPos.getY() + 1),
                              (double)(blockPos.getZ() + 1)
                           )
                           .offset((double)this.count7, (double)this.count7, (double)this.count7)
                     )
                     .size()
                  >= this.count5) {
                  this.updateState2();
                  return;
               }

               double offset4 = (double)blockPos.getX() + (this.getWorld().random.nextDouble() - this.getWorld().random.nextDouble()) * (double)this.count7 + 0.5;
               double offset5 = (double)(blockPos.getY() + this.getWorld().random.nextInt(3) - 1);
               double offset6 = (double)blockPos.getZ() + (this.getWorld().random.nextDouble() - this.getWorld().random.nextDouble()) * (double)this.count7 + 0.5;
               EntityMob entityMob = entity2 instanceof EntityMob ? (EntityMob)entity2 : null;
               entity2.applyDoubleByDouble(offset4, offset5, offset6, this.getWorld().random.nextFloat() * 360.0F, 0.0F);
               if (entityMob == null || entityMob.nextInt() && entityMob.isActive23()) {
                  this.resolveEntity(entity2, true);
                  this.getWorld().applyBlockPosByBlockPos(2004, blockPos, 0);
                  if (entityMob != null) {
                     entityMob.updateState44();
                  }

                  flag = true;
               }
            }

            if (flag) {
               this.updateState2();
            }
         }
      }
   }

   public abstract BlockPos getBlockPos();

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.labelText = tagCompound.resolveText("EntityId");
      this.count = tagCompound.processValue("Delay");
      this.list.clear();
      if (tagCompound.matchesText("SpawnPotentials", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("SpawnPotentials", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            this.list.add(new PropertiesTypeDampenerSorter(this, extendedLoggerEntry.getNBTTagCompound(index)));
         }
      }

      if (tagCompound.matchesText("SpawnData", 10)) {
         this.setPropertiesTypeDampenerSorter(new PropertiesTypeDampenerSorter(this, tagCompound.getNBTTagCompound("SpawnData"), this.labelText));
      } else {
         this.setPropertiesTypeDampenerSorter((PropertiesTypeDampenerSorter)null);
      }

      if (tagCompound.matchesText("MinSpawnDelay", 99)) {
         this.count2 = tagCompound.processValue("MinSpawnDelay");
         this.count3 = tagCompound.processValue("MaxSpawnDelay");
         this.count4 = tagCompound.processValue("SpawnCount");
      }

      if (tagCompound.matchesText("MaxNearbyEntities", 99)) {
         this.count5 = tagCompound.processValue("MaxNearbyEntities");
         this.count6 = tagCompound.processValue("RequiredPlayerRange");
      }

      if (tagCompound.matchesText("SpawnRange", 99)) {
         this.count7 = tagCompound.processValue("SpawnRange");
      }

      if (this.getWorld() != null) {
         this.entity = null;
      }
   }

   public void setPropertiesTypeDampenerSorter(PropertiesTypeDampenerSorter propertiesTypeDampenerSorter2) {
      this.propertiesTypeDampenerSorter = propertiesTypeDampenerSorter2;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   private String getText() {
      if (this.getPropertiesTypeDampenerSorter() == null) {
         if (this.labelText != null && this.labelText.equals("Minecart")) {
            this.labelText = "MinecartRideable";
         }

         return this.labelText;
      } else {
         return PropertiesTypeDampenerSorter.resolveText(this.getPropertiesTypeDampenerSorter());
      }
   }
}
