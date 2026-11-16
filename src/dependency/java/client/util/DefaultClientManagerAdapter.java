package client.util;

public class DefaultClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;

   @Override
   public boolean isActive() {
      return this.auxiliaryUUIDEntry.worldObj.isActive2();
   }

   @Override
   public void updateState() {
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled4(false);
   }

   public DefaultClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
   }

   @Override
   public void updateState3() {
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled4(true);
   }
}
