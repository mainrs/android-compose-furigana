import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import net.zerotask.libraries.android.compose.furigana.example.Content
import net.zerotask.libraries.android.compose.furigana.example.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy
import tools.fastlane.screengrab.locale.LocaleTestRule

class Screenshots {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @Before
    fun init() {
        Screengrab.setDefaultScreenshotStrategy(UiAutomatorScreenshotStrategy())
    }

    @Test
    fun screenshotShowcase() {
        composeTestRule.setContent {
            MaterialTheme {
                Content()
            }
        }
        Screengrab.screenshot("showcase")
    }
}
