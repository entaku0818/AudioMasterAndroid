package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

class AnimationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var visible by remember { mutableStateOf(false) }
            var expanded by remember { mutableStateOf(false) }
            var red by remember { mutableStateOf(false) }
            var moved by remember { mutableStateOf(false) }
            var rotated by remember { mutableStateOf(false) }
            var scaleExpanded by remember { mutableStateOf(false) }
            var rotatedX by remember { mutableStateOf(false) }
            var faded by remember { mutableStateOf(true) }
            var raised by remember { mutableStateOf(false) }
            var currentState by remember { mutableStateOf(true) }

            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier.verticalScroll(scrollState).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    // Existing animations
                    Button(onClick = { visible = !visible }) {
                        Text("Toggle Fade")
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text("Hello, World!")
                    }

                    Button(onClick = { expanded = !expanded }) {
                        Text("Expand")
                    }
                    Box(Modifier.size(animateDpAsState(if (expanded) 200.dp else 40.dp).value).background(Color.Blue))

                    Button(onClick = { red = !red }) {
                        Text("Change Color")
                    }
                    Box(Modifier.size(100.dp).background(animateColorAsState(if (red) Color.Red else Color.Blue).value))

                    Button(onClick = { moved = !moved }) {
                        Text("Move")
                    }
                    Text("Hello", Modifier.offset(x = animateDpAsState(if (moved) 100.dp else 0.dp).value))

                    Button(onClick = { rotated = !rotated }) {
                        Text("Rotate")
                    }
                    Box(Modifier.rotate(animateFloatAsState(if (rotated) 360f else 0f).value).background(Color.Green).size(50.dp))

                    // New animations
                    Button(onClick = { scaleExpanded = !scaleExpanded }) {
                        Text("Scale")
                    }
                    Box(Modifier.size(100.dp).graphicsLayer {
                        scaleX = if (scaleExpanded) 1.5f else 1f
                        scaleY = if (scaleExpanded) 1.5f else 1f
                    }.background(Color.Magenta))

                    Button(onClick = { rotatedX = !rotatedX }) {
                        Text("Flip X")
                    }
                    Box(Modifier.size(100.dp).graphicsLayer {
                        rotationX = if (rotatedX) 180f else 0f
                    }.background(Color.Cyan))

                    Button(onClick = { faded = !faded }) {
                        Text("Fade Opacity")
                    }
                    Text("Fading Text", Modifier.alpha(if (faded) 1f else 0.3f))

                    Button(onClick = { raised = !raised }) {
                        Text("Elevate")
                    }
                    val elevation = animateDpAsState(if (raised) 10.dp else 0.dp, label = "")
                    Card(
                        modifier = Modifier.size(200.dp),
                        elevation = cardElevation(defaultElevation = elevation.value)
                    ) {
                        Text("Elevated Card", Modifier.padding(16.dp))
                    }


                    Button(onClick = { currentState = !currentState }) {
                        Text("Crossfade")
                    }
                    Crossfade(targetState = currentState, label = "") { state ->
                        if (state) Text("Hello") else Text("Goodbye")
                    }
                }
            }

        }
    }
}
