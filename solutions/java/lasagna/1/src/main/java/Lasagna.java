public class Lasagna {
    // TODO: define the 'expectedMinutesInOven()' method
    public int expectedMinutesInOven(){
        return 40;
    }

    // TODO: define the 'remainingMinutesInOven()' method
  public int remainingMinutesInOven(int a){
      return expectedMinutesInOven() - a;
  }
    // TODO: define the 'preparationTimeInMinutes()' method
public int preparationTimeInMinutes(int a){
    return a * 2;
}
    // TODO: define the 'totalTimeInMinutes()' method
    public int totalTimeInMinutes(int layer, int time){
        return preparationTimeInMinutes(layer) + time;
    }
}
