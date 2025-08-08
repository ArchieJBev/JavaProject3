/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
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
 * DEPARTMENT: Mathematics and statistics
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diagonal;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upperDiagonal;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lowerDiagonal;
    
    /**
     * Constructor function: should initialise iDim and jDim through the Matrix
     * constructor and set up the values array.
     *
     * @param dimension  The dimension of the array.
     */
    public TriMatrix(int dimension) {
        // You need to fill in this method.
        /*
        Use the super function to inherit the dimension from the abstract matrix class. Then make  new double arrays 1 with 
        the same length as the dimension and 2 with the length-1. Name these arrays by the diagonal in which they represent- lower upper or just
        diagonal
        */
        super(dimension,dimension);
        diagonal = new double[dimension];
        upperDiagonal = new double[dimension -1];
        lowerDiagonal = new double[dimension -1];

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
        we check that if i is equal to j meaning the row index is equal to the column index then we return the diagonal array value i
        if the row is equal to the column+1 then we return the upperdiagonal value of the column index value
        if the row index is equal to the column index minus one then we return the i index value of the lowerdiagonal  array
        then we check if the i and j are out of bounds meaning less than 0 or above the dimensions for that array. 
        if they are then we throw a matrix exception
        for any other case we return 0 as the matrix is tridiagonal so most of the values will be zero
        */
        if(i==j){
            return diagonal[i];
        }
        else if(i==j+1){
            return upperDiagonal[j];
        }
        else if(i==j-1){
            return lowerDiagonal[i];
        }
        else if(i<0 || j<0){
            throw new MatrixException("Dimensions are out of bounds");
        }
        else{
            return 0.0;
        }
        

    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i      The location in the first co-ordinate.
     * @param j      The location in the second co-ordinate.
     * @param value  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double value) {
        // You need to fill in this method.
        /*
        similar to above we check if the row index is equal to the column index, if it is then we assign the value in the set fucntion to be the 
        ith index in the diagonal array.
        We then do the same if statements as above and we set the value equal to the j and i th index in the array for upper and lower diagonal
        */
        if(i==j){
            diagonal[i]=value;
        }
        else if(i==j+1){
            upperDiagonal[j]=value;
        }
        else if(i==j-1){
            lowerDiagonal[i]=value;
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
        we check if the matrix is n by n, if it is not then we throw a matrix exception
        we then have a trimatrix called ditz that we use the LU decomp function on so we can split it up into 2 matrices
        From the LUdecomp function i made i know all i have to do for the determinant of the two is to multiply the 
        diagonals of the Upper matrix. 
        So initialise the det double to be 1 and iterate through all the indexes in the diagonal array whilst multiplying them
        so we get the product of each diagonal in the upper matrix. then return the determinant
        */
        if(iDim !=jDim){
            throw new MatrixException("This matrix has no determinant due its dimensions!");
        }

        TriMatrix ditz = LUdecomp();
        double det = 1.0 ;
        for(int i=0;i<iDim;i++){
        det *= ditz.diagonal[i];
        }

        return det;




    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix LUdecomp() {
        // You need to fill in this method.
        /*
        start by making a trimatrix called LUTri with idim as its n by n dimension
        by help from the project specification we can split the tri diagonal matrix up into lower and upper matrix where the lower
        has diagonal elements of all 1 and for the upper diagonal of the upper matrix the values match that of the origin tri diagonal matrix
        Hence we can make formulas from this matrix multiplcation which i did on paper to find out the diagonal of the upper matrix and
        the lower diagonal of the lower matrix of the LU decomp.
        For the 1st row and column the formula does not match so we define them in the code separately however for the rest we use for loops
        to iterate over each row and column starting from index 1 and up until the final index of the array - these will be different for 
        diagonal as this has 1 more value than the lower and upper diagonal.
        After coding the formulas we use .diagonal[i] to set the specifc index of the specific array to the LUTri matrix. 
        After updating this matrix over the many different for loops for each array we then return the final LU matrix which is combined into 1 
        matrix as the 1 diagonals of L are replaced by the diagonals of the U matrix
        */
        
        TriMatrix LUTri = new TriMatrix(iDim);

        for(int i=0;i<iDim-1;i++){
            LUTri.lowerDiagonal[i] = lowerDiagonal[i];
        }

             LUTri.diagonal[0] = diagonal[0];
         
        
            LUTri.upperDiagonal[0] = upperDiagonal[0]/ diagonal[0];
         
        for(int i=1;i<iDim;i++){
            for(int a=1;a<iDim-1;a++){
                LUTri.diagonal[i] = diagonal[i] - lowerDiagonal[i-1]*LUTri.upperDiagonal[i-1];
                LUTri.upperDiagonal[a] = upperDiagonal[a]/LUTri.diagonal[a];

          }
       }


        return LUTri;

    }

    /**
     * Add the matrix to another second matrix.
     *
     * @param second  The Matrix to add to this matrix.
     * @return        The sum of this matrix with the second matrix.
     */
    public Matrix add(Matrix second){
        // You need to fill in this method.
        /*
        we first check to see if the matrices have the correct dimensions for summing. If they dont we throw a matrix exception 
        to show this is impossible. 
        we then create a tri matrix called sum with dimensions idim (n by n)
        For each of the diagonals i used a for loop from the first index in their array to their last. 
        I then used the set function to change the certain co ordinate of the array. 
        I had to cast the second matrix as a trimatrix as i kept getting an error that i could not convert a matrix to a tri matrix
        but i use the .diagonal to get the diagonal of the tri matrix and then add them to the corresponding index of the other matrix
        Then after all iterations i return the sum which should be a trimatrix

        */
       if(iDim != second.iDim || jDim != second.jDim){
        throw new MatrixException("These matrices cannot be summed together due to dimensions!");
       }
       TriMatrix sum = new TriMatrix(iDim);
       // for main diagonal
       for(int i=0; i< iDim;i++){
        sum.setIJ(i,i,diagonal[i]+((TriMatrix) second).diagonal[i]);
       }
       // upper diagonal
       for(int i=0; i<iDim-1;i++){
        sum.setIJ(i+1,i,upperDiagonal[i]+((TriMatrix) second).upperDiagonal[i]);
       }
       // lower diagonal
       for(int i=0;i<iDim-1;i++){
        sum.setIJ(i,i+1,lowerDiagonal[i]+ ((TriMatrix) second).lowerDiagonal[i]);

       }
       
        return sum;


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
         first we check if the dimensions are eligible for matrix multiplication, if they are not we throw a matrix exception
         otherwise the dimensions are fine so we create a general matrix called output and give it the correct dimensions after multpilcation
         with A. In the exact same way as in the general matrix class we calculate the product of one co-ordinate in the matrix by 
         using a nested for loop to represent the rows and columns then another for loop in that to represent a place holder for the multiplication.
         We then return the updated sum matrix that we updated using the set function

        */
        if(jDim !=A.iDim){
            throw new MatrixException("These matrices cannot be multiplied due to their dimensions!");
        }
        if(iDim !=jDim || A.iDim != A.jDim){
            throw new MatrixException("One or both of the matrices is not a tri diagonal matrix!");
        }
        else{
            GeneralMatrix output = new GeneralMatrix(iDim,A.jDim); 
            for(int i=0;i <= iDim-1;i++){ 
                for(int j=0;j <= A.jDim-1;j++){ 
                    double p = 0; 
                    for(int k=0;k <= iDim-1;k++){ 
                        p += getIJ(i,k)*A.getIJ(k,j); 
                    } 
                    output.setIJ(i,j,p); 
                } 
            } 
            return output;
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
        we start by creating a tri diagonal matrix called output5, we then use a nested for loop to iterate over all rows and columns of the index 
        so we start at 0 and end at the dimension-1. Then we use the set function to choose what co ordinate we want to change and we use 
        get to get the value of that certain (i,j) and then multiply this by the scalar double that is put into the multiply function.
        then return the matrix we created.

        */

        TriMatrix output5 = new TriMatrix(iDim); 
            for(int i=0;i<=iDim-1;i++){ 
                for(int j=0;j <= jDim-1;j++){ 
                    output5.setIJ(i,j,getIJ(i,j)*scalar); 
                } 
            } 
            return output5; 
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
        /*
        we start by using a for loop to iterate through the upper and lower diagonals of the tri matrix as these have the same number of values in
        their array. We then set the upper and lower diag with the math.random function we used in general matrix class. 
        We then do this again but for the main diagonal so the number of iterations will be 1 more. Once again we use the set and the MAth.random
        like in the for loop above.
        */
        for(int i=0;i<iDim-1;i++){ 
                // upper diag
                    setIJ(i,i+1,Math.random());
                    // lower
                    setIJ(i+1,i,Math.random());
            } 
            for(int i=0;i<=iDim-1;i++){
                // main diagonal
                setIJ(i,i,Math.random()); 
            }
        } 
    
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // Test your class implementation using this method.
        // use the set function to make a matrix

    TriMatrix tridiag1 = new TriMatrix(3);
    tridiag1.setIJ(0, 0, 3);
    tridiag1.setIJ(0, 1, 5);
    tridiag1.setIJ(1, 0, 9);
    tridiag1.setIJ(1, 1, 5);
    tridiag1.setIJ(1, 2, 0);
    tridiag1.setIJ(2, 2, 2);

    System.out.println("Tri diagonal matrix 1:");
    System.out.println(String.format(tridiag1.toString()));

    // test the get function
    double gettester2 = tridiag1.getIJ(0,1);
    System.out.println("The value at (0,1) is:"+ gettester2);

    // test the add function

    TriMatrix tridiag2 = new TriMatrix(3);
    tridiag2.setIJ(0,0,5);
    tridiag2.setIJ(0,1,1);
    tridiag2.setIJ(1,0,3);
    tridiag2.setIJ(1,1,4);
    tridiag2.setIJ(2,1,2);
    tridiag2.setIJ(1,2,7);
    tridiag2.setIJ(2,2,8);

    System.out.println("Tri diagonal matrix 2:");
    System.out.println(String.format(tridiag2.toString()));

    TriMatrix sum2 = (TriMatrix) tridiag2.add(tridiag1); 
    System.out.println("Outcome of the sum:"); 
    System.out.println(String.format(sum2.toString())); 



    // test the multiply function
    Matrix product12 =  tridiag1.multiply(tridiag2);
    System.out.println("product of tridiag1 x tridiag2:"); 
    System.out.println(String.format(product12.toString())); 


    // test the scalar multiplication method
    Matrix scaledmatrix = tridiag1.multiply(5.0);
    System.out.println("product of tri diagonal matrix multiplied by scalar:");
    System.out.println(String.format(scaledmatrix.toString()));


    // test random function 
    tridiag1.random();
    System.out.println("The random tri diagonal matrix:");
    System.out.println(String.format(tridiag1.toString()));

    // LU decomp function
    TriMatrix LUTridecomp = tridiag2.LUdecomp();
    System.out.println("The LU decomp of this matrix is:");
    System.out.println(String.format(LUTridecomp.toString()));

    TriMatrix tridiag3 = new TriMatrix(3);
    tridiag3.setIJ(0, 0, 3);
    tridiag3.setIJ(0, 1, 5);
    tridiag3.setIJ(1, 0, 9);
    tridiag3.setIJ(1, 1, 5);
    tridiag3.setIJ(1, 2, 0);
    tridiag3.setIJ(2, 2, 2);

    System.out.println("Tri diagonal matrix 3:");
    System.out.println(String.format(tridiag3.toString()));

    TriMatrix LUTridecomp3 = tridiag3.LUdecomp();
    System.out.println("The LU decomp of this matrix3 is:");
    System.out.println(String.format(LUTridecomp3.toString()));

    // test determinant function

    double tridiag3det = tridiag3.determinant();
    System.out.println("The determinant of this matrix is:"+tridiag3det);
    

    

    

















    }
}