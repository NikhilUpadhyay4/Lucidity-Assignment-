# Delivery Route Optimizer


# Introduction:
The Delivery Route Optimizer is a Java application designed to efficiently determine the optimal path for food delivery from multiple restaurants to their corresponding customers, starting from a given source location. The program uses a greedy heuristic algorithm to sequentially choose the next best step based on minimal combined travel and preparation times.

# Features:
Route Optimization: Determines an efficient route for deliveries considering the travel distance and cooking times.
Greedy Algorithm Implementation: Utilizes a simple, locally optimal method to calculate the delivery route.
Geographical Distance Calculation: Incorporates the Haversine formula to calculate distances between two geographical points.

# Prerequisites
Before you run the Delivery Route Optimizer, ensure you have the following installed: Java JDK 8 or later

# Installation
Clone the Repository: git clone https://github.com/NikhilUpadhyay4/Lucidity-Assignment-.git
Navigate to the Project Directory


# How It Works
The program initializes with a predefined source location and sets of restaurant-customer pairs along with their geographic coordinates. The delivery route calculation process involves:

  Starting from the source location.
  Iteratively selecting the nearest restaurant that minimizes the sum of travel time and cooking time.
  Moving to the restaurant's corresponding customer location after visiting the restaurant.
  Repeating the process until all pairs are visited.
  Each iteration chooses the next restaurant based on the current location, ensuring that travel time is minimized at each step, along with    accounting for the cooking time required at each restaurant.

