package net.minecraft.util;

public interface IProgressUpdate {
   void displaySavingString(String str);

   void setDoneWorking();

   void displayLoadingString(String str);

   void setLoadingProgress(int n);

   void resetProgressAndMessage(String str);
}
