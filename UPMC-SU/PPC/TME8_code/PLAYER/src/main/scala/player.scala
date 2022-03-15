
package upmc.akka.culto

import math._

import javax.sound.midi._
import javax.sound.midi.ShortMessage._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import akka.actor.{Props, Actor, ActorRef, ActorSystem}

case class MidiNote (pitch:Int, vel:Int, dur:Int, at:Int) 

object player {

val system = ActorSystem("Player")
val info = MidiSystem.getMidiDeviceInfo().filter(_.getName == "Gervill").headOption

val device = info.map(MidiSystem.getMidiDevice).getOrElse {
    println("[ERROR] Could not find Gervill synthesizer.")
      sys.exit(1)
  }

val rcvr = device.getReceiver()


/////////////////////////////////////////////////
def note_on (pitch:Int, vel:Int, chan:Int): Unit = {
    val msg = new ShortMessage
    msg.setMessage(NOTE_ON, chan, pitch, vel)
    rcvr.send(msg, -1)
}

def note_off (pitch:Int, chan:Int): Unit = {
    val msg = new ShortMessage
    msg.setMessage(NOTE_ON, chan, pitch, 0)
    rcvr.send(msg, -1)
}



//////////////////////////////////////////////////

class Intrument () extends Actor{
  def receive = {
    case MidiNote(p,v, d, at) => {
      system.scheduler.scheduleOnce ((at) milliseconds) (note_on (p,v,1))
      system.scheduler.scheduleOnce ((at+d) milliseconds) (note_off (p,1))
    }
    case "kill" =>
      println ("bye")
      device.close()
  }
}
  
//////////////////////////////////////////////////

def main(args: Array[String]): Unit = {
    device.open()
    //It is just a test
    val inst = system.actorOf(Props[Intrument], "Instrument")
    inst ! MidiNote(62,100,1000,0)
    println ("control c pour quitter ")
 }
}

