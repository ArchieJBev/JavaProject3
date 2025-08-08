/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented and they do not have placeholder return statements. Make sure 
 * you have carefully read the project formulation before starting to work 
 * on this file. You will also need to have completed the Matrix class, as 
 * well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 * 
 * Tasks:
 *
 * 1) Complete this class with the indicated methods and instance variables.
 *
 * 2) Fill in the following fields:
 *
 * NAME: Archie Bevan
 * UNIVERSITY ID: 5538165
 * DEPARTMENT: maths and stats
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param matrix      The matrix object that will be filled with random
     *                    samples.
     * @param nSamp       The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix matrix, int nSamp) {
        // You need to fill in this method.
        /*
        we start by initialising 3 doubles and naming them and putting them equal to zero.
        we then use a for loop starting at zero and going up to nSamp, this represents the number of samples
        we then randomise the matrix that is initialised above so all its values are U - (0,1).
        we then set val1 to be the determinant squared of the matrix. The sum1 sums together all the val1's for each
        matrix. We then do the same for val2 and sum2 however the determinant is not squared as we plug this into the variance 
        formula to create the output and then finally the result.

        */

        double result = 0;
        double sum1 = 0;
        double sum2 = 0;
        for(int i=0;i<=nSamp;i++){
            matrix.random();
            

            double val1 = Math.pow(matrix.determinant(),2);
            sum1 += val1;
            double val2 = matrix.determinant();
            sum2 += val2;
        }
        double output = sum2/nSamp;
        result = sum1/nSamp - Math.pow(output,2);
      
        return result;
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50 and print the results to the output. See the 
     * formulation for more detail.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
        /*
        start from 2 to 50 as said in the project formulation then create a new trimatrxi and a new general matrix so we can iterate over these
        i and j for these different types of matrices. Then use the matVariance function that we created above and set the nSamp to be what is 
        asked in the project formulation.
        Then print out the values and use the string.format("%d %.15e %.15e\n") way as seen in the link given in the project formulation pdf. 
        */
        for(int n=2;n<=50;n++){
            GeneralMatrix generalmat = new GeneralMatrix(n,n);
            TriMatrix trimat = new TriMatrix(n);

            double generalvar = matVariance(generalmat,20000);
            double trivar = matVariance(trimat,200000);

            System.out.println(String.format("%d %.15e %.15e\n" , n,generalvar,trivar));
        }

    }
}