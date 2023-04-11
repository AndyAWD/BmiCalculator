package tw.com.andyawd.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.com.andyawd.bmicalculator.ui.theme.BmiCalculatorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiCalculatorTheme {
        Surface {
            InputFieldsAndTextView()
        }
    }
}

@Composable
fun InputFieldsAndTextView() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(64.dp)
                    .padding(8.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(R.string.height),
                    fontSize = 32.sp,
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                TextField(
                    value = height,
                    label = { Text(stringResource(R.string.input_height)) },
                    modifier = Modifier.align(Alignment.Center),
                    onValueChange = {
                        height = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    maxLines = 1,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(64.dp)
                    .padding(8.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(R.string.weight),
                    fontSize = 32.sp,
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                TextField(
                    value = weight,
                    label = { Text(stringResource(R.string.input_weight)) },
                    modifier = Modifier.align(Alignment.Center),
                    onValueChange = {
                        weight = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    maxLines = 1,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(64.dp)
                    .padding(8.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(R.string.input_bmi, calculatorBmi(height, weight)),
                    fontSize = 32.sp
                )
            }
        }
    }
}

fun calculatorBmi(height: String, weight: String): String {
    return if (height.isNotEmpty() && weight.isNotEmpty()) {
        val heightMeter: Double = height.toDouble() * 0.01
        val heightMeterSquare: Double = heightMeter * heightMeter
        val calculatorBmi: Double = weight.toDouble() / heightMeterSquare
        val formatBmi = (calculatorBmi * 10).roundToInt() / 10.0
        formatBmi.toString()
    } else {
        "N/A"
    }
}
