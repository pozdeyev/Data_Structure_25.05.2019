package ru.geekbrains.datastructure.stack;

public class Brackets {


    private final String text;

    public Brackets(String text) {
        this.text = text;
    }
    //( ){ } - correct
    //( { ) } - incorrect

    public void check() {
        Stack<Character> stack = new StackImpl<>(text.length());

        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            switch (chr) {
                case '(':
                case '{':
                case '[':
                case '<':
                    stack.push(chr);
                    break;
                case ')':
                case '}':
                case ']':
                case '>':
                    if (stack.isEmpty()) { //не было откр. скобок
                        System.out.println("Error: " + chr + " at " + i);
                        break;
                    }
                    //Если он был не пустой (была откр. скобка) извлекаем
                    Character lastChar = stack.pop();
                    if (lastChar == '(' && chr != ')' ||
                            lastChar == '[' && chr != ']' ||
                            lastChar == '{' && chr != '}' ||
                            lastChar == '<' && chr != '>') {
                        System.out.println("Error: " + chr + " at " + i);
                    }
                    break;
                default:
                    break;
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delimiter");
        }
    }

    public static void main(String[] args) {
        // String text = "public E peek() {return data [size - 1];";
        String text = "<(() ())>";
        new Brackets(text).check();
    }
}
