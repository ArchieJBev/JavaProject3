/*
 * PROJECT III: Matrix.java
 *
 * This file contains a template for the class Matrix. Not all methods are
 * implemented and they do not have placeholder return statements. Make sure 
 * you have carefully read the project formulation before starting to work 
 * on this file.
 *
 * Some of the methods here are abstract. That means that they must be
 * implemented by their subclasses. If you're not sure about abstract classes,
 * you should consult the lecture notes for more information.
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

public abstract class Matrix {
    /**
     * Two variables to describe the dimensions of the Matrix.
     */
    protected int iDim;
    protected int jDim;

    /**
     * Constructor function. This is protected since abstract classes cannot
     * be instantiated anyway. Subclasses should call this function from their
     * constructors to set iDim and jDim.
     *
     * @param firstDim  The first dimension of the matrix.
     * @param secondDim  The second dimension of the matrix.
     */
    protected Matrix(int firstDim, int secondDim) { 
        // This method is complete.
        iDim = firstDim; 
        jDim = secondDim; 
    }
    // public int getIDim(){
    //     return iDim;
    // }
    // pubilc int getJDim(){
    //     return jDim;
    // }
    
    /**
     * Returns a String representation of the Matrix using the getIJ getter
     * function. You should use String.format() to format the double numbers to a
     * sensible number of decimal places and place the numbers in columns.
     * Remember: %n at the end of each row in the string will insert a 
     * newline character.
     * 
     * e.g.
     *  -2.00   0.00   0.00
     *   1.00  -1.00   0.12 
     *   0.00   0.00   1.00 
     *
     * @return A String representation of the Matrix.
     */
    public String toString() {
        // You need to fill in this method.
        /*Here i am initialising a variable called output by putting it equal to "" so i can add values to the 
        string using the get function. I then use two for loops to represent the row and column dimensions starting from 0 and ending at
        the dimension-1 . I then use the get function to get the value for that specfic co-ordinate. Then i made a string where i added the ij
        value and used %.2f to split up each row.  I had to use String.format in order to make this work as i kept encountering the "%.2f"
        being printed rather than it separating a new line. I then used += to update the output string every iteration
        */
        String output = "";
        for(int i=0;i <= iDim-1;i++){
            for(int j=0;j <= jDim-1;j++){
                double ij = getIJ(i,j);
                String newdpij = String.format("%.2f",ij);
                output += newdpij + " ";

            }
            output += "%n";
        }
        return output.toString() ;
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public abstract double getIJ(int i, int j);

    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public abstract void   setIJ(int i, int j, double val);
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public abstract double determinant();

    /**
     * Add the matrix to another second matrix.
     *
     * @param second  The Matrix to add to this matrix.
     * @return        The sum of this matrix with the second matrix.
     */
    public abstract Matrix add(Matrix second);

    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public abstract Matrix multiply(Matrix A);

    /**
     * Multiply the matrix by a scalar.
     *
     * @param scalar  The scalar to multiply the matrix by.
     * @return        The product of this matrix with the scalar.
     */
    public abstract Matrix multiply(double scalar);
    
    /**
     * Fills the matrix with random numbers which are uniformly distributed
     * between 0 and 1.
     */
    public abstract void random();


}

