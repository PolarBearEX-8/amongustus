package de.florianmichael.dietrichevents2;

import java.util.Arrays;
import java.util.function.Consumer;

public class DietrichEvents2 {
   private static final DietrichEvents2 GLOBAL = new DietrichEvents2(32, Throwable::printStackTrace);
   private Object[][] subscribers;
   private int[][] priorities;
   public Consumer<Throwable> errorHandler;

   public static DietrichEvents2 global() {
      return GLOBAL;
   }

   public DietrichEvents2(int eventCapacity) {
      this(eventCapacity, Throwable::printStackTrace);
   }

   public DietrichEvents2(int eventCapacity, Consumer<Throwable> errorHandler) {
      this.subscribers = new Object[eventCapacity][0];
      this.priorities = new int[eventCapacity][0];
      this.errorHandler = errorHandler;
   }

   public boolean hasSubscriber(int id) {
      return this.subscribers[id].length > 0;
   }

   public Object[] getSubscribers(int id) {
      return !this.hasSubscriber(id) ? null : this.subscribers[id];
   }

   public boolean isSubscriber(int id, Object object) {

      for (Object o : this.subscribers[id]) {
         if (o == object) {
            return true;
         }
      }

      return false;
   }

   public void setEventCapacity(int eventCapacity) {
      Object[][] subscribers = Arrays.copyOf(this.subscribers, this.subscribers.length);
      int[][] priorities = Arrays.copyOf(this.priorities, this.priorities.length);
      this.subscribers = new Object[eventCapacity][0];
      this.priorities = new int[eventCapacity][0];

      for (int i = 0; i < subscribers.length; i++) {
         this.subscribers[i] = subscribers[i];
         this.priorities[i] = priorities[i];
      }
   }

   public void subscribe(Object object, int... ids) {
      this.subscribe(object, 0, ids);
   }

   public void subscribe(Object object, int priority, int... ids) {
      for (int id : ids) {
         this.subscribe(id, object, priority);
      }
   }

   public void subscribe(int id, Object object) {
      this.subscribe(id, object, 0);
   }

   public void subscribe(int id, Object object, int priority) {
      if (this.subscribers.length <= id) {
         this.setEventCapacity(id + 1);
      }

      Object[] subscriberArr = this.subscribers[id];
      int[] priorityArr = this.priorities[id];
      int insertionIndex = subscriberArr.length;

      for (int i = 0; i < subscriberArr.length; i++) {
         if (priorityArr[i] > priority) {
            insertionIndex = i;
            break;
         }
      }

      Object[] newSubscriberArr = new Object[subscriberArr.length + 1];
      int[] newPriorityArr = new int[subscriberArr.length + 1];
      System.arraycopy(priorityArr, 0, newPriorityArr, 0, insertionIndex);
      System.arraycopy(subscriberArr, 0, newSubscriberArr, 0, insertionIndex);
      newPriorityArr[insertionIndex] = priority;
      newSubscriberArr[insertionIndex] = object;
      System.arraycopy(priorityArr, insertionIndex, newPriorityArr, insertionIndex + 1, priorityArr.length - insertionIndex);
      System.arraycopy(subscriberArr, insertionIndex, newSubscriberArr, insertionIndex + 1, subscriberArr.length - insertionIndex);
      this.subscribers[id] = newSubscriberArr;
      this.priorities[id] = newPriorityArr;
   }

   public void unsubscribe(int id, Object object) {
      Object[] subscriberArr = this.subscribers[id];
      int[] priorityArr = this.priorities[id];
      int removeIndex = -1;

      for (int i = 0; i < subscriberArr.length; i++) {
         if (subscriberArr[i] == object) {
            removeIndex = i;
            break;
         }
      }

      if (removeIndex != -1) {
         Object[] newSubscriberArr = new Object[subscriberArr.length - 1];
         int[] newPriorityArr = new int[subscriberArr.length - 1];
         if (removeIndex > 0) {
            System.arraycopy(subscriberArr, 0, newSubscriberArr, 0, removeIndex);
            System.arraycopy(priorityArr, 0, newPriorityArr, 0, removeIndex);
         }

         System.arraycopy(subscriberArr, removeIndex + 1, newSubscriberArr, removeIndex, subscriberArr.length - removeIndex - 1);
         System.arraycopy(priorityArr, removeIndex + 1, newPriorityArr, removeIndex, subscriberArr.length - removeIndex - 1);
         this.subscribers[id] = newSubscriberArr;
         this.priorities[id] = newPriorityArr;
      }
   }

   public void unsubscribeAll(int id) {
      this.subscribers[id] = new Object[0];
      this.priorities[id] = new int[0];
   }

   public void post(int id, AbstractEvent event) {
      if (this.subscribers.length <= id) {
         this.setEventCapacity(id + 1);
      } else {
         try {
            this.postInternal(id, event);
         } catch (Throwable e) {
            this.errorHandler.accept(e);
         }
      }
   }

   public void postInternal(int id, AbstractEvent event) {
      Object[] subscriber = this.subscribers[id];

      for (int i = 0; i < subscriber.length; i++) {
         event.call(subscriber[i]);
      }
   }
}
