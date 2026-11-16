package net.minecraft.entity.item;

import client.event.ComplexListener;
import client.setting.ClientSettingsBlackType;
import client.setting.MovementNumberProperty;
import client.setting.MovementProfileProperty;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.EnhancedDoubleEntry;
import client.util.IBlockState;
import client.util.StaticIntArrayEntry;
import client.util.VanillaRunnable;
import client.util.VectorCalculator;
import client.util.VectorConverter;
import client.util.VectorEvaluator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockActivatorRail;
import net.minecraft.block.BlockPosEntry;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCaveSpiderAdapter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.DiamondWeapon;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity implements ComplexListener {
   public boolean enabled13;
   public String labelText;
   public static final int[][][] countArray = new int[][][]{
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
   public int count12;
   public double value16;
   public double value6;
   public double value11;
   public double value9;
   public double value10;
   public double value7;
   public double value18;
   public double value8;

   @Override
   public void processDouble2(double posX, double posY, double posZ) {
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      float factor = this.width / 2.0F;
      float factor2 = this.height;
      this.setBoundingBox2(new AxisAlignedBB(posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)factor2, posZ + (double)factor));
   }

   @Override
   public boolean processAction() {
      return this.labelText != null;
   }

   public IBlockState getIBlockStateAsIBlockState() {
      return !this.isActive17() ? this.getIBlockState() : Block.processAction2(this.getDataWatcher().computeCount(20));
   }

   public void setEnabled5(boolean enabled) {
      this.getDataWatcher().applyTargetByTarget(22, (byte)(enabled ? 1 : 0));
   }

   @Override
   public double getValueAsDouble() {
      return 0.0;
   }

   public void applyIndex6(int count) {
      this.dataWatcher.applyTargetByTarget(18, count);
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public void applyDamageSource(DamageSource damageSource) {
      this.updateState9();
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         ItemStack stack = new ItemStack(Items.minecart, 1);
         if (this.labelText != null) {
            stack.resolveItemStack(this.labelText);
         }

         this.resolveEntityItem(stack, 0.0F);
      }
   }

   @Override
   public String getText() {
      return this.labelText != null ? this.labelText : super.getText();
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("CustomDisplayTile")) {
         int count = tagCompound.computeCount("DisplayData");
         if (tagCompound.matchesText("DisplayTile", 8)) {
            Block blockFromName = Block.getBlockFromName(tagCompound.resolveText("DisplayTile"));
            if (blockFromName == null) {
               this.applyIBlockState(Blocks.air.getDefaultState());
            } else {
               this.applyIBlockState(blockFromName.processAction(count));
            }
         } else {
            Block block = Block.processAction3(tagCompound.computeCount("DisplayTile"));
            if (block == null) {
               this.applyIBlockState(Blocks.air.getDefaultState());
            } else {
               this.applyIBlockState(block.processAction(count));
            }
         }

         this.applyIndex7(tagCompound.computeCount("DisplayOffset"));
      }

      if (tagCompound.matchesText("CustomName", 8) && tagCompound.resolveText("CustomName").length() > 0) {
         this.labelText = tagCompound.resolveText("CustomName");
      }
   }

   public int getCount7() {
      return !this.isActive17() ? this.getCount4() : this.getDataWatcher().computeCount(21);
   }

   @Override
   public void applyDouble2(double posX, double posY, double posZ) {
      this.value7 = this.motionX = posX;
      this.value18 = this.motionY = posY;
      this.value8 = this.motionZ = posZ;
   }

   public float getFloat() {
      return this.dataWatcher.computeFactor(19);
   }

   public abstract MovementNumberProperty getFURNACE();

   public void applyIndex5(int count) {
      this.dataWatcher.applyTargetByTarget(17, count);
   }

   @Override
   public IChatComponent getChatComponent() {
      if (this.processAction()) {
         ChatComponentText chatComponentText = new ChatComponentText(this.labelText);
         chatComponentText.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
         chatComponentText.getStyleHasParentManager().withLabelText(this.getEntityUniqueID().toString());
         return chatComponentText;
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]);
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withLabelText(this.getEntityUniqueID().toString());
         return baseManagerStyleIChatComponentAdapter;
      }
   }

   @Override
   public String getText2() {
      return this.labelText;
   }

   @Override
   public AxisAlignedBB resolveAxisAlignedBB(Entity entity) {
      return entity.isActive9() ? entity.getBoundingBox2() : null;
   }

   public double getValue2() {
      return 0.4;
   }

   public static EntityMinecart resolveEntityMinecart(World world, double posX, double posY, double posZ, MovementNumberProperty movementNumberProperty) {
      switch (StaticIntArrayEntry.countArray[movementNumberProperty.ordinal()]) {
         case 1:
            return new VectorEvaluator(world, posX, posY, posZ);
         case 2:
            return new EnhancedDoubleEntry(world, posX, posY, posZ);
         case 3:
            return new VectorCalculator(world, posX, posY, posZ);
         case 4:
            return new DiamondWeapon(world, posX, posY, posZ);
         case 5:
            return new BlockPosEntry(world, posX, posY, posZ);
         case 6:
            return new EntityCaveSpiderAdapter(world, posX, posY, posZ);
         default:
            return new VectorConverter(world, posX, posY, posZ);
      }
   }

   @Override
   public void applyEntity(Entity entity) {
      if (!this.worldObj.state && !entity.noClip && !this.noClip && entity != this.entity) {
         if (entity instanceof EntityLivingBase
            && !(entity instanceof EntityPlayer)
            && !(entity instanceof EntityVillagergolem)
            && this.getFURNACE() == MovementNumberProperty.RIDEABLE
            && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.01
            && this.entity == null
            && entity.ridingEntity == null) {
            entity.setEntity8(this);
         }

         double offset = entity.posX - this.posX;
         double offset2 = entity.posZ - this.posZ;
         double offset3 = offset * offset + offset2 * offset2;
         if (offset3 >= 1.0E-4F) {
            double offset4 = (double)MathHelper.sqrt_double(offset3);
            double offset5 = offset / offset4;
            double offset6 = offset2 / offset4;
            double offset7 = 1.0 / offset4;
            if (offset7 > 1.0) {
               offset7 = 1.0;
            }

            double offset8 = offset5 * offset7;
            double offset9 = offset6 * offset7;
            double offset10 = offset8 * 0.1F;
            double offset11 = offset9 * 0.1F;
            double offset12 = offset10 * (double)(1.0F - this.factor3);
            double offset13 = offset11 * (double)(1.0F - this.factor3);
            double offset14 = offset12 * 0.5;
            double offset15 = offset13 * 0.5;
            if (entity instanceof EntityMinecart) {
               double offset16 = entity.posX - this.posX;
               double offset17 = entity.posZ - this.posZ;
               Vec3 vec = new Vec3(offset16, 0.0, offset17).normalize();
               Vec3 vec2 = new Vec3(
                     (double)MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F), 0.0, (double)MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F)
                  )
                  .normalize();
               if (Math.abs(vec.distanceTo(vec2)) < 0.8F) {
                  return;
               }

               double offset18 = entity.motionX + this.motionX;
               double offset19 = entity.motionZ + this.motionZ;
               if (((EntityMinecart)entity).getFURNACE() == MovementNumberProperty.FURNACE && this.getFURNACE() != MovementNumberProperty.FURNACE) {
                  this.motionX *= 0.2F;
                  this.motionZ *= 0.2F;
                  this.applyDouble3(entity.motionX - offset14, 0.0, entity.motionZ - offset15);
                  entity.motionX *= 0.95F;
                  entity.motionZ *= 0.95F;
               } else if (((EntityMinecart)entity).getFURNACE() != MovementNumberProperty.FURNACE && this.getFURNACE() == MovementNumberProperty.FURNACE) {
                  entity.motionX *= 0.2F;
                  entity.motionZ *= 0.2F;
                  entity.applyDouble3(this.motionX + offset14, 0.0, this.motionZ + offset15);
                  this.motionX *= 0.95F;
                  this.motionZ *= 0.95F;
               } else {
                  double offset20 = offset18 / 2.0;
                  double offset21 = offset19 / 2.0;
                  this.motionX *= 0.2F;
                  this.motionZ *= 0.2F;
                  this.applyDouble3(offset20 - offset14, 0.0, offset21 - offset15);
                  entity.motionX *= 0.2F;
                  entity.motionZ *= 0.2F;
                  entity.applyDouble3(offset20 + offset14, 0.0, offset21 + offset15);
               }
            } else {
               this.applyDouble3(-offset14, 0.0, -offset15);
               entity.applyDouble3(offset14 / 4.0, 0.0, offset15 / 4.0);
            }
         }
      }
   }

   @Override
   public boolean isActive9() {
      return true;
   }

   public Vec3 resolveVec3(double posX, double posX2, double posY, double posZ) {
      int count = MathHelper.ceiling_double_int(posX);
      int count2 = MathHelper.ceiling_double_int(posX2);
      int count3 = MathHelper.ceiling_double_int(posY);
      if (SpecialBlockAdapter.matchesWorld3(this.worldObj, new BlockPos(count, count2 - 1, count3))) {
         count2--;
      }

      IBlockState blockState = this.worldObj.getBlockState(new BlockPos(count, count2, count3));
      if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
         MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(((SpecialBlockAdapter)blockState.getBlock()).getIProperty());
         double offset = (double)count2;
         if (comparable.isActive()) {
            offset = (double)(count2 + 1);
         }

         int[][] countMatrix = countArray[comparable.getCount()];
         double offset2 = (double)(countMatrix[1][0] - countMatrix[0][0]);
         double offset3 = (double)(countMatrix[1][2] - countMatrix[0][2]);
         double offset4 = Math.sqrt(offset2 * offset2 + offset3 * offset3);
         double offset5 = offset2 / offset4;
         double offset6 = offset3 / offset4;
         double offset7 = posX + offset5 * posZ;
         double offset8 = posY + offset6 * posZ;
         if (countMatrix[0][1] != 0 && MathHelper.ceiling_double_int(offset7) - count == countMatrix[0][0] && MathHelper.ceiling_double_int(offset8) - count3 == countMatrix[0][2]) {
            offset += (double)countMatrix[0][1];
         } else if (countMatrix[1][1] != 0
            && MathHelper.ceiling_double_int(offset7) - count == countMatrix[1][0]
            && MathHelper.ceiling_double_int(offset8) - count3 == countMatrix[1][2]) {
            offset += (double)countMatrix[1][1];
         }

         return this.resolveVec3(offset7, offset, offset8);
      } else {
         return null;
      }
   }

   public Vec3 resolveVec3(double posX, double posY, double posZ) {
      int count = MathHelper.ceiling_double_int(posX);
      int count2 = MathHelper.ceiling_double_int(posY);
      int count3 = MathHelper.ceiling_double_int(posZ);
      if (SpecialBlockAdapter.matchesWorld3(this.worldObj, new BlockPos(count, count2 - 1, count3))) {
         count2--;
      }

      IBlockState blockState = this.worldObj.getBlockState(new BlockPos(count, count2, count3));
      if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
         MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(((SpecialBlockAdapter)blockState.getBlock()).getIProperty());
         int[][] countMatrix = countArray[comparable.getCount()];
         double offset = (double)count + 0.5 + (double)countMatrix[0][0] * 0.5;
         double offset2 = (double)count2 + 0.0625 + (double)countMatrix[0][1] * 0.5;
         double offset3 = (double)count3 + 0.5 + (double)countMatrix[0][2] * 0.5;
         double offset4 = (double)count + 0.5 + (double)countMatrix[1][0] * 0.5;
         double offset5 = (double)count2 + 0.0625 + (double)countMatrix[1][1] * 0.5;
         double offset6 = (double)count3 + 0.5 + (double)countMatrix[1][2] * 0.5;
         double offset7 = offset4 - offset;
         double offset8 = (offset5 - offset2) * 2.0;
         double offset9 = offset6 - offset3;
         double offset10;
         if (offset7 == 0.0) {
            offset10 = posZ - (double)count3;
         } else if (offset9 == 0.0) {
            offset10 = posX - (double)count;
         } else {
            double offset11 = posX - offset;
            double offset12 = posZ - offset3;
            offset10 = (offset11 * offset7 + offset12 * offset9) * 2.0;
         }

         double offset13 = offset + offset7 * offset10;
         double offset14 = offset2 + offset8 * offset10;
         double offset15 = offset3 + offset9 * offset10;
         if (offset8 < 0.0) {
            offset14++;
         }

         if (offset8 > 0.0) {
            offset14 += 0.5;
         }

         return new Vec3(offset13, offset14, offset15);
      } else {
         return null;
      }
   }

   public void applyIBlockState(IBlockState blockState) {
      this.getDataWatcher().applyTargetByTarget(20, Block.processAction5(blockState));
      this.setEnabled5(true);
   }

   public IBlockState getIBlockState() {
      return Blocks.air.getDefaultState();
   }

   public void applyIndex(int count, int count2, int count3, boolean flag) {
   }

   @Override
   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public void applyFloat(float factor) {
      this.dataWatcher.applyTargetByTarget(19, factor);
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.worldObj.state || this.isDead) {
         this.applyIndex6(-this.getCount5());
         this.applyIndex5(10);
         this.updateState8();
         this.applyFloat(this.getFloat() + factor * 10.0F);
         return true;
      } else if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.applyIndex6(-this.getCount5());
         this.applyIndex5(10);
         this.updateState8();
         this.applyFloat(this.getFloat() + factor * 10.0F);
         boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).invulnerableManager.enabled2;
         if (flag || this.getFloat() > 40.0F) {
            if (this.entity != null) {
               this.entity.setEntity8((Entity)null);
            }

            if (flag && !this.processAction()) {
               this.updateState9();
            } else {
               this.applyDamageSource(damageSource);
            }
         }

         return true;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      if (this.isActive17()) {
         tagCompound.applyText("CustomDisplayTile", true);
         IBlockState iBlockStateAsIBlockState = this.getIBlockStateAsIBlockState();
         ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(iBlockStateAsIBlockState.getBlock());
         tagCompound.put("DisplayTile", object == null ? "" : object.toString());
         tagCompound.put("DisplayData", iBlockStateAsIBlockState.getBlock().getIndex(iBlockStateAsIBlockState));
         tagCompound.put("DisplayOffset", this.getCount7());
      }

      if (this.labelText != null && this.labelText.length() > 0) {
         tagCompound.put("CustomName", this.labelText);
      }
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      this.value16 = posX;
      this.value6 = posY;
      this.value11 = posZ;
      this.value9 = (double)factor;
      this.value10 = (double)factor2;
      this.count12 = count + 2;
      this.motionX = this.value7;
      this.motionY = this.value18;
      this.motionZ = this.value8;
   }

   @Override
   public AxisAlignedBB getAxisAlignedBB() {
      return null;
   }

   public void applyBlockPos(BlockPos blockPos, IBlockState blockState) {
      this.fallDistance = 0.0F;
      Vec3 vec = this.resolveVec3(this.posX, this.posY, this.posZ);
      this.posY = (double)blockPos.getY();
      boolean flag = false;
      boolean flag2 = false;
      SpecialBlockAdapter block = (SpecialBlockAdapter)blockState.getBlock();
      if (block == Blocks.block50) {
         flag = (Boolean)blockState.getComparable(BlockActivatorRail.internalImmutableSetEntry);
         flag2 = !flag;
      }

      MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(block.getIProperty());
      switch (StaticIntArrayEntry.countArray2[comparable.ordinal()]) {
         case 1:
            this.motionX -= 0.0078125;
            this.posY++;
            break;
         case 2:
            this.motionX += 0.0078125;
            this.posY++;
            break;
         case 3:
            this.motionZ += 0.0078125;
            this.posY++;
            break;
         case 4:
            this.motionZ -= 0.0078125;
            this.posY++;
      }

      int[][] countMatrix = countArray[comparable.getCount()];
      double offset = (double)(countMatrix[1][0] - countMatrix[0][0]);
      double offset2 = (double)(countMatrix[1][2] - countMatrix[0][2]);
      double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
      if (this.motionX * offset + this.motionZ * offset2 < 0.0) {
         offset = -offset;
         offset2 = -offset2;
      }

      double offset4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if (offset4 > 2.0) {
         offset4 = 2.0;
      }

      this.motionX = offset4 * offset / offset3;
      this.motionZ = offset4 * offset2 / offset3;
      if (this.entity instanceof EntityLivingBase && (double)((EntityLivingBase)this.entity).scaleFactor3 > 0.0) {
         double offset5 = -Math.sin((double)(this.entity.rotationYaw * (float) Math.PI / 180.0F));
         double offset6 = Math.cos((double)(this.entity.rotationYaw * (float) Math.PI / 180.0F));
         if (this.motionX * this.motionX + this.motionZ * this.motionZ < 0.01) {
            this.motionX += offset5 * 0.1;
            this.motionZ += offset6 * 0.1;
            flag2 = false;
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

      double offset7 = (double)blockPos.getX() + 0.5 + (double)countMatrix[0][0] * 0.5;
      double offset8 = (double)blockPos.getZ() + 0.5 + (double)countMatrix[0][2] * 0.5;
      double offset9 = (double)blockPos.getX() + 0.5 + (double)countMatrix[1][0] * 0.5;
      double offset10 = (double)blockPos.getZ() + 0.5 + (double)countMatrix[1][2] * 0.5;
      double offset11 = offset9 - offset7;
      double offset12 = offset10 - offset8;
      double offset13;
      if (offset11 == 0.0) {
         this.posX = (double)blockPos.getX() + 0.5;
         offset13 = this.posZ - (double)blockPos.getZ();
      } else if (offset12 == 0.0) {
         this.posZ = (double)blockPos.getZ() + 0.5;
         offset13 = this.posX - (double)blockPos.getX();
      } else {
         double offset14 = this.posX - offset7;
         double offset15 = this.posZ - offset8;
         offset13 = (offset14 * offset11 + offset15 * offset12) * 2.0;
      }

      this.posX = offset7 + offset11 * offset13;
      this.posZ = offset8 + offset12 * offset13;
      this.processDouble2(this.posX, this.posY, this.posZ);
      double value = this.motionX;
      double value2 = this.motionZ;
      if (this.entity != null) {
         value *= 0.75;
         value2 *= 0.75;
      }

      double value3 = this.getValue2();
      double doubleItem = MathHelper.getDouble7(value, -value3, value3);
      double doubleItem2 = MathHelper.getDouble7(value2, -value3, value3);
      this.applyDouble(doubleItem, 0.0, doubleItem2);
      if (countMatrix[0][1] != 0
         && MathHelper.ceiling_double_int(this.posX) - blockPos.getX() == countMatrix[0][0]
         && MathHelper.ceiling_double_int(this.posZ) - blockPos.getZ() == countMatrix[0][2]) {
         this.processDouble2(this.posX, this.posY + (double)countMatrix[0][1], this.posZ);
      } else if (countMatrix[1][1] != 0
         && MathHelper.ceiling_double_int(this.posX) - blockPos.getX() == countMatrix[1][0]
         && MathHelper.ceiling_double_int(this.posZ) - blockPos.getZ() == countMatrix[1][2]) {
         this.processDouble2(this.posX, this.posY + (double)countMatrix[1][1], this.posZ);
      }

      this.updateState3();
      Vec3 vec2 = this.resolveVec3(this.posX, this.posY, this.posZ);
      if (vec2 != null && vec != null) {
         double offset16 = (vec.yCoord - vec2.yCoord) * 0.05;
         double offset17 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (offset17 > 0.0) {
            this.motionX = this.motionX / offset17 * (offset17 + offset16);
            this.motionZ = this.motionZ / offset17 * (offset17 + offset16);
         }

         this.processDouble2(this.posX, vec2.yCoord, this.posZ);
      }

      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.posZ);
      if (count != blockPos.getX() || count2 != blockPos.getZ()) {
         double offset18 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.motionX = offset18 * (double)(count - blockPos.getX());
         this.motionZ = offset18 * (double)(count2 - blockPos.getZ());
      }

      if (flag) {
         double offset19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (offset19 > 0.01) {
            double offset20 = 0.06;
            this.motionX = this.motionX + this.motionX / offset19 * offset20;
            this.motionZ = this.motionZ + this.motionZ / offset19 * offset20;
         } else if (comparable == MovementProfileProperty.EAST_WEST) {
            if (this.worldObj.getBlockState(blockPos.getBlockPos()).getBlock().isActive2()) {
               this.motionX = 0.02;
            } else if (this.worldObj.getBlockState(blockPos.getBlockPos3()).getBlock().isActive2()) {
               this.motionX = -0.02;
            }
         } else if (comparable == MovementProfileProperty.NORTH_SOUTH) {
            if (this.worldObj.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock().isActive2()) {
               this.motionZ = 0.02;
            } else if (this.worldObj.getBlockState(blockPos.getBlockPos2()).getBlock().isActive2()) {
               this.motionZ = -0.02;
            }
         }
      }
   }

   public int getCount5() {
      return this.dataWatcher.computeCount(18);
   }

   public void applyIndex7(int count) {
      this.getDataWatcher().applyTargetByTarget(21, count);
      this.setEnabled5(true);
   }

   @Override
   public String getText5() {
      return this.processAction() ? this.labelText + ClientSettingsBlackType.RESET : this.getText() + ClientSettingsBlackType.RESET;
   }

   public void updateState4() {
      double value = this.getValue2();
      this.motionX = MathHelper.getDouble7(this.motionX, -value, value);
      this.motionZ = MathHelper.getDouble7(this.motionZ, -value, value);
      if (this.onGround) {
         this.motionX *= 0.5;
         this.motionY *= 0.5;
         this.motionZ *= 0.5;
      }

      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      if (!this.onGround) {
         this.motionX *= 0.95F;
         this.motionY *= 0.95F;
         this.motionZ *= 0.95F;
      }
   }

   public boolean isActive17() {
      return this.getDataWatcher().computeByteCode(22) == 1;
   }

   @Override
   public boolean isActive5() {
      return !this.isDead;
   }

   public void updateState3() {
      if (this.entity != null) {
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
   public void updateState9() {
      super.updateState9();
   }

   public int getCount6() {
      return this.dataWatcher.computeCount(17);
   }

   @Override
   public void updateState16() {
      this.applyIndex6(-this.getCount5());
      this.applyIndex5(10);
      this.applyFloat(this.getFloat() + this.getFloat() * 10.0F);
   }

   public int getCount4() {
      return 6;
   }

   @Override
   public void updateState() {
      if (this.getCount6() > 0) {
         this.applyIndex5(this.getCount6() - 1);
      }

      if (this.getFloat() > 0.0F) {
         this.applyFloat(this.getFloat() - 1.0F);
      }

      if (this.posY < -64.0) {
         this.updateState19();
      }

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

      if (this.worldObj.state) {
         if (this.count12 > 0) {
            double offset = this.posX + (this.value16 - this.posX) / (double)this.count12;
            double offset2 = this.posY + (this.value6 - this.posY) / (double)this.count12;
            double offset3 = this.posZ + (this.value11 - this.posZ) / (double)this.count12;
            double doubleItem = MathHelper.getDouble4(this.value9 - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + doubleItem / (double)this.count12);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.value10 - (double)this.rotationPitch) / (double)this.count12);
            this.count12--;
            this.processDouble2(offset, offset2, offset3);
            this.processFloat4(this.rotationYaw, this.rotationPitch);
         } else {
            this.processDouble2(this.posX, this.posY, this.posZ);
            this.processFloat4(this.rotationYaw, this.rotationPitch);
         }
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.motionY -= 0.04F;
         int count = MathHelper.ceiling_double_int(this.posX);
         int count2 = MathHelper.ceiling_double_int(this.posY);
         int count3 = MathHelper.ceiling_double_int(this.posZ);
         if (SpecialBlockAdapter.matchesWorld3(this.worldObj, new BlockPos(count, count2 - 1, count3))) {
            count2--;
         }

         BlockPos blockPos = new BlockPos(count, count2, count3);
         IBlockState blockState = this.worldObj.getBlockState(blockPos);
         if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
            this.applyBlockPos(blockPos, blockState);
            if (blockState.getBlock() == Blocks.block106) {
               this.applyIndex(count, count2, count3, (Boolean)blockState.getComparable(BlockActivatorRail.internalImmutableSetEntry));
            }
         } else {
            this.updateState4();
         }

         this.updateState13();
         this.rotationPitch = 0.0F;
         double offset4 = this.prevPosX - this.posX;
         double offset5 = this.prevPosZ - this.posZ;
         if (offset4 * offset4 + offset5 * offset5 > 0.001) {
            this.rotationYaw = (float)(MathHelper.getDouble6(offset5, offset4) * 180.0 / Math.PI);
            if (this.enabled13) {
               this.rotationYaw += 180.0F;
            }
         }

         double offset6 = (double)MathHelper.sqrt_float(this.rotationYaw - this.prevRotationYaw);
         if (offset6 < -170.0 || offset6 >= 170.0) {
            this.rotationYaw += 180.0F;
            this.enabled13 = !this.enabled13;
         }

         this.processFloat4(this.rotationYaw, this.rotationPitch);

         for (Entity entity : (Iterable<Entity>)(Iterable)this.worldObj.resolveListByEntity(this, this.getBoundingBox2().offset(0.2F, 0.0, 0.2F))) {
            if (entity != this.entity && entity.isActive9() && entity instanceof EntityMinecart) {
               entity.applyEntity(this);
            }
         }

         if (this.entity != null && this.entity.isDead) {
            if (this.entity.ridingEntity == this) {
               this.entity.ridingEntity = null;
            }

            this.entity = null;
         }

         this.isActive2();
      }
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(17, 0);
      this.dataWatcher.applyTarget(18, 1);
      this.dataWatcher.applyTarget(19, 0.0F);
      this.dataWatcher.applyTarget(20, 0);
      this.dataWatcher.applyTarget(21, 6);
      this.dataWatcher.applyTarget(22, (byte)0);
   }

   public EntityMinecart(World world) {
      super(world);
      this.enabled = true;
      this.applyFloat(0.98F, 0.7F);
   }

   public EntityMinecart(World world, double posX, double posY, double posZ) {
      this(world);
      this.processDouble2(posX, posY, posZ);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = posX;
      this.prevPosY = posY;
      this.prevPosZ = posZ;
   }
}
