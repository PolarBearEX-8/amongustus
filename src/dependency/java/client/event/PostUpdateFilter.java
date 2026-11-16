package client.event;

import net.minecraft.world.storage.WorldInfo;

public interface PostUpdateFilter {
   void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2, long value);

   void applyWorldInfo2(WorldInfo worldInfo, double offset);

   void applyWorldInfo(WorldInfo worldInfo, int count);

   void applyWorldInfoByWorldInfo(WorldInfo worldInfo, double offset);

   void applyWorldInfo(WorldInfo worldInfo, double offset);

   void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2);

   void applyWorldInfoByWorldInfo(WorldInfo worldInfo, int count);
}
