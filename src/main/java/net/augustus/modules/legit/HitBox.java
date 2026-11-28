package net.augustus.modules.legit;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class HitBox extends Module implements RayCastListener {
   public final DoubleValue expandXZ;
   public final DoubleValue expandY;
   public final BooleanValue serverHitBox;
   public final BooleanValue n18Hitbox;
   public final BooleanValue optifineHitbox;

   public HitBox() {
       super("HitBox", "SchlagBox", new Color(180, 207, 130), Categorys.LEGIT);
      this.expandXZ = new DoubleValue(this, "ExpandXZ", 0.0, 0.0, 1.0, 2);
      this.expandY = new DoubleValue(this, "ExpandY", 0.0, 0.0, 1.0, 2);
      this.serverHitBox = new BooleanValue(this, "ServerHitBox", false);
      this.n18Hitbox = new BooleanValue(this, "1.8 Hitbox", true);
      this.optifineHitbox = new BooleanValue(this, "OptifineHitbox", false);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(29);
   }

   public void onRayCast() {
      if (this.optifineHitbox.getValue()) {
         if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
            MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, mc.thePlayer.rotationYawHead, mc.thePlayer.rotationPitch);
            if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
               mc.objectMouseOver = hit;
            }
         }
      }
   }

   public AxisAlignedBB getExpandedBoundingBox(Entity entity, float f) {
      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      float f2 = (float)((double)f + ClientManagers.moduleManager.hitBox.expandXZ.getValue());
      float f3 = (float)((double)f + ClientManagers.moduleManager.hitBox.expandY.getValue());
      if (ClientManagers.moduleManager.hitBox.serverHitBox.getValue()) {
         if (entity instanceof EntityLivingBase) {
            Vec3 vec = new Vec3((double)entity.serverPosX / 32.0, (double)entity.serverPosY / 32.0, (double)entity.serverPosZ / 32.0);
            float f4 = entity.width / 2.0F;
            entityBoundingBox = entityBoundingBox.union(
               new AxisAlignedBB(
                     vec.xCoord - (double)f4,
                     vec.yCoord,
                     vec.zCoord - (double)f4,
                     vec.xCoord + (double)f4,
                     vec.yCoord + (double)entity.height,
                     vec.zCoord + (double)f4
                  )
                  .expand((double)f, (double)f, (double)f)
            );
         }
      }

      return entityBoundingBox.expand((double)f2, (double)f3, (double)f2);
   }



}
