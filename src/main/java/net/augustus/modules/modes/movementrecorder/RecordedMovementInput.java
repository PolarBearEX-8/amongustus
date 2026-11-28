package net.augustus.modules.modes.movementrecorder;

import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.codec.JsonPropertyCodec;
import com.google.gson.JsonObject;

public class RecordedMovementInput {
   private float yaw;
   private float pitch;
   private float forward;
   private float strafe;
   private boolean sprinting;
   private boolean jumping;
   private boolean sneaking;

   public RecordedMovementInput copy() {
      RecordedMovementInput recordedMovementInput = new RecordedMovementInput();
      recordedMovementInput.yaw = this.yaw;
      recordedMovementInput.pitch = this.pitch;
      recordedMovementInput.forward = this.forward;
      recordedMovementInput.strafe = this.strafe;
      recordedMovementInput.sprinting = this.sprinting;
      recordedMovementInput.jumping = this.jumping;
      recordedMovementInput.sneaking = this.sneaking;
      return recordedMovementInput;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      JsonPropertyCodec floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float yaw2 = this.yaw;
      floatCodec.write("Y", yaw2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float pitch2 = this.pitch;
      floatCodec.write("P", pitch2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float forward2 = this.forward;
      floatCodec.write("F", forward2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float strafe2 = this.strafe;
      floatCodec.write("S", strafe2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean sprinting2 = this.sprinting;
      floatCodec.write("SP", sprinting2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean jumping2 = this.jumping;
      floatCodec.write("J", jumping2, json);
      floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean sneaking2 = this.sneaking;
      floatCodec.write("SN", sneaking2, json);
      return json;
   }

   public void fromJson(JsonObject json) {
      JsonPropertyCodec floatCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float f = 1.0F;
      Float f2 = f;
      this.yaw = (Float)floatCodec.read("Y", f2, json);
      JsonPropertyCodec floatCodec2 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      f = 1.0F;
      Float f3 = f;
      this.pitch = (Float)floatCodec2.read("P", f3, json);
      JsonPropertyCodec floatCodec3 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      Float f4 = 1.0F;
      this.forward = (Float)floatCodec3.read("F", f4, json);
      JsonPropertyCodec floatCodec4 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getFloatCodec();
      f = 1.0F;
      Float f6 = f;
      this.strafe = (Float)floatCodec4.read("S", f6, json);
      JsonPropertyCodec booleanCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean bool = false;
      this.sprinting = (Boolean)booleanCodec.read("SP", bool, json);
      JsonPropertyCodec booleanCodec2 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean bool3 = false;
      this.jumping = (Boolean)booleanCodec2.read("J", bool3, json);
      JsonPropertyCodec booleanCodec3 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean bool5 = false;
      this.sneaking = (Boolean)booleanCodec3.read("SN", bool5, json);
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setPitch(float f) {
      this.pitch = f;
   }

   public float getForward() {
      return this.forward;
   }

   public void setForward(float f) {
      this.forward = f;
   }

   public float getStrafe() {
      return this.strafe;
   }

   public void setStrafe(float f) {
      this.strafe = f;
   }

   public boolean isSprinting() {
      return this.sprinting;
   }

   public void setSprinting(boolean flag) {
      this.sprinting = flag;
   }

   public boolean isJumping() {
      return this.jumping;
   }

   public void setJumping(boolean flag) {
      this.jumping = flag;
   }

   public boolean isSneaking() {
      return this.sneaking;
   }

   public void setSneaking(boolean flag) {
      this.sneaking = flag;
   }

   @Override
   public String toString() {
      return "Yaw: "
         + this.yaw
         + ", Pitch: "
         + this.pitch
         + ", Forward: "
         + this.forward
         + ", Strafe: "
         + this.strafe
         + ", Sprinting: "
         + this.sprinting
         + ", Jump: "
         + this.jumping
         + ", Sneak: "
         + this.sneaking;
   }

}
