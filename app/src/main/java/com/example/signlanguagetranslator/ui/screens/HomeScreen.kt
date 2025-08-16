package com.example.signlanguagetranslator.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signlanguagetranslator.Circle
import com.example.signlanguagetranslator.CustomBottomNavigation
import com.example.signlanguagetranslator.FabGroup
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.ui.theme.LightGrey2
import com.example.signlanguagetranslator.ui.theme.Poppins
import com.example.signlanguagetranslator.ui.theme.PrimaryGrey
import com.example.signlanguagetranslator.ui.theme.Shapes
import com.example.signlanguagetranslator.R.font.poppins
import com.example.signlanguagetranslator.R.drawable.ic_wallet
import com.example.signlanguagetranslator.ui.theme.CardRed
import com.example.signlanguagetranslator.ui.theme.Service1
import com.example.signlanguagetranslator.ui.theme.Service2
import com.example.signlanguagetranslator.ui.theme.Service3
import com.example.signlanguagetranslator.ui.theme.Service4

data class Developer(val imageId:Int,val name:String,val Amount:String)


@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

@Composable
fun HomeScreen(navController: NavController) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    HomeScreen(
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress,
        navController = navController
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }

}

@Composable
fun HomeScreen(
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    navController: NavController,
    toggleAnimation: () -> Unit = { }
) {
    val developerData = listOf(
        Developer(R.drawable.mark, "Mark Jeric", "Exconde"),
        Developer(R.drawable.jasmine, "Jasmine", "Macalintal"),
        Developer(R.drawable.zyra, "Zyra", "Flores"),
        Developer(R.drawable.raymundo, "John Raymon", "Guran"),
        Developer(R.drawable.ken, "Ken Russel", "Gopez"),
        Developer(R.drawable.jennel, "Jennel", "Atienza")

    )
    Box(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            HeaderUI(navController)
            CardUI()
            DataUI()
            ServicesUI(navController)
            SendMoneyUI(developerData)
        }

        CustomBottomNavigation(
            navController = navController,
        )

        Circle(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress, navController = navController, toggleAnimation = toggleAnimation)
        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation,
            navController = navController
        )
        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}


@Composable
fun SendMoneyUI(sendMoneyData: List<Developer>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Developers",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        LazyRow {
            items(sendMoneyData ) {item->
                SendMoneyItemUI(item)
            }
        }
    }
}

@Composable
fun SendMoneyItemUI(item: Developer) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(end = 6.dp)
            .padding(vertical = 10.dp)

    ) {
        Column(
            modifier = Modifier
                .size(width = 100.dp, height = 140.dp)
                .border(width = 0.dp, color = LightGrey2, shape = Shapes.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)

            )
            Text(
                text = item.name,
                modifier = Modifier
                    .alpha(0.6f)
                    .padding(top = 6.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins
            )
            Text(
                text = item.Amount,
                modifier = Modifier.alpha(0.8f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        }
    }
}

@Composable
fun ServicesUI(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Features",
            fontFamily = FontFamily(Font(poppins)),
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ServiceUI(R.drawable.baseline_camera_alt_24, "Recognition", Service1){
                navController.navigate("Recognition")
            }
            ServiceUI(R.drawable.baseline_face_24, "Avatar", Service2){
                navController.navigate("Avatar")
            }
            ServiceUI(R.drawable.baseline_book_24, "Dictionary", Service3){
                navController.navigate("DictionaryFeature")
            }
            ServiceUI(R.drawable.ic_more, "More", Service4){
                navController.navigate("More")
            }
        }

    }

}

@Composable
fun ServiceUI(id: Int, text: String, color: Color, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.size(60.dp),  //avoid the oval shape
            shape = CircleShape,
            border= BorderStroke(0.dp, color),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = color)
        ) {
            Icon(painter = painterResource(id = id), contentDescription = "content description")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            modifier = Modifier.alpha(0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(poppins))
        )
    }
}

@Composable
fun DataUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp), Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "57+",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(poppins)),
                lineHeight = 20.sp
            )
            Text(
                text = "Total Classes",
                fontFamily = FontFamily(Font(poppins)),
                modifier = Modifier.alpha(0.6f),
                fontSize = 14.sp
            )
        }
        Column {
            Text(
                text = "34.2k+",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(poppins)),
                lineHeight = 20.sp,
            )
            Text(
                text = "Total Data",
                fontFamily = FontFamily(Font(poppins)),
                modifier = Modifier.alpha(0.6f),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CardUI() {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(150.dp),
    ) {
        Row {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Sign Language",
                    modifier = Modifier
                        .alpha(1f),
                    fontFamily = FontFamily(
                        Font(poppins)
                    )
                )
                Text(
                    text = "Translator",
                    fontFamily = FontFamily(
                        Font(poppins)
                    ),
                    fontSize = 30.sp
                )
                Button(
                    onClick = { },
                    modifier = Modifier
                        .clip(Shapes.large)
                        .border(width = 0.dp, color = Color.Transparent, shape = Shapes.large),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGrey),
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = 12.sp,
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.slt3),
                contentDescription = "",
                modifier = Modifier
                    .alpha(0.8f)
                    .size(125.dp)
                    .padding(top = 20.dp, start = 20.dp)
            )
        }

    }
}

@Composable
fun HeaderUI(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp)
            .padding(bottom = 26.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Hi User",
                modifier = Modifier.alpha(1f),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(poppins)),
            )
            Text(
                text = "Welcome Back",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(poppins)),
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    navController.navigate("Profile")
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.default_icon),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .border(width = 0.dp, Color.Transparent, shape = Shapes.medium)
                    .clip(Shapes.medium)
            )
        }
}}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Create a mock NavController for the preview
    val navController = rememberNavController()

    // Call the HomeScreen composable
    HomeScreen(navController = navController)
}