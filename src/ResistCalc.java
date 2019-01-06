public class ResistCalc {


    public static double multiplier(double resistValue) {
        if(resistValue >= 0) {
            return 10 / (resistValue + 10);
        } else {
            return -(resistValue/10) + 1;
        }
    }
}
