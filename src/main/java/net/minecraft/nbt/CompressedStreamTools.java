package net.minecraft.nbt;

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
import net.minecraft.util.ReportedException;

public class CompressedStreamTools {

   public static void write(NBTTagCompound tag, DataOutput dataOutput) throws IOException {
      writeTag(tag, dataOutput);
   }

   public static void write(NBTTagCompound tag, File file) throws IOException {
      DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

      try {
         write(tag, out);
      } finally {
         out.close();
      }
   }

   public static NBTTagCompound readCompressed(InputStream in) throws IOException {
      DataInputStream in2 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(in)));

      NBTTagCompound tag;
      try {
         tag = read(in2, NBTSizeTracker.INFINITE);
      } finally {
         in2.close();
      }

      return tag;
   }

   public static void safeWrite(NBTTagCompound tag, File file) throws IOException {
      File file2 = new File(file.getAbsolutePath() + "_tmp");
      if (file2.exists()) {
         file2.delete();
      }

      write(tag, file2);
      if (file.exists()) {
         file.delete();
      }

      if (file.exists()) {
         throw new IOException("Failed to delete " + file);
      } else {
         file2.renameTo(file);
      }
   }

   private static NBTBase readTag(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      byte b = dataInput.readByte();
      if (b == 0) {
         return new NBTTagEnd();
      } else {
         dataInput.readUTF();
         NBTBase nbt = NBTBase.createNewByType(b);

         try {
            nbt.read(dataInput, n, nbtSizeTracker);
            return nbt;
         } catch (IOException e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Loading NBT data");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("NBT Tag");
            crashReportCategory.addCrashSection("Tag name", "[UNNAMED TAG]");
            crashReportCategory.addCrashSection("Tag type", b);
            throw new ReportedException(crashReport);
         }
      }
   }

   private static void writeTag(NBTBase nbt, DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(nbt.getId());
      if (nbt.getId() != 0) {
         dataOutput.writeUTF("");
         nbt.write(dataOutput);
      }
   }

   public static void writeCompressed(NBTTagCompound tag, OutputStream out) throws IOException {
      DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(out)));

      try {
         write(tag, out2);
      } finally {
         out2.close();
      }
   }

   public static NBTTagCompound read(File file) throws IOException {
      if (!file.exists()) {
         return null;
      } else {
         DataInputStream in = new DataInputStream(new FileInputStream(file));

         NBTTagCompound tag;
         try {
            tag = read(in, NBTSizeTracker.INFINITE);
         } finally {
            in.close();
         }

         return tag;
      }
   }

   public static NBTTagCompound read(DataInput dataInput, NBTSizeTracker nbtSizeTracker) throws IOException {
      NBTBase nbt = readTag(dataInput, 0, nbtSizeTracker);
      if (nbt instanceof NBTTagCompound) {
         return (NBTTagCompound)nbt;
      } else {
         throw new IOException("Root tag must be a named compound tag");
      }
   }

   public static NBTTagCompound read(DataInputStream in) throws IOException {
      return read(in, NBTSizeTracker.INFINITE);
   }
}
