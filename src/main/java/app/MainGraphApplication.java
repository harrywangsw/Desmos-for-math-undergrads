package app;

public class MainGraphApplication {
    public static void main(String[] args){
        final MainAppBuilder builder = new MainAppBuilder();
        builder.addMainView()
                .addMainUseCase()
                .build().setVisible(true);
    }
}
