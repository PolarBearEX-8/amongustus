package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.setting.SettingGroup;
import client.util.CancellableEventAdapter;
import client.util.CancellableEventCallbackAdapter;
import client.util.CoreStringEntry;
import client.util.LvlManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class JesusSpartanMode extends CoreStringEntry implements CancellableEventCallbackAdapter {
   public Vec3 resolveVec3(double offset, boolean flag, Vec3 vec) {
      if (minecraft.thePlayer.isRiding()) {
         return vec;
      } else {
         double offset2;
         if (flag && Math.abs(vec.yCoord - 0.005) >= 0.003 && Math.abs(vec.yCoord - offset / 16.0) < 0.003) {
            offset2 = -0.003;
         } else {
            offset2 = vec.yCoord - offset / 16.0;
         }

         return new Vec3(vec.xCoord, offset2, vec.zCoord);
      }
   }

   @Override
   public void updateState() {
   }

   @Override
   public void applyCancellableEventAdapter(CancellableEventAdapter event) {
      short shortValue = 19004;
      int count = 620446901;
      if (this.isActive()) {
         event.cancel();
         boolean flag = minecraft.thePlayer.motionY <= 0.0;
         double posY = minecraft.thePlayer.posY;
         float factor = minecraft.thePlayer.isRiding() ? 0.9F : 0.8F;
         float factor2 = 0.02F;
         float factor3 = (float)LvlManager.computeCount2(minecraft.thePlayer);
         if (factor3 > 3.0F) {
            factor3 = 3.0F;
         }

         if (!minecraft.thePlayer.onGround) {
            factor3 *= 0.5F;
         }

         if (factor3 > 0.0F) {
            factor += (0.54600006F - factor) * factor3 / 3.0F;
            factor2 += (minecraft.thePlayer.getScaleFactor6() - factor2) * factor3 / 3.0F;
         }

         Vec3 vec = new Vec3((double)event.getFactor(), 0.0, (double)event.getFactor2());
         this.applyVec3(factor2, vec);
         minecraft.thePlayer.applyDouble(minecraft.thePlayer.motionX, minecraft.thePlayer.motionY, minecraft.thePlayer.motionZ);
         Vec3 vec2 = new Vec3(minecraft.thePlayer.motionX, minecraft.thePlayer.motionY, minecraft.thePlayer.motionZ);
         if (minecraft.thePlayer.isCollidedHorizontally && minecraft.thePlayer.canExecute9()) {
            vec2 = new Vec3(vec2.xCoord, 0.2, vec2.zCoord);
         }

         minecraft.thePlayer.motionX = vec2.xCoord * (double)factor;
         minecraft.thePlayer.motionY = vec2.yCoord * 0.8F;
         minecraft.thePlayer.motionZ = vec2.zCoord * (double)factor;
         Vec3 vec3 = new Vec3(minecraft.thePlayer.motionX, minecraft.thePlayer.motionY, minecraft.thePlayer.motionZ);
         double offset = 0.08F;
         Vec3 vec4 = this.resolveVec3(offset, flag, vec3);
         minecraft.thePlayer.motionX = vec4.xCoord;
         minecraft.thePlayer.motionY = vec4.yCoord;
         minecraft.thePlayer.motionZ = vec4.zCoord;
         if (minecraft.thePlayer.isCollidedHorizontally
            && minecraft.thePlayer.checkDoubleByDouble(minecraft.thePlayer.motionX, minecraft.thePlayer.motionY + 0.6F - minecraft.thePlayer.posY + posY, minecraft.thePlayer.motionZ)) {
            minecraft.thePlayer.motionY = 0.3F;
         }
      }
   }

   @Override
   public void clear() {
   }

   private static Vec3 resolveVec3(Vec3 vec, float factor, float factor2) {
      double value = vec.getValue();
      if (value < 1.0E-7) {
         return new Vec3(0.0, 0.0, 0.0);
      } else {
         Vec3 vec3 = (value > 1.0 ? vec.normalize() : vec).getVec3((double)factor);
         float factor3 = MathHelper.abs(factor2 * (float) (Math.PI / 180.0));
         float factor4 = MathHelper.cos(factor2 * (float) (Math.PI / 180.0));
         return new Vec3(vec3.xCoord * (double)factor4 - vec3.zCoord * (double)factor3, vec3.yCoord, vec3.zCoord * (double)factor4 + vec3.xCoord * (double)factor3);
      }
   }

   public void applyVec3(float factor, Vec3 vec) {
      float yaw = minecraft.thePlayer.rotationYaw;
      Vec3 vec2 = resolveVec3(vec, factor, yaw);
      minecraft.thePlayer.motionX = minecraft.thePlayer.motionX + vec2.xCoord;
      minecraft.thePlayer.motionY = minecraft.thePlayer.motionY + vec2.yCoord;
      minecraft.thePlayer.motionZ = minecraft.thePlayer.motionZ + vec2.zCoord;
   }

   @Override
   public void updateState2() {
      byte byteValue = 38;
      this.applyIndex(byteValue);
   }

   public JesusSpartanMode(Jesus jesus, SettingGroup settingGroup) {
      super(jesus, settingGroup, "1.13");
   }
}
