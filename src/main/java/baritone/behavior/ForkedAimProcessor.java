package baritone.behavior;

import baritone.api.utils.Rotation;

public class ForkedAimProcessor extends AbstractAimProcessor {
   private Rotation prevRotation;
   public final AbstractAimProcessor parent;

   @Override
   public Rotation nextRotation(Rotation rotation) {
      return this.prevRotation = super.nextRotation(rotation);
   }

   public ForkedAimProcessor(AbstractAimProcessor aimProcessor, AbstractAimProcessor aimProcessor2) {
      super(aimProcessor2, null);
      this.parent = aimProcessor;
      this.prevRotation = this.parent.getPrevRotation();
   }

   @Override
   public Rotation getPrevRotation() {
      return this.prevRotation;
   }
}
