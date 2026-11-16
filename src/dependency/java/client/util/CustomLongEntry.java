package client.util;

import client.event.PostMotionProcessor;
import java.util.List;

public class CustomLongEntry implements PostMotionProcessor {
   public int count5;
   public boolean enabled;
   public long timestamp;
   public int count3;
   public long timestamp3;
   public int count;
   public long timestamp2;
   public int count2;
   public int count4;
   public List list;

   public long getTimestamp() {
      return this.timestamp;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount5() {
      return this.count5;
   }

   public int getCount4() {
      return this.count4;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count6 = this.count4;
      byteBufHandler.writeInt(count6);
      long timestamp4 = this.timestamp;
      byteBufHandler.writeLong(timestamp4);
      List list2 = this.list;
      byteBufHandler.applyList(list2);
      int count7 = this.count;
      byteBufHandler.writeInt(count7);
      long timestamp5 = this.timestamp3;
      byteBufHandler.writeLong(timestamp5);
      long timestamp6 = this.timestamp2;
      byteBufHandler.writeLong(timestamp6);
      int count8 = this.count2;
      byteBufHandler.writeInt(count8);
      int count9 = this.count5;
      byteBufHandler.writeInt(count9);
      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
      int count10 = this.count3;
      byteBufHandler.writeInt(count10);
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public List getList() {
      return this.list;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count4 = byteBufHandler.readInt();
      this.timestamp = byteBufHandler.readLong();
      this.list = byteBufHandler.getList();
      this.count = byteBufHandler.readInt();
      this.timestamp3 = byteBufHandler.readLong();
      this.timestamp2 = byteBufHandler.readLong();
      this.count2 = byteBufHandler.readInt();
      this.count5 = byteBufHandler.readInt();
      this.enabled = byteBufHandler.readBoolean();
      this.count3 = byteBufHandler.readInt();
   }

   public long getTimestamp3() {
      return this.timestamp3;
   }

   public int getCount2() {
      return this.count2;
   }

   public CustomLongEntry() {
   }

   public CustomLongEntry(int count6, long value, List list2, int count7, int count8, long value2, long value3, int count9, boolean flag, int count10) {
      this.count4 = count6;
      this.timestamp = value;
      this.list = list2;
      this.count = count7;
      this.count2 = count8;
      this.timestamp3 = value2;
      this.timestamp2 = value3;
      this.count5 = count9;
      this.enabled = flag;
      this.count3 = count10;
   }
}
