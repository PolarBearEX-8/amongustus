package net.augustus.modules.modes.anticheat.aim;

import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.utils.rotation.Rotation;
import net.minecraft.network.Packet;

public class TrackAimCheck extends AbstractAimTrackCheck {
   private final StatisticalSampleBuffer yawRatioBuffer;
   private final StatisticalSampleBuffer yawAccelBuffer;
   private final StatisticalSampleBuffer pitchRatioBuffer;
   private final StatisticalSampleBuffer pitchDiffBuffer;
   private final StatisticalSampleBuffer pitchDeltaBuffer;
   private final StatisticalSampleBuffer yawErrorBuffer;
   private final StatisticalSampleBuffer pitchErrorBuffer;
   private final StatisticalSampleBuffer yawDeltaBuffer;
   private final StatisticalSampleBuffer pitchAccelBuffer;
   private final StatisticalSampleBuffer yawDiffBuffer;

   public TrackAimCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
       super(str, antiCheatPlayerData2);
      this.yawDeltaBuffer = new StatisticalSampleBuffer(40);
      this.pitchDeltaBuffer = new StatisticalSampleBuffer(40);
      this.yawAccelBuffer = new StatisticalSampleBuffer(40);
      this.pitchAccelBuffer = new StatisticalSampleBuffer(40);
      this.yawDiffBuffer = new StatisticalSampleBuffer(40);
      this.pitchDiffBuffer = new StatisticalSampleBuffer(40);
      this.yawErrorBuffer = new StatisticalSampleBuffer(40);
      this.yawRatioBuffer = new StatisticalSampleBuffer(40);
      this.pitchRatioBuffer = new StatisticalSampleBuffer(40);
      this.pitchErrorBuffer = new StatisticalSampleBuffer(40);
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTrackSample(
      Rotation rotation, Rotation rotation2, Rotation rotation3, Rotation rotation4, Rotation rotation5, Rotation rotation6, Rotation rotation7, Rotation rotation8, Rotation rotation9
   ) {
   }
}
