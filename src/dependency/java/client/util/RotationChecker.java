package client.util;

public class RotationChecker {
   public ModelVariableController[] modelVariableControllerArray;

   public RotationChecker(ModelVariableController[] modelVariableControllerArray2) {
      this.modelVariableControllerArray = modelVariableControllerArray2;
   }

   public void updateState() {
      for (int index = 0; index < this.modelVariableControllerArray.length; index++) {
         this.modelVariableControllerArray[index].updateState();
      }
   }

   public boolean matchesListener61Extension(Listener61Extension listener61Extension) {
      for (int index = 0; index < this.modelVariableControllerArray.length; index++) {
         if (!this.modelVariableControllerArray[index].matchesListener61Extension(listener61Extension)) {
            return false;
         }
      }

      return true;
   }
}
