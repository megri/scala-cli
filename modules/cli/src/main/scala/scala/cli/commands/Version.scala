package scala.cli.commands

import caseapp._

import scala.build.internal.Constants
import scala.cli.CurrentParams

class Version(isSipScala: Boolean) extends ScalaCommand[VersionOptions] {
  override def group = "Miscellaneous"
  def run(options: VersionOptions, args: RemainingArgs): Unit = {
    CurrentParams.verbosity = options.verbosity.verbosity
    if (options.cliVersion)
      println(Constants.version)
    else if (options.scalaVersion)
      println(Constants.defaultScalaVersion)
    else
      println(Version.versionInfo(isSipScala))
  }
}

object Version {
  def versionInfo(isSipScala: Boolean) =
    val version            = Constants.version
    val detailedVersionOpt = Constants.detailedVersion.filter(_ != version).fold("")(" (" + _ + ")")
    val appName =
      if (isSipScala) "Scala code runner"
      else "Scala CLI"
    s"""$appName version: $version$detailedVersionOpt
       |Scala version (default): ${Constants.defaultScalaVersion}""".stripMargin
}
