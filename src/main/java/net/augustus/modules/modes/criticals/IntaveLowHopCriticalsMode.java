package net.augustus.modules.modes.criticals;

import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.SettingGroup;

public class IntaveLowHopCriticalsMode extends CriticalsMode implements AttackEntityListener {

   public IntaveLowHopCriticalsMode(Criticals criticals, SettingGroup doubleValue) {
      super(criticals, doubleValue, "IntaveLowHop");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new IntaveLowHopCriticalsMode((Criticals)obj, (SettingGroup)obj2);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (this.shouldCrit() && mc.thePlayer.onGround && !mc.thePlayer.isBurning() && mc.thePlayer.hurtTime != 10 && mc.thePlayer.hurtTime != 9) {
         mc.thePlayer.motionY = 0.085;
      }
   }

}
