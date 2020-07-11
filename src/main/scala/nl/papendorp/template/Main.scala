package nl.papendorp.template

object Main extends App
{
  val greeted = args.reduceOption( _ + " and " + _ ).getOrElse("you")
  println( s"Hello, $greeted!" )
}
