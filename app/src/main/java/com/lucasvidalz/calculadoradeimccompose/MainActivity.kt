package com.lucasvidalz.calculadoradeimccompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasvidalz.calculadoradeimccompose.calculo.CalcularIMC
import com.lucasvidalz.calculadoradeimccompose.ui.theme.CalculadoraDeIMCTheme
import com.lucasvidalz.calculadoradeimccompose.ui.theme.DARK_BLUE
import com.lucasvidalz.calculadoradeimccompose.ui.theme.LIGTH_BLUE
import com.lucasvidalz.calculadoradeimccompose.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeIMCTheme {
                CalculadoraIMC()
            }

        }
    }
}

@Composable
fun CalculadoraIMC() {
    val context = LocalContext.current
    val calcularIMC = CalcularIMC()
    var peso by remember{ mutableStateOf("") }
    var altura by remember{ mutableStateOf("") }
    var textResultado by remember{ mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGTH_BLUE,
                title = {
                    Text(
                        text = "Calculadora de IMC", color = WHITE
                    )
                },
                actions = {

                    IconButton(
                        onClick = {
                            peso = ""
                            altura = ""
                            textResultado = ""
                        }
                    ){
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh2),
                            contentDescription = "ÍCONE DE RESETAR TODOS OS CAMPOS"

                        )

                    }
                    

                }
            )
        }
    ) { paddingValues ->  //escopo do scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {//escopo da column
            Text(
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGTH_BLUE,
                modifier = Modifier.padding(50.dp)
            )
            OutlinedTextField(
                value = peso,
                onValueChange = {nextText ->
                    peso = nextText
                },
                label = {
                    Text(text = "Peso(KG)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LIGTH_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            )

            OutlinedTextField(
                value = altura,
                onValueChange = {nextText ->
                    altura = nextText
                },
                label = {
                    Text(text = "Altura")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LIGTH_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
            Button(
                onClick = {
                    if(peso.isEmpty() || altura.isEmpty()){
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                    }else{
                        calcularIMC.calcularIMC(peso, altura)
                        textResultado = calcularIMC.resultadoIMC()

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                        backgroundColor = LIGTH_BLUE,
                        contentColor = WHITE
                )
            ) {//scop of button
                Text(
                    text = "Calcula IMC",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                    )
            }
            Text(
                text = textResultado,
                fontSize = 18.sp,
                color = DARK_BLUE,
                fontWeight = FontWeight.Bold
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalculadoradeImcPreview() {
    CalculadoraIMC()
}