package client.util;

import client.Client;
import client.event.EventBusManager;
import client.render.RenderPass;
import java.util.ArrayList;

public class PlayerMovementHistory {
   public int count;
   public final ArrayList arrayList = new ArrayList();

   public boolean isActive() {
      return this.count <= 0;
   }

   public void applyRenderPass(RenderPass renderPass) {
      try {
         for (int index = 0; index < this.count; index++) {
            ((StepHistoryArray)this.arrayList.get(index)).applyRenderPassByRenderPass(renderPass);
         }

         this.count = 0;
      } catch (Throwable throwable) {
         Client.getClient().getClass();
         EventBusManager.applyThrowable(throwable);
      }
   }

   public void applyRunnableTask(RunnableTask runnableTask) {
      if (this.count >= this.arrayList.size()) {
         StepHistoryArray stepHistoryArray = new StepHistoryArray();
         stepHistoryArray.applyRunnableTask(runnableTask);
         this.arrayList.add(stepHistoryArray);
      } else {
         ((StepHistoryArray)this.arrayList.get(this.count)).applyRunnableTask(runnableTask);
      }

      this.count++;
   }

   public PlayerMovementHistory() {
      this.count = 0;
   }
}
