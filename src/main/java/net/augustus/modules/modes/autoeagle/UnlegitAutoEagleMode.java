package net.augustus.modules.modes.autoeagle;

import net.augustus.ClientManagers;
import net.augustus.events.EventMouseMove;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.world.scaffold.ScaffoldRaycastResult;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.world.BlockUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiFunction;
import net.augustus.modules.Module;
import net.augustus.modules.legit.AutoEagle;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;

public class UnlegitAutoEagleMode extends AutoEagleMode implements MoveInputListener, TickListener, MouseMoveListener, RayCastListener {
   private final BooleanValue silentRotation;
   private final BooleanValue intave;
   private final BooleanValue midRotation;
   private MovingObjectPosition targetBlockRayHit;
   private boolean isSneakingInput;
   private long lastMouseMoveTime;
   private long lastTickTime;
   private int lastTickDurationMs;
   private int pitchMouseDelta;

   public UnlegitAutoEagleMode(AutoEagle autoEagle, SettingGroup doubleValue) {
       super(autoEagle, doubleValue, "UnLegit");
      BooleanValue booleanValue = new BooleanValue(this.parentModule, "SilentRotation", true, this.modeSetting);
      this.silentRotation = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
      booleanValue = new BooleanValue(this.parentModule, "Intave", false, this.modeSetting);
      this.intave = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
      this.midRotation = (BooleanValue)new BooleanValue(this.parentModule, "MidRotation", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.targetBlockRayHit = null;
      this.isSneakingInput = false;
      this.lastMouseMoveTime = 0L;
      this.lastTickTime = 0L;
      this.lastTickDurationMs = 0;
      this.pitchMouseDelta = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(29);
   }

   @Override
   public void onEnable() {
      this.targetBlockRayHit = null;
      this.isSneakingInput = false;
      this.lastMouseMoveTime = 0L;
      this.lastTickTime = 0L;
      this.lastTickDurationMs = 0;
      this.pitchMouseDelta = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEarlyTick() {
      this.targetBlockRayHit = null;
      if (this.isSneakingInput && ((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         float rotationYaw = mc.thePlayer.rotationYaw;
         ArrayList list = new ArrayList();
         float f = 0.0F;

         for (float i = 72.0F; i < (float)(this.intave.getValue() ? 85 : 90); i += 0.05F) {
            float f2 = RotationUtil.applyPitchGcd(i, ClientManagers.rotationController.getPreviousRotation().pitch);
            if (f2 != f) {
               MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, rotationYaw, f2);
               if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                  && BlockUtil.isSolidBlock(hit.getBlockPos())
                  && hit.sideHit != EnumFacing.DOWN
                  && hit.sideHit != EnumFacing.UP
                  && (double)hit.getBlockPos().getY() < mc.thePlayer.posY
                  && (hit.hitVec.yCoord % 1.0 < 0.88 && hit.hitVec.yCoord % 1.0 > 0.18 || !this.intave.getValue())) {
                  list.add(new ScaffoldRaycastResult(hit, f2));
               }
            }

            f = f2;
         }

         if (!list.isEmpty()) {
            ScaffoldRaycastResult scaffoldRaycastResult = (ScaffoldRaycastResult)list.get(0);
            if (this.intave.getValue()) {
               for (ScaffoldRaycastResult scaffoldRaycastResult2 : (Iterable<ScaffoldRaycastResult>)(list)) {
                  if (scaffoldRaycastResult2 == null || scaffoldRaycastResult2.pitch < scaffoldRaycastResult.pitch) {
                     scaffoldRaycastResult = scaffoldRaycastResult2;
                  }
               }
            } else {
               if (this.midRotation.getValue()) {
                  list.sort(Comparator.comparingDouble(obj -> this.evaluateRaycastCandidate(rotationYaw, (ScaffoldRaycastResult)obj)));
               } else {
                  list.sort(Comparator.comparingDouble(obj2 -> (double)Math.abs(((ScaffoldRaycastResult)obj2).pitch - ClientManagers.rotationController.getPreviousRotation().pitch)));
               }

               scaffoldRaycastResult = (ScaffoldRaycastResult)list.get(0);
            }

            Module parentModule2 = this.parentModule;
            float pitch = scaffoldRaycastResult.pitch;
            ClientManagers.rotationController.requestPitch(pitch, (byte)-2, parentModule2);
            this.pitchMouseDelta = RotationUtil.computeMouseDeltas(0.0F, scaffoldRaycastResult.pitch, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch)[1];
            this.targetBlockRayHit = scaffoldRaycastResult.hitResult;
         }
      }
   }

   @Override
   public void onPostTick() {
      this.lastTickDurationMs = (int)Math.abs(this.lastTickTime - System.currentTimeMillis());
      this.lastTickTime = System.currentTimeMillis();
   }

   @Override
   public void onRayCast() {
      if (this.targetBlockRayHit != null) {
         MovingObjectPosition targetBlockRayHit2 = this.targetBlockRayHit;
         mc.objectMouseOver = targetBlockRayHit2;
      }
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (this.isSneakingInput && ((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         Module parentModule2 = this.parentModule;
         if (ClientManagers.rotationController.isOwnedBy(parentModule2) && !this.silentRotation.getValue()) {
            int n = (int)(System.currentTimeMillis() - this.lastMouseMoveTime);
            double d = (double)this.pitchMouseDelta / (double)this.lastTickDurationMs * (double)n;
            if (this.pitchMouseDelta != 0) {
               event.setDeltaY((int)d);
            }
         }
      }

      this.lastMouseMoveTime = System.currentTimeMillis();
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         ((AutoEagle)this.parentModule).eagling = false;
      } else {
         if (!((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            event.setSneak(true);
         }

         this.isSneakingInput = event.isSneak();
         ((AutoEagle)this.parentModule).eagling = ((AutoEagle)this.parentModule).fastPlace.getValue() && event.isSneak() && ((AutoEagle)this.parentModule).isPitchAboveMinimum();
         if (event.isSneak() || !((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            this.unsneakWhileMoving(event);
         }
      }
   }

   private void unsneakWhileMoving(EventMoveInput event) {
      if (MoveUtil.isMoving() && ((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         event.setSneak(false);
      }
   }

   private double computeHitCenterOffset(ScaffoldRaycastResult scaffoldRaycastResult, float f) {
      MovingObjectPosition hit = RayTraceUtil.rayTraceDefaultReach(1.0F, f, scaffoldRaycastResult.pitch);
      if (hit != null && hit.getBlockPos() != null && hit.hitVec != null) {
         double d = hit.hitVec.yCoord % 1.0;
         return Math.abs(0.5 - d);
      } else {
         return 100.0;
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new UnlegitAutoEagleMode((AutoEagle)obj, (SettingGroup)obj2);
   }

   private double evaluateRaycastCandidate(float f, ScaffoldRaycastResult scaffoldRaycastResult) {
      return this.computeHitCenterOffset(scaffoldRaycastResult, f);
   }

}
