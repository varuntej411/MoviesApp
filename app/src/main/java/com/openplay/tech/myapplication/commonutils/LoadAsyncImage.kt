package com.openplay.tech.myapplication.commonutils

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.openplay.tech.myapplication.R
import com.openplay.tech.myapplication.ui.theme.Pink40


@Composable
fun LoadAsyncImage(
    imageUrl: String,
    context: Context
) {
    AsyncImage(
        // without context we can use model directly assign Any type that can load image otherwise use builder
        //   model = imageUrl,
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .crossfade(true)
            .build(),
        contentDescription = "ImageHolder",
        contentScale = ContentScale.Crop,
       // placeholder = painterResource(id = R.drawable.ic_launcher_background),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Pink40)
        // Use Image Loader instead of model both will work same load type
        // imageLoader = ImageLoader(context).newBuilder()
        // .error(R.drawable.profile)
        //.crossfade(true).placeholder(R.drawable.ic_launcher_background).build(),
        // we can use this state of remeberpainter too based on requirement
        //val painter = rememberImagePainter(data = "https://images.unsplash.com/photo",
        // builder = {
        // transformations(
        // GrayscaleTransformation(),       // Gray Scale Transformation
        // CircleCropTransformation()       // Circle Crop Transformation
        // )
        // })

        //Image(
        //    painter = painter,
        //    contentDescription = "Forest Image",
        //    modifier = Modifier.fillMaxSize(),
        //    contentScale = ContentScale.Crop
        //)

    )

}

@Composable
fun SubImageLoader(
    imageUrl: String,
    context: Context
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "image",
        loading = { CustomProgressBarForAsyncImage() },
        error = { R.drawable.unselectedhome },
        contentScale = ContentScale.Fit,

        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(width = 1.dp, color = Color.Red)

    )
}

@Composable
fun CustomProgressBarForAsyncImage() {
    Box(
        modifier = Modifier.width(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}

@Composable
@Preview
fun PreviewAysncImage() {
    SubImageLoader(imageUrl = "https://i.pravatar.cc", context = LocalContext.current)
}