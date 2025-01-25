package com.example.uas_pam_131.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas_pam_131.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Color(0xFF42A5F5),
                shape = RoundedCornerShape(bottomEnd = 80.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Icon(
                    Icons.Filled.Home, contentDescription = "",
                    Modifier
                        .padding(6.dp)
                        .size(30.dp),
                    tint = Color.White
                )
                Text(
                    text = "Inventaris",
                    Modifier
                        .padding(6.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),

                    )
                Text(
                    text = "Kebun Binatang Taman Safari Indonesia",
                    Modifier
                        .padding(6.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    )

                )

            }
            Image(
                painter = painterResource(id = R.drawable.tamansafari),
                contentDescription = "",
                Modifier
                    .size(125.dp)
                    .clip(shape = CircleShape)

            )
        }
    }
}