package com.atm.controller;

public class Depositor 
{
    private double balance;
    private String pin;

    /**
     * Initialize depositor 
     * balance and pin number
     */
    public Depositor() {
        setBalance(3000);
	setPin("2020");
    }

    public double getBalance() { 
        return balance; 
    }
    
    public void setBalance(double balance) {
        this.balance = balance; 
    }
    
    public String getPin() { 
        return pin; 
    }
    
    public void setPin(String pin) { 
        this.pin = pin; 
    }
    
    public double withdraw(double amount) {
        return (balance -= amount); 
    }
    
    public double deposit(double amount) { 
        return (balance += amount); 
    }

}