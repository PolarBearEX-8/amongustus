package net.minecraft.item;

import client.event.PostMotionObserver;
import client.util.ExtendedListenerController;
import client.util.ListenerAdapter10;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class IronBoots extends ListenerAdapter10 {
   public PostMotionObserver postMotionObserver;
   public boolean enabled;

   @Override
   public JsonElement getJsonElement() {
      return (JsonElement)(this.postMotionObserver instanceof ExtendedListenerController
         ? new JsonPrimitive(this.postMotionObserver.getValue())
         : super.getJsonElement());
   }

   @Override
   public double getValue() {
      return this.postMotionObserver.getValue();
   }

   public ListenerAdapter10 getListenerAdapter10() {
      this.enabled = true;
      return this;
   }

   public IronBoots(DefaultItemEntry defaultItemEntry, PostMotionObserver postMotionObserver2) {
      super(defaultItemEntry);
      this.postMotionObserver = postMotionObserver2;
   }

   @Override
   public String toString() {
      return (this.enabled ? "return " : "") + this.postMotionObserver.toString();
   }
}
