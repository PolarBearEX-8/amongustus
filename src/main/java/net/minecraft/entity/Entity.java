package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.block.state.pattern.BlockPattern;
import com.google.gson.GsonBuilder;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.dietrichevents2.StateTypes;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.augustus.modules.render.esp.EntityEspBox;
import net.augustus.events.listeners.WebSlowDownListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.StepListener;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.events.EventWebSlowDown;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.modules.modes.killaura.unoise3.UNoise3RotationEngine;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.Augustus;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.events.EventStep;
import net.augustus.utils.rotation.Rotation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.EntityFirework;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity extends EntityFirework implements ICommandSender {
   private static final AxisAlignedBB ZERO_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   private static int nextEntityID;
   private int entityId;
   public double renderDistanceWeight;
   public final EntityEspBox entityEspBox = EntityEspBox.fromEntity(this);
   public boolean preventEntitySpawning;
   public Entity riddenByEntity;
   public Entity ridingEntity;
   public boolean forceSpawn;
   public World entityWorld;
   public double prevPosX;
   public double prevPosY;
   public double prevPosZ;
   public BoundedHistoryList positionHistory = new BoundedHistoryList(23);
   public double posX;
   public double posY;
   public double posZ;
   public double motionX;
   public double motionY;
   public double motionZ;
   public float rotationYaw;
   public float rotationPitch;
   public float prevRotationYaw;
   public float prevRotationPitch;
   private AxisAlignedBB boundingBox;
   public boolean onGround;
   public boolean isCollidedHorizontally;
   public boolean isCollidedVertically;
   public boolean isCollided;
   public boolean velocityChanged;
   public boolean isInWeb;
   private boolean isOutsideBorder;
   public boolean isDead;
   public float width;
   public float height;
   public float prevDistanceWalkedModified;
   public float distanceWalkedModified;
   public float distanceWalkedOnStepModified;
   public float fallDistance;
   public float clientFallDistance;
   private int nextStepDistance;
   public double lastTickPosX;
   public double lastTickPosY;
   public double lastTickPosZ;
   public float stepHeight;
   public boolean noClip;
   public float entityCollisionReduction;
   public Random rand;
   public int ticksExisted;
   public int fireResistance;
   private int fire;
   public boolean inWater;
   public int hurtResistantTime;
   public boolean firstUpdate;
   public boolean isImmuneToFire;
   public DataWatcher dataWatcher;
   private double entityRiderPitchDelta;
   private double entityRiderYawDelta;
   public boolean addedToChunk;
   public int chunkCoordX;
   public int chunkCoordY;
   public int chunkCoordZ;
   public int serverPosX;
   public int serverPosY;
   public int serverPosZ;
   public Vec3 serverPosVec;
   public boolean ignoreFrustumCheck;
   public boolean isAirBorne;
   public int timeUntilPortal;
   public boolean inPortal;
   public int portalCounter;
   public int dimension;
   public BlockPos lastPortalPos;
   public Vec3 lastPortalVec;
   public EnumFacing teleportDirection;
   private boolean invulnerable;
   public UUID entityUniqueID;
   private final CommandResultStats cmdResultStats;
   public AxisAlignedBB collidedYBoundingBox;
   private boolean renderNameTag;
   public long lastDamageAttackTime;
   public long lastAttackTime;

   public void applyEntityCollision(Entity entity) {
      if (entity.riddenByEntity != this && entity.ridingEntity != this && !entity.noClip && !this.noClip) {
         double d = entity.posX - this.posX;
         double d2 = entity.posZ - this.posZ;
         double d3 = MathHelper.abs_max(d, d2);
         if (d3 >= 0.009999999776482582) {
            d3 = (double)MathHelper.sqrt_double(d3);
            d /= d3;
            d2 /= d3;
            double d4 = 1.0 / d3;
            if (d4 > 1.0) {
               d4 = 1.0;
            }

            d *= d4;
            d2 *= d4;
            d *= 0.05000000074505806;
            d2 *= 0.05000000074505806;
            d *= (double)(1.0F - this.entityCollisionReduction);
            d2 *= (double)(1.0F - this.entityCollisionReduction);
            if (this.riddenByEntity == null) {
               this.addVelocity(-d, 0.0, -d2);
            }

            if (entity.riddenByEntity == null) {
               entity.addVelocity(d, 0.0, d2);
            }
         }
      }
   }

   public void setLocationAndAngles(double d, double d2, double d3, float f, float f2) {
      this.positionHistory.add(new Vec3(d, d2, d3));
      this.lastTickPosX = this.prevPosX = this.posX = d;
      this.lastTickPosY = this.prevPosY = this.posY = d2;
      this.lastTickPosZ = this.prevPosZ = this.posZ = d3;
      this.rotationYaw = f;
      this.rotationPitch = f2;
      this.setPosition(this.posX, this.posY, this.posZ);
   }

   public void performHurtAnimation() {
   }

   public void setCurrentItemOrArmor(int n, ItemStack stack) {
   }

   public NBTTagList newFloatNBTList(float[] floats) {
      NBTTagList tagList = new NBTTagList();

      for (float f : floats) {
         tagList.appendTag(new NBTTagFloat(f));
      }

      return tagList;
   }

   public boolean isEntityEqual(Entity entity) {
      return this == entity;
   }

   public boolean isInRangeToRender3d(double d, double d2, double d3) {
      double d4 = this.posX - d;
      double d5 = this.posY - d2;
      double d6 = this.posZ - d3;
      return this.isInRangeToRenderDist(d4 * d4 + d5 * d5 + d6 * d6);
   }

   public void setPositionAndUpdate(double d, double d2, double d3) {
      this.setLocationAndAngles(d, d2, d3, this.rotationYaw, this.rotationPitch);
   }

   public void onUpdate() {
      this.onEntityUpdate();
   }

   public void setRotation(float f, float f2) {
      this.rotationYaw = f % 360.0F;
      this.rotationPitch = f2 % 360.0F;
      if (this == Minecraft.getMinecraft().thePlayer) {
         UNoise3RotationEngine.syncAllEngines((double)f, (double)f2);
      }
   }

   public float getCollisionBorderSize() {
      return !ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)
            && (!Augustus.getInstance().getModuleManager().hitBox.isToggled() || !Augustus.getInstance().getModuleManager().hitBox.n18Hitbox.getValue())
         ? 0.0F
         : 0.1F;
   }

   public void setPortal(BlockPos pos) {
      if (this.timeUntilPortal > 0) {
         this.timeUntilPortal = this.getPortalCooldown();
      } else {
         if (!this.entityWorld.isRemote && !pos.equals(this.lastPortalPos)) {
            this.lastPortalPos = pos;
            BlockPattern.PatternHelper patternHelper = Blocks.portal.createPatternHelper(this.entityWorld, pos);
            double d = patternHelper.getFinger().getAxis() == EnumFacing.Axis.X ? (double)patternHelper.getPos().getZ() : (double)patternHelper.getPos().getX();
            double d2 = patternHelper.getFinger().getAxis() == EnumFacing.Axis.X ? this.posZ : this.posX;
            d2 = Math.abs(
               MathHelper.pct(
                  d2 - (double)(patternHelper.getFinger().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0), d, d - (double)patternHelper.getWidth()
               )
            );
            double d3 = MathHelper.pct(this.posY - 1.0, (double)patternHelper.getPos().getY(), (double)(patternHelper.getPos().getY() - patternHelper.getHeight()));
            this.lastPortalVec = new Vec3(d2, d3, 0.0);
            this.teleportDirection = patternHelper.getFinger();
         }

         this.inPortal = true;
      }
   }

   public void setSprinting(boolean flag) {
      this.setFlag(3, flag);
   }

   public void setAngles(float f, float f2) {
      if (this instanceof EntityPlayerSP) {
         Rotation rotation = ClientManagers.rotationController.applyMouseLookDelta(f, f2);
         YawPitchHelper yawPitchHelper = Augustus.getInstance().getYawPitchHelper();
         float realPitch = yawPitchHelper.realPitch;
         float realYaw = yawPitchHelper.realYaw;
         yawPitchHelper.realYaw = (float)((double)yawPitchHelper.realYaw + (double)f * 0.15);
         yawPitchHelper.realPitch = (float)((double)yawPitchHelper.realPitch - (double)f2 * 0.15);
         yawPitchHelper.realPitch = MathHelper.clamp_float(yawPitchHelper.realPitch, -90.0F, 90.0F);
         yawPitchHelper.realLastPitch = yawPitchHelper.realLastPitch + (yawPitchHelper.realPitch - realPitch);
         yawPitchHelper.realLastYaw = yawPitchHelper.realLastYaw + (yawPitchHelper.realYaw - realYaw);
         float yaw = rotation.yaw;
         float f3 = yawPitchHelper.alignYawToReference(yawPitchHelper.realLastYaw, yaw);
         this.rotationYaw = rotation.yaw;
         this.rotationPitch = rotation.pitch;
         this.prevRotationPitch = yawPitchHelper.realLastPitch;
         this.prevRotationYaw = f3;
      } else {
         float rotationPitch2 = this.rotationPitch;
         float rotationYaw2 = this.rotationYaw;
         this.rotationYaw = (float)((double)this.rotationYaw + (double)f * 0.15);
         this.rotationPitch = (float)((double)this.rotationPitch - (double)f2 * 0.15);
         this.rotationPitch = MathHelper.clamp_float(this.rotationPitch, -90.0F, 90.0F);
         this.prevRotationPitch = this.prevRotationPitch + (this.rotationPitch - rotationPitch2);
         this.prevRotationYaw = this.prevRotationYaw + (this.rotationYaw - rotationYaw2);
      }
   }

   public boolean interactFirst(EntityPlayer player) {
      return false;
   }

   public float getBrightness(float f) {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posY + (double)this.getEyeHeight());
      int n3 = MathHelper.floor_double(this.posZ);
      return this.entityWorld.isBlockLoaded(n, n2, n3) ? this.entityWorld.getLightBrightness(n, n2, n3) : 0.0F;
   }

   public void setSneaking(boolean flag) {
      this.setFlag(1, flag);
   }

   public boolean shouldSetPosAfterLoading() {
      return true;
   }

   public MovingObjectPosition rayTraceCustom(double d, float f, float f2, float f3, double d2, double d3, double d4) {
      d2 = f == 1.0F ? d2 : this.prevPosX + (this.posX - this.prevPosX) * (double)f;
      d3 = f == 1.0F ? d3 + (double)this.getEyeHeight() : this.prevPosY + (this.posY - this.prevPosY) * (double)f + (double)this.getEyeHeight();
      d4 = f == 1.0F ? d4 : this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f;
      if (f != 1.0F && f != 2.0F) {
         f3 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f;
         f2 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f;
      }

      float f4 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f5 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f6 = -MathHelper.cos(-f3 * 0.017453292F);
      float f7 = MathHelper.sin(-f3 * 0.017453292F);
      double d5 = (double)(f5 * f6);
      double d6 = (double)f7;
      double d7 = (double)(f4 * f6);
      double d8 = d2 + d5 * d;
      double d9 = d3 + d6 * d;
      double d10 = d4 + d7 * d;
      return this.entityWorld.rayTraceBlocks(d2, d3, d4, d8, d9, d10, false, false, true);
   }

   public int getMaxInPortalTime() {
      return 0;
   }

   public boolean equals(Object obj) {
      return obj instanceof Entity ? ((Entity)obj).entityId == this.entityId : false;
   }

   public void dealFireDamage(int n) {
      if (!this.isImmuneToFire) {
         this.attackEntityFrom(DamageSource.inFire, (float)n);
      }
   }

   public float getDistanceToEntity(Entity entity) {
      float f = (float)(this.posX - entity.posX);
      float f2 = (float)(this.posY - entity.posY);
      float f3 = (float)(this.posZ - entity.posZ);
      return MathHelper.sqrt_float(f * f + f2 * f2 + f3 * f3);
   }

   public boolean doesEntityNotTriggerPressurePlate() {
      return false;
   }

   public boolean writeMountToNBT(NBTTagCompound tag) {
      String entityString = this.getEntityString();
      if (!this.isDead && entityString != null) {
         tag.setString("id", entityString);
         this.writeToNBT(tag);
         return true;
      } else {
         return false;
      }
   }

   public boolean replaceItemInInventory(int n, ItemStack stack) {
      return false;
   }

   private void resetPositionToBB() {
      this.posX = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0;
      this.posY = this.getEntityBoundingBox().minY;
      this.posZ = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0;
      this.positionHistory.add(new Vec3(this.posX, this.posY, this.posZ));
   }

   public void applyEnchantments(EntityLivingBase entity, Entity entity2) {
      if (entity2 instanceof EntityLivingBase) {
         EnchantmentHelper.applyThornEnchantments((EntityLivingBase)entity2, entity);
      }

      EnchantmentHelper.applyArthropodEnchantments(entity, entity2);
   }

   public String getSwimSound() {
      return "game.neutral.swim";
   }

   public MovingObjectPosition rayTrace(double d, float f) {
      Vec3 positionEyes = this.getPositionEyes(f);
      Vec3 look = this.getLook(f);
      Vec3 vec = positionEyes.addVector(look.xCoord * d, look.yCoord * d, look.zCoord * d);
      return this.entityWorld.rayTraceBlocks(positionEyes.xCoord, positionEyes.yCoord, positionEyes.zCoord, vec.xCoord, vec.yCoord, vec.zCoord, false, false, true);
   }

   public final String getEntityString() {
      return EntityList.getEntityString(this);
   }

   public void moveFlying(float f, float f2, float f3) {
      float f4 = f * f + f2 * f2;
      if (f4 >= 1.0E-4F) {
         f4 = MathHelper.sqrt_float(f4);
         if (f4 < 1.0F) {
            f4 = 1.0F;
         }

         f4 = f3 / f4;
         f *= f4;
         f2 *= f4;
         float f5 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
         float f6 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
         this.motionX += (double)(f * f6 - f2 * f5);
         this.motionZ += (double)(f2 * f6 + f * f5);
      }
   }

   public Vec3 getPositionVector() {
      return new Vec3(this.posX, this.posY, this.posZ);
   }

   public Vec3 getLook(float f) {
      if (f == 1.0F) {
         return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
      } else {
         return this.getVectorForRotation(this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f, this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f);
      }
   }

   public double getDistanceSq(double d, double d2, double d3) {
      double d4 = this.posX - d;
      double d5 = this.posY - d2;
      double d6 = this.posZ - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public int getMaxFallHeight() {
      return 3;
   }

   public EntityItem entityDropItem(ItemStack stack, float f) {
      if (stack.stackSize != 0 && stack.getItem() != null) {
         EntityItem entityItem = new EntityItem(this.entityWorld, this.posX, this.posY + (double)f, this.posZ, stack);
         entityItem.setDefaultPickupDelay();
         this.entityWorld.spawnEntityInWorld(entityItem);
         return entityItem;
      } else {
         return null;
      }
   }

   public void setCustomNameTag(String str) {
      this.dataWatcher.updateObject(2, str);
   }

   public EnumFacing getHorizontalFacing(float f) {
      return EnumFacing.getHorizontal(MathHelper.floor_double((double)(f * 4.0F / 360.0F) + 0.5) & 3);
   }

   public void resetHeight() {
      float f = MathHelper.sqrt_double(
            this.motionX * this.motionX * 0.20000000298023224 + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224
         )
         * 0.2F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      this.playSound(this.getSplashSound(), f, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
      float f2 = (float)MathHelper.floor_double(this.getEntityBoundingBox().minY);

      for (int i = 0; (float)i < 1.0F + this.width * 20.0F; i++) {
         float f3 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
         float f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.WATER_BUBBLE,
               this.posX + (double)f3,
               (double)(f2 + 1.0F),
               this.posZ + (double)f4,
               this.motionX,
               this.motionY - (double)(this.rand.nextFloat() * 0.2F),
               this.motionZ,
               new int[0]
            );
      }

      for (int j = 0; (float)j < 1.0F + this.width * 20.0F; j++) {
         float f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
         float f6 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.WATER_SPLASH,
               this.posX + (double)f5,
               (double)(f2 + 1.0F),
               this.posZ + (double)f6,
               this.motionX,
               this.motionY,
               this.motionZ,
               new int[0]
            );
      }
   }

   public boolean isBurning() {
      boolean flag = this.entityWorld != null && this.entityWorld.isRemote;
      return !this.isImmuneToFire && (this.fire > 0 || flag && this.getFlag(0));
   }

   public double getDistanceSq(BlockPos pos) {
      return pos.distanceSq(this.posX, this.posY, this.posZ);
   }

   public void onDataWatcherUpdate(int n) {
   }

   public Entity getCommandSenderEntity() {
      return this;
   }

   public void syncPrevPos() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
   }

   public void addVelocity(double d, double d2, double d3) {
      this.motionX += d;
      this.motionY += d2;
      this.motionZ += d3;
      this.isAirBorne = true;
   }

   public boolean getAlwaysRenderNameTag() {
      return this.dataWatcher.getWatchableObjectByte(3) == 1;
   }

   public EntityItem dropItemWithOffset(Item item, int n, float f) {
      return this.entityDropItem(new ItemStack(item, n, 0), f);
   }

   public void setFire(int n) {
      int n2 = n * 20;
      n2 = EnchantmentProtection.getFireTimeForEntity(this, n2);
      if (this.fire < n2) {
         this.fire = n2;
      }
   }

   public void setDead() {
      this.isDead = true;
   }

   public boolean hitByEntity(Entity entity) {
      return false;
   }

   public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
      this.attackEntityFrom(DamageSource.lightningBolt, 5.0F);
      this.fire++;
      if (this.fire == 0) {
         this.setFire(8);
      }
   }

   public void setEntityId(int n) {
      this.entityId = n;
   }

   public String getName() {
      if (this.hasCustomName()) {
         return this.getCustomNameTag();
      } else {
         String entityString = EntityList.getEntityString(this);
         if (entityString == null) {
            entityString = "generic";
         }

         return StatCollector.translateToLocal("entity." + entityString + ".name");
      }
   }

   public Vec3 getLastPortalVec() {
      return this.lastPortalVec;
   }

   public boolean interactAt(EntityPlayer player, Vec3 vec) {
      return false;
   }

   public boolean isRenderNameTag() {
      return this.renderNameTag;
   }

   public void setBeenAttacked() {
      this.velocityChanged = true;
   }

   public void moveToBlockPosAndAngles(BlockPos pos, float f, float f2) {
      this.setLocationAndAngles((double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, f, f2);
   }

   public void setRenderNameTag(boolean flag) {
      this.renderNameTag = flag;
   }

   public void onKillEntity(EntityLivingBase entity) {
   }

   public boolean isEntityInvulnerable(DamageSource damageSource) {
      return this.invulnerable && damageSource != DamageSource.outOfWorld && !damageSource.isCreativePlayer();
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return this.getAlwaysRenderNameTag();
   }

   public void setInvisible(boolean flag) {
      this.setFlag(5, flag);
   }

   public Vec3 getPositionEyes(float f) {
      if (f == 1.0F) {
         return new Vec3(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
      } else {
         double d = this.prevPosX + (this.posX - this.prevPosX) * (double)f;
         double d2 = this.prevPosY + (this.posY - this.prevPosY) * (double)f + (double)this.getEyeHeight();
         double d3 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f;
         return new Vec3(d, d2, d3);
      }
   }

   public boolean hasCustomName() {
      return !this.dataWatcher.getWatchableObjectString(2).isEmpty();
   }

   public void extinguish() {
      this.fire = 0;
   }

   public void updateRiderPosition() {
      if (this.riddenByEntity != null) {
         this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
      }
   }

   public EnumFacing getTeleportDirection() {
      return this.teleportDirection;
   }

   public void addChatMessage(IChatComponent component) {
   }

   public boolean isOffsetPositionInLiquid(double d, double d2, double d3) {
      return this.isLiquidPresentInAABB(this.getEntityBoundingBox().offset(d, d2, d3));
   }

   public void setAir(int n) {
      this.dataWatcher.updateObject(1, (short)n);
   }

   public float getDistanceToEntityBox(Entity entity) {
      Vec3 positionEyes = this.getPositionEyes(1.0F);
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d2 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d3 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      Vec3 vec = new Vec3(d, d2, d3);
      return (float)new Vec3(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ).distanceTo(vec);
   }

   public Vec3 getLookCustom2(float f, float f2, float f3) {
      if (f != 1.0F && f != 2.0F) {
         return this.getVectorForRotation(this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f, this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f);
      } else {
         return this.getVectorForRotation(f3, f2);
      }
   }

   public double getDistance(double d, double d2, double d3) {
      double d4 = this.posX - d;
      double d5 = this.posY - d2;
      double d6 = this.posZ - d3;
      return (double)MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);
   }

   public void setSize(float f, float f2) {
      if (f != this.width || f2 != this.height) {
         float width2 = this.width;
         this.width = f;
         this.height = f2;
         this.setEntityBoundingBox(
            new AxisAlignedBB(
               this.getEntityBoundingBox().minX,
               this.getEntityBoundingBox().minY,
               this.getEntityBoundingBox().minZ,
               this.getEntityBoundingBox().minX + (double)this.width,
               this.getEntityBoundingBox().minY + (double)this.height,
               this.getEntityBoundingBox().minZ + (double)this.width
            )
         );
         if (this.width > width2 && !this.firstUpdate && !this.entityWorld.isRemote) {
            this.moveEntity((double)(width2 - this.width), 0.0, (double)(width2 - this.width));
         }
      }
   }

   public boolean isInWater() {
      return this.inWater;
   }

   public boolean canRenderOnFire() {
      return this.isBurning();
   }

   public Vec3 getLookVec() {
      return null;
   }

   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public boolean handleWaterMovement() {
      if (this.entityWorld
         .handleMaterialAcceleration(
            this.getEntityBoundingBox().minX + 0.001,
            this.getEntityBoundingBox().minY + 0.4000000059604645 + 0.001,
            this.getEntityBoundingBox().minZ + 0.001,
            this.getEntityBoundingBox().maxX - 0.001,
            this.getEntityBoundingBox().maxY - 0.001 - 0.4000000059604645,
            this.getEntityBoundingBox().maxZ - 0.001,
            Material.water,
            this
         )) {
         if (!this.inWater && !this.firstUpdate) {
            this.resetHeight();
         }

         this.fallDistance = 0.0F;
         this.clientFallDistance = 0.0F;
         this.inWater = true;
         this.fire = 0;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   public void preparePlayerToSpawn() {
      if (this.entityWorld != null) {
         while (true) {
            if (this.posY > 0.0 && this.posY < 256.0) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (!this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()) {
                  this.posY++;
                  continue;
               }
            }

            this.motionX = this.motionY = this.motionZ = 0.0;
            this.rotationPitch = 0.0F;
            break;
         }
      }
   }

   public boolean pushOutOfBlocks(double d, double d2, double d3) {
      BlockPos pos = new BlockPos(d, d2, d3);
      double d4 = d - (double)pos.getX();
      double d5 = d2 - (double)pos.getY();
      double d6 = d3 - (double)pos.getZ();
      if (this.entityWorld.getCollisionBoxes(this.getEntityBoundingBox()).isEmpty() && !this.entityWorld.isBlockFullCube(pos)) {
         return false;
      } else {
         byte b = 3;
         double d7 = 9999.0;
         if (!this.entityWorld.isBlockFullCube(pos.west()) && d4 < d7) {
            d7 = d4;
            b = 0;
         }

         if (!this.entityWorld.isBlockFullCube(pos.east()) && 1.0 - d4 < d7) {
            d7 = 1.0 - d4;
            b = 1;
         }

         if (!this.entityWorld.isBlockFullCube(pos.up()) && 1.0 - d5 < d7) {
            d7 = 1.0 - d5;
            b = 3;
         }

         if (!this.entityWorld.isBlockFullCube(pos.north()) && d6 < d7) {
            d7 = d6;
            b = 4;
         }

         if (!this.entityWorld.isBlockFullCube(pos.south()) && 1.0 - d6 < d7) {
            d7 = 1.0 - d6;
            b = 5;
         }

         float f = this.rand.nextFloat() * 0.2F + 0.1F;
         if (b == 0) {
            this.motionX = (double)(-f);
         }

         if (b == 1) {
            this.motionX = (double)f;
         }

         if (b == 3) {
            this.motionY = (double)f;
         }

         if (b == 4) {
            this.motionZ = (double)(-f);
         }

         if (b == 5) {
            this.motionZ = (double)f;
         }

         return true;
      }
   }

   public void setPosition(double d, double d2, double d3) {
      this.positionHistory.add(new Vec3(d, d2, d3));
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      float f = this.width / 2.0F;
      float height2 = this.height;
      this.setEntityBoundingBox(
         new AxisAlignedBB(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height2, d3 + (double)f)
      );
   }

   public final Vec3 getVectorForRotation(float f, float f2) {
      float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.cos(-f * 0.017453292F);
      float f6 = MathHelper.sin(-f * 0.017453292F);
      return new Vec3((double)(f4 * f5), (double)f6, (double)(f3 * f5));
   }

   public void copyCommandStats(Entity entity) {
      this.cmdResultStats.addAllStats(entity.getCommandStats());
   }

   public float getRotationYawHead() {
      return 0.0F;
   }

   public void addEntityCrashInfo(CrashReportCategory crashReportCategory) {
      crashReportCategory.addCrashSectionCallable("Entity Type", new Entity.ValueCallable(this));
      crashReportCategory.addCrashSection("Entity ID", this.entityId);
      crashReportCategory.addCrashSectionCallable("Entity Name", new Entity.ValueCallable2(this));
      crashReportCategory.addCrashSection("Entity's Exact location", String.format("%.2f, %.2f, %.2f", this.posX, this.posY, this.posZ));
      crashReportCategory.addCrashSection(
         "Entity's Block location",
         CrashReportCategory.getCoordinateInfo(
            (double)MathHelper.floor_double(this.posX), (double)MathHelper.floor_double(this.posY), (double)MathHelper.floor_double(this.posZ)
         )
      );
      crashReportCategory.addCrashSection("Entity's Momentum", String.format("%.2f, %.2f, %.2f", this.motionX, this.motionY, this.motionZ));
      crashReportCategory.addCrashSectionCallable("Entity's Rider", new Entity.ValueCallable3(this));
      crashReportCategory.addCrashSectionCallable("Entity's Vehicle", new Entity.ValueCallable4(this));
   }

   public abstract void entityInit();

   public void setRotationYawHead(float f) {
   }

   public AxisAlignedBB getCollisionBox(Entity entity) {
      return null;
   }

   public boolean canCommandSenderUseCommand(int n, String str) {
      return true;
   }

   public void setAlwaysRenderNameTag(boolean flag) {
      this.dataWatcher.updateObject(3, (byte)(flag ? 1 : 0));
   }

   public boolean isInLava() {
      return this.entityWorld
         .isMaterialInBB(
            this.getEntityBoundingBox().minX + 0.10000000149011612,
            this.getEntityBoundingBox().minY + 0.4000000059604645,
            this.getEntityBoundingBox().minZ + 0.10000000149011612,
            this.getEntityBoundingBox().maxX - 0.10000000149011612,
            this.getEntityBoundingBox().maxY - 0.4000000059604645,
            this.getEntityBoundingBox().maxZ - 0.10000000149011612,
            Material.lava
         );
   }

   public NBTTagList newDoubleNBTList(double[] doubles) {
      NBTTagList tagList = new NBTTagList();

      for (double d : doubles) {
         tagList.appendTag(new NBTTagDouble(d));
      }

      return tagList;
   }

   public boolean isImmuneToExplosions() {
      return false;
   }

   public CommandResultStats getCommandStats() {
      return this.cmdResultStats;
   }

   public AxisAlignedBB getEntityBoundingBox() {
      return this.boundingBox;
   }

   public boolean isInvisibleToPlayer(EntityPlayer player) {
      return !player.isSpectator() && this.isInvisible();
   }

   public BlockPos getPosition() {
      return new BlockPos(this.posX, this.posY + 0.5, this.posZ);
   }

   public boolean isPushedByWater() {
      return true;
   }

   public void doBlockCollisions() {
      int n = MathHelper.floor_double(this.getEntityBoundingBox().minX + 0.001);
      int n2 = MathHelper.floor_double(this.getEntityBoundingBox().minY + 0.001);
      int n3 = MathHelper.floor_double(this.getEntityBoundingBox().minZ + 0.001);
      int n4 = MathHelper.floor_double(this.getEntityBoundingBox().maxX - 0.001);
      int n5 = MathHelper.floor_double(this.getEntityBoundingBox().maxY - 0.001);
      int n6 = MathHelper.floor_double(this.getEntityBoundingBox().maxZ - 0.001);
      if (this.entityWorld.isAreaLoaded(n, n2, n3, n4, n5, n6, true)) {
         for (int i = n; i <= n4; i++) {
            for (int j = n2; j <= n5; j++) {
               for (int k = n3; k <= n6; k++) {
                  BlockPos pos = new BlockPos(i, j, k);
                  IBlockState blockState2 = this.entityWorld.getBlockState2(i, j, k);

                  try {
                     blockState2.getBlock().onEntityCollidedWithBlock(this.entityWorld, pos, blockState2, this);
                  } catch (Throwable e) {
                     CrashReport crashReport = CrashReport.makeCrashReport(e, "Colliding entity with block");
                     CrashReportCategory.addBlockInfo(crashReport.makeCategory("Block being collided with"), pos, blockState2);
                     throw new ReportedException(crashReport);
                  }
               }
            }
         }
      }
   }

   public boolean writeToNBTOptional(NBTTagCompound tag) {
      String entityString = this.getEntityString();
      if (!this.isDead && entityString != null && this.riddenByEntity == null) {
         tag.setString("id", entityString);
         this.writeToNBT(tag);
         return true;
      } else {
         return false;
      }
   }

   public void setIsInWeb(boolean flag) {
      this.isInWeb = flag;
   }

   public boolean canBePushed() {
      return false;
   }

   public void setEating(boolean flag) {
      this.setFlag(4, flag);
   }

   public boolean canAttackWithItem() {
      return true;
   }

   public String toString() {
      return String.format(
         "%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]",
         this.getClass().getSimpleName(),
         this.getName(),
         this.entityId,
         this.entityWorld == null ? "~NULL~" : this.entityWorld.getWorldInfo().getWorldName(),
         this.posX,
         this.posY,
         this.posZ
      );
   }

   public double getMountedYOffset() {
      return (double)this.height * 0.75;
   }

   public boolean isSneaking() {
      return this.getFlag(1);
   }

   public void kill() {
      this.setDead();
   }

   public MovingObjectPosition rayTraceCustom(double d, float f, float f2, float f3) {
      Vec3 positionEyes = this.getPositionEyes(f);
      Vec3 lookCustom2 = this.getLookCustom2(f, f2, f3);
      Vec3 vec = positionEyes.addVector(lookCustom2.xCoord * d, lookCustom2.yCoord * d, lookCustom2.zCoord * d);
      return this.entityWorld.rayTraceBlocks(positionEyes.xCoord, positionEyes.yCoord, positionEyes.zCoord, vec.xCoord, vec.yCoord, vec.zCoord, false, false, true);
   }

   public void mountEntity(Entity entity) {
      this.entityRiderPitchDelta = 0.0;
      this.entityRiderYawDelta = 0.0;
      if (entity == null) {
         if (this.ridingEntity != null) {
            this.setLocationAndAngles(
               this.ridingEntity.posX,
               this.ridingEntity.getEntityBoundingBox().minY + (double)this.ridingEntity.height,
               this.ridingEntity.posZ,
               this.rotationYaw,
               this.rotationPitch
            );
            this.ridingEntity.riddenByEntity = null;
         }

         this.ridingEntity = null;
      } else {
         if (this.ridingEntity != null) {
            this.ridingEntity.riddenByEntity = null;
         }

         if (entity != null) {
            for (Entity i = entity.ridingEntity; i != null; i = i.ridingEntity) {
               if (i == this) {
                  return;
               }
            }
         }

         this.ridingEntity = entity;
         entity.riddenByEntity = this;
      }
   }

   public boolean isSpectatedByPlayer(EntityPlayerMP entityPlayerMP) {
      return true;
   }

   public boolean isInWeb() {
      return this.isInWeb;
   }

   public void handleStatusUpdate(byte b) {
   }

   public Vec3 getInterpolatedPos(float f) {
      if (f == 1.0F) {
         return new Vec3(this.posX, this.posY, this.posZ);
      } else {
         double d = this.prevPosX + (this.posX - this.prevPosX) * (double)f;
         double d2 = this.prevPosY + (this.posY - this.prevPosY) * (double)f;
         double d3 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f;
         return new Vec3(d, d2, d3);
      }
   }

   public void playStepSound(BlockPos pos, Block block) {
      Block.SoundType stepSound = block.stepSound;
      if (this.entityWorld.getBlockState(pos.up()).getBlock() == Blocks.snow_layer) {
         stepSound = Blocks.snow_layer.stepSound;
         this.playSound(stepSound.getStepSound(), stepSound.getVolume() * 0.15F, stepSound.getFrequency());
      } else if (!block.getMaterial().isLiquid()) {
         this.playSound(stepSound.getStepSound(), stepSound.getVolume() * 0.15F, stepSound.getFrequency());
      }
   }

   public boolean getFlag(int n) {
      return (this.dataWatcher.getWatchableObjectByte(0) & 1 << n) != 0;
   }

   public int hashCode() {
      return this.entityId;
   }

   public String getDisplayNameString() {
      return this.getName() + EnumChatFormatting.RESET;
   }

   public void travelToDimension(int n) {
      if (!this.entityWorld.isRemote && !this.isDead) {
         this.entityWorld.theProfiler.startSection("changeDimension");
         MinecraftServer server = MinecraftServer.getServer();
         int dimension2 = this.dimension;
         WorldServer world = server.worldServerForDimension(dimension2);
         WorldServer world2 = server.worldServerForDimension(n);
         this.dimension = n;
         if (dimension2 == 1 && n == 1) {
            world2 = server.worldServerForDimension(0);
            this.dimension = 0;
         }

         this.entityWorld.removeEntity(this);
         this.isDead = false;
         this.entityWorld.theProfiler.startSection("reposition");
         server.getConfigurationManager().transferEntityToWorld(this, dimension2, world, world2);
         this.entityWorld.theProfiler.endStartSection("reloading");
         Entity entity = EntityList.createEntityByName(EntityList.getEntityString(this), world2);
         if (entity != null) {
            entity.copyDataFromOld(this);
            if (dimension2 == 1 && n == 1) {
               entity.moveToBlockPosAndAngles(this.entityWorld.getTopSolidOrLiquidBlock(world2.getSpawnPoint()), entity.rotationYaw, entity.rotationPitch);
            }

            world2.spawnEntityInWorld(entity);
         }

         this.isDead = true;
         this.entityWorld.theProfiler.endSection();
         world.resetUpdateEntityTick();
         world2.resetUpdateEntityTick();
         this.entityWorld.theProfiler.endSection();
      }
   }

   public void setSilent(boolean flag) {
      this.dataWatcher.updateObject(4, (byte)(flag ? 1 : 0));
   }

   public void onKillCommand() {
      this.setDead();
   }

   public boolean isInsideOfMaterial(Material material) {
      double d = this.posY + (double)this.getEyeHeight();
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(d);
      int n3 = MathHelper.floor_double(this.posZ);
      IBlockState blockState2 = this.entityWorld.getBlockState2(n, n2, n3);
      if (blockState2.getBlock().getMaterial() == material) {
         boolean flag = d < (double)((float)(n2 + 1) - (BlockLiquid.getLiquidHeightPercent(blockState2.getBlock().getMetaFromState(blockState2)) - 0.11111111F));
         return !flag && this instanceof EntityPlayer ? false : flag;
      } else {
         return false;
      }
   }

   public NBTTagCompound getNBTTagCompound() {
      return null;
   }

   public float getDistanceSqToEntityFloat(Entity entity) {
      float f = (float)(this.posX - entity.posX);
      float f2 = (float)(this.posY - entity.posY);
      float f3 = (float)(this.posZ - entity.posZ);
      return f * f + f2 * f2 + f3 * f3;
   }

   public boolean isInvisible() {
      return this.getFlag(5);
   }

   public HoverEvent getHoverEvent() {
      NBTTagCompound tag = new NBTTagCompound();
      String entityString = EntityList.getEntityString(this);
      tag.setString("id", this.getUniqueID().toString());
      if (entityString != null) {
         tag.setString("type", entityString);
      }

      tag.setString("name", this.getName());
      return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new ChatComponentText(tag.toString()));
   }

   public void fall(float f, float f2) {
      if (this.riddenByEntity != null) {
         this.riddenByEntity.fall(f, f2);
      }
   }

   public void setOnFireFromLava() {
      if (!this.isImmuneToFire) {
         this.attackEntityFrom(DamageSource.lava, 4.0F);
         this.setFire(15);
      }
   }

   public Entity(World world) {
      this.serverPosVec = new Vec3((double)this.serverPosX, (double)this.serverPosY, (double)this.serverPosZ);
      this.renderNameTag = true;
      this.lastDamageAttackTime = 0L;
      this.lastAttackTime = 0L;
      this.entityId = nextEntityID++;
      this.renderDistanceWeight = 1.0;
      this.boundingBox = ZERO_AABB;
      this.width = 0.6F;
      this.height = 1.8F;
      this.nextStepDistance = 1;
      this.rand = new Random();
      this.fireResistance = 1;
      this.firstUpdate = true;
      this.entityUniqueID = MathHelper.getRandomUuid(this.rand);
      this.cmdResultStats = new CommandResultStats();
      this.entityWorld = world;
      this.setPosition(0.0, 0.0, 0.0);
      if (world != null) {
         this.dimension = world.provider.getDimensionId();
      }

      this.dataWatcher = new DataWatcher(this);
      this.dataWatcher.addObject(0, (byte)0);
      this.dataWatcher.addObject(1, (short)300);
      this.dataWatcher.addObject(3, (byte)0);
      this.dataWatcher.addObject(2, "");
      this.dataWatcher.addObject(4, (byte)0);
      this.entityInit();
   }

   public void setOutsideBorder(boolean flag) {
      this.isOutsideBorder = flag;
   }

   public boolean isWet() {
      return this.inWater
         || this.entityWorld.isRainingAt(new BlockPos(this.posX, this.posY, this.posZ))
         || this.entityWorld.isRainingAt(new BlockPos(this.posX, this.posY + (double)this.height, this.posZ));
   }

   public void playSound(String str, float f, float f2) {
      if (!this.isSilent()) {
         this.entityWorld.playSoundAtEntity(this, str, f, f2);
      }
   }

   public int getPortalCooldown() {
      return 300;
   }

   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.setPosition(d, d2, d3);
      this.setRotation(f, f2);
      List collidingBoundingBoxes = this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox().contract(0.03125, 0.0, 0.03125));
      if (!collidingBoundingBoxes.isEmpty()) {
         double d4 = 0.0;

         for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(collidingBoundingBoxes)) {
            if (box.maxY > d4) {
               d4 = box.maxY;
            }
         }

         d2 += d4 - this.getEntityBoundingBox().minY;
         this.setPosition(d, d2, d3);
      }
   }

   public double getDistanceSqToCenter(BlockPos pos) {
      return pos.distanceSqToCenter(this.posX, this.posY, this.posZ);
   }

   public void updateRidden() {
      if (this.ridingEntity.isDead) {
         this.ridingEntity = null;
      } else {
         this.motionX = 0.0;
         this.motionY = 0.0;
         this.motionZ = 0.0;
         this.onUpdate();
         if (this.ridingEntity != null) {
            this.ridingEntity.updateRiderPosition();
            this.entityRiderYawDelta = this.entityRiderYawDelta + (double)(this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw);
            this.entityRiderPitchDelta = this.entityRiderPitchDelta + (double)(this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch);

            while (this.entityRiderYawDelta >= 180.0) {
               this.entityRiderYawDelta -= 360.0;
            }

            while (this.entityRiderYawDelta < -180.0) {
               this.entityRiderYawDelta += 360.0;
            }

            while (this.entityRiderPitchDelta >= 180.0) {
               this.entityRiderPitchDelta -= 360.0;
            }

            while (this.entityRiderPitchDelta < -180.0) {
               this.entityRiderPitchDelta += 360.0;
            }

            double d = this.entityRiderYawDelta * 0.5;
            double d2 = this.entityRiderPitchDelta * 0.5;
            float f = 10.0F;
            if (d > (double)f) {
               d = (double)f;
            }

            if (d < (double)(-f)) {
               d = (double)(-f);
            }

            if (d2 > (double)f) {
               d2 = (double)f;
            }

            if (d2 < (double)(-f)) {
               d2 = (double)(-f);
            }

            this.entityRiderYawDelta -= d;
            this.entityRiderPitchDelta -= d2;
         }
      }
   }

   public void setFlag(int n, boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(0);
      if (flag) {
         this.dataWatcher.updateObject(0, (byte)(watchableObjectByte | 1 << n));
      } else {
         this.dataWatcher.updateObject(0, (byte)(watchableObjectByte & ~(1 << n)));
      }
   }

   public int getAir() {
      return this.dataWatcher.getWatchableObjectShort(1);
   }

   public double getDistanceSqToEntity(Entity entity) {
      double d = this.posX - entity.posX;
      double d2 = this.posY - entity.posY;
      double d3 = this.posZ - entity.posZ;
      return d * d + d2 * d2 + d3 * d3;
   }

   public final boolean isImmuneToFire() {
      return this.isImmuneToFire;
   }

   public void writeToNBT(NBTTagCompound tag) {
      try {
         tag.setTag("Pos", this.newDoubleNBTList(new double[]{this.posX, this.posY, this.posZ}));
         tag.setTag("Motion", this.newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
         tag.setTag("Rotation", this.newFloatNBTList(new float[]{this.rotationYaw, this.rotationPitch}));
         tag.setFloat("FallDistance", this.fallDistance);
         tag.setShort("Fire", (short)this.fire);
         tag.setShort("Air", (short)this.getAir());
         tag.setBoolean("OnGround", this.onGround);
         tag.setInteger("Dimension", this.dimension);
         tag.setBoolean("Invulnerable", this.invulnerable);
         tag.setInteger("PortalCooldown", this.timeUntilPortal);
         tag.setLong("UUIDMost", this.getUniqueID().getMostSignificantBits());
         tag.setLong("UUIDLeast", this.getUniqueID().getLeastSignificantBits());
         if (this.getCustomNameTag() != null && this.getCustomNameTag().length() > 0) {
            tag.setString("CustomName", this.getCustomNameTag());
            tag.setBoolean("CustomNameVisible", this.getAlwaysRenderNameTag());
         }

         this.cmdResultStats.writeStatsToNBT(tag);
         if (this.isSilent()) {
            tag.setBoolean("Silent", this.isSilent());
         }

         this.writeEntityToNBT(tag);
         if (this.ridingEntity != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            if (this.ridingEntity.writeMountToNBT(tag2)) {
               tag.setTag("Riding", tag2);
            }
         }
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Saving entity NBT");
         this.addEntityCrashInfo(crashReport.makeCategory("Entity being saved"));
         throw new ReportedException(crashReport);
      }
   }

   public void copyDataFromOld(Entity entity) {
      NBTTagCompound tag = new NBTTagCompound();
      entity.writeToNBT(tag);
      this.readFromNBT(tag);
      this.timeUntilPortal = entity.timeUntilPortal;
      this.lastPortalPos = entity.lastPortalPos;
      this.lastPortalVec = entity.lastPortalVec;
      this.teleportDirection = entity.teleportDirection;
   }

   public boolean canTriggerWalking() {
      return true;
   }

   public void createRunningParticles() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posY - 0.20000000298023224);
      int n3 = MathHelper.floor_double(this.posZ);
      BlockPos pos = new BlockPos(n, n2, n3);
      IBlockState blockState = this.entityWorld.getBlockState(pos);
      if (blockState.getBlock().getRenderType() != -1) {
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.BLOCK_CRACK,
               this.posX + ((double)this.rand.nextFloat() - 0.5) * (double)this.width,
               this.getEntityBoundingBox().minY + 0.1,
               this.posZ + ((double)this.rand.nextFloat() - 0.5) * (double)this.width,
               -this.motionX * 4.0,
               1.5,
               -this.motionZ * 4.0,
               new int[]{Block.getStateId(blockState)}
            );
      }
   }

   public double getDistanceToVec(Vec3 vec) {
      double d = this.posX - vec.xCoord;
      double d2 = this.posY - vec.yCoord;
      double d3 = this.posZ - vec.zCoord;
      return (double)MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
   }

   public void setPositionAndRotation(double d, double d2, double d3, float f, float f2) {
      if (this instanceof EntityPlayerSP && ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         double d4 = MathHelper.clamp_double(d, -3.0E7, 3.0E7);
         double d5 = MathHelper.clamp_double(d3, -3.0E7, 3.0E7);
         this.prevPosX = d4;
         this.prevPosY = d2;
         this.prevPosZ = d5;
         this.setPosition(d, d2, d3);
         this.rotationYaw = f % 360.0F;
         this.rotationPitch = MathHelper.clamp_float(f2, -90.0F, 90.0F) % 360.0F;
         this.prevRotationYaw = f;
         this.prevRotationPitch = f2;
      } else {
         this.positionHistory.add(new Vec3(d, d2, d3));
         this.prevPosX = this.posX = d;
         this.prevPosY = this.posY = d2;
         this.prevPosZ = this.posZ = d3;
         this.prevRotationYaw = this.rotationYaw = f;
         this.prevRotationPitch = this.rotationPitch = f2;
         double d6 = (double)(this.prevRotationYaw - f);
         if (d6 < -180.0) {
            this.prevRotationYaw += 360.0F;
         }

         if (d6 >= 180.0) {
            this.prevRotationYaw -= 360.0F;
         }

         this.setPosition(this.posX, this.posY, this.posZ);
         this.setRotation(f, f2);
      }
   }

   public int getEntityId() {
      return this.entityId;
   }

   public void moveEntity(double d, double d2, double d3) {
      if (this.noClip) {
         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(d, d2, d3));
         this.resetPositionToBB();
      } else {
         this.entityWorld.theProfiler.startSection("move");
         double posX2 = this.posX;
         double posY2 = this.posY;
         double posZ2 = this.posZ;
         if (this.isInWeb) {
            this.isInWeb = false;
            EventWebSlowDown event = WebSlowDownListener.onWebSlowDown(0.25, 0.05000000074505806, false, false);
            d *= event.getHorizontalFactor();
            d2 *= event.getVerticalFactor();
            d3 *= event.getHorizontalFactor();
            if (!event.isKeepHorizontalMotion()) {
               this.motionX = 0.0;
               this.motionZ = 0.0;
            }

            if (!event.isKeepVerticalMotion()) {
               this.motionY = 0.0;
            }
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         boolean isSneaking = this.isSneaking();
         boolean flag = false;
         if (this instanceof EntityPlayerSP) {
            EventSafeWalk event2 = SafeWalkListener.onSafeWalk(StateTypes.PRE, false);
            if (event2.isDisableSneakSafeWalk()) {
               isSneaking = false;
            }

            flag = event2.isSafeWalk();
         }

         double d7 = d;
         double d8 = d3;
         boolean flag3 = (this.onGround && isSneaking || flag) && this instanceof EntityPlayer;
         if (flag3) {
            double d9;
            for (d9 = 0.05; d != 0.0 && this.entityWorld.getCollidingBoundingBoxesCapped(this, this.getEntityBoundingBox().offset(d, -1.0, 0.0)).isEmpty(); d4 = d) {
               if (d < d9 && d >= -d9) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d9;
               } else {
                  d += d9;
               }
            }

            for (; d3 != 0.0 && this.entityWorld.getCollidingBoundingBoxesCapped(this, this.getEntityBoundingBox().offset(0.0, -1.0, d3)).isEmpty(); d6 = d3) {
               if (d3 < d9 && d3 >= -d9) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d9;
               } else {
                  d3 += d9;
               }
            }

            for (; d != 0.0 && d3 != 0.0 && this.entityWorld.getCollidingBoundingBoxesCapped(this, this.getEntityBoundingBox().offset(d, -1.0, d3)).isEmpty(); d6 = d3) {
               if (d < d9 && d >= -d9) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d9;
               } else {
                  d += d9;
               }

               d4 = d;
               if (d3 < d9 && d3 >= -d9) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d9;
               } else {
                  d3 += d9;
               }
            }
         }

         if (this instanceof EntityPlayerSP) {
            boolean flag4 = d7 != d || d8 != d3;
            SafeWalkListener.onSafeWalk(StateTypes.POST, flag4);
         }

         List list = this.entityWorld.getCollidingBoundingBoxesCapped(this, this.getEntityBoundingBox().addCoord(d, d2, d3));
         AxisAlignedBB entityBoundingBox = this.getEntityBoundingBox();
         AxisAlignedBB box = null;
         double d10 = d2;

         for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(list)) {
            d2 = box2.calculateYOffset(this.getEntityBoundingBox(), d2);
            if (d2 != d10) {
               box = box2;
            }

            d10 = d2;
         }

         this.collidedYBoundingBox = box;
         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, d2, 0.0));
         boolean flag5 = this.onGround || d5 != d2 && d5 < 0.0;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d = box3.calculateXOffset(this.getEntityBoundingBox(), d);
         }

         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(d, 0.0, 0.0));

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d3 = box4.calculateZOffset(this.getEntityBoundingBox(), d3);
         }

         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, d3));
         float stepHeight2 = this.stepHeight;
         if (this instanceof EntityPlayerSP) {
            stepHeight2 = StepListener.onStep(this.stepHeight, StateTypes.PRE).getStepHeight();
         }

         if (stepHeight2 > 0.0F && flag5 && (d4 != d || d6 != d3)) {
            double d11 = d;
            double d12 = d2;
            double d13 = d3;
            AxisAlignedBB entityBoundingBox2 = this.getEntityBoundingBox();
            this.setEntityBoundingBox(entityBoundingBox);
            d2 = (double)stepHeight2;
            List list2 = this.entityWorld.getCollidingBoundingBoxesCapped(this, this.getEntityBoundingBox().addCoord(d4, d2, d6));
            AxisAlignedBB entityBoundingBox3 = this.getEntityBoundingBox();
            AxisAlignedBB box5 = entityBoundingBox3.addCoord(d4, 0.0, d6);
            double d14 = d2;

            for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box6.calculateYOffset(box5, d14);
            }

            entityBoundingBox3 = entityBoundingBox3.offset(0.0, d14, 0.0);
            double d15 = d4;

            for (AxisAlignedBB box7 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box7.calculateXOffset(entityBoundingBox3, d15);
            }

            entityBoundingBox3 = entityBoundingBox3.offset(d15, 0.0, 0.0);
            double d16 = d6;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box8.calculateZOffset(entityBoundingBox3, d16);
            }

            entityBoundingBox3 = entityBoundingBox3.offset(0.0, 0.0, d16);
            AxisAlignedBB entityBoundingBox4 = this.getEntityBoundingBox();
            double d17 = d2;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box9.calculateYOffset(entityBoundingBox4, d17);
            }

            entityBoundingBox4 = entityBoundingBox4.offset(0.0, d17, 0.0);
            double d18 = d4;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box10.calculateXOffset(entityBoundingBox4, d18);
            }

            entityBoundingBox4 = entityBoundingBox4.offset(d18, 0.0, 0.0);
            double d19 = d6;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d19 = box11.calculateZOffset(entityBoundingBox4, d19);
            }

            entityBoundingBox4 = entityBoundingBox4.offset(0.0, 0.0, d19);
            if ((d15 * d15 + d16 * d16) > (d18 * d18 + d19 * d19)) {
               d = d15;
               d3 = d16;
               d2 = -d14;
               this.setEntityBoundingBox(entityBoundingBox3);
            } else {
               d = d18;
               d3 = d19;
               d2 = -d17;
               this.setEntityBoundingBox(entityBoundingBox4);
            }

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d2 = box12.calculateYOffset(this.getEntityBoundingBox(), d2);
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, d2, 0.0));
            if (d11 * d11 + d13 * d13 >= d * d + d3 * d3) {
               d = d11;
               d2 = d12;
               d3 = d13;
               this.setEntityBoundingBox(entityBoundingBox2);
            }

            if (this instanceof EntityPlayerSP) {
               StepListener.postPostStep(StateTypes.POST);
            }
         }

         this.entityWorld.theProfiler.endSection();
         this.entityWorld.theProfiler.startSection("rest");
         this.resetPositionToBB();
         this.isCollidedHorizontally = d4 != d || d6 != d3;
         this.isCollidedVertically = d5 != d2;
         this.onGround = this.isCollidedVertically && d5 < 0.0;
         this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
         int n = MathHelper.floor_double(this.posX);
         int n2 = MathHelper.floor_double(this.posY - 0.20000000298023224);
         int n3 = MathHelper.floor_double(this.posZ);
         BlockPos pos = new BlockPos(n, n2, n3);
         Block block = this.entityWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = this.entityWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         this.updateFallState(d2, this.onGround, block, pos);
         if (d4 != d) {
            this.motionX = 0.0;
         }

         if (d6 != d3) {
            this.motionZ = 0.0;
         }

         if (d5 != d2) {
            block.onLanded(this.entityWorld, this);
         }

         if (this.canTriggerWalking() && !flag3 && this.ridingEntity == null) {
            double d22 = this.posX - posX2;
            double d23 = this.posY - posY2;
            double d24 = this.posZ - posZ2;
            if (block != Blocks.ladder) {
               d23 = 0.0;
            }

            if (block != null && this.onGround) {
               block.onEntityCollidedWithBlock(this.entityWorld, pos, this);
            }

            this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double(d22 * d22 + d24 * d24) * 0.6);
            this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double(d22 * d22 + d23 * d23 + d24 * d24) * 0.6);
            if (this.distanceWalkedOnStepModified > (float)this.nextStepDistance && block.getMaterial() != Material.air) {
               this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
               if (this.isInWater()) {
                  float f = MathHelper.sqrt_double(
                        this.motionX * this.motionX * 0.20000000298023224 + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224
                     )
                     * 0.35F;
                  if (f > 1.0F) {
                     f = 1.0F;
                  }

                  this.playSound(this.getSwimSound(), f, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
               }

               this.playStepSound(pos, block);
            }
         }

         try {
            this.doBlockCollisions();
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Checking entity block collision");
            this.addEntityCrashInfo(crashReport.makeCategory("Entity being checked for collision"));
            throw new ReportedException(crashReport);
         }

         boolean isWet = this.isWet();
         if (this.entityWorld.isFlammableWithin(this.getEntityBoundingBox().contract(0.001, 0.001, 0.001))) {
            this.dealFireDamage(1);
            if (!isWet) {
               this.fire++;
               if (this.fire == 0) {
                  this.setFire(8);
               }
            }
         } else if (this.fire <= 0) {
            this.fire = -this.fireResistance;
         }

         if (isWet && this.fire > 0) {
            this.playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
            this.fire = -this.fireResistance;
         }

         this.entityWorld.theProfiler.endSection();
      }
   }

   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         this.setBeenAttacked();
         return false;
      }
   }

   public EntityItem dropItem(Item item, int n) {
      return this.dropItemWithOffset(item, n, 0.0F);
   }

   public boolean isEntityAlive() {
      return !this.isDead;
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean isSprinting() {
      return this.getFlag(3);
   }

   public World getEntityWorld() {
      return this.entityWorld;
   }

   public void addToPlayerScore(Entity entity, int n) {
   }

   public void onEntityUpdate() {
      this.entityWorld.theProfiler.startSection("entityBaseTick");
      if (this.ridingEntity != null && this.ridingEntity.isDead) {
         this.ridingEntity = null;
      }

      long l = System.currentTimeMillis();
      this.prevDistanceWalkedModified = this.distanceWalkedModified;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      if (!this.entityWorld.isRemote && this.entityWorld instanceof WorldServer) {
         this.entityWorld.theProfiler.startSection("portal");
         MinecraftServer minecraftServer = ((WorldServer)this.entityWorld).getMinecraftServer();
         int maxInPortalTime = this.getMaxInPortalTime();
         if (this.inPortal) {
            if (minecraftServer.getAllowNether()) {
               if (this.ridingEntity == null && this.portalCounter++ >= maxInPortalTime) {
                  this.portalCounter = maxInPortalTime;
                  this.timeUntilPortal = this.getPortalCooldown();
                  byte b;
                  if (this.entityWorld.provider.getDimensionId() == -1) {
                     b = 0;
                  } else {
                     b = -1;
                  }

                  this.travelToDimension(b);
               }

               this.inPortal = false;
            }
         } else {
            if (this.portalCounter > 0) {
               this.portalCounter -= 4;
            }

            if (this.portalCounter < 0) {
               this.portalCounter = 0;
            }
         }

         if (this.timeUntilPortal > 0) {
            this.timeUntilPortal--;
         }

         this.entityWorld.theProfiler.endSection();
      }

      this.spawnRunningParticles();
      this.handleWaterMovement();
      if (this.entityWorld.isRemote) {
         this.fire = 0;
      } else if (this.fire > 0) {
         if (this.isImmuneToFire) {
            this.fire -= 4;
            if (this.fire < 0) {
               this.fire = 0;
            }
         } else {
            if (this.fire % 20 == 0) {
               this.attackEntityFrom(DamageSource.onFire, 1.0F);
            }

            this.fire--;
         }
      }

      if (this.isInLava()) {
         this.setOnFireFromLava();
         this.fallDistance *= 0.5F;
         this.clientFallDistance *= 0.5F;
      }

      if (this.posY < -64.0) {
         this.kill();
      }

      if (!this.entityWorld.isRemote) {
         this.setFlag(0, this.fire > 0);
      }

      this.firstUpdate = false;
      this.entityWorld.theProfiler.endSection();
   }

   public void readFromNBT(NBTTagCompound tag) {
      try {
         NBTTagList tagList = tag.getTagList("Pos", 6);
         NBTTagList tagList2 = tag.getTagList("Motion", 6);
         NBTTagList tagList3 = tag.getTagList("Rotation", 5);
         this.motionX = tagList2.getDoubleAt(0);
         this.motionY = tagList2.getDoubleAt(1);
         this.motionZ = tagList2.getDoubleAt(2);
         if (Math.abs(this.motionX) > 10.0) {
            this.motionX = 0.0;
         }

         if (Math.abs(this.motionY) > 10.0) {
            this.motionY = 0.0;
         }

         if (Math.abs(this.motionZ) > 10.0) {
            this.motionZ = 0.0;
         }

         this.prevPosX = this.lastTickPosX = this.posX = tagList.getDoubleAt(0);
         this.prevPosY = this.lastTickPosY = this.posY = tagList.getDoubleAt(1);
         this.prevPosZ = this.lastTickPosZ = this.posZ = tagList.getDoubleAt(2);
         this.prevRotationYaw = this.rotationYaw = tagList3.getFloatAt(0);
         this.prevRotationPitch = this.rotationPitch = tagList3.getFloatAt(1);
         this.setRotationYawHead(this.rotationYaw);
         this.setRenderYawOffset(this.rotationYaw);
         this.fallDistance = tag.getFloat("FallDistance");
         this.fire = tag.getShort("Fire");
         this.setAir(tag.getShort("Air"));
         this.onGround = tag.getBoolean("OnGround");
         this.dimension = tag.getInteger("Dimension");
         this.invulnerable = tag.getBoolean("Invulnerable");
         this.timeUntilPortal = tag.getInteger("PortalCooldown");
         if (tag.hasKey("UUIDMost", 4) && tag.hasKey("UUIDLeast", 4)) {
            this.entityUniqueID = new UUID(tag.getLong("UUIDMost"), tag.getLong("UUIDLeast"));
         } else if (tag.hasKey("UUID", 8)) {
            this.entityUniqueID = UUID.fromString(tag.getString("UUID"));
         }

         this.setPosition(this.posX, this.posY, this.posZ);
         this.setRotation(this.rotationYaw, this.rotationPitch);
         if (tag.hasKey("CustomName", 8) && tag.getString("CustomName").length() > 0) {
            this.setCustomNameTag(tag.getString("CustomName"));
         }

         this.setAlwaysRenderNameTag(tag.getBoolean("CustomNameVisible"));
         this.cmdResultStats.readStatsFromNBT(tag);
         this.setSilent(tag.getBoolean("Silent"));
         this.readEntityFromNBT(tag);
         if (this.shouldSetPosAfterLoading()) {
            this.setPosition(this.posX, this.posY, this.posZ);
         }
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Loading entity NBT");
         this.addEntityCrashInfo(crashReport.makeCategory("Entity being loaded"));
         throw new ReportedException(crashReport);
      }
   }

   public DataWatcher getDataWatcher() {
      return this.dataWatcher;
   }

   public int getBrightnessForRender(float f) {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posY + (double)this.getEyeHeight());
      int n3 = MathHelper.floor_double(this.posZ);
      return this.entityWorld.isBlockLoaded(n, n2, n3) ? this.entityWorld.getCombinedLight(n, n2, n3, 0) : 0;
   }

   public IChatComponent getDisplayName() {
      ChatComponentText chatComponentText = new ChatComponentText(this.getName());
      chatComponentText.getChatStyle().setChatHoverEvent(this.getHoverEvent());
      chatComponentText.getChatStyle().setInsertion(this.getUniqueID().toString());
      return chatComponentText;
   }

   public void onCollideWithPlayer(EntityPlayer player) {
   }

   public String getCustomNameTag() {
      return this.dataWatcher.getWatchableObjectString(2);
   }

   public String getSplashSound() {
      return "game.neutral.swim.splash";
   }

   public boolean isRiding() {
      return this.ridingEntity != null;
   }

   public float getEyeHeight() {
      return this.height * 0.85F;
   }

   public boolean isSilent() {
      return this.dataWatcher.getWatchableObjectByte(4) == 1;
   }

   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
      if (flag) {
         if (this.fallDistance > 0.0F) {
            if (block != null) {
               block.onFallenUpon(this.entityWorld, pos, this, this.fallDistance);
            } else {
               this.fall(this.fallDistance, 1.0F);
            }

            this.fallDistance = 0.0F;
         }
      } else if (d < 0.0) {
         this.fallDistance = (float)((double)this.fallDistance - d);
      }

      if (flag) {
         if (this.clientFallDistance > 0.0F) {
            this.clientFallDistance = 0.0F;
         }
      } else if (d < 0.0) {
         this.clientFallDistance = (float)((double)this.clientFallDistance - d);
      }
   }

   public double getYOffset() {
      return 0.0;
   }

   public boolean isInRangeToRenderDist(double d) {
      double averageEdgeLength = this.getEntityBoundingBox().getAverageEdgeLength();
      if (Double.isNaN(averageEdgeLength)) {
         averageEdgeLength = 1.0;
      }

      averageEdgeLength = averageEdgeLength * 64.0 * this.renderDistanceWeight;
      return d < averageEdgeLength * averageEdgeLength || Augustus.getInstance().getModuleManager().itemESP.isToggled();
   }

   public EnumFacing getHorizontalFacing() {
      return EnumFacing.getHorizontal(MathHelper.floor_double((double)(this.rotationYaw * 4.0F / 360.0F) + 0.5) & 3);
   }

   private boolean isLiquidPresentInAABB(AxisAlignedBB box) {
      return this.entityWorld.getCollidingBoundingBoxes(this, box).isEmpty() && !this.entityWorld.isAnyLiquid(box);
   }

   public boolean isOutsideBorder() {
      return this.isOutsideBorder;
   }

   public boolean sendCommandFeedback() {
      return false;
   }

   public void setInWeb() {
      this.isInWeb = true;
      this.fallDistance = 0.0F;
      this.clientFallDistance = 0.0F;
   }

   public UUID getUniqueID() {
      return this.entityUniqueID;
   }

   public float getExplosionResistance(Explosion explosion, World world, BlockPos pos, IBlockState state) {
      return state.getBlock().getExplosionResistance(this);
   }

   public abstract void readEntityFromNBT(NBTTagCompound tag);

   public boolean verifyExplosion(Explosion explosion, World world, BlockPos pos, IBlockState state, float f) {
      return true;
   }

   public ItemStack[] getInventory() {
      return null;
   }

   public void setWorld(World world) {
      this.entityWorld = world;
   }

   public void copyLocationAndAnglesFrom(Entity entity) {
      this.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
   }

   public void setCommandStat(CommandResultStats.Type type, int n) {
      this.cmdResultStats.setCommandStatScore(this, type, n);
   }

   public boolean isEntityInsideOpaqueBlock() {
      if (!this.noClip && !Augustus.getInstance().getModuleManager().freeCam.isToggled()) {
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(-2147483648, -2147483648, -2147483648);

         for (int i = 0; i < 8; i++) {
            int n = MathHelper.floor_double(this.posY + (double)(((float)((i >> 0) % 2) - 0.5F) * 0.1F) + (double)this.getEyeHeight());
            int n2 = MathHelper.floor_double(this.posX + (double)(((float)((i >> 1) % 2) - 0.5F) * this.width * 0.8F));
            int n3 = MathHelper.floor_double(this.posZ + (double)(((float)((i >> 2) % 2) - 0.5F) * this.width * 0.8F));
            if (pos.getX() != n2 || pos.getY() != n || pos.getZ() != n3) {
               pos.set(n2, n, n3);
               if (this.entityWorld.getBlockState(pos).getBlock().isVisuallyOpaque()) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void onChunkLoad() {
   }

   public void spawnRunningParticles() {
      if (this.isSprinting() && !this.isInWater() && this.onGround) {
         this.createRunningParticles();
      }
   }

   public void clientUpdateEntityNBT(NBTTagCompound tag) {
   }

   public boolean isEating() {
      return this.getFlag(4);
   }

   public abstract void writeEntityToNBT(NBTTagCompound tag);

   public Entity[] getParts() {
      return null;
   }

   public void setRenderYawOffset(float f) {
   }

   public void setEntityBoundingBox(AxisAlignedBB box) {
      this.boundingBox = box;
   }

   public void setVelocity(double d, double d2, double d3) {
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
   }



   public static class ValueCallable implements Callable {
      public final Entity entity;

      @Override
      public Object call() throws Exception {
         return this.getEntityTypeInfo();
      }

      public ValueCallable(Entity entity2) {
         this.entity = entity2;
      }

      public String getEntityTypeInfo() throws Exception {
         return EntityList.getEntityString(this.entity) + " (" + this.entity.getClass().getCanonicalName() + ")";
      }
   }

   public static class ValueCallable2 implements Callable {
      public final Entity entity;

      @Override
      public Object call() throws Exception {
         return this.getEntityNameInfo();
      }

      public ValueCallable2(Entity entity2) {
         this.entity = entity2;
      }

      public String getEntityNameInfo() throws Exception {
         return this.entity.getName();
      }
   }

   public static class ValueCallable3 implements Callable {
      public final Entity entity;

      public ValueCallable3(Entity entity2) {
         this.entity = entity2;
      }

      @Override
      public Object call() throws Exception {
         return this.getRiddenByInfo();
      }

      public String getRiddenByInfo() throws Exception {
         return this.entity.riddenByEntity.toString();
      }
   }

   public static class ValueCallable4 implements Callable {
      public final Entity entity;

      @Override
      public Object call() throws Exception {
         return this.getRidingInfo();
      }

      public String getRidingInfo() throws Exception {
         return this.entity.ridingEntity.toString();
      }

      public ValueCallable4(Entity entity2) {
         this.entity = entity2;
      }
   }
}
