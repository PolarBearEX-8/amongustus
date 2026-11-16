package client.util;

import java.util.Random;

public class ClientManagerHandler extends AimObserver {
   public int computeCount(Random random) {
      return (Integer)this.getDataRef2() >= (Integer)this.getDataRef()
         ? (Integer)this.getDataRef2()
         : (Integer)this.getDataRef2() + random.nextInt((Integer)this.getDataRef() - (Integer)this.getDataRef2() + 1);
   }

   public ClientManagerHandler(int count, int count2) {
      super(count, count2);
   }
}
