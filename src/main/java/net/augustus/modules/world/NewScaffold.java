package net.augustus.modules.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.world.scaffold.ScaffoldRaycastResult;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.TimeHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class NewScaffold extends Module implements TickListener, MoveInputListener, ClickListener {
   private final RotationUtil rotationUtil;
   private final TimeHelper timeHelper;
   private int lockedMoveTicks;
   private float savedMoveForward;
   private float savedMoveStrafe;
   private boolean savedSneaking;
   private boolean rotationLocked;
   private int predictTicks;
   private MovingObjectPosition lastRayTrace;

   public NewScaffold() {
       super("NewScaffold", "NeuGerüst", new Color(182, 194, 207), Categorys.WORLD);
      this.rotationUtil = new RotationUtil();
      this.timeHelper = new TimeHelper();
      this.savedMoveForward = 0.0F;
      this.savedMoveStrafe = 0.0F;
      this.savedSneaking = false;
      this.rotationLocked = false;
      this.predictTicks = 1;
      this.lastRayTrace = null;
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();
      this.lockedMoveTicks = 0;
      this.predictTicks = 0;
      this.rotationLocked = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(7);
   }

   @Override
   public void onEarlyTick() {
      BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ).down();
      float backwardYaw = this.getBackwardYaw();
      if (this.rotationLocked) {
         backwardYaw = ClientManagers.rotationController.getPreviousRotation().yaw;
      }

      RotationUtil rotationUtil2 = this.rotationUtil;
      float f = 24.0F;
      float f2 = 18.0F;
      float f5 = rotationUtil2.stepYawWithGcd(DefaultRandomGenerator.instance.nextFloat(f2, f), ClientManagers.rotationController.getPreviousRotation().yaw, backwardYaw);
      float pitch = ClientManagers.rotationController.getPreviousRotation().pitch;
      if (!this.timeHelper.reached(200L)) {
         rotationUtil2 = this.rotationUtil;
         float f6 = 24.0F;
         float f7 = 18.0F;
         float f8 = DefaultRandomGenerator.instance.nextFloat(f7, f6);
         float f9 = 80.34F;
         pitch = rotationUtil2.updatePitchWithIntaveSin(f8, ClientManagers.rotationController.getPreviousRotation().pitch, f9);
      }

      boolean flag = !this.rotationLocked;
      if (flag) {
         float f11 = 3.0F;
         float f12 = 1.0F;
         pitch += DefaultRandomGenerator.instance.nextFloat(f12, f11);
         pitch = RotationUtil.applyPitchGcd(pitch, ClientManagers.rotationController.getPreviousRotation().pitch);
      }

      if (flag && !mc.theWorld.getBlockState(pos).getBlock().isFullBlock()) {
         this.predictTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)1);
         double[] doubles = PlayerUtil.predictPlayerPosition(this.predictTicks - 1, mc.thePlayer, f5);
         System.out.println(doubles[0] + "   " + mc.thePlayer.posX + "    :   " + doubles[2] + "  " + mc.thePlayer.posZ);
         Vec3 vec = new Vec3(doubles[0], doubles[1], doubles[2]);
         System.out.println(this.predictTicks);
         ArrayList list = new ArrayList();
         float f13 = 0.0F;

         for (float i = 15.0F; i < 90.0F; i += 0.05F) {
            float f14 = RotationUtil.applyPitchGcd(i, ClientManagers.rotationController.getPreviousRotation().pitch);
            if (f14 != f13) {
               MovingObjectPosition hit = RayCastUtil.rayCastFromPosition(1.0F, vec, f5, f14);
               if (hit != null
                  && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                  && BlockUtil.isSolidBlock(hit.getBlockPos())
                  && hit.sideHit != EnumFacing.DOWN
                  && hit.sideHit != EnumFacing.UP
                  && (double)hit.getBlockPos().getY() < mc.thePlayer.posY) {
                  list.add(new ScaffoldRaycastResult(hit, f14));
               }
            }

            f13 = f14;
         }

         if (!list.isEmpty()) {
            this.lockedMoveTicks = this.predictTicks;
            list.sort(Comparator.comparingDouble(obj -> this.scoreRaycastCandidate(f5, vec, (ScaffoldRaycastResult)obj)));
            ScaffoldRaycastResult scaffoldRaycastResult = (ScaffoldRaycastResult)list.get(0);
            float[] floats = new float[]{f5, scaffoldRaycastResult.pitch};
            ClientManagers.rotationController.requestRotation(floats, (byte)-1, this);
            this.rotationLocked = true;
            return;
         }
      }

      this.rotationLocked = this.lockedMoveTicks > 0;
      ClientManagers.rotationController.requestRotation(new Rotation(f5, pitch), (byte)-1, this);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setMoveFixMode(MoveFixMode.DEFAULT);
      if (this.lockedMoveTicks > 0) {
         event.setMoveStrafe(this.savedMoveForward);
         event.setMoveForward(this.savedMoveStrafe);
         event.setSneak(this.savedSneaking);
      }

      this.lockedMoveTicks--;
      this.savedMoveForward = event.getMoveStrafe();
      this.savedMoveStrafe = event.getMoveForward();
      this.savedSneaking = event.isSneak();
   }

   @Override
   public void onClick(EventClick event) {
      if (this.lockedMoveTicks <= 0) {
         event.addRightClicks((byte)1);
         event.setBlockAllClicks(false);
         event.setIgnoreVanillaClicks(true);
      }
   }

   private double getPitchDeltaScore(ScaffoldRaycastResult scaffoldRaycastResult) {
      return (double)Math.abs(scaffoldRaycastResult.pitch - ClientManagers.rotationController.getPreviousRotation().pitch);
   }

   private double getHitCenterOffset(ScaffoldRaycastResult scaffoldRaycastResult, float f, Vec3 vec) {
      MovingObjectPosition hit = RayCastUtil.rayCastFromPosition(1.0F, vec, f, scaffoldRaycastResult.pitch);
      if (hit != null && hit.getBlockPos() != null && hit.hitVec != null) {
         double d = hit.hitVec.yCoord % 1.0;
         return Math.abs(0.5 - d);
      } else {
         return 100.0;
      }
   }

   private float getBackwardYaw() {
      return Augustus.getInstance().getYawPitchHelper().realYaw - 180.0F;
   }

   private double scoreRaycastCandidate(float f, Vec3 vec, ScaffoldRaycastResult scaffoldRaycastResult) {
      return this.getHitCenterOffset(scaffoldRaycastResult, f, vec);
   }


}
