package cc.baritone.utils;

import augustus.modules.misc.Baritone;
import cc.baritone.api.AuxiliaryItemEntry;
import cc.baritone.api.InternalItemEntry;
import client.Client;
import client.event.EventBusManager;
import client.event.TickListener;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.via.ViaProtocolHelper;
import java.util.Optional;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.util.MathHelper;

public final class TickListenerAdapter extends AuxiliaryItemEntry implements GlobalListener, TickListener {
   public LocalCustom localCustom;
   public DefaultCustom defaultCustom;
   public final CustomClientManager customClientManager;

   @Override
   public void forEach(HeuristicProcessor heuristicProcessor) {
      if (heuristicProcessor.getPacket() instanceof C03PacketPlayer) {
         C03PacketPlayer packet = (C03PacketPlayer)heuristicProcessor.getPacket();
         if (packet instanceof C04PacketPlayerPosition || packet instanceof C06PacketPlayerPosLook) {
            this.defaultCustom = new DefaultCustom(packet.getYawRotation5(), packet.getYawRotation());
         }
      }
   }

   @Override
   public void applySupplementalCustom(SupplementalCustom supplementalCustom) {
      this.defaultCustom = null;
      this.localCustom = null;
   }

   @Override
   public void applyDefaultCustom(DefaultCustom defaultCustom2, boolean flag) {
      this.localCustom = new LocalCustom(defaultCustom2, BreadthFirstDispatcher.resolveBreadthFirstDispatcher(flag));
   }

   public void updateState() {
      if (this.localCustom != null) {
         DefaultCustom defaultCustom2 = this.customClientManager.resolveDefaultCustom(this.localCustom.defaultCustom);
         this.staticListener.getEntityPlayerSP().rotationYaw = defaultCustom2.getFactor();
      }
   }

   public Optional getOptional() {
      return (Boolean)BaritoneManager.getSettings().freeLook.dataRef ? Optional.ofNullable(this.defaultCustom) : Optional.empty();
   }

   @Override
   public StandardListener getStandardListener() {
      return this.customClientManager;
   }

   @Override
   public void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      if (internalItemEntry.getInternalOptionType() == InternalOptionType.IN) {
         this.customClientManager.updateState();
      }
   }

   @Override
   public void onPreTick() {
      if (this.localCustom != null && this.localCustom.breadthFirstDispatcher != BreadthFirstDispatcher.NONE) {
         RotationManager rotationManager = Client.getClient().getRotationManager();
         DefaultCustom defaultCustom2 = this.customClientManager.resolveDefaultCustom(this.localCustom.defaultCustom);
         Rotation rotation = new Rotation(MathHelper.sqrt_float(defaultCustom2.getFactor()), defaultCustom2.getFactor2());
         Baritone baritone = Client.getClient().getModuleManager().baritone;
         byte byteValue = 0;
         rotationManager.applyRotation2(rotation, byteValue, 1898960900, (Module)baritone);
         if (this.localCustom.breadthFirstDispatcher == BreadthFirstDispatcher.CLIENT) {
            ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
            viaProtocolHelper.scaleFactor3 = viaProtocolHelper.scaleFactor1 = rotation.yaw;
            viaProtocolHelper.scaleFactor = viaProtocolHelper.scaleFactor2 = rotation.pitch;
         }

         this.localCustom = null;
      }
   }

   public TickListenerAdapter(BaritoneManager baritoneManager) {
      super(baritoneManager);
      this.customClientManager = new CustomClientManager(baritoneManager.getStaticListener());
      EventBusManager.getDietrichEvents2().subscribe(40, this);
   }
}
