package com.comida_aleatoria.br

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import com.comida_aleatoria.br.ui.theme.Comida_aleatoriaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Comida_aleatoriaTheme {
                CulinariaScreen()
            }
        }
    }
}

@Composable
fun CulinariaScreen() {
    val telaSplash = R.drawable.tela1
    val telaInicio = R.drawable.tela2
    val telaIntroducao = R.drawable.tela3


    val telasAleatorias = listOf(
        R.drawable.tela4,
        R.drawable.tela5,
        R.drawable.tela6,
        R.drawable.tela7,
        R.drawable.tela8,
        R.drawable.tela9,
        R.drawable.tela10,
        R.drawable.tela11,
        R.drawable.tela12,
        R.drawable.tela13,
        R.drawable.tela14,
        R.drawable.tela15
    )

    var etapa by remember { mutableStateOf(1) }
    var imagemAtual by remember { mutableStateOf(telaSplash) }

    val itimFont = FontFamily(Font(R.font.itim))

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imagemAtual),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        when (etapa) {
            1 -> {
                LaunchedEffect(Unit) {
                    delay(2000)
                    imagemAtual = telaInicio
                    etapa = 2
                }
            }
            2 -> {
                BotaoCentral(
                    texto = "Continuar",
                    aoClicar = {
                        imagemAtual = telaIntroducao
                        etapa = 3
                    },
                    fontFamily = itimFont,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 130.dp)
                )
            }
            3 -> {
                BotaoCentral(
                    texto = "Sortear",
                    aoClicar = {
                        imagemAtual = telasAleatorias.random()
                        etapa = 4
                    },
                    fontFamily = itimFont,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 120.dp)
                )
            }
            else -> {
                BotaoCentral(
                    texto = "Sortear",
                    aoClicar = {
                        imagemAtual = telasAleatorias.random()
                    },
                    fontFamily = itimFont,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 120.dp)
                )
            }
        }
    }
}

@Composable
fun BotaoCentral(
    texto: String,
    aoClicar: () -> Unit,
    fontFamily: FontFamily,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .width(165.dp)
            .height(52.dp)
            .background(
                color = Color(0xFFDFB46E),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Button(
            onClick = aoClicar,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(
                text = texto,
                fontFamily = fontFamily,
                fontSize = 24.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CulinariaScreenPreview() {
    Comida_aleatoriaTheme {
        CulinariaScreen()
    }
}