package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.Setting;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.AxisAlignedBB;

public class SafeWalk extends Module implements SafeWalkListener, MoveInputListener {
   private final StringValue mode;
   private final BooleanValue inAir;
   private final BooleanValue edgeCheck;
   private final BooleanValue activateOnlyIfLookingDown;
   private final DoubleValue pitchToActivate;

   public SafeWalk() {
       super("SafeWalk", "SichererWeg", new Color(115, 135, 26), Categorys.MOVEMENT);
      this.mode = new StringValue(this, "Mode", "Basic", new String[]{"Basic", "Sneak"});
      this.inAir = (BooleanValue)new BooleanValue(this, "InAir", true).withDisplayName("In air");
      BooleanValue booleanValue2 = new BooleanValue(this, "EdgeCheck", true);
      BooleanValue inAir2 = this.inAir;
      this.edgeCheck = (BooleanValue)((BooleanValue)booleanValue2.withVisibility(() -> inAir2.getValue())).withDisplayName("Edge Check");
      this.activateOnlyIfLookingDown = new BooleanValue(this, "Activate only if looking down", false);
      this.pitchToActivate = new DoubleValue(this, "Pitch to activate", 70.0, 0.0, 90.0, 0);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(33);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onPreSafeWalk(EventSafeWalk event) {
      if (this.passesPitchCondition()) {
         Block block = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock();
         Block block2 = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ).getBlock();
         if (mc.thePlayer.onGround) {
            if (this.mode.getSelectedValue().equals("Basic")) {
               event.setSafeWalk(true);
            }
         } else if (this.inAir.getValue() && (block instanceof BlockAir && block2 instanceof BlockAir || !this.edgeCheck.getValue())) {
            event.setSafeWalk(true);
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.mode.getSelectedValue().equals("Sneak") && !this.passesPitchCondition()) {
         Block block = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock();
         Block block2 = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ).getBlock();
         double[] doubles = PlayerUtil.predictPlayerPosition(0, mc.thePlayer);
         float f = mc.thePlayer.width / 2.0F;
         AxisAlignedBB box = new AxisAlignedBB(
            doubles[0] - (double)f,
            doubles[1],
            doubles[2] - (double)f,
            doubles[0] + (double)f,
            doubles[1] + (double)mc.thePlayer.height,
            doubles[2] + (double)f
         );
         boolean flag = false;
         if (!mc.thePlayer.onGround && block instanceof BlockAir && block2 instanceof BlockAir || !this.edgeCheck.getValue()) {
            box.offset(0.0, mc.thePlayer.lastGroundY - mc.thePlayer.getEntityBoundingBox().minY, 0.0);
            flag = true;
         }

         if (mc.thePlayer.onGround) {
            flag = true;
         }

         if (flag && mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, box.offset(0.0, -0.51, 0.0).expand(-0.22, 0.0, -0.22)).isEmpty()) {
            event.setSneak(true);
         }
      }
   }

   private boolean passesPitchCondition() {
      return !this.activateOnlyIfLookingDown.getValue() || (double)mc.thePlayer.rotationPitch <= this.pitchToActivate.getValue();
   }


}
