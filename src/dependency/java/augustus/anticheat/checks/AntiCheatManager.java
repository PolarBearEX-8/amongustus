package augustus.anticheat.checks;

import client.util.MinecraftAccessor;
import client.util.SimulatedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public final class AntiCheatManager implements MinecraftAccessor {
   public long timestamp2;
   public double value3;
   public float factor6;
   public Vec3 vec3;
   public long timestamp3;
   public long timestamp;
   public float factor3;
   public float factor5;
   public double value2;
   public EntityLivingBase entityLivingBase;
   public Vec3 vec4;
   public double value;
   public double value4;
   public float factor2;
   public boolean enabled;
   public float factor;
   public float factor4;
   public int count = -1;
   public Vec3 vec2;

   private static float computeFactor(int count2, Entity entity, char character, double offset, double offset2, char character2, double offset3) {
      Vec3 vec32 = SimulatedPlayer.getVec3(entity, (short)20072, -521758288);
      double offset4 = vec32.yCoord + 1.62F - (offset2 + 1.62F);
      double offset5 = vec32.xCoord - offset;
      double offset6 = vec32.zCoord - offset3;
      double offset7 = Math.sqrt(offset5 * offset5 + offset6 * offset6);
      return (float)(-Math.atan2(offset4, offset7) * 180.0 / Math.PI);
   }

   public float getFactor5() {
      return this.factor5;
   }

   public boolean currentTimeMillis(long value5) {
      return System.currentTimeMillis() - this.timestamp3 <= value5;
   }

   public boolean currentTimeMillis() {
      return System.currentTimeMillis() - this.timestamp2 < 2000L;
   }

   public void processAction(int count2) {
      this.count = count2;
      if (this.entityLivingBase != null && this.entityLivingBase.getEntityId() != count2) {
         this.timestamp3 = System.currentTimeMillis();
      }

      this.entityLivingBase = (EntityLivingBase)minecraft.theWorld.getEntityByID(count2);
      this.timestamp = System.currentTimeMillis();
   }

   public float getFactor4() {
      return this.factor4;
   }

   private static float computeFactor(Vec3 vec, double posX, double posY, double posZ) {
      double offset = vec.yCoord + 1.62F - (posY + 1.62F);
      double offset2 = vec.xCoord - posX;
      double offset3 = vec.zCoord - posZ;
      double offset4 = Math.sqrt(offset2 * offset2 + offset3 * offset3);
      return (float)(-Math.atan2(offset, offset4) * 180.0 / Math.PI);
   }

   public void processShort() {
      double posX = this.vec3.xCoord;
      double posY = this.vec3.yCoord;
      double posZ = this.vec3.zCoord;
      double posX2 = this.vec2.xCoord;
      double posY2 = this.vec2.yCoord;
      double posZ2 = this.vec2.zCoord;
      if (this.entityLivingBase != null) {
         Vec3 vec = new Vec3(this.entityLivingBase.posX, this.entityLivingBase.posY, this.entityLivingBase.posZ);
         Vec3 vec5 = new Vec3(this.entityLivingBase.lastTickPosX, this.entityLivingBase.lastTickPosY, this.entityLivingBase.lastTickPosZ);
         this.factor4 = computeFactor(vec, posX, posZ);
         this.factor5 = computeFactor(vec, posX, posY, posZ);
         this.factor = computeFactor(vec5, posX2, posZ2);
         this.factor3 = computeFactor(vec5, posX2, posY2, posZ2);
         this.factor6 = computeFactorByEntity(this.entityLivingBase, posX, posZ);
         char character = 42515;
         char character2 = 40895;
         char character3 = character;
         this.factor2 = computeFactor(11150, this.entityLivingBase, character3, posX, posY, character2, posZ);
         this.vec4 = new Vec3(
            this.entityLivingBase.prevPosX - this.entityLivingBase.posX,
            this.entityLivingBase.prevPosY - this.entityLivingBase.posY,
            this.entityLivingBase.prevPosZ - this.entityLivingBase.posZ
         );
      }

      this.vec2 = new Vec3(this.vec3);
      this.vec3 = new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
   }

   public float getFactor6() {
      return this.factor6;
   }

   public void processAction(double offset) {
      this.value = offset;
   }

   public double getValue() {
      return this.value;
   }

   public boolean currentTimeMillisByTime(long value5) {
      return System.currentTimeMillis() - this.timestamp <= value5;
   }

   public void currentTimeMillis2() {
      this.timestamp2 = System.currentTimeMillis();
   }

   public float getFactor2() {
      return this.factor2;
   }

   private static float computeFactor(Vec3 vec, double offset, double offset2) {
      double offset3 = vec.xCoord - offset;
      return (float)Math.toDegrees(Math.atan2(vec.zCoord - offset2, offset3)) - 90.0F;
   }

   public void updateState() {
      this.count = 0;
      this.entityLivingBase = null;
   }

   public int getIndex() {
      return this.count;
   }

   public Vec3 getVec4() {
      return this.vec4;
   }

   public float getFactor3() {
      return this.factor3;
   }

   private static float computeFactorByEntity(Entity entity, double offset, double offset2) {
      Vec3 vec32 = SimulatedPlayer.getVec3(entity, (short)20072, -521758288);
      double offset3 = vec32.xCoord - offset;
      return (float)Math.toDegrees(Math.atan2(vec32.zCoord - offset2, offset3)) - 90.0F;
   }

   public EntityLivingBase getEntityLivingBase() {
      return this.entityLivingBase;
   }

   public float getFactor() {
      return this.factor;
   }

   public AntiCheatManager() {
      this.vec3 = new Vec3(0.0, 0.0, 0.0);
      this.vec2 = new Vec3(0.0, 0.0, 0.0);
      this.vec4 = new Vec3(0.0, 0.0, 0.0);
      this.timestamp = 0L;
      this.timestamp3 = 0L;
      this.timestamp2 = 0L;
   }
}
