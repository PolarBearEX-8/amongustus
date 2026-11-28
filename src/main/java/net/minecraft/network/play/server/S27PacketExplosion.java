package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class S27PacketExplosion implements Packet {
   private double x;
   private double y;
   private double z;
   private float strength;
   private List affectedBlockPositions;
   private float motionX;
   private float motionY;
   private float motionZ;
   private boolean resetHorizontalMotion = false;
   private boolean resetVerticalMotion = false;

   public S27PacketExplosion(double d, double d2, double d3, float f, List list, Vec3 vec) {
      this.x = d;
      this.y = d2;
      this.z = d3;
      this.strength = f;
      this.affectedBlockPositions = Lists.newArrayList(list);
      if (vec != null) {
         this.motionX = (float)vec.xCoord;
         this.motionY = (float)vec.yCoord;
         this.motionZ = (float)vec.zCoord;
      }
   }

   public double getZ() {
      return this.z;
   }

   public float getMotionY() {
      return this.motionY;
   }

   public float getMotionZ() {
      return this.motionZ;
   }

   public S27PacketExplosion() {
   }

   public double getX() {
      return this.x;
   }

   public void setResetVerticalMotion(boolean flag) {
      this.resetVerticalMotion = flag;
   }

   public void setMotionX(float f) {
      this.motionX = f;
   }

   public void setResetHorizontalMotion(boolean flag) {
      this.resetHorizontalMotion = flag;
   }

   public boolean isResetVerticalMotion() {
      return this.resetVerticalMotion;
   }

   public float getStrength() {
      return this.strength;
   }

   public float getMotionX() {
      return this.motionX;
   }

   public void setMotionZ(float f) {
      this.motionZ = f;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void setMotionY(float f) {
      this.motionY = f;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.x = (double)buf.readFloat();
      this.y = (double)buf.readFloat();
      this.z = (double)buf.readFloat();
      this.strength = buf.readFloat();
      int n = buf.readInt();
      this.affectedBlockPositions = Lists.newArrayListWithCapacity(n);
      int n2 = (int)this.x;
      int n3 = (int)this.y;
      int n4 = (int)this.z;

      for (int i = 0; i < n; i++) {
         int n5 = buf.readByte() + n2;
         int n6 = buf.readByte() + n3;
         int n7 = buf.readByte() + n4;
         this.affectedBlockPositions.add(new BlockPos(n5, n6, n7));
      }

      this.motionX = buf.readFloat();
      this.motionY = buf.readFloat();
      this.motionZ = buf.readFloat();
   }

   public double getY() {
      return this.y;
   }

   public List getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public boolean isResetHorizontalMotion() {
      return this.resetHorizontalMotion;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleExplosion(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat((float)this.x);
      buf.writeFloat((float)this.y);
      buf.writeFloat((float)this.z);
      buf.writeFloat(this.strength);
      buf.writeInt(this.affectedBlockPositions.size());
      int n = (int)this.x;
      int n2 = (int)this.y;
      int n3 = (int)this.z;

      for (BlockPos pos : (Iterable<BlockPos>)(this.affectedBlockPositions)) {
         int n4 = pos.getX() - n;
         int n5 = pos.getY() - n2;
         int n6 = pos.getZ() - n3;
         buf.writeByte(n4);
         buf.writeByte(n5);
         buf.writeByte(n6);
      }

      buf.writeFloat(this.motionX);
      buf.writeFloat(this.motionY);
      buf.writeFloat(this.motionZ);
   }
}
