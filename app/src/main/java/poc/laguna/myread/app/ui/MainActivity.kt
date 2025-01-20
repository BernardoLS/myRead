package poc.laguna.myread.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import poc.laguna.myread.app.navigation.AppNavHost
import poc.laguna.myread.app.navigation.BottomNavigationBar
import poc.laguna.myread.core.ui.theme.MyReadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyReadTheme {
                val navigationController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navigationController = navigationController)
                    }
                ) { innerPadding ->
                   AppNavHost(
                       navController = navigationController,
                       modifier = Modifier.padding(innerPadding)
                   )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyReadTheme {
        val navigationController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(navigationController = navigationController)
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navigationController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}