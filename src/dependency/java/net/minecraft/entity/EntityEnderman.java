package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryClientManagerHandler;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.DamageSourceAdapter;
import client.util.ExtendedClassEntry;
import client.util.IBlockState;
import client.util.InterpolationSelector;
import client.util.InvalidOperationManager;
import client.util.NativeClientManagerAdapter;
import client.util.NativePredicateAdapter;
import client.util.SupplementalClientManagerAdapter;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.entity.player.EntityPlayerEntry;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMonster {
   public static final UUID uUID4 = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   public static final InvalidOperationManager invalidOperationManager3 = new InvalidOperationManager(uUID4, "Attacking speed boost", 0.15F, 0)
      .withEnabled(false);
   public static final Set set = Sets.newIdentityHashSet();
   public boolean enabled28;

   public boolean matchesEntity10(Entity entity) {
      Vec3 vec = new Vec3(
            this.posX - entity.posX,
            this.getBoundingBox2().minY + (double)(this.height / 2.0F) - entity.posY + (double)entity.getHeight2(),
            this.posZ - entity.posZ
         )
         .normalize();
      double offset = 16.0;
      double offset2 = this.posX + (this.random.nextDouble() - 0.5) * 8.0 - vec.xCoord * offset;
      double offset3 = this.posY + (double)(this.random.nextInt(16) - 8) - vec.yCoord * offset;
      double offset4 = this.posZ + (this.random.nextDouble() - 0.5) * 8.0 - vec.zCoord * offset;
      return this.checkDouble3(offset2, offset3, offset4);
   }

   public static Set getSet() {
      return set;
   }

   @Override
   public String getText9() {
      return "mob.endermen.death";
   }

   public static boolean matchesEntityEndermanByEntityEnderman(EntityEnderman entityEnderman, EntityPlayer player) {
      return entityEnderman.matchesEntityPlayer11(player);
   }

   public IBlockState getIBlockState() {
      return Block.processAction2(this.dataWatcher.computeShortCode(16) & '\uffff');
   }

   @Override
   public float getHeight2() {
      return 2.55F;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      IBlockState blockState;
      if (tagCompound.matchesText("carried", 8)) {
         blockState = Block.getBlockFromName(tagCompound.resolveText("carried")).processAction(tagCompound.processValue("carriedData") & '\uffff');
      } else {
         blockState = Block.processAction3(tagCompound.processValue("carried")).processAction(tagCompound.processValue("carriedData") & '\uffff');
      }

      this.applyIBlockState(blockState);
   }

   public boolean checkDouble3(double posX, double posY, double posZ) {
      double value = this.posX;
      double value2 = this.posY;
      double value3 = this.posZ;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      boolean flag = false;
      BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
      if (this.worldObj.checkBlockPos12(blockPos)) {
         boolean flag2 = false;

         while (!flag2 && blockPos.getY() > 0) {
            BlockPos blockPos2 = blockPos.down();
            if (this.worldObj.getBlockState(blockPos2).getBlock().getMaterial().isActiveAsFlag()) {
               flag2 = true;
            } else {
               this.posY--;
               blockPos = blockPos2;
            }
         }

         if (flag2) {
            super.applyDoubleByDouble(this.posX, this.posY, this.posZ);
            if (this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty() && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2())) {
               flag = true;
            }
         }
      }

      if (!flag) {
         this.processDouble2(value, value2, value3);
         return false;
      } else {
         short shortValue = 128;

         for (int index = 0; index < shortValue; index++) {
            double offset = (double)index / ((double)shortValue - 1.0);
            float factor = (this.random.nextFloat() - 0.5F) * 0.2F;
            float factor2 = (this.random.nextFloat() - 0.5F) * 0.2F;
            float factor3 = (this.random.nextFloat() - 0.5F) * 0.2F;
            double offset2 = value + (this.posX - value) * offset + (this.random.nextDouble() - 0.5) * (double)this.width * 2.0;
            double offset3 = value2 + (this.posY - value2) * offset + this.random.nextDouble() * (double)this.height;
            double offset4 = value3 + (this.posZ - value3) * offset + (this.random.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.PORTAL, offset2, offset3, offset4, (double)factor, (double)factor2, (double)factor3, new int[0]
               );
         }

         this.worldObj.applyText(value, value2, value3, "mob.endermen.portal", 1.0F, 1.0F);
         this.applyText("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(40.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(7.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(64.0);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      Item gunpowder = this.getGunpowder();
      if (gunpowder != null) {
         int count2 = this.random.nextInt(2 + count);

         for (int index = 0; index < count2; index++) {
            this.resolveEntityItem(gunpowder, 1);
         }
      }
   }

   @Override
   public Item getGunpowder() {
      return Items.enderPearl;
   }

   public static InvalidOperationManager getInvalidOperationManager3() {
      return invalidOperationManager3;
   }

   @Override
   public String getText3() {
      return this.isActive32() ? "mob.endermen.scream" : "mob.endermen.idle";
   }

   public static boolean matchesEntityEnderman(EntityEnderman entityEnderman, boolean flag) {
      return entityEnderman.enabled28 = flag;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         if (damageSource.getEntity() == null || !(damageSource.getEntity() instanceof EntityEndermite)) {
            if (!this.worldObj.state) {
               this.setEnabled6(true);
            }

            if (damageSource instanceof DamageSourceAdapter && damageSource.getEntity() instanceof EntityPlayer) {
               if (damageSource.getEntity() instanceof EntityPlayerAdapter && ((EntityPlayerAdapter)damageSource.getEntity()).internalWorldEntry.isActiveAsFlag()) {
                  this.setEnabled6(false);
               } else {
                  this.enabled28 = true;
               }
            }

            if (damageSource instanceof InterpolationSelector) {
               this.enabled28 = false;

               for (int index = 0; index < 64; index++) {
                  if (this.isActive33()) {
                     return true;
                  }
               }

               return false;
            }
         }

         boolean flag = super.matchesDamageSource(damageSource, factor);
         if (damageSource.canExecute() && this.random.nextInt(10) != 0) {
            this.isActive33();
         }

         return flag;
      }
   }

   private boolean matchesEntityPlayer11(EntityPlayer player) {
      ItemStack stack = player.inventoryPlayer.armorInventory[3];
      if (stack != null && stack.getItem() == Item.processAction2(Blocks.block146)) {
         return false;
      } else {
         Vec3 vec = player.getVec36(1.0F).normalize();
         Vec3 vec2 = new Vec3(
            this.posX - player.posX,
            this.getBoundingBox2().minY + (double)(this.height / 2.0F) - (player.posY + (double)player.getHeight2()),
            this.posZ - player.posZ
         );
         double valueAsDouble = vec2.getValueAsDouble();
         Vec3 vec3 = vec2.normalize();
         return vec.distanceTo(vec3) > 1.0 - 0.025 / valueAsDouble ? player.checkEntity4(this) : false;
      }
   }

   @Override
   public String getText10() {
      return "mob.endermen.hit";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      IBlockState iBlockState = this.getIBlockState();
      tagCompound.put("carried", (short)Block.getIdFromBlock(iBlockState.getBlock()));
      tagCompound.put("carriedData", (short)iBlockState.getBlock().getIndex(iBlockState));
   }

   public void applyIBlockState(IBlockState blockState) {
      this.dataWatcher.applyTargetByTarget(16, (short)(Block.processAction5(blockState) & 65535));
   }

   public void setEnabled6(boolean enabled) {
      this.dataWatcher.applyTargetByTarget(18, (byte)(enabled ? 1 : 0));
   }

   public boolean isActive32() {
      return this.dataWatcher.computeByteCode(18) > 0;
   }

   @Override
   public void updateState3() {
      if (this.isActive11()) {
         this.matchesDamageSource(DamageSource.damageSource, 1.0F);
      }

      if (this.isActive32() && !this.enabled28 && this.random.nextInt(100) == 0) {
         this.setEnabled6(false);
      }

      if (this.worldObj.isActive2()) {
         float factor = this.computeFactor(1.0F);
         if (factor > 0.5F
            && this.worldObj
               .checkIndex2(
                  MathHelper.ceiling_double_int(this.posX), MathHelper.ceiling_double_int(this.posY), MathHelper.ceiling_double_int(this.posZ)
               )
            && this.random.nextFloat() * 30.0F < (factor - 0.4F) * 2.0F) {
            this.setEntityLivingBase((EntityLivingBase)null);
            this.setEnabled6(false);
            this.enabled28 = false;
            this.isActive33();
         }
      }

      super.updateState3();
   }

   public boolean isActive33() {
      double offset = this.posX + (this.random.nextDouble() - 0.5) * 64.0;
      double offset2 = this.posY + (double)(this.random.nextInt(64) - 32);
      double offset3 = this.posZ + (this.random.nextDouble() - 0.5) * 64.0;
      return this.checkDouble3(offset, offset2, offset3);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (short)0);
      this.dataWatcher.applyTarget(17, (byte)0);
      this.dataWatcher.applyTarget(18, (byte)0);
   }

   @Override
   public void updateState33() {
      if (this.worldObj.state) {
         for (int index = 0; index < 2; index++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.PORTAL,
                  this.posX + (this.random.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.random.nextDouble() * (double)this.height - 0.25,
                  this.posZ + (this.random.nextDouble() - 0.5) * (double)this.width,
                  (this.random.nextDouble() - 0.5) * 2.0,
                  -this.random.nextDouble(),
                  (this.random.nextDouble() - 0.5) * 2.0,
                  new int[0]
               );
         }
      }

      this.state31 = false;
      super.updateState33();
   }

   public EntityEnderman(World world) {
      super(world);
      this.applyFloat(0.6F, 2.9F);
      this.stepHeight = 1.0F;
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new AuxiliaryWorldEntry(this, 1.0, false));
      this.goalSetupManager.add(7, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(8, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.goalSetupManager.add(10, new NativeClientManagerAdapter(this));
      this.goalSetupManager.add(11, new AuxiliaryClientManagerHandler(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(2, new EntityPlayerEntry(this));
      this.goalSetupManager2.add(3, new ExtendedClassEntry(this, EntityEndermite.class, 10, true, false, new NativePredicateAdapter(this)));
   }

   static {
      set.add(Blocks.blockGrass);
      set.add(Blocks.block115);
      set.add(Blocks.blockSand);
      set.add(Blocks.block45);
      set.add(Blocks.immutableSetEntry2Entry);
      set.add(Blocks.immutableSetEntry2Entry2);
      set.add(Blocks.secondaryBlockAdapter2);
      set.add(Blocks.secondaryBlockAdapter);
      set.add(Blocks.block135);
      set.add(Blocks.blockCactus);
      set.add(Blocks.block123);
      set.add(Blocks.block146);
      set.add(Blocks.block67);
      set.add(Blocks.blockMycelium);
   }
}
