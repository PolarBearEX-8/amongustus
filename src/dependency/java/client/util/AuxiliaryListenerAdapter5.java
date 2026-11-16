package client.util;

import client.event.PostUpdateFilter;
import net.minecraft.world.storage.WorldInfo;

public class AuxiliaryListenerAdapter5 implements PostUpdateFilter {
   public final WorldServerAdapter worldServerAdapter;

   @Override
   public void applyWorldInfoByWorldInfo(WorldInfo worldInfo, int count) {
      this.worldServerAdapter.getInternalListEntry().applyIndex(count);
   }

   public AuxiliaryListenerAdapter5(WorldServerAdapter worldServerAdapter2) {
      this.worldServerAdapter = worldServerAdapter2;
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, int count) {
      this.worldServerAdapter.getInternalListEntry().applyIndexByIndex(count);
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset) {
      this.worldServerAdapter.getInternalListEntry().applyDouble(offset);
   }

   @Override
   public void applyWorldInfo2(WorldInfo worldInfo, double offset) {
      this.worldServerAdapter.getInternalListEntry().applyDouble2(offset);
   }

   @Override
   public void applyWorldInfoByWorldInfo(WorldInfo worldInfo, double offset) {
      this.worldServerAdapter.getInternalListEntry().applyDoubleByDouble(offset);
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2) {
      this.worldServerAdapter.getInternalListEntry().applyDouble(offset, offset2);
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2, long value) {
      this.worldServerAdapter.getInternalListEntry().applyDouble(offset, offset2, value);
   }
}
