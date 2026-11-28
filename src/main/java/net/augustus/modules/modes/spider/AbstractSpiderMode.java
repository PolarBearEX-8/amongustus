package net.augustus.modules.modes.spider;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.SettingGroup;

public abstract class AbstractSpiderMode extends AbstractSubMode {

   public AbstractSpiderMode(Spider spider, SettingGroup doubleValue, String str) {
      super(spider, doubleValue, str);
   }

   public boolean canClimbWall() {
      return mc.thePlayer != null && mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava();
   }



}
