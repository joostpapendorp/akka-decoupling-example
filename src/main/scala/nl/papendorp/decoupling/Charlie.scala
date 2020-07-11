package nl.papendorp.decoupling

import akka.actor.typed.scaladsl.Behaviors.{ receive, same }
import akka.actor.typed.{ ActorRef, Behavior }
import nl.papendorp.decoupling.Bob.CharlieToBob

class Charlie( bob: => ActorRef[ CharlieToBob ] )
{
	import Charlie._
	import nl.papendorp.decoupling.Bob.HowYouDoinBob

	val concerned: Behavior[ BobToCharlie ] = receive( ( context, msg ) => msg match {
		case CryToCharlie =>
			context.log.info( "Bob cries to Charlie" )
			bob ! HowYouDoinBob
			same

		case LaughToCharlie =>
			context.log.info( "Bob laughs to Charlie" )
			bob ! HowYouDoinBob
			same
	} )
}

object Charlie
{
	sealed trait BobToCharlie extends Protocol[ Bob, Charlie ]
	case object CryToCharlie extends BobToCharlie
	case object LaughToCharlie extends BobToCharlie
}
