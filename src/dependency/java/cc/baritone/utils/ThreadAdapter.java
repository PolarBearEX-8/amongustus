package cc.baritone.utils;

public final class ThreadAdapter extends Thread {
   public ThreadAdapter(String text) {
      super(text);
   }

   @Override
   public void run() {
      try {
         Pokechu22WorldDownloaderManager.updateState4();
         Pokechu22WorldDownloaderManager.enabled6 = false;
         Pokechu22WorldDownloaderManager.updateState6();
      } catch (Throwable throwable) {
         Pokechu22WorldDownloaderManager.applyThrowable(throwable, "World Downloader Mod: Saving world");
      }
   }
}
