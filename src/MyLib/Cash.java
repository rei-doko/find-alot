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
    private Property property;

    private double contactPrice;
    private double capitalTax;
    private double documentaryStampTax;
    private double transferTax;
    private double registrationFee;
    private double notarial;
    private double agentFee;
    private double finalPayment;

    public Cash(Property property) {
        this.property = property;
        this.contactPrice = property.getContactPrice();

        capitalTax=contactPrice*0.06;
        documentaryStampTax=contactPrice*0.015;
        transferTax=contactPrice*0.05;
        registrationFee=contactPrice*0.03;
        notarial=contactPrice*0.01;
        agentFee=contactPrice*0.03;
        
        finalPayment=contactPrice+documentaryStampTax+transferTax+registrationFee+notarial;
    }
    
    public void showReceipt(){
        System.out.println("Contact Price:"+contactPrice);
        System.out.println("Capital Tax: "+capitalTax);
        System.out.println("Documentary Stamp Tax: "+documentaryStampTax);
        System.out.println("Transfer Tax: "+transferTax);
        System.out.println("Registration Fee: "+registrationFee);
        System.out.println("Notarial Fee: "+notarial);
        System.out.println("------------------------------");
        System.out.println("Final Payment: "+finalPayment);
    }

    public void setAgentFee(double agentFee) {
        this.agentFee = agentFee;
    }
    
    
    
}
