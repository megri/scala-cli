package scala.cli.commands.publish

import caseapp._

import scala.cli.commands.pgp.SharedPgpPushPullOptions
import scala.cli.commands.{
  CoursierOptions,
  LoggingOptions,
  SharedDirectoriesOptions,
  SharedInputOptions,
  SharedJvmOptions,
  SharedWorkspaceOptions
}
import scala.cli.signing.shared.PasswordOption
import scala.cli.signing.util.ArgParsers._

// format: off
final case class PublishSetupOptions(
  @Recurse
    logging: LoggingOptions = LoggingOptions(),
  @Recurse
    directories: SharedDirectoriesOptions = SharedDirectoriesOptions(),
  @Recurse
    coursier: CoursierOptions = CoursierOptions(),
  @Recurse
    workspace: SharedWorkspaceOptions = SharedWorkspaceOptions(),
  @Recurse
    input: SharedInputOptions = SharedInputOptions(),
  @Recurse
    publishParams: PublishParamsOptions = PublishParamsOptions(),
  @Recurse
    publishRepo: PublishRepositoryOptions = PublishRepositoryOptions(),
  @Recurse
    sharedPgp: SharedPgpPushPullOptions = SharedPgpPushPullOptions(),
  @Recurse
    sharedJvm: SharedJvmOptions = SharedJvmOptions(),

  @Group("Publishing")
  @HelpMessage("Public key to use to verify artifacts (to be uploaded to a key server)")
    publicKey: Option[PasswordOption] = None,

  @Group("Publishing")
  @HelpMessage("Check if some options for publishing are missing, and exit with non-zero return code if that's the case")
    check: Boolean = false,
  @Group("Publishing")
  @HelpMessage("GitHub token to use to upload secrets to GitHub - password encoded")
    token: Option[PasswordOption] = None,
  @Group("Publishing")
  @HelpMessage("Generate a random key pair for publishing, with a secret key protected by a random password")
    randomSecretKey: Option[Boolean] = None,
  @Group("Publishing")
  @HelpMessage("When generating a random key pair, the mail to associate to it")
    randomSecretKeyMail: Option[String] = None,
  @Group("Publishing")
  @HelpMessage("The option groups to check - can be \"all\", or a comma-separated list of \"core\", \"signing\", \"repo\", \"extra\"")
    checks: Option[String] = None,
  @Group("Publishing")
  @HelpMessage("Whether to check if a GitHub workflow already exists (one for publishing is written if none is found)")
    checkWorkflow: Option[Boolean] = None,
  @Group("Publishing")
  @HelpMessage("Dummy mode - don't upload any secret to GitHub")
    dummy: Boolean = false
)
// format: on

object PublishSetupOptions {
  implicit lazy val parser: Parser[PublishSetupOptions] = Parser.derive
  implicit lazy val help: Help[PublishSetupOptions]     = Help.derive
}
