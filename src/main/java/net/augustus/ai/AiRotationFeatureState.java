package net.augustus.ai;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;

public final class AiRotationFeatureState {
   private boolean prevPlayerOnGround;
   private float lastYawDelta;
   private String jumpPhase;
   private int prevPlayerHurtTime;
   private int prevTargetHurtTime;
   private double prevTargetY;
   private float lastPitchDelta;
   private double prevTargetZ;
   private double prevTargetX;
   private boolean initialized;

   public void setLastRotationDelta(float f, float f2) {
      this.lastYawDelta = f;
      this.lastPitchDelta = f2;
   }

   public void reset() {
      this.initialized = false;
      this.prevTargetX = 0.0;
      this.prevTargetY = 0.0;
      this.prevTargetZ = 0.0;
      this.prevPlayerHurtTime = 0;
      this.prevTargetHurtTime = 0;
      this.prevPlayerOnGround = true;
      this.jumpPhase = "ground";
      this.lastYawDelta = 0.0F;
      this.lastPitchDelta = 0.0F;
   }

   public double getPrevTargetY() {
      return this.prevTargetY;
   }

   public void update(EntityPlayerSP player, EntityLivingBase entity, String str) {
      this.prevTargetX = entity.posX;
      this.prevTargetY = entity.posY;
      this.prevTargetZ = entity.posZ;
      this.prevPlayerHurtTime = player.hurtTime;
      this.prevTargetHurtTime = entity.hurtTime;
      this.prevPlayerOnGround = player.onGround;
      this.jumpPhase = str;
   }

   public double getPrevTargetX() {
      return this.prevTargetX;
   }

   public int getPrevPlayerHurtTime() {
      return this.prevPlayerHurtTime;
   }

   public float getLastPitchDelta() {
      return this.lastPitchDelta;
   }

   public int getPrevTargetHurtTime() {
      return this.prevTargetHurtTime;
   }

   public void syncTarget(EntityLivingBase entity) {
      this.prevTargetX = entity.posX;
      this.prevTargetY = entity.posY;
      this.prevTargetZ = entity.posZ;
      this.prevTargetHurtTime = entity.hurtTime;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public boolean isPrevPlayerOnGround() {
      return this.prevPlayerOnGround;
   }

   public float getLastYawDelta() {
      return this.lastYawDelta;
   }

   public double getPrevTargetZ() {
      return this.prevTargetZ;
   }

   public AiRotationFeatureState() {
       super();
      this.reset();
   }

   public String getJumpPhase() {
      return this.jumpPhase;
   }

   public void initialize(EntityPlayerSP player, EntityLivingBase entity) {
      this.prevTargetX = entity.posX;
      this.prevTargetY = entity.posY;
      this.prevTargetZ = entity.posZ;
      this.prevPlayerHurtTime = player.hurtTime;
      this.prevTargetHurtTime = entity.hurtTime;
      this.prevPlayerOnGround = player.onGround;
      this.jumpPhase = player.onGround ? "ground" : "descending";
      this.initialized = true;
   }
}
