package ru.dopegeek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

// читаем строку из входящего системного потока, если она валидна декодируем, если нет - сообщаем об ошибке

      if (validateInputString(s)) System.out.println(decodeString(s));
      else System.out.println("wrong expression");
    }

// метод для валидации строки по паттерну
    private static boolean validateInputString(String inputString){
        String pattern = "(\\d+\\[([a-zA-Z]*(\\d+\\[)*[a-zA-Z]*(\\d+\\[)*]*\\w*)*)(\\d+\\[)((\\d+\\[)*[a-zA-Z]+(\\d+\\[)*]\\w*)*]?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(inputString);
        return  m.matches();
    }


// метод распаковки строки
    private static String decodeString(String inputString){
        int firstIndex, lastIndex, factor;
        String subString, start,end;

// повторяем действия пока в строке присутствуют скобки
while(inputString.contains("[")) {

//узнаем позиции скобок
    firstIndex = inputString.lastIndexOf('[');
    lastIndex = inputString.indexOf(']', firstIndex);

// узнаем множитель для субстроки
    factor = Integer.parseInt(inputString.charAt(firstIndex - 1) + "");


// определяем субстроку
    subString = inputString.substring(firstIndex+1, lastIndex);


// берем начало строки, конец строки и между ними вставляем умноженную субстроку
    start =   inputString.substring(0, firstIndex-1);
    end = inputString.substring(lastIndex + 1);
    inputString = start+subString.repeat(factor)+end;
}

        return inputString;
    }
}


