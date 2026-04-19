package com.sport.timer.ui

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sport.timer.PreferencesManager

@Composable
fun PreferencesScreen(application: Application, onBack: () -> Unit) {
    var workTime by remember { mutableStateOf(PreferencesManager.getDefaultWorkTime(application)) }
    var restTime by remember { mutableStateOf(PreferencesManager.getDefaultRestTime(application)) }
    var series by remember { mutableStateOf(PreferencesManager.getDefaultSeries(application)) }
    var workStep by remember { mutableStateOf(PreferencesManager.getWorkStep(application)) }
    var restStep by remember { mutableStateOf(PreferencesManager.getRestStep(application)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0D))
            .systemBarsPadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBack,
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2A2A)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("←", fontSize = 20.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "PRÉFÉRENCES",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Valeurs par défaut au démarrage et à la réinitialisation",
            color = Color(0xFF888888),
            fontSize = 12.sp,
            letterSpacing = 0.5.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ParameterPicker(
                label = "TRAVAIL",
                value = formatTime(workTime),
                onDecrement = { workTime = maxOf(10, workTime - workStep) },
                onIncrement = { workTime = minOf(180, workTime + workStep) },
                color = Color(0xFF4CAF50),
                enabled = true
            )
            ParameterPicker(
                label = "REPOS",
                value = formatTime(restTime),
                onDecrement = { restTime = maxOf(0, restTime - restStep) },
                onIncrement = { restTime = minOf(600, restTime + restStep) },
                color = Color(0xFF2196F3),
                enabled = true
            )
            ParameterPicker(
                label = "SÉRIES",
                value = series.toString(),
                onDecrement = { series = maxOf(1, series - 1) },
                onIncrement = { series = minOf(99, series + 1) },
                color = Color(0xFFFF9800),
                enabled = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pas d'incrémentation (secondes)",
            color = Color(0xFF888888),
            fontSize = 12.sp,
            letterSpacing = 0.5.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ParameterPicker(
                label = "PAS TRAV.",
                value = "${workStep}s",
                onDecrement = { workStep = maxOf(1, workStep - 1) },
                onIncrement = { workStep = minOf(60, workStep + 1) },
                color = Color(0xFF4CAF50),
                enabled = true
            )
            Spacer(modifier = Modifier.width(16.dp))
            ParameterPicker(
                label = "PAS REPOS",
                value = "${restStep}s",
                onDecrement = { restStep = maxOf(1, restStep - 1) },
                onIncrement = { restStep = minOf(60, restStep + 1) },
                color = Color(0xFF2196F3),
                enabled = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                PreferencesManager.saveDefaults(application, workTime, restTime, series, workStep, restStep)
                onBack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 0.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text(
                text = "ENREGISTRER",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
