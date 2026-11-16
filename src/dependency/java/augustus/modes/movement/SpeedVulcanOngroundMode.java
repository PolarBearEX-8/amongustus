package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.Client;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import client.util.SimulationSnapshot;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class SpeedVulcanOngroundMode extends SpeedModeHandler {
   public boolean enabled = false;

   @Override
   public void onMove(EventMove event) {
      if (this.enabled) {
         event.setState(false);
      }
   }

   @Override
   public void onPacket(EventPacket packet) {
      if (packet.getPacket() instanceof C03PacketPlayer
         && MinecraftAccessor.minecraft.thePlayer.onGround
         && (
            MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor != 0.0F
               || MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2 != 0.0F
         )) {
         if (MinecraftAccessor.minecraft.thePlayer.hurtTime > 0) {
            return;
         }

         if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state) {
            return;
         }

         C03PacketPlayer packet2 = (C03PacketPlayer)packet.getPacket();
         if (packet2.getCoordinate() % 0.015625 == 0.0) {
            if ((double)MinecraftAccessor.minecraft
                  .theWorld
                  .getIBlockState2(
                     MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY - 1.0, MinecraftAccessor.minecraft.thePlayer.posZ
                  )
                  .getBlock()
                  .scaleFactor
               > 0.601) {
               return;
            }

            packet2.setCoordinate(packet2.getCoordinate() + 1.0E-4);
            if (MinecraftAccessor.minecraft.thePlayer.ticksExisted % 2 == 0) {
               packet2.setOnGround(false);
               this.enabled = true;
            } else {
               this.enabled = false;
            }
         }
      }
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
   }

   @Override
   public void processChar() {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         if (!(
            (double)MinecraftAccessor.minecraft
                  .theWorld
                  .getIBlockState2(
                     MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY - 1.0, MinecraftAccessor.minecraft.thePlayer.posZ
                  )
                  .getBlock()
                  .scaleFactor
               > 0.601
         )) {
            if (MinecraftAccessor.minecraft.thePlayer.hurtTime <= 0) {
               if (!MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state) {
                  float factor = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor;
                  float factor2 = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2;
                  double[] offsetArray = MoveUtil.resolveDoubleArray(1.0, factor, factor2, Client.getClient().getViaProtocolHelper().scaleFactor1);
                  SimulationSnapshot simulationSnapshot = new SimulationSnapshot(offsetArray[0], offsetArray[1]);
                  if (simulationSnapshot.sqrt() != 0.0) {
                     SimulationSnapshot simulationSnapshot2 = simulationSnapshot.getSimulationSnapshot();
                     double offset = 0.0;
                     if (MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion2) != null) {
                        offset = 4.0;
                     }

                     double offset2 = 0.2975
                        + offset * 0.075F
                        + (MinecraftAccessor.minecraft.thePlayer.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble() - 0.2F) * 3.5;
                     if (offset2 < 0.2975) {
                        offset2 = 0.2975 + offset * 0.075F;
                     }

                     SimulationSnapshot simulationSnapshot3;
                     if (MinecraftAccessor.minecraft.thePlayer.ticksExisted % 2 == 0) {
                        double offset3 = offset2 + 0.323;
                        simulationSnapshot3 = simulationSnapshot2.resolveSimulationSnapshotByDouble(offset3);
                     } else {
                        simulationSnapshot3 = simulationSnapshot2.resolveSimulationSnapshotByDouble(offset2);
                     }

                     double value = simulationSnapshot3.value;
                     event.setCoordOffset2(value);
                     double value2 = simulationSnapshot3.value2;
                     event.setCoordOffset(value2);
                  }
               }
            }
         }
      }
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedVulcanOngroundMode(String text, Speed speed) {
      super(text, speed);
   }
}
