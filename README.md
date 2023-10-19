# Pizza Service MVC Architecture
## Maastricht University | Course: Software Engineering

## Overview
This repository contains a Client and Server architecture based on a Model-View-Controller architecture (MVC). The designed application resembled a Pizza ordering system designed for the Software Engineering course in LAB 6. The service communicates with clients over a REST API, and the code was written in Java.

## Project Description
The project aimed to create a pizza service that allows clients to interact with the restaurant's offerings and place orders. The main functionalities include listing available pizzas, fetching details of a specific pizza, placing orders, checking order status, and canceling orders.

## Software Requirements
The REST server and client have the following capabilities:
- **List Pizzas**: Retrieve all pizzas available for ordering.
- **Pizza Details**: For a selected pizza, return its toppings, price, and vegetarian status.
- **Place Orders**: Clients can order pizzas. Upon ordering, they receive a confirmation with an order-id, the pizzas ordered, and the estimated delivery time.
- **Check Order Status**: Using the order-id, clients can request the current estimated delivery time and the order's status (cancelled, in process, out for delivery).
- **Cancel Order**: Clients can cancel their orders if placed within the last 5 minutes. The order status updates to 'cancelled'.

Note: There is no need to implement a model as it was outsourced. Pizzas and their details can be hard-coded or stored in a text file. The same applies to orders; they can be static. For extended functionality, storing pizzas and orders in a database or CSV file is optional but not mandatory.

## Directory Structure
- **.idea/**: Contains IDE configuration files.
- **src/**: Source code directory.
  - **main/**: Main source code.
    - **java/**: Java source code.
      - **ch/**: Code specific to the 'ch' namespace.
        - **heigvd/**: Code specific to the 'heigvd' namespace.
          - **res/**: Resources directory.
            - **lab6/**: LAB6 main directory.
              - **Client.java**: Java file for the client implementation.
              - **Server.java**: Java file for the server implementation.
  - **test/**: Test files directory.

## How to Run
1. Navigate to `src/main/java/ch/heigvd/res/lab6/`.
2. Compile the Java files: `javac *.java`.
3. Start the server: `java Server`.
4. In a separate terminal, run the client: `java Client`.
