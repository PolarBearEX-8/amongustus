package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.settings.KeybindSetting;
import net.augustus.events.listeners.TimerListener;
import net.augustus.events.EventTimer;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public class Booster extends Module implements TimerListener {
   public final DoubleValue boostTicks;
   public final DoubleValue delay;
   public final KeybindSetting keyToToggleBoost;
   private final TimeHelper timeHelper;

   public Booster() {
       super("Booster", "Beschläuniger", new Color(214, 84, 186), Categorys.MOVEMENT);
      this.boostTicks = new DoubleValue(this, "BoostTicks", 2.0, 0.0, 20.0, 0);
      this.delay = new DoubleValue(this, "Delay", 2000.0, 0.0, 10000.0, 0);
      this.keyToToggleBoost = new KeybindSetting(this, "Key to toggle boost");
      this.timeHelper = new TimeHelper();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(41);
   }

   public void onTimerSpeed(EventTimer event) {
   }

   public void onTimerTick(EventTimer event) {
      if (KeyboardInputUtil.isKeyDown(this.keyToToggleBoost.getKeyCode())) {
         if (this.timeHelper.reached(this.delay.getValue())) {
            event.setExtraTicks((int)Math.max(0.0, this.boostTicks.getValue()));
            this.timeHelper.reset();
            this.sendChatMessage("Boosted");
         }
      }
   }

}
