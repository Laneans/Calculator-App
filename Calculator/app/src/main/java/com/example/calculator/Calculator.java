package com.example.calculator;

public class Calculator {

    private int firstArg;
    private int secondArg;

    private final StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public Calculator() {
        state = State.firstArgInput;
    }

    public void onDigitPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 7) {
                if(buttonId == R.id.zero) {
                    if (inputStr.length() >= 0 && inputStr.indexOf("0") != 0) {
                        inputStr.append("0");
                    }
                }
                else if (buttonId == R.id.one) {
                    inputStr.append("1");
                }
                else if (buttonId == R.id.two) {
                    inputStr.append("2");
                }
                else if (buttonId == R.id.three) {
                    inputStr.append("3");
                }
                else if (buttonId == R.id.four) {
                    inputStr.append("4");
                }
                else if (buttonId == R.id.five) {
                    inputStr.append("5");
                }
                else if (buttonId == R.id.six) {
                    inputStr.append("6");
                }
                else if (buttonId == R.id.seven) {
                    inputStr.append("7");
                }
                else if (buttonId == R.id.eight) {
                    inputStr.append("8");
                }
                else if (buttonId == R.id.nine) {
                    inputStr.append("9");
                }
        }

    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equal && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);

            if (actionSelected == R.id.plus) {
                inputStr.append(firstArg + secondArg);
            }
            else if (actionSelected == R.id.minus) {
                inputStr.append(firstArg - secondArg);
            }
            else if (actionSelected == R.id.multiply) {
                inputStr.append(firstArg * secondArg);
            }
            else if (actionSelected == R.id.division) {
                inputStr.append(firstArg / secondArg);
            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput && actionId != R.id.equal) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(inputStr)
                        .toString();
        }
    }

    private char getOperationChar() {
        if (actionSelected == R.id.plus) {
            return '+';
        } else if (actionSelected == R.id.minus) {
            return '-';
        } else if (actionSelected == R.id.multiply) {
            return '×';
        } else if (actionSelected == R.id.division) {
            return '/';
        }
        return 0;
    }

    public void reset() {
        state = State.firstArgInput;
        inputStr.setLength(0);
    }
}