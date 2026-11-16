package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventSprint;
import client.event.SprintListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.block.BlockAir;

public class VelocityBufferMode extends InternalStringArrayEntry implements SprintListener {
   @Override
   public void onSprint(EventSprint event) {
      if (this.isActive()) {
         if (minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 0.2, minecraft.thePlayer.posZ).getBlock() instanceof BlockAir
            && minecraft.thePlayer.hurtTime > 0) {
            event.setActive(true);
         }
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityBufferMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 33;
      this.applyIndex(byteValue);
   }

   public VelocityBufferMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "SafeWalk");
   }
}
