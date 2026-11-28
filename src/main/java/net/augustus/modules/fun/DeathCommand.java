package net.augustus.modules.fun;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.DynamicSettingList;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.TimeHelper;

public class DeathCommand extends Module implements TickListener {
   private final RandomizedRangeSettings delay;
   public final DynamicSettingList messageAtDeath;
   private final TimeHelper timeHelper;
   private boolean commandSent;
   private boolean wasDead;

   public DeathCommand() {
       super("DeathCommand", "Tötungsbefehl", new Color(156, 194, 255), Categorys.FUN);
      this.delay = (new RandomizedRangeSettings(this, "Delay", 0.0, 0.0, 0.0, 2000.0, 0)).withoutNotInARow();
      this.messageAtDeath = new DynamicSettingList(this, "Message at death", n -> {
         return new TextInputSetting(this, "Message_" + n, "/warp ffa");
      }, 1, 50).withInitialCount((byte)1);
      this.timeHelper = new TimeHelper();
      this.commandSent = false;
      this.wasDead = false;
   }

   @Override
   public void onEnable() {
      this.commandSent = false;
      this.wasDead = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPostTick() {
      if (mc.thePlayer != null) {
         if (mc.thePlayer.getCurrentHealth() <= 0.0F) {
            if (!this.wasDead) {
               this.commandSent = false;
               this.timeHelper.reset();
            }

            this.wasDead = true;
         } else {
            this.wasDead = false;
         }

         if (this.timeHelper.reached((long)this.delay.getLastIntValue()) && !this.commandSent) {
            this.messageAtDeath.nextEntry();
            TextInputSetting textInputSetting = (TextInputSetting)this.messageAtDeath.nextEntry();
            if (textInputSetting != null) {
               mc.thePlayer.sendChatMessage(textInputSetting.getValue());
            }

            this.commandSent = true;
         }
      }
   }

}
