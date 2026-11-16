package net.minecraft.entity;

import client.Client;
import client.ClientGlobals;
import client.event.EventSprint;
import client.event.EventTickStateCallback;
import client.event.SprintListener;
import client.module.Module;
import client.render.AttackRangeRenderer;
import client.rotation.Rotation;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsShowTextType;
import client.setting.CombatNumberProperty;
import client.setting.EnumFacingPositiveType;
import client.setting.EnumFacingXType;
import client.util.AuxiliaryAbstractEventAdapter;
import client.util.BacktrackRecord;
import client.util.CommandStatsManager;
import client.util.DataWatcher;
import client.util.FovConverter;
import client.util.FovEstimator;
import client.util.HUIHRVManager;
import client.util.HoverEventActionManager;
import client.util.IBlockState;
import client.util.InternalClientManagerRegistry;
import client.util.InterpolationAccumulator;
import client.util.LvlManager;
import client.util.PrimaryDoubleEntry;
import client.util.RayTraceWriter;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SkippingEntityWithIdManager;
import client.util.SmoothSynchronizer;
import client.util.SplineRanker;
import client.util.StepManager;
import client.util.VanillaRunnable;
import client.util.WMAZOA32EnchantmentManager;
import client.via.ViaLoadingBase;
import client.via.ViaProtocolHelper;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.dietrichevents2.StateTypes;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockBreakState;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.InternalBlockPosEntry;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity extends RayTraceWriter implements ICommandSender {
   public static final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   public static int count6;
   public int entityId;
   public double value3;
   public boolean enabled;
   public Entity entity;
   public Entity ridingEntity;
   public boolean enabled10;
   public World worldObj;
   public double prevPosX;
   public double prevPosY;
   public double prevPosZ;
   public BacktrackRecord backtrackRecord = new BacktrackRecord(23);
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
   public AxisAlignedBB boundingBox2;
   public boolean onGround;
   public boolean isCollidedHorizontally;
   public boolean isCollidedVertically;
   public boolean enabled5;
   public boolean enabled8;
   public boolean isInWeb;
   public boolean state;
   public boolean isDead;
   public float width;
   public float height;
   public float factor4;
   public float distanceWalkedModified;
   public float factor;
   public float fallDistance;
   public float factor2;
   public int count5;
   public double lastTickPosX;
   public double lastTickPosY;
   public double lastTickPosZ;
   public float stepHeight;
   public boolean noClip;
   public float factor3;
   public Random random;
   public int ticksExisted;
   public int count3;
   public int count9;
   public boolean inWater;
   public int hurtResistantTime;
   public boolean enabled2;
   public boolean state4;
   public DataWatcher dataWatcher;
   public double value;
   public double value2;
   public boolean enabled9;
   public int count7;
   public int count2;
   public int count8;
   public int indexOffset2;
   public int indexOffset3;
   public int indexOffset;
   public Vec3 vec3;
   public boolean enabled3;
   public boolean enabled4;
   public int count10;
   public boolean enabled7;
   public int count;
   public int count4;
   public BlockPos blockPos;
   public Vec3 positionVec2;
   public EnumFacing enumFacing;
   public boolean enabled6;
   public UUID entityUniqueID;
   public final CommandStatsManager commandStatsManager;
   public AxisAlignedBB boundingBox3;
   public boolean state1;
   public long epochMillis;
   public long timestamp;

   public void setScaleFactor12(float scaleFactor) {
   }

   public void applyBlockPos(BlockPos blockPos2) {
      if (this.count10 > 0) {
         this.count10 = this.getCount2();
      } else {
         if (!this.worldObj.state && !blockPos2.equals(this.blockPos)) {
            this.blockPos = blockPos2;
            InternalBlockPosEntry internalBlockPosEntry = Blocks.blockPortal.resolveInternalBlockPosEntry(this.worldObj, blockPos2);
            double offset = internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X
               ? (double)internalBlockPosEntry.getBlockPos().getZ()
               : (double)internalBlockPosEntry.getBlockPos().getX();
            double offset2 = Math.abs(
               MathHelper.clamp_double(
                  (internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X ? this.posZ : this.posX)
                     - (double)(internalBlockPosEntry.getEnumFacing().getEnumFacingAsEnumFacing().getEnumFacingPositiveType() == EnumFacingPositiveType.NEGATIVE ? 1 : 0),
                  offset,
                  offset - (double)internalBlockPosEntry.getCount()
               )
            );
            double offset3 = MathHelper.clamp_double(this.posY - 1.0, (double)internalBlockPosEntry.getBlockPos().getY(), (double)(internalBlockPosEntry.getBlockPos().getY() - internalBlockPosEntry.getCount2()));
            this.positionVec2 = new Vec3(offset2, offset3, 0.0);
            this.enumFacing = internalBlockPosEntry.getEnumFacing();
         }

         this.enabled7 = true;
      }
   }

   public UUID getEntityUniqueID() {
      return this.entityUniqueID;
   }

   public MovingObjectPosition getMovingObjectPosition(double offset, float posX2, float posY2, float posZ2) {
      Vec3 vec32 = this.getVec35(posX2);
      Vec3 vec33 = this.getVec33(posX2, posY2, posZ2);
      Vec3 vec34 = vec32.getVec312(vec33.xCoord * offset, vec33.yCoord * offset, vec33.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec32.xCoord, vec32.yCoord, vec32.zCoord, vec34.xCoord, vec34.yCoord, vec34.zCoord, false, false, true);
   }

   public int getCount() {
      return this.dataWatcher.computeShortCode(1);
   }

   public void applyNBTTagCompound3(NBTTagCompound tagCompound) {
      try {
         tagCompound.applyText("Pos", this.resolveNBTTagList(new double[]{this.posX, this.posY, this.posZ}));
         tagCompound.applyText("Motion", this.resolveNBTTagList(new double[]{this.motionX, this.motionY, this.motionZ}));
         tagCompound.applyText("Rotation", this.resolveNBTTagList(new float[]{this.rotationYaw, this.rotationPitch}));
         tagCompound.put("FallDistance", this.fallDistance);
         tagCompound.put("Fire", (short)this.count9);
         tagCompound.put("Air", (short)this.getCount());
         tagCompound.applyText("OnGround", this.onGround);
         tagCompound.put("Dimension", this.count4);
         tagCompound.applyText("Invulnerable", this.enabled6);
         tagCompound.put("PortalCooldown", this.count10);
         tagCompound.put("UUIDMost", this.getEntityUniqueID().getMostSignificantBits());
         tagCompound.put("UUIDLeast", this.getEntityUniqueID().getLeastSignificantBits());
         if (this.getText2() != null && this.getText2().length() > 0) {
            tagCompound.put("CustomName", this.getText2());
            tagCompound.applyText("CustomNameVisible", this.isActive6());
         }

         this.commandStatsManager.applyNBTTagCompoundByNBTTagCompound(tagCompound);
         if (this.isActive7()) {
            tagCompound.applyText("Silent", this.isActive7());
         }

         this.applyNBTTagCompoundByNBTTagCompound(tagCompound);
         if (this.ridingEntity != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            if (this.ridingEntity.matchesNBTTagCompoundByNBTTagCompound(tagCompound2)) {
               tagCompound.applyText("Riding", tagCompound2);
            }
         }
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Saving entity NBT");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being saved");
         this.applyCrashReportCategory(crashReportCategory);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public void setEnabled3(boolean enabled11) {
      this.dataWatcher.applyTargetByTarget(4, (byte)(enabled11 ? 1 : 0));
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   public void applyEntityByEntity(Entity entity2, int count11) {
   }

   public double computeValue(BlockPos blockPos2) {
      return blockPos2.computeValue(this.posX, this.posY, this.posZ);
   }

   public void processDouble2(double posX2, double posY2, double posZ2) {
      BacktrackRecord backtrackRecord2 = this.backtrackRecord;
      Vec3 vec = new Vec3(posX2, posY2, posZ2);
      backtrackRecord2.applyObject(vec);
      this.posX = posX2;
      this.posY = posY2;
      this.posZ = posZ2;
      float factor5 = this.width / 2.0F;
      float height2 = this.height;
      this.setBoundingBox2(
         new AxisAlignedBB(posX2 - (double)factor5, posY2, posZ2 - (double)factor5, posX2 + (double)factor5, posY2 + (double)height2, posZ2 + (double)factor5)
      );
   }

   public boolean processAction() {
      return !this.dataWatcher.resolveText(2).isEmpty();
   }

   public MovingObjectPosition getMovingObjectPosition2(double offset, float factor5) {
      Vec3 vec32 = this.getVec35(factor5);
      Vec3 vec33 = this.getVec36(factor5);
      Vec3 vec34 = vec32.getVec312(vec33.xCoord * offset, vec33.yCoord * offset, vec33.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec32.xCoord, vec32.yCoord, vec32.zCoord, vec34.xCoord, vec34.yCoord, vec34.zCoord, false, false, true);
   }

   public String getText6() {
      return "game.neutral.swim";
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(this.posX, this.posY, this.posZ);
   }

   public boolean checkDouble(double offset) {
      double value4 = this.getBoundingBox2().getValue();
      if (Double.isNaN(value4)) {
         value4 = 1.0;
      }

      double offset2 = value4 * 64.0 * this.value3;
      return offset < offset2 * offset2 || ((Module)Client.getClient().getModuleManager().itemESP).isToggled();
   }

   @Override
   public boolean matchesText(int count11, String text) {
      return true;
   }

   public void setBoundingBox2(AxisAlignedBB boundingBox) {
      this.boundingBox2 = boundingBox;
   }

   public void applyIndex3(int count11) {
      this.dataWatcher.applyTargetByTarget(1, (short)count11);
   }

   public void updateState17() {
      if (this.entity != null) {
         this.entity.processDouble2(this.posX, this.posY + this.getValueAsDouble() + this.entity.getValue(), this.posZ);
      }
   }

   public void applyBlockPos(BlockPos blockPos2, Block block) {
      StepManager stepManager = block.stepManager20;
      if (this.worldObj.getBlockState(blockPos2.up()).getBlock() == Blocks.block74) {
         StepManager stepManager2 = Blocks.block74.stepManager20;
         this.applyText(stepManager2.getText(), stepManager2.getFactor2() * 0.15F, stepManager2.getFactor());
      } else if (!block.getMaterial().isActive2()) {
         this.applyText(stepManager.getText(), stepManager.getFactor2() * 0.15F, stepManager.getFactor());
      }
   }

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      return false;
   }

   public void applyNBTTagCompound2(NBTTagCompound tagCompound) {
      try {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Pos", 6);
         NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("Motion", 6);
         NBTTagList extendedLoggerEntry3 = tagCompound.getExtendedLoggerEntry("Rotation", 5);
         this.motionX = extendedLoggerEntry2.computeValue(0);
         this.motionY = extendedLoggerEntry2.computeValue(1);
         this.motionZ = extendedLoggerEntry2.computeValue(2);
         if (Math.abs(this.motionX) > 10.0) {
            this.motionX = 0.0;
         }

         if (Math.abs(this.motionY) > 10.0) {
            this.motionY = 0.0;
         }

         if (Math.abs(this.motionZ) > 10.0) {
            this.motionZ = 0.0;
         }

         this.prevPosX = this.lastTickPosX = this.posX = extendedLoggerEntry.computeValue(0);
         this.prevPosY = this.lastTickPosY = this.posY = extendedLoggerEntry.computeValue(1);
         this.prevPosZ = this.lastTickPosZ = this.posZ = extendedLoggerEntry.computeValue(2);
         this.prevRotationYaw = this.rotationYaw = extendedLoggerEntry3.computeFactor(0);
         this.prevRotationPitch = this.rotationPitch = extendedLoggerEntry3.computeFactor(1);
         this.setScaleFactor12(this.rotationYaw);
         this.setScaleFactor8(this.rotationYaw);
         this.fallDistance = tagCompound.computeFactor("FallDistance");
         this.count9 = tagCompound.processValue("Fire");
         this.applyIndex3(tagCompound.processValue("Air"));
         this.onGround = tagCompound.matchesText("OnGround");
         this.count4 = tagCompound.computeCount("Dimension");
         this.enabled6 = tagCompound.matchesText("Invulnerable");
         this.count10 = tagCompound.computeCount("PortalCooldown");
         if (tagCompound.matchesText("UUIDMost", 4) && tagCompound.matchesText("UUIDLeast", 4)) {
            this.entityUniqueID = new UUID(tagCompound.computeTimestamp("UUIDMost"), tagCompound.computeTimestamp("UUIDLeast"));
         } else if (tagCompound.matchesText("UUID", 8)) {
            this.entityUniqueID = UUID.fromString(tagCompound.resolveText("UUID"));
         }

         this.processDouble2(this.posX, this.posY, this.posZ);
         this.processFloat4(this.rotationYaw, this.rotationPitch);
         if (tagCompound.matchesText("CustomName", 8) && tagCompound.resolveText("CustomName").length() > 0) {
            this.setLabelText(tagCompound.resolveText("CustomName"));
         }

         this.setEnabled(tagCompound.matchesText("CustomNameVisible"));
         this.commandStatsManager.applyNBTTagCompound(tagCompound);
         this.setEnabled3(tagCompound.matchesText("Silent"));
         this.applyNBTTagCompound(tagCompound);
         if (this.isActive14()) {
            this.processDouble2(this.posX, this.posY, this.posZ);
         }
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Loading entity NBT");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being loaded");
         this.applyCrashReportCategory(crashReportCategory);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public Vec3 getVec32(float factor5) {
      if (factor5 == 1.0F) {
         return new Vec3(this.posX, this.posY, this.posZ);
      } else {
         double offset = this.prevPosX + (this.posX - this.prevPosX) * (double)factor5;
         double offset2 = this.prevPosY + (this.posY - this.prevPosY) * (double)factor5;
         double offset3 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)factor5;
         return new Vec3(offset, offset2, offset3);
      }
   }

   public int computeCount(float factor5) {
      int count11 = MathHelper.ceiling_double_int(this.posX);
      int count12 = MathHelper.ceiling_double_int(this.posY + (double)this.getHeight2());
      int count13 = MathHelper.ceiling_double_int(this.posZ);
      return this.worldObj.checkIndex(count11, count12, count13) ? this.worldObj.computeCount(count11, count12, count13, 0) : 0;
   }

   public EnumFacing getEnumFacing2(float factor5) {
      return EnumFacing.resolveEnumFacing(MathHelper.ceiling_double_int((double)(factor5 * 4.0F / 360.0F) + 0.5) & 3);
   }

   public int getCountAsIndex() {
      return 0;
   }

   public boolean matchesBlockBreakState(BlockBreakState blockBreakState, World world, BlockPos blockPos2, IBlockState blockState, float factor5) {
      return true;
   }

   public double getValueAsDouble() {
      return (double)this.height * 0.75;
   }

   public boolean isActiveAsFlag() {
      return true;
   }

   public MovingObjectPosition resolveMovingObjectPosition(double offset, float posX2, float posY2, float posZ2, Vec3 vec) {
      Vec3 vec32 = vec.getVec312(0.0, (double)this.getHeight2(), 0.0);
      Vec3 vec33 = this.getVec33(posX2, posY2, posZ2);
      Vec3 vec34 = vec32.getVec312(vec33.xCoord * offset, vec33.yCoord * offset, vec33.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec32.xCoord, vec32.yCoord, vec32.zCoord, vec34.xCoord, vec34.yCoord, vec34.zCoord, false, false, true);
   }

   public CommandStatsManager getCommandStatsManager() {
      return this.commandStatsManager;
   }

   public Entity[] getEntityArray() {
      return null;
   }

   public void updateState19() {
      this.updateState9();
   }

   public NBTTagList resolveNBTTagList(double[] offsetArray) {
      NBTTagList nBTTagList = new NBTTagList();

      for (double offset : offsetArray) {
         nBTTagList.applyLISTManager(new PrimaryDoubleEntry(offset));
      }

      return nBTTagList;
   }

   public float getHeight() {
      return !ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)
            && (
               !((Module)Client.getClient().getModuleManager().hitBox).isToggled() || !Client.getClient().getModuleManager().hitBox.setting18Hitbox.isEnabled()
            )
         ? 0.0F
         : 0.1F;
   }

   public boolean matchesEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      return true;
   }

   public void applyIndex4(int count11) {
   }

   public boolean checkDouble(double posX2, double posY2, double posZ2) {
      BlockPos blockPos2 = new BlockPos(posX2, posY2, posZ2);
      double offset = posX2 - (double)blockPos2.getX();
      double offset2 = posY2 - (double)blockPos2.getY();
      double offset3 = posZ2 - (double)blockPos2.getZ();
      if (this.worldObj.resolveList(this.getBoundingBox2()).isEmpty() && !this.worldObj.matchesBlockPos5(blockPos2)) {
         return false;
      } else {
         byte byteValue = 3;
         double offset4 = 9999.0;
         if (!this.worldObj.matchesBlockPos5(blockPos2.getBlockPos()) && offset < offset4) {
            offset4 = offset;
            byteValue = 0;
         }

         if (!this.worldObj.matchesBlockPos5(blockPos2.getBlockPos3()) && 1.0 - offset < offset4) {
            offset4 = 1.0 - offset;
            byteValue = 1;
         }

         if (!this.worldObj.matchesBlockPos5(blockPos2.up()) && 1.0 - offset2 < offset4) {
            offset4 = 1.0 - offset2;
            byteValue = 3;
         }

         if (!this.worldObj.matchesBlockPos5(blockPos2.getBlockPosAsBlockPos()) && offset3 < offset4) {
            offset4 = offset3;
            byteValue = 4;
         }

         if (!this.worldObj.matchesBlockPos5(blockPos2.getBlockPos2()) && 1.0 - offset3 < offset4) {
            byteValue = 5;
         }

         float factor5 = this.random.nextFloat() * 0.2F + 0.1F;
         if (byteValue == 0) {
            this.motionX = (double)(-factor5);
         }

         if (byteValue == 1) {
            this.motionX = (double)factor5;
         }

         if (byteValue == 3) {
            this.motionY = (double)factor5;
         }

         if (byteValue == 4) {
            this.motionZ = (double)(-factor5);
         }

         if (byteValue == 5) {
            this.motionZ = (double)factor5;
         }

         return true;
      }
   }

   @Override
   public String getText() {
      if (this.processAction()) {
         return this.getText2();
      } else {
         String text = SkippingEntityWithIdManager.resolveTextByEntity(this);
         if (text == null) {
            text = "generic";
         }

         return I18n.getString2("entity." + text + ".name");
      }
   }

   public DataWatcher getDataWatcher() {
      return this.dataWatcher;
   }

   public NBTTagList resolveNBTTagList(float[] factorArray) {
      NBTTagList nBTTagList = new NBTTagList();

      for (float factor5 : factorArray) {
         nBTTagList.applyLISTManager(new InternalClientManagerRegistry(factor5));
      }

      return nBTTagList;
   }

   public void setEntity8(Entity entity2) {
      this.value = 0.0;
      this.value2 = 0.0;
      if (entity2 == null) {
         if (this.ridingEntity != null) {
            this.applyDoubleByDouble(
               this.ridingEntity.posX,
               this.ridingEntity.getBoundingBox2().minY + (double)this.ridingEntity.height,
               this.ridingEntity.posZ,
               this.rotationYaw,
               this.rotationPitch
            );
            this.ridingEntity.entity = null;
         }

         this.ridingEntity = null;
      } else {
         if (this.ridingEntity != null) {
            this.ridingEntity.entity = null;
         }

         if (entity2 != null) {
            for (Entity ridingEntity2 = entity2.ridingEntity; ridingEntity2 != null; ridingEntity2 = ridingEntity2.ridingEntity) {
               if (ridingEntity2 == this) {
                  return;
               }
            }
         }

         this.ridingEntity = entity2;
         entity2.entity = this;
      }
   }

   public boolean checkIndex(int count11) {
      return (this.dataWatcher.computeByteCode(0) & 1 << count11) != 0;
   }

   public NBTTagCompound getNBTTagCompound() {
      return null;
   }

   public float getHeight2() {
      return this.height * 0.85F;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, Entity entity2) {
      if (entity2 instanceof EntityLivingBase) {
         LvlManager.applyEntityLivingBase((EntityLivingBase)entity2, livingEntity);
      }

      LvlManager.applyEntityLivingBase2(livingEntity, entity2);
   }

   public void setIsInWeb(boolean isInWeb2) {
      this.isInWeb = isInWeb2;
   }

   public abstract void applyNBTTagCompound(NBTTagCompound tagCompound);

   public boolean matchesNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      String text = this.getText8();
      if (!this.isDead && text != null) {
         tagCompound.put("id", text);
         this.applyNBTTagCompound3(tagCompound);
         return true;
      } else {
         return false;
      }
   }

   public void updateState21() {
      if (this.ridingEntity.isDead) {
         this.ridingEntity = null;
      } else {
         this.motionX = 0.0;
         this.motionY = 0.0;
         this.motionZ = 0.0;
         this.updateState();
         if (this.ridingEntity != null) {
            this.ridingEntity.updateState17();
            this.value2 = this.value2 + (double)(this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw);
            this.value = this.value + (double)(this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch);

            while (this.value2 >= 180.0) {
               this.value2 -= 360.0;
            }

            while (this.value2 < -180.0) {
               this.value2 += 360.0;
            }

            while (this.value >= 180.0) {
               this.value -= 360.0;
            }

            while (this.value < -180.0) {
               this.value += 360.0;
            }

            double offset = this.value2 * 0.5;
            double offset2 = this.value * 0.5;
            float factor5 = 10.0F;
            if (offset > (double)factor5) {
               offset = (double)factor5;
            }

            if (offset < (double)(-factor5)) {
               offset = (double)(-factor5);
            }

            if (offset2 > (double)factor5) {
               offset2 = (double)factor5;
            }

            if (offset2 < (double)(-factor5)) {
               offset2 = (double)(-factor5);
            }

            this.value2 -= offset;
            this.value -= offset2;
         }
      }
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count11) {
      this.commandStatsManager.applyICommandSender(this, combatNumberProperty, count11);
   }

   public void applyText(String text, float factor5, float factor6) {
      if (!this.isActive7()) {
         this.worldObj.applyEntityByEntity(this, text, factor5, factor6);
      }
   }

   public boolean matchesEntity(Entity entity2) {
      return this == entity2;
   }

   public boolean canExecute() {
      return !this.isDead;
   }

   public void applyEntity2(Entity entity2) {
      this.commandStatsManager.applyCommandStatsManager(entity2.getCommandStatsManager());
   }

   public boolean matchesItemStack(int count11, ItemStack stack) {
      return false;
   }

   public float getDistanceToEntity(Entity entity2) {
      Vec3 vec32 = this.getVec35(1.0F);
      float height2 = entity2.getHeight();
      AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height2, (double)height2, (double)height2);
      double doubleItem = MathHelper.getDouble7(vec32.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec32.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec32.zCoord, boundingBox.minZ, boundingBox.maxZ);
      Vec3 vec = new Vec3(doubleItem, doubleItem2, doubleItem3);
      return (float)new Vec3(this.posX, this.posY + (double)this.getHeight2(), this.posZ).getCoordinate4(vec);
   }

   public EntityItem resolveEntityItem(ItemStack stack, float factor5) {
      if (stack.animationsToGo != 0 && stack.getItem() != null) {
         EntityItem entityItem = new EntityItem(this.worldObj, this.posX, this.posY + (double)factor5, this.posZ, stack);
         entityItem.updateState6();
         this.worldObj.matchesEntity(entityItem);
         return entityItem;
      } else {
         return null;
      }
   }

   public void applyItemStack(int count11, ItemStack stack) {
   }

   public void applyCrashReportCategory(CrashReportCategory crashReportCategory) {
      crashReportCategory.applyText("Entity Type", new FovEstimator(this));
      crashReportCategory.add("Entity ID", this.entityId);
      crashReportCategory.applyText("Entity Name", new SplineRanker(this));
      crashReportCategory.add("Entity's Exact location", String.format("%.2f, %.2f, %.2f", this.posX, this.posY, this.posZ));
      crashReportCategory.add(
         "Entity's Block location",
         CrashReportCategory.resolveText(
            (double)MathHelper.ceiling_double_int(this.posX),
            (double)MathHelper.ceiling_double_int(this.posY),
            (double)MathHelper.ceiling_double_int(this.posZ)
         )
      );
      crashReportCategory.add("Entity's Momentum", String.format("%.2f, %.2f, %.2f", this.motionX, this.motionY, this.motionZ));
      crashReportCategory.applyText("Entity's Rider", new InterpolationAccumulator(this));
      crashReportCategory.applyText("Entity's Vehicle", new FovConverter(this));
   }

   public void applyFloat(float factor5, float factor6) {
      if (factor5 != this.width || factor6 != this.height) {
         float width2 = this.width;
         this.width = factor5;
         this.height = factor6;
         this.setBoundingBox2(
            new AxisAlignedBB(
               this.getBoundingBox2().minX,
               this.getBoundingBox2().minY,
               this.getBoundingBox2().minZ,
               this.getBoundingBox2().minX + (double)this.width,
               this.getBoundingBox2().minY + (double)this.height,
               this.getBoundingBox2().minZ + (double)this.width
            )
         );
         if (this.width > width2 && !this.enabled2 && !this.worldObj.state) {
            this.applyDouble((double)(width2 - this.width), 0.0, (double)(width2 - this.width));
         }
      }
   }

   public EnumFacing getEnumFacing2() {
      return EnumFacing.resolveEnumFacing(MathHelper.ceiling_double_int((double)(this.rotationYaw * 4.0F / 360.0F) + 0.5) & 3);
   }

   public void applyDouble2(double posX2, double posY2, double posZ2) {
      this.motionX = posX2;
      this.motionY = posY2;
      this.motionZ = posZ2;
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public IChatComponent getChatComponent() {
      ChatComponentText chatComponentText = new ChatComponentText(this.getText());
      chatComponentText.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
      chatComponentText.getStyleHasParentManager().withLabelText(this.getEntityUniqueID().toString());
      return chatComponentText;
   }

   public Vec3 getPositionVec2() {
      return this.positionVec2;
   }

   public int getCount3() {
      return 3;
   }

   public double getValue() {
      return 0.0;
   }

   public double getCoordinate(BlockPos blockPos2) {
      return blockPos2.computeValueByDouble(this.posX, this.posY, this.posZ);
   }

   public void updateState23() {
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      this.applyIndex(5, enabledByFlag);
   }

   public Vec3 getVec33(float posX2, float posY2, float posZ2) {
      if (posX2 != 1.0F && posX2 != 2.0F) {
         float factor5 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * posX2;
         float factor6 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * posX2;
         return this.getVec34(factor5, factor6);
      } else {
         return this.getVec34(posZ2, posY2);
      }
   }

   public double getCoordinate(Vec3 vec) {
      double offset = this.posX - vec.xCoord;
      double offset2 = this.posY - vec.yCoord;
      double offset3 = this.posZ - vec.zCoord;
      return (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2 + offset3 * offset3);
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   public boolean isActive4() {
      return this.canExecute2();
   }

   public void processFloat2(float factor5, float factor6) {
      if (this.entity != null) {
         this.entity.processFloat2(factor5, factor6);
      }
   }

   public void setScaleFactor8(float scaleFactor) {
   }

   public void applyDoubleByDouble(double posX2, double posY2, double posZ2) {
      this.applyDoubleByDouble(posX2, posY2, posZ2, this.rotationYaw, this.rotationPitch);
   }

   public boolean checkDoubleByDouble(double posX2, double posY2, double posZ2) {
      AxisAlignedBB boundingBox = this.getBoundingBox2().expand(posX2, posY2, posZ2);
      return this.matchesAxisAlignedBB(boundingBox);
   }

   public double getDouble3(double posX2, double posY2, double posZ2) {
      double offset = this.posX - posX2;
      double offset2 = this.posY - posY2;
      double offset3 = this.posZ - posZ2;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public void updateState14() {
      this.isInWeb = true;
      this.fallDistance = 0.0F;
      this.factor2 = 0.0F;
   }

   public float getDistanceSqToEntity(Entity entity2) {
      float factor5 = (float)(this.posX - entity2.posX);
      float factor6 = (float)(this.posY - entity2.posY);
      float factor7 = (float)(this.posZ - entity2.posZ);
      return MathHelper.sin(factor5 * factor5 + factor6 * factor6 + factor7 * factor7);
   }

   public void applyIndex(int count11) {
      if (!this.state4) {
         this.matchesDamageSource(DamageSource.damageSource8, (float)count11);
      }
   }

   public void applyEntityPlayer2(EntityPlayer player) {
   }

   public String getText2() {
      return this.dataWatcher.resolveText(2);
   }

   public float getScaleFactor12() {
      return 0.0F;
   }

   public AxisAlignedBB resolveAxisAlignedBB(Entity entity2) {
      return null;
   }

   public boolean matchesEntityPlayer(EntityPlayer player, Vec3 vec) {
      return false;
   }

   public boolean matchesEntityPlayer4(EntityPlayer player) {
      return !player.isActive23() && this.isActive12();
   }

   public String getText7() {
      return "game.neutral.swim.splash";
   }

   @Override
   public World getWorldObj() {
      return this.worldObj;
   }

   public void setLocationAndAngles(double posX2, double posY2, double posZ2, float factor5, float factor6) {
      if (this instanceof EntityPlayerSP && ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         double doubleItem = MathHelper.getDouble7(posX2, -3.0E7, 3.0E7);
         double doubleItem2 = MathHelper.getDouble7(posZ2, -3.0E7, 3.0E7);
         this.prevPosX = doubleItem;
         this.prevPosY = posY2;
         this.prevPosZ = doubleItem2;
         this.processDouble2(posX2, posY2, posZ2);
         this.rotationYaw = factor5 % 360.0F;
         this.rotationPitch = MathHelper.clamp_float(factor6, -90.0F, 90.0F) % 360.0F;
         this.prevRotationYaw = factor5;
         this.prevRotationPitch = factor6;
      } else {
         BacktrackRecord backtrackRecord2 = this.backtrackRecord;
         Vec3 vec = new Vec3(posX2, posY2, posZ2);
         backtrackRecord2.applyObject(vec);
         this.prevPosX = this.posX = posX2;
         this.prevPosY = this.posY = posY2;
         this.prevPosZ = this.posZ = posZ2;
         this.prevRotationYaw = this.rotationYaw = factor5;
         this.prevRotationPitch = this.rotationPitch = factor6;
         double offset = (double)(this.prevRotationYaw - factor5);
         if (offset < -180.0) {
            this.prevRotationYaw += 360.0F;
         }

         if (offset >= 180.0) {
            this.prevRotationYaw -= 360.0F;
         }

         this.processDouble2(this.posX, this.posY, this.posZ);
         this.processFloat4(factor5, factor6);
      }
   }

   public void setState3(boolean state2) {
      this.applyIndex(3, state2);
   }

   public boolean isActive9() {
      return false;
   }

   public boolean checkDouble2(double posX2, double posY2, double posZ2) {
      double offset = this.posX - posX2;
      double offset2 = this.posY - posY2;
      double offset3 = this.posZ - posZ2;
      double offset4 = offset * offset + offset2 * offset2 + offset3 * offset3;
      return this.checkDouble(offset4);
   }

   public void setWorldObj(World worldObj2) {
      this.worldObj = worldObj2;
   }

   public ItemStack[] getItemStackArray2() {
      return null;
   }

   public void processByte(byte byteValue) {
   }

   public void applyBlockPos(BlockPos blockPos2, float factor5, float factor6) {
      this.applyDoubleByDouble((double)blockPos2.getX() + 0.5, (double)blockPos2.getY(), (double)blockPos2.getZ() + 0.5, factor5, factor6);
   }

   public void applyEntity(Entity entity2) {
      if (entity2.entity != this && entity2.ridingEntity != this && !entity2.noClip && !this.noClip) {
         double offset = entity2.posX - this.posX;
         double offset2 = entity2.posZ - this.posZ;
         double offset3 = MathHelper.computeValue(offset, offset2);
         if (offset3 >= 0.01F) {
            double offset4 = (double)MathHelper.sqrt_double(offset3);
            double offset5 = offset / offset4;
            double offset6 = offset2 / offset4;
            double offset7 = 1.0 / offset4;
            if (offset7 > 1.0) {
               offset7 = 1.0;
            }

            double offset8 = offset5 * offset7;
            double offset9 = offset6 * offset7;
            double offset10 = offset8 * 0.05F;
            double offset11 = offset9 * 0.05F;
            double offset12 = offset10 * (double)(1.0F - this.factor3);
            double offset13 = offset11 * (double)(1.0F - this.factor3);
            if (this.entity == null) {
               this.applyDouble3(-offset12, 0.0, -offset13);
            }

            if (entity2.entity == null) {
               entity2.applyDouble3(offset12, 0.0, offset13);
            }
         }
      }
   }

   public int getEntityId() {
      return this.entityId;
   }

   public void applyNBTTagCompound4(NBTTagCompound tagCompound) {
   }

   public double getDouble4(double posX2, double posY2, double posZ2) {
      double offset = this.posX - posX2;
      double offset2 = this.posY - posY2;
      double offset3 = this.posZ - posZ2;
      return (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2 + offset3 * offset3);
   }

   public float getScaleFactor3(Entity entity2) {
      float factor5 = (float)(this.posX - entity2.posX);
      float factor6 = (float)(this.posY - entity2.posY);
      float factor7 = (float)(this.posZ - entity2.posZ);
      return factor5 * factor5 + factor6 * factor6 + factor7 * factor7;
   }

   public final Vec3 getVec34(float factor5, float factor6) {
      float factor7 = MathHelper.cos(-factor6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor8 = MathHelper.abs(-factor6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor9 = -MathHelper.cos(-factor5 * (float) (Math.PI / 180.0));
      float factor10 = MathHelper.abs(-factor5 * (float) (Math.PI / 180.0));
      return new Vec3((double)(factor8 * factor9), (double)factor10, (double)(factor7 * factor9));
   }

   public void updateState20() {
      int count11 = MathHelper.ceiling_double_int(this.posX);
      int count12 = MathHelper.ceiling_double_int(this.posY - 0.2F);
      int count13 = MathHelper.ceiling_double_int(this.posZ);
      BlockPos blockPos2 = new BlockPos(count11, count12, count13);
      IBlockState blockState = this.worldObj.getBlockState(blockPos2);
      if (blockState.getBlock().processAction13() != -1) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.BLOCK_CRACK,
               this.posX + ((double)this.random.nextFloat() - 0.5) * (double)this.width,
               this.getBoundingBox2().minY + 0.1,
               this.posZ + ((double)this.random.nextFloat() - 0.5) * (double)this.width,
               -this.motionX * 4.0,
               1.5,
               -this.motionZ * 4.0,
               new int[]{Block.processAction5(blockState)}
            );
      }
   }

   public Vec3 getVec35(float factor5) {
      if (factor5 == 1.0F) {
         return new Vec3(this.posX, this.posY + (double)this.getHeight2(), this.posZ);
      } else {
         double offset = this.prevPosX + (this.posX - this.prevPosX) * (double)factor5;
         double offset2 = this.prevPosY + (this.posY - this.prevPosY) * (double)factor5 + (double)this.getHeight2();
         double offset3 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)factor5;
         return new Vec3(offset, offset2, offset3);
      }
   }

   public float computeFactor(float factor5) {
      int count11 = MathHelper.ceiling_double_int(this.posX);
      int count12 = MathHelper.ceiling_double_int(this.posY + (double)this.getHeight2());
      int count13 = MathHelper.ceiling_double_int(this.posZ);
      return this.worldObj.checkIndex(count11, count12, count13) ? this.worldObj.computeFactor(count11, count12, count13) : 0.0F;
   }

   public void applyIndex(int count11, boolean flag) {
      byte byteValue = this.dataWatcher.computeByteCode(0);
      if (flag) {
         this.dataWatcher.applyTargetByTarget(0, (byte)(byteValue | 1 << count11));
      } else {
         this.dataWatcher.applyTargetByTarget(0, (byte)(byteValue & ~(1 << count11)));
      }
   }

   public void applyFloatByFloat(float factor5, float factor6) {
      if (this instanceof EntityPlayerSP) {
         Rotation rotation = ClientGlobals.rotationManager.resolveRotation(factor5, factor6);
         ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
         float scaleFactor = viaProtocolHelper.scaleFactor2;
         float scaleFactor2 = viaProtocolHelper.scaleFactor1;
         viaProtocolHelper.scaleFactor1 = (float)((double)viaProtocolHelper.scaleFactor1 + (double)factor5 * 0.15);
         viaProtocolHelper.scaleFactor2 = (float)((double)viaProtocolHelper.scaleFactor2 - (double)factor6 * 0.15);
         viaProtocolHelper.scaleFactor2 = MathHelper.clamp_float(viaProtocolHelper.scaleFactor2, -90.0F, 90.0F);
         viaProtocolHelper.scaleFactor = viaProtocolHelper.scaleFactor + (viaProtocolHelper.scaleFactor2 - scaleFactor);
         viaProtocolHelper.scaleFactor3 = viaProtocolHelper.scaleFactor3 + (viaProtocolHelper.scaleFactor1 - scaleFactor2);
         float yaw = rotation.yaw;
         float scaleFactor3 = viaProtocolHelper.scaleFactor3;
         float factor7 = viaProtocolHelper.computeFactorByFloat(scaleFactor3, yaw);
         this.rotationYaw = rotation.yaw;
         this.rotationPitch = rotation.pitch;
         this.prevRotationPitch = viaProtocolHelper.scaleFactor;
         this.prevRotationYaw = factor7;
      } else {
         float pitch = this.rotationPitch;
         float yaw2 = this.rotationYaw;
         this.rotationYaw = (float)((double)this.rotationYaw + (double)factor5 * 0.15);
         this.rotationPitch = (float)((double)this.rotationPitch - (double)factor6 * 0.15);
         this.rotationPitch = MathHelper.clamp_float(this.rotationPitch, -90.0F, 90.0F);
         this.prevRotationPitch = this.prevRotationPitch + (this.rotationPitch - pitch);
         this.prevRotationYaw = this.prevRotationYaw + (this.rotationYaw - yaw2);
      }
   }

   public final String getText8() {
      return SkippingEntityWithIdManager.resolveTextByEntity(this);
   }

   public void setEnabled4(boolean enabled11) {
      this.applyIndex(4, enabled11);
   }

   public void setEntityId(int entityId2) {
      this.entityId = entityId2;
   }

   public boolean canExecute2() {
      boolean flag = this.worldObj != null && this.worldObj.state;
      return !this.state4 && (this.count9 > 0 || flag && this.checkIndex(0));
   }

   public void processFloat4(float factor5, float factor6) {
      this.rotationYaw = factor5 % 360.0F;
      this.rotationPitch = factor6 % 360.0F;
      if (this == Minecraft.getMinecraft().thePlayer) {
         AttackRangeRenderer.applyDouble2((double)factor5, (double)factor6);
      }
   }

   public void applyIndex2(int count11) {
      if (!this.worldObj.state && !this.isDead) {
         this.worldObj.uNKNOWNManager.applyTextByText("changeDimension");
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         int count12 = this.count4;
         WorldServer worldServer = vanillaRunnable.resolveWorldServer(count12);
         WorldServer worldServer2 = vanillaRunnable.resolveWorldServer(count11);
         this.count4 = count11;
         if (count12 == 1 && count11 == 1) {
            worldServer2 = vanillaRunnable.resolveWorldServer(0);
            this.count4 = 0;
         }

         this.worldObj.spawnEntityInWorld(this);
         this.isDead = false;
         this.worldObj.uNKNOWNManager.applyTextByText("reposition");
         vanillaRunnable.getMovingManager().applyEntity(this, count12, worldServer, worldServer2);
         this.worldObj.uNKNOWNManager.applyText("reloading");
         Entity entity2 = SkippingEntityWithIdManager.resolveEntityByText(SkippingEntityWithIdManager.resolveTextByEntity(this), worldServer2);
         if (entity2 != null) {
            entity2.applyEntity7(this);
            if (count12 == 1 && count11 == 1) {
               BlockPos blockPos2 = this.worldObj.resolveBlockPos(worldServer2.getBlockPos());
               entity2.applyBlockPos(blockPos2, entity2.rotationYaw, entity2.rotationPitch);
            }

            worldServer2.matchesEntity(entity2);
         }

         this.isDead = true;
         this.worldObj.uNKNOWNManager.updateState();
         worldServer.updateState20();
         worldServer2.updateState20();
         this.worldObj.uNKNOWNManager.updateState();
      }
   }

   public int getCount2() {
      return 300;
   }

   public void setState1(boolean state2) {
      this.state1 = state2;
   }

   public AxisAlignedBB getBoundingBox2() {
      return this.boundingBox2;
   }

   public float computeFactor(BlockBreakState blockBreakState, World world, BlockPos blockPos2, IBlockState blockState) {
      return blockState.getBlock().processAction(this);
   }

   public void setLabelText(String labelText) {
      this.dataWatcher.applyTargetByTarget(2, labelText);
   }

   public boolean matchesNBTTagCompound(NBTTagCompound tagCompound) {
      String text = this.getText8();
      if (!this.isDead && text != null && this.entity == null) {
         tagCompound.put("id", text);
         this.applyNBTTagCompound3(tagCompound);
         return true;
      } else {
         return false;
      }
   }

   public void updateState26() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
   }

   public void applyHUIHRVManager(HUIHRVManager hUIHRVManager) {
      this.matchesDamageSource(DamageSource.damageSource15, 5.0F);
      this.count9++;
      if (this.count9 == 0) {
         this.applyIndexByIndex(8);
      }
   }

   public void applyEntity7(Entity entity2) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      entity2.applyNBTTagCompound3(tagCompound);
      this.applyNBTTagCompound2(tagCompound);
      this.count10 = entity2.count10;
      this.blockPos = entity2.blockPos;
      this.positionVec2 = entity2.positionVec2;
      this.enumFacing = entity2.enumFacing;
   }

   public boolean matchesEntityByEntity(Entity entity2) {
      return false;
   }

   public void applyDouble(double posX2, double posY2, double posZ2) {
      double offset = posX2;
      double offset2 = posY2;
      double offset3 = posZ2;
      if (this.noClip) {
         this.setBoundingBox2(this.getBoundingBox2().expand(posX2, posY2, posZ2));
         this.updateState24();
      } else {
         this.worldObj.uNKNOWNManager.applyTextByText("move");
         double posX3 = this.posX;
         double posY3 = this.posY;
         double posZ3 = this.posZ;
         if (this.isInWeb) {
            this.isInWeb = false;
            AuxiliaryAbstractEventAdapter event = SmoothSynchronizer.resolveAuxiliaryAbstractEventAdapte(0.25, 0.05F, false, false);
            offset = posX2 * event.getValue2();
            offset2 = posY2 * event.getValue();
            offset3 = posZ2 * event.getValue2();
            if (!event.isEnabled()) {
               this.motionX = 0.0;
               this.motionZ = 0.0;
            }

            if (!event.isEnabled2()) {
               this.motionY = 0.0;
            }
         }

         double offset4 = offset;
         double offset5 = offset2;
         double offset6 = offset3;
         boolean sprinting = this.isSprinting();
         boolean flag = false;
         if (this instanceof EntityPlayerSP) {
            EventSprint event2 = SprintListener.resolveEventSprint(StateTypes.PRE, false);
            if (event2.isEnabled()) {
               sprinting = false;
            }

            flag = event2.isEnabled2();
         }

         double offset7 = offset;
         double offset8 = offset3;
         boolean flag2 = (this.onGround && sprinting || flag) && this instanceof EntityPlayer;
         if (flag2) {
            double offset9;
            for (offset9 = 0.05;
               offset != 0.0 && this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox2().expand(offset, -1.0, 0.0)).isEmpty();
               offset4 = offset
            ) {
               if (offset < offset9 && offset >= -offset9) {
                  offset = 0.0;
               } else if (offset > 0.0) {
                  offset -= offset9;
               } else {
                  offset += offset9;
               }
            }

            for (; offset3 != 0.0 && this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox2().expand(0.0, -1.0, offset3)).isEmpty(); offset6 = offset3) {
               if (offset3 < offset9 && offset3 >= -offset9) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset9;
               } else {
                  offset3 += offset9;
               }
            }

            for (;
               offset != 0.0 && offset3 != 0.0 && this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox2().expand(offset, -1.0, offset3)).isEmpty();
               offset6 = offset3
            ) {
               if (offset < offset9 && offset >= -offset9) {
                  offset = 0.0;
               } else if (offset > 0.0) {
                  offset -= offset9;
               } else {
                  offset += offset9;
               }

               offset4 = offset;
               if (offset3 < offset9 && offset3 >= -offset9) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset9;
               } else {
                  offset3 += offset9;
               }
            }
         }

         if (this instanceof EntityPlayerSP) {
            boolean flag3 = offset7 != offset || offset8 != offset3;
            SprintListener.resolveEventSprint(StateTypes.POST, flag3);
         }

         List collidingBoundingBoxes = this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox2().contract(offset, offset2, offset3));
         AxisAlignedBB boundingBox = this.getBoundingBox2();
         AxisAlignedBB boundingBox4 = null;
         double offset10 = offset2;

         for (AxisAlignedBB boundingBox5 : (Iterable<AxisAlignedBB>)(Iterable<?>)collidingBoundingBoxes) {
            offset2 = boundingBox5.getCoordinate3(this.getBoundingBox2(), offset2);
            if (offset2 != offset10) {
               boundingBox4 = boundingBox5;
            }

            offset10 = offset2;
         }

         this.boundingBox3 = boundingBox4;
         this.setBoundingBox2(this.getBoundingBox2().expand(0.0, offset2, 0.0));
         boolean flag4 = this.onGround || offset5 != offset2 && offset5 < 0.0;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset = ((AxisAlignedBB)iterator.next()).getCoordinate2(this.getBoundingBox2(), offset);
         }

         this.setBoundingBox2(this.getBoundingBox2().expand(offset, 0.0, 0.0));
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator2.next()).getCoordinate(this.getBoundingBox2(), offset3);
         }

         this.setBoundingBox2(this.getBoundingBox2().expand(0.0, 0.0, offset3));
         float stepHeight2 = this.stepHeight;
         if (this instanceof EntityPlayerSP) {
            stepHeight2 = EventTickStateCallback.getEventTickState(this.stepHeight, StateTypes.PRE).getScaleFactor();
         }

         if (stepHeight2 > 0.0F && flag4 && (offset4 != offset || offset6 != offset3)) {
            double offset11 = offset;
            double offset12 = offset2;
            double offset13 = offset3;
            AxisAlignedBB boundingBox6 = this.getBoundingBox2();
            this.setBoundingBox2(boundingBox);
            offset2 = (double)stepHeight2;
            List collidingBoundingBoxes2 = this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox2().contract(offset4, offset2, offset6));
            AxisAlignedBB boundingBox7 = this.getBoundingBox2();
            AxisAlignedBB boundingBox8 = boundingBox7.contract(offset4, 0.0, offset6);
            double offset14 = offset2;
            Iterator iterator3 = collidingBoundingBoxes2.iterator();

            while (iterator3.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator3.next()).getCoordinate3(boundingBox8, offset14);
            }

            AxisAlignedBB boundingBox9 = boundingBox7.expand(0.0, offset14, 0.0);
            double offset15 = offset4;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset15 = ((AxisAlignedBB)iterator4.next()).getCoordinate2(boundingBox9, offset15);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(offset15, 0.0, 0.0);
            double offset16 = offset6;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset16 = ((AxisAlignedBB)iterator5.next()).getCoordinate(boundingBox10, offset16);
            }

            AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, 0.0, offset16);
            AxisAlignedBB boundingBox12 = this.getBoundingBox2();
            double offset17 = offset2;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset17 = ((AxisAlignedBB)iterator6.next()).getCoordinate3(boundingBox12, offset17);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, offset17, 0.0);
            double offset18 = offset4;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               offset18 = ((AxisAlignedBB)iterator7.next()).getCoordinate2(boundingBox13, offset18);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(offset18, 0.0, 0.0);
            double offset19 = offset6;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset19 = ((AxisAlignedBB)iterator8.next()).getCoordinate(boundingBox14, offset19);
            }

            AxisAlignedBB boundingBox15 = boundingBox14.expand(0.0, 0.0, offset19);
            double offset20 = offset15 * offset15 + offset16 * offset16;
            double offset21 = offset18 * offset18 + offset19 * offset19;
            if (offset20 > offset21) {
               offset = offset15;
               offset3 = offset16;
               offset2 = -offset14;
               this.setBoundingBox2(boundingBox11);
            } else {
               offset = offset18;
               offset3 = offset19;
               offset2 = -offset17;
               this.setBoundingBox2(boundingBox15);
            }

            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset2 = ((AxisAlignedBB)iterator9.next()).getCoordinate3(this.getBoundingBox2(), offset2);
            }

            this.setBoundingBox2(this.getBoundingBox2().expand(0.0, offset2, 0.0));
            if (offset11 * offset11 + offset13 * offset13 >= offset * offset + offset3 * offset3) {
               offset = offset11;
               offset2 = offset12;
               offset3 = offset13;
               this.setBoundingBox2(boundingBox6);
            }

            if (this instanceof EntityPlayerSP) {
               EventTickStateCallback.applyStateTypes(StateTypes.POST);
            }
         }

         this.worldObj.uNKNOWNManager.updateState();
         this.worldObj.uNKNOWNManager.applyTextByText("rest");
         this.updateState24();
         this.isCollidedHorizontally = offset4 != offset || offset6 != offset3;
         this.isCollidedVertically = offset5 != offset2;
         this.onGround = this.isCollidedVertically && offset5 < 0.0;
         this.enabled5 = this.isCollidedHorizontally || this.isCollidedVertically;
         int count11 = MathHelper.ceiling_double_int(this.posX);
         int count12 = MathHelper.ceiling_double_int(this.posY - 0.2F);
         int count13 = MathHelper.ceiling_double_int(this.posZ);
         BlockPos blockPos2 = new BlockPos(count11, count12, count13);
         Block block = this.worldObj.getBlockState(blockPos2).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = this.worldObj.getBlockState(blockPos2.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos2 = blockPos2.down();
            }
         }

         this.applyBlock(offset2, this.onGround, block, blockPos2);
         if (offset4 != offset) {
            this.motionX = 0.0;
         }

         if (offset6 != offset3) {
            this.motionZ = 0.0;
         }

         if (offset5 != offset2) {
            block.processAction(this.worldObj, this);
         }

         if (this.isActiveAsFlag() && !flag2 && this.ridingEntity == null) {
            double offset22 = this.posX - posX3;
            double offset23 = this.posY - posY3;
            double offset24 = this.posZ - posZ3;
            if (block != Blocks.block81) {
               offset23 = 0.0;
            }

            if (block != null && this.onGround) {
               block.processAction(this.worldObj, blockPos2, this);
            }

            this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double(offset22 * offset22 + offset24 * offset24) * 0.6);
            this.factor = (float)((double)this.factor + (double)MathHelper.sqrt_double(offset22 * offset22 + offset23 * offset23 + offset24 * offset24) * 0.6);
            if (this.factor > (float)this.count5 && block.getMaterial() != Material.coreItemEntry) {
               this.count5 = (int)this.factor + 1;
               if (this.isInWater()) {
                  float factor5 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.2F + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.2F)
                     * 0.35F;
                  if (factor5 > 1.0F) {
                     factor5 = 1.0F;
                  }

                  this.applyText(this.getText6(), factor5, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
               }

               this.applyBlockPos(blockPos2, block);
            }
         }

         try {
            this.updateState13();
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Checking entity block collision");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being checked for collision");
            this.applyCrashReportCategory(crashReportCategory);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }

         boolean active = this.isActive11();
         if (this.worldObj.matchesAxisAlignedBB2(this.getBoundingBox2().resolveAxisAlignedBB(0.001, 0.001, 0.001))) {
            this.applyIndex(1);
            if (!active) {
               this.count9++;
               if (this.count9 == 0) {
                  this.applyIndexByIndex(8);
               }
            }
         } else if (this.count9 <= 0) {
            this.count9 = -this.count3;
         }

         if (active && this.count9 > 0) {
            this.applyText("random.fizz", 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
            this.count9 = -this.count3;
         }

         this.worldObj.uNKNOWNManager.updateState();
      }
   }

   public void applyFloat(float posX2, float posY2, float posZ2) {
      float factor5 = posX2 * posX2 + posY2 * posY2;
      if (factor5 >= 1.0E-4F) {
         float factor6 = MathHelper.sin(factor5);
         if (factor6 < 1.0F) {
            factor6 = 1.0F;
         }

         float factor7 = posZ2 / factor6;
         float factor8 = posX2 * factor7;
         float factor9 = posY2 * factor7;
         float factor10 = MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F);
         float factor11 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
         this.motionX += (double)(factor8 * factor11 - factor9 * factor10);
         this.motionZ += (double)(factor9 * factor11 + factor8 * factor10);
      }
   }

   public abstract void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound);

   public void updateState15() {
      this.updateState9();
   }

   public void applyIndexByIndex(int count11) {
      int count12 = count11 * 20;
      int count13 = WMAZOA32EnchantmentManager.computeCount(this, count12);
      if (this.count9 < count13) {
         this.count9 = count13;
      }
   }

   public void setEnabled2(boolean enabled11) {
      this.applyIndex(1, enabled11);
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
   }

   public AxisAlignedBB getAxisAlignedBB() {
      return null;
   }

   public void applyDouble(double posX2, double posY2, double posZ2, float factor5, float factor6, int count11, boolean flag) {
      this.processDouble2(posX2, posY2, posZ2);
      this.processFloat4(factor5, factor6);
      List list = this.worldObj.getList2(this, this.getBoundingBox2().resolveAxisAlignedBB(0.03125, 0.0, 0.03125));
      if (!list.isEmpty()) {
         double offset = 0.0;

         for (AxisAlignedBB boundingBox : (Iterable<AxisAlignedBB>)(Iterable<?>)list) {
            if (boundingBox.maxY > offset) {
               offset = boundingBox.maxY;
            }
         }

         double offset2 = posY2 + (offset - this.getBoundingBox2().minY);
         this.processDouble2(posX2, offset2, posZ2);
      }
   }

   public void applyDouble3(double posX2, double posY2, double posZ2) {
      this.motionX += posX2;
      this.motionY += posY2;
      this.motionZ += posZ2;
      this.enabled4 = true;
   }

   public Vec3 getVec36(float factor5) {
      if (factor5 == 1.0F) {
         return this.getVec34(this.rotationPitch, this.rotationYaw);
      } else {
         float factor6 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * factor5;
         float factor7 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * factor5;
         return this.getVec34(factor6, factor7);
      }
   }

   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos2) {
      if (flag) {
         if (this.fallDistance > 0.0F) {
            if (block != null) {
               block.processAction(this.worldObj, blockPos2, this, this.fallDistance);
            } else {
               this.processFloat2(this.fallDistance, 1.0F);
            }

            this.fallDistance = 0.0F;
         }
      } else if (offset < 0.0) {
         this.fallDistance = (float)((double)this.fallDistance - offset);
      }

      if (flag) {
         if (this.factor2 > 0.0F) {
            this.factor2 = 0.0F;
         }
      } else if (offset < 0.0) {
         this.factor2 = (float)((double)this.factor2 - offset);
      }
   }

   public EntityItem resolveEntityItem(Item item, int count11) {
      return this.resolveEntityItem(item, count11, 0.0F);
   }

   public EntityItem resolveEntityItem(Item item, int count11, float factor5) {
      return this.resolveEntityItem(new ItemStack(item, count11, 0), factor5);
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.posX, this.posY + 0.5, this.posZ);
   }

   public boolean matchesDamageSource(DamageSource damageSource, float factor5) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.updateState8();
         return false;
      }
   }

   public String getText5() {
      return this.getText() + ClientSettingsBlackType.RESET;
   }

   public boolean matchesDamageSource(DamageSource damageSource) {
      return this.enabled6 && damageSource != DamageSource.damageSource10 && !damageSource.isActive();
   }

   public void setEnabled(boolean enabled11) {
      this.dataWatcher.applyTargetByTarget(3, (byte)(enabled11 ? 1 : 0));
   }

   public boolean matchesMaterial(Material material) {
      double offset = this.posY + (double)this.getHeight2();
      int count11 = MathHelper.ceiling_double_int(this.posX);
      int count12 = MathHelper.ceiling_double_int(offset);
      int count13 = MathHelper.ceiling_double_int(this.posZ);
      IBlockState iBlockState = this.worldObj.getIBlockState(count11, count12, count13);
      if (iBlockState.getBlock().getMaterial() == material) {
         float factor5 = BlockLiquid.computeFactor(iBlockState.getBlock().getIndex(iBlockState)) - 0.11111111F;
         float factor6 = (float)(count12 + 1) - factor5;
         boolean flag = offset < (double)factor6;
         return !flag && this instanceof EntityPlayer ? false : flag;
      } else {
         return false;
      }
   }

   private boolean matchesAxisAlignedBB(AxisAlignedBB boundingBox) {
      return this.worldObj.getList2(this, boundingBox).isEmpty() && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(boundingBox);
   }

   public void applyDoubleByDouble(double posX2, double posY2, double posZ2, float factor5, float factor6) {
      BacktrackRecord backtrackRecord2 = this.backtrackRecord;
      Vec3 vec = new Vec3(posX2, posY2, posZ2);
      backtrackRecord2.applyObject(vec);
      this.lastTickPosX = this.prevPosX = this.posX = posX2;
      this.lastTickPosY = this.prevPosY = this.posY = posY2;
      this.lastTickPosZ = this.prevPosZ = this.posZ = posZ2;
      this.rotationYaw = factor5;
      this.rotationPitch = factor6;
      this.processDouble2(this.posX, this.posY, this.posZ);
   }

   @Override
   public Entity getEntity() {
      return this;
   }

   public void applyEntity5(Entity entity2) {
      this.applyDoubleByDouble(entity2.posX, entity2.posY, entity2.posZ, entity2.rotationYaw, entity2.rotationPitch);
   }

   public Vec3 getVecAsVec3() {
      return null;
   }

   public double getCoordinate(Entity entity2) {
      double offset = this.posX - entity2.posX;
      double offset2 = this.posY - entity2.posY;
      double offset3 = this.posZ - entity2.posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public HoverEventActionManager getHoverEventActionManager() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      String text = SkippingEntityWithIdManager.resolveTextByEntity(this);
      tagCompound.put("id", this.getEntityUniqueID().toString());
      if (text != null) {
         tagCompound.put("type", text);
      }

      tagCompound.put("name", this.getText());
      return new HoverEventActionManager(ClientSettingsShowTextType.SHOW_ENTITY, new ChatComponentText(tagCompound.toString()));
   }

   public boolean isWet() {
      return this.worldObj
         .matchesMaterial(
            this.getBoundingBox2().minX + 0.1F,
            this.getBoundingBox2().minY + 0.4F,
            this.getBoundingBox2().minZ + 0.1F,
            this.getBoundingBox2().maxX - 0.1F,
            this.getBoundingBox2().maxY - 0.4F,
            this.getBoundingBox2().maxZ - 0.1F,
            Material.material29
         );
   }

   private void updateState24() {
      this.posX = (this.getBoundingBox2().minX + this.getBoundingBox2().maxX) / 2.0;
      this.posY = this.getBoundingBox2().minY;
      this.posZ = (this.getBoundingBox2().minZ + this.getBoundingBox2().maxZ) / 2.0;
      BacktrackRecord backtrackRecord2 = this.backtrackRecord;
      Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
      backtrackRecord2.applyObject(vec);
   }

   public boolean isState1() {
      return this.state1;
   }

   public void updateState25() {
      if (!this.state4) {
         this.matchesDamageSource(DamageSource.damageSource4, 4.0F);
         this.applyIndexByIndex(15);
      }
   }

   public boolean isActive3() {
      return true;
   }

   public boolean isActive6() {
      return this.dataWatcher.computeByteCode(3) == 1;
   }

   public boolean isActive5() {
      return false;
   }

   public void updateState22() {
      if (this.worldObj != null) {
         while (true) {
            if (this.posY > 0.0 && this.posY < 256.0) {
               this.processDouble2(this.posX, this.posY, this.posZ);
               if (!this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()) {
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

   public void updateState9() {
      this.isDead = true;
   }

   public final boolean isState4() {
      return this.state4;
   }

   public boolean isActive14() {
      return true;
   }

   public boolean isActive13() {
      return this.isActive6();
   }

   public void updateState13() {
      int count11 = MathHelper.ceiling_double_int(this.getBoundingBox2().minX + 0.001);
      int count12 = MathHelper.ceiling_double_int(this.getBoundingBox2().minY + 0.001);
      int count13 = MathHelper.ceiling_double_int(this.getBoundingBox2().minZ + 0.001);
      int count14 = MathHelper.ceiling_double_int(this.getBoundingBox2().maxX - 0.001);
      int count15 = MathHelper.ceiling_double_int(this.getBoundingBox2().maxY - 0.001);
      int count16 = MathHelper.ceiling_double_int(this.getBoundingBox2().maxZ - 0.001);
      if (this.worldObj.checkIndex(count11, count12, count13, count14, count15, count16, true)) {
         while (count11 <= count14) {
            for (int index = count12; index <= count15; index++) {
               for (int index2 = count13; index2 <= count16; index2++) {
                  BlockPos blockPos2 = new BlockPos(count11, index, index2);
                  IBlockState iBlockState = this.worldObj.getIBlockState(count11, index, index2);

                  try {
                     iBlockState.getBlock().onEntityCollidedWithBlock(this.worldObj, blockPos2, iBlockState, this);
                  } catch (Throwable throwable) {
                     CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Colliding entity with block");
                     CrashReportCategory.applyCrashReportCategory(crashReport.resolveCrashReportCategory("Block being collided with"), blockPos2, iBlockState);
                     throw new SecondaryRuntimeExceptionAdapter(crashReport);
                  }
               }
            }

            count11++;
         }
      }
   }

   public void updateState18() {
      if (this.isRiding() && !this.isInWater() && this.onGround) {
         this.updateState20();
      }
   }

   public void updateState11() {
      float factor5 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.2F + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.2F) * 0.2F;
      if (factor5 > 1.0F) {
         factor5 = 1.0F;
      }

      this.applyText(this.getText7(), factor5, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
      float factor6 = (float)MathHelper.ceiling_double_int(this.getBoundingBox2().minY);

      for (int index = 0; (float)index < 1.0F + this.width * 20.0F; index++) {
         float factor7 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
         float factor8 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.WATER_BUBBLE,
               this.posX + (double)factor7,
               (double)(factor6 + 1.0F),
               this.posZ + (double)factor8,
               this.motionX,
               this.motionY - (double)(this.random.nextFloat() * 0.2F),
               this.motionZ,
               new int[0]
            );
      }

      for (int index2 = 0; (float)index2 < 1.0F + this.width * 20.0F; index2++) {
         float factor9 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
         float factor10 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.WATER_SPLASH,
               this.posX + (double)factor9,
               (double)(factor6 + 1.0F),
               this.posZ + (double)factor10,
               this.motionX,
               this.motionY,
               this.motionZ,
               new int[0]
            );
      }
   }

   public boolean canExecute4() {
      return false;
   }

   public void updateState16() {
   }

   public boolean isInWater() {
      return this.inWater;
   }

   public void updateState12() {
      this.count9 = 0;
   }

   public boolean isActive7() {
      return this.dataWatcher.computeByteCode(4) == 1;
   }

   public boolean isSprinting() {
      return this.checkIndex(1);
   }

   public boolean isActive12() {
      return this.checkIndex(5);
   }

   public boolean isActive15() {
      if (!this.noClip && !((Module)Client.getClient().getModuleManager().freeCam).isToggled()) {
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

         for (int index = 0; index < 8; index++) {
            int count11 = MathHelper.ceiling_double_int(this.posY + (double)(((float)((index >> 0) % 2) - 0.5F) * 0.1F) + (double)this.getHeight2());
            int count12 = MathHelper.ceiling_double_int(this.posX + (double)(((float)((index >> 1) % 2) - 0.5F) * this.width * 0.8F));
            int count13 = MathHelper.ceiling_double_int(this.posZ + (double)(((float)((index >> 2) % 2) - 0.5F) * this.width * 0.8F));
            if (auxiliaryBlockPosAdapter.getX() != count12 || auxiliaryBlockPosAdapter.getY() != count11 || auxiliaryBlockPosAdapter.getZ() != count13) {
               auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count12, count11, count13);
               if (this.worldObj.getBlockState(auxiliaryBlockPosAdapter).getBlock().isActive3()) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean canExecute6() {
      return this.checkIndex(4);
   }

   public boolean isSneaking() {
      return this.ridingEntity != null;
   }

   public boolean isActive2() {
      if (this.worldObj
         .matchesMaterialByMaterial(
            this.getBoundingBox2().minX + 0.001,
            this.getBoundingBox2().minY + 0.4F + 0.001,
            this.getBoundingBox2().minZ + 0.001,
            this.getBoundingBox2().maxX - 0.001,
            this.getBoundingBox2().maxY - 0.001 - 0.4F,
            this.getBoundingBox2().maxZ - 0.001,
            Material.coreItemEntry1,
            this
         )) {
         if (!this.inWater && !this.enabled2) {
            this.updateState11();
         }

         this.fallDistance = 0.0F;
         this.factor2 = 0.0F;
         this.inWater = true;
         this.count9 = 0;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   public void updateState10() {
      this.worldObj.uNKNOWNManager.applyTextByText("entityBaseTick");
      if (this.ridingEntity != null && this.ridingEntity.isDead) {
         this.ridingEntity = null;
      }

      this.factor4 = this.distanceWalkedModified;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      if (!this.worldObj.state && this.worldObj instanceof WorldServer) {
         this.worldObj.uNKNOWNManager.applyTextByText("portal");
         VanillaRunnable vanillaRunnable = ((WorldServer)this.worldObj).getVanillaRunnable();
         int countAsIndex = this.getCountAsIndex();
         if (this.enabled7) {
            if (vanillaRunnable.isActive12()) {
               if (this.ridingEntity == null && this.count++ >= countAsIndex) {
                  this.count = countAsIndex;
                  this.count10 = this.getCount2();
                  byte byteValue;
                  if (this.worldObj.secondaryFloatArrayEntry.getCountAsIndex() == -1) {
                     byteValue = 0;
                  } else {
                     byteValue = -1;
                  }

                  this.applyIndex2(byteValue);
               }

               this.enabled7 = false;
            }
         } else {
            if (this.count > 0) {
               this.count -= 4;
            }

            if (this.count < 0) {
               this.count = 0;
            }
         }

         if (this.count10 > 0) {
            this.count10--;
         }

         this.worldObj.uNKNOWNManager.updateState();
      }

      this.updateState18();
      this.isActive2();
      if (this.worldObj.state) {
         this.count9 = 0;
      } else if (this.count9 > 0) {
         if (this.state4) {
            this.count9 -= 4;
            if (this.count9 < 0) {
               this.count9 = 0;
            }
         } else {
            if (this.count9 % 20 == 0) {
               this.matchesDamageSource(DamageSource.damageSource9, 1.0F);
            }

            this.count9--;
         }
      }

      if (this.isWet()) {
         this.updateState25();
         this.fallDistance *= 0.5F;
         this.factor2 *= 0.5F;
      }

      if (this.posY < -64.0) {
         this.updateState19();
      }

      if (!this.worldObj.state) {
         this.applyIndex(0, this.count9 > 0);
      }

      this.enabled2 = false;
      this.worldObj.uNKNOWNManager.updateState();
   }

   public boolean isActive8() {
      return true;
   }

   public boolean isRiding() {
      return this.checkIndex(3);
   }

   public boolean isInWeb() {
      return this.isInWeb;
   }

   public void updateState() {
      this.updateState10();
   }

   public void updateState8() {
      this.enabled8 = true;
   }

   public boolean isActive11() {
      return this.inWater
         || this.worldObj.matchesBlockPos(new BlockPos(this.posX, this.posY, this.posZ))
         || this.worldObj.matchesBlockPos(new BlockPos(this.posX, this.posY + (double)this.height, this.posZ));
   }

   public abstract void updateState7();

   public boolean isState() {
      return this.state;
   }

   public boolean isActive10() {
      return false;
   }

   public Entity(World world) {
      this.vec3 = new Vec3((double)this.indexOffset2, (double)this.indexOffset3, (double)this.indexOffset);
      this.state1 = true;
      this.epochMillis = 0L;
      this.timestamp = 0L;
      this.entityId = count6++;
      this.value3 = 1.0;
      this.boundingBox2 = axisAlignedBB;
      this.width = 0.6F;
      this.height = 1.8F;
      this.count5 = 1;
      this.random = new Random();
      this.count3 = 1;
      this.enabled2 = true;
      this.entityUniqueID = MathHelper.processAction(this.random);
      this.commandStatsManager = new CommandStatsManager();
      this.worldObj = world;
      this.processDouble2(0.0, 0.0, 0.0);
      if (world != null) {
         this.count4 = world.secondaryFloatArrayEntry.getCountAsIndex();
      }

      this.dataWatcher = new DataWatcher(this);
      this.dataWatcher.applyTarget(0, (byte)0);
      this.dataWatcher.applyTarget(1, (short)300);
      this.dataWatcher.applyTarget(3, (byte)0);
      this.dataWatcher.applyTarget(2, "");
      this.dataWatcher.applyTarget(4, (byte)0);
      this.updateState7();
   }

   @Override
   public boolean equals(Object other) {
      return other instanceof Entity ? ((Entity)other).entityId == this.entityId : false;
   }

   @Override
   public String toString() {
      return String.format(
         "%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]",
         this.getClass().getSimpleName(),
         this.getText(),
         this.entityId,
         this.worldObj == null ? "~NULL~" : this.worldObj.getRandomSeedManager().getLabelText(),
         this.posX,
         this.posY,
         this.posZ
      );
   }

   @Override
   public int hashCode() {
      return this.entityId;
   }
}
