package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.SettingGroup;
import client.util.IBlockState;
import client.util.InternalStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class VelocityPolarMode extends InternalStringArrayEntry implements PacketListener {
   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityPolarMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (packet.getPacket() instanceof S12PacketEntityVelocity && minecraft.thePlayer != null && minecraft.theWorld != null && minecraft.thePlayer.onGround) {
         int count = MathHelper.ceiling_double_int(minecraft.thePlayer.posX);
         int count2 = MathHelper.ceiling_double_int(minecraft.thePlayer.posY + 2.0);
         int count3 = MathHelper.ceiling_double_int(minecraft.thePlayer.posZ);
         IBlockState iBlockState = minecraft.theWorld.getIBlockState(count, count2, count3);
         AxisAlignedBB boundingBox = iBlockState.getBlock().processAction(minecraft.theWorld, iBlockState, count, count2, count3);
         if (boundingBox != null
            && minecraft.thePlayer.getAxisAlignedBB() != null
            && minecraft.thePlayer.getBoundingBox2().expand(0.0, (double)(2.0F - minecraft.thePlayer.height) + 0.05, 0.0).intersectsWith(boundingBox)) {
            ((S12PacketEntityVelocity)packet.getPacket()).setOnGround1(false);
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
   }

   public VelocityPolarMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "PolarBlock");
   }
}
