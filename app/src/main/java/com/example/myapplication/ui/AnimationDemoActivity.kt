package com.example.myapplication.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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

            Column {
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
                Box(Modifier.size(animateDpAsState(if (expanded) 200.dp else 40.dp, label = "").value).background(Color.Blue))

                Button(onClick = { red = !red }) {
                    Text("Change Color")
                }
                Box(Modifier.size(100.dp).background(animateColorAsState(if (red) Color.Red else Color.Blue,
                    label = ""
                ).value))

                Button(onClick = { moved = !moved }) {
                    Text("Move")
                }
                Text("Hello", Modifier.offset(x = animateDpAsState(if (moved) 100.dp else 0.dp).value))

                Button(onClick = { rotated = !rotated }) {
                    Text("Rotate")
                }
                Box(Modifier.rotate(animateFloatAsState(if (rotated) 360f else 0f, label = "").value).background(Color.Green).size(50.dp))
            }
        }
    }
}
