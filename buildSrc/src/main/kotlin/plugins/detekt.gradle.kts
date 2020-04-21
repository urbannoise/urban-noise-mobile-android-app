/*
 * Copyright 2019 urbannoise.org
 *
 * Licensed under the Attribution-NonCommercial-ShareAlike 4.0
 * International (CC BY-NC-SA 4.0) you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plugins

import io.gitlab.arturbosch.detekt.detekt
import io.gitlab.arturbosch.detekt.DetektPlugin

apply<DetektPlugin>()

detekt {
    input = files("src/main/java")
    config = files("$rootDir/.detekt/config.yml")
    reports {
        xml {
            enabled = true
            destination = file("build/reports/detekt/report.xml")
        }
        html {
            enabled = true
            destination = file("build/reports/detekt/report.html")
        }
    }
}
