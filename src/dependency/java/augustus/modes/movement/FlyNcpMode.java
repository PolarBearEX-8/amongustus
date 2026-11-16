package augustus.modes.movement;

import augustus.modules.movement.LongJump;
import client.event.UpdateStateListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.AuxiliaryMoveListenerAdapter;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import java.util.function.BiFunction;
import net.minecraft.util.MathHelper;

public class FlyNcpMode extends AuxiliaryMoveListenerAdapter implements UpdateStateListener {
   public final NumberSetting settingNCPSpeed;

   public static BiFunction getBiFunction() {
      return ((item, group) -> new FlyNcpMode((LongJump)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
      ((LongJump)this.module).expanded2 = true;
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      if (((LongJump)this.module).expanded2) {
         minecraft.thePlayer.closeScreen();
         MoveUtil.processFloat((float)(MathHelper.getDouble7(this.settingNCPSpeed.getValue(), 0.0, 50.0) * MoveUtil.getValue2()));
         ((LongJump)this.module).expanded2 = false;
      } else {
         MoveUtil.processFloat((float)MoveUtil.getValue2());
      }
   }

   public FlyNcpMode(LongJump longJump, SettingGroup settingGroup) {
      super(longJump, settingGroup, "NCP");
      NumberSetting setting = new NumberSetting(this.module, "NCPSpeed", 4.0, 1.0, 10.0, 2, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingNCPSpeed = setting.getNumberSetting(booleanSupplier);
   }
}
