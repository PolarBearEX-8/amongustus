package client.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;

public class LoadingNBTDataManager {
   public static void applyNBTTagCompound(NBTTagCompound tagCompound, OutputStream outputStream) throws IOException {
      DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputStream)));

      try {
         applyNBTTagCompound(tagCompound, (DataOutput)dataOutputStream);
      } finally {
         dataOutputStream.close();
      }
   }

   public static void applyNBTTagCompound(NBTTagCompound tagCompound, DataOutput dataOutput) throws IOException {
      applyLISTManager(tagCompound, dataOutput);
   }

   public static NBTTagCompound resolveNBTTagCompound(InputStream inputStream) throws IOException {
      DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputStream)));

      NBTTagCompound tagCompound;
      try {
         tagCompound = resolveNBTTagCompound(dataInputStream, BytesWhereMaxAllowedManager.bytesWhereMaxAllowedManager);
      } finally {
         dataInputStream.close();
      }

      return tagCompound;
   }

   public static NBTTagCompound resolveNBTTagCompound(File file) throws IOException {
      if (!file.exists()) {
         return null;
      } else {
         DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));

         NBTTagCompound tagCompound;
         try {
            tagCompound = resolveNBTTagCompound(dataInputStream, BytesWhereMaxAllowedManager.bytesWhereMaxAllowedManager);
         } finally {
            dataInputStream.close();
         }

         return tagCompound;
      }
   }

   public static void applyNBTTagCompound(NBTTagCompound tagCompound, File file) throws IOException {
      DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));

      try {
         applyNBTTagCompound(tagCompound, (DataOutput)dataOutputStream);
      } finally {
         dataOutputStream.close();
      }
   }

   public static NBTTagCompound resolveNBTTagCompound(DataInputStream dataInputStream) throws IOException {
      return resolveNBTTagCompound(dataInputStream, BytesWhereMaxAllowedManager.bytesWhereMaxAllowedManager);
   }

   private static LISTManager resolveLISTManager(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      byte byteValue = dataInput.readByte();
      if (byteValue == 0) {
         return new InterpolationPicker();
      } else {
         dataInput.readUTF();
         LISTManager lISTManager = LISTManager.resolveLISTManager(byteValue);

         try {
            lISTManager.applyDataInput(dataInput, count, bytesWhereMaxAllowedManager);
            return lISTManager;
         } catch (IOException ioException) {
            CrashReport crashReport = CrashReport.resolveCrashReport(ioException, "Loading NBT data");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("NBT Tag");
            crashReportCategory.add("Tag name", "[UNNAMED TAG]");
            crashReportCategory.add("Tag type", byteValue);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   private static void applyLISTManager(LISTManager lISTManager, DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(lISTManager.getByteCode());
      if (lISTManager.getByteCode() != 0) {
         dataOutput.writeUTF("");
         lISTManager.writeInt(dataOutput);
      }
   }

   public static void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound, File file) throws IOException {
      File file2 = new File(file.getAbsolutePath() + "_tmp");
      if (file2.exists()) {
         file2.delete();
      }

      applyNBTTagCompound(tagCompound, file2);
      if (file.exists()) {
         file.delete();
      }

      if (file.exists()) {
         throw new IOException("Failed to delete " + file);
      } else {
         file2.renameTo(file);
      }
   }

   public static NBTTagCompound resolveNBTTagCompound(DataInput dataInput, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      LISTManager lISTManager = resolveLISTManager(dataInput, 0, bytesWhereMaxAllowedManager);
      if (lISTManager instanceof NBTTagCompound) {
         return (NBTTagCompound)lISTManager;
      } else {
         throw new IOException("Root tag must be a named compound tag");
      }
   }
}
