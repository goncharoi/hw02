package com.example.hw02

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    private object Key {
        const val CALCULATOR = "Calculator"
        const val NUMBER = "Number"
    }

    private object ThemeKey {
        const val APP_THEME = 0
        const val APP_THEME_GREEN = 1
    }

    companion object {
        private const val NAME_SHARED_PREFERENCE = "OPTIONS"
        private const val APP_THEME = "APP_THEME"
        private const val THEME_SWITCH_CHECKED = "THEME_SWITCH_CHECKED"
    }

    private lateinit var moSharedPref: SharedPreferences
    private lateinit var moNumber: TextView
    private lateinit var moSwitch: SwitchMaterial
    private lateinit var moCalc: Calculator
    private var mvNoInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moSharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE)
        setTheme(getAppTheme(R.style.AppTheme))
        setContentView(R.layout.activity_main)
        moSwitch = findViewById(R.id.theme_switch)
        moSwitch.isChecked = moSharedPref.getBoolean(THEME_SWITCH_CHECKED, false)
        moNumber = findViewById(R.id.number)
        moCalc = Calculator()

        findViewById<View>(R.id.button_clear).setOnClickListener {
            moCalc = Calculator()
            moNumber.text = ""
        }
        findViewById<View>(R.id.button_delete).setOnClickListener { moNumber.text = "" }

        //Вычисления
        findViewById<View>(R.id.button_plus).setOnClickListener {
            moNumber.text = moCalc.onActionBtn(Calculator.Action.PLUS, floatFromScr)
            mvNoInput = true
        }
        findViewById<View>(R.id.button_minus).setOnClickListener {
            moNumber.text = moCalc.onActionBtn(Calculator.Action.MINUS, floatFromScr)
            mvNoInput = true
        }
        findViewById<View>(R.id.button_multiply).setOnClickListener {
            moNumber.text = moCalc.onActionBtn(Calculator.Action.MULTIPLY, floatFromScr)
            mvNoInput = true
        }
        findViewById<View>(R.id.button_divide).setOnClickListener {
            moNumber.text = moCalc.onActionBtn(Calculator.Action.DIVIDE, floatFromScr)
            mvNoInput = true
        }
        findViewById<View>(R.id.button_equals).setOnClickListener {
            moNumber.text = moCalc.onActionBtn(Calculator.Action.EQUALS, floatFromScr)
            mvNoInput = true
        }

        //Набор
        findViewById<View>(R.id.button_0).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._0
                )
            )
        }
        findViewById<View>(R.id.button_1).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._1
                )
            )
        }
        findViewById<View>(R.id.button_2).setOnClickListener { addCharToNumber(getString(R.string._2)) }
        findViewById<View>(R.id.button_3).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._3
                )
            )
        }
        findViewById<View>(R.id.button_4).setOnClickListener { addCharToNumber(getString(R.string._4)) }
        findViewById<View>(R.id.button_5).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._5
                )
            )
        }
        findViewById<View>(R.id.button_6).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._6
                )
            )
        }
        findViewById<View>(R.id.button_7).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._7
                )
            )
        }
        findViewById<View>(R.id.button_8).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._8
                )
            )
        }
        findViewById<View>(R.id.button_9).setOnClickListener {
            addCharToNumber(
                getString(
                    R.string._9
                )
            )
        }
        findViewById<View>(R.id.button_dot).setOnClickListener { addCharToNumber(getString(R.string._dot)) }

        //Переключение темы
        moSwitch.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            // сохраним настройки
            setAppTheme(if (isChecked) ThemeKey.APP_THEME_GREEN else ThemeKey.APP_THEME)
            val editor = moSharedPref.edit()
            editor.putBoolean(THEME_SWITCH_CHECKED, moSwitch.isChecked)
            editor.apply()
            // пересоздадим активити, чтобы тема применилась
            recreate()
        }
    }

    private fun addCharToNumber(ivStr: String) {
        if (moNumber.text.toString() == Calculator.ERROR || mvNoInput) {
            moNumber.text = ""
        }
        if (ivStr == getString(R.string._dot) && moNumber.text.toString()
                .contains(getString(R.string._dot))
        ) {
            return
        }
        moNumber.text = moNumber.text.toString() + ivStr
        mvNoInput = false
    }

    private val floatFromScr: Float?
        get() = try {
            java.lang.Float.valueOf(moNumber.text.toString())
        } catch (loEx: NumberFormatException) {
            null
        }

    // Сохранение данных
    public override fun onSaveInstanceState(instanceState: Bundle) {
        super.onSaveInstanceState(instanceState)
        instanceState.putSerializable(Key.CALCULATOR, moCalc)
        instanceState.putString(Key.NUMBER, moNumber.text.toString())
    }

    //Восстановление данных
    override fun onRestoreInstanceState(instanceState: Bundle) {
        super.onRestoreInstanceState(instanceState)
        moCalc = (instanceState.getSerializable(Key.CALCULATOR) as Calculator?)!!
        moNumber.text = instanceState.getString(Key.NUMBER)
    }

    private fun getAppTheme(ivCodeStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(ivCodeStyle))
    }

    // Чтение настроек, параметр «тема»
    private fun getCodeStyle(ivCodeStyle: Int): Int {
        return moSharedPref.getInt(APP_THEME, ivCodeStyle)
    }

    // Сохранение настроек
    private fun setAppTheme(ivCodeStyle: Int) {
        val editor = moSharedPref.edit()
        editor.putInt(APP_THEME, ivCodeStyle)
        editor.apply()
    }

    private fun codeStyleToStyleId(ivCodeStyle: Int): Int {
        return when (ivCodeStyle) {
            ThemeKey.APP_THEME_GREEN -> R.style.AppThemeGreen
            else -> R.style.AppTheme
        }
    }
}