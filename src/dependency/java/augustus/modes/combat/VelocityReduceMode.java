package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import java.util.function.BiFunction;
import net.minecraft.block.BlockCache;
import net.minecraft.util.BlockPos;

public class VelocityReduceMode extends InternalStringArrayEntry implements UpdateStateListener {
   public int count2 = 0;

   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityReduceMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      if (this.isActive()) {
         if (minecraft.thePlayer.hurtTime > 0
            && minecraft.thePlayer.motionY <= 0.0
            && (
               BlockCache.checkBlockPos3(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 1.0, minecraft.thePlayer.posZ))
                  || BlockCache.checkBlockPos3(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 2.0, minecraft.thePlayer.posZ))
            )) {
            minecraft.thePlayer.motionY = -10.0;
         }

         if (minecraft.thePlayer.hurtTime == 5 && this.count2++ % 2 == 0) {
            MoveUtil.processFloat(0.15F);
         }
      }
   }

   public VelocityReduceMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "Karhu");
   }
}
