/**
 * Created by kelvin on 5/12/17.
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class UdpServer {
    public static void main(String[] args) throws Exception

    {

        DatagramSocket udpserversocket = new DatagramSocket(8084);
        System.out.printf("creating connection.............\n");
        byte[] receiveData = new byte[1204];
        while (true)

        {
            int n=800;
            int[][] array=new int[n][n];
            DatagramPacket receivearray =new DatagramPacket(receiveData,1004);
            udpserversocket.receive(receivearray);

            String matrixString = new String(receivearray.getData());

            System.out.println(matrixString);
            array = stringToMatrix(matrixString);

            System.out.printf("\n*****MARTIX***** \n");
            for(int row=0;row<array.length;row++)
            {
                for(int column=0;column<array.length;column++)
                {
                    System.out.printf("%d\t",array[row][column]);
                }
                System.out.printf("\n");
            }
//
//
          deter jav = new deter();
          int c = jav.caldeterminant(array, n);
            System.out.println("calculated determinant = " + c);
          String determinantString = String.valueOf(c);

            int port = receivearray.getPort();
            DatagramPacket sendPacket =
                    new DatagramPacket(
                            determinantString.getBytes(),
                            determinantString.getBytes().length,
                            receivearray.getAddress(),
                            port);

            udpserversocket.send(sendPacket);


        }
    }


    public static int[][] stringToMatrix(String s){
        s=s.replace("[","");//replacing all [ to ""
        s=s.substring(0,s.length()-2);//ignoring last two ]]
        String s1[]=s.split("],");//separating all by "],"

        int my_matrincs[][] = new int[s1.length][s1.length];//declaring two dimensional matrix for input

        for(int i=0;i<s1.length;i++){
            s1[i]=s1[i].trim();//ignoring all extra space if the string s1[i] has
            String single_int[]=s1[i].split(", ");//separating integers by ", "

            for(int j=0;j<single_int.length;j++){
                my_matrincs[i][j]=Integer.parseInt((single_int[j]).replace("]",""));//adding single values
            }
        }

        //printing result
        for(int i=0;i<my_matrincs.length;i++){
            for(int j=0;j<my_matrincs.length;j++){
                System.out.print(my_matrincs[i][j]+" ");
            }
            System.out.println("");
        }

        return my_matrincs;
    }
}
