package net.augustus.modules.modes.velocity;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class PolarBlockVelocityMode extends VelocityMode implements PacketListener {

   public PolarBlockVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "PolarBlock");
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new PolarBlockVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity() && !event.isCancelled()) {
         if (event.getPacket() instanceof S12PacketEntityVelocity && mc.thePlayer != null && mc.theWorld != null && mc.thePlayer.onGround) {
            int n = MathHelper.floor_double(mc.thePlayer.posX);
            int n2 = MathHelper.floor_double(mc.thePlayer.posY + 2.0);
            int n3 = MathHelper.floor_double(mc.thePlayer.posZ);
            IBlockState blockState2 = mc.theWorld.getBlockState2(n, n2, n3);
            AxisAlignedBB collisionBoundingBox = blockState2.getBlock().getCollisionBoundingBox(mc.theWorld, blockState2, n, n2, n3);
            if (collisionBoundingBox != null
               && mc.thePlayer.getEntityBoundingBox() != null
               && mc.thePlayer.getEntityBoundingBox().offset(0.0, (double)(2.0F - mc.thePlayer.height) + 0.05, 0.0).intersectsWith(collisionBoundingBox)) {
               ((S12PacketEntityVelocity)event.getPacket()).setApplyHorizontalVelocity(false);
            }
         }
      }
   }

}
