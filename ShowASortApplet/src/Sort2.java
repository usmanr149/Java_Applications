/**
 * Created by usman on 17/07/15.
 */
public class Sort2 {

    public Sort2(){

    }

    //sort integers from lowest to highest
    public void bubblesort( int numbers[] ){
        int size = numbers.length;
        int x, y, temp;

        for(x = 1; x < size; x++){
            for(y = 0; y < size - 1; y++){
                if(numbers[y] > numbers[y+1]){
                    temp = numbers[y];
                    numbers[y] = numbers[y+1];
                    numbers[y+1] = temp;
                }
            }
        }
    }

}
