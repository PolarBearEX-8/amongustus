package baritone.api.command.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Registry {
   private final Deque entries = new LinkedList();
   private final Set registered = new HashSet();
   public final Collection registry = Collections.unmodifiableCollection(this.entries);

   public boolean registered(Object obj) {
      return this.registered.contains(obj);
   }

   public void unregister(Object obj) {
      if (this.registered(obj)) {
         this.entries.remove(obj);
         this.registered.remove(obj);
      }
   }

   public Iterator iterator() {
      return this.entries.iterator();
   }

   public Stream stream() {
      return this.entries.stream();
   }

   public Stream descendingStream() {
      return StreamSupport.stream(Spliterators.spliterator(this.descendingIterator(), (long)this.entries.size(), 16448), false);
   }

   public boolean register(Object obj) {
      if (!this.registered(obj)) {
         this.entries.addFirst(obj);
         this.registered.add(obj);
         return true;
      } else {
         return false;
      }
   }

   public Iterator descendingIterator() {
      return this.entries.descendingIterator();
   }
}
