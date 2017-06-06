/**
 * Created by kelvin on 5/11/17.
 */
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

class udpclient {
    public static void main(String[] args)  {
        out.println("Eneter the order of the matrix:\n");
        Scanner input = new Scanner(System.in);
        int number   =input.nextInt();
        int [][] array= new int [number][number];
        int row;
        int column;
        System.out.printf("\nEnter the values of the matrix:\n");
        for(row=0;row<array.length;row++)
        {
            for(column=0;column<array.length;column++)
            {
               System.out.printf("Matrix[%d][%d]\t",row ,column);
                array[row][column]=input.nextInt();
            }
        }
        //print the entered matrix
        System.out.printf("\n*****MARTIX***** \n");
        for(row=0;row<array.length;row++)
        {
            for(column=0;column<array.length;column++)
            {
                System.out.printf("%d\t",array[row][column]);
            }
            System.out.printf("\n");
        }

        String matrixString = Arrays.deepToString(array);
        System.out.println("String : " + matrixString);
        //create the udp socket.
        byte[] sendarray = new byte[1204];
        byte[] receiveData = new byte[1204];
        DatagramSocket clientUdpSocket = null;
        try {
            clientUdpSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        sendarray = matrixString.getBytes();
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName("localhost");

            DatagramPacket sendToserver = new DatagramPacket(
                    sendarray,sendarray.length,IPAddress,8084);
            clientUdpSocket.send(sendToserver);
            DatagramPacket recvData= new DatagramPacket(receiveData,receiveData.length);
            clientUdpSocket.receive(recvData);
            String strG = new String(recvData.getData());
            System.out.printf("determinant from the server:" + strG +"\n\n");
            clientUdpSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
