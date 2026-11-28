package wdl;

public interface IBackupProgressMonitor {
   void onNextFile(String str);

   void setNumberOfFiles(int n);
}
