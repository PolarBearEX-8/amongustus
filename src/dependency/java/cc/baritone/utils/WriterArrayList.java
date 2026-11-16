package cc.baritone.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

public class WriterArrayList extends java.util.ArrayList implements WriterCallback, Callback {
   public static final long serialVersionUID = 0x36ed996451b68eedL;

   public static void applyByteArray(byte[] byteValueArray, Writer writer) throws IOException {
      if (byteValueArray == null) {
         writer.write("null");
      } else if (byteValueArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(byteValueArray[0]));

         for (int index = 1; index < byteValueArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(byteValueArray[index]));
         }

         writer.write("]");
      }
   }

   public static void applyShortArray(short[] shortValueArray, Writer writer) throws IOException {
      if (shortValueArray == null) {
         writer.write("null");
      } else if (shortValueArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(shortValueArray[0]));

         for (int index = 1; index < shortValueArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(shortValueArray[index]));
         }

         writer.write("]");
      }
   }

   public WriterArrayList(Collection collection) {
      super(collection);
   }

   public WriterArrayList() {
   }

   @Override
   public String toString() {
      return this.getText();
   }

   public static String resolveText(float[] factorArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyFloatArray(factorArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static void applyTimeArray(long[] valueArray, Writer writer) throws IOException {
      if (valueArray == null) {
         writer.write("null");
      } else if (valueArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(valueArray[0]));

         for (int index = 1; index < valueArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(valueArray[index]));
         }

         writer.write("]");
      }
   }

   public static void applyCharArray(char[] characterArray, Writer writer) throws IOException {
      if (characterArray == null) {
         writer.write("null");
      } else if (characterArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[\"");
         writer.write(String.valueOf(characterArray[0]));

         for (int index = 1; index < characterArray.length; index++) {
            writer.write("\",\"");
            writer.write(String.valueOf(characterArray[index]));
         }

         writer.write("\"]");
      }
   }

   public static String resolveText(Collection collection) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyCollection(collection, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static String resolveText(short[] shortValueArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyShortArray(shortValueArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static String resolveText(Object[] entryArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyTargetArray(entryArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static void applyIndexArray(int[] countArray, Writer writer) throws IOException {
      if (countArray == null) {
         writer.write("null");
      } else if (countArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(countArray[0]));

         for (int index = 1; index < countArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(countArray[index]));
         }

         writer.write("]");
      }
   }

   public static String resolveText(double[] offsetArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyDoubleArray(offsetArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static String resolveText(int[] countArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyIndexArray(countArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static void applyFloatArray(float[] factorArray, Writer writer) throws IOException {
      if (factorArray == null) {
         writer.write("null");
      } else if (factorArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(factorArray[0]));

         for (int index = 1; index < factorArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(factorArray[index]));
         }

         writer.write("]");
      }
   }

   public static void applyDoubleArray(double[] offsetArray, Writer writer) throws IOException {
      if (offsetArray == null) {
         writer.write("null");
      } else if (offsetArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(offsetArray[0]));

         for (int index = 1; index < offsetArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(offsetArray[index]));
         }

         writer.write("]");
      }
   }

   public static void applyTargetArray(Object[] entryArray, Writer writer) throws IOException {
      if (entryArray == null) {
         writer.write("null");
      } else if (entryArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         WriterHelper.applyTarget(entryArray[0], writer);

         for (int index = 1; index < entryArray.length; index++) {
            writer.write(",");
            WriterHelper.applyTarget(entryArray[index], writer);
         }

         writer.write("]");
      }
   }

   public static void applyFlagArray(boolean[] flagArray, Writer writer) throws IOException {
      if (flagArray == null) {
         writer.write("null");
      } else if (flagArray.length == 0) {
         writer.write("[]");
      } else {
         writer.write("[");
         writer.write(String.valueOf(flagArray[0]));

         for (int index = 1; index < flagArray.length; index++) {
            writer.write(",");
            writer.write(String.valueOf(flagArray[index]));
         }

         writer.write("]");
      }
   }

   @Override
   public String getText() {
      return resolveText(this);
   }

   public static String resolveText(char[] characterArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyCharArray(characterArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static String resolveText(byte[] byteValueArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyByteArray(byteValueArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   @Override
   public void applyWriter(Writer writer) throws IOException {
      applyCollection(this, writer);
   }

   public static String resolveText(long[] valueArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyTimeArray(valueArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static void applyCollection(Collection collection, Writer writer) throws IOException {
      if (collection == null) {
         writer.write("null");
      } else {
         boolean flag = true;
         Iterator iterator = collection.iterator();
         writer.write(91);

         while (iterator.hasNext()) {
            if (flag) {
               flag = false;
            } else {
               writer.write(44);
            }

            Object entry = iterator.next();
            if (entry == null) {
               writer.write("null");
            } else {
               WriterHelper.applyTarget(entry, writer);
            }
         }

         writer.write(93);
      }
   }

   public static String resolveText(boolean[] flagArray) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyFlagArray(flagArray, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }
}
