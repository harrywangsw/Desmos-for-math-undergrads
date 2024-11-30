package data_access;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.ODESystem;
import use_case.equations.APIAccessException;
import use_case.equations.EquationsDataAccessInterface;

/**
 * DAO for the Equations use cases.
 * Implements the use of the wolfram API to collect data for solution/critical points use case.
 */
public class EquationsDataAccessObject implements EquationsDataAccessInterface {

    private static final String APP_ID = "QUGYWV-XEXLWQT37U";
    private static final String API_OUTPUT_TYPE = "json";
    private static final String API_OUTPUT_FORMAT = "image";
    private static final String BASE_URL = "https://api.wolframalpha.com/v2/query?appid=" + APP_ID
            + "&output=" + API_OUTPUT_TYPE + "&format=" + API_OUTPUT_FORMAT + "&includepodid=Result&input=Solve+";

    @Override
    public String[] getSolution(ODESystem system) throws APIAccessException {

        final StringBuilder query = new StringBuilder();
        final String[] equations = system.getEquations();
        final String[] variables = system.getVariables();
        for (int i = 0; i < equations.length; i++) {
            final String equation = equations[i];
            query.append(equation).append("=").append(variables[i]).append("',");
        }

        final String fullUrl = BASE_URL + URLEncoder.encode(query.toString(), StandardCharsets.UTF_8);

        try {
            final String jsonstring = NewtonDataAccessObject.getHTML(fullUrl);
            final JSONObject apiResult = new JSONObject(jsonstring).getJSONObject("queryresult");
            final int numpods = apiResult.getInt("numpods");
            if (numpods == 0) {
                throw new APIAccessException("There are no solutions");
            }
            final JSONArray solutions = apiResult.getJSONArray("pods")
                    .getJSONObject(0).getJSONArray("subpods");
            final ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < solutions.length(); i++) {
                results.add(solutions.getJSONObject(i).getJSONObject("img").getString("src"));
            }
            return results.toArray(new String[0]);
        }
        catch (JSONException | IOException exception) {
            throw new APIAccessException(exception.getMessage());
        }
    }

    @Override
    public String[] getCritPoints(ODESystem system) throws APIAccessException {

        final StringBuilder query = new StringBuilder();
        for (String equation : system.getEquations()) {
            query.append(equation).append("=0,");
        }

        final String fullUrl = BASE_URL + URLEncoder.encode(query.toString(), StandardCharsets.UTF_8);

        try {
            final String jsonstring = NewtonDataAccessObject.getHTML(fullUrl);
            final JSONObject apiResult = new JSONObject(jsonstring).getJSONObject("queryresult");
            final int numpods = apiResult.getInt("numpods");
            if (numpods == 0) {
                throw new APIAccessException("There are no solutions");
            }
            final JSONArray solutions = apiResult.getJSONArray("pods")
                    .getJSONObject(0).getJSONArray("subpods");
            final ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < solutions.length(); i++) {
                results.add(solutions.getJSONObject(i).getJSONObject("img").getString("src"));
            }
            return results.toArray(new String[0]);
        }
        catch (JSONException | IOException exception) {
            throw new APIAccessException(exception.getMessage());
        }
    }
}
