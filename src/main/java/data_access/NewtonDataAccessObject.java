package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import use_case.equations.APIAccessException;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;

/**
 * Data access object for using newton API.
 */
public class NewtonDataAccessObject implements PhasePortraitDataAccessInterface {
    public static final float INTERVAL = 0.1f;

    /**
     * Copied from internet, performs GET action and return the results from newton api.
     *
     * @param urlToRead the newton api url
     * @return return string from newton api
     * @throws IOException when newton api returns error
     */
    public static String getHTML(String urlToRead) throws IOException {
        final StringBuilder result = new StringBuilder();
        final URL url = new URL(urlToRead);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }
        return result.toString();
    }

    /**
     * Evaluates a single ODE at a point.
     *
     * @param expression one of the equations in our system of ODE
     * @param variable   the characters representing the variables
     * @param point      the specific point we're evaluating the ode at
     * @return the result of the evaluation
     * @throws RuntimeException when newton api returns error
     * @throws APIAccessException when newton api returns error
     */
    public float evaluatesingleOdeatpoint(String expression, String[] variable,
                                          List<Float> point) throws APIAccessException {
        String exp = expression;
        for (int i = 0; i < variable.length; i++) {
            exp = exp.replace(variable[i], "(" + String.format("%.12f", point.get(i)) + ")");
        }
        String jsonstring = null;
        try {
            jsonstring = getHTML("https://newton.vercel.app/api/v2/simplify/" + exp);
        }
        catch (IOException err) {
            throw new RuntimeException(err);
        }
        final JSONObject json = new JSONObject(jsonstring);
        return Float.parseFloat(json.getString("result"));
    }

    /**
     * Performs Euler's method for a system of ODEs.
     *
     * @param expressions the expressions for the time derivatives (i.e. if the system of ODE is x'=2x
     *                    y'=y^2+x, expression would be ["2x", "y^2+x"]
     * @param vars        the characters representing the variables
     * @param ico initial condition
     * @param end_time    the endpoint of euler method.
     * @return the numerica solution
     * @throws APIAccessException when newton returns error
     */
    @Override
    public List<List<Float>> eulersolve(String[] expressions, String[] vars, Float[] ico,
                                        float end_time) throws APIAccessException {
        final List<List<Float>> result = new ArrayList<List<Float>>();
        result.add(Arrays.asList(ico));
        for (int i = 0; i < end_time / INTERVAL; i++) {
            final List<Float> current = new ArrayList<>(result.get(result.size() - 1));
            final List<Float> nextpoint = new ArrayList<Float>();
            for (int j = 0; j < expressions.length; j++) {
                nextpoint.add(current.get(j) + INTERVAL * evaluatesingleOdeatpoint(expressions[j], vars, current));
            }
            result.add(nextpoint);
        }
        return result;
    }
}
