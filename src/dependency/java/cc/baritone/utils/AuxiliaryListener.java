package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandler;
import java.util.List;
import java.util.function.Predicate;

public interface AuxiliaryListener extends INACTIVEHandler {
   void setPredicate(Predicate predicate);

   List getList();

   Predicate getPredicate();

   default void updateState2() {
      this.updateState();
   }
}
