package client.util;

import augustus.modules.movement.Jesus;
import client.ClientGlobals;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;
import net.minecraft.block.BlockLiquid;

public abstract class CoreStringEntry extends ModeOptionSupplier {
   public boolean isActive() {
      return !ClientGlobals.moduleManager.jesus.settingDisableAtSneakJump.isEnabled()
         || !minecraft.gameSettings.keyBindSneak.isKeyDown()
            && (
               !minecraft.gameSettings.keyBindJump.isKeyDown()
                  || !(minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.posY + 1.0, minecraft.thePlayer.posZ).getBlock() instanceof BlockLiquid)
            );
   }

   public CoreStringEntry(Jesus jesus, SettingGroup settingGroup, String text) {
      super(jesus, settingGroup, text);
   }
}
