package nl.papendorp.decoupling

import akka.actor.typed.scaladsl.Behaviors._
import akka.actor.typed.{ ActorRef, Behavior }
import nl.papendorp.decoupling.Alice.BobToAlice
import nl.papendorp.decoupling.Charlie.BobToCharlie

class Bob( alice: => ActorRef[ BobToAlice ], charlie: => ActorRef[ BobToCharlie ] )
{
	import Alice._
	import Bob._
	import Charlie._

	val happy: Behavior[ BobsPersonalLife ] = receive( ( context, msg ) => msg match {
		case HowYouDoinBob =>
			context.log.info( "Charlie asks how Bob doin'" )
			charlie ! LaughToCharlie
			same

		case ScoldBob =>
			context.log.info( "Alice scolds Bob" )
			alice ! ApologizeToAlice
			sad

		case SingToBob =>
			context.log.info( "Alice sings to Bob" )
			alice ! LaughAtAlice
			same
	} )

	val sad: Behavior[ BobsPersonalLife ] = receive( ( context, msg ) => msg match {
		case HowYouDoinBob =>
			context.log.info( "Charlie asks how Bob doin'" )
			charlie ! CryToCharlie
			same

		case ScoldBob =>
			context.log.info( "Alice scolds Bob" )
			alice ! ApologizeToAlice
			same

		case SingToBob =>
			context.log.info( "Alice sings to Bob" )
			alice ! LaughAtAlice
			happy
	} )
}

object Bob
{
	sealed trait BobsPersonalLife

	sealed trait AliceToBob extends Protocol[ Alice, Bob ] with BobsPersonalLife
	case object SingToBob extends AliceToBob
	case object ScoldBob extends AliceToBob

	sealed trait CharlieToBob extends Protocol[ Charlie, Bob ] with BobsPersonalLife
	case object HowYouDoinBob extends CharlieToBob
}
