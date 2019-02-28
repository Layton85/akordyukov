package ru.job4j.function;

/**
* Calculator.
*
* @author Alexander Kordyukov (alex-programm@yandex.ru)
* @version $Id$
* @since 0.1
*/
public class Calculator {
    /** internal variable for result */
	private double result;
    
	/** addition function */
    public void add(double first, double second) {
        this.result = first + second;
    }
	
	/** subtraction  function */
	public void subtract(double first, double second) {
		this.result = first - second;
	}
	
	/** division function */
	public void div(double first, double second) {
		this.result = first / second;
	}

	/** multiplication function */
	public void multiple(double first, double second) {
		this.result = first * second;
	}
    
    /** getting result function */
	public double getResult() {
        return this.result;
    }
}