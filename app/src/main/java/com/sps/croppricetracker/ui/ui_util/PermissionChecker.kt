package com.sps.croppricetracker.ui.ui_util

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.sps.croppricetracker.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionChecker(content: @Composable () -> Unit) {
    var state by remember { mutableStateOf(false) }

    val multiplePermissionsState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberMultiplePermissionsState(
            permissions = listOf(
//                Manifest.permission.POST_NOTIFICATIONS,
//                Manifest.permission.READ_MEDIA_AUDIO,
//                Manifest.permission.READ_MEDIA_VIDEO,
//                Manifest.permission.READ_MEDIA_IMAGES,
            ),
            onPermissionsResult = {
                state = it.all { perm -> perm.value }
            }
        )
    } else {
        rememberMultiplePermissionsState(
            permissions = listOf(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
            ),
            onPermissionsResult = {
                state = it.all { perm -> perm.value }
            }
        )
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    multiplePermissionsState.launchMultiplePermissionRequest()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    if (state) {
        content()
    } else {
        ShowPermissionPreview(multiplePermissionsState)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShowPermissionPreview(multiplePermissionsState: MultiplePermissionsState) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.badge_verified),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = "App Permission",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "The app requires the following permission to function properly," +
                        " please accept the permission to continue using the app",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp, start = 0.dp, end = 0.dp)
            ) {
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(multiplePermissionsState.permissions) { state ->
                        PermItem(state)
                    }
                }
            }

            Button(onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }) {
                Text(text = "Proceed")
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermItem(state: PermissionState) {
    val perm = state.permission.split(".").last()
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = perm, modifier = Modifier.weight(1f))
        if (!state.status.isGranted) {
            OutlinedIconButton(onClick = { state.launchPermissionRequest() }) {
                Icon(imageVector = Icons.Default.Security, contentDescription = null)
            }
        } else {
            FilledIconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                )
            }
        }
    }
}