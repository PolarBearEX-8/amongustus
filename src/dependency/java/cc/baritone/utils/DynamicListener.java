package cc.baritone.utils;

import java.util.List;
import java.util.stream.Stream;

public interface DynamicListener extends DRNHandler {
   default boolean isActive() {
      return false;
   }

   Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter;

   List getList();

   List getListAsList();

   String getLabelText2();

   void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter;
}
