package pl.sda;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
//            final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            final ServerSocket serverSocket = new ServerSocket(5555);
            while (true) {
                final Socket accept = serverSocket.accept();
                final Thread thread = new Connection(accept);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Connection extends Thread {

    private Socket socket;

    public Connection(Socket accept) {
        socket = accept;
    }

    @Override
    public void run() {

        try {
            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            final BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));

            String s = null;
            int state = 1;

            int state_2 = 1;

            while ((s = bufferedReader.readLine()) != null) {

                if ((state_2==1)&&s.equalsIgnoreCase("state2toServer_2")){
                    System.out.println(new String(s.getBytes("UTF-8"))+state_2);
                };

                if (state == 1 && s.equalsIgnoreCase("state1toServer")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    dataOutputStream.writeBytes("state2toClient\r");
                    state++;
                } else if (state == 2 && s.equalsIgnoreCase("state3toServer")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    dataOutputStream.writeBytes("state4toClient\r");
                    state++;
                }
                else if (state == 3 && s.equalsIgnoreCase("state5toServer")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    dataOutputStream.writeBytes("state6toClient\r");
                    state++;
                }
                else if (state == 4 && s.equalsIgnoreCase("state7toServer")) {
                    System.out.println(new String(s.getBytes("UTF-8"))+state);
                    dataOutputStream.writeBytes("state8toClient\r");
                    state++;
                }
            }
//                } else if (state == 3 && s.startsWith("SIZE:")) {
//                    String[] split = s.split(":");
//                    String sendPart = split[0];
//                    String sizePart = split[1];
//                    try {
//                        Integer integer = Integer.valueOf(sizePart);
//                    } catch (NumberFormatException e) {
//                        dataOutputStream.writeBytes("NO\r");
//                    }
//                    dataOutputStream.writeBytes("OK\r");
//                    state++;
//                } else if (state == 4 && s.equalsIgnoreCase("")) {
//                    System.out.println("koniec połączenia");



        bufferedReader.close();
        dataOutputStream.close();
        outputStream.close();
        inputStream.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}

