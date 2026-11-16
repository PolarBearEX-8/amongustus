package client.util;

public interface ArrayListControllerAService {
   void updateState();

   void applyArrayListControllerAdapter(ArrayListControllerAdapter arrayListControllerAdapter);

   void applyIndex(int count, int count2, int count3, int count4, int count5, int count6);

   void applyArrayListControllerContext(int count, boolean flag, boolean flag2, ArrayListControllerContext arrayListControllerContext);

   void applyFlag(boolean flag, float factor);

   default void applyTextArray(String[] textArray) {
   }

   void updateState2();
}
