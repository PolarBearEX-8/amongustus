package javazoom.jl.player;



import javazoom.jl.decoder.JavaLayerException;
public class JavaSoundAudioDeviceFactory extends AudioDeviceFactory {
   private boolean tested = false;
   private static final String DEVICE_CLASS_NAME = null;

   @Override
   public synchronized AudioDevice createAudioDevice() throws JavaLayerException {
      if (!this.tested) {
         this.testAudioDevice();
         this.tested = true;
      }

      try {
         return this.createAudioDeviceImpl();
      } catch (Exception e) {
         throw new JavaLayerException("unable to create JavaSound device: " + e);
      } catch (LinkageError e2) {
         throw new JavaLayerException("unable to create JavaSound device: " + e2);
      }
   }

   public JavaSoundAudioDevice createAudioDeviceImpl() throws JavaLayerException {
      ClassLoader classLoader = this.getClass().getClassLoader();

      try {
         return (JavaSoundAudioDevice)this.instantiate(classLoader, "cc.431");
      } catch (Exception e) {
         throw new JavaLayerException("Cannot create JavaSound device", e);
      } catch (LinkageError e2) {
         throw new JavaLayerException("Cannot create JavaSound device", e2);
      }
   }

   public void testAudioDevice() throws JavaLayerException {
      this.createAudioDeviceImpl().test();
   }
}
