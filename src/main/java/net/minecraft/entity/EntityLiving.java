package net.minecraft.entity;

import java.util.UUID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.optifine.config.Config;

public abstract class EntityLiving extends EntityLivingBase {
   public final EntityAITasks tasks;
   public final EntityAITasks targetTasks;
   public int livingSoundTime;
   public int experienceValue;
   public EntityMoveHelper moveHelper;
   public EntityJumpHelper jumpHelper;
   public PathNavigate navigator;
   public float[] equipmentDropChances = new float[5];
   private final EntityLookHelper lookHelper;
   private final EntityBodyHelper bodyHelper;
   private EntityLivingBase attackTarget;
   private final EntitySenses senses;
   private final ItemStack[] equipment = new ItemStack[5];
   private boolean canPickUpLoot;
   private boolean persistenceRequired;
   private boolean isLeashed;
   private Entity leashedToEntity;
   private NBTTagCompound leashNBTTag;
   private UUID teamUuid = null;
   private String teamUuidString = null;

   @Override
   public int getMaxFallHeight() {
      if (this.getAttackTarget() == null) {
         return 3;
      } else {
         int n = (int)(this.getCurrentHealth() - this.getMaxHealth() * 0.33F);
         n -= (3 - this.entityWorld.getDifficulty().getDifficultyId()) * 4;
         if (n < 0) {
            n = 0;
         }

         return n + 3;
      }
   }

   public boolean getLeashed() {
      return this.isLeashed;
   }

   @Override
   public final void updateEntityActionState() {
      this.age++;
      this.entityWorld.theProfiler.startSection("checkDespawn");
      this.despawnEntity();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("sensing");
      this.senses.clearSensingCache();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("targetSelector");
      this.targetTasks.onUpdateTasks();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("goalSelector");
      this.tasks.onUpdateTasks();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("navigation");
      this.navigator.onUpdateNavigation();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("mob tick");
      this.updateAITasks();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("controls");
      this.entityWorld.theProfiler.startSection("move");
      this.moveHelper.onUpdateMoveHelper();
      this.entityWorld.theProfiler.endStartSection("look");
      this.lookHelper.onUpdateLook();
      this.entityWorld.theProfiler.endStartSection("jump");
      this.jumpHelper.doJump();
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.endSection();
   }

   @Override
   public float updateDistance(float f, float f2) {
      this.bodyHelper.updateRenderAngles();
      return f2;
   }

   public EntityMoveHelper getMoveHelper() {
      return this.moveHelper;
   }

