package client.event;

public interface PreUpdateDispatcher {
   void applyIndex(int count, int count2, int count3, int count4, int count5, int count6);

   void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag);

   boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6);

   void applyIndex(int count, int count2, int count3);
}
