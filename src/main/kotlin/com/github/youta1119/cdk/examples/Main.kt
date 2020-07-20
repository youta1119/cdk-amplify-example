@file:JvmName("Main")

package com.github.youta1119.cdk.examples


import jp.justincase.cdkdsl.services.amplify.App
import jp.justincase.cdkdsl.services.amplify.BranchOptions
import jp.justincase.cdkdsl.services.amplify.GitHubSourceCodeProvider
import jp.justincase.cdkdsl.services.secretsmanager.SecretAttributes
import software.amazon.awscdk.core.App
import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.services.secretsmanager.Secret

class AmplifyConsoleAppCdkStack(scope: Construct, id: String) : Stack(scope, id) {
    init {
        val oauthTokenSecret = Secret.fromSecretAttributes(this, "oauth-token", SecretAttributes {
            secretArn = "<your secret arn here>"
        })
        val amplifyApp = App("example") {
            appName = "example"
            sourceCodeProvider = GitHubSourceCodeProvider {
                oauthToken = oauthTokenSecret.secretValue
                repository = "cdk-amplify-example"
                owner = "youta1119"
            }
        }
        val branches = listOf("master", "develop")
        branches.forEach{
            amplifyApp.addBranch(it, BranchOptions {
                branchName = it
            })
        }

    }
}

fun main() {
    val app = App()
    AmplifyConsoleAppCdkStack(app, "amplify-app")
    app.synth()
}