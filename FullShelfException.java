package com.company;

/**
 * This exception class is thrown to check if the bookshelf object is full (has reached 20 book objects)
 *
 *  @author  Tracy Yip Student ID: 114527635 Recitation: R-03
 */
public class FullShelfException extends Exception
{
    /**
     * Constructs an FullShelfException that passes
     * a string to its super class (Exception)
     */
    public FullShelfException()
    {
        super("The shelf is already full. Please consider trying another shelf.");
    }

    /**
     * Constructs an FullShelfException that passes
     * a specified string given in the parameter to its
     * super class (Exception)
     *
     * @param in
     *    A specified string of message that is desired to be printed
     */
    public FullShelfException(String in)
    {
        super(in);
    }
}
