package client.util;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoalSetupManager {
   public static final Logger logger = LogManager.getLogger();
   public List list2 = Lists.newArrayList();
   public List list = Lists.newArrayList();
   public final UNKNOWNManager uNKNOWNManager;
   public int count;
   public int count2 = 3;

   public void applyRotationPool(RotationPool rotationPool) {
      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         ClientManager53Entry clientManager53Entry = (ClientManager53Entry)iterator.next();
         RotationPool rotationPool2 = clientManager53Entry.rotationPool;
         if (rotationPool2 == rotationPool) {
            if (this.list.contains(clientManager53Entry)) {
               rotationPool2.updateState();
               this.list.remove(clientManager53Entry);
            }

            iterator.remove();
         }
      }
   }

   private boolean matchesClientManager53Entry(ClientManager53Entry clientManager53Entry, ClientManager53Entry clientManager53Entry2) {
      return (clientManager53Entry.rotationPool.getCount() & clientManager53Entry2.rotationPool.getCount()) == 0;
   }

   private boolean matchesClientManager53EntryByClientManager53E(ClientManager53Entry clientManager53Entry) {
      return clientManager53Entry.rotationPool.isActiveAsFlag();
   }

   public void updateState() {
      this.uNKNOWNManager.applyTextByText("goalSetup");
      if (this.count++ % this.count2 == 0) {
         for (Object clientManager53EntryObj : this.list2) { ClientManager53Entry clientManager53Entry = (ClientManager53Entry)clientManager53EntryObj;
            if (this.list.contains(clientManager53Entry)) {
               if (this.matchesClientManager53Entry(clientManager53Entry) && this.matchesClientManager53EntryByClientManager53E(clientManager53Entry)) {
                  continue;
               }

               clientManager53Entry.rotationPool.updateState();
               this.list.remove(clientManager53Entry);
            }

            if (this.matchesClientManager53Entry(clientManager53Entry) && clientManager53Entry.rotationPool.isActive()) {
               clientManager53Entry.rotationPool.updateState3();
               this.list.add(clientManager53Entry);
            }
         }
      } else {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ClientManager53Entry clientManager53Entry2 = (ClientManager53Entry)iterator.next();
            if (!this.matchesClientManager53EntryByClientManager53E(clientManager53Entry2)) {
               clientManager53Entry2.rotationPool.updateState();
               iterator.remove();
            }
         }
      }

      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.applyTextByText("goalTick");
      Iterator iterator2 = this.list.iterator();

      while (iterator2.hasNext()) {
         ((ClientManager53Entry)iterator2.next()).rotationPool.updateState2();
      }

      this.uNKNOWNManager.updateState();
   }

   public void add(int count3, RotationPool rotationPool) {
      this.list2.add(new ClientManager53Entry(this, count3, rotationPool));
   }

   private boolean matchesClientManager53Entry(ClientManager53Entry clientManager53Entry) {
      for (Object clientManager53Entry2Obj : this.list2) { ClientManager53Entry clientManager53Entry2 = (ClientManager53Entry)clientManager53Entry2Obj;
         if (clientManager53Entry2 != clientManager53Entry) {
            if (clientManager53Entry.count >= clientManager53Entry2.count) {
               if (!this.matchesClientManager53Entry(clientManager53Entry, clientManager53Entry2) && this.list.contains(clientManager53Entry2)) {
                  return false;
               }
            } else if (!clientManager53Entry2.rotationPool.isActive2() && this.list.contains(clientManager53Entry2)) {
               return false;
            }
         }
      }

      return true;
   }

   public GoalSetupManager(UNKNOWNManager uNKNOWNManager2) {
      this.uNKNOWNManager = uNKNOWNManager2;
   }
}
