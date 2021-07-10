package com.example.hw02

import java.io.Serializable

class Calculator : Serializable {
    enum class Action {
        PLUS, MINUS, MULTIPLY, DIVIDE, EQUALS
    }
    private var mvArgument1: Float? = null
    private var mvAction: Action? = null

    private fun calculate(ivArg1: Float?, ivArg2: Float?): Float? {
        if (mvAction != null && ivArg1 != null && ivArg2 != null) {
            return when (mvAction) {
                Action.PLUS -> ivArg1 + ivArg2
                Action.MULTIPLY -> ivArg1 * ivArg2
                Action.MINUS -> ivArg1 - ivArg2
                Action.DIVIDE -> if (ivArg2 == 0f) null else ivArg1 / ivArg2
                else -> null
            }
        }
        return null
    }

    fun onActionBtn(ivAct: Action, ivArgument2: Float?): String {
        var lvClearInput = false

        when {
            mvAction != null -> mvArgument1 = calculate(mvArgument1, ivArgument2)
            ivAct == Action.EQUALS -> mvArgument1 = ivArgument2
            else -> {
                mvArgument1 = ivArgument2
                lvClearInput = true
            }
        }
        mvAction = if (ivAct != Action.EQUALS) {
            ivAct
        } else {
            null
        }
        return when {
            lvClearInput -> ""
            mvArgument1 != null -> mvArgument1.toString()
            else -> ERROR
        }
    }

    companion object {
        const val ERROR = "Error"
    }
}