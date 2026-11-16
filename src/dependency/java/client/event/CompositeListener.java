package client.event;

public interface CompositeListener {
   void applyText2(String text);

   void setCount2(int count);

   void applyTextByText(String text);

   void applyText(String text);

   void updateState();
}
