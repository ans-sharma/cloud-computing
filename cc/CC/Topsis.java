import java.util.Arrays;
public class Topsis{
    public static double[][] dataNormilisation(double[][] matrix){
        double[][] temp =  new double[matrix.length][matrix[0].length];
        for(int i = 0; i< matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                temp[i][j] = matrix[i][j];
            }
        }
        double[] tempRootSum = new double[matrix[0].length];
        for(int i=0; i<temp.length; i++){
            for(int j=0; j<temp[0].length; j++){
                temp[i][j] = Math.pow(temp[i][j], 2);
            }
        }
        for(int i=0; i<temp[0].length; i++){
            double tempSum = 0;
            for(int j=0; j<temp.length; j++){
                tempSum += temp[j][i];
                // System.out.println(temp[j][i]);
            }
            tempSum = Math.pow(tempSum, 0.5);
            tempRootSum[i] = tempSum;
            // System.out.println(tempSum);
            
        }
        // System.out.println(Arrays.toString(tempRootSum));
        // System.out.println(Arrays.deepToString(matrix));
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = matrix[i][j] / tempRootSum[j];
            }
        }

        return matrix;
    }

    public static double[][] weightedMatrix(double[][] matrix, double[] weights){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = matrix[i][j] * weights[j];
            }
        }
        return matrix;
    }
    
    public static double[] calPositiveSolution(double[][] matrix, boolean[] beneficial){
        double[] temp = new double[matrix[0].length];
        for(int i = 0; i< matrix[0].length; i++){
            double tempMaxMin = matrix[0][i];
            for(int j=0; j < matrix.length; j++){
                if(beneficial[i]){
                    if(tempMaxMin < matrix[j][i]){
                        tempMaxMin = matrix[j][i];
                    }
                }else{
                    if(tempMaxMin > matrix[j][i]){
                        tempMaxMin = matrix[j][i];
                    }
                }
            }
            temp[i] = tempMaxMin;
        }
        return temp;
    }

    public static double[] calNegativeSolution(double[][] matrix, boolean[] beneficial){
        double[] temp = new double[matrix[0].length];
        for(int i = 0; i< matrix[0].length; i++){
            double tempMaxMin = matrix[0][i];
            for(int j=0; j < matrix.length; j++){
                if(!beneficial[i]){
                    if(tempMaxMin < matrix[j][i]){
                        tempMaxMin = matrix[j][i];
                    }
                }else{
                    if(tempMaxMin > matrix[j][i]){
                        tempMaxMin = matrix[j][i];
                    }
                }
            }
            temp[i] = tempMaxMin;
        }
        return temp;
    }
    
    public static double[] calSeperationMatrix(double[][] matrix, double[] seperationMatrix, double[] solution){
        for(int i =0; i < matrix.length; i++){
            double tempSum = 0;
            for(int j=0; j<matrix[0].length; j++){
                double tempCal = matrix[i][j] - solution[j];
                tempSum = tempSum + Math.pow(tempCal, 2);
            }
            tempSum = Math.pow(tempSum, 0.5);
            seperationMatrix[i]=tempSum;
        }
        return seperationMatrix;
    }

    public static double[] calRelativeCoffieientValue(double[] relativeCofficientValues, double[] positiveSeperationMatrix, double[] negativeSeperationMatrix){
        for(int i=0; i<relativeCofficientValues.length; i++){
            // double tempSum = positiveSeperationMatrix[i] + negativeSeperationMatrix[i];
            relativeCofficientValues[i] = negativeSeperationMatrix[i] / (positiveSeperationMatrix[i] + negativeSeperationMatrix[i]);
        }

        return relativeCofficientValues;
    }

    public static int[] calRank(int[] rank, double[] relativeCofficientValues){
        double[] temp = new double[relativeCofficientValues.length];
        int tempCount = 1;
        for(int i=0; i< relativeCofficientValues.length; i++){
            temp[i] = relativeCofficientValues[i];
        }
        for(int i=0; i < relativeCofficientValues.length; i++){
            double maxVal = Arrays.stream(temp).max().getAsDouble();
            for(int j=0; j< relativeCofficientValues.length; j++){
                if( maxVal == temp[j]){
                    rank[j] = tempCount;
                    tempCount += 1;
                    temp[j] = 0;
                    break;  
                }
            }
        }
        return rank;
    }

    public static void main(String[] args) {
        // double[][] matrix = {{
        // // Data for different materials.
        // //  YM   CS   FT    MLC      H    Cost
        //     210, 330, 54.5, 0.00111, 150, 0.673
        // }, {
        //     212, 632.5, 46, 0.00117, 355, 0.7045
        // }, {
        //     212, 655, 87.5, 0.000515, 305, 0.864
        // }, {
        //     206.5, 1575, 38, 0.00026, 483, 1.175
        // }, {
        //     206.5, 360, 111.5, 0.00089, 190, 0.8665
        // }, {
        //     187.5, 1825, 80, 0.00071, 532.5, 6.97
        // }, {
        //     210, 1930, 21, 0.00002055, 771, 7.99
        // }, {
        //     593, 4405, 14.05, 0.00135, 1250, 79.6
        // }, {
        //     212.5, 1655, 120, 0.00113, 448.5, 1.73
        // }};
        // //                 W(YM)  W(CS)  W(FT)  W(MLC)  W(H)  W(Cost)       
        // double[] weights = {0.291, 0.079, 0.206, 0.188, 0.098, 0.139};
        // double[][] matrix = {{1, 8},
        //                      {4, 5}, 
        //                      {2, 7} };
        double[][] matrix = {{1, 8},
                             {4, 5}, 
                             {2, 7},
                             {8, 1} };
        
        // double[][] matrix = {{1, 5},
        //                      {4, 2}, 
        //                      {3, 3},
        //                      {5, 1} };
 
        double[] weights = {0.5, 0.5};
        boolean[] beneficial = {true, true};


        // boolean[] beneficial = {true, true, true, true, true, false};
        double[] positiveSoln = new double[matrix[0].length];
        double[] negativeSoln = new double[matrix[0].length];
        double[] positiveSeperationMatrix = new double[matrix.length];
        double[] negativeSeperationMatrix = new double[matrix.length];
        double[] relativeCofficientValues = new double[matrix.length];
        int[] rank = new int[matrix.length];
        // System.out.println(Arrays.deepToString(matrix));
        matrix = dataNormilisation(matrix); // Array ko Normalise kar diya
        matrix = weightedMatrix(matrix, weights);
        positiveSoln = calPositiveSolution(matrix, beneficial);
        negativeSoln = calNegativeSolution(matrix, beneficial);
        positiveSeperationMatrix = calSeperationMatrix(matrix, positiveSeperationMatrix, positiveSoln);
        negativeSeperationMatrix = calSeperationMatrix(matrix, negativeSeperationMatrix, negativeSoln);
        relativeCofficientValues = calRelativeCoffieientValue(relativeCofficientValues, positiveSeperationMatrix, negativeSeperationMatrix);
        rank = calRank(rank, relativeCofficientValues);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("");
        System.out.println(Arrays.toString(positiveSoln));
        System.out.println("");
        System.out.println(Arrays.toString(negativeSoln));
        System.out.println("");
        System.out.println(Arrays.toString(positiveSeperationMatrix));
        System.out.println("");
        System.out.println(Arrays.toString(negativeSeperationMatrix));
        System.out.println("");
        System.out.println(Arrays.toString(relativeCofficientValues));
        System.out.println("");
        System.out.println(Arrays.toString(rank));

        
    }
}
