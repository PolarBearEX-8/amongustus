package net.augustus.modules.modes.jesus;

import net.augustus.ClientManagers;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.BlockLiquid;

public abstract class AbstractJesusMode extends AbstractSubMode {

   public AbstractJesusMode(Jesus jesus, SettingGroup doubleValue, String str) {
      super(jesus, doubleValue, str);
   }

   public boolean shouldApplyJesus() {
      return !ClientManagers.moduleManager.jesus.disableatsneakJump.getValue()
         || !mc.gameSettings.keyBindSneak.isKeyDown()
            && (
               !mc.gameSettings.keyBindJump.isKeyDown()
                  || !(mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY + 1.0, mc.thePlayer.posZ).getBlock() instanceof BlockLiquid)
            );
   }



}
