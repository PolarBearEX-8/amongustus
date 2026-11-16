package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;

public class EnhancedClientManagerAdapter extends RotationPool {
   public EntitySlime entitySlime;
   public int count2;

   @Override
   public void updateState2() {
      this.entitySlime.applyEntityByEntity(this.entitySlime.getEntityLivingBase4(), 10.0F, 10.0F);
      ((VirtualFloatEntry)this.entitySlime.getEntityPillagerAdapter()).applyFloat(this.entitySlime.rotationYaw, this.entitySlime.isActive29());
   }

   public EnhancedClientManagerAdapter(EntitySlime entitySlime2) {
      this.entitySlime = entitySlime2;
      this.setCount(2);
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase = this.entitySlime.getEntityLivingBase4();
      return entityLivingBase == null ? false : (!entityLivingBase.canExecute() ? false : !(entityLivingBase instanceof EntityPlayer) || !((EntityPlayer)entityLivingBase).invulnerableManager.enabled);
   }

   @Override
   public void updateState3() {
      this.count2 = 300;
      super.updateState3();
   }

   @Override
   public boolean isActiveAsFlag() {
      EntityLivingBase entityLivingBase = this.entitySlime.getEntityLivingBase4();
      return entityLivingBase == null
         ? false
         : (!entityLivingBase.canExecute() ? false : (entityLivingBase instanceof EntityPlayer && ((EntityPlayer)entityLivingBase).invulnerableManager.enabled ? false : --this.count2 > 0));
   }
}
