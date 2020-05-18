/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.camera.camera2.pipe

import android.content.Context
import androidx.camera.camera2.pipe.impl.CameraGraphModule
import androidx.camera.camera2.pipe.impl.CameraPipeComponent
import androidx.camera.camera2.pipe.impl.CameraPipeModule
import androidx.camera.camera2.pipe.impl.DaggerCameraPipeComponent

/**
 * Interface to configure and access the camera device wrappers.
 */
class CameraPipe(private val config: CameraPipe.Config) {
    private val component: CameraPipeComponent = DaggerCameraPipeComponent.builder()
        .cameraPipeModule(CameraPipeModule(config))
        .build()

    fun create(config: CameraGraph.Config): CameraGraph {
        return component.cameraGraphComponentBuilder()
            .cameraGraphModule(CameraGraphModule(config))
            .build()
            .cameraGraph()
    }

    data class Config(
        val appContext: Context
    )
}