package client.util;

public class SplineSchedulerExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public final SplineScheduler splineScheduler;

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
   }

   public SplineSchedulerExtendedMinecraftEntry(SplineScheduler splineScheduler2) {
      super(splineScheduler2.mc, splineScheduler2.width, splineScheduler2.height, 80, splineScheduler2.height - 40, splineScheduler2.fontRenderer.indexOffset + 1);
      this.splineScheduler = splineScheduler2;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.splineScheduler.fontRenderer.getIndex((String)SplineScheduler.resolveList(this.splineScheduler).get(count), 10, count3, 16777215);
      this.splineScheduler.fontRenderer.getIndex((String)SplineScheduler.resolveListBySplineScheduler(this.splineScheduler).get(count), 230, count3, 16777215);
   }

   @Override
   public int size() {
      return SplineScheduler.resolveList(this.splineScheduler).size();
   }

   @Override
   public int getCount22() {
      return this.count9 - 10;
   }

   @Override
   public boolean checkIndex(int count) {
      return false;
   }

   @Override
   public void updateState2() {
   }
}
