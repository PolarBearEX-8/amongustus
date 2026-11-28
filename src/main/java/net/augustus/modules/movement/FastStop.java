package net.augustus.modules.movement;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ModeSetting;
import net.augustus.events.EventSafeWalk;
import net.augustus.modules.modes.faststop.FastStopMode;
import net.augustus.modules.modes.faststop.LegitFastStopMode;
import net.augustus.modules.modes.faststop.NoneFastStopMode;
import net.augustus.modules.modes.faststop.UnlegitFastStopMode;
import net.augustus.modules.modes.faststop.UnlegitEdgeFastStopMode;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class FastStop extends Module implements MoveInputListener, UpdateListener, SafeWalkListener {
   private final SettingGroup onGround;
   private final ModeSetting modesOnGround;
   private final SettingGroup inAir;
   private final ModeSetting modesInAir;
   public final BooleanValue notWhileFlying;

   public FastStop() {
       super("FastStop", "SchnellesStoppen", new Color(102, 212, 255), Categorys.MOVEMENT);
      this.onGround = new SettingGroup(this, "OnGround");
      this.modesOnGround = new ModeSetting(
         this,
         "ModesOnGround",
         "Unlegit",
         this.onGround,
         new BiFunction[]{
            (obj, obj2) -> new NoneFastStopMode((FastStop)obj, (SettingGroup)obj2),
            (obj3, obj4) -> new UnlegitFastStopMode((FastStop)obj3, (SettingGroup)obj4),
            (obj5, obj6) -> new UnlegitEdgeFastStopMode((FastStop)obj5, (SettingGroup)obj6),
            (obj7, obj8) -> new LegitFastStopMode((FastStop)obj7, (SettingGroup)obj8)
         }
      );
      this.inAir = new SettingGroup(this, "InAir", true);
      this.modesInAir = new ModeSetting(
         this,
         "ModesInAir",
         "Unlegit",
         this.inAir,
         new BiFunction[]{
            (obj9, obj10) -> new NoneFastStopMode((FastStop)obj9, (SettingGroup)obj10),
            (obj11, obj12) -> new UnlegitFastStopMode((FastStop)obj11, (SettingGroup)obj12),
            (obj13, obj14) -> new UnlegitEdgeFastStopMode((FastStop)obj13, (SettingGroup)obj14),
            (obj15, obj16) -> new LegitFastStopMode((FastStop)obj15, (SettingGroup)obj16)
         }
      );
      this.notWhileFlying = new BooleanValue("NotWhileFlying", false);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21, (short)1500);
      this.toggleEventSubscription(33);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      ((FastStopMode)this.modesOnGround.getAbstractSubMode()).updateMoveInputState(event);
      ((FastStopMode)this.modesInAir.getAbstractSubMode()).updateMoveInputState(event);
      this.getActiveMode().onMoveInput(event);
   }

   @Override
   public void onPostUpdate() {
      this.getActiveMode().onUpdate();
   }

   @Override
   public void onPreSafeWalk(EventSafeWalk event) {
      this.getActiveMode().onSafeWalk(event);
   }

   private FastStopMode getActiveMode() {
      return mc.thePlayer.onGround ? (FastStopMode)this.modesOnGround.getAbstractSubMode() : (FastStopMode)this.modesInAir.getAbstractSubMode();
   }

}
