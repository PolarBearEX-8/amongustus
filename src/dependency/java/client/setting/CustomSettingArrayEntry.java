package client.setting;

import augustus.modules.movement.MovementRecorder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.MathHelper;

public abstract class CustomSettingArrayEntry extends ModeOptionSupplier {
   public List getList() {
      int count = MathHelper.ceiling_double_int(minecraft.thePlayer.posX);
      int count2 = MathHelper.ceiling_double_int(minecraft.thePlayer.posY);
      int count3 = MathHelper.ceiling_double_int(minecraft.thePlayer.posZ);
      ArrayList list = new ArrayList();

      for (int index = count - 6; index < count + 6; index++) {
         for (int index2 = count2 - 6; index2 < count2 + 6; index2++) {
            for (int index3 = count3 - 6; index3 < count3 + 6; index3++) {
               list.add(minecraft.theWorld.getIBlockState(index, index2, index3).getBlock().isState18());
            }
         }
      }

      return list;
   }

   public CustomSettingArrayEntry(MovementRecorder movementRecorder, SettingGroup settingGroup, String text) {
      super(movementRecorder, settingGroup, text);
   }
}
