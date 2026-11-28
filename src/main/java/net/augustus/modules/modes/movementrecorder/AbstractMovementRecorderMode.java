package net.augustus.modules.modes.movementrecorder;

import net.augustus.modules.modes.AbstractSubMode;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.movement.MovementRecorder;
import net.augustus.settings.SettingGroup;
import net.minecraft.util.MathHelper;

public abstract class AbstractMovementRecorderMode extends AbstractSubMode {

   public AbstractMovementRecorderMode(MovementRecorder movementRecorder, SettingGroup doubleValue, String str) {
      super(movementRecorder, doubleValue, str);
   }

   public List captureSurroundingBlocks() {
      int n = MathHelper.floor_double(mc.thePlayer.posX);
      int n2 = MathHelper.floor_double(mc.thePlayer.posY);
      int n3 = MathHelper.floor_double(mc.thePlayer.posZ);
      byte b = 6;
      ArrayList list = new ArrayList();

      for (int i = n - b; i < n + b; i++) {
         for (int j = n2 - b; j < n2 + b; j++) {
            for (int k = n3 - b; k < n3 + b; k++) {
               list.add(mc.theWorld.getBlockState2(i, j, k).getBlock().isFullBlock());
            }
         }
      }

      return list;
   }



}
