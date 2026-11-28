package net.minecraft.entity.item;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity implements IWorldNameable {
   private boolean isInReverse;
   private String entityName;
   private static final int[][][] matrix = new int[][][]{
      {{0, 0, -1}, {0, 0, 1}},
      {{-1, 0, 0}, {1, 0, 0}},
      {{-1, -1, 0}, {1, 0, 0}},
      {{-1, 0, 0}, {1, -1, 0}},
      {{0, 0, -1}, {0, -1, 1}},
      {{0, -1, -1}, {0, 0, 1}},
      {{0, 0, 1}, {1, 0, 0}},
      {{0, 0, 1}, {-1, 0, 0}},
      {{0, 0, -1}, {-1, 0, 0}},
      {{0, 0, -1}, {1, 0, 0}}
   };
   private int turnProgress;
   private double minecartX;
   private double minecartY;
   private double minecartZ;
   private double minecartYaw;
   private double minecartPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;

   public void onActivatorRailPass(int n, int n2, int n3, boolean flag) {
   }

   public void setDamage(float f) {
      this.dataWatcher.updateObject(19, f);
   }

   public Vec3 getPosOffset(double d, double d2, double d3, double d4) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      if (BlockRailBase.isRailBlock(this.entityWorld, new BlockPos(n, n2 - 1, n3))) {
         n2--;
      }

      IBlockState blockState = this.entityWorld.getBlockState(new BlockPos(n, n2, n3));
      if (BlockRailBase.isRailBlock(blockState)) {
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)blockState.getValue(((BlockRailBase)blockState.getBlock()).getShapeProperty());
         d2 = (double)n2;
         if (value.isAscending()) {
            d2 = (double)(n2 + 1);
         }

         int[][] intsArr = matrix[value.getMetadata()];
         double d5 = (double)(intsArr[1][0] - intsArr[0][0]);
         double d6 = (double)(intsArr[1][2] - intsArr[0][2]);
         double d7 = Math.sqrt(d5 * d5 + d6 * d6);
         d5 /= d7;
         d6 /= d7;
         d += d5 * d4;
         d3 += d6 * d4;
         if (intsArr[0][1] != 0 && MathHelper.floor_double(d) - n == intsArr[0][0] && MathHelper.floor_double(d3) - n3 == intsArr[0][2]) {
            d2 += (double)intsArr[0][1];
         } else if (intsArr[1][1] != 0 && MathHelper.floor_double(d) - n == intsArr[1][0] && MathHelper.floor_double(d3) - n3 == intsArr[1][2]) {
            d2 += (double)intsArr[1][1];
         }

         return this.getPos(d, d2, d3);
      } else {
         return null;
      }
   }

   public void applyDrag() {
      if (this.riddenByEntity != null) {
         this.motionX *= 0.997F;
         this.motionY *= 0.0;
         this.motionZ *= 0.997F;
      } else {
         this.motionX *= 0.96F;
         this.motionY *= 0.0;
         this.motionZ *= 0.96F;
      }
   }

   @Override
   public boolean canBePushed() {
      return true;
   }

   @Override
   public IChatComponent getDisplayName() {
      if (this.hasCustomName()) {
         ChatComponentText chatComponentText = new ChatComponentText(this.entityName);
         chatComponentText.getChatStyle().setChatHoverEvent(this.getHoverEvent());
         chatComponentText.getChatStyle().setInsertion(this.getUniqueID().toString());
         return chatComponentText;
      } else {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(this.getName(), new Object[0]);
         chatComponentTranslation.getChatStyle().setChatHoverEvent(this.getHoverEvent());
         chatComponentTranslation.getChatStyle().setInsertion(this.getUniqueID().toString());
         return chatComponentTranslation;
      }
   }

   @Override
   public void applyEntityCollision(Entity entity) {
      if (!this.entityWorld.isRemote && !entity.noClip && !this.noClip && entity != this.riddenByEntity) {
         if (entity instanceof EntityLivingBase
            && !(entity instanceof EntityPlayer)
            && !(entity instanceof EntityIronGolem)
            && this.getMinecartType() == EntityMinecart.EnumMinecartType.RIDEABLE
            && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.01
            && this.riddenByEntity == null
            && entity.ridingEntity == null) {
            entity.mountEntity(this);
         }

         double d = entity.posX - this.posX;
         double d2 = entity.posZ - this.posZ;
         double d3 = d * d + d2 * d2;
         if (d3 >= 1.0E-4F) {
            d3 = (double)MathHelper.sqrt_double(d3);
            d /= d3;
            d2 /= d3;
            double d4 = 1.0 / d3;
            if (d4 > 1.0) {
               d4 = 1.0;
            }

            d *= d4;
            d2 *= d4;
            d *= 0.1F;
            d2 *= 0.1F;
            d *= (double)(1.0F - this.entityCollisionReduction);
            d2 *= (double)(1.0F - this.entityCollisionReduction);
            d *= 0.5;
            d2 *= 0.5;
            if (entity instanceof EntityMinecart) {
               double d5 = entity.posX - this.posX;
               double d6 = entity.posZ - this.posZ;
               Vec3 vec = new Vec3(d5, 0.0, d6).normalize();
               Vec3 vec2 = new Vec3(
                     (double)MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F),
                     0.0,
                     (double)MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)
                  )
                  .normalize();
               if (Math.abs(vec.dotProduct(vec2)) < 0.8F) {
                  return;
               }

               double d8 = entity.motionX + this.motionX;
               double d9 = entity.motionZ + this.motionZ;
               if (((EntityMinecart)entity).getMinecartType() == EntityMinecart.EnumMinecartType.FURNACE
                  && this.getMinecartType() != EntityMinecart.EnumMinecartType.FURNACE) {
                  this.motionX *= 0.2F;
                  this.motionZ *= 0.2F;
                  this.addVelocity(entity.motionX - d, 0.0, entity.motionZ - d2);
                  entity.motionX *= 0.95F;
                  entity.motionZ *= 0.95F;
               } else if (((EntityMinecart)entity).getMinecartType() != EntityMinecart.EnumMinecartType.FURNACE
                  && this.getMinecartType() == EntityMinecart.EnumMinecartType.FURNACE) {
                  entity.motionX *= 0.2F;
                  entity.motionZ *= 0.2F;
                  entity.addVelocity(this.motionX + d, 0.0, this.motionZ + d2);
                  this.motionX *= 0.95F;
                  this.motionZ *= 0.95F;
               } else {
                  d8 /= 2.0;
                  d9 /= 2.0;
                  this.motionX *= 0.2F;
                  this.motionZ *= 0.2F;
                  this.addVelocity(d8 - d, 0.0, d9 - d2);
                  entity.motionX *= 0.2F;
                  entity.motionZ *= 0.2F;
                  entity.addVelocity(d8 + d, 0.0, d9 + d2);
               }
            } else {
               this.addVelocity(-d, 0.0, -d2);
               entity.addVelocity(d / 4.0, 0.0, d2 / 4.0);
            }
         }
      }
   }

   @Override
   public AxisAlignedBB getCollisionBox(Entity entity) {
      return entity.canBePushed() ? entity.getEntityBoundingBox() : null;
   }

   @Override
   public void setCustomNameTag(String str) {
      this.entityName = str;
   }

   @Override
   public boolean hasCustomName() {
      return this.entityName != null;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.entityWorld.isRemote || this.isDead) {
         this.setRollingDirection(-this.getRollingDirection());
         this.setRollingAmplitude(10);
         this.setBeenAttacked();
         this.setDamage(this.getDamage() + f * 10.0F);
         return true;
      } else if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         this.setRollingDirection(-this.getRollingDirection());
         this.setRollingAmplitude(10);
         this.setBeenAttacked();
         this.setDamage(this.getDamage() + f * 10.0F);
         boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;
         if (flag || this.getDamage() > 40.0F) {
            if (this.riddenByEntity != null) {
               this.riddenByEntity.mountEntity((Entity)null);
            }

            if (flag && !this.hasCustomName()) {
               this.setDead();
            } else {
               this.killMinecart(damageSource);
            }
         }

         return true;
      }
   }

   public IBlockState getDefaultDisplayTile() {
      return Blocks.air.getDefaultState();
   }

   public void setDisplayTileOffset(int n) {
      this.getDataWatcher().updateObject(21, n);
      this.setHasDisplayTile(true);
   }

   public EntityMinecart(World world, double d, double d2, double d3) {
      this(world);
      this.setPosition(d, d2, d3);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = d;
      this.prevPosY = d2;
      this.prevPosZ = d3;
   }

   public void killMinecart(DamageSource damageSource) {
      this.setDead();
      if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         ItemStack stack = new ItemStack(Items.minecart, 1);
         if (this.entityName != null) {
            stack.setStackDisplayName(this.entityName);
         }

         this.entityDropItem(stack, 0.0F);
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   @Override
   public void performHurtAnimation() {
      this.setRollingDirection(-this.getRollingDirection());
      this.setRollingAmplitude(10);
      this.setDamage(this.getDamage() + this.getDamage() * 10.0F);
   }

   @Override
   public void setDead() {
      super.setDead();
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.minecartX = d;
      this.minecartY = d2;
      this.minecartZ = d3;
      this.minecartYaw = (double)f;
      this.minecartPitch = (double)f2;
      this.turnProgress = n + 2;
      this.motionX = this.velocityX;
      this.motionY = this.velocityY;
      this.motionZ = this.velocityZ;
   }

   @Override
   public String getName() {
      return this.entityName != null ? this.entityName : super.getName();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      if (this.hasDisplayTile()) {
         tag.setBoolean("CustomDisplayTile", true);
         IBlockState displayTile = this.getDisplayTile();
         ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(displayTile.getBlock());
         tag.setString("DisplayTile", nameForObject == null ? "" : nameForObject.toString());
         tag.setInteger("DisplayData", displayTile.getBlock().getMetaFromState(displayTile));
         tag.setInteger("DisplayOffset", this.getDisplayTileOffset());
      }

      if (this.entityName != null && this.entityName.length() > 0) {
         tag.setString("CustomName", this.entityName);
      }
   }

   public int getRollingDirection() {
      return this.dataWatcher.getWatchableObjectInt(18);
   }

   public int getDefaultDisplayTileOffset() {
      return 6;
   }

   public abstract EntityMinecart.EnumMinecartType getMinecartType();

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public void moveAlongTrack(BlockPos pos, IBlockState state) {
      this.fallDistance = 0.0F;
      Vec3 pos2 = this.getPos(this.posX, this.posY, this.posZ);
      this.posY = (double)pos.getY();
      boolean flag = false;
      boolean flag2 = false;
      BlockRailBase block = (BlockRailBase)state.getBlock();
      if (block == Blocks.golden_rail) {
         flag = (Boolean)state.getValue(BlockRailPowered.POWERED);
         flag2 = !flag;
      }

      double d = 0.0078125;
      BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)state.getValue(block.getShapeProperty());
      switch (value) {
         case ASCENDING_EAST:
            this.motionX -= 0.0078125;
            this.posY++;
            break;
         case ASCENDING_WEST:
            this.motionX += 0.0078125;
            this.posY++;
            break;
         case ASCENDING_NORTH:
            this.motionZ += 0.0078125;
            this.posY++;
            break;
         case ASCENDING_SOUTH:
            this.motionZ -= 0.0078125;
            this.posY++;
      }

      int[][] intsArr = matrix[value.getMetadata()];
      double d2 = (double)(intsArr[1][0] - intsArr[0][0]);
      double d3 = (double)(intsArr[1][2] - intsArr[0][2]);
      double d4 = Math.sqrt(d2 * d2 + d3 * d3);
      if ((this.motionX * d2 + this.motionZ * d3) < 0.0) {
         d2 = -d2;
         d3 = -d3;
      }

      double d6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if (d6 > 2.0) {
         d6 = 2.0;
      }

      this.motionX = d6 * d2 / d4;
      this.motionZ = d6 * d3 / d4;
      if (this.riddenByEntity instanceof EntityLivingBase) {
         if (((double)((EntityLivingBase)this.riddenByEntity).moveForward) > 0.0) {
            double d7 = -Math.sin((double)(this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F));
            double d8 = Math.cos((double)(this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F));
            if ((this.motionX * this.motionX + this.motionZ * this.motionZ) < 0.01) {
               this.motionX += d7 * 0.1;
               this.motionZ += d8 * 0.1;
               flag2 = false;
            }
         }
      }

      if (flag2) {
         if (Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) < 0.03) {
            this.motionX *= 0.0;
            this.motionY *= 0.0;
            this.motionZ *= 0.0;
         } else {
            this.motionX *= 0.5;
            this.motionY *= 0.0;
            this.motionZ *= 0.5;
         }
      }

      double d11 = 0.0;
      double d12 = (double)pos.getX() + 0.5 + (double)intsArr[0][0] * 0.5;
      double d13 = (double)pos.getZ() + 0.5 + (double)intsArr[0][2] * 0.5;
      double d14 = (double)pos.getX() + 0.5 + (double)intsArr[1][0] * 0.5;
      double d15 = (double)pos.getZ() + 0.5 + (double)intsArr[1][2] * 0.5;
      d2 = d14 - d12;
      d3 = d15 - d13;
      if (d2 == 0.0) {
         this.posX = (double)pos.getX() + 0.5;
         d11 = this.posZ - (double)pos.getZ();
      } else if (d3 == 0.0) {
         this.posZ = (double)pos.getZ() + 0.5;
         d11 = this.posX - (double)pos.getX();
      } else {
         double d16 = this.posX - d12;
         double d17 = this.posZ - d13;
         d11 = (d16 * d2 + d17 * d3) * 2.0;
      }

      this.posX = d12 + d2 * d11;
      this.posZ = d13 + d3 * d11;
      this.setPosition(this.posX, this.posY, this.posZ);
      double motionX2 = this.motionX;
      double motionZ2 = this.motionZ;
      if (this.riddenByEntity != null) {
         motionX2 *= 0.75;
         motionZ2 *= 0.75;
      }

      double maximumSpeed = this.getMaximumSpeed();
      motionX2 = MathHelper.clamp_double(motionX2, -maximumSpeed, maximumSpeed);
      motionZ2 = MathHelper.clamp_double(motionZ2, -maximumSpeed, maximumSpeed);
      this.moveEntity(motionX2, 0.0, motionZ2);
      if (intsArr[0][1] != 0
         && MathHelper.floor_double(this.posX) - pos.getX() == intsArr[0][0]
         && MathHelper.floor_double(this.posZ) - pos.getZ() == intsArr[0][2]) {
         this.setPosition(this.posX, this.posY + (double)intsArr[0][1], this.posZ);
      } else if (intsArr[1][1] != 0
         && MathHelper.floor_double(this.posX) - pos.getX() == intsArr[1][0]
         && MathHelper.floor_double(this.posZ) - pos.getZ() == intsArr[1][2]) {
         this.setPosition(this.posX, this.posY + (double)intsArr[1][1], this.posZ);
      }

      this.applyDrag();
      Vec3 pos3 = this.getPos(this.posX, this.posY, this.posZ);
      if (pos3 != null && pos2 != null) {
         double d18 = (pos2.yCoord - pos3.yCoord) * 0.05;
         d6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d6 > 0.0) {
            this.motionX = this.motionX / d6 * (d6 + d18);
            this.motionZ = this.motionZ / d6 * (d6 + d18);
         }

         this.setPosition(this.posX, pos3.yCoord, this.posZ);
      }

      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posZ);
      if (n != pos.getX() || n2 != pos.getZ()) {
         d6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.motionX = d6 * (double)(n - pos.getX());
         this.motionZ = d6 * (double)(n2 - pos.getZ());
      }

      if (flag) {
         double d19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d19 > 0.01) {
            double d20 = 0.06;
            this.motionX = this.motionX + this.motionX / d19 * d20;
            this.motionZ = this.motionZ + this.motionZ / d19 * d20;
         } else if (value == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (this.entityWorld.getBlockState(pos.west()).getBlock().isNormalCube()) {
               this.motionX = 0.02;
            } else if (this.entityWorld.getBlockState(pos.east()).getBlock().isNormalCube()) {
               this.motionX = -0.02;
            }
         } else if (value == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (this.entityWorld.getBlockState(pos.north()).getBlock().isNormalCube()) {
               this.motionZ = 0.02;
            } else if (this.entityWorld.getBlockState(pos.south()).getBlock().isNormalCube()) {
               this.motionZ = -0.02;
            }
         }
      }
   }

   public void moveDerailedMinecart() {
      double maximumSpeed = this.getMaximumSpeed();
      this.motionX = MathHelper.clamp_double(this.motionX, -maximumSpeed, maximumSpeed);
      this.motionZ = MathHelper.clamp_double(this.motionZ, -maximumSpeed, maximumSpeed);
      if (this.onGround) {
         this.motionX *= 0.5;
         this.motionY *= 0.5;
         this.motionZ *= 0.5;
      }

      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      if (!this.onGround) {
         this.motionX *= 0.95F;
         this.motionY *= 0.95F;
         this.motionZ *= 0.95F;
      }
   }

   @Override
   public double getMountedYOffset() {
      return 0.0;
   }

   public static EntityMinecart createMinecart(World world, double d, double d2, double d3, EntityMinecart.EnumMinecartType enumMinecartType) {
      switch (enumMinecartType) {
         case CHEST:
            return new EntityMinecartChest(world, d, d2, d3);
         case FURNACE:
            return new EntityMinecartFurnace(world, d, d2, d3);
         case TNT:
            return new EntityMinecartTNT(world, d, d2, d3);
         case SPAWNER:
            return new EntityMinecartMobSpawner(world, d, d2, d3);
         case HOPPER:
            return new EntityMinecartHopper(world, d, d2, d3);
         case COMMAND_BLOCK:
            return new EntityMinecartCommandBlock(world, d, d2, d3);
         default:
            return new EntityMinecartEmpty(world, d, d2, d3);
      }
   }

   public IBlockState getDisplayTile() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTile() : Block.getStateById(this.getDataWatcher().getWatchableObjectInt(20));
   }

   public Vec3 getPos(double d, double d2, double d3) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      if (BlockRailBase.isRailBlock(this.entityWorld, new BlockPos(n, n2 - 1, n3))) {
         n2--;
      }

      IBlockState blockState = this.entityWorld.getBlockState(new BlockPos(n, n2, n3));
      if (BlockRailBase.isRailBlock(blockState)) {
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)blockState.getValue(((BlockRailBase)blockState.getBlock()).getShapeProperty());
         int[][] intsArr = matrix[value.getMetadata()];
         double d4 = 0.0;
         double d5 = (double)n + 0.5 + (double)intsArr[0][0] * 0.5;
         double d6 = (double)n2 + 0.0625 + (double)intsArr[0][1] * 0.5;
         double d7 = (double)n3 + 0.5 + (double)intsArr[0][2] * 0.5;
         double d8 = (double)n + 0.5 + (double)intsArr[1][0] * 0.5;
         double d9 = (double)n2 + 0.0625 + (double)intsArr[1][1] * 0.5;
         double d10 = (double)n3 + 0.5 + (double)intsArr[1][2] * 0.5;
         double d11 = d8 - d5;
         double d12 = (d9 - d6) * 2.0;
         double d13 = d10 - d7;
         if (d11 == 0.0) {
            d = (double)n + 0.5;
            d4 = d3 - (double)n3;
         } else if (d13 == 0.0) {
            d3 = (double)n3 + 0.5;
            d4 = d - (double)n;
         } else {
            double d14 = d - d5;
            double d15 = d3 - d7;
            d4 = (d14 * d11 + d15 * d13) * 2.0;
         }

         d = d5 + d11 * d4;
         d2 = d6 + d12 * d4;
         d3 = d7 + d13 * d4;
         if (d12 < 0.0) {
            d2++;
         }

         if (d12 > 0.0) {
            d2 += 0.5;
         }

         return new Vec3(d, d2, d3);
      } else {
         return null;
      }
   }

   public boolean hasDisplayTile() {
      return this.getDataWatcher().getWatchableObjectByte(22) == 1;
   }

   @Override
   public void onUpdate() {
      if (this.getRollingAmplitude() > 0) {
         this.setRollingAmplitude(this.getRollingAmplitude() - 1);
      }

      if (this.getDamage() > 0.0F) {
         this.setDamage(this.getDamage() - 1.0F);
      }

      if (this.posY < -64.0) {
         this.kill();
      }

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

      if (this.entityWorld.isRemote) {
         if (this.turnProgress > 0) {
            double d = this.posX + (this.minecartX - this.posX) / (double)this.turnProgress;
            double d2 = this.posY + (this.minecartY - this.posY) / (double)this.turnProgress;
            double d3 = this.posZ + (this.minecartZ - this.posZ) / (double)this.turnProgress;
            double d4 = MathHelper.wrapAngleTo180_double(this.minecartYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d4 / (double)this.turnProgress);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.minecartPitch - (double)this.rotationPitch) / (double)this.turnProgress);
            this.turnProgress--;
            this.setPosition(d, d2, d3);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         } else {
            this.setPosition(this.posX, this.posY, this.posZ);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         }
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.motionY -= 0.04F;
         int n = MathHelper.floor_double(this.posX);
         int n2 = MathHelper.floor_double(this.posY);
         int n3 = MathHelper.floor_double(this.posZ);
         if (BlockRailBase.isRailBlock(this.entityWorld, new BlockPos(n, n2 - 1, n3))) {
            n2--;
         }

         BlockPos pos = new BlockPos(n, n2, n3);
         IBlockState blockState = this.entityWorld.getBlockState(pos);
         if (BlockRailBase.isRailBlock(blockState)) {
            this.moveAlongTrack(pos, blockState);
            if (blockState.getBlock() == Blocks.activator_rail) {
               this.onActivatorRailPass(n, n2, n3, (Boolean)blockState.getValue(BlockRailPowered.POWERED));
            }
         } else {
            this.moveDerailedMinecart();
         }

         this.doBlockCollisions();
         this.rotationPitch = 0.0F;
         double d5 = this.prevPosX - this.posX;
         double d6 = this.prevPosZ - this.posZ;
         if (d5 * d5 + d6 * d6 > 0.001) {
            this.rotationYaw = (float)(MathHelper.atan2(d6, d5) * 180.0 / Math.PI);
            if (this.isInReverse) {
               this.rotationYaw += 180.0F;
            }
         }

         double d7 = (double)MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);
         if (d7 < -170.0 || d7 >= 170.0) {
            this.rotationYaw += 180.0F;
            this.isInReverse = !this.isInReverse;
         }

         this.setRotation(this.rotationYaw, this.rotationPitch);

         for (Entity entity : (Iterable<Entity>)(this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.2F, 0.0, 0.2F)))) {
            if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityMinecart) {
               entity.applyEntityCollision(this);
            }
         }

         if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
            if (this.riddenByEntity.ridingEntity == this) {
               this.riddenByEntity.ridingEntity = null;
            }

            this.riddenByEntity = null;
         }

         this.handleWaterMovement();
      }
   }

   @Override
   public String getCustomNameTag() {
      return this.entityName;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public void setDisplayTile(IBlockState state) {
      this.getDataWatcher().updateObject(20, Block.getStateId(state));
      this.setHasDisplayTile(true);
   }

   @Override
   public void setPosition(double d, double d2, double d3) {
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      float f = this.width / 2.0F;
      float height2 = this.height;
      this.setEntityBoundingBox(
         new AxisAlignedBB(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height2, d3 + (double)f)
      );
   }

   public float getDamage() {
      return this.dataWatcher.getWatchableObjectFloat(19);
   }

   public void setRollingAmplitude(int n) {
      this.dataWatcher.updateObject(17, n);
   }

   public void setHasDisplayTile(boolean flag) {
      this.getDataWatcher().updateObject(22, (byte)(flag ? 1 : 0));
   }

   @Override
   public void setVelocity(double d, double d2, double d3) {
      this.velocityX = this.motionX = d;
      this.velocityY = this.motionY = d2;
      this.velocityZ = this.motionZ = d3;
   }

   public int getRollingAmplitude() {
      return this.dataWatcher.getWatchableObjectInt(17);
   }

   public double getMaximumSpeed() {
      return 0.4;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      if (tag.getBoolean("CustomDisplayTile")) {
         int integer = tag.getInteger("DisplayData");
         if (tag.hasKey("DisplayTile", 8)) {
            Block blockFromName = Block.getBlockFromName(tag.getString("DisplayTile"));
            if (blockFromName == null) {
               this.setDisplayTile(Blocks.air.getDefaultState());
            } else {
               this.setDisplayTile(blockFromName.getStateFromMeta(integer));
            }
         } else {
            Block blockById = Block.getBlockById(tag.getInteger("DisplayTile"));
            if (blockById == null) {
               this.setDisplayTile(Blocks.air.getDefaultState());
            } else {
               this.setDisplayTile(blockById.getStateFromMeta(integer));
            }
         }

         this.setDisplayTileOffset(tag.getInteger("DisplayOffset"));
      }

      if (tag.hasKey("CustomName", 8) && tag.getString("CustomName").length() > 0) {
         this.entityName = tag.getString("CustomName");
      }
   }

   public int getDisplayTileOffset() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset() : this.getDataWatcher().getWatchableObjectInt(21);
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(17, 0);
      this.dataWatcher.addObject(18, 1);
      this.dataWatcher.addObject(19, 0.0F);
      this.dataWatcher.addObject(20, 0);
      this.dataWatcher.addObject(21, 6);
      this.dataWatcher.addObject(22, (byte)0);
   }

   public void setRollingDirection(int n) {
      this.dataWatcher.updateObject(18, n);
   }

   public EntityMinecart(World world) {
      super(world);
      this.preventEntitySpawning = true;
      this.setSize(0.98F, 0.7F);
   }

   @Override
   public String getDisplayNameString() {
      return this.hasCustomName() ? this.entityName + EnumChatFormatting.RESET : this.getName() + EnumChatFormatting.RESET;
   }

public enum EnumMinecartType {
      RIDEABLE(0, "MinecartRideable"),
      CHEST(1, "MinecartChest"),
      FURNACE(2, "MinecartFurnace"),
      TNT(3, "MinecartTNT"),
      SPAWNER(4, "MinecartSpawner"),
      HOPPER(5, "MinecartHopper"),
      COMMAND_BLOCK(6, "MinecartCommandBlock");

   private static final Map ID_LOOKUP = Maps.newHashMap();
      private final int networkID;
      private final String name;
   static {
         for (EnumMinecartType enumMinecartType : values()) {
            ID_LOOKUP.put(enumMinecartType.getNetworkID(), enumMinecartType);
         }
      }

      private EnumMinecartType(int n, String str) {
         this.networkID = n;
         this.name = str;
      }

      public static EnumMinecartType byNetworkID(int n) {
         EnumMinecartType enumMinecartType = (EnumMinecartType)ID_LOOKUP.get(n);
         return enumMinecartType == null ? RIDEABLE : enumMinecartType;
      }

      public int getNetworkID() {
         return this.networkID;
      }

      public String getName() {
         return this.name;
      }
   }
}
