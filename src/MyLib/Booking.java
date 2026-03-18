/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.ArrayList;
import java.util.HashMap;

public class Booking {
    // Map agent -> list of booking requests
    private HashMap<Agent, ArrayList<BookingRequest>> agentRequests = new HashMap<>();

    public void requestBooking(Customer customer, Property property, Agent agent) {
        BookingRequest request = new BookingRequest(customer, property, agent);
        agentRequests.putIfAbsent(agent, new ArrayList<>());
        agentRequests.get(agent).add(request);
    }

    public ArrayList<BookingRequest> getRequestsForAgent(Agent agent) {
        return agentRequests.getOrDefault(agent, new ArrayList<>());
    }

    public void confirmBooking(BookingRequest request) {
        Property property = request.getProperty();
        Customer customer = request.getCustomer();
        property.updateStatus("Book");
        property.setOwner(customer);
        // Remove request from agent list
        agentRequests.get(request.getAgent()).remove(request);
    }
    
    public static class BookingRequest {
        private Customer customer;
        private Property property;
        private Agent agent;

        public BookingRequest(Customer customer, Property property, Agent agent) {
            this.customer = customer;
            this.property = property;
            this.agent = agent;
        }

        public Customer getCustomer() { return customer; }
        public Property getProperty() { return property; }
        public Agent getAgent() { return agent; }
    }
}