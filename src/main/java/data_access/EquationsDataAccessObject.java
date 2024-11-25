package data_access;

import app.newton;
import entity.ODESystem;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.equations.APIAccessException;
import use_case.equations.EquationsDataAccessInterface;

import java.util.ArrayList;

public class EquationsDataAccessObject implements EquationsDataAccessInterface {

    private static final String APP_ID = "QUGYWV-XEXLWQT37U";
    private static final String API_OUTPUT_TYPE = "json";
    private static final String API_OUTPUT_FORMAT = "plaintext";
    private final StringBuilder BASE_URL = new StringBuilder("https://api.wolframalpha.com/v2/query?appid=" +
            APP_ID +"&output="+ API_OUTPUT_TYPE +"&format="+ API_OUTPUT_FORMAT +"&includepodid=Result&input=Solve ");

    @Override
    public String getSolution(ODESystem system) throws APIAccessException {
        return "the expected solution";
    }

    @Override
    public String[] getCritPoints(ODESystem system) throws APIAccessException {

        for (String equation : system.getEquations()) {
            BASE_URL.append(equation).append("=0,");
        }
        try {
            String jsonstring = newton.getHTML(BASE_URL.toString());
            JSONArray apiResult = new JSONObject(jsonstring).getJSONArray("pods")
                    .getJSONObject(0).getJSONArray("subpods");
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < apiResult.length(); i++) {
                results.add(apiResult.getJSONObject(i).getString(API_OUTPUT_FORMAT));
            }
            return results.toArray(new String[0]);
        } catch (Exception e) {
            throw new APIAccessException(e.getMessage());
        }
    }
}
