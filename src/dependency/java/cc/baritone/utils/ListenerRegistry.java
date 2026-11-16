package cc.baritone.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public final class ListenerRegistry implements StaticListener {
   public final BaritoneManager baritoneManager;
   public final Minecraft mc;
   public final DefaultListener defaultListener;

   @Override
   public EntityPlayerSP getEntityPlayerSP() {
      return this.mc.thePlayer;
   }

   @Override
   public NativeListener getNativeListener() {
      return this.baritoneManager.getReadmeTxtManager().getDepthFirstEvaluator();
   }

   @Override
   public Minecraft getMc() {
      return this.mc;
   }

   @Override
   public MovingObjectPosition getMovingObjectPosition() {
      return this.mc.objectMouseOver;
   }

   @Override
   public World getWorld() {
      return this.mc.theWorld;
   }

   @Override
   public DefaultListener getDefaultListener() {
      return this.defaultListener;
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      return renderViewEntity == null
         ? this.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
         : BetterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(new BlockPos(renderViewEntity));
   }

   private DefaultCustom getDefaultCustomAsDefaultCustom() {
      return StaticListener.super.getDefaultCustom();
   }

   @Override
   public DefaultCustom getDefaultCustom() {
      return ((DefaultCustom)this.baritoneManager.getTickListenerAdapter().getOptional().orElseGet(this::getDefaultCustomAsDefaultCustom));
   }

   public ListenerRegistry(BaritoneManager baritoneManager2, Minecraft mc2) {
      this.baritoneManager = baritoneManager2;
      this.mc = mc2;
      this.defaultListener = new MinecraftEntry(mc2);
   }
}
