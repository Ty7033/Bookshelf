package com.company;

/**
 * This exception class is thrown to check if the bookshelf object is empty (has no book objects)
 *
 *  @author  Tracy Yip
 */
public class EmptyShelfException extends Exception
{
    /**
     * Constructs an EmptyShelfException that passes
     * a string to its super class (Exception)
     */
    public EmptyShelfException()
    {
        super("The shelf is empty. There are no books to remove. " +
            "Please consider adding books on it before deciding to remove them or trying another shelf.");
    }

    /**
     * Constructs an EmptyShelfException that passes
     * a specified string given in the parameter to its
     * super class (Exception)
     *
     * @param in
     *    A specified string of message that is desired to be printed
     */
    public EmptyShelfException(String in)
    {
        super(in);
    }
}