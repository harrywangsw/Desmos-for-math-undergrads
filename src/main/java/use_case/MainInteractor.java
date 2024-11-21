package use_case;

import use_case.main.MainInputBoundary;

public class MainInteractor implements MainInputBoundary {


    @Override
    public void executePlot() {
        System.out.println("Temporary Plot");
    }

    @Override
    public void executePhasePotrait() {
        System.out.println("Temporary Draw Phase Portrait");
    }

    @Override
    public void executeHelp() {
        System.out.println("You just need to believe hard enough.");
    }
}
