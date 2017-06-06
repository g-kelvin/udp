/**
 * Created by kelvin on 5/12/17.
 */
public class deter {
    public int caldeterminant(int [][] array,int n)
    {
        n=array.length;
        int result=0;
        int row;
        int column;
        int sign = 1;
        if (n==1)
        {
            result=array[0][0];
            return result;
        }
//        else
//        if ( n==2)
//        {
//            result=(array[0][0]*array[1][1])-(array[0][1]*array[1][0]);
//            return result;
//        }
       else {
                for (int j1 = 0; j1 < n; j1++) {
                    int newMatrix[][] = new int[n - 1][n - 1]; //the new matrix.

                    for (row = 1; row < n; row++) {
                        int j = 0;
                        for (column = 0; column < n; column++) {
                            if (j1 == column)
                                continue;
                            newMatrix[row - 1][j] = array[row][column];
                            j++;
                        }
                    }
                    result += sign * array[0][j1] * caldeterminant(newMatrix, n - 1);
                    sign = -sign;

//                    System.out.println(
//                            sign + " * " + array[0][j1] + " *  " +
//                                    caldeterminant(newMatrix, n - 1) + " = " + result);
                }
         }


       return result;

    }

}
