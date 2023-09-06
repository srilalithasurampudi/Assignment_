import java.util.*;

public class BusinessLogic {
    static HashMap <String, ArrayList<Integer>> data = new HashMap<>();
    public BusinessLogic() {
        ArrayList<Integer> prices_AAPL = new ArrayList<>();
        prices_AAPL.add(100);
        prices_AAPL.add(90);
        prices_AAPL.add(95);
        ArrayList<Integer> prices_IBM = new ArrayList<>();
        prices_IBM.add(120);
        prices_IBM.add(110);
        prices_IBM.add(112);
        data.put("AAPL", prices_AAPL);
        data.put("IBM", prices_IBM);
    }

    public static int calculatePrice(String input) {
        String[] request = input.split(",");
        String company = request[0];
        String symbol = request[1];
        int quantity = Integer.parseInt(request[2]); 
        int result = -1;
        if(!data.containsKey(symbol)) {
            return -1;
        }
        if (company.equalsIgnoreCase("ABC Inc")) {
            result = quantity * data.get(symbol).get(1);
        }
        else if (company.equalsIgnoreCase("XYZ Inc")) {
            result = quantity * data.get(symbol).get(2);
        }
        else {
            result = quantity * data.get(symbol).get(0);
        }
        return result;
    }
}