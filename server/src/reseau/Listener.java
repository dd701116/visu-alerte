package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Stack;

public class Listener extends Thread{
	
	private Socket socket;
	private ListenerControler controler;
	
	private boolean exit = true;
	
	private Stack<String> stack;
	
	public Listener(ListenerControler controler, Socket socket){
		this.controler = controler;
		this.socket = socket;
		this.stack = new Stack<>();
	}
	
	public void run(){
		try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    		String msg;
    		
    		
            while((msg=in.readLine())!=null && !exit){
               //System.out.println("> Server listen : "+msg);
               stack.add(msg);
               controler.hears(msg);
            }
            System.err.println("Client "+this.socket.getInetAddress()+" are offline !");
            controler.alert("connection lost with "+this.socket.getPort());
            this.exit();
          }catch(SocketException e){
        	  System.err.println(e.getMessage());
          } catch (IOException e) {
              e.printStackTrace();
          }
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public void open() {
		this.start();
		exit=false;
	}
	
	public void close() throws IOException {
		socket.close();
		exit = true;		
	}
	
	public void exit() {
		exit = true;		
	}
	
	public boolean isExit(){
		return exit;
	}

	public String hearSomething() {
		// TODO Auto-generated method stub
		return stack.pop();
	}

}
