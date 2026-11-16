package cc.baritone.utils;

import java.util.stream.Stream;

public final class GlobalCustom {
   public DynamicListener dynamicListener;
   public String labelText;
   public BreadthFirstValidator breadthFirstValidator;

   private Stream getStream() {
      try {
         return this.dynamicListener.empty(this.labelText, this.breadthFirstValidator);
      } catch (ExceptionAdapter exception) {
      } catch (Throwable throwable) {
         throwable.printStackTrace();
      }

      return Stream.empty();
   }

   private GlobalCustom(DynamicListener dynamicListener2, String text, BreadthFirstValidator breadthFirstValidator2) {
      this.dynamicListener = dynamicListener2;
      this.labelText = text;
      this.breadthFirstValidator = breadthFirstValidator2;
   }

   public static void applyGlobalCustom(GlobalCustom globalCustom) {
      globalCustom.updateState();
   }

   public GlobalCustom(DynamicListener dynamicListener2, String text, BreadthFirstValidator breadthFirstValidator2, BreadthFirstNavigator breadthFirstNavigator) {
      this(dynamicListener2, text, breadthFirstValidator2);
   }

   private void updateState() {
      try {
         this.dynamicListener.applyText(this.labelText, this.breadthFirstValidator);
      } catch (Throwable throwable) {
         ((EnhancedListener)(throwable instanceof EnhancedListener ? (EnhancedListener)throwable : new DynamicListenerException(throwable)))
            .applyDynamicListener(this.dynamicListener, this.breadthFirstValidator.getLinkedList());
      }
   }

   public static Stream resolveStream(GlobalCustom globalCustom) {
      return globalCustom.getStream();
   }
}
