package client.util;

import net.minecraft.nbt.NBTTagList;

public class FovFilter {
   public long timestamp;
   public boolean enabled;
   public byte[] byteFlagArray;
   public RecordNode25 recordNode25;
   public RecordNode25 recordNode2;
   public RecordNode25 recordNode3;
   public byte[] byteFlagArray2;
   public NBTTagList nBTTagList;
   public NBTTagList nBTTagList2;
   public NBTTagList nBTTagList3;
   public final int count;
   public final int count2;

   public FovFilter(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }
}
