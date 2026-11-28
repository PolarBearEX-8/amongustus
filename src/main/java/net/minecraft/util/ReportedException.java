package net.minecraft.util;

import net.minecraft.crash.CrashReport;

public class ReportedException extends RuntimeException {
   private final CrashReport theReportedExceptionCrashReport;

   @Override
   public Throwable getCause() {
      return this.theReportedExceptionCrashReport.getCrashCause();
   }

   public ReportedException(CrashReport crashReport) {
      this.theReportedExceptionCrashReport = crashReport;
   }

   @Override
   public String getMessage() {
      return this.theReportedExceptionCrashReport.getDescription();
   }

   public CrashReport getCrashReport() {
      return this.theReportedExceptionCrashReport;
   }
}
