package nl.papendorp.template

import java.io.ByteArrayOutputStream

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers

class MainSuite extends AnyFreeSpecLike
		with Matchers
{
	val NewLine: String = sys.props( "line.separator" )

	"The main should" - {
		"output its command line arguments to the Console." in {
			val mock = new ByteArrayOutputStream()
			Console.withOut( mock ){
				Main.main( Array( "test", "suite" ) )
			}

			val actualOutput = mock.toString()
			actualOutput should be( "Hello, test and suite!" + NewLine )
		}

		"use a default to output if no arguments are given." in {
			val mock = new ByteArrayOutputStream()
			Console.withOut( mock ){
				Main.main( Array() )
			}

			val actualOutput = mock.toString()
			actualOutput should be( "Hello, you!" + NewLine )
		}
	}
}
