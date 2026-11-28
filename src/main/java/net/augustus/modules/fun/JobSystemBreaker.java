package net.augustus.modules.fun;

import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.Set;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventRenderBlock;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class JobSystemBreaker extends Module implements RenderBlockListener, TickListener {
   private static final Set GOLD_ORE_POSITIONS = Sets.newConcurrentHashSet();
   private final DoubleValue range;

   public JobSystemBreaker() {
       super("JobSystemBreaker", "JobTerminierere", new Color(66, 135, 245), Categorys.FUN);
      this.range = new DoubleValue(this, "Range", 6.0, 4.5, 6.0, 2);
   }

   @Override
   public void onEnable() {
      GOLD_ORE_POSITIONS.clear();
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void onDisable() {
      GOLD_ORE_POSITIONS.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(30);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onRenderBlock(EventRenderBlock event) {
      if (event.getBlockState() != null && event.getBlockState().getBlock().equals(Blocks.gold_ore)) {
         BlockPos immutableBlockPos = event.getImmutableBlockPos();
         if (immutableBlockPos == null) {
            return;
         }

         GOLD_ORE_POSITIONS.add(immutableBlockPos);
      }
   }

   @Override
   public void onTick() {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      GOLD_ORE_POSITIONS.removeIf(obj -> {
         if (Math.sqrt(((BlockPos)obj).distanceSqToCenter(positionEyes.xCoord, positionEyes.yCoord, positionEyes.zCoord)) < Math.max(1.0, this.range.getValue())) {
            NetHandlerPlayClient netHandler = mc.getNetHandler();
            C07PacketPlayerDigging.Action startdestroyblock = C07PacketPlayerDigging.Action.START_DESTROY_BLOCK;
            EnumFacing[] values = EnumFacing.getValues();
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            int n = EnumFacing.getValues().length - 1;
            netHandler.addToSendQueue(new C07PacketPlayerDigging(startdestroyblock, (BlockPos)obj, values[instance.nextInt((byte)0, n)]));
            netHandler = mc.getNetHandler();
            startdestroyblock = C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK;
            values = EnumFacing.getValues();
            instance = DefaultRandomGenerator.instance;
            int n2 = EnumFacing.getValues().length - 1;
            netHandler.addToSendQueue(new C07PacketPlayerDigging(startdestroyblock, (BlockPos)obj, values[instance.nextInt((byte)0, n2)]));
            return true;
         } else {
            return false;
         }
      });
   }

}
