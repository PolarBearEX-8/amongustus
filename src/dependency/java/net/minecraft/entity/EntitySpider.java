package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.DisablerUndefinedType;
import client.setting.GameSettingsPeacefulType;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ClientManagerBridge;
import client.util.ContextClientManager;
import client.util.HitBoxSystem;
import client.util.PathfindManager;
import client.util.PathfindManagerBridge;
import client.util.ServiceClientManager;
import client.util.SupplementalClientManagerAdapter;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntitySpider extends EntityMonster {
   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.ARTHROPOD;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.spider.step", 0.15F, 1.0F);
   }

   @Override
   public String getText9() {
      return "mob.spider.death";
   }

   @Override
   public double getValueAsDouble() {
      return (double)(this.height * 0.5F);
   }

   @Override
   public float getHeight2() {
      return 0.65F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(16.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
   }

   @Override
   public void updateState14() {
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      super.applyFlag(flag, count);
      if (flag && (this.random.nextInt(3) == 0 || this.random.nextInt(1 + count) > 0)) {
         this.resolveEntityItem(Items.spiderEye, 1);
      }
   }

   @Override
   public boolean matchesPotionEffect(PotionEffect potionEffect) {
      return potionEffect.getIndexOffset3() == Potion.potion24.indexOffset3 ? false : super.matchesPotionEffect(potionEffect);
   }

   @Override
   public Item getGunpowder() {
      return Items.string;
   }

   @Override
   public PathfindManager resolvePathfindManager(World world) {
      return new PathfindManagerBridge(this, world);
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      Object entry = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      if (this.worldObj.random.nextInt(100) == 0) {
         EntitySkeleton entitySkeleton = new EntitySkeleton(this.worldObj);
         entitySkeleton.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         entitySkeleton.resolvePostRenderConsumer(goldenOre, (PostRenderConsumer)null);
         this.worldObj.matchesEntity(entitySkeleton);
         entitySkeleton.setEntity8(this);
      }

      if (entry == null) {
         entry = new ServiceClientManager();
         if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD && this.worldObj.random.nextFloat() < 0.1F * goldenOre.getFactor()) {
            ((ServiceClientManager)entry).applyRandom(this.worldObj.random);
         }
      }

      if (entry instanceof ServiceClientManager) {
         int count = ((ServiceClientManager)entry).count;
         if (count > 0 && Potion.potionArray[count] != null) {
            this.applyPotionEffectByPotionEffect(new PotionEffect(count, Integer.MAX_VALUE));
         }
      }

      return (PostRenderConsumer)entry;
   }

   @Override
   public String getText3() {
      return "mob.spider.say";
   }

   @Override
   public String getText10() {
      return "mob.spider.say";
   }

   public void setEnabled6(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 1);
      } else {
         byteValue2 = (byte)(byteValue & -2);
      }

      this.dataWatcher.applyTargetByTarget(16, byteValue2);
   }

   @Override
   public boolean canExecute9() {
      return this.isActive32();
   }

   @Override
   public void updateState() {
      super.updateState();
      if (!this.worldObj.state) {
         this.setEnabled6(this.isCollidedHorizontally);
      }
   }

   public boolean isActive32() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   public EntitySpider(World world) {
      super(world);
      this.applyFloat(1.4F, 0.9F);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(3, new ClientManagerBridge(this, 0.4F));
      this.goalSetupManager.add(4, new ContextClientManager(this, EntityPlayer.class));
      this.goalSetupManager.add(4, new ContextClientManager(this, EntityVillagergolem.class));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 0.8));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(6, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(2, new HitBoxSystem(this, EntityPlayer.class));
      this.goalSetupManager2.add(3, new HitBoxSystem(this, EntityVillagergolem.class));
   }
}
