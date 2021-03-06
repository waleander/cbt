/* automatically generated by build/build.scala from templates/Proguard.scala */
package cbt
import java.io.File
import java.nio.file.Files.deleteIfExists

sealed class KeepOptionModifier(val string: String)
object KeepOptionModifier {

  /** Also keep any classes in the descriptors of specified fields and methods. */
  object includedescriptorclasses
      extends KeepOptionModifier("includedescriptorclasses")

  /** Allow the specified entry points to be removed in the shrinking step. */
  object allowshrinking extends KeepOptionModifier("allowshrinking")

  /** Allow the specified entry points to be modified in the optimization step. */
  object allowoptimization extends KeepOptionModifier("allowoptimization")

  /** Allow the specified entry points to be renamed in the obfuscation step. */
  object allowobfuscation extends KeepOptionModifier("allowobfuscation")
}

trait Proguard extends BaseBuild {
  def proguardKeep(keep: (Seq[KeepOptionModifier], String)) = {
    ProguardLib(context.cbtLastModified, context.paths.mavenCache).proguard(
      outjars = Seq(scalaTarget / "proguarded.jar"),
      injars = classpath,
      libraryjars = Proguard.`rt.jar`,
      keep = keep
    )
  }
}

object Proguard {
  val version = "5.3.2"
  val `rt.jar` = ClassPath(
    Seq(new File(System.getProperty("java.home"), "lib/rt.jar")))
}

