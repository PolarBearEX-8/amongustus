package net.minecraft.entity.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.augustus.modules.misc.ExploitFixer;
import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFallingBlock extends Entity {
   private IBlockState fallTile;
   public int fallTime;
   public boolean shouldDropItem = true;
   private boolean canSetAsBlock;
   private boolean hurtEntities;
   private int fallHurtMax = 40;
   private float fallHurtAmount = 2.0F;
   public NBTTagCompound tileEntityData;

   @Override
   public void fall(float f, float f2) {
      Block block = this.fallTile.getBlock();
      if (this.hurtEntities) {
         int n = MathHelper.ceiling_float_int(f - 1.0F);
         if (n > 0) {
            ArrayList list = Lists.newArrayList(this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
            boolean flag = block == Blocks.anvil;
            DamageSource damageSource = flag ? DamageSource.anvil : DamageSource.fallingBlock;

            for (Entity entity : (Iterable<Entity>)(list)) {
               entity.attackEntityFrom(damageSource, (float)Math.min(MathHelper.floor_float((float)n * this.fallHurtAmount), this.fallHurtMax));
            }

            if (flag && (double)this.rand.nextFloat() < 0.05000000074505806 + (double)n * 0.05) {
               int value = (Integer)this.fallTile.getValue(BlockAnvil.DAMAGE);
               if (++value > 2) {
                  this.canSetAsBlock = true;
               } else {
                  this.fallTile = this.fallTile.withProperty(BlockAnvil.DAMAGE, value);
               }
            }
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      Block block = this.fallTile != null ? this.fallTile.getBlock() : Blocks.air;
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(block);
      tag.setString("Block", nameForObject == null ? "" : nameForObject.toString());
      tag.setByte("Data", (byte)block.getMetaFromState(this.fallTile));
      tag.setByte("Time", (byte)this.fallTime);
      tag.setBoolean("DropItem", this.shouldDropItem);
      tag.setBoolean("HurtEntities", this.hurtEntities);
      tag.setFloat("FallHurtAmount", this.fallHurtAmount);
      tag.setInteger("FallHurtMax", this.fallHurtMax);
      if (this.tileEntityData != null) {
         tag.setTag("TileEntityData", this.tileEntityData);
      }
   }

   @Override
   public boolean canRenderOnFire() {
      return false;
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public EntityFallingBlock(World world, double d, double d2, double d3, IBlockState state) {
      super(world);
      this.fallTile = state;
      this.preventEntitySpawning = true;
      this.setSize(0.98F, 0.98F);
      this.setPosition(d, d2, d3);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = d;
      this.prevPosY = d2;
      this.prevPosZ = d3;
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public EntityFallingBlock(World world) {
      super(world);
   }

   public World getWorldObj() {
      return this.entityWorld;
   }

   @Override
   public void onUpdate() {
      Block block = this.fallTile.getBlock();
      if (block.getMaterial() == Material.air) {
         this.setDead();
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         if (this.fallTime++ == 0) {
            BlockPos pos = new BlockPos(this);
            if (this.entityWorld.getBlockState(pos).getBlock() == block) {
               this.entityWorld.setBlockToAir(pos);
            } else if (!this.entityWorld.isRemote) {
               this.setDead();
               return;
            }
         }

         this.motionY -= 0.03999999910593033;
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.9800000190734863;
         this.motionY *= 0.9800000190734863;
         this.motionZ *= 0.9800000190734863;
         if (!this.entityWorld.isRemote) {
            BlockPos pos2 = new BlockPos(this);
            if (this.onGround) {
               this.motionX *= 0.699999988079071;
               this.motionZ *= 0.699999988079071;
               this.motionY *= -0.5;
               if (this.entityWorld.getBlockState(pos2).getBlock() != Blocks.piston_extension) {
                  this.setDead();
                  if (!this.canSetAsBlock) {
                     if (this.entityWorld.canBlockBePlaced(block, pos2, true, EnumFacing.UP, (Entity)null, (ItemStack)null)
                        && !BlockFalling.canFallInto(this.entityWorld, pos2.down())
                        && this.entityWorld.setBlockState(pos2, this.fallTile, 3)) {
                        if (block instanceof BlockFalling) {
                           ((BlockFalling)block).onEndFalling(this.entityWorld, pos2);
                        }

                        if (this.tileEntityData != null && block instanceof ITileEntityProvider) {
                           TileEntity tileEntity = this.entityWorld.getTileEntity(pos2);
                           if (tileEntity != null) {
                              NBTTagCompound tag = new NBTTagCompound();
                              tileEntity.writeToNBT(tag);

                              for (String str : (Iterable<String>)(this.tileEntityData.getKeySet())) {
                                 NBTBase tag2 = this.tileEntityData.getTag(str);
                                 if (!str.equals("x") && !str.equals("y") && !str.equals("z")) {
                                    tag.setTag(str, tag2.copy());
                                 }
                              }

                              tileEntity.readFromNBT(tag);
                              tileEntity.markDirty();
                           }
                        }
                     } else if (this.shouldDropItem && this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
                        this.entityDropItem(new ItemStack(block, 1, block.damageDropped(this.fallTile)), 0.0F);
                     }
                  }
               }
            } else if (this.fallTime > 100 && !this.entityWorld.isRemote && (pos2.getY() < 1 || pos2.getY() > 256) || this.fallTime > 600) {
               if (this.shouldDropItem && this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
                  this.entityDropItem(new ItemStack(block, 1, block.damageDropped(this.fallTile)), 0.0F);
               }

               this.setDead();
            }
         }
      }
   }

   @Override
   public void addEntityCrashInfo(CrashReportCategory crashReportCategory) {
      super.addEntityCrashInfo(crashReportCategory);
      if (this.fallTile != null) {
         Block block = this.fallTile.getBlock();
         crashReportCategory.addCrashSection("Immitating block ID", Block.getIdFromBlock(block));
         crashReportCategory.addCrashSection("Immitating block data", block.getMetaFromState(this.fallTile));
      }
   }

   @Override
   public void entityInit() {
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      int n = tag.getByte("Data") & 255;
      if (tag.hasKey("Block", 8)) {
         ExploitFixer exploitFixer = Augustus.getInstance().getModuleManager().exploitFixer;
         String string = tag.getString("Block");
         if (!exploitFixer.isToggled() || !exploitFixer.fallingBlockFix.getValue() || !string.isEmpty()) {
            this.fallTile = Block.getBlockFromName(string).getStateFromMeta(n);
         }
      } else if (tag.hasKey("TileID", 99)) {
         this.fallTile = Block.getBlockById(tag.getInteger("TileID")).getStateFromMeta(n);
      } else {
         this.fallTile = Block.getBlockById(tag.getByte("Tile") & 255).getStateFromMeta(n);
      }

      this.fallTime = tag.getByte("Time") & 255;
      Block block = this.fallTile.getBlock();
      if (tag.hasKey("HurtEntities", 99)) {
         this.hurtEntities = tag.getBoolean("HurtEntities");
         this.fallHurtAmount = tag.getFloat("FallHurtAmount");
         this.fallHurtMax = tag.getInteger("FallHurtMax");
      } else if (block == Blocks.anvil) {
         this.hurtEntities = true;
      }

      if (tag.hasKey("DropItem", 99)) {
         this.shouldDropItem = tag.getBoolean("DropItem");
      }

      if (tag.hasKey("TileEntityData", 10)) {
         this.tileEntityData = tag.getCompoundTag("TileEntityData");
      }

      if (block == null || block.getMaterial() == Material.air) {
         this.fallTile = Blocks.sand.getDefaultState();
      }
   }

   public void setHurtEntities(boolean flag) {
      this.hurtEntities = flag;
   }

   public IBlockState getBlock() {
      return this.fallTile;
   }

}
