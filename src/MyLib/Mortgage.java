package MyLib;

public class Mortgage {
    private Property property;

    private double contactPrice;
    private double capitalTax;
    private double documentaryStampTax;
    private double transferTax;
    private double registrationFee;
    private double notarial;
    private double agentFee;

    // removed private int monthsToPay; 
    private double monthlyPayment;
    private double finalPayment;
    
    //added more variables
    private double downpayment; 
    private double reservationFee = 20000;
    private double loanAmount;
    private double totalCashOut;

    public Mortgage(Property property) {
        this.property = property;
        this.contactPrice = property.getContactPrice();
        
        //added downpayment and loanAmount
        downpayment=contactPrice*0.05;
        loanAmount=contactPrice-downpayment;
        
        capitalTax=contactPrice*0.06;
        documentaryStampTax=contactPrice*0.015;
        transferTax=contactPrice*0.05;
        // removed registrationFee=contactPrice*0.03;
        notarial=contactPrice*0.01;
        agentFee=contactPrice*0.03;
        
        //added final if downpayment
        totalCashOut = downpayment + reservationFee + capitalTax+documentaryStampTax+transferTax+notarial;
        
        //changed the formula for the total if not downpayment
        finalPayment=contactPrice+reservationFee+capitalTax+documentaryStampTax+transferTax+notarial;
    }
    
    public void showReceipt(){ 
        System.out.println("Contact Price:"+contactPrice);
        
        //added downpayment
        System.out.println("Downpayment: "+downpayment);
        
        System.out.println("Capital Tax: "+capitalTax);
        System.out.println("Documentary Stamp Tax: "+documentaryStampTax);
        System.out.println("Transfer Tax: "+transferTax);
        System.out.println("Registration Fee: "+registrationFee);
        System.out.println("Notarial Fee: "+notarial);
        System.out.println("------------------------------");
        System.out.println("Final Payment: "+finalPayment);
        /* System.out.println("Final Total Cash Out: "+totalCashOut); <-- maybe we could have a separate 
        showReceipt if they chose downpayment option*/
    } 

    ////////////////////
    public double getAgentFee() {
        return agentFee;
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
    
    /////// AMORTIZATION COMPUTATION /////////////
    public double computeAmortization(String loanType){

        double interestRate = 0;
        int years = 0;

        if(loanType.equalsIgnoreCase("PagIbig")){ // logic for each bank loan type. Update nalang with jframe (button group or combo box?)
            interestRate = 6.25;                   
            years = 30;
        }
        else if(loanType.equalsIgnoreCase("RCBC")){
            interestRate = 6.60;
            years = 30;
        }
        else if(loanType.equalsIgnoreCase("SBC")){
            interestRate = 6.80;
            years = 20;
        }
        else if(loanType.equalsIgnoreCase("BDO")){
            interestRate = 6.88;
            years = 20;
        }
        else if(loanType.equalsIgnoreCase("InHouse")){
            interestRate = 10.50;
            years = 25;
        }
        else{
            System.out.println("Invalid loan option.");
            return 0;
        }

        double r = (interestRate / 100) / 12;
        int n = years * 12;
            
        //formula for monthly amortization or monthlyPayment based on the sheets
        monthlyPayment = loanAmount * (r * Math.pow(1 + r, n)) /
                        (Math.pow(1 + r, n) - 1);

        return monthlyPayment;
    }
}
