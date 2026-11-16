package client.util;

import client.event.PostMotionDispatcher;

public class AuxiliaryListenerAdapter8 implements PostMotionDispatcher {
   public final AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder;
   public final int count;

   @Override
   public int getCount() {
      return this.count;
   }

   @Override
   public Object getTarget() {
      return this.getAuxiliaryResourceLocationHol();
   }

   public AuxiliaryResourceLocationHolder getAuxiliaryResourceLocationHol() {
      return new AuxiliaryResourceLocationHolder(this.auxiliaryResourceLocationHolder);
   }

   public AuxiliaryListenerAdapter8(AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder2, int count2) {
      this.auxiliaryResourceLocationHolder = auxiliaryResourceLocationHolder2;
      this.count = count2;
   }
}
