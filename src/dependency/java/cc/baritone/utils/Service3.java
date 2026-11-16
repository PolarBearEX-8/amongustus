package cc.baritone.utils;

import java.io.IOException;

public interface Service3 {
   long timestamp = -1L;

   int read(byte[] byteValueArray, int count, int count2) throws IOException;

   long getTimestampAsTime();

   long getTimestamp();

   boolean isActive();

   long getTimestamp2();

   boolean isActiveAsFlag();
}
