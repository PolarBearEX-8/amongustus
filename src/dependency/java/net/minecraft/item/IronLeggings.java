package net.minecraft.item;

import client.event.PostMotionObserver;
import client.util.InternalListenerRegistry;
import client.util.ListenerAdapter10;

public class IronLeggings extends ListenerAdapter10 {
   public PostMotionObserver postMotionObserver;
   public InternalListenerRegistry internalListenerRegistry;

   @Override
   public double getValue() {
      double value = this.postMotionObserver.getValue();
      this.internalListenerRegistry.setValue(value);
      return value;
   }

   public IronLeggings(DefaultItemEntry defaultItemEntry, InternalListenerRegistry internalListenerRegistry2, PostMotionObserver postMotionObserver2) {
      super(defaultItemEntry);
      this.internalListenerRegistry = internalListenerRegistry2;
      this.postMotionObserver = postMotionObserver2;
   }

   @Override
   public String toString() {
      return this.internalListenerRegistry.getLabelText2() + " = " + this.postMotionObserver.toString();
   }
}
