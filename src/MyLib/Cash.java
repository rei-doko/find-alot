/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myLib;

/**
 *
 * @author Admin
 */
public class Cash implements Payment{
    private double contactPrice;
    private double capitalTax;
    private double documentaryStampTax;
    private double transferTax;
    private double registrationFee;
    private double notarial;
    private double agentFee;
    
    private double finalPayment;

    public Cash(double finalPayment) {
        this.finalPayment = finalPayment;
    }
    
    public double findFinalPayment(){
        
        //general taxes
        capitalTax=propertyPrice*0.06;
        documentaryStampTax=propertyPrice*0.15;
        transferTax=propertyPrice*0.05;
        registrationFee=propertyPrice*0.03;
        notarial=propertyPrice*0.01;
        //////////////////////////////////////////////////////////////////////////////////////////
        agentFee=propertyPrice*0.03;
        
        finalPayment=propertyPrice+documentaryStampTax+transferTax+registrationFee+notarial;
        return finalPayment;
    }
    
    public void showReceipt(){
        System.out.println("Contact Price:"+contactPrice);
        System.out.println("Capital Tax: "+capitalTax);
        System.out.println("Documentary Stamp Tax: "+documentaryStampTax);
        System.out.println("Transfer Tax: "+transferTax);
        System.out.println("Registration Fee: "+registrationFee);
        System.out.println("Notarial Fee: "+notarial);
        System.out.println("Final Payment: "+finalPayment);
    }

    public void setAgentFee(double agentFee) {
        this.agentFee = agentFee;
    }
    
    
    
}
