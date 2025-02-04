import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    icon: DrawableResource? = null, // Use either a drawable resource ID or a painter
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    fontFamily: FontFamily = LocalUrbanistFontFamily.current
) {
    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Image(
                    painter = painterResource(icon), // Cast to Int for resource ID
                    contentDescription = "$text Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.button.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    fontFamily = fontFamily,
                ),
            )
        }
    }
}