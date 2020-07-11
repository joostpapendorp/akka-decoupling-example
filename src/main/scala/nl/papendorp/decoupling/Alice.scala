package nl.papendorp.decoupling

import akka.actor.typed.scaladsl.Behaviors.{ receive, same }
import akka.actor.typed.{ ActorRef, Behavior }
import nl.papendorp.decoupling.Bob.AliceToBob

class Alice( bob: ActorRef[ AliceToBob ] )
{
	import Alice._
	import nl.papendorp.decoupling.Bob.{ ScoldBob, SingToBob }

	val happy: Behavior[ BobToAlice ] = receive( ( context, msg ) => msg match {
		case LaughAtAlice =>
			context.log.info( "Bob laughs at Alice" )
			bob ! ScoldBob
			annoyed

		case ApologizeToAlice =>
			context.log.info( "Bob apologizes to Alice" )
			bob ! SingToBob
			same
	} )

	val annoyed: Behavior[ BobToAlice ] = receive( ( context, msg ) => msg match {
		case LaughAtAlice =>
			context.log.info( "Bob laughs at Alice" )
			bob ! ScoldBob
			same

		case ApologizeToAlice =>
			context.log.info( "Bob apologizes to Alice" )
			bob ! SingToBob
			happy
	} )
}

object Alice
{

	sealed trait BobToAlice extends Protocol[ Bob, Alice ]

	case object ApologizeToAlice extends BobToAlice

	case object LaughAtAlice extends BobToAlice

}
