import java.util.*;
import java.lang.Math;

public class sudoku{

  public static void main(String args[]){

    String sudokuString = args[0];
    int length = (int)Math.sqrt(sudokuString.length());

    //maak puzzel
    int[][] arrays = new int[length][length];
    for(int row = 0; row < length; row++){
      for(int column = 0; column < length; column++){
        arrays[row][column] = Character.getNumericValue(sudokuString.charAt(row * length + column));
      }
    }
    //find valid
    boolean valid = findValid(arrays);
  }

  public static boolean findValid(int[][] newArray ){

    Position position = findPosition(newArray);

    // geen open plek meer, puzzel is opgelost.
    if (position == null){
      for(int i = 0; i < newArray.length; i++){
        System.out.println(Arrays.toString(newArray[i]));
      }
      return true;
    }

    // zoek getal voor lege plek
    for(int number = 1; number < newArray.length + 1; number++){
      // getal uit loop mag ingevuld.
      if (checkValid(newArray, number, position)){
        newArray[position.row][position.column] = number;

        if (findValid(newArray)){
          return true;
        }
        newArray[position.row][position.column] = 0;
      }
  }
  // geen getal gevonden
  return false;
}

  public static boolean checkValid(int[][] arrays, int num1, Position position){

    //check row
    for(int column = 0; column < arrays.length; column++){
      if (arrays[position.row][column] == num1){
        return false;
      }
    }

    //check column
    for(int row = 0; row < arrays.length; row++){
      if (arrays[row][position.column] == num1){
        return false;
      }
    }

    // check box
    for(int row = 0; row < Math.sqrt(arrays.length); row++){
        for(int column = 0; column < Math.sqrt(arrays.length); column++){
          if (arrays[row + position.row /3 * 3][column + position.column / 3 * 3] == num1){
            return false;
          }
    }

  }
  return true;
}

  public static class Position{
    int row;
    int column;

    public Position(int row, int column){
      this.row = row;
      this.column = column;
    }
    public void printPos(){
      System.out.println(row + " " + column);
    }
  }

  public static Position findPosition(int[][] arrays){

    for(int i = 0; i < arrays.length; i++){
      for(int y = 0; y < arrays.length; y++){
        if(arrays[i][y] == 0){
          Position position = new Position(i, y);
          return position;
        }
      }
    }
    return null;
  }
}
