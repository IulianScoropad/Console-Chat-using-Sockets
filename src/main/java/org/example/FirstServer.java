package org.example;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FirstServer {
  public static void main(String[] args)  {
    try {
      // Creating a ServerSocket to listen on a port
      ServerSocket serverSocket = new ServerSocket(12345);
      System.out.println("Server started, waiting for connection...");
      Socket clientSocket = serverSocket.accept();
      System.out.println("Client connected.");

      // Input and output streams for reading/writing data
      DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

      // BufferedReader to read user input from the console
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      // Empty strings for reading input and sending messages
      String string1 = "";
      String string2 = "";

      // Running the communication loop until "done" is received
      while (!string1.equals("done")) {
        // Read message from the client
        string1 = dataInputStream.readUTF();
        System.out.println("Client says: " + string1);

        // Read input from the server console
        System.out.print("Server: ");
        string2 = bufferedReader.readLine();

        // Send the server's message back to the client
        dataOutputStream.writeUTF(string2);
        dataOutputStream.flush();
      }

      // Close resources
      dataInputStream.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
