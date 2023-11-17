/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networklab;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This program demonstrates a client socket application that connects to
 * a web server and send a HTTP HEAD request.
 *
 * @author www.codejava.net
 */
public class HttpRequest {
 
  public static void main(String args[]){
      
      URL myUrl;
      
      try{
          myUrl = new URL("http://nginx.org/");
          
      }catch (MalformedURLException ex){
          ex.printStackTrace();
          return;
      }              
      
      String name = myUrl.getHost();
      int port = 80;
      
      try(Socket socket = new Socket(name, port)){
          OutputStream outStream = socket.getOutputStream();
          PrintWriter pWriter = new PrintWriter(outStream, true);
          
          pWriter.println("HEAD " + myUrl.getPath() + " HTTP/1.1");
          pWriter.println("Host: " + name);
          pWriter.println("User-Agent: MyHttpClient");
          pWriter.println();
          
          InputStream inStream = socket.getInputStream();
          
          BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
          
          String text;
          
          while((text = reader.readLine()) != null){
              System.out.println(text);
          }
      }catch (UnknownHostException ex){
          System.out.println("Server not found : " + ex.getMessage());
          
      }catch (IOException ex){
          System.out.println("I/O error : " + ex.getMessage());
      }
      
  }
         
}