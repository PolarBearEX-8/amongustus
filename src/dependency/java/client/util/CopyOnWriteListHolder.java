package client.util;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListHolder {
   public CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
   public CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();

   public void setCopyOnWriteArrayList(CopyOnWriteArrayList copyOnWriteArrayList3) {
      this.copyOnWriteArrayList = copyOnWriteArrayList3;
   }

   public CopyOnWriteArrayList getCopyOnWriteArrayList2() {
      return this.copyOnWriteArrayList2;
   }

   public void setCopyOnWriteArrayList2(CopyOnWriteArrayList copyOnWriteArrayList3) {
      this.copyOnWriteArrayList2 = copyOnWriteArrayList3;
   }

   public CopyOnWriteArrayList getCopyOnWriteArrayList() {
      return this.copyOnWriteArrayList;
   }
}
