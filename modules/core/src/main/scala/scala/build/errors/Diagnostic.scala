package scala.build.errors

import scala.build.Position
import scala.build.errors.Diagnostic.RelatedInformation

trait Diagnostic {
  def message: String
  def severity: Severity
  def positions: Seq[Position]
  def relatedInformation: Option[RelatedInformation] = None
}

object Diagnostic {

  case class RelatedInformation(message: String)
  object Messages {
    val bloopTooOld =
      "JVM that is hosting bloop is older than the requested runtime. Please run `scala-cli bloop exit`, and then use `--jvm` flag to restart Bloop"
  }

  private case class ADiagnostic(
    message: String,
    severity: Severity,
    positions: Seq[Position]
  ) extends Diagnostic

  def apply(
    message: String,
    severity: Severity,
    positions: Seq[Position] = Nil
  ): Diagnostic = ADiagnostic(message, severity, positions)
}
