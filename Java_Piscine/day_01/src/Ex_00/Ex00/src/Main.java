public class Main {
    public static void main(String[] args) {
        final  String MOD = "--profile=dev";
        boolean DEVMode = args.length == 1 && args[0].equals(MOD);
        Menu menu = new Menu(DEVMode);
        menu.start();

    }
}