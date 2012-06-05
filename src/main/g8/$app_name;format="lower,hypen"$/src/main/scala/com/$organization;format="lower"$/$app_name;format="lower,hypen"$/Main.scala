
package com.$organization;format="lower"$.$app_name;format="lower,hypen"$

import akka.actor.{Props, ActorSystem}
import cc.spray.{SprayCanRootService, HttpService}
import cc.spray.io.IoWorker
import cc.spray.can.server.HttpServer
import cc.spray.io.pipelines.MessageHandlerDispatch

object $app_name;format="Camel"$ extends App {

  val system = ActorSystem("hello")

  val mainModule = new HelloService {
    implicit def actorSystem = system
  }

  val httpService = system.actorOf(Props(new HttpService(mainModule.helloService)), "http-service-hello")
  val rootService = system.actorOf(Props(new SprayCanRootService(httpService)), "root-service")

  val ioWorker = new IoWorker(system)

  ioWorker.start()

  val sprayCanServer = system.actorOf(
    Props(new HttpServer(ioWorker, MessageHandlerDispatch.SingletonHandler(rootService))),
    "http-server"
  )

  sprayCanServer ! HttpServer.Bind("localhost", 8080)

  system.registerOnTermination {
    ioWorker.stop()
  }

}



