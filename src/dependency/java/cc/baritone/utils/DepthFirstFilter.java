package cc.baritone.utils;

import java.util.concurrent.Callable;

public final class DepthFirstFilter implements Callable {
   public String getText() {
      return Pokechu22WorldDownloaderManager.getText4();
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
