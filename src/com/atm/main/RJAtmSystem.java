
package com.atm.main;

import com.atm.controller.Depositor;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * rjbautista1990@yahoo.com
 * @author RJ Bautista
 */
public class RJAtmSystem
{
    private Depositor depositor;
    private String pin;
    private String inputOldPin;
    private String newPin;
    private String amount;
    private double amountDeposit;
    private double amountWithdraw;
    private int response;
        
    /**
     * Initialize balance and pin
     */
    public RJAtmSystem() 
    {
        depositor = new Depositor();
        init();
    }
    
    private void init()
    {
        displayWelcome();
        displayMenu();
    }
    
    public void displayWelcome()
    {
        pin = JOptionPane.showInputDialog(null, "Welcome to AMA Bank!"
             + "\n Please enter your 4-digit PIN: ");
        
        while ( ! pin.equals(depositor.getPin()) ) {            
            pin = JOptionPane.showInputDialog(null, "Invalid PIN."
             + "\n Please enter your 4-digit PIN: ");
            
            if ( pin.equals(depositor.getPin()) ) {
                break;
            }
        }        
    }

    public void displayMenu()
    {
        Object[] options = {"Withdraw", "Deposit", "Check Balance", "Change PIN", "Exit"};
     
        int selected = returnResponse(options, "Select transaction", 1);
        
        switch(selected)
        {
            case 0:
                withdraw();
                break;
            case 1:
                deposit();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                changePin();
                break;
            case 4:
                init();
                break;
        }
    }
    
    public void withdraw()
    {
        amount = JOptionPane.showInputDialog(null, 
        " Please Enter Amount to Withdraw :");

        amountWithdraw = Double.parseDouble(amount);
   
        if ( amountWithdraw > depositor.getBalance()) {
            JOptionPane.showMessageDialog(null,"\n Sorry! Your balance is not enough "
            + "for this transaction.. P" + depositor.getBalance());
            displayMenu();

        } else {
            depositor.withdraw(amountWithdraw); 
            JOptionPane.showMessageDialog(null,"\n Please get your cash. Thank you");
            JOptionPane.showMessageDialog(null, "\n Your current balance is P"
                + depositor.getBalance());
            
            Object[] options = {"Yes, please", "No thanks!"};
            
            anotherTransaction(options, "withdraw");
            
            displayMenu();
        }

    }

    public void deposit()
    {
        amount = JOptionPane.showInputDialog(null, "Enter amount :");
      
        amountDeposit = Double.parseDouble(amount);
      
        depositor.deposit(amountDeposit);
        
        JOptionPane.showMessageDialog(null, "Your account balance is : P"
            + depositor.getBalance());

        Object[] options = {"Yes, please", "No thanks!"};
        response = returnResponse( options, "Would you like to print receipt?", 0);
                
        if (response == 0) {
            JOptionPane.showMessageDialog(null, "Your account "
                + "balance is : P" + depositor.getBalance() + "\n"
                + "You deposit P" + amountDeposit);
            
            displayMenu();
            
        } else {
            
            anotherTransaction(options, "deposit");
            
            displayMenu();
        }         
    }

    public void checkBalance()
    {
        JOptionPane.showMessageDialog( null,
            "Your balance is : P" + depositor.getBalance());

        displayMenu();
    }    

    public void changePin()
    {
        inputOldPin = JOptionPane.showInputDialog(null, "Please Enter Old PIN");
            
        while ( ! inputOldPin.equals(depositor.getPin()) ) {
            inputOldPin = JOptionPane.showInputDialog(null, "Please Enter Old PIN");
        }
      
        newPin = JOptionPane.showInputDialog(null, "Enter new PIN");
        depositor.setPin(newPin);

        JOptionPane.showMessageDialog(null, "PIN changed..");
        displayMenu();
    }

    public int returnResponse(Object options[], String question, int jOption)
    {
        int response = JOptionPane.showOptionDialog(null,
                question,
                "",
                (jOption == 0) ? JOptionPane.YES_NO_OPTION : JOptionPane.DEFAULT_OPTION,                
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        
        return response;
    }
    
    /**
     * if code is 0 it is a withdrawal, else deposit
     */
    private void anotherTransaction(Object[] options, String depositOrWithdraw)
    {
        
        do {
            response = returnResponse(  options, 
                                        "Would you like another transaction?", 
                                        0); // 0 means yesNo Option
                
            if (response == 1) { break; }                
                
            if (depositOrWithdraw.equals("withdraw")) {
                withdraw();
            }
                
            if (depositOrWithdraw.equals("deposit")) {
                deposit();
            }

            JOptionPane.showMessageDialog(null, "Your account balance is : P"
                    + depositor.getBalance());
                
        } while (response == 0);
    }
        
    // Start
    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            RJAtmSystem atm = new RJAtmSystem(); 
	} catch(Exception e) {
            System.out.println("Loading failed");
	}
          
   }
}
