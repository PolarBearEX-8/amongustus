package cc.baritone.utils;

import cc.baritone.api.AuxiliaryINACTIVEHandlerExtension;
import cc.baritone.api.ExtendedINACTIVEHandlerExtension;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.api.InternalINACTIVEHandlerExtension;

public interface ActiveListener {
   ExtendedINACTIVEHandlerExtension getExtendedINACTIVEHandlerExten();

   InternalINACTIVEHandlerExtension getInternalINACTIVEHandlerExten();

   GenericListener getGenericListener();

   Listener getListener();

   AuxiliaryINACTIVEHandlerExtension getAuxiliaryINACTIVEHandlerExte();

   StaticListener getStaticListener();

   LocalListener getLocalListener();

   AuxiliaryListener getAuxiliaryListener();

   CompositeListener getCompositeListener();

   SupplementalListener getSupplementalListener();

   SchematicsHandler getSchematicsHandler();

   void start();

   GlobalListener getGlobalListener();

   CoreListener getCoreListener();

   INACTIVEHandlerExtension getINACTIVEHandlerExtension();

   CustomListener getCustomListener();

   PassiveListener getPassiveListener();
}
