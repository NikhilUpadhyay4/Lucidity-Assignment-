
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME : Practice
 * @PACKAGE_NAME : PACKAGE_NAME
 * @CREATED_BY : nikhilupadhyay
 * @CREATED_DATE : 13/04/24
 **/

public class FoodDelivery {


    // Pair Class
    static class Place{
        String name;
        double latitude;
        double longitude;

        /**
         * Constructs a new Place.
         *
         * @param name The name of the place.
         * @param latitude The geographical latitude of the place.
         * @param longitude The geographical longitude of the place.
         */
        Place(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    // RestaurantCustomerPair class
    static class RestaurantCustomerPair {
        FoodDelivery.Place restaurant;
        FoodDelivery.Place customer;

        /**
         * Constructs a pair of a restaurant and its corresponding customer.
         *
         * @param restaurant The Place of the restaurant.
         * @param customer The Place of the corresponding customer.
         */
        RestaurantCustomerPair(FoodDelivery.Place restaurant, FoodDelivery.Place customer) {
            this.restaurant = restaurant;
            this.customer = customer;
        }
    }


    /**
     * Calculates the distance between two geographical places using the Haversine formula.
     *
     * @param loc1 The first place.
     * @param loc2 The second place.
     * @return The distance in kilometers between loc1 and loc2.
     */
    public static double calculateHaversineDistance(Place loc1, Place loc2) {
        final double R = 6371; // Radius of the Earth in kilometers
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Return distance in kilometers
    }

    /**
     * Determines the optimal delivery path starting from a given source place.
     *
     * @param source The starting place.
     * @param pairs Array of pairs each containing a restaurant and its corresponding customer.
     * @param cookingTimes Cooking times for each restaurant.
     * @return A list of Places representing the delivery path.
     */
    public static List<Place> determineOptimalPath(Place source, RestaurantCustomerPair[] pairs, int[] cookingTimes) {
        List<Place> path = new ArrayList<>();
        path.add(source);
        Place currentPlace = source;
        // Continue building the path until it includes all restaurants and customers.
        while (path.size() < pairs.length * 2 + 1) {
            double minTime = Double.MAX_VALUE;
            Place nextRestaurant = null;
            Place nextCustomer = null;

            // Iterate over each restaurant-customer pair to find the next optimal stop.
            for (int i = 0; i < pairs.length; i++) {
                if (!path.contains(pairs[i].restaurant)) {
                    double travelTimeToRestaurant = calculateHaversineDistance(currentPlace, pairs[i].restaurant) / 20.0 * 60.0;
                    double totalTime = travelTimeToRestaurant + cookingTimes[i];
                    // Update the next stop if the calculated total time is less than the previously found minimum time
                    if (totalTime < minTime) {
                        minTime = totalTime;
                        nextRestaurant = pairs[i].restaurant;
                        nextCustomer = pairs[i].customer;
                    }
                }
            }

            // Add the next restaurant and its corresponding customer to the path if found.
            if (nextRestaurant != null) {
                path.add(nextRestaurant);
                path.add(nextCustomer);
                currentPlace = nextCustomer; // Update current location to the last customer's place
            }
        }
        return path;
    }




    public static void main(String[] args) {


        // Example usage of the determineOptimalPath method

        Place source = new Place("Source", 0.0, 0.0);

        // Array containing pairs of restaurants and their corresponding customers.
        RestaurantCustomerPair[] pairs = new RestaurantCustomerPair[]{
                new RestaurantCustomerPair(new Place("R1", 4.0, 0.0), new Place("C1", 5.0, -1.0)),
                new RestaurantCustomerPair(new Place("R2", 9.0, 0.6), new Place("C2", 1.2, 2.1)),
                new RestaurantCustomerPair(new Place("R3", 1.0, 0.6), new Place("C3", 5.0, 2.1))
        };

        // Array of cooking times in minutes for each restaurant.
        int[] cookingTimes = new int[]{30, 30, 30};
        List<Place> path = determineOptimalPath(source, pairs, cookingTimes);
        System.out.print("Optimal path for delivery:");
        for (Place loc : path) {
            System.out.print(" "+loc.name);
        }
      
    }




}