case class ProguardLib(
    cbtLastModified: Long,
    mavenCache: File,
    dependency: Option[DependencyImplementation] = None
)(
    implicit logger: Logger,
    transientCache: java.util.Map[AnyRef, AnyRef],
    classLoaderCache: ClassLoaderCache
) {

  /**
  Typed interface on top of the proguard command line tool.
  Check the official ProGuard docs for usage.
  Use `Some(None)` to call an option without arguments.
  Use `true` to set a flag.

  @see https://www.guardsquare.com/en/proguard/manual/refcard
  @see https://www.guardsquare.com/en/proguard/manual/usage

  @param adaptclassstrings Adapt string constants in the specified classes, based on the obfuscated names of any corresponding classes.
  @param adaptresourcefilecontents Update the contents of the specified resource files, based on the obfuscated names of the processed classes.
  @param adaptresourcefilenames Rename the specified resource files, based on the obfuscated names of the corresponding class files.
  @param allowaccessmodification Allow the access modifiers of classes and class members to be modified, while optimizing.
  @param applymapping Reuse the given mapping, for incremental obfuscation.
  @param assumenosideeffects Assume that the specified methods don't have any side effects, while optimizing.
  @param basedirectory Specifies the base directory for subsequent relative file names.
  @param classobfuscationdictionary Use the words in the given text file as obfuscated class names.
  @param dontnote Don't print notes about potential mistakes or omissions in the configuration.
  @param dontobfuscate Don't obfuscate the input class files.
  @param dontoptimize Don't optimize the input class files.
  @param dontpreverify Don't preverify the processed class files.
  @param dontshrink Don't shrink the input class files.
  @param dontskipnonpubliclibraryclasses Don't ignore non-public library classes (the default).
  @param dontskipnonpubliclibraryclassmembers Don't ignore package visible library class members.
  @param dontusemixedcaseclassnames Don't generate mixed-case class names while obfuscating.
  @param dontwarn Don't warn about unresolved references at all.
  @param dump Write out the internal structure of the processed class files, to the standard output or to the given file.
  @param flattenpackagehierarchy Repackage all packages that are renamed into the single given parent package.
  @param forceprocessing Process the input, even if the output seems up to date.
  @param ignorewarnings Print warnings about unresolved references, but continue processing anyhow.
  @param include Read configuration options from the given file.
  @param injars Specifies the program jars (or wars, ears, zips, or directories).
  @param keep Preserve the specified classes and class members.
  @param keepattributes Preserve the given optional attributes; typically Exceptions, InnerClasses, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable, LocalVariableTable, LocalVariableTypeTable, Synthetic, EnclosingMethod, and *Annotation*.
  @param keepclasseswithmembernames Preserve the names of the specified classes and class members, if all of the specified class members are present (after the shrinking step).
  @param keepclasseswithmembers Preserve the specified classes and class members, if all of the specified class members are present.
  @param keepclassmembernames Preserve the names of the specified class members (if they aren't removed in the shrinking step).
  @param keepclassmembers Preserve the specified class members, if their classes are preserved as well.
  @param keepdirectories Keep the specified directories in the output jars (or wars, ears, zips, or directories).
  @param keepnames Preserve the names of the specified classes and class members (if they aren't removed in the shrinking step).
  @param keeppackagenames Keep the specified package names from being obfuscated.
  @param keepparameternames Keep the parameter names and types of methods that are kept.
  @param libraryjars Specifies the library jars (or wars, ears, zips, or directories).
  @param mergeinterfacesaggressively Allow any interfaces to be merged, while optimizing.
  @param microedition Target the processed class files at Java Micro Edition.
  @param obfuscationdictionary Use the words in the given text file as obfuscated field names and method names.
  @param optimizationpasses The number of optimization passes to be performed.
  @param optimizations The optimizations to be enabled and disabled.
  @param outjars Specifies the names of the output jars (or wars, ears, zips, or directories).
  @param overloadaggressively Apply aggressive overloading while obfuscating.
  @param packageobfuscationdictionary Use the words in the given text file as obfuscated package names.
  @param printconfiguration Write out the entire configuration in traditional ProGuard style, to the standard output or to the given file.
  @param printmapping Print the mapping from old names to new names for classes and class members that have been renamed, to the standard output or to the given file.
  @param printseeds List classes and class members matched by the various -keep options, to the standard output or to the given file.
  @param printusage List dead code of the input class files, to the standard output or to the given file.
  @param renamesourcefileattribute Put the given constant string in the SourceFile attributes.
  @param repackageclasses Repackage all class files that are renamed into the single given package.
  @param skipnonpubliclibraryclasses Ignore non-public library classes.
  @param target Set the given version number in the processed classes.
  @param useuniqueclassmembernames Ensure uniform obfuscated class member names for subsequent incremental obfuscation.
  @param verbose Write out some more information during processing.
  @param whyareyoukeeping Print details on why the given classes and class members are being kept in the shrinking step.
    */
  case class proguard(
      adaptclassstrings: Option[Option[String]] = None,
      adaptresourcefilecontents: Option[Option[String]] = None,
      adaptresourcefilenames: Option[Option[String]] = None,
      allowaccessmodification: Boolean = false,
      applymapping: Option[File] = None,
      assumenosideeffects: Option[String] = None,
      basedirectory: Option[File] = None,
      classobfuscationdictionary: Option[File] = None,
      dontnote: Option[Option[String]] = None,
      dontobfuscate: Boolean = false,
      dontoptimize: Boolean = false,
      dontpreverify: Boolean = false,
      dontshrink: Boolean = false,
      dontskipnonpubliclibraryclasses: Boolean = false,
      dontskipnonpubliclibraryclassmembers: Boolean = false,
      dontusemixedcaseclassnames: Boolean = false,
      dontwarn: Option[Option[String]] = None,
      dump: Option[Option[File]] = None,
      flattenpackagehierarchy: Option[Option[String]] = None,
      forceprocessing: Boolean = false,
      ignorewarnings: Boolean = false,
      include: Option[File] = None,
      injars: ClassPath,
      keep: (Seq[KeepOptionModifier], String),
      keepattributes: Option[Option[String]] = None,
      keepclasseswithmembernames: Option[String] = None,
      keepclasseswithmembers: Option[(Seq[KeepOptionModifier], String)] = None,
      keepclassmembernames: Option[String] = None,
      keepclassmembers: Option[(Seq[KeepOptionModifier], String)] = None,
      keepdirectories: Option[Option[String]] = None,
      keepnames: Option[String] = None,
      keeppackagenames: Option[Option[String]] = None,
      keepparameternames: Boolean = false,
      libraryjars: ClassPath,
      mergeinterfacesaggressively: Boolean = false,
      microedition: Boolean = false,
      obfuscationdictionary: Option[File] = None,
      optimizationpasses: Option[Int] = None,
      optimizations: Option[String] = None,
      outjars: Seq[File],
      overloadaggressively: Boolean = false,
      packageobfuscationdictionary: Option[File] = None,
      printconfiguration: Option[Option[File]] = None,
      printmapping: Option[Option[File]] = None,
      printseeds: Option[Option[File]] = None,
      printusage: Option[Option[File]] = None,
      renamesourcefileattribute: Option[Option[String]] = None,
      repackageclasses: Option[Option[String]] = None,
      skipnonpubliclibraryclasses: Boolean = false,
      target: Option[String] = None,
      useuniqueclassmembernames: Boolean = false,
      verbose: Boolean = false,
      whyareyoukeeping: Option[String] = None
  ) extends (() => ClassPath) {

    // type class rendering scala values into string arguments
    private class valueToStrings[T](val apply: T => Option[Seq[String]])
    private object valueToStrings {
      def apply[T: valueToStrings](value: T) =
        implicitly[valueToStrings[T]].apply(value)
      implicit object SeqFile
          extends valueToStrings[Seq[File]](v => Some(v.map(_.string)))
      implicit object ClassPath
          extends valueToStrings[ClassPath](v => Some(Seq(v.string)))
      implicit object File
          extends valueToStrings[File](v => Some(Seq(v.string)))
      implicit object String extends valueToStrings[String](v => Some(Seq(v)))
      implicit object Int
          extends valueToStrings[Int](i => Some(Seq(i.toString)))
      implicit object Boolean
          extends valueToStrings[Boolean]({
            case false => None
            case true => Some(Nil)
          })
      implicit def Option2[T: valueToStrings]: valueToStrings[Option[T]] =
        new valueToStrings(
          _.map(implicitly[valueToStrings[T]].apply(_).toSeq.flatten)
        )
      implicit def Option3[T: valueToStrings]
        : valueToStrings[Option[Option[String]]] =
        new valueToStrings(_.map(_.toSeq))
      implicit def SpecWithModifiers: valueToStrings[(Seq[KeepOptionModifier],
                                                      String)] =
        new valueToStrings({
          case (modifiers, spec) =>
            Some(
              Seq(modifiers.map(_.string).map("," ++ _).mkString)
                .filterNot(_ == "") :+ spec)
        })
    }

    // capture string argument values and names
    val capturedArgs = capture_args.captureArgs

    def apply: ClassPath = {
      val args = capturedArgs.args
        .map(arg => arg.copy(name = "-" ++ arg.name))
        .flatMap(_.toSeqOption)
        .flatten
      outjars.map(_.toPath).map(deleteIfExists)
      val c = dependency getOrElse MavenResolver(cbtLastModified,
                                                 mavenCache,
                                                 mavenCentral).bindOne(
          MavenDependency("net.sf.proguard", "proguard-base", Proguard.version)
        ) runMain (
          "proguard.ProGuard",
          args: _*
        )
      if (c != ExitCode.Success) throw new Exception
      ClassPath(outjars)
    }
  }
}
