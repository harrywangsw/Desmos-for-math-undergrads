package data_access;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import use_case.phase_portrait.PhasePortraitDataAccessInterface;

public class NewtonDataAccessObject implements PhasePortraitDataAccessInterface {
    //interval for euler's method
    public static final float interval = 0.01f;

    /**
     * copied from internet, performs GET action and return the results from newton api
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    /**
     * evaluates a single ODE at a point
     * @param expression one of the equations in our system of ODE
     * @param variable the characters representing the variables
     * @param point the specific point we're evaluating the ode at
     * @return
     * @throws Exception
     */
    public float evaluate_single_ODE_at_point(String expression, String[] variable, List<Float> point) throws Exception
    {
        for (int i = 0; i < variable.length; i++){
            expression = expression.replace(variable[i], "("+String.format("%.12f", point.get(i))+")");
        }
        String jsonstring = getHTML("https://newton.vercel.app/api/v2/simplify/"+expression);
        JSONObject json = new JSONObject(jsonstring);
        return Float.parseFloat(json.getString("result"));
    }

    /**
     * performs Euler's method for a system of ODEs
     * @param expressions the expressions for the time derivatives (i.e. if the system of ODE is x'=2x
     *                                                                                           y'=y^2+x, expression would be ["2x", "y^2+x"]
     * @param vars the characters representing the variables
     * @param initial_conditions
     * @param end_time the endpoint of euler method.
     * @return
     * @throws Exception
     */
    @Override
    public List<List<Float>> euler_solve(String[] expressions, String[] vars, Float[] initial_conditions, float end_time) throws Exception {
        List<List<Float>> result = new ArrayList<List<Float>>();
        result.add(Arrays.asList(initial_conditions));
        for (int i = 0; i < end_time/interval; i++){
            List<Float> current = new ArrayList<>(result.get(result.size() - 1));
            List<Float> next_point = new ArrayList<Float>();
            for (int j = 0; j < expressions.length; j++) {
                next_point.add(current.get(j)+interval*evaluate_single_ODE_at_point(expressions[j], vars, current));
            }
            result.add(next_point);
        }
        return result;
    }
}
