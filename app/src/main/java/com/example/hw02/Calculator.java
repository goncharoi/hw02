package com.example.hw02;

import java.io.Serializable;

public class Calculator implements Serializable {
    private Float mvArgument1;
    private Action mvAction;

    protected Float run(Float ivArg1, Float ivArg2) {
        if (mvAction != null && ivArg1 != null && ivArg2 != null) {
            switch (mvAction) {
                case PLS:
                    return ivArg1 + ivArg2;
                case MLT:
                    return ivArg1 * ivArg2;
                case MNS:
                    return ivArg1 - ivArg2;
                case DIV:
                    return ivArg2 == 0f ? null : (ivArg1 / ivArg2);
            }
        }
        return null;
    }

    public String onActionBtn(Action ivAct, Float ivArgument2){
        if (mvAction != null) {
            mvArgument1 = run(mvArgument1, ivArgument2);
        } else
            mvArgument1 = ivArgument2;

        mvAction = ivAct;

        if (mvArgument1 != null)
            return mvArgument1.toString();
        else
            return "Error";
    }

}
