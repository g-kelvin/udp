import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.*;
import java.io.*;

/**
 * Created by kelvin on 5/10/17.
 */
public class serverudp {
    public static void main(String[] args) throws Exception

    {

        DatagramSocket udpserversocket = new DatagramSocket();
        System.out.printf("creating connection.............\n");
        byte[] sendDet = new byte[1204];
        byte[] receiveData = new byte[1204];
        while (true)

        {
            int n=800;
            int[][] array=new int[n][n];
            DatagramPacket receivearray =new DatagramPacket(receiveData,1004);
            udpserversocket.receive(receivearray);
            System.out.printf("%d",receivearray.getData());

//            array = (int[][]) receivearray.getBytes();
            String str =new String(receivearray.getData(),0,receivearray.getLength());

            int row =0;
            int column=0;
            System.out.printf("the matrix is:\n");
            for(row=0;row<array.length;row++)
            {
                for(column=0;column<array.length;column++)
                {
                    System.out.printf("%d\t",array[row][column]);
                }

            System.out.printf("\n");
            }
            InetAddress IPAddress = receivearray.getAddress();
            DatagramPacket senddeterminant = new DatagramPacket(sendDet,sendDet.length,IPAddress,8081);
            int determinant=0;
            if (array.length==1)
            {
               determinant=array[0][0];
               System.out.printf("%d",determinant);
            }
            else
                if(array.length==2)
                {
                    determinant=(array[0][0]*array[1][1])-(array[0][1]*array[1][0]);
                    System.out.printf("%d",determinant);

                }
                udpserversocket.send(senddeterminant);
                System.out.printf("%d",determinant);

        }
    }
}
