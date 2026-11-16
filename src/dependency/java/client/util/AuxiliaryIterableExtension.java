package client.util;

public interface AuxiliaryIterableExtension extends Iterable {
   void applyTarget(Object entry, Object entry2);

   Object getObject(Object entry);
}
