package testapp;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class TestApp {

    public static void main(String[] args) {
        final String serverUri = "wss://login-eu-w1.dev.cc.sinch.com/team3/scweb";
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(serverUri));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            System.out.println("Sending ...");
            // send message to websocket
            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");
            ByteBuffer byteBuf = ByteBuffer.wrap(new byte[1024 * 18]);
            clientEndPoint.sendBinary(byteBuf);
            System.out.println("Sent");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
