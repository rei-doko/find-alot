public class Cash {
    private double propertyPrice;
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

    public void setAgentFee(double agentFee) {
        this.agentFee = agentFee;
    }
}
