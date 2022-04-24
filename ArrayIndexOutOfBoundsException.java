package com.company;

/**
 * This exception class is thrown to check if an index is valid in the array
 *
 *  @author  Tracy Yip
 */
public class ArrayIndexOutOfBoundsException extends Exception
{
    /**
     * Constructs an ArrayIndexOutOfBoundsException that passes
     * a string to its super class (Exception)
     */
    public ArrayIndexOutOfBoundsException()
    {
        super("There is no book at the index. Please try again from the beginning.");
    }

    /**
     * Constructs an ArrayIndexOutOfBoundsException that passes
     * a specified string given in the parameter to its
     * super class (Exception)
     *
     * @param in
     *    A specified string of message that is desired to be printed
     */
    public ArrayIndexOutOfBoundsException(String in)
    {
        super(in);
    }
}
