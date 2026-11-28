package baritone.behavior;

import baritone.Baritone;
import baritone.api.behavior.ILookBehavior;
import baritone.api.event.events.PacketEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.utils.Rotation;
import baritone.api.behavior.look.IAimProcessor;
import java.util.Optional;
import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationController;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.events.EventManager;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public final class LookBehavior extends Behavior implements TickListener, ILookBehavior {
   private LookTarget target;
   private Rotation serverRotation;
   private final AimProcessor processor;

   public void pig() {
      if (this.target != null) {
         Rotation rotation = this.processor.peekRotation(this.target.rotation);
         this.ctx.player().rotationYaw = rotation.getYaw();
      }
   }

   public IAimProcessor getAimProcessor() {
      return this.processor;
   }

   public Optional getEffectiveRotation() {
      return (Boolean)Baritone.settings().freeLook.value ? Optional.ofNullable(this.serverRotation) : Optional.empty();
   }

   public LookBehavior(Baritone baritone2) {
      super(baritone2);
      this.processor = new AimProcessor(baritone2.getPlayerContext());
      EventManager.getEventBus().subscribe(40, this);
   }

   public void onTick(TickEvent tickEvent) {
      if (tickEvent.getType() == TickEvent.Type.IN) {
         this.processor.tick();
      }
   }

   public void updateTarget(Rotation rotation, boolean flag) {
      this.target = new LookTarget(rotation, LookTargetMode.resolve(flag));
   }

   public void onWorldEvent(WorldEvent worldEvent) {
      this.serverRotation = null;
      this.target = null;
   }

   public void onEarlyTick() {
      if (this.target != null && this.target.mode != LookTargetMode.NONE) {
         RotationController rotationController = Augustus.getInstance().getRotationController();
         Rotation rotation = this.processor.peekRotation(this.target.rotation);
         net.augustus.utils.rotation.Rotation rotation2 = new net.augustus.utils.rotation.Rotation(MathHelper.wrapAngleTo180_float(rotation.getYaw()), rotation.getPitch());
         rotationController.requestRotation(rotation2, (byte)0, (Module)Augustus.getInstance().getModuleManager().baritone);
         if (this.target.mode == LookTargetMode.CLIENT) {
            YawPitchHelper yawPitchHelper = Augustus.getInstance().getYawPitchHelper();
            yawPitchHelper.realLastYaw = yawPitchHelper.realYaw = rotation2.yaw;
            yawPitchHelper.realLastPitch = yawPitchHelper.realPitch = rotation2.pitch;
         }

         this.target = null;
      }
   }

   public void onSendPacket(PacketEvent packetEvent) {
      if (packetEvent.getPacket2() instanceof C03PacketPlayer) {
         C03PacketPlayer packet2 = (C03PacketPlayer)packetEvent.getPacket2();
         if (packet2 instanceof C03PacketPlayer.C04PacketPlayerPosition || packet2 instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
            this.serverRotation = new Rotation(packet2.getYaw(), packet2.getPitch());
         }
      }
   }


   public static class InnerEntry {
   }
}
