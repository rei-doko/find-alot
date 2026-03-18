/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.ArrayList;
import java.util.HashMap;

public class Booking {
    private Customer customer;
    private Property property;
    private Agent agent;
    
    public Booking(Customer customer, Property property, Agent agent) {
        this.customer = customer;
        this.property = property;
        this.agent = agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Property getProperty() {
        return property;
    }

    public Agent getAgent() {
        return agent;
    }
}