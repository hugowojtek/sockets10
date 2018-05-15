package pl.sda;

import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String... args) {

        try {//TO JEST KLIENT

            System.out.println();

            String toSend = "Cześć uczę się JAVY";

            Socket socket = new Socket("localhost", 5555);

            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();

            final BufferedReader readFromSocket = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            final DataOutputStream writeToSocket =
                    new DataOutputStream(outputStream);


            writeToSocket.writeBytes("state1toServer\r");

            int state = 1;
            String s = null;
            while ((s = readFromSocket.readLine()) != null) {


                if (state == 1 && s.equalsIgnoreCase("state2toClient")) {
                    //System.out.println(s+" 1rs");
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    writeToSocket.writeBytes("state3toServer\r");
                    state++;
                } else if (state == 2 && s.equalsIgnoreCase("state4toClient")) {
                    //System.out.println(s+" 2rs");
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    writeToSocket.writeBytes("state5toServer\r");
                    state++;
                } else if (state == 3 && s.equalsIgnoreCase("state6toClient")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    writeToSocket.writeBytes("state7toServer\r");
                    state++;
                } else if (state == 4 && s.equalsIgnoreCase("state8toClient")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    readFromSocket.close();
                    writeToSocket.close();
                    System.out.println("kończe...");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
