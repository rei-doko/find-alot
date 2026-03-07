public class Mortgage {
    private Property property;

    private double contactPrice;
    private double capitalTax;
    private double documentaryStampTax;
    private double transferTax;
    private double registrationFee;
    private double notarial;
    private double agentFee;

    private int monthsToPay;
    private double monthlyPayment;

    private double finalPayment;

    public Mortgage(Property property) {
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

    ////////////////////
    public double getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(double agentFee) {
        this.agentFee = agentFee;
    }
    /* CODE FOR ADMIN
    public void confirmAgentFee(Payment agentPay) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name+", do you want to confirm the agent fee of "+agentPay.getAgentFee()+"? (y/n)");

        String ans = scanner.nextLine();
        if(ans=="y"){
            System.out.println("Agent fee has been confirmed.");
        } 
        else{
            System.out.println("Agent fee remains unconfirmed.");
        }
    } */
}
