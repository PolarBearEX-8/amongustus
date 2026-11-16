package client.event;

@FunctionalInterface
public interface ExternalListener {
   void applyIndex(int count, double offset, double offset2);
}
