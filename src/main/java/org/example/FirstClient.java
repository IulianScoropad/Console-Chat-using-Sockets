package org.example;

import java.io.*;
import java.net.Socket;


public class FirstClient {
  public static void main(String[] args) {

    try {
      // Connecting to the server on localhost and port 12345
      Socket socket = new Socket("localhost", 12345);
      System.out.println("Connected to the server.");

      // Input and output streams for reading/writing data
      DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

      // BufferedReader to read user input from the console
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      // Empty strings for communication
      String string1 = "";
      String string2 = "";

      // Running the communication loop
      while (!string1.equals("done")) {
        // Read user input (client's message to server)
        System.out.print("Client: ");
        string1 = bufferedReader.readLine();

        // Send the message to the server
        dataOutputStream.writeUTF(string1);
        dataOutputStream.flush();

        // Read server's response
        string2 = dataInputStream.readUTF();
        System.out.println("Server says: " + string2);
      }

      // Close resources
      dataOutputStream.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
