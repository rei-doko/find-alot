# Find ALot

FindALOT is a Java-based Real Estate Sales and Management System designed to assist buyers and agents in managing property transactions within a residential subdivision. The system focuses on providing an intuitive and comprehensive graphical user interface (GUI) that supports both novice and experienced users in navigating real estate information and processes. The software allows users to view and manage detailed information about residential lots, including unit selling price, lot area (in square meters), block and lot location, number of floors, property type (townhouse, semi-detached, or detached), status, applicable discounts, miscellaneous fees, taxes, and required down payment. Each residential block displays its corresponding lots, enabling users to identify available properties based on their preferences easily. Additionally, “FindALOT” incorporates agent-related fees and mortgage computations, allowing buyers to view estimated installment payments based on annual interest rates and loan terms. These features help simulate real-world financial considerations involved in purchasing property. All transaction records, reservations, and property details are stored in the system’s records for reference and future access. “FindALOT” aims to streamline real estate transactions by combining clarity, accessibility, and realistic financial modeling within a user-friendly digital platform.


# Table of Contents

- [Usage](#usage)
- [Features](#features)
- [Installation](#installation)
- [Documentation](#documentation)
- [License](#license)

# Usage

1. Register an account on the authentication page, setting the account type. (Customer / Agent)
2. Log in to your account.

Customer
---

1. Search and filter for desired properties.
2. Select the property.
3. Book the property to request a reservation.
4. Open the "Owned Properties" panel.
5. Once reserved, select the property and click on confirm payment method. (Cash / Mortgage)
6. Once approved by the agent, the property will be owned by the user.

Agent
---

1. Search and filter for properties in the "Properties" panel.
2. Open the "Requests" panel to view incoming booking requests.
3. Select a property with the customer in mind.
4. Click the reserve button.
5. Once the customer chooses the payment method, confirm the purchase.

# Features

- Property table with filter
- Property booking
- Booking request page
- Direct agent commission fee

# Documentation
Known Issue: A bug causes the status of the property to be set as "Sold" and the owner to be the booker when the reservation is confirmed by the agent
Cause: A merge issue during GUI implementation disrupted core logic code in the confirm reservation button
Status: Unresolved
Attempts: Locate the former confirmReserveButton found in the AgentDashboard.java on the AgentDashboard.form

## License

This project is licensed under the MIT License. See the LICENSE file for details.
