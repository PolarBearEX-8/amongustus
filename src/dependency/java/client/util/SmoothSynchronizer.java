package client.util;

import client.event.EventBusManager;

public interface SmoothSynchronizer {
   AuxiliaryAbstractEventAdapter auxiliaryAbstractEventAdapter = new AuxiliaryAbstractEventAdapter();

   static AuxiliaryAbstractEventAdapter resolveAuxiliaryAbstractEventAdapte(double offset, double offset2, boolean flag, boolean flag2) {
      AuxiliaryAbstractEventAdapter.applyAuxiliaryAbstractEventAdap(auxiliaryAbstractEventAdapter, offset, offset2, flag, flag2);
      EventBusManager.getDietrichEvents2().post(49, auxiliaryAbstractEventAdapter);
      return auxiliaryAbstractEventAdapter;
   }

   void applyAuxiliaryAbstractEventAdap(AuxiliaryAbstractEventAdapter event);
}
