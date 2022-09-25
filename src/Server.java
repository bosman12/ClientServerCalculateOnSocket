import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket client;
    BufferedReader reader;
    BufferedWriter writer;

    public Server(String port){
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Server(String ip, String port){
        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void accept(){
        try {
            client=server.accept();
            createStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createStreams() {
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String readLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void writeLine(String msg){
        try {
            writer.write(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
