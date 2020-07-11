package nl.papendorp.decoupling

import akka.actor.typed.scaladsl.Behaviors.{ receiveMessage, same, setup, stopped }
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }
import nl.papendorp.decoupling.Alice.{ BobToAlice, LaughAtAlice }
import nl.papendorp.decoupling.Bob.BobsPersonalLife
import nl.papendorp.decoupling.Charlie.{ BobToCharlie, LaughToCharlie }

object AkkaDecouplingExample extends App
{
  sealed trait SystemMessage
  case object Start extends SystemMessage
  case object Stop extends SystemMessage

  lazy val bla: Behavior[ SystemMessage ] = setup( context => {
    import context._

    lazy val alice: ActorRef[ BobToAlice ] = spawn( new Alice( bob ).happy, "Alice" )
    lazy val bob: ActorRef[ BobsPersonalLife ] = spawn( new Bob( alice, charlie ).happy, "Bob" )
    lazy val charlie: ActorRef[ BobToCharlie ] = spawn( new Charlie( bob ).concerned, "Charlie" )

    receiveMessage{
      case Start =>
        alice ! LaughAtAlice
        charlie ! LaughToCharlie
        same

      case Stop =>
        stopped
    }
  } )

  lazy val system = ActorSystem( bla, "AliceBobAndCharlie" )

  system ! Start
  Thread.sleep( 1000 )
  system ! Stop
}
