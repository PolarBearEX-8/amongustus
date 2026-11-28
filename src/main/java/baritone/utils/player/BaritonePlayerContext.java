package baritone.utils.player;

import baritone.Baritone;
import baritone.api.cache.IWorldData;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.IPlayerController;
import baritone.api.utils.Rotation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public final class BaritonePlayerContext implements IPlayerContext {
   private final Baritone baritone;
   private final Minecraft mc;
   private final IPlayerController playerController;

   @Override
   public EntityPlayerSP player() {
      return this.mc.thePlayer;
   }

   @Override
   public Minecraft getMc() {
      return this.mc;
   }

   @Override
   public IPlayerController playerController() {
      return this.playerController;
   }

   @Override
   public BetterBlockPos viewerPos() {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      return renderViewEntity == null ? this.playerFeet() : BetterBlockPos.from(new BlockPos(renderViewEntity));
   }

   @Override
   public IWorldData worldData() {
      return this.baritone.getWorldProvider2().getCurrentWorld2();
   }

   public BaritonePlayerContext(Baritone baritone2, Minecraft mc2) {
      this.baritone = baritone2;
      this.mc = mc2;
      this.playerController = new BaritonePlayerController(mc2);
   }

   @Override
   public MovingObjectPosition objectMouseOver() {
      return this.mc.objectMouseOver;
   }

   @Override
   public Rotation playerRotations() {
      return (Rotation)(this.baritone.getLookBehavior2().getEffectiveRotation().orElseGet(() -> IPlayerContext.super.playerRotations()));
   }

   @Override
   public World world() {
      return this.mc.theWorld;
   }
}
