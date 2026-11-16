package cc.baritone.utils;

import java.util.LinkedList;
import java.util.ListIterator;
import net.minecraft.util.EnumFacing;

public class ListenerBridge implements Listener {
   public final LinkedList linkedList = new LinkedList();
   public ManagedListener[] managedListenerArray = new ManagedListener[0];

   @Override
   public synchronized ManagedListener resolveManagedListener2(ManagedListener managedListener, EnumFacing enumFacing, int count) {
      ListIterator listIterator = this.linkedList.listIterator();

      while (listIterator.hasNext()) {
         ManagedListener managedListener2 = (ManagedListener)listIterator.next();
         if (managedListener2 == managedListener) {
            listIterator.remove();
            listIterator.add(managedListener2.resolveManagedListener(enumFacing, count));
            this.toArray();
            return (ManagedListener)listIterator.previous();
         }
      }

      return null;
   }

   @Override
   public ManagedListener resolveManagedListener(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      return this.resolveManagedListener(new DepthFirstMonitor(betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2));
   }

   @Override
   public ManagedListener[] getManagedListenerArrayAsC44OArray() {
      return this.managedListenerArray;
   }

   @Override
   public synchronized ManagedListener resolveManagedListener(ManagedListener managedListener) {
      this.linkedList.add(managedListener);
      this.toArray();
      return managedListener;
   }

   @Override
   public synchronized ManagedListener[] getManagedListenerArray() {
      ManagedListener[] managedListenerArrayAsC44OArray = this.getManagedListenerArrayAsC44OArray();
      this.linkedList.clear();
      this.toArray();
      return managedListenerArrayAsC44OArray;
   }

   @Override
   public synchronized ManagedListener resolveManagedListenerByManagedListener(ManagedListener managedListener) {
      this.linkedList.remove(managedListener);
      this.toArray();
      return managedListener;
   }

   @Override
   public synchronized ManagedListener resolveManagedListenerByManagedListener(ManagedListener managedListener, EnumFacing enumFacing, int count) {
      ListIterator listIterator = this.linkedList.listIterator();

      while (listIterator.hasNext()) {
         ManagedListener managedListener2 = (ManagedListener)listIterator.next();
         if (managedListener2 == managedListener) {
            listIterator.remove();
            listIterator.add(managedListener2.resolveManagedListenerByEnumFacing(enumFacing, count));
            this.toArray();
            return (ManagedListener)listIterator.previous();
         }
      }

      return null;
   }

   @Override
   public synchronized ManagedListener getManagedListener() {
      return this.linkedList.size() == 1 ? (ManagedListener)this.linkedList.peekFirst() : null;
   }

   @Override
   public ManagedListener peekLast() {
      return (ManagedListener)this.linkedList.peekLast();
   }

   @Override
   public synchronized ManagedListener resolveManagedListener(ManagedListener managedListener, EnumFacing enumFacing, int count) {
      ListIterator listIterator = this.linkedList.listIterator();

      while (listIterator.hasNext()) {
         ManagedListener managedListener2 = (ManagedListener)listIterator.next();
         if (managedListener2 == managedListener) {
            listIterator.remove();
            listIterator.add(managedListener2.resolveManagedListener2(enumFacing, count));
            this.toArray();
            return (ManagedListener)listIterator.previous();
         }
      }

      return null;
   }

   private void toArray() {
      this.managedListenerArray = (ManagedListener[])this.linkedList.toArray(new ManagedListener[0]);
   }

   public ListenerBridge(BaritoneManager baritoneManager) {
      new BreadthFirstCoordinator(baritoneManager, this);
   }
}