   private void recreateLeash() {
      if (this.isLeashed && this.leashNBTTag != null) {
         if (this.leashNBTTag.hasKey("UUIDMost", 4) && this.leashNBTTag.hasKey("UUIDLeast", 4)) {
            UUID uuid = new UUID(this.leashNBTTag.getLong("UUIDMost"), this.leashNBTTag.getLong("UUIDLeast"));

            for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.entityWorld.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(10.0, 10.0, 10.0)))) {
               if (entity.getUniqueID().equals(uuid)) {
                  this.leashedToEntity = entity;
                  break;
               }
            }
         } else if (this.leashNBTTag.hasKey("X", 99) && this.leashNBTTag.hasKey("Y", 99) && this.leashNBTTag.hasKey("Z", 99)) {
            BlockPos pos = new BlockPos(this.leashNBTTag.getInteger("X"), this.leashNBTTag.getInteger("Y"), this.leashNBTTag.getInteger("Z"));
            EntityLeashKnot knotForPosition = EntityLeashKnot.getKnotForPosition(this.entityWorld, pos);
            if (knotForPosition == null) {
               knotForPosition = EntityLeashKnot.createKnot(this.entityWorld, pos);
            }

            this.leashedToEntity = knotForPosition;
         } else {
            this.clearLeashed(false, true);
         }
      }

      this.leashNBTTag = null;
   }

   public void eatGrassBonus() {
   }

   public static int getArmorPosition(ItemStack stack) {
      if (stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin) || stack.getItem() == Items.skull) {
         return 4;
      } else {
         if (stack.getItem() instanceof ItemArmor) {
            switch (((ItemArmor)stack.getItem()).armorType) {
               case 0:
                  return 4;
               case 1:
                  return 3;
               case 2:
                  return 2;
               case 3:
                  return 1;
            }
         }

         return 0;
      }
   }

   public boolean canDespawn() {
      return true;
   }

   public float getRenderSizeModifier() {
      return 1.0F;
   }

   @Override
   public Team getTeam() {
      UUID uniqueID = this.getUniqueID();
      if (this.teamUuid != uniqueID) {
         this.teamUuid = uniqueID;
         this.teamUuidString = uniqueID.toString();
      }

      return this.entityWorld.getScoreboard().getPlayersTeam(this.teamUuidString);
   }

   public Entity getLeashedToEntity() {
      return this.leashedToEntity;
   }

   public void setAttackTarget(EntityLivingBase entity) {
      this.attackTarget = entity;
   }

   public EntityLiving(World world) {
      super(world);
      this.tasks = new EntityAITasks(world != null && world.theProfiler != null ? world.theProfiler : null);
      this.targetTasks = new EntityAITasks(world != null && world.theProfiler != null ? world.theProfiler : null);
      this.lookHelper = new EntityLookHelper(this);
      this.moveHelper = new EntityMoveHelper(this);
      this.jumpHelper = new EntityJumpHelper(this);
      this.bodyHelper = new EntityBodyHelper(this);
      this.navigator = this.getNewNavigator(world);
      this.senses = new EntitySenses(this);

      for (int i = 0; i < this.equipmentDropChances.length; i++) {
         this.equipmentDropChances[i] = 0.085F;
      }

      this.getUniqueID().getLeastSignificantBits();
   }

   public boolean isNoDespawnRequired() {
      return this.persistenceRequired;
   }

   @Override
   public ItemStack getEquipmentInSlot(int n) {
      return this.equipment[n];
   }

   public int getMaxSpawnedInChunk() {
      return 4;
   }

   public PathNavigate getNewNavigator(World world) {
      return new PathNavigateGround(this, world);
   }

   @Override
   public void onUpdate() {
      if (Config.isSmoothWorld() && this.canSkipUpdate()) {
         this.onUpdateMinimal();
      } else {
         super.onUpdate();
         if (!this.entityWorld.isRemote) {
            this.updateLeashedState();
         }
      }
   }

   public void playLivingSound() {
      String livingSound = this.getLivingSound();
      if (livingSound != null) {
         this.playSound(livingSound, this.getSoundVolume(), this.getSoundPitch());
      }
   }

   public void setNoAI(boolean flag) {
      this.dataWatcher.updateObject(15, (byte)(flag ? 1 : 0));
   }

   public void setMoveForward(float f) {
      this.moveForward = f;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0);
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      Item dropItem = this.getDropItem();
      if (dropItem != null) {
         int n2 = this.rand.nextInt(3);
         if (n > 0) {
            n2 += this.rand.nextInt(n + 1);
         }

         for (int i = 0; i < n2; i++) {
            this.dropItem(dropItem, 1);
         }
      }
   }

   @Override
   public void setAIMoveSpeed(float f) {
      super.setAIMoveSpeed(f);
      this.setMoveForward(f);
   }

   public boolean isAIDisabled() {
      return this.dataWatcher.getWatchableObjectByte(15) != 0;
   }

   public void updateEquipmentIfNeeded(EntityItem entityItem) {
      ItemStack entityItem2 = entityItem.getEntityItem();
      int armorPosition = getArmorPosition(entityItem2);
      if (armorPosition > -1) {
         boolean flag = true;
         ItemStack equipmentInSlot = this.getEquipmentInSlot(armorPosition);
         if (equipmentInSlot != null) {
            if (armorPosition == 0) {
               if (entityItem2.getItem() instanceof ItemSword && !(equipmentInSlot.getItem() instanceof ItemSword)) {
                  flag = true;
               } else if (entityItem2.getItem() instanceof ItemSword && equipmentInSlot.getItem() instanceof ItemSword) {
                  ItemSword item = (ItemSword)entityItem2.getItem();
                  ItemSword item2 = (ItemSword)equipmentInSlot.getItem();
                  if (item.getDamageVsEntity() != item2.getDamageVsEntity()) {
                     flag = item.getDamageVsEntity() > item2.getDamageVsEntity();
                  } else {
                     flag = entityItem2.getMetadata() > equipmentInSlot.getMetadata() || entityItem2.hasTagCompound() && !equipmentInSlot.hasTagCompound();
                  }
               } else if (entityItem2.getItem() instanceof ItemBow && equipmentInSlot.getItem() instanceof ItemBow) {
                  flag = entityItem2.hasTagCompound() && !equipmentInSlot.hasTagCompound();
               } else {
                  flag = false;
               }
            } else if (entityItem2.getItem() instanceof ItemArmor && !(equipmentInSlot.getItem() instanceof ItemArmor)) {
               flag = true;
            } else if (entityItem2.getItem() instanceof ItemArmor && equipmentInSlot.getItem() instanceof ItemArmor) {
               ItemArmor item3 = (ItemArmor)entityItem2.getItem();
               ItemArmor item4 = (ItemArmor)equipmentInSlot.getItem();
               if (item3.damageReduceAmount != item4.damageReduceAmount) {
                  flag = item3.damageReduceAmount > item4.damageReduceAmount;
               } else {
                  flag = entityItem2.getMetadata() > equipmentInSlot.getMetadata() || entityItem2.hasTagCompound() && !equipmentInSlot.hasTagCompound();
               }
            } else {
               flag = false;
            }
         }

         if (flag && this.canEquipItem(entityItem2)) {
            if (equipmentInSlot != null && this.rand.nextFloat() - 0.1F < this.equipmentDropChances[armorPosition]) {
               this.entityDropItem(equipmentInSlot, 0.0F);
            }

            if (entityItem2.getItem() == Items.diamond && entityItem.getThrower() != null) {
               EntityPlayer playerEntityByName = this.entityWorld.getPlayerEntityByName(entityItem.getThrower());
               if (playerEntityByName != null) {
                  playerEntityByName.triggerAchievement(AchievementList.diamondsToYou);
               }
            }

            this.setCurrentItemOrArmor(armorPosition, entityItem2);
            this.equipmentDropChances[armorPosition] = 2.0F;
            this.persistenceRequired = true;
            this.onItemPickup(entityItem, 1);
            entityItem.setDead();
         }
      }
   }

   public void clearLeashed(boolean flag, boolean flag2) {
      if (this.isLeashed) {
         this.isLeashed = false;
         this.leashedToEntity = null;
         if (!this.entityWorld.isRemote && flag2) {
            this.dropItem(Items.lead, 1);
         }

         if (!this.entityWorld.isRemote && flag && this.entityWorld instanceof WorldServer) {
            ((WorldServer)this.entityWorld).getEntityTracker().sendToAllTrackingEntity(this, new S1BPacketEntityAttach(1, this, null));
         }
      }
   }

   private boolean canSkipUpdate() {
      if (this.isChild()) {
         return false;
      } else if (this.hurtTime > 0) {
         return false;
      } else if (this.ticksExisted < 20) {
         return false;
      } else {
         World entityWorld2 = this.getEntityWorld();
         if (entityWorld2 == null) {
            return false;
         } else if (entityWorld2.playerEntities.size() != 1) {
            return false;
         } else {
            Entity entity = (Entity)entityWorld2.playerEntities.get(0);
            double d = Math.max(Math.abs(this.posX - entity.posX) - 16.0, 0.0);
            double d2 = Math.max(Math.abs(this.posZ - entity.posZ) - 16.0, 0.0);
            return !this.isInRangeToRenderDist(d * d + d2 * d2);
         }
      }
   }

   public void setEquipmentBasedOnDifficulty(DifficultyInstance difficultyInstance) {
      if (this.rand.nextFloat() < 0.15F * difficultyInstance.getClampedAdditionalDifficulty()) {
         int n = this.rand.nextInt(2);
         float f = this.entityWorld.getDifficulty() == EnumDifficulty.HARD ? 0.1F : 0.25F;
         if (this.rand.nextFloat() < 0.095F) {
            n++;
         }

         if (this.rand.nextFloat() < 0.095F) {
            n++;
         }

         if (this.rand.nextFloat() < 0.095F) {
            n++;
         }

         for (int i = 3; i >= 0; i--) {
            ItemStack currentArmor = this.getCurrentArmor(i);
            if (i < 3 && this.rand.nextFloat() < f) {
               break;
            }

            if (currentArmor == null) {
               Item armorItemForSlot = getArmorItemForSlot(i + 1, n);
               if (armorItemForSlot != null) {
                  this.setCurrentItemOrArmor(i + 1, new ItemStack(armorItemForSlot));
               }
            }
         }
      }
   }

   @Override
   public ItemStack getHeldItem() {
      return this.equipment[0];
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("CanPickUpLoot", this.canPickUpLoot());
      tag.setBoolean("PersistenceRequired", this.persistenceRequired);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.equipment.length; i++) {
         NBTTagCompound tag2 = new NBTTagCompound();
         if (this.equipment[i] != null) {
            this.equipment[i].writeToNBT(tag2);
         }

         tagList.appendTag(tag2);
      }

      tag.setTag("Equipment", tagList);
      NBTTagList tagList2 = new NBTTagList();

      for (int j = 0; j < this.equipmentDropChances.length; j++) {
         tagList2.appendTag(new NBTTagFloat(this.equipmentDropChances[j]));
      }

      tag.setTag("DropChances", tagList2);
      tag.setBoolean("Leashed", this.isLeashed);
      if (this.leashedToEntity != null) {
         NBTTagCompound tag3 = new NBTTagCompound();
         if (this.leashedToEntity instanceof EntityLivingBase) {
            tag3.setLong("UUIDMost", this.leashedToEntity.getUniqueID().getMostSignificantBits());
            tag3.setLong("UUIDLeast", this.leashedToEntity.getUniqueID().getLeastSignificantBits());
         } else if (this.leashedToEntity instanceof EntityHanging) {
            BlockPos hangingPosition = ((EntityHanging)this.leashedToEntity).getHangingPosition();
            tag3.setInteger("X", hangingPosition.getX());
            tag3.setInteger("Y", hangingPosition.getY());
            tag3.setInteger("Z", hangingPosition.getZ());
         }

         tag.setTag("Leash", tag3);
      }

      if (this.isAIDisabled()) {
         tag.setBoolean("NoAI", this.isAIDisabled());
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 20) {
         this.spawnExplosionParticle();
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public void setEnchantmentBasedOnDifficulty(DifficultyInstance difficultyInstance) {
      float f = difficultyInstance.getClampedAdditionalDifficulty();
      if (this.getHeldItem() != null && this.rand.nextFloat() < 0.25F * f) {
         EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItem(), (int)(5.0F + f * (float)this.rand.nextInt(18)));
      }

      for (int i = 0; i < 4; i++) {
         ItemStack currentArmor = this.getCurrentArmor(i);
         if (currentArmor != null && this.rand.nextFloat() < 0.5F * f) {
            EnchantmentHelper.addRandomEnchantment(this.rand, currentArmor, (int)(5.0F + f * (float)this.rand.nextInt(18)));
         }
      }
   }

   public EntityJumpHelper getJumpHelper() {
      return this.jumpHelper;
   }

   private void onUpdateMinimal() {
      this.age++;
      if (this instanceof EntityMob) {
         if (this.getBrightness(1.0F) > 0.5F) {
            this.age += 2;
         }
      }

      this.despawnEntity();
   }

   public EntitySenses getEntitySenses() {
      return this.senses;
   }

   @Override
   public boolean isServerWorld() {
      return super.isServerWorld() && !this.isAIDisabled();
   }

   @Override
   public ItemStack getCurrentArmor(int n) {
      return this.equipment[n + 1];
   }

   @Override
   public final boolean interactFirst(EntityPlayer player) {
      if (this.getLeashed() && this.getLeashedToEntity() == player) {
         this.clearLeashed(true, !player.capabilities.isCreativeMode);
         return true;
      } else {
         ItemStack currentItem = player.inventory.getCurrentItem();
         if (currentItem != null && currentItem.getItem() == Items.lead && this.allowLeashing()) {
            if (!(this instanceof EntityTameable) || !((EntityTameable)this).isTamed()) {
               this.setLeashedToEntity(player, true);
               currentItem.stackSize--;
               return true;
            }

            if (((EntityTameable)this).isOwner(player)) {
               this.setLeashedToEntity(player, true);
               currentItem.stackSize--;
               return true;
            }
         }

         return this.interact(player) || super.interactFirst(player);
      }
   }

   @Override
   public ItemStack[] getInventory() {
      return this.equipment;
   }

   public void enablePersistence() {
      this.persistenceRequired = true;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.entityWorld.theProfiler.startSection("looting");
      if (!this.entityWorld.isRemote && this.canPickUpLoot() && !this.dead && this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
         for (EntityItem entityItem : (Iterable<EntityItem>)(this.entityWorld.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(1.0, 0.0, 1.0)))) {
            if (!entityItem.isDead && entityItem.getEntityItem() != null && !entityItem.cannotPickup()) {
               this.updateEquipmentIfNeeded(entityItem);
            }
         }
      }

      this.entityWorld.theProfiler.endSection();
   }

   public int getVerticalFaceSpeed() {
      return 40;
   }

   public boolean interact(EntityPlayer player) {
      return false;
   }

   @Override
   public void setCurrentItemOrArmor(int n, ItemStack stack) {
      this.equipment[n] = stack;
   }

   public EntityLookHelper getLookHelper() {
      return this.lookHelper;
   }

   public void updateLeashedState() {
      if (this.leashNBTTag != null) {
         this.recreateLeash();
      }

      if (this.isLeashed) {
         if (!this.isEntityAlive()) {
            this.clearLeashed(true, true);
         }

         if (this.leashedToEntity == null || this.leashedToEntity.isDead) {
            this.clearLeashed(true, true);
         }
      }
   }

   public boolean getCanSpawnHere() {
      return true;
   }

   public PathNavigate getNavigator() {
      return this.navigator;
   }

   public boolean canEquipItem(ItemStack stack) {
      return true;
   }

   private float updateRotation(float f, float f2, float f3) {
      float f4 = MathHelper.wrapAngleTo180_float(f2 - f);
      if (f4 > f3) {
         f4 = f3;
      }

      if (f4 < -f3) {
         f4 = -f3;
      }

      return f + f4;
   }

   public void updateAITasks() {
   }

   public void faceEntity(Entity entity, float f, float f2) {
      double d = entity.posX - this.posX;
      double d2 = entity.posZ - this.posZ;
      double d3;
      if (entity instanceof EntityLivingBase) {
         EntityLivingBase entity2 = (EntityLivingBase)entity;
         d3 = entity2.posY + (double)entity2.getEyeHeight() - (this.posY + (double)this.getEyeHeight());
      } else {
         d3 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (this.posY + (double)this.getEyeHeight());
      }

      double d4 = (double)MathHelper.sqrt_double(d * d + d2 * d2);
      float f3 = (float)(MathHelper.atan2(d2, d) * 180.0 / Math.PI) - 90.0F;
      float f4 = (float)(-(MathHelper.atan2(d3, d4) * 180.0 / Math.PI));
      this.rotationPitch = this.updateRotation(this.rotationPitch, f4, f2);
      this.rotationYaw = this.updateRotation(this.rotationYaw, f3, f);
   }

   public static Item getArmorItemForSlot(int n, int n2) {
      switch (n) {
         case 4:
            if (n2 == 0) {
               return Items.leather_helmet;
            } else if (n2 == 1) {
               return Items.golden_helmet;
            } else if (n2 == 2) {
               return Items.chainmail_helmet;
            } else if (n2 == 3) {
               return Items.iron_helmet;
            } else if (n2 == 4) {
               return Items.diamond_helmet;
            }
         case 3:
            if (n2 == 0) {
               return Items.leather_chestplate;
            } else if (n2 == 1) {
               return Items.golden_chestplate;
            } else if (n2 == 2) {
               return Items.chainmail_chestplate;
            } else if (n2 == 3) {
               return Items.iron_chestplate;
            } else if (n2 == 4) {
               return Items.diamond_chestplate;
            }
         case 2:
            if (n2 == 0) {
               return Items.leather_leggings;
            } else if (n2 == 1) {
               return Items.golden_leggings;
            } else if (n2 == 2) {
               return Items.chainmail_leggings;
            } else if (n2 == 3) {
               return Items.iron_leggings;
            } else if (n2 == 4) {
               return Items.diamond_leggings;
            }
         case 1:
            if (n2 == 0) {
               return Items.leather_boots;
            } else if (n2 == 1) {
               return Items.golden_boots;
            } else if (n2 == 2) {
               return Items.chainmail_boots;
            } else if (n2 == 3) {
               return Items.iron_boots;
            } else if (n2 == 4) {
               return Items.diamond_boots;
            }
         default:
            return null;
      }
   }

   public EntityLivingBase getAttackTarget() {
      return this.attackTarget;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("CanPickUpLoot", 1)) {
         this.setCanPickUpLoot(tag.getBoolean("CanPickUpLoot"));
      }

      this.persistenceRequired = tag.getBoolean("PersistenceRequired");
      if (tag.hasKey("Equipment", 9)) {
         NBTTagList tagList = tag.getTagList("Equipment", 10);

         for (int i = 0; i < this.equipment.length; i++) {
            this.equipment[i] = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i));
         }
      }

      if (tag.hasKey("DropChances", 9)) {
         NBTTagList tagList2 = tag.getTagList("DropChances", 5);

         for (int j = 0; j < tagList2.tagCount(); j++) {
            this.equipmentDropChances[j] = tagList2.getFloatAt(j);
         }
      }

      this.isLeashed = tag.getBoolean("Leashed");
      if (this.isLeashed && tag.hasKey("Leash", 10)) {
         this.leashNBTTag = tag.getCompoundTag("Leash");
      }

      this.setNoAI(tag.getBoolean("NoAI"));
   }

   public void setLeashedToEntity(Entity entity, boolean flag) {
      this.isLeashed = true;
      this.leashedToEntity = entity;
      if (!this.entityWorld.isRemote && flag && this.entityWorld instanceof WorldServer) {
         ((WorldServer)this.entityWorld).getEntityTracker().sendToAllTrackingEntity(this, new S1BPacketEntityAttach(1, this, this.leashedToEntity));
      }
   }

   @Override
   public boolean replaceItemInInventory(int n, ItemStack stack) {
      int n2;
      if (n == 99) {
         n2 = 0;
      } else {
         n2 = n - 100 + 1;
         if (n2 < 0 || n2 >= this.equipment.length) {
            return false;
         }
      }

      if (stack != null && getArmorPosition(stack) != n2 && (n2 != 4 || !(stack.getItem() instanceof ItemBlock))) {
         return false;
      } else {
         this.setCurrentItemOrArmor(n2, stack);
         return true;
      }
   }

   public Item getDropItem() {
      return null;
   }

   public void setEquipmentDropChance(int n, float f) {
      this.equipmentDropChances[n] = f;
   }

   public int getTalkInterval() {
      return 80;
   }

   @Override
   public int getExperiencePoints(EntityPlayer player) {
      if (this.experienceValue > 0) {
         int experienceValue2 = this.experienceValue;
         ItemStack[] inventory = this.getInventory();

         for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && this.equipmentDropChances[i] <= 1.0F) {
               experienceValue2 += 1 + this.rand.nextInt(3);
            }
         }

         return experienceValue2;
      } else {
         return this.experienceValue;
      }
   }

   public void spawnExplosionParticle() {
      if (this.entityWorld.isRemote) {
         for (int i = 0; i < 20; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = this.rand.nextGaussian() * 0.02;
            double d4 = 10.0;
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.EXPLOSION_NORMAL,
                  this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d * d4,
                  this.posY + (double)(this.rand.nextFloat() * this.height) - d2 * d4,
                  this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d3 * d4,
                  d,
                  d2,
                  d3,
                  new int[0]
               );
         }
      } else {
         this.entityWorld.setEntityState(this, (byte)20);
      }
   }

   public void setCanPickUpLoot(boolean flag) {
      this.canPickUpLoot = flag;
   }

   public boolean canAttackClass(Class clazz) {
      return clazz != EntityGhast.class;
   }

   public boolean allowLeashing() {
      return !this.getLeashed() && !(this instanceof IMob);
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(15, (byte)0);
   }

   public void despawnEntity() {
      if (this.persistenceRequired) {
         this.age = 0;
      } else {
         EntityPlayer closestPlayerToEntity = this.entityWorld.getClosestPlayerToEntity(this, -1.0);
         if (closestPlayerToEntity != null) {
            double d = closestPlayerToEntity.posX - this.posX;
            double d2 = closestPlayerToEntity.posY - this.posY;
            double d3 = closestPlayerToEntity.posZ - this.posZ;
            double d4 = d * d + d2 * d2 + d3 * d3;
            if (this.canDespawn() && d4 > 16384.0) {
               this.setDead();
            }

            if (this.age > 600 && this.rand.nextInt(800) == 0 && d4 > 1024.0 && this.canDespawn()) {
               this.setDead();
            } else if (d4 < 1024.0) {
               this.age = 0;
            }
         }
      }
   }

   public boolean canBeSteered() {
      return false;
   }

   @Override
   public void dropEquipment(boolean flag, int n) {
      for (int i = 0; i < this.getInventory().length; i++) {
         ItemStack equipmentInSlot = this.getEquipmentInSlot(i);
         boolean flag2 = this.equipmentDropChances[i] > 1.0F;
         if (equipmentInSlot != null && (flag || flag2) && this.rand.nextFloat() - (float)n * 0.01F < this.equipmentDropChances[i]) {
            if (!flag2 && equipmentInSlot.isItemStackDamageable()) {
               int n2 = Math.max(equipmentInSlot.getMaxDamage() - 25, 1);
               int n3 = equipmentInSlot.getMaxDamage() - this.rand.nextInt(this.rand.nextInt(n2) + 1);
               if (n3 > n2) {
                  n3 = n2;
               }

               if (n3 < 1) {
                  n3 = 1;
               }

               equipmentInSlot.setItemDamage(n3);
            }

            this.entityDropItem(equipmentInSlot, 0.0F);
         }
      }
   }

   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      this.getEntityAttribute(SharedMonsterAttributes.followRange)
         .applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05, 1));
      return entityLivingData;
   }

   public boolean isNotColliding() {
      return this.entityWorld.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()
         && !this.entityWorld.isAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      this.entityWorld.theProfiler.startSection("mobBaseTick");
      if (this.isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++) {
         this.livingSoundTime = -this.getTalkInterval();
         this.playLivingSound();
      }

      this.entityWorld.theProfiler.endSection();
   }

   public boolean canPickUpLoot() {
      return this.canPickUpLoot;
   }

   public String getLivingSound() {
      return null;
   }


   public enum SpawnPlacementType {
      ON_GROUND,
      IN_AIR,
      IN_WATER;
   }
}
