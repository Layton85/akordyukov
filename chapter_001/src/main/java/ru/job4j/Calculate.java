package ru.job4j;

/**
* Class for calculate task.
*
* @author Alexander Kordyukov (alex-programm@yandex.ru)
* @version $Id$
* @since 0.1
*/
public class Calculate {
	/**
    * Constructor, outputting a string to the console
    *
    * @param arg - arg
	*/
    public static void main(String[] args) {
        System.out.println("Hello, World !");
    }
	
	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}
}