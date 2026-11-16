package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.event.TickListener;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CoreStringEntry;
import client.util.MoveUtil;
import client.util.TimeHelper;
import net.minecraft.util.MathHelper;

public class FlyJumpMode extends CoreStringEntry implements TickListener {
   public final NumberSetting settingMotion;
   public final BooleanSetting settingOnlyMove;
   public final TimeHelper timer;

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 40;
      this.applyIndex(byteValue);
   }

   @Override
   public void onRunTick() {
      if (this.isActive() && minecraft.thePlayer.isInWater()) {
         if (!this.settingOnlyMove.isEnabled() || MoveUtil.canExecute()) {
            minecraft.thePlayer.motionY = this.settingMotion.getValue();
            if (this.timer.hasReached(200L) && minecraft.thePlayer.isRiding()) {
               float factor = minecraft.thePlayer.rotationYaw * (float) (Math.PI / 180.0);
               minecraft.thePlayer.motionX = minecraft.thePlayer.motionX - (double)(MathHelper.abs(factor) * 0.2F);
               minecraft.thePlayer.motionZ = minecraft.thePlayer.motionZ + (double)(MathHelper.cos(factor) * 0.2F);
               MoveUtil.updateState();
            }
         }

         this.timer.reset();
      }
   }

   public FlyJumpMode(Jesus jesus, SettingGroup settingGroup) {
        super(jesus, settingGroup, "Jump");
      
      NumberSetting setting = new NumberSetting(this.module, "Motion", 0.6, 0.01, 2.0, 2, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingMotion = setting.getNumberSetting(booleanSupplier);
      BooleanSetting setting2 = new BooleanSetting(this.module, "OnlyMove", false, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingOnlyMove = (BooleanSetting)setting2.getSetting(booleanSupplier2);
      this.timer = new TimeHelper();
   }
}
