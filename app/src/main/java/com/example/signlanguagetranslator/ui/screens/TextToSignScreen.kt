package com.example.signlanguagetranslator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sceneview.Scene
import io.github.sceneview.math.Position
import io.github.sceneview.model.getAnimationIndex
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNode

@Composable
fun TextToSignScreen() {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val environmentLoader = rememberEnvironmentLoader(engine)

    val cameraNode = rememberCameraNode(engine).apply {
        position = Position(z = 4.0f)
    }

    val centerNode = rememberNode(engine)
        .addChildNode(cameraNode)
    val customNode = rememberNode {
        ModelNode(
            modelInstance = modelLoader.createModelInstance(
                assetFileLocation = "models/female.glb"
            ),
            scaleToUnits = 1.0f
        )
    }

    // Choose animation by name
    val animationName = "your_animation_name"
    val animationIndex = customNode.animator.getAnimationIndex(animationName)

    // Play the animation if found
    if (animationIndex != null) {
        customNode.playAnimation(animationIndex)
    }

    val centerNodePosition = Position(x = 0.0f, y = 0.65f, z = 0.0f)

    Scene(
        modifier = Modifier.fillMaxSize(),
        engine = engine,
        modelLoader = modelLoader,
        cameraNode = cameraNode,
        childNodes = listOf(centerNode, customNode),
        environment = environmentLoader.createHDREnvironment(
            assetFileLocation = "environments/sky2k.hdr"
        )!!,
        onFrame = {

            cameraNode.lookAt(centerNode)
            centerNode.position = centerNodePosition
        }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 4.dp)
                .padding(16.dp),
            trailingIcon = {
                // Add an icon to send the user input
                IconButton(onClick = { /* Send the user input */ }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            }
        )
    }
}