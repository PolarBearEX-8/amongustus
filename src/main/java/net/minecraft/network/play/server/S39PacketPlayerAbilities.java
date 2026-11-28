package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S39PacketPlayerAbilities implements Packet {
   private boolean invulnerable;
   private boolean flying;
   private boolean allowFlying;
   private boolean creativeMode;
   private float flySpeed;
   private float walkSpeed;

   public float getWalkSpeed() {
      return this.walkSpeed;
   }

   public void setInvulnerable(boolean flag) {
      this.invulnerable = flag;
   }

   public boolean isFlying() {
      return this.flying;
   }

   public void setFlySpeed(float f) {
      this.flySpeed = f;
   }

   public void setFlying(boolean flag) {
      this.flying = flag;
   }

   public void setCreativeMode(boolean flag) {
      this.creativeMode = flag;
   }

   public boolean isInvulnerable() {
      return this.invulnerable;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void setAllowFlying(boolean flag) {
      this.allowFlying = flag;
   }

   public void setWalkSpeed(float f) {
      this.walkSpeed = f;
   }

   public S39PacketPlayerAbilities(PlayerCapabilities playerCapabilities) {
      this.setInvulnerable(playerCapabilities.disableDamage);
      this.setFlying(playerCapabilities.isFlying);
      this.setAllowFlying(playerCapabilities.allowFlying);
      this.setCreativeMode(playerCapabilities.isCreativeMode);
      this.setFlySpeed(playerCapabilities.getFlySpeed());
      this.setWalkSpeed(playerCapabilities.getWalkSpeed());
   }

   public boolean isAllowFlying() {
      return this.allowFlying;
   }

   public boolean isCreativeMode() {
      return this.creativeMode;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      byte b = buf.readByte();
      this.setInvulnerable((b & 1) > 0);
      this.setFlying((b & 2) > 0);
      this.setAllowFlying((b & 4) > 0);
      this.setCreativeMode((b & 8) > 0);
      this.setFlySpeed(buf.readFloat());
      this.setWalkSpeed(buf.readFloat());
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerAbilities(this);
   }

   public float getFlySpeed() {
      return this.flySpeed;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      byte b = 0;
      if (this.isInvulnerable()) {
         b = (byte)(b | 1);
      }

      if (this.isFlying()) {
         b = (byte)(b | 2);
      }

      if (this.isAllowFlying()) {
         b = (byte)(b | 4);
      }

      if (this.isCreativeMode()) {
         b = (byte)(b | 8);
      }

      buf.writeByte(b);
      buf.writeFloat(this.flySpeed);
      buf.writeFloat(this.walkSpeed);
   }

   public S39PacketPlayerAbilities() {
   }
}
