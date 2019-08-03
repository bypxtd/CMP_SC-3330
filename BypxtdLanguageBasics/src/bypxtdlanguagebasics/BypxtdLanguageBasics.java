/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdlanguagebasics;

/**
 *
 * @author bruce
 */
public class BypxtdLanguageBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Variables
        byte sample1 = 0x3A;
        byte sample2 = 58;
        short heartRate = 85;
        long deposits = 135002796;
        float acceleration = 9.584f;
        float mass = 14.6f;
        double distance = 129.763001;
        boolean lost = true;
        boolean expensive = true;
        int choice = 1;
        char integral = '\u222B';
        char letter1 = 'a';
        char letter2 = 97;
        String greeting = "Hello";
        String name = "Karen";
        
        // Challenge 1
        if(sample1 == sample2)
        {
            System.out.println("The samples are equal.");
        }
        else
        {
            System.out.println("The samples are not equal.");
        }
        
        // Challenge 2
        if(heartRate >= 40 && heartRate<= 80)
        {
            System.out.println("Heart rate is normal.");
        }
        else
        {
            System.out.println("Heart rate is not normal.");
        }
        
        // Challenge 3
        if(deposits >= 100000000)
        {
            System.out.println("You are exceedingly wealthy.");
        }
        else
        {
            System.out.println("Sorry you are so poor.");
        }
        
        // Challenge 4
        float force = mass * acceleration;
        System.out.printf("force = %f\n", force);
        
        // Challenge 5
        System.out.printf("%f is the distance.\n", distance);
        
        // Challenge 6
        if(lost == true && expensive == true)
        {
            System.out.println("I am really sorry! I will get the manager.");
        }
        else if(lost == true && expensive == false)
        {
            System.out.println("Here is coupon for 10% off.");
        }
        
        // Challenge 7
        switch(choice)
        {
            case 1: 
                System.out.println("You chose 1.");
                break;
            
            case 2: 
                System.out.println("You chose 2.");
                break;
            
            case 3: 
                System.out.println("You chose 3.");
                break;
            
            case 4: 
                if(choice != 1 || choice != 2 || choice != 3)
                {
                    System.out.println("You made an unknown choice.");
                }   
                break;
        }
        
        // Challenge 8
        System.out.printf("%c is an integral.\n", integral);
        
        // Challenge 9
        if(letter1 == letter2)
        {
            System.out.printf("%c and %c are the same.\n", letter1, letter2);
        }
        else if(letter1 != letter2)
        {
            System.out.printf("%c and %c are not the same.\n", letter1, letter2);
        }
        
        // Challenge 10
        for(int i = 5; i < 11; i++)
        {
            System.out.printf("i = %d\n", i);
        }
        
        // Challenge 11
        int age = 0;
        while(age < 6)
        {
            System.out.printf("age = %d\n", age++);
        }
        
        // Challenge 12
        System.out.printf("%s %s\n", greeting, name);
    }
    
}
