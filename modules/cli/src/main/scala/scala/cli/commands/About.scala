package scala.cli.commands

import caseapp._

import scala.build.internal.Constants
import scala.cli.CurrentParams

class About(isSipScala: Boolean) extends ScalaCommand[AboutOptions] {

  override def group = "Miscellaneous"

  def run(options: AboutOptions, args: RemainingArgs): Unit = {
    CurrentParams.verbosity = options.verbosity.verbosity
    println(Version.versionInfo(isSipScala))
    val newestScalaCliVersion = Update.newestScalaCliVersion(options.ghToken.map(_.get()))
    val isOutdated = CommandUtils.isOutOfDateVersion(
      newestScalaCliVersion,
      Constants.version
    )
    if (isOutdated)
      println(
        s"""Your Scala CLI version is outdated. The newest version is $newestScalaCliVersion
           |It is recommended that you update Scala CLI through the same tool or method you used for its initial installation for avoiding the creation of outdated duplicates.""".stripMargin
      )
  }
}
