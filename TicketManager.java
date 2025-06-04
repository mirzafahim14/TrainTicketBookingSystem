

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TicketManager {
    private static final Set<String> assignedSeats = new HashSet<>();
    private static final Random random = new Random();

    public double[] calculateFare(String ticketClass, String ticketType, int fromIndex, int toIndex, int adultQty, int childQty) {
        double pricePerStation = ticketClass.equals("Standard") ? 55 :
                                 ticketClass.equals("Economy") ? 85 : 105;
        int stationCount = Math.abs(toIndex - fromIndex);
        if (stationCount == 0) stationCount = 1;

        double adultFare = pricePerStation * stationCount * adultQty;
        double childFare = pricePerStation * stationCount * 0.5 * childQty;

        double subtotal = adultFare + childFare;
        if (ticketType.equals("Return")) subtotal *= 2;

        double tax = subtotal * 0.10;
        double total = subtotal + tax;

        return new double[] {subtotal, tax, total};
    }

    public String assignSeat() {
        if (assignedSeats.size() >= 180) {
            throw new IllegalStateException("All seats are booked.");
        }
        String seat;
        do {
            char row = (char) ('A' + random.nextInt(6));
            int num = 1 + random.nextInt(30);
            seat = row + String.valueOf(num);
        } while (assignedSeats.contains(seat));
        assignedSeats.add(seat);
        return seat;
    }
}