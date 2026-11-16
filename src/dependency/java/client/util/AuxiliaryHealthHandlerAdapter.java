package client.util;

public class AuxiliaryHealthHandlerAdapter extends HealthHandlerAdapter {
   public final DuplicateStatIdManager duplicateStatIdManager;

   public AuxiliaryHealthHandlerAdapter(DuplicateStatIdManager duplicateStatIdManager2) {
      super(duplicateStatIdManager2.labelText);
      this.duplicateStatIdManager = duplicateStatIdManager2;
   }
}
