package pochi.websocket;

import pochi.websocket.GroupSocket;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class GroupClient
{
    public static void main(String[] args)
    {
      String destUri = "";
      
        if (args.length > 0) {
            destUri = destUri + args[0];
        }
        WebSocketClient client = new WebSocketClient();
        GroupSocket socket = new GroupSocket();
        try {
            client.start();
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);
            socket.awaitClose(5, TimeUnit.SECONDS);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
