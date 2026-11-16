package client.util;

import client.event.CanTResolveHostnameListener;
import client.event.PreUpdateDispatcher;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;

public class InternalClientManagerProvider extends SplineCalculator {
   public final GuiMultiplayer guiMultiplayer;
   public final List list = Lists.newArrayList();
   public final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
   public final PreUpdateDispatcher preUpdateDispatcher = new LanServerScanningManager();
   public int count20 = -1;

   @Override
   public boolean checkIndex(int count) {
      return count == this.count20;
   }

   @Override
   public int getCount20() {
      return super.getCount20() + 85;
   }

   public void applyList(List list2) {
      this.copyOnWriteArrayList.clear();

      for (InterpolationDispatcher interpolationDispatcher : (Iterable<InterpolationDispatcher>)(Iterable<?>)list2) {
         this.copyOnWriteArrayList.add(new DistanceAdapter(this.guiMultiplayer, interpolationDispatcher));
      }
   }

   @Override
   public int getCount22() {
      return super.getCount22() + 30;
   }

   public void setCount20(int count) {
      this.count20 = count;
   }

   public int getCount3() {
      return this.count20;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      if (count < this.list.size()) {
         return (PreUpdateDispatcher)this.list.get(count);
      } else {
         int count2 = count - this.list.size();
         return count2 == 0 ? this.preUpdateDispatcher : (PreUpdateDispatcher)this.copyOnWriteArrayList.get(--count2);
      }
   }

   @Override
   public int size() {
      return this.list.size() + 1 + this.copyOnWriteArrayList.size();
   }

   public void applyServersManager(ServersManager serversManager) {
      this.list.clear();

      for (int index = 0; index < serversManager.size(); index++) {
         this.list.add(new CanTResolveHostnameListener(this.guiMultiplayer, serversManager.get(index)));
      }
   }

   public InternalClientManagerProvider(GuiMultiplayer guiMultiplayer2, Minecraft mc, int count, int count2, int count3, int count4, int count5) {
      super(mc, count, count2, count3, count4, count5);
      this.guiMultiplayer = guiMultiplayer2;
   }
}
