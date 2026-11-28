package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMob {
   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(
         attackingSpeedBoostModifierUUID, "Attacking speed boost", 0.15F, 0
      )
      .setSaved(false);
   private static final Set carriableBlocks = Sets.newIdentityHashSet();
   private boolean isAggressive;

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (short)0);
      this.dataWatcher.addObject(17, (byte)0);
      this.dataWatcher.addObject(18, (byte)0);
   }

   @Override
   public String getLivingSound() {
      return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
   }

   public IBlockState getHeldBlockState() {
      return Block.getStateById(this.dataWatcher.getWatchableObjectShort(16) & '\uffff');
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      IBlockState heldBlockState = this.getHeldBlockState();
      tag.setShort("carried", (short)Block.getIdFromBlock(heldBlockState.getBlock()));
      tag.setShort("carriedData", (short)heldBlockState.getBlock().getMetaFromState(heldBlockState));
   }

   private boolean shouldAttackPlayer(EntityPlayer player) {
      ItemStack stack = player.inventory.armorInventory[3];
      if (stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
         return false;
      } else {
         Vec3 vec = player.getLook(1.0F).normalize();
         Vec3 vec2 = new Vec3(
            this.posX - player.posX,
            this.getEntityBoundingBox().minY + (double)(this.height / 2.0F) - (player.posY + (double)player.getEyeHeight()),
            this.posZ - player.posZ
         );
         double d = vec2.lengthVector();
         vec2 = vec2.normalize();
         return vec.dotProduct(vec2) > 1.0 - 0.025 / d ? player.canEntityBeSeen(this) : false;
      }
   }

   public boolean teleportRandomly() {
      return this.teleportTo(this.posX + (this.rand.nextDouble() - 0.5) * 64.0, this.posY + (double)(this.rand.nextInt(64) - 32), this.posZ + (this.rand.nextDouble() - 0.5) * 64.0);
   }

   @Override
   public String getDeathSound() {
      return "mob.endermen.death";
   }

   @Override
   public Item getDropItem() {
      return Items.ender_pearl;
   }

   public static AttributeModifier accessAttackingSpeedBoostModifier() {
      return attackingSpeedBoostModifier;
   }

   public boolean teleportTo(double d, double d2, double d3) {
      double posX2 = this.posX;
      double posY2 = this.posY;
      double posZ2 = this.posZ;
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      boolean flag = false;
      BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
      if (this.entityWorld.isBlockLoaded(pos)) {
         boolean flag2 = false;

         while (!flag2 && pos.getY() > 0) {
            BlockPos pos2 = pos.down();
            if (this.entityWorld.getBlockState(pos2).getBlock().getMaterial().blocksMovement()) {
               flag2 = true;
            } else {
               this.posY--;
               pos = pos2;
            }
         }

         if (flag2) {
            super.setPositionAndUpdate(this.posX, this.posY, this.posZ);
            if (this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.entityWorld.isAnyLiquid(this.getEntityBoundingBox())) {
               flag = true;
            }
         }
      }

      if (!flag) {
         this.setPosition(posX2, posY2, posZ2);
         return false;
      } else {
         short s = 128;

         for (int i = 0; i < s; i++) {
            double d4 = (double)i / ((double)s - 1.0);
            float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f3 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double d5 = posX2 + (this.posX - posX2) * d4 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d6 = posY2 + (this.posY - posY2) * d4 + this.rand.nextDouble() * (double)this.height;
            double d7 = posZ2 + (this.posZ - posZ2) * d4 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.entityWorld.spawnParticle(EnumParticleTypes.PORTAL, d5, d6, d7, (double)f, (double)f2, (double)f3, new int[0]);
         }

         this.entityWorld.playSoundEffect(posX2, posY2, posZ2, "mob.endermen.portal", 1.0F, 1.0F);
         this.playSound("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   public static boolean invokeShouldAttackPlayer(EntityEnderman entityEnderman, EntityPlayer player) {
      return entityEnderman.shouldAttackPlayer(player);
   }

   public void setScreaming(boolean flag) {
      this.dataWatcher.updateObject(18, (byte)(flag ? 1 : 0));
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (damageSource.getEntity() == null || !(damageSource.getEntity() instanceof EntityEndermite)) {
            if (!this.entityWorld.isRemote) {
               this.setScreaming(true);
            }

            if (damageSource instanceof EntityDamageSource && damageSource.getEntity() instanceof EntityPlayer) {
               if (damageSource.getEntity() instanceof EntityPlayerMP && ((EntityPlayerMP)damageSource.getEntity()).theItemInWorldManager.isCreative()) {
                  this.setScreaming(false);
               } else {
                  this.isAggressive = true;
               }
            }

            if (damageSource instanceof EntityDamageSourceIndirect) {
               this.isAggressive = false;

               for (int i = 0; i < 64; i++) {
                  if (this.teleportRandomly()) {
                     return true;
                  }
               }

               return false;
            }
         }

         boolean flag = super.attackEntityFrom(damageSource, f);
         if (damageSource.isUnblockable() && this.rand.nextInt(10) != 0) {
            this.teleportRandomly();
         }

         return flag;
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      IBlockState state;
      if (tag.hasKey("carried", 8)) {
         state = Block.getBlockFromName(tag.getString("carried")).getStateFromMeta(tag.getShort("carriedData") & '\uffff');
      } else {
         state = Block.getBlockById(tag.getShort("carried")).getStateFromMeta(tag.getShort("carriedData") & '\uffff');
      }

      this.setHeldBlockState(state);
   }

   @Override
   public void onLivingUpdate() {
      if (this.entityWorld.isRemote) {
         for (int i = 0; i < 2; i++) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.PORTAL,
                  this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.rand.nextDouble() * (double)this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  new int[0]
               );
         }
      }

      this.jumping = false;
      super.onLivingUpdate();
   }

   public void setHeldBlockState(IBlockState state) {
      this.dataWatcher.updateObject(16, (short)(Block.getStateId(state) & 65535));
   }

   @Override
   public void updateAITasks() {
      if (this.isWet()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0) {
         this.setScreaming(false);
      }

      if (this.entityWorld.isDaytime()) {
         float brightness = this.getBrightness(1.0F);
         if (brightness > 0.5F
            && this.entityWorld.canSeeSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))
            && this.rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F) {
            this.setAttackTarget((EntityLivingBase)null);
            this.setScreaming(false);
            this.isAggressive = false;
            this.teleportRandomly();
         }
      }

      super.updateAITasks();
   }

   public static boolean assignIsAggressive(EntityEnderman entityEnderman, boolean flag) {
      return entityEnderman.isAggressive = flag;
   }

   public EntityEnderman(World world) {
      super(world);
      this.setSize(0.6F, 2.9F);
      this.stepHeight = 1.0F;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.tasks.addTask(10, new EntityEnderman.AIPlaceBlock(this));
      this.tasks.addTask(11, new EntityEnderman.AITakeBlock(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.targetTasks.addTask(2, new EntityEnderman.AIFindPlayer(this));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityEndermite.class, 10, true, false, new EntityEnderman.FilterPredicate(this)));
   }

   static {
      carriableBlocks.add(Blocks.grass);
      carriableBlocks.add(Blocks.dirt);
      carriableBlocks.add(Blocks.sand);
      carriableBlocks.add(Blocks.gravel);
      carriableBlocks.add(Blocks.yellow_flower);
      carriableBlocks.add(Blocks.red_flower);
      carriableBlocks.add(Blocks.brown_mushroom);
      carriableBlocks.add(Blocks.red_mushroom);
      carriableBlocks.add(Blocks.tnt);
      carriableBlocks.add(Blocks.cactus);
      carriableBlocks.add(Blocks.clay);
      carriableBlocks.add(Blocks.pumpkin);
      carriableBlocks.add(Blocks.melon_block);
      carriableBlocks.add(Blocks.mycelium);
   }

   public static Set accessCarriableBlocks() {
      return carriableBlocks;
   }

   @Override
   public float getEyeHeight() {
      return 2.55F;
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      Item dropItem = this.getDropItem();
      if (dropItem != null) {
         int n2 = this.rand.nextInt(2 + n);

         for (int i = 0; i < n2; i++) {
            this.dropItem(dropItem, 1);
         }
      }
   }

   public boolean teleportToEntity(Entity entity) {
      Vec3 vec = new Vec3(
         this.posX - entity.posX,
         this.getEntityBoundingBox().minY + (double)(this.height / 2.0F) - entity.posY + (double)entity.getEyeHeight(),
         this.posZ - entity.posZ
      );
      vec = vec.normalize();
      double d = 16.0;
      return this.teleportTo(this.posX + (this.rand.nextDouble() - 0.5) * 8.0 - vec.xCoord * d, this.posY + (double)(this.rand.nextInt(16) - 8) - vec.yCoord * d, this.posZ + (this.rand.nextDouble() - 0.5) * 8.0 - vec.zCoord * d);
   }

   @Override
   public String getHurtSound() {
      return "mob.endermen.hit";
   }

   public boolean isScreaming() {
      return this.dataWatcher.getWatchableObjectByte(18) > 0;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
   }


   public static class FilterPredicate implements Predicate {
      public final EntityEnderman enderman;

      public FilterPredicate(EntityEnderman entityEnderman) {
         this.enderman = entityEnderman;
      }

      public boolean apply(EntityEndermite entityEndermite) {
         return entityEndermite.isSpawnedByPlayer();
      }

      public boolean apply(Object obj) {
         return this.apply((EntityEndermite)obj);
      }
   }

   public static class AITakeBlock extends EntityAIBase {
      private EntityEnderman enderman;

      @Override
      public void updateTask() {
         Random rng = this.enderman.getRNG();
         World entityWorld = this.enderman.entityWorld;
         int n = MathHelper.floor_double(this.enderman.posX - 2.0 + rng.nextDouble() * 4.0);
         int n2 = MathHelper.floor_double(this.enderman.posY + rng.nextDouble() * 3.0);
         int n3 = MathHelper.floor_double(this.enderman.posZ - 2.0 + rng.nextDouble() * 4.0);
         BlockPos pos = new BlockPos(n, n2, n3);
         IBlockState blockState = entityWorld.getBlockState(pos);
         Block block = blockState.getBlock();
         if (EntityEnderman.accessCarriableBlocks().contains(block)) {
            this.enderman.setHeldBlockState(blockState);
            entityWorld.setBlockState(pos, Blocks.air.getDefaultState());
         }
      }

      public AITakeBlock(EntityEnderman entityEnderman) {
         this.enderman = entityEnderman;
      }

      @Override
      public boolean shouldExecute() {
         return !this.enderman.entityWorld.getGameRules().getBoolean("mobGriefing")
            ? false
            : (this.enderman.getHeldBlockState().getBlock().getMaterial() != Material.air ? false : this.enderman.getRNG().nextInt(20) == 0);
      }
   }

   public static class AIFindPlayer extends EntityAINearestAttackableTarget {
      private EntityPlayer player;
      private int aggroTime;
      private int teleportTime;
      private EntityEnderman enderman;

      @Override
      public void resetTask() {
         this.player = null;
         this.enderman.setScreaming(false);
         this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(EntityEnderman.accessAttackingSpeedBoostModifier());
         super.resetTask();
      }

      @Override
      public boolean continueExecuting() {
         if (this.player != null) {
            if (!EntityEnderman.invokeShouldAttackPlayer(this.enderman, this.player)) {
               return false;
            } else {
               EntityEnderman.assignIsAggressive(this.enderman, true);
               this.enderman.faceEntity(this.player, 10.0F, 10.0F);
               return true;
            }
         } else {
            return super.continueExecuting();
         }
      }

      @Override
      public boolean shouldExecute() {
         double targetDistance = this.getTargetDistance();
         List entitiesWithinAABB = this.taskOwner
            .entityWorld
            .getEntitiesWithinAABB(EntityPlayer.class, this.taskOwner.getEntityBoundingBox().expand(targetDistance, 4.0, targetDistance), this.targetEntitySelector);
         Collections.sort(entitiesWithinAABB, this.theNearestAttackableTargetSorter);
         if (entitiesWithinAABB.isEmpty()) {
            return false;
         } else {
            this.player = (EntityPlayer)entitiesWithinAABB.get(0);
            return true;
         }
      }

      public AIFindPlayer(EntityEnderman entityEnderman) {
         super(entityEnderman, EntityPlayer.class, true);
         this.enderman = entityEnderman;
      }

      @Override
      public void updateTask() {
         if (this.player != null) {
            if (--this.aggroTime <= 0) {
               this.targetEntity = this.player;
               this.player = null;
               super.startExecuting();
               this.enderman.playSound("mob.endermen.stare", 1.0F, 1.0F);
               this.enderman.setScreaming(true);
               this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(EntityEnderman.accessAttackingSpeedBoostModifier());
            }
         } else {
            if (this.targetEntity != null) {
               if (this.targetEntity instanceof EntityPlayer && EntityEnderman.invokeShouldAttackPlayer(this.enderman, (EntityPlayer)this.targetEntity)) {
                  if (this.targetEntity.getDistanceSqToEntity(this.enderman) < 16.0) {
                     this.enderman.teleportRandomly();
                  }

                  this.teleportTime = 0;
               } else if (this.targetEntity.getDistanceSqToEntity(this.enderman) > 256.0 && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.targetEntity)
                  )
                {
                  this.teleportTime = 0;
               }
            }

            super.updateTask();
         }
      }

      @Override
      public void startExecuting() {
         this.aggroTime = 5;
         this.teleportTime = 0;
      }
   }

   public static class AIPlaceBlock extends EntityAIBase {
      private EntityEnderman enderman;

      @Override
      public boolean shouldExecute() {
         return !this.enderman.entityWorld.getGameRules().getBoolean("mobGriefing")
            ? false
            : (this.enderman.getHeldBlockState().getBlock().getMaterial() == Material.air ? false : this.enderman.getRNG().nextInt(2000) == 0);
      }

      public AIPlaceBlock(EntityEnderman entityEnderman) {
         this.enderman = entityEnderman;
      }

      private boolean canPlaceBlock(World world, BlockPos pos, Block block, Block block2, Block block3) {
         return !block.canPlaceBlockAt(world, pos)
            ? false
            : (block2.getMaterial() != Material.air ? false : (block3.getMaterial() == Material.air ? false : block3.isFullCube()));
      }

      @Override
      public void updateTask() {
         Random rng = this.enderman.getRNG();
         World entityWorld = this.enderman.entityWorld;
         int n = MathHelper.floor_double(this.enderman.posX - 1.0 + rng.nextDouble() * 2.0);
         int n2 = MathHelper.floor_double(this.enderman.posY + rng.nextDouble() * 2.0);
         int n3 = MathHelper.floor_double(this.enderman.posZ - 1.0 + rng.nextDouble() * 2.0);
         BlockPos pos = new BlockPos(n, n2, n3);
         Block block = entityWorld.getBlockState(pos).getBlock();
         Block block2 = entityWorld.getBlockState(pos.down()).getBlock();
         if (this.canPlaceBlock(entityWorld, pos, this.enderman.getHeldBlockState().getBlock(), block, block2)) {
            entityWorld.setBlockState(pos, this.enderman.getHeldBlockState(), 3);
            this.enderman.setHeldBlockState(Blocks.air.getDefaultState());
         }
      }
   }
}
