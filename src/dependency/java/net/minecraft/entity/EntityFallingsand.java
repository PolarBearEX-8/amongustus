package net.minecraft.entity;

import augustus.modules.misc.ExploitFixer;
import client.Client;
import client.event.ServiceListener;
import client.module.Module;
import client.util.IBlockState;
import client.util.LISTManager;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFallingsand extends Entity {
   public IBlockState iBlockState;
   public int count11;
   public boolean enabled13 = true;
   public boolean enabled15;
   public boolean enabled14;
   public int count12 = 40;
   public float factor5 = 2.0F;
   public NBTTagCompound nBTTagCompound;

   public World getWorldAsWorld() {
      return this.worldObj;
   }

   public void setEnabled14(boolean enabled) {
      this.enabled14 = enabled;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      int count = tagCompound.computeByteCode("Data") & 255;
      if (tagCompound.matchesText("Block", 8)) {
         ExploitFixer exploitFixer = Client.getClient().getModuleManager().exploitFixer;
         String text = tagCompound.resolveText("Block");
         if (!((Module)exploitFixer).isToggled() || !exploitFixer.settingFallingBlockFix.isEnabled() || !text.isEmpty()) {
            this.iBlockState = Block.getBlockFromName(text).processAction(count);
         }
      } else if (tagCompound.matchesText("TileID", 99)) {
         this.iBlockState = Block.processAction3(tagCompound.computeCount("TileID")).processAction(count);
      } else {
         this.iBlockState = Block.processAction3(tagCompound.computeByteCode("Tile") & 255).processAction(count);
      }

      this.count11 = tagCompound.computeByteCode("Time") & 255;
      Block block = this.iBlockState.getBlock();
      if (tagCompound.matchesText("HurtEntities", 99)) {
         this.enabled14 = tagCompound.matchesText("HurtEntities");
         this.factor5 = tagCompound.computeFactor("FallHurtAmount");
         this.count12 = tagCompound.computeCount("FallHurtMax");
      } else if (block == Blocks.block51) {
         this.enabled14 = true;
      }

      if (tagCompound.matchesText("DropItem", 99)) {
         this.enabled13 = tagCompound.matchesText("DropItem");
      }

      if (tagCompound.matchesText("TileEntityData", 10)) {
         this.nBTTagCompound = tagCompound.getNBTTagCompound("TileEntityData");
      }

      if (block == null || block.getMaterial() == Material.coreItemEntry) {
         this.iBlockState = Blocks.blockSand.getDefaultState();
      }
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   @Override
   public void applyCrashReportCategory(CrashReportCategory crashReportCategory) {
      super.applyCrashReportCategory(crashReportCategory);
      if (this.iBlockState != null) {
         Block block = this.iBlockState.getBlock();
         crashReportCategory.add("Immitating block ID", Block.getIdFromBlock(block));
         crashReportCategory.add("Immitating block data", block.getIndex(this.iBlockState));
      }
   }

   @Override
   public boolean isActive4() {
      return false;
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      Block block = this.iBlockState.getBlock();
      if (this.enabled14) {
         int count = MathHelper.computeCount(factor - 1.0F);
         if (count > 0) {
            ArrayList list = Lists.newArrayList(this.worldObj.resolveListByEntity(this, this.getBoundingBox2()));
            boolean flag = block == Blocks.block51;
            DamageSource damageSource = flag ? DamageSource.damageSource14 : DamageSource.damageSource6;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
               ((Entity)iterator.next()).matchesDamageSource(damageSource, (float)Math.min(MathHelper.floor_float((float)count * this.factor5), this.count12));
            }

            if (flag && (double)this.random.nextFloat() < 0.05F + (double)count * 0.05) {
               int comparable = (Integer)this.iBlockState.getComparable(BlockAnvil.auxiliaryImmutableSetEntry);
               if (++comparable > 2) {
                  this.enabled15 = true;
               } else {
                  this.iBlockState = this.iBlockState.resolveIBlockState(BlockAnvil.auxiliaryImmutableSetEntry, comparable);
               }
            }
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      Block block = this.iBlockState != null ? this.iBlockState.getBlock() : Blocks.air;
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(block);
      tagCompound.put("Block", object == null ? "" : object.toString());
      tagCompound.put("Data", (byte)block.getIndex(this.iBlockState));
      tagCompound.put("Time", (byte)this.count11);
      tagCompound.applyText("DropItem", this.enabled13);
      tagCompound.applyText("HurtEntities", this.enabled14);
      tagCompound.put("FallHurtAmount", this.factor5);
      tagCompound.put("FallHurtMax", this.count12);
      if (this.nBTTagCompound != null) {
         tagCompound.applyText("TileEntityData", this.nBTTagCompound);
      }
   }

   @Override
   public boolean isActive5() {
      return !this.isDead;
   }

   @Override
   public void updateState() {
      Block block = this.iBlockState.getBlock();
      if (block.getMaterial() == Material.coreItemEntry) {
         this.updateState9();
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         if (this.count11++ == 0) {
            BlockPos blockPos = new BlockPos(this);
            if (this.worldObj.getBlockState(blockPos).getBlock() == block) {
               this.worldObj.matchesBlockPos8(blockPos);
            } else if (!this.worldObj.state) {
               this.updateState9();
               return;
            }
         }

         this.motionY -= 0.04F;
         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.98F;
         this.motionY *= 0.98F;
         this.motionZ *= 0.98F;
         if (!this.worldObj.state) {
            BlockPos blockPos2 = new BlockPos(this);
            if (this.onGround) {
               this.motionX *= 0.7F;
               this.motionZ *= 0.7F;
               this.motionY *= -0.5;
               if (this.worldObj.getBlockState(blockPos2).getBlock() != Blocks.blockPistonExtension) {
                  this.updateState9();
                  if (!this.enabled15) {
                     if (this.worldObj.matchesBlock(block, blockPos2, true, EnumFacing.UP, (Entity)null, (ItemStack)null)
                        && !CustomBlockAdapter.matchesWorld3(this.worldObj, blockPos2.down())
                        && this.worldObj.matchesBlockPos(blockPos2, this.iBlockState, 3)) {
                        if (block instanceof CustomBlockAdapter) {
                           ((CustomBlockAdapter)block).applyWorld4(this.worldObj, blockPos2);
                        }

                        if (this.nBTTagCompound != null && block instanceof ServiceListener) {
                           TileEntity tileEntity = this.worldObj.resolveTileEntity(blockPos2);
                           if (tileEntity != null) {
                              NBTTagCompound tagCompound = new NBTTagCompound();
                              tileEntity.applyNBTTagCompound(tagCompound);

                              for (String text : (Iterable<String>)(Iterable)this.nBTTagCompound.keySet()) {
                                 LISTManager lISTManager = this.nBTTagCompound.get(text);
                                 if (!text.equals("x") && !text.equals("y") && !text.equals("z")) {
                                    tagCompound.applyText(text, lISTManager.getLISTManager());
                                 }
                              }

                              tileEntity.applyNBTTagCompoundByNBTTagCompound(tagCompound);
                              tileEntity.processAction5();
                           }
                        }
                     } else if (this.enabled13 && this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
                        this.resolveEntityItem(new ItemStack(block, 1, block.getMetaFromState(this.iBlockState)), 0.0F);
                     }
                  }
               }
            } else if (this.count11 > 100 && !this.worldObj.state && (blockPos2.getY() < 1 || blockPos2.getY() > 256) || this.count11 > 600) {
               if (this.enabled13 && this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
                  this.resolveEntityItem(new ItemStack(block, 1, block.getMetaFromState(this.iBlockState)), 0.0F);
               }

               this.updateState9();
            }
         }
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityFallingsand(World world, double posX, double posY, double posZ, IBlockState blockState) {
      super(world);
      this.iBlockState = blockState;
      this.enabled = true;
      this.applyFloat(0.98F, 0.98F);
      this.processDouble2(posX, posY, posZ);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = posX;
      this.prevPosY = posY;
      this.prevPosZ = posZ;
   }

   public EntityFallingsand(World world) {
      super(world);
   }
}
