/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * implemented and they do not have placeholder return statements. Make sure 
 * you have carefully read the project formulation before starting to work 
 * on this file. You will also need to have completed the Matrix class.
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
 * DEPARTMENT: Maths and stats
 */


import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] values;

    /*
    CODE WILL RUN IF IDIM AND JDIM VARIABLES IN MATRIX CLASS ARE PUBLIC NOT PROTECTED
    BUT THIS MIGHT GO AGAINST THE SPECIFICATION
    TO FIX:

     * Either, define a global private veriable within GenralMAtrix
     * class to hold iDim and jDim so that you do not have accessor issues
     * as defined in the error.
     * 
     * OR
     * 
     * Define getter and setter functions in the Matrix class, one example is commented out
     */
    

    /**
     * Constructor function: should initialise iDim and jDim through the Matrix
     * constructor and set up the data array.
     *
     * @param firstDim   The first dimension of the array.
     * @param secondDim  The second dimension of the array.
     */
    public GeneralMatrix(int firstDim, int secondDim) {
        // You need to fill in this method.
        /*
        I used the super function so the first and second dim can be inherited from the abstract matrix class. 
        I then set values to be a new double taking in the dimensions of that specific point to know where the value is.
        I then made an exception when the i and j where less than zero as this does not make sense
        */
        super(firstDim,secondDim); 
        values = new double[firstDim][secondDim];
        if(firstDim<0 || secondDim<0){
            throw new MatrixException("This is not within the bounds for a matrix");
        }
    }

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the second matrix.
     *
     * @param second  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix second) {
        // You need to fill in this method.
        /*
        The use of super is the same reason for the method above but for the dimensions of the second matrix
        for values this is then the same as the above but for the dimensions for the second matrix(the copy)
        Then iterate over each row and column index using two for loops up to the dimension-1 then
        assign the value of (i,j) of the second matrix to the (i,j) of the initial matrix using 
        values[][]
        then throw a matrix exception for if the dimensions of both matrices are not the same
        */
        super(second.iDim, second.jDim); 
        values = new double[second.iDim][second.jDim]; 
        for(int i=0;i <= second.iDim-1;i++){ 
            for (int j=0;j <= second.jDim-1;j++){ 
                values[i][j] = second.values[i][j]; 
            } 
        } 
        if(second.iDim != iDim || second.jDim!= jDim){
            throw new MatrixException("These matrices do not have same dimensions hence cannot be copied");
        }
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
        /*
        if the dimensions are within the correct index then return the associated values i and j using the 
        values[][] we used earlier. Otherwise we cannot access a value that has dimensions outside of the 
        specified indexes.
        */

        if(i>=0 && j>=0 && i <= iDim-1 && j <= jDim-1){ 
            return values [i][j]; 
        } 
        else{ 
            throw new MatrixException("You cannot get a value for these dimensions") ; 
        } 
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the values array.
     *
     * @param i      The location in the first co-ordinate.
     * @param j      The location in the second co-ordinate.
     * @param value  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double value) {
        // You need to fill in this method.
        /*
        similar to above however we assign a value to the values[i][j] value so we are not returning anything
        we use an if statement to declare if the indexes are within the dimensions of the matrix. If they are not then
        we throw a matrix exception to show the value has index out of bounds.
        */
        if(i>=0 && j>=0 && i <= iDim-1 && j <= jDim-1){ 
            values[i][j] = value;  
        }  
        else{ 
            throw new MatrixException("These dimensions cannot be set a value as they are out of bounds"); 
    
        } 
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
        /*
        check if the matrix is n by n if it is not then throw a matrix exception
        initialise a double called sign that is updated by the LUdecomp, this can either be -1 or 1 
        Then set the determinant equal to the sign to initialise it
        Then update the determiant using a for loop by multiplying all the diagonal elements
        using values[i][i]. Then return the final determinant.
        */
        if(iDim != jDim){ 
            throw new MatrixException("This is not an n by n matrix!!"); 
        } 
        double[] sign = new double[1]; 
        GeneralMatrix LUmx = LUdecomp(sign); 
       
        double determinant = sign[0]; 
        for(int i=0;i <= iDim-1;i++){ 
            determinant *= LUmx.values[i][i]; 
        
        } 
    return determinant; 
    }

    /**
     * Add the matrix to another second matrix.
     *
     * @param second  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the second matrix.
     */
    public Matrix add(Matrix second) {
        // You need to fill in this method.

        /*
        start off by checking if the dimensions are correct for summing matrices. If they are not then throw a matrix exception
        create a general matrix with dimensions idim and jdim. Then use a nested for loop to update a double called sum using get functions of the 
        initial matrix and the second matrix. The indexes will be the same as this is for addition
        Then update the output1 generalmatrix using setIJ replacing the i,j value with the output1 value
        */
        if(iDim !=second.iDim || jDim !=second.jDim){ 
            throw new MatrixException("These matrices cannot be summed together!:"); 
        } 
        GeneralMatrix output1 = new GeneralMatrix(iDim,jDim); 
        for(int i=0;i <= iDim-1;i++){ 
            for(int j=0;j <= jDim-1;j++){ 
                double sum = getIJ(i,j) + second.getIJ(i,j); 
                output1.setIJ(i,j,sum); 
            } 
        } 
        return output1;
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        // You need to fill in this method.
        /*
        check if the dimensions are correct for matrix multiplication, if not then throw a matrix exception
        otherwise create a new general matrix with the dimensions of the product- we know this from matrix multiplication.
        Then use a nested for loop to represent the index of the row and columns up to the final row and column. 
        Then intialise a double called product by setting it equal to 0. We then use another for loop with the same number of iterations
        as i and j so we can update the product double using the matrix multpication formula we know from maths.
        Then update the output2 matrix for each set of co ordinates for each iteration using the set function and we set this (i,j)
        to be equal to the product -double
        */
        if(jDim !=A.iDim){ 
            throw new MatrixException("You cannot multiply these matrices due to their dimensions"); 
        } 
        else{ 
            GeneralMatrix output2 = new GeneralMatrix(iDim,A.jDim); 
            for(int i=0;i <= iDim-1;i++){ 
                for(int j=0;j <= A.jDim-1;j++){ 
                    double product = 0; 
                    for(int k=0;k <= jDim-1;k++){ 
                        product += getIJ(i,k)*A.getIJ(k,j); 
                    } 
                    output2.setIJ(i,j,product); 
                } 
            } 
            return output2;
        }  
    }

    /**
     * Multiply the matrix by a scalar.
     *
     * @param scalar  The scalar to multiply the matrix by.
     * @return        The product of this matrix with the scalar.
     */
    public Matrix multiply(double scalar) {
        // You need to fill in this method.
        /*
        if the scalar we multiply by is equal to zero then create a new general matrix with the same dimensions which is always going to be 
        a zero matrix
        otherwise i created a general matrix with the same dimensions and used a nested for loop  both going from the first row and column
        to the last row and column. Then update the output matrix using the set function by choosing the certain row and column we want to 
        change and update it with the original value of the co ordinate multiplied by the scalar. we then return the updated general matrix
        */
        if(scalar == 0){  
            return new GeneralMatrix(iDim, jDim); 
        } 
        else{ 
            GeneralMatrix output3 = new GeneralMatrix(iDim,jDim); 
            for(int i=0;i<=iDim-1;i++){ 
                for(int j=0;j <= jDim-1;j++){ 
                    output3.setIJ(i,j,getIJ(i,j)*scalar); 
                } 
            } 
            return output3; 
        } 
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
        /*
        again we use a nested for loop from the first row and columnn to the last row and column to ensure we iterate over all values in the 
        matrix. Then create a double called unifnumber which we can give a random number using the math.random function. 
        At each iteration this will then update the matrix that we can choose by using the setIJ function and replacing the (i,j)th value 
        with the unifnumber random double.
        */
        for(int i=0;i<=iDim-1;i++){ 
            for(int j=0;j <= jDim-1;j++){ 
                double unifnumber = Math.random(); 
                setIJ(i,j,unifnumber); 
            } 
        } 
    }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     * 
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 u_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * sign[0] calculated by the function.
     * 
     * If the matrix is singular, then the routine throws a MatrixException.
     * In this case the string from the exception's getMessage() will contain
     * "singular"
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     * 
     * @param sign  An array of length 1. On exit, the value contained in here
     *              will either be 1 or -1, which you can use to calculate the
     *              correct sign on the determinant.
     * @return      The LU decomposition of the matrix.
     */
    public GeneralMatrix LUdecomp(double[] sign) {
        // This method is complete. You should not even attempt to change it!!
        if (jDim != iDim)
            throw new MatrixException("Matrix is not square");
        if (sign.length != 1)
            throw new MatrixException("d should be of length 1");
        
        int           i, imax = -10, j, k; 
        double        big, dum, sum, temp;
        double[]      vv   = new double[jDim];
        GeneralMatrix a    = new GeneralMatrix(this);
        
        sign[0] = 1.0;
        
        for (i = 1; i <= jDim; i++) {
            big = 0.0;
            for (j = 1; j <= jDim; j++)
                if ((temp = Math.abs(a.values[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }
        
        for (j = 1; j <= jDim; j++) {
            for (i = 1; i < j; i++) {
                sum = a.values[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.values[i-1][k-1]*a.values[k-1][j-1];
                a.values[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= jDim; i++) {
                sum = a.values[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.values[i-1][k-1]*a.values[k-1][j-1];
                a.values[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= jDim; k++) {
                    dum = a.values[imax-1][k-1];
                    a.values[imax-1][k-1] = a.values[j-1][k-1];
                    a.values[j-1][k-1] = dum;
                }
                sign[0] = -sign[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.values[j-1][j-1] == 0.0)
                a.values[j-1][j-1] = 1.0e-20;
            if (j != jDim) {
                dum = 1.0/a.values[j-1][j-1];
                for (i = j+1; i <= jDim; i++)
                    a.values[i-1][j-1] *= dum;
            }
        }
        
        return a;
    }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // Test your class implementation using this method.
        // create matrices and give them values 
        GeneralMatrix matrix1 = new GeneralMatrix(3,3); 
        System.out.println("matrix1:"); 
        System.out.println(String.format(matrix1.toString())); 
        matrix1.setIJ(1,1,3); 
        matrix1.setIJ(2,2,-2); 
        matrix1.setIJ(1,2,4); 
        matrix1.setIJ(0,0,1); 
        System.out.println("matrix1 after setter function:"); 
        System.out.println(String.format(matrix1.toString())); 
        GeneralMatrix matrix3 = new GeneralMatrix(2,2); 
        matrix3.setIJ(0,0,-4); 
        matrix3.setIJ(1,1,2); 
        System.out.println("matrix3 after setter function:"); 
        System.out.println(String.format(matrix1.toString())); 
// test the get function  
        GeneralMatrix matrix2 = new GeneralMatrix(3,3); 
        matrix2.setIJ(0,2,-5); 
        System.out.println("matrix2:"); 
        System.out.println(String.format(matrix2.toString())); 
        double gettester = matrix1.getIJ(1,1); 
        System.out.println("Value at (1,1) for matrix 1:"+gettester); 
// test the determinant function 
        double detofmatrix1 = matrix1.determinant(); 
        System.out.println(" det of matrix 1:"+ detofmatrix1); 
      //  double detofmatrix2 = matrix2.determinant(); 
      //  System.out.println("det of matrix 2:"+detofmatrix2); 
// add function 
       GeneralMatrix outcome = (GeneralMatrix) matrix1.add(matrix2); 
        System.out.println("Outcome of the sum:"); 
        System.out.println(String.format(outcome.toString())); 
    //    GeneralMatrix invalid1 = (GeneralMatrix) matrix3.add(matrix1); 
      //  System.out.println("invalid1 test:"); 
    //    System.out.println(String.format(invalid1.toString())); 
 

        //multiply function 
        GeneralMatrix product = (GeneralMatrix) matrix1.multiply(matrix2); 
        System.out.println("product of matrix1 x matrix2:"); 

        System.out.println(String.format(product.toString())); 

        // multiply scalar function
        GeneralMatrix product2 = (GeneralMatrix) matrix1.multiply(5.0);
        System.out.println("product of matrix multiplied by scalar:");
        System.out.println(String.format(product2.toString()));

        // random function
        GeneralMatrix randmatrix = new GeneralMatrix(3,3);
        randmatrix.random();
        System.out.println("The random matrix:");
        System.out.println(String.format(randmatrix.toString()));

 
   }
}