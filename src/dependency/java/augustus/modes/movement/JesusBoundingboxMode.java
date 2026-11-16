package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.EventBlockCollision;
import client.setting.ActionSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class JesusBoundingboxMode extends InternalStringArrayEntry implements BlockCollisionListener {
   public final ActionSetting actionSetting;
   public final NumberSetting settingBoundingBoxHeight;

   @Override
   public void updateState() {
   }

   @Override
   public void onBlockCollision(EventBlockCollision event) {
      if (this.isActive()
         && minecraft.thePlayer != null
         && !((double)minecraft.thePlayer.hurtTime > this.actionSetting.getDouble6())
         && !((double)minecraft.thePlayer.hurtTime < this.actionSetting.getDouble3() + 1.0)
         && event.getBlock() instanceof BlockAir) {
         BlockPos blockPos = event.getBlockPos();
         if (blockPos.getY() == (int)minecraft.thePlayer.posY + 1) {
            AxisAlignedBB boundingBox = new AxisAlignedBB(
               (double)blockPos.getX(),
               (double)blockPos.getY(),
               (double)blockPos.getZ(),
               (double)(blockPos.getX() + 1),
               (double)blockPos.getY() + this.settingBoundingBoxHeight.getValue(),
               (double)(blockPos.getZ() + 1)
            );
            event.setBoundingBox(boundingBox);
         }
      }
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusBoundingboxMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState2() {
      byte byteValue = 3;
      this.applyIndex(byteValue);
   }

   public JesusBoundingboxMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "BoundingBox");
      ActionSetting setting = new ActionSetting(
         this.module, "EndBoundingBoxHurtTime", "StartBoundingBoxHurtTime", 0.0, 10.0, 0.0, 10.0, 0, this.settingGroup
      );
      String text = "StartHurtTime";
      String text2 = "EndHurtTime";
      setting = setting.getActionSetting(text2, text).getActionSetting();
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.actionSetting = setting.getActionSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(this.module, "BoundingBoxHeight", 1.0, 0.0, 1.0, 2, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingBoundingBoxHeight = setting2.getNumberSetting(booleanSupplier2);
   }
}
