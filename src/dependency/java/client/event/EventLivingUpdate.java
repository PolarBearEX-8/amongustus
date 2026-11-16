package client.event;

import client.util.RandomUtil;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.entity.EntityLivingBase;

public class EventLivingUpdate extends AbstractEvent {
   public float factor3;
   public static final int count = 10;
   public float factor4;
   public boolean enabled = false;
   public float factor2;
   public float factor;
   public EntityLivingBase entityLivingBase;

   public float getFactor3() {
      return this.factor3;
   }

   public void setEntityLivingBase(EntityLivingBase entityLivingBase2) {
      this.entityLivingBase = entityLivingBase2;
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   private void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posY, float posZ) {
      this.factor = posX;
      this.factor3 = posX2;
      this.factor4 = posY;
      this.factor2 = posZ;
      this.entityLivingBase = livingEntity;
      this.enabled = false;
   }

   public EntityLivingBase getTargetEntity() {
      return this.entityLivingBase;
   }

   public void call(Object entry) {
      EventLivingUpdateCallback event = (EventLivingUpdateCallback)entry;
      this.onLivingUpdateCallback(event);
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void processAction(float factor5) {
      this.factor4 = factor5;
   }

   public void processAction2(float factor5) {
      this.factor3 = factor5;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public float getFactor4() {
      return this.factor4;
   }

   public void processAction(int count2) {
      this.factor = RandomUtil.processAction5(count2);
      this.factor3 = RandomUtil.processAction4(count2);
      this.factor4 = RandomUtil.processAction7(count2);
      this.factor2 = RandomUtil.processAction2(count2);
   }

   public float getFactor() {
      return this.factor;
   }

   public void onLivingUpdateCallback(EventLivingUpdateCallback event) {
      event.onLivingUpdate(this);
   }

   public void processAction3(float factor5) {
      this.factor2 = factor5;
   }

   public static void onLivingUpdateByEventLivingUpdat(
      EventLivingUpdate event, int count2, EntityLivingBase livingEntity, float posX, float posY, float posZ, short shortValue, char character, float factor5
   ) {
      event.applyEntityLivingBaseByEntityLivingBase(livingEntity, posX, posY, posZ, factor5);
   }

   public void processAction4(float factor5) {
      this.factor = factor5;
   }
}
