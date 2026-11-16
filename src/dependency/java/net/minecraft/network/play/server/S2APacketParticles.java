package net.minecraft.network.play.server;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S2APacketParticles implements Packet {
   public AttackEffectsExplosionNormalType attackEffectsExplosionNormalType;
   public float factor7;
   public float factor5;
   public float factor;
   public float factor2;
   public float factor3;
   public float factor4;
   public float factor6;
   public int count;
   public boolean enabled;
   public int[] countArray;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2APacketParticles(this);
   }

   public double getValue() {
      return (double)this.factor;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.resolveAttackEffectsExplosionNormal(packet.readInt());
      if (this.attackEffectsExplosionNormalType == null) {
         this.attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.BARRIER;
      }

      this.enabled = packet.readBoolean();
      this.factor7 = packet.readFloat();
      this.factor5 = packet.readFloat();
      this.factor = packet.readFloat();
      this.factor2 = packet.readFloat();
      this.factor3 = packet.readFloat();
      this.factor4 = packet.readFloat();
      this.factor6 = packet.readFloat();
      this.count = packet.readInt();
      int count2 = this.attackEffectsExplosionNormalType.getCount();
      this.countArray = new int[count2];

      for (int index = 0; index < count2; index++) {
         this.countArray[index] = packet.getInt3();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int[] getCountArray() {
      return this.countArray;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public float getFactor4() {
      return this.factor4;
   }

   public double getValue2() {
      return (double)this.factor5;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.attackEffectsExplosionNormalType.getIndex());
      packet.writeBoolean(this.enabled);
      packet.writeFloat(this.factor7);
      packet.writeFloat(this.factor5);
      packet.writeFloat(this.factor);
      packet.writeFloat(this.factor2);
      packet.writeFloat(this.factor3);
      packet.writeFloat(this.factor4);
      packet.writeFloat(this.factor6);
      packet.writeInt(this.count);
      int count2 = this.attackEffectsExplosionNormalType.getCount();

      for (int index = 0; index < count2; index++) {
         packet.updateInt(this.countArray[index]);
      }
   }

   public float getFactor3() {
      return this.factor3;
   }

   public int getIndex() {
      return this.count;
   }

   public float getScaleFactor() {
      return this.factor6;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public AttackEffectsExplosionNormalType getAttackEffectsExplosionNormalType() {
      return this.attackEffectsExplosionNormalType;
   }

   public double getValueAsDouble() {
      return (double)this.factor7;
   }

   public S2APacketParticles(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType2,
      boolean flag,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posY,
      float posZ,
      int count2,
      int[] countArray2
   ) {
      this.attackEffectsExplosionNormalType = attackEffectsExplosionNormalType2;
      this.enabled = flag;
      this.factor7 = posX;
      this.factor5 = posX2;
      this.factor = posX3;
      this.factor2 = posX4;
      this.factor3 = posX5;
      this.factor4 = posY;
      this.factor6 = posZ;
      this.count = count2;
      this.countArray = countArray2;
   }

   public S2APacketParticles() {
   }
}
