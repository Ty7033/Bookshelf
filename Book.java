package com.company;

/**
 * This class represents a book that has a title, author, borrower, and condition.
 *
 *  @author  Tracy Yip
 */

public class Book
{
    private String title;//A string that holds the title of the book
    private String author;//A string that holds the author of the book
    private String borrower;//A string that holds the borrower of the book
    private int condition;// An integer that holds the condition of the book

    /**
     * This is a constructor used to create a new Book object.
     *
     * @param title
     *    The title of the book
     * @param author
     *    The author of the book
     * @param condition
     *    The current condition of the book ranging from 1-5
     */
    public Book(String title, String author, int condition)
    {
        this.author = author;
        this.title = title;
        this.condition = condition;
        this.borrower = "";
    }

    /**
     * This is a getter method in the Book class.
     *
     * @return
     *    The author of the book
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * This is a getter method in the Book class.
     *
     * @return
     *    The title of the book
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * This is a getter method in the Book class.
     *
     * @return
     *    The borrower of the book
     */
    public String getBorrower()
    {
        return borrower;
    }

    /**
     * This is a getter method in the Book class.
     *
     * @return
     *    The condition of the book
     */
    public int getCondition()
    {
        return condition;
    }

    /**
     * This is a method that sets the author of the book to the String in its parameter.
     *
     * @param author
     *    The author of the book
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * This is a method that sets the title of the book to the String in its parameter.
     *
     * @param title
     *    The title of the book object
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * This is a method that sets the borrower of the book to the String in its parameter.
     *
     * @param borrower
     *    The new borrower of the book object
     */
    public void setBorrower(String borrower)
    {
        this.borrower = borrower;
    }

    /**
     * This is a method that sets the condition of the book to the integer in its parameter.
     *
     * @param condition
     *    The condition of the book (1-5)
     */
    public void setCondition(int condition)
    {
        this.condition = condition;
    }

    /**
     * This method is used to clone or copy the title, author, and condition of a Book into a new Book object
     *
     * @return
     *    A cloned book represented with the name newBook
     */
    public Object clone()
    {
        Book newBook = new Book(title, author, getCondition());
        return newBook;
    }

    /**
     * This equals method determines whether a book object is equal to the object passed in the
     * parameter.It first checks whether the object passed in the parameter is an instance of Book.
     * If true,the object is typecasted and represented in a Book object called "a", where its author,
     * title, and condition are compared to the first book's properties.
     *
     * @param o
     *    The book that is being compared to the current or first book object
     * @return
     *     A boolean that indicates if the two books are equal or not
     */
    public boolean equals(Object o)
    {
        if (o instanceof Book)
        {
            Book a = (Book) o;
            return (this.author.equals(a.author) && this.title.equals(a.title) && this.condition == a.condition);
        }
        else
        {
            return false;
        }
    }

    /**
     * This toString method organizes the book's title, author, condition,
     * and borrower in a row that is neatly spaced out. If the borrower field is an empty string,
     * it is printed out as "<none>".
     *
     * @return
     *     A string that contains the book object's properties with the correct
     *     spacing in the order of title, author, condition, and borrower
     */
    public String toString()
    {
        if(borrower.equals(""))
        {
            setBorrower("<none>");
        }
        return String.format("%-50s\t %-40s\t %-20d\t %-30s\t", title, author, condition, borrower);
    }
}

