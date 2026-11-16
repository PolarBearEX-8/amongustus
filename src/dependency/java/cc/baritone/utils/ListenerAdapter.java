package cc.baritone.utils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;

public final class ListenerAdapter implements ExtraListener {
   public final List list2 = new CopyOnWriteArrayList();
   public final List list = Collections.unmodifiableList(this.list2);

   @Override
   public synchronized ActiveListener resolveActiveListenerByMinecraft(Minecraft mc) {
      Object entry = this.resolveActiveListener3(mc);
      if (entry == null) {
         this.list2.add(entry = new BaritoneManager(mc));
      }

      return (ActiveListener)entry;
   }

   @Override
   public AdvancedListener getAdvancedListener() {
      return DiagonalNavigator.INSTANCE;
   }

   @Override
   public synchronized boolean matchesActiveListener(ActiveListener activeListener) {
      return activeListener != this.get() && this.list2.remove(activeListener);
   }

   @Override
   public ActiveListener get() {
      return (ActiveListener)this.list2.get(0);
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public SharedListener getSharedListener() {
      return BreadthFirstSelector.INSTANCE;
   }

   @Override
   public CommonListener getCommonListener() {
      return AStarProvider.INSTANCE;
   }

   public ListenerAdapter() {
      ((BaritoneManager)this.resolveActiveListenerByMinecraft(Minecraft.getMinecraft())).resolveHeuristicRecorder((item -> new ManagerItemEntry((BaritoneManager)item)));
   }
}
