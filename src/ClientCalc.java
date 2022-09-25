public class ClientCalc {
    public static void main(String[] args) {
        if(args.length<3){
            System.out.println("Usage:\r\n"+
                    "java Socketor server 8000 /\r\n"+
                    "java Socketor client 127.0.0.1 8000 intArg1 intArg2");
            return;
        }
        ClientCalc socketor = new ClientCalc();
        if(args[0].equals("server")){
            socketor.runServer(args[1],args[2]);
        }
        if (args[0].equals("client")){
            socketor.runClient(args[1],args[2],args[3],args[4]);
        }

    }
    private void runClient(String ip, String port, String a, String b) {
        Server server = new Server(ip ,port);
        server.writeLine(a);
        server.writeLine(b);
        String answer = server.readLine();
        System.out.println(answer);
        server.close();
    }

    private void runServer(String port, String operation) {
        Server server = new Server(port);
        System.out.println("Server started with operation ' "+ operation + " 'on port  "+ port);
        while(true){
            server.accept();
            String a = server.readLine();
            String b = server.readLine();
            int result = calculate(operation,a,b);
            String message = a + " " + operation + " " + b + " = " + result;
            server.writeLine(message);
            System.out.println("Accepted: " + message);
            server.close();
        }
    }
    private int calculate(String operation,String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);

        switch (operation){
            case "+" : return x+y;
            case "-" : return x-y;
            case "*" : return x*y;
            case "/" : return x/y;
            default: return x+y;
        }
    }




}
