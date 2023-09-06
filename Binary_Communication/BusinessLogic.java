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
        String[] request = input.split("\n");
        if (request.length != 3)
            return -1;
        String[] companyInput = request[0].split(":");
        String company = companyInput[1];
        String[] symbolInput = request[1].split(":");
        String symbol = symbolInput[1];
        String[] quantityInput = request[2].split(":");
        int quantity = Integer.parseInt(quantityInput[1]); 
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
