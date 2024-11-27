package data_access;

import entity.ODESystem;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.equations.APIAccessException;
import use_case.equations.EquationsDataAccessInterface;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EquationsDataAccessObject implements EquationsDataAccessInterface {

    private static final String APP_ID = "QUGYWV-XEXLWQT37U";
    private static final String API_OUTPUT_TYPE = "json";
    private static final String API_OUTPUT_FORMAT = "image";
    private final String BASE_URL = "https://api.wolframalpha.com/v2/query?appid=" +
            APP_ID +"&output="+ API_OUTPUT_TYPE +"&format="+ API_OUTPUT_FORMAT +"&includepodid=Result&input=Solve+";

    @Override
    public String[] getSolution(ODESystem system) throws APIAccessException {

        StringBuilder query = new StringBuilder();
        String[] equations = system.getEquations();
        String[] variables = system.getVariables();
        for (int i = 0; i < equations.length; i++) {
            String equation = equations[i];
            query.append(equation).append("=").append(variables[i]).append("',");
        }

        final String fullURL = BASE_URL + URLEncoder.encode(query.toString(), StandardCharsets.UTF_8);


        try {
            System.out.println(BASE_URL);
            String jsonstring = newton.getHTML(fullURL);
            final JSONObject apiResult = new JSONObject(jsonstring).getJSONObject("queryresult");
            System.out.println(apiResult);
            int numpods = apiResult.getInt("numpods");
            if (numpods == 0) {
                throw new APIAccessException("There are no solutions");
            }
            JSONArray solutions = apiResult.getJSONArray("pods")
                    .getJSONObject(0).getJSONArray("subpods");
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < solutions.length(); i++) {
                results.add(solutions.getJSONObject(i).getJSONObject("img").getString("src"));
            }
            return results.toArray(new String[0]);
        } catch (Exception e) {
            throw new APIAccessException(e.getMessage());
        }
    }

    @Override
    public String[] getCritPoints(ODESystem system) throws APIAccessException {

        StringBuilder query = new StringBuilder();
        for (String equation : system.getEquations()) {
            query.append(equation).append("=0,");
        }

        final String fullURL = BASE_URL + URLEncoder.encode(query.toString(), StandardCharsets.UTF_8);


        try {
            System.out.println(BASE_URL);
            String jsonstring = NewtonDataAccessObject.getHTML(fullURL);
            final JSONObject apiResult = new JSONObject(jsonstring).getJSONObject("queryresult");
            System.out.println(apiResult);
            int numpods = apiResult.getInt("numpods");
            if (numpods == 0) {
                throw new APIAccessException("There are no solutions");
            }
            JSONArray solutions = apiResult.getJSONArray("pods")
                    .getJSONObject(0).getJSONArray("subpods");
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < solutions.length(); i++) {
                results.add(solutions.getJSONObject(i).getJSONObject("img").getString("src"));
            }
            return results.toArray(new String[0]);
        } catch (Exception e) {
            throw new APIAccessException(e.getMessage());
        }
    }
}