package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;
import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class ByteBufHandler extends ByteBuf {
   public final ByteBuf byteBuf;

   public ByteBuf setIndex(int count, int count2) {
      return this.byteBuf.setIndex(count, count2);
   }

   public float readFloat() {
      return this.byteBuf.readFloat();
   }

   public ByteBuf writeFloat(float factor) {
      return this.byteBuf.writeFloat(factor);
   }

   public short readShort() {
      return this.byteBuf.readShort();
   }

   public int readUnsignedShort() {
      return this.byteBuf.readUnsignedShort();
   }

   public ByteBuf writeShort(int count) {
      return this.byteBuf.writeShort(count);
   }

   public Map getMap3() {
      int count = 0;
      Map map = null;
      Object entry = null;
      Object entry2 = null;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         entry2 = this.getIndexArray();
         map.put(entry, entry2);
      }

      return map;
   }

   public byte[][] getByteArrayAsByteArray() {
      int count = this.readInt();
      byte[][] entry = new byte[count][];

      for (int index = 0; index < count; index++) {
         entry[index] = this.getByteArray();
      }

      return entry;
   }

   public void applyTextArray(String[] textArray) {
      this.byteBuf.writeInt(textArray.length);

      for (String text : textArray) {
         this.applyTextByText(text);
      }
   }

   public void applyMap4(Map map) {
      Object entry = null;
      Object entry2 = null;
      int size = map.size();
      this.writeInt(size);

      for (Entry entry3 : (Iterable<Entry>)(Object)(map.entrySet())) {
         entry = (String)entry3.getKey();
         entry2 = (double[])entry3.getValue();
         this.applyTextByText((String)entry);
         this.applyDoubleArray((double[])entry2);
      }
   }

   public Map getMap2() {
      int count = 0;
      Map map = null;
      Object entry = null;
      float factor = 0.0F;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         factor = this.readFloat();
         map.put(entry, factor);
      }

      return map;
   }

   public void applyMapByMap(Map map) {
      Object entry = null;
      Object entry2 = null;
      int size = map.size();
      this.writeInt(size);

      for (Entry entry3 : (Iterable<Entry>)(Object)(map.entrySet())) {
         entry = (String)entry3.getKey();
         entry2 = (byte[])entry3.getValue();
         this.applyTextByText((String)entry);
         this.applyByteArray((byte[])entry2);
      }
   }

   public void applyFlagArray(boolean[][] flagMatrix) {
      this.byteBuf.writeInt(flagMatrix.length);

      for (boolean[] flagArray : flagMatrix) {
         this.applyFlagArray(flagArray);
      }
   }

   public void applyTextByText(String text) {
      byte[] entry = text.getBytes(StandardCharsets.UTF_8);
      if (entry.length <= 32767) {
         this.byteBuf.writeInt(entry.length);
         this.byteBuf.writeBytes(entry);
      }
   }

   public int getCount() {
      int count = 0;
      int count2 = 0;

      byte byteValue;
      do {
         byteValue = this.readByte();
         count |= (byteValue & 127) << count2++ * 7;
         if (count2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((byteValue & 128) == 128);

      return count;
   }

   public void applyMap(Map map) {
      int size = map.size();
      this.writeInt(size);

      for (Entry entry2 : (Iterable<Entry>)(Object)(map.entrySet())) {
         String entry = (String)entry2.getKey();
         boolean flag = (Boolean)entry2.getValue();
         this.applyTextByText(entry);
         this.writeBoolean(flag);
      }
   }

   public boolean[] getFlagArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return null;
      } else {
         boolean[] entry = new boolean[count];

         for (int index = 0; index < count; index++) {
            entry[index] = this.readBoolean();
         }

         return entry;
      }
   }

   public void applyByteArray(byte[] byteValueArray) {
      int length = byteValueArray.length;
      this.writeInt(length);
      this.byteBuf.writeBytes(byteValueArray);
   }

   public String[] getTextArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return null;
      } else {
         String[] text = new String[count];

         for (int index = 0; index < count; index++) {
            text[index] = this.getTextAsText();
         }

         return text;
      }
   }

   public List getListAsList() {
      int count = 0;
      List list = null;
      count = this.readInt();
      list = new ArrayList(count);

      for (int index = 0; index < count; index++) {
         list.add(this.getByteArray());
      }

      return list;
   }

   public Map getMapAsMap() {
      int count = 0;
      Map map = null;
      Object entry = null;
      Object entry2 = null;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         entry2 = this.getDoubleArrayAsDoubleArray();
         map.put(entry, entry2);
      }

      return map;
   }

   public void applyFlagArray(boolean[] flagArray) {
      this.byteBuf.writeInt(flagArray.length);

      for (boolean flag : flagArray) {
         this.writeBoolean(flag);
      }
   }

   public int[][] getIndexArrayAsIndexArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return (int[][])null;
      } else {
         int[][] entry = new int[count][];

         for (int index = 0; index < count; index++) {
            entry[index] = this.getIndexArray();
         }

         return entry;
      }
   }

   public void applyList(List list) {
      int size = list.size();
      this.writeInt(size);

      for (String text : (Iterable<String>)(Object)(list)) {
         this.applyTextByText(text);
      }
   }

   public List getList() {
      int count = 0;
      List list = null;
      count = this.readInt();
      list = new ArrayList(count);

      for (int index = 0; index < count; index++) {
         list.add(this.getTextAsText());
      }

      return list;
   }

   public static int computeCount(int count) {

      for (int index = 1; index < 5; index++) {
         if ((count & -1 << index * 7) == 0) {
            return index;
         }
      }

      return 5;
   }

   public int[] getIndexArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return null;
      } else {
         int[] entry = new int[count];

         for (int index = 0; index < count; index++) {
            entry[index] = this.readInt();
         }

         return entry;
      }
   }

   public String getText() {
      StringBuilder builder = null;
      byte byteValue = 0;
      builder = new StringBuilder();

      while ((byteValue = this.byteBuf.readByte()) != 0) {
         builder.append((char)byteValue);
      }

      return builder.toString();
   }

   public Map getMap() {
      int count = 0;
      Map map = null;
      Object entry = null;
      boolean flag = false;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         flag = this.readBoolean();
         map.put(entry, flag);
      }

      return map;
   }

   public void applyText(String text) {
      this.byteBuf.writeBytes(text.getBytes(StandardCharsets.US_ASCII));
      this.byteBuf.writeByte(0);
   }

   public byte readByte() {
      return this.byteBuf.readByte();
   }

   public ByteBuf writeByte(int count) {
      return this.byteBuf.writeByte(count);
   }

   public long readLong() {
      return this.byteBuf.readLong();
   }

   public ByteBuf writeLong(long value) {
      return this.byteBuf.writeLong(value);
   }

   public boolean isWritable(int count) {
      return this.byteBuf.isWritable(count);
   }

   public boolean isWritable() {
      return this.byteBuf.isWritable();
   }

   public boolean isReadable(int count) {
      return this.byteBuf.isReadable(count);
   }

   public boolean isReadable() {
      return this.byteBuf.isReadable();
   }

   public ByteBuf retainedDuplicate() {
      return this.byteBuf.retainedDuplicate();
   }

   public boolean[][] getFlagArrayAsFlagArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return (boolean[][])null;
      } else {
         boolean[][] entry = new boolean[count][];

         for (int index = 0; index < count; index++) {
            entry[index] = this.getFlagArray();
         }

         return entry;
      }
   }

   public double[][] getDoubleArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return (double[][])null;
      } else {
         double[][] entry = new double[count][];

         for (int index = 0; index < count; index++) {
            entry[index] = this.getDoubleArrayAsDoubleArray();
         }

         return entry;
      }
   }

   public String[][] getTextArrayAsTextArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return (String[][])null;
      } else {
         String[][] text = new String[count][];

         for (int index = 0; index < count; index++) {
            text[index] = this.getTextArray();
         }

         return text;
      }
   }

   public void applyMap3(Map map) {
      Object entry = null;
      Object entry2 = null;
      int size = map.size();
      this.writeInt(size);

      for (Entry entry3 : (Iterable<Entry>)(Object)(map.entrySet())) {
         entry = (String)entry3.getKey();
         entry2 = (int[])entry3.getValue();
         this.applyTextByText((String)entry);
         this.applyIndexArray((int[])entry2);
      }
   }

   public void applyTextArray(String[][] textMatrix) {
      this.byteBuf.writeInt(textMatrix.length);

      for (String[] textArray : textMatrix) {
         this.applyTextArray(textArray);
      }
   }

   public void applyMap5(Map map) {
      Object entry = null;
      float factor = 0.0F;
      int size = map.size();
      this.writeInt(size);

      for (Entry entry2 : (Iterable<Entry>)(Object)(map.entrySet())) {
         entry = (String)entry2.getKey();
         factor = (Float)entry2.getValue();
         this.applyTextByText((String)entry);
         this.writeFloat(factor);
      }
   }

   public Map getMap4() {
      int count = 0;
      Map map = null;
      Object entry = null;
      Object entry2 = null;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         entry2 = this.getByteArray();
         map.put(entry, entry2);
      }

      return map;
   }

   public void applyByteArray(byte[][] byteValueMatrix) {
      int length = byteValueMatrix.length;
      this.writeInt(length);

      for (byte[] byteValueArray : byteValueMatrix) {
         this.applyByteArray(byteValueArray);
      }
   }

   public double[] getDoubleArrayAsDoubleArray() {
      int count = this.byteBuf.readInt();
      if (count >= 400) {
         return null;
      } else {
         double[] entry = new double[count];

         for (int index = 0; index < count; index++) {
            entry[index] = this.readDouble();
         }

         return entry;
      }
   }

   public void applyIndexByIndex(int count) {
      int count2;
      for (count2 = count; (count2 & -128) != 0; count2 >>>= 7) {
         int count3 = count2 & 127 | 128;
         this.writeByte(count3);
      }

      this.writeByte(count2);
   }

   public void applyIndexArray(int[][] countMatrix) {
      this.byteBuf.writeInt(countMatrix.length);

      for (int[] countArray : countMatrix) {
         this.applyIndexArray(countArray);
      }
   }

   public Map getMap5() {
      int count = 0;
      Map map = null;
      Object entry = null;
      Object entry2 = null;
      count = this.readInt();
      map = new HashMap(count);

      for (int index = 0; index < count; index++) {
         entry = this.getTextAsText();
         entry2 = this.getFlagArray();
         map.put(entry, entry2);
      }

      return map;
   }

   public String getTextAsText() {
      int count = 0;
      String text = null;
      count = this.byteBuf.readInt();
      int count2 = this.byteBuf.readerIndex();
      Charset charset = StandardCharsets.UTF_8;
      int count3 = count2;
      text = this.toString(count3, count, charset);
      int count4 = this.readerIndex() + count;
      this.readerIndex(count4);
      return text;
   }

   public void applyListByList(List list) {
      int size = list.size();
      this.writeInt(size);

      for (byte[] byteValueArray : (Iterable<byte[]>)(Object)(list)) {
         this.applyByteArray(byteValueArray);
      }
   }

   public UUID getUUID() {
      return new UUID(this.byteBuf.readLong(), this.byteBuf.readLong());
   }

   public void applyDoubleArray(double[][] offsetMatrix) {
      this.byteBuf.writeInt(offsetMatrix.length);

      for (double[] offsetArray : offsetMatrix) {
         this.applyDoubleArray(offsetArray);
      }
   }

   public Object resolveTarget(Class clazz) {
      return clazz.getEnumConstants()[this.readInt()];
   }

   public void applyDoubleArray(double[] offsetArray) {
      this.byteBuf.writeInt(offsetArray.length);

      for (double offset : offsetArray) {
         this.writeDouble(offset);
      }
   }

   public void applyUUID(UUID uuid) {
      this.byteBuf.writeLong(uuid.getMostSignificantBits());
      this.byteBuf.writeLong(uuid.getLeastSignificantBits());
   }

   public long memoryAddress() {
      return this.byteBuf.memoryAddress();
   }

   public ByteBuf retain() {
      return this.byteBuf.retain();
   }

   public ByteBuf retain(int count) {
      return this.byteBuf.retain(count);
   }

   public int refCnt() {
      return this.byteBuf.refCnt();
   }

   public ByteBuf touch(Object entry) {
      return this.byteBuf.touch(entry);
   }

   public ByteBuf touch() {
      return this.byteBuf.touch();
   }

   public void applyEnum(Enum enumItem) {
      int ordinal = enumItem.ordinal();
      this.writeInt(ordinal);
   }

   public void applyIndexArray(int[] countArray) {
      this.byteBuf.writeInt(countArray.length);

      for (int count : countArray) {
         this.writeInt(count);
      }
   }

   public void applyMap2(Map map) {
      Object entry = null;
      Object entry2 = null;
      int size = map.size();
      this.writeInt(size);

      for (Entry entry3 : (Iterable<Entry>)(Object)(map.entrySet())) {
         entry = (String)entry3.getKey();
         entry2 = (boolean[])entry3.getValue();
         this.applyTextByText((String)entry);
         this.applyFlagArray((boolean[])entry2);
      }
   }

   public byte[] getByteArray() {
      int count = this.readInt();
      byte[] entry = new byte[count];
      this.byteBuf.readBytes(entry);
      return entry;
   }

   public int getUnsignedMedium(int count) {
      return this.byteBuf.getUnsignedMedium(count);
   }

   public int readUnsignedShortLE() {
      return this.byteBuf.readUnsignedShortLE();
   }

   public int getUnsignedMediumLE(int count) {
      return this.byteBuf.getUnsignedMediumLE(count);
   }

   public ByteBuf readRetainedSlice(int count) {
      return this.byteBuf.readRetainedSlice(count);
   }

   public ByteBuf discardSomeReadBytes() {
      return this.byteBuf.discardSomeReadBytes();
   }

   public int getUnsignedShortLE(int count) {
      return this.byteBuf.getUnsignedShortLE(count);
   }

   public int writeCharSequence(CharSequence text, Charset charset) {
      return this.byteBuf.writeCharSequence(text, charset);
   }

   public long readUnsignedIntLE() {
      return this.byteBuf.readUnsignedIntLE();
   }

   public ByteBuffer internalNioBuffer(int count, int count2) {
      return this.byteBuf.internalNioBuffer(count, count2);
   }

   public int readUnsignedMediumLE() {
      return this.byteBuf.readUnsignedMediumLE();
   }

   public int readUnsignedMedium() {
      return this.byteBuf.readUnsignedMedium();
   }

   public int maxCapacity() {
      return this.byteBuf.maxCapacity();
   }

   public ByteBuf writeZero(int count) {
      return this.byteBuf.writeZero(count);
   }

   public int maxWritableBytes() {
      return this.byteBuf.maxWritableBytes();
   }

   public ByteBuf setShortLE(int count, int count2) {
      return this.byteBuf.setShortLE(count, count2);
   }

   public ByteBuf resetWriterIndex() {
      return this.byteBuf.resetWriterIndex();
   }

   public int writableBytes() {
      return this.byteBuf.writableBytes();
   }

   public ByteBuf readSlice(int count) {
      return this.byteBuf.readSlice(count);
   }

   public long getLongLE(int count) {
      return this.byteBuf.getLongLE(count);
   }

   public ByteBuf writeIntLE(int count) {
      return this.byteBuf.writeIntLE(count);
   }

   public ByteBuf setMedium(int count, int count2) {
      return this.byteBuf.setMedium(count, count2);
   }

   public ByteBuf resetReaderIndex() {
      return this.byteBuf.resetReaderIndex();
   }

   public short getShortLE(int count) {
      return this.byteBuf.getShortLE(count);
   }

   public double readDouble() {
      return this.byteBuf.readDouble();
   }

   public int getUnsignedShort(int count) {
      return this.byteBuf.getUnsignedShort(count);
   }

   public ByteBuffer[] nioBuffers(int count, int count2) {
      return this.byteBuf.nioBuffers(count, count2);
   }

   public ByteBuffer[] nioBuffers() {
      return this.byteBuf.nioBuffers();
   }

   public ByteBuf writeDouble(double offset) {
      return this.byteBuf.writeDouble(offset);
   }

   public long getUnsignedInt(int count) {
      return this.byteBuf.getUnsignedInt(count);
   }

   public CharSequence getCharSequence(int count, int count2, Charset charset) {
      return this.byteBuf.getCharSequence(count, count2, charset);
   }

   public int getIntLE(int count) {
      return this.byteBuf.getIntLE(count);
   }

   public ByteBuf writeShortLE(int count) {
      return this.byteBuf.writeShortLE(count);
   }

   public ByteBuf ensureWritable(int count) {
      return this.byteBuf.ensureWritable(count);
   }

   public int ensureWritable(int count, boolean flag) {
      return this.byteBuf.ensureWritable(count, flag);
   }

   public int getMedium(int count) {
      return this.byteBuf.getMedium(count);
   }

   public int readIntLE() {
      return this.byteBuf.readIntLE();
   }

   public ByteBuf writeMedium(int count) {
      return this.byteBuf.writeMedium(count);
   }

   public ByteBufAllocator alloc() {
      return this.byteBuf.alloc();
   }

   public long readUnsignedInt() {
      return this.byteBuf.readUnsignedInt();
   }

   public int bytesBefore(byte byteValue) {
      return this.byteBuf.bytesBefore(byteValue);
   }

   public int bytesBefore(int count, byte byteValue) {
      return this.byteBuf.bytesBefore(count, byteValue);
   }

   public int bytesBefore(int count, int count2, byte byteValue) {
      return this.byteBuf.bytesBefore(count, count2, byteValue);
   }

   public int readMedium() {
      return this.byteBuf.readMedium();
   }

   public ByteBuf retainedSlice(int count, int count2) {
      return this.byteBuf.retainedSlice(count, count2);
   }

   public ByteBuf retainedSlice() {
      return this.byteBuf.retainedSlice();
   }

   public ByteBuf setMediumLE(int count, int count2) {
      return this.byteBuf.setMediumLE(count, count2);
   }

   public int nioBufferCount() {
      return this.byteBuf.nioBufferCount();
   }

   public CharSequence readCharSequence(int count, Charset charset) {
      return this.byteBuf.readCharSequence(count, charset);
   }

   public int forEachByte(int count, int count2, ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByte(count, count2, byteProcessor);
   }

   public int forEachByte(ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByte(byteProcessor);
   }

   public ByteBuf markReaderIndex() {
      return this.byteBuf.markReaderIndex();
   }

   public int setCharSequence(int count, CharSequence text, Charset charset) {
      return this.byteBuf.setCharSequence(count, text, charset);
   }

   public ByteBuf setIntLE(int count, int count2) {
      return this.byteBuf.setIntLE(count, count2);
   }

   public ByteBuf markWriterIndex() {
      return this.byteBuf.markWriterIndex();
   }

   public ByteBuf asReadOnly() {
      return this.byteBuf.asReadOnly();
   }

   public boolean readBoolean() {
      return this.byteBuf.readBoolean();
   }

   public short readShortLE() {
      return this.byteBuf.readShortLE();
   }

   public long getUnsignedIntLE(int count) {
      return this.byteBuf.getUnsignedIntLE(count);
   }

   public ByteBuf writerIndex(int count) {
      return this.byteBuf.writerIndex(count);
   }

   public int writerIndex() {
      return this.byteBuf.writerIndex();
   }

   public ByteBuf setBytes(int count, byte[] byteValueArray, int count2, int count3) {
      return this.byteBuf.setBytes(count, byteValueArray, count2, count3);
   }

   public int setBytes(int count, InputStream inputStream, int count2) throws IOException {
      return this.byteBuf.setBytes(count, inputStream, count2);
   }

   public ByteBuf setBytes(int count, byte[] byteValueArray) {
      return this.byteBuf.setBytes(count, byteValueArray);
   }

   public int setBytes(int count, ScatteringByteChannel scatteringByteChannel, int count2) throws IOException {
      return this.byteBuf.setBytes(count, scatteringByteChannel, count2);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2) {
      return this.byteBuf.setBytes(count, byteBuf2);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2, int count2) {
      return this.byteBuf.setBytes(count, byteBuf2, count2);
   }

   public int setBytes(int count, FileChannel fileChannel, long value, int count2) throws IOException {
      return this.byteBuf.setBytes(count, fileChannel, value, count2);
   }

   public ByteBuf setBytes(int count, ByteBuffer buffer) {
      return this.byteBuf.setBytes(count, buffer);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2, int count2, int count3) {
      return this.byteBuf.setBytes(count, byteBuf2, count2, count3);
   }

   public ByteBuf writeBoolean(boolean flag) {
      return this.byteBuf.writeBoolean(flag);
   }

   public ByteBuf setZero(int count, int count2) {
      return this.byteBuf.setZero(count, count2);
   }

   public short readUnsignedByte() {
      return this.byteBuf.readUnsignedByte();
   }

   public int readerIndex() {
      return this.byteBuf.readerIndex();
   }

   public ByteBuf readerIndex(int count) {
      return this.byteBuf.readerIndex(count);
   }

   public int readMediumLE() {
      return this.byteBuf.readMediumLE();
   }

   public int forEachByteDesc(ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByteDesc(byteProcessor);
   }

   public int forEachByteDesc(int count, int count2, ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByteDesc(count, count2, byteProcessor);
   }

   public ByteBuf setLongLE(int count, long value) {
      return this.byteBuf.setLongLE(count, value);
   }

   public int readableBytes() {
      return this.byteBuf.readableBytes();
   }

   public ByteBuf discardReadBytes() {
      return this.byteBuf.discardReadBytes();
   }

   public boolean hasMemoryAddress() {
      return this.byteBuf.hasMemoryAddress();
   }

   public short getUnsignedByte(int count) {
      return this.byteBuf.getUnsignedByte(count);
   }

   public ByteBuf writeMediumLE(int count) {
      return this.byteBuf.writeMediumLE(count);
   }

   public ByteBuf writeLongLE(long value) {
      return this.byteBuf.writeLongLE(value);
   }

   public long readLongLE() {
      return this.byteBuf.readLongLE();
   }

   public int getMediumLE(int count) {
      return this.byteBuf.getMediumLE(count);
   }

   public ByteBuf skipBytes(int count) {
      return this.byteBuf.skipBytes(count);
   }

   public ByteBuffer nioBuffer() {
      return this.byteBuf.nioBuffer();
   }

   public ByteBuffer nioBuffer(int count, int count2) {
      return this.byteBuf.nioBuffer(count, count2);
   }

   public ByteBufHandler(ByteBuf byteBuf2) {
      this.byteBuf = byteBuf2;
   }

   public boolean equals(Object other) {
      return this.byteBuf.equals(other);
   }

   public String toString() {
      return this.byteBuf.toString();
   }

   public String toString(int count, int count2, Charset charset) {
      return this.byteBuf.toString(count, count2, charset);
   }

   public String toString(Charset charset) {
      return this.byteBuf.toString(charset);
   }

   public int hashCode() {
      return this.byteBuf.hashCode();
   }

   public int compareTo(ByteBuf byteBuf2) {
      return this.byteBuf.compareTo(byteBuf2);
   }

   public int indexOf(int count, int count2, byte byteValue) {
      return this.byteBuf.indexOf(count, count2, byteValue);
   }

   public boolean getBoolean(int count) {
      return this.byteBuf.getBoolean(count);
   }

   public byte getByte(int count) {
      return this.byteBuf.getByte(count);
   }

   public short getShort(int count) {
      return this.byteBuf.getShort(count);
   }

   public char getChar(int count) {
      return this.byteBuf.getChar(count);
   }

   public int getInt(int count) {
      return this.byteBuf.getInt(count);
   }

   public long getLong(int count) {
      return this.byteBuf.getLong(count);
   }

   public float getFloat(int count) {
      return this.byteBuf.getFloat(count);
   }

   public double getDouble(int count) {
      return this.byteBuf.getDouble(count);
   }

   public ByteBuf clear() {
      return this.byteBuf.clear();
   }

   public int getBytes(int count, GatheringByteChannel gatheringByteChannel, int count2) throws IOException {
      return this.byteBuf.getBytes(count, gatheringByteChannel, count2);
   }

   public ByteBuf getBytes(int count, OutputStream outputStream, int count2) throws IOException {
      return this.byteBuf.getBytes(count, outputStream, count2);
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2, int count2) {
      return this.byteBuf.getBytes(count, byteBuf2, count2);
   }

   public ByteBuf getBytes(int count, ByteBuffer buffer) {
      return this.byteBuf.getBytes(count, buffer);
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2, int count2, int count3) {
      return this.byteBuf.getBytes(count, byteBuf2, count2, count3);
   }

   public ByteBuf getBytes(int count, byte[] byteValueArray, int count2, int count3) {
      return this.byteBuf.getBytes(count, byteValueArray, count2, count3);
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2) {
      return this.byteBuf.getBytes(count, byteBuf2);
   }

   public int getBytes(int count, FileChannel fileChannel, long value, int count2) throws IOException {
      return this.byteBuf.getBytes(count, fileChannel, value, count2);
   }

   public ByteBuf getBytes(int count, byte[] byteValueArray) {
      return this.byteBuf.getBytes(count, byteValueArray);
   }

   public byte[] array() {
      return this.byteBuf.array();
   }

   public int arrayOffset() {
      return this.byteBuf.arrayOffset();
   }

   public boolean hasArray() {
      return this.byteBuf.hasArray();
   }

   public boolean isDirect() {
      return this.byteBuf.isDirect();
   }

   public int readInt() {
      return this.byteBuf.readInt();
   }

   public ByteBuf writeInt(int count) {
      return this.byteBuf.writeInt(count);
   }

   public ByteBuf copy() {
      return this.byteBuf.copy();
   }

   public ByteBuf copy(int count, int count2) {
      return this.byteBuf.copy(count, count2);
   }

   public ByteBuf setBoolean(int count, boolean flag) {
      return this.byteBuf.setBoolean(count, flag);
   }

   public ByteBuf setByte(int count, int count2) {
      return this.byteBuf.setByte(count, count2);
   }

   public ByteBuf setChar(int count, int count2) {
      return this.byteBuf.setChar(count, count2);
   }

   public ByteBuf setDouble(int count, double offset) {
      return this.byteBuf.setDouble(count, offset);
   }

   public ByteBuf setFloat(int count, float factor) {
      return this.byteBuf.setFloat(count, factor);
   }

   public ByteBuf setInt(int count, int count2) {
      return this.byteBuf.setInt(count, count2);
   }

   public ByteBuf setLong(int count, long value) {
      return this.byteBuf.setLong(count, value);
   }

   public ByteBuf setShort(int count, int count2) {
      return this.byteBuf.setShort(count, count2);
   }

   public ByteBuf unwrap() {
      return this.byteBuf.unwrap();
   }

   public int capacity() {
      return this.byteBuf.capacity();
   }

   public ByteBuf capacity(int count) {
      return this.byteBuf.capacity(count);
   }

   public char readChar() {
      return this.byteBuf.readChar();
   }

   public ByteBuf writeChar(int count) {
      return this.byteBuf.writeChar(count);
   }

   public ByteBuf writeBytes(ByteBuffer buffer) {
      return this.byteBuf.writeBytes(buffer);
   }

   public int writeBytes(InputStream inputStream, int count) throws IOException {
      return this.byteBuf.writeBytes(inputStream, count);
   }

   public int writeBytes(ScatteringByteChannel scatteringByteChannel, int count) throws IOException {
      return this.byteBuf.writeBytes(scatteringByteChannel, count);
   }

   public ByteBuf writeBytes(byte[] byteValueArray) {
      return this.byteBuf.writeBytes(byteValueArray);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2, int count, int count2) {
      return this.byteBuf.writeBytes(byteBuf2, count, count2);
   }

   public ByteBuf writeBytes(byte[] byteValueArray, int count, int count2) {
      return this.byteBuf.writeBytes(byteValueArray, count, count2);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2, int count) {
      return this.byteBuf.writeBytes(byteBuf2, count);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2) {
      return this.byteBuf.writeBytes(byteBuf2);
   }

   public int writeBytes(FileChannel fileChannel, long value, int count) throws IOException {
      return this.byteBuf.writeBytes(fileChannel, value, count);
   }

   public boolean isReadOnly() {
      return this.byteBuf.isReadOnly();
   }

   public ByteBuf readBytes(int count) {
      return this.byteBuf.readBytes(count);
   }

   public int readBytes(GatheringByteChannel gatheringByteChannel, int count) throws IOException {
      return this.byteBuf.readBytes(gatheringByteChannel, count);
   }

   public ByteBuf readBytes(byte[] byteValueArray, int count, int count2) {
      return this.byteBuf.readBytes(byteValueArray, count, count2);
   }

   public ByteBuf readBytes(OutputStream outputStream, int count) throws IOException {
      return this.byteBuf.readBytes(outputStream, count);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2) {
      return this.byteBuf.readBytes(byteBuf2);
   }

   public int readBytes(FileChannel fileChannel, long value, int count) throws IOException {
      return this.byteBuf.readBytes(fileChannel, value, count);
   }

   public ByteBuf readBytes(byte[] byteValueArray) {
      return this.byteBuf.readBytes(byteValueArray);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2, int count, int count2) {
      return this.byteBuf.readBytes(byteBuf2, count, count2);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2, int count) {
      return this.byteBuf.readBytes(byteBuf2, count);
   }

   public ByteBuf readBytes(ByteBuffer buffer) {
      return this.byteBuf.readBytes(buffer);
   }

   public ByteBuf duplicate() {
      return this.byteBuf.duplicate();
   }

   public ByteBuf order(ByteOrder byteOrder) {
      return this.byteBuf.order(byteOrder);
   }

   public ByteOrder order() {
      return this.byteBuf.order();
   }

   public ByteBuf slice(int count, int count2) {
      return this.byteBuf.slice(count, count2);
   }

   public ByteBuf slice() {
      return this.byteBuf.slice();
   }

   public boolean release() {
      return this.byteBuf.release();
   }

   public boolean release(int count) {
      return this.byteBuf.release(count);
   }
}
