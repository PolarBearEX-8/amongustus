package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;

public class AutoRespawn extends Module implements TickListener {
   private final TimeHelper deathScreenTimer;
   private final TimeHelper respawnCooldownTimer;
   public final RangeSetting delay;

   public AutoRespawn() {
       super("AutoRespawn", "AutomatischerRespawn", new Color(82, 222, 124), Categorys.PLAYER);
      this.deathScreenTimer = new TimeHelper();
      this.respawnCooldownTimer = new TimeHelper();
      this.delay = new RangeSetting(this, "Delay", 1000.0, 2000.0, 0.0, 5000.0, 0);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      if (mc.currentScreen instanceof GuiGameOver) {
         if (mc.thePlayer.isDead) {
            if (this.deathScreenTimer.reached((long)DefaultRandomGenerator.instance.nextInt(this.delay.getMinValue(), this.delay.getMaxValue()))) {
               if (this.respawnCooldownTimer.reached(10000L)) {
                  mc.thePlayer.respawnPlayer();
                  GuiScreen screen = (GuiScreen)null;
                  mc.displayGuiScreen(screen);
                  this.deathScreenTimer.reset();
                  this.respawnCooldownTimer.reset();
               }
            }
         }
      } else {
         this.deathScreenTimer.reset();
      }
   }

}
