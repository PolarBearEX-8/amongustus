package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;

public class NoJumpDelay extends Module implements UpdateListener {
   private final BooleanValue whileScaffold;
   private final DoubleValue chance;

   public NoJumpDelay() {
       super("NoJumpDelay", "KeineSprungVerzögerung", new Color(235, 64, 52), Categorys.MOVEMENT);
      this.whileScaffold = new BooleanValue(this, "WhileScaffold", true);
      this.chance = new DoubleValue(this, "Chance", 100.0, 0.0, 100.0, 0);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   public void onPreUpdate() {
      if ((!ClientManagers.moduleManager.scaffold.isToggled() || this.whileScaffold.getValue()) && Math.random() <= this.chance.getValue() / 100.0) {
         mc.thePlayer.setJumpTicks(0);
      }
   }

}
