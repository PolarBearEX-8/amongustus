import dev.jnic.rNBrJa.JNICLoader;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
public class DumpJnicStream {
  public static void main(String[] args) throws Exception {
    System.setProperty("os.name", "Windows");
    try (InputStream raw=JNICLoader.class.getResourceAsStream("/dev/jnic/lib/0ddbcb95-8ed7-436c-8f25-ed8b61ad2807.dat");
         JNICLoader decoded=new JNICLoader(raw); OutputStream out=new FileOutputStream(args[0])) {
      if(raw==null) throw new FileNotFoundException(); byte[] b=new byte[8192]; int n; long total=0;
      while((n=decoded.read(b))!=-1){out.write(b,0,n);total+=n;} System.out.println("decoded_bytes="+total);
    }
    Field f=JNICLoader.class.getDeclaredField("z"); f.setAccessible(true); ByteBuffer b=((ByteBuffer)f.get(null)).duplicate();
    System.out.print("buffer_position="+b.position()+" limit="+b.limit()+" bytes="); b.clear(); while(b.hasRemaining()) System.out.printf("%02x",b.get()&255); System.out.println(); try (FileOutputStream seedOut = new FileOutputStream(args[1])) { ByteBuffer seed=b.duplicate(); seed.flip(); while(seed.hasRemaining()) seedOut.write(seed.get() & 255); }
  }
}
