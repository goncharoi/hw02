package com.example.hw02;

import java.io.Serializable;

public class Calculator implements Serializable {
    public enum Action {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EQUALS
    }

    public static final String ERROR = "Error";

    private Float mvArgument1;
    private Action mvAction;

    protected Float calculate(Float ivArg1, Float ivArg2) {
        if (mvAction != null && ivArg1 != null && ivArg2 != null) {
            switch (mvAction) {
                case PLUS:
                    return ivArg1 + ivArg2;
                case MULTIPLY:
                    return ivArg1 * ivArg2;
                case MINUS:
                    return ivArg1 - ivArg2;
                case DIVIDE:
                    return ivArg2 == 0f ? null : (ivArg1 / ivArg2);
            }
        }
        return null;
    }

    public String onActionBtn(Action ivAct, Float ivArgument2) {
        boolean lvClearInput = false;

        if (mvAction != null) {
            mvArgument1 = calculate(mvArgument1, ivArgument2);
        } else if (ivAct == Action.EQUALS) {
            mvArgument1 = ivArgument2;
        } else {
            mvArgument1 = ivArgument2;
            lvClearInput = true;
        }

        if (ivAct != Action.EQUALS) {
            mvAction = ivAct;
        } else {
            mvAction = null;
        }

        if (lvClearInput) {
            return "";
        } else if (mvArgument1 != null) {
            return mvArgument1.toString();
        } else {
            return ERROR;
        }
    }

}
