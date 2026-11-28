package net.minecraft.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
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
import java.util.UUID;
import net.augustus.modules.misc.ExploitFixer;
import net.augustus.Augustus;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class PacketBuffer extends ByteBuf {
   private final ByteBuf buf;

   public ByteBuf writeIntLE(int n) {
      return this.buf.writeIntLE(n);
   }

   public ByteBuf copy() {
      return this.buf.copy();
   }

   public ByteBuf retain() {
      return this.buf.retain();
   }

   public ByteBuf order(ByteOrder byteOrder) {
      return this.buf.order(byteOrder);
   }

   public int readUnsignedMedium() {
      return this.buf.readUnsignedMedium();
   }

   public ByteBuf readBytes(ByteBuf buf2, int n, int n2) {
      return this.buf.readBytes(buf2, n, n2);
   }

   public Enum readEnumValue(Class clazz) {
      return ((Enum[])clazz.getEnumConstants())[this.readVarIntFromBuffer()];
   }

   public ByteBuf touch() {
      return this.buf.touch();
   }

   public ByteBuf writeZero(int n) {
      return this.buf.writeZero(n);
   }

   public int getIntLE(int n) {
      return this.buf.getIntLE(n);
   }

   public int getInt(int n) {
      return this.buf.getInt(n);
   }

   public int writeBytes(FileChannel fileChannel, long l, int n) throws IOException {
      return this.buf.writeBytes(fileChannel, l, n);
   }

   public long memoryAddress() {
      return this.buf.memoryAddress();
   }

   public ByteBuf discardSomeReadBytes() {
      return this.buf.discardSomeReadBytes();
   }

   public ByteBufAllocator alloc() {
      return this.buf.alloc();
   }

   public float getFloat(int n) {
      return this.buf.getFloat(n);
   }

   public double getDouble(int n) {
      return this.buf.getDouble(n);
   }

   public int forEachByteDesc(int n, int n2, ByteProcessor byteProcessor) {
      return this.buf.forEachByteDesc(n, n2, byteProcessor);
   }

   public int getUnsignedShort(int n) {
      return this.buf.getUnsignedShort(n);
   }

   public ByteBuf writeDouble(double d) {
      return this.buf.writeDouble(d);
   }

   public int setBytes(int n, ScatteringByteChannel scatteringByteChannel, int n2) throws IOException {
      return this.buf.setBytes(n, scatteringByteChannel, n2);
   }

   public int readerIndex() {
      return this.buf.readerIndex();
   }

   public void writeBlockPos(BlockPos pos) {
      this.writeLong(pos.toLong());
   }

   public boolean release() {
      return this.buf.release();
   }

   public long getUnsignedInt(int n) {
      return this.buf.getUnsignedInt(n);
   }

   public PacketBuffer(ByteBuf buf2) {
      this.buf = buf2;
   }

   public ByteBuf readBytes(byte[] bytes) {
      return this.buf.readBytes(bytes);
   }

   public ByteBuf readSlice(int n) {
      return this.buf.readSlice(n);
   }

   public ByteBuf writerIndex(int n) {
      return this.buf.writerIndex(n);
   }

   public ByteBuf setBytes(int n, byte[] bytes) {
      return this.buf.setBytes(n, bytes);
   }

   public ByteBuf writeBytes(ByteBuf buf2, int n, int n2) {
      return this.buf.writeBytes(buf2, n, n2);
   }

   public int readableBytes() {
      return this.buf.readableBytes();
   }

   public boolean isReadOnly() {
      return this.buf.isReadOnly();
   }

   public short getShortLE(int n) {
      return this.buf.getShortLE(n);
   }

   public ByteBuf writeBytes(byte[] bytes, int n, int n2) {
      return this.buf.writeBytes(bytes, n, n2);
   }

   public short readShortLE() {
      return this.buf.readShortLE();
   }

   public int hashCode() {
      return this.buf.hashCode();
   }

   public BlockPos readBlockPos() {
      return BlockPos.fromLong(this.readLong());
   }

   public byte[] readByteArray() {
      byte[] bytes = new byte[this.readVarIntFromBuffer()];
      this.readBytes(bytes);
      return bytes;
   }

   public int getBytes(int n, GatheringByteChannel gatheringByteChannel, int n2) throws IOException {
      return this.buf.getBytes(n, gatheringByteChannel, n2);
   }

   public IChatComponent readChatComponent() throws IOException {
      return IChatComponent.Serializer.jsonToComponent(this.readUtf8String(32767));
   }

   public ByteBuf resetReaderIndex() {
      return this.buf.resetReaderIndex();
   }

   public int maxWritableBytes() {
      return this.buf.maxWritableBytes();
   }

   public long getUnsignedIntLE(int n) {
      return this.buf.getUnsignedIntLE(n);
   }

   public ByteBuf capacity(int n) {
      return this.buf.capacity(n);
   }



   public double readDouble() {
      return this.buf.readDouble();
   }

   public ByteBuffer internalNioBuffer(int n, int n2) {
      return this.buf.internalNioBuffer(n, n2);
   }

   public int getUnsignedMedium(int n) {
      return this.buf.getUnsignedMedium(n);
   }

   public int readMedium() {
      return this.buf.readMedium();
   }

   public ByteBuf discardReadBytes() {
      return this.buf.discardReadBytes();
   }

   public ByteBuf unwrap() {
      return this.buf.unwrap();
   }

   public ByteBuf setZero(int n, int n2) {
      return this.buf.setZero(n, n2);
   }

   public int nioBufferCount() {
      return this.buf.nioBufferCount();
   }

   public ReferenceCounted touch_m1() {
      return this.touch();
   }

   public void writeEnumValue(Enum obj) {
      this.writeVarIntToBuffer(obj.ordinal());
   }

   public int refCnt() {
      return this.buf.refCnt();
   }

   public ByteBuf getBytes(int n, ByteBuffer buf2) {
      return this.buf.getBytes(n, buf2);
   }

   public long readUnsignedIntLE() {
      return this.buf.readUnsignedIntLE();
   }

   public int readUnsignedShortLE() {
      return this.buf.readUnsignedShortLE();
   }

   public ByteBuf readBytes(OutputStream out, int n) throws IOException {
      return this.buf.readBytes(out, n);
   }

   public short getShort(int n) {
      return this.buf.getShort(n);
   }

   public ByteBuffer nioBuffer(int n, int n2) {
      return this.buf.nioBuffer(n, n2);
   }

   public ByteBuf writeBytes(ByteBuffer buf2) {
      return this.buf.writeBytes(buf2);
   }

   public short getUnsignedByte(int n) {
      return this.buf.getUnsignedByte(n);
   }

   public int maxCapacity() {
      return this.buf.maxCapacity();
   }

   public char getChar(int n) {
      return this.buf.getChar(n);
   }

   public boolean isReadable() {
      return this.buf.isReadable();
   }

   public int setBytes(int n, InputStream in, int n2) throws IOException {
      return this.buf.setBytes(n, in, n2);
   }

   public ByteBuf retainedDuplicate() {
      return this.buf.retainedDuplicate();
   }

   public ByteBuf getBytes(int n, OutputStream out, int n2) throws IOException {
      return this.buf.getBytes(n, out, n2);
   }

   public ByteBuf setInt(int n, int n2) {
      return this.buf.setInt(n, n2);
   }

   public ByteBuf setLong(int n, long l) {
      return this.buf.setLong(n, l);
   }

   public int writeCharSequence(CharSequence text, Charset charset) {
      return this.buf.writeCharSequence(text, charset);
   }

   public int writeBytes(ScatteringByteChannel scatteringByteChannel, int n) throws IOException {
      return this.buf.writeBytes(scatteringByteChannel, n);
   }

   public ByteBuf readBytes(ByteBuf buf2) {
      return this.buf.readBytes(buf2);
   }

   public ByteBuffer[] nioBuffers(int n, int n2) {
      return this.buf.nioBuffers(n, n2);
   }

   public ByteBuf readBytes(ByteBuf buf2, int n) {
      return this.buf.readBytes(buf2, n);
   }

   public ByteBuf setMediumLE(int n, int n2) {
      return this.buf.setMediumLE(n, n2);
   }

   public ByteBuf setBytes(int n, byte[] bytes, int n2, int n3) {
      return this.buf.setBytes(n, bytes, n2, n3);
   }

   public ByteBuf duplicate() {
      return this.buf.duplicate();
   }

   public ByteBuf setShort(int n, int n2) {
      return this.buf.setShort(n, n2);
   }

   public int getMediumLE(int n) {
      return this.buf.getMediumLE(n);
   }

   public int readUnsignedMediumLE() {
      return this.buf.readUnsignedMediumLE();
   }

   public String toString() {
      return this.buf.toString();
   }

   public ByteBuf writeMediumLE(int n) {
      return this.buf.writeMediumLE(n);
   }

   public String toString(Charset charset) {
      return this.buf.toString(charset);
   }

   public long readVarLong() {
      long l = 0L;
      int n = 0;

      byte b;
      do {
         b = this.readByte();
         l |= (long)(b & 127) << n++ * 7;
         if (n > 10) {
            throw new RuntimeException("VarLong too big");
         }
      } while ((b & 128) == 128);

      return l;
   }

   public CharSequence readCharSequence(int n, Charset charset) {
      return this.buf.readCharSequence(n, charset);
   }

   public int bytesBefore(int n, byte b) {
      return this.buf.bytesBefore(n, b);
   }

   public int getUnsignedMediumLE(int n) {
      return this.buf.getUnsignedMediumLE(n);
   }

   public short readUnsignedByte() {
      return this.buf.readUnsignedByte();
   }

   public CharSequence getCharSequence(int n, int n2, Charset charset) {
      return this.buf.getCharSequence(n, n2, charset);
   }

   public UUID readUuid() {
      return new UUID(this.readLong(), this.readLong());
   }

   public ByteBuf writeBytes(byte[] bytes) {
      return this.buf.writeBytes(bytes);
   }

   public long getLongLE(int n) {
      return this.buf.getLongLE(n);
   }

   public ByteBuf getBytes(int n, byte[] bytes, int n2, int n3) {
      return this.buf.getBytes(n, bytes, n2, n3);
   }

   public int getUnsignedShortLE(int n) {
      return this.buf.getUnsignedShortLE(n);
   }

   public ByteBuffer nioBuffer() {
      return this.buf.nioBuffer();
   }

   public int indexOf(int n, int n2, byte b) {
      return this.buf.indexOf(n, n2, b);
   }

   public void writeByteArray(byte[] bytes) {
      this.writeVarIntToBuffer(bytes.length);
      this.writeBytes(bytes);
   }

   public static int getVarIntSize(int n) {
      for (int i = 1; i < 5; i++) {
         if ((n & -1 << i * 7) == 0) {
            return i;
         }
      }

      return 5;
   }

   public int readVarIntFromBuffer() {
      int n = 0;
      int n2 = 0;

      byte b;
      do {
         b = this.readByte();
         n |= (b & 127) << n2++ * 7;
         if (n2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((b & 128) == 128);

      return n;
   }

   public int forEachByte(int n, int n2, ByteProcessor byteProcessor) {
      return this.buf.forEachByte(n, n2, byteProcessor);
   }

   public ByteBuf writeShort(int n) {
      return this.buf.writeShort(n);
   }

   public ByteBuf setBytes(int n, ByteBuf buf2) {
      return this.buf.setBytes(n, buf2);
   }

   public int forEachByteDesc(ByteProcessor byteProcessor) {
      return this.buf.forEachByteDesc(byteProcessor);
   }

   public void writeVarLong(long l) {
      while ((l & -128L) != 0L) {
         this.writeByte((int)(l & 127L) | 128);
         l >>>= 7;
      }

      this.writeByte((int)l);
   }

   public ByteBuf readBytes(byte[] bytes, int n, int n2) {
      return this.buf.readBytes(bytes, n, n2);
   }

   public ByteBuf getBytes(int n, ByteBuf buf2, int n2, int n3) {
      return this.buf.getBytes(n, buf2, n2, n3);
   }

   public float readFloat() {
      return this.buf.readFloat();
   }

   public int setCharSequence(int n, CharSequence text, Charset charset) {
      return this.buf.setCharSequence(n, text, charset);
   }

   public ByteBuf writeMedium(int n) {
      return this.buf.writeMedium(n);
   }

   public int readUnsignedShort() {
      return this.buf.readUnsignedShort();
   }

   public int readInt() {
      return this.buf.readInt();
   }

   public int setBytes(int n, FileChannel fileChannel, long l, int n2) throws IOException {
      return this.buf.setBytes(n, fileChannel, l, n2);
   }

   public boolean readBoolean() {
      return this.buf.readBoolean();
   }

   public ByteBuf ensureWritable(int n) {
      return this.buf.ensureWritable(n);
   }

   public ByteBuf markWriterIndex() {
      return this.buf.markWriterIndex();
   }

   public boolean hasMemoryAddress() {
      return this.buf.hasMemoryAddress();
   }

   public ByteBuf getBytes(int n, byte[] bytes) {
      return this.buf.getBytes(n, bytes);
   }

   public long readLongLE() {
      return this.buf.readLongLE();
   }

   public ByteBuf setIntLE(int n, int n2) {
      return this.buf.setIntLE(n, n2);
   }

   public ByteOrder order() {
      return this.buf.order();
   }

   public boolean release(int n) {
      return this.buf.release(n);
   }

   public ByteBuf slice(int n, int n2) {
      return this.buf.slice(n, n2);
   }

   public ByteBuf writeShortLE(int n) {
      return this.buf.writeShortLE(n);
   }

   public ByteBuf slice() {
      return this.buf.slice();
   }

   public ByteBuf setDouble(int n, double d) {
      return this.buf.setDouble(n, d);
   }

   public boolean isWritable(int n) {
      return this.buf.isWritable(n);
   }

   public char readChar() {
      return this.buf.readChar();
   }

   public int readIntLE() {
      return this.buf.readIntLE();
   }

   public int bytesBefore(int n, int n2, byte b) {
      return this.buf.bytesBefore(n, n2, b);
   }

   public ItemStack readItemStackFromBuffer() throws IOException {
      ItemStack stack = null;
      ExploitFixer exploitFixer = Augustus.getInstance().getModuleManager().exploitFixer;
      if (exploitFixer.isToggled() && exploitFixer.itemReading.getValue() && this.readableBytes() <= 0) {
         return null;
      } else {
         short s = this.readShort();
         if (s >= 0) {
            byte b = this.readByte();
            short s2 = this.readShort();
            stack = new ItemStack(Item.getItemById(s), b, s2);
            if (exploitFixer.isToggled() && exploitFixer.itemReading.getValue()) {
               try {
                  stack.setTagCompound(this.readNBTTagCompoundFromBuffer());
               } catch (Exception e) {
               }
            } else {
               stack.setTagCompound(this.readNBTTagCompoundFromBuffer());
            }
         }

         return stack;
      }
   }

   public byte[] array() {
      return this.buf.array();
   }

   public boolean equals(Object obj) {
      return this.buf.equals(obj);
   }

   public ByteBuf writeChar(int n) {
      return this.buf.writeChar(n);
   }

   public int getBytes(int n, FileChannel fileChannel, long l, int n2) throws IOException {
      return this.buf.getBytes(n, fileChannel, l, n2);
   }

   public int compareTo(ByteBuf other) {
      return this.buf.compareTo(other);
   }

   public boolean getBoolean(int n) {
      return this.buf.getBoolean(n);
   }

   public ByteBuf skipBytes(int n) {
      return this.buf.skipBytes(n);
   }

   public ByteBuf markReaderIndex() {
      return this.buf.markReaderIndex();
   }

   public String toString(int n, int n2, Charset charset) {
      return this.buf.toString(n, n2, charset);
   }

   public ByteBuf setLongLE(int n, long l) {
      return this.buf.setLongLE(n, l);
   }

   public long readLong() {
      return this.buf.readLong();
   }

   public NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException {
      int n = this.readerIndex();
      if (this.readByte() == 0) {
         return null;
      } else {
         this.readerIndex(n);
         return CompressedStreamTools.read(new ByteBufInputStream(this), new NBTSizeTracker(2097152L));
      }
   }

   public long getLong(int n) {
      return this.buf.getLong(n);
   }

   public boolean isDirect() {
      return this.buf.isDirect();
   }

   public ByteBuf retainedSlice(int n, int n2) {
      return this.buf.retainedSlice(n, n2);
   }

   public ByteBuf copy(int n, int n2) {
      return this.buf.copy(n, n2);
   }

   public ByteBuf readerIndex(int n) {
      return this.buf.readerIndex(n);
   }

   public int ensureWritable(int n, boolean flag) {
      return this.buf.ensureWritable(n, flag);
   }

   public int writerIndex() {
      return this.buf.writerIndex();
   }

   public ByteBuf writeBytes(ByteBuf buf2, int n) {
      return this.buf.writeBytes(buf2, n);
   }

   public ByteBuf setShortLE(int n, int n2) {
      return this.buf.setShortLE(n, n2);
   }

   public long readUnsignedInt() {
      return this.buf.readUnsignedInt();
   }

   public String readStringFromBuffer(int n) {
      int n2 = this.readVarIntFromBuffer();
      if (n2 > n * 4) {
         throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + n2 + " > " + n * 4 + ")");
      } else if (n2 < 0) {
         throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         String str = new String(this.toString(this.buf.readerIndex(), n2, Charsets.UTF_8));
         this.readerIndex(this.readerIndex() + n2);
         if (str.length() > n) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + n2 + " > " + n + ")");
         } else {
            return str;
         }
      }
   }

   public ByteBuf asReadOnly() {
      return this.buf.asReadOnly();
   }

   public PacketBuffer writeString(String str) {
      byte[] bytes = str.getBytes(Charsets.UTF_8);
      if (bytes.length > 32767) {
         throw new EncoderException("String too big (was " + str.length() + " bytes encoded, max " + 32767 + ")");
      } else {
         this.writeVarIntToBuffer(bytes.length);
         this.writeBytes(bytes);
         return this;
      }
   }

   public ByteBuf writeInt(int n) {
      return this.buf.writeInt(n);
   }

   public ByteBuf setBoolean(int n, boolean flag) {
      return this.buf.setBoolean(n, flag);
   }

   public int readBytes(GatheringByteChannel gatheringByteChannel, int n) throws IOException {
      return this.buf.readBytes(gatheringByteChannel, n);
   }

   public ByteBuf resetWriterIndex() {
      return this.buf.resetWriterIndex();
   }

   public ByteBuf getBytes(int n, ByteBuf buf2) {
      return this.buf.getBytes(n, buf2);
   }

   public ByteBuf writeFloat(float f) {
      return this.buf.writeFloat(f);
   }

   public ReferenceCounted retain_m1() {
      return this.retain();
   }

   public boolean hasArray() {
      return this.buf.hasArray();
   }

   public int capacity() {
      return this.buf.capacity();
   }

   public ByteBuf setBytes(int n, ByteBuf buf2, int n2) {
      return this.buf.setBytes(n, buf2, n2);
   }

   public ByteBuf readBytes(int n) {
      return this.buf.readBytes(n);
   }

   public int getMedium(int n) {
      return this.buf.getMedium(n);
   }

   public ByteBuf writeBoolean(boolean flag) {
      return this.buf.writeBoolean(flag);
   }

   public ByteBuf setByte(int n, int n2) {
      return this.buf.setByte(n, n2);
   }

   public ByteBuf readBytes(ByteBuffer buf2) {
      return this.buf.readBytes(buf2);
   }

   public ByteBuffer[] nioBuffers() {
      return this.buf.nioBuffers();
   }

   public ByteBuf readRetainedSlice(int n) {
      return this.buf.readRetainedSlice(n);
   }

   public ByteBuf touch(Object obj) {
      return this.buf.touch(obj);
   }

   public int readBytes(FileChannel fileChannel, long l, int n) throws IOException {
      return this.buf.readBytes(fileChannel, l, n);
   }

   public int writeBytes(InputStream in, int n) throws IOException {
      return this.buf.writeBytes(in, n);
   }

   public int readMediumLE() {
      return this.buf.readMediumLE();
   }

   public void writeChatComponent(IChatComponent component) throws IOException {
      this.writeString(IChatComponent.Serializer.componentToJson(component));
   }

   public ByteBuf setIndex(int n, int n2) {
      return this.buf.setIndex(n, n2);
   }

   public int bytesBefore(byte b) {
      return this.buf.bytesBefore(b);
   }

   public void writeVarIntToBuffer(int n) {
      while ((n & -128) != 0) {
         this.writeByte(n & 127 | 128);
         n >>>= 7;
      }

      this.writeByte(n);
   }

   public ByteBuf setMedium(int n, int n2) {
      return this.buf.setMedium(n, n2);
   }

   public ByteBuf writeLong(long l) {
      return this.buf.writeLong(l);
   }

   public ByteBuf writeByte(int n) {
      return this.buf.writeByte(n);
   }

   public ByteBuf writeBytes(ByteBuf buf2) {
      return this.buf.writeBytes(buf2);
   }

   public String readUtf8String(int n) {
      int n2 = this.readVarIntFromBuffer();
      if (n2 > n * 4) {
         throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + n2 + " > " + n * 4 + ")");
      } else if (n2 < 0) {
         throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         String str = new String(this.toString(this.buf.readerIndex(), n2, Charsets.UTF_8));
         this.readerIndex(this.readerIndex() + n2);
         if (str.length() > n) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + n2 + " > " + n + ")");
         } else {
            return str;
         }
      }
   }

   public int arrayOffset() {
      return this.buf.arrayOffset();
   }

   public byte readByte() {
      return this.buf.readByte();
   }

   public ByteBuf getBytes(int n, ByteBuf buf2, int n2) {
      return this.buf.getBytes(n, buf2, n2);
   }

   public ByteBuf setBytes(int n, ByteBuf buf2, int n2, int n3) {
      return this.buf.setBytes(n, buf2, n2, n3);
   }

   public ByteBuf clear() {
      return this.buf.clear();
   }

   public short readShort() {
      return this.buf.readShort();
   }

   public ReferenceCounted touch_m1(Object obj) {
      return this.touch(obj);
   }

   public boolean isWritable() {
      return this.buf.isWritable();
   }

   public void writeUuid(UUID uuid) {
      this.writeLong(uuid.getMostSignificantBits());
      this.writeLong(uuid.getLeastSignificantBits());
   }

   public ByteBuf setChar(int n, int n2) {
      return this.buf.setChar(n, n2);
   }

   public int writableBytes() {
      return this.buf.writableBytes();
   }

   public void writeItemStackToBuffer(ItemStack stack) {
      if (stack == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(Item.getIdFromItem(stack.getItem()));
         this.writeByte(stack.stackSize);
         this.writeShort(stack.getMetadata());
         NBTTagCompound tag = null;
         if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
            tag = stack.getTagCompound();
         }

         this.writeNBTTagCompoundToBuffer(tag);
      }
   }

   public ByteBuf setFloat(int n, float f) {
      return this.buf.setFloat(n, f);
   }

   public boolean isReadable(int n) {
      return this.buf.isReadable(n);
   }

   public ByteBuf retainedSlice() {
      return this.buf.retainedSlice();
   }

   public void writeNBTTagCompoundToBuffer(NBTTagCompound tag) {
      if (tag == null) {
         this.writeByte(0);
      } else {
         try {
            CompressedStreamTools.write(tag, new ByteBufOutputStream(this));
         } catch (IOException e) {
            throw new EncoderException(e);
         }
      }
   }

   public ByteBuf writeLongLE(long l) {
      return this.buf.writeLongLE(l);
   }

   public ByteBuf retain(int n) {
      return this.buf.retain(n);
   }

   public ReferenceCounted retain_m1(int n) {
      return this.retain(n);
   }

   public ByteBuf setBytes(int n, ByteBuffer buf2) {
      return this.buf.setBytes(n, buf2);
   }

   public byte getByte(int n) {
      return this.buf.getByte(n);
   }

   public int forEachByte(ByteProcessor byteProcessor) {
      return this.buf.forEachByte(byteProcessor);
   }

}
