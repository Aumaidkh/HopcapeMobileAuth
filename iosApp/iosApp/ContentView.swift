import UIKit
import SwiftUI
import auth


struct ContentView: View {
    var body: some View {
        VStack{
            Text("Splash")
                .onAppear {
                    if let rootWindow = UIApplication.shared.windows.first {
                        let authConfigBuilder: (AuthConfigBuilder) -> AuthConfig = { builder in
                            builder.setAuthenticationFlowLauncher(authenticationFlowLauncher: IOSAuthenticationFlowLauncher(window: rootWindow))
                            return builder.build()
                        }
                        let dependencyFactory = IOSPlatformAuthenticationDependencyFactory()
                        let authenticator = AuthenticatorCompanion().create(factory: dependencyFactory)
                        authenticator.configure(config: authConfigBuilder)
                        authenticator.authenticate(onAuthenticationSuccess: {
                            print("Authenticated...")
                        }, onAuthenticationFailure: {
                            print("Error while authentication")
                        })
                    }
                    
                }
        }
    }
}



