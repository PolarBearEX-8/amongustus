package net.optifine.entity.model.anim;


public class ModelUpdater {
   private ModelVariableUpdater[] modelVariableUpdaters;

   public void update() {
      for (int i = 0; i < this.modelVariableUpdaters.length; i++) {
         this.modelVariableUpdaters[i].update();
      }
   }

   public boolean initialize(IModelResolver modelResolver) {
      for (int i = 0; i < this.modelVariableUpdaters.length; i++) {
         if (!this.modelVariableUpdaters[i].initialize(modelResolver)) {
            return false;
         }
      }

      return true;
   }

   public ModelUpdater(ModelVariableUpdater[] modelVariableUpdaters2) {
      this.modelVariableUpdaters = modelVariableUpdaters2;
   }
}
