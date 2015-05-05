package parser;

public class Phone {
    public static String parse(String text) {
        text = text.replaceAll("[^0-9]", "");
        int length = text.length();
        if(length < 10) return null;
        if(length == 10) return "38"+text;
        if(length == 12) return text;
        throw new RuntimeException("Не известный формат номера "+text);
    }
}
