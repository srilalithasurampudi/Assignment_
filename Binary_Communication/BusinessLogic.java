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

    public static int calculatePrice(String symbol, String  company, int quantity) {
        int result = -1;
        String symb = symbol.trim();
        if(!data.containsKey(symb)) {
            return -1;
        }
        if (company.equalsIgnoreCase("ABC Inc")) {
            result = quantity * data.get(symb).get(1);
        }
        else if (company.equalsIgnoreCase("XYZ Inc")) {
            result = quantity * data.get(symb).get(2);
        }
        else {
            result = quantity * data.get(symb).get(0);
        }
        return result;
    }
}
