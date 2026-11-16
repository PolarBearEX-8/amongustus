package cc.baritone.utils;

public class UnableCreateInternalCustom extends AbstractInternalCustomController {
   public boolean enabled = false;
   public static final String labelText = "cc.49O";

   @Override
   public synchronized InternalCustom getInternalCustom() throws AuxiliaryExceptionAdapter {
      if (!this.enabled) {
         this.updateState();
         this.enabled = true;
      }

      try {
         return this.getHeuristicScheduler();
      } catch (Exception exception) {
         throw new AuxiliaryExceptionAdapter("unable to create JavaSound device: " + exception);
      } catch (LinkageError linkageError) {
         throw new AuxiliaryExceptionAdapter("unable to create JavaSound device: " + linkageError);
      }
   }

   public void updateState() throws AuxiliaryExceptionAdapter {
      this.getHeuristicScheduler().updateState4();
   }

   public HeuristicScheduler getHeuristicScheduler() throws AuxiliaryExceptionAdapter {
      ClassLoader classLoader = this.getClass().getClassLoader();

      try {
         return (HeuristicScheduler)this.resolveInternalCustom(classLoader, "cc.49O");
      } catch (Exception exception) {
         throw new AuxiliaryExceptionAdapter("Cannot create JavaSound device", exception);
      } catch (LinkageError linkageError) {
         throw new AuxiliaryExceptionAdapter("Cannot create JavaSound device", linkageError);
      }
   }
}
