package net.augustus.modules.modes.jesus;

import net.augustus.events.EventLiquidJump;
import net.augustus.events.EventSwim;
import net.augustus.events.listeners.LiquidJumpListener;
import net.augustus.events.listeners.SwimListener;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.SettingGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Swim113JesusMode extends AbstractJesusMode implements SwimListener, LiquidJumpListener {

   public Swim113JesusMode(Jesus jesus, SettingGroup doubleValue) {
      super(jesus, doubleValue, "1.13");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(38);
      this.toggleEventSubscription(67);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public void onSwim(EventSwim event) {
      if (this.shouldApplyJesus()) {
         event.cancel();
         boolean flag = mc.thePlayer.motionY <= 0.0;
         double posY = mc.thePlayer.posY;
         float f = mc.thePlayer.isSprinting() ? 0.9F : 0.8F;
         float f2 = 0.02F;
         float depthStriderModifier = (float)EnchantmentHelper.getDepthStriderModifier(mc.thePlayer);
         if (depthStriderModifier > 3.0F) {
            depthStriderModifier = 3.0F;
         }

         if (!mc.thePlayer.onGround) {
            depthStriderModifier *= 0.5F;
         }

         if (depthStriderModifier > 0.0F) {
            f += (0.54600006F - f) * depthStriderModifier / 3.0F;
            f2 += (mc.thePlayer.getAIMoveSpeed() - f2) * depthStriderModifier / 3.0F;
         }

         this.applyRelativeMovement(f2, new Vec3((double)event.getStrafe(), 0.0, (double)event.getForward()));
         mc.thePlayer.moveEntity(mc.thePlayer.motionX, mc.thePlayer.motionY, mc.thePlayer.motionZ);
         Vec3 vec2 = new Vec3(mc.thePlayer.motionX, mc.thePlayer.motionY, mc.thePlayer.motionZ);
         if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.isOnLadder()) {
            vec2 = new Vec3(vec2.xCoord, 0.2, vec2.zCoord);
         }

         mc.thePlayer.motionX = vec2.xCoord * (double)f;
         mc.thePlayer.motionY = vec2.yCoord * 0.800000011920929;
         mc.thePlayer.motionZ = vec2.zCoord * (double)f;
         Vec3 vec3 = new Vec3(mc.thePlayer.motionX, mc.thePlayer.motionY, mc.thePlayer.motionZ);
         Vec3 vec4 = this.applyFluidFallingMovement(0.07999999821186066, flag, vec3);
         mc.thePlayer.motionX = vec4.xCoord;
         mc.thePlayer.motionY = vec4.yCoord;
         mc.thePlayer.motionZ = vec4.zCoord;
         if (mc.thePlayer.isCollidedHorizontally
            && mc.thePlayer.isOffsetPositionInLiquid(mc.thePlayer.motionX, mc.thePlayer.motionY + 0.6000000238418579 - mc.thePlayer.posY + posY, mc.thePlayer.motionZ)) {
            mc.thePlayer.motionY = 0.30000001192092896;
         }
      }
   }

   public void applyRelativeMovement(float f, Vec3 vec) {
      Vec3 inputVector = getInputVector(vec, f, mc.thePlayer.rotationYaw);
      mc.thePlayer.motionX = mc.thePlayer.motionX + inputVector.xCoord;
      mc.thePlayer.motionY = mc.thePlayer.motionY + inputVector.yCoord;
      mc.thePlayer.motionZ = mc.thePlayer.motionZ + inputVector.zCoord;
   }

   private static Vec3 getInputVector(Vec3 vec, float f, float f2) {
      double d = vec.lengthSquared();
      if (d < 1.0E-7) {
         return new Vec3(0.0, 0.0, 0.0);
      } else {
         Vec3 vec2 = (d > 1.0 ? vec.normalize() : vec).scale((double)f);
         float f3 = MathHelper.sin(f2 * 0.017453292F);
         float f4 = MathHelper.cos(f2 * 0.017453292F);
         return new Vec3(vec2.xCoord * (double)f4 - vec2.zCoord * (double)f3, vec2.yCoord, vec2.zCoord * (double)f4 + vec2.xCoord * (double)f3);
      }
   }

   public Vec3 applyFluidFallingMovement(double d, boolean flag, Vec3 vec) {
      if (mc.thePlayer.isSprinting()) {
         return vec;
      } else {
         double d2;
         if (flag && Math.abs(vec.yCoord - 0.005) >= 0.003 && Math.abs(vec.yCoord - d / 16.0) < 0.003) {
            d2 = -0.003;
         } else {
            d2 = vec.yCoord - d / 16.0;
         }

         return new Vec3(vec.xCoord, d2, vec.zCoord);
      }
   }

   @Override
   public void onLiquidJump(EventLiquidJump event) {
      event.setWaterMotionY(0.03999999910593033);
   }

}
