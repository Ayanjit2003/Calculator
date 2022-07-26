package ayanjit.app1.calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var text1: TextView? = null
    var isnum: Boolean = false
    var isdot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text1 = findViewById(R.id.tv_input)
    }

    fun onDigit(view: View) {
        text1?.append((view as Button).text)
        isnum = true
        isdot = false
    }
    fun onClear(view: View){
        text1?.text = ""
    }
    fun onPoint(view: View) {
        if (isnum && !isdot) {
            text1?.append(".")
            isnum = false
            isdot = true
        }
    }
    fun operator (view: View){
        text1?.text.let {
            if(isnum && !isOPadded(it.toString())){
                text1?.append((view as Button).text)
                isnum = false
                isdot = false
            }
        }
    }
    fun onEqual (view: View){
        if (isnum){
            var value1 = text1?.text.toString()
            var prefix = ""
            try {
                if ((value1.startsWith("-") || (value1.startsWith("+"))
                            || (value1.startsWith("/")) || (value1.startsWith("x")))){
                    prefix = "-1"
                    value1 = value1.substring(1)
                }
                if (value1.contains("-"))
                {
                    val spiral = value1.split("-")
                    var one = spiral[0]
                    var two = spiral[1]
                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    text1?.text = remzero((one.toDouble() - two.toDouble()).toString())
                }
                else if (value1.contains("+"))
                    {
                        val spiral = value1.split("+")
                        var one = spiral[0]
                        var two = spiral[1]
                        if (prefix.isNotEmpty())
                        {
                            one = prefix + one
                        }
                        text1?.text = remzero((one.toDouble() + two.toDouble()).toString())
                    }
                else if (value1.contains("/"))
                {
                    val spiral = value1.split("/")
                    var one = spiral[0]
                    var two = spiral[1]
                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    text1?.text = remzero((one.toDouble() / two.toDouble()).toString())
                }
                else if (value1.contains("x"))
                {
                    val spiral = value1.split("x")
                    var one = spiral[0]
                    var two = spiral[1]
                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    text1?.text = remzero((one.toDouble() * two.toDouble()).toString())
                }
                }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun remzero (result:String): String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }
    private fun isOPadded (value : String): Boolean {
        return if(value.startsWith("-")){
            false
            }
        else{
             value.contains("+") || value.contains("-")
                || value.contains("x") || value.contains("/")
        }
}
}

