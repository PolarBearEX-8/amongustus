package augustus.modes.movement;

import augustus.modules.combat.Criticals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventSendPacket;
import client.event.EventWorldLoad;
import client.event.NetworkPacketListener;
import client.event.RenderTickListener;
import client.event.WorldLoadListener;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.ChatUtil;
import client.util.ExtendedStringArrayEntry;
import client.util.MovingObjectType;
import client.util.TimeHelper;
import java.util.function.BiFunction;
import net.minecraft.entity.EntityLivingBase;

public class StepLagMode extends ExtendedStringArrayEntry implements NetworkPacketListener, RenderTickListener, WorldLoadListener, ClickListener {
   public final NumberSetting settingLagDuration = new NumberSetting(this.module, "LagDuration", 200.0, 0.0, 400.0, 0, this.settingGroup);
   public final BooleanSetting settingForceHit = new BooleanSetting(this.module, "ForceHit", true, this.settingGroup);
   public static boolean enabled3 = true;
   public final TimeHelper timer = new TimeHelper();
   public final TimeHelper timer2 = new TimeHelper();
   public EntityLivingBase entityLivingBase = null;
   public int count2 = 0;
   public boolean enabled2 = false;

   @Override
   public void updateState() {
   }

   public static boolean canExecute() {
      return enabled3;
   }

   @Override
   public void onClick(EventClick event) {
      if (this.entityLivingBase != null) {
         int count = event.getIndexOffset() + (event.isState3() ? 0 : minecraft.gameSettings.keyBindAttack.getPressTime());
         if (count > 0 && this.entityLivingBase.hurtTime < 2) {
            ;
         }

         if (enabled3) {
            if (count <= 0 && this.count2 == 0) {
               event.updateInt(1);
               count++;
            }

            this.count2 += count;
         }
      }
   }

   @Override
   public void onPostNetworkPacket(EventSendPacket packet) {
      boolean flag = enabled3;
      packet.setCancelled(flag);
   }

   @Override
   public void onWorldEvent(EventWorldLoad event) {
      this.entityLivingBase = null;
   }

   @Override
   public void clear() {
      this.entityLivingBase = null;
      this.updateState3();
   }

   private void updateState3() {
      enabled3 = false;
      this.count2 = 0;
      this.enabled2 = false;
   }

   @Override
   public void updateState2() {
      byte byteValue = 25;
      this.applyIndex(byteValue);
      byte byteValue2 = 29;
      this.applyIndex(byteValue2);
      this.applyIndex(7, 10000);
      byte byteValue3 = 17;
      this.applyIndex(byteValue3);
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new StepLagMode((Criticals)item, (SettingGroup)group));
   }

   @Override
   public void onPostRenderTick() {
      if (minecraft.objectMouseOver == null) {
         this.updateState3();
      } else {
         EntityLivingBase entityLivingBase2 = ChatUtil.getEntityLivingBase2(
            minecraft.objectMouseOver.entityHit instanceof EntityLivingBase ? (EntityLivingBase)minecraft.objectMouseOver.entityHit : null
         );
         if (entityLivingBase2 != null) {
            this.entityLivingBase = entityLivingBase2;
            this.timer2.reset();
         }

         if (this.timer2.hasReached(500L)) {
            this.entityLivingBase = null;
         }

         if (this.entityLivingBase == null) {
            this.updateState3();
         } else {
            if ((this.entityLivingBase.hurtTime > 2 || this.enabled2)
               && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
               && minecraft.objectMouseOver.entityHit != null
               && minecraft.objectMouseOver.entityHit.getEntityId() == this.entityLivingBase.getEntityId()
               && this.isActive()) {
               if (!enabled3) {
                  this.timer.reset();
               }

               enabled3 = true;
            }

            if ((this.entityLivingBase.hurtTime == 0 || this.entityLivingBase.hurtTime == 10) && !this.enabled2) {
               this.updateState3();
            }

            TimeHelper timer3 = this.timer;
            double value = this.settingLagDuration.getValue();
            if (timer3.hasReachedDouble(value)) {
               this.updateState3();
            }
         }
      }
   }

   public StepLagMode(Criticals criticals, SettingGroup settingGroup) {
      super(criticals, settingGroup, "Lag");
   }
}
