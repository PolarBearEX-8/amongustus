package net.minecraft.entity.projectile;

import net.minecraft.item.ItemFishFood;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFishHook extends Entity {
   private static final List JUNK = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Items.leather_boots), 10).setMaxDamagePercent(0.9F),
      new WeightedRandomFishable(new ItemStack(Items.leather), 10),
      new WeightedRandomFishable(new ItemStack(Items.bone), 10),
      new WeightedRandomFishable(new ItemStack(Items.potionitem), 10),
      new WeightedRandomFishable(new ItemStack(Items.string), 5),
      new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 2).setMaxDamagePercent(0.9F),
      new WeightedRandomFishable(new ItemStack(Items.bowl), 10),
      new WeightedRandomFishable(new ItemStack(Items.stick), 5),
      new WeightedRandomFishable(new ItemStack(Items.dye, 10, EnumDyeColor.BLACK.getDyeDamage()), 1),
      new WeightedRandomFishable(new ItemStack(Blocks.tripwire_hook), 10),
      new WeightedRandomFishable(new ItemStack(Items.rotten_flesh), 10)
   );
   private static final List TREASURE = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Blocks.waterlily), 1),
      new WeightedRandomFishable(new ItemStack(Items.name_tag), 1),
      new WeightedRandomFishable(new ItemStack(Items.saddle), 1),
      new WeightedRandomFishable(new ItemStack(Items.bow), 1).setMaxDamagePercent(0.25F).setEnchantable(),
      new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 1).setMaxDamagePercent(0.25F).setEnchantable(),
      new WeightedRandomFishable(new ItemStack(Items.book), 1).setEnchantable()
   );
   private static final List FISH = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 60),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.getMetadata()), 25),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.getMetadata()), 2),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata()), 13)
   );
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   public boolean inGround;
   public int shake;
   public EntityPlayer angler;
   private int ticksInGround;
   private int ticksInAir;
   private int ticksCatchable;
   private int ticksCaughtDelay;
   private int ticksCatchableDelay;
   private float fishApproachAngle;
   public Entity caughtEntity;
   private int fishPosRotationIncrements;
   private double fishX;
   private double fishY;
   private double fishZ;
   private double fishYaw;
   private double fishPitch;
   private double clientMotionX;
   private double clientMotionY;
   private double clientMotionZ;

   @Override
   public boolean isInRangeToRenderDist(double d) {
      double d2 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(d2)) {
         d2 = 4.0;
      }

      d2 *= 64.0;
      return d < d2 * d2;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("xTile", (short)this.xTile);
      tag.setShort("yTile", (short)this.yTile);
      tag.setShort("zTile", (short)this.zTile);
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inTile);
      tag.setString("inTile", nameForObject == null ? "" : nameForObject.toString());
      tag.setByte("shake", (byte)this.shake);
      tag.setByte("inGround", (byte)(this.inGround ? 1 : 0));
   }

   @Override
   public void setDead() {
      super.setDead();
      if (this.angler != null) {
         this.angler.fishEntity = null;
      }
   }

   public void handleHookCasting(double d, double d2, double d3, float f, float f2) {
      float f3 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
      d /= (double)f3;
      d2 /= (double)f3;
      d3 /= (double)f3;
      d += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d2 += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d3 += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d *= (double)f;
      d2 *= (double)f;
      d3 *= (double)f;
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
      float f4 = MathHelper.sqrt_double(d * d + d3 * d3);
      this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(d, d3) * 180.0 / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(d2, (double)f4) * 180.0 / Math.PI);
      this.ticksInGround = 0;
   }

   public EntityFishHook(World world, double d, double d2, double d3, EntityPlayer player) {
      this(world);
      this.setPosition(d, d2, d3);
      this.ignoreFrustumCheck = true;
      this.angler = player;
      player.fishEntity = this;
   }

   public static List getFish() {
      return FISH;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.xTile = tag.getShort("xTile");
      this.yTile = tag.getShort("yTile");
      this.zTile = tag.getShort("zTile");
      if (tag.hasKey("inTile", 8)) {
         this.inTile = Block.getBlockFromName(tag.getString("inTile"));
      } else {
         this.inTile = Block.getBlockById(tag.getByte("inTile") & 255);
      }

      this.shake = tag.getByte("shake") & 255;
      this.inGround = tag.getByte("inGround") == 1;
   }

   public int handleHookRetraction() {
      if (this.entityWorld.isRemote) {
         return 0;
      } else {
         byte b = 0;
         if (this.caughtEntity != null) {
            double d = this.angler.posX - this.posX;
            double d2 = this.angler.posY - this.posY;
            double d3 = this.angler.posZ - this.posZ;
            double d4 = (double)MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
            double d5 = 0.1;
            this.caughtEntity.motionX += d * d5;
            this.caughtEntity.motionY = this.caughtEntity.motionY + d2 * d5 + (double)MathHelper.sqrt_double(d4) * 0.08;
            this.caughtEntity.motionZ += d3 * d5;
            b = 3;
         } else if (this.ticksCatchable > 0) {
            EntityItem entityItem = new EntityItem(this.entityWorld, this.posX, this.posY, this.posZ, this.getFishingResult());
            double d6 = this.angler.posX - this.posX;
            double d7 = this.angler.posY - this.posY;
            double d8 = this.angler.posZ - this.posZ;
            double d9 = (double)MathHelper.sqrt_double(d6 * d6 + d7 * d7 + d8 * d8);
            double d10 = 0.1;
            entityItem.motionX = d6 * d10;
            entityItem.motionY = d7 * d10 + (double)MathHelper.sqrt_double(d9) * 0.08;
            entityItem.motionZ = d8 * d10;
            this.entityWorld.spawnEntityInWorld(entityItem);
            this.angler
               .entityWorld
               .spawnEntityInWorld(
                  new EntityXPOrb(this.angler.entityWorld, this.angler.posX, this.angler.posY + 0.5, this.angler.posZ + 0.5, this.rand.nextInt(6) + 1)
               );
            b = 1;
         }

         if (this.inGround) {
            b = 2;
         }

         this.setDead();
         this.angler.fishEntity = null;
         return b;
      }
   }

   @Override
   public void setVelocity(double d, double d2, double d3) {
      this.clientMotionX = this.motionX = d;
      this.clientMotionY = this.motionY = d2;
      this.clientMotionZ = this.motionZ = d3;
   }

   private ItemStack getFishingResult() {
      float f = this.entityWorld.rand.nextFloat();
      int luckOfSeaModifier = EnchantmentHelper.getLuckOfSeaModifier(this.angler);
      int lureModifier = EnchantmentHelper.getLureModifier(this.angler);
      float f2 = 0.1F - (float)luckOfSeaModifier * 0.025F - (float)lureModifier * 0.01F;
      float f3 = 0.05F + (float)luckOfSeaModifier * 0.01F - (float)lureModifier * 0.01F;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      f3 = MathHelper.clamp_float(f3, 0.0F, 1.0F);
      if (f < f2) {
         this.angler.triggerAchievement(StatList.junkFishedStat);
         return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, JUNK)).getItemStack(this.rand);
      } else {
         f -= f2;
         if (f < f3) {
            this.angler.triggerAchievement(StatList.treasureFishedStat);
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, TREASURE)).getItemStack(this.rand);
         } else {
            float f4 = f - f3;
            this.angler.triggerAchievement(StatList.fishCaughtStat);
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, FISH)).getItemStack(this.rand);
         }
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.fishPosRotationIncrements > 0) {
         double d = this.posX + (this.fishX - this.posX) / (double)this.fishPosRotationIncrements;
         double d2 = this.posY + (this.fishY - this.posY) / (double)this.fishPosRotationIncrements;
         double d3 = this.posZ + (this.fishZ - this.posZ) / (double)this.fishPosRotationIncrements;
         double d4 = MathHelper.wrapAngleTo180_double(this.fishYaw - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + d4 / (double)this.fishPosRotationIncrements);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.fishPitch - (double)this.rotationPitch) / (double)this.fishPosRotationIncrements);
         this.fishPosRotationIncrements--;
         this.setPosition(d, d2, d3);
         this.setRotation(this.rotationYaw, this.rotationPitch);
      } else {
         if (!this.entityWorld.isRemote) {
            ItemStack currentEquippedItem = this.angler.getCurrentEquippedItem();
            if (this.angler.isDead
               || !this.angler.isEntityAlive()
               || currentEquippedItem == null
               || currentEquippedItem.getItem() != Items.fishing_rod
               || this.getDistanceSqToEntity(this.angler) > 1024.0) {
               this.setDead();
               this.angler.fishEntity = null;
               return;
            }

            if (this.caughtEntity != null) {
               if (!this.caughtEntity.isDead) {
                  this.posX = this.caughtEntity.posX;
                  double height2 = (double)this.caughtEntity.height;
                  this.posY = this.caughtEntity.getEntityBoundingBox().minY + height2 * 0.8;
                  this.posZ = this.caughtEntity.posZ;
                  return;
               }

               this.caughtEntity = null;
            }
         }

         if (this.shake > 0) {
            this.shake--;
         }

         if (this.inGround) {
            if (this.entityWorld.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
               this.ticksInGround++;
               if (this.ticksInGround == 1200) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            this.motionX = this.motionX * (double)(this.rand.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition hit = this.entityWorld.rayTraceBlocks(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
         vec = new Vec3(this.posX, this.posY, this.posZ);
         vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null) {
            vec2 = new Vec3(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
         }

         Entity entity = null;
         List list = this.entityWorld
            .getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
         double d5 = 0.0;

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            if (entity2.canBeCollidedWith() && (entity2 != this.angler || this.ticksInAir >= 5)) {
               float f = 0.3F;
               MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec, vec2);
               if (hit2 != null) {
                  double d6 = vec.squareDistanceTo(hit2.hitVec);
                  if (d6 < d5 || d5 == 0.0) {
                     entity = entity2;
                     d5 = d6;
                  }
               }
            }
         }

         if (entity != null) {
            hit = new MovingObjectPosition(entity);
         }

         if (hit != null) {
            if (hit.entityHit != null) {
               if (hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0.0F)) {
                  this.caughtEntity = hit.entityHit;
               }
            } else {
               this.inGround = true;
            }
         }

         if (!this.inGround) {
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
            this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f2) * 180.0 / Math.PI);

            while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
               this.prevRotationPitch -= 360.0F;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
               this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
               this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
               this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f3 = 0.92F;
            if (this.onGround || this.isCollidedHorizontally) {
               f3 = 0.5F;
            }

            byte b = 5;
            double d7 = 0.0;

            for (int j = 0; j < b; j++) {
               AxisAlignedBB entityBoundingBox = this.getEntityBoundingBox();
               double d8 = entityBoundingBox.maxY - entityBoundingBox.minY;
               double d9 = entityBoundingBox.minY + d8 * (double)j / (double)b;
               double d10 = entityBoundingBox.minY + d8 * (double)(j + 1) / (double)b;
               AxisAlignedBB box2 = new AxisAlignedBB(entityBoundingBox.minX, d9, entityBoundingBox.minZ, entityBoundingBox.maxX, d10, entityBoundingBox.maxZ);
               if (this.entityWorld.isAABBInMaterial(box2, Material.water)) {
                  d7 += 1.0 / (double)b;
               }
            }

            if (!this.entityWorld.isRemote && d7 > 0.0) {
               WorldServer entityWorld2 = (WorldServer)this.entityWorld;
               int n = 1;
               BlockPos pos = new BlockPos(this).up();
               if (this.rand.nextFloat() < 0.25F && this.entityWorld.isRainingAt(pos)) {
                  n = 2;
               }

               if (this.rand.nextFloat() < 0.5F && !this.entityWorld.canSeeSky(pos.getX(), pos.getY(), pos.getZ())) {
                  n--;
               }

               if (this.ticksCatchable > 0) {
                  this.ticksCatchable--;
                  if (this.ticksCatchable <= 0) {
                     this.ticksCaughtDelay = 0;
                     this.ticksCatchableDelay = 0;
                  }
               } else if (this.ticksCatchableDelay > 0) {
                  this.ticksCatchableDelay -= n;
                  if (this.ticksCatchableDelay <= 0) {
                     this.motionY -= 0.2F;
                     this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                     float f4 = (float)MathHelper.floor_double(this.getEntityBoundingBox().minY);
                     entityWorld2.spawnParticle(
                        EnumParticleTypes.WATER_BUBBLE,
                        this.posX,
                        (double)(f4 + 1.0F),
                        this.posZ,
                        (int)(1.0F + this.width * 20.0F),
                        (double)this.width,
                        0.0,
                        (double)this.width,
                        0.2F,
                        new int[0]
                     );
                     entityWorld2.spawnParticle(
                        EnumParticleTypes.WATER_WAKE,
                        this.posX,
                        (double)(f4 + 1.0F),
                        this.posZ,
                        (int)(1.0F + this.width * 20.0F),
                        (double)this.width,
                        0.0,
                        (double)this.width,
                        0.2F,
                        new int[0]
                     );
                     this.ticksCatchable = MathHelper.getRandomIntegerInRange(this.rand, 10, 30);
                  } else {
                     this.fishApproachAngle = (float)((double)this.fishApproachAngle + this.rand.nextGaussian() * 4.0);
                     float f5 = this.fishApproachAngle * (float) (Math.PI / 180.0);
                     float f6 = MathHelper.sin(f5);
                     float f7 = MathHelper.cos(f5);
                     double d11 = this.posX + (double)(f6 * (float)this.ticksCatchableDelay * 0.1F);
                     double d12 = (double)((float)MathHelper.floor_double(this.getEntityBoundingBox().minY) + 1.0F);
                     double d13 = this.posZ + (double)(f7 * (float)this.ticksCatchableDelay * 0.1F);
                     Block block = entityWorld2.getBlockState(new BlockPos((int)d11, (int)d12 - 1, (int)d13)).getBlock();
                     if (block == Blocks.water || block == Blocks.flowing_water) {
                        if (this.rand.nextFloat() < 0.15F) {
                           entityWorld2.spawnParticle(
                              EnumParticleTypes.WATER_BUBBLE, d11, d12 - 0.1F, d13, 1, (double)f6, 0.1, (double)f7, 0.0, new int[0]
                           );
                        }

                        float f8 = f6 * 0.04F;
                        float f9 = f7 * 0.04F;
                        entityWorld2.spawnParticle(EnumParticleTypes.WATER_WAKE, d11, d12, d13, 0, (double)f9, 0.01, (double)(-f8), 1.0, new int[0]);
                        entityWorld2.spawnParticle(EnumParticleTypes.WATER_WAKE, d11, d12, d13, 0, (double)(-f9), 0.01, (double)f8, 1.0, new int[0]);
                     }
                  }
               } else if (this.ticksCaughtDelay > 0) {
                  this.ticksCaughtDelay -= n;
                  float f10 = 0.15F;
                  if (this.ticksCaughtDelay < 20) {
                     f10 = (float)((double)f10 + (double)(20 - this.ticksCaughtDelay) * 0.05);
                  } else if (this.ticksCaughtDelay < 40) {
                     f10 = (float)((double)f10 + (double)(40 - this.ticksCaughtDelay) * 0.02);
                  } else if (this.ticksCaughtDelay < 60) {
                     f10 = (float)((double)f10 + (double)(60 - this.ticksCaughtDelay) * 0.01);
                  }

                  if (this.rand.nextFloat() < f10) {
                     float f11 = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F) * (float) (Math.PI / 180.0);
                     float f12 = MathHelper.randomFloatClamp(this.rand, 25.0F, 60.0F);
                     double d14 = this.posX + (double)(MathHelper.sin(f11) * f12 * 0.1F);
                     double d15 = (double)((float)MathHelper.floor_double(this.getEntityBoundingBox().minY) + 1.0F);
                     double d16 = this.posZ + (double)(MathHelper.cos(f11) * f12 * 0.1F);
                     Block block2 = entityWorld2.getBlockState(new BlockPos((int)d14, (int)d15 - 1, (int)d16)).getBlock();
                     if (block2 == Blocks.water || block2 == Blocks.flowing_water) {
                        entityWorld2.spawnParticle(EnumParticleTypes.WATER_SPLASH, d14, d15, d16, 2 + this.rand.nextInt(2), 0.1F, 0.0, 0.1F, 0.0, new int[0]);
                     }
                  }

                  if (this.ticksCaughtDelay <= 0) {
                     this.fishApproachAngle = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F);
                     this.ticksCatchableDelay = MathHelper.getRandomIntegerInRange(this.rand, 20, 80);
                  }
               } else {
                  this.ticksCaughtDelay = MathHelper.getRandomIntegerInRange(this.rand, 100, 900);
                  this.ticksCaughtDelay = this.ticksCaughtDelay - EnchantmentHelper.getLureModifier(this.angler) * 20 * 5;
               }

               if (this.ticksCatchable > 0) {
                  this.motionY = this.motionY - (double)(this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2;
               }
            }

            double d17 = d7 * 2.0 - 1.0;
            this.motionY += 0.04F * d17;
            if (d7 > 0.0) {
               f3 = (float)((double)f3 * 0.9);
               this.motionY *= 0.8;
            }

            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;
            this.setPosition(this.posX, this.posY, this.posZ);
         }
      }
   }

   public EntityFishHook(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
      this.ignoreFrustumCheck = true;
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.fishX = d;
      this.fishY = d2;
      this.fishZ = d3;
      this.fishYaw = (double)f;
      this.fishPitch = (double)f2;
      this.fishPosRotationIncrements = n;
      this.motionX = this.clientMotionX;
      this.motionY = this.clientMotionY;
      this.motionZ = this.clientMotionZ;
   }

   public EntityFishHook(World world, EntityPlayer player) {
      super(world);
      this.ignoreFrustumCheck = true;
      this.angler = player;
      this.angler.fishEntity = this;
      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = 0.4F;
      this.motionX = (double)(
         -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f
      );
      this.motionZ = (double)(
         MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f
      );
      this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI) * f);
      this.handleHookCasting(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
   }

   @Override
   public void entityInit() {
   }
}
