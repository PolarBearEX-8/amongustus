package wdl;

public final class WDLSaveThread extends Thread {

   @Override
   public void run() {

      try {
         WDL.saveEverything();
         WDL.saving = false;
         WDL.onSaveComplete();
      } catch (Throwable e) {
         WDL.crashed(e, "World Downloader Mod: Saving world");
      }
   }

   public WDLSaveThread(String str) {
      super(str);
   }
}
