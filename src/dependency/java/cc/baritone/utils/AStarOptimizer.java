package cc.baritone.utils;

import java.util.Enumeration;
import java.util.Hashtable;

public class AStarOptimizer extends AbstractInternalCustomController {
   public static AStarOptimizer aStarOptimizer = null;
   public Hashtable hashtable = new Hashtable();

   public static synchronized AStarOptimizer getAStarOptimizer() {
      if (aStarOptimizer == null) {
         aStarOptimizer = new AStarOptimizer();
         aStarOptimizer.updateState();
      }

      return aStarOptimizer;
   }

   public AbstractInternalCustomController[] getAbstractInternalCustomContro() {
      AbstractInternalCustomController[] abstractInternalCustomControllerArray = null;
      synchronized (this.hashtable) {
         int size = this.hashtable.size();
         if (size != 0) {
            abstractInternalCustomControllerArray = new AbstractInternalCustomController[size];
            int count = 0;
            Enumeration enumeration = this.hashtable.elements();

            while (enumeration.hasMoreElements()) {
               AbstractInternalCustomController abstractInternalCustomController = (AbstractInternalCustomController)enumeration.nextElement();
               abstractInternalCustomControllerArray[count++] = abstractInternalCustomController;
            }
         }

         return abstractInternalCustomControllerArray;
      }
   }

   public void applyAbstractInternalCustomCont(AbstractInternalCustomController abstractInternalCustomController) {
      this.hashtable.remove(abstractInternalCustomController.getClass());
   }

   public void remove(Class clazz) {
      this.hashtable.remove(clazz);
   }

   public void updateState() {
      this.applyAbstractInternalCustomContByAbstractInternal(new UnableCreateInternalCustom());
   }

   @Override
   public InternalCustom getInternalCustom() throws AuxiliaryExceptionAdapter {
      InternalCustom internalCustom = null;
      AbstractInternalCustomController[] abstractInternalCustomContro = this.getAbstractInternalCustomContro();
      if (abstractInternalCustomContro == null) {
         throw new AuxiliaryExceptionAdapter(this + ": no factories registered");
      } else {
         AuxiliaryExceptionAdapter auxiliaryExceptionAdapter = null;

         for (int index = 0; internalCustom == null && index < abstractInternalCustomContro.length; index++) {
            try {
               internalCustom = abstractInternalCustomContro[index].getInternalCustom();
            } catch (AuxiliaryExceptionAdapter exception) {
               auxiliaryExceptionAdapter = exception;
            }
         }

         if (internalCustom == null && auxiliaryExceptionAdapter != null) {
            throw new AuxiliaryExceptionAdapter("Cannot create AudioDevice", auxiliaryExceptionAdapter);
         } else {
            return internalCustom;
         }
      }
   }

   public void applyAbstractInternalCustomContByAbstractInternal(AbstractInternalCustomController abstractInternalCustomController) {
      this.hashtable.put(abstractInternalCustomController.getClass(), abstractInternalCustomController);
   }
}
